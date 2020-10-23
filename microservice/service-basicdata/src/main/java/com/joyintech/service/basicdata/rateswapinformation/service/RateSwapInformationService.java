package com.joyintech.service.basicdata.rateswapinformation.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.service.basicdata.rateswapinformation.dao.RateSwapInformationMapper;
import com.joyintech.service.basicdata.rateswapinformation.dto.RateSwapInformationDto;
import com.joyintech.service.basicdata.yieldcurveinfo.dao.MstYieldCurveInfo001Mapper;
import com.joyintech.service.basicdata.yieldcurveinfo.dto.MstYieldCurveInfoDto;
import com.joyintech.tams.common.base.dao.MstIrsQuotationMapper;
import com.joyintech.tams.common.base.dao.MstYieldCurveInfoMapper;
import com.joyintech.tams.common.base.entity.MstIrsQuotation;
import com.joyintech.tams.common.base.entity.MstYieldCurveInfo;
import com.joyintech.tams.common.base.entity.MstIrsQuotationKey;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("backRateSwapInformationService")
public class RateSwapInformationService extends BaseService<RateSwapInformationDto> {

    @Autowired
    private RateSwapInformationMapper rateSwapInformationMapper;

	@Autowired
	private MstYieldCurveInfoMapper mstyieldcurveinfomapper;
	
	@Autowired
	private MstYieldCurveInfo001Mapper mstyieldcurveinfo001mapper;

    @Autowired
    private MstIrsQuotationMapper mstIrsQuotationMapper;

    /**
     * 判断新增时数据库中是否存在相同的记录
     * @param dto
     * @return
     */
    public List<MstYieldCurveInfoDto> check(RateSwapInformationDto dto) {
        Map<String,Object> param = new HashMap<String,Object>();
        //区分页面展示的曲线品种和真正要入库的曲线代码，dto.curveCode 中存的是三个字段连接的品种代码 
        param.put("curveCode", dto.getRateSwapInformationDtoList().get(0).getCurveCode());
        param.put("curveType", dto.getCurveType());
        param.put("curveDate", new SimpleDateFormat("yyyy-MM-dd").format(dto.getCurveDate()));
        List<MstYieldCurveInfoDto> list = mstyieldcurveinfo001mapper.selectByParams(param);
        return list;
    }

    /**
     * 利率行情信息入库
     * @param entity
     * @param operateType
     */
    public void insert(RateSwapInformationDto entity, String operateType) {
        List<RateSwapInformationDto> list = entity.getRateSwapInformationDtoList();
        for (RateSwapInformationDto dto : list){
        	MstYieldCurveInfo mstYieldCurveInfo = new MstYieldCurveInfo();
            BeanUtil.copyPropertiesIgnoreNull(mstYieldCurveInfo, dto);
            mstYieldCurveInfo.setInputType(DictCodeListExt1.录入方式_手工录入.getValue());
            mstYieldCurveInfo.setYield(mstYieldCurveInfo.getYield().divide(new BigDecimal(100)));
            //原期限品种转换成关键年限点
            String termTypeIrs = dto.getTermTypeIrs();
            if(termTypeIrs.endsWith("M")) {
            	termTypeIrs = termTypeIrs.replace("M", "");
            	mstYieldCurveInfo.setKeyPoint(new BigDecimal(Float.parseFloat(termTypeIrs)/12).setScale(14, BigDecimal.ROUND_HALF_UP));
            }else if(termTypeIrs.endsWith("Y")) {
            	termTypeIrs = termTypeIrs.replace("Y", "");
            	mstYieldCurveInfo.setKeyPoint(new BigDecimal(termTypeIrs));
            }
            mstyieldcurveinfomapper.insertSelective(mstYieldCurveInfo);
			/*
			 * MstIrsQuotation mstIrsQuotation = new MstIrsQuotation();
			 * BeanUtil.copyPropertiesIgnoreNull(mstIrsQuotation, dto);
			 * mstIrsQuotation.setUpdateTime(new Date()); mstIrsQuotation.setCreateTime(new
			 * Date()); mstIrsQuotation.setBidPrice(dto.getBidPrice().divide(new
			 * BigDecimal(100))); mstIrsQuotation.setAvgPrice(dto.getAvgPrice().divide(new
			 * BigDecimal(100)));
			 * mstIrsQuotation.setOfferPrice(dto.getOfferPrice().divide(new
			 * BigDecimal(100))); mstIrsQuotation.setCurveCode(entity.getCurveCode());
			 * mstIrsQuotation.setCurveDate(entity.getCurveDate());
			 * mstIrsQuotation.setCurveType(" "); //曲线类型已经在curveCode中带入
			 * mstIrsQuotation.setCurveIntType(DictCodeListExt2.曲线利率类型_即期.getValue());
			 * mstIrsQuotation.setDataTime(DictCodeListExt2.利率互换曲线时刻_16_30.getValue());
			 * mstIrsQuotation.setCcy(DictCodeList.币种_人民币_CNY.getValue());
			 * mstIrsQuotation.setInputType(DictCodeListExt1.录入方式_手工录入.getValue());
			 * mstIrsQuotationMapper.insertSelective(mstIrsQuotation);
			 */
        }
    }

