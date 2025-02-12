package pojo;

import enums.UserRole;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private String userName;
    private String password;
    private String email;
    private String name;
    private String surname;
    private String patronymic;
    private Boolean isBanned;
    private UserRole userRole;

    public User() {
        userRole = UserRole.user;
        isBanned = false;
    }

    public User(String userName, String password, String email, String surname, String name, String patronymic) {
        this();
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public User(String userName, String password, String email, String surname, String name, String patronymic, UserRole userRole) {
        this.userRole = userRole;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
    }

    public User(String userName, String password) {
        this();
        this.userName = userName;
        this.password = password;
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

    public void setName(String name) {
        this.name = name;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public void setPattername(String pattername) {
        this.patronymic = pattername;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getPattername() {
        return patronymic;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsBanned() {
        return isBanned;
    }

    public void setIsBanned(Boolean banned) {
        isBanned = banned;
    }
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
