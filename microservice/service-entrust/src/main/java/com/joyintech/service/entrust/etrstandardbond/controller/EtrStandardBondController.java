
package com.joyintech.service.entrust.etrstandardbond.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBond003Dto;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondInDto;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondSearchDto;
import com.joyintech.service.entrust.etrstandardbond.service.EtrStandardBondService;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;

/**
 * 回购交易微服务
 */
@RestController("EtrStandardBondCtr_dtx")
@RequestMapping("/EtrStandardBond")
public class EtrStandardBondController {

    @Autowired
    private EtrStandardBondService etrStandardBondService;

    /**
     * 质押券管理查询画面，查询质押券信息列表
     * 质押券入库画面，查询可质押债券列表
     *
     * @param page   page
     * @param length length
     * @param param  参数
     * @return Page<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectSearchDataList/{page}/{length}")
    public Page<EtrStandardBondSearchDto> selectForPage(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length)
            throws Exception {
        return etrStandardBondService.selectSearchDataList(page, length, param);
    }

    /**
     * 质押券入库画面，查询可质押债券列表
     *
     * @param param 参数
     * @return Page<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectAvaPledgeBondList")
    public List<EtrStandardBondInDto> selectAvaPledgeBondList(@RequestBody Map<String, Object> param)
            throws Exception {
        return etrStandardBondService.selectAvaPledgeBondList(param);
    }
    
    /**
     * 质押券入库画面，查询已入库债券列表
     *
     * @param param 参数
     * @return Page<TrdStandardBondDto>
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectPledgeBondList")
    public List<EtrStandardBondInDto> selectPledgeBondList(@RequestBody Map<String, Object> param)
            throws Exception {
        return etrStandardBondService.selectPledgeBondList(param);
    }

    /**
     * 质押券入库画面，保存处理
     *
     * @param param 参数
     * @return inSaveCount
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("inSave")
    public Long inSave(@RequestBody EtrStandardBondInDto dto) throws Exception {
        return etrStandardBondService.inSave(dto);
    }

    /**
     * 质押券出库,删除即可
     *
     * @param param 参数
     * @return outSaveCount
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("outSave")
    public Long outSave(@RequestBody EtrStandardBondSearchDto dto) throws Exception {
        return etrStandardBondService.outSave(dto);
    }
    
    /**
     * 优先级设置明细查询，填充画面列表
     *
     */
    @RequestMapping("selectEtrSecPriDetailList")
    public EtrStandardBond003Dto selectEtrSecPriDetailList() throws Exception {
        return etrStandardBondService.selectEtrSecPriDetailList();
    }
    
    /**
     * 优先级设置入库，先删后插
     *
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("etrSecPrioritySave")
    public Long etrSecPrioritySave(@RequestBody EtrStandardBond003Dto dto) throws Exception {
        return etrStandardBondService.etrSecPrioritySave(dto);
    }
    
    /**
     * 自动导入质押券 查询
     *
     * @param param 参数
     * @return list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectAutoPledge")
    public List<PledgeBondInfoDto> selectAutoPledge(@RequestBody Map<String, Object> param) throws Exception {
        return etrStandardBondService.selectAutoPledge(param);
    }
    
    /**
     * 查询证券托管户：联想框文本框
     *
     * @param param 画面参数
     * @return 证券托管户：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    public List<SuggestDto> getSecManageAcctId(@RequestBody Map<String, Object> param) {
        return etrStandardBondService.getSecManageAcctId(param);
    }
    
    /**
     * 查询登录用户是否满足指定的角色岗位 
     */
    @RequestMapping("selectUserRolePosList")
    public Long selectUserRolePosList(@RequestBody Map<String, Object> param){
        return etrStandardBondService.selectUserRolePosList(param) ;
    }
}
