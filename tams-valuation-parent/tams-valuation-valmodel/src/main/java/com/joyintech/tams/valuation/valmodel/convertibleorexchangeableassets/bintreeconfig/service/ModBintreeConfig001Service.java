package com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.ModBintreeConfigDto;
import com.joyintech.tams.valuation.valmodel.convertibleorexchangeableassets.bintreeconfig.dto.StockQuotationDto;
import com.joyintech.tams.valuation.valmodel.valgeneralfixedincomeassets.cashflowdiscountmanagement.dto.AssetInfoDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 二叉树模型参数配置
 * @author yuluan
 * @version 1.0 2021年07月01日
 * @see ModBintreeConfig001Service
 * @since 1.0
 */
@Service
public class ModBintreeConfig001Service extends CommonExportService<ModBintreeConfigDto> {
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<ModBintreeConfigDto> findPage(Map<String,Object> map){
    	Page<ModBintreeConfigDto> page=restTemplate.postForPage(
                ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModBintreeConfig/selectForPage",
                map, ModBintreeConfigDto.class);
    	List<ModBintreeConfigDto> root=page.getRoot();
    	List<ModBintreeConfigDto> list1=root.stream().filter(s -> s.getCompStockid()!=null).collect(Collectors.toList());
    	StockQuotationDto qStockQuotationDto=new StockQuotationDto();
    	qStockQuotationDto.setStockCodeList(list1.stream().map(ModBintreeConfigDto::getCompStockid).collect(Collectors.toList()));
    	List<StockQuotationDto> quotations = restTemplate.postForList(
				ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModBintreeConfig/getStockQuotation", qStockQuotationDto,
				StockQuotationDto.class);
    	if(!quotations.isEmpty()){
    		Map<String, StockQuotationDto> quotationsMap = quotations.stream().collect(Collectors.toMap(StockQuotationDto::getStockCode, a -> a,(k1,k2)->k1));
    		root.stream().forEach(e->{
    			if(e.getCompStockid()!=null){
    				if(quotationsMap.containsKey(e.getCompStockid())){
    					StockQuotationDto stockQuotationDto=quotationsMap.get(e.getCompStockid());
    					e.setCompPE(stockQuotationDto.getPe());
    					e.setCompPB(stockQuotationDto.getPb());
    				}
    			}
            });
    		page.setRoot(root);
    	}
        return page;
    }
public List<SelectOptionDto> getFinprodIdForBintree(Map<String, Object> parameters) {
    	
		List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
		AssetInfoDto qAssetInfoDto=new AssetInfoDto();
		if(parameters.get("search")!=null&&!"".equals(parameters.get("search"))){
			qAssetInfoDto.setSearch(parameters.get("search").toString());
		}
		if(parameters.get("initValue")!=null&&!"".equals(parameters.get("initValue"))){
			qAssetInfoDto.setInitValue(parameters.get("initValue").toString());
		}
		/** modify by yanya on 20210824 修改为按照估值侧证券品种进行查询**/
		//qAssetInfoDto.setType8List(Arrays.asList("F11"));
		if(parameters.containsKey("IS_GP") && StringUtil.equals(DictCodeList.是否_是.getValue(), parameters.get("IS_GP").toString())) {
			qAssetInfoDto.setType18List(Arrays.asList(DictCodeListExt1.证券品种_股票.getValue()));
		}
		else {
			qAssetInfoDto.setType18List(Arrays.asList(DictCodeListExt1.证券品种_债券.getValue()));
			qAssetInfoDto.setType1List(Arrays.asList(DictCodeList.债券类型_可转债.getValue(),DictCodeList.债券类型_可交换债.getValue()));
		}
		List<AssetInfoDto> aseetS = restTemplate.postForList(
				ServiceApplication.VAL_MODEL_SERVICE_NAME + "/ModBintreeConfig/getAssetNoClassify", qAssetInfoDto,
				AssetInfoDto.class);
		resultList = aseetS.stream().map(operationStation -> {
			SelectOptionDto stationDictVO = new SelectOptionDto();
			stationDictVO.setId(operationStation.getFinprodId());
			stationDictVO.setText(operationStation.getFinprodAbbr());
			stationDictVO.setObject(operationStation.getFinprodMarketId());
			return stationDictVO;
		}).collect(Collectors.toList());

		return resultList;
    }
    
    /**
    * 导出
    */
   @Override
   protected void buildExportParam(ExportParam exportParam) {
       String export_conf = "export_conf";
       Map<String,Object> map = exportParam.getSelectParam();
       if(map.get("tableNum") != null){
           export_conf = export_conf + map.get("tableNum").toString();
       }
       exportParam.config(
               "/com/joyintech/tams/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/conf/export_conf.xml").dataSource(
                   DataSourceType.MASERT);
   }
   @Override
	public Page<ModBintreeConfigDto> selectPageData(Map<String, Object> selectParam) {
		return findPage(selectParam);
	} 
   
   
   /**
    * 设置单元格值
    * <p>
    * 如果单元格值需要特殊处理，比如数值计算等，可以实现该方法
    * </p>
    *
    * @param beanName sheet对应的JavaBean
    * @param cell 单元格
    * @param propName 属性名
    * @param value 单元格的值
    */
   @Override
   public void setCellValue(String beanName, Cell cell, String propName, Object value) {
       String[] dateArr = {"repStatusDate","repVdate","repMdate","repVdate3","repMdate3",
    		   "repVdate4","repMdate4","repVdate5","repMdate5","repVdate8","repMdate8",
    		   "repVdate9","repMdate9","repVdate11","repMdate11","repVdate15","repMdate15",
    		   "repSetDate","repEndDate","repValueDate",
                           "repExpiringDate","repInvestStartdate","repInvestEnddate"};
       for(int i = 0; i<dateArr.length; i++){
           if(StringUtil.equals(dateArr[i], propName)){
               cell.setCellValue(DateUtil.format((Date)value));
           }
       }
   }
    
}
