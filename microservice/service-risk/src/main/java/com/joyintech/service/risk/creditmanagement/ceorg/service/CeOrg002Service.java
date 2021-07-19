package com.joyintech.service.risk.creditmanagement.ceorg.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.TreeNode;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditmanagement.ceorg.dao.CeOrgMapper;
import com.joyintech.service.risk.creditmanagement.ceorg.dao.CreditConfInfoDaoMapper;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditConfInfoDto;
import com.joyintech.service.risk.creditmanagement.ceorg.dto.CreditLinesOrgDto;
import com.joyintech.tams.common.base.dao.CreditConfInfoMapper;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.dao.CreditTagInfoMapper;
import com.joyintech.tams.common.base.entity.CreditConfInfo;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.CreditTagInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListRsk;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;


/**
 * 机构授信
 * @author 裴家俊
 * @version 1.0 2018年10月24日
 * @see CeOrg002Service
 * @since 1.0
 */
@Service("backCeOrg002Service")
public class CeOrg002Service extends BaseService<CreditConfInfoDto> {

	@Autowired
	private CeOrgMapper ceOrgMapper;

	@Autowired
	private CreditLinesMapper creditLinesMapper;

	@Autowired(required = false)
	private BaseGetBussinessId baseGetBussinessId;

	@Autowired
	private CreditConfInfoDaoMapper creditConfInfoDaoMapper;

	@Autowired
	private CreditTagInfoMapper creditTagInfoMapper;

	@Autowired
	private CreditConfInfoMapper creditConfInfoMapper;

    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public List<SelectOptionDto> getCustomerId(Map<String, Object> map) {
        return ceOrgMapper.getCustomerId(map);
    }

    @SuppressWarnings("rawtypes")
	public List<TreeNode> getAllCeOrgQuote(String rank) {
        return ceOrgMapper.getAllCeOrgQuote(rank);
    }

    @SuppressWarnings("rawtypes")
   	public List<TreeNode> getParentCeOrgQuote(String rank) {
           return ceOrgMapper.getParentCeOrgQuote(rank);
       }

    public Page<CreditConfInfoDto> getTreeCeOrgQuote(int page, int length,Map<String, Object> param)
            throws Exception {
            return super.queryForPage(page, length, () -> ceOrgMapper.getTreeCeOrgQuote(param));
        }

    /**
     * 查询额度信息
     * @param map
     */
    public CreditLinesOrgDto queryCeOrgBySeqno(Map<String, Object> map ) {
    	CreditLinesOrgDto retCre = ceOrgMapper.queryCeOrgBySeqno(map);
    	List<CreditLines> retMxList=ceOrgMapper.queryCeOrgByPflshAndCustomId(retCre.getPflsh(),retCre.getCustomerId());
    	retCre.setCreditQuoteItem(retMxList);
    	return retCre;
    }

    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param
     */
    public int searchRepeatByVmDate(Map<String, Object> param ) {
    		return ceOrgMapper.searchRepeatByVmDate(param);
    }

