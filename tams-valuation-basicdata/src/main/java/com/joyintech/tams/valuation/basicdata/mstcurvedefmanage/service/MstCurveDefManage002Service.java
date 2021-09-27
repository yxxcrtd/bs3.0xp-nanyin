
package com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.valuation.basicdata.mstcurvedefmanage.dto.MstCurveDefManageDto;
import com.joyintech.tams.valuation.common.constants.ServiceApplication;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 收益率曲线管理service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author songjun
 * @version 1.0 2021-06-16
 * @see MstCurveDefManage002Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MstCurveDefManage002Service extends CommonExportService<MstCurveDefManageDto> implements BaseProcessService<MstCurveDefManageDto> {

    /**
     * @Description:获取期限描述下拉框
     * @auther: yuzj
     * @date: 2021/8/20
     * @param:
     * @return:
     */
    public List<SelectOptionDto> getNkSelect() {
        List<SelectOptionDto> linkSelectOptionList = new ArrayList<>();
        //1 2 3 4 5
        String[] termDescribeArr = {"1","2","3","4","5"};
        for(int i=0;i<termDescribeArr.length;i++){
            SelectOptionDto selectOptionDto = new SelectOptionDto();
            selectOptionDto.setId(termDescribeArr[i]);
            selectOptionDto.setText(termDescribeArr[i]);
            linkSelectOptionList.add(selectOptionDto);
        }
        return linkSelectOptionList;
    }


    /**
     * @Description:工作流，处理数据方法
     * @auther: yuzj
     * @date: 2021/09/11
     * @param: type 操作类型 NEW新增，MODIFY修改，DELETE删除，REVOKE撤销
     * @return:
     */
    @Override
    public void doComplete(MstCurveDefManageDto entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        } else if (OperationType.NEW.equals(type)) {
            action = "insert";
        } else {
            action = "update";
        }

        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/service/MstCurveDefManage/"+ action,
                entity, Integer.class);

    }


    /**
     * @Description:获取期限描述下拉框
     * @auther: yuzj
     * @date: 2021/8/20
     * @param:
     * @return:
     */
    public Page<SelectOptionDto> getCurveGroupSelect(Map<String,Object> map) {
        return restTemplate.postForPage(ServiceApplication.VALUATION_SERVICE_NAME+"/service/MstCurveDefManage/getCurveGroupSelect",
                map, SelectOptionDto.class);
    }

    /**
     * @Description:根据主键查询收益率曲线详情
     * @auther: yuzj
     * @date: 2021/9/13
     * @param:
     * @return:
     */
    public MstCurveDefManageDto selectByKey(Map<String,Object> map) {
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/service/MstCurveDefManage/selectByKey",
                map, MstCurveDefManageDto.class);
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
                        + "/service/MstCurveDefManage/getLinkSelectOption",
                param, SelectOptionDto.class);
    }




}
