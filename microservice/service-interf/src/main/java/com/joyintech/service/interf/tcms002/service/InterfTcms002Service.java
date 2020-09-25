/*
 * 文件名：InterfTcms002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.tcms002.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dc.eai.data.CompositeData;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbSyshead;
import com.joyintech.service.interf.core.constant.EsbHeadEnum;
import com.joyintech.service.interf.core.constant.EsbServiceInfoList;
import com.joyintech.service.interf.core.constant.InterfConstant;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;
import com.joyintech.service.interf.core.service.ReceivedEsbService;
import com.joyintech.service.interf.core.util.InterfException;
import com.joyintech.service.interf.isms004.entity.InterfSMS004Entity;
import com.joyintech.service.interf.isms004.entity.InterfSubSysNoArray;
import com.joyintech.service.interf.loadfile.service.UpLoadFileService;
import com.joyintech.service.interf.tcms002.dao.InterfTcms002Mapper;
import com.joyintech.service.interf.tcms002.dto.BussFinProductFeeInfoDto;
import com.joyintech.service.interf.tcms002.dto.FinProductBuild002Dto;
import com.joyintech.service.interf.tcms002.entity.InFlowEntity;
import com.joyintech.service.interf.tcms002.entity.InterfTcms002InEntity;
import com.joyintech.service.interf.tcms002.entity.InterfTcms002OutEntity;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 理财产品信息查询Tcms002处理详细
 * 
 * @author changwenbin
 * @version 1.0 2018年11月12日
 * @see InterfTcms002Service
 * @since 1.0
 */
@Service
@EsbSyshead(value = EsbServiceInfoList.INF_TCMS002)
public class InterfTcms002Service extends ReceivedEsbService<InterfTcms002OutEntity, InterfTcms002InEntity> {

    /**
     * 查询模式1
     */
    private static final String SEARCHMODE = "1";

    /**
     * 子系统编号
     */
    private String subSysNo;

    /**
     * 理财产品信息查询TCMS002mapper
     */
    @Autowired
    private InterfTcms002Mapper interfTcms002Mapper;

    /**
     * 产品说明书上传Service
     */
    @Autowired
    private UpLoadFileService upLoadService;

