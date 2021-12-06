public enum UserRoles {
    MANAGER("MANAGER"),
    EMPLOYEE("EMPLOYEE"),
    CUSTOMER("CUSTOMER");

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
