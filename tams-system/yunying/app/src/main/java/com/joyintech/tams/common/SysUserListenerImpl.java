package com.joyintech.tams.common;

import com.joyintech.system.base.dao.SysHomePageConfigMapper;
import com.joyintech.system.base.dao.SysUserHomePageConfigMapper;
import com.joyintech.system.base.entity.SysUser;
import com.joyintech.system.main.dao.MainMapper;
import com.joyintech.system.main.dto.SettingManagerDto;
import com.joyintech.system.user.SysUserListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SysUserListenerImpl implements SysUserListener {

    private static final Logger logger = LoggerFactory.getLogger(SysUserListenerImpl.class);

    /**
     * 系统主页面用Dao
     */
    @Autowired
    private MainMapper mainMapper;

    /**
     * 用户主页配置Dao
     */
    @Autowired
    private SysUserHomePageConfigMapper userHomePageConfigMapper;

    @Autowired
    private SysHomePageConfigMapper SysHomePageConfigMapper;

    @Override
    public void onCreated(SysUser user) {
        SettingManagerDto settingManagerDto =new SettingManagerDto();
        settingManagerDto.setUserId(user.getUserName());
        settingManagerDto.setConfigId("17bed495075840329fe80bcbcbe51dea");
        mainMapper.deleteUserHomePageConfig(settingManagerDto);

        settingManagerDto.setOrderNo(0L);
        settingManagerDto.setVisibility("01");
        userHomePageConfigMapper.insert(settingManagerDto);
    }

    @Override
    public void onDeleted(String userId) {}

    @Override
    public void onModified(SysUser user){}

    @Override
    public void onLocked(String userId) {}

    @Override
    public void onUnlocked(String userId) {}

    @Override
    public void passwordReset(String userId) {}

    @Override
    public void updatePassword(String userId, String newPassword){}
}
