package com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.service;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dto.PbcPrdBaseinfoEndSub;
/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author liuyu
 * @version 1.0 2019年6月17日
 * @see PbcPrdBaseinfoEndSubMService
 * @since 1.0
 */
@Service
public class PbcPrdBaseinfoEndSubMService extends CommonExportService<PbcPrdBaseinfoEndSub> {
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   public Page<PbcPrdBaseinfoEndSub> searchPage(Map<String, Object> param) {
       return restTemplate.postForPage(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoEndSub/findPage",param,
               PbcPrdBaseinfoEndSub.class);
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
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoEndSub/updateSumStaus",map,
               Integer.class);
   }
   
   public String recalReport(Map<String, Object> map){
       return restTemplate.postForObject(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoEndSub/recalReport",map,
               String.class);
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
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoEndSub/cancelMannul",map,
               String.class);
   }
   
   /**
   * 数据检查.
   * @author liuyu
   * @time 2019年6月25日 下午3:07:29
   * @param map
   * @return
    */
   public List<ExcelValidDto> reportCheck(Map<String, Object> map) {
       return restTemplate.postForList(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdBaseinfoEndSub/reportCheck", map,
               ExcelValidDto.class);
   }

}
