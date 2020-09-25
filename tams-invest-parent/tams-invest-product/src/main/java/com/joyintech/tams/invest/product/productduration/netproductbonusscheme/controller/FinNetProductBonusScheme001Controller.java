package com.joyintech.tams.invest.product.productduration.netproductbonusscheme.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto.FinNetProductBonusDealDto;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import com.joyintech.tams.invest.product.productduration.netproductbonusscheme.service.FinNetProductBonusSchemeService;
import com.joyintech.tams.systemtools.dto.PageElementConfigDto;
import com.joyintech.tams.systemtools.util.PageElementUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;

/**
 * 净值型分红方案管理controller
 *
 * @author liuwei
 * @version 1.0 2018年9月7日
 * @see FinNetProductBonusScheme001Controller
 * @since 1.0
 */
@Controller
@PageId("FinNetProductBonusScheme001")
@RequestMapping("/invest/product/productduration/netproductbonusscheme/FinNetProductBonusScheme001")
public class FinNetProductBonusScheme001Controller extends BaseController {

    /**
     * 净值型分红方案管理service
     */
    @Autowired
    private FinNetProductBonusSchemeService finNetProductBonusSchemeService;

    /**
     * 净值型分红方案界面的初始化
     * @return 初始化结果
     * @see
     * @since 1.0
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
    	setSelectOptionToModel(model,new DictCode[] {DictCode.发送状态});
    	
    	model.addAttribute("YES", DictCodeList.是否_是.getValue());
    	// 画面元素是否显示
        PageElementConfigDto pageElementConfigDto = new PageElementConfigDto();
        pageElementConfigDto.setPageId(this.getPageId());
        Map<String, String> isEleShow = PageElementUtil.isPageElementShow(pageElementConfigDto);

        model.addAttribute("isEleShow", isEleShow);
        return super.init(model);
    }

    /**
     * 产品代码的联想下拉框事件
     *
     * @return 查询的产品代码和产品名称列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketId")
    public List<SelectOptionDto> getFinprodMarketId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return finNetProductBonusSchemeService.getFinprodMarketId(map);
    }

    /**
     * 产品代码的联想下拉框事件
     *
     * @return 查询的产品代码和产品名称列表
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFinprodMarketIdList")
    public List<SelectOptionDto> getFinprodMarketIdList(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return finNetProductBonusSchemeService.getFinprodMarketIdList(map);
    }
    
    /**
     * 画面查询方法
     * @return 查询结果集
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinNetProductBonusSchemeDto> selectAll()
            throws Exception {
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("FINPROD_TYPE_TG_F24", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        map.put("FINPROD_TYPE_TG_F26", DictCodeListExt1.金融产品类型_投管用_理财子产品.getValue());
        map.put("BUSI_TYPE", DictCodeList.业务类型_净值型TA交易.getValue());
        Page<FinNetProductBonusSchemeDto> page = finNetProductBonusSchemeService.selectForPage(map);
        return page;
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupDelete.class}) FinNetProductBonusSchemeDto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     *  页面删除
     * @param dto 费用品种
     * @param br br
     * @return 删除结果
     * @throws Exception Exception
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinNetProductBonusSchemeDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinNetProductBonusSchemeDto> processParameter = new ProcessParameter<FinNetProductBonusSchemeDto>();
        WorkFlowParameter<FinNetProductBonusSchemeDto> params = new WorkFlowParameter<FinNetProductBonusSchemeDto>();
        dto.setTradeId(dto.getBonusPlanId());
        params.setPageId("FinNetProductBonusScheme002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getBonusPlanId());
        params.setBusinessType(DictCodeList.共通业务类型_净值型产品分红方案管理.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> "产品简称" + "(" + entity.getFinprodAbbr() + ")-" + "产品代码"
                + "(" + entity.getFinprodMarketId() + ")"+"-" + "分红方案编号" + "(" + entity.getBonusPlanId() + ")-"
                +(StringUtil.isNotEmpty(entity.getUnitBonusVlu())?("按单位分红" + "(" + entity.getUnitBonusVlu() + ")-" ):(""+ "按总额分红" + "(" + entity.getTotalAmt() + ")-")) 
                + "除权日" + "(" + DateUtil.format(entity.getDelDate()) + ")");
        processParameter.setWorkFlowParameter(params);
        
        String msg = finNetProductBonusSchemeService.start(processParameter,dto);
        return new AjaxResult(msg);
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */

    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
            throws Exception {
    	Map<String,Object> map = RequestUtil.getParameters(getRequest());
    	map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("BUSI_TYPE", DictCodeList.业务类型_净值型TA交易.getValue());
        return finNetProductBonusSchemeService.export(this.getPageId(),map);

    }
    
    @RequestMapping("bonusDetailInit")
    public String bonusDetailInit(Model model){
        String bTradeId = this.getParam("bTradeId");
        model.addAttribute("bTradeId",bTradeId);
        return "invest/product/productduration/netproductbonusscheme/FinNetProductBonusDetails";
    }
    
    @ResponseBody
    @RequestMapping("queryBonusDetail")
    public Page<FinNetProductBonusDealDto> queryBonusDetail(){
        Map<String,Object> map = RequestUtil.getParameters(getRequest());
        List<FinNetProductBonusDealDto> list = finNetProductBonusSchemeService.queryBonusDetail(map);
        Page<FinNetProductBonusDealDto> page = new Page<FinNetProductBonusDealDto>();
        page.setRoot(list);
        return page;
    }
    
    @ResponseBody
    @RequestMapping("idBonusAmtConfirm")
    public AjaxResult idBonusAmtConfirm(@RequestBody FinNetProductBonusSchemeDto dto){    	
    	AjaxResult ar = new AjaxResult();
    	String tradeId = finNetProductBonusSchemeService.idBonusAmtConfirm(dto);
        ar.setData(tradeId);
    	return ar;
    }
    
    /**
     * 产品分红方案-发送销售接口状态
     *
     * @param FinNetProductBonusSchemeDto
     * @return 发送结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("sendProductBonusSchemeToSale")
    public AjaxResult sendProductBonusSchemeToInterf(@RequestBody FinNetProductBonusSchemeDto finNetProductBonusSchemeDto) {
        //或许选择推送的数据，循环调用推送销售系统方法
        List<FinNetProductBonusSchemeDto> dtoList = finNetProductBonusSchemeDto.getSelectedItem() ;
        AjaxResult ar = new AjaxResult() ;
        if(dtoList.size() > 0){
            List<ValidMessage> errorList = ar.getErrorList();
            boolean sendFlag = true ;// 默认状态为推送销售系统
            for (FinNetProductBonusSchemeDto finProductBonusSchemeDto : dtoList) {
                // 查询是否已经推送销售系统
                if(DictCodeList.发送状态_发送成功.getValue().equals(finProductBonusSchemeDto.getSendStatus())){
                    errorList.add(new ValidMessage(this.getMessage("FIN_PROD_E_0032","未发送和发送失败")));
                    sendFlag = false ;
                    return ar ;
                }
            }
			if (sendFlag) {
				List<String> bonusPlanIds = new ArrayList<>();
				for (FinNetProductBonusSchemeDto model : dtoList) {
					bonusPlanIds.add(model.getBonusPlanId());
				}
				// 推送产品分红至销售系统
				Map<String, String> map = finNetProductBonusSchemeService.sendProductBonusSchemeToSale(bonusPlanIds);
				errorList.add(new ValidMessage(map.get("msg")));
			}
        }
        return ar;
    }
}
