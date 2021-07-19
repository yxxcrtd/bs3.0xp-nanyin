package com.joyintech.service.risk.creditmanagement.creditlinemanage.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.service.risk.creditmanagement.creditlinemanage.dao.CreditTagInfoDaoMapper;
import com.joyintech.service.risk.creditmanagement.creditlinemanage.dto.CreditTagInfoDto;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.dao.CreditTagInfoMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;


/**
 * 额度标签管理
 * @author chenlei1
 * @version 1.0 2019年02月15日
 * @see CeLine002Service
 * @since 1.0
 */
@Service
public class CeLine002Service extends BaseService{
	
	@Autowired
	private CreditTagInfoDaoMapper creditTagInfoDaoMapper;

	
	@Autowired
	private CreditLinesMapper creditLinesMapper;
	
	@Autowired(required = false)
	private BaseGetBussinessId baseGetBussinessId;
   
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(CreditTagInfoDto entity) {
//    	entity.setCreditUsingType("01");
    	creditTagInfoDaoMapper.insert(entity);
    }
    
   
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(CreditTagInfoDto entity) {
    	if(null!=entity){
    		creditTagInfoDaoMapper.updateByPrimaryKey(entity);
    	}
    }
    
    private String getQuotePrimaryKey(){
		if(null != baseGetBussinessId) {
			 BussinessCodeParameter codeParameter = new BussinessCodeParameter();
	            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
			return baseGetBussinessId.buildBussinessCode(codeParameter);
		}
		return IDGenerator.generate();
	}
    /**
     * 删除机构授信
     * @param name
     */
    public int delete(CreditTagInfoDto dto) {
    	Map<String, Object> param=new HashMap<String, Object>();
    	param.put("seqno", dto.getCreditTagId());
    	return creditTagInfoDaoMapper.deleteByPrimaryKey(dto.getCreditTagId());
    }


    /**
     * 查询额度信息
     * @param map
     */
    public CreditTagInfoDto queryCeLineBySeqno(Map<String, Object> map ) {
    	String seqno=(String) map.get("seqno");
    	CreditTagInfoDto retCre = creditTagInfoDaoMapper.selectByPrimaryKey(seqno);
    	return retCre;
    }
    
    /**
     * 获取额度标签
     * @return List<SelectOptionDto>
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getCreditTagInfoList(int page, int length,Map<String, Object> pageParam) {
			return super.queryForPage(page, length,() -> creditTagInfoDaoMapper.getCreditTagInfoList(pageParam));
	}
    
	/**
	 * 校验标签重名
	 * @param param
	 */
	public Boolean checkTag(Map<String, Object> param) {
		int count= creditTagInfoDaoMapper.checkTag(param);
		int countType= creditTagInfoDaoMapper.checkCreditType(param);

		if(count>0 || countType>0)
		{
			return  true;
		}
		return  false;
	}

	// add by yushilong 20200630 增加额度标签管理导入功能 start
	/**
	 * 保存导入数据
	 *
	 * @param list 需保存的数据
	 * @see
	 * @since 1.0
	 */
	public void mergeInfoList(List<CreditTagInfoDto> list){
		int batchCount = 50;// 每批commit的个数
		int batchLastIndex = batchCount;// 每批最后一个的下标
		for (int index = 0; index < list.size();) {
			if (batchLastIndex > list.size() - 1) {
				batchLastIndex = list.size();
				creditTagInfoDaoMapper.mergeInfoList(list.subList(index, batchLastIndex));
				break;// 数据插入完毕,退出循环
			}
			else {
				creditTagInfoDaoMapper.mergeInfoList(list.subList(index, batchLastIndex));
				index = batchLastIndex;// 设置下一批下标
				batchLastIndex = index + batchCount;
			}
		}
	}

    public int checkTagCanDelete(CreditTagInfoDto dto) {
		return creditTagInfoDaoMapper.checkTagCanDelete(dto);
    }
    // add by yushilong 20200630 增加额度标签管理导入功能 end
}
