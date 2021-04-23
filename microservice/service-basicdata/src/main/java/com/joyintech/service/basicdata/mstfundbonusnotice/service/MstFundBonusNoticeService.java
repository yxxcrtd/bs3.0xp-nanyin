package com.joyintech.service.basicdata.mstfundbonusnotice.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstfundbonusnotice.dao.MstFundBonusNotice001Mapper;
import com.joyintech.service.basicdata.mstfundbonusnotice.dto.MstFundBonusNoticeDto;
import com.joyintech.service.basicdata.mstsharebonusnotice.dto.MstShareBounsNoticeDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Classname MstFundBonusNoticeService
 * @Description 基金分红公告
 * @author zhuhanlu 
 * @date 2020/8/7 13:38 
 * @version 1.0 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MstFundBonusNoticeService extends BaseService<MstFundBonusNoticeDto> {

    @Autowired private MstFundBonusNotice001Mapper mstFundBonusNotice001Mapper;

    public List<SelectOptionDto> getFundId(Map<String,Object> map) {
        return mstFundBonusNotice001Mapper.getFundId(map);
    }

    public Page<MstFundBonusNoticeDto> selectForPage(int page, int length, Map<String,Object> map){
        Page<MstFundBonusNoticeDto> pageTemp = this.queryForPage(page,length,()->mstFundBonusNotice001Mapper.selectForPage(map));
        return this.queryForPage(page,length,()->mstFundBonusNotice001Mapper.selectForPage(map));
    }

    /**
     *
     * 工作流完结之后的操作
     * <功能详细描述>
     * @param entity 实体
     * @param type 类型
     * @see
     * @since 1.0
     */
    public void doComplete(MstFundBonusNoticeDto entity) {
        if(StringUtil.equals(entity.getOperateType(), "DELETE")) {
            mstFundBonusNotice001Mapper.deleteByPrimaryKey(entity);
        }else if(StringUtil.equals(entity.getOperateType(), "NEW")){
            mstFundBonusNotice001Mapper.insert(entity);
        }else{
            mstFundBonusNotice001Mapper.updateByPrimaryKeySelective(entity);
        }

    }

    /**
     *
     * @Description 根据shareId和regDate查询数据
     * @Param [pageParam]
     * @return java.util.List<com.joyintech.service.basicdata.mstsharebonusnotice.dto.MstShareBounsNoticeDto>
     * @version 1.0
     * @see
     **/
    public List<MstFundBonusNoticeDto> selectAllList(Map<String, Object> pageParam) {
        return mstFundBonusNotice001Mapper.selectAllList(pageParam);
    }
}
