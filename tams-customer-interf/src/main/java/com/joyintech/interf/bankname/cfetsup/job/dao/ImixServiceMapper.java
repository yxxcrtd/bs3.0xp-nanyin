package com.joyintech.interf.bankname.cfetsup.job.dao;

import com.joyintech.interf.bankname.cfetsup.job.dto.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ImixServiceMapper {
	/**
	 * 获取储存本币成员的文件路径
	 * @return
	 */
	public Map<String, String> getRdiFileInfo();
	/**
	 * 批量插入本币成员 信息
	 * @param data 数据
	 */
	public void saveImixMems(List<FxImixMemInfoDto> data);
	/**
	 * 批量插入本币交易员信息
	 * @param data 数据
	 */
	public void saveImixDealers(List<FxImixMemMailInfoDto> data);
	/**
	 * 批量插入错误代码信息
	 * @param data 数据
	 */
	public void saveErrorInfo(List<FxImixErrorInfoDto> data);
	/**
	 * 批量插入外汇债券信息
	 * @param data 数据
	 */
	public void saveImixBondDealers(List<FxImixBondinfoDto> data);
	/**
	 * 批量插入外汇机构主从关系信息
	 * @param data
	 */
	public void saveImixJgzcgx(List<FxJgzcgxDto> data);
	/**
	 * 批量更新本币交易成员通讯录信息
	 * @return
	 */	
	public void updateBbjycytxl();
	/**
	 * 清空本币交易成员信息表
	 * @return
	 */
	public void clearImixMems();
	/**
	 * 保存本币交易成员信息表
	 * @return
	 */
	public void saveImixMems();
	/**
	 * 清空本币交易通讯录表
	 * @return
	 */
	public void clearImixDealers();
	/**
	 * 清空错误码对照表
	 * @return
	 */
	public void clearErrorInfo();
	/**
	 * 清空外汇债券信息表
	 * @return
	 */
	public  void clearImixBondDealers();
	/**
	 * 清空机构主从关系信息信息表
	 * @return
	 */
	public void saveJgzcgx(List<FxJgzcgxDto> saJgzcgxVos);
	/**
	 * 保存机构主从关系信息信息表
	 * @return
	 */
	public void clearImixJgzcgx();
}
