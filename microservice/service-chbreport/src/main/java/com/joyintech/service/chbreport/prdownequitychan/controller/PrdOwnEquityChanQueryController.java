package com.joyintech.service.chbreport.prdownequitychan.controller;

import com.joyintech.framework.grid.Page;
import com.joyintech.service.chbreport.prdownequitychan.service.PrdOwnEquityChanQuery001Service;
import com.joyintech.tams.common.base.entity.PrdOwnEquityChan;
import com.joyintech.tams.svreport.chbreport.common.dto.ChbreportPrdInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 产品所有者权益变动表查询服务
 *
 * @author WanShiwei
 * @version 2.1.6 2021年04月20日
 * @see
 * @since 2.1.6
 */
@RestController("PrdOwnEquityChanQueryCtr_dtx")
@RequestMapping("/PrdOwnEquityChanQuery")
public class PrdOwnEquityChanQueryController {

    /**
     * service
     */
    @Autowired
    private PrdOwnEquityChanQuery001Service PrdOwnEquityChanQuery001Service;

    /**
     * 业务画面查询事件
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<ChbreportPrdInfoDto> selectForPage(@RequestBody Map<String, Object> param) throws Exception {
        return PrdOwnEquityChanQuery001Service.selectForPage(param);
    }

    /**
     * 查询产品所有者权益变动表导出数据
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectPrdOwnEquityChanList")
    public List<PrdOwnEquityChan> selectPrdOwnEquityChanList(@RequestBody Map<String, Object> param) throws Exception {
        return PrdOwnEquityChanQuery001Service.selectPrdOwnEquityChanList(param);
    }
}
