package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDatDiffDto;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao.PbcPrdBaseinfoEndSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDatDiff;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcDatTemplateName;
import com.joyintech.tams.svreport.pbc.common.service.PbcDatPriviewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能:产品中止信息
 * @since 1.6.0
 * @version 1.6.0
 * @作者：meifaxin
 * @创建时间：2021年7月30日
 * @修改记录：
 */
@Service
@TemplateName(PbcDatTemplateName.PBC_DAT_REPORT_PIE)
public class PbcPrdBaseInfoEndSubDatService extends PbcDatPriviewService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PbcPrdBaseinfoEndSubDatMapper pbcPrdBaseinfoEndSubDatMapper;

    @Override
    public Map<Object, Object> createData(Map<String,Object> param){
        Map<Object, Object> data = new HashMap<Object, Object>();
        String datZipCode=StringUtil.isNotEmpty(param.get("datZipCode"))?param.get("datZipCode").toString():"";
        if (param.get("datZipCode") == null) {
            return data;
        }
        List<PbcPrdBaseinfoEndSubDatDiff> listDetail=pbcPrdBaseinfoEndSubDatMapper.queryPbcPrdBaseInfoEndByDatZipCode(datZipCode);
        if (!CollectionUtils.isEmpty(listDetail)){
            for(PbcPrdBaseinfoEndSubDatDiff detail : listDetail){
                //登记表和DAT表数据差异比较
                dealPbcPrdBaseinfoEndSubDatDiff(detail);
                dealPbcPrdBaseinfoEndSubDat(detail);
            }
        }else{
            listDetail.add(new PbcPrdBaseinfoEndSubDatDiff());  //为空
        }
        data.put("report", listDetail);
        return data;
    }



    @SuppressWarnings("unchecked")
    @Override
    public Map<Object, Object> createDataMS(Map<String,Object> param){
        return createData(param);
    }
    /**
     * 功能:处理查询出的产品报表明细数据
     * @since 1.6.0
     * @version 1.6.0
     * @param result
     * @return
     * PbcPrdBaseinfoSubDat
     */
    public void dealPbcPrdBaseinfoEndSubDat(PbcPrdBaseinfoEndSubDatDiff result) {
        if (StringUtil.isNotEmpty(result.getCcy())) {
            result.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种_中债, result.getCcy()));
        }
    }
    /**
     * 功能:登记表和DAT表差异
     * @since 1.6.0
     * @version 1.6.0
     * @param result
     * @return
     * PbcPrdBaseinfoSubDat
     */
    public void dealPbcPrdBaseinfoEndSubDatDiff(PbcPrdBaseinfoEndSubDatDiff result){
        if (!StringUtil.equals(result.getPbcProdId(),result.getPbcProdIdPrd())){
            result.setPbcProdIdDiff(1);
        }
        if (!StringUtil.equals(result.getMdate(),result.getMdatePrd())){
            result.setMdateDiff(1);
        }
        if (!StringUtil.equals(result.getCusYearYield(),result.getCusYearYieldPrd())){
            result.setCusYearYieldDiff(1);
        }
        if (!StringUtil.equals(result.getCcy(),result.getCcyPrd())){
            result.setCcyDiff(1);
        }
        if (!StringUtil.equals(result.getCustomProfit(),result.getCustomProfitPrd())){
            result.setCustomProfitDiff(1);
        }
        if (!StringUtil.equals(result.getCustomProfitCny(),result.getCustomProfitCnyPrd())){
            result.setCustomProfitCnyDiff(1);
        }
    }
}
