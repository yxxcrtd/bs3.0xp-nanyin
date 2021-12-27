package com.joyintech.sso.config.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.sso.common.Message;
import com.joyintech.sso.common.MessageCode;
import com.joyintech.sso.common.dto.TreeNode;
import com.joyintech.sso.common.service.BaseService;
import com.joyintech.sso.config.dao.SsoUserPlatFormMapper;
import com.joyintech.sso.config.dto.GrantAuthorizationDto;
import com.joyintech.sso.config.pojo.SsoUserPlatForm;
import com.joyintech.sso.util.StringUtils;
import com.joyintech.sso.util.TreeUtil;

@Service
public class SsoUserPlatformService extends BaseService {
    @Resource
    private SsoUserPlatFormMapper ssoUserPlatFormMapper;

    /**
     * 查询 用户授权平台信息list
     * @param where
     * @return
     */
    public List<SsoUserPlatForm> listSsoUserPlatForm(Map<String,Object> where) {
        return ssoUserPlatFormMapper.listSsoUserPlatForm(where);
    }

    /**
     * 查询 用户授权平台信息
     * @param userid
     * @param userid
     * @return
     */
    public SsoUserPlatForm selectSsoUserPlatFormById(String userid, String platformid){
        return ssoUserPlatFormMapper.selectSsoUserPlatFormById(userid,platformid);
    }

