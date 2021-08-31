package com.joyintech.service.valuation.valmodel.batch.batchtask.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.dao.SysBatchGrpMapper;
import com.joyintech.framework.base.dao.SysBatchPrdGrpMapper;
import com.joyintech.framework.base.dao.SysBatchgrpExecInfoMapper;
import com.joyintech.framework.base.dto.Product;
import com.joyintech.framework.base.entity.*;
import com.joyintech.framework.base.service.ProductService;
import com.joyintech.framework.constant.BatchDictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.service.valuation.valmodel.batch.batchtask.dao.ModBatchTaskMapper;
import com.joyintech.service.valuation.valmodel.batch.batchtask.dto.*;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogDetailDTO;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogs;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModBatchLogsExample;
import com.joyintech.tams.valuation.common.batch.batchTasks.ModValBatchParams;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.component.batch.dao.ValModBatchLogsMapper;
import com.joyintech.tams.valuation.component.batch.dao.ModBatchParams2Mapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 王波
 * @date 2021-07-13
 * @see ModBatchTaskService
 * @since 1.0
 */
@Service
public class ModBatchTaskService extends BaseService {

    @Autowired
    private ModBatchTaskMapper modBatchTaskMapper;

    @Autowired
    private SysBatchGrpMapper batchGroupMapper;

    @Autowired(required = false)
    private ProductService productService;

    @Autowired
    private SysBatchPrdGrpMapper batchProductGroupMapper;

    @Autowired
    private SysBatchgrpExecInfoMapper batchExecuteDetailMapper;

    @Autowired
    private ValModBatchLogsMapper modBatchLogsMapper;

    public List<BatchTaskDto> getBatchTask(Map<String, Object> params) {
        return modBatchTaskMapper.getBatchTask(params);
    }

    public void batchInsertBatchGroup(List<SysBatchJob> jobList) {

        modBatchTaskMapper.batchInsertBatchGroup(jobList);
    }

    public void batchInsertProdGroup(List<SysBatchGrpPrdlink> products) {
        modBatchTaskMapper.batchInsertProdGroup(products);
    }

    /**
     * 读取资产信息
     * @param params
     * @return
     */
    public Page<SelectOptionDto> getAssetInfo(Map<String, Object> params, int page, int length) {
        params.put("page", page);
        params.put("length", length);

        Page<BatchAssetInfoDto> assetInfoPages = new Page<>();
        Boolean isImpairment = (Boolean)params.get("isImpairment");
        if (isImpairment != null && isImpairment) {
//            assetInfoPages= super.queryForPage(page, length, () -> modBatchTaskMapper.getAssetInfoImpairment(params));
            assetInfoPages= super.queryForPage(page, length, () -> modBatchTaskMapper.getLossAssetInfo(params));
        } else {
            List<String> finprodTypes = new ArrayList<>(2);
            finprodTypes.add(DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue());
            finprodTypes.add(DictCodeListExt1.金融产品类型_投管用_债券.getValue());
            params.put("finprodTypes", finprodTypes);
            assetInfoPages=super.queryForPage(page, length, () -> modBatchTaskMapper.getAssetInfo(params));
        }

        Page<SelectOptionDto> res = new Page<>();
        BeanUtils.copyProperties(assetInfoPages, res);
        if (CollectionUtils.isNotEmpty(assetInfoPages.getRoot())) {
            List<SelectOptionDto> root = new ArrayList<>(assetInfoPages.getRoot().size());
            for (BatchAssetInfoDto batchAssetInfoDto : assetInfoPages.getRoot()) {
                SelectOptionDto select = new SelectOptionDto();
                select.setId(batchAssetInfoDto.getFinprodId());
                select.setText(batchAssetInfoDto.getFinprodName());
                root.add(select);
            }
            res.setRoot(root);
        }
        return res;
    }

    /**
     * 主画面分页查询
     * @param params
     * @param page
     * @param length
     * @return
     */
    public Page<BatchExecutingStatusDto> findBatchExeDetail(Map<String, Object> params, int page, int length) {
        return super.queryForPage(page, length, ()->modBatchTaskMapper.findBatchExeDetail(params));
    }

