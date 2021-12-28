package com.joyintech.sso.config.service;

import com.joyintech.sso.common.Message;
import com.joyintech.sso.common.MessageCode;
import com.joyintech.sso.config.dao.SsoPlatformMapper;
import com.joyintech.sso.config.dto.SsoPlatformDto;
import com.joyintech.sso.config.pojo.SsoPlatform;
import com.joyintech.sso.util.StringUtils;
import com.joyintech.sso.util.UUIDUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SsoPlatformService {
    @Resource
    private SsoPlatformMapper ssoPlatformMapper;

    /**
     * 查询平台管理信息list
     *
     * @param ssoPlatformDto
     * @return
     */
    public List<SsoPlatform> listSsoPlatform(SsoPlatformDto ssoPlatformDto) {
        return ssoPlatformMapper.listSsoPlatform(ssoPlatformDto);
    }

    /**
     * 查询平台管理信息
     *
     * @param id
     * @return
     */
    public SsoPlatform selectSsoPlatfromById(String id) {
        return ssoPlatformMapper.selectSsoPlatfromById(id);
    }

    /**
     * 新增平台管理信息
     *
     * @param ssoPlatform
     * @return
     */
    public Message<String> insertSsoPlatform(SsoPlatform ssoPlatform) {
        Message<String> message = this.validateSsoPlatform(ssoPlatform);
        if (message != null) {
            return message;
        }

        if (ssoPlatformMapper.selectSsoPlatfromById(ssoPlatform.getPlatformcode()) != null) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_CODE_EXISTS);
        }
        ssoPlatform.setId(UUIDUtil.generate());
        ssoPlatform.setStatus(0);
        if (null == ssoPlatform.getSyncFlag()) {
            ssoPlatform.setSyncFlag(0);
        }
        ssoPlatformMapper.insertSsoPlatform(ssoPlatform);
        return new Message<String>().success();
    }

    /**
     * 删除平台管理信息
     *
     * @param id
     * @return
     */
    public int deleteSsoPlatformById(String id) {
        return ssoPlatformMapper.deleteSsoPlatformById(id);
    }

    /**
     * 更新平台管理信息
     *
     * @param ssoPlatform
     * @return
     */
    public Message<String> updateSsoPlatform(SsoPlatform ssoPlatform) {
        Message<String> message = this.validateSsoPlatform(ssoPlatform);
        if (message != null) {
            return message;
        }
        ssoPlatformMapper.updateSsoPlatform(ssoPlatform);
        return new Message<String>().success();
    }


    /**
     * 校验平台管理信息
     *
     * @param ssoPlatform
     * @return
     */
    public Message<String> validateSsoPlatform(SsoPlatform ssoPlatform) {
       /* if(StringUtils.isEmpty(ssoPlatform.getId())) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_ID_ERROR);
        }*/

        if (ssoPlatform.getPlatformcode() == null) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_CODE_NULL);
        }

        if (StringUtils.isEmpty(ssoPlatform.getPlatformname())) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_NAME_ERROR);
        }

        if (StringUtils.isEmpty(ssoPlatform.getPlatformHost())) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_HOST_ERROR);
        }
        if (StringUtils.isEmpty(ssoPlatform.getPlatformIndex())) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_INDEX_ERROR);
        }

        if (ssoPlatform.getWeight() == null) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_WEIGHT_ERROR);
        }

        if (ssoPlatform.getCookiekey() == null) {
            return new Message<String>(MessageCode.FAIL_COOKIEKEY_NULL);
        }
        /*if(ssoPlatform.getStatus() == null) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_STATUS_ERROR);
        }*/

        return null;
    }

    public Message<String> updatestatus(SsoPlatform ssoPlatform) {
        if (ssoPlatform.getId() == null) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_ID_ERROR);
        }

        if (null == ssoPlatform.getStatus()) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_STATUS_ERROR);
        }
        ssoPlatformMapper.updateSsoPlatformById(ssoPlatform.getId(), ssoPlatform.getStatus());
        return new Message<String>().success();
    }
}
