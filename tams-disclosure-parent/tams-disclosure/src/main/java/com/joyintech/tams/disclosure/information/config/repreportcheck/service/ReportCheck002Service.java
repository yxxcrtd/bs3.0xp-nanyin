package com.joyintech.tams.disclosure.information.config.repreportcheck.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RepDataCheck;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.information.config.repreportcheck.dao.ReportCheckMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportCheck002Service {
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ReportCheck002Service.class);


    @Autowired
    private ReportCheckMapper reportCheckMapper;

    public List<RepDataCheck>getRepDataCheckList(){
        return reportCheckMapper.getRepDataCheckList();
    }

    public AjaxResult check(List<RepDataCheck>dataList){
        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            RepDataCheck repDataCheck=dataList.get(i);
            if(!StringUtil.isNotEmpty(repDataCheck.getCheckType())){
                ValidMessage validMessage = new ValidMessage("第"+i+"行比较符为空");
                errorList.add(validMessage);
            }
            String paramValue=repDataCheck.getParamValue();
            if(StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_不包含临界值,repDataCheck.getCheckType())
            ||StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_包含较小的临界值,repDataCheck.getCheckType())
            ||StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_包含较大的临界值,repDataCheck.getCheckType())){
                //若阈值是一个范围，需要以分号分隔分别输入两边的临界值，例：控制的区间是50-80，此处需要“50;80”
            }else{
                if(StringUtil.isNotEmpty(paramValue)){
                    ValidMessage validMessage = new ValidMessage("第"+i+"行");
                    errorList.add(validMessage);
                }
            }
        }

        return webResult;
    }
}
