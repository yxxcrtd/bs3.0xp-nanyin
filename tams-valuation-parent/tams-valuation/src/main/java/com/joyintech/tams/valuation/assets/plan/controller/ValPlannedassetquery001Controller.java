/*
 * 文件名：ValPlannedassetquery001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：zhangzhao
 * 修改时间：2018年5月7日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.valuation.assets.plan.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.valuation.assets.plan.dto.ValPlannedassetquery001Dto;
import com.joyintech.tams.valuation.assets.plan.service.ValPlannedassetquery001Service;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 模板示例<br>
 * 计划类资产查询
 * 
 * @author zhangzhao
 * @version 2.0 2018年7月19日
 * @see ValPlannedassetquery001Controller
 * @since 2.0
 */
@Controller
@PageId("ValPlannedassetquery001")
@RequestMapping("/valuation/assets/plan/ValPlannedassetquery001")
public class ValPlannedassetquery001Controller extends BaseController {
    
    /**
     * ValPlannedassetquery001Service
     */
    @Autowired
    private ValPlannedassetquery001Service valPlannedassetquery001Service;

    /**
     * 重写init方法，加载页面 {@inheritDoc}
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.收益类型);
        this.setSelectOptionToModel(model, DictCode.计划类资产类型);
        this.setSelectOptionToModel(model, DictCode.频率);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("finprodType", DictCodeListExt1.金融产品类型_计划类资产.getValue());
        return super.init(model);
    }

    /**
     * 债券获取
     *
     * @return 返回的数据
     * @exception Exception
     *                异常
     * @since 1.0
     */
    @RequestMapping("/getBondId")
    @ResponseBody
    public Page<SelectOptionDto> getBondId(HttpServletRequest request) {
        Map<String, Object> param =RequestUtil.getPageParam();
        return valPlannedassetquery001Service.getBondId(param);
    }


    /**
     * 查询前校验
     * 
     * @param dto 参数
     * @param result 校验结果
     * @return AjaxResult
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("checkQuery")
//    public AjaxResult checkQuery(@Valid @RequestBody ValBondassetquery001SearchDto dto,
//            BindingResult result) {
//        if (result != null && result.hasErrors()) {
//            return new AjaxResult(result);
//        }
//        return new AjaxResult();
//    }

    /**
     * 债券类资产数据取得
     *
     * @return 记账凭证数据
     * @throws Exception
     *             异常
     * @see
     */
    @RequestMapping("findPage")
    @ResponseBody
    public Page<ValPlannedassetquery001Dto> findPage() throws Exception {
        return valPlannedassetquery001Service.findPage(RequestUtil.getPageParam());
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
    public ResponseEntity<byte[]> export()throws Exception {
            String datas = this.getParam("datas");
            Map<String, Object> paraMap = RequestUtil.getPageParam();
            List<ValPlannedassetquery001Dto> listdto;
            if (datas == null) {
            listdto = (List<ValPlannedassetquery001Dto>)valPlannedassetquery001Service.findPageout(paraMap);
            }
            else {
                listdto = JsonUtils.json2List(datas, ValPlannedassetquery001Dto.class);
            }
            if(listdto!=null&&listdto.size()>0){
                for(ValPlannedassetquery001Dto dto:listdto){
                    //收益类型
                    String profitTypeCode = dto.getProfitTypeCode();
                    dto.setProfitTypeCode(SysDataUtil.getNameFromDataDict(DictCode.收益类型, profitTypeCode));
                }
            }
            File outFile = valPlannedassetquery001Service.makeFile(
                "/com/joyintech/tams/valuation/assets/plan/config/export_ValPlannedassetquery001.xml", listdto);
            return ExportUtil.export(
                this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                     + DateUtil.format(new Date())
                                     + SystemGlobalConstant.CLOSE_PARENTHESIS
                                     + SystemGlobalConstant.POINT
                                     + FileNameUtil.getFileExtName(outFile),
                outFile, true);
        }

}
