package com.joyintech.service.entrust.etribrepo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.service.entrust.etribrepo.dto.EtrIbRepoLib001Dto;
import com.joyintech.service.entrust.etribrepo.service.EtrIbRepoLib001Service;
import com.joyintech.service.entrust.etrstandardbond.dto.EtrStandardBondInDto;

@RestController("EtrIbRepoLib001_dtx")
@RequestMapping("EtrIbRepoLib001")
public class EtrIbRepoLib001Controller {

	@Autowired
	private EtrIbRepoLib001Service etrIbRepoLib001Service;
	
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
    public List<EtrIbRepoLib001Dto> selectAvaPledgeBondList(@RequestBody Map<String, Object> param)
            throws Exception {
        return etrIbRepoLib001Service.selectAvaPledgeBondList(param);
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
    public List<EtrIbRepoLib001Dto> selectPledgeBondList(@RequestBody Map<String, Object> param)
            throws Exception {
        return etrIbRepoLib001Service.selectPledgeBondList(param);
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
    public Long inSave(@RequestBody EtrIbRepoLib001Dto dto) throws Exception {
        return etrIbRepoLib001Service.inSave(dto);
    }
}
