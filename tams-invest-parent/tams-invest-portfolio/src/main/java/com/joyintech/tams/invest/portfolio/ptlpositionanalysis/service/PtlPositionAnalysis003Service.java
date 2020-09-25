
package com.joyintech.tams.invest.portfolio.ptlpositionanalysis.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.actorrole.dto.SystemActorRoleDto002;
import com.joyintech.tams.common.base.dao.FilterDetailMapper;
import com.joyintech.tams.common.base.dao.TargetTypeInfoMapper;
import com.joyintech.tams.common.base.dao.TilterInfoMapper;
import com.joyintech.tams.common.base.entity.FilterDetail;
import com.joyintech.tams.common.base.entity.TargetDetail;
import com.joyintech.tams.common.base.entity.TargetTypeInfo;
import com.joyintech.tams.common.base.entity.TilterInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlpositionanalysis.dto.PtlPositionAnalysis003Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * 组合持仓分析报表-》指标设置
 * @author nixiukuan
 * @version 1.0 2019年12月05日
 * @see PtlPositionAnalysis003Service
 * @since 1.0
 */
@Service
public class PtlPositionAnalysis003Service extends CommonExportService<PtlPositionAnalysis003Dto> {


    @Autowired
    private TargetTypeInfoMapper targetTypeInfoMapper;//指标明细
    @Autowired
    private TilterInfoMapper tilterInfoMapper;//过滤器
    @Autowired
    private FilterDetailMapper filterDetailMapper; //过滤器明细
    /**
     * 获取指标设置树结构
     * @return 查询结果集
     * @throws Exception 异常
     */
    public List<TreeNode<TargetDetail>> getAllTreeNode(Map<String, Object> map) {
        List<TreeNode<TargetDetail>> treeNodeList=new ArrayList<>();
        String userId = com.joyintech.tams.systemtools.util.CommonUtil.getUserName();
        List<TargetDetail> targetDetailList=new ArrayList<>();
        targetDetailList= restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/PtlPositionAnalysis/getAllTreeNode",
                userId, TargetDetail.class);

        TreeNode<TargetDetail> treeNode01=new TreeNode<TargetDetail>();
        TargetDetail targetDetail01=new TargetDetail();
        targetDetail01.setTaregetCode(DictCodeListExt3.指标类型代码_综合指标.getValue());

        treeNode01.setName(DictCodeListExt3.指标类型代码_综合指标.getCodeName());
        treeNode01.setId(DictCodeListExt3.指标类型代码_综合指标.getValue());
        treeNode01.setParent(true);
        treeNode01.setObject(targetDetail01);
        treeNode01.setOpen(true);
        treeNodeList.add(treeNode01);

        TreeNode<TargetDetail> treeNode02=new TreeNode<TargetDetail>();
        TargetDetail targetDetail02=new TargetDetail();
        targetDetail02.setTaregetCode(DictCodeListExt3.指标类型代码_债券指标.getValue());
        treeNode02.setName(DictCodeListExt3.指标类型代码_债券指标.getCodeName());
        treeNode02.setId(DictCodeListExt3.指标类型代码_债券指标.getValue());
        treeNode02.setParent(true);
        treeNode02.setObject(targetDetail02);
        treeNode02.setOpen(true);
        treeNodeList.add(treeNode02);
        TreeNode<TargetDetail> treeNode03=new TreeNode<TargetDetail>();
        TargetDetail targetDetail03=new TargetDetail();
        targetDetail03.setTaregetCode(DictCodeListExt3.指标类型代码_股票指标.getValue());
        treeNode03.setName(DictCodeListExt3.指标类型代码_股票指标.getCodeName());
        treeNode03.setId(DictCodeListExt3.指标类型代码_股票指标.getValue());
        treeNode03.setParent(true);
        treeNode03.setObject(targetDetail03);
        treeNode03.setOpen(true);
        treeNodeList.add(treeNode03);
        targetDetailList.forEach(item->
        {
            TreeNode<TargetDetail> treeNode=new TreeNode<TargetDetail>();
            treeNode.setName(item.getTaregetName());
            treeNode.setId(item.getTaregetCode());
            treeNode.setParent(false);
            treeNode.setpId(item.getTaregetTypeCode());
            treeNode.setObject(item);
            treeNode.setOpen(false);
            treeNodeList.add(treeNode);
        });
        return  treeNodeList;

    }

    /**
     * 保存设置的列
     * @return 查询结果集
     * @throws Exception 异常
     */
    @Transactional(rollbackFor=Exception.class)
    public boolean savaTableCols(Map<String, Object> map) {
        List<String> list= StringUtil.splitToList(GlobalConstant.COMMA,map.get("msg").toString()) ;
        String userId = com.joyintech.tams.systemtools.util.CommonUtil.getUserName();
        String uuid=UUID.randomUUID().toString().replaceAll("-","");
        String  filterCode="";
        //获取当前用户对应的过滤器代码，并删除对应的指标明细后新增指标明细
        TilterInfo tilterInfo = tilterInfoMapper.selectByUserId(userId);
        if(tilterInfo!=null)
        {
            filterCode=tilterInfo.getFilterCode();
            //删除之前记录
            FilterDetail filterDetail=new FilterDetail();
            filterDetail.setFilterCode(tilterInfo.getFilterCode());
            filterDetailMapper.deleteByFilterCode(filterDetail);
        }
        else
        {
            filterCode=uuid;
            tilterInfo=new TilterInfo();
            tilterInfo.setFilterCode(uuid);
            tilterInfo.setFilterName("默认过滤器");
            tilterInfo.setFilterRight("01");
            tilterInfo.setUserId(userId);
            tilterInfo.setIsDefault(DictCodeList.是否_是.getValue());
            tilterInfo.setCreateUser(LoginUserUtil.getUserName());
            tilterInfo.setUpdateUser(LoginUserUtil.getUserName());
            //新建一条过滤器信息
            tilterInfoMapper.insert(tilterInfo);
        }

        String finalFilterCode = filterCode;
        list.forEach(item->
        {
                FilterDetail filterDetail=new FilterDetail();
                filterDetail.setFilterCode( finalFilterCode);
                filterDetail.setTaregetCode(item);
                filterDetail.setCreateUser(LoginUserUtil.getUserName());
                filterDetail.setUpdateUser(LoginUserUtil.getUserName());
                filterDetailMapper.insert(filterDetail);

        });

        return  true;
    }
}
