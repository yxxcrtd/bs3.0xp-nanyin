package com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.interf.bankname.cfetsup.imixbasicinfo.imixaccountcfets.dto.FxImixAccountInfo001Dto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public interface FxImixAccountInfoMapper {


   public List<FxImixAccountInfo001Dto> findPage(Map<String, Object> pageParam);

   public List<SelectOptionDto> getProductid(Map para);

   public List<SelectOptionDto> getCfetsid(Map para);

   public List<SelectOptionDto> getEffectfalg(Map para);

   public List<SelectOptionDto> getJycs(Map para);


   public void save(FxImixAccountInfo001Dto dto);

   public String  findJgdm(String cstpid);

   public FxImixAccountInfo001Dto selectDtoInfoBySeqno(String seqno);

   public void deleteBySeqno(String seqno);

   public void update(FxImixAccountInfo001Dto dto);

   public int checkAccountid(String accountid);
}
