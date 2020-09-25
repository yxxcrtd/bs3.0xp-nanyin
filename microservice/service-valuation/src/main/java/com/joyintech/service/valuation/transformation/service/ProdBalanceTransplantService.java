package com.joyintech.service.valuation.transformation.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.common.interf.base.dto.BalanceTransplantDto;
import com.joyintech.tams.valuation.common.interf.base.util.InterfHelper;
import com.joyintech.service.valuation.datatransplant.dto.TemplateCheckDto;
import com.joyintech.service.valuation.transformation.constants.TransConstant;
import com.joyintech.service.valuation.transformation.dto.ProdBalanceDto;
import com.joyintech.service.valuation.transformation.dto.ResultData;
import com.joyintech.tams.common.base.dao.BokBooksetMapper;
import com.joyintech.tams.common.base.entity.BokBookset;
import com.joyintech.tams.common.base.entity.BokSubject;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;
import com.joyintech.tams.valuation.common.service.DataTransplantCommonService;

@Service(value="serviceProdBalanceTransplantService")
@Transactional(rollbackFor = Exception.class)
public class ProdBalanceTransplantService extends CommonExportService<BalanceTransplantDto> {

	@Autowired
	private DataTransplantCommonService dataTransplantCommonService;
	
	@Autowired
    private BokBooksetMapper valBokBooksetMapper;

	@Value("${tams.properties.maxSqlNum:500}")
	private int maxSqlNum;

	@SuppressWarnings("unchecked")
	public ResultData execute(List<List<String>> sheetDataList) {
		ResultData result = new ResultData();
		List<ProdBalanceDto> listProdBalanceDto = new ArrayList<ProdBalanceDto>();
		listProdBalanceDto = convertToDtoOway(sheetDataList);
		// 存储校验信息
		List<TemplateCheckDto> checkList = new ArrayList<TemplateCheckDto>();
		
		// 存储转换结果
		List<BalanceTransplantDto> balanceList = new ArrayList<BalanceTransplantDto>();
				
		// 类型
		String typeError = "错误";
		// String typeWarn = "警告";
		// 错误条数
		int number = 0;
		// 模板校验
		Map<String, Object> map = checkTransplantTemple(listProdBalanceDto, checkList,
				typeError, number);
		checkList = (List<TemplateCheckDto>) map.get("checkList");
		number = (int) map.get("number");
		
		if (number == 0) {
			String batchSeqno = InterfHelper.getUUid();
			// 数据循环处理
			for (ProdBalanceDto dto : listProdBalanceDto) {
				//账务余额模板DTO
				BalanceTransplantDto rst = new BalanceTransplantDto();
				//导入批次号
				rst.setBatchSeqno(batchSeqno);
				//账套代码
				rst.setBooksetId(dto.getBooksetId());
				//业务明细代码
				rst.setBusiId(dto.getProdId());
				//业务明细类型
				String busiDetailType = DictCodeListExt1.金融产品类型_理财产品.getValue()+GlobalConstant.分号+
						DictCodeListExt1.金融产品类型_理财产品.getCodeName();
				String branch = null; 
				if(DictCodeList.是否_是.getCodeName().equals(dto.getIsZq())){
					busiDetailType = DictCodeListExt1.金融产品类型_理财子产品.getValue()+GlobalConstant.分号+
							DictCodeListExt1.金融产品类型_理财子产品.getCodeName();
					branch = DateUtil.format(dto.getVdate(),DateUtil.DEFAULT_OTHER_FORMAT);
				}
				rst.setBusiDetailType(busiDetailType);
				//日期
				rst.setBalanceDate(DateUtil.format(dto.getCdate()));
				//分支序号
				rst.setBranch(branch);
				//币种
				rst.setoCcy(dto.getoCcy());
				
				//投资目的--默认为空
				
				//结息日-默认为空
				
				//科目号、余额方向、原币余额、数量余额
				//1. 实收资本
				BalanceTransplantDto result_subject = this.getBalanceTransplantDto(rst, dto, 
						TransConstant.产品余额报表_实收资本);
				if(result_subject!=null){
					balanceList.add(result_subject);
				}
				
				//2. 应付利润
				result_subject = this.getBalanceTransplantDto(rst, dto, 
						TransConstant.产品余额报表_应付利润);
				if(result_subject!=null){
					balanceList.add(result_subject);
				}
				
				//3. 应付托管费
				result_subject = this.getBalanceTransplantDto(rst, dto, 
						TransConstant.产品余额报表_应付托管费);
				if(result_subject!=null){
					balanceList.add(result_subject);
				}
				
				//4. 应付销售服务费
				result_subject = this.getBalanceTransplantDto(rst, dto, 
						TransConstant.产品余额报表_应付销售服务费);
				if(result_subject!=null){
					balanceList.add(result_subject);
				}
				
				//5. 应付固定管理费
				result_subject = this.getBalanceTransplantDto(rst, dto, 
						TransConstant.产品余额报表_应付固定管理费);
				if(result_subject!=null){
					balanceList.add(result_subject);
				}
				
				//6. 应付超额管理费
				result_subject = this.getBalanceTransplantDto(rst, dto, 
						TransConstant.产品余额报表_应付超额管理费);
				if(result_subject!=null){
					balanceList.add(result_subject);
				}
				
				//7. 其他资本公积
				result_subject = this.getBalanceTransplantDto(rst, dto, 
						TransConstant.产品余额报表_损益平准金);
				if(result_subject!=null){
					balanceList.add(result_subject);
				}
				
				//8.未分配利润
				result_subject = this.getBalanceTransplantDto(rst, dto, 
						TransConstant.产品余额报表_未分配利润);
				if(result_subject!=null){
					balanceList.add(result_subject);
				}
			}
			result.setBatchSeqno(batchSeqno);//导入批次
			dataTransplantCommonService.insertTrsBalanceTransplant(balanceList);
			result.setRstCode(TransConstant.解析状态_成功);
		}else{
			result.setRstCode(TransConstant.解析状态_失败);
		}
		result.setCheckList(checkList);
//		result.setBalanceList(balanceList);
		return result;
	}
	
