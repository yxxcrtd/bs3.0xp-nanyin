package com.joyintech.service.product.productduration.netproductbonusscheme.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productduration.netproductbonusscheme.dao.FinNetProductBonusSchemeMapper;
import com.joyintech.service.product.productduration.netproductbonusscheme.dto.FinNetProductBonusDealDto;
import com.joyintech.service.product.productduration.netproductbonusscheme.dto.FinNetProductBonusSchemeDto;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.dao.PrdBonusPlanNMapper;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.common.base.entity.TrdFinProductDealTotal;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 净值型产品分红方案Service
 *
 * @author liuwei
 * @version 1.0 2018年9月14日
 * @see FinNetProductBonusSchemeService
 * @since 1.0
 */
@Service("backFinNetProductBonusSchemeService")
public class FinNetProductBonusSchemeService extends BaseService<FinNetProductBonusSchemeDto> {
    /**
     * 净值型产品分红方案mapper
     */
    @Autowired
    private FinNetProductBonusSchemeMapper finNetProductBonusSchemeMapper;
    /**
     * 净值型产品分红方案表mapper
     */
    @Autowired
    private PrdBonusPlanNMapper prdBonusPlanNMapper;
    /**
     * 标的类金融产品附属表mapper
     */
    @Autowired
    private FinProductAddMapper finProductAddMapper;
    
    /**
     * 产品代码的联想下拉框事件
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketId(Map<String,Object> map) {
        map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("PROFIT_TYPE_01",DictCodeList.收益类型_净值型.getValue());
        return finNetProductBonusSchemeMapper.getFinprodMarketId(map);
    }

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<FinNetProductBonusSchemeDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {        
        return super.queryForPage(page, length, () -> finNetProductBonusSchemeMapper.select(pageParam));
    }

    /**
     * 删除事件
     * @param dto dto
     * @return 删除结果
     */
    public int delete(FinNetProductBonusSchemeDto dto){
        return prdBonusPlanNMapper.deleteByPrimaryKey(dto.getBonusPlanId());
    }

    /**
     * 产品代码的下拉框事件
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodMarketIdList(Map<String,Object> map) {
        map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("PROFIT_TYPE_01",DictCodeList.收益类型_净值型.getValue());
        return finNetProductBonusSchemeMapper.getFinprodMarketIdList(map);
    }

    /**
     * 查询产品简称和产品名称
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public FinNetProductBonusSchemeDto getAbbrAndName(Map<String,Object> map) {
        map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("PROFIT_TYPE_01",DictCodeList.收益类型_净值型.getValue());
        return finNetProductBonusSchemeMapper.getAbbrAndName(map);
    }

    /**
     * 插入操作
     *
     * @param dto 选中参数
     * @see
     * @since 1.0
     */
    public int insert(FinNetProductBonusSchemeDto dto) {
        return prdBonusPlanNMapper.insertSelective(dto);
    }

    /**
     * 修改操作
     *
     * @param dto 选中参数
     * @see
     * @since 1.0
     */
    public int update(FinNetProductBonusSchemeDto dto) {
        return prdBonusPlanNMapper.updateByPrimaryKey(dto);
    }

