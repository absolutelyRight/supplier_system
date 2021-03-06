package hello.service;

import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.MapMaker;

import hello.api_model.ServiceResult;
import hello.api_model.UserLogin;
import hello.models.SupplierEntity;
import hello.util.CommonTool;
import hello.util.MailHepler;
import leap.core.annotation.Bean;
import leap.orm.query.PageResult;

@Bean
public class SupplierService {
    private Logger log;
    private Map<String,String> verifyCodeCache=new MapMaker().concurrencyLevel(32).expiration(60, TimeUnit.SECONDS).makeMap();
	private MailHepler mailHelper = new MailHepler("smtp.163.com", "Jacksoh@163.com").setAuth("Jacksoh@163.com", "smtpat163");
    private Random random = new Random();
		
    public SupplierService() {
        log = LoggerFactory.getLogger(this.getClass());
    }

    public ServiceResult SupplierLogin(UserLogin input){
        log.info("SupplierLogin");
        final ServiceResult output = new ServiceResult();
        if (input.getUsername() == "" || input.getPassword() == ""||!CommonTool.isEmil(input.getUsername())) {
            output.setCode(404);
            output.setMsg(UserService.PARAMETER_EMPTY);
        }
        log.info(input.getUsername());
        //只允许邮箱登陆
        final SupplierEntity supplier = SupplierEntity.findBy("S_EMAIL", input.getUsername());

        if (supplier == null || (supplier.getSIsdelete() != null && supplier.getSIsdelete() == 1)) {
            //用户名不存在
            output.setCode(404);
            output.setMsg(UserService.USER_NOT_EXIST);
            return output;
        }
        if (!supplier.getSPassword().equals(DigestUtils.md5Hex(input.getPassword()))) {
            //用户密码不匹配
            output.setCode(401);
            output.setMsg(UserService.USER_ERROR);
            return output;
        }
        supplier.setSPassword("");
        output.setBusinessObject(supplier);
        return output;
    }
    
    /**
     * 
     * @param email
     * @return {code:400,msg:"邮箱格式不正确"} when email format error<br>
     * {code:500} when email send error<br>
     * {code:200} when success
     */
	public ServiceResult SendMailVerifyCode(String email) {
		if (!CommonTool.isEmil(email)) {
			return new ServiceResult(400, "邮箱格式不正确", null);
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 6; i++) {
			sb.append(random.nextInt(10));
		}
		String code = sb.toString();
		String errMsg = mailHelper.send("供应商平台", Arrays.asList(email), "注册验证码", "您的邮箱验证码为" + code + ",请不要泄露。");
		if (errMsg == null) {
			verifyCodeCache.put(email, code);
			return ServiceResult.SUCCESS;
		} else {
			return new ServiceResult(500);
		}
	}
    
	/**
	 * 
	 * @param supplier
	 * @param verifyCode
	 * @return {code:404,msg:"验证码错误或已失效"} when email verify fail<br>
     * {code:500} when save error<br>
     * {code:200} when success
	 */
    public ServiceResult SupplierRegister(SupplierEntity supplier,String verifyCode){
        final ServiceResult result=SupplierIsNotExist(supplier.getSEmail());
        if (result.getCode()==200){
        	String code = verifyCodeCache.get(supplier.getSEmail());
			if(code==null||!code.equals(verifyCode)){
				result.setCode(404);
                result.setMsg("验证码错误或已失效");
                return result;
			}
            supplier.setSId(CommonTool.getIdUUID(SupplierEntity.class.getName()));
            supplier.setSCheckStatus(0);

            final SupplierEntity supplierEntity=supplier.save();
            if(supplierEntity!=null){
            	supplierEntity.setSPassword(null);
                result.setBusinessObject(supplierEntity);
            }else {
                result.setCode(500);
                result.setMsg("未保存成功");
            }
        }
        return result;
    }
    public ServiceResult SupplierIsNotExist(String input){
        log.info("SupplierIsExist");
        final ServiceResult output = new ServiceResult();
        if (input == ""||!CommonTool.isEmil(input)) {
            output.setCode(404);
            output.setMsg(UserService.PARAMETER_EMPTY);
        }
        SupplierEntity supplierEntity=SupplierEntity.findBy("S_EMAIL", input);
        if (supplierEntity==null){
            output.setCode(200);
            output.setMsg("该用户不存在");
        }else {
            output.setCode(401);
            output.setMsg("该用户已存在");
        }
        return output;

    }
    public ServiceResult  SupplierUpdate(SupplierEntity nowSupplier,SupplierEntity futureSupplier){
        final SupplierEntity s1=SupplierEntity.findBy("S_EMAIL", futureSupplier.getSEmail());
        if(s1!=null){
            return new ServiceResult(401,"该邮箱已存在",null);
        }
        futureSupplier.setSId(nowSupplier.getSId());
        futureSupplier.setSStartTime(nowSupplier.getSStartTime());
        futureSupplier.setSCheckStatus(0);
        futureSupplier.update();
        return new ServiceResult(200,"",futureSupplier);
    }
    
