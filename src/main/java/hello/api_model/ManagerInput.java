package hello.api_model;

public class ManagerInput {
    //fixme 要么加入角色信息。一个用户名对应一个角色
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
