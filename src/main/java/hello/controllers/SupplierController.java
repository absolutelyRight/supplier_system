package hello.controllers;

import hello.api_model.UserLogin;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hello.api_model.ServiceResult;
import hello.api_model.UserInfo;
import hello.models.AffixEntity;
import hello.models.SupplierEntity;
import hello.service.FileService;
import hello.service.SupplierService;
import leap.core.annotation.Inject;
import leap.lang.json.JSON;
import leap.web.action.ControllerBase;
import leap.web.annotation.Path;
import leap.web.annotation.PathParam;
import leap.web.annotation.http.DELETE;
import leap.web.annotation.http.GET;
import leap.web.annotation.http.POST;

public class SupplierController extends ControllerBase {

    @Inject
    private SupplierService supplierService;
    @Inject
    private FileService fileService;
	private final int[] EDITABLE_TYPE = new int[] { UserInfo.DEMAND, UserInfo.MANAGER, UserInfo.ROOT };

	public SupplierController() {
		this.supplierService = new SupplierService();
		this.fileService = new FileService();
	}

	@POST("login")
    public ServiceResult login(UserLogin input) {
        //邮箱，用户ID都可以登陆
        final ServiceResult output = supplierService.SupplierLogin(input);
        if (output.getCode() == 200) {
            request().getServletRequest().getSession().setAttribute("supplier", output.getBusinessObject());
        }
        return output;
    }

    //校验是否存在该用户名,不存在则返回200
    @Path("notexist")
    public ServiceResult notExist(String input) {
        return supplierService.SupplierIsNotExist(input);
    }

    @POST("register")
    public ServiceResult registerSupplier(SupplierEntity supplierEntity) {
        final ServiceResult output=supplierService.SupplierRegister(supplierEntity);
        if (output.getCode()==200){
            request().getServletRequest().getSession().setAttribute("supplier", output.getBusinessObject());
        }
        return output;
    }
    @POST("update")
    public ServiceResult updateSupplier(SupplierEntity supplier){
        final SupplierEntity supplierEntity = (SupplierEntity) request().getServletRequest().getSession().getAttribute("supplier");
        if (supplierEntity == null) {
            return ServiceResult.NOT_LOGIN;
        }
        ServiceResult serviceResult=supplierService.SupplierUpdate(supplierEntity,supplier);
        if (serviceResult.getCode()==200){
            request().getServletRequest().getSession().setAttribute("supplier",serviceResult.getBusinessObject());
        }
        return serviceResult;
    }
    
    @POST("add")
	public ServiceResult addSupplier(SupplierEntity supplier) throws Exception {
		final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return ServiceResult.NOT_LOGIN;
		}
		if (Arrays.binarySearch(EDITABLE_TYPE, user.getType()) > -1) {
			List<AffixEntity> affixes = fileService.uploadFiles(request().getServletRequest());
			if (!affixes.isEmpty()) {
				supplier.setSAffix(JSON.encode(affixes.stream().map(a -> a.getId()).collect(Collectors.toList())));
			}
			return supplierService.Add(supplier);
		} else {
			return ServiceResult.POWER_ERROR;
		}
	}
    
    @POST("/admin/update")
	public ServiceResult modifySupplier(SupplierEntity supplier) throws Exception {
		final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return ServiceResult.NOT_LOGIN;
		}
		if (Arrays.binarySearch(EDITABLE_TYPE, user.getType()) > -1) {
			List<AffixEntity> affixes = fileService.uploadFiles(request().getServletRequest());
			if (!affixes.isEmpty()) {
				supplier.setSAffix(JSON.encode(affixes.stream().map(a -> a.getId()).collect(Collectors.toList())));
			}
			return supplierService.Modify(supplier);
		} else {
			return ServiceResult.POWER_ERROR;
		}
	}

	@POST("page/{index}")
	public ServiceResult listSupplierPage(@PathParam int index, String type, String name) {
		/*final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return ServiceResult.NOT_LOGIN;
		}
		if (Arrays.binarySearch(EDITABLE_TYPE, user.getType()) > -1) {*/
			return supplierService.PageSearch(type, name, index, 5);
		/*} else {
			return ServiceResult.POWER_ERROR;
		}*/
	}
	
    @POST("unchecked/{index}")
    public ServiceResult listUncheckedSupplierPage(@PathParam int index,String name,
    		Date regTimeInf,Date regTimeSup) {
    	final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return ServiceResult.NOT_LOGIN;
		}
		if (Arrays.binarySearch(EDITABLE_TYPE, user.getType()) > -1) {
			return supplierService.UncheckedPageSearch(name,regTimeInf,regTimeSup, index, 5);
		} else {
			return ServiceResult.POWER_ERROR;
		}
    }

	@DELETE("{id}")
	public ServiceResult removeSupplier(@PathParam String id) {
		final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return ServiceResult.NOT_LOGIN;
		}
		if (Arrays.binarySearch(EDITABLE_TYPE, user.getType()) > -1) {
			return supplierService.Remove(id);
		} else
			return ServiceResult.POWER_ERROR;
	}
	
	@GET("{id}")
	public ServiceResult getSupplier(@PathParam String id) {
		final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return ServiceResult.NOT_LOGIN;
		}
		if (Arrays.binarySearch(EDITABLE_TYPE, user.getType()) > -1) {
			return supplierService.get(id);
		} else
			return ServiceResult.POWER_ERROR;
	}
	
	@GET("check/{id}")
	public ServiceResult checkSupplier(@PathParam String id,String refuseReason) {
		final UserInfo user = (UserInfo) request().getServletRequest().getSession().getAttribute("user");
		if (user == null) {
			return ServiceResult.NOT_LOGIN;
		}
		if (Arrays.binarySearch(EDITABLE_TYPE, user.getType()) > -1) {
			return supplierService.check(id, refuseReason);
		} else
			return ServiceResult.POWER_ERROR;
	}
	
}
