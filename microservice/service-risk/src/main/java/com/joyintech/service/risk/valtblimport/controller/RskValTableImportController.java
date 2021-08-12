/*
 * 文件名：RskValTableImportController.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.valtblimport.controller;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.annotation.DTXTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.service.risk.valtblimport.dto.PreOccupationChangeDto;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.risk.valtblimport.dto.ValTableImpRiskDto;
import com.joyintech.service.risk.valtblimport.service.RskValTableImportService;

/**
 *  估值表导入
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2019年1月3日
 * @see RskValTableImportController
 * @since 1.0
 */
@RestController("rskValTableImportCtl_dtx")
@RequestMapping("/RskValTableImport")
public class RskValTableImportController {

    @Autowired
    private RskValTableImportService rskValTableImportService;
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("saveValTbl")
    public void saveValTbl(@RequestBody RskValtblAssetSubDto dto){
        rskValTableImportService.saveValTbl(dto);
    }
    
    /**
     * 估值表科目数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectCount")
    public int selectCount(@RequestBody Map<String,Object> paramMap){
        return rskValTableImportService.selectCount(paramMap);
    }

    /**
     * 校验是否存在生效的投向信息
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectGoalCount")
    public int selectGoalCount(@RequestBody Map<String,Object> paramMap){
        return rskValTableImportService.selectGoalCount(paramMap);
    }
    /**
     * 
     * 上农商个性化_估值表导入 
     * 获取估值表文件中各资产的分配明细 
     * add by zhangpei
     * 20201222
     * @param dto
     * @return
     */
    @PostMapping("preOccupationDetail")
    public List<PreOccupationChangeDto> preOccupationDetail(@RequestBody RskValtblAssetSubDto dto){
    	return rskValTableImportService.preOccupationDetail(dto);
    }
    /**
	 * 上农商个性化_估值表导入
	 * 查询需要释放的自主管理信息
	 * add by zhangpei 
	 * 20201223
	 * @param dto
	 * @return
	 */
    @PostMapping("queryNeedFreedInfo")
    public List<PreOccupationChangeDto> queryNeedFreedInfo(@RequestBody RskValtblAssetSubDto dto){
    	return rskValTableImportService.queryNeedFreedInfo(dto);
    }
    /**
	 * 上农商个性化_估值表导入
	 * 根据委托代码，更新指令状态为‘已完成’
	 * add by zhangpei
	 * 20201224
	 * @param list
	 * @return
	 */
    @PostMapping("updateEntrustStatus")
    public int updateEntrustStatus(@RequestBody List<PreOccupationChangeDto> list){
    	return rskValTableImportService.updateEntrustStatus(list);
    }
    /**
     * 插入导入估值表风险实占留痕表
     * @param list
     * @return
     */
    @PostMapping("insertValImpRisk")
    public int insertValImpRisk(@RequestBody List<ValTableImpRiskDto> list){
    	return rskValTableImportService.insertValImpRisk(list);
    }
    /**
     * 删除导入估值表分线实占留痕表的相关数据
     * @param list
     * @return
     */
    @PostMapping("deleteByPrimaryKeyRisk")
    public int deleteByPrimaryKeyRisk(@RequestBody List<ValTableImpRiskDto> list){
    	return rskValTableImportService.deleteByPrimaryKeyRisk(list);
    }
    /**
     * 根据委外组合和导入时间查询导入的指令数据
     * @param dto
     * @return
     */
    @PostMapping("queryImpRiskInfo")
    public List<ValTableImpRiskDto> queryImpRiskInfo(@RequestBody RskValtblAssetSubDto dto){
    	return rskValTableImportService.queryImpRiskInfo(dto);
    }
    
}