	/**
	 * 设置科目号、余额方向、原币余额、数量余额
	 * @param dto
	 * @param booksetId
	 * @param subjectType
	 * @return
	 */
	private BalanceTransplantDto getBalanceTransplantDto(BalanceTransplantDto dto,ProdBalanceDto template,String subjectType){
		BalanceTransplantDto newDto = dto.clone();
		String subjectNo = null;
		String balFlag = null;//余额方向
		BigDecimal happenAmt = null;
		BigDecimal happenNumber = null;
		/**
		 * 实收资本
		 *   原币余额 = 当前份额/当前本金
		 */
		if(TransConstant.产品余额报表_实收资本.equals(subjectType)){
			subjectNo = TransConstant.产品余额报表_科目.get(subjectType);
			balFlag = TransConstant.产品余额报表_科目余额方向.get(subjectType);
			happenAmt = template.getShare();
			BokSubject bokSubject = dataTransplantCommonService.findSubjectInfo(template.getBooksetId(), subjectNo);
			if(DictCodeList.是否_是.getValue().equals(bokSubject.getAccQuaFlag())){
				happenNumber = template.getShare();
			}
		}
		/**
		 * 应付利润
		 *  原币余额 = 应付客户利息
		 */
		else if(TransConstant.产品余额报表_应付利润.equals(subjectType)){
			subjectNo = TransConstant.产品余额报表_科目.get(subjectType);
			balFlag = TransConstant.产品余额报表_科目余额方向.get(subjectType);
			happenAmt = template.getIntAmt();
		}
		/**
		 * 应付托管费
		 *  原币余额 = 应付托管费
		 */
		else if(TransConstant.产品余额报表_应付托管费.equals(subjectType)){
			subjectNo = TransConstant.产品余额报表_科目.get(subjectType);
			balFlag = TransConstant.产品余额报表_科目余额方向.get(subjectType);
			happenAmt = template.getFeeAmtTG();
		}
		/**
		 * 应付销售服务费
		 *  原币余额 = 应付销售费
		 */
		else if(TransConstant.产品余额报表_应付销售服务费.equals(subjectType)){
			subjectNo = TransConstant.产品余额报表_科目.get(subjectType);
			balFlag = TransConstant.产品余额报表_科目余额方向.get(subjectType);
			happenAmt = template.getFeeAmtXS();
		}
		
		/**
		 * 应付固定管理费
		 *  原币余额 = 应付固定管理费
		 */
		else if(TransConstant.产品余额报表_应付固定管理费.equals(subjectType)){
			subjectNo = TransConstant.产品余额报表_科目.get(subjectType);
			balFlag = TransConstant.产品余额报表_科目余额方向.get(subjectType);
			happenAmt = template.getFeeAmtGD();
		}
		/**
		 * 应付超额管理费
		 *  原币余额 = 超额业绩收益
		 */
		else if(TransConstant.产品余额报表_应付超额管理费.equals(subjectType)){
			subjectNo = TransConstant.产品余额报表_科目.get(subjectType);
			balFlag = TransConstant.产品余额报表_科目余额方向.get(subjectType);
			happenAmt = template.getProfitAmt();
		}
		/**
		 * 损益平准金-已实现
		 *  原币余额 = 其他资本公积
		 */
		else if(TransConstant.产品余额报表_损益平准金.equals(subjectType)){
			subjectNo = TransConstant.产品余额报表_科目.get(subjectType);
			balFlag = TransConstant.产品余额报表_科目余额方向.get(subjectType);
			happenAmt = template.getCapitalQT();
		}
		
		/**
		 * 未分配利润
		 *  原币余额 = 未分配利润
		 */
		else if(TransConstant.产品余额报表_未分配利润.equals(subjectType)){
			subjectNo = TransConstant.产品余额报表_科目.get(subjectType);
			balFlag = TransConstant.产品余额报表_科目余额方向.get(subjectType);
			happenAmt = template.getUnIntAmt();
		}
		
		
		if(subjectNo==null){
			return null;
		}
		if(happenAmt==null || happenAmt.compareTo(BigDecimal.ZERO)==0){
			return null;
		}
		//科目号
		newDto.setSubjectNo(subjectNo);
		//余额方向
		balFlag = balFlag+GlobalConstant.分号+
				InterfHelper.getDictColdeList_ItemName(DictCode.借贷方向.getValue(),balFlag);
		newDto.setCdFlag(balFlag);
		//原币余额
		newDto.setoHappenAmt(happenAmt);
		//数量
		newDto.setHappenNumber(happenNumber);
		
		return newDto;
	}
	

