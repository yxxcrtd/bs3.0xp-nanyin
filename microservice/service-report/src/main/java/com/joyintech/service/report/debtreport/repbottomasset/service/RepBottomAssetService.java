/*
 * 文件名：RepBottomAssetService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年12月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.debtreport.repbottomasset.service;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.tams.common.base.entity.RepBottomAsset;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.report.debtreport.repbottomasset.dao.MyRepBottomAssetMapper;

/**
 * 底层资产/负债信息报送信息
 * @author fanshuanghu
 * @version 1.0 2018年12月27日
 * @see RepBottomAssetService
 * @since 1.0
 */
@Service
public class RepBottomAssetService extends BaseService<RepBottomAsset>{
    /**
     * 注入Mapper
     */
    @Autowired
    private MyRepBottomAssetMapper myRepBottomAssetMapper;
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
    public Page<RepBottomAsset> findPage(int page, int length, Map<String, Object> map)
        throws Exception {
        return super.queryForPage(page, length, () -> myRepBottomAssetMapper.findPage(map));
    }
    /**
     * 获取对应资管及委外资产行内资产/负债编码（资管计划代码）
     * @return 对应资管及委外资产行内资产/负债编码（资管计划代码）集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepAssetCode(Map<String,Object> map){
        return myRepBottomAssetMapper.getRepAssetCode(map);
    }
    /**
     * 获取底层资产行内资产/负债编码（底层资产代码）
     * @return 底层资产行内资产/负债编码（底层资产代码）集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepBottomCode2(Map<String,Object> map){
        return myRepBottomAssetMapper.getRepBottomCode2(map);
    }
}
