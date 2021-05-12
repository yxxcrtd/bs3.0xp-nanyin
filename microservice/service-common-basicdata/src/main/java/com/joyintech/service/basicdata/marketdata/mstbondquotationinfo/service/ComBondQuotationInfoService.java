package com.joyintech.service.basicdata.marketdata.mstbondquotationinfo.service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstbondquotationinfo.dao.ComBondQuotationInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.MstBondValuationInfoMapper;
import com.joyintech.tams.common.base.entity.MstBondValuationInfo;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.marketdata.mstbondquotationinfo.dto.ComBondQuotationInfoDto;

/**
 * @author wangzehui
 */
@Service("serviceComBondQuotationInfoService")
public class ComBondQuotationInfoService extends BaseService<ComBondQuotationInfoDto>{
    /**
     * 定义mapper(mine)
     */
    @Autowired
    private ComBondQuotationInfoMapper comBondQuotationInfoMapper;

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
    public Page<ComBondQuotationInfoDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        pageParam.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        return super.queryForPage(page, length, () -> comBondQuotationInfoMapper.select(pageParam));
    }
    /**
     * 资产代码的联想下拉框
     * @param secId
     * @return List
     * @throws Exception 异常
     */
    public List<SuggestDto> getSecIdOption(String secId) {
        Map<String, Object> param = new HashMap<>(2);
        // 获取前台输入的值
        param.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        param.put("secId", secId);
        return comBondQuotationInfoMapper.getSecIdOption(param);
    }

    /**
     * 查询债券简称
     * 
     * @return List
     */
    public Page<SelectOptionDto> getFinprodAbbrOption(Map<String, Object> map, int page, int length) {
        /** modify by yanya on 20210207 产品类型接口接入时，不接入finprod_type2,此处过滤条件用估值自身类型**/
    	//map.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        map.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        return this.queryForPageForSelectReturn(page, length,() -> comBondQuotationInfoMapper.getFinprodAbbrOption(map));
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
//                () -> mstBondQuotationInfoMapper.select(exportParam.getSelectParam())).dataSource(
//                    DataSourceType.MASERT);
//    }


    /**
     * 获取金融产品简称
     * @param finprodAbbr
     * @return
     */
    public List<SuggestDto> getFinProdAbbr(String finprodAbbr) {
        Map<String, Object> param = new HashMap<>();
        param.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        param.put("finprodAbbr", finprodAbbr);
        return comBondQuotationInfoMapper.getFinProdAbbr(param);
    }

    /**
     * @param secId secId
     * @return dto
     */
    public MstBondValuationInfo getBySecId(String secId) {
        return comBondQuotationInfoMapper.getBySecId(secId);
    }

    /**
     *
     * @param secId
     * @param valueDate
     * @param valueSource
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public ComBondQuotationInfoDto selectByKey(String secId, String valueDate, String valueSource) throws IllegalAccessException, InvocationTargetException {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("secId", secId);
        map.put("valueDate", valueDate);
        map.put("valueSource", valueSource);
        return comBondQuotationInfoMapper.selectByKey(map);
    }

    /**
     * 新建画面的资产代码查询
     * @param secId
     * @return
     */
    public List<SuggestDto> getSecIdToNew(String secId) {
        Map<String, Object> param = new HashMap<>();
        param.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        // 获取前台输入的值
        param.put("secId", secId);
        return comBondQuotationInfoMapper.getSecIdToNew(param);
    }

    public List<SelectOptionDto> getAssetCodeList() {
        return comBondQuotationInfoMapper.getAssetCodeList();
    }


    /**
     * 查询所有符合条件的数据
     * @param pageParam
     * @return
     * @throws Exception
     */
    public List<ComBondQuotationInfoDto> selectAllList(Map<String, Object> pageParam) throws Exception {
        /** modify by yanya on 20210207 产品类型接口接入时，不接入finprod_type2,此处过滤条件用估值自身类型**/
        //pageParam.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("FINPROD_TYPE_F01", DictCodeListExt1.金融产品类型_债券.getValue());
        return comBondQuotationInfoMapper.select(pageParam);
    }


   /**
    * 保存导入数据
    *
    * @param list 需保存的数据
    * @see
    * @since 1.0
    */
   public void mergeInfoList(List<ComBondQuotationInfoDto> list){
       int batchCount = 50;// 每批commit的个数
       int batchLastIndex = batchCount;// 每批最后一个的下标
       for (int index = 0; index < list.size();) {
           if (batchLastIndex > list.size() - 1) {
               batchLastIndex = list.size();
               comBondQuotationInfoMapper.mergeInfoList(list.subList(index, batchLastIndex));
               break;// 数据插入完毕,退出循环
           }
           else {
        	   comBondQuotationInfoMapper.mergeInfoList(list.subList(index, batchLastIndex));
               index = batchLastIndex;// 设置下一批下标
               batchLastIndex = index + batchCount;
           }
       }
   }
}
