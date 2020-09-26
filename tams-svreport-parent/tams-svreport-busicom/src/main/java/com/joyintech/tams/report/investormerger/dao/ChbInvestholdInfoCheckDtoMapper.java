package com.joyintech.tams.report.investormerger.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.joyintech.tams.common.base.entity.ChbInvestholdInfoSub;
import com.joyintech.tams.report.investormerger.dto.ChbEquHoldCheckDto;
import com.joyintech.tams.report.investormerger.dto.ChbInvestholdInfoCheckDto;
import com.joyintech.tams.report.submitmaintenance.investorsholdinfo.dto.InvestorsHoldInfo001Dto;
import com.joyintech.tams.report.submitmanagement.investorsholdinforegist.dto.InvestorsHoldInfoRegist001Dto;

public interface ChbInvestholdInfoCheckDtoMapper {
    int insert(ChbInvestholdInfoCheckDto record);

    int insertSelective(ChbInvestholdInfoCheckDto record);
    
    /**
     * @Description 通过model查询
     * @param record
     * @return List<ChbInvestholdInfoTmpDto>
     */
    List<ChbInvestholdInfoCheckDto> selectBymodel(ChbInvestholdInfoCheckDto record);
    int deleteBymodel(ChbInvestholdInfoCheckDto record);
    int updateMergerIdAndOperatestatus(ChbInvestholdInfoCheckDto record);
    
    /**
     * @Description 通过持有者身份信息识别标识查询投资者持有信息
     * @param indentifyMark
     * @return ChbInvestholdInfoTmpDto
     */
    List<ChbInvestholdInfoCheckDto> selectByIndentifyMark(@Param("recordlist") List<ChbEquHoldCheckDto> recordlist);
    
    int insertToChbInvestholdInfo(ChbInvestholdInfoCheckDto record);
    
    int deleteChbInvestholdInfo(ChbEquHoldCheckDto record);
    /**
     * 查询 对未合并的数据 进行合并处理
     * @return
     */
    List<InvestorsHoldInfo001Dto> selectHbhChbInvestholdInfoUnmerged();
    /**
     * 将合并处理后查询后的检查表数据更新 合并状态和合并标识
     * @return
     */
    int updateChbInvestholdCheckAftermerged();
    List<String> selectLastMergerIndentifyMarkAndBankcode(); 
    /**
     * @Description 通过model查询正式表（维护表）
     * @param record
     * @return List<ChbInvestholdInfoTmpDto>
     */
    List<ChbInvestholdInfoCheckDto> selectBymodelFromFormer(ChbInvestholdInfoCheckDto record);
    /**
     * 修改持有信息检查表状态为 未处理
     * @param record
     * @return
     */
   int updateOperatestatusW(ChbInvestholdInfoCheckDto record);
   /**
    * 删除维护表中数据 根据识别标识和登记银行代码
    * @param record
    * @return
    */
   int deleteByBankcodeAndIndentifyMarkWH(ChbInvestholdInfoCheckDto record);
   /**
    * 删除登记表中数据 根据识别标识和登记银行代码
    * @param record
    * @return
    */
   int deleteByBankcodeAndIndentifyMarkDJ(ChbInvestholdInfoCheckDto record);
   
   int updateHoldInfoCheck(ChbEquHoldCheckDto record);
   
   /**
	 * @Description 更新投资者持有信息登记表
	 * @param record
	 * @return int
	 */
	int updateChbInvestholdSub(@Param("indentifyMark")String indentifyMark,@Param("mergerindentifymark")String mergerindentifymark);

	
	List<InvestorsHoldInfo001Dto> selectAllHoldInfoData(@Param("newKeyList")List<InvestorsHoldInfo001Dto> newKeyList);

	
	List<InvestorsHoldInfo001Dto> queryRepeatEquityInfoHoldUnSubmit(@Param("keys")List<String> keys);
	List<InvestorsHoldInfo001Dto>queryNotInHoldButIncheckInfoHold(@Param("keys")List<String> keys);
	List<InvestorsHoldInfoRegist001Dto> queryInvestorsHoldInfoRegistByPkUnsubmit(@Param("keys")List<String> keys);
	
	int deleteInvestholdInfoBatch(@Param("keys")List<String> keys);
	int deleteInvestholdInfoSubBatch(@Param("keys")List<String> keys);
	int updateUnMergeInvestorsHoldInfo(@Param("keys")List<String> keys);
	int updateUnMergeInvestorsHoldInfoSub(@Param("keys")List<String> keys);
	int updateUnMergeInvestorsHoldInfoCheck(@Param("keys")List<String> keys);
	
	int checkInvestholdInfoSub(InvestorsHoldInfo001Dto item);

	int deleteChbInvestholdInfoSub(ChbEquHoldCheckDto chbEquHoldCheckDto);

	int insertChbInvestholdInfoSub(ChbInvestholdInfoSub holdsub);

}