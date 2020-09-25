package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.servermanage.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.servermanage.dto.ServerManage001Dto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 外汇交易中心服务器管理业务层
 *
 * @author zcf
 * @version 1.0
 * @date 2020-01-13
 */
@Service
public class ServerManage001Service extends BaseService<ServerManage001Dto> {


    /**
     * 分页查询
     * @param para
     * @return
     */
    public Page<ServerManage001Dto> selectPageData(Map<String, Object> para) {
        Page<ServerManage001Dto> page = new Page<>();
        //样例 start
        List<ServerManage001Dto> list = new ArrayList<>();
        ServerManage001Dto dto = new ServerManage001Dto();
        dto.setFileName("文件1.log");
        dto.setFileSize("12M");
        dto.setFileUrl("/home/");
        list.add(dto);
        page.setRoot(list);
        //end
        return page;
    }

}
