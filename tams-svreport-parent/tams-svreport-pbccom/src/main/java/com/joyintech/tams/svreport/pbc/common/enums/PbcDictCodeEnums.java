package com.joyintech.tams.svreport.pbc.common.enums;

import com.joyintech.tams.constant.DictCode;

public enum PbcDictCodeEnums {
    募集方式_公募("公募","1"),
    募集方式_私募("私募","2"),
    管理方式_集合("集合管理","1"),
    管理方式_单独("单独管理","2"),
    运行方式_开放式净值型("开放式净值型","1"),
    运行方式_开放式非净值型("开放式非净值型","2"),
    运行方式_封闭式净值型("封闭式净值型","3"),
    运行方式_封闭式非净值型("封闭式非净值型","4"),
    产品类型_固定收益类("固定收益类","1"),
    产品类型_权益类("权益类","2"),
    产品类型_商品及金融衍生品类("商品及金融衍生品类","3"),
    产品类型_混合类("混合类","4"),
    有("有","1"),
    无("无","2"),
    是("无","1"),
    否("无","2"),
    受托职责_主动管理业务("主动管理业务","1"),
    受托职责_被动管理业务("被动管理业务","2"),
    ;

    private String name;

    private String code;

    PbcDictCodeEnums(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static String getCodeByName(String name){
        for (PbcDictCodeEnums codeEnums : PbcDictCodeEnums.values()) {
            if(codeEnums.name.equals(name)){
                return codeEnums.code;
            }
        }
        return "";
    }
}
