
package com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.dto.MstCurveGroupManageDto;
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
 * @Description:收益率曲线族管理service
 * @auther: yuzj
 * @date: 2021/9/27
 * @param:
 * @return:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MstCurveGroupManage001Service extends CommonExportService<MstCurveGroupManageDto> {
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
    public Page<MstCurveGroupManageDto> selectForPage(Map<String, Object> pageParam){
        return simpleRestTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME + "/service/MstCurveGroupManage/selectForPage",
            pageParam, MstCurveGroupManageDto.class);
    }




    @Override
    public Page<MstCurveGroupManageDto> selectPageData(Map<String, Object> selectParam) {
        Page<MstCurveGroupManageDto> pageData = new Page<>();
        String selectedRows = MapUtils.getString(selectParam, "selectedRows");

        // 判断是导出选中项，还是导出全部
        if (StringUtils.isNotBlank(selectedRows)) {
            try {
                String pageSize = StringUtil.getOrElse(RequestUtil.getParam(GlobalConstant.PARAM_LENGTH), "20");
                List<MstCurveGroupManageDto> dataList = JsonUtils.json2List(selectedRows, MstCurveGroupManageDto.class);
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


    public MstCurveGroupManageDto selectByKey(Map<String,Object> param){
        return  simpleRestTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME +"/service/MstCurveGroupManage/selectByKey",param,
                MstCurveGroupManageDto.class);
    }
}
