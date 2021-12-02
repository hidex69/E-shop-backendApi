package com.eshop.demo.models.user;

public class UserDto {
    private String login;
    private RoleEntity roleEntity;
    private boolean isValid;

    public UserDto() {
    }

    public UserDto(String login, RoleEntity roleEntity, boolean isValid) {
        this.login = login;
        this.roleEntity = roleEntity;
        this.isValid = isValid;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public RoleEntity getRoleEntity() {
        return roleEntity;
    }

    public void setRoleEntity(RoleEntity roleEntity) {
        this.roleEntity = roleEntity;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "login='" + login + '\'' +
                ", roleEntity=" + roleEntity +
                ", isValid=" + isValid +
                '}';
    }
}
