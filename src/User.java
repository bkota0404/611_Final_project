public class User {

    private UserRoles userRole;
    private String userName;
    private String password;
    private int userId;
    private String name;

    public User(int id, String name, String userName, String password, UserRoles userRole){
        this.setName(name);
        this.setUserName(userName);
        this.setUserId(id);
        this.setPassword(password);
        this.setUserRole(userRole);
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public UserRoles getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRoles userRole) {
        this.userRole = userRole;
    }


}
