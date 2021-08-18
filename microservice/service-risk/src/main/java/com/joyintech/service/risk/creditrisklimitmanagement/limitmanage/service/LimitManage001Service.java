package com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dao.LimitManageMapper;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.CreditItemInfoDto;
import com.joyintech.service.risk.creditrisklimitmanagement.limitmanage.dto.LimitManageInfoDto;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.constant.DictCodeList;


/**
 * 额度管理
 * @author chenlei1
 * @version 1.0 2020年02月26日
 * @see LimitManage001Service
 * @since 1.0
 */
@Service("backLimitManage001Service")
public class LimitManage001Service extends BaseService<LimitManageInfoDto> {
	
	@Autowired
	private LimitManageMapper limitManageMapper;
	
	  /**
     * 平台日期
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
	
    /**
     * 画面查询方法
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<LimitManageInfoDto> selectForPage(int page, int length,Map<String, Object> param) 
        throws Exception {
	     param.put("ptDate", iPlatformDate.getSysDate());
        return super.queryForPage(page, length, () -> limitManageMapper.selectForPage(param));
    }

    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public Page<SelectOptionDto> getCustomerId(Map<String, Object> map, int page, int length) {
    	return this.queryForPageForSelectReturn(page, length,()->limitManageMapper.getCustomerId(map));
    }
    
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(LimitManageInfoDto entity) {
    	if(StringUtil.isNotEmpty(entity.getCreditAmt())){
    		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
    	}
    	entity.setHapCreditAmt(new BigDecimal(0));
    	entity.setCreditFlag(DictCodeList.是否_是.getValue());
    	entity.setCreditType("01");
    	/*if(StringUtil.isNotEmpty(entity.getCreditQuoteItem())){
    		for(CreditLines dto:entity.getCreditQuoteItem()){
    			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
    				dto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
    	    	}
    			dto.setVdate(entity.getVdate());
    			dto.setMdate(entity.getMdate());
    			dto.setRemark(entity.getRemark());
    			dto.setPflsh(entity.getPflsh());
    			dto.setHapCreditAmt(new BigDecimal(0));
    			dto.setCustomerId(entity.getCustomerId());
    			dto.setCreditFlag(DictCodeList.是否_是.getValue());
    			dto.setParentCustomerId(entity.getCustomerId());
    			limitManageMapper.insert(dto);
    		}
    		
    	}*/
    	
    	if(StringUtil.isNotEmpty(entity.getCreditLabelItem())){
		  for(CreditItemInfoDto dto:entity.getCreditLabelItem()){
			String se=IDGenerator.generate();
			CreditLines cldto=new CreditLines();
			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
				cldto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
	    	}
			cldto.setVdate(entity.getVdate());
			cldto.setMdate(entity.getMdate());
			cldto.setRemark(entity.getRemark());
			cldto.setPflsh(entity.getPflsh());
			cldto.setHapCreditAmt(new BigDecimal(0));
			cldto.setCustomerId(entity.getCustomerId());
			cldto.setCreditFlag(DictCodeList.是否_是.getValue());
			cldto.setParentCustomerId(entity.getCustomerId());
			cldto.setSeqno(dto.getCreditMstId());
			cldto.setCreditMstId(se);
			//cldto.setCreditType("01");
			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
				cldto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
	    	}
			dto.setCreditMstId(se);
			//limitManageMapper.insert(cldto);	
			
			//creditMstInfoMapper.insert(dto);
		  }		
	   }
    	//limitManageMapper.insert(entity);
    }
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(LimitManageInfoDto entity) {
    	if(null!=entity){
    		if(StringUtil.isNotEmpty(entity.getCreditAmt())){
        		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
        	}
    		//limitManageMapper.updateByPrimaryKeySelective(entity);
    		if(StringUtil.isNotEmpty(entity.getCreditQuoteItem())){
        		for(CreditLines dto:entity.getCreditQuoteItem()){
        			if(StringUtil.isNotEmpty(dto.getCreditAmt())){
        				dto.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
        	    	}
        			dto.setVdate(entity.getVdate());
        			dto.setMdate(entity.getMdate());
        			dto.setRemark(entity.getRemark());
        			dto.setPflsh(entity.getPflsh());
        			dto.setCustomerId(entity.getCustomerId());
        			dto.setParentCustomerId(entity.getCustomerId());
        			if(StringUtil.isNotEmpty(dto.getSeqno())){
        				//如果授信额度为空则删除
						if (!StringUtil.isNotEmpty(dto.getCreditAmt())){
							//limitManageMapper.deleteByPrimaryKey(dto.getSeqno());
						}else {
							//limitManageMapper.updateByPrimaryKeySelective(dto);
						}
        			}
        			else{
        				//dto.setSeqno(getQuotePrimaryKey());
        				dto.setHapCreditAmt(new BigDecimal(0));
        				dto.setCreditFlag(DictCodeList.是否_是.getValue());
            			//limitManageMapper.insert(dto);
        			}
        		}
        		
        	}
    	}
    }
    
    /**
     * 删除机构授信
     * @param name
     */
    public int delete(LimitManageInfoDto dto) {
    	Map<String, Object> param=new HashMap<String, Object>();
    	param.put("seqno", dto.getSeqno());
    	LimitManageInfoDto retCre = limitManageMapper.queryCeOrgBySeqno(param);
    	return limitManageMapper.deleteOrgMx(retCre.getPflsh());
    }

   
}
