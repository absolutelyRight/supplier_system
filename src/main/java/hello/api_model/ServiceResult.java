package hello.api_model;

public class ServiceResult {
    private Integer code=200;
    private String msg;
    private Object businessObject;
    public static ServiceResult ERROR_RESULT=new ServiceResult(400);
    public static ServiceResult NOT_FOUND=new ServiceResult(404);
    public static ServiceResult NOT_LOGIN=new ServiceResult(401,"未登录，请登录",null);
    public static ServiceResult DB_ERROR=new ServiceResult(500,"数据库更改失败",null);
    public static ServiceResult POWER_ERROR=new ServiceResult(403,"权限不足",null);
    public static ServiceResult SUCCESS=new ServiceResult(200);
    public ServiceResult() {
    }

    public ServiceResult(Integer code, String msg, Object businessObject) {
        this.code = code;
        this.msg = msg;
        this.businessObject = businessObject;
    }

    public ServiceResult(Integer integer) {
        code=integer;
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getBusinessObject() {
        return businessObject;
    }

    public void setBusinessObject(Object businessObject) {
        this.businessObject = businessObject;
    }
}
