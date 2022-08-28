/*
 * 文件名：ProdPartner001Service.java
 * 描述：理财资管管理系统V2.0
 * 修改人：zhouqiang
 * 修改时间：2022年7月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.disclosure.information.config.saleagency.service;

import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.DisProdAgency;
import com.joyintech.tams.common.base.entity.DisSaleAgency;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.information.config.prodpartner.dao.ProdPartner002Mapper;
import com.joyintech.tams.disclosure.information.config.prodpartner.dto.ProdPartnerDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dao.SaleAgency001Mapper;
import com.joyintech.tams.disclosure.information.config.saleagency.dao.SaleAgency002Mapper;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.ProdSaleAgencyDto;
import com.joyintech.tams.disclosure.information.config.saleagency.dto.SaleAgencyDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 
 * 代销结构维护
 * 〈功能详细描述〉
 * @author zhouqiang
 * @version 1.0 2022年7月12日
 * @see SaleAgency002Service
 * @since 1.0
 */
@Service
public class SaleAgency002Service extends CommonExportService<SaleAgencyDto>
    implements BaseProcessService<SaleAgencyDto> {

    @Autowired
    private SaleAgency002Mapper saleAgency002Mapper;

    @Autowired
    private SaleAgency001Mapper saleAgency001Mapper;

    /**
     * 查询产品树结构
     * @param
     * @return
     */
    public List<TreeNode> getProductTree() {
        List<TreeNode> result = new ArrayList<TreeNode>();
        //获取父节点
        result.addAll(this.getParentNodes());
        //查询当前所有产品，作为子节点
        List<TreeNode> prodInfoNodes=saleAgency001Mapper.selectProdInfo();
        result.addAll(prodInfoNodes);
        return result;
    }

    /**
     * 根据产品代码或产品名称模糊查询联系下拉框
     *
     * @param map 参数map
     * @return 产品代码和简称
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(Map<String, Object> map) {
        map.put("dataStatus",DictCodeListExt3.信披_数据状态_有效E.getValue());
        return saleAgency002Mapper.queryProdIdInfo(map);
    }

    @Override
    public void doComplete(SaleAgencyDto dto, OperationType type) {
        if (OperationType.DELETE.equals(type)) {
            deleteSaleAgency(dto);
        }
        else if (OperationType.NEW.equals(type)) {
            //新建
            insertSaleAgency(dto);
        }
        else {
            //更新
            updateSaleAgency(dto);
        }
    }

    /**
     * 更新代销机构信息
     * 更新产品代销机构关联
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateSaleAgency(SaleAgencyDto dto) {
        //1.根据代销机构代码删除对应的关联产品数据
        saleAgency002Mapper.deleteByAgencyId(dto.getSequenceNo());
        //2.插入产品代销机构关联表
        List<DisProdAgency> disProdAgencyList=new ArrayList<>();
        if(dto.getNodes()!=null &&dto.getNodes().size()>0){
            List<String> ids = this.getParentNodes().stream().map(TreeNode::getId).collect(Collectors.toList());
            dto.getNodes().removeAll(ids);
        }
        if(StringUtil.isNotEmpty(dto.getNodes())){
            for (String prodId : dto.getNodes()) {
                DisProdAgency disProdAgency=new DisProdAgency();
                disProdAgency.setAgencyId(dto.getSequenceNo());
                disProdAgency.setProdId(prodId);
                disProdAgency.setCreateUser(CommonUtil.getUserName());
                disProdAgency.setCreateDept(CommonUtil.getDeptCode());
                disProdAgency.setUpdateUser(CommonUtil.getUserName());
                disProdAgencyList.add(disProdAgency);
            }
        }
        //批量插入产品代销机构关联表
        if(!StringUtil.isEmptyList(disProdAgencyList)){
            saleAgency002Mapper.insertBtachProdAgency(disProdAgencyList);
        }
        //3.更新代销机构信息表
        saleAgency001Mapper.updateByPrimaryKey(dto);
    }

    /**
     * 删除代销机构
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteSaleAgency(SaleAgencyDto dto) {
        //根据代销机构id删除已经与该代销机构关联的产品
        saleAgency002Mapper.deleteByAgencyId(dto.getSequenceNo());
        //删除该代销机构
        saleAgency001Mapper.deleteByPrimaryKey(dto.getSequenceNo());
    }

    /**
     * 新建代销机构
     * @param dto
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertSaleAgency(SaleAgencyDto dto) {
        if(DictCodeListExt5.维护类型_代销机构.getValue().equals(dto.getMaintenanceType())){
            //构建代销机构信息dto
            DisSaleAgency disSaleAgency=new DisSaleAgency();
            disSaleAgency.setSequenceNo(dto.getSequenceNo());
            disSaleAgency.setAgencyName(dto.getAgencyName());
            disSaleAgency.setAgreementVdate(dto.getAgreementVdate());
            disSaleAgency.setAgreementMdate(dto.getAgreementMdate());
            disSaleAgency.setCreateUser(CommonUtil.getUserName());
            disSaleAgency.setCreateDept(CommonUtil.getDeptCode());
            disSaleAgency.setUpdateUser(CommonUtil.getUserName());
            saleAgency001Mapper.insertEX(disSaleAgency);
            //产品代销机构关联dto
            List<DisProdAgency> disProdAgencyList=new ArrayList<>();
            if(dto.getNodes()!=null &&dto.getNodes().size()>0){
                List<String> ids = this.getParentNodes().stream().map(TreeNode::getId).collect(Collectors.toList());
                dto.getNodes().removeAll(ids);
            }
            if(StringUtil.isNotEmpty(dto.getNodes())){
                for (String prodId : dto.getNodes()) {
                    DisProdAgency disProdAgency=new DisProdAgency();
                    disProdAgency.setAgencyId(dto.getSequenceNo());
                    disProdAgency.setProdId(prodId);
                    disProdAgency.setCreateUser(CommonUtil.getUserName());
                    disProdAgency.setCreateDept(CommonUtil.getDeptCode());
                    disProdAgency.setUpdateUser(CommonUtil.getUserName());
                    disProdAgencyList.add(disProdAgency);
                }
            }
            /*if(StringUtil.isNotEmpty(dto.getProdIds())){
                List<String> prodIds = StringUtil.splitToList(",",dto.getProdIds());
                for (String prodId : prodIds) {
                    DisProdAgency disProdAgency=new DisProdAgency();
                    disProdAgency.setAgencyId(dto.getSequenceNo());
                    disProdAgency.setProdId(prodId);
                    disProdAgency.setCreateUser(CommonUtil.getUserName());
                    disProdAgency.setCreateDept(CommonUtil.getDeptCode());
                    disProdAgency.setUpdateUser(CommonUtil.getUserName());
                    disProdAgencyList.add(disProdAgency);
                }
            }*/
            //批量插入产品代销机构关联表
            if(!StringUtil.isEmptyList(disProdAgencyList)){
                saleAgency002Mapper.insertBtachProdAgency(disProdAgencyList);
            }
        }else{
            //理财产品维度
            List<String> agencyIds = StringUtil.splitToList(",", dto.getAgencyIds());
            List<DisProdAgency> list=new ArrayList<>();
            for (String agencyId : agencyIds) {
                DisProdAgency disProdAgency=new DisProdAgency();
                disProdAgency.setAgencyId(agencyId);
                disProdAgency.setProdId(dto.getProdId());
                disProdAgency.setCreateUser(CommonUtil.getUserName());
                disProdAgency.setCreateDept(CommonUtil.getDeptCode());
                disProdAgency.setUpdateUser(CommonUtil.getUserName());
                list.add(disProdAgency);
            }
            //批量插入产品代销机构关联表
            saleAgency002Mapper.insertBtachProdAgency(list);
        }
    }

    /**
     * 根据代销机构名称查询代销机构是否重复
     * @param agencyName
     * @return
     */
    public boolean checkNameIsRepeat(String agencyName) {
        return saleAgency002Mapper.selectSaleAgencyByName(agencyName)>0;
    }

    /**
     * 根据代销机构id查询代销机构信息及关联产品
     * @param sequenceNo
     * @return
     */
    public SaleAgencyDto selectDetail(String sequenceNo) {
        SaleAgencyDto saleAgencyDto=null;
        Map<String,Object> map=new HashMap<>();
        map.put("agencyId",StringUtil.splitToList(null,sequenceNo));
        List<SaleAgencyDto> saleAgencyDtos = saleAgency001Mapper.select(map);
        if(!StringUtil.isEmptyList(saleAgencyDtos)){
            saleAgencyDto=saleAgencyDtos.get(0);
            List<ProdSaleAgencyDto> prodSaleAgencyDtoList = saleAgencyDto.getProdSaleAgencyDtoList();
            if(prodSaleAgencyDtoList!=null && prodSaleAgencyDtoList.size()>0){//有关联的产品
                saleAgencyDto.setProdIds(prodSaleAgencyDtoList.stream().map(ProdSaleAgencyDto::getProdId).distinct().collect(Collectors.joining(",")));
            }
            return saleAgencyDto;
        }
        return new SaleAgencyDto();
    }

    public List<TreeNode> getProductTreeByAgencyId(String agencyId,String pageModel) {

        List<TreeNode> result=new ArrayList<>();
        List<TreeNode> parentNodes = getParentNodes();
        //查询所有产品
        List<TreeNode> prodInfoNodes=saleAgency001Mapper.selectProdInfo();
        //根据机构id查询关联的产品
        List<ProdSaleAgencyDto> prodSaleAgencyDtos = saleAgency001Mapper.selectProdByAgencyId(agencyId);
        if(prodSaleAgencyDtos!=null && prodSaleAgencyDtos.size()>0 && StringUtil.equals(pageModel,"2")) {
            for (TreeNode prodInfoNode : prodInfoNodes) {
                for (ProdSaleAgencyDto prodSaleAgencyDto : prodSaleAgencyDtos) {
                    if(StringUtil.equals(prodInfoNode.getId(),prodSaleAgencyDto.getProdId())){
                        prodInfoNode.setChecked(true);
                        prodInfoNode.setOpen(true);
                        //循环将父节点置为选中状态
                            setParentChecked(parentNodes,prodInfoNode.getpId());
                            result.add(prodInfoNode);
                    }
                }
            }
        }
        if (StringUtil.equals(pageModel,"1")){
            result.addAll(prodInfoNodes);
        }
        result.addAll(parentNodes);
        return result;
    }

    /**
     * 将父节点置为选中状态
     * @param parentNodes
     * @param pid
     */
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
     * 流程中查看产品树
     * @param nodes
     * @return
     */
    public List<TreeNode> getProductTree_wfl(List<String> nodes) {
        List<TreeNode> resultList=new ArrayList<>();
        List<TreeNode> parentNodes = this.getParentNodes();
        //查询所有产品
        List<TreeNode> prodInfoNodes=saleAgency001Mapper.selectProdInfo();
        resultList.addAll(parentNodes);
        resultList.addAll(prodInfoNodes);
        if(nodes!=null && nodes.size()>0){
            for (TreeNode prodInfoNode : resultList) {
                for (String node : nodes) {
                    if(node.equals(prodInfoNode.getId())){
                        prodInfoNode.setChecked(true);
                        prodInfoNode.setOpen(true);
                    }
                }
            }
        }
        return resultList;
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
                    prodSeriesList = saleAgency002Mapper.selectAllProdSeries(param);
                    if(prodSeriesList!=null && prodSeriesList.size()>0){
                        for (String map_3 : prodSeriesList) {
                            TreeNode treeDto_3 = new TreeNode();
                            treeDto_3.setId(map_1.getItemCode() + map_2.getItemCode() + map_3);
                            treeDto_3.setName(map_3);
                            treeDto_3.setpId(map_1.getItemCode() + map_2.getItemCode());
                            treeDto_3.setOpen(true);
                            parentNodes.add(treeDto_3);
                        }
                    }

                }
            }
        }
        return parentNodes;
    }

    /**
     * 根据产品代码查询关联的代销机构
     * @param prodId
     * @return
     */
    public List<ProdSaleAgencyDto> selectAgencyByProdId(String prodId) {
        return saleAgency002Mapper.selectAgencyByProdId(prodId);
    }
}
