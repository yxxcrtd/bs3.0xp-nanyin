package com.joyintech.service.basicdata.mstcommissionset.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.TrdFeeConfigDetailMapper;
import com.joyintech.tams.common.base.dao.TrdFeeConfigMapper;
import com.joyintech.tams.common.base.entity.TrdFeeConfigDetail;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstcommissionset.dao.MstCommissionSetMapper;
import com.joyintech.service.basicdata.mstcommissionset.dto.MstCommissionSetDto;

/**
 * 佣金设置
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年3月22日
 * @see MstCommissionSetService
 * @since 1.0
 */
@Service("backMstCommissionSetService")
public class MstCommissionSetService extends BaseService<MstCommissionSetDto>{
    /**
     * 定义mapper
     */
    @Autowired
    private MstCommissionSetMapper mstCommissionSetMapper;
    
    @Autowired
    private TrdFeeConfigMapper trdFeeConfigMapper;
    
    @Autowired
    private TrdFeeConfigDetailMapper trdFeeConfigDetailMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<MstCommissionSetDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
//    	pageParam.put("feeType", BussinessDictCodeList.费用类型_佣金.getValue());
    	Page<MstCommissionSetDto> resutList= super.queryForPage(page, length, () -> mstCommissionSetMapper.selectForPage(pageParam));
    /*	List<MstCommissionSetDto> reList=new ArrayList<MstCommissionSetDto>();
    	for(MstCommissionSetDto dto:resutList.getRoot()){
    		if(assetSecTypeT.size()>0){
				entity.setAssetSecType(String.join(",", new HashSet<>(assetSecTypeT)));
			}
    	}*/
    	return resutList;
    }
  

    /**
     * 查询佣金设置信息
     * @param map
     */
    public MstCommissionSetDto getBussinessEntityBySeqNo(Map<String, Object> map ) {
    	List<MstCommissionSetDto> retCreList = mstCommissionSetMapper.getBussinessEntityBySeqNo(map);
    	MstCommissionSetDto retCre = retCreList.get(0);
    	List<TrdFeeConfigDetail> detailList = mstCommissionSetMapper.getTrdFeeConfigDetailList(map);
    	if(detailList != null && detailList.size()>0){
			retCre.setTrdFeeConfigDetailList(detailList);
			if("N".equals(retCre.getIsGrade())){
				if(detailList.get(0).getChargeRate() != null){
					retCre.setChargeRate(detailList.get(0).getChargeRate());
				}
				if(detailList.get(0).getChargeValue() != null){
					retCre.setChargeValue(detailList.get(0).getChargeValue());
				}
				if(detailList.get(0).getFeeMaxi() != null){
					retCre.setFeeMaxi(detailList.get(0).getFeeMaxi());
				}
				if(detailList.get(0).getFeeMini() != null){
					retCre.setFeeMini(detailList.get(0).getFeeMini());
				}
			}
		}
    	return retCre;
    }
    
    /**
     * 证券代码联想文本框service层实现
      * @param map 检索参数
      * @return SuggestDto
      */
    public List<SelectOptionDto> getSecAcctName(Map<String, Object> map) {
        return mstCommissionSetMapper.getSecAcctName(map);
    }

	/**
	 * 新建编辑场合时是否重复
	 * @param batchSeq
	 * @param secAcctId
	 * @param ps
	 * @param tradeType
	 * @param feeType
	 * @return
	 */
    public int searchRepeatBySecPs(Map<String, Object> map) {
    	return mstCommissionSetMapper.searchRepeatBySecPs(map);
    }
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(MstCommissionSetDto entity) {
    	insertOrUpdate(entity);
    }
    
   private void insertOrUpdate(MstCommissionSetDto entity){
	   	for(String psDto:entity.getPs().split(",")){
	   		for(String customerId:entity.getCustomerId().split(",")){
	   			//entity.setRegistOrg(mstCommissionSetMapper.getSecRegistOrg(customerId));
	   			if(StringUtil.isNotEmpty(entity.getTermType())){
		   			for(String termDto:entity.getTermType().split(",")){
		   				insertTable(entity,termDto,customerId,psDto);
		   			}
	   			}
	   			else{
	   				insertTable(entity,null,customerId,psDto);
	   			}
	   		}
	   	}
   }
   private void insertTable(MstCommissionSetDto entity,String termDto,String secIdDto,String psDto){
	   MstCommissionSetDto mstComDto=new MstCommissionSetDto();
		BeanUtil.copyPropertiesIgnoreNull(mstComDto, entity);
		mstComDto.setSeqNo(IDGenerator.generate());
		mstComDto.setTermType(termDto);
		mstComDto.setPs(psDto);
	   	trdFeeConfigMapper.insert(mstComDto);
	   	if(entity.getTrdFeeConfigDetailList() != null && entity.getTrdFeeConfigDetailList().size()>0){
	   		for(TrdFeeConfigDetail trdDetailDto : entity.getTrdFeeConfigDetailList()){
				//BeanUtil.copyPropertiesIgnoreNull(trdDetailDto, mstComDto);
				trdDetailDto.setSeqNo(IDGenerator.generate());
				trdDetailDto.setConfigSeqNo(mstComDto.getSeqNo());
				if (StringUtil.isNotEmpty(trdDetailDto.getChargeRate())) {
					trdDetailDto.setChargeRate(trdDetailDto.getChargeRate().divide(new BigDecimal(1000)));
				}
				// 分档金额乘以10000入库
				trdDetailDto.setGradeAmt(ArithmeticUtil.multiply(trdDetailDto.getGradeAmt(), new BigDecimal(10000)));
				trdFeeConfigDetailMapper.insert(trdDetailDto);
			}
		}else{
			TrdFeeConfigDetail trdDetailDto = new TrdFeeConfigDetail();
			//BeanUtil.copyPropertiesIgnoreNull(trdDetailDto, mstComDto);
			trdDetailDto.setSeqNo(IDGenerator.generate());
			trdDetailDto.setConfigSeqNo(mstComDto.getSeqNo());
			if(entity.getChargeRate() != null){
				trdDetailDto.setChargeRate(entity.getChargeRate().divide(new BigDecimal(1000)));
			}
			if(entity.getChargeValue() != null){
				trdDetailDto.setChargeValue(entity.getChargeValue());
			}
			if(entity.getFeeMaxi() != null){
				trdDetailDto.setFeeMaxi(entity.getFeeMaxi());
			}
			if(entity.getFeeMini() != null){
				trdDetailDto.setFeeMini(entity.getFeeMini());
			}
			// 分档金额乘以10000入库
			trdDetailDto.setGradeAmt(ArithmeticUtil.multiply(trdDetailDto.getGradeAmt(), new BigDecimal(10000)));

			trdFeeConfigDetailMapper.insert(trdDetailDto);
		}
//	   	TrdFeeConfigDetail trdDetailDto=new TrdFeeConfigDetail();
//	   	BeanUtil.copyPropertiesIgnoreNull(trdDetailDto, mstComDto);
//	   	trdDetailDto.setConfigSeqNo(mstComDto.getSeqNo());
//	   	if (StringUtil.isNotEmpty(trdDetailDto.getChargeRate())) {
//	   	    trdDetailDto.setChargeRate(trdDetailDto.getChargeRate().divide(new BigDecimal(1000)));
//	   	}
//	   	trdFeeConfigDetailMapper.insert(trdDetailDto);
   }
    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(MstCommissionSetDto entity) {
    	if(null!=entity){
			mstCommissionSetMapper.deleteDtlByBatchSeq(entity.getBatchSeq());
			mstCommissionSetMapper.deleteByBatchSeq(entity.getBatchSeq());
    		insertOrUpdate(entity);
    	}
    }
    
    /**
     * 删除佣金设置
     * @param dto
     */
    public int delete(MstCommissionSetDto dto) {
        int i= mstCommissionSetMapper.deleteDtlByBatchSeq(dto.getBatchSeq());
        i += mstCommissionSetMapper.deleteByBatchSeq(dto.getBatchSeq());
    	return i;
    }

	/**
	 * add by hudongyang
	 * @param param
	 * @return
	 */
	public List<SelectOptionDto> getMstCustomerInfo(Map<String, Object> param) {
		return mstCommissionSetMapper.getMstCustomerInfo(param);
	}
    
}
