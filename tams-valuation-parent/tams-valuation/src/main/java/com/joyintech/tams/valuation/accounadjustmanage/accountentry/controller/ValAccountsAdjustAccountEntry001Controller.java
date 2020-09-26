/*
 * 文件名：Accountset001Controller.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2016年12月26日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.accounadjustmanage.accountentry.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.system.constant.SystemGlobalConstant;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ExportEntity;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry001Dto;
import com.joyintech.tams.valuation.accounadjustmanage.accountentry.service.ValAccountsAdjustAccountEntry001Service;
/**
*
* 文件名：AccountsAdjustAccountEntry001Controller.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* @author wujie
* 修改时间：2018年5月14日
* 修改内容：会计记账场景配置
* 系统名称：估值核算系统V1.0
*/
@Controller
@PageId("ValAccountsAdjustAccountEntry001")
@RequestMapping("/valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry001")
public class ValAccountsAdjustAccountEntry001Controller extends BaseController {

    /**
     * Accountset001Service
     */
    @Autowired
    private ValAccountsAdjustAccountEntry001Service valAccountsAdjustAccountEntry001Service;
    /**
     * 页面初始化，显示画面<br>
     * 
     * @param model Model
     * @return 初始化路径
     * @see
     * @since 1.0
     */
    public String init(Model model) {
        List<SelectOptionDto> booksetIds = valAccountsAdjustAccountEntry001Service.findBooksets();
        String booksetId = null;
        if (booksetIds != null && booksetIds.size() > 0) {
            booksetId = booksetIds.get(0).getId();
        }
        // 所属账套控件初期化
        model.addAttribute("booksetIds", JsonUtils.object2Json(booksetIds));
        model.addAttribute("booksetId", booksetId);
        return super.init(model);
    }

    /**
     * 所属账套下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/findBooksets")
    @ResponseBody
    public List<SelectOptionDto> findBooksets() {
        return valAccountsAdjustAccountEntry001Service.findBooksets();
    }

    /**
     * 场景模块下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/findTableIds")
    @ResponseBody
    public List<SelectOptionDto> findTableIds() {
        Map<String, Object> param = RequestUtil.getPageParam();
        return valAccountsAdjustAccountEntry001Service.findTableIds(param);
    }
    /**
     * 检索账套代码
     * 
     * @return 账套代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getBooksetId")
    public List<SuggestDto> getBooksetId()
        throws Exception {
//        String param = super.getRequest().getParameter("param");
        
        return valAccountsAdjustAccountEntry001Service.getBooksetId(RequestUtil.getPageParam());
    }
    /**
     * 检索数据
     * 
     * param booksetId 账套代码
     * param nodeId 会计场景代码
     * param level 展开的最大级别
     * @return AccountSetDto list
     */
    @RequestMapping("findAccountEntry")
    @ResponseBody
    public Page<ValAccountsAdjustAccountEntry001Dto> findAccountEntry() {
        String booksetId = this.getParam("booksetId") == null ? "" : this.getParam("booksetId");
        String nodeId = this.getParam("nodeId");
        Integer level = this.getParam("level") == null ? -1 : Integer.valueOf(
            this.getParam("level"));
        return valAccountsAdjustAccountEntry001Service.findAccountEntry(booksetId, nodeId, level);
        // String
        // resultStr="{\"message\":\"\",\"code\":0,\"root\":"+JSONUtil.toJsonString(result)+",\"is\":true,\"tip\":\"操作成功！\"}";
        // try {
        // this.getResponse().setCharacterEncoding("UTF-8");
        // this.getResponse().setContentType("text/html;charset=UTF-8");
        // this.getResponse().getWriter().println(resultStr);
        // } catch (IOException e) {
        // // TODO Auto-generated catch block
        // e.printStackTrace();
        // }
    }

