package com.joyintech.invest.interf.business;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;

import com.joyintech.invest.interf.common.core.client.ClientData;

import com.joyintech.invest.interf.common.core.entity.InterfDataDictInfo;
import com.joyintech.invest.interf.common.core.service.InvestInterfCommonService;
import com.joyintech.invest.interf.common.core.service.InvestInterfDataDictService;
import com.joyintech.invest.interf.common.core.utility.WebserviceIntefUtil;

/**
 * 投管接口业务共通
 * 
 * @program: 2.1.2
 * @description: 业务相关
 * @author: 强成西
 * @create: 2020-02-08 11:41
 **/
public class BusinessUtil {

    /**
     * 发起方系统代码
     */
    private static String SYSTEM_CODE_SEND = "INV";

    /**
     * 接收方系统代码
     */
    private static String SYSTEM_CODE_REC = "INV";

    /**
     * 接口SEQUENCE
     */
    public static String INVINTERF_SEQUENCE = "INVINTERF_SEQUENCE";


    /**
     * 根据金融产品类型和交易方向转换成接口的交易类型
     * 
     * @param finprodType2 金融产品类型
     * @param ps 交易方向
     * @param excType 其他区分类型（EXC_PS）
     * @param isInnerTrade 是否内部交易
     * @return 接口交易二级类型
     */
    public static String getInferfTradeType(Object finprodType2,Object ps,Object excType, Object isInnerTrade) {

        String rstMsg="";//根据key返回对应的值
        try {
            //读取配置文件
            Properties prop = WebserviceIntefUtil.getProperties("InterfTradeTypeChange.properties");
            rstMsg = prop.getProperty((StringUtil.isNotEmpty(finprodType2)? String.valueOf(finprodType2):"") +( StringUtil.isNotEmpty(ps)? "."+ps:"")+( StringUtil.isNotEmpty(excType)? "."+excType:"") + (StringUtil.isNotEmpty(
                    isInnerTrade) ? "."+isInnerTrade:""));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  rstMsg;
    }

    /**
     * check是否是日期格式
     * 
     * @param param 参数
     * @return check结果
     */
    public static boolean isErrorDate(Object param) {
        if (StringUtil.isNotEmpty(param)) {
            try {
                Date result = DateUtil.parse((String)param);
                if (result == null) {
                    return true;
                }
            }
            catch (RuntimeException e) {
                return true;
            }
        }
        return false;
    }

    /**
     * check是否是数值
     * 
     * @param param 金融产品类型
     * @return check结果
     */
    public static boolean isNotNumeric(Object param) {
        if (StringUtil.isNotEmpty(param)) {
            try {
                Double.parseDouble((String)param);
            }
            catch (NumberFormatException e) {
                return true;
            }
        }
        return false;
    }

    /**
     * check是否正确的数据字典范围值
     * 
     * @param dictCode 数据字典类型
     * @param itemCode 参数
     * @return check结果
     */
    public static boolean isErrorDictCode(String dictCode, Object itemCode) {
        if (StringUtil.isNotEmpty(itemCode)) {
            InvestInterfDataDictService investInterfDataDictService = SpringContextUtil.getBean(InvestInterfDataDictService.class);
            List<InterfDataDictInfo> items = investInterfDataDictService.getAllDictCode();
            if (null == items || items.stream().filter(
                    item -> StringUtil.equals(item.getItmCode(), itemCode) && StringUtil.equals(item.getDctCode(), dictCode)).findFirst().orElse(
                    null) == null) {
                return true;
            }
        }
        return false;
    }

    /**
     * 输出接口消息头
     * 
     * @param request 参数对象
     * @param response 报文对象
     * @param infId 接口ID
     * @see
     * @since 1.0
     */
    public static void createSendInterfHeader(Map<String, Object> request, ClientData response,
                                              String infId) {
        // 消息头
        Map<String, Object> header = response.getHead();
        // 接口代码
        header.put("INF_ID", infId);
        // 发起方系统代码
        header.put("REQUEST_SYS", SYSTEM_CODE_SEND);
        // 发起方流水号【生成规则:系统代码+日期+seqno(9位)】
        //header.put("REQUEST_SEQNO", IDGenerator.generate());
        header.put("REQUEST_SEQNO",getSeqno(request));
        // 发起方系统日期
        header.put("REQUEST_DATE", DateUtil.format(new Date((Long)request.get("platDate")),
            DateUtil.DEFAULT_OTHER_FORMAT));
        // 发起方系统时间戳
        header.put("REQUEST_TIME",
            DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1));
    }

    /**
     * 输入接口消息头
     * 
     * @param request 参数对象
     * @param response 报文对象
     * @param infId 接口ID
     * @see
     * @since 1.0
     */
    public static void createRecInterfHeader(Map<String, Object> request, ClientData response,
                                             String infId) {
        // 消息头
        Map<String, Object> header = response.getHead();
        // 接口代码
        header.put("INF_ID", infId);
        // 发起方流水号
        header.put("REQUEST_SEQNO", request.get("REQUEST_SEQNO"));
        // 应答码 TODO
        // header.put("RESPONSE_INFO_ID", "");
        // 应答描述
        // 接收方系统代码
        header.put("RESPONSE_SYS", SYSTEM_CODE_REC);
        // 发起方系统日期
        header.put("RESPONSE_DATE", DateUtil.format(new Date(), DateUtil.DEFAULT_OTHER_FORMAT));
        // 发起方系统时间戳
        header.put("RESPONSE_TIME",
            DateUtil.format(new Date(), DateUtil.DATETIME_FORMAT_WITH_SSS_YEAR1));
    }
    /**
     * 流水号获取
     *生成规则:系统代码+日期+seqno(9位,不足补0)
     * @see
     * @since 1.0
     */
    public static String getSeqno(Map<String, Object> request)
    {
        InvestInterfCommonService interfCommonService = SpringContextUtil.getBean(InvestInterfCommonService.class);
        String seqno=SYSTEM_CODE_SEND+DateUtil.format(new Date((Long)request.get("platDate")),
                DateUtil.DEFAULT_OTHER_FORMAT)+interfCommonService.getNextSequence();
        return seqno;
    }

}
