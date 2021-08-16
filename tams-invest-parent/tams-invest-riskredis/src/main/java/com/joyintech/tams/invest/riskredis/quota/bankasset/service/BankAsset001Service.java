package com.joyintech.tams.invest.riskredis.quota.bankasset.service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExcelUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.ExcelValidDto;
import com.joyintech.tams.invest.common.redisinit.dto.ImplParaRds;
import com.joyintech.tams.invest.common.redisinit.service.RiskRefreshRedisService;
import com.joyintech.tams.invest.riskredis.quota.bankasset.dto.BankAsset001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 商业银行净资产维护
 * @author libaisong
 * @version 1.0 2020年12月29日
 * @see BankAsset001Service
 * @since 1.0
 */
@Service
public class BankAsset001Service extends CommonExportService<BankAsset001Dto> implements BaseProcessService<BankAsset001Dto>{

    /**
     * 日志
     */
    private static Logger logger = LoggerFactory.getLogger(BankAsset001Service.class);
    
    @Autowired
    private RiskRefreshRedisService riskRefreshRedisService;
    
	@Override
	public void doComplete(BankAsset001Dto dto, OperationType operationType) {
		String action = StringUtil.EMPTY;
		if (OperationType.DELETE.equals(operationType)) {
			action = "delete";
		} else if (OperationType.NEW.equals(operationType)) {
			if(!StringUtil.isNotEmpty(dto.getCustomerId())){
				List<BankAsset001Dto> bankAsset001Dtos = restTemplate.postForList(
						ServiceApplication.RISK_RDS_SERVICE_NAME + "/BankAsset/selectBankAssetByInsitituteCode",
						dto.getInsitituteCode(), BankAsset001Dto.class);
				if(bankAsset001Dtos!=null&&bankAsset001Dtos.size()>0){
					dto.setCustomerId(bankAsset001Dtos.get(0).getCustomerId());
				}
				
			}
			action = "insert";
		}else {
			action = "update";
		}
		restTemplate.postForObject(ServiceApplication.RISK_RDS_SERVICE_NAME + "/BankAsset/" + action,
				dto, Integer.class);
		
		// 刷新Redis参数（商业银行净资产维护）
        ImplParaRds implParaRds=new ImplParaRds();
        implParaRds.setCommonBusinessType(BussinessDictCodeListRds.共通业务类型_商业银行净资产维护.getValue());
        try{
            riskRefreshRedisService.refreshParamRedis(implParaRds);
        }catch (Exception e){
            //异常无需抛出，在内部处理掉
            logger.error("商业银行净资产维护更新指标参数redis异常",e);
        }
	}
	
    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @return Page类 Page
     * @throws Exception 异常信息
     */
    public Page<BankAsset001Dto> selectForPage(Map<String, Object> pageParam){
        return restTemplate.postForPage(
            ServiceApplication.RISK_RDS_SERVICE_NAME + "/BankAsset/findPage",
            pageParam, BankAsset001Dto.class);
    }

    /**
     * 主体代码下拉框查询
     * @return
     * @throws Exception
     */
    public Page<SelectOptionDto> selectCustomerId(Map<String, Object> map){
    	Page<SelectOptionDto> page = restTemplate.postForPage(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/BankAsset/selectCustomerId",
                map, SelectOptionDto.class);
        return page;
    }
    
    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
            "/com/joyintech/tams/invest/riskredis/quota/bankasset/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }
    
    /**
     * 导出
     */
    @Override
    public Page<BankAsset001Dto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    
    public BankAsset001Dto selectBankAsset(Map<String, Object> map){
    	BankAsset001Dto dto = restTemplate.postForObject(
    			ServiceApplication.RISK_RDS_SERVICE_NAME+ "/BankAsset/selectBankAsset", 
    			map, BankAsset001Dto.class);
    	return dto;
    }
    
    public Date selectMaxBankAsset(Map<String, Object> map){
    	Date maxEffDate = restTemplate.postForObject(
    			ServiceApplication.RISK_RDS_SERVICE_NAME+ "/BankAsset/selectMaxBankAsset", 
    			map, Date.class);
    	return maxEffDate;
    }
    
    public List<SuggestDto> getCustomerIdList(Map<String, Object> param) {
        return restTemplate.postForList(
        		ServiceApplication.RISK_RDS_SERVICE_NAME + "/BankAsset/getCustomerIdList", param,
                SuggestDto.class);
    }
    
    public List<BankAsset001Dto> selectForExport(Map<String, Object> param) {
        return restTemplate.postForList(
                ServiceApplication.RISK_RDS_SERVICE_NAME + "/BankAsset/selectForExport", param,
                BankAsset001Dto.class);
    }
    
