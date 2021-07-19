package com.joyintech.service.risk.creditmanagement.creditrulemanage.service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.creditmanagement.creditrulemanage.dao.CreditRuleMapper;
import com.joyintech.service.risk.creditmanagement.creditrulemanage.dto.CreditRuleInfoDto;
import com.joyintech.tams.common.base.dao.CreditRuleInfoMapper;
import com.joyintech.tams.common.base.entity.CreditRuleInfo;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;


/**
 * 额度校验规则
 * @author gejiaao
 * @version 1.0 2020年03月01日
 * @see CreditRuleService
 * @since 1.0
 */
@Service("backCreditRuleService")
public class CreditRuleService extends BaseService<CreditRuleInfoDto> {
	
	@Autowired
	private CreditRuleMapper creditRuleMapper;

    @Autowired
    private CreditRuleInfoMapper creditRuleInfoMapper;

    @Autowired(required = false)
    private BaseGetBussinessId baseGetBussinessId;
    /**
     * 画面查询方法
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<CreditRuleInfoDto> selectForPage(int page, int length,Map<String, Object> param)
        throws Exception {
        return super.queryForPage(page, length, () -> creditRuleMapper.selectForPage(param));
    }

    /**
     * 客户代码联想文本框service层实现
      *
      * @param map 检索参数
      * @return SuggestDto
      */
    public Page<SelectOptionDto> getLimitLabel(Map<String, Object> map, int page, int length) {
    	//map.put("param", map);
    	return this.queryForPageForSelectReturn(page, length,()-> creditRuleMapper.getLimitLabel(map));
    }

