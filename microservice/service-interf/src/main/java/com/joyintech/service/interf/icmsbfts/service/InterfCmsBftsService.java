/*
 * 文件名：InterfCmsBftsService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：杨静敏
 * 修改时间：2018年11月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.icmsbfts.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.service.interf.core.service.InterfBaseService;
import com.joyintech.service.interf.core.util.InterfUtil;
import com.joyintech.service.interf.icmsbfts.dao.InterfCmsBftsMapper;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tools.util.FileUtil;

/**
 * 每日上传CMS信贷资产余额信息到bfts服务器
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月29日
 * @see InterfCmsBftsService
 * @since 1.0
 */
@Service
public class InterfCmsBftsService extends InterfBaseService {

    /**
     * CMS信贷资产余额信息mapper
     */
    @Autowired
    private InterfCmsBftsMapper interfCmsbftsMapper;

    /**
     * ods文件路径KEY
     */
    private static final String ODS_FILE_PATH = "odsFilePath";

    /**
     * 数据文件扩展名
     */
    private static final String FILE_EXT = "fileExt";

    /**
     * 功能入口：获取数据生成文件
     * 
     * @return 生成文件路径和文件名
     * @see
     * @since 1.0
     */
    public String execute() {
        String date = getSysDate();
        // 从数据库取值
        Map<String, Object> map = new HashMap<>();
        // 设置参数
        map.put("sysDate", getSysDate());
        map.put("prodType2_F01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        map.put("prodType2_F03", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        map.put("prodType2_F02", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        map.put("cashType2_C01", DictCodeListExt1.现金流二级类型_本金.getValue());
        List<String> list = interfCmsbftsMapper.selectCmsBftsList(map);

        // 文件存放位置
        Map<String, Object> interfCache = getSysParamMap();
        String filePath = (String)interfCache.get(ODS_FILE_PATH);
        // 规范文件路径
        filePath = InterfUtil.pathEndAsSeparator(filePath);
        filePath = filePath + "cmsfile/" + date;

        // 文件名 PD_CMS_TCMS_BALANCE_YYYYMMDD.txt
        String name = "/PD_CMS_TCMS_BALANCE_" + date + "." + (String)interfCache.get(FILE_EXT);
        // 生成文件路径
        String cmsFilePath = filePath + name;
        FileUtil.writeFile(filePath, name, list, "utf-8");
        return cmsFilePath;
    }

}
