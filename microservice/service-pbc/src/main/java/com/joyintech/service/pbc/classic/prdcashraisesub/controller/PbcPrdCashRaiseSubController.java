package com.joyintech.service.pbc.classic.prdcashraisesub.controller;

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
import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.dto.PbcProdRaiseSub;
import com.joyintech.tams.svreport.pbc.classic.prdcashraisesub.service.PbcPrdCashRaiseSubService001;

/**
 * 
* 产品资金募集信息controller。
* @ClassName: PbcPrdCashRaiseSubController 
* @author liuyu
* @date 2019年6月17日 上午10:38:00 
*
 */
@RestController
@RequestMapping("/PbcPrdCashRaiseSub")
public class PbcPrdCashRaiseSubController {
    /**
     * 产品资金募集信息service
     */
    @Autowired
    private PbcPrdCashRaiseSubService001 pbcPrdCashRaiseSubService001;
    /**
    *
    * 业务画面查询事件
    *
    * @return
    * @see
    * @since 1.0
    */
   @PostMapping("findPage/{page}/{length}")
   public Page<PbcProdRaiseSub> searchPage(@RequestBody Map<String, Object> param) {
       Page<PbcProdRaiseSub> page = pbcPrdCashRaiseSubService001.searchPage(param);
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
       pbcPrdCashRaiseSubService001.doManul(map);
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
       return pbcPrdCashRaiseSubService001.doCancelManul(map);
   }
    
   /**
    * 数据检查
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
        	keyList=pbcPrdCashRaiseSubService001.getCheckRepList(param);
        }
        else{
        	keyList=StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
        }
        if (CollectionUtils.isEmpty(keyList)) {
            List<ExcelValidDto> checkResult = new ArrayList<ExcelValidDto>();
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
            return checkResult;
        }
        return pbcPrdCashRaiseSubService001.checkData(keyList);
	}
}
