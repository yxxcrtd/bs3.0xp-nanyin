/*
 * 文件名：Rsk099Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2019年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.riskrdsinf.info.rsk099.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.joyintech.framework.base.BaseDictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrdsinf.cache.InterfCacheUtil;
import com.joyintech.service.riskrdsinf.common.service.RskAssetInfoService;
import com.joyintech.service.riskrdsinf.common.service.RskProdInfoService;
import com.joyintech.service.riskrdsinf.core.annotation.INF;
import com.joyintech.service.riskrdsinf.core.client.ClientData;
import com.joyintech.service.riskrdsinf.core.constant.InfDictCode;
import com.joyintech.service.riskrdsinf.core.constant.InfDictCodeList;
import com.joyintech.service.riskrdsinf.core.exception.InterfException;
import com.joyintech.service.riskrdsinf.core.service.InfBaseService;
import com.joyintech.service.riskrdsinf.core.util.InfMapValUtil;
import com.joyintech.service.riskrdsinf.info.rsk099.entity.InterfAssetInfo;
import com.joyintech.service.riskrdsinf.info.rsk099.entity.PrdCommonInfo;
import com.joyintech.service.riskrdsinf.info.rsk099.entity.Rsk099Entity;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.prodinfo.BussProdService;
import com.joyintech.tams.invest.common.prodinfo.dto.IPtlProd;
import com.joyintech.tams.invest.common.redisinit.dto.ImplCond;
import com.joyintech.tams.invest.common.redisinit.service.RiskRedisInitService;
import com.joyintech.tools.util.FileUtil;
import com.joyintech.tools.util.SFtpConnect;

/**
 * 文件通知接口
 * 
 * @author zhangrui1
 * @version 1.0 2019年9月11日
 * @see Rsk099Service
 * @since 1.0
 */
@Service
@INF("CallInfos")
public class Rsk099Service extends InfBaseService<Rsk099Entity> {

    /**
     * KEY:通知类型
     */
    public static final String KEY_CALLTYPE = "callType";

    /**
     * KEY:处理状态
     */
    public static final String KEY_DEALSTATUS = "dealStatus";

    /**
     * KEY:业务编号
     */
    public static final String KEY_INTERFID = "interfId";

    /**
     * KEY:文件名
     */
    public static final String KEY_FILENAME = "fileName";

    /**
     * KEY:文件名前缀
     */
    public static final String KEY_FILECODE = "fileCode";

    /**
     * KEY:文件批次号
     */
    public static final String KEY_FILENO = "fileNo";

    /**
     * KEY:文件路径
     */
    public static final String KEY_FILEPATH = "filePath";

    /**
     * KEY:消息列表
     */
    public static final String KEY_MESSAGE = "message";

    /**
     * KEY:产品基本信息
     */
    public static final String KEY_FILE_PROD = "fileProdInfo";

    /**
     * KEY:资产基本信息-其他
     */
    public static final String KEY_FILE_ASSET_OTHER = "fileAssetInfoOther";

    /**
     * KEY:资产基本信息-债券类
     */
    public static final String KEY_FILE_ASSET_BOND = "fileAssetInfoBond";

    /**
     * KEY:资产基本信息-计划类
     */
    public static final String KEY_FILE_ASSET_PLAN = "fileAssetInfoPlan";

    /**
     * 线程池
     */
    @Autowired
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    /**
     * 批量插入资产基本信息Sevice
     */
    @Autowired
    private RskAssetInfoService assetInfoSrc;

    /**
     * 产品基本信息批量登录Sevice
     */
    @Autowired
    private RskProdInfoService prodInfoSrc;

    /**
     * redis数据初始化
     */
    @Autowired
    private RiskRedisInitService<ImplCond> redisRriter;

    /**
     * 交易类金融产品表Mapper
     */
    @Autowired
    private FinTradeProductMapper trdProductMapper;
    
    /**
     * 金融产品分类表Mapper
     */
    @Autowired
    private FinProductTypeMapper prodTypeMapper;

