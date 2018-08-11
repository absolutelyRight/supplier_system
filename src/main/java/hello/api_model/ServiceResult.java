package hello.api_model;

public class ServiceResult {
    private Integer code=200;
    private String msg;
    private Object businessObject;
    public static ServiceResult ERROR_RESULT=new ServiceResult(400);
    public static ServiceResult NOT_LOGIN=new ServiceResult(401,"未登录，请登录",null);
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
