package com.joyintech.tams.invest.entrust.etrbondbiddingresult.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.entrust.etrbondbiddingresult.dto.Etrbondbiddingresult001Dto;
import com.joyintech.tams.invest.entrust.etrbondbiddingresult.service.EtrbondbiddingresultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@PageId("EtrBondBiddingResult001")
@RequestMapping("/invest/entrust/etrbondbiddingresult/EtrBondBiddingResult001")
public class EtrBondBiddingResult001Controller extends BaseController {

    @Autowired
    private EtrbondbiddingresultService etrbondbiddingresultService;
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.交易方向, BussinessDictCode.交易场所,
                BussinessDictCode.交易流程状态, BussinessDictCode.交易平台, BussinessDictCode.交易市场, BussinessDictCode.缴款状态);
        //add by chenjiaxing 主动提醒跳转初始化 start
        Map<String, Object> map = RequestUtil.getPageParam();
        if(StringUtil.isNotEmpty(map.get("remType")) && StringUtil.equals(map.get("remType"),"R10S1")) {
            model.addAttribute("finprodId", (String)map.get("finprodId"));
            model.addAttribute("mdate", (String)map.get("mdate"));
        }
        //add by chenjiaxing 主动提醒跳转初始化 end
        return super.init(model);
    }


    /**
     *
     * 基金分页查询
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<Etrbondbiddingresult001Dto> selectForPage(){
        Page<Etrbondbiddingresult001Dto> page = etrbondbiddingresultService.selectForPage(
                RequestUtil.getPageParam());
        return page;
    }

    /**
     * 文件导出 <功能详细描述>
     *
     * @return 导出返回参数
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
        return etrbondbiddingresultService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
    }

    @ResponseBody
    @RequestMapping("/findFinProductByOFinprodId")
    public FinProduct findFinProductByOFinprodId(@RequestBody Etrbondbiddingresult001Dto dto){
        Map<String,Object> objectMap = new HashMap<>();
        objectMap.put("oFinprodId",dto.getFinprodId());
        List<FinProduct> result = etrbondbiddingresultService.findFinProductByOFinprodId(objectMap);
        if (result == null || result.isEmpty()) {
            return new FinProduct();
        }
        return result.get(0);
    }

    /**
     * 手工释放check
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("checkManualRelease")
    public AjaxResult checkManualRelease(@RequestBody Map<String,Object> map){
        String msg = StringUtil.EMPTY;
        //1.根据有用参数查询是否设置了冻结头寸
        int  checkFrozenPosition = etrbondbiddingresultService.checkFrozenPosition(map);
        if(checkFrozenPosition == 0){
            msg = map.get("portfolioId")+"组合并未有冻结头寸，请确认！";
            return new AjaxResult(msg, MessageType.错误消息);
        }
        //2.是否已释放头寸
        BigDecimal getHasFrozenPosition = etrbondbiddingresultService.getHasFrozenPosition(map);
        if(checkFrozenPosition != 0 && (getHasFrozenPosition == null || getHasFrozenPosition.compareTo(BigDecimal.ZERO) == 0)){
            msg = map.get("portfolioId")+"组合对应头寸已释放，不能重复释放！";
            return new AjaxResult(msg, MessageType.错误消息);
        }
        return new AjaxResult("确认释放头寸？",MessageType.确认消息);
    }
    /**
     * 手工释放头寸
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("doManualRelease")
    public AjaxResult doManualRelease(@RequestBody Map<String,Object> map) throws ParseException {
        etrbondbiddingresultService.doManualRelease(map);
        return new AjaxResult("头寸释放成功！",MessageType.提示消息);
    }
}
