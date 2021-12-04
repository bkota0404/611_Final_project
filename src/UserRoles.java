public enum UserRoles {
    MANAGER("Manager"),
    EMPLOYEE("Employee"),
    CUSTOMER("Customer");

    private String userRoles;

    UserRoles(String userRoles) {
        this.setUserRoles(userRoles);
    }

    public String getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(String userRoles) {
        this.userRoles = userRoles;
    }

}
