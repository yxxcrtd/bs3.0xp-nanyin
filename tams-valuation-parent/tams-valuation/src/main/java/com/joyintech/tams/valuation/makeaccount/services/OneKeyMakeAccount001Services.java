/*
 * 文件名：OneKeyMakeAccount001Services.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：HuangJian
 * 修改时间：2019年2月27日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.makeaccount.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.joyintech.framework.base.dao.SysBatchPrdGrpMapper;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.base.entity.SysBatchPrdGrp;
import com.joyintech.framework.base.service.ProductService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.SysUnaccountantInfoDetailMapper;
import com.joyintech.tams.common.base.dao.SysUnaccountantInfoMapper;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfo;
import com.joyintech.tams.common.base.entity.SysUnaccountantInfoDetail;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dao.ValAccountHandle001Mapper;
import com.joyintech.tams.valuation.dailyoperation.dailyaccountmake.valaccounthandle.dto.ValAccountHandle001Dto;
import com.joyintech.tams.valuation.makeaccount.dao.OneKeyMakeAccount001Mapper;
import com.joyintech.tams.valuation.makeaccount.dto.OneKeyMakeAccount001Dto;
import com.joyintech.tams.valuation.system.batchstart.dao.ValBatchStart001Mapper;
import com.joyintech.tams.valuation.system.batchstart.service.ValBatchStart001Service;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 * 
 * @author HuangJian
 * @version 1.0 2019年2月27日
 * @see OneKeyMakeAccount001Services
 * @since 1.0
 */
@Service
public class OneKeyMakeAccount001Services {

    @Autowired
    private ValAccountHandle001Mapper valAccountHandle001Mapper;

    @Autowired
    private OneKeyMakeAccount001Mapper oneKeyMakeAccount001Mapper;

    @Autowired
    private SysUnaccountantInfoMapper sysUnaccountantInfoMapper;

    @Autowired
    private SysUnaccountantInfoDetailMapper sysUnaccountantInfoDetailMapper;

//    @Autowired
//    private ValBatchStart001Service valBatchStart001Service;

    @Autowired(required = false)
    private ProductService productService;

    @Autowired
    private SysBatchPrdGrpMapper batchProductGroupMapper;

