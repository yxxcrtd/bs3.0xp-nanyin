/*
 * 文件名：PtlManagementInfo003Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：fanshuanghu
 * 修改时间：2018年11月20日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.portfolio.ptlmanagementinfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.tams.common.base.dao.PtlPortfolioAuthorityMapper;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.common.base.entity.PtlPortfolioAuthority;
import com.joyintech.tams.common.base.entity.User;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.component.business.common.service.BussCommonMapperDelegateService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.NodeDto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfo003Dto;
import com.joyintech.tams.invest.portfolio.ptlmanagementinfo.dto.PtlManagementInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;

/**
 * 组合权限分配
 *
 * @author fanshuanghu
 * @version 1.0 2018年11月20日
 * @see PtlManagementInfo003Service
 * @since 1.0
 */
@Service("PtlManagementInfo003Service")
@Transactional(rollbackFor = Exception.class)
public class PtlManagementInfo003Service extends CommonExportService<PtlManagementInfo003Dto>
        implements BaseProcessService<PtlManagementInfo003Dto> {

    @Autowired(required = false)
    private BussCommonMapperDelegateService bussCommonMapperDelegateService;

    @Autowired
    private PtlManagementInfo001Service ptlManagementInfo001Service;

    /**
     * 通过用户名删除
     *
     * @param userId 用户名
     * @see
     * @since 1.0
     */
    public void deleteByUserId(String userId) {
        restTemplate.getForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/deleteByUserId?userId=" + userId,
                Integer.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void doComplete(PtlManagementInfo003Dto dto, OperationType type) {
        restTemplate.postForObject(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/doComplete003", dto,
                String.class);
    }

    /**
     * 获取组合集合
     *
     * @param map 请求参数
     * @return 组合集合
     * @see
     * @since 1.0
     */
    public List<PtlPortfolio> getSelectedport(Map<String, Object> map) {
        return restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getSelectedport", map,
                PtlPortfolio.class);
    }


    public List<TreeNode<NodeDto>> getTree(Map<String, Object> map) {
        List<TreeNode<NodeDto>> tree = new ArrayList<>();
        List<NodeDto> inisNodeList = restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getInstitu", "",
                NodeDto.class);
        List<NodeDto> depNodeList = restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getDep", "",
                NodeDto.class);
        List<NodeDto> userNodeList = restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/getUser", "",
                NodeDto.class);
        if (inisNodeList != null && inisNodeList.size() > 0) {
            for (NodeDto insNode : inisNodeList) {
                TreeNode<NodeDto> node = new TreeNode<NodeDto>();
                node.setId(insNode.getCode());
                node.setName(insNode.getName());
                if (StringUtil.isNotEmpty(insNode.getUpperCode())) {
                    node.setpId(insNode.getUpperCode());
                }
                tree.add(node);
            }
        }

        if (depNodeList != null && depNodeList.size() > 0) {
            for (NodeDto depNode : depNodeList) {
                TreeNode<NodeDto> node = new TreeNode<NodeDto>();
                node.setId(depNode.getCode());
                node.setName(depNode.getName());
                if (StringUtil.isNotEmpty(depNode.getUpperCode())) {
                    node.setpId(depNode.getUpperCode());
                }
                tree.add(node);
            }
        }

        if (userNodeList != null && userNodeList.size() > 0) {
            for (NodeDto userNode : userNodeList) {
                TreeNode<NodeDto> node = new TreeNode<NodeDto>();
                node.setId(userNode.getCode());
                node.setName(userNode.getName());
                if (StringUtil.isNotEmpty(userNode.getUpperCode())) {
                    node.setpId(userNode.getUpperCode());
                }
                tree.add(node);
            }
        }

        boolean userIdIsEmpty = true;
        String workflowId = (String) map.get(GlobalConstant.WORKFLOW_ID);
        if (!StringUtil.equals(workflowId, "null") && StringUtil.isNotEmpty(workflowId)) {
            PtlManagementInfo003Dto entity = this.getBussinessEntityByWorkflowId(
                    workflowId);
            if (entity.getUserList() != null) {
                userIdIsEmpty = false;
                for (String userName : entity.getUserList()) {
                    for (TreeNode<NodeDto> treeNode : tree) {
                        if (StringUtil.equals(treeNode.getId(), userName)) {
                            treeNode.setChecked(true);
                        }
                    }
                }
            }
        }

        String portfolioId = (String) map.get("portfolioId");
        // 检索DB中存储的数据权限信息，已经选中的数据权限的check设成ON
        if (userIdIsEmpty && StringUtil.isNotEmpty(portfolioId)) {
            List<PtlManagementInfoDto> ptlManagementInfoDtoList = ptlManagementInfo001Service.selectUserNameList(portfolioId);
            for (PtlManagementInfoDto ptlManagementInfoDto : ptlManagementInfoDtoList) {
                for (TreeNode<NodeDto> treeNode : tree) {
                    if (StringUtil.equals(treeNode.getId(), ptlManagementInfoDto.getPortfolioId())) {
                        treeNode.setChecked(true);
                        while (treeNode != null && StringUtil.isNotEmpty(treeNode.getpId()) && !StringUtil.equals(treeNode.getpId(), treeNode.getId())) {
                            TreeNode<NodeDto> temp = null;
                            for (TreeNode<NodeDto> node : tree) {
                                if (StringUtil.equals(node.getId(), treeNode.getpId())) {
                                    node.setChecked(true);
                                    temp = node;
                                }
                            }
                            treeNode = temp;

                        }
                    }
                }
            }
        }
        return tree;

    }

    public void insertUser(PtlManagementInfo003Dto dto) {
        restTemplate.postForList(
                ServiceApplication.PORTFOLIO_SERVICE_NAME + "/ptlmanagementinfo/insertUser", dto,
                String.class);
    }
}