    /**
     * 生成组合Service
     */
    @Autowired
    private BussProdService<IPtlProd> ptlService;

    /**
     * {@inheritDoc}
     */
    @Override
    protected Map<String, Map<String, Rsk099Entity>> handle(ClientData request,
                                                            ClientData response)
        throws InterfException {
        // 文件名前缀
        String fileCode = (String)request.getBodyField(KEY_FILECODE);
        // 文件路径
        String filePath = (String)request.getBodyField(KEY_FILEPATH);
        String fileName = (String)request.getBodyField(KEY_FILENAME);
        // 文件下载
        try {
            filePath = downloadFile(filePath, fileName);
        }
        catch (SftpException | JSchException e) {
            getLogger().error("文件下载失败", e);
            throw new InterfException("文件下载失败");
        }
        // 读取文件内容
        List<String> strList = getFileList(filePath + "/" + fileName);
        // 文件内容为空
        if (strList == null) {
            response.setBodyField(KEY_DEALSTATUS, InfDictCodeList.文件通知处理状态_失败.getValue());
            response.setBodyField(KEY_MESSAGE, "文件不存在");
            throw new InterfException("文件不存在");
        }
        if (strList.isEmpty()) {
            response.setBodyField(KEY_DEALSTATUS, InfDictCodeList.文件通知处理状态_失败.getValue());
            response.setBodyField(KEY_MESSAGE, "空文件");
            throw new InterfException("空文件");
        }
        // 文件大小
        String infoSizeStr = strList.get(0);
        int infoSize = Integer.parseInt(infoSizeStr);
        // 文件大小=0或者数据行为0
        if (infoSize == 0 || strList.size() <= 3) {
            response.setBodyField(KEY_DEALSTATUS, InfDictCodeList.文件通知处理状态_失败.getValue());
            response.setBodyField(KEY_MESSAGE, "基本信息内容为空");
            return null;
        }
        // 判断文件格式
        if (infoSize + 3 != strList.size()) {
            response.setBodyField(KEY_DEALSTATUS, InfDictCodeList.文件通知处理状态_失败.getValue());
            response.setBodyField(KEY_MESSAGE, "文件格式不正确");
            throw new InterfException("文件格式不正确");
        }
        // 解析文件入库
        parseData(fileCode, strList);
        // 结束设置结果
        writeResponse(request, response);
        return null;
    }

