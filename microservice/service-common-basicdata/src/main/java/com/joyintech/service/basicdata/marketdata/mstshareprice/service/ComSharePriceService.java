/*
 * 文件名：MstSharePriceService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：wangpeng
 * 修改时间：2019年6月28日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.marketdata.mstshareprice.service;

import java.util.List;
import java.util.Map;

import com.joyintech.service.basicdata.marketdata.mstshareprice.dao.ComSharePrice001Mapper;
import com.joyintech.service.basicdata.marketdata.mstshareprice.dto.ComSharePriceDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
//import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.MstSharePriceMapper;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 股票行情管理service
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangpeng
 * @version 1.0 2019年6月28日
 * @see ComSharePriceService
 * @since 1.0
 */
@Service("serviceComSharePriceService")
@Transactional(rollbackFor = Exception.class)
public class ComSharePriceService extends BaseService<ComSharePriceDto>{

   /**
    * 股票行情管理mapper
    */
    @Autowired
    private ComSharePrice001Mapper comSharePrice001Mapper;
    
    @Autowired
    private  MstSharePriceMapper mstSharePriceMapper;

//    @Autowired
//    private Joyin2JixuInterface joyin2JixuInterface;
    /**
     * 
     * 获取分页查询结果（sharesId  dealDateString）
     * <功能详细描述>
     * @param pageParam Map<String, Object> 
     * @return Page<MstSharePriceDto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public Page<ComSharePriceDto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
        return super.queryForPage(page,length,()-> comSharePrice001Mapper.select(pageParam));
    }
    /**
     * 
     * 获取list查询结果（sharesId  dealDateString）
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<MstSharePriceDto>
     * @see
     * @since 1.0
     */
    public List<ComSharePriceDto> selectAllList(Map<String, Object> pageParam) {
        return comSharePrice001Mapper.select(pageParam);
    }
    
    /**
     * 通过资产代码或者资产名称等信息
     * @param page
     * @param length
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getFinprodIdForAdd(int page, int length,Map<String, String> map) {
        Page<SelectOptionDto> result = null;
        if(CacheUtil.getSysParam("ISSINGLEVAL").equals(DictCodeListExt1.是或否_否.getValue())){
            map.put("planasset", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            /*map.put("notworth", DictCodeList.收益类型_货币型.getValue());*/
            result = this.queryForPageForSelectReturn(page, length,()->comSharePrice001Mapper.getFinprodIdForAdd(map));
        }else{
//            List<SelectOptionDto> list = joyin2JixuInterface.getSecurityDtoByAssetType(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
//            result = new Page<SelectOptionDto>(new PageInfo<SelectOptionDto>(list));
        }
        return result;
    }
    /**
     *
     * 工作流完结之后的操作
     * <功能详细描述>
     * @param entity 实体
     * @param type 类型
     * @see
     * @since 1.0
     */
    public void doComplete(ComSharePriceDto entity) {
        if(StringUtil.equals(entity.getOperateType(), "DELETE")) {     
        	mstSharePriceMapper.deleteByPrimaryKey(entity);
        }else if(StringUtil.equals(entity.getOperateType(), "NEW")){
        	mstSharePriceMapper.insert(entity);
        }else{
        	mstSharePriceMapper.updateByPrimaryKeySelective(entity);
        }
        
    }
    /**
     * 获取指定日期之前最近的股票信息
     * @param pageParam
     * @return
     */
	public ComSharePriceDto getLatelyShareById(Map<String, Object> pageParam) {
		 return comSharePrice001Mapper.getLatelyShareById(pageParam);
	}

	   /**
	    * 保存导入数据
	    *
	    * @param list 需保存的数据
	    * @see
	    * @since 1.0
	    */
	   public void mergeInfoList(List<ComSharePriceDto> list){
	       int batchCount = 50;// 每批commit的个数
	       int batchLastIndex = batchCount;// 每批最后一个的下标
	       for (int index = 0; index < list.size();) {
	           if (batchLastIndex > list.size() - 1) {
	               batchLastIndex = list.size();
	               comSharePrice001Mapper.mergeInfoList(list.subList(index, batchLastIndex));
	               break;// 数据插入完毕,退出循环
	           }
	           else {
	        	   comSharePrice001Mapper.mergeInfoList(list.subList(index, batchLastIndex));
	               index = batchLastIndex;// 设置下一批下标
	               batchLastIndex = index + batchCount;
	           }
	       }
	   }
}
