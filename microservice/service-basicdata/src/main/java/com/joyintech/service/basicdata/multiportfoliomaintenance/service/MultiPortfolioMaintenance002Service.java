package com.joyintech.service.basicdata.multiportfoliomaintenance.service;

import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.service.basicdata.multiportfoliomaintenance.dao.MutiptlPortfolioDetailMapper;
import com.joyintech.service.basicdata.multiportfoliomaintenance.dto.MutiptlPortfolioDetailDto;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections.list.AbstractLinkedList;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对账参数设置
 */
@Service("backMultiPortfolioMaintenance002Service")
public class MultiPortfolioMaintenance002Service extends BaseService<MutiptlPortfolioDetailDto>{


    @Autowired
    private MutiptlPortfolioDetailMapper mutiptlPortfolioDetailMapper;

    /**
     * 根据名称查询账户树
     *
     * @param paramMap
     * @return
     * @since 1.0
     */
    public List<TreeNode> getMutiptlPortfolioAcctnoTree(Map<String, Object> paramMap) {
        paramMap.put("username", SecurityUtil.getLoginUser().getUserName());
        List<MutiptlPortfolioDetailDto> portfolioAcctnoList=mutiptlPortfolioDetailMapper.getMutiptlPortfolioAcctnoTree(paramMap);
        Map<String,List<MutiptlPortfolioDetailDto>> mapList=portfolioAcctnoList
                .stream()
                .collect(Collectors.groupingBy(dto ->
                        dto.getPortfolioId() + "_" + dto.getPortfolioName()));
        List<TreeNode> treeNodes=new ArrayList<>();
        mapList.forEach((key, list) -> {
            TreeNode treeNode=new TreeNode();
            treeNode.setId(key.split("_")[0]);
            treeNode.setName(key.split("_")[1]);
            treeNode.setOpen(true);
            treeNodes.add(treeNode);
            list.forEach(detailDto -> {
                TreeNode node=new TreeNode();
                node.setId(detailDto.getAcctNo());
                node.setName(detailDto.getAcctName());
                node.setpId(key.split("_")[0]);
                node.setOpen(true);
                treeNodes.add(node);
            });
        });
        return treeNodes;
    }
}
