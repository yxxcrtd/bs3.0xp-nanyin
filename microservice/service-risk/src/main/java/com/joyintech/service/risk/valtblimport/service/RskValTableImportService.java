/*
 * 文件名：RskValTableImportService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.risk.valtblimport.service;

import java.util.List;
import java.util.Map;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.risk.valtblimport.dao.RskValTableImportMapper;
import com.joyintech.service.risk.valtblimport.dto.RskValtblAssetSubDto;
import com.joyintech.service.risk.valtblimport.dto.ValType;
import com.joyintech.tams.common.base.dao.RskValtblAssetSubMapper;
import com.joyintech.tams.common.base.dao.RskValtblSumAssetSubMapper;
import com.joyintech.tams.common.base.entity.RskValtblAssetSub;
import com.joyintech.tams.common.base.entity.RskValtblSumAssetSub;


@Service("backRskValTableImportService")
public class RskValTableImportService {
	
    @Autowired
    private RskValtblAssetSubMapper rskValtblAssetSubMapper;
    @Autowired
    private RskValtblSumAssetSubMapper rskValtblSumAssetSubMapper;
    @Autowired
    private RskValTableImportMapper rskValTableImportMapper;
    @Autowired
    private FinTradeProductMapper finTradeProductMapper;
    @Autowired
    private FinProductTypeMapper finProductTypeMapper;
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void saveValTbl(RskValtblAssetSubDto dto){ 
        rskValTableImportMapper.deleteValTable(dto);
        rskValTableImportMapper.deleteSumValTable(dto);
        if(null!=dto.getSelectedItem()&&!dto.getSelectedItem().isEmpty()){
            for(RskValtblAssetSubDto entity:dto.getSelectedItem()){
                if(StringUtil.equals(entity.getValType().getDesc(), ValType.ASSET.getDesc())
                        && StringUtil.isNotEmpty(entity.getIsMatch()) && StringUtil.equals(entity.getIsMatch(), DictCodeListExt1.是否_否.getValue())){
                    continue;
                }
                if(StringUtil.equals(entity.getValType().getDesc(),ValType.ASSET.getDesc())){
                    RskValtblAssetSub record = new RskValtblAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblAssetSubMapper.insert(record);
                }else{
                    RskValtblSumAssetSub record = new RskValtblSumAssetSub();
                    BeanUtil.copyPropertiesIgnoreNull(record, entity);
                    record.setPtlId(dto.getPtlId());
                    record.setValDate(dto.getValDate());
                    rskValtblSumAssetSubMapper.insert(record);
                }
            }
        }
    }
    
    /**
     *  估值表科目数量
     * <功能详细描述>
     * @param paramMap
     * @return
     * @see
     * @since 1.0
     */
    public int selectCount(Map<String,Object> paramMap){
        return rskValTableImportMapper.selectCount(paramMap);
    }
    /**
     * 估值表保存
     * <功能详细描述>
     * @param dto
     * @see
     * @since 1.0
     */
    public void createRepoInfo(RskValtblAssetSubDto entity2){
        //根据资产代码，查询系统中是否已经存在
        List<FinTradeProduct> list= finTradeProductMapper.selectByFinprodId(entity2.getFinprodId());
        if(StringUtil.isEmptyList(list)){
            //若不在，则生成新的数据
            String code=entity2.getSubCode().substring(0, 8);
            String finprodType="";
            String tradeMarket="";
            String type1="";
            String type2="";
            String type3="";
            String type6="";
            String type7="";
            String type8="";
            String type9="";
            String type10="";
            String type11="";
            String type12="";
            if(StringUtil.isNotEmpty(code)){
                if(code.equals("12020101")){
                    //上交所质押式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue();//F03
                    tradeMarket="03";
                    type1="02";
                    type2="02";
                    type3="G03";
                    type6="H04";
                    type7="04";
                    type8="F07";

                }else if(code.equals("12020102")){
                    //上交所买断式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue();//F05
                    tradeMarket="03";
                    type1="04";
                    type2="02";
                    type3="G04";
                    type6="H03";
                    type7="04";
                    type8="F10";
                    type9="06";
                    type10="44";
                    type11="093";
                    type12="095";

                }else if(code.equals("12020201")){
                    //银行间质押式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue();//F03
                    tradeMarket="01";
                    type1="02";
                    type2="01";
                    type3="G01";
                    type6="H04";
                    type7="04";
                    type8="F07";

                }else if(code.equals("12020202")){
                    //银行间买断式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue();//F05
                    tradeMarket="01";
                    type1="04";
                    type2="01";
                    type3="G02";
                    type6="H03";
                    type7="04";
                    type8="F10";
                    type9="06";
                    type10="44";
                    type11="093";
                    type12="095";

                }else if(code.equals("12020301")){
                    //深交所质押式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue();//F03
                    tradeMarket="04";
                    type1="02";
                    type2="03";
                    type3="G03";
                    type6="H04";
                    type7="04";
                    type8="F07";
                }else if(code.equals("12020302")){
                    //深交所买断式逆回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue();//F05
                    tradeMarket="04";
                    type1="04";
                    type2="03";
                    type3="G04";
                    type6="H03";
                    type7="04";
                    type8="F10";
                    type9="06";
                    type10="44";
                    type11="093";
                    type12="095";

                }else if(code.equals("22020101")){
                    //上交所卖出质押式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();//F02
                    tradeMarket="03";
                    type1="01";
                    type2="02";
                    type6="H04";
                    type7="03";
                    type8="F07";

                }else if(code.equals("22020102")){
                    //上交所卖出买断式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue();//F04
                    tradeMarket="03";
                    type1="03";
                    type2="02";
                    type6="H03";
                    type7="03";
                    type8="F10";
                    type9="12";
                    type10="45";
                    type11="093";
                    type12="095";

                }else if(code.equals("22020201")){
                    //银行间卖出质押式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();//F02
                    tradeMarket="01";
                    type1="01";
                    type2="01";
                    type6="H04";
                    type7="03";
                    type8="F07";

                }else if(code.equals("22020202")){
                    //银行间卖出买断式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue();//F04
                    tradeMarket="01";
                    type1="03";
                    type2="01";
                    type6="H03";
                    type7="03";
                    type8="F10";
                    type9="12";
                    type10="45";
                    type11="093";
                    type12="095";


                }else if(code.equals("22020301")){
                    //深交所卖出质押式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue();//F02
                    tradeMarket="04";
                    type1="01";
                    type2="03";
                    type6="H04";
                    type7="03";
                    type8="F07";

                }else if(code.equals("22020302")){
                    //深交所卖出买断式回购
                    finprodType=DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue();//F04
                    tradeMarket="04";
                    type1="03";
                    type2="03";
                    type6="H03";
                    type7="03";
                    type8="F10";
                    type9="12";
                    type10="45";
                    type11="093";
                    type12="095";

                }
                FinTradeProduct ftp=new FinTradeProduct();
                ftp.setFinprodId(entity2.getFinprodId());
                ftp.setBranch(0L);
                ftp.setFinprodType(finprodType);
                ftp.setFinprodType2(finprodType);
                ftp.setTradeMarket(tradeMarket);
                ftp.setCreateUser(CommonUtil.getUserName());
                ftp.setUpdateUser(CommonUtil.getUserName());
                ftp.setCreateDept(CommonUtil.getDeptCode());
                ftp.setCreateTime(DateUtil.now());
                ftp.setUpdateTime(DateUtil.now());
                finTradeProductMapper.insert(ftp);
                FinProductType fpt=new FinProductType();
                fpt.setFinprodId(entity2.getFinprodId());
                fpt.setBranch(0L);
                fpt.setType1(type1);
                fpt.setType2(type2);
                fpt.setType3(type3);
                fpt.setType6(type6);
                fpt.setType7(type7);
                fpt.setType8(type8);
                fpt.setType9(type9);
                fpt.setType10(type10);
                fpt.setType11(type11);
                fpt.setType12(type12);
                fpt.setCreateUser(CommonUtil.getUserName());
                fpt.setUpdateUser(CommonUtil.getUserName());
                fpt.setCreateDept(CommonUtil.getDeptCode());
                fpt.setCreateTime(DateUtil.now());
                fpt.setUpdateTime(DateUtil.now());
                finProductTypeMapper.insert(fpt);
            }
        }
    }
}
