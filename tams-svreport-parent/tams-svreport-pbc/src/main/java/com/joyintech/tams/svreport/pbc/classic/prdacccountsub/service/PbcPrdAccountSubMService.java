package com.joyintech.tams.svreport.pbc.classic.prdacccountsub.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.svreport.pbc.classic.prdacccountsub.dto.PbcPrdAccountSub;
/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author liuyu
 * @version 1.0 2019年6月17日
 * @see PbcPrdAccountSubMService
 * @since 1.0
 */
@Service
public class PbcPrdAccountSubMService extends BaseService<PbcPrdAccountSub> {
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   public Page<PbcPrdAccountSub> searchPage(Map<String, Object> param) {
       return restTemplate.postForPage(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdAccountSub/findPage",param,
               PbcPrdAccountSub.class);
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
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdAccountSub/updateSumStaus",map,
               void.class);
   }
   
   public String recalReport(Map<String, Object> map){
       return restTemplate.postForObject(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdAccountSub/recalReport",map,
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
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdAccountSub/cancelMannul",map,
               String.class);
   }
    

}