	/**
	 * 将excel中的数据转换为实体类
	 * 
	 * @param sheetDataList
	 * @return
	 * @see
	 * @since 1.0
	 */
	private List<ProdBalanceDto> convertToDtoOway(List<List<String>> sheetDataList) {
		// 定义返回实体类
		List<ProdBalanceDto> dtoList = new ArrayList<ProdBalanceDto>();
		// 循环进行数据处理
		for (List<String> row : sheetDataList) {
			// 定义实体类
			ProdBalanceDto dto = new ProdBalanceDto();
			
			int column = 0;
			// 账套代码
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setBooksetId(row.get(column).trim());
			}
			
			column++;
			
			//产品代码
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setProdId(row.get(column).trim());
			}
			
			column++;
			
			// 是否周期型子产品
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setIsZq(row.get(column).trim());
			}
			
			column++;
			
			// 余额日期
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setCdate(row.get(column) == null ? null : DateUtil.parse(row.get(column).trim()));
			}
			
			column++;
			
			//原币
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setoCcy(row.get(column));
			}
			
			column++;
			
			//当前份额
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setShare(new BigDecimal(row.get(column).trim()));
			}
			
			column++;
			
			//当前净资产/当前本金
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setPrincipal(new BigDecimal(row.get(column).trim()));
			}
			
			column++;
			
			//起息日
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setVdate(row.get(column) == null ? null : DateUtil.parse(row.get(column).trim()));
			}
			
			column++;
			
			//到期日
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setMdate(row.get(column) == null ? null : DateUtil.parse(row.get(column).trim()));
			}
			
			column++;
			
			//应付客户利息
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setIntAmt(new BigDecimal(row.get(column).trim()));
			}
			
			column++;
			
			//应付固定管理费
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setFeeAmtGD(new BigDecimal(row.get(column).trim()));
			}
			
			column++;
			
			//应付托管费
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setFeeAmtTG(new BigDecimal(row.get(column).trim()));
			}
			
			column++;
			
			//应付销售费
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setFeeAmtXS(new BigDecimal(row.get(column).trim()));
			}
			
			column++;
			
			//应付其他费用
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setFeeAmtQT(new BigDecimal(row.get(column).trim()));
			}
			
			column++;
			
			//超额业绩收益
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setProfitAmt(new BigDecimal(row.get(column).trim()));
			}
			
			column++;
			
			//其他资本公积
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setCapitalQT(new BigDecimal(row.get(column).trim()));
			}
			
			column++;
			
			//未分配利润
			if (StringUtils.isNotEmpty(row.get(column))) {
				dto.setUnIntAmt(new BigDecimal(row.get(column).trim()));
			}
			
			// 塞入list
			dtoList.add(dto);

		}

		return dtoList;
	}
	
	/**
	 * 模板校验
	 */
	private Map<String, Object> checkTransplantTemple(List<ProdBalanceDto> listProdBalanceDto, List<TemplateCheckDto> checkList,
			String typeError, int number) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < listProdBalanceDto.size(); i++) {
			ProdBalanceDto dto = listProdBalanceDto.get(i);
			// 判断是否有错误
			boolean error = false;
			// 校验账套表中是否存在该账套
			if (StringUtil.isNotEmpty(dto.getBooksetId())) {
				BokBookset valBokBookset = valBokBooksetMapper.selectByPrimaryKey(dto.getBooksetId());
				if(valBokBookset==null){
					TemplateCheckDto templateCheckDto = new TemplateCheckDto();
					templateCheckDto.setErrorLineNum(i + 2);
					templateCheckDto.setErrorType(typeError);
					templateCheckDto.setErrorMessage("账套表中不存在该账套");
					checkList.add(templateCheckDto);
					error = true;
				}else{
					dto.setBooksetIdTmpl(valBokBookset.getBooksetIdTmpl());
				}
			}
			//判断产品是否存在
			if (!StringUtil.isNotEmpty(dto.getProdId())) {
				TemplateCheckDto templateCheckDto = new TemplateCheckDto();
				templateCheckDto.setErrorLineNum(i + 2);
				templateCheckDto.setErrorType(typeError);
				templateCheckDto.setErrorMessage("产品代码不能为空");
				checkList.add(templateCheckDto);
				error = true;
			}
			
			// 是否周期型子产品:校验枚举字段是否有效
			if (StringUtil.isNotEmpty(dto.getIsZq())) {
				if(!DictCodeList.是否_是.getCodeName().equals(dto.getIsZq())
						&& !DictCodeList.是否_否.getCodeName().equals(dto.getIsZq())){
					TemplateCheckDto templateCheckDto = new TemplateCheckDto();
					templateCheckDto.setErrorLineNum(i + 2);
					templateCheckDto.setErrorType(typeError);
					templateCheckDto.setErrorMessage(
							"是否周期型子产品：" + dto.getIsZq() + "无效");
					checkList.add(templateCheckDto);
					error = true;
				}
			}
			
			//判断余额日期
			if (!StringUtil.isNotEmpty(dto.getCdate())) {
				TemplateCheckDto templateCheckDto = new TemplateCheckDto();
				templateCheckDto.setErrorLineNum(i + 2);
				templateCheckDto.setErrorType(typeError);
				templateCheckDto.setErrorMessage("余额日期不能为空");
				checkList.add(templateCheckDto);
				error = true;
			}
			
			// 币种:校验枚举字段是否有效
			if (StringUtil.isNotEmpty(dto.getoCcy())) {
				String ccy = TransConstant.币种Map.get(dto.getoCcy());
				if (!StringUtil.isNotEmpty(ccy)) {
					TemplateCheckDto templateCheckDto = new TemplateCheckDto();
					templateCheckDto.setErrorLineNum(i + 2);
					templateCheckDto.setErrorType(typeError);
					templateCheckDto.setErrorMessage(
							"认购币种：" + dto.getoCcy() + "目前不支持");
					checkList.add(templateCheckDto);
					error = true;
				}else{
					dto.setoCcy(ccy);
				}
			}
			//起息日
			if(DictCodeList.是否_是.getCodeName().equals(dto.getIsZq())){
				if (dto.getVdate() == null) {
					TemplateCheckDto templateCheckDto = new TemplateCheckDto();
					templateCheckDto.setErrorLineNum(i + 2);
					templateCheckDto.setErrorType(typeError);
					templateCheckDto.setErrorMessage("起息日不能为空");
					checkList.add(templateCheckDto);
					error = true;
				}
			}
			
			if (error) {
				number++;
			}

		}
		map.put("checkList", checkList);
		map.put("number", number);

		return map;
	}
}
