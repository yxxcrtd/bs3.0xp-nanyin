package com.joyintech.service.svrcom.system.subinfobatchlog.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import com.joyintech.tams.report.system.subinfobatchlog.service.ChbSubinfoBatchLogService;

/**
 * 中债报送信息登记生成日志Controller
 * 
 * @author zhangyf
 */
@RestController
@RequestMapping("/ChbSubinfoBatchLog")
public class ChbSubinfoBatchLogController {

	@Autowired
	private ChbSubinfoBatchLogService chbSubinfoBatchLogService;

	/**
	 * 查询日志结果列表
	 * 
	 * @return 结果列表，分页显示
	 */
	@PostMapping("findPage/{page}/{length}")
	public Page<ChbSubinfoBatchLog> queryBatchProResult(@RequestBody Map<String, Object> param) {
		return chbSubinfoBatchLogService.queryBatchResult(param);
	}
	
	/**
	 * 用户列表
	 */
	@GetMapping("selectUserList")
	public List<SelectOptionDto> selectUserList() {
		return chbSubinfoBatchLogService.selectUserList();
	}
	
	/**
	 * 日志存表
	 */
	@PostMapping("insert")
	public void insert(@RequestBody ChbSubinfoBatchLog log) {
		chbSubinfoBatchLogService.insert(log);
	}
}