    /**
     * 导出check
     * 
     * @param booksetId 账套代码
     * @param nodeId 会计场景代码
     * @param level 展开的最大级别
     * @return AccountSetDto list
     */
    @RequestMapping("exportCheck")
    @ResponseBody
    public AjaxResult exportCheck(String booksetId, String nodeId, Integer level) {
        List<ValAccountsAdjustAccountEntry001Dto> result = valAccountsAdjustAccountEntry001Service.findAccountEntry(
            booksetId, nodeId, level).getRoot();
        if (result == null || result.isEmpty()) {
            return new AjaxResult(getMessage("VAL_COMMON_E_0015"), MessageType.错误消息);
        }
        return new AjaxResult();
    }

    /**
     * 导出
     * 
     * @return 列表
     * @throws Exception 异常信息
     * @see
     * @since 1.0
     */
    @RequestMapping(value = ACTION_EXPORT)
    public ResponseEntity<byte[]> export()
        throws Exception {
        String booksetId = this.getParam("booksetId");
        String nodeId = this.getParam("nodeId");
        Integer level = -1;
        List<ValAccountsAdjustAccountEntry001Dto> result = valAccountsAdjustAccountEntry001Service.findAccountEntry(
            booksetId, nodeId, level).getRoot();
        File outFile = valAccountsAdjustAccountEntry001Service.makeFile(
            "/com/joyintech/tams/valuation/accounadjustmanage/accountentry/conf/export_accountentry.xml",
            result);
        return ExportUtil.export(
            this.getMessage(this.getPageId()) + SystemGlobalConstant.OPEN_PARENTHESIS
                                 + DateUtil.format(new Date())
                                 + SystemGlobalConstant.CLOSE_PARENTHESIS
                                 + SystemGlobalConstant.POINT
                                 + FileNameUtil.getFileExtName(outFile),
            outFile, true);
    }

    /**
     * 将业务实体dto转换成共通导出模板entity
     * 
     * @param dtos 业务实体dto list
     * @return 返回导出模板entity list
     * @see
     * @since 1.0
     */
    private List<ExportEntity> formatEntity(List<ValAccountsAdjustAccountEntry001Dto> dtos) {
        List<ExportEntity> entitys = new ArrayList<ExportEntity>();
        if (dtos != null && dtos.size() > 0) {
            for (ValAccountsAdjustAccountEntry001Dto dto : dtos) {
                ExportEntity ee = new ExportEntity();
                ee.setColumn1(dto.getSceneNodeId());
                ee.setColumn2(dto.getDictId());
                ee.setColumn3(dto.getTableName());
                ee.setColumn4(dto.getNodeValue());
                ee.setColumn5(dto.getDictName());
                if (dto.getLevel() != 0) {
                    ee.setColumn6(
                        dto.getIsEntry() ? DictCodeList.是否_是.getCodeName() : DictCodeList.是否_否.getCodeName());
                }
                entitys.add(ee);
            }
        }
        return entitys;
    }

    /**
     * 删除check
     * 
     * @param dto 行数据
     * @param result check结果
     * @return WebResult
     * @see
     * @since 1.0
     */
    @RequestMapping("deleteCheck")
    @ResponseBody
    public AjaxResult deleteCheck(@RequestBody @Valid ValAccountsAdjustAccountEntry001Dto dto,
                                 BindingResult result) {
        if (result != null && result.hasErrors()) {
            return new AjaxResult(result);
        }
        return new AjaxResult(getMessage("ACCOUNT_C_0001"), MessageType.确认消息);
    }

    /**
     * 删除分支节点
     * 
     * @param dto 行数据
     * @return WebResult
     * @see
     * @since 1.0
     */
    @RequestMapping("deleteEntry")
    @ResponseBody
    public AjaxResult deleteEntry(@RequestBody ValAccountsAdjustAccountEntry001Dto dto) {
        valAccountsAdjustAccountEntry001Service.deleteEntry(dto);
        return new AjaxResult(getMessage("VAL_COMMON_I_0007"));
    }
}
