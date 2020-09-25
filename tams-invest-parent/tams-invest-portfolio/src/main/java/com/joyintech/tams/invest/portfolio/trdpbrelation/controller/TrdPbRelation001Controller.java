package com.joyintech.tams.invest.portfolio.trdpbrelation.controller;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.invest.portfolio.trdpbrelation.dto.TrdPbRelation001Dto;
import com.joyintech.tams.invest.portfolio.trdpbrelation.service.TrdPbRelation001Service;

/**
 * PB编号管理
 * 〈功能详细描述〉
 * @author caoxue
 * @version 1.0 2019年12月24日
 * @see TrdPbRelation001Controller
 * @since 1.0
 *
 */
@Controller
@PageId("TrdPbRelation001")
@RequestMapping("/invest/portfolio/trdpbrelation/TrdPbRelation001")
public class TrdPbRelation001Controller extends BaseController {

    @Autowired
    private TrdPbRelation001Service trdPbRelation001Service;

    /**
     * 界面初始化 {@inheritDoc}
     */
    @Override
    @RequestMapping(PAGE_INIT)
    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 分页查询
     * 
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<TrdPbRelation001Dto> findPage()
        throws Exception {
        return trdPbRelation001Service.selectForPage(
            RequestUtil.getParameters(getRequest()));
    }

    /**
     * 画面点击保存按钮时check
     *
     * @param dto 实体类
     * @param result
     * @return check结果
     */
    @ResponseBody
    @RequestMapping("deleteCheck")
    public AjaxResult deleteCheck(@RequestBody @Validated({
        ValidateGroupDelete.class}) TrdPbRelation001Dto dto, BindingResult result) {
        return commonCheck(result, dto.getOperateType());
    }

    /**
     * 删除
     * 
     * @param dto MstMarketInstitutionsDto
     * @param br BindingResult
     * @return AjaxResult
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping(ACTION_DELETE)
    public AjaxResult delete(@RequestBody TrdPbRelation001Dto dto)
        throws Exception {
        String msg = "删除失败";
        int num = trdPbRelation001Service.delete(dto);
        if (num > 0) {
            msg = "删除成功";
        }
        return new AjaxResult(msg, MessageType.提示消息);
    }

    /**
     * 导出信息excel
     * 
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping(ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        Map<String, Object> paraMap = RequestUtil.getPageParam();
        List<TrdPbRelation001Dto> listdto = trdPbRelation001Service.selectForExport(
            paraMap);

        File outFile = trdPbRelation001Service.makeFile(
            "/com/joyintech/tams/invest/portfolio/trdpbrelation/conf/export_conf.xml",
            listdto);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }
}
