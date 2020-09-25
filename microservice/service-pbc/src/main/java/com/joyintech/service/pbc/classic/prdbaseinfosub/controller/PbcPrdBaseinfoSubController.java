package com.joyintech.service.pbc.classic.prdbaseinfosub.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.dto.PbcPrdBaseinfoSub;
import com.joyintech.tams.svreport.pbc.classic.prdbaseinfosub.service.PbcPrdBaseinfoSubService001;

/**
 * 
* 产品基本信息controller。
* @ClassName: PbcPrdBaseinfoSubController 
* @author liuyu
* @date 2019年6月17日 上午10:38:00 
*
 */
@RestController
@RequestMapping("/PbcPrdBaseinfoSub")
public class PbcPrdBaseinfoSubController {
    /**
     * 产品基本信息service
     */
    @Autowired
    private PbcPrdBaseinfoSubService001 pbcPrdBaseinfoSubService001;
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<PbcPrdBaseinfoSub> searchPage(@RequestBody Map<String, Object> param) {
       Page<PbcPrdBaseinfoSub> page = pbcPrdBaseinfoSubService001.searchPage(param);
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
       pbcPrdBaseinfoSubService001.doManul(map);
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
       return pbcPrdBaseinfoSubService001.doCancelManul(map);
   }
   /**
    * 报送检查
    * @param map 查询条件
    * @return 检查结果
    * @see
    * @since 1.0
    */
   @PostMapping("reportCheck")
   public List<ExcelValidDto> reportCheck(@RequestBody Map<String,Object> map) {
       return pbcPrdBaseinfoSubService001.checkData(map);
   }
}
