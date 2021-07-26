package com.joyintech.service.riskrds.limitmanagement.rskredisparamconfig.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.riskrds.limitmanagement.rskredisparamconfig.dao.RskRedisParamConfigMapper;
import com.joyintech.service.riskrds.limitmanagement.rskredisparamconfig.dto.RskRedisParamConfigDto;
import com.joyintech.tams.common.base.dao.RskBusinessRedisConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service("RskRedisParamConfigService")
public class RskRedisParamConfigService extends BaseService {

    @Autowired
    private RskBusinessRedisConfigMapper rskBusinessRedisConfigMapper;

    @Autowired
    private RskRedisParamConfigMapper rskRedisParamConfigMapper;

    /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类 Page
     */
    public Page<RskRedisParamConfigDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page, length,
            () -> rskRedisParamConfigMapper.selectForPage(pageParam));
    }

    /**
     * 插入风险事件信息
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void insert(RskRedisParamConfigDto entity) {
    	List<String> paramList = StringUtil.splitToList(GlobalConstant.COMMA, entity.getParaCode());
    	if(!StringUtil.isEmptyList(paramList)) {
            paramList.stream().forEach(item->{
                entity.setParaCode(item);
                rskBusinessRedisConfigMapper.deleteByPrimaryKey(entity);
                rskBusinessRedisConfigMapper.insert(entity);
            });
    	}
    }

    /**
     * 更新
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void update(RskRedisParamConfigDto entity) {
        this.insert(entity);
        //rskBusinessRedisConfigMapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 移除
     * @param entity
     */
    @Transactional(rollbackFor = Throwable.class)
    public void delete(RskRedisParamConfigDto entity) {
        rskBusinessRedisConfigMapper.deleteByPrimaryKey(entity);
    }

    /**
     * 查询基本信息
     * @param commonBusinessType
     * @param paraCode
     * @return
     */
    public RskRedisParamConfigDto getBasicInfo(String commonBusinessType, String paraCode){
        return rskRedisParamConfigMapper.getBasicInfo(commonBusinessType,paraCode);
    }

    public int getRepeatCount(Map<String,Object> param){
        return rskRedisParamConfigMapper.getRepeatCount(param);
    }
}
