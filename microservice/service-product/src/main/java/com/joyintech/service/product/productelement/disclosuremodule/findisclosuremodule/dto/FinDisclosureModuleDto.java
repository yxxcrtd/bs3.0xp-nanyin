package com.joyintech.service.product.productelement.disclosuremodule.findisclosuremodule.dto;


import com.joyintech.tams.common.base.entity.PrdFileTemplat;
/**
 * 
 * @author wangzehui
 *
 */
public class FinDisclosureModuleDto extends PrdFileTemplat {
    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 文档代码
     */
    private String fileId;
    
//    /**
//     * 文件路径
//     */
//    private String path;
//    /**
//     * 文件路径
//     */
//    private String pathName;
//    
//    /**
//     * 流水号
//     */
//    private String seqNo;
//    /**
//     * 流水号
//     * @return 流水号
//     */
//    public String getSeqNo() {
//        return seqNo;
//    }
//
//
//    /**
//     * 流水号
//     * @param seqNo 流水号
//     */
//    public void setSeqNo(String seqNo) {
//        this.seqNo = seqNo;
//    }

    public String getFileName() {
        return fileName;
    }
//
//
//    public String getPath() {
//        return path;
//    }
//
//    
//    public void setPath(String path) {
//        this.path = path;
//    }
//    public String getPathName() {
//        return pathName;
//    }
//
//
//    
//    public void setPathName(String pathName) {
//        this.pathName = pathName;
//    }
//
//    /**
//     * 要素id
//     */
//    private String elementId;
//
//    /**
//     * pageModel
//     */
//    private String pageModel;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    
    public String getFileId() {
        return fileId;
    }
    /**
     * opType
     */
    private String opType;
    
    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

//    /**
//     * 操作类型
//     */
//    private String operateType;
//
//    /**
//     * 选择数据
//     */
//    private List<FinDisclosureModuleDto> selectedItem;
    
//    /**
//     * 操作类型
//     * @return 操作类型
//     */
//    public String getOperateType() {
//        return operateType;
//    }
//
//
//    /**
//     * 操作类型
//     * @param operateType 操作类型
//     */
//    public void setOperateType(String operateType) {
//        this.operateType = operateType;
//    }
//
//
//    /**
//     * 选择数据
//     * @return list
//     */
//    public List<FinDisclosureModuleDto> getSelectedItem() {
//        return selectedItem;
//    }
//
//
//    /**
//     * 选择数据
//     * @param selectedItem list
//     */
//    public void setSelectedItem(List<FinDisclosureModuleDto> selectedItem) {
//        this.selectedItem = selectedItem;
//    }
//
//
//    /**
//     * 要素id
//     * @return elementId 要素id
//     */
//    public String getElementId() {
//        return elementId;
//    }
//
//
//    /**
//     * 要素id
//     * @param elementId 要素id
//     */
//    public void setElementId(String elementId) {
//        this.elementId = elementId;
//    }
//
//
//    /**
//     * pageModel
//     * @return String
//     * @see
//     * @since 1.0
//     */
//    public String getPageModel() {
//        return pageModel;
//    }
//
//
//    /**
//     * pageModel
//     * @param pageModel pageModel
//     */
//    public void setPageModel(String pageModel) {
//        this.pageModel = pageModel;
//    }
    
    /**
     * 文件名
     * @param fileName fileName
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    
    public String getOpType() {
        return opType;
    }

    
    public void setOpType(String opType) {
        this.opType = opType;
    }
    

}
