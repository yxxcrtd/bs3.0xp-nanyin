package com.joyintech.tams.invest.common.settle.service;

/**
 * @author zznn
 * @date 2021/10/25
 */

import com.joyintech.framework.base.dao.SysBatchGrpMapper;
import com.joyintech.framework.base.dao.SysBatchPrdGrpMapper;
import com.joyintech.framework.base.dao.SysBatchgrpExecInfoMapper;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.base.entity.*;
import com.joyintech.framework.base.service.ProductService;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.system.batch.batchstart.dao.SysBatchStart001Mapper;
import com.joyintech.system.batch.batchstart.service.SysBatchStart001Service;
import com.joyintech.tams.invest.common.settle.dao.SettleDownLinkMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SettleDownLinkService {

    @Autowired
    private SettleDownLinkMapper settleDownLinkMapper;

    @Autowired
    private SysBatchStart001Mapper sysBatchStart001Mapper;
    @Autowired
    private SysBatchGrpMapper batchGroupMapper;
    @Autowired
    private SysBatchgrpExecInfoMapper batchExecuteDetailMapper;
    @Autowired
    private SysBatchPrdGrpMapper batchProductGroupMapper;
    @Autowired(
            required = false
    )
    private ProductService productService;

    public void execute(Map<String,Object> param){
        List<String> taskIds = listSysBatchTask(param);
        String startDate = param.get("execDate").toString();
        String endDate = param.get("execDate").toString();
        String batchGroupType = GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE;
        String prodGroupType = GlobalConstant.BATCH_EXECUTE_TYPE_GROUP;
        executeBatch(StringUtils.join(taskIds.toArray(), ","),
                StringUtil.EMPTY, startDate, endDate, batchGroupType, prodGroupType);
    }



    /**
     *  查找taskid
     * @param param
     */
    @DataSource("BASE")
    private List<String> listSysBatchTask(Map<String,Object> param){
        return settleDownLinkMapper.listSysBatchTask(param);
    }

    @DataSource("BASE")
    public Boolean executeBatch(String selectedBatchGroup, String selectedProductGroup, String startDate, String endDate, String batchGroupType, String prodGroupType) {
        String batchGroupSeqNo = selectedBatchGroup;
        String nowString = null;
        String[] prodGroupSeqNos;
        if ("1".equals(batchGroupType)) {
            batchGroupSeqNo = IDGenerator.generate();
            SysBatchGrp batchGroup = new SysBatchGrp();
            batchGroup.setSystemCode(SysCodeUtil.getSysCode());
            nowString = DateUtil.getCurrentDateTime();
            batchGroup.setSeqNo(batchGroupSeqNo);
            batchGroup.setBatchName("system" + "创建于" + nowString);
            batchGroup.setDetail(batchGroup.getBatchName() + ";启动批处理时创建。");
            String[] batchGroupSeqNos = selectedBatchGroup.split(",");
            List<SysBatchJob> jobList = new ArrayList();
            prodGroupSeqNos = batchGroupSeqNos;
            int var13 = batchGroupSeqNos.length;

            for(int var14 = 0; var14 < var13; ++var14) {
                String taskId = prodGroupSeqNos[var14];
                SysBatchJob job = new SysBatchJob();
                job.setBatchSeqNo(batchGroupSeqNo);
                job.setTaskId(taskId);
                jobList.add(job);
            }

            this.batchGroupMapper.insert(batchGroup);
            this.sysBatchStart001Mapper.batchInsertBatchGroup(jobList);
        }

        String prodGroupSeqNo = selectedProductGroup;
        String userName = LoginUserUtil.getUserName();
        SysBatchPrdGrp productGroup;
        Map productMap;
        ArrayList products;
        if (StringUtil.isNotEmpty(selectedProductGroup) && "1".equals(prodGroupType)) {
            prodGroupSeqNo = IDGenerator.generate();
            productGroup = new SysBatchPrdGrp();
            productGroup.setSeqNo(prodGroupSeqNo);
            productGroup.setGroupName("system"+ "创建于" + (nowString == null ? DateUtil.getCurrentDateTime() : nowString));
            productGroup.setDetail(productGroup.getGroupName() + ";启动批处理时创建。");
            productGroup.setSystemCode(SysCodeUtil.getSysCode());
            prodGroupSeqNos = StringUtil.split(selectedProductGroup, ",");
            List<Product> productList = new ArrayList();
            if (this.productService != null) {
                productList = this.productService.findProductByIds(prodGroupSeqNos);
            }

            productMap = (Map)((List)productList).parallelStream().collect(Collectors.toMap(Product::getProdId, (product) -> {
                return product;
            }));
            products = new ArrayList();
            String[] var30 = prodGroupSeqNos;
            int var31 = prodGroupSeqNos.length;

            for(int var32 = 0; var32 < var31; ++var32) {
                String prodId = var30[var32];
                SysBatchGrpPrdlink groupProducts = new SysBatchGrpPrdlink();
                groupProducts.setProductId(prodId);
                groupProducts.setGroupSeq(prodGroupSeqNo);
                groupProducts.setProductName(((Product)productMap.get(prodId)).getProdName());
                products.add(groupProducts);
            }

            this.batchProductGroupMapper.insert(productGroup);
            this.sysBatchStart001Mapper.batchInsertProdGroup(products);
        } else if (StringUtil.isEmpty(selectedProductGroup) && "1".equals(prodGroupType) && MultilevelDataAuthorityUtil.isEnabled() && org.apache.commons.lang3.StringUtils.isNotEmpty(userName) && !org.apache.commons.lang3.StringUtils.equals("system", userName)) {
            prodGroupSeqNo = IDGenerator.generate();
            productGroup = new SysBatchPrdGrp();
            productGroup.setSeqNo(prodGroupSeqNo);
            productGroup.setGroupName("system" + "创建于" + (nowString == null ? DateUtil.getCurrentDateTime() : nowString));
            productGroup.setDetail(productGroup.getGroupName() + ";启动批处理时创建。");
            productGroup.setSystemCode(SysCodeUtil.getSysCode());
            prodGroupSeqNos = StringUtil.split(selectedProductGroup, ",");
            new ArrayList();
            List<Product> productList = this.batchProductGroupMapper.getBelongProductList(MultilevelDataAuthorityUtil.getOrganizationCode(userName));
            productMap = (Map)productList.parallelStream().collect(Collectors.toMap(Product::getProdId, (product) -> {
                return product;
            }));
            products = new ArrayList();
            Iterator var29 = productList.iterator();

            while(var29.hasNext()) {
                Product prod = (Product)var29.next();
                SysBatchGrpPrdlink groupProducts = new SysBatchGrpPrdlink();
                groupProducts.setProductId(prod.getProdId());
                groupProducts.setGroupSeq(prodGroupSeqNo);
                groupProducts.setProductName(prod.getProdName());
                products.add(groupProducts);
            }

            this.batchProductGroupMapper.insert(productGroup);
            if (!StringUtil.isEmptyList(products)) {
                this.sysBatchStart001Mapper.batchInsertProdGroup(products);
            }
        }

        SysBatchgrpExecInfo executeDetail = new SysBatchgrpExecInfo();
        executeDetail.setSystemCode(SysCodeUtil.getSysCode());
        executeDetail.setSeqNo(IDGenerator.generate());
        executeDetail.setBatchSeqNo(batchGroupSeqNo);
        executeDetail.setPrdGroupSeqNo(prodGroupSeqNo);
        executeDetail.setStartDate(DateUtil.parse(startDate));
        executeDetail.setEndDate(DateUtil.parse(endDate));
        executeDetail.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
        executeDetail.setCreateUser("system");
        executeDetail.setUpdateUser("system");
        executeDetail.setDetailName(StringUtil.isBlank(RequestUtil.getParam("detailName")) ? "system" + "创建于" + DateUtil.getCurrentDateTime() : RequestUtil.getParam("detailName"));
        this.batchExecuteDetailMapper.insert(executeDetail);
        return true;
    }
}

