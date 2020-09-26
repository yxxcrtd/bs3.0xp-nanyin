/*
 * 文件名：DisRepAssetInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：信息披露系统V1.0
 * 修改人：ZhangYangFei
 * 修改时间：2019年5月7日
 * 修改内容：新建
 * 系统名称：信息披露系统V1.0
 */
 
package com.joyintech.tams.svreport.interf.dis.assetinfo.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.FileImpService;
import com.joyintech.tams.common.InterfDefine;
import com.joyintech.tams.common.InterfFile;
import com.joyintech.tams.common.base.entity.ChbInterfConfigDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.util.ChbInterfUtil;
import com.joyintech.tams.svreport.interf.dis.assetinfo.dao.DisRepAssetInfoMapper;
import com.joyintech.tams.svreport.interf.dis.assetinfo.dto.DisRepAssetInfo;

/**
 * 资产要素信息接口
 *      解析资产要素信息接口数据，存入REP_ASSET_INFO
 *      
 * @author ZhangYangFei
 * @version 1.0 2019年5月7日
 * @see DisRepAssetInfoService
 * @since 1.0
 */
@Service
public class DisRepAssetInfoService extends FileImpService {
    /**
     * logger
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 数据批量一次性insert条数
     */
    @Value("${application.reportInsertCount}")
    public int reportInsertCount;
    
    /**
     * 长sql最大一次插入或者更新条数
     */
    @Value("${application.reportInsertLongCount}")
    public int reportInsertLongCount;
    
    /**
     * 业务主键
     */
    private static final String BUSINESSKEY_DIS_ASSET_INFO = "ASSET_CODE";
    
    @Autowired
    private  DisRepAssetInfoMapper disRepAssetInfoMapper;
    
    private Map<String, String> assetTypeMap;
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String saveData(List<Map<String, String>> listData, String saveFlag, String dateSource) {
        if (StringUtil.isEmptyList(listData)) {
            logger.info("saveData方法参数listData为空。");
            return null;
        }
        // 解析文件数据到dto
        DisRepAssetInfo dto = null;
        List<DisRepAssetInfo> dtoList = new ArrayList<DisRepAssetInfo>();
        for(Map<String, String> data : listData){
            //将map转换为dto
        	try{
        		  dto = transMap2Dto(data);
                  dtoList.add(dto);
        	}
        	catch(Exception e){
				logger.error("解析数据错误：",e.getMessage(),e);
           		return  "fail";
			}
        }
        //存表
        int success = this.mergeRepAssetInfoByPart(dtoList);
        
        logger.info("saveData方法成功存入数据到REP_ASSET_INFO：" + success + "条");
        return "success";
    }
    
