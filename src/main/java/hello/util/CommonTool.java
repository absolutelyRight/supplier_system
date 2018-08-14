package hello.util;

import java.util.UUID;

public class CommonTool {
    //时间需与前端统一数据格式
    public static final String TIME_FORMAT="yyyy-MM-dd";
    static public String getBingoLoginTableName(String str){
        //邮箱登录
        if (str.matches("[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)+"))
            return "U_EMAIL";
        //登录的是
        if (str.matches("^((13[0-9])|(15[0-3, 5-9])|(18[0,2,3,5-9])|(17[0-8])|(147))\\d{8}$")){
            return "U_PHONE";
        }
        return "U_ACCOUNT";
    }
    static public boolean isEmil(String str){
        //只允许邮箱登录
        if (str.matches("[a-zA-Z0-9_]+@[a-zA-Z0-9_]+(\\.[a-zA-Z0-9_]+)+"))
            return true;
        return false;
    }
    static public String getIdUUID(String name){
        UUID uuid = UUID.randomUUID();
        name=name.substring(0,name.indexOf("E"));
        return name+"-"+uuid.toString().substring(0,10);
    }
}
