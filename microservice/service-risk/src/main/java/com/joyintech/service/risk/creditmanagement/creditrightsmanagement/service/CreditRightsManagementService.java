package com.joyintech.service.risk.creditmanagement.creditrightsmanagement.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RiskWeightMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditmanagement.creditrightsmanagement.dao.CreditRightsManagementMapper;
import com.joyintech.service.risk.creditmanagement.creditrightsmanagement.dto.CreditRightsManagementDto;


/**
 * 风险权数管理
 * @author huwenbin
 *
 */
@Service
public class CreditRightsManagementService extends BaseService<CreditRightsManagementDto>{
	/**
	 * 自定义Mapper
	 */
    @Autowired
	private CreditRightsManagementMapper creditRightsManagementMapper;
    
    /**
     * 风险权数管理
     */
    @Autowired
    private RiskWeightMapper riskWeightMapper;
	
	/**
	 * 
	 * 页面查询
	 * <功能详细描述>
	 * @param page int
	 * @param length int
	 * @param pageParam Map<String, Object>
	 * @return CreditRightsManagementDto
	 * @throws Exception Exception
	 * @see
	 * @since 1.0
	 */
    public Page<CreditRightsManagementDto> selectForPage(int page, int length, Map<String, Object> pageParam)
	        throws Exception {
    	pageParam.put("endDate", DateUtil.parse((String)pageParam.get("endDate")));
        pageParam.put("startDate", DateUtil.parse((String)pageParam.get("startDate")));
    	return super.queryForPage(page,length,() -> creditRightsManagementMapper.select(pageParam));
    }
    
    /**
     * 实现docomplement方法
     * @param entity CreditRightsManagementDto
     */
    public void doComplete(CreditRightsManagementDto entity) {
        String type = entity.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            riskWeightMapper.deleteByPrimaryKey(entity.getSeqno());            
        }
        else if (StringUtil.equals(type, "NEW")) {
            riskWeightMapper.insert(entity);
        }
        else {
            riskWeightMapper.updateByPrimaryKey(entity);
        }
    }
    
    /**
     * 
     * 
     * 双击、编辑查看详情
     * <功能详细描述>
     * @param seqno String
     * @return CreditRightsManagementDto
     * @see
     * @since 1.0
     */
    public CreditRightsManagementDto selectDetail(String seqno){
    	return creditRightsManagementMapper.selectDetail(seqno);
    }
    
    /**
     * 
     * 同一个资产二级类型、同一生效日只能有一条数据
     * <功能详细描述>
     * @param pageParam Map<String, Object>
     * @return List<CreditRightsManagementDto>
     * @see
     * @since 1.0
     */
    public List<CreditRightsManagementDto> checkalreadyhave(Map<String, Object> pageParam){
    	pageParam.put("effDate", DateUtil.parse((String)pageParam.get("effDate")));
    	return creditRightsManagementMapper.checkalreadyhave(pageParam);
    }
}
