package com.sangwookim.domain;

import javax.validation.constraints.NotNull;
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

    @Size(min=2, max=5)
    private String name;

    private Timestamp created_at;
    private Timestamp updated_at;

    private boolean remember_me;
    private String remember_id;

    public void setRemember_id(String remember_id) {
        this.remember_id = remember_id;
    }

    public String getRemember_id() {
        return remember_id;
    }

    public void setRemember_me(boolean remember_me) {
        this.remember_me = remember_me;
    }

    public boolean isRemember_me() {
        return remember_me;
    }

    public void setPassword_prev(String password_prev) {
        this.password_prev = password_prev;
    }

    public String getPassword_prev() {
        return password_prev;
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
