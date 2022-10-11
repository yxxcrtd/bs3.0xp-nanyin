package com.joyintech.tams.disclosure.information.reveal.reportconfirm.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.system.base.entity.SysUser;
import com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao.ReportConfirmManage005Mapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportConfirmManage005Service extends BaseService {
    @Autowired
    private ReportConfirmManage005Mapper reportConfirmManage005Mapper;

    public Page<SysUser> findPage(Map<String, Object> param){
        Page<SysUser>page = new Page<SysUser>();
        List<SysUser>userList=reportConfirmManage005Mapper.getUserList(param);
        if(!CollectionUtils.isEmpty(userList)){
            page.setRoot(userList);
        }
        return page;
    }
}
