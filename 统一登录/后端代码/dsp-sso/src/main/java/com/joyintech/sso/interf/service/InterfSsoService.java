package com.joyintech.sso.interf.service;

import com.joyintech.sso.interf.dao.InterfSsoMapper;
import com.joyintech.sso.interf.dto.InterfInstitution;
import com.joyintech.sso.login.dto.SsoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther zhangnian
 * @date 2021/5/12
 */
@Service
public class InterfSsoService {

    @Autowired
    private InterfSsoMapper interfSsoMapper;

    public List<InterfInstitution> selectInstitution(){
        return interfSsoMapper.selectInstitution();
    }

    public List<SsoUser> selectUser(){
        return interfSsoMapper.selectUser();
    }

}

