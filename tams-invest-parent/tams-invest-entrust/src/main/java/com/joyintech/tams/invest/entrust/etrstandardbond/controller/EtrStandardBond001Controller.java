/*
 * 文件名：TrdStandardBond001Controller.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：陈志力
 * 修改时间：2019年1月22日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.entrust.etrstandardbond.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.JoyinMessage;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.entrust.etrstandardbond.dto.EtrStandardBond001Dto;
import com.joyintech.tams.invest.entrust.etrstandardbond.service.EtrStandardBond001Service;

/**
 * 质押券管理
 * 〈功能详细描述〉
 *
 * @author lizhi
 * @version 1.0 2019-12-26
 * @see EtrStandardBond001Controller
 * @since 1.0
 */
@PageId("EtrStandardBond001")
@RequestMapping("/invest/entrust/etrstandardbond/EtrStandardBond001")
@Controller
public class EtrStandardBond001Controller extends BaseController {

    @Autowired(required = false)
    private EtrStandardBond001Service etrStandardBond001Service;

    @Override
    public String init(Model model) {
        this.setSelectOptionToModel(model, DictCode.交易场所);
        return super.init(model);
    }

    /**
     * 质押券管理列表查询
     *
     * @return Page<TrdStandardBond001Dto>泛型的page实体类
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @RequestMapping("selectPageData")
    @ResponseBody
    public Page<EtrStandardBond001Dto> selectPageData() throws Exception {
        return etrStandardBond001Service.selectPageData(RequestUtil.getPageParam());
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
        return etrStandardBond001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }

    /**
     * 出库操作
     *
     * @return excel
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @RequestMapping("outSave")
    public AjaxResult outSave(@RequestBody EtrStandardBond001Dto dto) {
     // 校验操作的角色是否是投资经理
        Map<String,Object> map = new HashMap<>();
        map.put("role_pos", DictCodeListExt2.角色岗位_投资经理.getValue());
        Long count = etrStandardBond001Service.selectUserRolePosList(map) ;
        if(count > 0){
            Long outFlat = etrStandardBond001Service.outSave(dto);
            // 标记为大于0，出库成功
            if(outFlat > 0){
                return new AjaxResult(this.getMessage("EtrStandardBond001.outSave_002"));
            }else{
                return new AjaxResult(this.getMessage("EtrStandardBond001.outSave_003"));
            }
        }else{
            AjaxResult ar = new AjaxResult() ;
            List<JoyinMessage> joyinMessages = new ArrayList<>();
            joyinMessages.add(
                    new JoyinMessage(this.getMessage("EtrStandardBond003.role",
                      DictCodeListExt2.角色岗位_投资经理.getCodeName()), MessageType.警告消息));
            ar.setJoyinMessages(joyinMessages);

            return ar ;
        }
    }
    /**
     * 根据组合代码查询证券托管户,并合并通道代码
     *
     * @param param 画面参数
     * @return 证券托管户/通道：联想框文本框
     * @see
     * @since 1.0
     */
    @RequestMapping("getSecManageAcctId")
    @ResponseBody
    public List<SelectOptionDto> getSecManageAcctId(String search) {
        Map<String,Object> map = new HashMap<>();
        map.put("search", search);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        return etrStandardBond001Service.getSecManageAcctId(map);
    }
}
