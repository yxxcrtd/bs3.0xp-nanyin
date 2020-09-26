/*
 * 文件名：AssetMangBaseInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：qiangchengxi
 * 修改时间：2018年6月22日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.common.interf.base.service.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.InterfCommonConstant;
import com.joyintech.tams.valuation.common.interf.base.dao.AssetMangBaseInfoMapper;
import com.joyintech.tams.valuation.common.interf.base.dto.TargetFinProduct;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateColMatchDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderDTO;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author qiangchengxi
 * @version 1.0 2018年6月22日
 * @see AssetMangBaseInfoService
 * @since 1.0
 */
@Service
@Scope("prototype")
public class AssetMangBaseInfoService {
    
    @Autowired
    private AssetMangBaseInfoMapper mapper;
    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
	public List<TemplateOrderInfoReaderResult> execute(Map<String, Object> param) {
        
        //获取批次号
        //获取批次号
        String orderNo = param.get("orderNo").toString();
        List<String> tempList = (List<String>)param.get(InterfCommonConstant.接口_运行接口列表);        
        
        //获取模板相关信息
        List<TemplateOrderInfoReaderResult> list= mapper.queryTempletDetail(orderNo,tempList);
        /**add by wujie on 20181228 start remark:拆借、债券回购、债券借贷**/
        Map<String,TargetFinProduct> mapTargetFinProduct = getTargetFinProductMap(mapper.queryTargetFinProduct());
        /**add by wujie on 20181228 end**/
        
        //获取模板需check对应字段信息
        for (TemplateOrderInfoReaderResult template : list) {
            List<TemplateOrderInfoReaderDTO> clist=mapper.queryTempletCheckDetail(template.getTmpEName());
            List<TemplateColMatchDTO> matchList = mapper.queryTempletDtoMatchInfo(
                template.getTmpEName(), template.getDtoName());
            template.setListTemplateOrderInfoDTO(clist);
            template.setListTemplateColMatchDTO(matchList);
            /**add by wujie on 20181228 start remark:拆借、债券回购、债券借贷**/
            template.setMapTargetFinProduct(mapTargetFinProduct);
            /**add by wujie on 20181228 end**/
        }
        return list;
    }
    /**
     * List 转 Map  债券回购、拆借标的、债券借贷标的
     */
    private Map<String,TargetFinProduct> getTargetFinProductMap(List<TargetFinProduct> list){
    	Map<String,TargetFinProduct> map = new HashMap<String,TargetFinProduct>();
    	if(list == null || list.size()==0){
    		return null;
    	}
    	for(TargetFinProduct obj :list){
    		String key = obj.getFinprodId()+obj.getFinprodType();
    		map.put(key, obj);
    	}
    	return map;
    }
}
