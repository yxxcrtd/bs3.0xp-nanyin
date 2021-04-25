/*
 * 文件名：FinBondTargetAsset001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年9月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.bond.controller;

import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

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
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.invest.asset.bond.dto.FinBondTargetAssetDto;
import com.joyintech.tams.invest.asset.bond.service.FinBondTargetAssetService;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 债券类标的信息管理
 * @author zhangnian
 * @version 1.0 2018年9月20日
 * @see FinBondTargetAsset002Controller
 * @since 1.0
 */
@Controller
@PageId("FinBondTargetAsset001")
@RequestMapping("/invest/asset/bond/FinBondTargetAsset001")
public class FinBondTargetAsset001Controller extends BaseController{
   
    @Autowired
    private FinBondTargetAssetService finBondTargetAssetService;

    @Autowired
    private CommonBusinessService commonBusinessService;
    /**
     * 初始化数据字典下拉框
     */
    private static final IDictCode[] DICT_CODES = new DictCode[] {DictCode.息票品种,DictCode.利率类型,
    	// 新增行内五级分类 wangguiqi 2019-07-23 start
    	DictCode.行内资产二级分类, DictCode.行内资产三级分类, DictCode.行内资产四级分类, DictCode.行内资产五级分类,
    	// 新增行内五级分类 wangguiqi 2019-07-23 end	
        DictCode.WIND一级分类_含债券一级分类等,DictCode.WIND二级分类_含债券二级分类等,DictCode.是否,DictCode.币种};
    
    @Override
    public String init(Model model){
        setSelectOptionToModel(model, DICT_CODES);
        model.addAttribute("FINPROD_TYPE_TG_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        model.addAttribute("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE")==null?"01":CacheUtil.getSysParam("IS_SHOW_TYPE"));
        //是否新增债券查询条件添加004控制  modify by liupeilin 2020-01-15  begin
        model.addAttribute("YES",DictCodeList.是否_是.getValue());
        model.addAttribute("YES_OR_NO_Y", DictCodeList.是否_是.getValue());
        if("004".equals(CacheUtil.getSysParam("CUSTOMER_BANK_CODE"))){
            model.addAttribute("BANK_CODE", "004");
        }
        //是否新增债券查询条件添加004控制  modify by liupeilin 2020-01-15  end
        return super.init(model);
    }
    
    
    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<FinBondTargetAssetDto> selectAll()
        throws Exception {
        Page<FinBondTargetAssetDto> page = finBondTargetAssetService.selectForPage(
            RequestUtil.getParameters(getRequest()));
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
        ValidateGroupDelete.class}) FinBondTargetAssetDto dto, BindingResult result) {
        
        //新增撤销校验，不允许删除上市债券  add by mashukai 20210425 start
        AjaxResult res= new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        if (dto.getSelectedItem() != null && dto.getSelectedItem().size()>0
                && dto.getSelectedItem().get(0).getFinprodId() != null){
            String finprodId = dto.getSelectedItem().get(0).getFinprodId();
            int count = finBondTargetAssetService.selectIsIssueAsset(finprodId);
            if (count > 0){
                ValidMessage validMessage = new ValidMessage("上市债券不允许删除,请先撤销对应的上市流通交易");
                errorList.add(validMessage);
                res.setErrorList(errorList);
                res.setMessageType(MessageType.错误消息);
                return res;
            }
        }
        //新增撤销校验，不允许删除上市债券  add by mashukai 20210425 end
        
        return commonCheck(result, dto.getOperateType());
    }


    @ResponseBody
    @RequestMapping("editCheck")
    public AjaxResult editCheck(){
        Map<String,Object> param = RequestUtil.getPageParam();
        //判断是否存在交易
        List<JoyinMessage> joyinMessages = new ArrayList<>();
        List<ValidMessage> errorList = new ArrayList<ValidMessage>();
        //区分含权债债券标的；含权债债券标的做完含权交易硬控不可改标的信息 add by yujinfa start
        String isRightStr = (String) param.get("isRightStr");
        AjaxResult wr = new AjaxResult();
        if(null != commonBusinessService){
            if(commonBusinessService.isTransInAsset((String)param.get("finprodId"))>0&&(DictCodeList.是否_否.getCodeName().equals(isRightStr))){
                JoyinMessage ms = new JoyinMessage(getMessage("FIN_ASSET_W_0002"),
                        MessageType.确认消息);
                joyinMessages.add(ms);
                wr.setJoyinMessages(joyinMessages);
                return wr;
            }
            else if(commonBusinessService.isBondExerciseAsset((String)param.get("finprodId"))>0&&(DictCodeList.是否_是.getCodeName().equals(isRightStr))){
                errorList.add(new ValidMessage(getMessage("含权债已存在行权交易，不允许修改资产信息!")));
                wr.setErrorList(errorList);
                return wr;
            }
            else{
                return wr;
            }
        }
        else{
                return wr;
        }
        //区分含权债债券标的；含权债债券标的做完含权交易硬控不可改标的信息 add by yujinfa end
    }

    
    /**
     * 删除
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Validated({
        ValidateGroupDelete.class}) FinBondTargetAssetDto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<FinBondTargetAssetDto> processParameter = new ProcessParameter<FinBondTargetAssetDto>();
        WorkFlowParameter<FinBondTargetAssetDto> params = new WorkFlowParameter<FinBondTargetAssetDto>();
        params.setPageId("FinBondTargetAsset002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(item -> item.getFinprodMarketId());
        params.setBusinessType(DictCodeList.共通业务类型_债券类资产信息.getValue());
        // 设置流程业务说明, entity即传入的dto
        params.setComment(entity -> {
            return WorkFlowUtil.concatComment(getMessage("FinBondTargetAsset002.finprodId")+
                GlobalConstant.OPEN_PARENTHESIS+entity.getFinprodMarketId()+GlobalConstant.CLOSE_PARENTHESIS+
                getMessage("FinBondTargetAsset002.finprodName")+GlobalConstant.OPEN_PARENTHESIS+
                entity.getFinprodAbbr()+GlobalConstant.CLOSE_PARENTHESIS);
        });
        processParameter.setWorkFlowParameter(params);
        String msg = finBondTargetAssetService.start(processParameter,
            dto.getSelectedItem().toArray(
                new FinBondTargetAssetDto[dto.getSelectedItem().size()]));
        return new AjaxResult(msg);
    }
    
    /**
     * 导出
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
    	Map<String, Object> param = RequestUtil.getParameters(getRequest());
    	param.put("isShowType", CacheUtil.getSysParam("IS_SHOW_TYPE"));
        return finBondTargetAssetService.export(this.getPageId(),param);
    }
}