    /**
     * 将map转换为dto
     * 
     * @param data
     * @return
     * @see
     * @since 1.0
     */
    private DisRepAssetInfo transMap2Dto(Map<String, String> data) {
        DisRepAssetInfo dto = new DisRepAssetInfo();
        dto.setAssetId(data.get("ASSET_CODE"));//资产代码
        dto.setMarketId(data.get(""));//市场代码
        dto.setAssetName(data.get("ASSET_NAME"));//资产名称
        dto.setIssuer(data.get(""));//发行人
        dto.setIdate(DateUtil.parse(data.get("")));//发行日
        dto.setIssuePrice(null);//发行价
        dto.setIssueAmt(null);//发行规模
        dto.setCcy(data.get("CCY"));//币种
        dto.setVdate(DateUtil.parse(data.get("VDATE")));//起息日
        dto.setMdate(DateUtil.parse(data.get("MDATE")));//到期日
        dto.setActmdate(DateUtil.parse(data.get("")));//实际到期日
        dto.setTradeMarket(data.get(""));//交易场所
        dto.setRegistOrg(data.get("REGIST_ORG"));//登记托管机构
        //dto.setAssetType(null);//废弃字段
        dto.setFixAncier(data.get("FINANCIE"));//融资人
        dto.setAssetTypeOne(null);//资产一级分类//暂空
        dto.setAssetTypeTwo(data.get("ASSET_TYPE"));//资产二级分类:取资产/负债类别
        dto.setAssetTypeThree(data.get("ASSET_TYPE2"));//资产三级分类：取资产/负债二级类别
        //dto.setAssetBusiType(null);//业务类型、废弃字段
        //dto.setIsStandAsset(null);//是否标准资产、废弃字段
        //dto.setRateType(null);//废弃字段
        dto.setAssetTypeDis(transAssetTypeDis(dto));//信披报表一级类型
        
        // 增加信批资产类型的赋值 zhangyf 2019625 start
        if(null == assetTypeMap){
        	assetTypeMap = getAssetTypeMap();
        }
        dto.setDisAssetTypeone(assetTypeMap.get(data.get("ORG_ASSET_TYPE1")));//资产一级分类
        dto.setDisAssetTypeytwo(assetTypeMap.get(data.get("ORG_ASSET_TYPE1")+"|"+data.get("ORG_ASSET_TYPE2")));//资产二级分类
        dto.setDisAssetTypethree(assetTypeMap.get(data.get("ORG_ASSET_TYPE1")+"|"+data.get("ORG_ASSET_TYPE2")+"|"+data.get("ORG_ASSET_TYPE3")));//资产三级分类
        // 增加信批资产类型的赋值 zhangyf 2019625 end
        
        dto.setInputType("");//导入方法
        dto.setInputUser("");//导入用户
        dto.setAdapterId("0");//适配器ID
        dto.setStatus(data.get("STATUS"));//状态
        dto.setCreateUser("interf");
        dto.setCreateDept("");
        dto.setUpdateUser("interf");
        try {
            if(StringUtil.isNotEmpty(data.get("CREATE_TIME"))){
                dto.setCreateTime(DateUtil.parseDate(data.get("CREATE_TIME"),new String[] {DateUtil.DEFAULT_DATETIME_FORMAT_ODS}));
            }else{
                dto.setCreateTime(new Date());
            }
            if(StringUtil.isNotEmpty(data.get("UPDATE_TIME"))){
                dto.setUpdateTime(DateUtil.parseDate(data.get("UPDATE_TIME"),new String[] {DateUtil.DEFAULT_DATETIME_FORMAT_ODS}));
            }else{
                dto.setUpdateTime(new Date());
            }
        } catch (ParseException e) {
            logger.error("transMap2Dto方法转换CreateTime或UpdateTime异常");
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return dto;
    }
    
    /**
     * 分批存表
     * 
     * @param dtoList
     * @return
     * @see
     * @since 1.0
     */
    private int mergeRepAssetInfoByPart(List<DisRepAssetInfo> dtoList) {
        //一次性最大插入条数
        int maxDataCountOneTime = reportInsertLongCount;
        //插入list大小
        int dataCount = dtoList.size();
        int mergeCount = 0;
        //若需要插入的数据比最大插入的数据少，则直接全部插入
        if (dataCount <= maxDataCountOneTime) {
            mergeCount = disRepAssetInfoMapper.mergeRepAssetInfo(dtoList);
            return mergeCount;
        }
        
        List<DisRepAssetInfo> newDataList = new ArrayList<DisRepAssetInfo>();
        newDataList.addAll(dtoList);
        
        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
            mergeCount += disRepAssetInfoMapper.mergeRepAssetInfo(newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            mergeCount += disRepAssetInfoMapper.mergeRepAssetInfo(newDataList);
        }
        return mergeCount;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String importDB(List<Map<String, String>> listData, InterfFile interfFile) {
        if(CollectionUtils.isEmpty(listData)){
            return "success";
        }
        // 数据检查
        checkData(listData, interfFile.getFilename(), InterfDefine.资产要素信息接口数据.getCode(),
            InterfDefine.数据检查类型_资产要素信息接口.getCode(), BUSINESSKEY_DIS_ASSET_INFO,
            BUSINESSKEY_DIS_ASSET_INFO);
        ChbInterfConfigDto interfConfig = ChbInterfUtil.queryInterfConfigByInterfid(
            InterfDefine.资产要素信息接口数据.getCode());
        if (interfConfig == null) {
            logger.info("CHB_INTERF_CONFIG表中无配置项！");
            return "fail";
        }
        String saveFlag = interfConfig.getContentType();
        return saveData(listData, saveFlag, interfFile.getDataSource());
    }
    
    /**
     * 设定资产类型-信批
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    private String transAssetTypeDis(DisRepAssetInfo dto) {
        String assettype3 = dto.getAssetTypeThree();
        if (!StringUtil.isNotEmpty(assettype3)) {
            return null;
        }
        String assetTypeDis = null;
        switch (assettype3) {
            case "1002":
            case "1003":
            case "1004":
            case "1005":
                assetTypeDis = "01";// 货币市场工具
                break;
            case "1501":
            case "1101":
            case "1110":
            case "1109":
            case "1102":
            case "1111":
            case "1112":
            case "1113":
            case "1114":
            case "1115":
            case "1117":
            case "2101":
            case "2501":
            case "2502":
                assetTypeDis = "02";// 债券
                break;
            case "1116":
                assetTypeDis = "03";// 资产支持证券
                break;
            case "1107":
            case "1504":
                assetTypeDis = "04";// 拆出资产
                break;
            case "1108":
            case "1506":
                assetTypeDis = "06";// 买入返售金融资产
                break;
            case "1901":
                assetTypeDis = "05";// 拆入资金
                break;
            case "1902":
                assetTypeDis = "07";// 买入返售金融负债
                break;
            case "1202":
            case "1203":
            case "1204":
            case "1205":
            case "1206":
            case "1207":
            case "1208":
            case "1209":
            case "2201":
            case "1201":
            case "1210":
            case "1503":
            case "1701":
            case "1702":
            case "1703":
            case "1704":
            case "1705":
            case "1706":
            case "1299":
            case "1399":
            case "1599":
            case "2801":
            case "2802":
            case "2803":
            case "2804":
            case "2805":
            case "2806":
            case "2899":
                assetTypeDis = "08";// 计划类资产
                break;
            case "1301":
            case "1302":
            case "1305":
            case "1502":
                assetTypeDis = "09";// 权益类投资（股权投资）
                break;
            case "1401":
            case "1402":
            case "1403":
            case "1404":
            case "1405":
            case "1406":
            case "1499":
            case "1601":
            case "1602":
            case "1699":
                assetTypeDis = "10";// 商品及金融衍生品投资(资产或负债）
                break;
            case "2299":
            case "2301":
            case "2302":
            case "2303":
            case "2304":
            case "2305":
            case "1603":
                assetTypeDis = "12";// 其他资产
                break;
            case "2000":
                assetTypeDis = "13";// 其他类负债
                break;
            case "1505":
            case "1507":
            case "1508":
            case "1509":
            case "1106":
            case "2401":
            case "1303":
            case "2403":
            case "2402":
            case "2499":
            case "2601":
            case "2602":
            case "2604":
            case "2605":
            case "2606":
            case "2607":
            case "2610":
            case "2611":
            case "2612":
            case "2617":
            case "2613":
            case "2614":
            case "2616":
            case "2618":
            case "2619":
            case "2620":
            case "2699":
                // ASSET_TYPE2 not in ('1502')
                if (!StringUtil.equals("1502", assettype3)) {
                    assetTypeDis = "11";// 基金投资
                    break;
                }
            default:
                break;
        }
        return assetTypeDis;
    }
    
    /**
     * 设定资产类型映射MAP（key=接口.资产分类拼接，竖线隔开，value= 信批资产类型）
     * 
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
	private Map<String, String> getAssetTypeMap() {
		Map<String, String> assetTypeMap = new HashMap<String, String>();
		//信披资产一级类型，key = 接口.资产一级分类
		assetTypeMap.put("F08", "DIS01");// 拆借
		assetTypeMap.put("F07", "DIS02");// 质押式回购
		assetTypeMap.put("F10", "DIS03");// 买断式回购
		assetTypeMap.put("F05", "DIS04");// 存款类资产
		assetTypeMap.put("F02", "DIS05");// 基金类资产
		assetTypeMap.put("F04", "DIS06");// 计划类（净值型）
		assetTypeMap.put("F03", "DIS07");// 计划类（利率型）
		assetTypeMap.put("F09", "DIS08");// 计划类（货币型）
		assetTypeMap.put("F06", "DIS09");// 项目类资产
		assetTypeMap.put("F01", "DIS10");// 债券
		//assetTypeMap.put("", "DIS11");// 权益类投资
		
		//信披资产二级类型，key = 接口.资产一级分类|接口.资产二级分类
		assetTypeMap.put("F08|01","DIS0101");// 拆入
		assetTypeMap.put("F08|02","DIS0102");// 拆出
		assetTypeMap.put("F07|03","DIS0201");// 质押式正回购
		assetTypeMap.put("F07|04","DIS0202");// 质押式逆回购
		assetTypeMap.put("F10|03","DIS0301");// 买断式正回购
		assetTypeMap.put("F10|04","DIS0302");// 买断式逆回购
		assetTypeMap.put("F05|D01","DIS0401");// 同业借款
		assetTypeMap.put("F05|D02","DIS0402");// 存放同业
		assetTypeMap.put("F02|F01","DIS0501");// 货币基金
		assetTypeMap.put("F02|F02","DIS0502");// 非货币基金
		assetTypeMap.put("F04|N01","DIS0601");// 券商资产管理计划
		assetTypeMap.put("F04|N02","DIS0602");// 信托产品
		assetTypeMap.put("F04|N03","DIS0603");// 基金资产管理计划
		assetTypeMap.put("F04|N04","DIS0604");// 保险资产管理计划
		assetTypeMap.put("F04|N05","DIS0605");// 期货资产管理计划
		assetTypeMap.put("F04|N06","DIS0606");// 其他资产管理计划
		assetTypeMap.put("F03|N01","DIS0701");// 券商资产管理计划
		assetTypeMap.put("F03|N02","DIS0702");// 信托产品
		assetTypeMap.put("F03|N04","DIS0703");// 保险资产管理计划
		assetTypeMap.put("F09|N01","DIS0801");// 券商资产管理计划
		assetTypeMap.put("F09|N02","DIS0802");// 信托产品
		assetTypeMap.put("F09|N03","DIS0803");// 基金资产管理计划
		assetTypeMap.put("F09|N04","DIS0804");// 保险资产管理计划
		assetTypeMap.put("F09|N05","DIS0805");// 期货资产管理计划
		assetTypeMap.put("F09|N06","DIS0806");// 其他资产管理计划
		assetTypeMap.put("F06|P01","DIS0901");// 非标债权
		assetTypeMap.put("F06|P02","DIS0902");// 结构化证券
		assetTypeMap.put("F06|P03","DIS0903");// 其他权益类投资
		assetTypeMap.put("F06|P04","DIS0904");// 其他产业投资基金
		assetTypeMap.put("F06|P05","DIS0905");// 股票定向增发投资
		assetTypeMap.put("F06|P06","DIS0906");// 基础设施基金
		assetTypeMap.put("F06|P07","DIS0907");// 券商债权收益权投资
		assetTypeMap.put("F06|P08","DIS0908");// 信贷资产流转项目
		assetTypeMap.put("F06|P09","DIS0909");// 券商收益凭证
		assetTypeMap.put("F06|P10","DIS0910");// 股票质押式回购
		assetTypeMap.put("F06|P11","DIS0911");// 理财直接融资工具
		//assetTypeMap.put("","DIS0912");// 委外投资
		assetTypeMap.put("F01|B01","DIS1001");// 国债
		assetTypeMap.put("F01|B02","DIS1002");// 地方政府债
		assetTypeMap.put("F01|B03","DIS1003");// 央行票据
		assetTypeMap.put("F01|B05","DIS1004");// 金融债
		assetTypeMap.put("F01|B06","DIS1005");// 企业债
		assetTypeMap.put("F01|B07","DIS1006");// 公司债
		assetTypeMap.put("F01|B08","DIS1007");// 中期票据
		assetTypeMap.put("F01|B09","DIS1008");// 短期融资券
		assetTypeMap.put("F01|B10","DIS1009");// 定向工具
		assetTypeMap.put("F01|B11","DIS1010");// 国际机构债
		assetTypeMap.put("F01|B12","DIS1011");// 政府支持机构债
		assetTypeMap.put("F01|B04","DIS1012");// 同业存单
		assetTypeMap.put("F01|B13","DIS1013");// 资产支持证券
		assetTypeMap.put("F01|B14","DIS1014");// 可转债
		assetTypeMap.put("F01|B15","DIS1015");// 可交换债
		assetTypeMap.put("F01|B16","DIS1016");// 可分离转债存债
		assetTypeMap.put("F01|B17","DIS1017");// 优先股
		// assetTypeMap.put("","DIS1101");// 股权
		// assetTypeMap.put("","DIS1102");// 股票(一级市场)
		// assetTypeMap.put("","DIS1103");// 股票(二级市场)
		
		//信披资产三级类型，key = 接口.资产一级分类|接口.资产二级分类|接口.资产三级分类
		assetTypeMap.put("F02|F02|01","DIS050201");//债券基金
		assetTypeMap.put("F02|F02|03","DIS050203");//股票基金
		assetTypeMap.put("F02|F02|04","DIS050204");//混合基金
		assetTypeMap.put("F02|F02|06","DIS050206");//其他公募基金
		assetTypeMap.put("F02|F02|05","DIS050205");//基金中基金
		assetTypeMap.put("F06|P01|1201","DIS090101");//票据类
		assetTypeMap.put("F06|P01|1210","DIS090110");//信用证
		assetTypeMap.put("F06|P01|1202","DIS090102");//信托贷款
		assetTypeMap.put("F06|P01|1203","DIS090103");//委托贷款
		assetTypeMap.put("F06|P01|1204","DIS090104");//信贷资产转让
		assetTypeMap.put("F06|P01|1205","DIS090105");//收/受益权
		assetTypeMap.put("F06|P01|1206","DIS090106");//委托债权
		assetTypeMap.put("F06|P01|1207","DIS090107");//应收账款
		assetTypeMap.put("F06|P01|1208","DIS090108");//带回购条款的股权性融资
		assetTypeMap.put("F06|P01|1209","DIS090109");//债权融资类产品
		assetTypeMap.put("F06|P01|1299","DIS090199");//其他非标准化债权类投资
		assetTypeMap.put("F01|B05|01","DIS100401");//政策银行债
		assetTypeMap.put("F01|B05|02","DIS100402");//商业银行债
		assetTypeMap.put("F01|B05|03","DIS100403");//商业银行次级债券
		assetTypeMap.put("F01|B05|04","DIS100404");//保险公司债
		assetTypeMap.put("F01|B05|05","DIS100405");//证券公司债
		assetTypeMap.put("F01|B05|06","DIS100406");//证券公司短期融资券
		assetTypeMap.put("F01|B05|07","DIS100407");//其他金融机构债
		assetTypeMap.put("F01|B06|08","DIS100501");//一般企业债
		assetTypeMap.put("F01|B06|09","DIS100502");//集合企业债
		assetTypeMap.put("F01|B07|10","DIS100601");//一般公司债
		assetTypeMap.put("F01|B07|11","DIS100602");//私募债
		assetTypeMap.put("F01|B08|12","DIS100701");//一般中期票据
		assetTypeMap.put("F01|B08|13","DIS100702");//集合票据
		assetTypeMap.put("F01|B09|14","DIS100801");//超短期融资债券
		assetTypeMap.put("F01|B09|15","DIS100802");//一般短期融资券
		assetTypeMap.put("F01|B13|16","DIS101301");//证监会主管ABS
		assetTypeMap.put("F01|B13|17","DIS101302");//银监会主管ABS
		assetTypeMap.put("F01|B13|18","DIS101303");//交易商协会ABN

		return assetTypeMap;
	}
	
}
