/*
 * 文件名：PaActiveRemind002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2018年8月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.remind.controller;

import java.util.*;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.select.SelectDto;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeRds;
import com.joyintech.tams.invest.remind.dto.PaActiveRemind001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.tams.invest.remind.service.PaActiveRemind002Service;

/**
 * 主动提醒详情
 * 
 * @author xiexinting
 * @version 1.0 2018年8月22日
 * @see PaActiveRemind002Controller
 * @since 1.0
 */
@Controller
@PageId("PaActiveRemind002")
@RequestMapping("/invest/remind/PaActiveRemind002")
public class PaActiveRemind002Controller extends BaseController {

    /**
     * 获取002service对象
     */
    @Autowired
    private PaActiveRemind002Service paActiveRemind002Service;

    /**
     * 主动提醒详情页面初始化
     */
    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, BussinessDictCode.提醒类型_大分类,BussinessDictCodeRds.单位);
//        this.setSelectOptionToModel(model, BussinessDictCode.提醒类型_小分类);
        setSelectOptionToModel1(model, BussinessDictCode.提醒类型_小分类);
        model.addAttribute("mainCategory", getParam("mainCategory"));
        model.addAttribute("smallCategory", getParam("smallCategory"));
        model.addAttribute("quaryDate", DateUtil.parse(getParam("quaryDate")));
        model.addAttribute("normal", BussinessDictCodeListRds.监督状态_正常.getCodeName());
        model.addAttribute("warning", BussinessDictCodeListRds.监督状态_触发预警.getCodeName());
        model.addAttribute("approve",BussinessDictCodeListRds.监督状态_触发审批.getCodeName());
        model.addAttribute("violation",BussinessDictCodeListRds.监督状态_触发超限.getCodeName());
        model.addAttribute("error",BussinessDictCodeListRds.监督状态_异常.getCodeName());
        model.addAttribute("NONE", BussinessDictCodeListRds.单位_无.getValue()); 
        return super.init(model);
    }

    /**
     * 
     * 查询主动提醒详情信息
     * <功能详细描述>
     * @return 返回查询出的分页数据
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selramind")
    public List<PaActiveRemind001Dto> selramind()
        throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        param.put("quaryDate", DateUtil.parse((String)param.get("quaryDate")));
        List<PaActiveRemind001Dto> page = paActiveRemind002Service.selRamind(param);
        return page;
    }

    /**
     *
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public void setSelectOptionToModel1(Model model, IDictCode... dictCodes){
        for (IDictCode dictCode : dictCodes) {
            SelectDto selectDto =getSelectDataFromDataDic(dictCode, null);
            if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
                List<SelectOptionDto> options = selectDto.getOptions();
                if (dictCode.equals(BussinessDictCode.提醒类型_小分类)) {
                    for (int index = options.size() - 1; index >= 0; index-- ) {
                        SelectOptionDto option = options.get(index);
                        if (StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_债券存续期回款确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_债券提前3个月.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_债券提前1个月.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_债券提前5天.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_基金类货币型申购确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_基金类货币型赎回确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_基金类净值型申购确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_基金类净值型赎回确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_计划类净值型申购确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_计划类净值型赎回确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_项目类资产存续期回款确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_计划类利率型存续期回款确认.getValue())) {
                            options.remove(option);
                        }
                    }
                }
                model.addAttribute(dictCode.getValue(), JsonUtils.objectArray2Json(options.toArray()));
                model.addAttribute(dictCode.getValue() + "_default", selectDto.getInitValue());
            }
        }
    }

    /**
     *
     * 关闭提醒
     * <功能详细描述>
     * @return
     * @return 返回查询出的分页数据
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("closeRemind")
    public int closeRemind()
            throws Exception {
        Map<String, Object> param = RequestUtil.getParameters(getRequest());
        return paActiveRemind002Service.closeRemind(param);
    }

    /**
     *
     * 根据大分类查询相应小分类
     * <功能详细描述>
     * @return
     * @return 返回查询出的数据
     * @throws Exception 抛出异常
     * @see
     * @since 1.0
     */
   /* @ResponseBody
    @RequestMapping("selectSmallCategory")
    public List selectSmallCategory(@RequestBody Map<String, Object> param) throws Exception {
        List list = setSelectOptionToModel2((String) param.get("mainCategory"),BussinessDictCode.提醒类型_小分类);
        return list;
    }*/


    /**
     *
     * <功能详细描述>
     * @param
     * @return
     * @see
     * @since 1.0
     *//*
    public List setSelectOptionToModel2(String mainCategory,IDictCode... dictCodes){
        List<Object> list = new ArrayList<Object>();
        for (IDictCode dictCode : dictCodes) {
            SelectDto selectDto =getSelectDataFromDataDic(dictCode, null);
            if (selectDto != null && !StringUtil.isEmptyList(selectDto.getOptions())) {
                List<SelectOptionDto> options = selectDto.getOptions();
                if (dictCode.equals(BussinessDictCode.提醒类型_小分类)) {
                    for (int index = options.size() - 1; index >= 0; index-- ) {
                        SelectOptionDto option = options.get(index);
                        if (StringUtil.equals(option.getId(), BussinessDictCodeList.提醒类型_小分类_债券存续期回款确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_回购回款确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_项目类资产存续期回款确认.getValue())
                                || StringUtil.equals(option.getId(),BussinessDictCodeList.提醒类型_小分类_计划类利率型存续期回款确认.getValue())) {
                            options.remove(option);
                        }
                        if(mainCategory!=null){
                            if(mainCategory.equals("R1")){
                                if(!option.getId().contains("R1")){
                                    options.remove(option);
                                }
                            }else if(mainCategory.equals("R2")){
                                if(!option.getId().contains("R2")){
                                    options.remove(option);
                                }
                            }else if(mainCategory.equals("R3")){
                                if(!option.getId().contains("R3")){
                                    options.remove(option);
                                }
                            }else if(mainCategory.equals("R4")){
                                if(!option.getId().contains("R4")){
                                    options.remove(option);
                                }
                            }else if(mainCategory.equals("R5")){
                                if(!option.getId().contains("R5")){
                                    options.remove(option);
                                }
                            }
                        }
                    }
                }

                list.addAll(Arrays.asList(options.toArray()));
            }
        }
        return list;
    }*/
}
