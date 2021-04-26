package com.joyintech.tams.settle.batch.datacreate.processor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.joyintech.batch.batchbase.baseclass.BaseProcessor;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.SzdJsmx;
import com.joyintech.tams.common.base.entity.SzdSjsjg;
import com.joyintech.tams.common.base.entity.SzdSjsmx;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListStl;
import com.joyintech.tams.settle.batch.datacreate.constant.StlDataCreateConstant;
import com.joyintech.tams.settle.batch.datacreate.dto.StlDataCreateDto;
import com.joyintech.tams.settle.batch.datacreate.dto.StlDataCreateReaderDto;
import com.joyintech.tams.settle.batch.datacreate.dto.StlDataCreateWriteDto;
import com.joyintech.tams.settle.batch.datadeal.constant.StlDataDealProcessConstant;

/**
 * 生成结算数据文件需要的WriterDto
 * @author 陈卓
 * @date 2020/11/24
 * @see StlDataCreateProcessor
 * @since 1.0
 */
@Service
@Lazy
public class StlDataCreateProcessor extends BaseProcessor<NeedOperateEntity, StlDataCreateWriteDto> {

    @Override
    public StlDataCreateWriteDto process(NeedOperateEntity operateEntity) throws Exception {
    	StlDataCreateWriteDto stlDataCreateWriteDto = new StlDataCreateWriteDto();
    	StlDataCreateReaderDto stlDataCreateReaderDto = (StlDataCreateReaderDto)operateEntity;
    	if (stlDataCreateReaderDto != null && stlDataCreateReaderDto.getStlDataCreateDtoList() != null && stlDataCreateReaderDto.getStlDataCreateDtoList().size() > 0) {
    		List<SzdJsmx> jsmx02SzdJsmxList = new ArrayList<SzdJsmx>();
    		List<SzdJsmx> jsmx03SzdJsmxList = new ArrayList<SzdJsmx>();
    		List<SzdSjsmx> sjsmx0SzdSjsmxList = new ArrayList<SzdSjsmx>();
    		List<SzdSjsmx> sjsmx1SzdSjsmxList = new ArrayList<SzdSjsmx>();
    		List<SzdSjsjg> szdSjsjgList = new ArrayList<SzdSjsjg>();
    		// 开始处理
    		for (StlDataCreateDto stlDataCreateDto : stlDataCreateReaderDto.getStlDataCreateDtoList()) {
    			// 成交明细.金融产品类型（投管） = 数据字典.金融产品类型（投管）.债券 and 成交明细.交易场所 = 数据字典.交易场所.上海证券交易所
    			if (StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && 
    					StringUtil.equals(stlDataCreateDto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
    				if (StringUtil.equals(stlDataCreateDto.getIsNetSett(), DictCodeList.是否_是.getValue())) {
    					jsmx02SzdJsmxList.add(generatorJsmx02Bond(stlDataCreateDto));
    				}
    				else {
    					jsmx03SzdJsmxList.add(generatorJsmx03Bond(stlDataCreateDto));
    				}
    			}
    			// 成交明细.金融产品类型（投管） = 数据字典.金融产品类型（投管）.债券 and 成交明细.交易场所 = 数据字典.交易场所.深圳证券交易所
    			if (StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_债券.getValue()) && 
    					StringUtil.equals(stlDataCreateDto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
    				if (StringUtil.equals(stlDataCreateDto.getIsNetSett(), DictCodeList.是否_是.getValue())) {
    					sjsmx0SzdSjsmxList.add(generatorSjsmx0Bond(stlDataCreateDto));
    				}
    				else {
    					sjsmx1SzdSjsmxList.add(generatorSjsmx1Bond(stlDataCreateDto));
    				}
    			}
    			// 成交明细.金融产品类型（投管） = 数据字典.金融产品类型（投管）.基金 and 成交明细.交易场所 = 数据字典.交易场所.上海证券交易所
    			if (StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_基金.getValue()) && 
    					StringUtil.equals(stlDataCreateDto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
    				jsmx02SzdJsmxList.add(generatorJsmx02Fund(stlDataCreateDto));
    			}
    			// 成交明细.金融产品类型（投管） = 数据字典.金融产品类型（投管）.基金 and 成交明细.交易场所 = 数据字典.交易场所.深圳证券交易所
    			if (StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_基金.getValue()) && 
    					StringUtil.equals(stlDataCreateDto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
    				sjsmx1SzdSjsmxList.add(generatorSjsmx1Fund(stlDataCreateDto));
    			}
    			// 成交明细.金融产品类型（投管） = 数据字典.金融产品类型（投管）.股票 and 成交明细.交易场所 = 数据字典.交易场所.上海证券交易所
    			if (StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_股票.getValue()) && 
    					StringUtil.equals(stlDataCreateDto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
    				jsmx02SzdJsmxList.add(generatorJsmx02Stock(stlDataCreateDto));
    			}
    			// 成交明细.金融产品类型（投管） = 数据字典.金融产品类型（投管）.股票 and 成交明细.交易场所 = 数据字典.交易场所.深圳证券交易所
    			if (StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_股票.getValue()) && 
    					StringUtil.equals(stlDataCreateDto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
    				sjsmx1SzdSjsmxList.add(generatorSjsmx1Stock(stlDataCreateDto));
    			}
    			// 成交明细.金融产品类型（投管） = 数据字典.金融产品类型（投管）.债券质押式正回购、债券质押式逆回购 and 成交明细.交易场所 = 数据字典.交易场所.上海证券交易所
    			if ((StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue()) ||
    					StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) && 
    					StringUtil.equals(stlDataCreateDto.getTradeMarket(), DictCodeListExt1.交易场所_上海证券交易所.getValue())) {
    				if (StringUtil.equals(stlDataCreateDto.getType6(), DictCodeList.回购类型_质押式竞价回购.getValue())) {
    					jsmx02SzdJsmxList.add(generatorJsmx02Repo(stlDataCreateDto));
    				}
    				if (StringUtil.equals(stlDataCreateDto.getType6(), DictCodeList.回购类型_质押式协议回购.getValue())) {
    					jsmx02SzdJsmxList.add(generatorJsmx03Repo(stlDataCreateDto));
    				}
    			}
    			// 成交明细.金融产品类型（投管） = 数据字典.金融产品类型（投管）.债券质押式正回购、债券质押式逆回购 and 成交明细.交易场所 = 数据字典.交易场所.深圳证券交易所
    			if ((StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue()) ||
    					StringUtil.equals(stlDataCreateDto.getFinprodTypeTg(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) && 
    					StringUtil.equals(stlDataCreateDto.getTradeMarket(), DictCodeListExt1.交易场所_深圳证券交易所.getValue())) {
    				if (StringUtil.equals(stlDataCreateDto.getType6(), DictCodeList.回购类型_质押式竞价回购.getValue())) {
    					sjsmx1SzdSjsmxList.add(generatorSjsmx1Repo(stlDataCreateDto));
    				}
    				if (StringUtil.equals(stlDataCreateDto.getType6(), DictCodeList.回购类型_质押式协议回购.getValue())) {
    					szdSjsjgList.add(generatorSjsjgRepo(stlDataCreateDto));
    				}
    			}
    		}
    		// 处理结果塞入stlDataCreateWriteDto
    		stlDataCreateWriteDto.setJsmx02SzdJsmxList(jsmx02SzdJsmxList);
    		stlDataCreateWriteDto.setJsmx03SzdJsmxList(jsmx03SzdJsmxList);
    		stlDataCreateWriteDto.setSjsmx0SzdSjsmxList(sjsmx0SzdSjsmxList);
    		stlDataCreateWriteDto.setSjsmx1SzdSjsmxList(sjsmx1SzdSjsmxList);
    		stlDataCreateWriteDto.setSzdSjsjgList(szdSjsjgList);
    		stlDataCreateWriteDto.setTradeDate(stlDataCreateReaderDto.getTradeDate());
    	}
    	return stlDataCreateWriteDto;
    }
    
    /**
     * 生成jsmx02-债券的SzdJsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdJsmx
     */
    private SzdJsmx generatorJsmx02Bond(StlDataCreateDto stlDataCreateDto) {
    	SzdJsmx szdJsmx = new SzdJsmx();
    	szdJsmx.setScdm(StlDataDealProcessConstant.SCDM_01);
    	szdJsmx.setJllx(StlDataDealProcessConstant.JLLX_001);
    	szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_001);
    	szdJsmx.setJsfs(StlDataDealProcessConstant.JSFS_001);
    	if (StringUtil.equals(stlDataCreateDto.getTradePlat(), DictCodeList.交易平台_固定收益平台.getValue())) {
    		if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_国债.getValue())) {
    			szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_601);
    		}
    		if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_公司债.getValue())) {
    			szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_611);
    		}
    	}
    	else {
    		if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_国债.getValue())) {
    			szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_003);
    		}
    		if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_企业债.getValue())) {
    			szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_004);
    		}
    	}
    	if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_国债.getValue())) {
    		szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_000);
    	}
    	if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_企业债.getValue())) {
    		szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_010);
    	}
    	if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_公司债.getValue())) {
    		szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_011);
    	}
    	szdJsmx.setGhlx(StlDataDealProcessConstant.GHLX_00A);
    	szdJsmx.setCjbh(stlDataCreateDto.getTradeId());
    	szdJsmx.setSqbh(stlDataCreateDto.getOrderNo());
    	szdJsmx.setJyrq(stlDataCreateDto.getTradeDate());
    	szdJsmx.setQsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setJsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setXwh1(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setXwh2(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setZqzh(stlDataCreateDto.getSecAcctId());
    	szdJsmx.setZqdm1(stlDataCreateDto.getFinprodMarketId());
    	szdJsmx.setZqlb(StlDataDealProcessConstant.ZQLB_GZ);
    	szdJsmx.setLtlx(StlDataDealProcessConstant.LTLX_0);
    	if (StringUtil.equals(stlDataCreateDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_B);
    		szdJsmx.setSl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(100)).toString());
    		szdJsmx.setCjsl(szdJsmx.getSl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getFpriceAmt()), new BigDecimal(-1)).toString());
    		}
    		else {
    			szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    		}
    		szdJsmx.setSjsf(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    	}
    	else {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_S);
    		szdJsmx.setSl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-100)).toString());
    		szdJsmx.setCjsl(szdJsmx.getSl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdJsmx.setQsje(stlDataCreateDto.getFpriceAmt());
    		}
    		else {
    			szdJsmx.setQsje(stlDataCreateDto.getTradeAmt());
    		}
    		szdJsmx.setSjsf(stlDataCreateDto.getTradeAmt());
    	}
    	szdJsmx.setZjzh(stlDataCreateDto.getTrdAcctId());
    	szdJsmx.setBz(stlDataCreateDto.getCcy());
    	szdJsmx.setJg1(stlDataCreateDto.getUnitCprice());
    	if (stlDataCreateDto.getUnitFprice() != null) {
    		szdJsmx.setJg2(stlDataCreateDto.getUnitFprice());
    	}
    	else {
    		szdJsmx.setJg2(stlDataCreateDto.getUnitCprice());
    	}
    	szdJsmx.setYhs(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJsf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setGhf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setZgf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setSxf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje1(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje2(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje3(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJgdm(StlDataDealProcessConstant.JGDM_0000);
    	return szdJsmx;
    }
    
    /**
     * 生成jsmx03-债券的SzdJsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdJsmx
     */
    private SzdJsmx generatorJsmx03Bond(StlDataCreateDto stlDataCreateDto) {
    	SzdJsmx szdJsmx = new SzdJsmx();
    	szdJsmx.setScdm(StlDataDealProcessConstant.SCDM_01);
    	szdJsmx.setJllx(StlDataDealProcessConstant.JLLX_003);
    	szdJsmx.setJsfs(StlDataDealProcessConstant.JSFS_002);
    	if (StringUtil.equals(stlDataCreateDto.getTradePlat(), DictCodeList.交易平台_固定收益平台.getValue())) {
    		szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_101);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getTradePlat(), DictCodeList.交易平台_竞价撮合平台.getValue())) {
    		szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_001);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getTradePlat(), DictCodeList.交易平台_大宗交易平台.getValue())) {
    		szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_002);
    	}
    	if (StringUtil.equals(stlDataCreateDto.getTradePlat(), DictCodeList.交易平台_固定收益平台.getValue())) {
    		if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_国债.getValue())) {
    			szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_601);
    		}
    		if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_公司债.getValue())) {
    			szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_611);
    		}
    	}
    	else {
    		if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_国债.getValue())) {
    			szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_003);
    		}
    		if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_企业债.getValue())) {
    			szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_004);
    		}
    	}
    	if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_国债.getValue())) {
    		szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_000);
    	}
    	if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_企业债.getValue())) {
    		szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_010);
    	}
    	if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_公司债.getValue())) {
    		szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_011);
    	}
    	szdJsmx.setGhlx(StlDataDealProcessConstant.GHLX_00A);
    	szdJsmx.setCjbh(stlDataCreateDto.getTradeId());
    	szdJsmx.setSqbh(stlDataCreateDto.getOrderNo());
    	szdJsmx.setJyrq(stlDataCreateDto.getTradeDate());
    	szdJsmx.setQsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setJsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setXwh1(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setXwh2(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setZqzh(stlDataCreateDto.getSecAcctId());
    	szdJsmx.setZqdm1(stlDataCreateDto.getFinprodMarketId());
    	szdJsmx.setZqlb(StlDataDealProcessConstant.ZQLB_GZ);
    	szdJsmx.setLtlx(StlDataDealProcessConstant.LTLX_0);
    	if (StringUtil.equals(stlDataCreateDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_B);
    		szdJsmx.setSl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(100)).toString());
    		szdJsmx.setCjsl(szdJsmx.getSl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getFpriceAmt()), new BigDecimal(-1)).toString());
    		}
    		else {
    			szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    		}
    		szdJsmx.setSjsf(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    	}
    	else {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_S);
    		szdJsmx.setSl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-100)).toString());
    		szdJsmx.setCjsl(szdJsmx.getSl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdJsmx.setQsje(stlDataCreateDto.getFpriceAmt());
    		}
    		else {
    			szdJsmx.setQsje(stlDataCreateDto.getTradeAmt());
    		}
    		szdJsmx.setSjsf(stlDataCreateDto.getTradeAmt());
    	}
    	szdJsmx.setZjzh(stlDataCreateDto.getTrdAcctId());
    	szdJsmx.setBz(stlDataCreateDto.getCcy());
    	szdJsmx.setJg1(stlDataCreateDto.getUnitCprice());
    	if (stlDataCreateDto.getUnitFprice() != null) {
    		szdJsmx.setJg2(stlDataCreateDto.getUnitFprice());
    	}
    	else {
    		szdJsmx.setJg2(stlDataCreateDto.getUnitCprice());
    	}
    	szdJsmx.setYhs(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJsf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setGhf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setZgf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setSxf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje1(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje2(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje3(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJgdm(StlDataDealProcessConstant.JGDM_0000);
    	return szdJsmx;
    }
    
    /**
     * 生成sjsmx0-债券的SzdSjsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdSjsmx
     */
    private SzdSjsmx generatorSjsmx0Bond(StlDataCreateDto stlDataCreateDto) {
    	SzdSjsmx szdSjsmx = new SzdSjsmx();
    	szdSjsmx.setMxbfzh(stlDataCreateDto.getTrdAcctId());
    	szdSjsmx.setMxsjlx(StlDataCreateConstant.SJSMX_MXSJLX_01);
    	szdSjsmx.setMxywlb(StlDataDealProcessConstant.SJSMX_YWLB_JY00);
    	szdSjsmx.setMxzqdm(stlDataCreateDto.getFinprodMarketId());
    	szdSjsmx.setMxjydy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxtgdy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxzqzh(stlDataCreateDto.getSecAcctId());
    	szdSjsmx.setMxddbh(stlDataCreateDto.getOrderNo());
    	szdSjsmx.setMxywlsh(stlDataCreateDto.getTradeId());
    	if (StringUtil.equals(stlDataCreateDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
    		szdSjsmx.setMxcjsl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(100)));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdSjsmx.setMxqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getFpriceAmt()), new BigDecimal(-1)));
    		}
    		else {
    			szdSjsmx.setMxqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    		}
    		szdSjsmx.setMxsfje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    	}
    	else {
    		szdSjsmx.setMxcjsl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-100)));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdSjsmx.setMxqsbj(new BigDecimal(stlDataCreateDto.getFpriceAmt()));
    		}
    		else {
    			szdSjsmx.setMxqsbj(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    		}
    		szdSjsmx.setMxsfje(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    	}
    	szdSjsmx.setMxcjjg(new BigDecimal(stlDataCreateDto.getUnitCprice()));
    	if (stlDataCreateDto.getUnitFprice() != null) {
    		szdSjsmx.setMxqsjg(new BigDecimal(stlDataCreateDto.getUnitFprice()));
    	}
    	else {
    		szdSjsmx.setMxqsjg(new BigDecimal(stlDataCreateDto.getUnitCprice()));
    	}
    	szdSjsmx.setMxxyjy(StlDataCreateConstant.SJSMX_MXXYJY_1);
    	if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_国债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_10);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_企业债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_11);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_公司债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1C);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_可转债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_12);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_金融债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1R);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_优先股.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1Y);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_资产支持证券.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1Z);
    	}
    	else {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1J);
    	}
    	szdSjsmx.setMxgfxz(StlDataDealProcessConstant.SJSMX_GFXZ_00);
    	szdSjsmx.setMxjsfs(StlDataDealProcessConstant.SJSMX_JSFS_A);
    	szdSjsmx.setMxhbdh(stlDataCreateDto.getCcy());
    	szdSjsmx.setMxcjrq(stlDataCreateDto.getTradeDate());
    	szdSjsmx.setMxqsrq(stlDataCreateDto.getSettleDate());
    	szdSjsmx.setMxjsrq(stlDataCreateDto.getSettleDate());
    	szdSjsmx.setMxjyfs(StlDataDealProcessConstant.SJSMX_JYFS_01);
    	szdSjsmx.setMxzqdm2(stlDataCreateDto.getFinprodMarketId());
    	return szdSjsmx;
    }
    
    /**
     * 生成sjsmx1-债券的SzdSjsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdSjsmx
     */
    private SzdSjsmx generatorSjsmx1Bond(StlDataCreateDto stlDataCreateDto) {
    	SzdSjsmx szdSjsmx = new SzdSjsmx();
    	szdSjsmx.setMxbfzh(stlDataCreateDto.getTrdAcctId());
    	szdSjsmx.setMxsjlx(StlDataCreateConstant.SJSMX_MXSJLX_01);
    	szdSjsmx.setMxywlb(StlDataDealProcessConstant.SJSMX_YWLB_JY00);
    	szdSjsmx.setMxzqdm(stlDataCreateDto.getFinprodMarketId());
    	szdSjsmx.setMxjydy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxtgdy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxzqzh(stlDataCreateDto.getSecAcctId());
    	szdSjsmx.setMxddbh(stlDataCreateDto.getOrderNo());
    	szdSjsmx.setMxywlsh(stlDataCreateDto.getTradeId());
    	if (StringUtil.equals(stlDataCreateDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
    		szdSjsmx.setMxcjsl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(100)));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdSjsmx.setMxqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getFpriceAmt()), new BigDecimal(-1)));
    		}
    		else {
    			szdSjsmx.setMxqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    		}
    		szdSjsmx.setMxsfje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    	}
    	else {
    		szdSjsmx.setMxcjsl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-100)));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdSjsmx.setMxqsbj(new BigDecimal(stlDataCreateDto.getFpriceAmt()));
    		}
    		else {
    			szdSjsmx.setMxqsbj(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    		}
    		szdSjsmx.setMxsfje(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    	}
    	szdSjsmx.setMxcjjg(new BigDecimal(stlDataCreateDto.getUnitCprice()));
    	if (stlDataCreateDto.getUnitFprice() != null) {
    		szdSjsmx.setMxqsjg(new BigDecimal(stlDataCreateDto.getUnitFprice()));
    	}
    	else {
    		szdSjsmx.setMxqsjg(new BigDecimal(stlDataCreateDto.getUnitCprice()));
    	}
    	szdSjsmx.setMxxyjy(StlDataCreateConstant.SJSMX_MXXYJY_1);
    	if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_国债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_10);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_企业债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_11);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_公司债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1C);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_可转债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_12);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_金融债.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1R);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_优先股.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1Y);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getType9(), DictCodeListStl.资产二级分类_资产支持证券.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1Z);
    	}
    	else {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_1J);
    	}
    	szdSjsmx.setMxgfxz(StlDataDealProcessConstant.SJSMX_GFXZ_00);
    	szdSjsmx.setMxjsfs(StlDataDealProcessConstant.SJSMX_JSFS_N);
    	szdSjsmx.setMxhbdh(stlDataCreateDto.getCcy());
    	szdSjsmx.setMxcjrq(stlDataCreateDto.getTradeDate());
    	szdSjsmx.setMxqsrq(stlDataCreateDto.getSettleDate());
    	szdSjsmx.setMxjsrq(stlDataCreateDto.getSettleDate());
    	szdSjsmx.setMxjyfs(StlDataDealProcessConstant.SJSMX_JYFS_02);
    	szdSjsmx.setMxzqdm2(stlDataCreateDto.getFinprodMarketId());
    	return szdSjsmx;
    }
    
    /**
     * 生成jsmx02-基金的SzdJsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdJsmx
     */
    private SzdJsmx generatorJsmx02Fund(StlDataCreateDto stlDataCreateDto) {
    	SzdJsmx szdJsmx = new SzdJsmx();
    	szdJsmx.setScdm(StlDataDealProcessConstant.SCDM_01);
    	szdJsmx.setJllx(StlDataDealProcessConstant.JLLX_001);
    	if (StringUtil.equals(stlDataCreateDto.getTradePlat(), DictCodeList.交易平台_大宗交易平台.getValue())) {
    		szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_002);
    	}
    	else {
    		szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_001);
    	}
    	szdJsmx.setJsfs(StlDataDealProcessConstant.JSFS_001);
    	if (StringUtil.equals(stlDataCreateDto.getTrmFundType(), DictCodeListExt2.交易所基金类型_ETF.getValue())) {
    		szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_104);
    		szdJsmx.setQsbz(StlDataCreateConstant.QSBZ_051);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getTrmFundType(), DictCodeListExt2.交易所基金类型_LOF.getValue())) {
    		szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_034);
    		szdJsmx.setQsbz(StlDataCreateConstant.QSBZ_05B);
    	}
    	else {
    		szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_002);
    		szdJsmx.setQsbz(StlDataCreateConstant.QSBZ_050);
    	}
    	szdJsmx.setGhlx(StlDataDealProcessConstant.GHLX_00A);
    	szdJsmx.setCjbh(stlDataCreateDto.getTradeId());
    	szdJsmx.setSqbh(stlDataCreateDto.getOrderNo());
    	szdJsmx.setJyrq(stlDataCreateDto.getTradeDate());
    	szdJsmx.setQsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setJsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setXwh1(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setXwh2(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setZqzh(stlDataCreateDto.getSecAcctId());
    	szdJsmx.setZqdm1(stlDataCreateDto.getFinprodMarketId());
    	szdJsmx.setZqlb(StlDataDealProcessConstant.ZQLB_JJ);
    	szdJsmx.setLtlx(StlDataDealProcessConstant.LTLX_0);
    	if (StringUtil.equals(stlDataCreateDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_B);
    		szdJsmx.setSl(stlDataCreateDto.getShareAmt());
    		szdJsmx.setCjsl(szdJsmx.getSl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getFpriceAmt()), new BigDecimal(-1)).toString());
    		}
    		else {
    			szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    		}
    		szdJsmx.setSjsf(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    	}
    	else {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_S);
    		szdJsmx.setSl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-1)).toString());
    		szdJsmx.setCjsl(szdJsmx.getSl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdJsmx.setQsje(stlDataCreateDto.getFpriceAmt());
    		}
    		else {
    			szdJsmx.setQsje(stlDataCreateDto.getTradeAmt());
    		}
    		szdJsmx.setSjsf(stlDataCreateDto.getTradeAmt());
    	}
    	szdJsmx.setZjzh(stlDataCreateDto.getTrdAcctId());
    	szdJsmx.setBz(stlDataCreateDto.getCcy());
    	szdJsmx.setJg1(stlDataCreateDto.getUnitCprice());
    	if (stlDataCreateDto.getUnitFprice() != null) {
    		szdJsmx.setJg2(stlDataCreateDto.getUnitFprice());
    	}
    	else {
    		szdJsmx.setJg2(stlDataCreateDto.getUnitCprice());
    	}
    	szdJsmx.setYhs(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJsf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setGhf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setZgf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setSxf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje1(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje2(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje3(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJgdm(StlDataDealProcessConstant.JGDM_0000);
    	return szdJsmx;
    }
    
    /**
     * 生成sjsmx1-基金的SzdSjsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdSjsmx
     */
    private SzdSjsmx generatorSjsmx1Fund(StlDataCreateDto stlDataCreateDto) {
    	SzdSjsmx szdSjsmx = new SzdSjsmx();
    	szdSjsmx.setMxbfzh(stlDataCreateDto.getTrdAcctId());
    	szdSjsmx.setMxsjlx(StlDataCreateConstant.SJSMX_MXSJLX_01);
    	szdSjsmx.setMxywlb(StlDataDealProcessConstant.SJSMX_YWLB_JY01);
    	szdSjsmx.setMxzqdm(stlDataCreateDto.getFinprodMarketId());
    	szdSjsmx.setMxjydy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxtgdy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxzqzh(stlDataCreateDto.getSecAcctId());
    	szdSjsmx.setMxddbh(stlDataCreateDto.getOrderNo());
    	szdSjsmx.setMxywlsh(stlDataCreateDto.getTradeId());
    	if (StringUtil.equals(stlDataCreateDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
    		szdSjsmx.setMxcjsl(new BigDecimal(stlDataCreateDto.getShareAmt()));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdSjsmx.setMxqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getFpriceAmt()), new BigDecimal(-1)));
    		}
    		else {
    			szdSjsmx.setMxqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    		}
    		szdSjsmx.setMxsfje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    	}
    	else {
    		szdSjsmx.setMxcjsl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-1)));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdSjsmx.setMxqsbj(new BigDecimal(stlDataCreateDto.getFpriceAmt()));
    		}
    		else {
    			szdSjsmx.setMxqsbj(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    		}
    		szdSjsmx.setMxsfje(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    	}
    	szdSjsmx.setMxcjjg(new BigDecimal(stlDataCreateDto.getUnitCprice()));
    	if (stlDataCreateDto.getUnitFprice() != null) {
    		szdSjsmx.setMxqsjg(new BigDecimal(stlDataCreateDto.getUnitFprice()));
    	}
    	else {
    		szdSjsmx.setMxqsjg(new BigDecimal(stlDataCreateDto.getUnitCprice()));
    	}
    	szdSjsmx.setMxxyjy(StlDataCreateConstant.SJSMX_MXXYJY_1);
    	if (StringUtil.equals(stlDataCreateDto.getTrmFundType(), DictCodeListExt2.交易所基金类型_ETF.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_EF);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getTrmFundType(), DictCodeListExt2.交易所基金类型_LOF.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_15);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getTrmFundType(), DictCodeListExt2.交易所基金类型_封闭式基金.getValue())) {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_18);
    	}
    	else {
    		szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_16);
    	}
    	szdSjsmx.setMxgfxz(StlDataDealProcessConstant.SJSMX_GFXZ_00);
    	szdSjsmx.setMxjsfs(StlDataDealProcessConstant.SJSMX_JSFS_N);
    	szdSjsmx.setMxhbdh(stlDataCreateDto.getCcy());
    	szdSjsmx.setMxcjrq(stlDataCreateDto.getTradeDate());
    	szdSjsmx.setMxqsrq(stlDataCreateDto.getSettleDate());
    	szdSjsmx.setMxjsrq(stlDataCreateDto.getSettleDate());
    	szdSjsmx.setMxjyfs(StlDataDealProcessConstant.SJSMX_JYFS_02);
    	szdSjsmx.setMxzqdm2(stlDataCreateDto.getFinprodMarketId());
    	return szdSjsmx;
    }
    
    /**
     * 生成jsmx02-股票的SzdJsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdJsmx
     */
    private SzdJsmx generatorJsmx02Stock(StlDataCreateDto stlDataCreateDto) {
    	SzdJsmx szdJsmx = new SzdJsmx();
    	szdJsmx.setScdm(StlDataDealProcessConstant.SCDM_01);
    	szdJsmx.setJllx(StlDataDealProcessConstant.JLLX_001);
    	if (StringUtil.equals(stlDataCreateDto.getTradePlat(), DictCodeList.交易平台_大宗交易平台.getValue())) {
    		szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_002);
    	}
    	else if (StringUtil.equals(stlDataCreateDto.getTradePlat(), DictCodeList.交易平台_综合协议交易平台.getValue())) {
    		szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_014);
    	}
    	else {
    		szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_001);
    	}
    	szdJsmx.setJsfs(StlDataDealProcessConstant.JSFS_001);
    	if (StringUtil.equals(stlDataCreateDto.getBoard(), DictCodeList.上市板_主板.getValue())) {
    		szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_001);
    		szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_060);
    	}
    	else {
    		szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_037);
    		szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_061);
    	}
    	szdJsmx.setGhlx(StlDataDealProcessConstant.GHLX_00A);
    	szdJsmx.setCjbh(stlDataCreateDto.getTradeId());
    	szdJsmx.setSqbh(stlDataCreateDto.getOrderNo());
    	szdJsmx.setJyrq(stlDataCreateDto.getTradeDate());
    	szdJsmx.setQsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setJsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setXwh1(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setXwh2(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setZqzh(stlDataCreateDto.getSecAcctId());
    	szdJsmx.setZqdm1(stlDataCreateDto.getFinprodMarketId());
    	szdJsmx.setZqlb(StlDataDealProcessConstant.ZQLB_XL);
    	szdJsmx.setLtlx(StlDataDealProcessConstant.LTLX_0);
    	if (StringUtil.equals(stlDataCreateDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_B);
    		szdJsmx.setSl(stlDataCreateDto.getShareAmt());
    		szdJsmx.setCjsl(szdJsmx.getSl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getFpriceAmt()), new BigDecimal(-1)).toString());
    		}
    		else {
    			szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    		}
    		szdJsmx.setSjsf(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    	}
    	else {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_S);
    		szdJsmx.setSl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-1)).toString());
    		szdJsmx.setCjsl(szdJsmx.getSl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdJsmx.setQsje(stlDataCreateDto.getFpriceAmt());
    		}
    		else {
    			szdJsmx.setQsje(stlDataCreateDto.getTradeAmt());
    		}
    		szdJsmx.setSjsf(stlDataCreateDto.getTradeAmt());
    	}
    	szdJsmx.setZjzh(stlDataCreateDto.getTrdAcctId());
    	szdJsmx.setBz(stlDataCreateDto.getCcy());
    	szdJsmx.setJg1(stlDataCreateDto.getUnitCprice());
    	if (stlDataCreateDto.getUnitFprice() != null) {
    		szdJsmx.setJg2(stlDataCreateDto.getUnitFprice());
    	}
    	else {
    		szdJsmx.setJg2(stlDataCreateDto.getUnitCprice());
    	}
    	szdJsmx.setYhs(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJsf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setGhf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setZgf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setSxf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje1(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje2(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje3(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJgdm(StlDataDealProcessConstant.JGDM_0000);
    	return szdJsmx;
    }
    
    /**
     * 生成sjsmx1-股票的SzdSjsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdSjsmx
     */
    private SzdSjsmx generatorSjsmx1Stock(StlDataCreateDto stlDataCreateDto) {
    	SzdSjsmx szdSjsmx = new SzdSjsmx();
    	szdSjsmx.setMxbfzh(stlDataCreateDto.getTrdAcctId());
    	szdSjsmx.setMxsjlx(StlDataCreateConstant.SJSMX_MXSJLX_01);
    	szdSjsmx.setMxywlb(StlDataDealProcessConstant.SJSMX_YWLB_JY00);
    	szdSjsmx.setMxzqdm(stlDataCreateDto.getFinprodMarketId());
    	szdSjsmx.setMxjydy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxtgdy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxzqzh(stlDataCreateDto.getSecAcctId());
    	szdSjsmx.setMxddbh(stlDataCreateDto.getOrderNo());
    	szdSjsmx.setMxzqlb(StlDataDealProcessConstant.SJSMX_ZQLB_00);
    	szdSjsmx.setMxywlsh(stlDataCreateDto.getTradeId());
    	if (StringUtil.equals(stlDataCreateDto.getPs(), DictCodeList.交易方向_买入.getValue())) {
    		szdSjsmx.setMxcjsl(new BigDecimal(stlDataCreateDto.getShareAmt()));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdSjsmx.setMxqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getFpriceAmt()), new BigDecimal(-1)));
    		}
    		else {
    			szdSjsmx.setMxqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    		}
    		szdSjsmx.setMxsfje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    	}
    	else {
    		szdSjsmx.setMxcjsl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-1)));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		if (stlDataCreateDto.getFpriceAmt() != null) {
    			szdSjsmx.setMxqsbj(new BigDecimal(stlDataCreateDto.getFpriceAmt()));
    		}
    		else {
    			szdSjsmx.setMxqsbj(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    		}
    		szdSjsmx.setMxsfje(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    	}
    	szdSjsmx.setMxcjjg(new BigDecimal(stlDataCreateDto.getUnitCprice()));
    	if (stlDataCreateDto.getUnitFprice() != null) {
    		szdSjsmx.setMxqsjg(new BigDecimal(stlDataCreateDto.getUnitFprice()));
    	}
    	else {
    		szdSjsmx.setMxqsjg(new BigDecimal(stlDataCreateDto.getUnitCprice()));
    	}
    	szdSjsmx.setMxxyjy(StlDataCreateConstant.SJSMX_MXXYJY_1);
    	szdSjsmx.setMxgfxz(StlDataDealProcessConstant.SJSMX_GFXZ_00);
    	szdSjsmx.setMxjsfs(StlDataDealProcessConstant.SJSMX_JSFS_A);
    	szdSjsmx.setMxhbdh(stlDataCreateDto.getCcy());
    	szdSjsmx.setMxcjrq(stlDataCreateDto.getTradeDate());
    	szdSjsmx.setMxqsrq(stlDataCreateDto.getSettleDate());
    	szdSjsmx.setMxjsrq(stlDataCreateDto.getSettleDate());
    	if (StringUtil.equals(stlDataCreateDto.getTradePlat(), DictCodeList.交易平台_竞价撮合平台.getValue())) {
    		szdSjsmx.setMxjyfs(StlDataDealProcessConstant.SJSMX_JYFS_01);
    	}
    	else {
    		szdSjsmx.setMxjyfs(StlDataDealProcessConstant.SJSMX_JYFS_02);
    	}
    	szdSjsmx.setMxzqdm2(stlDataCreateDto.getFinprodMarketId());
    	return szdSjsmx;
    }
    
    /**
     * 生成jsmx02-回购的SzdJsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdJsmx
     */
    private SzdJsmx generatorJsmx02Repo(StlDataCreateDto stlDataCreateDto) {
    	SzdJsmx szdJsmx = new SzdJsmx();
    	szdJsmx.setScdm(StlDataDealProcessConstant.SCDM_01);
    	szdJsmx.setJllx(StlDataDealProcessConstant.JLLX_001);
    	szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_001);
    	szdJsmx.setJsfs(StlDataDealProcessConstant.JSFS_001);
    	szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_023);
    	szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_02A);
    	szdJsmx.setGhlx(StlDataDealProcessConstant.GHLX_00A);
    	szdJsmx.setCjbh(stlDataCreateDto.getTradeId());
    	szdJsmx.setSqbh(stlDataCreateDto.getOrderNo());
    	szdJsmx.setJyrq(stlDataCreateDto.getTradeDate());
    	szdJsmx.setQsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setJsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setQtrq(stlDataCreateDto.getmPayDate());
    	szdJsmx.setXwh1(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setXwh2(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setZqzh(stlDataCreateDto.getSecAcctId());
    	szdJsmx.setZqdm1(stlDataCreateDto.getTradeFinprodId());
    	if (StringUtil.equals(stlDataCreateDto.getType7(), DictCodeList.回购拆借方向_正回购.getValue())) {
    		szdJsmx.setZqdm2(StlDataCreateConstant.ZQDM2_888880);
    		szdJsmx.setZqlb(StlDataDealProcessConstant.ZQLB_GZ);
        	szdJsmx.setLtlx(StlDataDealProcessConstant.LTLX_0);
    	}
    	if (StringUtil.equals(stlDataCreateDto.getType7(), DictCodeList.回购拆借方向_正回购.getValue())) {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_B);
    		szdJsmx.setSl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-100)).toString());
    		szdJsmx.setCjsl(szdJsmx.getSl());
    		szdJsmx.setQsje(stlDataCreateDto.getTradeAmt());
    		szdJsmx.setSjsf(stlDataCreateDto.getTradeAmt());
    	}
    	else {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_S);
    		szdJsmx.setCjsl(stlDataCreateDto.getTradeAmt());
    		szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    		szdJsmx.setSjsf(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    	}
    	szdJsmx.setZjzh(stlDataCreateDto.getTrdAcctId());
    	szdJsmx.setBz(stlDataCreateDto.getCcy());
    	szdJsmx.setJg1(StlDataCreateConstant.JG_100);
    	if (StringUtil.isNotEmpty(stlDataCreateDto.getIntRate())) {
    		szdJsmx.setJg2(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getIntRate()), new BigDecimal(100)).toString());
    	}
    	szdJsmx.setYhs(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJsf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setGhf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setZgf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setSxf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje1(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje2(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje3(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJgdm(StlDataDealProcessConstant.JGDM_0000);
    	return szdJsmx;
    }
    
    /**
     * 生成jsmx03-回购的SzdJsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdJsmx
     */
    private SzdJsmx generatorJsmx03Repo(StlDataCreateDto stlDataCreateDto) {
    	SzdJsmx szdJsmx = new SzdJsmx();
    	szdJsmx.setScdm(StlDataDealProcessConstant.SCDM_01);
    	szdJsmx.setJllx(StlDataDealProcessConstant.JLLX_003);
    	szdJsmx.setJyfs(StlDataDealProcessConstant.JYFS_106);
    	szdJsmx.setJsfs(StlDataDealProcessConstant.JSFS_002);
    	szdJsmx.setYwlx(StlDataDealProcessConstant.YWLX_619);
    	szdJsmx.setQsbz(StlDataDealProcessConstant.QSBZ_02C);
    	szdJsmx.setGhlx(StlDataCreateConstant.GHLX_00C);
    	szdJsmx.setCjbh(stlDataCreateDto.getTradeId());
    	szdJsmx.setSqbh(stlDataCreateDto.getOrderNo());
    	szdJsmx.setJyrq(stlDataCreateDto.getTradeDate());
    	szdJsmx.setQsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setJsrq(stlDataCreateDto.getSettleDate());
    	szdJsmx.setQtrq(stlDataCreateDto.getmPayDate());
    	szdJsmx.setXwh1(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setXwh2(stlDataCreateDto.getShSeatNo());
    	szdJsmx.setZqzh(stlDataCreateDto.getSecAcctId());
    	szdJsmx.setZqdm1(stlDataCreateDto.getFinprodId());
    	szdJsmx.setZqdm2(stlDataCreateDto.getPledgeFinprodId());
		szdJsmx.setZqlb(StlDataDealProcessConstant.ZQLB_GZ);
    	szdJsmx.setLtlx(StlDataDealProcessConstant.LTLX_0);
    	if (StringUtil.equals(stlDataCreateDto.getType7(), DictCodeList.回购拆借方向_正回购.getValue())) {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_B);
    		szdJsmx.setCjsl(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-1)).toString());
    		szdJsmx.setQsje(stlDataCreateDto.getShareAmt());
    		szdJsmx.setSjsf(stlDataCreateDto.getTradeAmt());
    	}
    	else {
    		szdJsmx.setMmbz(StlDataDealProcessConstant.MMBS_S);
    		szdJsmx.setCjsl(stlDataCreateDto.getShareAmt());
    		szdJsmx.setQsje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getShareAmt()), new BigDecimal(-1)).toString());
    		szdJsmx.setSjsf(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)).toString());
    	}
    	szdJsmx.setZjzh(stlDataCreateDto.getTrdAcctId());
    	szdJsmx.setBz(stlDataCreateDto.getCcy());
    	if (StringUtil.isNotEmpty(stlDataCreateDto.getIntRate())) {
    		szdJsmx.setJg1(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getIntRate()), new BigDecimal(100)).toString());
    	}
    	szdJsmx.setJg2(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setYhs(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJsf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setGhf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setZgf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setSxf(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje1(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje2(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setQtje3(StlDataCreateConstant.JSMX_0);
    	szdJsmx.setJgdm(StlDataDealProcessConstant.JGDM_0000);
    	return szdJsmx;
    }
    
    /**
     * 生成sjsmx1-回购的SzdSjsmx
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdSjsmx
     */
    private SzdSjsmx generatorSjsmx1Repo(StlDataCreateDto stlDataCreateDto) {
    	SzdSjsmx szdSjsmx = new SzdSjsmx();
    	szdSjsmx.setMxbfzh(stlDataCreateDto.getTrdAcctId());
    	szdSjsmx.setMxsjlx(StlDataCreateConstant.SJSMX_MXSJLX_01);
    	szdSjsmx.setMxywlb(StlDataDealProcessConstant.SJSMX_YWLB_HGCS);
    	szdSjsmx.setMxzqdm(stlDataCreateDto.getTradeFinprodId());
    	szdSjsmx.setMxjydy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxtgdy(stlDataCreateDto.getSzSeatNo());
    	szdSjsmx.setMxzqzh(stlDataCreateDto.getSecAcctId());
    	szdSjsmx.setMxddbh(stlDataCreateDto.getOrderNo());
    	szdSjsmx.setMxywlsh(stlDataCreateDto.getTradeId());
    	if (StringUtil.equals(stlDataCreateDto.getType7(), DictCodeList.回购拆借方向_正回购.getValue())) {
    		szdSjsmx.setMxcjsl(ArithmeticUtil.multiply(ArithmeticUtil.divide(new BigDecimal(stlDataCreateDto.getPrinAmt()), new BigDecimal(100), 2), new BigDecimal(-1)));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		szdSjsmx.setMxqsbj(new BigDecimal(stlDataCreateDto.getPrinAmt()));
    		szdSjsmx.setMxsfje(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    	}
    	else {
    		szdSjsmx.setMxcjsl(ArithmeticUtil.divide(new BigDecimal(stlDataCreateDto.getPrinAmt()), new BigDecimal(100), 2));
    		szdSjsmx.setMxqssl(szdSjsmx.getMxcjsl());
    		szdSjsmx.setMxqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getPrinAmt()), new BigDecimal(-1)));
    		szdSjsmx.setMxsfje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    	}
    	if (StringUtil.isNotEmpty(stlDataCreateDto.getIntRate())) {
    		szdSjsmx.setMxcjjg(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getIntRate()), new BigDecimal(100)));
    	}
    	szdSjsmx.setMxqsjg(new BigDecimal(StlDataCreateConstant.JG_100));
    	szdSjsmx.setMxzqlb(StlDataCreateConstant.SJSMX_ZQLB_13);
    	szdSjsmx.setMxgfxz(StlDataDealProcessConstant.SJSMX_GFXZ_00);
    	szdSjsmx.setMxjsfs(StlDataDealProcessConstant.SJSMX_JSFS_A);
    	szdSjsmx.setMxhbdh(stlDataCreateDto.getCcy());
    	szdSjsmx.setMxcjrq(stlDataCreateDto.getTradeDate());
    	szdSjsmx.setMxqsrq(stlDataCreateDto.getSettleDate());
    	szdSjsmx.setMxjsrq(stlDataCreateDto.getSettleDate());
    	szdSjsmx.setMxqtrq(stlDataCreateDto.getmPayDate());
    	szdSjsmx.setMxjyfs(StlDataDealProcessConstant.SJSMX_JYFS_02);
    	szdSjsmx.setMxzqdm2(stlDataCreateDto.getPledgeFinprodId());
    	return szdSjsmx;
    }
    
    /**
     * 生成sjsjg-回购的SzdSjsjg
     * @param stlDataCreateDto StlDataCreateDto
     * @return SzdSjsjg
     */
    private SzdSjsjg generatorSjsjgRepo(StlDataCreateDto stlDataCreateDto) {
    	SzdSjsjg szdSjsjg = new SzdSjsjg();
    	szdSjsjg.setJgbfzh(stlDataCreateDto.getTrdAcctId());
    	szdSjsjg.setJgsjlx(StlDataCreateConstant.SJSMX_MXSJLX_01);
    	szdSjsjg.setJgywlb(StlDataCreateConstant.SJSJG_YWLB_XYCS);
    	szdSjsjg.setJgzqdm(stlDataCreateDto.getFinprodId());
    	szdSjsjg.setJgjydy(stlDataCreateDto.getSzSeatNo());
    	szdSjsjg.setJgtgdy(stlDataCreateDto.getSzSeatNo());
    	szdSjsjg.setJgzqzh(stlDataCreateDto.getSecAcctId());
    	szdSjsjg.setJgddbh(stlDataCreateDto.getOrderNo());
    	szdSjsjg.setJgywlsh(stlDataCreateDto.getTradeId());
    	if (StringUtil.equals(stlDataCreateDto.getType7(), DictCodeList.回购拆借方向_正回购.getValue())) {
    		szdSjsjg.setJgcjsl(ArithmeticUtil.multiply(ArithmeticUtil.divide(new BigDecimal(stlDataCreateDto.getPrinAmt()), new BigDecimal(100), 2), new BigDecimal(-1)));
    		szdSjsjg.setJgqssl(szdSjsjg.getJgcjsl());
    		szdSjsjg.setJgqsbj(new BigDecimal(stlDataCreateDto.getPrinAmt()));
    		szdSjsjg.setJgsfje(new BigDecimal(stlDataCreateDto.getTradeAmt()));
    	}
    	else {
    		szdSjsjg.setJgcjsl(ArithmeticUtil.divide(new BigDecimal(stlDataCreateDto.getPrinAmt()), new BigDecimal(100), 2));
    		szdSjsjg.setJgqssl(szdSjsjg.getJgcjsl());
    		szdSjsjg.setJgqsbj(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getPrinAmt()), new BigDecimal(-1)));
    		szdSjsjg.setJgsfje(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getTradeAmt()), new BigDecimal(-1)));
    	}
    	if (StringUtil.isNotEmpty(stlDataCreateDto.getIntRate())) {
	    	szdSjsjg.setJgcjjg(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getIntRate()), new BigDecimal(100)));
	    	szdSjsjg.setJgqsjg(ArithmeticUtil.multiply(new BigDecimal(stlDataCreateDto.getIntRate()), new BigDecimal(100)));
    	}
    	szdSjsjg.setJgzqlb(StlDataCreateConstant.SJSMX_ZQLB_13);
    	szdSjsjg.setJggfxz(StlDataDealProcessConstant.SJSMX_GFXZ_00);
    	szdSjsjg.setJgjsfs(StlDataDealProcessConstant.SJSMX_JSFS_N);
    	szdSjsjg.setJghbdh(stlDataCreateDto.getCcy());
    	szdSjsjg.setJgjsbz(StlDataCreateConstant.SJSJG_JSBZ_Y);
    	szdSjsjg.setJgcjrq(stlDataCreateDto.getTradeDate());
    	szdSjsjg.setJgqsrq(stlDataCreateDto.getSettleDate());
    	szdSjsjg.setJgjsrq(stlDataCreateDto.getSettleDate());
    	szdSjsjg.setJgqtrq(stlDataCreateDto.getmPayDate());
    	szdSjsjg.setJgjyfs(StlDataDealProcessConstant.SJSMX_JYFS_02);
    	szdSjsjg.setJgzqdm2(stlDataCreateDto.getPledgeFinprodId());
    	return szdSjsjg;
    }
}