    /**
     * 插入
     * @param entity
     * @return
     * @since 1.0
     */
    public void insert(CreditRuleInfoDto entity) throws InvocationTargetException, IllegalAccessException {
        List<CreditRuleInfoDto> creditRuleInfoDtos = getCreditRuleInfoDtos(entity);
        for (CreditRuleInfoDto creditRuleInfoDto : creditRuleInfoDtos) {
            if((creditRuleInfoDto.getAstType2 () == null || creditRuleInfoDto.getAstType3 () == null) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B01") && creditRuleInfoDto.getAstType3 ().equals ("B01")) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B02") && creditRuleInfoDto.getAstType3 ().equals ("B02")) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B03") && creditRuleInfoDto.getAstType3 ().equals ("B03")) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B04") && creditRuleInfoDto.getAstType3 ().equals ("B04")) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B05") && (creditRuleInfoDto.getAstType3 ().equals ("B001") || creditRuleInfoDto.getAstType3 ().equals ("B002") || creditRuleInfoDto.getAstType3 ().equals ("B003") || creditRuleInfoDto.getAstType3 ().equals ("B004")  || creditRuleInfoDto.getAstType3 ().equals ("B005") || creditRuleInfoDto.getAstType3 ().equals ("B006") || creditRuleInfoDto.getAstType3 ().equals ("B007"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B06") && (creditRuleInfoDto.getAstType3 ().equals ("B008")   || creditRuleInfoDto.getAstType3 ().equals ("B009"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B07") && (creditRuleInfoDto.getAstType3 ().equals ("B010")   || creditRuleInfoDto.getAstType3 ().equals ("B011"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B08") && (creditRuleInfoDto.getAstType3 ().equals ("B012")   || creditRuleInfoDto.getAstType3 ().equals ("B013"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B09") && (creditRuleInfoDto.getAstType3 ().equals ("B014")   || creditRuleInfoDto.getAstType3 ().equals ("B015"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B10") && creditRuleInfoDto.getAstType3 ().equals ("B10")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B11") && creditRuleInfoDto.getAstType3 ().equals ("B11")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B12") && creditRuleInfoDto.getAstType3 ().equals ("B12")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B13") && (creditRuleInfoDto.getAstType3 ().equals ("B016")   || creditRuleInfoDto.getAstType3 ().equals ("B017") || creditRuleInfoDto.getAstType3 ().equals ("B018"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B14") && creditRuleInfoDto.getAstType3 ().equals ("B14")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B15") && creditRuleInfoDto.getAstType3 ().equals ("B15")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B16") && creditRuleInfoDto.getAstType3 ().equals ("B16")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("B17") && creditRuleInfoDto.getAstType3 ().equals ("B17")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("D01") && creditRuleInfoDto.getAstType3 ().equals ("D01")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("D02") && (creditRuleInfoDto.getAstType3 ().equals ("D001")  || creditRuleInfoDto.getAstType3 ().equals ("D002"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("F01") && creditRuleInfoDto.getAstType3 ().equals ("F01") ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("F02") && (creditRuleInfoDto.getAstType3 ().equals ("F001")  || creditRuleInfoDto.getAstType3 ().equals ("F003") || creditRuleInfoDto.getAstType3 ().equals ("F004") || creditRuleInfoDto.getAstType3 ().equals ("F006")|| creditRuleInfoDto.getAstType3 ().equals ("F007"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("F29") && creditRuleInfoDto.getAstType3 ().equals ("F05")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("F30") && creditRuleInfoDto.getAstType3 ().equals ("F06")  ) ||
                    ((creditRuleInfoDto.getAstType2 ().equals ("H01") || creditRuleInfoDto.getAstType2 ().equals ("H03")  ) && (creditRuleInfoDto.getAstType3 ().equals ("H03") || creditRuleInfoDto.getAstType3 ().equals ("H04"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("L01") && (creditRuleInfoDto.getAstType3 ().equals ("L01")   || creditRuleInfoDto.getAstType3 ().equals ("L02"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("L02") && creditRuleInfoDto.getAstType3 ().equals ("L01")  ) ||
                    ((creditRuleInfoDto.getAstType2 ().equals ("N01")  || creditRuleInfoDto.getAstType2 ().equals ("N02")   || creditRuleInfoDto.getAstType2 ().equals ("N03") || creditRuleInfoDto.getAstType2 ().equals ("N04" )|| creditRuleInfoDto.getAstType2 ().equals ("N05") || creditRuleInfoDto.getAstType2 ().equals ("N06")) && (creditRuleInfoDto.getAstType3 ().equals ("N001") || creditRuleInfoDto.getAstType3 ().equals ("N004"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P01") && (creditRuleInfoDto.getAstType3 ().equals ("1201")   || creditRuleInfoDto.getAstType3 ().equals ("1202") || creditRuleInfoDto.getAstType3 ().equals ("1203") || creditRuleInfoDto.getAstType3 ().equals ("1204") || creditRuleInfoDto.getAstType3 ().equals ("1205" )|| creditRuleInfoDto.getAstType3 ().equals ("1206") || creditRuleInfoDto.getAstType3 ().equals ("1207") || creditRuleInfoDto.getAstType3 ().equals ("1208")  || creditRuleInfoDto.getAstType3 ().equals ("1209") || creditRuleInfoDto.getAstType3 ().equals ("1210") || creditRuleInfoDto.getAstType3 ().equals ("1299"))) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P02") && creditRuleInfoDto.getAstType3 ().equals ("P02")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P03") && creditRuleInfoDto.getAstType3 ().equals ("P03")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P04") && creditRuleInfoDto.getAstType3 ().equals ("P04")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P05") && creditRuleInfoDto.getAstType3 ().equals ("P05")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P06") && creditRuleInfoDto.getAstType3 ().equals ("P06")  ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P07") && creditRuleInfoDto.getAstType3 ().equals ("P07") ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P08") && creditRuleInfoDto.getAstType3 ().equals ("P08") ) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P09") && (creditRuleInfoDto.getAstType3 ().equals ("P09") )) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P10") && (creditRuleInfoDto.getAstType3 ().equals ("P10") )) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P11") && (creditRuleInfoDto.getAstType3 ().equals ("P11") )) ||
                    (creditRuleInfoDto.getAstType2 ().equals ("P12") && (creditRuleInfoDto.getAstType3 ().equals ("P12")) )
            ){
                creditRuleInfoMapper.insert(creditRuleInfoDto);
            }
        }
    }


    /**
     * 更新
     * @param entity
     * @return
     * @since 1.0
     */
    public void update(CreditRuleInfoDto entity) {
        if(null!=entity){
            creditRuleInfoMapper.updateByPrimaryKey(entity);
        }
    }

    private String getQuotePrimaryKey(){
        if(null != baseGetBussinessId) {
            BussinessCodeParameter codeParameter = new BussinessCodeParameter();
            codeParameter.setCommonBusinessType(DictCodeList.共通业务类型_机构授信);
            return baseGetBussinessId.buildBussinessCode(codeParameter);
        }
        return IDGenerator.generate();
    }
    /**
     * 删除校验规则
     * @param dto
     */
    public int delete(CreditRuleInfoDto dto) {
        return creditRuleInfoMapper.deleteByPrimaryKey(dto.getCreditRuleId());
    }


    /**
     * 查询校验规则信息
     * @param map
     */
    public CreditRuleInfo queryCreditRuleBySeqno(Map<String, Object> map ) {
        String seqno=(String) map.get("seqno");
        return creditRuleInfoMapper.selectByPrimaryKey(seqno);
    }

    /**
     * 校验标签重名
     * @param param
     */
    public Boolean checkTag(Map<String, Object> param) {
        int count= creditRuleMapper.checkTag(param);
        if(count>0)
        {
            return  true;
        }
        return  false;
    }

    /**
     * 校验规则是否重复
     * @param dto
     */
    public List<CreditRuleInfoDto> checkRule(CreditRuleInfoDto dto) throws InvocationTargetException, IllegalAccessException {
        List<CreditRuleInfoDto> astType5List = getCreditRuleInfoDtos(dto);

        for (CreditRuleInfoDto creditRuleInfoDto : astType5List) {
            List<CreditRuleInfoDto> resultDtos = creditRuleMapper.checkRule(creditRuleInfoDto);
//            if (resultDtos.size() > 0){
//                return  resultDtos.get(0);
//            }
            return resultDtos;
        }

        return  null;
    }

    private List<CreditRuleInfoDto> getCreditRuleInfoDtos(CreditRuleInfoDto dto) throws InvocationTargetException, IllegalAccessException {
        List<CreditRuleInfoDto> dtoList = new ArrayList<>();
        String astType2 = dto.getAstType2();
        if (StringUtil.isNotEmpty(astType2) && astType2.contains(",")){
            String[] split = astType2.split(",");
            for (String s : split) {
                //处理拆分资产二级分类
                CreditRuleInfoDto dest = new CreditRuleInfoDto();
                BeanUtil.copyProperties(dest, dto);
                dest.setAstType2(s);
                dest.setCreditRuleId(IDGenerator.generate());
                dtoList.add(dest);
            }
        }else {
            dto.setCreditRuleId(IDGenerator.generate());
            dtoList.add(dto);
        }
        //循环资产二级分类Dto
        List<CreditRuleInfoDto> astType3List = new ArrayList<>();
        for (CreditRuleInfoDto astType2Dto : dtoList) {
            if (StringUtil.isNotEmpty(astType2Dto.getAstType3()) && astType2Dto.getAstType3().contains(",")){
                for (String s1 : astType2Dto.getAstType3().split(",")) {
                    CreditRuleInfoDto dest = new CreditRuleInfoDto();
                    BeanUtil.copyProperties(dest, astType2Dto);
                    dest.setAstType3(s1);
                    dest.setCreditRuleId(IDGenerator.generate());
                    astType3List.add(dest);
                }
            }else {
                astType2Dto.setCreditRuleId(IDGenerator.generate());
                astType3List.add(astType2Dto);
            }
        }
        //循环资产三级分类Dto
        List<CreditRuleInfoDto> astType4List = new ArrayList<>();
        for (CreditRuleInfoDto astType3Dto : astType3List) {
            if (StringUtil.isNotEmpty(astType3Dto.getAstType4()) && astType3Dto.getAstType4().contains(",")){
                for (String s2 : astType3Dto.getAstType4().split(",")) {
                    CreditRuleInfoDto dest = new CreditRuleInfoDto();
                    BeanUtil.copyProperties(dest, astType3Dto);
                    dest.setAstType4(s2);
                    dest.setCreditRuleId(IDGenerator.generate());
                    astType4List.add(dest);
                }
            }else {
                astType3Dto.setCreditRuleId(IDGenerator.generate());
                astType4List.add(astType3Dto);
            }
        }
        //循环资产四级分类Dto
        List<CreditRuleInfoDto> astType5List = new ArrayList<>();
        for (CreditRuleInfoDto astType4Dto : astType4List) {
            if (StringUtil.isNotEmpty(astType4Dto.getAstType5()) && astType4Dto.getAstType5().contains(",")){
                for (String s3 : astType4Dto.getAstType5().split(",")) {
                    CreditRuleInfoDto dest = new CreditRuleInfoDto();
                    BeanUtil.copyProperties(dest, astType4Dto);
                    dest.setAstType5(s3);
                    dest.setCreditRuleId(IDGenerator.generate());
                    astType5List.add(dest);
                }
            }else {
                astType4Dto.setCreditRuleId(IDGenerator.generate());
                astType5List.add(astType4Dto);
            }
        }
        return astType5List;
    }
}
