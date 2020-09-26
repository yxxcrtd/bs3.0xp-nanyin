package com.joyintech.tams.valuation.common.interf.base.service.account;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.valuation.common.constants.BookConstant;
import com.joyintech.tams.valuation.common.constants.DataIndexSequenceType;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.TargetFinProduct;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.ValBooksetInfoDto;
import com.joyintech.tams.valuation.common.interf.base.dto.ValueAddedTaxRecCommonResult;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.base.util.MD5Util;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValAddValueTax;
import com.joyintech.tams.valuation.common.utils.DBUtil;

/**
 * 增值税信息-模板处理 
 * 
 * @author dongxiangyang
 * @version 1.0 2018年7月10日
 * @see ValueAddedTaxTemplateRecService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class ValueAddedTaxTemplateRecService {
	 @Autowired
	 private TemplateAnalysisForXMLService templateAnalysisForXMLService;
	 
	 @Autowired
	 private DBUtil dBUtil;
	 /**
	  * 共通方法service
	  */
	 @Autowired
	 private InterfCommonService interfCommonService;
	
	 
	 @SuppressWarnings({"unchecked", "rawtypes"})
	 public ValueAddedTaxRecCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
             TemplateOrderInfoReaderResult acctEntity){
		// 定义返回变量
		 ValueAddedTaxRecCommonResult result=new ValueAddedTaxRecCommonResult();
		 List<ValAddValueTax> valAddValueTaxList_CS=new ArrayList<ValAddValueTax>();
		 
		//解析模板后的list以及日志list
	        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		 
	      //文件路径设置  TODO 此部分处理待以后从画面入口的时        候进行acctEntity的文件路径的重置，根据templateSourceFlgDTO中的路径进行重新设置。
	        // 1、文件读取解析 进行数据有效性的check,并出进入日志表的数据结构
	        returnResult= templateAnalysisForXMLService.execute(acctEntity);
	        valAddValueTaxList_CS=returnResult.getT(ValAddValueTax.class);
	        
	        List<ValAddValueTax> valAddValueTaxList = new ArrayList<ValAddValueTax>();
	        for(ValAddValueTax obj:valAddValueTaxList_CS){
	        	if("22210301".equals(obj.getSubjectId())){
	        		BigDecimal amt = BigDecimal.ZERO;
	        		BigDecimal amtnum = obj.getValNum()==null?BigDecimal.ZERO:obj.getValNum();
	        		if(obj.getCdFlag().equals(DictCodeList.借贷方向_借.getValue())){
	        			amt = obj.getcAmt();
	        		}else{
	        			amt = obj.getdAmt();
	        		}
	        		/**
	        		 * 注：资管22210301应交增值税附加科目值，估值系统需要在接口处理，
	        		 * 需要按照相应的比例将22210301的值拆分到估值系统的22210101、22210102、22210103中。
	        		 * 如资管22210301的值为12，则估值系统中22210101的值为12*(7/12)、22210102的值为12*(3/12)、22210103的值为12*(2/12).
	        		 */
	        		BigDecimal b_7 = new BigDecimal(7);
	        		BigDecimal b_3 = new BigDecimal(3);
	        		BigDecimal amt_22210101 = BigDecimal.ZERO;
	        		BigDecimal amt_22210102 = BigDecimal.ZERO;
	        		BigDecimal amt_22210103 = BigDecimal.ZERO;
	        		if(amt.compareTo(BigDecimal.ZERO)!=0){
	        			amt_22210101 = amt.multiply(b_7.divide(amt,2,BigDecimal.ROUND_HALF_UP));
	        			amt_22210102 = amt.multiply(b_3.divide(amt,2,BigDecimal.ROUND_HALF_UP));
	        			amt_22210103 = amt.subtract(amt_22210101).subtract(amt_22210102);
	        		}
	        		BigDecimal amtnum_22210101 = BigDecimal.ZERO;
	        		BigDecimal amtnum_22210102 = BigDecimal.ZERO;
	        		BigDecimal amtnum_22210103 = BigDecimal.ZERO;
	        		if(amtnum.compareTo(BigDecimal.ZERO)!=0){
	        			amtnum_22210101 = amtnum.multiply(b_7.divide(amtnum,2,BigDecimal.ROUND_HALF_UP));
	        			amtnum_22210102 = amtnum.multiply(b_3.divide(amtnum,2,BigDecimal.ROUND_HALF_UP));
	        			amtnum_22210103 = amtnum.subtract(amtnum_22210101).subtract(amtnum_22210102);
	        		}		
	        		ValAddValueTax obj_22210101 = obj;
	        		obj_22210101.setSubjectId("22210101");
	        		if(obj.getCdFlag().equals(DictCodeList.借贷方向_借.getValue())){
	        			obj_22210101.setcAmt(amt_22210101);
	        		}else{
	        			obj_22210101.setdAmt(amt_22210101);
	        		}
	        		obj_22210101.setValNum(amtnum_22210101);
	        		valAddValueTaxList.add(obj_22210101);
	        		
	        		
	        		ValAddValueTax obj_22210102 = obj;
	        		obj_22210102.setSubjectId("22210102");
	        		if(obj.getCdFlag().equals(DictCodeList.借贷方向_借.getValue())){
	        			obj_22210102.setcAmt(amt_22210102);
	        		}else{
	        			obj_22210102.setdAmt(amt_22210102);
	        		}
	        		obj_22210102.setValNum(amtnum_22210102);
	        		valAddValueTaxList.add(obj_22210102);
	        		
	        		
	        		ValAddValueTax obj_22210103 = obj;
	        		obj_22210103.setSubjectId("22210103");
	        		if(obj.getCdFlag().equals(DictCodeList.借贷方向_借.getValue())){
	        			obj_22210103.setcAmt(amt_22210103);
	        		}else{
	        			obj_22210103.setdAmt(amt_22210103);
	        		}
	        		obj_22210103.setValNum(amtnum_22210103);
	        		valAddValueTaxList.add(obj_22210103);
	        		
	        	}else{
	        		valAddValueTaxList.add(obj);
	        	}
	        }
	        
	        Map<String,Object> map=this.handleValAddValueTax(valAddValueTaxList,acctEntity);
	        if (!map.isEmpty()) {
	        	result.setListValBokBookdetailCurrent((List<BokBookdetailCurrent>)map.get("ValBokBookdetailCurrent"));
	            result.setListValBokDetailId((List<BokDetailId>)map.get("ValBokDetailId"));
	        }
	        //日志
	        result.setListValInterflog(returnResult.getValInterflog());
		 return result;
	 }
	 
	 
	 /**
	     * <一句话功能简述>
	     * <功能详细描述>
	 * @param acctEntity 
	     * @param dto
	     * @return ValueAddedTaxRecCommonResult
	     * @see
	     * @since 1.0
	     */
	 public Map<String,Object> handleValAddValueTax( List<ValAddValueTax> valAddValueTaxList, TemplateOrderInfoReaderResult acctEntity){
		 Map<String, Object> resultMap = new HashMap<>();
		 //账务明细list
		 List<BokBookdetailCurrent> valBokBookdetailCurrentList=new ArrayList<BokBookdetailCurrent>();
		 //账务明细代码表List
		 List<BokDetailId> listValBokDetailId=new ArrayList<BokDetailId>();
		 for(ValAddValueTax li:valAddValueTaxList){
			 BokBookdetailCurrent valBokBookdetailCurrent=this.getValBokBookt(li,acctEntity);
			 valBokBookdetailCurrentList.add(valBokBookdetailCurrent);
			 BokDetailId valBokDetailId=this.getBokDetailId(li);
			 valBokDetailId.setBokDetailId(valBokBookdetailCurrent.getBokDetailId());
			 listValBokDetailId.add(valBokDetailId);
		 }
		 resultMap.put("ValBokBookdetailCurrent", valBokBookdetailCurrentList);
		 resultMap.put("ValBokDetailId", listValBokDetailId);
		 return resultMap;
	 }
	 /**
	     * <一句话功能简述>
	     * <功能详细描述>
	 * @param acctEntity 
	     * @param dto
	     * @return ValBokBookdetailCurrent
	     * @see
	     * @since 1.0
	     */
	 private BokBookdetailCurrent getValBokBookt(ValAddValueTax val, TemplateOrderInfoReaderResult acctEntity){
	     BokBookdetailCurrent  valBokBookdetailCurrent=new BokBookdetailCurrent();
		 BokSubject valBokSubject=new BokSubject();
		 
		 //科目号
		 valBokSubject.setSubjectNo(val.getSubjectId());
		 //流水(自动生成)
		 valBokBookdetailCurrent.setSeqno(dBUtil.getSequenceIndexData(DataIndexSequenceType.记账类型));
		 //根据产品信息生成账套代码
		 ValBooksetInfoDto  valBooksetInfoDto=interfCommonService.getBooksetInfoByProdId(val.getProdId());
		//账套代码
		 valBokSubject.setBooksetId(valBooksetInfoDto==null?val.getProdId():valBooksetInfoDto.getBooksetId());
		 
		 //账套代码=根据产品id获取账套信息
		 valBokBookdetailCurrent.setBooksetId(valBooksetInfoDto==null?val.getProdId():valBooksetInfoDto.getBooksetId());
		 //场景代码
		 valBokBookdetailCurrent.setTableId(val.getTableId());
		 //交易编号
		 valBokBookdetailCurrent.setTradeId(val.getTradeId());
		 //发生日期
		 valBokBookdetailCurrent.setHappenDate(val.getHappenDate());
		 //入账日期
		 valBokBookdetailCurrent.setBookDate(val.getAccountDate());
		 //账套日期
		 valBokBookdetailCurrent.setBooksetDate(val.getAccountDate());
		 //基础科目性质
		 BokSubject valBokSubjectResult=interfCommonService.getValBokSubject(valBokSubject);
		 valBokBookdetailCurrent.setBaseSubjectNature(valBokSubjectResult==null?null:valBokSubjectResult.getBaseSubjectNature());
		 
		 
//		 //标的信息
		 TargetFinProduct targetFinProduct=interfCommonService.getTargetFinProduct(val.getProdId());
//		 //账务明细代码=T2账务明细代码=产品代码+标的信息.金融产品类型
//		 valBokBookdetailCurrent.setBokDetailId(val.getProdId()+(targetFinProduct==null?"":targetFinProduct.getFinprodType()));
		 /**
		  * 计提类的都是固定的写死的账务明细代码，如果是支付则托管户科目的账务明细代码 = MD5(账户+账户类型） 其他科目依然是写死的账务明细代码
		  * 场景代码	所属类型
			2601	计提
			2603	计提
			2605	计提
			2607	支付
			2609	支付
			2611	支付
			2613	计提
			2614	支付
			2615	支付
			2616	支付
		  */
		 String bokDetailId = "";
		 if("2607".equals(val.getTableId()) 
				 || "2609".equals(val.getTableId()) 
				 || "2611".equals(val.getTableId()) 
				 || "2614".equals(val.getTableId()) 
				 || "2615".equals(val.getTableId()) 
				 || "2616".equals(val.getTableId())){
			 //根据产品ID、账户类型查询账户号
		     StlFinProductAcct valFinProductAcct = interfCommonService.queryValFinProductAcct(
					 val.getProdId(), DictCodeListExt1.金融产品类型_理财产品.getValue(), DictCodeListExt1.资金账户类型_产品托管户.getValue(),null,val.getCcy());
			 if(valFinProductAcct != null){
				 bokDetailId = valFinProductAcct.getAcctNo()+DictCodeListExt1.资金账户类型_产品托管户.getValue();
				 bokDetailId = MD5Util.string2MD5(bokDetailId);
			 }
		 }else{
			 bokDetailId = BookConstant.DEF_BOK_DETAIL_ID;
		 }
		 valBokBookdetailCurrent.setBokDetailId(bokDetailId);
		 
		 //业务明细代码=产品代码
		 valBokBookdetailCurrent.setBusiId(val.getProdId());
		 //分支序号 =全局设置默认为o
		 valBokBookdetailCurrent.setBranch(InterfCommonConstant.分支序号_默认);
		 //科目号
		 valBokBookdetailCurrent.setSubjectNo(val.getSubjectId());
		 //四级科目，增值税当前没有四级科目
		 //借贷方向
		 valBokBookdetailCurrent.setCdFlag(val.getCdFlag());
		 //原币=币种
		 valBokBookdetailCurrent.setoCcy(val.getCcy());
		 //原币发生额
		 if(val.getCdFlag().equals(DictCodeList.借贷方向_借.getValue())){
			 valBokBookdetailCurrent.setoHappenAmt(val.getcAmt());
		 }else if(val.getCdFlag().equals(DictCodeList.借贷方向_贷.getValue())){
			 valBokBookdetailCurrent.setoHappenAmt(val.getdAmt());
		 }
	     //发生数量=根据账套科目信息判断
		 if(valBokSubjectResult!=null && !StringUtils.isEmpty(valBokSubjectResult.getAccQuaFlag())&&valBokSubjectResult.getAccQuaFlag().equals(DictCodeList.是否_是.getValue())){
			 valBokBookdetailCurrent.setHappenNumber(val.getValNum());
		 }
		 //凭证编号
		 //		 交易类：账套代码+场景代码+交易编号
		 //		 计提类：账套代码+场景代码+发生日+账务明细代码(T1.业务明细代码+T2.业务明细类型)
		 //		 损益结转： 账套代码+场景代码+发生日
		 String bookId = (valBooksetInfoDto==null?val.getProdId():valBooksetInfoDto.getBooksetId())+val.getTableId();
		 if(!StringUtils.isEmpty(val.getTradeId())){
			 bookId=bookId + val.getTradeId();
		 }else{
			 bookId=bookId + DateUtil.format(val.getHappenDate(), "yyyymmdd")+ val.getProdId()+targetFinProduct.getFinprodType();
		 }
		 //bookId做MD5加密
		 bookId = MD5Util.string2MD5(bookId);
		 
		 valBokBookdetailCurrent.setBookId(bookId);
		 //凭证说明=接口介入
		 valBokBookdetailCurrent.setBookRemark(DictCodeListExt1.生成方式_接口接入.getValue());
		 //凭证类型=01 日终
		 valBokBookdetailCurrent.setBookType(DictCodeList.凭证类型_手工.getValue()); 
		 //处理优先级=固定值 8
		 valBokBookdetailCurrent.setTaskPri(new Long(8));
		 //处理序列
		 valBokBookdetailCurrent.setBookOrder(new BigDecimal(dBUtil.getSequenceIndex(DataIndexSequenceType.处理序列)));
   	     //创建部门  待定
		 //valBokBookdetailCurrent.setCreateDept();
		 //创建时间 =系统时间
		 valBokBookdetailCurrent.setCreateTime(acctEntity.getResovleDate());
		 //更新时间=系统时间
		 valBokBookdetailCurrent.setUpdateTime(new Date());
		 return valBokBookdetailCurrent;
	 }
	
	 private BokDetailId getBokDetailId(ValAddValueTax val){
		 BokDetailId valBokDetailId=new BokDetailId();
		 //账套代码
		//根据产品信息生成账套代码
		 ValBooksetInfoDto  valBooksetInfoDto=interfCommonService.getBooksetInfoByProdId(val.getProdId());
		 //账套代码=根据产品id获取账套信息
		 valBokDetailId.setBooksetId(valBooksetInfoDto==null?val.getProdId():valBooksetInfoDto.getBooksetId());
		 //标的信息
		 TargetFinProduct targetFinProduct=interfCommonService.getTargetFinProduct(val.getProdId());
//		 //账务明细代码=T1的账务明细代码
//		 valBokDetailId.setBokDetailId(val.getProdId()+(targetFinProduct==null?"":targetFinProduct.getFinprodType()));
		 //业务明细代码=T1业务明细代码
		 valBokDetailId.setBusiId(val.getProdId());
		 //业务明细类型=标的信息.金融产品类型
		 valBokDetailId.setBusiDetailType(targetFinProduct==null?null:targetFinProduct.getFinprodType());
		 //投资目的=默认为空
		 valBokDetailId.setInvAim(null);
		 //证券管理户代码=默认为空
		 valBokDetailId.setSecManageAcctId(null);
		 //交易编号=T1交易编号
		 valBokDetailId.setTradeId(val.getTradeId());
		 //创建部门=待定
		 //valBokDetailId.setCreateDept();
		 //创建时间
		 valBokDetailId.setCreateTime(new Date());
		 //更新时间
		 valBokDetailId.setUpdateTime(new Date());
		 return valBokDetailId;
	 }
	 
	 
}
