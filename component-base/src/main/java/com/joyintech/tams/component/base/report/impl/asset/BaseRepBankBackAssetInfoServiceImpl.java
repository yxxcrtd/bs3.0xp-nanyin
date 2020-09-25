/*
 * 文件名：BaseRepBankBackAssetInfoServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：刘二通
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.component.base.report.impl.asset;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RepLiabilitiesMapper;
import com.joyintech.tams.common.base.entity.RepLiabilities;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.base.report.asset.BaseRepBankBackAssetInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepBankBackAssetInfoDto;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepTradeInfoMapper;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationInputParaDto;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationOutputDto;
import com.joyintech.tams.invest.common.util.xmlrelation.XmlRelationUtil;

/**
 * 负债(同业拆入卖出回购)报送信息 实现类
 * @author 刘二通
 * @version 1.0 2018年11月5日
 * @see BaseRepBankBackAssetInfoServiceImpl
 * @since 1.0
 */
@Service
public class BaseRepBankBackAssetInfoServiceImpl implements BaseRepBankBackAssetInfoService {

    /**
     * RepLiabilitiesMapper
     */
    @Autowired
    private RepLiabilitiesMapper repLiabilitiesMapper;
    
    /**
     * BaseRepTradeInfoMapper
     */
    @Autowired
    private BaseRepTradeInfoMapper baseRepTradeInfoMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insert(List<RepLiabilities> repLiabilitiesList) {
        if (!StringUtil.isEmptyList(repLiabilitiesList)) {
            for (RepLiabilities repLiabilities : repLiabilitiesList) {
            	// 计息基础中债报送转换
            	List<XmlRelationInputParaDto> list = new ArrayList<XmlRelationInputParaDto>();
            	XmlRelationInputParaDto inputParaDto = new XmlRelationInputParaDto();
            	inputParaDto.setSysCode(DictCodeListExt1.子系统代码_共通.getValue());
            	inputParaDto.setDictCode(DictCode.计息基础.getValue());
            	inputParaDto.setItemCode(repLiabilities.getRepBasis());
            	list.add(inputParaDto);
            	XmlRelationOutputDto basisRelationDto = XmlRelationUtil.getOutPut(list);
            	if (basisRelationDto != null && !StringUtil.isEmptyList(basisRelationDto.getResultDtoList())) {
            		repLiabilities.setRepBasis(basisRelationDto.getResultDtoList().get(0).getItemCode());
            	}
            	// 对手方
            	if (StringUtil.isNotEmpty(repLiabilities.getRepCounterparty())) {
            		String tradeComponent = baseRepTradeInfoMapper.selectTradeComponent(repLiabilities.getRepCounterparty());
            		if (StringUtil.isNotEmpty(tradeComponent)) {
            			repLiabilities.setRepCounterparty(tradeComponent);
            		}
            	}
                repLiabilitiesMapper.insert(repLiabilities);
            }
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RepLiabilities> selectRepBankBackAssetInfo(BaseRepBankBackAssetInfoDto dto) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean deleteByPrimaryKey(BaseRepBankBackAssetInfoDto dto) {
        // TODO Auto-generated method stub
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean updateRepBankBackAssetInfo(BaseRepBankBackAssetInfoDto dto) {
        // TODO Auto-generated method stub
        return false;
    }

}