    public List<BatchAssetInfoDto> getAllAssetInfo() {
        Map<String, Object> requestMaps = new HashMap<>();
        List<BatchAssetInfoDto> assetInfos = restTemplate.postForList(ServiceApplication.CUSTOMER_INTERF_NAME
                        + "/interf/ModelCallOnInvest/getAllAssetInfo"
                , requestMaps, BatchAssetInfoDto.class);
        return assetInfos;
    }

    @Autowired
    private SqlSessionTemplate template;

    public void insertBatchParams(List<ModValBatchParams> params) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            ModBatchParams2Mapper batchMapper = session.getMapper(ModBatchParams2Mapper.class);
            int size = params.size();
            for(int index = 0; index < size; index++){
                batchMapper.insert(params.get(index));
                count ++;
                // 每1000次提交
                if (count % 1000 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }
        finally {
            session.close();
        }
    }

    /**
     * 检查是否有未执行的坡处理
     * @param finprodIds
     * @return
     */
    public boolean checkIsRunning(String finprodIds) {
        Map<String, Object> params = new HashMap<String, Object>(4);
        if (StringUtil.isNotBlank(finprodIds)) {
            List<String> finprodIdList = Arrays.asList(finprodIds.split(GlobalConstant.COMMA));
            params.put("finprodIdList", finprodIdList);
        }
        return modBatchTaskMapper.checkIsRunning(params) > 0;
    }

    /**
     * 跑批前清理掉之前的参数
     */
    public void cleanBatchParams() {
        modBatchTaskMapper.cleanBatchParams();
    }