    public void parseData(final String fileCode, final List<String> strList) {
        threadPoolTaskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    // 取得产品基本信息文件Code
                    String fileProdInfo = InterfCacheUtil.getSysParam(KEY_FILE_PROD);
                    // 解析文件
                    if (StringUtil.equals(fileCode, fileProdInfo)) {
                        List<PrdCommonInfo> entityList = parseFileList(
                            strList.subList(2, strList.size() - 1), PrdCommonInfo.class, null);
                        // 更新DB
                        prodInfoSrc.insertBatchList(entityList);
                        // List to Array
                        PrdCommonInfo[] entityArr = new PrdCommonInfo[entityList.size()];
                        entityList.toArray(entityArr);
                        //根据组合代码查询改组合在风控系统中是否已经风险启动
                        List<PtlPortfolio> enabledList = ptlService.selectAllEnaled(entityArr);
                        // 先删除组合
                        ptlService.deleteBussProd(entityArr);
                        if(!StringUtil.isEmptyList(enabledList)){
                        	for(PtlPortfolio p:enabledList){
                            	for(PrdCommonInfo e:entityArr){
                            		if(StringUtil.equals(e.getPortfolioId(), p.getPortfolioId())){
                            			e.setRiskEnabled(p.getRiskEnabled());
                            		}
                            	}
                            }
                        }
                        
                        // 自动生成组合
                        ptlService.insertBussProd(entityArr);
                        ImplCond cond = new ImplCond();
                        cond.setDel(false);
                        List<String> idList = entityList.stream().map(item -> item.getPortfolioId())
                                .collect(Collectors.toList());
                        cond.setPortfIdArray(idList.toArray(new String[idList.size()]));
                        // 设置平台时间
                        cond.setPlateDate(DateUtil.parse(InterfCacheUtil.getSysDate()));
                        // 更新Redis
                        redisRriter.dealPortData(cond);
                        return;
                    }
                    // 取得资产基本信息文件Code
                    String fileAssetInfoBond = InterfCacheUtil.getSysParam(KEY_FILE_ASSET_BOND);
                    String fileAssetInfoPlan = InterfCacheUtil.getSysParam(KEY_FILE_ASSET_PLAN);
                    List<InterfAssetInfo> entityList = null;
                    if (StringUtil.equals(fileCode, fileAssetInfoBond)) {
                        entityList = parseFileList(strList.subList(2, strList.size() - 1),
                            InterfAssetInfo.class, (info, arr) -> {
                                // 债券-wind分类对应
                                info.setWindType1(arr[14]);
                                info.setWindType2(arr[15]);
                                info.setInvestType(
                                    getInvestType(info.getAssetType(), info.getAssetType2()));
                                info.setIssueAmt(StringUtil.isEmpty(arr[21]) ? BigDecimal.ZERO : new BigDecimal(arr[21]));
                            });
                    }
                    else if (StringUtil.equals(fileCode, fileAssetInfoPlan)) {
                        entityList = parseFileList(strList.subList(2, strList.size() - 1),
                            InterfAssetInfo.class,
                            // 计划类资产-投资性质对应
                            (info, arr) -> info.setInvestType(arr[14]));
                    }
                    else {
                        entityList = parseFileList(strList.subList(2, strList.size() - 1),
                            InterfAssetInfo.class, (info, arr) -> {
                                if (CommonUtil.isType(info.getAssetType(),
                                    InfDictCodeList.资产大类_项目类资产)) {
                                    info.setInvestType(
                                        getInvestType(info.getType1(), info.getType2()));
                                }
                                else if (CommonUtil.isType(info.getAssetType(),
                                        InfDictCodeList.资产大类_债券质押式回购)) {
                                	String finType = CommonUtil.isType(info.getAssetType2(), InfDictCodeList.交易方向_正回购) 
                                			? BussinessDictCodeListRds.金融产品类型_债券质押式正回购.getValue()
                                			: BussinessDictCodeListRds.金融产品类型_债券质押式逆回购.getValue();
                                	info.setFinprodType(finType);
                                    info.setInvestType(getInvestType(info.getAssetType(), info.getAssetType2()));
                                }
                                else if (CommonUtil.isType(info.getAssetType(),
                                        InfDictCodeList.资产大类_拆借)) {
                                	String finType = CommonUtil.isType(info.getAssetType2(), InfDictCodeList.交易方向_拆入) 
                                			? BussinessDictCodeListRds.金融产品类型_拆入.getValue()
                                			: BussinessDictCodeListRds.金融产品类型_拆出.getValue();
                                	info.setFinprodType(finType);
                                    info.setInvestType(getInvestType(info.getAssetType(), info.getAssetType2()));
                                }
                                else {
                                    info.setInvestType(
                                        getInvestType(info.getAssetType(), info.getAssetType2()));
                                }
                            });
                    }
                    if (!saveToTrdProduct(entityList)) {
                    	// 更新DB
                        assetInfoSrc.insertBatchList(entityList);
                    }
                    ImplCond cond = new ImplCond();
                    cond.setDel(false);
                    List<String> idList = entityList.stream().map(item -> item.getAssetCode())
                            .collect(Collectors.toList());
                    cond.setAssetIdArray(idList.toArray(new String[idList.size()]));
                    // 设置平台时间
                    cond.setPlateDate(DateUtil.parse(InterfCacheUtil.getSysDate()));
                    // 更新Redis
                    redisRriter.dealAssetData(cond);
                    // 更新资产索引
                    cond = new ImplCond();
                    List<String> mkList = new ArrayList<>();
                    entityList.forEach(item->{
                    	String marketCode = item.getMarketCode();
                    	String tradePlc = item.getTradePlace();
                    	if (marketCode != null && StringUtil.isNotEmpty(marketCode.trim())
                    			&& tradePlc != null && StringUtil.isNotEmpty(tradePlc.trim())) {
                    		mkList.add(marketCode + GlobalConstant.COLON + tradePlc);
                    	}
                    });
                    if (!StringUtil.isEmptyList(mkList)) {
                    	cond.setParamCodeArray(mkList.toArray(new String[mkList.size()]));
                        redisRriter.dealAssetIndex(cond);
                    }
                }
                catch (InterfException e) {
                    getLogger().error("登录异常", e);
                }
            }
        });
    }

    /**
     * 保存到交易类金融产品表
     * 
     * @param entityList 产品列表
     */
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Throwable.class)
    public boolean saveToTrdProduct(List<InterfAssetInfo> entityList){
    	if (!StringUtil.isEmptyList(entityList)) {
    		for(InterfAssetInfo entity : entityList){
    			if (!CommonUtil.isType(entity.getAssetType(),
                        InfDictCodeList.资产大类_债券质押式回购)) {
    				return false;
    			}
    			else {
    				FinTradeProduct product = new FinTradeProduct();
    				String user = "INF";
    				// 金融产品代码
    				product.setFinprodId(entity.getAssetCode());
    				// 分支序号
    				product.setBranch(0L);
    				// 金融产品类型
    				product.setFinprodType(entity.getFinprodType());
    				// 起息日
    				product.setVdate(entity.getVdate());
    				// 到期日
    				product.setMdate(entity.getMdate());
    				// 交易对手
    				product.setCounterId(entity.getIssuer());
    				// 交易场所
    				product.setTradeMarket(entity.getTradePlace());
    				// 交易日历代码
    				product.setCalendarId(entity.getCalendarCode());
    				// 创建者
    				product.setCreateUser(user);
    				// 更新者
    				product.setUpdateUser(user);
    				
    				FinProductType prodType = new FinProductType();
    				// 分支序号
    				prodType.setBranch(0L);
    				// 金融产品代码
    				prodType.setFinprodId(entity.getAssetCode());
    				// 系统资产大类
    				prodType.setType6(entity.getAssetType());
    				// 系统资产细类
    				prodType.setType7(entity.getAssetType2());
    				// wind一级分类
    				prodType.setType4(entity.getWindType1());
    				// wind二级分类
    				prodType.setType5(entity.getWindType2());
    				// 创建者
    				prodType.setCreateUser(user);
    				// 更新者
    				prodType.setUpdateUser(user);
    				// 删除存在的资产数据
    				trdProductMapper.deleteByPrimaryKey(product);
    				// 登录资产数据
    				trdProductMapper.insertSelective(product);
    				// 删除存在的资产分类数据
    				prodTypeMapper.deleteByPrimaryKey(prodType);
    				// 登录资产分类数据
    				prodTypeMapper.insertSelective(prodType);
    				return true;
    			}
    		}
    	}
    	return false;
    }

    /**
     * 获取投资性质
     * 
     * @param type1 系统资产大类
     * @param type2 系统资产细类
     * @return 投资性质
     * @see
     * @since 1.0
     */
    protected String getInvestType(String type1, String type2) {
        StringBuilder builder = new StringBuilder(GlobalConstant.OPEN_MIDDLE);
        builder.append(type1);
        if (!StringUtil.isEmpty(type2)) {
            builder.append(GlobalConstant.COMMA).append(type2);
        }
        builder.append(GlobalConstant.CLOSE_MIDDLE);
        if (isInvestType(InfDictCodeList.投资性质_权益类, builder.toString())) {
            return InfDictCodeList.投资性质_权益类.getValue();
        }
        else if (isInvestType(InfDictCodeList.投资性质_混合类, builder.toString())) {
            return InfDictCodeList.投资性质_混合类.getValue();
        }
        else if (isInvestType(InfDictCodeList.投资性质_商品及金融衍生品类, builder.toString())) {
            return InfDictCodeList.投资性质_商品及金融衍生品类.getValue();
        }
        else if (isInvestType(null, builder.toString())) {
            return "";
        }
        return InfDictCodeList.投资性质_固定收益类.getValue();
    }

    private boolean isInvestType(BaseDictCodeList invest, String cond) {
        String head = InfDictCode.投资性质.getValue();
        String value = InterfCacheUtil.getSysParam(
            head + GlobalConstant.UNDER_LINE + (invest == null ? "" : invest.getValue()));
        return StringUtil.contains(value, cond);
    }

    public List<String> getFileList(String file)
        throws InterfException {
        // 文件编码格式
        String encoding = InterfCacheUtil.getSysParam(BussinessConstantRds.RSK_FTP_ENCODE);
        try {
            return FileUtil.getFile(file, encoding);
        }
        catch (IOException e) {
            throw new InterfException(e);
        }
    }

    private String downloadFile(String ftpPath, String fileName)
        throws SftpException, JSchException {
        // 文件下载本地路径
        String localPath = InterfCacheUtil.getSysParam(BussinessConstantRds.RSK_LOCAL_PATH);
        // 文件下载服务器主机
        String host = InterfCacheUtil.getSysParam(BussinessConstantRds.RSK_FTP_HOST);
        // 文件下载服务器端口
        String ptStr = InterfCacheUtil.getSysParam(BussinessConstantRds.RSK_FTP_PORT);
        int port = Integer.parseInt(ptStr);
        // 文件下载服务器登录用户
        String username = InterfCacheUtil.getSysParam(BussinessConstantRds.RSK_FTP_USER);
        // 文件下载服务器登录密码
        String password = InterfCacheUtil.getSysParam(BussinessConstantRds.RSK_FTP_PASS);
        // 文件下载服务器登录
        SFtpConnect conn = SFtpConnect.build(host, port, username, password);
        // 文件下载
        conn.download(ftpPath + fileName, localPath);
        // 返回文件下载路径
        return localPath;
    }

    private <T> List<T> parseFileList(List<String> strList, Class<T> clz, Helper<T> helper)
        throws InterfException {
        List<T> tList = new ArrayList<>();
        String term = new String(new byte[] {BussinessConstantRds.B_TERM});
        if (helper != null) {
            for (String str : strList) {
                str = str + term + "&";
                String[] content = str.split(term);
                T t = InfMapValUtil.parseArrToEntity(content, clz);
                helper.doHelper(t, content);
                tList.add(t);
            }
        }
        else {
            for (String str : strList) {
                str = str + term + "&";
                String[] content = str.split(term);
                tList.add(InfMapValUtil.parseArrToEntity(content, clz));
            }
        }
        return tList;
    }

    private void writeResponse(ClientData request, ClientData response) {
        // 通知类型
        response.setBodyField(KEY_CALLTYPE, InfDictCodeList.文件通知类型_反馈处理状态.getValue());
        // 业务编号
        response.setBodyField(KEY_INTERFID, request.getBodyField(KEY_INTERFID));
        // 文件名
        response.setBodyField(KEY_FILENAME, request.getBodyField(KEY_FILENAME));
        // 文件名前缀
        response.setBodyField(KEY_FILECODE, request.getBodyField(KEY_FILECODE));
        // 文件批次号
        response.setBodyField(KEY_FILENO, request.getBodyField(KEY_FILENO));
        // 文件路径
        response.setBodyField(KEY_FILEPATH, request.getBodyField(KEY_FILEPATH));
        // 文件通知处理状态
        response.setBodyField(KEY_DEALSTATUS, InfDictCodeList.文件通知处理状态_成功.getValue());
    }

    interface Helper<T> {

        void doHelper(T t, String[] content);
    }

    @Override
	protected boolean isCalcAmount() {
		return false;
	}

    /**
     * {@inheritDoc}
     */
    @Override
    protected boolean checkWithCond(Rsk099Entity obj, ClientData response) {
        return false;
    }
}
