package com.sangwookim.domain;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

public class User {
    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9]*")
    private String id;

    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9,!,@,#,$,%,^,&,*,?,_,~]*")
    private String password_prev;

    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9,!,@,#,$,%,^,&,*,?,_,~]*")
    private String password;

    @Size(min=4, max=20)
    @Pattern(regexp = "[a-zA-Z0-9,!,@,#,$,%,^,&,*,?,_,~]*")
    private String password_confirm;

    @Size(min=2, max=4)
    @Pattern(regexp = "[가-힣]*")
    private String name;

    private Timestamp created_at;
    private Timestamp updated_at;

    private boolean user_login;
    private boolean user_exist;

    public User(){
        this.user_login = false;
        this.user_exist = false;
    }

    public void setPassword_prev(String password_prev) {
        this.password_prev = password_prev;
    }

    public String getPassword_prev() {
        return password_prev;
    }

    public boolean isUser_exist() {
        return user_exist;
    }

    public void setUser_exist(boolean user_exist) {
        this.user_exist = user_exist;
    }

    public boolean isUser_login() {
        return user_login;
    }

    public void setUser_login(boolean user_login) {
        this.user_login = user_login;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public String getPassword_confirm() {
        return password_confirm;
    }

    public void setPassword_confirm(String password_confirm) {
        this.password_confirm = password_confirm;
    }
}
