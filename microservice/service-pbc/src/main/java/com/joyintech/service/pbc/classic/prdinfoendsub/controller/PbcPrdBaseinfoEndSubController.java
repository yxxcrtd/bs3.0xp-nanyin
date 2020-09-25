package com.joyintech.service.pbc.classic.prdinfoendsub.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.dto.PbcPrdBaseinfoEndSub;
import com.joyintech.tams.svreport.pbc.classic.prdinfoendsub.serivce.PrdBaseinfoEndSubService001;

/**
 * 
* 产品终止信息controller。
* @ClassName: PbcPrdBaseinfoEndSubController 
* @author liuyu
* @date 2019年6月17日 上午10:38:00 
*
 */
@RestController
@RequestMapping("/PbcPrdBaseinfoEndSub")
public class PbcPrdBaseinfoEndSubController {
    /**
     * 产品终止信息service
     */
    @Autowired
    private PrdBaseinfoEndSubService001 pbcPrdBaseinfoEndSubService001;
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<PbcPrdBaseinfoEndSub> searchPage(@RequestBody Map<String, Object> param) {
       Page<PbcPrdBaseinfoEndSub> page = pbcPrdBaseinfoEndSubService001.searchPage(param);
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
       pbcPrdBaseinfoEndSubService001.doManul(map);
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
       return pbcPrdBaseinfoEndSubService001.doCancelManul(map);
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
       List<String> keyList = new ArrayList<String>();
       Map<String, Object> param = map;
       if(!param.containsKey("checkKeys")){
           keyList=pbcPrdBaseinfoEndSubService001.getCheckRepList(param);
       }
       else{
           keyList=StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
       }
       if (CollectionUtils.isEmpty(keyList)) {
           List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
           checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
           return checkResult;
       }
       return pbcPrdBaseinfoEndSubService001.checkData(keyList);
   }
    
}
