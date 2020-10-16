/*
 * 文件名：MstSecuritiesAccountService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangjun
 * 修改时间：2018年9月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.basicdata.mstsecuritiesaccount.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.StlSecAcctInfoMapper;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.service.basicdata.mstsecuritiesaccount.dao.MstSecuritiesAccountMapper;
import com.joyintech.service.basicdata.mstsecuritiesaccount.dto.MstSecuritiesAccountDto;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author zhangjun
 * @version 1.0 2018年9月11日
 * @see MstSecuritiesAccountService
 * @since 1.0
 */
@Service("backMstSecuritiesAccountService")
public class MstSecuritiesAccountService extends BaseService<MstSecuritiesAccountDto>{
//    /**
//     * 附件信息共通Service
//     */
//    @Autowired(required=false)
//    private MstFileCommonService mstFileCommonService;
    /**
     * 手写证券账号管理Mapper
     */
    @Autowired
    private MstSecuritiesAccountMapper mstSecuritiesAccountMapper;
    /**
     * 自动生成Mapper
     */
    @Autowired
    private StlSecAcctInfoMapper  stlSecAcctInfoMapper;
    
    /**
     * 查询
     * @param pageParam Map
     * @return Page类
     * @throws Exception 异常信息
     */
    public Page<MstSecuritiesAccountDto> selectForPage(int page, int length,Map<String, Object> param)
        throws Exception {
        return super.queryForPage(page, length, () -> mstSecuritiesAccountMapper.select(param));
    }
    
