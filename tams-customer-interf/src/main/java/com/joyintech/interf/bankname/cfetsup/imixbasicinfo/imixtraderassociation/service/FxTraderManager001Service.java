package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dao.FxTraderManagerMapper;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixtraderassociation.dto.FxTraderManagerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 银行间交易员信息查询
 *
 * @author zcf
 * @version 1.0
 * @date 2019-09-29
 */
@Service
public class FxTraderManager001Service extends CommonExportService<FxTraderManagerDto> {

    @Autowired
    private FxTraderManagerMapper fxTraderManagerMapper;

    /**
     * 分页查询
     * @param para
     * @return
     */
    @Override
    public Page<FxTraderManagerDto> selectPageData(Map<String, Object> para) {
        return this.queryForPage(Integer.valueOf(para.get("page").toString()),Integer.valueOf(para.get("length").toString()),
                ()-> fxTraderManagerMapper.selectPageData(para));
    }
    /**
     * 新增
     * @param  dto
     * @return
     */
    public int insert(FxTraderManagerDto dto){
        return fxTraderManagerMapper.insert(dto);
    }
    /**
     * 根据流水号查询实体类
     * @param  seqno
     * @return
     */
    public FxTraderManagerDto selectByPrimaryKey(String seqno){
        return fxTraderManagerMapper.selectByPrimaryKey(seqno);
    }
    /**
     * 根据流水号删除实体类
     * @param  seqno
     * @return
     */
    public int deleteByPrimaryKey(String seqno){
        return fxTraderManagerMapper.deleteByPrimaryKey(seqno);
    }
    /**
     * 修改实体类
     * @param  dto
     * @return
     */
    public int updateByPrimaryKey(FxTraderManagerDto dto){
        return fxTraderManagerMapper.updateByPrimaryKey(dto);
    }
    /**
     * 用户名按键精灵
     * @param  map
     * @return
     */
    public List<SuggestDto> getUser(Map map){
        return fxTraderManagerMapper.getUser(map);
    }/**
     * 根据用户名查询是否绑定过外汇交易员
     * @param  dto
     * @return
     */
    public int findCountByStaffid(FxTraderManagerDto dto){
        return fxTraderManagerMapper.findCountByStaffid(dto);
    }
    /**
     * 根据外汇交易员看是否绑定过用户
     * @param  dto
     * @return
     */
    public int findCountByCstptraderid(FxTraderManagerDto dto){
        return fxTraderManagerMapper.findCountByCstptraderid(dto);
    }

}
