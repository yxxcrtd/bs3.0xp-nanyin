package com.joyintech.tams.svreport.chb.businessdatamaintain.chbinvesttrade.service;

import com.github.pagehelper.PageHelper;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.ChbInvestTrade;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.SysDataUtil;
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

import java.util.HashMap;
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

    public Page<SelectOptionDto> getProdName(Map<String, Object> param) {
        return queryForPageForSelectReturn(Integer.parseInt(null == param.get("page")?"1":(String) param.get("page")), Integer.parseInt(null == (String) param.get("length")?"1":(String) param.get("length")),()->chbInvestTradeDao.getProdName(param));
    }

    public Page<SelectOptionDto> getRegCode(Map<String, Object> param) {
        return queryForPageForSelectReturn(Integer.parseInt(null == param.get("page")?"1":(String) param.get("page")), Integer.parseInt(null == (String) param.get("length")?"1":(String) param.get("length")),()->chbInvestTradeDao.getRegCode(param));
    }

    public Page<SelectOptionDto> getCustomerName(Map<String, Object> param) {
        return queryForPageForSelectReturn(Integer.parseInt(null == param.get("page")?"1":(String) param.get("page")), Integer.parseInt(null == (String) param.get("length")?"1":(String) param.get("length")),()->chbInvestTradeDao.getCustomerName(param));
    }

    public Page<SelectOptionDto> getAssetCode(Map<String, Object> param) {
        Page<SelectOptionDto> page = queryForPageForSelectReturn(Integer.parseInt(null == param.get("page")?"1":(String) param.get("page")), Integer.parseInt(null == (String) param.get("length")?"1":(String) param.get("length")), () -> chbInvestTradeDao.getAssetCode(param));
        if (CollectionUtils.isNotEmpty(page.getRoot())) {
            page.getRoot().forEach(item -> {
                item.setId(item.getId());
                item.setText(item.getId());
            });
        }
        return page;
    }

    public Map<String,String> selectProdIdAndRegCodeByProdName(String prodName) {
        return chbInvestTradeDao.selectProdIdAndRegCodeByProdName(prodName);
    }

    public ChbInvestTradeDto selectByKey(String tradeCode,String prodId,String assetCode) {
        return chbInvestTradeDao.selectByKey(tradeCode,prodId,assetCode);
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

    public Map<String, String> selectRivalPartyType(String tradeCounter) {
        Map<String, String> map = chbInvestTradeDao.selectRivalPartyType(tradeCounter);
        if(null==map){
            map = new HashMap<>();
        }
        map.put("COUNTER_TYPE", SysDataUtil.getNameFromDataDict(DictCode.交易对手类型,map.get("COUNTER_TYPE")));
        return map;
    }
}
