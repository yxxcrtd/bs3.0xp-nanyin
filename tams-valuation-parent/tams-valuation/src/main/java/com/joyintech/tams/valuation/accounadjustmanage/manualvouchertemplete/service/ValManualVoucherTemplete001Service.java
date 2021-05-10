/*
 * 文件名：ValManualVoucher001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001SearchDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 手工凭证模板查询service
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ValManualVoucherTemplete001Service extends CommonExportService<ValManualVoucherTemplete001Dto> implements BaseProcessService<ValManualVoucherTemplete001Dto> {

    /**
     * 根据条件获取表格信息
     * 
     * @param dto 筛选条件
     * @return 表格信息
     * @throws Exception 
     */
    public Page<ValManualVoucherTemplete001Dto> findPage(ValManualVoucherTemplete001SearchDto dto) throws Exception {
        Map<String, ValManualVoucherTemplete001SearchDto> map = new HashMap<String, ValManualVoucherTemplete001SearchDto>();
        map.put("dto", dto);
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete001/findPage",map, ValManualVoucherTemplete001Dto.class);
    }

    /**
     *
     * 根据条件查询全部信息
     * @param dto 筛选条件
     * @return 全部信息
     * @see
     * @since 1.0
     */
    public List<ValManualVoucherTemplete001Dto> selectAll(ValManualVoucherTemplete001SearchDto dto){
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete001/selectAll",dto, ValManualVoucherTemplete001Dto.class);
    }


    public boolean delete(ValManualVoucherTemplete001Dto dto) {
        List<ValManualVoucherTemplete001Dto> dtolist = dto.getSelectedItem();
        List<String> tmpIdList = new ArrayList<>();
        if(!dtolist.isEmpty()){
            for(ValManualVoucherTemplete001Dto entity : dtolist){
                tmpIdList.add(entity.getTmpCode());
            }
            return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValManualVoucherTemplete003/deleteVouch", tmpIdList, boolean.class);
        }
        return false;

    }

    @Override
    public void doComplete(ValManualVoucherTemplete001Dto valManualVoucherTemplete001Dto, OperationType operationType) {

    }
}
