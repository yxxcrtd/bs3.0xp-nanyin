package com.joyintech.tams.component.business.entrust;

import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/component/entrust/EtrCommon")
public class EtrComponentCommonController extends BaseController {

    @Autowired
    private CommonBusinessService commonBusinessService;

    /**
     * 获取文件的唯一标识
     *
     * @return businessId
     */
    @ResponseBody
    @RequestMapping("/findInvAim")
    public String getBusinessId() {
        return commonBusinessService.findInvAim(RequestUtil.getPageParam());
    }

}
