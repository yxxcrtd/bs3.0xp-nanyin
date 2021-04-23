package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.standard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.BokBooksetCapacctInfo;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.StlFinProductAcct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.StandardInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.ValBooksetInfoDto;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSubjectInfo;

@Service
public class StandardInterfService {
	@Autowired
	private TemplateAnalysisForXMLService templateAnalysisForXMLService;
	/**
	 * 接口共通Service
	 */
	@Autowired
	private InterfCommonService interfCommonService;

	/**
	 * 读取模板处理
	 * 
	 * @param templateSourceFlgDTO
	 * @param acctEntity
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public StandardInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
			TemplateOrderInfoReaderResult acctEntity) {
		// 定义返回变量
		StandardInfoCommonResult result = new StandardInfoCommonResult();
		List<ValSubjectInfo> listValSubjectInfo = new ArrayList<ValSubjectInfo>();
		// 解析模板后的list以及日志list
		TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
		// 1、文件读取解析
	    returnResult = templateAnalysisForXMLService.execute(acctEntity);

		// 2、进行数据有效性的check,并生出进入日志表的数据结构
		listValSubjectInfo = returnResult.getT(ValSubjectInfo.class);

		// 3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
		// 判断返回结果不为空
		Map<String, Object> map = handleListValSubjectInfo(listValSubjectInfo);

		if (map != null) {
			List<StlFinProductAcct> listValFinProductAcct = (List<StlFinProductAcct>) map.get("ValFinProductAcct");
			result.setValFinProductAcctList(listValFinProductAcct);

			List<BokBooksetCapacctInfo> valBooksetCapacctInfoList = (List<BokBooksetCapacctInfo>) map
					.get("ValSubjectInfo");
			result.setValBooksetCapacctInfoList(valBooksetCapacctInfoList);
			
			/**add by wujie on 20180814 start remark:活期利息改造**/
			List<BokDetailId> bokDetailIdList = (List<BokDetailId>)map.get("BokDetailId");
			result.setBokDetailIdList(bokDetailIdList);
			//几许传数
			result.setListValSubjectInfo(listValSubjectInfo);
			
