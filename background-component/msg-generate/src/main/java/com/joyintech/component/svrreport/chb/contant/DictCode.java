/*
 * 文件名：DictCode.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管系统V2.0
 * 修改人：王波
 * 修改时间：2020年9月25日
 * 修改内容：新建
 * 系统名称：理财资管系统V2.0
 */
 
package com.joyintech.component.svrreport.chb.contant;

import com.joyintech.background.component.common.pack.common.base.BaseDictCode;
import com.joyintech.background.component.common.pack.common.util.StringUtil;

/**
 * 数据字典
 * @description 
 * @author 王波
 * @version 1.0 2020年9月25日
 * @see DictCode
 * @since 1.0
 */
public enum DictCode implements BaseDictCode {
	/**
	 * 增加从业人员相关数据字典
	 * @modify zhaoyongsheng
	 * @date 20201017
	 */
	从业人员类型("SVR_EMPLOYEE_TYPE"),
	所属总行或分支行("SVR_BELONGS_BANK"),
	学历("SVR_EDUCATION"),
	学位("SVR_ACADEMIC_DEGREE"),
	专业技术职称("SVR_PROFESSIONAL_TITLE"),
	理财专业证书("SVR_FIN_PROFESSIONAL_CRTF"),
	登记业务分类("SVR_REG_BUSI_TYPE"),
	登记人员类别("SVR_REG_EMPLOYEE_TYPE"),
	/**从业人员相关数字字典增加     end**/
	
	/**
	 * 报表名称(CODE)
	 */
	报表名称("SVR_REPORT_CODE"),

    /**
     * 报表名称(CODE)
     */
    报表编码("DI_REPORT_CODE"),
    /**
     * 币种全称（中债）
     */
    币种全称_中债("SVR_CURRENCY"),

    /**
     * 接口系统ID
     */
    接口系统ID("SVR_INTERF_ID"),

    /**
     * 是否(中债)
     */
    是否_中债("SVR_YES_OR_NO"),


    /**
     * 维护数据状态
     */
    维护数据状态("MAINTAIN_STATUS"),

    /**
     * 报送反馈状态
     */
    报送反馈状态("SUBMIT_FEEDBACK_STATUS"),

    /**
     * 执行结果
     */
    执行结果("EXEC_RESULT"),

    /**
     * 接口类型
     */
    接口类型("SVR_INTERF_TYPE"),

    /**
     * 预警标示
     */
    预警标示("SVR_ISNEEDWARNING"),
	/**
	 * 资产/负债类别
	 */
	资产_负债类别("SVR_ASSETDEBT_TYPE"),

    /**
     * 报送状态
     */
    报送状态("SUBMIT_STATUS"),

    // add by zhangliang 20210510 中债直连反馈异常处理 start
    /**
     * 报送状态
     */
    中债一二三期文件处理状态("FILE_HANDLE_STATUS"),
    // add by zhangliang 20210510 中债直连反馈异常处理 end
	;

    /**
     * 对应于数据字典code
     */
    private String value;

    private DictCode(String value) {
        this.value = value;
    }

    /**
     * @param value 数据字典值
     * @return DictCode
     * @see
     * @since 1.0
     */
    public static DictCode get(String value) {
        DictCode[] values = DictCode.values();
        for (DictCode code : values) {
            if (StringUtil.equals(value, code.getValue())) {
                return code;
            }
        }
        return null;
    }

    /**
     * 获取 value
     *
     * @return value.
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置 value
     *
     * @param value value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 通过value生成枚举
     *
     * @param value value值
     * @return DictCode
     */
    public static DictCode fromValue(String value) {
        for (DictCode dictCode : DictCode.values()) {
            if (dictCode.getValue().equals(value)) {
                return dictCode;
            }
        }
        return null;
    }

}
