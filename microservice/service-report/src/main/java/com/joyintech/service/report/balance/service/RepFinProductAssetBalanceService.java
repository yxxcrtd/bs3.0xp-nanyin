/*
 * 文件名：RepFinProductAssetBalanceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年3月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.report.balance.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.report.balance.dao.RepFinProductAssetBalanceMapper;
import com.joyintech.service.report.balance.dto.RepFinProductAssetBalanceDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 资产余额表
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年3月5日
 * @see RepFinProductAssetBalanceService
 * @since 1.0
 */
@Service
public class RepFinProductAssetBalanceService extends BaseService<RepFinProductAssetBalanceDto>{

    @Autowired
    private RepFinProductAssetBalanceMapper repFinProductAssetBalanceMapper;
    
    public Page<RepFinProductAssetBalanceDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
          if(StringUtil.equals(pageParam.get("showLevel"), DictCodeListExt1.层级信息_一级.getValue())){
              return super.queryForPage(page, length, () -> repFinProductAssetBalanceMapper.queryForPage1(pageParam)); 
          }else if(StringUtil.equals(pageParam.get("showLevel"), DictCodeListExt1.层级信息_二级.getValue())){
              return super.queryForPage(page, length, () -> repFinProductAssetBalanceMapper.queryForPage2(pageParam)); 
          }else if(StringUtil.equals(pageParam.get("showLevel"), DictCodeListExt1.层级信息_三级.getValue())){
              return super.queryForPage(page, length, () -> repFinProductAssetBalanceMapper.queryForPage3(pageParam)); 
          }
          return super.queryForPage(page, length, () -> repFinProductAssetBalanceMapper.queryForPage4(pageParam)); 
          
    }
    
    /**
     * 汇总(到处)
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<RepFinProductAssetBalanceDto> selectAllListSum(Map<String, Object> pageParam){
        if(StringUtil.equals(pageParam.get("showLevel"), DictCodeListExt1.层级信息_一级.getValue())){
            return repFinProductAssetBalanceMapper.queryForPage1(pageParam); 
        }else if(StringUtil.equals(pageParam.get("showLevel"), DictCodeListExt1.层级信息_二级.getValue())){
            return repFinProductAssetBalanceMapper.queryForPage2(pageParam); 
        }else if(StringUtil.equals(pageParam.get("showLevel"), DictCodeListExt1.层级信息_三级.getValue())){
            return repFinProductAssetBalanceMapper.queryForPage3(pageParam); 
        }
        return repFinProductAssetBalanceMapper.queryForPage4(pageParam);
    }
    
    /**
     * 底层明细查询
     * <功能详细描述>
     * @param pageParam
     * @return
     * @see
     * @since 1.0
     */
    public List<RepFinProductAssetBalanceDto> selectAllList(Map<String, Object> pageParam){
        return repFinProductAssetBalanceMapper.selectAllList(pageParam);
    }
    
}
