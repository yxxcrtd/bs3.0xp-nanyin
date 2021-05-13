package com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.CacheUtil;
import com.joyintech.service.basicdata.marketdata.valfxvaluztioninfo.dao.ComFxValuztionInfo001Mapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
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

//    @Autowired
//    private Joyin2JixuInterface joyin2JixuInterface;

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
//
//            List<SelectOptionDto> selectOptionDtoList = new ArrayList<>();
//            for (SelectOptionDto single : resultList) { // 组装select节点对象
//                SelectOptionDto selectOptionDto = new SelectOptionDto();
//                selectOptionDto.setId(single.getId());
//                selectOptionDto.setText(single.getId());
//                selectOptionDtoList.add(selectOptionDto);
//            }
        }else{
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

    /**
     * 保存导入数据
     *
     * @param list 需保存的数据
     * @see
     * @since 1.0
     */
    public void mergeInfoList(List<ComFxValuztionInfo001Dto> list){
        int batchCount = 50;// 每批commit的个数
        int batchLastIndex = batchCount;// 每批最后一个的下标
        for (int index = 0; index < list.size();) {
            if (batchLastIndex > list.size() - 1) {
                batchLastIndex = list.size();
                comFxValuztionInfo001Mapper.mergeInfoList(list.subList(index, batchLastIndex));
                break;// 数据插入完毕,退出循环
            }
            else {
            	comFxValuztionInfo001Mapper.mergeInfoList(list.subList(index, batchLastIndex));
                index = batchLastIndex;// 设置下一批下标
                batchLastIndex = index + batchCount;
            }
        }
    }

}