    /**
     * 利率行情信息编辑
     * @param entity
     * @param operateType
     */
    public void update(RateSwapInformationDto entity, String operateType) {
        List<RateSwapInformationDto> list = entity.getRateSwapInformationDtoList();
        for (RateSwapInformationDto dto : list){
        	MstYieldCurveInfo mstYieldCurveInfo = new MstYieldCurveInfo();
            BeanUtil.copyPropertiesIgnoreNull(mstYieldCurveInfo, dto);
            mstYieldCurveInfo.setInputType(DictCodeListExt1.录入方式_手工录入.getValue());
            mstYieldCurveInfo.setYield(mstYieldCurveInfo.getYield().divide(new BigDecimal(100)));
            //原期限品种转换成关键年限点
            String termTypeIrs = dto.getTermTypeIrs();
            if(termTypeIrs.endsWith("M")) {
            	termTypeIrs = termTypeIrs.replace("M", "");
            	mstYieldCurveInfo.setKeyPoint(new BigDecimal(Float.parseFloat(termTypeIrs)/12).setScale(14, BigDecimal.ROUND_HALF_UP));
            }else if(termTypeIrs.endsWith("Y")) {
            	termTypeIrs = termTypeIrs.replace("Y", "");
            	mstYieldCurveInfo.setKeyPoint(new BigDecimal(termTypeIrs));
            }
            mstYieldCurveInfo.setUpdateTime(new Date());
            mstyieldcurveinfomapper.updateByPrimaryKeySelective(mstYieldCurveInfo);
        }
//        for (RateSwapInformationDto dto : list){
//            MstIrsQuotation mstIrsQuotation = new MstIrsQuotation();
//            BeanUtil.copyPropertiesIgnoreNull(mstIrsQuotation, dto);
//            mstIrsQuotation.setUpdateTime(new Date());
//            mstIrsQuotation.setBidPrice(dto.getBidPrice().divide(new BigDecimal(100)));
//            mstIrsQuotation.setAvgPrice(dto.getAvgPrice().divide(new BigDecimal(100)));
//            mstIrsQuotation.setOfferPrice(dto.getOfferPrice().divide(new BigDecimal(100)));
//            mstIrsQuotationMapper.updateByPrimaryKeySelective(mstIrsQuotation);
//        }
    }

    /**
     * 利率行情信息删除
     * @param entity
     * @param operateType
     */
    public void delete(RateSwapInformationDto entity, String operateType) {
        List<RateSwapInformationDto> list = entity.getRateSwapInformationDtoList();
        for (RateSwapInformationDto dto : list){
        	MstYieldCurveInfo mstYieldCurveInfo = new MstYieldCurveInfo();
            mstYieldCurveInfo.setCurveCode(dto.getCurveCode());
            mstYieldCurveInfo.setCurveDate(dto.getCurveDate());
            //原期限品种转换成关键年限点
            String termTypeIrs = dto.getTermTypeIrs();
            if(termTypeIrs.endsWith("M")) {
            	termTypeIrs = termTypeIrs.replace("M", "");
            	mstYieldCurveInfo.setKeyPoint(new BigDecimal(Float.parseFloat(termTypeIrs)/12).setScale(14, BigDecimal.ROUND_HALF_UP));
            }else if(termTypeIrs.endsWith("Y")) {
            	termTypeIrs = termTypeIrs.replace("Y", "");
            	mstYieldCurveInfo.setKeyPoint(new BigDecimal(termTypeIrs));
            }
            mstyieldcurveinfomapper.deleteByPrimaryKey(mstYieldCurveInfo);
        }
//        for (RateSwapInformationDto dto : list){
//            MstIrsQuotationKey mstIrsQuotationKey = new MstIrsQuotationKey();
//            BeanUtil.copyPropertiesIgnoreNull(mstIrsQuotationKey, dto);
//            mstIrsQuotationKey.setCurveCode(entity.getCurveCode());
//            mstIrsQuotationKey.setCurveDate(entity.getCurveDate());
//            mstIrsQuotationMapper.deleteByPrimaryKey(mstIrsQuotationKey);
//        }

    }

    public List<RateSwapInformationDto> selectByParams(RateSwapInformationDto dto) {
        List<MstYieldCurveInfo> list = rateSwapInformationMapper.selectByParams(dto);
        List<RateSwapInformationDto> rateSwapInformationDtoList = new ArrayList<RateSwapInformationDto>();
        for (MstYieldCurveInfo mstYieldCurveInfo : list){
            RateSwapInformationDto rateSwapInformationDto = new RateSwapInformationDto();
            BeanUtil.copyPropertiesIgnoreNull(rateSwapInformationDto, mstYieldCurveInfo);
            //关键年限点 转换成 原期限品种
            BigDecimal keyPoint = mstYieldCurveInfo.getKeyPoint();
            if(keyPoint.compareTo(BigDecimal.ONE) == -1) {
            	keyPoint = keyPoint.multiply(new BigDecimal(12));
            	int k = keyPoint.intValue();
            	rateSwapInformationDto.setTermTypeIrs(k+"M");
            }else{
            	int k = keyPoint.intValue();
            	rateSwapInformationDto.setTermTypeIrs(k+"Y");
            }
            rateSwapInformationDtoList.add(rateSwapInformationDto);
        }
        return rateSwapInformationDtoList;
    }


    /**
     *@title: getCurveType
     *@description: 获取利率互换曲线类型（名称）
     *@author: 王贵奇
     *@date: 2019/09/27
     *@return: Page<SelectOptionDto>
     *@throws:
     */
	public Page<SelectOptionDto> getCurveType(int page, int length,Map<String, Object> param) {
        param.put("CURVE_TYPE_02", DictCodeListExt2.曲线类别_利率互换曲线.getValue());
        param.put("CURVE_CLASS", DictCode.曲线类别.getValue());
        param.put("IRS_CURVE_TYPE",  DictCode.利率互换曲线.getValue());
        param.put("PRICE_TYPE",  DictCode.价格类型.getValue());
        return this.queryForPageForSelectReturn(page, length,()->rateSwapInformationMapper.getCurveType(param));
    }
}
