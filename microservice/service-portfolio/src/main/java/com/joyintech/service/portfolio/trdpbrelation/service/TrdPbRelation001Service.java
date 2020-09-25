/*
 * 文件名：TrdPbRelation001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.1.2
 * 修改人：caoxue
 * 修改时间：2019年12月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.1.2
 */

package com.joyintech.service.portfolio.trdpbrelation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlsubfundsmange.service.PtlSubFundsAllocate001Service;
import com.joyintech.service.portfolio.trdpbrelation.dao.TrdPbRelation001Mapper;
import com.joyintech.service.portfolio.trdpbrelation.dto.TrdPbRelation001Dto;
import com.joyintech.tams.common.base.dao.TrdPbRelationMapper;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;

/**
 * PB编号管理Service 〈功能详细描述〉
 * 
 * @author caoxue
 * @version 1.0 2019年12月25日
 * @see TrdPbRelation001Service
 * @since 1.0
 *
 */
@Service
public class TrdPbRelation001Service extends CommonExportService<TrdPbRelation001Dto> {

	@Autowired
	private TrdPbRelation001Mapper trdPbRelation001Mapper;
	
	@Autowired
	private PtlSubFundsAllocate001Service ptlSubFundsAllocate001Service;

	/**
	 * 画面查询方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public Page<TrdPbRelation001Dto> selectForPage(int page, int length, Map<String, Object> pageParam) {
		return super.queryForPage(page, length, () -> trdPbRelation001Mapper.selectForPage(pageParam));
	}

	 /**
	 * 删除方法
	 *
	 * @param entity
	 * @return
	 */
	 public Integer delete(TrdPbRelation001Dto entity) {
		 return trdPbRelation001Mapper.delete(entity.getSeqno());
	 }
	 
	 /**
	  * 新建方法
	  *
	  * @param entity
	  * @return
	  */
	 public Integer insert(TrdPbRelation001Dto entity) {

		String seqNo = IDGenerator.generate();
		entity.setSeqno(seqNo);
		return trdPbRelation001Mapper.insert(entity);
	 }

	 /**
	 * 查询PB和产品/子组合关联信息
	 *
	 * @param pageParam
	 * @return
	 */
	public TrdPbRelation001Dto getTrdPbRelation(TrdPbRelation001Dto entity) {
		List<TrdPbRelation001Dto> list = trdPbRelation001Mapper.getTrdPbRelation(entity);
		if(StringUtil.isEmptyList(list)){
			return null;
		}
		return list.get(0);
	}

	/**
	 * Excel导出方法
	 * 
	 * @param page
	 * @param length
	 * @param pageParam
	 * @return
	 */
	public List<TrdPbRelation001Dto> selectForExport(Map<String, Object> pageParam) {
		return trdPbRelation001Mapper.selectForExport(pageParam);
	}
	
	/**
     * 查询产品/子组合代码
     * 
     * @param pageParam 参数
     * @return list
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryPrdPortfolioId(int page, int length,
                                               Map<String, Object> pageParam) {
        String factorType = (String)pageParam.get("factorType");
        if(DictCodeListExt3.PB要素类型_产品.getValue().equals(factorType)){//产品代码
        	pageParam.put("FINPROD_TYPE", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
            return super.queryForPageForSelectReturn(page, length,
                    () -> trdPbRelation001Mapper.queryPrdId(pageParam));
        }else if(DictCodeListExt3.PB要素类型_子组合.getValue().equals(factorType)){//子组合代码
        	return ptlSubFundsAllocate001Service.getSubPortfolioId(pageParam, page, length);
        }
        Page<SelectOptionDto> list=new Page<SelectOptionDto>();
        list.setRoot(new ArrayList<SelectOptionDto>());
        return list;
    }
}