    /**
     * excel导入功能
     *
     * @param flieName 文件名称
     * @return 检验list
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public List<ExcelValidDto> importData(String flieName)
        throws Exception {
        // 检查文件名是否为空
        if (StringUtil.isNotEmpty(flieName)) {
            if (flieName.indexOf("商业银行净资产维护") != -1) {
                ExcelUtil excelUtil = ExcelUtil.build(FileNameUtil.getUploadFilePath(flieName),
                    null);
                List<List<String>> dataList = excelUtil.readToList(0, 1, 0);
                if (null != dataList && dataList.size() > 0) {
                    // 将excel表格中的数据转化为dto
                    List<ExcelValidDto> checkResult = checkExcelData(dataList);
                    if (checkResult.isEmpty()) {
                        List<BankAsset001Dto> equityList = converToBean(dataList);
                        // 保存，已存在则更新
                        for(BankAsset001Dto dto :equityList){
                        	restTemplate.postForObject(ServiceApplication.RISK_RDS_SERVICE_NAME + "/BankAsset/" + "insert",
                    				dto, Integer.class);
                        }
                     // 刷新Redis参数（商业银行净资产维护）
                        ImplParaRds implParaRds=new ImplParaRds();
                        implParaRds.setCommonBusinessType(BussinessDictCodeListRds.共通业务类型_商业银行净资产维护.getValue());
                        try{
                            riskRefreshRedisService.refreshParamRedis(implParaRds);
                        }catch (Exception e){
                            //异常无需抛出，在内部处理掉
                            logger.error("商业银行净资产维护更新指标参数redis异常",e);
                        }
                    }
                    return checkResult;
                }
            }
            else {
                List<ExcelValidDto> checkResult = new ArrayList<>();
                checkResult.add(new ExcelValidDto(StringUtil.EMPTY, StringUtil.EMPTY,
                    getMessage("文件名称错误，请下载模板后上传")));
                return checkResult;
            }
        }
        return null;
    }

    /**
     * 导入文件内容检查
     * 
     * @param dataList 导入数据
     * @return 返回检查结果
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkExcelData(List<List<String>> dataList)
			throws ParseException {
		List<ExcelValidDto> checkResult = new ArrayList<>();
		List<BankAsset001Dto> equityList = converToBean(dataList);
		BankAsset001Dto bankAsset001Dto = null;
		if (equityList.size() >= 1) {
			for (int i = 0; i < equityList.size(); i++) {
				bankAsset001Dto = equityList.get(i);
				// 检查表内数据每行是否合法
				if (checkExcelCow(i, dataList.get(i)) != null) {
					checkResult.addAll(checkExcelCow(i, dataList.get(i)));
				}
				if(!StringUtil.isEmptyList(checkResult)){
					return checkResult;
				}
				// 检查表内数据是否有主键重复
				if (equityList.size() >= 2) {
					for (int k = i + 1; k < equityList.size(); k++) {
						if(StringUtil.isNotEmpty(equityList.get(k).getCustomerName()) 
								&& StringUtil.isNotEmpty(equityList.get(k).getInsitituteCode())){
						if (equityList.get(k).getCustomerName().equals(bankAsset001Dto.getCustomerName())) {
							checkResult.add(new ExcelValidDto(dataList.get(i).get(2), String.valueOf(i + 2), "主体名称重复"));
						}
						if (equityList.get(k).getInsitituteCode().equals(bankAsset001Dto.getInsitituteCode())) {
							checkResult
									.add(new ExcelValidDto(dataList.get(i).get(1), String.valueOf(i + 2), "组织机构代码重复"));
						}
						}
					}
				}
				// 根据组织代码找市场机构代码
				String insitituteCode = bankAsset001Dto.getInsitituteCode();
				List<BankAsset001Dto> bankAsset001Dtos = restTemplate.postForList(
						ServiceApplication.RISK_RDS_SERVICE_NAME + "/BankAsset/selectBankAssetByInsitituteCode",
						insitituteCode, BankAsset001Dto.class);
				if (StringUtil.isEmptyList(bankAsset001Dtos)) {
					checkResult.add(new ExcelValidDto(dataList.get(i).get(1), String.valueOf(i + 2), insitituteCode+"在系统中未找到对应市场机构信息"));
				} else {
					if (bankAsset001Dtos.size() > 1) {
						boolean isMatch = false;
						for (BankAsset001Dto dto : bankAsset001Dtos) {
							if (StringUtil.equals(bankAsset001Dto.getCustomerName(), dto.getCustomerName())) {
								bankAsset001Dto.setCustomerId(dto.getCustomerId());
								isMatch = true;
								break;
							} /*else {
								checkResult.add(new ExcelValidDto(dataList.get(i).get(1), String.valueOf(i),
										"市场机构管理需增加此重复性检验"));
							}*/
						}
						if(!isMatch){//如果未匹配上给出提示
							checkResult.add(new ExcelValidDto(dataList.get(i).get(1), String.valueOf(i +2),
									insitituteCode+"在系统中未找到对应市场机构信息"));
						}
					} else {
						bankAsset001Dto.setCustomerId(bankAsset001Dtos.get(0).getCustomerId());
					}
				}

			}
		}
		return checkResult;
	}

    /**
     * list转换为Bean
     *
     * @param dataList excel解析后的list
     * @return List<MstCoinFundQuotationInfo001Dto>
     * @throws ParseException 抛出异常
     * @see
     * @since 1.0
     */
    private List<BankAsset001Dto> converToBean(List<List<String>> dataList)
			throws ParseException {
		// 返回的list定义
		List<BankAsset001Dto> returnInfoList = new ArrayList<BankAsset001Dto>();
		BankAsset001Dto bankAsset001Dto = null;
		for (int i = 0; i < dataList.size(); i++) {
			List<String> list = dataList.get(i);
			bankAsset001Dto = new BankAsset001Dto();
			if (StringUtil.isNotEmpty(list.get(1))) {
				bankAsset001Dto.setCustomerName(list.get(1).trim());
			}
			if (StringUtil.isNotEmpty(list.get(0))) {
				// 导入为组织为统一社会信用代码
				bankAsset001Dto.setInsitituteCode(list.get(0).trim());
				// 根据组织代码找市场机构代码
				String insitituteCode = bankAsset001Dto.getInsitituteCode();
				List<BankAsset001Dto> bankAsset001Dtos = restTemplate.postForList(
						ServiceApplication.RISK_RDS_SERVICE_NAME + "/BankAsset/selectBankAssetByInsitituteCode",
						insitituteCode, BankAsset001Dto.class);
				if (!StringUtil.isEmptyList(bankAsset001Dtos)) {
					if (bankAsset001Dtos.size() > 1) {
						for (BankAsset001Dto dto : bankAsset001Dtos) {
							if (StringUtil.equals(bankAsset001Dto.getCustomerName(), dto.getCustomerName())) {
								bankAsset001Dto.setCustomerId(dto.getCustomerId());
							}
						}
					} else {
						bankAsset001Dto.setCustomerId(bankAsset001Dtos.get(0).getCustomerId());
					}
				}else{
					bankAsset001Dto.setCustomerId(insitituteCode);
				}
			}
			if (StringUtil.isNotEmpty(list.get(2))) {
				bankAsset001Dto.setNetValue(new BigDecimal(list.get(2).trim()));
			}
			/*if (StringUtil.isNotEmpty(list.get(3))) {
				bankAsset001Dto.setEffDate(DateUtil.parse(list.get(3).trim()));
			}*/
			bankAsset001Dto.setEffDate(DateUtil.parse(DateUtil.getSysDate()));
			bankAsset001Dto.setValStatus(BussinessDictCodeListRds.商业银行净资产维护_有效状态_有效.getValue());
			bankAsset001Dto.setCreateUser(CommonUtil.getUserName());
			bankAsset001Dto.setCreateDept(CommonUtil.getDeptCode());
			bankAsset001Dto.setUpdateUser(CommonUtil.getUserName());
			returnInfoList.add(bankAsset001Dto);
		}
		return returnInfoList;
	}

    /**
     * 检查excel是否为空或者传入列是否正确
     *
     * @param i 第几行
     * @param list 每一列解析后的List<String>
     * @return 检查结构
     * @see
     * @since 1.0
     */
    private List<ExcelValidDto> checkExcelCow(int i, List<String> list) {
        List<ExcelValidDto> excelValidDtolist = new ArrayList<ExcelValidDto>();
        // 列数为3
        if (null == list || list.size() == 0) {
            ExcelValidDto dto = new ExcelValidDto(StringUtil.EMPTY, String.valueOf(i + 2), "数据格式失败，数据模板中有空值");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(0))) {
            // 验证主体代码为空
            ExcelValidDto dto = new ExcelValidDto(list.get(0), String.valueOf(i + 2),
                "主体代码为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(1))) {
            // 验证主体名称为空
            ExcelValidDto dto = new ExcelValidDto(list.get(1), String.valueOf(i + 2),
                "主体名称为空");
            excelValidDtolist.add(dto);
        }
        if (StringUtil.isEmpty(list.get(2))) {
            // 验证净资产为空
            ExcelValidDto dto = new ExcelValidDto(list.get(2), String.valueOf(i + 2),
                "净资产为空");
            excelValidDtolist.add(dto);
        }
        return excelValidDtolist;
    }


}
