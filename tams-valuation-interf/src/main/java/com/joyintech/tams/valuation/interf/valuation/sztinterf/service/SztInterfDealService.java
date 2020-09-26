package com.joyintech.tams.valuation.interf.valuation.sztinterf.service;
/**
 * @author yanya
 * @create 2019-12-12 16:25
 */

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.sztinterf.constant.DictCodeSzt;
import com.joyintech.tams.valuation.common.sztinterf.constant.DictCodeSztList;
import com.joyintech.tams.valuation.common.sztinterf.constant.SztValConstant;
import com.joyintech.tams.valuation.common.sztinterf.dto.SztXmlMessageDto;
import com.joyintech.tams.valuation.common.sztinterf.service.SztInterfCommonService;
import com.joyintech.tams.valuation.common.sztinterf.utils.SztDecodeXml;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.dao.SztInterfDealMapper;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.dto.SztIntfLogModel;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.utils.ApplicationConfig;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.utils.SztConnector;
import com.joyintech.tams.valuation.interf.valuation.sztinterf.utils.SztMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SztInterfDealService
 * @Description: 深圳通接口处理service
 * @Author yanya
 * @Date 2019/12/12
 * @Version V1.0
 **/
@Service
public class SztInterfDealService {

    @Autowired
    SztInterfCommonService sztInterfCommonService;

//    @Autowired
//    private InterfCommonService interfCommonService;

    @Autowired
    SztInterfDealMapper sztInterfDealMapper;
//    /**
//     * 接口处理service执行方法execute
//     */
//    public void execute(Map<String, Object> params) {
//
//    }

