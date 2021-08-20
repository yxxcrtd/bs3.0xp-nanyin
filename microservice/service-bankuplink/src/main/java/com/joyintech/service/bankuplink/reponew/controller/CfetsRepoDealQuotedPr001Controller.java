package com.joyintech.service.bankuplink.reponew.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.bankuplink.cstp.dto.CstpSelectDto;
import com.joyintech.service.bankuplink.ibu.dto.RepoQuoteInfoDto;
import com.joyintech.service.bankuplink.reponew.dto.CfetsRepoDealQuotedPr001Dto;
import com.joyintech.service.bankuplink.reponew.service.CfetsRepoDealQuotedPr001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author chenjiaxing
 * @version 1.0 2021年8月7日
 * @see CfetsRepoDealQuotedPr001Controller
 * @since 1.0
 */
@RestController("cfetsRepoDealQuotedPr001_dtx")
@RequestMapping("/CfetsRepoDealQuotedPr001")
public class CfetsRepoDealQuotedPr001Controller {

    /**
     * 注入Service
     */
    @Autowired
    private CfetsRepoDealQuotedPr001Service cfetsRepoDealQuotedPr001Service;

    /**
     * 委托指令查询
     *
     * @param param 入参
     * @return 委托指令
     * @see
     * @since 1.0
     */
    @PostMapping("queryZlzxList")
    public List<CfetsRepoDealQuotedPr001Dto> queryZlzxList(@RequestBody Map<String, Object> param) throws Exception {
        return cfetsRepoDealQuotedPr001Service.queryZlzxList(param);
    }

    /**
     * 质押券管理
     *
     * @param param 入参
     * @return 委托指令
     * @see
     * @since 1.0
     */
    @PostMapping("queryZyqglList")
    public List<CfetsRepoDealQuotedPr001Dto> queryZyqglList(@RequestBody Map<String, Object> param) throws Exception {
        return cfetsRepoDealQuotedPr001Service.queryZyqglList(param);
    }

/*    *
     * 委托查询
     *
     * @param param 入参
     * @return 委托指令
     * @see
     * @since 1.0*/

    @PostMapping("queryWtdhbjList")
    public List<RepoQuoteInfoDto> queryWtdhbjList(@RequestBody Map<String, Object> param) throws Exception {
        return cfetsRepoDealQuotedPr001Service.queryWtdhbjList(param);
    }

    /**
     * 成交查询
     * @param map 查询
     * @return 分页查询结果
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    @ResponseBody
    @PostMapping("getCstpQryTableList")
    public List<CstpSelectDto> getCstpQryTableList(@RequestBody Map<String,Object> map) throws Exception{
        return cfetsRepoDealQuotedPr001Service.getCstpQryTableList(map);
    }
}
