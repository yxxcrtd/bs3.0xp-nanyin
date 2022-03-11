package com.joyintech.service.chb.basicdata.perprodeclareinfo.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.chb.basicdata.perprodeclareinfo.dao.SvrPerProDeclareInfoMapper;
import com.joyintech.tams.basicdata.perprodeclareinfo.dto.SvrPerProDeclareInfo001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 产品申报信息登记维护 Service
 * @author yxj
 * @date 2022/03/07
 */
@Service("SvrPerProDeclareInfoMService")
public class SvrPerProDeclareInfoMService extends CommonExportService<SvrPerProDeclareInfo001Dto> {

    //
    @Autowired
    private SvrPerProDeclareInfoMapper svrPerProDeclareInfoMapper;

    /**
     * 分页查询
     * @param page
     * @param length
     * @param pageParam
     * @return
     */
    public Page<SvrPerProDeclareInfo001Dto> selectForPage(int page, int length, Map<String, Object> pageParam) {
        return super.queryForPage(page,length,() -> svrPerProDeclareInfoMapper.selectForPage(pageParam));
    }

    /**
     * 保存方法
     * @param dto
     */
    public void save(SvrPerProDeclareInfo001Dto dto) {
        Date sysDate = DateUtil.platDateFromCache();// 获取平台日期
        dto.setCreateTime(sysDate);
        dto.setUpdateTime(sysDate);
        dto.setCreateUser(CommonUtil.getUserName());
        dto.setUpdateUser(CommonUtil.getUserName());

        dto.setDeclareUuid(UUID.randomUUID().toString().replace("-", "").toUpperCase() + (new Random().nextInt(8998)+1000) + "");
        dto.setStatus("E");

        int num = svrPerProDeclareInfoMapper.insertSvrPerProDeclareInfo(dto);
    }

    /**
     * 修改
     * @param dto
     */
    public void update(SvrPerProDeclareInfo001Dto dto) {
        dto.setStatus("E");
        int num = svrPerProDeclareInfoMapper.updateSvrPerProDeclareInfo(dto);
    }

    /**
     * 获取详情
     * @param param
     * @return
     */
    public SvrPerProDeclareInfo001Dto selectDetail(Map<String, Object> param) {
        return svrPerProDeclareInfoMapper.selectDetail(param);
    }

    /**
     * 删除
     * @param dto
     */
    public void deleteByPrimaryKey(SvrPerProDeclareInfo001Dto dto){
        svrPerProDeclareInfoMapper.deleteByPrimaryKey(dto.getDeclareUuid());
    }

    /**
     *
     * @param param
     * @return
     */
    public List<SuggestDto> queryProdInfo(Map<String, Object> param) {
        return svrPerProDeclareInfoMapper.queryProdInfo(param);
    }

    /**
     * 获取所有信息
     * @param param
     * @return
     */
    public List<SvrPerProDeclareInfo001Dto> queryAllList(Map<String, Object> param) {
        return svrPerProDeclareInfoMapper.selectForPage(param);
    }

    public void deleteList(Map<String, Object> param) {
        String keys = param.get("datas").toString();
        List<String> split = StringUtil.splitToList(GlobalConstant.COMMA, keys);
        param.put("deleteKeys",split);
        svrPerProDeclareInfoMapper.deleteList(param);
    }
}
