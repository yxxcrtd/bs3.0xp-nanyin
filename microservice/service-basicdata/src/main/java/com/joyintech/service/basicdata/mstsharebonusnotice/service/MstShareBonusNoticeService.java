package com.joyintech.service.basicdata.mstsharebonusnotice.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstsharebonusnotice.dao.MstShareBonusNotice001Mapper;
import com.joyintech.service.basicdata.mstsharebonusnotice.dto.MstShareBounsNoticeDto;
import com.joyintech.service.basicdata.mstshareprice.dto.MstSharePriceDto;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Classname MstShareBonusNoticeService
 * @Description 股票分红公告
 * @author zhuhanlu 
 * @date 2020/8/6 16:56 
 * @version 1.0 
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MstShareBonusNoticeService extends BaseService<MstShareBounsNoticeDto> {
    @Autowired
    private MstShareBonusNotice001Mapper mstShareBonusNotice001Mapper;


    //资金账号下拉框
    public List<SelectOptionDto> getShareId(Map<String, Object> param) {
        param.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return mstShareBonusNotice001Mapper.getShareId(param);
    }

    /**
     *
     * @Description 分页查询
     * @Param [page, length, param]
     * @return com.joyintech.framework.grid.Page<com.joyintech.service.basicdata.mstsharebonusnotice.dto.MstShareBounsNoticeDto>
     * @version 1.0
     * @see
    **/
    public Page<MstShareBounsNoticeDto> selectForPage(int page, int length,Map<String, Object> param) throws Exception {
         return this.queryForPage(page,length,()->mstShareBonusNotice001Mapper.selectForPage(param));
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
    public void doComplete(MstShareBounsNoticeDto entity) {
        if(StringUtil.equals(entity.getOperateType(), "DELETE")) {
            mstShareBonusNotice001Mapper.deleteByPrimaryKey(entity);
        }else if(StringUtil.equals(entity.getOperateType(), "NEW")){
            mstShareBonusNotice001Mapper.insert(entity);
        }else{
            mstShareBonusNotice001Mapper.updateByPrimaryKeySelective(entity);
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
    public List<MstShareBounsNoticeDto> selectAllList(Map<String, Object> pageParam) {
        return mstShareBonusNotice001Mapper.selectAllList(pageParam);
    }
}
