package com.joyintech.tams.svreport.chb.boksubject.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import com.joyintech.tams.svreport.chb.boksubject.dao.BokSubjectDao;
import com.joyintech.tams.svreport.chb.boksubject.dto.BokSubjectDto;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author wubingbing
 * @date 2022-03-28 14:39
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BokSubjectService extends CommonExportService<BokSubjectDto> {
    @Autowired
    private BokSubjectDao bokSubjectMapper;
    /**
     * 检索上级科目代码列表
     *
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFSubjectId(String param) {
        return queryForSuggest(bokSubjectMapper::getFSubjectId, null, param);
    }

    /**
     * 获取科目文本输入框模糊查询
     *
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getSubjectId(String param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValSubjectSet001/getSubjectId", param, SuggestDto.class);
//        return queryForSuggest(valSubjectSet001Mapper::getSubjectId, null, param);
    }



    /**
     * 获取科目文本输入框模糊查询 和 联动
     *
     * @return 上级科目代码列表
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSubjectId2(Map<String, Object> map) {
        List<SelectOptionDto> list = bokSubjectMapper.getSubjectId2(map);
        if (CollectionUtils.isNotEmpty(list)) {
            list.forEach(item -> {
                item.setId(item.getId());
                item.setText(item.getId() + "（" + item.getText() + "）");
            });
        }
        return list;
    }

    /**
     * 获取科目文本输入框模糊查询 和 联动
     *
     * @return 上级科目代码列表
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getSubjectLevel(Map<String, Object> map)  {
        return bokSubjectMapper.getSubjectLevel(map);
    }

    /**
     * 获取科目下拉框
     * @return
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryValBokSubject(Map<String, Object> map) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValSubjectSet001/queryValBokSubject", map, SelectOptionDto.class);
    }

    /**
     * 检索科目列表
     *
     * @param pageParam 过滤参数
     * @return 科目列表
     * @throws Exception
     * @see
     * @since 1.0
     */
    public Page<BokSubjectDto> selectForPage(Map<String, Object> pageParam) {
        return queryForPage(Integer.parseInt((String)pageParam.get("page")), Integer.parseInt((String)pageParam.get("length")), () -> bokSubjectMapper.selectAll(pageParam));
    }
    /**
     * 根据主键检索科目
     *
     * @param booksetId 账套代码
     * @param subjectNo 科目代码
     * @return 科目
     * @see
     * @since 1.0
     */
    public BokSubjectDto selectValBokSubject(String booksetId, String subjectNo) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("booksetId", booksetId);
        params.put("subjectNo", subjectNo);
        return restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValSubjectSet002/selectValBokSubject", params, BokSubjectDto.class);
    }

    /**
     * 检索上级科目代码列表(支持入参)
     *
     * @param param 过滤参数
     * @return 上级科目代码列表
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getFSubjectId2(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValSubjectSet001/getFSubjectId2", param, SuggestDto.class);
    }


    /**
     * 批量删除（删除当前科目及其子科目）
     * @param selectedItem
     * @see
     * @since 1.0
     */
    public void deleteByBatch(List<BokSubjectDto> selectedItem) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValSubjectSet001/deleteByBatch", selectedItem, boolean.class);
    }

    public List<SuggestDto> getBooksetId(Map<String, Object> param) {
        return restTemplate.postForList(ServiceApplication.VALUATION_SERVICE_NAME+"/ValSubjectSet001/getBooksetId", param, SuggestDto.class);
    }

    public void save(BokSubjectDto dto) {
        restTemplate.postForObject(ServiceApplication.VALUATION_SERVICE_NAME+"/ValSubjectSet002/save", dto, boolean.class);
    }

    public List<SelectOptionDto> getSubjectName(Map<String, Object> pageParam) {
        List<SelectOptionDto> list = bokSubjectMapper.getSubjectName(pageParam);
//        if (CollectionUtils.isNotEmpty(list)) {
//            list.forEach(item -> {
//                item.setId(item.getId());
//                item.setText(item.getId() + "（" + item.getText() + "）");
//            });
//        }
        return list;
    }
    protected List<SuggestDto> queryForSuggest(Supplier<List<SuggestDto>> selector, String code, String name) {
        return this.queryForSuggest(selector, (item) -> {
            if (StringUtil.isNotEmpty(code) && item.getCode().toLowerCase().indexOf(code.trim().toLowerCase()) < 0) {
                return false;
            } else {
                return !StringUtil.isNotEmpty(name) || item.getCode().toLowerCase().indexOf(name) >= 0 || item.getCode().toLowerCase().indexOf(name.trim().toLowerCase()) >= 0;
            }
        });
    }

}
