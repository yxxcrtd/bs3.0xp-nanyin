package com.joyintech.tams.report.system.subinfobatchlog.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.common.base.entity.ChbSysUser;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.subinfobatchlog.dao.ChbSubinfoAutoBatchLogMapper;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;

/**
 * 中债报送信息登记生成日志servcie
 * 
 * @author zhangyf
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ChbSubinfoBatchLogService extends BaseService<ChbSubinfoBatchLog> {

	@Autowired
	private ChbSubinfoAutoBatchLogMapper chbSubinfoAutoBatchLogMapper;

	/**
	 * 用户列表
	 */
	@DataSource(DataSourceType.OTHER)
	public List<SelectOptionDto> selectUserList() {
		List<ChbSysUser> userList = chbSubinfoAutoBatchLogMapper.selectUserList();
		if (CollectionUtils.isEmpty(userList)) {
			return null;
		}
		List<SelectOptionDto> optionsList = new ArrayList<>();
		userList.forEach(user -> {
			SelectOptionDto options = new SelectOptionDto();
			options.setId(user.getUserName());
			options.setText(user.getRealName());
			optionsList.add(options);
		});
		return optionsList;
	}

	/**
	 * 查询日志结果
	 * 
	 * @param map
	 * @return
	 */
	public Page<ChbSubinfoBatchLog> queryBatchResult(Map<String, Object> map) {
		int pagenum = Integer.parseInt(map.get(GlobalConstant.PARAM_PAGE).toString());
		int legth = Integer.parseInt(map.get(GlobalConstant.PARAM_LENGTH).toString());
		Page<ChbSubinfoBatchLog> page = super.queryForPage(pagenum, legth,
				() -> chbSubinfoAutoBatchLogMapper.queryBatchResult(map));
		for (ChbSubinfoBatchLog dto : page.getRoot()) {
			dto.setTaskName(BatchBeanName.getBatchNameByTaskId(dto.getTaskId()));
			dto.setFileType(SysDataUtil.getNameFromDataDict(DictCode.报送文件类别, dto.getFileType()));
			dto.setDealStatus(SysDataUtil.getNameFromDataDict(DictCode.报送处理状态, dto.getDealStatus()));
		}
		return page;
	}

	/**
	 * 日志存表
	 */
	public int insert(ChbSubinfoBatchLog log) {
		return chbSubinfoAutoBatchLogMapper.insert(log);
	}
	
	/**
	 * 根据主键查询日志
	 */
	public ChbSubinfoBatchLog selectByPrimaryKey(String batchExeId) {
		return chbSubinfoAutoBatchLogMapper.selectByPrimaryKey(batchExeId);
	}
	
	/**
	 * 根据主键更新日志
	 */
	public int updateByPrimaryKey(ChbSubinfoBatchLog log) {
		return chbSubinfoAutoBatchLogMapper.updateByPrimaryKeySelective(log);
	}
	
	/**
	 * 根据处理状态查询日志集合
	 */
	public List<ChbSubinfoBatchLog> selectByDealStatus(ChbSubinfoBatchLog status) {
		return chbSubinfoAutoBatchLogMapper.selectByDealStatus(status);
	}
}
