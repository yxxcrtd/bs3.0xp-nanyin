package com.joyintech.tams.invest.product.riskLevelRating.controller;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.ValidateGroupNew;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.component.business.core.service.MstWorkflowCommonService;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.product.riskLevelRating.dto.RiskLevelRating001Dto;
import com.joyintech.tams.invest.product.riskLevelRating.dto.RiskLevelRating002Dto;
import com.joyintech.tams.invest.product.riskLevelRating.service.RiskLevelRating002Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @program: RiskLevelRating002Controller
 * @description: 产品风险等级评定信息新建
 * @author: jyl
 * @create: 2020-01-02
 **/
@Controller
@PageId("RiskLevelRating002")
@RequestMapping("/invest/product/riskLevelRating/RiskLevelRating002")
public class RiskLevelRating002Controller extends BaseController {
    @Autowired
    RiskLevelRating002Service riskLevelRating002Service;
    @Autowired
    MstWorkflowCommonService mstWorkflowCommonService;

    public String init(Model model) {
        setSelectOptionToModel(model, DictCode.资产风险等级2, DictCode.资产二级分类, DictCode.资产三级分类
                , DictCode.资产四级分类, DictCode.资产五级分类,DictCode.平安资产类型);
        String seqNo = this.getParam("seqNo");
        String workflowId = this.getParam("workflowId");
        RiskLevelRating001Dto entity = new RiskLevelRating001Dto();
        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isEmpty(workflowId)) {
            model.addAttribute("edate", DateUtil.getSysDate());
            String seqNo1 = IDGenerator.generate();
            model.addAttribute("seqNo", seqNo1);//生成流水号
        }
        if (WorkFlowUtil.isNotFromToDoList() && !StringUtil.isNotEmpty(seqNo)) {

            model.addAttribute("entity", entity);
        } else if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(seqNo)) {
            entity.setSeqNo(seqNo);
            entity.setFinProductCode(this.getParam("finProductCode"));
            entity.setRiskLevel(this.getParam("riskLevel"));
            entity.setFinprodAbbr(this.getParam("finprodAbbr"));
            entity.setAjustRiskLevel(this.getParam("ajustRiskLevel"));
            entity.setGenerateTime(DateUtil.parse(this.getParam("generateTime")));
            model.addAttribute("entity", entity);

        } else if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            entity = riskLevelRating002Service.getBussinessEntityByWorkflowId(workflowId);
            model.addAttribute("entity", entity);
        }
        return super.init(model);
    }

    /**
     * 资产评定列表查询
     *
     * @param pageParam
     * @return RiskLevelRating002Dto
     */
    @RequestMapping("/findList")
    @ResponseBody
    public RiskLevelRating002Dto findList(@RequestBody Map<String, Object> pageParam) {
        if(!StringUtil.isNotEmpty(pageParam.get("generateTime"))) {
            pageParam.put("generateTime", DateUtil.getSysDate());
        }
        return riskLevelRating002Service.findList(pageParam);
    }

    /**
     * 校验
     *
     * @param dto
     * @param result
     * @return AjaxResult
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
            ValidateGroupEdit.class, ValidateGroupDelete.class, ValidateGroupNew.class}) RiskLevelRating001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());

    }

    /**
     * 校验单个产品在一个评定日中只能有一条数据
     *
     * @param dto
     * @return List
     */
    @ResponseBody
    @RequestMapping("/onlyOne")
    public List onlyOne(@RequestBody RiskLevelRating001Dto dto) {
        Map<String, Object> pageParam = new HashMap<>();
        pageParam.put("finProductCode", dto.getFinProductCode());
        pageParam.put("generateTime",DateUtil.platDateFromCache());
        List li = new ArrayList();
        li.add(riskLevelRating002Service.onlyOne(pageParam));
        return li;
    }

    /**
     * 新建、编辑提交
     *
     * @param dto
     * @param br
     * @return AjaxResult
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated RiskLevelRating001Dto dto, BindingResult br) throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        dto.setCreateDept(LoginUserUtil.getDeptCode());
        dto.setCreateUser(LoginUserUtil.getUserName());
        dto.setUpdateUser(LoginUserUtil.getUserName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1= new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
        String d1=sdf1.format(new Date());
        String d2=sdf2.format(DateUtil.platDateFromCache());
        String d3=d2+" "+d1;
        Date d=sdf.parse(d3);
        dto.setGenerateTime(d);
        ProcessParameter<RiskLevelRating001Dto> processParameter = new ProcessParameter<RiskLevelRating001Dto>();
        WorkFlowParameter<RiskLevelRating001Dto> params = new WorkFlowParameter<RiskLevelRating001Dto>();
        dto.setCreateUser(LoginUserUtil.getUserName());
        dto.setUpdateUser(LoginUserUtil.getUserName());
//        dto.setCreateTime(DateUtil.now());
//        dto.setUpdateTime(DateUtil.now());
        //判断是否在工作流中
        String SeqNo = dto.getSeqNo();
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId(this.getPageId());
        parameter.setBusinessDataPk1(SeqNo);
        if (mstWorkflowCommonService.isInWflProcess(parameter)) {
            AjaxResult result = new AjaxResult();
            result.setMessage("不能操作流程中的数据！");
            result.setMessageType(MessageType.错误消息);
            return result;
        }
        params.setPageId(this.getPageId());
        params.setElementId(this.getParam("elementId"));

        params.setBusinessCode(dto.getFinProductCode());
        params.setBusinessType(DictCodeListExt3.共通业务类型_产品风险等级评定.getValue());
        params.setComment(entity -> this.getMessage("RiskLevelRating001.seqNo") + "("
                + entity.getSeqNo() + ")" + this.getMessage("RiskLevelRating002.createTime") + "("
                + DateUtil.format(DateUtil.now()) + ")");
        processParameter.setWorkFlowParameter(params);
        String msg = riskLevelRating002Service.start(processParameter, dto);
        //msg= SpringContextUtil.getTextValue("COMMON_I_0011");
        return new AjaxResult(msg);
    }

    /**
     * 删除
     *
     * @param dto
     * @return AjaxResult
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("delete")
    public AjaxResult delete(@RequestBody RiskLevelRating001Dto dto)
            throws Exception {
        ProcessParameter<RiskLevelRating001Dto> processParameter = new ProcessParameter<RiskLevelRating001Dto>();
        WorkFlowParameter<RiskLevelRating001Dto> params = new WorkFlowParameter<RiskLevelRating001Dto>();
        //判断是否在工作流中
        String prdDesignId = dto.getSeqNo();
        WflValidInputParameter parameter = new WflValidInputParameter();
        parameter.setPageId(this.getPageId());//"TotalMaintain001"
        parameter.setBusinessDataPk1(prdDesignId);
        if (mstWorkflowCommonService.isInWflProcess(parameter)) {
            AjaxResult result = new AjaxResult();
            result.setMessage("不能删除流程中的数据！");
            result.setMessageType(MessageType.错误消息);
            return result;
        }
        params.setPageId(this.getPageId());//"TotalMaintain001"
        dto.setCreateUser(LoginUserUtil.getUserName());
        params.setElementId(this.getParam("elementId"));
        params.setBusinessCode(dto.getFinProductCode());

        params.setBusinessType(DictCodeListExt3.共通业务类型_产品风险等级评定.getValue());
        params.setComment(entity -> "产品风险等级流水号" + "(" + entity.getSeqNo() + ")"
        );
        processParameter.setWorkFlowParameter(params);

        String msg = riskLevelRating002Service.start(processParameter, dto);
        return new AjaxResult(msg);
    }

    /**
     * 获取流水号
     *
     * @param object
     * @return List
     */
    @ResponseBody
    @RequestMapping("/selectNo")
    public List selectNo(@RequestBody Map<String, Object> object) {
        Map<String, Object> map = RequestUtil.getPageParam();
        map.put("generateTime",DateUtil.getSysDate());
        List list = new ArrayList();
        list.add(riskLevelRating002Service.selectNo(object));
        return list;
    }
    /**
     * 获取调整后资产风险等级列表
     *
     * @param seqNo
     * @return List
     */
    @ResponseBody
    @RequestMapping("/selectAjustRisk")
    public List<RiskLevelRating002Dto> selectAjustRisk(@RequestBody String seqNo) {
        List<RiskLevelRating002Dto> list = new ArrayList();
        list=riskLevelRating002Service.selectAjustRisk(seqNo);
        return list;
    }
    /**
     * 点击产品风险等级评定，产品风险等级=∑（调整后）资产风险等级*每个资产的集中度。
     *
     * @param dto
     * @return String
     */
    @ResponseBody
    @RequestMapping("/prodRisk")
    public List prodRisk(@RequestBody RiskLevelRating002Dto dto){
        List list=new ArrayList();
        list.add(riskLevelRating002Service.prodRisk(dto));
        return list;
    }
}
