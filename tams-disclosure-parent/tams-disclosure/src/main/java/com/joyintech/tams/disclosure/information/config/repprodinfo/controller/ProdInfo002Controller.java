/*
 * 文件名：DisProdPlanManage002Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年11月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.repprodinfo.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdBasicChangeDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdInfoDto;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdBasicChangeService;
import com.joyintech.tams.disclosure.information.config.repprodinfo.service.ProdInfoService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

/**
 * 报表模板管理新建
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 *
 * @author longhao
 * @version 1.0 2018年11月9日
 * @see ProdInfo002Controller
 * @since 1.0
 */
@Controller
@PageId("ProdInfo002")
@RequestMapping("/disclosure/information/config/repprodinfo/ProdInfo002")
public class ProdInfo002Controller extends BaseController {

    @Autowired
    ProdInfoService prodInfoService;

    @Autowired
    ProdBasicChangeService prodBasicChangeService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdInfo002Controller.class);

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCodeExt1.信披_产品运作方式);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_产品状态);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_是否是分级产品);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_募集方式);
        this.setSelectOptionToModel(model, DictCodeExt1.发行方式_信批);
        this.setSelectOptionToModel(model, DictCodeExt1.是否是指数型产品);
        this.setSelectOptionToModel(model, DictCodeExt1.是否是客户周期型产品);
        this.setSelectOptionToModel(model, DictCodeExt1.开放频率);
        this.setSelectOptionToModel(model, DictCodeExt1.信批产品类别);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_产品类型);
        this.setSelectOptionToModel(model, DictCodeExt1.信披_投资性质);
        this.setSelectOptionToModel(model, DictCode.是或否);
        this.setSelectOptionToModel(model, DictCodeExt1.行情代码);


        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        String id = this.getParam("prodId");
        String repProdType = this.getParam("repProdType");


        // 不来自于代办
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(id)) {
            // 根据主键获取业务实体
            ProdInfoDto dto = new ProdInfoDto();
            if (StringUtils.equals(DictCodeListExt5.信批产品类别_普通产品.getValue(), repProdType)) {
                dto = prodInfoService.selectByCommonProdId(id);
            } else if (StringUtils.equals(DictCodeListExt5.信批产品类别_子产品.getValue(), repProdType)) {
                dto = prodInfoService.selectBySonProdId(id);
            } else {
                dto = prodInfoService.selectByProdId(id);
            }
            //业绩基准变更
            dto.setBasicChangeList(prodBasicChangeService.selectListByProdId(dto.getpProdId()));
            model.addAttribute("entity", dto);

        }
        // 来自代办 现在是不走流程
        else if (StringUtil.isNotEmpty(workflowId)) {
            // 根据工作流与留痕处理提供的共通方法获取业务实体
            ProdInfoDto entity = prodInfoService.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
            model.addAttribute("workflowId", workflowId);
        }
        return super.init(model);
    }


    /**
     * check
     *
     * @param prodId 查询产品明细信息
     * @return 结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getPrdInfo")
    public ProdInfoDto getPrdInfo(String prodId) {

        return prodInfoService.getPrdInfo(prodId);
    }

    /**
     * check
     *
     * @param dto 待校验数据
     * @return 结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/checkSave")
    public AjaxResult checkSave(@RequestBody ProdInfoDto dto) {
        AjaxResult result = prodInfoService.check(dto);
        if (result.getErrorList().isEmpty()) {
            result.setData(this.getMessage("DIS_COMMON_C_0001", this.getMessage("SysCommon.save")));
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Valid ProdInfoDto dto, BindingResult bindingResult) {
        String operateType = dto.getOperateType();
        String pageModel = dto.getPageModel();
        if (StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)) {//新增
            ProdInfoDto existDto = prodInfoService.selectByProdStatus(dto.getProdId());
            if (existDto != null) {
                return new AjaxResult("子产品代码已存在", MessageType.错误消息);
            }
            if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), dto.getIsGrade())
                    && StringUtils.equals(dto.getProdId(), dto.getpProdId())) {
                return new AjaxResult("分级产品，子产品代码和产品主代码不能一样", MessageType.错误消息);
            }
            /*if (StringUtils.equals(DictCodeListExt1.是或否_否.getValue(), dto.getIsGrade())
                    && !StringUtils.equals(dto.getProdId(), dto.getpProdId())) {
                return new AjaxResult("非分级产品，子产品代码和产品主代码需要一致", MessageType.错误消息);
            }*/

        } else if (StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {//修改

        }
        if (StringUtils.equals(DictCodeListExt5.信披募集方式_公募.getValue(), dto.getRaiseType())
                && dto.getProdRe().length() != 14) {
            return new AjaxResult("募集方式为公募,产品登记编码为14位", MessageType.错误消息);
        }
        if (StringUtils.equals(DictCodeListExt5.信披募集方式_私募.getValue(), dto.getRaiseType())
                && dto.getProdRe().length() != 15) {
            return new AjaxResult("募集方式为私募,产品登记编码为15位", MessageType.错误消息);
        }
        if (DateUtil.compareDate(dto.getRaiseEnddate(),dto.getRaiseStrdate()) < 0
                || DateUtil.compareDate(dto.getRaiseEnddate(),dto.getSetDate()) > 0
                || DateUtil.compareDate(dto.getRaiseEnddate(),dto.getpSetDate()) > 0) {
            return new AjaxResult("募集结束日期必须大于等于募集起始日期，且必须小于子产品成立日期和产品成立日！", MessageType.错误消息);
        }

        if (StringUtils.equals(DictCodeListExt5.发行方式_开放式.getValue(), dto.getIssuingWay())
                && StringUtils.isBlank(dto.getOpenFreq())) {
            return new AjaxResult("发行方式为开放式，开放频率必填", MessageType.错误消息);
        }

        if (StringUtils.equals(DictCodeListExt5.信披产品类型_净值型产品.getValue(), dto.getProdType())
                && StringUtils.isNotEmpty( dto.getOpenFreq())
                && StringUtils.equals(DictCodeListExt5.开放频率_日开.getValue(), dto.getOpenFreq())
                && dto.getHoldday() == null) {
            return new AjaxResult("开放频率为日开且产品类型为净值型,固定持有天数必填", MessageType.错误消息);
        }

        /* NYLCXP-80 除“子产品代码”“子产品名称”“子产品成立日期”外，其余字段的值不和已有数据相同 add by kongzhifang 2022-08-14 start */
        if(StringUtil.isNotEmpty(dto.getpProdId())){
            ProdInfoDto pProdInfo = prodInfoService.selectByProdStatus(dto.getpProdId());
            if(pProdInfo!=null){
                pProdInfo.setpProdId(pProdInfo.getProdId());
                pProdInfo.setpProdName(pProdInfo.getProdName());
                pProdInfo.setpSetDate(pProdInfo.getSetDate());
                if(!compareField(prodInfoModelToMap(dto),prodInfoModelToMap(pProdInfo))) {
                    return new AjaxResult("除子产品代码、子产品名称、子产品成立日期外，其余字段必须和已有数据相同", MessageType.错误消息);
                }
            }
        }
        /* NYLCXP-80 除“子产品代码”“子产品名称”“子产品成立日期”外，其余字段的值不和已有数据相同 add by kongzhifang 2022-08-14 end */

        if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), dto.getIsIndex())) {
            if (CollectionUtils.isEmpty(dto.getBasicChangeList())) {
                return new AjaxResult("行情代码或系数不能为空", MessageType.错误消息);
            }
            Set<String> setChangeDto = new HashSet<>();
            for (ProdBasicChangeDto i : dto.getBasicChangeList()) {
                if (StringUtils.isBlank(i.getDataType()) || StringUtils.isBlank(i.getProportion())) {
                    return new AjaxResult("行情代码或系数不能为空", MessageType.错误消息);
                } else {
                    setChangeDto.add(i.getDataType());
                }
            }
            if (setChangeDto.size() != dto.getBasicChangeList().size()) {
                return new AjaxResult("行情代码重复", MessageType.错误消息);
            }
        }
        return commonCheck(bindingResult, operateType);
    }


    /**
     * 数据保存操作
     *
     * @param dto 请求实体类
     */
    @ResponseBody
    @RequestMapping(OPERATE_TYPE_SAVE)
    public AjaxResult save(@RequestBody @Validated({
            ValidateGroupEdit.class}) ProdInfoDto dto, BindingResult br)
            throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        // 新建留痕表参数和工作流参数
        ProcessParameter<ProdInfoDto> processParameter = new ProcessParameter<ProdInfoDto>();
        WorkFlowParameter<ProdInfoDto> params = new WorkFlowParameter<ProdInfoDto>();
        params.setPageId("ProdInfo002");
        params.setElementId(dto.getElementId());
        params.setBusinessCode(dto.getProdId());
        params.setBusinessType(DictCodeListExt4.共通业务类型_产品基本信息.getValue());
        params.setComment(entity -> "产品基本信息-产品代码" + "(" + entity.getProdId() + ")");
        // 塞入工作流参数
        processParameter.setWorkFlowParameter(params);

        Date sysDate = com.joyintech.framework.util.DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());

        // 流程开始
        String msg = prodInfoService.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 合并父类和子类字段
     * @param a
     * @param b
     * @return
     */
    private Field[] getAllField(Field[] a,Field[] b){
        List list = new ArrayList(Arrays.asList(a));
        list.addAll(Arrays.asList(b));
        Field[] str = new Field[list.size()];
        list.toArray(str);
        return str;
    }

    /**
     * 产品基本信息model(前台页面字段)转换为map
     * @param dto
     * @return
     */
    private Map<String,Object> prodInfoModelToMap(ProdInfoDto dto){
        Field[] fields = getAllField(dto.getClass().getDeclaredFields(),dto.getClass().getSuperclass().getDeclaredFields());
        Map<String,Object> dtoMap = new HashMap<>();
        for (Field field : fields) {
            Annotation annotation = field.getAnnotation(NotEmpty.class);//获取@NotEmpty注解的字段，即必填项
            String name = field.getName();
            if(StringUtil.equals(name,"prodId")||StringUtil.equals(name,"prodName")
                    ||StringUtil.equals(name,"setDate")){
                continue;
            }
            if (annotation != null||StringUtil.equals(name,"openFreq")||StringUtil.equals(name,"holdday")) {//开放频率和固定持有天数为非必填项也要判断
                try {
                    String replace = name.substring(0, 1).toUpperCase() + name.substring(1);
                    if(StringUtil.equals(name,"pProdId")||StringUtil.equals(name,"pSetDate")
                            ||StringUtil.equals(name,"pProdName")){//这三个字段的get方法不遵守驼峰规则
                        replace = name;
                    }
                    Method method = dto.getClass().getMethod("get" + replace);
                    dtoMap.put(name,method.invoke(dto));
                }catch (Exception e){
                    e.getStackTrace();
                }
            }
        }
        return dtoMap;
    }

    /**
     * 比较子产品和母产品字段值是否一致
     * @param map1
     * @param map2
     * @return
     */
    private boolean compareField(Map<String, Object> map1, Map<String, Object> map2){
        for (String key : map1.keySet()) {
            if(StringUtil.equals(key,"pSetDate")||StringUtil.equals(key,"mdate")
                    ||StringUtil.equals(key,"raiseStrdate")||StringUtil.equals(key,"raiseEnddate")){/* Date类型特殊处理 */
                if(DateUtil.compareDate((Date)map1.get(key),(Date)map2.get(key))!=0){
                    return false;
                }
            }else if(StringUtil.equals(key,"raiseAmt")){/* BigDecimal类型特殊处理 */
                if(((BigDecimal)map1.get(key)).compareTo((BigDecimal)map2.get(key))!=0){
                    return false;
                }
            }else if(!((map1.get(key)==null&&map2.get(key)==null)
                    ||StringUtil.equals(map1.get(key),map2.get(key)))){
                return false;
            }
        }
        return true;
    }

}
