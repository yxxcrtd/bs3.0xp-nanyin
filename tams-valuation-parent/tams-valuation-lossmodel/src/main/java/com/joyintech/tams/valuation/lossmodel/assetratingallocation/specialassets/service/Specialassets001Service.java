package com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.dto.AssetInfoDto;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.specialassets.dto.ModAssetGradeSpecialConfigDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
//import com.joyintech.tams.model.common.constant.ServiceApplication;
//import com.joyintech.tams.model.common.dto.AssetInfoDto;
//import com.joyintech.tams.model.impairment.assetratingallocation.specialassets.dto.ModAssetGradeSpecialConfigDto;

/**
 * 资产端品种与期限分布明细表
 * @author yuluan
 * @version 1.0 2019年08月01日
 * @see Specialassets001Service
 * @since 1.0
 */
@Service
public class Specialassets001Service extends CommonExportService<ModAssetGradeSpecialConfigDto>{
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<ModAssetGradeSpecialConfigDto> findPage(Map<String,Object> map){
        
        return restTemplate.postForPage(
            ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/selectForPage",
            map, ModAssetGradeSpecialConfigDto.class);
    }
//    public Boolean check(@RequestBody Map<String, Object> param) {
//        return restTemplate.postForObject(
//                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/TrdInvestDealQuery001/getFinprodIdForImpairment", param,
//                Boolean.class);
//    }
    public List<ModAssetGradeSpecialConfigDto> selectForExport(Map<String,Object> map){
        
        return restTemplate.postForList(
            ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/selectForExport",
            map, ModAssetGradeSpecialConfigDto.class);
    }
    
    public List<SelectOptionDto> getFinprodIdForImpairment(Map<String, Object> parameters) {
    	
		List<SelectOptionDto> resultList = new ArrayList<SelectOptionDto>();
//		ModAseetMappingDto qModAseetMappingDto=new ModAseetMappingDto();
//		qModAseetMappingDto.setInput1List(Arrays.asList("F01","F18"));
//		List<ModAseetMappingDto> tgfls=restTemplate.postForList(
//				ServiceApplication.MODEL_SERVICE_NAME + "/ImpairmentCommon/getModAseetMappingByModel", qModAseetMappingDto,
//				ModAseetMappingDto.class);
		AssetInfoDto qAssetInfoDto=new AssetInfoDto();
		if(parameters.get("search")!=null&&!"".equals(parameters.get("search"))){
			qAssetInfoDto.setSearch(parameters.get("search").toString());
		}
		if(parameters.get("initValue")!=null&&!"".equals(parameters.get("initValue"))){
			qAssetInfoDto.setInitValue(parameters.get("initValue").toString());
		}
//		qAssetInfoDto.setType12List(tgfls.stream().map(ModAseetMappingDto::getOutput5).collect(Collectors.toList()));
		List<AssetInfoDto> aseetS = restTemplate.postForList(
				ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/getAssetForImpairment", qAssetInfoDto,
				AssetInfoDto.class);
		resultList = aseetS.stream().map(operationStation -> {
			SelectOptionDto stationDictVO = new SelectOptionDto();
			stationDictVO.setId(operationStation.getFinprodId());
			stationDictVO.setText(operationStation.getFinprodAbbr());
			stationDictVO.setCol(operationStation.getType10());
			return stationDictVO;
		}).collect(Collectors.toList());

		return resultList;
    }

    
    /**
     * 根据条件查询所有
     * @return 
     * @see
     * @since 1.0
     */
    public List<ModAssetGradeSpecialConfigDto> findAll(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.REPORT_SERVICE_NAME + "/AssetSideDetialReport/findAll",
            map, ModAssetGradeSpecialConfigDto.class);
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
        
       /*exportParam.config(
           "/com/joyintech/tams/invest/report/debtreport/repassetdeptregist/conf/"+export_conf+".xml").dataSource(
               DataSourceType.MASERT);*/
       exportParam.config(
               "/com/joyintech/tams/model/impairment/assetratingallocation/specialassets/conf/export_conf.xml").dataSource(
                   DataSourceType.MASERT);
   }
   @Override
	public Page<ModAssetGradeSpecialConfigDto> selectPageData(Map<String, Object> selectParam) {
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
   /**
    * 根据产品代码模糊查询联系下拉框
    * 
    * @param map 参数map
    * @return 产品代码和简称
    * @see
    * @since 1.0
    */
   public List<SelectOptionDto> queryAssetCodeInfo(Map<String, Object> map) {
       map.put("userId", SecurityUtil.getLoginUser().getUserName());
       return restTemplate.postForList(
           ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/Specialassets/getFinprodIdForImpairment", map,
           SelectOptionDto.class);
   }
    
}
