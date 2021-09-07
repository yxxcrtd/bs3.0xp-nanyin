
package com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.dto.MstCurveDefManageDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 收益率曲线管理service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author songjun
 * @version 1.0 2021-06-16
 * @see MstCurveDefManage001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MstCurveDefManage001Service extends CommonExportService<MstCurveDefManageDto> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     *
     * 查询
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return Page<MstYieldCurveInfoDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<MstCurveDefManageDto> selectForPage(Map<String, Object> pageParam){
        return simpleRestTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/service/MstCurveDefManage/selectForPage",
            pageParam, MstCurveDefManageDto.class);
    }


    /**
     * 查询收益率曲线下拉框
     * @param param
     * @return
     */
    public Page<SelectOptionDto> getCurveCode(Map<String,Object> param){
        return simpleRestTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME +"/service/MstCurveDefManage/getCurveCode",param,
                SelectOptionDto.class);
    }

    /**
     * 更新状态
     *
     */
    public void updateEffStatus(MstCurveDefManageDto dto){
        simpleRestTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME +"/service/MstCurveDefManage/updateEffStatus",dto,
                null);
    }

    /**
     * 导出
     *
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/valuation/basicdata/mstcurvedefmanage/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<MstCurveDefManageDto> selectPageData(Map<String, Object> selectParam) {
        Page<MstCurveDefManageDto> pageData = new Page<>();
        String selectedRows = MapUtils.getString(selectParam, "selectedRows");

        // 判断是导出选中项，还是导出全部
        if (StringUtils.isNotBlank(selectedRows)) {
            try {
                String pageSize = StringUtil.getOrElse(RequestUtil.getParam(GlobalConstant.PARAM_LENGTH), "20");
                List<MstCurveDefManageDto> dataList = JsonUtils.json2List(selectedRows, MstCurveDefManageDto.class);
                pageData.setRoot(dataList);
                pageData.setRecords(dataList.size());
                pageData.setTotal((int) Math.ceil(dataList.size() * 1.0d / Integer.parseInt(pageSize)));
            } catch (IOException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
            }
        }
        else {
            // 如果是导出全部
            //selectParam.put(GlobalConstant.PARAM_PAGE, 1);
            //selectParam.put(GlobalConstant.PARAM_LENGTH, Integer.MAX_VALUE);
            pageData = selectForPage(selectParam);
        }
        return pageData;
    }

    /**
     * 查询收益率曲线定义是否存在
     * @param dto
     * @return
     */
    public List<MstCurveDefManageDto> getCurveDefCount(MstCurveDefManageDto dto){
        return  simpleRestTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME +"/service/MstCurveDefManage/getCurveDefCount",dto,
                MstCurveDefManageDto.class);
    }

    public  MstCurveDefManageDto selectByKey(Map<String,Object> param){
        return  simpleRestTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME +"/service/MstCurveDefManage/selectByKey",param,
                MstCurveDefManageDto.class);
    }
}
