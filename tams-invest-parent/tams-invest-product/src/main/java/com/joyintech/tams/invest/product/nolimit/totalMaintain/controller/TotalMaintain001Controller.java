package com.joyintech.tams.invest.product.nolimit.totalMaintain.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.dto.TotalMaintain001Dto;
import com.joyintech.tams.invest.product.nolimit.totalMaintain.service.TotalMaintain001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * @program: TotalMaintain001Controller
 * @description: 总量/总比例维护
 * @author: jyl
 * @create: 2019-12-18 17:06
 **/
@Controller
@PageId("TotalMaintain001")
@RequestMapping("/invest/product/nolimit/totalMaintain/TotalMaintain001")
public class TotalMaintain001Controller extends BaseController {
    @Autowired
    TotalMaintain001Service totalMaintain001Service;

    public String init(Model model) {
        return super.init(model);
    }

    /**
     * 搜索查找非标限额总量信息
     *
     * @return Page
     */
    @RequestMapping("/findPage")
    @ResponseBody
    public Page<TotalMaintain001Dto> findPage() {
        Map<String, Object> map = RequestUtil.getPageParam();
        return totalMaintain001Service.findPage(map);
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
        return totalMaintain001Service.export(this.getPageId(), RequestUtil.getPageParam());
    }
}
