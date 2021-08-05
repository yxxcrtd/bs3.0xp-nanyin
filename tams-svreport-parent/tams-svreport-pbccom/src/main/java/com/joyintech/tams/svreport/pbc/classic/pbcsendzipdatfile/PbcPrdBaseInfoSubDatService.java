package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dao.PbcPrdBaseinfoSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDatDiffDto;
import com.joyintech.tams.svreport.pbc.common.annotation.TemplateName;
import com.joyintech.tams.svreport.pbc.common.constants.PbcDatTemplateName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 功能:产品基本信息
 * @since 1.6.0
 * @version 1.6.0
 * @作者：meifaxin
 * @创建时间：2021年7月29日
 * @修改记录：
 */
@Service
@TemplateName(PbcDatTemplateName.PBC_DAT_REPORT_PIB)
public class PbcPrdBaseInfoSubDatService extends PbcReportProdDatTotalService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PbcPrdBaseinfoSubDatMapper pbcPrdBaseinfoSubDatMapper;

    @Override
    public Map<Object, Object> createData(Map<String,Object> param){
        Map<Object,Object> data  = new HashMap<Object,Object>();
        if(param.get("datZipCode")==null){
            return data;
        }
        String datZipCode=StringUtil.isNotEmpty(param.get("datZipCode"))?param.get("datZipCode").toString():"";
        List<PbcPrdBaseinfoSubDatDiffDto> listDetail=pbcPrdBaseinfoSubDatMapper.queryPbcPrdBaseInfoByDatZipCode(datZipCode);
        if (!CollectionUtils.isEmpty(listDetail)){
            for(PbcPrdBaseinfoSubDatDiffDto detail : listDetail){
                //登记表和DAT表数据差异比较
                dealPbcPrdBaseinfoSubDatDiff(detail);
                //处理数据
                dealPbcPrdBaseinfoSubDat(detail);
            }
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
    public PbcPrdBaseinfoSubDatDiffDto dealPbcPrdBaseinfoSubDat(PbcPrdBaseinfoSubDatDiffDto result) {
        if (StringUtil.isNotEmpty(result.getPbcProdType())){
            result.setPbcProdType( SysDataUtil.getNameFromDataDict(DictCode.人行产品种类,result.getPbcProdType()));
        }
        if (StringUtil.isNotEmpty(result.getCustomerType())){
            result.setCustomerType( SysDataUtil.getNameFromDataDict(DictCode.人行客户类型,result.getCustomerType()));
        }
        if (StringUtil.isNotEmpty(result.getRaiseType())){
            result.setRaiseType( SysDataUtil.getNameFromDataDict(DictCode.人行募集方式,result.getRaiseType()));
        }
        if (StringUtil.isNotEmpty(result.getManageType())){
            result.setManageType( SysDataUtil.getNameFromDataDict(DictCode.人行管理类型,result.getManageType()));
        }
        if (StringUtil.isNotEmpty(result.getFiduciaryDuty())){
            result.setFiduciaryDuty( SysDataUtil.getNameFromDataDict(DictCode.人行管理方式,result.getFiduciaryDuty()));
        }
        if (StringUtil.isNotEmpty(result.getOperateMode())){
            result.setOperateMode(result.getOperateMode() + StringUtil.SPACE + SysDataUtil.getNameFromDataDict(DictCode.人行产品运作模式,result.getOperateMode()));
        }
        if (StringUtil.isNotEmpty(result.getContMode())){
            result.setContMode(SysDataUtil.getNameFromDataDict(DictCode.人行合作模式,result.getContMode()));
        }
        if (StringUtil.isNotEmpty(result.getBusiModel())){
            result.setBusiModel( SysDataUtil.getNameFromDataDict(DictCode.人行业务模式,result.getBusiModel()));
        }
        if (StringUtil.isNotEmpty(result.getIncomeGuaranIdent())){
            result.setIncomeGuaranIdent(StringUtil.SPACE + SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getIncomeGuaranIdent()));
        }
        if (StringUtil.isNotEmpty(result.getPrinGuaranIdent())){
            result.setPrinGuaranIdent( SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getPrinGuaranIdent()));
        }
        if (StringUtil.isNotEmpty(result.getTermRightFlag())){
            result.setTermRightFlag( SysDataUtil.getNameFromDataDict(DictCode.人行有无,result.getTermRightFlag()));
        }
        if (StringUtil.isNotEmpty(result.getCusRedeemFlag())){
            result.setCusRedeemFlag( SysDataUtil.getNameFromDataDict(DictCode.人行有无,result.getCusRedeemFlag()));
        }
        if (StringUtil.isNotEmpty(result.getCreditFlag())){
            result.setCreditFlag( SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getCreditFlag()));
        }
        if (StringUtil.isNotEmpty(result.getCreditType())){
            result.setCreditType( SysDataUtil.getNameFromDataDict(DictCode.人行信誉机构类型,result.getCreditType()));
        }
        if (StringUtil.isNotEmpty(result.getCreditMode())){
            //多个用,切开
            String [] creditModes=result.getCreditMode().split(GlobalConstant.COMMA);
            String creditMode="";
            int i=0;
            for(String single:creditModes){
                i++;
                creditMode=creditMode+SysDataUtil.getNameFromDataDict(DictCode.人行增信形式,single);
                if(i<creditModes.length){
                    creditMode=creditMode+GlobalConstant.COMMA;
                }
            }
            result.setCreditMode(creditMode);
        }
        if (StringUtil.isNotEmpty(result.getIsConstracte())){
            result.setIsConstracte( SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getIsConstracte()));
        }
        if (StringUtil.isNotEmpty(result.getTransferType())){
            result.setTransferType( SysDataUtil.getNameFromDataDict(DictCode.人行是否,result.getTransferType()));
        }
        if (StringUtil.isNotEmpty(result.getIdentiFlag())){
            result.setIdentiFlag( SysDataUtil.getNameFromDataDict(DictCode.人行理财产品发起机构标识,result.getIdentiFlag()));
        }
        if(result.getOutDepCtry()!=null){
            List<String> outDepCtrys=StringUtil.splitToList(GlobalConstant.COMMA, result.getOutDepCtry());
            result.setOutDepCtry("");
            for(String outDepCtry : outDepCtrys){
                result.setOutDepCtry(result.getOutDepCtry()+","+outDepCtry+" "+SvrDataUtil.getNameFromDataDict(DictCode.所属国家或地区, outDepCtry));
            }
            result.setOutDepCtry(result.getOutDepCtry().substring(1));
        }
        //募集资金币种
        if (StringUtil.isNotEmpty(result.getRaiseCcy())){
            String raiseCcyStr= SysDataUtil.getNameFromDataDict(DictCode.币种,result.getRaiseCcy());
            result.setRaiseCcy(raiseCcyStr);
        }
        //兑付本金币种
        if (StringUtil.isNotEmpty(result.getCashPrinCcy())){
            String cashPrinCcy= SysDataUtil.getNameFromDataDict(DictCode.币种,result.getCashPrinCcy());
            result.setCashPrinCcy(cashPrinCcy);
        }
        //兑付收益币种
        if (StringUtil.isNotEmpty(result.getCashProfCcy())){
            String cashProfCcy= SysDataUtil.getNameFromDataDict(DictCode.币种,result.getCashProfCcy());
            result.setCashProfCcy(cashProfCcy);
        }
        // 境内托管机构名称
        if(result.getInDepName()!=null){
            result.setInDepName(result.getInDepName().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.境内托管机构名称, result.getInDepName()));
        }
        // 境内托管机构名称
        if(result.getIsMarketFund()!=null){
            result.setIsMarketFund(SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, result.getIsMarketFund()));
        }
        return result;
    }
    /**
     * 功能:登记表和DAT表差异
     * @since 1.6.0
     * @version 1.6.0
     * @param result
     * @return
     * PbcPrdBaseinfoSubDat
     */
    public void dealPbcPrdBaseinfoSubDatDiff(PbcPrdBaseinfoSubDatDiffDto result) {
        if (!StringUtil.equals(result.getPbcProdId(),result.getPbcProdIdPrd())){
            result.setPbcProdIdDiff(1);
        }
        if (!StringUtil.equals(result.getIssuePartyId(),result.getIssuePartyIdPrd())){
            result.setIssuePartyIdDiff(1);
        }
        if (!StringUtil.equals(result.getProdName(),result.getProdNamePrd())){
            result.setProdNameDiff(1);
        }
        if (!StringUtil.equals(result.getPbcProdType(),result.getPbcProdTypePrd())){
            result.setPbcProdTypeDiff(1);
        }
        if (!StringUtil.equals(result.getIvstorientation(),result.getIvstorientationPrd())){
            result.setIvstorientationDiff(1);
        }
        if (!StringUtil.equals(result.getProdBrand(),result.getProdBrandPrd())){
            result.setProdBrandDiff(1);
        }
        if (null!=result.getProdStage()&&null!=result.getProdStagePrd()&&result.getProdStage().compareTo(result.getProdStagePrd())!=0){
            result.setProdStageDiff(1);
        }
        if (!StringUtil.equals(result.getInsideProdId(),result.getInsideProdIdPrd())){
            result.setInsideProdIdDiff(1);
        }
        if (!StringUtil.equals(result.getRaiseType(),result.getRaiseTypePrd())){
            result.setRaiseTypeDiff(1);
        }
        if (!StringUtil.equals(result.getManageType(),result.getManageTypePrd())){
            result.setManageTypeDiff(1);
        }
        if (!StringUtil.equals(result.getOperateMode(),result.getOperateModePrd())){
            result.setOperateModeDiff(1);
        }
        if (!StringUtil.equals(result.getRaiseVdate01(),result.getRaiseVdate01Prd())){
            result.setRaiseVdate01Diff(1);
        }
        if (!StringUtil.equals(result.getRaiseEnddate01(),result.getRaiseEnddate01Prd())){
            result.setRaiseEnddate01Diff(1);
        }
        if (!StringUtil.equals(result.getTermRightFlag(),result.getTermRightFlagPrd())){
            result.setTermRightFlagDiff(1);
        }
        if (!StringUtil.equals(result.getCusRedeemFlag(),result.getCusRedeemFlagPrd())){
            result.setCusRedeemFlagDiff(1);
        }
        if (!StringUtil.equals(result.getCreditFlag(),result.getCreditFlagPrd())){
            result.setCreditFlagDiff(1);
        }
        if (!StringUtil.equals(result.getInDepCode(),result.getInDepCodePrd())){
            result.setInDepCodeDiff(1);
        }
        if (!StringUtil.equals(result.getOutDepCtry(),result.getOutDepCtryPrd())){
            result.setOutDepCtryDiff(1);
        }
        if (!StringUtil.equals(result.getOutDepName(),result.getOutDepNamePrd())){
            result.setOutDepNameDiff(1);
        }
        if (!StringUtil.equals(result.getVdate01(),result.getVdate01Prd())){
            result.setVdate01Diff(1);
        }
        if (!StringUtil.equals(result.getMdate01(),result.getMdate01Prd())){
            result.setMdate01Diff(1);
        }
        if (!StringUtil.equals(result.getFiduciaryDuty(),result.getFiduciaryDutyPrd())){
            result.setFiduciaryDutyDiff(1);
        }
        if (!StringUtil.equals(result.getIsConstracte(),result.getIsConstractePrd())){
            result.setIsConstracteDiff(1);
        }
        if (!StringUtil.equals(result.getTransferType(),result.getTransferTypePrd())){
            result.setTransferTypeDiff(1);
        }
        if (!StringUtil.equals(result.getRaiseCcy(),result.getRaiseCcyPrd())){
            result.setRaiseCcyDiff(1);
        }
        if (!StringUtil.equals(result.getCashPrinCcy(),result.getCashPrinCcyPrd())){
            result.setCashPrinCcyDiff(1);
        }
        if (!StringUtil.equals(result.getCashProfCcy(),result.getCashProfCcyPrd())){
            result.setCashProfCcyDiff(1);
        }
        if (!StringUtil.equals(result.getCreditType(),result.getCreditTypePrd())){
            result.setCreditTypeDiff(1);
        }
        if (!StringUtil.equals(result.getCreditMode(),result.getCreditModePrd())){
            result.setCreditModeDiff(1);
        }

    }

}
