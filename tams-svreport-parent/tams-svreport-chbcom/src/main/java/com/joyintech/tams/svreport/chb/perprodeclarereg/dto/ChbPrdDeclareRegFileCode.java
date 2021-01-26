package com.joyintech.tams.svreport.chb.perprodeclarereg.dto;

import com.joyintech.framework.util.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 九大附件枚举
 *
 * @author 张亮
 * @version 1.0 2020年10月15日
 * @see ChbPrdDeclareRegFileCode
 * @since 1.0
 */
public enum  ChbPrdDeclareRegFileCode {

    报告主文件("01", "报告主文件", false, 20, "doc,docx,xls,xlsx,zip,pdf,jpg,jpeg,bmp,png,gif,gd,ppt,pptx,txt"),

    理财产品可行性评估报告("02", "理财产品可行性评估报告", true, 20, "doc,docx,xls,xlsx,zip,pdf,jpg,jpeg,bmp,png,gif,gd,ppt,pptx,txt"),

    内部审核文件("03", "内部审核文件", true, 20, "doc,docx,xls,xlsx,zip,pdf,jpg,jpeg,bmp,png,gif,gd,ppt,pptx,txt"),

    尽职调查文件("04", "对理财产品投资管理人、托管人、投资顾问等相关方的尽职调查文件", true, 20, "doc,docx,xls,xlsx,zip,pdf,jpg,jpeg,bmp,png,gif,gd,ppt,pptx,txt"),

    法律文件("05", "与理财产品投资管理人、托管人、投资顾问等相关方签署的法律文件", true, 20, "doc,docx,xls,xlsx,zip,pdf,jpg,jpeg,bmp,png,gif,gd,ppt,pptx,txt"),

    理财产品销售文件("06", "理财产品销售文件", true, 20, "doc,docx,xls,xlsx,zip,pdf,jpg,jpeg,bmp,png,gif,gd,ppt,pptx,txt"),

    理财产品说明书("07", "理财产品说明书", true, 20, "doc,docx,xls,xlsx,zip,pdf,jpg,jpeg,bmp,png,gif,gd,ppt,pptx,txt"),

    理财产品宣传材料("08", "理财产品宣传材料", true, 20, "doc,docx,xls,xlsx,zip,pdf,jpg,jpeg,bmp,png,gif,gd,ppt,pptx,txt"),

    其他材料("09", "其他材料", false, 20, "doc,docx,xls,xlsx,zip,pdf,jpg,jpeg,bmp,png,gif,gd,ppt,pptx,txt");



    /**
     * 文件夹序号
     */
    private String folderNo;

    /**
     * 文件夹名称
     */
    private String folderName;

    /**
     * 是否必填
     */
    private boolean isNend;

    /**
     * 包含文件大小
     */
    private long fileSize;

    /**
     * 包含文件类型
     */
    private String fileType;

    ChbPrdDeclareRegFileCode(String folderNo, String folderName, boolean isNend, long fileSize, String fileType) {
        this.folderNo = folderNo;
        this.folderName = folderName;
        this.isNend = isNend;
        this.fileSize = fileSize;
        this.fileType = fileType;
    }

    public String getFolderNo() {
        return folderNo;
    }

    public String getFolderName() {
        return folderName;
    }

    public boolean isNend() {
        return isNend;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public String getFileType() {
        return fileType;
    }

    public static List<String> genFolderNoList(){
        List<String> folderNoList = new ArrayList<>();
        ChbPrdDeclareRegFileCode[] allData = ChbPrdDeclareRegFileCode.class.getEnumConstants();
        for (ChbPrdDeclareRegFileCode code : allData){
            folderNoList.add(code.getFolderNo());
        }

        return folderNoList;
    }

    public static ChbPrdDeclareRegFileCode genInfoByFolderNo(String folderNo){
        ChbPrdDeclareRegFileCode[] allData = ChbPrdDeclareRegFileCode.class.getEnumConstants();
        for (ChbPrdDeclareRegFileCode code : allData){
            if (StringUtil.equals(code.getFolderNo(), folderNo)){
                return code;
            }
        }
        return null;
    }
}
