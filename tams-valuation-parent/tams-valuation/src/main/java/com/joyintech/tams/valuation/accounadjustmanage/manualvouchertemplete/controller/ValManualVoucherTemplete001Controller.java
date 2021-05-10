/*
 * 文件名：ValManualVoucherTemplete001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：weipeng
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.dto.ValManualVoucherTemplete001SearchDto;
import com.joyintech.tams.valuation.accounadjustmanage.manualvouchertemplete.service.ValManualVoucherTemplete001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * 手工凭证模板
 * 
 * @author sunshuaishuai
 * @version 2.0 2021年2月8日
 * @see ValManualVoucherTemplete001Controller
 * @since 2.0
 */
@Controller
@PageId("ValManualVoucherTemplete001")
@RequestMapping("/valuation/accounadjustmanage/manualvouchertemplete/ValManualVoucherTemplete001")
public class ValManualVoucherTemplete001Controller extends BaseController {
    
    /**
     * ValManualVoucherTemplete001Service
     */
    @Autowired
    private ValManualVoucherTemplete001Service valManualVoucherTemplete001Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.金融产品类型);
        this.setSelectOptionToModel(model, DictCode.是否);
        
        model.addAttribute("YES", DictCodeList.是否_是.getValue());
       // model.addAttribute("FINPRODID_LIST",JsonUtils.objectArray2Json(valManualVoucherTemplete001Service.getBooksetIdForSelect().toArray()));
        return super.init(model);
    }

    /**
     * 页面查询
     *
     * @return 查询数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValManualVoucherTemplete001Dto> findPage() throws Exception {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        ValManualVoucherTemplete001SearchDto dto = (ValManualVoucherTemplete001SearchDto)JsonUtils.map2entity(paraMap, ValManualVoucherTemplete001SearchDto.class);
        return valManualVoucherTemplete001Service.findPage(dto);
    }

    /**
     * 删除时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @param operateType 操作类型
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ValManualVoucherTemplete001Dto dto, BindingResult result, String operateType) {
        return commonCheck(result, operateType);
    }

    /**
     * 手工凭证删除
     * @param dto
     * @param br
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody @Valid ValManualVoucherTemplete001Dto dto, BindingResult br) throws Exception{

        if(br.hasErrors()){
            return new AjaxResult(br);
        }
        if(dto!=null){
            if( valManualVoucherTemplete001Service.delete(dto)){
                return new AjaxResult("删除成功");
            }
        }
        return new AjaxResult("删除失败");
    }

    /**
     * 导出信息excel
     *
     * @return excel
     * @throws Exception
     *             异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export() throws Exception {
        Map<String, Object> params= RequestUtil.getPageParam();
        String datas = this.getParam("datas");
        ValManualVoucherTemplete001SearchDto dto = (ValManualVoucherTemplete001SearchDto)JsonUtils.map2entity(params, ValManualVoucherTemplete001SearchDto.class);
        List<ValManualVoucherTemplete001Dto> dtoList;
        if (datas == null) {
            dtoList = valManualVoucherTemplete001Service.selectAll(dto);
        }
        else {
            dtoList = JsonUtils.json2List(datas, ValManualVoucherTemplete001Dto.class);
        }
        File outFile=null;
        if(StringUtil.isNotEmpty(dto.getIsShowfl()) && "no".equals(dto.getIsShowfl())){
            outFile = valManualVoucherTemplete001Service.makeFile(
                    "/com/joyintech/tams/valuation/accounadjustmanage/manualvouchertemplete/conf/export_manualvouchertemplete_no.xml",
                    dtoList);
        }else{
            outFile = valManualVoucherTemplete001Service.makeFile(
                    "/com/joyintech/tams/valuation/accounadjustmanage/manualvouchertemplete/conf/export_manualvouchertemplete.xml",
                    dtoList);
        }

        return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                        + DateUtil.format(new Date()) + SystemGlobalConstant.CLOSE_PARENTHESIS
                        + SystemGlobalConstant.POINT + FileNameUtil.getFileExtName(outFile),
                outFile, true);
    }

}
