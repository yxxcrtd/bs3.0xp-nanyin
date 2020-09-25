package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListDetailDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto.RskAccessListLogDto;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagementDetailService;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagementRecordService;
import com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.service.AssetNameManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 名单管理--双击明细
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see AssetNameManagementDetailController
 * @since 1.0
 */
@Controller("assetNameManagementDetailController1")
@PageId("AssetNameManagementDetail")
@RequestMapping("/invest/risk/namelistmanage/assetnamelist/AssetNameManagementDetail")
public class AssetNameManagementDetailController extends BaseController {

    @Autowired
    private AssetNameManagementService assetNameManagementService;
    
    @Autowired
    private AssetNameManagementDetailService assetNameManagementDeatilService;
    
    @Autowired
    private AssetNameManagementRecordService assetNameManagementRecordService;
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
        List<RskAccessListDetailDto> resultList = assetNameManagementService.selectAssetListDetail(
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
        List<RskAccessListLogDto> resultList = assetNameManagementService.selectAssetListDetailLog(
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
            String EXPORT_EXCEL_TYPE = CacheUtil.getSysParam(GlobalConstant.EXPORT_EXCEL_TYPE);
            // 模板文件后缀名
            String fileType = GlobalConstant.FILE_POSTFIX_XLSX; 
            if(StringUtil.equals(EXPORT_EXCEL_TYPE, "02")){
                fileType = GlobalConstant.FILE_POSTFIX_XLS;
            }
            return assetNameManagementRecordService.export("名单更新履历"+fileType,
                RequestUtil.getParameters(getRequest()));
        }else{
            return assetNameManagementDeatilService.export(this.getPageId(),
                RequestUtil.getParameters(getRequest())); 
        }
       
    } 
}
