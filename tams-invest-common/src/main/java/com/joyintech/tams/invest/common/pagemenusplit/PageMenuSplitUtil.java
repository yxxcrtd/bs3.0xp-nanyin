package com.joyintech.tams.invest.common.pagemenusplit;

import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.framework.jdbc.datasource.DynamicDataSourceHolder;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.pagemenusplit.dao.PageMenuSplitMapper;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PageMenuSplitUtil {

    /**
     * 根据画面id获取交易场所列表
     * @param pageId 画面id
     * @return 交易场所列表
     */
    public static List<String> getTradeMarketList(String pageId) {
        if (StringUtil.isBlank(pageId)) {
            return new ArrayList<>();
        }

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageId", pageId);
        paramMap.put("dictId", DictCode.交易场所.getValue());

        PageMenuSplitMapper pageMenuSplitMapper = SpringContextUtil.getBean(PageMenuSplitMapper.class);

        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<String> resultList  = pageMenuSplitMapper.selectDictItemList(paramMap);
        DynamicDataSourceHolder.clearDataSource();

        return resultList;
    }

    /**
     * 根据画面id获取交易平台列表
     * @param pageId 画面id
     * @return 交易平台列表
     */
    public static List<String> getTradePlatFormList(String pageId) {
        if (StringUtil.isBlank(pageId)) {
            return new ArrayList<>();
        }

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageId", pageId);
        paramMap.put("dictId", DictCode.交易平台.getValue());

        PageMenuSplitMapper pageMenuSplitMapper = SpringContextUtil.getBean(PageMenuSplitMapper.class);

        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<String> resultList  = pageMenuSplitMapper.selectDictItemList(paramMap);
        DynamicDataSourceHolder.clearDataSource();

        return resultList;
    }

    /**
     * 根据原画面中已经设置好的下拉框的选项,结合拆分画面配置的数据,获取拆分画面的下拉框选项
     * @param model
     * @param dictCode
     * @param pageId
     */
    public static void handlePageSelectDictList(Model model, IDictCode dictCode, String pageId) {
        if (StringUtil.isBlank(pageId)) {
            return;
        }

        Map<String, Object> paramMap = new HashMap();
        paramMap.put("pageId", pageId);
        paramMap.put("dictId", dictCode.getValue());

        PageMenuSplitMapper pageMenuSplitMapper = SpringContextUtil.getBean(PageMenuSplitMapper.class);
        DynamicDataSourceHolder.putDataSource(DataSourceType.OTHER);
        List<String> resultList  = pageMenuSplitMapper.selectDictItemList(paramMap);
        DynamicDataSourceHolder.clearDataSource();

        if (StringUtil.isEmptyList(resultList)) {
            return;
        }

        try {
            List<SelectOptionDto> options = JsonUtils.json2List((String) model.asMap().get(dictCode.getValue()), SelectOptionDto.class);
            model.addAttribute(dictCode.getValue(), JsonUtils.objectArray2Json(options.stream().filter(x -> resultList.contains(x.getId())).toArray()));
        }
        catch(Exception e) {
        }
    }
}
