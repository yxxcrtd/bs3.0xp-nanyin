package com.joyintech.service.basicdata.mstcommissionset.service;

import java.math.BigDecimal;
import java.util.ArrayList;
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
public class MstCommissionSetService extends BaseService{
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
    	MstCommissionSetDto retCre = mstCommissionSetMapper.getBussinessEntityBySeqNo(map);
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
    
    public int searchRepeatBySecPs1(Map<String, Object> map) {
    	List<MstCommissionSetDto> list =  mstCommissionSetMapper.searchRepeatBySecPs1(map);
    	if(list == null)
    		return 0;
    	return list.size();
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
	   		for(String secIdDto:entity.getSecAcctId().split(",")){
	   			entity.setRegistOrg(mstCommissionSetMapper.getSecRegistOrg(secIdDto));
	   			if(StringUtil.isNotEmpty(entity.getTermType())){
		   			for(String termDto:entity.getTermType().split(",")){
		   				insertTable(entity,termDto,secIdDto,psDto);
		   			}
	   			}
	   			else{
	   				insertTable(entity,null,secIdDto,psDto);
	   			}
	   		}
	   	}
   }
   private void insertTable(MstCommissionSetDto entity,String termDto,String secIdDto,String psDto){
	   MstCommissionSetDto mstComDto=new MstCommissionSetDto();
		BeanUtil.copyPropertiesIgnoreNull(mstComDto, entity);
//		mstComDto.setSeqNo(IDGenerator.generate());
		mstComDto.setTermType(termDto);
		mstComDto.setSecAcctId(secIdDto);
		mstComDto.setPs(psDto);
		List<String> seqNoList = new ArrayList<>();
		//判断页面资产分类一是否为空
		if(StringUtil.isNotEmpty(entity.getWindtypeone())){
			//当资产分类一不为空的时候，循环遍历，资产分类一文本框中每一种分类会生成一组数据
			for(String windtypeone:entity.getWindtypeone().split(",")){
				//把资产分类一放到插入库的参数中（此时，资产分类一固定为其中一种）
				mstComDto.setWindtypeone(windtypeone);
				//查询出此时的资产分类一对应的所有的资产分类二
				List<String> list=trdFeeConfigMapper.findWindtypetwo(windtypeone);
				//判断页面资产分类二是否为空、此时的资产分类一对应的资产分类二是否为空（就是list是否为空）
				if(StringUtil.isNotEmpty(entity.getWindtypetwo())&&list.size()>0){
					//设置标记，记录此时的资产分类一，是否插入库过资产分类二有值的记录
					Boolean flag=false;
					//循环遍历页面资产分类二
					for(String windtypetwo:entity.getWindtypetwo().split(",")){
						//判断此时资产分类一对应的所有的资产分类二中是否包含页面资产分类二中的种类
						if(list.contains(windtypetwo)){
							//包含时，会插入一条该资产分类一条件下资产分类二不为空的数据，此时需要把标记赋值为真
							mstComDto.setWindtypetwo(windtypetwo);
							flag=true;
                            String seqNo=IDGenerator.generate();
                            seqNoList.add(seqNo);
                            mstComDto.setSeqNo(seqNo);
							trdFeeConfigMapper.insert(mstComDto);
						}
					}
					//判断标记
					if(!flag){
						//当为假时，此时代表，资产分类二不为空，但是此时没有插入库过该资产分类一条件下资产分类二不为空的数据，此时要插入库一条该资产分类一条件下资产分类二为空的数据
                        String seqNo=IDGenerator.generate();
                        seqNoList.add(seqNo);
                        mstComDto.setSeqNo(seqNo);
						trdFeeConfigMapper.insert(mstComDto);
					}
				}else{
					//当页面资产分类二为空、或者此时的资产分类一对应的所有资产分类二为空的时候
					mstComDto.setWindtypetwo(null);
                    String seqNo=IDGenerator.generate();
                    seqNoList.add(seqNo);
                    mstComDto.setSeqNo(seqNo);
					trdFeeConfigMapper.insert(mstComDto);
				}
			}
		}else{
			//当资产分类一为空的时候，插库（此时资产分类一和资产分类二均为空）
            String seqNo=IDGenerator.generate();
            seqNoList.add(seqNo);
            mstComDto.setSeqNo(seqNo);
			trdFeeConfigMapper.insert(mstComDto);
		}
	   	if("Y".equals(entity.getIsGrade())){
	   		for(TrdFeeConfigDetail dto:entity.getTrdFeeConfigDetailList()){
				TrdFeeConfigDetail trdFeeConfigDetailDto=new TrdFeeConfigDetail();
				BeanUtil.copyPropertiesIgnoreNull(trdFeeConfigDetailDto, dto);
//				trdFeeConfigDetailDto.setSeqNo(IDGenerator.generate());
//				trdFeeConfigDetailDto.setConfigSeqNo(mstComDto.getSeqNo());
				if (StringUtil.isNotEmpty(trdFeeConfigDetailDto.getChargeRate())) {
					trdFeeConfigDetailDto.setChargeRate(trdFeeConfigDetailDto.getChargeRate().divide(new BigDecimal(1000)));
				}
				trdFeeConfigDetailDto.setGradeAmount(trdFeeConfigDetailDto.getGradeAmount().multiply(new BigDecimal(10000)));
				for(String seqNo:seqNoList){
                    trdFeeConfigDetailDto.setSeqNo(IDGenerator.generate());
                    trdFeeConfigDetailDto.setConfigSeqNo(seqNo);
                    trdFeeConfigDetailMapper.insert(trdFeeConfigDetailDto);
                }
			}
	   		return;
		}
	   	TrdFeeConfigDetail trdDetailDto=new TrdFeeConfigDetail();
	   	BeanUtil.copyPropertiesIgnoreNull(trdDetailDto, mstComDto);
	   	trdDetailDto.setConfigSeqNo(mstComDto.getSeqNo());
	    trdDetailDto.setSeqNo(IDGenerator.generate());
	   	if (StringUtil.isNotEmpty(trdDetailDto.getChargeRate())) {
	   	    trdDetailDto.setChargeRate(trdDetailDto.getChargeRate().divide(new BigDecimal(1000)));
	   	}
       for(String seqNo:seqNoList){
           trdDetailDto.setSeqNo(IDGenerator.generate());
           trdDetailDto.setConfigSeqNo(seqNo);
           trdFeeConfigDetailMapper.insert(trdDetailDto);
       }
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


	public List<TrdFeeConfigDetail> findTable(Map<String, Object> param) {
//		Page<TrdFeeConfigDetail> resutList= super.queryForPage(page, length, () -> mstCommissionSetMapper.findTable(param));
		return mstCommissionSetMapper.findTable(param);
	}
}
