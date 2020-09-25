package com.joyintech.tams.invest.basicdata.mstfeevarieties.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.tams.invest.basicdata.mstfeevarieties.dto.MstFeeVarietiesDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.constant.OperationType;
/**
 * 
 * 费用品种信息管理
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年8月30日
 * @see MstFeeVarietiesService
 * @since 1.0
 */
@Service
public class MstFeeVarietiesService extends CommonExportService<MstFeeVarietiesDto> implements BaseProcessService<MstFeeVarietiesDto> {

    /**
     * 根据页面条件查询数据
     * 
     * @param pageParam 页面参数
     * @return 查询到的所有数据
     * @throws Exception 异常
     */
    public Page<MstFeeVarietiesDto> selectForPage(Map<String, Object> pageParam)
        throws Exception {
        Page<MstFeeVarietiesDto> page = restTemplate.postForPage(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFeeVarieties/selectForPage",
            pageParam, MstFeeVarietiesDto.class);
        return page;
    }

    /**
     * 工作流（留痕操作结束的时候执行的方法）
     */
    @Override
    public void doComplete(MstFeeVarietiesDto entity, OperationType type) {
        entity.setOperateType(type.toString());
        restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFeeVarieties/doComplete",
            entity, Integer.class);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/basicdata/mstfeevarieties/conf/export_conf.xml").dataSource(
                DataSourceType.MASERT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<MstFeeVarietiesDto> selectPageData(Map<String, Object> selectParam) {
        try {
            return selectForPage(selectParam);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得费用名称的下拉框
     * 
     * @param param 查询条件
     * @return 结果集
     */
    public List<SuggestDto> getFeeNameOption(Map<String, Object> param) {
        return restTemplate.postForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFeeVarieties/getFeeNameOption",
            param, SuggestDto.class);
    }

    /**
     * 根据费用代码查询数据
     * 
     * @param map 费用代码
     * @return 查询到的实体类说
     */
    public MstFeeVarietiesDto selectByFeeId(Map<String, Object> map) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFeeVarieties/selectByFeeId",
            map, MstFeeVarietiesDto.class);
    }

    /**
     * 判断费Id
     * <功能详细描述>
     * @param feeId feeId
     * @return 统计结果
     * @see
     * @since 1.0
     */
    public int isUseByFeeId(String feeId) {
        return restTemplate.postForObject(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFeeVarieties/isUseByFeeId",
            feeId, Integer.class);
    }

    /**
     * 通过费用名称查询数据，验证费用名称的唯一性
     * <功能详细描述>
     * @param feeName feeName
     * @return dto
     * @see
     * @since 1.0
     */
    public List<MstFeeVarietiesDto> getDtoByFeeName(String feeName) {
        return restTemplate.getForList(
            ServiceApplication.BASICDATA_SERVICE_NAME + "/MstFeeVarieties/getDtoByFeeName?feeName="
                +feeName,MstFeeVarietiesDto.class);
    }

    
}
