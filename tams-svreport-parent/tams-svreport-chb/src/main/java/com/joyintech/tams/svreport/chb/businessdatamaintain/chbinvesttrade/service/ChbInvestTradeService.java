package com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbInvestTrade;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbboksubject.dto.ChbBokSubjectDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.dao.ChbInvestTradeDao;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.dto.ChbInvestTradeDto;
import com.joyintech.tams.svreport.chb.businessdatamaintain.chbmstbondcreditrating.dto.ChbMstBondCreditRatingDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections.CollectionUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author wubingbing
 * @date 2022-04-27 14:17
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbInvestTradeService extends CommonExportService<ChbInvestTradeDto> implements BaseProcessService<ChbInvestTradeDto> {
    @Autowired
    private ChbInvestTradeDao chbInvestTradeDao;
    @Override
    public void doComplete(ChbInvestTradeDto entity, OperationType type) {
        entity.setUpdateUser(CommonUtil.getUserName());
        entity.setUpdateTime(DateUtil.now());
        if (OperationType.DELETE.equals(type)) {
            entity.setStatus(DictCodeListExt1.维护数据状态_删除.getValue());
            chbInvestTradeDao.update(entity);

        }
        else if (OperationType.NEW.equals(type)) {
            entity.setDataSource(DictCodeListExt2.数据来源_新建导入.getValue());
            entity.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            entity.setTradeSeqno(IDGenerator.generate(32));
            chbInvestTradeDao.insert(entity);
        }
        else if(OperationType.MODIFY.equals(type)){
            chbInvestTradeDao.insert(entity);
        }

    }

    public List<SelectOptionDto> getProdName(Map<String, Object> param) {
        return chbInvestTradeDao.getProdName(param);
    }

    public List<SelectOptionDto> getRegCode(Map<String, Object> param) {
        return chbInvestTradeDao.getRegCode(param);
    }

    public List<SelectOptionDto> getCustomerName(Map<String, Object> param) {
        return chbInvestTradeDao.getCustomerName(param);
    }

    public List<SelectOptionDto> getAssetCode(Map<String, Object> param) {
        List<SelectOptionDto> list = chbInvestTradeDao.getAssetCode(param);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(item -> {
                item.setId(item.getId());
                item.setText(item.getId());
            });
        }
        return list;
    }

    public Map<String,String> selectProdIdAndRegCodeByProdName(String prodName) {
        return chbInvestTradeDao.selectProdIdAndRegCodeByProdName(prodName);
    }

    public ChbInvestTradeDto selectByKey(String tradeCode,String prodId,String registBankCode,String assetCode) {
        return chbInvestTradeDao.selectByKey(tradeCode,prodId,registBankCode,assetCode);
    }

    public Map<String,String> selectAssetNameByAssetCode(String assetCode) {
        return chbInvestTradeDao.selectAssetNameByAssetCode(assetCode);
    }
    public Page<ChbInvestTradeDto> selectForPage(Map<String, Object> pageParam) {
        pageParam.put("prodNames", StringUtil.isNotEmpty(pageParam.get("prodName"))?(pageParam.get("prodName").toString().split(",")):null);
        pageParam.put("regCodes", StringUtil.isNotEmpty(pageParam.get("regCode"))?(pageParam.get("regCode").toString().split(",")):null);
        pageParam.put("assetCodes", StringUtil.isNotEmpty(pageParam.get("assetCode"))?(pageParam.get("assetCode").toString().split(",")):null);
        return queryForPage(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")), () -> chbInvestTradeDao.selectAll(pageParam));
    }

    public List<ChbInvestTradeDto> selectAll(Map<String, Object> params) {
        List<ChbInvestTradeDto> list = chbInvestTradeDao.selectAll(params);
        return list;
    }
}