			//
//			if(map.get("BokDetailId_no")!=null){
//				Map<String,Object> paramMap = new HashMap<String,Object>();
//				paramMap.put("BokDetailId_no", map.get("BokDetailId_no"));
//				result.setParamMap(paramMap);
//			}
			/**add by wujie on 20180814 end**/
		}
        // 添加日志
        result.setValInterflog(returnResult.getValInterflog());
		return result;
	}

	/**
	 * 
	 * @param handleListValSubjectInfo
	 *            标的账户信息表
	 * @return 金融产品账户表
	 * @see
	 * @since 1.0
	 */
	private Map<String, Object> handleListValSubjectInfo(List<ValSubjectInfo> listValSubjectInfo) {
		Map<String, Object> resultMap = new HashMap<>();
		List<StlFinProductAcct> valFinProductAcctList = new ArrayList<StlFinProductAcct>();
		List<BokBooksetCapacctInfo> valBooksetCapacctInfoList = new ArrayList<BokBooksetCapacctInfo>();
		
		/**add by wujie on 20180814 start remark:活期利息改造**/
		List<BokDetailId> bokDetailIdList = new ArrayList<BokDetailId>();
		/**add by wujie on 20180814 end**/
		// 获取基础信息
		for (ValSubjectInfo dto : listValSubjectInfo) {
			if (DictCodeListExt1.金融产品类型_理财产品.getValue().equals(dto.getSubjectType())
					|| DictCodeListExt1.金融产品类型_投资组合.getValue().equals(dto.getSubjectType())) {
			    
			    // 资金账户
                StlFinProductAcct valFinProductAcct = getValFinProductAcct(dto);
                valFinProductAcctList.add(valFinProductAcct);
                
	            //调用共通设计->【详细设计-接口共通方法设计.xlsx】->账套信息(根据产品代码) _分层产品SHEET
                String layeringId = dto.getSubjectId();
                /**add by wujie on 20190104 start**/
                layeringId = layeringId+
                		FinalString.LAYERING_SPLIT+
                		dto.getSubjectType();
                /**add by wujie on 20190104 end**/
                List<ValBooksetInfoDto> valBooksetInfoDtoList = interfCommonService.getBokBooksetByProdId_Layering(layeringId);
                
                StlFinProductAcct obj = new StlFinProductAcct();
                obj.setFinprodId(dto.getSubjectId());
                obj.setFinprodType(dto.getSubjectType());
                obj.setAcctNo(dto.getActNo());
                obj.setCcy(dto.getCcy());
                obj.setAcctType(dto.getActType());
                List<BokBooksetCapacctInfo> vBokBooksetCapacctInfos = interfCommonService.booksetCapacctInfoInit(obj,valBooksetInfoDtoList);
                valBooksetCapacctInfoList.addAll(vBokBooksetCapacctInfos);
				
                // 托管户-同时初始化清算款账户
                if (DictCodeListExt1.资金账户类型_产品托管户.getValue().equals(dto.getActType())) {
                    StlFinProductAcct valFinProductAcctQSK = getValFinProductAcct(dto);
                    valFinProductAcctQSK.setAcctNo(InterfCommonConstant.清算款账户号+dto.getCcy());
                    valFinProductAcctQSK.setAcctType(DictCodeListExt1.资金账户类型_待清算款.getValue());
                    valFinProductAcctList.add(valFinProductAcctQSK);
                    
                    obj.setAcctNo(InterfCommonConstant.清算款账户号+dto.getCcy());
                    obj.setAcctType(DictCodeListExt1.资金账户类型_待清算款.getValue());
                    List<BokBooksetCapacctInfo>  vBokBooksetCapacctInfoQSKList = interfCommonService.booksetCapacctInfoInit(obj,valBooksetInfoDtoList);
                    valBooksetCapacctInfoList.addAll(vBokBooksetCapacctInfoQSKList);
                }	
                /**modify by wujie on 20190402
                if(DictCodeList.是否_是.getValue().equals(dto.getIsCalc())){
                **/
                	for(ValBooksetInfoDto booksetInfoDto:valBooksetInfoDtoList) {
                        BokDetailId bokDetailId = this.getBokDetailId(booksetInfoDto.getBooksetId(),dto);
                        bokDetailIdList.add(bokDetailId);
					}
                /**modify by wujie on 20190402
                }**/
				
			} 
			/** modify by dongjun on 20190320 通道变更 start**/
			else if (DictCodeListExt1.金融产品类型_通道.getValue().equals(dto.getSubjectType())){
			    // 资金账户
                StlFinProductAcct valFinProductAcct = getValFinProductAcct(dto);
                valFinProductAcctList.add(valFinProductAcct);  
			}
			/** modify by dongjun on 20190320 通道变更 end**/
		}
		resultMap.put("ValSubjectInfo", valBooksetCapacctInfoList);
		resultMap.put("ValFinProductAcct", valFinProductAcctList);
		
		/**add by wujie on 20180814 start remark:活期利息改造**/
		resultMap.put("BokDetailId", bokDetailIdList);
		/**add by wujie on 20180814 end**/
		return resultMap;
	}

	/**
	 * 金融产品账户表 接收
	 * 
	 * @param dto
	 *            标的账户信息
	 * @return 金融产品账户表
	 * @see
	 * @since 1.0
	 */
	private StlFinProductAcct getValFinProductAcct(ValSubjectInfo dto) {

		StlFinProductAcct valFinProductAcct = new StlFinProductAcct();
		// 金融产品代码 = A.标的代码
		valFinProductAcct.setFinprodId(dto.getSubjectId());
		// 金融产品类型 = A.标的类型
		valFinProductAcct.setFinprodType(dto.getSubjectType());
		// 账号 = A.账号
		valFinProductAcct.setAcctNo(dto.getActNo());
		// 币种 = A.币种
		valFinProductAcct.setCcy(dto.getCcy());
		// 账户类型 = A.账户类型
		valFinProductAcct.setAcctType(dto.getActType());
		return valFinProductAcct;
	}
	
	/**
	 * 账务明细代码表 接收
	 * 
	 * @param dto
	 *            标的账户信息
	 * @return 账务明细代码表
	 * @see
	 * @since 1.0
	 * add by wujie on 20180814
	 */
	private BokDetailId getBokDetailId(String booksetId,ValSubjectInfo dto) {
		BokDetailId bokDetailId = new BokDetailId();
		//1.根据产品ID查询账套信息
		/** modify by wujie on 20181116 start
		ValBooksetInfoDto bok = interfCommonService.getBooksetInfoByProdId(dto.getSubjectId());
		bokDetailId.setBooksetId(bok==null?dto.getSubjectId():bok.getBooksetId());
		**/
		bokDetailId.setBooksetId(booksetId);
		/**add by wujie on 20181116 end**/
		
		//业务明细代码 = A.账号
		bokDetailId.setBusiId(dto.getActNo());
		
		//业务明细类型 = 数据字典:金融产品类型.资金账户
		bokDetailId.setBusiDetailType(dto.getActType());

        //币种 = 数据字典: A.币种
        bokDetailId.setCcy(dto.getCcy());
		
		//2. 账务明细代码 = MD5(A.账号+A.账户类型)
		bokDetailId.setBokDetailId(interfCommonService.genMd5BokDetailId(bokDetailId));
		//add lby on 2020 start
		//调用共通设计->【详细设计-接口共通方法设计.xlsx】->四级科目号设置SHEET
		BokDetailId bokDetailIdTail = interfCommonService.settingDetailSubjectNoTaiL(dto.getActNo(),dto.getSubjectType(),0L,null,booksetId);
		//T5  账务明细代码表.四级科目尾号 = DTO.四级科目尾号
		bokDetailId.setDetailSubjectNoTail(bokDetailIdTail.getDetailSubjectNoTail());
		//T5 账务明细代码表.四级科目尾号名称 = DTO.四级科目尾号名称
		bokDetailId.setDetailSubnameTail(bokDetailIdTail.getDetailSubnameTail());
		//add lby on 2020 end
		return bokDetailId;
	}
}
