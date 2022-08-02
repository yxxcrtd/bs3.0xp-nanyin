/*
 * 文件名：ProdBonusplanManager002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.repprodinfo.service;

import com.joyintech.framework.util.IDGenerator;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.constant.DictCodeListExt5;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dao.ProdBasicChangeMapper;
import com.joyintech.tams.disclosure.information.config.repprodinfo.dto.ProdBasicChangeDto;
import com.joyintech.tams.systemtools.service.CommonPlatformDateService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 业绩基准变更
 *
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see ProdBasicChangeService
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdBasicChangeService {


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBasicChangeService.class);


    @Autowired
    private ProdBasicChangeMapper prodBasicChangeMapper;

    @Autowired
    private CommonPlatformDateService commonPlatformDateService;

    /**
     * 根据产品主代码 查询所有的业绩基准变更信息
     *
     * @param prodId
     * @return
     */
    public List<ProdBasicChangeDto> selectListByProdId(String prodId) {
        List<ProdBasicChangeDto> list = new ArrayList<>();
        List<ProdBasicChangeDto> result = prodBasicChangeMapper.selectListByProdId(prodId);
        if (CollectionUtils.isNotEmpty(result)) {
            result.forEach(i -> {
                if (StringUtils.contains(i.getProportion(), "%")) {
                    i.setProportion(i.getProportion().substring(0, i.getProportion().indexOf("%")));
                }
                list.add(i);
            });
        }

        return list;
    }


    /**
     * 新增或更新 业绩基准变更信息
     *
     * @param list
     * @return
     */
    public void updateProdBasicChangeList(List<ProdBasicChangeDto> list, String prodId, String isIndex) {
        Date platDate = commonPlatformDateService.getSysDate(); //平台日
        if (CollectionUtils.isNotEmpty(list) || StringUtils.equals(DictCodeListExt1.是或否_否.getValue(), isIndex)) {
            //根据prodId进行更新，将老数据状态改成 删除状态
            List<ProdBasicChangeDto> oldList = prodBasicChangeMapper.selectListByProdId(prodId);
            if (CollectionUtils.isNotEmpty(oldList)) {
                for (ProdBasicChangeDto i : oldList) {
                    i.setStatus(DictCodeListExt3.信披_数据状态_删除D.getValue());
                    prodBasicChangeMapper.updateByGroupId(i);
                }
            }
            //新增
            if (StringUtils.equals(DictCodeListExt1.是或否_是.getValue(), isIndex)) {
                String groupId = IDGenerator.generate(36);
                for (ProdBasicChangeDto i : list) {
                    insertProdBasicChange(i, prodId, groupId);
                }
            }
        }
    }

    /**
     * 插入数据
     *
     * @param dto
     * @param prodId
     */
    public void insertProdBasicChange(ProdBasicChangeDto dto, String prodId, String groupId) {
        Date platDate = commonPlatformDateService.getSysDate(); //平台日
        dto.setUuid(IDGenerator.generate());
        dto.setStatus(DictCodeListExt3.信披_数据状态_有效E.getValue());
        dto.setProdId(prodId);
        dto.setGroupId(groupId);
        dto.setEffDate(platDate);
        if (StringUtils.contains(dto.getProportion(), "%")) {
            dto.setProportion(dto.getProportion());
        } else {
            dto.setProportion(dto.getProportion() + "%");
        }
        prodBasicChangeMapper.insert(dto);
    }

    /**
     * 导入时，根据业绩比较基准 解析数据并保存
     *
     * @param performCompare 业绩比较基准
     * @param prodId         产品代码
     */
    public boolean insertProdBasicChangeFromExcel(String performCompare, String prodId) {
        boolean flag = false;
        List<ProdBasicChangeDto> list = analyseDate(performCompare);
        String groupId = IDGenerator.generate();
        if (CollectionUtils.isNotEmpty(list)) {
            for (ProdBasicChangeDto i : list) {
                insertProdBasicChange(i, prodId, groupId);
            }
            flag = true;
        }
        return flag;
    }

    /**
     * 将业绩基础信息 解析出来
     *
     * @param performCompare
     * @return
     */
    public List<ProdBasicChangeDto> analyseDate(String performCompare) {
        List<ProdBasicChangeDto> list = new ArrayList<>();
        String[] arr = performCompare.toLowerCase().split("\\+");
        boolean splitFlag = false ; //运算符拆分标识，false没有拆分，true已拆分

        for (int i = 0; i < arr.length; i++) {
            String[] arr2 = new String[2];
            if (arr[i].contains("×")) {
                arr2 = arr[i].split("×");
            } else if (StringUtils.contains(arr[i], "*")) {
                arr2 = arr[i].split("\\*");
            } else if (StringUtils.contains(arr[i], "x")) {
                arr2 = arr[i].split("x");
            }
            if (arr2.length > 0 && StringUtils.isNotEmpty(arr2[0]) && StringUtils.isNotEmpty(arr2[1])) {
                splitFlag = true ;
                String dataType = null;
                if (StringUtils.contains(arr2[0], "中证500")) {
                    dataType = DictCodeListExt5.行情代码_中证500指数收益率.getValue();
                } else if (StringUtils.contains(arr2[0], "中债新综合全价")) {
                    dataType = DictCodeListExt5.行情代码_中债新综合全价指数收益率.getValue();
                } else if (StringUtils.contains(arr2[0], "沪深300")) {
                    dataType = DictCodeListExt5.行情代码_沪深300指数收益率.getValue();
                } else if (StringUtils.contains(arr2[0], "中债新综合财富")) {
                    dataType = DictCodeListExt5.行情代码_中债新综合财富指数.getValue();
                }
                if (StringUtils.isNotBlank(dataType)) {
                    ProdBasicChangeDto dto = new ProdBasicChangeDto();
                    dto.setDataType(dataType);
                    dto.setProportion(arr2[1]);
                    list.add(dto);
                }
            }
        }
        if(!splitFlag ){
            String dataType = null;
            if (StringUtils.contains(arr[0], "中证500")) {
                dataType = DictCodeListExt5.行情代码_中证500指数收益率.getValue();
            } else if (StringUtils.contains(arr[0], "中债新综合全价")) {
                dataType = DictCodeListExt5.行情代码_中债新综合全价指数收益率.getValue();
            } else if (StringUtils.contains(arr[0], "沪深300")) {
                dataType = DictCodeListExt5.行情代码_沪深300指数收益率.getValue();
            } else if (StringUtils.contains(arr[0], "中债新综合财富")) {
                dataType = DictCodeListExt5.行情代码_中债新综合财富指数.getValue();
            }
            if (StringUtils.isNotBlank(dataType)) {
                ProdBasicChangeDto dto = new ProdBasicChangeDto();
                dto.setDataType(dataType);
                dto.setProportion("100%");
                list.add(dto);
            }

        }
        return list;
    }

    public static void main(String[] args) {
         String str="中证500指数涨跌幅";
        //String str="中债新综合全价指数收益率*90%+沪深300指数收益率*10%";
        String[] arr = str.toLowerCase().split("\\+");
        boolean splitFlag = false ; //运算符拆分标识，false没有拆分，true已拆分
        for (int i = 0; i < arr.length; i++) {
            String[] arr2 = new String[2];
            if (arr[i].contains("×")) {
                arr2 = arr[i].split("×");
            } else if (StringUtils.contains(arr[i], "*")) {
                arr2 = arr[i].split("\\*");
            } else if (StringUtils.contains(arr[i], "x")) {
                arr2 = arr[i].split("x");
            }
            if (arr2.length > 0 && StringUtils.isNotEmpty(arr2[0]) && StringUtils.isNotEmpty(arr2[1])) {
                splitFlag = true ;
                String dataType = null;
                if (StringUtils.contains(arr2[0], "中证500")) {
                    dataType = DictCodeListExt5.行情代码_中证500指数收益率.getValue();
                } else if (StringUtils.contains(arr2[0], "中债新综合全价")) {
                    dataType = DictCodeListExt5.行情代码_中债新综合全价指数收益率.getValue();
                } else if (StringUtils.contains(arr2[0], "沪深300")) {
                    dataType = DictCodeListExt5.行情代码_沪深300指数收益率.getValue();
                } else if (StringUtils.contains(arr2[0], "中债新综合财富")) {
                    dataType = DictCodeListExt5.行情代码_中债新综合财富指数.getValue();
                }
                if (StringUtils.isNotBlank(dataType)) {
                    System.out.println(dataType);
                    System.out.println(arr2[1]);
                }
            }

            if(!splitFlag ){
                String dataType = null;
                if (StringUtils.contains(arr[0], "中证500")) {
                    dataType = DictCodeListExt5.行情代码_中证500指数收益率.getValue();
                } else if (StringUtils.contains(arr[0], "中债新综合全价")) {
                    dataType = DictCodeListExt5.行情代码_中债新综合全价指数收益率.getValue();
                } else if (StringUtils.contains(arr[0], "沪深300")) {
                    dataType = DictCodeListExt5.行情代码_沪深300指数收益率.getValue();
                } else if (StringUtils.contains(arr[0], "中债新综合财富")) {
                    dataType = DictCodeListExt5.行情代码_中债新综合财富指数.getValue();
                }
                if (StringUtils.isNotBlank(dataType)) {
                    System.out.println(dataType);
                }

            }

        }

    }


}
