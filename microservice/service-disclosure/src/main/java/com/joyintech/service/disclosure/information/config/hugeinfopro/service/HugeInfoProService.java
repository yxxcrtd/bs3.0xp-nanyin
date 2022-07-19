//package com.joyintech.service.disclosure.information.config.hugeinfopro.service;
//
//import com.joyintech.framework.base.BaseService;
//import com.joyintech.framework.constant.GlobalConstant;
//import com.joyintech.framework.grid.Page;
//import com.joyintech.framework.select.SelectOptionDto;
//import com.joyintech.framework.util.BeanUtil;
//import com.joyintech.framework.util.StringUtil;
//import com.joyintech.service.disclosure.information.config.hugeinfopro.dao.HugeInfoProMapper;
//import com.joyintech.service.disclosure.information.config.hugeinfopro.dto.HugeInfoProDto;
//import com.joyintech.service.disclosure.information.config.proelementadj.dao.ProElementAdjMapper;
//import com.joyintech.service.disclosure.information.config.proelementadj.dto.ProElementAdjDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.Map;
//
//@Service
//public class HugeInfoProService extends BaseService<HugeInfoProDto> {
//    @Autowired
//    private HugeInfoProMapper hugeInfoProMapper;
//
//    public Page<HugeInfoProDto> selectForPage(int page, int length, Map<String, Object> param) {
//        String prodRegistNo = (String)param.get("prodRegistNo");
//        if(StringUtil.isNotEmpty(prodRegistNo)){
//            param.put("prodRegistNoList", StringUtil.splitToList(GlobalConstant.COMMA,prodRegistNo));
//        }
//        String prodId = (String)param.get("prodId");
//        if(StringUtil.isNotEmpty(prodId)){
//            param.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
//        }
//        return super.queryForPage(page, length, () -> hugeInfoProMapper.select(param));
//    }
//
//    public ProElementAdjDto selectByPrimaryKey(String prodRegistNo) {
//        return hugeInfoProMapper.selectByPrimaryKey(prodRegistNo);
//    }
//
//    public void doComplete(ProElementAdjDto dto) {
//        String type = dto.getOperateType();
//        if (StringUtil.equals(type, "DELETE")) {
//            hugeInfoProMapper.deleteByKey(dto.getProdRegistNo());
//        }
//        else if (StringUtil.equals(type, "NEW")) {
//            ProElementAdjDto proElementAdjDto = new ProElementAdjDto();
//            BeanUtil.copyPropertiesIgnoreNull(proElementAdjDto,dto);
//            hugeInfoProMapper.insert(proElementAdjDto);
//        }
//        else {
//            hugeInfoProMapper.updateByKey(dto);
//        }
//    }
//
//
//
//    public String selectByProdRegistNo(Map<String, Object> map) {
//        return hugeInfoProMapper.selectByProdRegistNo(map);
//    }
//
//
//}