    /**
     * 执行深圳通对账接口并接受返回信息
     * @param prodIds
     * @param interfType
     * @param cDate
     * @param detailFlag
     */
    public void callSztInterf(String prodIds, String interfType, String cDate, String detailFlag) throws Exception {
        //定义 		List<String> listProdId = prodIds.split(",")
        List<String> listProdId = StringUtil.splitToList(GlobalConstant.COMMA, prodIds);
        //1、调用工具类生成连接器，并初始化连接
        SztConnector connector = ApplicationConfig.connectorInit();
        connector.init();
        //2、初始化报文，根据产品代码以及日期生成对账的估值表报文信息
        SztMessage msg = ApplicationConfig.msgInit();
        msg.setId("");
        //循环产品
        listProdId.forEach(prodId -> {
            //2.1 删除历史记录
            sztInterfDealMapper.deleteInterfLog(prodId, interfType, cDate);
            //定义变量.报文信息Dto
            SztXmlMessageDto sztXmlMessageDto = new SztXmlMessageDto();
            if(DictCodeListExt1.深圳通接口类型_科目信息表.getValue().equals(interfType)) {
                //变量.报文信息Dto = 调用深圳通接口共通方法【深圳通科目信息表报文生成】
                sztXmlMessageDto = sztInterfCommonService.createSubjectInfoDocument(prodId, cDate, detailFlag);
            }
            else if(DictCodeListExt1.深圳通接口类型_估值表.getValue().equals(interfType)) {
                //变量.报文信息Dto = 调用深圳通接口共通方法【深圳通估值表报文生成】
                sztXmlMessageDto = sztInterfCommonService.createBookValuationDocument(prodId, cDate);
            }
            else if(DictCodeListExt1.深圳通接口类型_余额表.getValue().equals(interfType)) {
                //变量.报文信息Dto = 调用深圳通接口共通方法【深圳通余额表报文生成】
                sztXmlMessageDto = sztInterfCommonService.createSubjectBalalceDocument(prodId, cDate);
            }
            //调用深圳通加密工具类加压加密报文体
            String body = SztDecodeXml.encodeZip(sztXmlMessageDto.getResp());
            //"<?xml version=\"1.0\" encoding=\"GBK\"?>"+"<IN>"+body+"</IN>"
            msg.setBody(SztValConstant.深圳通_接口文件头XML + SztValConstant.深圳通_接口文件头开始字段 + body + SztValConstant.深圳通_接口文件头结尾字段);
            //2.2 发送报文
            //发送报文并接收即时返回值
            //String send = "0";//connector.sendMessage(msg,null);
            String send = connector.sendMessage(msg,null);
            //2.3 接收报文
            SztIntfLogModel sztIntfLogModel = new SztIntfLogModel();
            /** modify by yanya on 20200311 增加深圳通返回结果的处理，针对不同的行，处理的方式可能不一样**/
            if(DictCodeSztList.深圳通接口发送结果_成功.equals(send)) {
                sztIntfLogModel.setXmlStatus(DictCodeListExt1.深圳通报文状态_已发送.getValue());
                if(!DictCodeListExt1.深圳通接口类型_科目信息表.getValue().equals(interfType)){
                    //sztIntfLogModel.对账状态 = 数据字典.深圳通对账状态.对账中
                    sztIntfLogModel.setCheckStatus(DictCodeListExt1.深圳通对账状态_对账中.getValue());//对账中
                }
            }
            else {
                sztIntfLogModel.setXmlStatus(DictCodeListExt1.深圳通报文状态_发送失败.getValue());
                if(!DictCodeListExt1.深圳通接口类型_科目信息表.getValue().equals(interfType)){
                    //sztIntfLogModel.对账状态 = 数据字典.深圳通对账状态.对账中
                    sztIntfLogModel.setCheckStatus(DictCodeListExt1.深圳通对账状态_未对账.getValue());//对账中
                }
                DictCodeSztList dict = DictCodeSztList.from(DictCodeSzt.深圳通接口发送结果, send);
                if(dict != null) {
                    sztIntfLogModel.setReturnTxt(dict.getCodeName());
                }
            }
//            if(null != send) {
//                //对方已经接收到发送的报文,发送状态为发送成功
//                sztIntfLogModel.setXmlStatus(DictCodeListExt1.深圳通报文状态_已发送.getValue());
//                if(!DictCodeListExt1.深圳通接口类型_科目信息表.getValue().equals(interfType)){
//                    //sztIntfLogModel.对账状态 = 数据字典.深圳通对账状态.对账中
//                    sztIntfLogModel.setCheckStatus(DictCodeListExt1.深圳通对账状态_对账中.getValue());//对账中
//
//                }
//            }
//            else {
//                sztIntfLogModel.setXmlStatus(DictCodeListExt1.深圳通报文状态_发送失败.getValue());
//                if(!DictCodeListExt1.深圳通接口类型_科目信息表.getValue().equals(interfType)){
//                    //sztIntfLogModel.对账状态 = 数据字典.深圳通对账状态.对账中
//                    sztIntfLogModel.setCheckStatus(DictCodeListExt1.深圳通对账状态_未对账.getValue());//对账中
//
//                }
//            }
            //sztIntfLogModel.setSeqno(interfCommonService.getUUid());//主键
            sztIntfLogModel.setFinprodId(prodId);//金融产品代码
            sztIntfLogModel.setSerialno(sztXmlMessageDto.getSerialNo());//业务流水号
            sztIntfLogModel.setInterType(interfType);//接口类型
            sztIntfLogModel.setSendTime(new Date());//发送时间
            sztIntfLogModel.setCdate(cDate);//估值日期
            sztIntfLogModel.setCreateUser(SztValConstant.深圳通_接口创建人);//创建人
            sztIntfLogModel.setCreateTime(new Date());//创建日期
            sztIntfLogModel.setUpdateUser(SztValConstant.深圳通_接口更新人);//更新人
            sztIntfLogModel.setUpdateTime(new Date());//更新时间
            sztIntfLogModel.setDetailCount(sztXmlMessageDto.getSendNum());//明细条数
            //变量.报文结果 插入深圳通报文信息记录表
            sztInterfDealMapper.insertInterfLog(sztIntfLogModel);
        });
    }
}
