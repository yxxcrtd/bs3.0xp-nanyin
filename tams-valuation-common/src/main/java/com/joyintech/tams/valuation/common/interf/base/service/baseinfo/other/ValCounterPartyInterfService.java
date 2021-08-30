package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.other;

import com.joyintech.tams.valuation.common.interf.tempTables.entity.MstCounterParty;
import com.joyintech.tams.valuation.common.interf.base.dto.OtherBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ValCounterPartyInterfService {

    @Autowired
    private TemplateAnalysisForXMLService templateAnalysisForXMLService;

    /**
     * 读取模板处理
     *
     * @param templateSourceFlgDTO
     * @param acctEntity
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public OtherBaseInfoCommonResult execute(TemplateSourceFlgDTO templateSourceFlgDTO,
                                             TemplateOrderInfoReaderResult acctEntity) {
        /**modify by dongjun on 20181012 start 返回结果集修改**/
        //定义返回变量
        OtherBaseInfoCommonResult obcResult = new OtherBaseInfoCommonResult();
        List<MstCounterParty> listValCounterParty = new ArrayList<MstCounterParty>();
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);

        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValCounterParty = returnResult.getT(MstCounterParty.class);

        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 判断返回结果不为空
        /*if (handleListValCounterParty(listValCounterParty) != null) {
            obcResult.setValCounterPartyList((List<MstCounterParty>)handleListValCounterParty(
                    listValCounterParty).get("ValCounterParty"));
        }*/
        if (listValCounterParty != null) {
            obcResult.setValCounterPartyList(listValCounterParty);
        }
        if(returnResult.getValInterflog() != null){
            obcResult.setValInterflog(returnResult.getValInterflog());
        }
        return obcResult;
        /**modify by dongjun on 20181012 end 返回结果集修改**/
    }

    /**
     *
     * @param handleListValAgencyInfo 机构信息表
     * @return 客户信息表
     * @see
     * @since 1.0
     */
    /*private Map<String, Object> handleListValCounterParty(List<MstCounterParty> listValCounterParty) {
        Map<String, Object> resultMap = new HashMap<>();
        List<MstCounterParty> valCounterPartyList = new ArrayList<MstCounterParty>();
        // 获取基础信息
        for (MstCounterParty dto : listValCounterParty) {
            // 客户信息  处理
            valCounterPartyList.add(dto);
        }
        resultMap.put("ValCounterParty", valCounterPartyList);
        return resultMap;
    }*/
}
