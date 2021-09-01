package com.joyintech.tams.valuation.common.interf.base.service.baseinfo.other;


import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.interf.base.dto.OtherBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateCheckReturnResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.common.TemplateAnalysisForXMLService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValMstBondCreditRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValBondCreditRatingInterfService {

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
        List<ValMstBondCreditRating> listValMstBondCreditRating = new ArrayList<ValMstBondCreditRating>();
        //解析模板后的list以及日志list
        TemplateCheckReturnResult returnResult = new TemplateCheckReturnResult();
        // 1、文件读取解析
        returnResult= templateAnalysisForXMLService.execute(acctEntity);

        //2、进行数据有效性的check,并生出进入日志表的数据结构
        listValMstBondCreditRating = returnResult.getT(ValMstBondCreditRating.class);

        //3、更具有效数据，进行数据的编辑，并生成进入业务表的数据结构
        // 判断返回结果不为空
        /*if (handleListValCounterParty(listValCounterParty) != null) {
            obcResult.setValCounterPartyList((List<MstCounterParty>)handleListValCounterParty(
                    listValCounterParty).get("ValCounterParty"));
        }*/
        if (listValMstBondCreditRating != null) {
            //录入方式
            for(ValMstBondCreditRating valMstBondCreditRating:listValMstBondCreditRating){
                valMstBondCreditRating.setInputType(DictCodeListExt1.录入方式_接口导入.getValue());
            }
            obcResult.setValBondCreditRatingList(listValMstBondCreditRating);
        }
        if(returnResult.getValInterflog() != null){
            obcResult.setValInterflog(returnResult.getValInterflog());
        }
        return obcResult;
        /**modify by dongjun on 20181012 end 返回结果集修改**/
    }
}
