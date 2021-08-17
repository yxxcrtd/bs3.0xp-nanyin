/*
 * 文件名：RskValTableImportService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.valtblimport.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumAssetSubMapper;
import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblSumAssetSub;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.valtblimport.dao.PreOccupationChangeMapper;
import com.joyintech.service.risk.valtblimport.dao.RskValTableImportMapper;
import com.joyintech.service.risk.valtblimport.dto.PreOccupActualDetailDto;
import com.joyintech.service.risk.valtblimport.dto.PreOccupationChangeDto;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.risk.valtblimport.dto.ValTableImpRiskDto;
import com.joyintech.service.risk.valtblimport.dto.ValType;
import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumAssetSubMapper;
import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblSumAssetSub;


@Service("backRskValTableImportService")
public class RskValTableImportService {
	
    @Autowired
    private RskValtblAssetSubMapper rskValtblAssetSubMapper;
    @Autowired
    private RskValtblSumAssetSubMapper rskValtblSumAssetSubMapper;
    @Autowired
    private RskValTableImportMapper rskValTableImportMapper;
    
    @Autowired
    private PreOccupationChangeMapper preOccupationChangeMapper;
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void saveValTbl(RskValtblAssetSubDto dto){ 
        rskValTableImportMapper.deleteValTable(dto);
        rskValTableImportMapper.deleteSumValTable(dto);
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            for(RskValtblAssetSubDto entity:dto.getSelectedItem()){
                if(StringUtil.equals(entity.getValType().getDesc(),ValType.ASSET.getDesc())){
                    RskValtblAssetSub record = new RskValtblAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblAssetSubMapper.insert(record);
                }else{
                    RskValtblSumAssetSub record = new RskValtblSumAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblSumAssetSubMapper.insert(record);
                }
            }
        }
    }
    
    /**
     *  估值表科目数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public int selectCount(Map<String,Object> paramMap){
        return rskValTableImportMapper.selectCount(paramMap);
    }
    
    /**
     * 上农商个性化_估值表导入 
     * 获取估值表文件中各资产的分配明细 
     * add by zhangpei
     * 20201222
     */
	public List<PreOccupationChangeDto> preOccupationDetail(RskValtblAssetSubDto dto) {
		// 如果导入的估值表组合非自主管理资产，则跳出这个方法 TODO

		// 1.查询估值日期当日自主专户代码的组合分配情况
		Map<String, Object> paramMap = new HashedMap<String, Object>();
		// 实际持仓
		paramMap.put("POSITION_TYPE_03", BussinessDictCodeList.持仓类型_实际.getValue());
		// 估值日期
		paramMap.put("valDate", DateUtil.format(dto.getValDate(), "yyyy-MM-dd"));
		// 自主管理专户
		paramMap.put("ptlId", dto.getPtlId());
		// 自主管理资产 投管类型
		paramMap.put("FINPROD_TYPE_TG_F17", DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue());
		BigDecimal totalAmout = preOccupationChangeMapper.getSelfManagerTotal(paramMap);

		//2.计算估值表文件中各资产的分配明细 
		List<PreOccupationChangeDto> list = preOccupationChangeMapper.getSelfManagerByOne(paramMap);
		if (!StringUtil.isEmptyList(list) && StringUtil.isNotEmpty(totalAmout)) {
			for (PreOccupationChangeDto oneAmoutDto : list) {
				// 计算组合持仓占比Pi 单个持仓份额/汇总持仓份额 全精度计算
				oneAmoutDto.setPercentage(ArithmeticUtil.divide(oneAmoutDto.getAmount(), totalAmout, 8));
				// 获取估值表持仓情况Qi 计算估值表实际持仓占用风险数额
				List<PreOccupActualDetailDto> detailList = preOccupationChangeMapper.getValtblAllotAmoutForSmv(paramMap);
				if (!StringUtil.isEmptyList(detailList)) {
					for (PreOccupActualDetailDto detailDto : detailList) {
						//如果是债券，对债券授信特殊处理
						if(StringUtil.equals(detailDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
							paramMap.put("finprodId", detailDto.getAssetFinprodId());
							List<PreOccupActualDetailDto> detailListCredit = preOccupationChangeMapper.getValtblAllotAmout(paramMap);
							if(!StringUtil.isEmptyList(detailListCredit)){
								detailDto.setAllotAmoutCredit(ArithmeticUtil.multiply(detailListCredit.get(0).getValAmout(), oneAmoutDto.getPercentage()));
							}
						}else{
							detailDto.setAllotAmoutCredit(ArithmeticUtil.multiply(detailDto.getValAmout(), oneAmoutDto.getPercentage()));
						}
						// 每个组合的数量记为Rij（估值表实际持仓）=估值表文件中的该资产投资的数量Qi*1中计算的比例Pi
						detailDto.setAllotAmout(ArithmeticUtil.multiply(detailDto.getValAmout(), oneAmoutDto.getPercentage()));
					}
				}
				oneAmoutDto.setDetailDtos(detailList);
			}
		}
		
		return list;

	}
	/**
	 * 上农商个性化_估值表导入
	 * 查询需要释放的自主管理信息
	 * add by zhangpei 
	 * 20201223
	 * @param dto
	 * @return
	 */
	public List<PreOccupationChangeDto> queryNeedFreedInfo(RskValtblAssetSubDto dto){
		Map<String, Object> paramMap = new HashedMap<String, Object>();
		// 估值日期
		paramMap.put("valDate", DateUtil.format(dto.getValDate(), "yyyy-MM-dd"));
		// 自主管理专户
		paramMap.put("ptlId", dto.getPtlId());
		//查询需要释放的预占
		paramMap.put("entrustStatus", DictCodeList.委托状态_待接收.getValue());
		return preOccupationChangeMapper.queryNeedFreedInfo(paramMap);
	}
	/**
	 * 上农商个性化_估值表导入
	 * 根据委托代码，更新指令状态为‘已完成’
	 * add by zhangpei
	 * 20201224
	 * @param list
	 * @return
	 */
	public int updateEntrustStatus(List<PreOccupationChangeDto> list){
		int result=0;
		if(!StringUtil.isEmptyList(list)){
			for(PreOccupationChangeDto dto: list){
				dto.setEntrustStatus(DictCodeList.委托状态_已完成.getValue());
				result+=preOccupationChangeMapper.updateEntrustStatus(dto);
			}
		}
		return result;
	}
	/**
	 * 插入导入估值表风险实占留痕表
	 * @param list
	 * @return
	 */
	public int insertValImpRisk(List<ValTableImpRiskDto> list){
		int result=0;
		if(!StringUtil.isEmptyList(list)){
			for(ValTableImpRiskDto dto:list){
				result+=preOccupationChangeMapper.insertValImpRisk(dto);
			}
		}
		return result;
	}
	/**
	 * 删除导入估值表分线实占留痕表的相关数据
	 * @param list
	 * @return
	 */
	public int deleteByPrimaryKeyRisk(List<ValTableImpRiskDto> list){
		int result=0;
		if(!StringUtil.isEmptyList(list)){
			for(ValTableImpRiskDto dto:list){
				result+=preOccupationChangeMapper.deleteByPrimaryKeyRisk(dto.getTradeId());
			}
		}
		return result;
	}
	/**
	 * 根据委外组合和导入时间查询导入的指令数据
	 * @param dto
	 * @return
	 */
	public List<ValTableImpRiskDto> queryImpRiskInfo(RskValtblAssetSubDto dto){
		Map<String, Object> paramMap = new HashedMap<String, Object>();
		// 估值日期
		paramMap.put("valDate", DateUtil.format(dto.getValDate(), "yyyy-MM-dd"));
		// 自主管理专户
		paramMap.put("ptlId", dto.getPtlId());
		return preOccupationChangeMapper.queryImpRiskInfo(paramMap);
	}

	public int selectGoalCount(Map<String, Object> paramMap) {
		return rskValTableImportMapper.selectGoalCount(paramMap);
	}
}
