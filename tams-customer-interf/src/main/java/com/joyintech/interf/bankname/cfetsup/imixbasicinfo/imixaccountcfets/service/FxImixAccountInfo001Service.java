package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.service;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dto.FxImixAccountInfo001Dto;

import java.util.List;
import java.util.Map;

public interface FxImixAccountInfo001Service {
    /**
     * 分页查询方法
     * @param map
     * @return
     */
    public Page<FxImixAccountInfo001Dto> findPage(Map<String, Object> pageParam);

    /**
     * 组合代码查询
     * @param para
     * @return
     */
    public Page<SelectOptionDto> getProductid(Map para);

    public Page<SelectOptionDto> getCfetsid(Map para);

    public Page<SelectOptionDto> getEffectfalg(Map para);

    public void save(FxImixAccountInfo001Dto dto);

    public String findJgdm(String cstpid);

    public FxImixAccountInfo001Dto selectDtoInfoBySeqno(String seqno);

    public List<SelectOptionDto> getJycs(Map para);

    public void deleteBySeqno(String seqno);

    public void update(FxImixAccountInfo001Dto dto);

    public int checkAccountid(String accountid);

}
