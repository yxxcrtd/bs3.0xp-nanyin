package com.joyintech.service.pbc.classic.prdinitraisesub.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.dto.PbcInitProdRaisingSub;
import com.joyintech.tams.svreport.pbc.classic.prdinitraisesub.service.PbcPrdInitRaiseSubService001;

/**
 * 
* 产品起始募集信息controller。
* @ClassName: PbcInitProdRaisingSubController 
* @author liuyu
* @date 2019年6月17日 上午10:38:00 
*
 */
@RestController
@RequestMapping("/PbcInitProdRaisingSub")
public class PbcInitProdRaisingSubController {
    /**
     * 产品起始募集信息service
     */
    @Autowired
    private PbcPrdInitRaiseSubService001 pbcPrdInitRaiseSubService001;
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   
   @PostMapping("findPage/{page}/{length}")
   public Page<PbcInitProdRaisingSub> searchPage(@RequestBody Map<String, Object> param) {
       Page<PbcInitProdRaisingSub> page = pbcPrdInitRaiseSubService001.searchPage(param);
       return page;
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
   
   @PostMapping("updateSumStaus")
   public void updateSumStaus(@RequestBody Map<String, Object> map) {
       pbcPrdInitRaiseSubService001.doManul(map);
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
   
   @PostMapping("cancelMannul")
   public String cancelMannul(@RequestBody Map<String, Object> map) {
       return pbcPrdInitRaiseSubService001.doCancelManul(map);
   }
}