    @Autowired
    private ValBatchStart001Mapper valBatchStart001Mapper;

//    @Autowired
//    private InterfCommonService interfCommonService;

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ValBatchStart001Service.class);

    /**
     * 左侧树形的任务菜单
     * 
     * @param params
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("rawtypes")
    public List<TreeNode> getTreeNodeList(Map<String, Object> params) {
        // List<ValAccountHandle001Dto> list=valAccountHandle001Mapper.findAllTask(params);
        List<TreeNode> treeList = new ArrayList<TreeNode>();
        // 一键做账
        String YJZZ = CacheUtil.getSysParam("UNITFYCACCOUNTANT_MODEL_CODE");
        String[] yjzuArr = null;
        if (StringUtil.isNotEmpty(YJZZ)) {
            yjzuArr = YJZZ.split(",");
        }
        if (yjzuArr != null){
            for (String item : yjzuArr) {
                TreeNode node = new TreeNode();
                switch (item) {
                    case "1":
                        node.setId("001");
                        node.setName("公共数据导入");
                        node.setpId(null);
                        treeList.add(node);
                        params.put("taskGrpType", "InterfData");
                        List<ValAccountHandle001Dto> list1 = valAccountHandle001Mapper.findAllTask(
                                params);
                        for (ValAccountHandle001Dto dto : list1) {
                            node = new TreeNode();
                            node.setId(dto.getTaskGrpId());
                            node.setName(dto.getTaskGrpName());
                            if (dto.getpTaskGrpId() == null || "".equals(dto.getpTaskGrpId())) {
                                node.setpId("001");
                            }
                            else {
                                node.setpId(dto.getpTaskGrpId());
                            }
                            treeList.add(node);
                        }
                        break;
                    case "2":
                        node.setId("002");
                        node.setName("交易数据导入");
                        node.setpId(null);
                        treeList.add(node);
                        params.put("taskGrpType", "InterfDeal");
                        List<ValAccountHandle001Dto> list2 = valAccountHandle001Mapper.findAllTask(
                                params);
                        for (ValAccountHandle001Dto dto : list2) {
                            node = new TreeNode();
                            node.setId(dto.getTaskGrpId());
                            node.setName(dto.getTaskGrpName());
                            if (dto.getpTaskGrpId() == null || "".equals(dto.getpTaskGrpId())) {
                                node.setpId("002");
                            }
                            else {
                                node.setpId(dto.getpTaskGrpId());
                            }
                            treeList.add(node);
                        }
                        break;
                    case "3":
                        node.setId("003");
                        node.setName("登账批处理");
                        node.setpId(null);
                        treeList.add(node);
                        params.put("taskGrpType", "task");
                        List<ValAccountHandle001Dto> list3 = valAccountHandle001Mapper.findAllTask(
                                params);
                        for (ValAccountHandle001Dto dto : list3) {
                            node = new TreeNode();
                            node.setId(dto.getTaskGrpId());
                            node.setName(dto.getTaskGrpName());
                            if (dto.getpTaskGrpId() == null || "".equals(dto.getpTaskGrpId())) {
                                node.setpId("003");
                            }
                            else {
                                node.setpId(dto.getpTaskGrpId());
                            }
                            treeList.add(node);
                        }
                        break;
                    case "4":
                        node.setId("004");
                        node.setName("估值表核对（管理人）");
                        node.setpId(null);
                        treeList.add(node);
                        break;
                    case "5":
                        node.setId("005");
                        node.setName("估值表核对（托管人）");
                        node.setpId(null);
                        treeList.add(node);
                        break;
                    case "6":
                        node.setId("006");
                        node.setName("余额表核对");
                        node.setpId(null);
                        treeList.add(node);
                        break;
                    case "7":
                        node.setId("007");
                        node.setName("净值确认下发");
                        node.setpId(null);
                        treeList.add(node);
                        break;
                    case "8":
                        node.setId("008");
                        node.setName("数据回传");
                        node.setpId(null);
                        treeList.add(node);
                        params.put("taskGrpType", "DataBack");
                        List<ValAccountHandle001Dto> list8 = valAccountHandle001Mapper.findAllTask(
                                params);
                        for (ValAccountHandle001Dto dto : list8) {
                            node = new TreeNode();
                            node.setId(dto.getTaskGrpId());
                            node.setName(dto.getTaskGrpName());
                            if (dto.getpTaskGrpId() == null || "".equals(dto.getpTaskGrpId())) {
                                node.setpId("008");
                            }
                            else {
                                node.setpId(dto.getpTaskGrpId());
                            }
                            treeList.add(node);
                        }
                        break;
                    default:
                        break;

                }

            }
        }
        return treeList;
    }

    /**
     * 检测主表中的数据是否能够执行一键做账
     * 
     * @return boolean
     * @see
     * @since 1.0
     */
    public boolean check() {

        if (oneKeyMakeAccount001Mapper.check() == 0) {
            return true;
        }
        else {
            return false;
        }

    }

    /**
     * @param selectedBatchGroup
     * @param selectedProductGroup
     * @param startDate
     * @param endDate
     * @param batchGroupType
     * @param prodGroupType
     * @param detailName
     * @return
     */
    public String doComplete(String selectedBatchGroup, String selectedProductGroup,
                             String startDate, String endDate, String batchGroupType,
                             String prodGroupType, String detailName) {
        JsonObject json = new JsonObject();
        int check = 1;
        SysUnaccountantInfo sysUnaccountantInfo = new SysUnaccountantInfo();
        String prodGroupSeqNo = selectedProductGroup;
        List<String> prodList = null;
        String nowString = null;
        // 账套模式
        if (StringUtil.isNotEmpty(prodGroupSeqNo)
            && GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(prodGroupType)) {
            prodGroupSeqNo = IDGenerator.generate();
            sysUnaccountantInfo.setPrdGroupSeqNo(prodGroupSeqNo);
            SysBatchPrdGrp productGroup = new SysBatchPrdGrp();
            productGroup.setSystemCode("VAL");
            nowString = DateUtil.getCurrentDateTime();
            productGroup.setSeqNo(prodGroupSeqNo);
            productGroup.setGroupName(SecurityUtil.getLoginUser().getRealName() + "创建于"
                                      + (nowString == null ? DateUtil.getCurrentDateTime() : nowString));
            productGroup.setDetail(productGroup.getGroupName() + ";启动批处理时创建。");
            String[] prodGroupSeqNos = StringUtil.split(selectedProductGroup, ",");
            List<Product> productList = new ArrayList<Product>();
            if (productService != null) {
                productList = productService.findProductByIds(prodGroupSeqNos);
            }
            Map<String, Product> productMap = productList.parallelStream().collect(
                Collectors.toMap(Product::getProdId, product -> product));
            List<SysBatchGrpPrdlink> products = new ArrayList<>();
            for (String prodId : prodGroupSeqNos) {
                SysBatchGrpPrdlink groupProducts = new SysBatchGrpPrdlink();
                groupProducts.setProductId(prodId);
                groupProducts.setGroupSeq(prodGroupSeqNo);
                groupProducts.setProductName(productMap.get(prodId).getProdName());
                products.add(groupProducts);
            }
            insertProdGroup(productGroup, products);

            prodList = Arrays.asList(selectedProductGroup.split(","));
        }
        // 账套组模式
        else if (StringUtil.isNotEmpty(prodGroupSeqNo)
                 && GlobalConstant.BATCH_EXECUTE_TYPE_GROUP.equals(prodGroupType)) {
            String[] prodGroupSeqNos = StringUtil.split(selectedProductGroup, ",");
            List<String> listprd = findAllProdbyGroup(prodGroupSeqNos);
            String[] prodLists = listprd.toArray(new String[listprd.size()]);
            // 组成一个账套组
            prodGroupSeqNo = IDGenerator.generate();
            sysUnaccountantInfo.setPrdGroupSeqNo(prodGroupSeqNo);
            SysBatchPrdGrp productGroup = new SysBatchPrdGrp();
            productGroup.setSystemCode("VAL");
            nowString = DateUtil.getCurrentDateTime();
            productGroup.setSeqNo(prodGroupSeqNo);
            productGroup.setGroupName(SecurityUtil.getLoginUser().getRealName() + "创建于"
                                      + (nowString == null ? DateUtil.getCurrentDateTime() : nowString));
            productGroup.setDetail(productGroup.getGroupName() + ";启动批处理时创建。");
            List<Product> productList = new ArrayList<Product>();
            if (productService != null) {
                productList = productService.findProductByIds(prodLists);
            }
            Map<String, Product> productMap = productList.parallelStream().collect(
                Collectors.toMap(Product::getProdId, product -> product));
            List<SysBatchGrpPrdlink> products = new ArrayList<>();
            for (String prodId : prodLists) {
                SysBatchGrpPrdlink groupProducts = new SysBatchGrpPrdlink();
                groupProducts.setProductId(prodId);
                groupProducts.setGroupSeq(prodGroupSeqNo);
                groupProducts.setProductName(productMap.get(prodId).getProdName());
                products.add(groupProducts);
            }
            insertProdGroup(productGroup, products);

            prodList = findAllProdbyGroup(prodGroupSeqNos);

        }

        String seqNo = IDGenerator.generate();
        sysUnaccountantInfo.setSeqNo(seqNo);
        sysUnaccountantInfo.setTaskName(detailName);
        sysUnaccountantInfo.setStatus("0");
        sysUnaccountantInfo.setStartTime(DateUtil.parse(startDate));
        sysUnaccountantInfo.setBusiDate(DateUtil.parse(startDate));
        sysUnaccountantInfoMapper.insertSelective(sysUnaccountantInfo);
        // 一键做账
        String YJZZ = CacheUtil.getSysParam("UNITFYCACCOUNTANT_MODEL_CODE");
        String[] yjzuArr = null;
        if (StringUtil.isNotEmpty(YJZZ)) {
            yjzuArr = YJZZ.split(",");
        }
        String execTaskIdBatch = IDGenerator.generate();
        String execTaskIdData = IDGenerator.generate();
        String execTaskIdDeal = IDGenerator.generate();
        String execTaskId4 = IDGenerator.generate();
        String execTaskId5 = IDGenerator.generate();
        String execTaskId6 = IDGenerator.generate();
        String execTaskId7 = IDGenerator.generate();
        String execTaskId8 = IDGenerator.generate();
        int i = 1;
        if (yjzuArr != null){
            for (String item : yjzuArr) {
                SysUnaccountantInfoDetail dysUnaccountantInfoDetail = new SysUnaccountantInfoDetail();

                switch (item) {
                    case "1":
                        dysUnaccountantInfoDetail.setSeqNo(seqNo);
                        dysUnaccountantInfoDetail.setExectaskId(execTaskIdData);
                        dysUnaccountantInfoDetail.setModelCode("1");
                        dysUnaccountantInfoDetail.setOrderNo((long)i);
                        dysUnaccountantInfoDetail.setStatus("0");
                        dysUnaccountantInfoDetail.setStartTime(DateUtil.now());
                        dysUnaccountantInfoDetail.setBusiDate(DateUtil.parse(startDate));
                        sysUnaccountantInfoDetailMapper.insertSelective(dysUnaccountantInfoDetail);
                        json.addProperty("execTaskIdData", execTaskIdData);
                        i++ ;
                        break;
                    case "2":
                        dysUnaccountantInfoDetail.setSeqNo(seqNo);
                        dysUnaccountantInfoDetail.setExectaskId(execTaskIdDeal);
                        dysUnaccountantInfoDetail.setModelCode("2");
                        dysUnaccountantInfoDetail.setOrderNo((long)i);
                        dysUnaccountantInfoDetail.setStatus("0");
                        dysUnaccountantInfoDetail.setStartTime(DateUtil.now());
                        dysUnaccountantInfoDetail.setBusiDate(DateUtil.parse(startDate));
                        sysUnaccountantInfoDetailMapper.insertSelective(dysUnaccountantInfoDetail);
                        json.addProperty("execTaskIdDeal", execTaskIdDeal);
                        i++ ;
                        break;
                    case "3":
                        dysUnaccountantInfoDetail.setSeqNo(seqNo);
                        dysUnaccountantInfoDetail.setExectaskId(execTaskIdBatch);
                        dysUnaccountantInfoDetail.setModelCode("3");
                        dysUnaccountantInfoDetail.setOrderNo((long)i);
                        dysUnaccountantInfoDetail.setStatus("0");
                        dysUnaccountantInfoDetail.setStartTime(DateUtil.now());
                        dysUnaccountantInfoDetail.setBusiDate(DateUtil.parse(startDate));
                        sysUnaccountantInfoDetailMapper.insertSelective(dysUnaccountantInfoDetail);
                        json.addProperty("execTaskIdBatch", execTaskIdBatch);
                        i++ ;
                        break;
                    case "4":
                        dysUnaccountantInfoDetail.setSeqNo(seqNo);
                        dysUnaccountantInfoDetail.setExectaskId(execTaskId4);
                        dysUnaccountantInfoDetail.setModelCode("4");
                        dysUnaccountantInfoDetail.setOrderNo((long)i);
                        dysUnaccountantInfoDetail.setStatus("0");
                        dysUnaccountantInfoDetail.setStartTime(DateUtil.now());
                        dysUnaccountantInfoDetail.setBusiDate(DateUtil.parse(startDate));
                        sysUnaccountantInfoDetailMapper.insertSelective(dysUnaccountantInfoDetail);
                        json.addProperty("execTaskId4", execTaskId4);
                        i++ ;
                        break;
                    case "5":
                        dysUnaccountantInfoDetail.setSeqNo(seqNo);
                        dysUnaccountantInfoDetail.setExectaskId(execTaskId5);
                        dysUnaccountantInfoDetail.setModelCode("5");
                        dysUnaccountantInfoDetail.setOrderNo((long)i);
                        dysUnaccountantInfoDetail.setStatus("0");
                        dysUnaccountantInfoDetail.setStartTime(DateUtil.now());
                        dysUnaccountantInfoDetail.setBusiDate(DateUtil.parse(startDate));
                        sysUnaccountantInfoDetailMapper.insertSelective(dysUnaccountantInfoDetail);
                        json.addProperty("execTaskId5", execTaskId5);
                        i++ ;
                        break;
                    case "6":
                        dysUnaccountantInfoDetail.setSeqNo(seqNo);
                        dysUnaccountantInfoDetail.setExectaskId(execTaskId6);
                        dysUnaccountantInfoDetail.setModelCode("6");
                        dysUnaccountantInfoDetail.setOrderNo((long)i);
                        dysUnaccountantInfoDetail.setStatus("0");
                        dysUnaccountantInfoDetail.setStartTime(DateUtil.now());
                        dysUnaccountantInfoDetail.setBusiDate(DateUtil.parse(startDate));
                        sysUnaccountantInfoDetailMapper.insertSelective(dysUnaccountantInfoDetail);
                        json.addProperty("execTaskId6", execTaskId6);
                        i++ ;
                        break;
                    case "7":
                        dysUnaccountantInfoDetail.setSeqNo(seqNo);
                        dysUnaccountantInfoDetail.setExectaskId(execTaskId7);
                        dysUnaccountantInfoDetail.setModelCode("7");
                        dysUnaccountantInfoDetail.setOrderNo((long)i);
                        dysUnaccountantInfoDetail.setStatus("0");
                        dysUnaccountantInfoDetail.setStartTime(DateUtil.now());
                        dysUnaccountantInfoDetail.setBusiDate(DateUtil.parse(startDate));
                        sysUnaccountantInfoDetailMapper.insertSelective(dysUnaccountantInfoDetail);
                        json.addProperty("execTaskId7", execTaskId7);
                        i++ ;
                        break;
                    case "8":
                        dysUnaccountantInfoDetail.setSeqNo(seqNo);
                        dysUnaccountantInfoDetail.setExectaskId(execTaskId8);
                        dysUnaccountantInfoDetail.setModelCode("8");
                        dysUnaccountantInfoDetail.setOrderNo((long)i);
                        dysUnaccountantInfoDetail.setStatus("0");
                        dysUnaccountantInfoDetail.setStartTime(DateUtil.now());
                        dysUnaccountantInfoDetail.setBusiDate(DateUtil.parse(startDate));
                        sysUnaccountantInfoDetailMapper.insertSelective(dysUnaccountantInfoDetail);
                        json.addProperty("execTaskId8", execTaskId8);
                        i++ ;
                        break;
                    default:
                        break;

                }
            }
        }
        json.addProperty("excuteDate", DateUtil.parse(startDate).getTime());
        json.addProperty("check", check);
        json.addProperty("seqNo", seqNo);
        json.addProperty("booksetIdArr", JsonString(prodList));
        json.addProperty("startDate", startDate);
        json.addProperty("busiDate", startDate.replace("-", ""));
        return json.toString();

    }

    private void insertProdGroup(SysBatchPrdGrp productGroup, List<SysBatchGrpPrdlink> products) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        try {
            batchProductGroupMapper.insert(productGroup);
            valBatchStart001Mapper.batchInsertProdGroup(products);
        }
        catch (Exception e) {
            logger.error("", e);
        }
        finally {
            DynamicDataSourceHolder.clearDataSource();
        }
    }

    private List<String> findAllProdbyGroup(String[] prodGroupSeqNos) {
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<String> list = new ArrayList<>();
        try {
            list = oneKeyMakeAccount001Mapper.findAllProdbyGroup(prodGroupSeqNos);
        }
        catch (Exception e) {
            logger.error("", e);
        }
        finally {
            DynamicDataSourceHolder.clearDataSource();
        }
        return list;
    }

    /**
     * 总进度日志
     * @param map
     * @return
     */
    public List<OneKeyMakeAccount001Dto> findloglogtotal(Map<String, Object> map) {
        return oneKeyMakeAccount001Mapper.findloglogtotal(map);

    }

    /**
     * 批处理日志
     * 
     * @param map
     * @return
     * @throws Exception
     */
    @DataSource(DataSourceType.OTHER)
    public List<ValAccountHandle001Dto> findLogPage(Map<String, Object> map)
        throws Exception {

        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<ValAccountHandle001Dto> list = new ArrayList<>();
        try {
            list = oneKeyMakeAccount001Mapper.findLogPage(map);
        }
        catch (Exception e) {
            logger.error("", e);
        }
        finally {
            DynamicDataSourceHolder.clearDataSource();
        }

        return list;

    }
    
    /**
     * 
     * 估值表 余额表对账信息
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public  List<OneKeyMakeAccount001Dto>  checkAccountLog(Map<String, Object> map){
       return  oneKeyMakeAccount001Mapper.checkAccountLog(map);
    }
    
    /**
     * 
     * <一句话功能简述>
     * <功能详细描述>
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    private String JsonString(List<String> list){
//        String JsonString="";
//        if(list!=null&&list.size()>0){
//            for(String item:list){
//                JsonString+=item+GlobalConstant.COMMA; 
//            }
//            
//            return JsonString;
//        }else{
//            return JsonString;
//        }
        
            
            if(list==null){
               return null;
            }
          
            StringBuilder result = new StringBuilder();
            boolean first = true;
          
            //第一个前面不拼接","
            for(String string :list) {
               if(first) {
                  first=false;
               }else{
                  result.append(GlobalConstant.COMMA);
               }
               result.append(string);
            }
            return result.toString();
        
        
    }
    
    
    
    
    
    
}