    /**
     * 查询分红方案编号
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public int selectBonuScalcFlag(Map<String,Object> map) {
        return finNetProductBonusSchemeMapper.selectBonuScalcFlag(map);
    }

    /**
     * 产品是否已派发红利
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public String IsDividend(Map<String,Object> map) {
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("YES_OR_NO_N",DictCodeList.是否_否.getValue());
        map.put("PS_C8",DictCodeList.交易方向_产品除权登记.getValue());
        return finNetProductBonusSchemeMapper.IsDividend(map);
    }

    /**
     * 查询交易日历编号
     *
     * @param finprodId 金融产品代码
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public String selectCalendarId(String finprodId) {
        return finNetProductBonusSchemeMapper.selectCalendarId(finprodId);
    }

    /**
     * 验证同一除权日是否重复登录
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public int countDelDate(Map<String,Object> map) {
        return finNetProductBonusSchemeMapper.countDelDate(map);
    }

    /**
     * 修改或双击查看界面
     *
     * @param bonusPlanId 分红方案编号
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public FinNetProductBonusSchemeDto selectInfo(String bonusPlanId) {
        Map<String,Object> map = new HashMap<>();
        map.put("BUSI_TYPE", DictCodeList.业务类型_净值型TA交易.getValue());
        map.put("FINPROD_TYPE_TG_F16", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("FINPROD_TYPE_TG_F24", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        map.put("PROFIT_TYPE_01",DictCodeList.收益类型_净值型.getValue());
        map.put("bonusPlanId",bonusPlanId);
        return finNetProductBonusSchemeMapper.selectInfo(map);
    }

    /**
     * 查询基准日未分配余额
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public BigDecimal getTdyUnpaidIntincexp(Map<String,Object> map){
        return finNetProductBonusSchemeMapper.getTdyUnpaidIntincexp(map);
    }

    /**
     * 查询净值参考日
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public Date getVluRefDate(Map<String,Object> map){
        return finNetProductBonusSchemeMapper.getVluRefDate(map);
    }

    /**
     * 查询参考日净值和份额
     *
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public FinNetProductBonusSchemeDto getTdyUnitValueAndTdyShareAmt(Map<String,Object> map){
        return finNetProductBonusSchemeMapper.getTdyUnitValueAndTdyShareAmt(map);
    }
    
    /**
     * 查询产品是否存在有效的现金分红或红利再投交易
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public int isExistBonusDeal(Map<String,Object> map){
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("YES_OR_NO_N",DictCodeList.是否_否.getValue());
        return finNetProductBonusSchemeMapper.isExistBonusDeal(map);
    }
    
    /**
     * 查询分红模式
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public String queryBonusMode(Map<String, Object> map) {
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        return finNetProductBonusSchemeMapper.queryBonusMode(map);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<FinNetProductBonusDealDto> queryBonusDetail(Map<String, Object> param) {
        return finNetProductBonusSchemeMapper.queryBonusDetail(param);
    }

	public String queryBonusPlanId(Map<String, Object> map) {
		return finNetProductBonusSchemeMapper.queryBonusPlanId(map);
	}

	/**
	 * 查询 交易明细数据，查看、撤销调用
	 * @param map
	 * @return
	 */
	public FinNetProductBonusSchemeDto selectDetailInfo(Map<String, Object> map) {
		return finNetProductBonusSchemeMapper.selectDetailInfo(map);
	}

	public String idBonusAmtConfirm(Map<String, Object> map) {
		return finNetProductBonusSchemeMapper.idBonusAmtConfirm(map);
	}

	public List<TrdFinProductDealTotal> queryAfterTrdFinProductDealTotal(FinNetProductBonusSchemeDto dto) {
		return finNetProductBonusSchemeMapper.queryAfterTrdFinProductDealTotal(dto);
	}
	
	/**
     * 通过产品id查询 标的类金融产品附属表
     *
     * @param finprodId 产品代码
     * @return 查询结果
     * @see
     * @since 1.0
     */
    public FinProductAdd selectFinProductADD(String finprodId) {
        
        return finProductAddMapper.selectByPrimaryKey(finprodId);
    }
    
    /**
     * 查询产品是否已完全有效的现金分红或红利再投交易
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal isExceedBonusDeal(Map<String,Object> map){
        map.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        map.put("YES_OR_NO_N",DictCodeList.是否_否.getValue());
        return finNetProductBonusSchemeMapper.isExceedBonusDeal(map);
    }
    
    public List<FinNetProductBonusSchemeDto> findCycleDataTableInfo(Map<String, Object> param) 
    		throws Exception {
    	
    	String tradeId  = (String) param.get("tradeId");
    	List<FinNetProductBonusSchemeDto> list = new ArrayList<FinNetProductBonusSchemeDto>();
    	//通过汇总交易编号获取已经做过交易的期次子产品信息
    	if(StringUtil.isNotEmpty(tradeId)) {
    		 list = finNetProductBonusSchemeMapper.findCycleDataTableInfoByTradeId(param);
    	} else {
    	//新建 获取期次子产品信息
    		list = finNetProductBonusSchemeMapper.findCycleDataTableInfo(param);
    	}

    	return list;
    }
    
    public String queryTradeIdByTotle(Map<String, Object> map) { 
        return finNetProductBonusSchemeMapper.queryTradeIdByTotle(map);
    }
}
