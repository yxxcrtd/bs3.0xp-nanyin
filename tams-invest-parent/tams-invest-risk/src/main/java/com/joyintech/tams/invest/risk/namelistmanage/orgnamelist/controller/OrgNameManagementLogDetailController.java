package com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListLogDto;
import com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.service.OrgNameManagementLogDetailService;
import com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.service.OrgNameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 名单管理--双击履历明细
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see OrgNameManagementLogDetailController
 * @since 1.0
 */
@Controller
@PageId("OrgNameManagementLogDetail")
@RequestMapping("/invest/risk/namelistmanage/orgnamelist/OrgNameManagementLogDetail")
public class OrgNameManagementLogDetailController extends BaseController {

    @Autowired
    private OrgNameManagementService orgNameManagementService;
    
    @Autowired
    private OrgNameManagementLogDetailService orgNameManagementLogDetailService;
    @Override
    public String init(Model model) {
        model.addAttribute("listDate",this.getParam("listDate"));
        model.addAttribute("updateUser",this.getParam("updateUser"));
        model.addAttribute("spr",this.getParam("spr"));
        model.addAttribute("listId",this.getParam("listId"));
        return super.init(model);
    }

    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectNameListLogDetail")
    public List<RskAccessListLogDto> selectNameListLogDetail()
        throws Exception {
        Map<String,Object> param = RequestUtil.getParameters(getRequest());
        List<RskAccessListLogDto> resultList = orgNameManagementService.selectNameListLogDetail(param);
        return resultList;
    }
    
    /**
     * <一句话功能简述> 导出模板
     * 
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @RequestMapping("export")
    public ResponseEntity<byte[]> export()
        throws Exception {
      return orgNameManagementLogDetailService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
       
    }
}
