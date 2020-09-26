package com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.dto.PbcProdRaiseSub;
/**
 * 人行报表 服务
 * 〈功能详细描述〉
 * @author liuyu
 * @version 1.0 2019年6月17日
 * @see PbcProdRaiseSubMService
 * @since 1.0
 */
@Service
public class PbcPrdCashRaiseSubMService extends BaseService<PbcProdRaiseSub>{
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   public Page<PbcProdRaiseSub> searchPage(Map<String, Object> param) {
       return restTemplate.postForPage(
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdCashRaiseSub/findPage",param,
               PbcProdRaiseSub.class);
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
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdCashRaiseSub/updateSumStaus",map,
               Integer.class);
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
               SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdCashRaiseSub/cancelMannul",map,
               String.class);
   }
   
   /**
    * 数据检查
    * @param map 页面信息
    * @return 检查结果
    */
   public List<ExcelValidDto> reportCheck(Map<String, Object> map) {
		return restTemplate.postForList(
	       		SvrServiceApplication.PBC_SERVICE_NAME + "/PbcPrdCashRaiseSub/reportCheck", map,
	       		ExcelValidDto.class);
	}
}
