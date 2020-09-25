package com.joyintech.service.product.riskLevelRating.controller;


import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.riskLevelRating.dto.RiskLevelRating001Dto;
import com.joyintech.service.product.riskLevelRating.dto.RiskLevelRating002Dto;
import com.joyintech.service.product.riskLevelRating.service.RiskLevelRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: RiskLevelRatingController
 * @description: 产品风险等级评定
 * @author: jyl
 * @create: 2020-01-02
 **/

@RestController("backRiskLevelRating_dtx")
@RequestMapping("/RiskLevelRating")
public class RiskLevelRatingController {
    @Autowired
    private RiskLevelRatingService riskLevelRatingService;

    /**
     * 产品风险等级评定信息查询
     *
     * @param page
     * @param length
     * @param pageParam
     * @return Page
     * @throws Exception
     */
    @RequestMapping("findPage/{page}/{length}")
    public Page<RiskLevelRating001Dto> findPage(@RequestBody Map<String, Object> paramMap,
                                                @PathVariable("page") int page,
                                                @PathVariable("length") int length) throws Exception {
        return riskLevelRatingService.findPage(page, length, paramMap);
    }

    /**
     * 产品代码下拉框信息查询
     *
     * @param paramMap
     * @param length
     * @param page
     * @return Page
     * @throws Exception
     */
    @RequestMapping("findId/{page}/{length}")
    public Page<SelectOptionDto> findId(@RequestBody Map<String, Object> paramMap,
                                        @PathVariable("page") int page,
                                        @PathVariable("length") int length) throws Exception {
        return riskLevelRatingService.findId(page, length, paramMap);
    }

    /**
     * 资产评定列表
     *
     * @param paramMap
     * @return RiskLevelRating002Dto
     * @throws Exception
     */
    @RequestMapping("findList")
    public RiskLevelRating002Dto findList(@RequestBody Map<String, Object> paramMap
    ) throws Exception {
        return riskLevelRatingService.findList(paramMap);
    }

    /**
     * 校验单产品在某一天是否存在风险等级评价信息
     *
     * @param param
     * @return int
     */
    @RequestMapping("onlyOne")
    public int onlyOne(@RequestBody Map<String, Object> param) {
        return riskLevelRatingService.onlyOne(param);
    }

    /**
     * 向产品风险等级评定表中插入数据
     *
     * @param dto
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("insertRisk")
    public void insertRisk(@RequestBody RiskLevelRating001Dto dto) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat sdf1= new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd");
        String d1=sdf1.format(new Date());
        String d2=sdf2.format(dto.getGenerateTime());
        String d3=d2+" "+d1;
        dto.setGenerateTime(sdf.parse(d3));
        riskLevelRatingService.insertRisk(dto);
        if (StringUtil.isNotEmpty(dto.getSelectIem()) && dto.getSelectIem().size() > 0) {
            for (RiskLevelRating002Dto d : dto.getSelectIem()) {
                dto.setFinProductCode(d.getFinProductCode());
                dto.setFinprodAbbr(d.getFinprodAbbr());
                dto.setRiskLevel(d.getAssetRiskType());
                dto.setAjustRiskLevel(d.getAjustRiskLevel());
                riskLevelRatingService.insertPrdRiskLevel(dto);
            }
        }
    }

    /**
     * 向产品资产风险等级评定表中插入数据
     *
     * @param dto
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("insertPrdRiskLevel")
    public void insertPrdRiskLevel(@RequestBody RiskLevelRating001Dto dto) {

    }

    /**
     * 修改产品风险等级评定表
     *
     * @param dto
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("updateRiskLevel")
    public void updateRiskLevel(@RequestBody RiskLevelRating001Dto dto) {
        riskLevelRatingService.updateRiskLevel(dto);
        if(StringUtil.isNotEmpty(dto.getSelectIem()) && dto.getSelectIem().size()>0){
            for (RiskLevelRating002Dto d : dto.getSelectIem()) {
                dto.setFinProductCode(d.getFinProductCode());
                dto.setAjustRiskLevel(d.getAjustRiskLevel());
                riskLevelRatingService.updatePrdRiskLevel(dto);
            }
        }
    }

    /**
     * 修改产品资产风险等级评定表
     *
     * @param dto
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("updatePrdRiskLevel")
    public void updatePrdRiskLevel(@RequestBody RiskLevelRating001Dto dto) {
        riskLevelRatingService.updatePrdRiskLevel(dto);
    }

    /**
     * 删除产品风险等级评定表
     *
     * @param seqNo
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("deleteRiskLevel")
    public void deleteRiskLevel(String seqNo) {
        riskLevelRatingService.deleteRiskLevel(seqNo);
        riskLevelRatingService.deletePrdRiskLevel(seqNo);
    }

    /**
     * 删除产品资产风险等级评定表
     *
     * @param seqNo
     */
    @Transactional
    @DTXTransaction
    @RequestMapping("deletePrdRiskLevel")
    public void deletePrdRiskLevel(String seqNo) {
        riskLevelRatingService.deletePrdRiskLevel(seqNo);
    }

    /**
     * 获取流水号
     *
     * @param param
     * @return String
     */
    @RequestMapping("selectNo")
    public String selectNo(@RequestBody Map<String, Object> param) {
        return riskLevelRatingService.selectNo(param);
    }

    /**
     * 获取调整后资产风险等级列表
     *
     * @param seqNo
     * @return List
     */
    @RequestMapping("selectAjustRisk")
    public List<RiskLevelRating002Dto> selectAjustRisk(String seqNo){
        return riskLevelRatingService.selectAjustRisk(seqNo);
    }
    /**
     * 点击产品风险等级评定，产品风险等级=∑（调整后）资产风险等级*每个资产的集中度。
     *
     * @param dto
     * @return String
     */
    @RequestMapping("prodRisk")
    public String prodRisk(@RequestBody RiskLevelRating002Dto dto){
    return riskLevelRatingService.prodRisk(dto);
    }
}