	public ServiceResult Remove(String id) {
		SupplierEntity user = SupplierEntity.findOrNull(id);
		if (user == null) {
			return ServiceResult.NOT_FOUND;
		}
		// 逻辑删除
		user.setSIsdelete(1);
		user.save();
		return ServiceResult.SUCCESS;
	}

	public ServiceResult Add(SupplierEntity supplier) {
		final ServiceResult result = SupplierIsNotExist(supplier.getSEmail());
		if (result.getCode() == 200) {
			SupplierEntity record = new SupplierEntity();
			record.setSId(CommonTool.getIdUUID(SupplierEntity.class.getName()));
			record.setSCheckStatus(2);
			Date now = new Date(new java.util.Date().getTime());
			record.setSCheckTime(now);
			record.setSIsdelete(0);

			record.setSAffix(supplier.getSAffix());
			record.setSAbility(supplier.getSAbility());
			record.setSAccountName(supplier.getSAccountName());
			record.setSFax(supplier.getSFax());
			record.setSFullName(supplier.getSFullName());
			record.setSShortName(supplier.getSShortName());
			record.setSUrl(supplier.getSUrl());
			record.setSPassword(DigestUtils.md5Hex(supplier.getSPassword()));
			record.setSPhone(supplier.getSPhone());
			record.setSAddress(supplier.getSAddress());
			record.setSPhone(supplier.getSPhone());
			record.setSDeputy(supplier.getSDeputy());
			record.setSEmail(supplier.getSEmail());
			record.setSContact(supplier.getSContact());
			record.setSRegistTime(now);
			record.save();
			return ServiceResult.SUCCESS;
		} else
			return result;
	}
	
	public ServiceResult Modify(SupplierEntity supplier) {
		if (supplier.getSId() == null)
			return ServiceResult.NOT_FOUND;
		final ServiceResult result = SupplierIsNotExist(supplier.getSEmail());
		if (result.getCode() == 200) {
			SupplierEntity record = new SupplierEntity();
			record.setSCheckStatus(2);
			record.setSCheckTime(new Date(new java.util.Date().getTime()));

			record.setSAffix(supplier.getSAffix());
			record.setSAbility(supplier.getSAbility());
			record.setSAccountName(supplier.getSAccountName());
			record.setSFax(supplier.getSFax());
			record.setSFullName(supplier.getSFullName());
			record.setSShortName(supplier.getSShortName());
			record.setSUrl(supplier.getSUrl());
			if(supplier.getSPassword()!=null&&supplier.getSPassword().length()>0)
				record.setSPassword(DigestUtils.md5Hex(supplier.getSPassword()));
			record.setSPhone(supplier.getSPhone());
			record.setSAddress(supplier.getSAddress());
			record.setSPhone(supplier.getSPhone());
			record.setSDeputy(supplier.getSDeputy());
			record.setSEmail(supplier.getSEmail());
			record.setSContact(supplier.getSContact());
			record.save();
			return ServiceResult.SUCCESS;
		} else
			return result;
	}
    
	public ServiceResult PageSearch(String type, String name, int index, int pageSize) {
		String key = "supplier.all";
		PageResult<SupplierEntity> page = SupplierEntity.<SupplierEntity>query(key).param("name", name)
				.param("type",type)
				.pageResult(index, pageSize);
		Map<String, Object> pageObj = new HashMap<>(4);
		pageObj.put("page", page);
		pageObj.put("list", page.list());
		return new ServiceResult(200, "", pageObj);
	}

	public ServiceResult UncheckedPageSearch(String name,Date regTimeInf,Date regTimeSup, int index, int pageSize) {
		String key = "supplier.unchecked";
		PageResult<SupplierEntity> page = SupplierEntity.<SupplierEntity>query(key)
				.param("name", name)
				.param("regTimeInf", regTimeInf)
				.param("regTimeSup", regTimeSup)
				.pageResult(index, pageSize);
		Map<String, Object> pageObj = new HashMap<>(4);
		pageObj.put("page", page);
		pageObj.put("list", page.list());
		return new ServiceResult(200, "", pageObj);
	}
	
	public ServiceResult get(String id){
		SupplierEntity supplier = SupplierEntity.findOrNull(id);
		if(supplier==null)
			return ServiceResult.NOT_FOUND;
		else{
			supplier.setSPassword(null);
			return new ServiceResult(200, null, supplier);
		}
	}
	
	public ServiceResult check(String id,String refuseReason){
		SupplierEntity supplier = SupplierEntity.findOrNull(id);
		if(supplier==null||supplier.getSCheckStatus().intValue()!=1)
			return ServiceResult.NOT_FOUND;
		else{
			SupplierEntity record=new SupplierEntity();
			record.setSId(id);
			if(refuseReason==null||refuseReason.length()==0){
				record.setSCheckStatus(2);
			}
			else{
				record.setSCheckStatus(3);
				record.setSReason(refuseReason);
			}
			record.setSCheckTime(new Date(new java.util.Date().getTime()));
			record.save();
			return ServiceResult.SUCCESS;
		}
	}
}
