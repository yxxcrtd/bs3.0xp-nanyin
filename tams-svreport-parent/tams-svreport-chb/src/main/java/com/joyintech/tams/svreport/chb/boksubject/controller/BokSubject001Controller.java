package com.joyintech.tams.svreport.chb.boksubject.controller;

import com.google.common.collect.Lists;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.svreport.chb.boksubject.dto.BokSubjectDto;
import com.joyintech.tams.svreport.chb.boksubject.service.BokSubjectService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 科目定义controller
 * @author wubingbing
 * @date 2022-03-28 14:36
 */
@Controller
@PageId("BokSubject001")
@RequestMapping("/chb/boksubject/boksubject001")
public class BokSubject001Controller extends BaseController {
    @Autowired
    private BokSubjectService bokSubjectService;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.科目类型);
        this.setSelectOptionToModel(model, DictCode.科目级别);
        this.setSelectOptionToModel(model, DictCode.科目状态);
        return super.init(model);
    }

    /**
     * 检索上级科目代码
     *
     * @return 账套上级科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getFSubjectId")
    public List<SuggestDto> getFSubjectId(String param)
    {
        return bokSubjectService.getFSubjectId(param);
    }

    /**
     * 检索科目代码
     *
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSubjectId")
    public List<SuggestDto> getSubjectId()
    {
        String param = super.getRequest().getParameter("param");
        return bokSubjectService.getSubjectId(param);
    }


    /**
     * 检索科目代码 支持联动
     *
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSubjectId2")
    public List<SelectOptionDto> getSubjectId2()
    {
        return bokSubjectService.getSubjectId2(RequestUtil.getPageParam());
    }

    /**
     * 科目名称 支持联动
     *
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSubjectName")
    public List<SelectOptionDto> getSubjectName()
    {
        return bokSubjectService.getSubjectName(RequestUtil.getPageParam());
    }


    /**
     * 检索科目级别 支持联动
     *
     * @return 账套科目代码联想框内容
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("/getSubjectLevel")
    public List<SelectOptionDto> getSubjectLevel()
    {
        List<SelectOptionDto> list = Lists.newArrayList();
        String subjectNo = getParam("subjectNo");
        if (StringUtils.isEmpty(subjectNo)) {
            list = getSelectDataFromDataDic(DictCode.科目级别, getParam("search")).getOptions();
        }
        else {
            list = bokSubjectService.getSubjectLevel(RequestUtil.getPageParam());
            if(CollectionUtils.isNotEmpty(list)) {
                list.forEach(item -> {
                    item.setText(SysDataUtil.getNameFromDataDict(DictCode.科目级别, item.getId()));
                });
            }
        }
        return list;
    }

    /**
     * 所属科目下拉框获取
     *
     * @return 返回的数据
     * @exception Exception 异常
     * @since 1.0
     */
    @RequestMapping("/queryValBokSubject")
    @ResponseBody
    public List<SelectOptionDto> queryValBokSubject(HttpServletRequest request) {
        Map<String, Object> param = RequestUtil.getPageParam();
        return bokSubjectService.queryValBokSubject(param);
    }



    /**
     * 检索页面列表
     *
     * @return
     * @throws Exception
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("findPage")
    public Page<BokSubjectDto> selectAllSubject()
    {
        Page<BokSubjectDto> page = bokSubjectService.selectForPage(RequestUtil.getPageParam());
        return page;
    }

    /**
     * 删除按钮点击时的check
     *
     * @param dto dto
     * @param result BindingResult
     * @return check结果
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("check")
    public AjaxResult check(@RequestBody @Validated({ValidateGroupDelete.class}) BokSubjectDto dto,
                            BindingResult result) {
        return commonCheck(result, "delete");
    }


}
