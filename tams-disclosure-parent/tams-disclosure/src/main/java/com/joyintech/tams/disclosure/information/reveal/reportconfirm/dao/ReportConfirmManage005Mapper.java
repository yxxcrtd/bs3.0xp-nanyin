package com.joyintech.tams.disclosure.information.reveal.reportconfirm.dao;

import com.joyintech.system.base.entity.SysUser;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ReportConfirmManage005Mapper {
    List<SysUser>getUserList(Map<String, Object> param);
}
