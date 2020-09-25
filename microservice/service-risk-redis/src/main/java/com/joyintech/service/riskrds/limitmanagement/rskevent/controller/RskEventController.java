package com.joyintech.service.riskrds.limitmanagement.rskevent.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent001Dto;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent002Dto;
import com.joyintech.service.riskrds.limitmanagement.rskevent.dto.RskEvent003Dto;
import com.joyintech.service.riskrds.limitmanagement.rskevent.service.RskEventService;
import com.joyintech.tams.common.base.entity.RskEvent;

/**
 * 风险事件管理
 * 
 * @author : wangzhen
 */
@RestController("RskEventController_dtx")
@RequestMapping("/RskEvent")
public class RskEventController {

    /*
     * 与redis中重名了
     */
    @Resource(name = "RskPageEventService")
    private RskEventService rskEventService;

    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("findPage/{page}/{length}")
    public Page<RskEvent001Dto> selectAll(@RequestBody Map<String, Object> param,
                                          @PathVariable("page") int page,
                                          @PathVariable("length") int length)
        throws Exception {
        return rskEventService.selectForPage(page, length, param);
    }

    /**
     * 画面查询方法
     *
     * @param param 画面参数
     * @param page 页码数
     * @param length 页面长度
     * @return 查询结果集
     * @throws Exception 异常
     */
    @PostMapping("selectMultiOptionEvent/{page}/{length}")
    public Page<SelectOptionDto> selectMultiOptionEvent(@RequestBody Map<String, Object> param,
                                                        @PathVariable("page") int page,
                                                        @PathVariable("length") int length)
        throws Exception {
        return rskEventService.selectMultiOptionEvent(page, length, param);
    }

    @PostMapping("selectRskEventList")
    public List<RskEvent> selectRskEventList(@RequestBody Map<String, Object> param)
        throws Exception {
        String eventName = "";
        if (null != param.get("eventName")) {
            eventName = String.valueOf(param.get("eventName"));
        }
        return rskEventService.selectByEventName(eventName);
    }

    /**
     * 根据风险事件代码查询风险事件
     * 
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("selectByEventCode")
    public RskEvent selectByPrimaryKey(@RequestBody Map<String, Object> param)
        throws Exception {
        String eventCode = "";
        if (null != param.get("eventCode")) {
            eventCode = String.valueOf(param.get("eventCode"));
        }
        return rskEventService.selectByEventCode(eventCode);
    }

    @PostMapping("selectByEventNameNotEqCode")
    public List<RskEvent> selectRskEventListNotSelf(@RequestBody Map<String, Object> param)
        throws Exception {
        String eventName = "";
        String eventCode = "";
        if (null != param.get("eventName")) {
            eventName = String.valueOf(param.get("eventName"));
        }
        if (null != param.get("eventCode")) {
            eventCode = String.valueOf(param.get("eventCode"));
        }
        return rskEventService.selectByEventNameNotEqCode(eventName, eventCode);
    }

    /**
     * 根据风险事件代码查询风险事件信息
     * 
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("selectEventDetailInfoByCode")
    public RskEvent002Dto selectEventDetailInfoByCode(@RequestBody Map<String, Object> param)
        throws Exception {
        String eventCode = "";
        if (null != param.get("eventCode")) {
            eventCode = String.valueOf(param.get("eventCode"));
        }
        return rskEventService.selectEventDetailInfoByCode(eventCode);
    }

    /**
     * 插入
     * 
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insert")
    public void insert(@RequestBody RskEvent002Dto dto)
        throws Exception {
        rskEventService.insert(dto);
    }

    /**
     * 更新
     * 
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("update")
    public void update(@RequestBody RskEvent002Dto dto)
        throws Exception {
        rskEventService.update(dto);
    }

    /**
     * 移除
     * 
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("delete")
    public void delete(@RequestBody RskEvent002Dto dto)
        throws Exception {
        rskEventService.delete(dto);
    }

    /**
     * 根据风险事件代码查询风险事件关联指标参数信息
     *
     * @param param
     * @return
     * @throws Exception
     */
    @PostMapping("selectEventRelParaByCode")
    public RskEvent003Dto selectEventRelParaByCode(@RequestBody Map<String, Object> param)
        throws Exception {
        String eventCode = "";
        if (null != param.get("eventCode")) {
            eventCode = String.valueOf(param.get("eventCode"));
        }
        return rskEventService.selectEventRelParaByCode(eventCode);
    }

    /**
     * 插入
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("insertRelPara")
    public void insertRelPara(@RequestBody RskEvent003Dto dto)
        throws Exception {
        rskEventService.insertRelPara(dto);
    }

    /**
     * 更新
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("updateRelPara")
    public void updateRelPara(@RequestBody RskEvent003Dto dto)
        throws Exception {
        rskEventService.updateRelPara(dto);
    }

    /**
     * 移除
     *
     * @param dto
     * @throws Exception
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("deleteRelPara")
    public void deleteRelPara(@RequestBody RskEvent003Dto dto)
        throws Exception {
        rskEventService.deleteRelPara(dto);
    }
    @GetMapping("isExistEventCode")
    public boolean isExistEventCode(String eventCode){
    	return rskEventService.isExistEventCode(eventCode);
    }
}
