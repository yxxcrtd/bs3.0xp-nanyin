package com.joyintech.service.valuation.basedata.marketdata.valbondquotationinfo.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.MstBondValuationInfoMapper;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.tams.common.base.entity.MstBondValuationInfoKey;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.basedata.marketdata.valbondquotationinfo.dao.ValBondQuotationInfoMapper;
import com.joyintech.service.valuation.basedata.marketdata.valbondquotationinfo.dto.ValBondQuotationInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * @author wangzehui
 */
@Service("backValBondQuotationInfoService")
public class ValBondQuotationInfoService extends BaseService<ValBondQuotationInfoDto>{
    /**
     * 定义mapper(mine)
     */
    @Autowired
    private ValBondQuotationInfoMapper valBondQuotationInfoMapper;

    /**
     * 定义mapper(系统生成)
     */
    @Autowired
    private MstBondValuationInfoMapper mstBondValuationInfoMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<ValBondQuotationInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return super.queryForPage(page, length, () -> valBondQuotationInfoMapper.select(pageParam));
    }
    /**
     * 资产代码的联想下拉框
     * @param param param
     * @return List
     * @throws Exception 异常
     */
    public List<SuggestDto> getSecIdOption(String secId) {
        Map<String, Object> param = new HashMap<>(2);
        // 获取前台输入的值
        param.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("secId", secId);
        return valBondQuotationInfoMapper.getSecIdOption(param);
    }

    /**
     * 查询债券简称
     * 
     * @return List
     */
    public List<SelectOptionDto> getFinprodAbbrOption(Map<String, Object> map) {
    	map.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        return valBondQuotationInfoMapper.getFinprodAbbrOption(map);
    }


    /**
     * 删除操作
     */
    public int delete(MstBondValuationInfo entity) {
        return mstBondValuationInfoMapper.deleteByPrimaryKey(entity);
    }
    
    /**
     * 删除操作
     */
    public int insert(MstBondValuationInfo entity) {
        // 入库时市价收益率除100 by 赵东东 2019.11.4 start
    	if(StringUtil.isNotEmpty(entity.getBpyield())){
        entity.setBpyield(entity.getBpyield().divide(new BigDecimal(100)));
        }
        // 入库时市价收益率除100 by 赵东东 2019.11.4 end
        return mstBondValuationInfoMapper.insert(entity);
    }
    
    /**
     * 更新操作
     */
    public int update(MstBondValuationInfo entity) {
        // 入库时市价收益率除100 by 赵东东 2019.11.4 start
    	if(StringUtil.isNotEmpty(entity.getBpyield())){
        entity.setBpyield(entity.getBpyield().divide(new BigDecimal(100)));
        }
        // 入库时市价收益率除100 by 赵东东 2019.11.4 end
        return mstBondValuationInfoMapper.updateByPrimaryKeySelective(entity);
    }
//
//    /**
//     * 导出
//     */
//    @Override
//    protected void buildExportParam(ExportParam exportParam) {
//        exportParam.config(
//            "/com/joyintech/basicdata/mstbondquotationinfo/conf/export_conf.xml").select(
//                () -> valBondQuotationInfoMapper.select(exportParam.getSelectParam())).dataSource(
//                    DataSourceType.MASERT);
//    }

    /**
     * 获取金融产品简称
     * 
     * @param param finProdAbbr
     * @return List
     */
    public List<SuggestDto> getFinProdAbbr(String finprodAbbr) {
        Map<String, Object> param = new HashMap<>();
        param.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("finprodAbbr", finprodAbbr);
        return valBondQuotationInfoMapper.getFinProdAbbr(param);
    }

    /**
     * @param secId secId
     * @return dto
     */
    public MstBondValuationInfo getBySecId(String secId) {
        return valBondQuotationInfoMapper.getBySecId(secId);
    }
    /**
     * 
     * @param map map
     * @return dto
     * @throws InvocationTargetException 
     * @throws IllegalAccessException 
     */
    public ValBondQuotationInfoDto selectByKey(String secId, String valueDate,String valueSource) throws IllegalAccessException, InvocationTargetException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("secId", secId);
        map.put("valueDate", valueDate);
        map.put("valueSource", valueSource);
        return valBondQuotationInfoMapper.selectByKey(map);
    }

    /**
     * 新建画面的资产代码查询
     * <功能详细描述>
     * @param param param
     * @return list
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getSecIdToNew(String secId) {
        Map<String, Object> param = new HashMap<>();
        param.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        // 获取前台输入的值
        param.put("secId", secId);
        return valBondQuotationInfoMapper.getSecIdToNew(param);
    }

    public List<SelectOptionDto> getAssetCodeList() {
        return valBondQuotationInfoMapper.getAssetCodeList();
    }
    
}
