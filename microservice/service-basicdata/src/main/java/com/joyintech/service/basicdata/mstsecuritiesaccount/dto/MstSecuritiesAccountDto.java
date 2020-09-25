/*
 * 文件名：MstSecuritiesAccountDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月13日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstsecuritiesaccount.dto;



import com.joyintech.tams.common.base.entity.StlSecAcctInfo;


/**
 * 证券账户管理信息
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月13日
 * @see MstSecuritiesAccountDto
 * @since 1.0
 */
public class MstSecuritiesAccountDto extends StlSecAcctInfo{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 资金账户名称
     */
    private String acctName;
    /**
     * 资金账户账号
     */
    private String busiAcctNo1;
    /**
     * 回款账户账号
     */
    private String busiAcctNo2;
//    /**
//     * 
//     */
//    private List<FileDto> fileArray;
//    
//    /**
//     * 获取 fileArray
//     * @return fileArray.
//     */
//    public List<FileDto> getFileArray() {
//        return fileArray;
//    }
//    
//    /**
//     * 设置 fileArray
//     * @param fileArray fileArray
//     */
//    public void setFileArray(List<FileDto> fileArray) {
//        this.fileArray = fileArray;
//    }


    /**
     * 获取 busiAcctNo1
     * @return busiAcctNo1.
     */
    public String getBusiAcctNo1() {
        return busiAcctNo1;
    }

    
    /**
     * 设置 busiAcctNo1
     * @param busiAcctNo1 busiAcctNo1
     */
    public void setBusiAcctNo1(String busiAcctNo1) {
        this.busiAcctNo1 = busiAcctNo1;
    }

    
    /**
     * 获取 busiAcctNo2
     * @return busiAcctNo2.
     */
    public String getBusiAcctNo2() {
        return busiAcctNo2;
    }

    
    /**
     * 设置 busiAcctN2
     * @param busiAcctN2 busiAcctNo2
     */
    public void setBusiAcctNo2(String busiAcctNo2) {
        this.busiAcctNo2 = busiAcctNo2;
    }

    /**
     * 获取资金账户名称
     * @return acctName.
     */
    public String getAcctName() {
        return acctName;
    }
    
    /**
     * 设置 资金账户名称
     * @param acctName acctName
     */
    public void setAcctName(String acctName) {
        this.acctName = acctName;
    }
    /**
     * 文件名
     */
    private String fileName;
    
    
    /**
     * 获取 operateType
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    
    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    
    /**
     * 获取 fileName
     * @return fileName.
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * 文档代码
     */
    private String bussinessId;
    
    /**
     * 文件路径
     */
    private String path;
    
    /**
     * 流水号
     */
    private String seqNo;
    /**
     * 流水号
     * @return 流水号
     */
    public String getSeqNo() {
        return seqNo;
    }


    /**
     * 流水号
     * @param seqNo 流水号
     */
    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }


    public String getPath() {
        return path;
    }

    
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 要素id
     */
    private String elementId;

    /**
     * pageModel
     */
    private String pageModel;


    
    


    
    /**
     * 获取 bussinessId
     * @return bussinessId.
     */
    public String getBussinessId() {
        return bussinessId;
    }

    
    /**
     * 设置 bussinessId
     * @param bussinessId bussinessId
     */
    public void setBussinessId(String bussinessId) {
        this.bussinessId = bussinessId;
    }
    /**
     * 操作类型
     */
    private String operateType;

   
    /**
     * 要素id
     * @return elementId 要素id
     */
    public String getElementId() {
        return elementId;
    }


    /**
     * 要素id
     * @param elementId 要素id
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }


    /**
     * pageModel
     * @return String
     * @see
     * @since 1.0
     */
    public String getPageModel() {
        return pageModel;
    }


    /**
     * pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    
    /**
     * 文件名
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