    /**
     * 新增 用户授权平台信息
     * @param ssoUserPlatForm
     * @return
     */
    public Message<String> insertSsoUserPlatForm(SsoUserPlatForm ssoUserPlatForm) {
        Message<String> message = this.validateSsoUserPlatForm(ssoUserPlatForm);
        if(message != null) {
            return message;
        }

        if(ssoUserPlatFormMapper.selectSsoUserPlatFormById(ssoUserPlatForm.getUserid(), ssoUserPlatForm.getPlatformid()) != null) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_USERID_EXISTS);
        }

        ssoUserPlatFormMapper.insertSsoUserPlatForm(ssoUserPlatForm);
        return new Message<String>().success();
    }

    /**
     * 删除 用户授权平台信息
     * @param userid
     * @param platformid
     * @return
     */
    public Message<String> deleteSsoUserPlatFormById(String userid,String platformid) {
        ssoUserPlatFormMapper.deleteSsoUserPlatFormById(userid,platformid);
        return new Message<String>().success();
    }

    /**
     * 根据平台代码 批量删除 用户授权平台信息
     * @param userid
     * @param platformids
     * @return
     */
    public Message<String> deleteSsoUserPlatFormByPlatformids(String userid,String[] platformids){
        Map<String,Object> map = new HashMap<>();
        map.put("userid",userid);
        map.put("platformids",platformids);
        ssoUserPlatFormMapper.deleteSsoUserPlatFormByPlatformids(map);
        return new Message<String>().success();
    }

    /**
     * 更新 用户授权平台信息
     * @param ssoUserPlatForm
     * @return
     */
    public Message<String> updateSsoUserPlatForm(SsoUserPlatForm ssoUserPlatForm) {
        Message<String> message = this.validateSsoUserPlatForm(ssoUserPlatForm);
        if(message != null) {
            return message;
        }
        ssoUserPlatFormMapper.updateSsoUserPlatForm(ssoUserPlatForm);
        return new Message<String>().success();
    }


    /**
     * 校验 用户授权平台信息
     * @param ssoUserPlatForm
     * @return
     */
    public Message<String> validateSsoUserPlatForm(SsoUserPlatForm ssoUserPlatForm){
        if(StringUtils.isEmpty(ssoUserPlatForm.getUserid())) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_USERID_ERROR);
        }

        if(StringUtils.isEmpty(ssoUserPlatForm.getPlatformid())) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_NAME_ERROR);
        }

        return null;
    }

    /**
     * 机构用户
     * @param where
     * @return
     * @throws Exception
     */
    public List<TreeNode> getDeptAndUserTree(Map<String,String> where) throws Exception {
        List<TreeNode> nodeList = this.queryDeptAndUserNodeList(where);
        List<TreeNode> treeNodeList = TreeUtil.getTree(nodeList, "id");
        return treeNodeList;
    }

    /**
     * 查询机构用户树形结构
     * @param where
     * @return
     */
    private List<TreeNode> queryDeptAndUserNodeList(Map<String,String> where){
        List<TreeNode> returnList = new ArrayList<TreeNode>();
        List<TreeNode> treeNodeList = ssoUserPlatFormMapper.selectTreeNodeList(where);

        if(treeNodeList == null) {
            return returnList;
        }
        if((StringUtil.isNotEmpty(where.get("realname")))) {
            //完整的树结构
            List<TreeNode> allTreeNodeList = ssoUserPlatFormMapper.selectTreeNodeList(new HashMap<String,String>());
            //机构
            List<TreeNode> institutionList = new ArrayList<>();
            //部门
            List<TreeNode> departmentList = new ArrayList<>();
            //用户
            List<TreeNode> userList = new ArrayList<>();

            for(TreeNode node :treeNodeList) {
                if("1".equals(node.getType())) {
                    institutionList.add(node);
                }
                else if("2".equals(node.getType())) {
                    departmentList.add(node);
                }
                else if("3".equals(node.getType())) {
                    userList.add(node);
                }
            }

            List<TreeNode> tempTreeList = new ArrayList<>();
            tempTreeList.addAll(allTreeNodeList);

            if(userList.size()>0) {
                Map<String,TreeNode> parentNodeMap = new HashMap<String,TreeNode>();
                for(TreeNode node: userList) {
                    setParentNode(tempTreeList,node,parentNodeMap);
                }
                Collection<TreeNode> mapList = parentNodeMap.values();
                List<TreeNode> userNodeList= new ArrayList<TreeNode>(mapList);

                returnList.addAll(userNodeList);
                returnList.addAll(userList);
            }

            if(departmentList.size()>0) {
                //子节点
                Map<String,TreeNode> childrenNodeMap = new HashMap<String,TreeNode>();
                for(TreeNode node: departmentList) {
                    setChildrenNode(tempTreeList,node,childrenNodeMap);
                }
                Collection<TreeNode> mapList = childrenNodeMap.values();
                List<TreeNode> childrenNodeList= new ArrayList<TreeNode>(mapList);

                //母节点
                tempTreeList.clear();
                tempTreeList.addAll(allTreeNodeList);
                Map<String,TreeNode> parentNodeMap = new HashMap<String,TreeNode>();
                for(TreeNode node: departmentList) {
                    setParentNode(tempTreeList,node,parentNodeMap);
                }
                mapList  = parentNodeMap.values();
                List<TreeNode> parentNodeList= new ArrayList<TreeNode>(mapList);

                returnList.addAll(childrenNodeList);
                returnList.addAll(departmentList);
                returnList.addAll(parentNodeList);
            }

            if(institutionList.size()>0) {
                Map<String,TreeNode> childrenNodeMap = new HashMap<String,TreeNode>();
                for (TreeNode node: institutionList) {
                    setParentNode(tempTreeList,node,childrenNodeMap);
                }
                Collection<TreeNode> mapList = childrenNodeMap.values();
                List<TreeNode> childrenNodeList= new ArrayList<TreeNode>(mapList);

                returnList.addAll(childrenNodeList);
                returnList.addAll(institutionList);
            }

        }
        else {
            returnList.addAll(treeNodeList);
        }

        return this.removeDupliById(returnList);
    }

    /**
     * 新增 用户授权平台信息
     * @param grantAuthorizationDto
     * @return
     */
    public Message<String> grantAuthorizationForUser(GrantAuthorizationDto grantAuthorizationDto) {
        if(StringUtils.isEmpty(grantAuthorizationDto.getUserid())) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_USERID_ERROR);
        }

        if(null!=grantAuthorizationDto.getPlatformids()&&grantAuthorizationDto.getPlatformids().size()==0) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_ID_ERROR);
        }

        //先删除  再新增
        ssoUserPlatFormMapper.deleteSsoUserPlatFormByUserId(grantAuthorizationDto.getUserid());
        grantAuthorizationDto.getPlatformids().forEach(item -> {
            SsoUserPlatForm ssoUserPlatForm = new SsoUserPlatForm();
            ssoUserPlatForm.setUserid(grantAuthorizationDto.getUserid());
            ssoUserPlatForm.setPlatformid(item);
            ssoUserPlatForm.setMduser(null);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ssoUserPlatForm.setMdtime(sdf.format(new Date()));
            ssoUserPlatFormMapper.insertSsoUserPlatForm(ssoUserPlatForm);
        });

        return new Message<String>().success();
    }

    public Message<String> grantAuthorizationForPlatForm(GrantAuthorizationDto grantAuthorizationDto) {
        if(StringUtils.isEmpty(grantAuthorizationDto.getPlatformid())) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_ID_ERROR);
        }

        if(null!=grantAuthorizationDto.getUserids()&&grantAuthorizationDto.getUserids().size()==0) {
            return new Message<String>(MessageCode.FAIL_PLATFORM_USERID_ERROR);
        }

        //先删除  再新增
        ssoUserPlatFormMapper.deleteSsoUserPlatFormByPlatformid(grantAuthorizationDto.getPlatformid());
        grantAuthorizationDto.getUserids().forEach(item -> {
            SsoUserPlatForm ssoUserPlatForm = new SsoUserPlatForm();
            ssoUserPlatForm.setUserid(item);
            ssoUserPlatForm.setPlatformid(grantAuthorizationDto.getPlatformid());
            ssoUserPlatForm.setMduser(null);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ssoUserPlatForm.setMdtime(sdf.format(new Date()));
            ssoUserPlatFormMapper.insertSsoUserPlatForm(ssoUserPlatForm);
        });

        return new Message<String>().success();
    }

    public int deleteSsoUserPlatFormByPlatformid(String platformid){
        return ssoUserPlatFormMapper.deleteSsoUserPlatFormByPlatformid(platformid);
    }

    public List<String> selectSsoUserListPlatFormById(String platformid){
        return ssoUserPlatFormMapper.selectSsoUserListPlatFormById(platformid);
    }
}