    /**
     * 新建编辑场合时查询生效及到期日是否重复
     * @param
     */
    public int searchRepeatByVmDateArea(Map<String, Object> param ) {
    		return ceOrgMapper.searchRepeatByVmDateArea(param);
    }
    /**
     * 新建编辑场合时查询批复号是否重复
     * @param
     */
    public int searchRepeatByPflsh(String pflsh,String seqno,String creditMstId ) {
    		return ceOrgMapper.searchRepeatByPflsh(pflsh,seqno,creditMstId);
    }
    // add by yushilong 查询是否重复校验 20200629 start
    /**
     * 新建编辑场合时查询数据是否重复
     * @param param
     */
    public int searchRepeat(Map<String, Object> param) {
    		return ceOrgMapper.searchRepeat(param);
    }
    // add by yushilong 查询是否重复校验 20200629 end
    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(CreditLinesOrgDto entity) {
		// 更新非法人主体额度
		if (StringUtil.isNotEmpty(entity.getNlpscreditline())) {
			List<CreditTagInfo> tagInfoList = creditConfInfoDaoMapper.findCreditTagInfo(DictCodeListRsk.额度分类_非法人机构.getValue());
			CreditTagInfo tagInfo;
			if (tagInfoList.size() == 0){
				//标签
				tagInfo = new CreditTagInfo();
				tagInfo.setCreditTagId(IDGenerator.generate());
				tagInfo.setCreditType(DictCodeListRsk.额度分类_非法人机构.getValue());
				creditTagInfoMapper.insert(tagInfo);
			}else {
				tagInfo = tagInfoList.get(0);
			}

			List<CreditConfInfo> confInfoList = creditConfInfoDaoMapper.findCreditConfInfo(tagInfo.getCreditTagId());
			CreditConfInfo confDto;
			if (confInfoList.size() == 0){
				//分项
				confDto = new CreditConfInfoDto();
				confDto.setCreditMstId(IDGenerator.generate());
				confDto.setCreditTagId(tagInfo.getCreditTagId());
				creditConfInfoMapper.insert(confDto);
			}else {
				confDto = confInfoList.get(0);
			}
			//额度
			CreditLines linesDto = new CreditLines();
			linesDto.setSeqno(getQuotePrimaryKey());
			linesDto.setCreditType(DictCodeListRsk.授信占用方式_机构授信.getValue());
			linesDto.setCreditMstId(confDto.getCreditMstId());
			linesDto.setCustomerId(entity.getCustomerId());
			linesDto.setPflsh(entity.getPflsh());
			linesDto.setCreditFlag(DictCodeList.是否_是.getValue());
			linesDto.setHapCreditAmt(new BigDecimal(0));
			linesDto.setCreditAmt(entity.getNlpscreditline().multiply(new BigDecimal(100000000)));
			linesDto.setCreditAmtAdd(linesDto.getCreditAmt());
			linesDto.setVdate(entity.getVdate());
			linesDto.setMdate(entity.getMdate());
			linesDto.setIsInterbank(entity.getIsInterbank());
			linesDto.setIsLoopAmt(entity.getIsLoopAmt());
			creditLinesMapper.insert(linesDto);
		}
		if (StringUtil.isNotEmpty(entity.getCreditAmt())) {
			entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
		}
		entity.setHapCreditAmt(new BigDecimal(0));
		entity.setCreditFlag(DictCodeList.是否_是.getValue());
		entity.setCreditType("01");

		if (StringUtil.isNotEmpty(entity.getCreditLabelItem())) {
			for (CreditConfInfoDto dto : entity.getCreditLabelItem()) {
				List<CreditConfInfo> confInfoList = creditConfInfoDaoMapper.findCreditConfInfo(dto.getCreditTagId());
				String creditMstId = IDGenerator.generate();
				if (confInfoList.size() > 0){
					dto.setCreditMstId(confInfoList.get(0).getCreditMstId());
				}else {
					dto.setCreditMstId(creditMstId);
					creditConfInfoDaoMapper.insert(dto);
				}

				CreditLines creditLines = new CreditLines();
				if (StringUtil.isNotEmpty(dto.getCreditAmt())) {
					creditLines.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
				}
				creditLines.setSeqno(dto.getSeqno());
				creditLines.setVdate(entity.getVdate());
				creditLines.setMdate(entity.getMdate());
				creditLines.setRemark(entity.getRemark());
				creditLines.setPflsh(entity.getPflsh());
				creditLines.setHapCreditAmt(new BigDecimal(0));
				creditLines.setCustomerId(entity.getCustomerId());
				creditLines.setCreditFlag(DictCodeList.是否_是.getValue());
				creditLines.setParentCustomerId(entity.getCustomerId());
				creditLines.setCreditMstId(dto.getCreditMstId());
				creditLines.setCreditType(DictCodeListRsk.授信占用方式_机构授信.getValue());
				creditLines.setIsLoopAmt(entity.getIsLoopAmt());
				creditLinesMapper.insert(creditLines);
			}
		}
		creditLinesMapper.insert(entity);
		// modify by yushilong 入库逻辑变更 20200629 end
    }


    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(CreditLinesOrgDto entity) {
    	if(null!=entity){
    		// STEP 1 更新机构额度数据
    		if(StringUtil.isNotEmpty(entity.getCreditAmt())){
        		entity.setCreditAmt(entity.getCreditAmt().multiply(new BigDecimal(100000000)));
        	}
    		creditLinesMapper.updateByPrimaryKeySelective(entity);

			// 更新非法人主体额度
			CreditLines creditLinesNlps = creditConfInfoDaoMapper.findCreditLines(entity.getCustomerId(),entity.getPflsh());
			if (StringUtil.isNotEmpty(entity.getNlpscreditline())) {
				if (creditLinesNlps != null){
					creditLinesNlps.setCreditAmt(entity.getNlpscreditline().multiply(new BigDecimal(100000000)));
					creditLinesMapper.updateByPrimaryKeySelective(creditLinesNlps);
				}else {
					List<CreditTagInfo> tagInfoList = creditConfInfoDaoMapper.findCreditTagInfo(DictCodeListRsk.额度分类_非法人机构.getValue());
					CreditTagInfo tagInfo;
					if (tagInfoList.size() == 0){
						//标签
						tagInfo = new CreditTagInfo();
						tagInfo.setCreditTagId(IDGenerator.generate());
						tagInfo.setCreditType(DictCodeListRsk.额度分类_非法人机构.getValue());
						creditTagInfoMapper.insert(tagInfo);
					}else {
						tagInfo = tagInfoList.get(0);
					}

					List<CreditConfInfo> confInfoList = creditConfInfoDaoMapper.findCreditConfInfo(tagInfo.getCreditTagId());
					CreditConfInfo confDto;
					if (confInfoList.size() == 0){
						//分项
						confDto = new CreditConfInfoDto();
						confDto.setCreditMstId(IDGenerator.generate());
						confDto.setCreditTagId(tagInfo.getCreditTagId());
						creditConfInfoMapper.insert(confDto);
					}else {
						confDto = confInfoList.get(0);
					}
					//额度
					CreditLines linesDto = new CreditLines();
					linesDto.setSeqno(getQuotePrimaryKey());
					linesDto.setCreditType(DictCodeListRsk.授信占用方式_机构授信.getValue());
					linesDto.setCreditMstId(confDto.getCreditMstId());
					linesDto.setCustomerId(entity.getCustomerId());
					linesDto.setPflsh(entity.getPflsh());
					linesDto.setCreditFlag(DictCodeList.是否_是.getValue());
					linesDto.setHapCreditAmt(new BigDecimal(0));
					linesDto.setCreditAmt(entity.getNlpscreditline().multiply(new BigDecimal(100000000)));
					linesDto.setCreditAmtAdd(linesDto.getCreditAmt());
					linesDto.setVdate(entity.getVdate());
					linesDto.setMdate(entity.getMdate());
					linesDto.setIsInterbank(entity.getIsInterbank());
					linesDto.setIsLoopAmt(entity.getIsLoopAmt());
					creditLinesMapper.insert(linesDto);
				}
			}else {
				if (creditLinesNlps != null){
					creditLinesMapper.deleteByPrimaryKey(creditLinesNlps.getSeqno());
				}
			}

    		// STEP 2 更新分项额度数据
    		Map<String,Object> params = new HashMap<String,Object>();
			params.put("seqno", entity.getSeqno());
    		// 查询数据库中记录，并转换成Map
    		List<CreditConfInfoDto> rs = creditConfInfoDaoMapper.getCreditConfig(params);
    		Map<String,CreditConfInfoDto> confInfoDtoMap = rs.stream().collect(Collectors.toMap(CreditConfInfoDto :: getCreditTagId,item -> item, (key1,key2) -> key1));
			// 页面传进来的数据和数据库中匹配
    		if(StringUtil.isNotEmpty(entity.getCreditLabelItem())){
        		for(CreditConfInfoDto dto:entity.getCreditLabelItem()){
					CreditLines creditLines = new CreditLines();
					// 匹配上的时候更新
					CreditConfInfoDto item = confInfoDtoMap.get(dto.getCreditTagId());
					if (item != null){
						// 查询对应的分项额度
						creditLines = creditLinesMapper.selectByPrimaryKey(item.getSeqno());
						// 重新设置关键值
						if(StringUtil.isNotEmpty(dto.getCreditAmt())){
							creditLines.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
						}
						creditLines.setVdate(entity.getVdate());
						creditLines.setMdate(entity.getMdate());
						creditLines.setIsLoopAmt(entity.getIsLoopAmt());
						creditLines.setRemark(entity.getRemark());
						// 执行更新操作
						creditLinesMapper.updateByPrimaryKeySelective(creditLines);
						// 移除更新过后的数据，后续还存在Map集合中的数据需要删除
						confInfoDtoMap.remove(dto.getCreditTagId());
					}else {
						// 数据库中不存在的数据，需要新建
						List<CreditConfInfo> confInfoList = creditConfInfoDaoMapper.findCreditConfInfo(dto.getCreditTagId());
						String creditMstId = IDGenerator.generate();
						if (confInfoList.size() > 0){
							dto.setCreditMstId(confInfoList.get(0).getCreditMstId());
						}else {
							dto.setCreditMstId(creditMstId);
							creditConfInfoDaoMapper.insert(dto);
						}
						creditLines.setSeqno(getQuotePrimaryKey());
						creditLines.setHapCreditAmt(new BigDecimal(0));
						if (StringUtil.isNotEmpty(dto.getCreditAmt())) {
							creditLines.setCreditAmt(dto.getCreditAmt().multiply(new BigDecimal(100000000)));
						}
						creditLines.setVdate(entity.getVdate());
						creditLines.setMdate(entity.getMdate());
						creditLines.setRemark(entity.getRemark());
						creditLines.setPflsh(entity.getPflsh());
						creditLines.setHapCreditAmt(new BigDecimal(0));
						creditLines.setCustomerId(entity.getCustomerId());
						creditLines.setCreditFlag(DictCodeList.是否_是.getValue());
						creditLines.setParentCustomerId(entity.getCustomerId());
						creditLines.setCreditMstId(dto.getCreditMstId());
						creditLines.setCreditType(DictCodeListRsk.授信占用方式_机构授信.getValue());
						creditLines.setIsLoopAmt(entity.getIsLoopAmt());
						creditLinesMapper.insert(creditLines);
					}
        		}
				// 删除数据
				confInfoDtoMap.values().forEach(item -> {
					creditLinesMapper.deleteByPrimaryKey(item.getSeqno());
				});
        	}
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
     * @param
     */
    public int delete(CreditLinesOrgDto dto) {
    	Map<String, Object> param=new HashMap<String, Object>();
    	param.put("seqno", dto.getSeqno());
    	CreditLinesOrgDto retCre = ceOrgMapper.queryCeOrgBySeqno(param);
    	return ceOrgMapper.deleteOrgMx(retCre.getCustomerId(),retCre.getPflsh());
    }

	/**
	 * 如果当前数据额度已被占用则不允许操作
	 * by nixiukuan 20191024
	 * @param param
	 */
	public Boolean searchIsUse(Map<String, Object> param) {
		int count= ceOrgMapper.searchIsUse(param);
		if(count>0)
		{
			return  true;
		}
		return  false;
	}
	
	
    public String getOrgInfo(String customerId) {
    	return ceOrgMapper.getOrgInfo(customerId);
    }


}
