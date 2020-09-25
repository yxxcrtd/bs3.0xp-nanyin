/*
 * 文件名：PtlManagementInfo003Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月19日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.portfolio.ptlmanagementinfo.controller;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.tams.common.base.dao.PtlPortfolioAuthorityMapper;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.PtlPortfolioAuthority;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.NodeDto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfo003Dto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfoDto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo001Service;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service.PtlManagementInfo003Service;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import com.joyintech.workflow.engine.dto.WorkFlowParameter;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 用户分配
 * @author fanshuanghu
 * @version 1.0 2018年11月19日
 * @see PtlManagementInfo003Controller
 * @since 1.0
 */
@Controller
@PageId("PtlManagementInfo003")
@RequestMapping("/invest/portfolio/ptlmanagementinfo/PtlManagementInfo003")
public class PtlManagementInfo003Controller extends BaseController{
    
    @Autowired
    private PtlManagementInfo001Service ptlManagementInfo001Service;
    
    @Autowired
    private PtlManagementInfo003Service ptlManagementInfo003Service;
    /**
     * 通用Mapper共同代理方法
     */
    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;
    /**
     * 界面初始化
     * {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model){
        String workflowId = getParam(GlobalConstant.WORKFLOW_ID);
        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
            PtlManagementInfo003Dto entity = ptlManagementInfo003Service.getBussinessEntityByWorkflowId(
                workflowId);
            model.addAttribute("portData", entity.getSelectedList());
            model.addAttribute("entity", entity);
            // 判断逐渐是否为空
        }
        // 初始化
        return super.init(model);
    }
    /**
     * 获取用户
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getUserId")
    public List<SelectOptionDto> getUserId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        List<SelectOptionDto> list =  ptlManagementInfo001Service.getUserList();
        if(map.get("search") != null &&!map.get("search").toString().isEmpty()){
            for(int i = list.size()-1; i>=0; i--){
                //实现模糊查询
                if(!Pattern.matches(".*"+map.get("search").toString()+".*", list.get(i).getId())
                    && !Pattern.matches(".*"+map.get("search").toString()+".*", list.get(i).getText())){
                    list.remove(i);
                }
            }
        }else if(map.get("initValue") != null && StringUtil.isNotEmpty(map.get("initValue").toString())){
            for(int i = list.size()-1; i>=0; i--){
                //初始化查询
                if(StringUtil.equals(map.get("initValue").toString(), list.get(i).getId())){
                    SelectOptionDto sDto = new SelectOptionDto();
                    sDto.setId(list.get(i).getId());
                    sDto.setText(list.get(i).getText());
                    List<SelectOptionDto> sList = new ArrayList<SelectOptionDto>();
                    sList.add(sDto);
                    list = sList;
                    break;
                }
            }
        }
        return list;
    }
    /**
     * 获取组合列表
     * @return 组合列表
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPortPage")
    public List<PtlManagementInfoDto> getPortPage() throws Exception{
        return ptlManagementInfo001Service.getPortPage(
            RequestUtil.getParameters(getRequest()));
    }
     
    /**
     * 获取组合下拉框
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getPortfolioId")
    public List<SelectOptionDto> getPortfolioId(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return ptlManagementInfo001Service.getPortfolioId(map);
    }
    /**
     * 获取树节点
     * @return 获取用户树
     * @see
     * @since 1.0
     */
//    @ResponseBody
//    @RequestMapping("getUser")
//    public List<TreeNode<SelectOptionDto>> getUser(){
//        Map<String,Object> requestMap = RequestUtil.getPageParam();
//        String portfolioId = null;
//        if( requestMap.get("portfolioId") != null){
//            portfolioId = requestMap.get("portfolioId").toString();
//        }
//        String workflowId = RequestUtil.getParam(GlobalConstant.WORKFLOW_ID);
//        List<TreeNode<SelectOptionDto>> retList = new ArrayList<>();
//        List<SelectOptionDto> userList = ptlManagementInfo001Service.getUserList();
//        Map<String,List<SelectOptionDto>> map = userList.stream().collect(JoyinCollectors.groupingBy(x->x.getObject().toString()));
//        for (String deptName : map.keySet()) {
//            // 生成部门的节点
////            List<SelectOptionDto> userMap = map.get(deptName);
//            TreeNode<SelectOptionDto> data = new TreeNode<SelectOptionDto>();
//            data.setId(deptName);
//            data.setName(deptName);
////            data.setpId(deptName);
//            data.setOpen(true);
//            retList.add(data);
//            
//            for (SelectOptionDto userSelectDto : map.get(deptName)) {
//                    // 生成部门的子节点
//                    data = new TreeNode<SelectOptionDto>();
//                    data.setId(userSelectDto.getId());
//                    data.setName(userSelectDto.getText());
//                    data.setpId(deptName);
//                    data.setOpen(true);
//                    retList.add(data);
//            }
//        }
//        
//        
//        
//        if (!WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(workflowId)) {
//            PtlManagementInfo003Dto entity = ptlManagementInfo003Service.getBussinessEntityByWorkflowId(
//                workflowId);
//            if(entity.getUserList() != null){
//                for(String userName : entity.getUserList()){
//                    for (TreeNode<SelectOptionDto> treeNode : retList) {
//                        if (StringUtil.equals(treeNode.getId(), userName)) {
//                            treeNode.setChecked(true);
//                        }
//                    }
//                }
//            }
//        }
//        // 检索DB中存储的数据权限信息，已经选中的数据权限的check设成ON
//        if (WorkFlowUtil.isNotFromToDoList() && StringUtil.isNotEmpty(portfolioId)) {
//            List<PtlManagementInfoDto> ptlManagementInfoDtoList = ptlManagementInfo001Service.selectUserNameList(portfolioId);
//            for (PtlManagementInfoDto ptlManagementInfoDto: ptlManagementInfoDtoList) {
//                for (TreeNode<SelectOptionDto> treeNode : retList) {
//                    if (StringUtil.equals(treeNode.getId(), ptlManagementInfoDto.getPortfolioId())) {
//                        treeNode.setChecked(true);
//                    }
//                }
//            }
//        }
//        return retList;
//    }
    
    
    /**
     * 获取树节点
     * @return 获取用户树
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getUser")
    public List<TreeNode<NodeDto>> getUser(){
        Map<String,Object> map = RequestUtil.getPageParam();
        return ptlManagementInfo003Service.getTree(map);
    }
    /**
     * 插入数据
     * @param dto 组合权限实体类
     * @return 生成信息
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("insertUser")
    public AjaxResult insertUser(@RequestBody PtlManagementInfo003Dto dto){
        String msg = "";
        if(bussCommonMapperDelegateService != null){
            if(dto.getSelectedList().get(0).getUserId() == null || dto.getSelectedList().get(0).getUserId().isEmpty()){
                return new AjaxResult(getMessage("FW_E_0006",getMessage("PtlManagementInfo003.user")));
            }
            ptlManagementInfo003Service.insertUser(dto);
            msg = this.getMessage("BUSS_COM_I_0030","","");
        }
        return new AjaxResult(msg);
    }
    
    /**
     * 画面点击保存按钮时check
     * 
     * @param dto 实体类
     * @param result 
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlManagementInfo003Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }
    /**
     * 获取组合集合
     * @param map 请求参数
     * @return 组合集合
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("getSelectedport")
    public List<PtlPortfolio> getSelectedport(@RequestBody Map<String,Object> map){
        return ptlManagementInfo003Service.getSelectedport(map);
    }
    /**
     * 保存
     * @param dto dto
     * @param br br
     * @return 结果
     * @throws Exception 异常
     */
    @ResponseBody
    @RequestMapping("save")
    public AjaxResult save(@RequestBody @Validated({
        ValidateGroupEdit.class}) PtlManagementInfo003Dto dto, BindingResult br)
        throws Exception {
        if (br.hasErrors()) {
            return new AjaxResult(br);
        }
        ProcessParameter<PtlManagementInfo003Dto> processParameter = new ProcessParameter<PtlManagementInfo003Dto>();
        WorkFlowParameter<PtlManagementInfo003Dto> params = new WorkFlowParameter<PtlManagementInfo003Dto>();
        params.setPageId("PtlManagementInfo003");
        params.setElementId(dto.getElementId());
        params.setBusinessType(DictCodeList.共通业务类型_组合管理.getValue());
        String msg  = "";
        if(StringUtil.isNotEmpty(dto.getAllocationWay()) && StringUtil.equals(dto.getAllocationWay(), "01")){
            params.setBusinessCode(item -> item.getPortfolioId());
            // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-"  + "组合代码" + "("  + "组合代码"+")"
            params.setComment(
                entity -> this.getMessage("PtlManagementInfo001.portfolioName") + "("
                    + entity.getPortfolioName() + ")" + "-"
                    + this.getMessage("PtlManagementInfo001.portfolioId") + "("
                    + entity.getPortfolioId() + ")" );
            processParameter.setWorkFlowParameter(params);
            msg = ptlManagementInfo003Service.start(processParameter, dto);
        }else if(StringUtil.isNotEmpty(dto.getAllocationWay()) && StringUtil.equals(dto.getAllocationWay(), "02")){
            params.setBusinessCode(item -> item.getUserId());
         // 设置流程业务说明, 组合名称 + "(" + 组合名称 + ")" + "-"  + "组合代码" + "("  + "组合代码"+")"
            params.setComment(
                entity -> this.getMessage("PtlManagementInfo003.user") + "("
                    + entity.getUserId() + ")");
            processParameter.setWorkFlowParameter(params);
            msg = ptlManagementInfo003Service.start(processParameter, dto);
        }
        return new AjaxResult(msg);
    }
}
