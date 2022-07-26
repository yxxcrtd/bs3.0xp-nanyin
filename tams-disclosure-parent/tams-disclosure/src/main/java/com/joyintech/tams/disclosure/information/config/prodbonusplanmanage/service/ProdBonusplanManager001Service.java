/*
 * 文件名：ValTableGenerate001Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dao.ProdBonusplanMapper;
import com.joyintech.tams.disclosure.information.config.prodbonusplanmanage.dto.ProdBonusplanDto;
import com.joyintech.tams.systemtools.util.ExcelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 记账凭证查询service
 *
 * @author ruanhongming
 * @version 2.0 2018年5月3日
 * @see ProdBonusplanManager001Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ProdBonusplanManager001Service extends CommonExportService<ProdBonusplanDto> /*implements BaseProcessService<ProdBonusplanDto>*/ {

    @Autowired
    private ProdBonusplanMapper prodBonusplanMapper;

    @Autowired
    private ProdBonusplanManager002Service prodBonusplanManager002Service;


    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(ProdBonusplanManager001Service.class);

    private Map<String, List<String>> propertiesMap = new HashMap<>();


    public List<ProdBonusplanDto> searchReportsNew(Map<String, Object> params) {
        List<ProdBonusplanDto> result = prodBonusplanMapper.searchReports(params);
        // 遍历构造数据
        if (CollectionUtils.isNotEmpty(result)) {
            result.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态, item.getDataStatus()));
                item.setBonusModeName(SysDataUtil.getNameFromDataDict(DictCode.信披_分红方式, item.getBonusMode()));
            });
        }
        return result;
    }

    /**
     * 指标库查询service <一句话功能简述> <功能详细描述>
     *
     * @param pageParam
     * @return
     */
    /**
     * 指标库查询service <一句话功能简述> <功能详细描述>
     *
     * @param params
     * @return
     */
    public Page<ProdBonusplanDto> findByPage(Map<String, Object> params) {
        String dataStatus = (String) params.get("dataStatus");
        String[] dataStatusArr = null;
        if (StringUtil.isNotEmpty(dataStatus)) {
            dataStatusArr = dataStatus.split(",");
        }
        params.put("dataStatusArr", dataStatusArr);

        String bonusMode = (String) params.get("bonusMode");
        String[] bonusModeArr = null;
        if (StringUtil.isNotEmpty(bonusMode)) {
            bonusModeArr = bonusMode.split(",");
        }
        params.put("bonusModeArr", bonusModeArr);

        String prodId = (String) params.get("prodId");
        String[] prodIdArr = null;
        if (StringUtil.isNotEmpty(prodId)) {
            prodIdArr = prodId.split(",");
        }
        params.put("prodIdArr", prodIdArr);
        Page<ProdBonusplanDto> pages = super.queryForPageForSelectReturn(
                Integer.parseInt((String) params.get("page")),
                Integer.parseInt((String) params.get("length")),
                () -> prodBonusplanMapper.searchReports(params));
        List<ProdBonusplanDto> list = pages.getRoot();
        // 遍历构造数据
        if (CollectionUtils.isNotEmpty(list)) {
            list.stream().forEach(item -> {
                item.setDataStatusName(SysDataUtil.getNameFromDataDict(DictCode.信披_数据状态, item.getDataStatus()));
                item.setBonusModeName(SysDataUtil.getNameFromDataDict(DictCode.信披_分红方式, item.getBonusMode()));

            });
            pages.setRoot(list);
        }
        return pages;
    }


    /**
     * todo
     *
     * @param prodId
     * @return
     */
    public ProdBonusplanDto selectByProdId(String prodId) {
        return prodBonusplanMapper.selectByProdId(prodId, DictCodeListExt3.信披_数据状态_有效.getValue());
    }


    /**
     * 导入check
     *
     * @param fileName
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
//        List<String> findList =  this.queryFinprod(Maps.newHashMap()).stream().map(item->item.getId()).collect(Collectors.toList());
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        if (objects.size() - 1 > 50000) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "单次最多导入50000条。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }
        String totalMsg = "";
        List<List<String>> jsonArrays = new ArrayList<>();
        // 重复数据
        int repeatNum = 0;
        if (objects.size() <= 1) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", "数据格式错误，内容需要大于1行，模板是有表头的。");
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        } else {
            if (null == objects.get(0) || objects.get(0).size() != 8) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("errorMsg", "数据格式错误，数据格式需要8列。");
                AjaxResult wr = new AjaxResult();
                wr.setData(resultMap);
                return wr;
            } else {
                StringBuilder returnerrorMsg = new StringBuilder("");
                for (int i = 1; i < objects.size(); i++) {
                    StringBuilder errorMsg = new StringBuilder("");
                    List<String> rowData = objects.get(i);

                    if (!StringUtil.isNotEmpty(rowData.get(0))) {
                        errorMsg.append("子产品代码 必填！");
                    }else{
                        //不存在 校验 TODO


                    }
                    if (!StringUtil.isNotEmpty(rowData.get(1))) {
                        errorMsg.append("分红方式 必填！");
                    } else if (!StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getCodeName(), rowData.get(1))
                            && !StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getCodeName(), rowData.get(1))
                            && !StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue(), rowData.get(1))
                            && !StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getValue(), rowData.get(1))) {
                        errorMsg.append("分红方式值 不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(2))) {
                        errorMsg.append("收益分配基准日 必填！");
                    } else if (!this.isDateStr(rowData.get(2))) {
                        errorMsg.append("收益分配基准日 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(3))) {
                        errorMsg.append("现金红利发放日 必填！");
                    } else if (!this.isDateStr(rowData.get(3))) {
                        errorMsg.append("现金红利发放日 格式不正确！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(4))) {
                        errorMsg.append("业绩报酬 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(5))) {
                        errorMsg.append("超额业绩报酬收取比例 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(6))) {
                        errorMsg.append("业绩比较基准 必填！");
                    }
                    if (!StringUtil.isNotEmpty(rowData.get(7))) {
                        errorMsg.append("业绩比较基准比例 必填！");
                    }

                    if (StringUtil.isNotEmpty(errorMsg.toString())) {
                        errorMsg.append("<br />");
                        returnerrorMsg.append("第" + (i + 1) + "行数据：" + errorMsg.toString());
                    }
                }
                if (StringUtil.isNotEmpty(returnerrorMsg.toString())) {
                    Map<String, Object> resultMap = new HashMap<>();
                    resultMap.put("errorMsg", returnerrorMsg.toString());
                    AjaxResult wr = new AjaxResult();
                    wr.setData(resultMap);
                    return wr;
                }
                //第一行【i=0】为表头，所以不操作。
                for (int i = objects.size() - 1; i >= 1; i--) {
                    List<String> list1 = objects.get(i);
                    Boolean isRepeat = false;
                    for (int k = i - 1; k >= 1; k--) {
                        List<String> list2 = objects.get(k);
                        //子产品代码，相同时为重复数据
                        if ((StringUtil.equals(list1.get(0), list2.get(0)))) {
                            isRepeat = true;
                            break;
                        }
                    }
                    if (isRepeat) {
                        repeatNum++;
                    } else {
                        jsonArrays.add(list1);
                    }
                }
            }
        }
        if (repeatNum > 0) {
            totalMsg += "导入文件存在" + String.valueOf(repeatNum) + "条数据重复，重复数据不导入，是否确认？";
        }
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg);
        }
        resultMap.put("correctData", JsonUtils.object2Json(jsonArrays));
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }

    public static boolean isDateStr(String str) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);
            format.parse(str);
            return true;
        } catch (Exception var3) {
            return false;
        }
    }


    public AjaxResult importFile(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        String tempList = (String) param.get("tempList");
        ProdBonusplanDto dto = getValNetAssetQuotationInfoListData(tempList);
        List<ProdBonusplanDto> selectedItem = dto.getSelectedItem();
        List<ProdBonusplanDto> existData = new ArrayList<>();
        List<ProdBonusplanDto> newData = new ArrayList<>();
        for (ProdBonusplanDto temp : selectedItem) {
            //数据已存在 【产品编码 相同】，做更新操作。
            if (StringUtil.isNotEmpty(this.selectByProdId(temp.getProdId()))) {
                existData.add(temp);
            } else {
                temp.setDataStatus(DictCodeListExt3.信披_数据状态_有效.getValue());
                temp.setDataSource(DictCodeListExt3.信披_数据来源_EXCEL导入.getValue());
                newData.add(temp);
            }
        }
        //更新操作
        for (ProdBonusplanDto existDataDto : existData) {
            //this.doComplete(existDataDto, OperationType.MODIFY);
            prodBonusplanManager002Service.update(existDataDto);
        }
        //插入操作
        for (ProdBonusplanDto newDataDto : newData
        ) {
            // this.doComplete(newDataDto, OperationType.NEW);
            prodBonusplanManager002Service.insert(newDataDto);
        }
        ajaxResult.setStatuCode(HttpStatus.OK.value());
        return ajaxResult;
    }


    public ProdBonusplanDto getValNetAssetQuotationInfoListData(String tempList) {
        ProdBonusplanDto dto = new ProdBonusplanDto();
        // 行情信息列表
        List<ProdBonusplanDto> prodBonusplanDtoList = new ArrayList<>();
       // dto.setSelectedItem(prodBonusplanDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);

        for (int i = 0; i < objects.size(); i++) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray) objects.get(i)).toJSONString());
            ProdBonusplanDto prodBonusplanDto = new ProdBonusplanDto();
			/*if (StringUtil.isNotEmpty(rowData.get(0))) {
				prodBonusplanDto.setDataStatus(rowData.get(0).toString());
			}*/
            if (StringUtil.isNotEmpty(rowData.get(0))) {
                prodBonusplanDto.setProdId(rowData.get(0).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                if (StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getCodeName(), rowData.get(1).toString())
                        || StringUtils.equals(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue(), rowData.get(1).toString())) {
                    prodBonusplanDto.setBonusMode(DictCodeListExt3.信披_分红方式_份额分红后净值为1.getValue());
                } else if (StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getCodeName(), rowData.get(1).toString())
                        || StringUtils.equals(DictCodeListExt3.信披_分红方式_到期兑付.getValue(), rowData.get(1).toString())) {
                    prodBonusplanDto.setBonusMode(DictCodeListExt3.信披_分红方式_到期兑付.getValue());
                }
            }
            if (StringUtil.isNotEmpty(rowData.get(2))) {
                prodBonusplanDto.setSettDate(DateUtil.parse(rowData.get(2).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(3))) {
                prodBonusplanDto.setPaymentDate(DateUtil.parse(rowData.get(3).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(4))) {
                prodBonusplanDto.setAchievement(rowData.get(4).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(5))) {
                prodBonusplanDto.setExcessAchievementProp(new BigDecimal(rowData.get(5).toString()));
            }
            if (StringUtil.isNotEmpty(rowData.get(6))) {
                prodBonusplanDto.setAchievementBasis(rowData.get(6).toString());
            }
            if (StringUtil.isNotEmpty(rowData.get(7))) {
                prodBonusplanDto.setAchievementBasisProp(new BigDecimal(rowData.get(7).toString()));
            }

            prodBonusplanDtoList.add(prodBonusplanDto);
        }
        dto.setSelectedItem(prodBonusplanDtoList);
        return dto;
    }

}