    /**
     * 执行批处理
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public boolean executeBatch(String realName,
                                String selectedBatchGroup,
                                String selectedProductGroup,
                                String startDate,
                                String endDate,
                                String batchGroupType,
                                String prodGroupType,
                                String finprodIds) {
        String batchGroupSeqNo = selectedBatchGroup;
        String nowString = null;
        if (GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(batchGroupType)) {
            batchGroupSeqNo = IDGenerator.generate();
            SysBatchGrp batchGroup = new SysBatchGrp();
            // TODO
            batchGroup.setSystemCode(SysCodeUtil.getSysCode());
            nowString = DateUtil.getCurrentDateTime();
            batchGroup.setSeqNo(batchGroupSeqNo);
            batchGroup.setBatchName(realName + "创建于" + nowString);
            batchGroup.setDetail(batchGroup.getBatchName() + ";启动批处理时创建。");
            String[] batchGroupSeqNos = selectedBatchGroup.split(",");
            List<SysBatchJob> jobList = new ArrayList<>();
            for (String taskId : batchGroupSeqNos) {
                SysBatchJob job = new SysBatchJob();
                job.setBatchSeqNo(batchGroupSeqNo);
                job.setTaskId(taskId);
                jobList.add(job);
            }
            batchGroupMapper.insert(batchGroup);
            batchInsertBatchGroup(jobList);
        }

        String prodGroupSeqNo = selectedProductGroup;
        if (StringUtil.isNotEmpty(prodGroupSeqNo) && GlobalConstant.BATCH_EXECUTE_TYPE_SINGLE.equals(prodGroupType)) {
            prodGroupSeqNo = IDGenerator.generate();
            SysBatchPrdGrp productGroup = new SysBatchPrdGrp();
            productGroup.setSeqNo(prodGroupSeqNo);
            productGroup.setGroupName(realName + "创建于"
                    + (nowString == null ? DateUtil.getCurrentDateTime() : nowString));
            productGroup.setDetail(productGroup.getGroupName() + ";启动批处理时创建。");
            productGroup.setSystemCode(SysCodeUtil.getSysCode());
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
            batchProductGroupMapper.insert(productGroup);
            batchInsertProdGroup(products);
        }

        SysBatchgrpExecInfo executeDetail = new SysBatchgrpExecInfo();
        executeDetail.setSystemCode(SysCodeUtil.getSysCode());
        executeDetail.setSeqNo(IDGenerator.generate());
        executeDetail.setBatchSeqNo(batchGroupSeqNo);
        executeDetail.setPrdGroupSeqNo(prodGroupSeqNo);
        executeDetail.setStartDate(DateUtil.parse(startDate));
        executeDetail.setEndDate(DateUtil.parse(endDate));
        executeDetail.setStatus(BatchDictCodeList.批处理执行状态_未执行.getValue());
        executeDetail.setCreateUser(LoginUserUtil.getUserName());
        executeDetail.setUpdateUser(LoginUserUtil.getUserName());
        executeDetail.setDetailName(StringUtil.isBlank(
                RequestUtil.getParam("detailName")) ? (realName + "创建于"
                + DateUtil.getCurrentDateTime()) : RequestUtil.getParam("detailName"));

        batchExecuteDetailMapper.insert(executeDetail);

        List<ModValBatchParams> params = new ArrayList<>();

        if (StringUtil.isNotBlank(finprodIds)) {
            List<String> finprodIdList = Arrays.asList(finprodIds.split(GlobalConstant.COMMA));
            for (String finprodId : finprodIdList) {
                ModValBatchParams param = new ModValBatchParams();
                param.setSeqno(IDGenerator.generate());
                param.setExecDate(DateUtil.parse(startDate));
                // 批处理执行序号
                param.setBatchExecSeqno(executeDetail.getSeqNo());

                // 资产代码
                param.setFinprodId(finprodId);

                param.setCreateUser(LoginUserUtil.getUserName() == null?"system":LoginUserUtil.getUserName());
                param.setUpdateUser(LoginUserUtil.getUserName() == null?"system":LoginUserUtil.getUserName());
                params.add(param);
            }
        } else {
            List<BatchAssetInfoDto> assetInfos = getAllAssetInfo();
            if (CollectionUtils.isNotEmpty(assetInfos)) {
                for (BatchAssetInfoDto assetInfo : assetInfos) {
                    ModValBatchParams param = new ModValBatchParams();
                    param.setSeqno(IDGenerator.generate());
                    param.setExecDate(DateUtil.parse(startDate));
                    // 批处理执行序号
                    param.setBatchExecSeqno(executeDetail.getSeqNo());

                    // 资产代码
                    param.setFinprodId(assetInfo.getFinprodId());

                    param.setCreateUser(LoginUserUtil.getUserName() == null?"system":LoginUserUtil.getUserName());
                    param.setUpdateUser(LoginUserUtil.getUserName() == null?"system":LoginUserUtil.getUserName());
                    params.add(param);
                }
            }
        }

        insertBatchParams(params);
        return true;
    }

    public List<ModBatchLogDetailDTO> findBatchExeTaskForExport(Map<String, Object> params) {
        String batchSeqno = (String)params.get("detailSeqNo");
        String taskId = (String)params.get("taskId");
        String execStatus = (String)params.get("execStatus");
        ModBatchLogsExample example = new ModBatchLogsExample();
        ModBatchLogsExample.Criteria criteria = example.createCriteria();

        if (StringUtil.isNotBlank(batchSeqno)) {
            criteria.andBatchExecSeqnoEqualTo(batchSeqno);
        }
        if (StringUtil.isNotBlank(taskId)) {
            criteria.andTaskIdEqualTo(taskId);
        }
        if (StringUtil.isNotBlank(execStatus) && !StringUtil.equals("undefined", execStatus)) {
            criteria.andExecStatusEqualTo(execStatus);
        }

        List<ModBatchLogs> logPage =  modBatchLogsMapper.selectByExampleWithBLOBs(example);
        List<ModBatchLogDetailDTO> res = new ArrayList<>();
        BeanUtil.copyPropertiesIgnoreNull(res, logPage);

        if (CollectionUtils.isNotEmpty(logPage)) {
            List<String> finProdIdList = new ArrayList<>(logPage.size());
            for (ModBatchLogs modBatchLogs : logPage) {
                finProdIdList.add(modBatchLogs.getFinprodId());
                ModBatchLogDetailDTO modBatchLogDetailDTO = new ModBatchLogDetailDTO();
                BeanUtil.copyPropertiesIgnoreNull(modBatchLogDetailDTO, modBatchLogs);
                res.add(modBatchLogDetailDTO);
            }
            Map<String, Object> requestMap = new HashMap<>();
            requestMap.put("finProdIdList", finProdIdList);
            requestMap.put("isImpairment", true);
            List<BatchAssetInfoDto> assetInfos = restTemplate.postForList(ServiceApplication.CUSTOMER_INTERF_NAME
                            + "/interf/ModelCallOnInvest/getAllAssetInfo"
                    , requestMap, BatchAssetInfoDto.class);
            if (CollectionUtils.isNotEmpty(assetInfos)) {
                Map<String, BatchAssetInfoDto> map = assetInfos.stream()
                        .collect(Collectors.toMap(BatchAssetInfoDto::getFinprodId, Function.identity()));
                res.forEach(x->x.setFinprodName(map.get(x.getFinprodId()) == null ? null:map.get(x.getFinprodId()).getFinprodName()));
            }
        }

        return res;
    }

    public Page<ModBatchLogDetailDTO> findBatchExeTask(Map<String, Object> params, int page, int length) {
        String batchSeqno = (String)params.get("detailSeqNo");
        String taskId = (String)params.get("taskId");
        String execStatus = (String)params.get("execStatus");
        ModBatchLogsExample example = new ModBatchLogsExample();
        ModBatchLogsExample.Criteria criteria = example.createCriteria();
        
        if (StringUtil.isNotBlank(batchSeqno)) {
            criteria.andBatchExecSeqnoEqualTo(batchSeqno);
        }
        if (StringUtil.isNotBlank(taskId)) {
            criteria.andTaskIdEqualTo(taskId);
        }
        if (StringUtil.isNotBlank(execStatus) && !StringUtil.equals("undefined", execStatus)) {
            criteria.andExecStatusEqualTo(execStatus);
        }

        Page<ModBatchLogs> logPage = super.queryForPage(page, length, () -> modBatchLogsMapper.selectByExampleWithBLOBs(example));
        Page<ModBatchLogDetailDTO> res = new Page<>();
        BeanUtil.copyPropertiesIgnoreNull(res, logPage);
        List<ModBatchLogDetailDTO> root = new ArrayList<>(logPage.getRoot().size());
        if (CollectionUtils.isNotEmpty(logPage.getRoot())) {
            List<String> finProdIdList = new ArrayList<>(logPage.getRoot().size());
            for (ModBatchLogs modBatchLogs : logPage.getRoot()) {
                finProdIdList.add(modBatchLogs.getFinprodId());
                ModBatchLogDetailDTO modBatchLogDetailDTO = new ModBatchLogDetailDTO();
                BeanUtil.copyPropertiesIgnoreNull(modBatchLogDetailDTO, modBatchLogs);
                root.add(modBatchLogDetailDTO);
            }
            Map<String, Object> requestMaps = new HashMap<>();
            requestMaps.put("finProdIdList", finProdIdList);
            List<BatchAssetInfoDto> assetInfos = restTemplate.postForList(ServiceApplication.CUSTOMER_INTERF_NAME
                            + "/interf/ModelCallOnInvest/getAllAssetInfo"
                    , requestMaps, BatchAssetInfoDto.class);
            if (CollectionUtils.isNotEmpty(assetInfos)) {
                Map<String, BatchAssetInfoDto> map = assetInfos.stream()
                        .collect(Collectors.toMap(BatchAssetInfoDto::getFinprodId, Function.identity()));
                root.forEach(x->x.setFinprodName(map.get(x.getFinprodId()) == null ? null:map.get(x.getFinprodId()).getFinprodName()));
            }
        }
        res.setRoot(root);
        return res;
    }
}
