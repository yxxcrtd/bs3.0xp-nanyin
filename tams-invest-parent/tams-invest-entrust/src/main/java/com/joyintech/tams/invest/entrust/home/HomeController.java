package com.joyintech.tams.invest.entrust.home;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.intf.Page;
import com.joyintech.framework.constant.DictCodeList;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.system.constant.SystemDictCodeList;
import com.joyintech.system.main.dto.LayoutStyle;
import com.joyintech.system.main.dto.SettingSaveDto;
import com.joyintech.system.main.service.MainService;
import com.joyintech.tams.invest.entrust.home.service.HomeService;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.dto.ProcessParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


@Controller
@PageId("Home")
@RequestMapping("/invest/entrust/home/Home")
public class HomeController extends BaseController{
    @Autowired
    private MainService mainService;

    @Autowired
    private HomeService homeService;
    
    @Override
    public String init(Model model) {
        return super.init(model);
    }


    /**
     * 获取可配置画面列表
     *
     * @return 列表
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    @ResponseBody
    @RequestMapping("getConfigList")
    public AjaxResult getHomeViewConfigList() {
        List<String> elementIdSet = (List)CacheUtil.getAppCache("ALL_AUTH_MODULES", List.class);
        Map<String, Page> pagesMap = CacheUtil.getPages();
        Map<String, Page> pagesMapTarget = new HashMap();
        Iterator pagesMapSet = pagesMap.keySet().iterator();

        while(pagesMapSet.hasNext()) {
            String pageId = (String)pagesMapSet.next();
            Page page = (Page)pagesMap.get(pageId);
            if (page != null && StringUtil.equals(page.getIsHpSelect(), DictCodeList.是否_是.getValue())) {
                pagesMapTarget.put(pageId, page);
            }
        }

        List<Page> pageList = (List)pagesMapTarget.values().stream().sorted(new Comparator<Page>() {
            public int compare(Page o1, Page o2) {
                return o1 == null ? -1 : o1.getName().compareTo(o2.getName());
            }
        }).collect(Collectors.toList());
        AjaxResult result = new AjaxResult();
        Map<String, Object> map = new HashMap();
        // 主页配置可选页面
        map.put("pageList", pageList);
        // 登录用户的主页配置一览
//        map.put("layoutList", mainService.selectHomePageConfigLayout());
        map.put("layoutStyle", "{\"settingSaveIds\":[\"2\",\"1\"],\"pageList\":[\"SysUser001\",\"PbcPrdAssetDebtSubPreview\",\"SvreportHome002\"]}");
        result.setData(map);
        return result;
    }

    /**
     * 可配置page联想框
     * @return
     */
    @RequestMapping("selectPage")
    @ResponseBody
    public com.joyintech.framework.grid.Page<SelectOptionDto> selectPage() {
        Map<String, Object> param = RequestUtil.getPageParam();
//        return bussCommonSelectService.selectCommonSelectForPage(selectTarget, param);
        return homeService.selectPage(param);
    }

    @ResponseBody
    @RequestMapping("getCheckedConfigList")
    public AjaxResult getCheckedConfigList() {
        String dynInfo = homeService.getCheckedConfigList(RequestUtil.getPageParam()); 
        AjaxResult result = new AjaxResult();
        result.setData(dynInfo);
        return result;
    }

    @ResponseBody
    @RequestMapping({"/save"})
    public AjaxResult save() {
        AjaxResult result = new AjaxResult();
        Map<String, Object> param = RequestUtil.getPageParam();
        Integer flag = homeService.save(param);
        
        if(flag > 0){
            result.setData("true");
            return result;
        }
        result.setData("false");
        return result;
    }
    
    
}
