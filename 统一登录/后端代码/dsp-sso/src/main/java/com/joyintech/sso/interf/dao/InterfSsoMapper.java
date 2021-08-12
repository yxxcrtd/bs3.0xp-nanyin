package com.joyintech.sso.interf.dao;

import com.joyintech.sso.interf.dto.InterfInstitution;
import com.joyintech.sso.login.dto.SsoUser;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther zhangnian
 * @date 2021/5/12
 */
@Repository
public interface InterfSsoMapper {

    List<InterfInstitution> selectInstitution();

    List<SsoUser> selectUser();

}

