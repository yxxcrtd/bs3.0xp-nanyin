/*
 * 文件名：RepRaiseSumRegistService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repraisesumregist.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.joyintech.tams.common.base.entity.RepRaiseSum;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repraisesumregist.dao.RepRaiseSumRegistMapper;

/**
 * 募集期总量登记
 * @author fanshuanghu
 * @version 1.0 2018年12月28日
 * @see RepRaiseSumRegistService
 * @since 1.0
 */
@Service
public class RepRaiseSumRegistService extends BaseService<RepRaiseSum>{
    
    @Autowired
    private RepRaiseSumRegistMapper repRaiseSumRegistMapper;
    /**
     * 
     * 页面查询
     * <功能详细描述>
     * @param page int
     * @param length int
     * @param pageParam Map<String, Object>
     * @return Page<RepFinProductRegistCodeMngDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<RepRaiseSum> findPage(int page, int length, Map<String, Object> map)
        throws Exception {
        return super.queryForPage(page, length, () -> repRaiseSumRegistMapper.findPage(map));
    }
    /**
     * 获取金融产品代码下拉框集合
     * @return 金融产品代码下拉框集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getProductCode(Map<String,Object> map){
        return repRaiseSumRegistMapper.getProductCode(map);
    }
}
