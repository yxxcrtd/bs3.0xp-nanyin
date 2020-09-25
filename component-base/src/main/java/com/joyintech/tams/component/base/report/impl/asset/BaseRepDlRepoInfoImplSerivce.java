/*
 * 文件名：BaseRepDlRepoInfoImplSerivce.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.impl.asset;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RepAssetInterLendMapper;
import com.joyintech.tams.common.base.entity.RepAssetInterLend;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.base.report.asset.BaseRepDlRepoInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepDlRepoInfoDto;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepDlRepoInfoMapper;
import com.joyintech.tams.component.base.report.impl.dao.BaseRepTradeInfoMapper;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationInputParaDto;
import com.joyintech.tams.invest.common.dto.xmlrelation.XmlRelationOutputDto;
import com.joyintech.tams.invest.common.util.xmlrelation.XmlRelationUtil;
import com.joyintech.tools.util.ListUtils;

/**
 * 拆放同业及买入返售报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月5日
 * @see BaseRepDlRepoInfoImplSerivce
 * @since 1.0
 */
@Service
public class BaseRepDlRepoInfoImplSerivce extends BaseService<RepAssetInterLend> implements BaseRepDlRepoInfoService{

    
    /**
     * 生成
     */
    @Autowired
    private RepAssetInterLendMapper repAssetInterLendMapper;
    
    /**
     * 自定义
     */
    @Autowired
    private BaseRepDlRepoInfoMapper baseRepDlRepoInfoMapper;
    
    /**
     * BaseRepTradeInfoMapper
     */
    @Autowired
    private BaseRepTradeInfoMapper baseRepTradeInfoMapper;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insert(List<RepAssetInterLend> list) {
        if (list != null && list.size() > 0) {
            for(RepAssetInterLend repAssetInterLend : list){
            	// 计息基础中债报送转换
            	List<XmlRelationInputParaDto> changeList = new ArrayList<XmlRelationInputParaDto>();
            	XmlRelationInputParaDto inputParaDto = new XmlRelationInputParaDto();
            	inputParaDto.setSysCode(DictCodeListExt1.子系统代码_共通.getValue());
            	inputParaDto.setDictCode(DictCode.计息基础.getValue());
            	inputParaDto.setItemCode(repAssetInterLend.getRepBasis());
            	changeList.add(inputParaDto);
            	XmlRelationOutputDto basisRelationDto = XmlRelationUtil.getOutPut(changeList);
            	if (basisRelationDto != null && !StringUtil.isEmptyList(basisRelationDto.getResultDtoList())) {
            		repAssetInterLend.setRepBasis(basisRelationDto.getResultDtoList().get(0).getItemCode());
            	}
            	// 对手方
            	if (StringUtil.isNotEmpty(repAssetInterLend.getRepTradeTarget())) {
            		String tradeComponent = baseRepTradeInfoMapper.selectTradeComponent(repAssetInterLend.getRepTradeTarget());
            		if (StringUtil.isNotEmpty(tradeComponent)) {
            			repAssetInterLend.setRepTradeTarget(tradeComponent);
            		}
            	}
                repAssetInterLendMapper.insert(repAssetInterLend);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(BaseRepDlRepoInfoDto dto) {
        List<String> finlist = dto.getRepFinprodIdList();
        if (finlist != null && finlist.size() > 0) {
            List<List<String>> list= ListUtils.splitList(finlist);
            for(List<String> idlist:list){
                baseRepDlRepoInfoMapper.delete(idlist);
            }
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(BaseRepDlRepoInfoDto dto) {
        List<RepAssetInterLend> list = dto.getRepDlRepoInfoList();
        if(list!=null && list.size()>0){
            if(this.delete(dto)){
                return this.insert(list);
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<RepAssetInterLend> select(BaseRepDlRepoInfoDto dto) {
        List<String> finprodIdList = dto.getRepFinprodIdList();
        if (finprodIdList != null && finprodIdList.size() > 0) {
            int maxDataCountOneTime = 1000;
            int dataCount = finprodIdList.size();
            List<RepAssetInterLend> result = new ArrayList<>();
            if (dataCount <= maxDataCountOneTime) {
                result.addAll(baseRepDlRepoInfoMapper.select(finprodIdList));
            }
            else {
                List<String> newDataList = new ArrayList<String>();
                newDataList.addAll(finprodIdList);
                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++ ) {
                    result.addAll(baseRepDlRepoInfoMapper.select(
                        newDataList.subList(0, maxDataCountOneTime)));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                    result.addAll(baseRepDlRepoInfoMapper.select(newDataList));
                }
            }
            return result;
        }
        return null;
    }

}
