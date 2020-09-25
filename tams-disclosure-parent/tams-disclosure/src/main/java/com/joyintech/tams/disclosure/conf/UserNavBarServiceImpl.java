package com.joyintech.tams.disclosure.conf;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.joyintech.framework.navbar.UserNavBarService;
import com.joyintech.framework.navbar.UserNavbar;

/**
 * UserNavBarService实现类 自定义用户菜单，解决注销时无法退出至统一认证登录页
 * 
 * @author Administrator
 */
@Controller
public class UserNavBarServiceImpl implements UserNavBarService {
    /**
     * 是否开启SSO认证
     */
    @Value("${shiro.ssoEnable}")
    private boolean ssoEnable;
    
    @Override
    public List<UserNavbar> getUserNavbar() {
        List<UserNavbar> list = new ArrayList<>();
        if(ssoEnable) {
            UserNavbar bar = new UserNavbar();
            bar.setId("ssoLogOut");
            bar.setName("注销登录");
            bar.setAction("ssoLogOut()");
            list.add(bar);
        }
        return list;
    }

}
