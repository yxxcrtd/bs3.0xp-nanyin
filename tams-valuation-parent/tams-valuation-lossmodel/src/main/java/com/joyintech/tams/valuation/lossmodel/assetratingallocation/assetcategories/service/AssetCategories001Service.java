
package com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.base.entity.SysBatchGrpPrdlink;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.tams.valuation.lossmodel.assetratingallocation.assetcategories.dto.AssetCategories001Dto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;

/**
 * 资产大类配置
 *
 * @author 刘华明
 * @version 1.0 2021年6月29日
 * @see AssetCategories001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AssetCategories001Service extends CommonExportService<AssetCategories001Dto> implements BaseProcessService<AssetCategories001Dto>{
    /**
     * 检索账套代码列表
     *
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getBooksetId(Map<String, Object> param) {
        return null;
    }

    /**
     * 检索模板账套
     *
     * @param params 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetIdTemp(Map<String, Object> params) {
        return null;//restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetIdTemp", params, SuggestDto.class);
    }

    /**
     * 检索账套代码列表
     *
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getBooksetIdForSelect() {
        return null;//restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetIdForSelect", SelectOptionDto.class);
    }

    /**
     * 检索账套代码列表(分页下拉框)
     * @param param
     * @return
     */
    public Page<SelectOptionDto> findBooksetIdByPage(Map<String, Object> param) {
        return null;//restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/findBooksetIdByPage", param, SelectOptionDto.class);
    }

    /**
     * 检索账套代码列表（只取理财账套）
     *
     * @param params 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetId2(Map<String, Object> params) {
        return null;//restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetId2", params, SuggestDto.class);
    }

    /**
     * 根据账套组中账套ID查询账套 add by yanya 20190929
     *
     * @param params 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getBooksetId3(Map<String, Object> params) {
        return null;//restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetId3", params, SuggestDto.class);
    }

    public List<AssetCategories001Dto> selectForExport(Map<String, Object> paraMap) {
        return restTemplate.postForList(
                ServiceApplication.LOSS_MODEL_SERVICE_NAME + "/ValAssetCategories001/selectForExport", paraMap,
                AssetCategories001Dto.class);
    }

    /**
     * 根据资产类别查询资产大类评级配置
     *
     * @param params
     * @return List<AssetCategories001Dto>
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<AssetCategories001Dto> getAllAssetCategoriesPage(Map<String,Object> params) throws Exception {
        Page<AssetCategories001Dto> rstobj = restTemplate.postForPage(ServiceApplication.LOSS_MODEL_SERVICE_NAME+"/ValAssetCategories001/getAllAssetCategoriesPage", params, AssetCategories001Dto.class);
        return rstobj;
    }

    /**
     * 格式化创建SelectOptionDto对象
     *
     * @param selectId 下拉Id值
     * @param text 下拉文本值
     * @return dto
     * @see
     * @since 1.0
     */
    public SelectOptionDto createSelect(String selectId, String text) {
        SelectOptionDto dto = new SelectOptionDto();
        dto.setId(selectId);
        dto.setText(text);
        return dto;
    }

    /**
     * queryVoucherCount
     * @param booksetId booksetId
     * @return int
     * @see
     * @since 1.0
     */
    public int queryVoucherCount(String booksetId) {
        return 0;//restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/queryVoucherCount?booksetId="+booksetId, Integer.class);
    }

    /**
     * doComplete
     */
    @Override
    public void doComplete(AssetCategories001Dto dto, OperationType operationType) {
        //restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/doComplete?booksetId=", Integer.class);
    }

    /**
     * 批量删除
     *
     * @param datas 科目信息列表
     * @see
     * @since 1.0
     */
    public void delete(String datas) {
        //restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/delete", datas, boolean.class);
    }

    /**
     * 获取产品组下的账套
     * @param params
     * @return
     */
    public List<SysBatchGrpPrdlink> findAllProduct(Map<String, Object> params) {
        return null;//restTemplate.postForList(
                //ServiceApplication.VALUATION_SERVICE_NAME + "/ValAssetBook001/findAllProduct",
                //params, SysBatchGrpPrdlink.class);
    }

    /**
     * 产品组
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> findBooksetGroup(Map<String, Object> pageParam) {
        return null;//restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/findBooksetGroup",
               // pageParam, SelectOptionDto.class);
    }

    /**
     * 账套代码
     * @param pageParam
     * @return
     */
    public Page<SelectOptionDto> getBooksetIdNew(Map<String, Object> pageParam) {
        return null;//restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/ValAssetBook001/getBooksetIdNew",
                //pageParam, SelectOptionDto.class);
    }

    /**
     * 通用联动查询
     * @param param : dict_code_parent,dict_code_child,item_code_parent
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @DataSource(DataSourceType.OTHER)
    public List<SelectOptionDto> getLinkSelectOption(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME
                        + "/ValuationMethodScheme001/getLinkSelectOption",
                param, SelectOptionDto.class);
    }
}