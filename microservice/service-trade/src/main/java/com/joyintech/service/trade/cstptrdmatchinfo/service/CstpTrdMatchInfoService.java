package com.joyintech.service.trade.cstptrdmatchinfo.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.trade.cstptrdmatchinfo.dao.CstpTrdMatchInfoMapper;
import com.joyintech.service.trade.cstptrdmatchinfo.dto.CstpTrdMatchInfoDto;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;

/**
 * @ClassName TrdMatchInfoService
 * @Description: TODO
 * @Author wangxuelei
 * @Date 2020/1/9 0009
 * @Version V1.0
 **/
@Service("backCstpTrdMatchInfoService")
public class CstpTrdMatchInfoService extends BaseService<CstpTrdMatchInfoDto> {

    @Autowired
    private CstpTrdMatchInfoMapper cstpTrdMatchInfoMapper;
    /**
     * 标的类金融产品mapper
     */
    @Autowired
    private FinProductMapper finProductMapper;

    public Page<CstpTrdMatchInfoDto> findPage(int page, int length, Map<String,Object> map) throws Exception{
    	Page<CstpTrdMatchInfoDto> pageLimit =  super.queryForPage(page, length, () -> cstpTrdMatchInfoMapper.findPage(map));
        finprodType2PinAn(pageLimit.getRoot());
        return pageLimit;
    }
    
	/**
	 * 将金融产品类型转为平安数据字典
	 * <功能详细描述>
	 * @param list
	 * @return
	 * @see
	 * @since 1.0
	 */
	public void finprodType2PinAn(List<CstpTrdMatchInfoDto> list){
	    if(!list.isEmpty()){
            for(CstpTrdMatchInfoDto dto : list){
            	if (dto.getTradeType().equals("01")) {
	                String finType2=dto.getFinprodType();
	                if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
	                    dto.setFinprodType(DictCodeList.平安资产类型_债券.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_基金.getValue())){
	                    dto.setFinprodType(DictCodeList.平安资产类型_基金.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_存款.getValue())){
	                    dto.setFinprodType(DictCodeList.平安资产类型_存款.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_拆入.getValue())
	                        || StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_拆出.getValue())){
	                    dto.setFinprodType(DictCodeList.平安资产类型_拆借.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
	                        || StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())){
	                    dto.setFinprodType(DictCodeList.平安资产类型_质押式回购.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue())
	                        || StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue())){
	                    dto.setFinprodType(DictCodeList.平安资产类型_买断式回购.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_项目类资产.getValue())){
	                    dto.setFinprodType(DictCodeList.平安资产类型_项目类资产.getValue());
	                }
	                else if(StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
	                    FinProduct finProduct=finProductMapper.selectByPrimaryKey(dto.getFinprodId());
	                    if(StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_净值型.getValue())){
	                        dto.setFinprodType(DictCodeList.平安资产类型_计划类资产_净值型.getValue());
	                    }
	                    else if(StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_收益型.getValue())){
	                        dto.setFinprodType(DictCodeList.平安资产类型_计划类资产_利率型.getValue());
	                    }else if(StringUtil.equals(finProduct.getProfitType(), DictCodeList.收益类型_货币型.getValue())){
	                        dto.setFinprodType(DictCodeList.平安资产类型_计划类资产_货币型.getValue());
	                    }
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_即期.getValue())) {
	                    dto.setFinprodType(DictCodeListExt2.平安资产类型_外汇即期.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_远期.getValue())) {
	                    dto.setFinprodType(DictCodeListExt2.平安资产类型_外汇远期.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_掉期.getValue())) {
	                    dto.setFinprodType(DictCodeListExt2.平安资产类型_外汇掉期.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券借贷融入.getValue())) {
	                    dto.setFinprodType(DictCodeListExt2.平安资产类型_债券借贷.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_债券借贷融出.getValue())) {
	                    dto.setFinprodType(DictCodeListExt2.平安资产类型_债券借贷.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_利率互换.getValue())) {
	                    dto.setFinprodType(DictCodeListExt2.平安资产类型_利率互换.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
	                    dto.setFinprodType(DictCodeListExt2.平安资产类型_股票.getValue());
	                }
	                else if (StringUtil.equals(finType2, DictCodeListExt2.金融产品类型_投管用_一级投标债券.getValue())) {
	                    dto.setFinprodType(DictCodeListExt2.平安资产类型_一级市场债.getValue());
	                }
            	}
            }    
        }
	}
}
