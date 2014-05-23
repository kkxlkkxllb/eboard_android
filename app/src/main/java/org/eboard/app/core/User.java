package org.eboard.app.core;

import android.text.TextUtils;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -7060210544600464481L;

    protected String username;
    protected String phone;
    protected String userid;
    protected String email;
    protected String imgUrl;
    protected String devicetype;
    protected String userrole;
    protected String sessionid;
    protected String channel;
    protected Integer classroom_id;

    protected String sessionToken;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Integer getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(Integer classroom_id) {
        this.classroom_id = classroom_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public String getSessionToken() {
        return sessionToken;
    }

    public void setSessionToken(String sessionToken) {
        this.sessionToken = sessionToken;
    }
}
