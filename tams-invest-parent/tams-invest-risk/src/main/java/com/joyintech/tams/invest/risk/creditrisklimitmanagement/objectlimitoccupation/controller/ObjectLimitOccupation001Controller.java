package com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.controller;

import java.util.Date;
import java.util.Map;

import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.dto.ObjectLimitOccupationDto;
import com.joyintech.tams.invest.risk.creditrisklimitmanagement.objectlimitoccupation.service.ObjectLimitOccupation001Service;

/**
 * 主体额度占用调整
 * 
 * @author chengzhong
 * @version 1.0 2020年02月26日
 * @see ObjectLimitOccupation001Controller
 * @since 1.0
 */
@Controller
@PageId("ObjectLimitOccupation001")
@RequestMapping("/invest/risk/creditrisklimitmanagement/objectlimitoccupation/ObjectLimitOccupation001")
public class ObjectLimitOccupation001Controller extends BaseController {

	/**
	 * ObjectLimitOccupation001Service
	 */
	@Autowired
	private ObjectLimitOccupation001Service objectLimitOccupation001Service;

	/**
	 * 初始化的查询条件
	 */
	private static final IDictCode[] DICT_CODES = new DictCode[] { DictCode.额度分类 };
	
	@Autowired
    private IPlatformDate iPlatformDate;
	
	@Override
	public String init(Model model) {
		setSelectOptionToModel(model, DICT_CODES);
		return super.init(model);
	}

	/**
	 * 画面查询方法
	 * 
	 * @return 所有数据
	 * @throws Exception
	 *             异常
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<ObjectLimitOccupationDto> selectAll() throws Exception {
		Map<String, Object> param = RequestUtil.getParameters(getRequest());
		String platDate = iPlatformDate.getSysDateStr();
		param.put("ptDate", platDate);
		return objectLimitOccupation001Service.selectForPage(param);
	}

	/**
	 * 主体名称联想文本框
	 * 
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getObject")
	public Page<SelectOptionDto> getObject(@RequestParam Map<String, Object> params) throws Exception {
		return objectLimitOccupation001Service.getObject(params);
	}
    
	/**
	 * 导出信息excel
	 * 
	 * @return excel
	 * @throws Exception
	 *             异常
	 */
	@RequestMapping("export")
	public ResponseEntity<byte[]> export() throws Exception {
		return objectLimitOccupation001Service.export(this.getPageId(), RequestUtil.getParameters(getRequest()));

	}
	
	/**
	 * 查询操作人员
	 * @return
	 */
    @ResponseBody
    @RequestMapping("getUserName")
    public Page<SelectOptionDto> getUserName() {
    	return objectLimitOccupation001Service.getUserName(RequestUtil.getPageParam());
    }
    
}
