package com.joyintech.tams.invest.entrust.etribrepo.controller;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author wl
 * @date 2021年02月2日
 * @see EtribRepoInfo003RiskcalController
 * @since 1.0
 */
@Controller
@PageId("EtribRepoInfo003Riskcal")
@RequestMapping("/invest/entrust/etribrepo/EtribRepoInfo003Riskcal")
public class EtribRepoInfo003RiskcalController extends BaseController {
    @Override
    public String init(Model model) {
        return super.init(model);
    }

}