    /**
     * 组合代码联想下拉框 <功能详细描述>
     * 
     * @param param 组合代码 用户Id
     * @return List<SuggestDto>
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getPortfolioId(Map<String, Object> param) {
        return mstSecuritiesAccountMapper.getPortfolioId(param);
    }
    
    /**
     * 联想文本框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    public List<SuggestDto> getSecAcctId(String param) {
        return  mstSecuritiesAccountMapper.getSecAcctId(param);
    }
    /**
     * 联想文本框service层实现
     *
     * @param param String
     * @return SuggestDto
     */
    public MstSecuritiesAccountDto selectBySecAcctId(String secAcctId) {
        return  mstSecuritiesAccountMapper.selectBySecAcctId(secAcctId);
    }
    /**
     * 回款账户以及资金结算账户联想文本框service层实现
     *
     * @param search String
     * @return SuggestDto
     */
    public List<SelectOptionDto> getBusiAcctNo(String search) { 
        return  mstSecuritiesAccountMapper.getBusiAcctNo(search);
    }
    /**
     * 删除操作
     *
     * @param param String
     * @return SuggestDto
     */
    public int delete(StlSecAcctInfo entity) {
//        mstSecuritiesAccountMapper.deleteFileByBusinessId(entity.getSecAcctId());
        return  stlSecAcctInfoMapper.deleteByPrimaryKey(entity.getSecAcctId());
    }
    /**
     * 插入操作
     *
     * @param param String
     * @return SuggestDto
     */
    public int insert(MstSecuritiesAccountDto entity) {
//        if(entity.getFileName()!=null){
//          //查询上传的文件
//            FileDto dto=new FileDto();
//            dto.setFileName(entity.getFileName());
//            dto.setBusinessId(entity.getSecAcctId());
//            dto.setPath(entity.getPath());
//            dto.setSeqNo(entity.getSeqNo());//生成流水号
//            dto.setCreateDept(entity.getCreateDept());
//            mstSecuritiesAccountMapper.insertIntoFile(dto);
//        }
//        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
//        List<SysUploadFileInfo> fileList = new ArrayList<>();
//        if(entity.getFileArray()!=null){
//            for(FileDto fileArray:entity.getFileArray()){
//                SysUploadFileInfo sysUploadFileInfo = new SysUploadFileInfo();
//                sysUploadFileInfo.setSeqNo(fileArray.getFileNo());//生成流水号
//                fileList.add(sysUploadFileInfo);
//            }
//            mstFileInputParameter.setBussinessId(entity.getSecAcctId());
//            mstFileInputParameter.setFileList(fileList);
//            if(mstFileCommonService != null){
//                mstFileCommonService.updateBusinessId(mstFileInputParameter);
//            }
//        }
        return  stlSecAcctInfoMapper.insert(entity);
    }
    /**
     * 修改操作
     *
     * @param param String
     * @return SuggestDto
     */
    public int update(MstSecuritiesAccountDto entity) {
//        List<FileDto> dto=this.selectFileByBusinessId(entity.getSecAcctId());
//        if(dto!=null){
//            mstSecuritiesAccountMapper.deleteFileByBusinessId(entity.getSecAcctId());
//            dto.setFileName(entity.getFileName());
//            dto.setBusinessId(entity.getSecAcctId());
//            dto.setPath(entity.getPath());
//            dto.setSeqNo(entity.getSeqNo());//生成流水号
//            dto.setCreateDept(entity.getCreateDept());
//            mstSecuritiesAccountMapper.insertIntoFile(dto);
//        }
//        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
//        List<SysUploadFileInfo> fileList = new ArrayList<>();
//        if(entity.getFileArray()!=null){
//            for(FileDto fileArray:entity.getFileArray()){
//                SysUploadFileInfo sysUploadFileInfo = new SysUploadFileInfo();
//                sysUploadFileInfo.setSeqNo(fileArray.getFileNo());//生成流水号
//                fileList.add(sysUploadFileInfo);
//            }
//            mstFileInputParameter.setBussinessId(entity.getSecAcctId());
//            mstFileInputParameter.setFileList(fileList);
//            if(mstFileCommonService != null){
//                mstFileCommonService.updateBusinessId(mstFileInputParameter);
//            }
//        }
        return  stlSecAcctInfoMapper.updateByPrimaryKey(entity);
    }
    /**
     *检查证券账户账号是否用过
     * <功能详细描述>
     * @param secAcctId
     * @return
     * @see
     * @since 1.0
     */
    public int checkBySecAcctId(MstSecuritiesAccountDto dto) {
        return mstSecuritiesAccountMapper.checkBySecAcctId(dto);
    }
    /**
     * 检查证券账户名称是否用过
     * <功能详细描述>
     * @param secAcctName
     * @return
     * @see
     * @since 1.0
     */
    public int checkBySecAcctName(MstSecuritiesAccountDto dto) {
        return mstSecuritiesAccountMapper.checkBySecAcctName(dto);
    }
    /**
     * 检查资金结算账户是否存在
     * <功能详细描述>
     * @param trdAcctId
     * @return
     * @see
     * @since 1.0
     */
    public int checkByTrdAcctId(MstSecuritiesAccountDto dto) {
        return mstSecuritiesAccountMapper.checkByTrdAcctId(dto);
    }
    /**
     * 删除时检查数据是否被使用过
     * <功能详细描述>
     * @param secAcctId
     * @return
     * @see
     * @since 1.0
     */
    public int checkCodeIsUse(MstSecuritiesAccountDto dto) {
        return mstSecuritiesAccountMapper.checkCodeIsUse(dto);
    }
    /**
     * 批量删除时检查数据是否被使用过
     * <功能详细描述>
     * @param item
     * @return
     * @see
     * @since 1.0
     */
    public List<MstSecuritiesAccountDto> checkAllCodeIsUse(List<MstSecuritiesAccountDto> item) {
        return mstSecuritiesAccountMapper.checkAllCodeIsUse(item);
    }
//    /**
//     * 查询上传的文件
//     * <功能详细描述>
//     * @param item
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public List<FileDto> selectFileByBusinessId(String businessId) {
//        return mstSecuritiesAccountMapper.selectFileByBusinessId(businessId);
//    }
//    /**
//     * 插入附件文件
//     * <功能详细描述>
//     * @param item
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public int insertIntoFile(FileDto dto) {
//        return mstSecuritiesAccountMapper.insertIntoFile(dto);
//    }
//    /**
//     * 插入附件文件
//     * <功能详细描述>
//     * @param item
//     * @return
//     * @see
//     * @since 1.0
//     */
//    public int deleteFileByBusinessId(String businessId) {
//        return mstSecuritiesAccountMapper.deleteFileByBusinessId(businessId);
//    }
}
