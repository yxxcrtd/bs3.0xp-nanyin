/*
 * 文件名：AccountsAdjustAccountEntry001Service.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All
 * Rights Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.assitsaccount.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dao.ValAssistAccounting001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.assitsaccount.dto.ValAssistAccounting001Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author 殷亚
 * @description 辅助核算配置
 * @date 2022-1-12 15:40
 */
@Service(value = "serviceValAssistAccounting001Service")
@Transactional(rollbackFor = Exception.class)
public class ValAssistAccounting001Service extends CommonExportService<ValAssistAccounting001Dto> {

    /**
     * AccountsAdjustAccountEntry001Mapper
     */
    @Autowired
    private ValAssistAccounting001Mapper valAssistAccounting001Mapper;


    /**
     * 查询所有账套 <br/>
     * 用于所属账套控件初期化
     *
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findBooksets() {
        return valAssistAccounting001Mapper.findBooksets();
    }


    /**
     * @return com.joyintech.framework.grid.Page<ValAssistAccounting001Dto>
     * @author 殷亚
     * @description 检索数据
     * @date 2022-1-12 15:56
     */
    public Page<ValAssistAccounting001Dto> findPage(int page, int length,
                                                         Map<String, Object> pageParam) {
        //账套代码
        String booksetId = (String) pageParam.get("booksetId");
        pageParam.put("booksetId", booksetId);
        //内部科目编码
        String subjectInternalCode = (String) pageParam.get("subjectInternalCode");
        String[] codeArr = null;
        if (StringUtil.isNotEmpty(subjectInternalCode)) {
            codeArr = subjectInternalCode.split(",");
        }
        pageParam.put("subjectInternalCodeArr", codeArr);
        Page<ValAssistAccounting001Dto> list= this.queryForPage(page, length,
                () -> valAssistAccounting001Mapper.findPage(pageParam));
        list.getRoot().stream().forEach(p-> p.getHands().addAll(valAssistAccounting001Mapper.findHands(p.getBooksetId(),p.getSubjectInternalCode())));

        return list;
    }


}
