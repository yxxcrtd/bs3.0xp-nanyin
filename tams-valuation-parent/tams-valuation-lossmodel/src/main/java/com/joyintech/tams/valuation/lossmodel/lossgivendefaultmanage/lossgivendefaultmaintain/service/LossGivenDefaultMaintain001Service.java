package com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.service;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.lossgivendefaultmanage.lossgivendefaultmaintain.dto.ModLgdConfigDto;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 资产端品种与期限分布明细表
 * @author yuluan
 * @version 1.0 2019年08月01日
 * @see LossGivenDefaultMaintain001Service
 * @since 1.0
 */
@Service
public class LossGivenDefaultMaintain001Service extends CommonExportService<ModLgdConfigDto>{
    /**
     * 分页查询
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<ModLgdConfigDto> findPage(Map<String,Object> map){
        
        return restTemplate.postForPage(
            ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/LossGivenDefaultMaintain/selectForPage",
            map, ModLgdConfigDto.class);
    }
 
    public List<ModLgdConfigDto> selectForExport(Map<String,Object> map){
        return restTemplate.postForList(
            ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/LossGivenDefaultMaintain/selectForExport",
            map, ModLgdConfigDto.class);
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
	public Page<ModLgdConfigDto> selectPageData(Map<String, Object> selectParam) {
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
     * 通用联动查询
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.LOSS_MODEL_SERVICE_NAME
                        + "/LossGivenDefaultMaintain/getLinkSelectOption",
                param, SelectOptionDto.class);
    }
    
}
