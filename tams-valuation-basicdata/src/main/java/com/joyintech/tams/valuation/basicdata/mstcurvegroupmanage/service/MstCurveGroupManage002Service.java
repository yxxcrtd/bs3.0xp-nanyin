
package com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.valuation.basicdata.mstcurvegroupmanage.dto.MstCurveGroupManageDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 收益率曲线管理service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author songjun
 * @version 1.0 2021-06-16
 * @see MstCurveGroupManage002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MstCurveGroupManage002Service extends CommonExportService<MstCurveGroupManageDto> implements BaseProcessService<MstCurveGroupManageDto> {


    /**
     * @Description:工作流，处理数据方法
     * @auther: yuzj
     * @date: 2021/09/27
     * @param: type 操作类型 NEW新增，MODIFY修改，DELETE删除，REVOKE撤销
     * @return:
     */
    @Override
    public void doComplete(MstCurveGroupManageDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        } else if (OperationType.NEW.equals(type)) {
            action = "insert";
        } else {
            action = "update";
        }

        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/service/MstCurveGroupManage/"+ action,
                entity, Integer.class);

    }


    /**
     * @Description:根据主键查询收益率曲线详情
     * @auther: yuzj
     * @date: 2021/9/13
     * @param:
     * @return:
     */
    public MstCurveGroupManageDto selectByKey(Map<String,Object> map) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/service/MstCurveGroupManage/selectByKey",
                map, MstCurveGroupManageDto.class);
    }



}
