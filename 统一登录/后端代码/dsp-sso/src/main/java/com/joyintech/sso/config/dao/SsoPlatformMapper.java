package com.joyintech.sso.config.dao;

import com.joyintech.sso.config.dto.SsoPlatformDto;
import com.joyintech.sso.config.pojo.SsoPlatform;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface SsoPlatformMapper {

    /**
     * 根据id查询平台管理信息
     * @param id
     * @return
     */
    public SsoPlatform selectSsoPlatfromById(@Param("id") String id);

    /**
     * 查询 平台管理信息 list
     * @return
     */
    public List<SsoPlatform> listSsoPlatform(SsoPlatformDto ssoPlatformDto);

    /**
     * 新增 平台管理信息
     * @param ssoPlatform
     * @return
     */
    @Insert("INSERT INTO SSO_PLATFORM (ID ,PLATFORM_NAME,PLATFORM_CODE, PLATFORM_URL, WEIGHT, MDTIME, MDUSER, ICON, COOKIE_KEY, REMARK1, REMARK2,SYNC_FLAG,PLATFORM_HOST,DESCRIBE) "
            + "VALUES (#{id},#{platformname},#{platformcode},#{platformIndex},#{weight},#{mdtime},#{mduser},#{icon},#{cookiekey},#{remark1},#{remark2},#{syncFlag},#{platformHost},#{describe})")
    public int insertSsoPlatform(SsoPlatform ssoPlatform);

    /**
     * 删除 平台管理信息
     * @param id
     * @return
     */
    @Delete("DELETE FROM SSO_PLATFORM WHERE ID = #{id}")
    public int deleteSsoPlatformById(@Param("id") String id);

    /**
     * 更新 平台管理信息
     * @param ssoPlatform
     * @return
     */
    public int updateSsoPlatform(SsoPlatform ssoPlatform);

    @Update("update SSO_PLATFORM set STATUS = #{status} WHERE ID = #{id}")
    public void updateSsoPlatformById(@Param("id") String id,@Param("status") int status);

}
