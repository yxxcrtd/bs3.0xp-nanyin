package com.joyintech.service.riskrdsinf.http;

import com.joyintech.framework.security.User;

import java.util.Date;

public class InterfUser implements User {
    @Override
    public String getUid() {
        return null;
    }

    @Override
    public void setUid(String s) {

    }

    @Override
    public String getUserName() {
        return null;
    }

    @Override
    public String getRealName() {
        return null;
    }

    @Override
    public void setUserName(String s) {

    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public void setPassword(String s) {

    }

    @Override
    public String getMailAddr() {
        return null;
    }

    @Override
    public void setMailAddr(String s) {

    }

    @Override
    public void setSalt(String s) {

    }

    @Override
    public String getSalt() {
        return null;
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    public boolean isLocked() {
        return false;
    }

    @Override
    public boolean isExpired() {
        return false;
    }

    @Override
    public void setLastPassDate(Date date) {

    }

    @Override
    public Date getLastPassDate() {
        return null;
    }

    @Override
    public String getDeptCode() {
        return null;
    }

    @Override
    public String getOrgCode() {
        return null;
    }
}
