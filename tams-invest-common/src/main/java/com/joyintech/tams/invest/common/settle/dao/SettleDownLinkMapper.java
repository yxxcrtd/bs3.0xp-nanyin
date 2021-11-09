package com.joyintech.tams.invest.common.settle.dao;

import com.joyintech.tams.common.base.entity.StlSecRecon;
import com.joyintech.tams.common.base.entity.StlSecReconPara;
import com.joyintech.tams.invest.common.recon.dto.StlCashReconDto;
import com.joyintech.tams.invest.common.recon.dto.StlSecReconDealDto;
import com.joyintech.tams.invest.common.recon.dto.StlSecpReconDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SettleDownLinkMapper {

    List<String> listSysBatchTask(Map<String,Object> param);
}
