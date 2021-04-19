package com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dao.ComFxValuztionInfo001Mapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dto.ComFxValuztionInfo001Dto;

/**
 * 外汇估值信息
 *
 * @author zhangwei
 * @createTime 2019-07-26 11:23
 */
@SuppressWarnings("rawtypes")
@Service("seriveComFxValuztionInfo001Service")
public class ComFxValuztionInfo001Service extends BaseService {

    @Autowired
    private ComFxValuztionInfo001Mapper comFxValuztionInfo001Mapper;

    /**
     * 交易编号获取
     * @param params
     *
     * @return 返回的数据
     */
    public List<SelectOptionDto> getFinprodId2(Map<String, Object> params) {
        List<SelectOptionDto> resultList = null;
        if(CacheUtil.getSysParam("ISSINGLEVAL").equals(DictCodeListExt1.是或否_否.getValue())){
            resultList = comFxValuztionInfo001Mapper.getFinprodId2(params);

            List<SelectOptionDto> selectOptionDtoList = new ArrayList<>();
            for (SelectOptionDto single : resultList) { // 组装select节点对象
                SelectOptionDto selectOptionDto = new SelectOptionDto();
                selectOptionDto.setId(single.getId());
                selectOptionDto.setText(single.getId());
                selectOptionDtoList.add(selectOptionDto);
            }
            return selectOptionDtoList;
        }else{
            List<String> assetTypes = new ArrayList<>();
            assetTypes.add(DictCodeListExt1.金融产品类型_投管用_远期.getValue());
            assetTypes.add(DictCodeListExt1.金融产品类型_投管用_即期.getValue());
            assetTypes.add(DictCodeListExt1.金融产品类型_投管用_掉期.getValue());
            params.put("ASSETTYPES",assetTypes);
            //直接从几许库查询了，不要引入几许的依赖了
            resultList = comFxValuztionInfo001Mapper.getFinprodIdFromJx(params);
//            resultList = joyin2JixuInterface.getSecurityDtoByAssetType(DictCodeListExt1.金融产品类型_投管用_远期.getValue());
//            resultList.addAll(joyin2JixuInterface.getSecurityDtoByAssetType(DictCodeListExt1.金融产品类型_投管用_即期.getValue()));
//            resultList.addAll(joyin2JixuInterface.getSecurityDtoByAssetType(DictCodeListExt1.金融产品类型_投管用_掉期.getValue()));
        }

        return resultList;

//        Page<SelectOptionDto> result = null;
//        if(CacheUtil.getSysParam("ISSINGLEVAL").equals(DictCodeListExt1.是或否_否)){
//            map.put("planasset", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
//            /*map.put("notworth", DictCodeList.收益类型_货币型.getValue());*/
//            result = this.queryForPageForSelectReturn(page, length,()->valSharePrice001Mapper.getFinprodIdForAdd(map));
//        }else{
//            List<SelectOptionDto> list = joyin2JixuInterface.getSecurityDtoByAssetType(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
//            result = new Page<SelectOptionDto>(new PageInfo<SelectOptionDto>(list));
//        }
//        return result;
    	 
	}
    
    /**
     * 列表查询 - 分页
     * @param page
     * @param length
     * @param params
     * @return
     */
    @SuppressWarnings("unchecked")
    public Page<ComFxValuztionInfo001Dto> queryPageList(int page, int length, Map<String, Object> params) {
        return super.queryForPage(page,length,()-> comFxValuztionInfo001Mapper.selectList(params));
    }

    /**
     * 列表查询
     * @param params
     * @return
     */
    public List<ComFxValuztionInfo001Dto> queryAllList(Map<String, Object> params) {
        return comFxValuztionInfo001Mapper.selectList(params);
    }

}