    /**
     * {@inheritDoc}
     */
    @Override
	public InterfTcms002OutEntity selectResponseData(InterfTcms002InEntity entity) throws Exception {
        // 产品状态
        String status = null;
        // 设置参数 金融产品类型_理财产品code
        entity.setLcprod(DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // 设置参数 现金流二级类型_利息code
        entity.setXjint(DictCodeListExt1.现金流二级类型_利息.getValue());
        // 设置参数 是否_是 code
        entity.setYes(DictCodeList.是否_是.getValue());
        // 设置参数 排期发行方式_单一 code
        entity.setDanyi(DictCodeList.排期发行方式_单一.getValue());
        // 查询输出
        InterfTcms002OutEntity outEntity = interfTcms002Mapper.selectChkmode(entity);
        if (outEntity == null) {
            // 输出为空的处理
            outEntity = new InterfTcms002OutEntity();
            // 异常状态
            boolean exstatus = false;
            // 查询数据留痕表中产品信息
            InFlowEntity flowentity = interfTcms002Mapper.selectStatus(entity.getProdid());
            if (flowentity != null) {
				if (!StringUtil.equals(flowentity.getProstatus(), DictCodeList.数据状态_新建中.getValue())
						&& !StringUtil.equals(flowentity.getProstatus(), DictCodeList.数据状态_删除.getValue())) {
                    exstatus = true;
				} else {
                    status = flowentity.getProstatus();
                    // 将jsondata转换为 dto
                    FinProductBuild002Dto findto = JsonUtils.json2Object(flowentity.getDatajson(),
                        FinProductBuild002Dto.class);
                    // 抽取dto封装输出entity
                    transformEntity(outEntity, findto);
                }
			} else {
                exstatus = true;
            }
            // 查询产品不存在
            if (exstatus) {
                throw new InterfException(entity.getProdid() + "产品信息不存在！");
            }
		} else {
            BigDecimal syield = interfTcms002Mapper.selectYield(outEntity.getFinprodid(),
                DictCodeListExt1.现金流二级类型_销售费.getValue());
            BigDecimal hyield = interfTcms002Mapper.selectYield(outEntity.getFinprodid(),
                DictCodeListExt1.现金流二级类型_托管费.getValue());
            // 赋值产品状态
            status = InterfDictCodeList.产品状态_有效存续中.getValue();
            // 设置 托管费率
            outEntity.setHyield(hyield);
            // 设置 销售费率
            outEntity.setSyield(syield);
        }
        // 设置销售平台
        outEntity.setMktplat("对公理财销售系统");
        // 设置收益币种
        outEntity.setSubccy(DictCodeList.币种_人民币_CNY.getValue());
        // 设置收益币种
        outEntity.setSettccy(DictCodeList.币种_人民币_CNY.getValue());
        // 设置付息周期
        outEntity.setIntpaycycle(InterfDictCodeList.付息周期_利随本清.getValue());
        // 设置产品状态
        outEntity.setPrdstatus(status);
        transCode(outEntity);
        // 设置文件地址
        outEntity.setFileurl(getFileByChk(entity.getChkmode(), outEntity.getFinprodid(), entity));
        return outEntity;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public int saveInterfData(InterfTcms002InEntity entity) throws Exception {
        // 查询类接口，无登录操作
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
	public int saveBusinessData(InterfTcms002InEntity entity) throws Exception {
        // 查询类接口，无登录操作
        return 0;
    }

    /**
     * 获取上传文件地址
     * 
	 * @param chkmode
	 *            查询模式
	 * @param finprodid
	 *            金融产品代码
	 * @param inEntity
	 *            Tcms002输入entity
     * @return 文件地址
     * @see
     * @since 1.0
     */
    private String getFileByChk(String chkmode, String finprodid, InterfTcms002InEntity inEntity) {
        if (SEARCHMODE.equals(chkmode)) {
            String fileUrl = interfTcms002Mapper.selectProFile(finprodid);
            InterfSMS004Entity entity = new InterfSMS004Entity();
            // 设置批次编号
            entity.setSeqId(inEntity.getProdid());
            // 子系统列表
            List<InterfSubSysNoArray> arr = new ArrayList<>();
            InterfSubSysNoArray subSys = new InterfSubSysNoArray();
            // 子系统ID
            subSys.setSubSysNo(subSysNo);
            arr.add(subSys);
            entity.setArr(arr);
            upLoadService.setNoticeEntity(entity);
			return upLoadService.upLoadFile(getInterfId(), getUuid(), getBizSeqNo(), InterfConstant.INF_BFTS_PRODINS,
					fileUrl);
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CompositeData doReceivedData(CompositeData reqData, String uuid) {
        // 设置系统编号
        CompositeData head = reqData.getStruct(InterfConstant.SYS_HEAD);
        subSysNo = head.getField(EsbHeadEnum.系统报文头_请求系统编号.colName()).strValue();
        return super.doReceivedData(reqData, uuid);
    }

    /**
     * 新旧系统code转换
     * 
	 * @param entity
	 *            输出entity
     * @see
     * @since 1.0
     */
    private void transCode(InterfTcms002OutEntity entity) {
        // 转换销售部门code
        entity.setMktdeptno(transMkt(entity.getMktdeptno()));
        // 转换产品处理模式code
        entity.setProdmode(transPmode(entity.getProdmode()));
        // 转换滚动模式code
        entity.setRollmode(transRmode(entity.getRollmode()));
        // 转换发行方式code
        entity.setIssuetype(transIssue(entity.getIssuetype()));
    }

    /**
     * 封装接口输出entity
     * 
	 * @param outentity
	 *            输出entity
	 * @param findto
	 *            流程entity
     * @see
     * @since 1.0
     */
    private void transformEntity(InterfTcms002OutEntity outentity, FinProductBuild002Dto findto) {
        // 设置 产品代码
        outentity.setProdid(findto.getFinprodMarketId());
        // 设置 产品简称
        outentity.setShortname(findto.getFinprodAbbr());
        // 设置 产品名称
        outentity.setProdname(findto.getFinprodName());
        // 设置 销售部门
        outentity.setMktdeptno(findto.getSaleDepartment());
        // 设置产品一级分类
        if (findto.getBussFinProductSaleParamDto() != null) {
            outentity.setProdtype1(findto.getBussFinProductSaleParamDto().getPrdBand());
        }
        // 设置产品处理模式
        outentity.setProdmode(findto.getDealMode());
        // 设置滚动模式
        outentity.setRollmode(findto.getDealMode());
        // 设置产品期次
        if (findto.getBussRepFinProductInfoDto() != null) {
            // 00X转换
			String term = String.format("%03d", findto.getBussRepFinProductInfoDto().getRepFinprodStage());
            outentity.setProductperiod(term);
        }
        // 设置发行方式
        outentity.setIssuetype(transIssue(findto.getEntrustType()));
        // 设置分行
        if (StringUtil.equals(outentity.getIssuetype(), InterfDictCodeList.发行方式_集合.getValue())) {
            outentity.setBranch(findto.getEntruster());
        }
        if (null != findto.getBussFinProductFeeInfoDto()
            && null != findto.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList()
            && !findto.getBussFinProductFeeInfoDto().getBussFinProductFeeInfoDtoList().isEmpty()) {
			for (BussFinProductFeeInfoDto fdto : findto.getBussFinProductFeeInfoDto()
					.getBussFinProductFeeInfoDtoList()) {
				// 一体化对应etliu start
				// if (StringUtil.equals(fdto.getFeeType(),
				// DictCodeListExt2.费用类型_托管费.getValue())) {
				if (StringUtil.equals(fdto.getFeeType(), DictCodeListExt1.现金流二级类型_托管费.getValue())) {
					// 一体化对应etliu end
                    // 设置 托管费率
                    outentity.setHyield(fdto.getFeeRate());
                }
				// 一体化对应etliu start
				// if (StringUtil.equals(fdto.getFeeType(),
				// DictCodeListExt1.一次性费用类型_投管_交易手续费.getValue())) {
				if (StringUtil.equals(fdto.getFeeType(), DictCodeListExt1.现金流二级类型_销售费.getValue())) {
					// 一体化对应etliu end
                    // 设置 销售费率
                    outentity.setSyield(fdto.getFeeRate());
                }
            }
        }
        // 设置成立日
        outentity.setVdate(DateUtil.format(findto.getVdate(), DateUtil.DEFAULT_OTHER_FORMAT));
        // 设置到期日
        outentity.setMdate(DateUtil.format(findto.getMdate(), DateUtil.DEFAULT_OTHER_FORMAT));
        // 设置产品期限
        outentity.setTerm(String.valueOf(findto.getTermDays()));
        // 设置计息基础
        outentity.setBasis(findto.getBasis());
        // 设置预期收益率（年化）
        if (StringUtil.equals(findto.getIsLay(), DictCodeList.是否_否.getValue())) {
            outentity.setYield(findto.getYield());
        }
        // 设置销售开始日
		outentity.setSsdate(DateUtil.format(findto.getFirstSaleVdate(), DateUtil.DEFAULT_OTHER_FORMAT));
        // 设置销售截止日
		outentity.setSedate(DateUtil.format(findto.getFirstSaleMdate(), DateUtil.DEFAULT_OTHER_FORMAT));
    }

    /**
     * 字典转换-销售部门
     * 
	 * @param mkt
	 *            wind代码
     * @return 对应字典项
     * @see
     * @since 1.0
     */

    private String transMkt(String mkt) {
        String code = null;
        if (StringUtil.equals(mkt, DictCodeList.销售部门_零售产品管理部.getValue())) {
            code = InterfDictCodeList.销售部门_零售产品管理部.getValue();
		} else if (StringUtil.equals(mkt, DictCodeList.销售部门_金融同业部.getValue())) {
            code = InterfDictCodeList.销售部门_金融同业部.getValue();
		} else if (StringUtil.equals(mkt, DictCodeList.销售部门_对公产品.getValue())
                 || StringUtil.equals(mkt, DictCodeList.销售部门_现金管理部.getValue())) {
            code = InterfDictCodeList.销售部门_对公产品和现金管理部.getValue();
		} else {
            code = mkt;
        }
        return code;
    }

    /**
     * 字典转换-产品处理模式
     * 
	 * @param pmode
	 *            产品处理模式
     * @return 对应字典项
     * @see
     * @since 1.0
     */
    private String transPmode(String pmode) {
        String code = null;
        if (StringUtil.equals(pmode, DictCodeList.处理模式_普通.getValue())) {
            code = InterfDictCodeList.产品处理模式_普通类型.getValue();
		} else if (StringUtil.equals(pmode, BussinessDictCodeList.处理模式_滚动.getValue())) {
            code = InterfDictCodeList.产品处理模式_滚动N天型产品.getValue();
		} else if (StringUtil.equals(pmode, BussinessDictCodeList.处理模式_滚动N天.getValue())) {
            code = InterfDictCodeList.产品处理模式_滚动N天型产品.getValue();
		} else if (StringUtil.equals(pmode, BussinessDictCodeList.处理模式_普通N天.getValue())) {
            code = InterfDictCodeList.产品处理模式_普通类型.getValue();
		} else if (StringUtil.equals(pmode, BussinessDictCodeList.处理模式_开放阶梯.getValue())) {
            code = InterfDictCodeList.产品处理模式_开放式阶梯型.getValue();
		} else if (StringUtil.equals(pmode, BussinessDictCodeList.处理模式_日添利.getValue())) {
            code = InterfDictCodeList.产品处理模式_日添利.getValue();
		} else if (StringUtil.equals(pmode, BussinessDictCodeList.处理模式_智能日添利.getValue())) {
            code = InterfDictCodeList.产品处理模式_7乘24日添利.getValue();
		} else if (StringUtil.equals(pmode, BussinessDictCodeList.处理模式_货币型.getValue())) {
            code = InterfDictCodeList.产品处理模式_货币型.getValue();
		} else if (StringUtil.equals(pmode, BussinessDictCodeList.处理模式_净值型.getValue())) {
            code = InterfDictCodeList.产品处理模式_净值型产品.getValue();
		} else {
            code = pmode;
        }
        return code;
    }

    /**
     * 字典转换-滚动模式
     * 
	 * @param rmode
	 *            滚动模式
     * @return 对应字典项
     * @see
     * @since 1.0
     */
    private String transRmode(String rmode) {
        String code = null;
        if (StringUtil.equals(rmode, DictCodeList.处理模式_普通.getValue())) {
            code = InterfDictCodeList.滚动模式_一次性产品.getValue();
		} else if (StringUtil.equals(rmode, BussinessDictCodeList.处理模式_滚动.getValue())) {
            code = InterfDictCodeList.滚动模式_定期滚动型产品.getValue();
		} else if (StringUtil.equals(rmode, BussinessDictCodeList.处理模式_滚动N天.getValue())) {
            code = InterfDictCodeList.滚动模式_N天每日滚动型.getValue();
		} else if (StringUtil.equals(rmode, BussinessDictCodeList.处理模式_普通N天.getValue())) {
            code = InterfDictCodeList.滚动模式_N天每日滚动型.getValue();
		} else if (StringUtil.equals(rmode, BussinessDictCodeList.处理模式_开放阶梯.getValue())) {
            code = InterfDictCodeList.滚动模式_按天计收益全开放型.getValue();
		} else if (StringUtil.equals(rmode, BussinessDictCodeList.处理模式_日添利.getValue())) {
            code = InterfDictCodeList.滚动模式_日添利型.getValue();
		} else if (StringUtil.equals(rmode, BussinessDictCodeList.处理模式_智能日添利.getValue())) {
            code = InterfDictCodeList.滚动模式_日添利型.getValue();
		} else if (StringUtil.equals(rmode, BussinessDictCodeList.处理模式_货币型.getValue())) {
            code = InterfDictCodeList.滚动模式_日添利型.getValue();
		} else if (StringUtil.equals(rmode, BussinessDictCodeList.处理模式_净值型.getValue())) {
            code = InterfDictCodeList.滚动模式_净值型.getValue();
		} else {
            code = rmode;
        }
        return code;
    }

    /**
     * 数据转换-发行方式
     * 
	 * @param issue
	 *            发行方式
     * @return 对应字典项
     * @see
     * @since 1.0
     */
    private String transIssue(String issue) {
        if (StringUtil.equals(issue, DictCodeList.排期发行方式_单一.getValue())) {
            return InterfDictCodeList.发行方式_单一.getValue();
		} else {
            return InterfDictCodeList.发行方式_集合.getValue();
        }
    }

}
