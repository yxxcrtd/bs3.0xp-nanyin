package com.joyintech.sso.config.dao;

import com.joyintech.sso.common.dto.TreeNode;
import com.joyintech.sso.config.pojo.SsoUserPlatForm;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface SsoUserPlatFormMapper {

    /**
     * 根据id查询 用户授权平台信息
     * @param userid
     * @param platformid
     * @return
     */
    @Select("SELECT * FROM SSO_USER_PLATFORM WHERE USER_ID= #{userid} AND PLATFORM_ID = #{platformid}")
    public SsoUserPlatForm selectSsoUserPlatFormById(@Param("userid") String userid, @Param("platformid") String platformid);

    /**
     * 查询 用户授权平台信息 list
     * @return
     */
    public List<SsoUserPlatForm> listSsoUserPlatForm(Map<String,Object> where);

    /**
     * 新增 用户授权平台信息
     * @param ssoUserPlatForm
     * @return
     */
    @Insert("INSERT INTO SSO_USER_PLATFORM (USER_ID, PLATFORM_ID, MDTIME, MDUSER, REMARK1, REMARK2) "
            + "VALUES (#{userid},#{platformid},#{mdtime},#{mduser},#{remark1},#{remark2})")
    public int insertSsoUserPlatForm(SsoUserPlatForm ssoUserPlatForm);

    /**
     * 删除 用户授权平台信息
     * @param userid
     * @param platformid
     * @return
     */
    @Delete("DELETE FROM SSO_USER_PLATFORM WHERE USER_ID= #{userid} AND PLATFORM_ID = #{platformid}")
    public int deleteSsoUserPlatFormById(@Param("userid") String userid,@Param("platformid") String platformid);

    /**
     * 根据用户ids 批量删除 用户授权平台信息
     * @param map
     * @return
     */
    public int  deleteSsoUserPlatFormByPlatformids(Map<String,Object> map);
    /**
     * 更新 用户授权平台信息
     * @param ssoUserPlatForm
     * @return
     */
    public int updateSsoUserPlatForm(SsoUserPlatForm ssoUserPlatForm);

    /**
     * 查询整个树结构
     */
    public List<TreeNode> selectTreeNodeList(Map<String,String> map);

    @Delete("DELETE FROM SSO_USER_PLATFORM WHERE USER_ID= #{userid}")
    public int deleteSsoUserPlatFormByUserId(@Param("userid") String userid);


    @Delete("DELETE FROM SSO_USER_PLATFORM WHERE PLATFORM_ID = #{platformid}")
    public int deleteSsoUserPlatFormByPlatformid(@Param("platformid") String platformid);


    @Select("SELECT USER_ID FROM SSO_USER_PLATFORM WHERE PLATFORM_ID = #{platformid}")
    public List<String> selectSsoUserListPlatFormById(@Param("platformid") String platformid);
}
