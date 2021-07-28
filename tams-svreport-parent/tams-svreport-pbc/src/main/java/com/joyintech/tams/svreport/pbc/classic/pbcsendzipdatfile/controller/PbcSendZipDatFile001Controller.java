package com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.controller;


import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.pbc.classic.pbcsendzipdatfile.service.PbcSendZipDatFileMService;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;


/**
 * 人行报送文件管理界面Controller
 * 〈功能详细描述〉
 * @author meifaxin
 * @version 1.0 2021年7月27日
 * @see PbcSendZipDatFile001Controller
 * @since 1.0
 */
@Controller
@PageId("PbcSendZipDatFile")
@RequestMapping("/pbc/pbcsendzipdatfile/PbcSendZipDatFile")
public class PbcSendZipDatFile001Controller extends BaseController{


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * service
     */
    @Autowired
    private PbcSendZipDatFileMService pbcSendZipDatFileMService;

	
    /**
	 * {@inheritDoc}
	 */
    @RequestMapping(PAGE_INIT)
	@Override
	public String init(Model model) {
		super.setSelectOptionToModel(model, DictCode.人行报送类型, DictCode.报送状态, DictCode.人行报送反馈状态);
		return super.init(model);
	}

	/**
	 *
	 * 业务画面查询事件
	 *
	 * @return
	 * @see
	 * @since 1.0
	 */
	@ResponseBody
	@RequestMapping("findPage")
	public Page<PbcMonthReport> searchPage() {
		Page<PbcMonthReport> page = pbcSendZipDatFileMService
				.searchPage(RequestUtil.getPageParam());
		return page;
	}


    /**
     *
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPageBysubmitDate")
    public String searchPageBysubmitDate() {
        Map<String,String> result = new HashMap<String,String>();
        PbcMonthReport report = pbcSendZipDatFileMService
                .searchPageBysubmitDate(RequestUtil.getPageParam());
        if (report.getStartdate() != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(report.getStartdate());
            result.put("YEAR",cal.get(Calendar.YEAR)+"");
            result.put("MONTH",cal.get(Calendar.MONTH)+1+"");
        }
        return JsonUtils.object2Json(result);
    }


}
