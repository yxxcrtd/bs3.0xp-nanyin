/*
 * 文件名：AccountsAdjustAccountEntry001Service.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All
 * Rights Reserved. 描述：中信银行资管系统 修改人：qiangchengxi 修改时间：2017年1月13日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.service.valuation.accounadjustmanage.accountentry.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.system.user.dto.User;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dao.ValAccountsAdjustAccountEntry001Mapper;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.definition.ValAccountEntryCodeValue;
import com.joyintech.service.valuation.accounadjustmanage.accountentry.dto.ValAccountsAdjustAccountEntry001Dto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.valuation.common.constants.GlobalConstant;

/**
*
* 文件名：AccountsAdjustAccountEntry001Service.java
* 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
* 描述：估值核算系统V1.0
* 修改人：wujie
* 修改时间：2018年5月14日
* 修改内容：会计分录管理用service
* 系统名称：估值核算系统V1.0
*/
@Service(value="serviceValAccountsAdjustAccountEntry001Service")
@Transactional(rollbackFor = Exception.class)
public class ValAccountsAdjustAccountEntry001Service extends CommonExportService<ValAccountsAdjustAccountEntry001Dto> {

    /**
     * AccountsAdjustAccountEntry001Mapper
     */
    @Autowired
    private ValAccountsAdjustAccountEntry001Mapper valAccountsAdjustAccountEntry001Mapper;

    
    
    /**
     * 查询所有账套 <br/>
     * 用于所属账套控件初期化
     * 
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findBooksets() {
        return valAccountsAdjustAccountEntry001Mapper.findBooksets();
    }
    
    
    /**
     * 查询所有会计场景 <br/>
     * 用于场景模块控件初期化
     * 
     * @return SelectOptionDto list
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> findTableIds(Map<String, Object> param ) {
        return valAccountsAdjustAccountEntry001Mapper.findTableIds(param);
    }
    
    /**
     * 检索数据
     * 
     * @param booksetId 账套代码
     * @param nodeId 会计场景代码
     * @param level 级别
     * @return AccountSetDto list
     */
    public Page<ValAccountsAdjustAccountEntry001Dto> findAccountEntry(String booksetId, String nodeId,
                                                                   Integer level,String bookEvent) {
        List<ValAccountsAdjustAccountEntry001Dto> dtoList = valAccountsAdjustAccountEntry001Mapper.findAccountEntry(
            booksetId, nodeId, bookEvent,
            DictCodeListExt1.数据类型_下拉框.getValue(), level , GlobalConstant.ACCOUNTEVENTS);
        return super.queryForTree(()->dtoList,
            ValAccountsAdjustAccountEntry001Dto::getSceneNodeId,
            ValAccountsAdjustAccountEntry001Dto::getFsceneNodeid,
            ValAccountsAdjustAccountEntry001Dto::getSceneNodeId, (item)->true);
//        if (dtoList != null) {
//            Map<String, List<AccountsAdjustAccountEntry001Dto>> everyFAndS = new HashMap<>();
//            for (AccountsAdjustAccountEntry001Dto dto : dtoList) {
//                List<AccountsAdjustAccountEntry001Dto> sNode = everyFAndS.get(
//                    dto.getSceneNodeId());
//                List<AccountsAdjustAccountEntry001Dto> fNode = everyFAndS.get(
//                    dto.getFsceneNodeid());
//                if (sNode == null) {
//                    sNode = new ArrayList<>();
//                    everyFAndS.put(dto.getSceneNodeId(), sNode);
//                }
//
//                if (fNode == null) {
//                    sNode = new ArrayList<>();
//                    everyFAndS.put(dto.getFsceneNodeid(), sNode);
//                }
//                else {
//                    fNode.add(dto);
//                }
//            }
//
//            for (int i = 0; i < dtoList.size(); i++ ) {
//                List<AccountsAdjustAccountEntry001Dto> fs = everyFAndS.get(
//                    dtoList.get(i).getSceneNodeId());
//                if (fs != null) {
//                    for (AccountsAdjustAccountEntry001Dto dto : fs) {
//                        dto.setParent(i+1);
//                    }
//                    dtoList.get(i).setIndex(i+1);
//                }
//            }
//        }
//        return dtoList;
    }
    
    
    /**
     * 删除场景配置信息及其相关数据
     * 
     * @param dto 行数据
     * @see
     * @since 1.0
     */
    public void deleteEntry(ValAccountsAdjustAccountEntry001Dto dto) {
        //场景分支配置表
//        Date updateTime = new Date();
        valAccountsAdjustAccountEntry001Mapper.deleteConfig(dto.getSceneNodeId());

        //删除场景科目关系信息
//        accountsAdjustAccountEntry001Mapper.updateSceneSubjectToD(dto.getSceneNodeId(),
//            getUserFromSession().getUid(), updateTime);
    }
    /**
     * 从session中取出用户
     *
     * @return SysUser
     */
    public User getUserFromSession() {
        Session session = SecurityUtils.getSubject().getSession(false);
        User user = (User)session.getAttribute(GlobalConstant.CURRENT_USER);
        return user;
    }
    
    /**
     * 查询更新时间
     * 
     * @param sceneNodeId 场景分支节点
     * @return 更新时间
     * @see
     * @since 1.0
     */
    public Date findAccountEntryByNodeId(String sceneNodeId) {
        return valAccountsAdjustAccountEntry001Mapper.findAccountEntryByNodeId(sceneNodeId);
    }
}
