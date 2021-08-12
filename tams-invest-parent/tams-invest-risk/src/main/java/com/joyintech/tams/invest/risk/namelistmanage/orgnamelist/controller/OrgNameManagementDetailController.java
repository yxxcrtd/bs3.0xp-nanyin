package com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListLogDto;
import com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.service.OrgNameManagementDetailService;
import com.joyintech.tams.invest.risk.namelistmanage.orgnamelist.service.OrgNameManagementRecordService;
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
 * 名单管理--双击明细
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see OrgNameManagementDetailController
 * @since 1.0
 */
@Controller("orgNameManagementDetailController1")
@PageId("OrgNameManagementDetail")
@RequestMapping("/invest/risk/namelistmanage/orgnamelist/OrgNameManagementDetail")
public class OrgNameManagementDetailController extends BaseController {

    @Autowired
    private OrgNameManagementService orgNameManagementService;
    
    @Autowired
    private OrgNameManagementDetailService orgNameManagementDetailService;
    
    @Autowired
    private OrgNameManagementRecordService orgNameManagementRecordService;
    @Override
    public String init(Model model) {
        model.addAttribute("workflowId",this.getParam("workflowId"));
        model.addAttribute("workflowStatus",this.getParam("workflowStatus"));
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
    @RequestMapping("selectAssetListDetail")
    public List<RskAccessListDetailDto> selectAssetListDetail()
        throws Exception {
        List<RskAccessListDetailDto> resultList = orgNameManagementService.selectAssetListDetail(
            RequestUtil.getParameters(getRequest()));
        return resultList;
    }

    /**
     * 初始化查询
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("selectAssetListDetailLog")
    public List<RskAccessListLogDto> selectAssetListDetailLog()
        throws Exception {
        List<RskAccessListLogDto> resultList = orgNameManagementService.selectAssetListDetailLog(
            RequestUtil.getParameters(getRequest()));
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
        Map<String, Object>  params=RequestUtil.getParameters(getRequest());
        //分别下载明细列表与履历列表
        if(StringUtil.isNotEmpty(params.get("type")) && params.get("type").equals("record")){
            return orgNameManagementRecordService.export("名单更新履历.xlsx",
                RequestUtil.getParameters(getRequest()));
        }else{
            return orgNameManagementDetailService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest()));
        }
       
    }
}
