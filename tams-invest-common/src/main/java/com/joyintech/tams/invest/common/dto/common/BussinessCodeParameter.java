package com.joyintech.tams.invest.common.dto.common;

import com.joyintech.framework.base.BaseDictCodeList;

import java.util.HashMap;
import java.util.Map;

/**
 * 业务编号生成规则参数 〈功能详细描述〉
 * 
 * @author dingzhipeng
 * @version 1.0 2018年8月20日
 * @see BussinessCodeParameter
 * @since 1.0
 */
public class BussinessCodeParameter {
    /**
     * 业务编号规则序号
     */
    private String seqNo;

    /**
     * 共通业务类型
     */
    private BaseDictCodeList commonBusinessType;
    //-----------add by jjpei 20190627---------
    /**
     * 共通业务类型EXT
     */
    private Map<String,Object> commonBusinessExtType;
    //-----------add by jjpei 20190627---------
    /**
     * 一级分类
     */
    private BaseDictCodeList type1;

    /**
     * 二级分类
     */
    private BaseDictCodeList type2;

    /**
     * 三级分类
     */
    private BaseDictCodeList type3;

    /**
     * 四级分类
     */
    private BaseDictCodeList type4;

    /**
     * 五级分类
     */
    private BaseDictCodeList type5;

    /**
     * 六级分类
     */
    private BaseDictCodeList type6;
    /**
     * mapInfo
     */
    private Map<String,Object> mapInfo;

    public BussinessCodeParameter() {
    }

    public BussinessCodeParameter(BaseDictCodeList commonBusinessType) {
        this.commonBusinessType = commonBusinessType;
    }

    /**
     * 获取 commonBusinessType
     * 
     * @return commonBusinessType.
     */
    public BaseDictCodeList getCommonBusinessType() {
        return commonBusinessType;
    }

    /**
     * 设置 commonBusinessType
     * 
     * @param commonBusinessType commonBusinessType
     */
    public void setCommonBusinessType(BaseDictCodeList commonBusinessType) {
        this.commonBusinessType = commonBusinessType;
    }

    /**
     * 获取 type1
     * 
     * @return type1.
     */
    public BaseDictCodeList getType1() {
        return type1;
    }

    /**
     * 设置 type1
     * 
     * @param type1 type1
     */
    public void setType1(BaseDictCodeList type1) {
        this.type1 = type1;
    }

    /**
     * 获取 type2
     * 
     * @return type2.
     */
    public BaseDictCodeList getType2() {
        return type2;
    }

    /**
     * 设置 type2
     * 
     * @param type2 type2
     */
    public void setType2(BaseDictCodeList type2) {
        this.type2 = type2;
    }

    /**
     * 获取 type3
     * 
     * @return type3.
     */
    public BaseDictCodeList getType3() {
        return type3;
    }

    /**
     * 设置 type3
     * 
     * @param type3 type3
     */
    public void setType3(BaseDictCodeList type3) {
        this.type3 = type3;
    }

    /**
     * 获取 type4
     * 
     * @return type4.
     */
    public BaseDictCodeList getType4() {
        return type4;
    }

    /**
     * 设置 type4
     * 
     * @param type4 type4
     */
    public void setType4(BaseDictCodeList type4) {
        this.type4 = type4;
    }

    /**
     * 获取 type5
     * 
     * @return type5.
     */
    public BaseDictCodeList getType5() {
        return type5;
    }

    /**
     * 设置 type5
     * 
     * @param type5 type5
     */
    public void setType5(BaseDictCodeList type5) {
        this.type5 = type5;
    }

    /**
     * 获取 type6
     * 
     * @return type6.
     */
    public BaseDictCodeList getType6() {
        return type6;
    }

    /**
     * 设置 type6
     * 
     * @param type6 type6
     */
    public void setType6(BaseDictCodeList type6) {
        this.type6 = type6;
    }

    /**
     * 获取 seqNo
     *
     * @return seqNo
     */
    public String getSeqNo() {
        return seqNo;
    }

    /**
     * 设置 seqNo
     *
     * @param seqNo seqNo
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public Map<String, Object> getCommonBusinessExtType() {
        return commonBusinessExtType;
    }

    public void setCommonBusinessExtType(Map<String, Object> commonBusinessExtType) {
        this.commonBusinessExtType = commonBusinessExtType;
    }
    /**
     * 转换成DICTCODELIST
     * @param baseDictCode
     * @return
     */
    public static Map<String,Object> fromDictCodeList(BaseDictCodeList baseDictCode){
        Map<String,Object> retMap=new HashMap<String,Object>();
        retMap.put("codeName",baseDictCode.getCodeName());
        retMap.put("value",baseDictCode.getValue());
        retMap.put("dictCode",baseDictCode.getDictCode());
        return retMap;
    }
    /**
     * 转换成DICTCODELIST
     * @return
     */
    public Map<String,Object> initMapInfo(){
        if(mapInfo==null){
            mapInfo = new HashMap<>();
        }
        if(seqNo!=null){
            mapInfo.put("seqNo",seqNo);
        }
        if(commonBusinessType!=null){
            mapInfo.put("commonBusinessType",fromDictCodeList(commonBusinessType));
        }
        if(type1!=null){
            mapInfo.put("type1",fromDictCodeList(type1));
        }
        if(type2!=null){
            mapInfo.put("type2",fromDictCodeList(type2));
        }
        if(type3!=null){
            mapInfo.put("type3",fromDictCodeList(type3));
        }
        if(type4!=null){
            mapInfo.put("type4",fromDictCodeList(type4));
        }
        if(type5!=null){
            mapInfo.put("type5",fromDictCodeList(type5));
        }
        if(type6!=null){
            mapInfo.put("type6",fromDictCodeList(type6));
        }
        if(commonBusinessExtType !=null ){
            mapInfo.put(commonBusinessType==null?"commonBusinessType":"commonBusinessExtType",commonBusinessExtType);
        }
        return mapInfo;
    }

    /**
     * 获取 mapInfo
     *
     * @return mapInfo mapInfo
     */
    public Map<String, Object> getMapInfo() {
        return this.mapInfo;
    }

    /**
     * 设置 mapInfo
     *
     * @param mapInfo mapInfo
     */
    public void setMapInfo(Map<String, Object> mapInfo) {
        this.mapInfo = mapInfo;
    }
}
