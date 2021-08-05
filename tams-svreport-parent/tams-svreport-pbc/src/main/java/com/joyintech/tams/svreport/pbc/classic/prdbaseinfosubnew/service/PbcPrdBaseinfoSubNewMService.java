package com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosubnew.dto.PbcPrdBaseinfoSubNew;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author liuyu
 * @version 1.0 2019年6月17日
 * @see PbcPrdBaseinfoSubNewMService
 * @since 1.0
 */
@Service
public class PbcPrdBaseinfoSubNewMService extends BaseService<PbcPrdBaseinfoSubNew> {
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   public Page<PbcPrdBaseinfoSubNew> searchPage(Map<String, Object> param) {
       return restTemplate.postForPage(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSubNew/findPage",param,
               PbcPrdBaseinfoSubNew.class);
   }


    /**
     * 人行产品代码下拉框初始值
     * @param param
     *
     * @return 人行产品代码下拉框初始值
     */
    @SuppressWarnings("unchecked")
    public List<SelectOptionDto> getPbcProdId(Map<String, Object> param) {
        return restTemplate.postForObject(
                SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSubNew/getPbcProdId",param,
                List.class);
    }

   /**
    * 手工处理功能
    *
    * @return 提示信息
    * @param map
    *            条件map
    * @seea
    * @since 1.0
    */
   public void updateSumStaus(Map<String, Object> map) {
       restTemplate.postForObject(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSubNew/updateSumStaus",map,
               Integer.class);
   }
   
   public String recalReport(Map<String, Object> map){
       return restTemplate.postForObject(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSubNew/recalReport",map,
               String.class);
   }
   /**
    * 数据检查
    * @param map 查询条件
    * @return 检查结果
    * @see
    * @since 1.0
    */
   public List<ExcelValidDto> reportCheck(Map<String,Object> map) {
	   return restTemplate.postForList(
	       		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSubNew/reportCheck", map,
	       		ExcelValidDto.class);
   }
   /**
    * 取消手工处理功能
    *
    * @return 提示信息
    * @param map
    *            条件map
    * @seea
    * @since 1.0
    */
   public String cancelMannul(Map<String, Object> map) {
       return restTemplate.postForObject(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoSubNew/cancelMannul",map,
               String.class);
   }
    

}
