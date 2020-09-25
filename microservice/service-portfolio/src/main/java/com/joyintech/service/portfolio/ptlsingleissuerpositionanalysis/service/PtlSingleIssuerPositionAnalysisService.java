package com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlsingleassetpositionanalysis.dto.PtlSingleAssetPositionAnalysis001Dto;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dao.PtlSingleIssuerPositionAnalysisMapper;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysisDto;
@Service
public class PtlSingleIssuerPositionAnalysisService extends BaseService<PtlSingleIssuerPositionAnalysisDto>{
	@Autowired
	private PtlSingleIssuerPositionAnalysisMapper ptlSingleIssuerPositionAnalysisMapper;
	
	/**
	 * 获取发行人下拉框中的值
	 * @param map
	 * @return
	 */
	public Page<SelectOptionDto> getPublisher(@RequestBody Map<String,Object> map, int page, int length){
    	Page<SelectOptionDto> selectOptDto = this.queryForPageForSelectReturn(page, length,()->ptlSingleIssuerPositionAnalysisMapper.getPublisher(map));
        return selectOptDto;
    }
	
	 /**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @param page 页码
     * @param length 页面长度
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<PtlSingleIssuerPositionAnalysisDto> selectForPage(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
        List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
        pageParam.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        if(StringUtil.isNotEmpty(pageParam.get("publisher"))){
            pageParam.put("publisherList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("publisher")));
        }
        if(StringUtil.isNotEmpty(pageParam.get("parentId")) && !StringUtil.equals(pageParam.get("parentId"),"root")) {
            List<PtlSingleIssuerPositionAnalysisDto> resultList = ptlSingleIssuerPositionAnalysisMapper.selectAll(pageParam);
            Page<PtlSingleIssuerPositionAnalysisDto> pagelist = new Page<>();
            pagelist.setRoot(resultList);
            return pagelist;
        }
        return super.queryForPage(page, length, () -> ptlSingleIssuerPositionAnalysisMapper.selectAll(pageParam));
    }
    
    /**
     * 导出查询list。
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<PtlSingleIssuerPositionAnalysisDto> selectForExport(int page, int length, Map<String, Object> pageParam)
            throws Exception {
    	if(StringUtil.isNotEmpty(pageParam.get("publisher"))){
            pageParam.put("publisherList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("publisher")));
        }
        // 多级法人改造 add by jingqiongqiong 20200615
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //当前用户所属机构代码
            String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
            List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
            pageParam.put("institutionCode",createInst);
            pageParam.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        }
        // 多级法人改造 add by jingqiongqiong 20200615
    	List<PtlSingleIssuerPositionAnalysisDto> resultList = new ArrayList<>();
    	Page<PtlSingleIssuerPositionAnalysisDto> pageRoot = super.queryForPage(page, length, () -> ptlSingleIssuerPositionAnalysisMapper.selectAll(pageParam));
        List<PtlSingleIssuerPositionAnalysisDto> listRoot = pageRoot.getRoot();
        for(PtlSingleIssuerPositionAnalysisDto entity : listRoot) {
            if(StringUtil.equals(entity.getParentInfo(), "root")) {
                resultList.add(entity);
                pageParam.put("parentId", entity.getChildInfo());
                List<PtlSingleIssuerPositionAnalysisDto> listRoot2 = ptlSingleIssuerPositionAnalysisMapper.selectAll(pageParam);
                for(PtlSingleIssuerPositionAnalysisDto entity2 : listRoot2) {
                    if(StringUtil.equals(entity2.getParentInfo(), entity.getChildInfo())) {
                        resultList.add(entity2);
                    }
                }
            }
        }
        pageRoot.setRoot(resultList);
    	return pageRoot;
    }
    
    /**
     * 导出查询list。
     * 
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    // add by wangyang1【吉林银行个性化代码】添加导出一级明细数据  start
    public Page<PtlSingleIssuerPositionAnalysisDto> selectForExportTwo(int page, int length, Map<String, Object> pageParam)
            throws Exception {
        if(StringUtil.isNotEmpty(pageParam.get("publisher"))){
            pageParam.put("publisherList",StringUtil.splitToList(GlobalConstant.COMMA,(String)pageParam.get("publisher")));
        }
        // 多级法人改造 add by jingqiongqiong 20200615
        if(MultilevelDataAuthorityUtil.isEnabled()) {
            //当前用户所属机构代码
            String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
            //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
            List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
            pageParam.put("institutionCode",createInst);
            pageParam.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        }
        // 多级法人改造 add by jingqiongqiong 20200615
        List<PtlSingleIssuerPositionAnalysisDto> resultList = new ArrayList<>();
        Page<PtlSingleIssuerPositionAnalysisDto> pageRoot = super.queryForPage(page, length, () -> ptlSingleIssuerPositionAnalysisMapper.selectAll(pageParam));
        List<PtlSingleIssuerPositionAnalysisDto> listRoot = pageRoot.getRoot();
        for(PtlSingleIssuerPositionAnalysisDto entity : listRoot) {
            if(StringUtil.equals(entity.getParentInfo(), "root")) {
                resultList.add(entity);
                pageParam.put("parentId", entity.getChildInfo());
                List<PtlSingleIssuerPositionAnalysisDto> listRoot2 = ptlSingleIssuerPositionAnalysisMapper.selectAll(pageParam);
                for(PtlSingleIssuerPositionAnalysisDto entity2 : listRoot2) {
                    if(StringUtil.equals(entity2.getParentInfo(), entity.getChildInfo())) {
                        resultList.add(entity2);
                        pageParam.put("parentId", entity2.getChildInfo());
                        List<PtlSingleIssuerPositionAnalysisDto> listRoot3 = ptlSingleIssuerPositionAnalysisMapper.selectAll(pageParam);
                        for(PtlSingleIssuerPositionAnalysisDto entity3 : listRoot3) {
                            if(StringUtil.equals(entity3.getParentInfo(), entity2.getChildInfo())) {
                                resultList.add(entity3);
                            }
                        }
                    } 
                }
            }
        }
        pageRoot.setRoot(resultList);
        return pageRoot;
    }
    // add by wangyang1【吉林银行个性化代码】添加导出一级明细数据 end
}
