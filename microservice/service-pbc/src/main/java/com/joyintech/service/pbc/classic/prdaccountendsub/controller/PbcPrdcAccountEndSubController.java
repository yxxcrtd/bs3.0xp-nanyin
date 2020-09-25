package com.joyintech.service.pbc.classic.prdaccountendsub.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.dto.PbcPrdAccountEndSub;
import com.joyintech.tams.svreport.pbc.classic.prdaccountendsub.service.PbcPrdAccountEndSubService001;

/**
 * 
* 资产池终止信息controller。
* @ClassName: PbcAccountEndSubController 
* @author liuyu
* @date 2019年6月17日 上午10:38:00 
*
 */
@RestController
@RequestMapping("/PbcPrdAccountEndSub")
public class PbcPrdcAccountEndSubController {
    /**
     * 资产池基本信息service
     */
    @Autowired
    private PbcPrdAccountEndSubService001 pbcAccountEndSubService001;
    
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<PbcPrdAccountEndSub> searchPage(@RequestBody Map<String, Object> param) {
       Page<PbcPrdAccountEndSub> page = pbcAccountEndSubService001.searchPage(param);
       return page;
   }
   /**
    * 手工处理功能
    *
    * @return 提示信息
    * @param map
    *            条件map
    * @seeapbcAccountEndSubService001
    * @since 1.0
    */
   @PostMapping("updateSumStaus")
   public void updateSumStaus(@RequestBody Map<String, Object> map) {
       pbcAccountEndSubService001.doManul(map);
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
       return pbcAccountEndSubService001.doCancelManul(map);
   }
}
