package com.joyintech.service.product.riskLevelRating.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.prdrisklevel.service.PrdRiskLevelService;
import com.joyintech.service.product.riskLevelRating.dao.RiskLevelRatingMapper;
import com.joyintech.service.product.riskLevelRating.dto.RiskLevelRating001Dto;
import com.joyintech.service.product.riskLevelRating.dto.RiskLevelRating002Dto;
import com.joyintech.tams.constant.DictCodeList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @program: RiskLevelRatingService
 * @description: 产品风险等级评定
 * @author: jyl
 * @create: 2020-01-02
 **/
@Service(value = "riskLevelRatingService")
@Transactional(rollbackFor = Exception.class)
public class RiskLevelRatingService extends CommonExportService {
    @Autowired
    private RiskLevelRatingMapper riskLevelRatingMapper;
    @Autowired
    private PrdRiskLevelService prdRiskLevelService;

    /**
     * 产品风险等级评定信息查询
     *
     * @param page
     * @param length
     * @param pageParam
     * @return
     * @throws Exception
     */
    public Page<RiskLevelRating001Dto> findPage(int page, int length,
                                                Map<String, Object> pageParam)
            throws Exception {
        pageParam.put("isLast", DictCodeList.是否_是.getValue());

        return this.queryForPage(page, length,
                () -> riskLevelRatingMapper.findPage(pageParam));
    }

    /**
     * 产品代码下拉框信息查询
     *
     * @param page
     * @param length
     * @param pageParam
     * @return Page
     */
    public Page<SelectOptionDto> findId(int page, int length,
                                        Map<String, Object> pageParam)
            throws Exception {


        return this.queryForPage(page, length,
                () -> riskLevelRatingMapper.findId(pageParam));
    }

    /**
     * 资产评定列表
     *
     * @param pageParam
     * @return RiskLevelRating002Dto
     */
    public RiskLevelRating002Dto findList(Map<String, Object> pageParam) {
        List<RiskLevelRating002Dto> dtoList = riskLevelRatingMapper.findList(pageParam);
        RiskLevelRating002Dto dto = new RiskLevelRating002Dto();
        String seqNo= (String) pageParam.get("seqNo");
        if (seqNo!=null&&seqNo!=""){
            List<RiskLevelRating002Dto> riskList=riskLevelRatingMapper.selectAjustRisk(seqNo);
            for (RiskLevelRating002Dto d:dtoList) {
                for (RiskLevelRating002Dto r:riskList){
                    if (StringUtil.equals(d.getFinProductCode(),r.getFinProductCode())) {
                        d.setAjustRiskLevel(r.getAjustRiskLevel());
                    }
                }
            }
        }
        //调用加权平均计算风险等级方法获取产品风险等级
        dto.setRiskList(dtoList);
        BigDecimal risk=new BigDecimal("0");
        for (int i=0;i<dtoList.size();i++){
            risk= ArithmeticUtil.doAdd(risk,ArithmeticUtil.multiply
                    (new BigDecimal(dtoList.get(i).getAssetRiskType()==null?"0":dtoList.get(i).getAssetRiskType()),dtoList.get(i).getCrn()));
        }
        String riskRating=PrdRiskLevelService.riskRating(risk);
        if (!StringUtil.isNotEmpty(riskRating)){
            riskRating="01";
        }
        dto.setRiskLevel(riskRating);
        return dto;
    }

    /**
     * 校验单产品在某一天是否存在风险等级评价信息
     *
     * @param param
     * @return int
     */
    public int onlyOne(Map<String, Object> param) {
       Date date=new Date( (Long) param.get("generateTime"));
        param.put("generateTime",date);
        return riskLevelRatingMapper.onlyOne(param);
    }

    /**
     * 向产品风险等级评定表中插入数据
     *
     * @param dto
     */
    public void insertRisk(RiskLevelRating001Dto dto) {
        riskLevelRatingMapper.insertRisk(dto);
    }

    /**
     * 向产品资产风险等级评定表中插入数据
     *
     * @param dto
     */
    public void insertPrdRiskLevel(RiskLevelRating001Dto dto) {
        dto.setRiskLevelNo(IDGenerator.generate());
        riskLevelRatingMapper.insertPrdRiskLevel(dto);
    }

    /**
     * 修改产品风险等级评定表
     *
     * @param dto
     */
    public void updateRiskLevel(RiskLevelRating001Dto dto) {
        riskLevelRatingMapper.updateRiskLevel(dto);
    }

    /**
     * 修改产品资产风险等级评定表
     *
     * @param dto
     */
    public void updatePrdRiskLevel(RiskLevelRating001Dto dto) {
        riskLevelRatingMapper.updatePrdRiskLevel(dto);
    }

    /**
     * 删除产品风险等级评定表
     *
     * @param seqNo
     */
    public void deleteRiskLevel(String seqNo) {
        riskLevelRatingMapper.deleteRiskLevel(seqNo);
    }

    /**
     * 删除产品资产风险等级评定表
     *
     * @param seqNo
     */
    public void deletePrdRiskLevel(String seqNo) {
        riskLevelRatingMapper.deletePrdRiskLevel(seqNo);
    }

    /**
     * 获取流水号
     *
     * @param param
     * @return String
     */
    public String selectNo(Map<String, Object> param) {
        return riskLevelRatingMapper.selectNo(param);
    }

    /**
     * 获取调整后资产风险等级列表
     *
     * @param seqNo
     * @return List
     */
    public List<RiskLevelRating002Dto> selectAjustRisk(String seqNo){
        return riskLevelRatingMapper.selectAjustRisk(seqNo);
    }
    /**
     * 点击产品风险等级评定，产品风险等级=∑（调整后）资产风险等级*每个资产的集中度。
     *
     * @param dt
     * @return String
     */
    public String prodRisk(RiskLevelRating002Dto dt){
        List<RiskLevelRating002Dto> dto=dt.getRiskList();
        BigDecimal risk=new BigDecimal("0");
        for (int i=0;i<dto.size();i++){
         risk= ArithmeticUtil.doAdd(risk,ArithmeticUtil.multiply
                 (new BigDecimal(dto.get(i).getAjustRiskLevel()==null?"0":dto.get(i).getAjustRiskLevel()),dto.get(i).getCrn()));
        }
        String riskRating=PrdRiskLevelService.riskRating(risk);
        return riskRating;
    }
}
