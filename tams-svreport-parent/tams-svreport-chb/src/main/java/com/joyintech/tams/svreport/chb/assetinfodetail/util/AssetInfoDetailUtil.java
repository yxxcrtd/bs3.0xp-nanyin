package com.joyintech.tams.svreport.chb.assetinfodetail.util;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.base.entity.SysDatadictListLink;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.svreport.chb.assetinfodetail.asset.DtoChangeUtil;
import com.joyintech.tams.svreport.chb.assetinfodetail.dao.AssetInfoDetailMapper;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.AssetInfoDetailDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepDepositInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.dto.asset.SvrBussRepPlacementInfoDto;
import com.joyintech.tams.svreport.chb.assetinfodetail.service.AssetInfoDetailMService;
import com.joyintech.tams.systemtools.util.CommonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.*;

@Service
public class AssetInfoDetailUtil {

    private Logger logger = LoggerFactory.getLogger(getClass());

    static Map<String,Set<String>> map = null;

    @Autowired
    private AssetInfoDetailMapper assetInfoDetailMapper;

    /**
     * 获取资产及二级类型的关系
     * @return
     */
    public Map<String, Set<String>> getDataListLink(){

        if(map==null||map.isEmpty()){
            map = new HashMap<>();
            //界面配置,根据SYS_DATADICT_LIST_LINK
            Map<String,Object> params = new HashMap<>();
            params.put("linkDictCode", DictCode.资产_负债类别.getValue());
//		params.put("linkItemCode", itemCode);
            List<SysDatadictListLink> listLink = assetInfoDetailMapper.getDataListLinkData(params);
            for (SysDatadictListLink link:listLink) {
                if(null!=map.get(link.getLinkedItemCode())){
                    map.get(link.getLinkedItemCode()).add(link.getItemCode());
                }else{
                    map.put(link.getLinkedItemCode(),new HashSet<>(Arrays.asList(link.getItemCode())));
                }
            }
        }
        return map;
    }

    public AssetInfoDetailDto getDtoByPageMessgae(AssetInfoDetailDto dto)  {
        try {
            //遍历资产类型数据
            for (ServerMenu menu : ServerMenu.getAllAsset()) {
                //判断数据字典中是否有当前资产类型的实体类
                if (getDataListLink().get(menu.getAssetType()).contains(dto.getAssetType2())) {
                    //获取实体类中对应名称的资产方法
                    PropertyDescriptor pd = new PropertyDescriptor(menu.getServerId(),
                            dto.getClass());
                    Method getMethod =pd.getReadMethod();
                    //获取对应的实体类字段
                    Object obj1 = getMethod.invoke(dto);
                    //执行数据转换方法
                    DtoChangeUtil.SetChbToRep(obj1, dto, menu.getFlagStr(), true);
                    //资产类型赋值
                    dto.setAssetType(menu.getAssetType());
                }
            }
            return dto;
        }catch (Exception e){
            logger.info("实体转换错误,请检查数据类型",e);
        }finally {
            return null;
        }
    }

    public List<AssetInfoDetailDto> convertToBean(List<List<String>> dataList){
        List<AssetInfoDetailDto> entities = new ArrayList<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            if ( i < 2 ){
                continue;
            }
            List<String> list = dataList.get(i);
            AssetInfoDetailDto entity = new AssetInfoDetailDto();
            entity.setAssetCode(list.get(0));
            entity.setIssuePartyId(list.get(1));
            entity.setAssetType("01");
            entity.setAssetType2(list.get(2));
            entity.setTradeMarket(list.get(3));
            entity.setCcy(list.get(4));
            if (DictCodeListExt2.资产_负债类别_本行_他行存款_大额存单.getValue().equals(entity.getAssetType())){
                entity.setSvrBussRepDepositInfoDto(getSvrBussRepDepositInfoDto(list));
            }else if (DictCodeListExt2.资产_负债类别_拆放同业_买入返售_资产_同业拆入_卖出回购_负债.getValue().equals(entity.getAssetType())){

            }
            getDtoByPageMessgae(entity);
            entity.setCreateUser(LoginUserUtil.getUserName());
            entity.setUpdateUser(LoginUserUtil.getUserName());
            if(StringUtil.isEmpty(entity.getAssetUuid())){
                entity.setAssetUuid(IDGenerator.generate(36));
            }
            entity.setStatus(DictCodeListExt2.数据状态_有效.getValue());
            /***当资产为资管计划或者委外时,需要保存资管计划开始结束日**/
            if(DictCodeListExt2.资产_负债类别_资产管理产品.getValue().equals(entity.getAssetType())
                    ||DictCodeListExt2.资产_负债类别_委外投资_协议方式.getValue().equals(entity.getAssetType())){
                entity.setPlanSdate(entity.getVdate());
                entity.setPlanEdate(entity.getMdate());
            }
            /***【资产基本信息维护】-新建或修改页面（债券类资产），“具体类别”字段未保存至CHB_ASSET_INFO表**/
            if(DictCodeListExt2.资产_负债类别_债券类资产_理财直接融资工具_同业存单.getValue().equals(entity.getAssetType())){
                entity.setChbSectype(entity.getSvrBussFinBondRepInfoDto().getRepSectype());
            }
            entities.add(entity);
        }
        return entities;
    }

    public SvrBussRepDepositInfoDto getSvrBussRepDepositInfoDto(List<String> list){
        SvrBussRepDepositInfoDto dto = new SvrBussRepDepositInfoDto();
        dto.setRepBankName(list.get(5));
        dto.setAmt(new BigDecimal(list.get(6)));
        dto.setRepVdate(DateUtil.parse(list.get(7)));
        dto.setRepMdate(DateUtil.parse(list.get(8)));
        dto.setAssetYield(new BigDecimal(list.get(9)));
        dto.setRepBasis(list.get(10));
        dto.setRepDepoType(list.get(11));
        dto.setRepTargetType(list.get(12));
        dto.setRepTarget(list.get(13));
        dto.setRepRemark(list.get(14));
        dto.setRepPbcAssetType1(list.get(15));
        dto.setRepPbcAssetType2(list.get(16));
        dto.setRepPbcAssetType3(list.get(17));
        return dto;
    }

    public SvrBussRepPlacementInfoDto getSvrBussRepPlacementInfoDto(List<String> list){
        SvrBussRepPlacementInfoDto dto = new SvrBussRepPlacementInfoDto();
        dto.setAssetCode(list.get(5));
        return dto;
    }
}
