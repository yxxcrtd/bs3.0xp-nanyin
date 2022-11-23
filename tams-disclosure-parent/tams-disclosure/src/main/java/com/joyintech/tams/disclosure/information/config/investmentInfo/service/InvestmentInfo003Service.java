package com.joyintech.tams.disclosure.information.config.investmentInfo.service;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Amount;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.disclosure.information.config.investmentInfo.dto.InvestmentInfo002Dto;
import com.joyintech.tams.disclosure.information.config.reportcontentenum.dao.ReportContentEnum002Mapper;
import com.joyintech.tams.disclosure.information.config.saleagency.dao.SaleAgency002Mapper;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class InvestmentInfo003Service extends CommonExportService<InvestmentInfo002Dto> implements BaseProcessService<InvestmentInfo002Dto> {

    @Autowired
    private ReportContentEnum002Mapper reportContentEnum002Mapper;

    @Autowired
    private SaleAgency002Mapper saleAgency002Mapper;

    @Override
    public void doComplete(InvestmentInfo002Dto investmentInfo002Dto, OperationType operationType) {

    }

    public List<TreeNode> getProductTree(List<String> prodIdList) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        //获取父节点
        List<TreeNode> parentNodes = getParentNodes();
        result.addAll(parentNodes);
        //查询当前所有产品，作为子节点
        List<TreeNode> prodInfoNodes=reportContentEnum002Mapper.selectInvesProdInfo();
        if(CollectionUtils.isNotEmpty(prodIdList)){
            for (TreeNode prodInfoNode : prodInfoNodes) {
                if(prodIdList.contains(prodInfoNode.getId())){
                    prodInfoNode.setChecked(true);
                    prodInfoNode.setOpen(true);
                    //循环将父节点置为选中状态
                    setParentChecked(parentNodes,prodInfoNode.getpId());
                }
            }
        }
        result.addAll(prodInfoNodes);
        return result;
    }

    private void setParentChecked(List<TreeNode> parentNodes, String pid) {
        if (StringUtil.isNotBlank(pid)){
            for (TreeNode node : parentNodes) {
                if(pid.equals(node.getId())){
                    node.setChecked(true);
                    setParentChecked(parentNodes,node.getpId());
                    break;
                }
            }
        }
    }

    /**
     * 组装除产品子节点以外的父节点
     * @return
     */
    public List<TreeNode> getParentNodes(){
        List<DataDictItem> prodStatusList = new ArrayList<DataDictItem>();//产品状态
        List<DataDictItem> issueTypeList = new ArrayList<DataDictItem>();//发行方式
        List<String> prodSeriesList = new ArrayList<String>();//产品系列
        List<TreeNode> parentNodes = new ArrayList<TreeNode>();
        List<String> prodSeriesListSon = new ArrayList<String>();
        prodStatusList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.信披_产品状态.getValue());
        issueTypeList = (List<DataDictItem>) SysDataUtil.getDataDictItem(DictCodeExt1.发行方式_信批.getValue());
        //一级：产品状态
        if (prodStatusList!=null && prodStatusList.size() > 0) {
            for (DataDictItem map_1 : prodStatusList) {
                //查询产品系列的条件
                Map<String,Object> param=new HashMap<>();
                TreeNode treeDto = new TreeNode();
                treeDto.setId(map_1.getItemCode());
                treeDto.setName(map_1.getItemValue());
                treeDto.setpId("");
                treeDto.setOpen(true);
                parentNodes.add(treeDto);
                param.put("prodStatus",map_1.getItemCode());
                //二级：发行方式
                for (DataDictItem map_2 : issueTypeList) {
                    TreeNode treeDto_2 = new TreeNode();
                    treeDto_2.setId(map_1.getItemCode() + map_2.getItemCode());
                    treeDto_2.setName(map_2.getItemValue());
                    treeDto_2.setpId(map_1.getItemCode());
                    treeDto_2.setOpen(true);
                    parentNodes.add(treeDto_2);
                    param.put("issueType",map_2.getItemCode());
                    //三级：产品系列：产品系列为文本框输入，不是字典值，需要从产品基本信息表中查询所有的产品系列
                    prodSeriesList = saleAgency002Mapper.selectAllInvesProdSeries(param);
                    if (prodSeriesList != null && prodSeriesList.size() > 0) {
                        for (String map_3 : prodSeriesList) {
                            TreeNode treeDto_3 = new TreeNode();
                            treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + map_3);
                            treeDto_3.setName(map_3);
                            treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
                            treeDto_3.setOpen(true);
                            parentNodes.add(treeDto_3);
                            param.put("prodSeries",map_3);
                            prodSeriesListSon=saleAgency002Mapper.selectAllInvesProdSeriesSon(param);
                            for (String map_4 : prodSeriesListSon) {
                                TreeNode treeDto_4 = new TreeNode();
                                treeDto_4.setId(map_1.getItemCode() + map_2.getItemCode() + map_3+map_4);
                                treeDto_4.setName(map_4);
                                treeDto_4.setpId(map_1.getItemCode() + map_2.getItemCode()+ map_3);
                                treeDto_4.setOpen(true);
                                parentNodes.add(treeDto_4);
                            }
                        }
                    } else {
                        TreeNode treeDto_3 = new TreeNode();
                        treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + "无数据");
                        treeDto_3.setName("无数据");
                        treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
                        treeDto_3.setOpen(true);
                        parentNodes.add(treeDto_3);
                    }

                }

            }
        }

        return parentNodes;
    }
}
