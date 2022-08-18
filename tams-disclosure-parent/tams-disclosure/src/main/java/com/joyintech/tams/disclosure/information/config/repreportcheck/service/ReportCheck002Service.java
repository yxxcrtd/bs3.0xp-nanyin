package com.joyintech.tams.disclosure.information.config.repreportcheck.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.RepDataCheckMapper;
import com.joyintech.tams.common.base.entity.RepDataCheck;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.information.config.repreportcheck.dao.ReportCheckMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.nio.cs.ext.Big5;

import java.math.BigDecimal;
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

    @Autowired
    private RepDataCheckMapper repDataCheckMapper;

    public List<RepDataCheck>getRepDataCheckList(){
        List<RepDataCheck>list=reportCheckMapper.getRepDataCheckList();
        for (RepDataCheck repDataCheck:list) {
            String paramValue=repDataCheck.getParamValue1();
            if(StringUtil.isNotEmpty(repDataCheck.getParamValue2())){
                paramValue+="；"+repDataCheck.getParamValue2();
            }
            repDataCheck.setParamValue(paramValue);
        }
        return list;
    }

    public AjaxResult check(List<RepDataCheck>dataList){
        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++) {
            int index=i+1;
            RepDataCheck repDataCheck=dataList.get(i);
            if(!StringUtil.isNotEmpty(repDataCheck.getCheckType())){
                ValidMessage validMessage = new ValidMessage("第"+index+"行比较符为空");
                errorList.add(validMessage);
            }
            String paramValue=repDataCheck.getParamValue();
            if(StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_不包含临界值.getValue(),repDataCheck.getCheckType())
            ||StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_包含较小的临界值.getValue(),repDataCheck.getCheckType())
            ||StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_包含较大的临界值.getValue(),repDataCheck.getCheckType())
            ||StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_同时包含较小和较大的临界值.getValue(),repDataCheck.getCheckType())){
                //若阈值是一个范围，需要以分号分隔分别输入两边的临界值，例：控制的区间是50-80，此处需要“50;80”
                List<String>strList=StringUtil.splitToList("；",paramValue);
                if(CollectionUtils.isEmpty(strList)||strList.size() != 2){
                    ValidMessage validMessage = new ValidMessage("第"+index+"输入格式错误，需要以分号分隔分别输入两边的临界值");
                    errorList.add(validMessage);
                }else{
                    String s1=strList.get(0);
                    String s2=strList.get(1);
                    try {
                        BigDecimal paramValue1=new BigDecimal(s1);
                        BigDecimal paramValue2=new BigDecimal(s2);

                        if(paramValue1.compareTo(paramValue2)>=0){
                            ValidMessage validMessage = new ValidMessage("第"+index+"分号左边的阈值一定要小于分号右边的阈值");
                            errorList.add(validMessage);
                        }

                    }catch (Exception e) {
                        ValidMessage validMessage = new ValidMessage("第"+index+"输入值错误，请输入正确的数字");
                        errorList.add(validMessage);
                    }
                }

            }else{
                if(StringUtil.isNotEmpty(paramValue)){
                    try {
                        BigDecimal paramValueTmp=new BigDecimal(paramValue);

                    }catch (Exception e) {
                        ValidMessage validMessage = new ValidMessage("第"+index+"输入值错误，请输入正确的数字");
                        errorList.add(validMessage);
                    }
                }else {
                    ValidMessage validMessage = new ValidMessage("第"+index+"行阈值为空");
                    errorList.add(validMessage);
                }

            }
        }
        if (errorList.size() > 0) {
            webResult.setErrorList(errorList);
        }

        return webResult;
    }
    public AjaxResult save(List<RepDataCheck>dataList){
        AjaxResult webResult = new AjaxResult();
        for (int i = 0; i < dataList.size(); i++) {
            RepDataCheck repDataCheck=dataList.get(i);
            String paramValue=repDataCheck.getParamValue();
            if(StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_不包含临界值.getValue(),repDataCheck.getCheckType())
                    ||StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_包含较小的临界值.getValue(),repDataCheck.getCheckType())
                    ||StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_包含较大的临界值.getValue(),repDataCheck.getCheckType())
                    ||StringUtil.equals(DictCodeListExt5.信披数据校验比较符_范围控制_同时包含较小和较大的临界值.getValue(),repDataCheck.getCheckType())){
                //若阈值是一个范围，需要以分号分隔分别输入两边的临界值，例：控制的区间是50-80，此处需要“50;80”
                List<String>aa=StringUtil.splitToList("；",paramValue);
                repDataCheck.setParamValue1(aa.get(0));
                repDataCheck.setParamValue2(aa.get(1));
            }else{
                repDataCheck.setParamValue1(paramValue);
                repDataCheck.setParamValue2(null);
            }
            repDataCheck.setStatus("E");
            repDataCheckMapper.deleteByPrimaryKey(repDataCheck);
            repDataCheckMapper.insert(repDataCheck);
        }
        webResult.setMessage("保存成功");
        return webResult;
    }

}
