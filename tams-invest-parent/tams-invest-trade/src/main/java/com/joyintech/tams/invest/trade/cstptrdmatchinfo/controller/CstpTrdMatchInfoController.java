package com.joyintech.tams.invest.trade.cstptrdmatchinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.trade.cstptrdmatchinfo.dto.CstpTrdMatchInfoDto;
import com.joyintech.tams.invest.trade.cstptrdmatchinfo.service.CstpTrdMatchInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @ClassName CstpTrdMatchInfoController
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
@Controller
@PageId("CSTPTrdMatchInfo")
@RequestMapping("/invest/trade/cstp/CSTPTrdMatchInfo")
public class CstpTrdMatchInfoController  extends BaseController {
    @Autowired
    private CstpTrdMatchInfoService cstpTrdMatchInfoService;

    @ResponseBody
    @RequestMapping("findPage")
    public Page<CstpTrdMatchInfoDto> findPage(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return cstpTrdMatchInfoService.findPage(map);
    }

    @ResponseBody
    @RequestMapping("saveTrdMatchInfoByCstp")
    public AjaxResult saveTrdMatchInfoByCstp(@RequestBody CstpTrdMatchInfoDto dto){
        try {
            cstpTrdMatchInfoService.saveTrdMatchInfoByCstp(dto);
        } catch (Exception e) {
           return new AjaxResult("交易匹配确认失败",MessageType.错误消息);
        }
        return new AjaxResult("交易匹配确认成功",MessageType.提示消息);
    }

    @ResponseBody
    @RequestMapping("deleteTrdMatchInfoByCstp")
    public AjaxResult deleteTrdMatchInfoByCstp(@RequestBody CstpTrdMatchInfoDto dto){
        try {
            cstpTrdMatchInfoService.deleteTrdMatchInfoByCstp(dto);
        } catch (Exception e) {
            return new AjaxResult("false");
        }
        return new AjaxResult();
    }
    
    /**
     * 导出
     * @return
     * @throws Exception
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export() throws Exception {
        return cstpTrdMatchInfoService.export(this.getPageId(),RequestUtil.getParameters(getRequest()));
    }
    
    /**
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkPosition")
    public Map<String,Object> checkPosition(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return cstpTrdMatchInfoService.checkPosition(map);
    }
    
    
}
