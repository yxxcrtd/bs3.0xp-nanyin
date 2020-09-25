package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.service.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dao.FxImixAccountInfoMapper;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dto.FxImixAccountInfo001Dto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.service.FxImixAccountInfo001Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value="mstCstpAccountInfo001Service")
public class FxImixAccountInfo001ServiceImpl extends BaseService
implements FxImixAccountInfo001Service {

    @Autowired
    private FxImixAccountInfoMapper fxImixAccountInfoMapper;

    @Override
    public Page<FxImixAccountInfo001Dto> findPage(Map<String, Object> pageParam) {

        return this.queryForPage(Integer.valueOf(pageParam.get("page").toString()), Integer.valueOf(pageParam.get("length").toString()),
                () -> fxImixAccountInfoMapper.findPage(pageParam));
    }

    /**
     * 查询组合代码
     * @param para
     * @return
     */
    public Page<SelectOptionDto> getProductid(Map para){
        return new Page(fxImixAccountInfoMapper.getProductid(para));
    }

    @Override
    public Page<SelectOptionDto> getCfetsid(Map para) {
        return  new Page(fxImixAccountInfoMapper.getCfetsid(para));
    }

    @Override
    public Page<SelectOptionDto> getEffectfalg(Map para) {
        return  new Page(fxImixAccountInfoMapper.getEffectfalg(para));
    }

    @Override
    public void save(FxImixAccountInfo001Dto dto) {
        dto.setEffectflag("E");
        fxImixAccountInfoMapper.save(dto);
    }

    @Override
    public String findJgdm(String cstpid){
        return fxImixAccountInfoMapper.findJgdm(cstpid);
    }

    @Override
    public FxImixAccountInfo001Dto selectDtoInfoBySeqno(String seqno) {
        return fxImixAccountInfoMapper.selectDtoInfoBySeqno(seqno);
    }

    @Override
    public List<SelectOptionDto> getJycs(Map para) {
        return fxImixAccountInfoMapper.getJycs(para);
    }

    @Override
    public void deleteBySeqno(String seqno) {
        fxImixAccountInfoMapper.deleteBySeqno(seqno);
    }

    @Override
    public void update(FxImixAccountInfo001Dto dto) {
        fxImixAccountInfoMapper.update(dto);
    }

    @Override
    public int checkAccountid(String accountid) {
        return fxImixAccountInfoMapper.checkAccountid(accountid);
    }
}
