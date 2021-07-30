package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysInstitution;
import com.joyintech.tams.common.base.dao.PbcSendZipDatFileMapper;
import com.joyintech.tams.common.base.entity.PbcSendZipDatFile;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.ChbCcyConvertUtil;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.common.util.ObjEqual;
import com.joyintech.tams.report.system.instregbankcode.dao.SysInstRegBankCode001Mapper;
import com.joyintech.tams.report.system.instregbankcode.dto.SysInstRegBankCode001Dto;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dao.PbcPrdAccountSubMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dao.PbcPrdBaseinfoSubMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dao.PbcPrdBaseinfoSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubdat.dto.PbcPrdBaseinfoSubDat;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dao.PbcPrdBaseinfoEndSubMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dto.PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dao.PbcPrdBaseinfoEndSubDatMapper;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsubdat.dto.PbcPrdBaseinfoEndSubDat;
import com.joyintech.tams.svreport.pbc.common.service.PbcDatPriviewService;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcReportProdDetailDto;
import com.joyintech.tams.svreport.pbc.report.dao.PbcReportTemplateMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;


/**
 * 功能:人行报送明细总入口
 * @since 1.6.0
 * @version 1.6.0
 * @作者：meifaxin
 * @创建时间：2021年7月29日
 * @修改记录：
 */
@Service
public abstract class PbcReportProdDatTotalService extends PbcDatPriviewService {
    
    @Autowired
    private PbcPrdBaseinfoSubDatMapper pbcPrdBaseinfoSubDatMapper;
	@Autowired
	private PbcPrdBaseinfoSubMapper pbcPrdBaseinfoSubMapper;
	@Autowired
	private PbcPrdBaseinfoEndSubDatMapper pbcPrdBaseinfoEndSubDatMapper;
	@Autowired
	private PbcPrdBaseinfoEndSubMapper pbcPrdBaseinfoEndSubMapper;

    @Override
    public Map<Object, Object> createData(Map<String,Object> param){
    	Map<Object,Object> data  = new HashMap<Object,Object>();
    	if(param.get("reportuuid")==null){
    		return data;
    	}
    	String reportuuid=param.get("reportuuid").toString();
		String reporttype=param.get("reporttype").toString();
		if(reporttype.equals(DictCodeListExt2.人行报送明细类型_产品基本信息.getValue())){
			PbcPrdBaseinfoSubDat pbcPrdBaseinfoSubDat = pbcPrdBaseinfoSubDatMapper.selectByPrimaryKey(reportuuid);
			PbcPrdBaseinfoSub pbcPrdBaseinfoSub=pbcPrdBaseinfoSubMapper.selectByPrimaryKey(reportuuid);
			Map<String,Integer> redMap=ObjEqual.domainEquals(pbcPrdBaseinfoSubDat,pbcPrdBaseinfoSub);
			data.put("report", dealPbcPrdBaseinfoSubDat(pbcPrdBaseinfoSubDat));
			data.put("redMap", redMap);
		}else if(reporttype.equals(DictCodeListExt2.人行报送明细类型_产品终止信息.getValue())){
			PbcPrdBaseinfoEndSubDat pbcPrdBaseinfoEndSubDat = pbcPrdBaseinfoEndSubDatMapper.selectByPrimaryKey(reportuuid);
			PbcPrdBaseinfoEndSub pbcPrdBaseinfoEndSub=pbcPrdBaseinfoEndSubMapper.selectByPrimaryKey(reportuuid);
			Map<String,Integer> redMap=ObjEqual.domainEquals(pbcPrdBaseinfoEndSubDat,pbcPrdBaseinfoEndSub);
			data.put("report", pbcPrdBaseinfoEndSubDat);
			data.put("redMap", redMap);
		}
    	return  data;
    }
    
    @SuppressWarnings("unchecked")
	@Override
    public Map<Object, Object> createDataMS(Map<String,Object> param){
    	return createData(param);
//    	return restTemplate.postForObject(
//        		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcMonthReportProd/createDataProd",param,
//        		Map.class);
    }


	/**
	 * 功能:处理查询出的产品报表明细数据
	 * @since 1.6.0
	 * @version 1.6.0
	 * @param result
	 * @return
	 * PbcPrdBaseinfoSubDat
	 */
	public PbcPrdBaseinfoSubDat dealPbcPrdBaseinfoSubDat(PbcPrdBaseinfoSubDat result) {
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
		return result;
	}

    



	
	

    
}
