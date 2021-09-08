package com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dao.ModAssetGradeSpecialConfigDtoMapper;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto.AssetInfoDto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto.AssetSideDetialRepoDto;
import com.joyintech.service.valuation.lossmodel.assetratingallocation.specialassets.dto.ModAssetGradeSpecialConfigDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.StringUtil;

/**
 * 报送前检查service
 * 
 * @author dingzhipeng
 * @version 1.0 2017年10月11日
 * @see SpecialassetsService
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SpecialassetsService extends BaseService<ModAssetGradeSpecialConfigDto> {
    @Autowired
    private ModAssetGradeSpecialConfigDtoMapper modAssetGradeSpecialConfigDtoMapper;

    private static Map<String, String> assetTypeTwoMap = new LinkedHashMap<String, String>();
    static {
        assetTypeTwoMap.put("01", "100");
        assetTypeTwoMap.put("02", "100");
        assetTypeTwoMap.put("03", "900");
        assetTypeTwoMap.put("04", "400");
        assetTypeTwoMap.put("05", "200");
        assetTypeTwoMap.put("06", "200");
        assetTypeTwoMap.put("07", "200");
        assetTypeTwoMap.put("08", "900");
        assetTypeTwoMap.put("09", "300");
        assetTypeTwoMap.put("14", "600");
        assetTypeTwoMap.put("15", "700");
        assetTypeTwoMap.put("16", "800");
        assetTypeTwoMap.put("18", "500");

    }

    public String check(Map<String, Object> param){
    	String finprodId=param.get("finprodId").toString();
    	int count=modAssetGradeSpecialConfigDtoMapper.checkIfHashData(finprodId);
    	if(count>0){
    		return "Y";
    	}
    	return "N";
    }
    public Page<ModAssetGradeSpecialConfigDto> selectForPage(int page, int length, Map<String, Object> param)
            throws Exception {
    	String finprodNames = (String)param.get("finprodIds");
        param.put("finprodIds", StringUtil.splitToList(GlobalConstant.COMMA, finprodNames));
        return super.queryForPage(page, length, () -> modAssetGradeSpecialConfigDtoMapper.selectForPage(param));
    }
    public List<ModAssetGradeSpecialConfigDto> selectForExport(Map<String, Object> param)
            throws Exception {
    	String finprodNames = (String)param.get("finprodNames");
        param.put("finprodIds", StringUtil.splitToList(GlobalConstant.COMMA, finprodNames));
        return modAssetGradeSpecialConfigDtoMapper.selectForPage(param);
    }
    
    public ModAssetGradeSpecialConfigDto queryDtoBySeqno(String seqno){
    	return modAssetGradeSpecialConfigDtoMapper.selectByPrimaryKey(seqno);
    }
    public void insert(ModAssetGradeSpecialConfigDto dto){
    	modAssetGradeSpecialConfigDtoMapper.insert(dto);
    }
    public void update(ModAssetGradeSpecialConfigDto dto){
    	modAssetGradeSpecialConfigDtoMapper.updateByPrimaryKey(dto);
    }
    public int delete(ModAssetGradeSpecialConfigDto dto){
    	return modAssetGradeSpecialConfigDtoMapper.deleteByPrimaryKey(dto.getSeqno());
    }

    /**
     * 资产代码：下拉框
     *
     * @param
     * @return 资产代码：下拉框
     * @throws Exception
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getFinprodIdForImpairment(Map<String, Object> param) throws Exception {
        return modAssetGradeSpecialConfigDtoMapper.getFinprodIdForImpairment(param);
    }



    public List<AssetInfoDto> getAssetForImpairment(AssetInfoDto assetInfoDto){
        return modAssetGradeSpecialConfigDtoMapper.selectAssetForImpairment(assetInfoDto);
    }

    /**
     * 获取对应资管及委外资产行内资产/负债编码（资管计划代码）
     * @return 对应资管及委外资产行内资产/负债编码（资管计划代码）集合
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> getRepAssetCode(Map<String,Object> map){
        return modAssetGradeSpecialConfigDtoMapper.getRepAssetCode(map);
    }


    public List<AssetSideDetialRepoDto> findAll(Map<String, Object> pageParam)
            throws Exception {
        List<AssetSideDetialRepoDto> resultList = null;
        if (pageParam.get("reportDate") != null) {
            Date cdate = DateUtil.parse(pageParam.get("reportDate").toString());
            pageParam.put("reportDate", cdate);
        }
        String ifpenetrate = "";// 是否穿透
        if (pageParam.get("ifpenetrate") != null) {
            ifpenetrate = pageParam.get("ifpenetrate").toString();
        }
        if("".equals(pageParam.get("assettypeTwo"))){
            pageParam.put("assettypeTwo", null);
        }
        if("".equals(pageParam.get("assettypeThree"))){
            pageParam.put("assettypeThree", null);
        }
        if("".equals(pageParam.get("assettypeFour"))){
            pageParam.put("assettypeFour", null);
        }
        if("".equals(pageParam.get("assettypeFive"))){
            pageParam.put("assettypeFive", null);
        }
        pageParam.put("reportNames",
                StringUtil.splitToList(",",(String) pageParam.get("reportNames")));
        pageParam.put("prodList",
                StringUtil.splitToList(",",(String) pageParam.get("prodList")));
        pageParam.put("assetList",
                StringUtil.splitToList(",",(String) pageParam.get("assetList")));
        Map<String, AssetSideDetialRepoDto> resultMap = new HashMap<String, AssetSideDetialRepoDto>();
        if ("01".equals(ifpenetrate)) {// 01 不穿透 默认
            resultList =  modAssetGradeSpecialConfigDtoMapper.queryRegistList(pageParam);

            BigDecimal zj = BigDecimal.ZERO;
            for (AssetSideDetialRepoDto single : resultList) {
                if(single.getScalePbc()!=null)
                    zj = zj.add(single.getScalePbc());
            }
            for (AssetSideDetialRepoDto single : resultList) {
                String key=getKey(single);
                single.setAssettype(assetTypeTwoMap.get(single.getAssettypeTwo()));
                if(single.getScalePbc()!=null)
                    single.setRatio(single.getScalePbc().divide(zj, 17, RoundingMode.HALF_UP));
                if (StringUtil.isNotEmpty(single.getAssettypeFive())&& single.getAssettypeFive().equals("024")) {
                    single.setNoFixedTerm(single.getScaleG06());
                }
                resultMap.put(key, single);
            }
            calOther(resultMap, pageParam);
        } else {// 02 仅通道穿透 //03穿透
            // 查询出正常投资的资产 （不含投通道）
            resultList =  modAssetGradeSpecialConfigDtoMapper.queryRegistList(pageParam);
            for (AssetSideDetialRepoDto single : resultList) {
                String key=getKey(single);
                resultMap.put(key, single);
            }
            calOther(resultMap, pageParam);
            // 找到产品投资通道的资产及投资份额
            List<AssetSideDetialRepoDto> chlList = modAssetGradeSpecialConfigDtoMapper.queryRegistListChl(pageParam);
            List<AssetSideDetialRepoDto> list = new ArrayList<>();
            for (AssetSideDetialRepoDto single : chlList) {
                // 找到对应资管计划对应最近的底层投资日期 及总量
                Map<String, Object> param = new HashMap<String, Object>();
                param.put("assetcode", single.getAssetcode());
                param.put("reportDate", pageParam.get("reportDate"));
                param.put("shareamt", single.getShareamt());
                List<AssetSideDetialRepoDto> all = calPenetrateChl(param);
                if (all != null)
                    list.addAll(all);
            }
            for (AssetSideDetialRepoDto single : list) {
                String key=getKey(single);
                if (resultMap.containsKey(key)) {
                    AssetSideDetialRepoDto rs = resultMap.get(key);
                    rs.setScaleG06(rs.getScaleG06().add(single.getScaleG06()));
                    rs.setScalePbc(rs.getScalePbc().add(single.getScalePbc()));
                    rs.setValue(rs.getValue().add(single.getValue()));
                    if(single.getAvgDuration()!=null){
                        if(rs.getAvgDuration()==null){
                            rs.setAvgDuration(single.getAvgDuration());
                        }else{
                            BigDecimal zjq = rs.getAvgDuration().multiply(rs.getValue())
                                    .add(single.getAvgDuration().multiply(single.getValue()));
                            rs.setAvgDuration(zjq.divide(rs.getValue(), 15, RoundingMode.HALF_UP));
                        }
                    }
                    if (single.getNoFixedTerm() != null) {
                        rs.setNoFixedTerm((rs.getNoFixedTerm() == null ? BigDecimal.ZERO : rs.getNoFixedTerm())
                                .add(single.getNoFixedTerm()));
                    }
                    if (single.getUnderOneM() != null) {
                        rs.setUnderOneM((rs.getUnderOneM() == null ? BigDecimal.ZERO : rs.getUnderOneM())
                                .add(single.getUnderOneM()));
                    }
                    if (single.getUnderThreeM() != null) {
                        rs.setUnderThreeM((rs.getUnderThreeM() == null ? BigDecimal.ZERO : rs.getUnderThreeM())
                                .add(single.getUnderThreeM()));
                    }
                    if (single.getUnderSixM() != null) {
                        rs.setUnderSixM((rs.getUnderSixM() == null ? BigDecimal.ZERO : rs.getUnderSixM())
                                .add(single.getUnderSixM()));
                    }
                    if (single.getUnderOneY() != null) {
                        rs.setUnderOneY((rs.getUnderOneY() == null ? BigDecimal.ZERO : rs.getUnderOneY())
                                .add(single.getUnderOneY()));
                    }
                    if (single.getUnderTwoY() != null) {
                        rs.setUnderTwoY((rs.getUnderTwoY() == null ? BigDecimal.ZERO : rs.getUnderTwoY())
                                .add(single.getUnderTwoY()));
                    }
                    if (single.getUnderThreeY() != null) {
                        rs.setUnderThreeY((rs.getUnderThreeY() == null ? BigDecimal.ZERO : rs.getUnderThreeY())
                                .add(single.getUnderThreeY()));
                    }
                    if (single.getAfterThreeY() != null) {
                        rs.setAfterThreeY((rs.getAfterThreeY() == null ? BigDecimal.ZERO : rs.getAfterThreeY())
                                .add(single.getAfterThreeY()));
                    }
                } else {
                    resultMap.put(key, single);
                }
            }
            List<AssetSideDetialRepoDto> mapList = new ArrayList<AssetSideDetialRepoDto>();
            BigDecimal zj = BigDecimal.ZERO;
            for (String key : resultMap.keySet()) {
                if(resultMap.get(key).getScalePbc()!=null)
                    zj = zj.add(resultMap.get(key).getScalePbc());
            }
            for (String key : resultMap.keySet()) {
                AssetSideDetialRepoDto single = resultMap.get(key);
                single.setAssettype(assetTypeTwoMap.get(single.getAssettypeTwo()));
                single.setRatio(single.getScalePbc().divide(zj, 17, RoundingMode.HALF_UP));
                if (StringUtil.isNotEmpty(single.getAssettypeFive())&& single.getAssettypeFive().equals("024")) {
                    single.setNoFixedTerm(single.getScaleG06());
                }
                mapList.add(single);
            }
            resultList=mapList;
        }
        // 处理小计及合计
        calSubtotal(resultList);
        for(AssetSideDetialRepoDto single:resultList){
            single.setRatio(single.getRatio().multiply(new BigDecimal(100)));
            if(single.getAvgGyield()!=null){
                single.setAvgGyield(single.getAvgGyield().multiply(new BigDecimal(100)));
            }
            if(single.getAvgMyield()!=null){
                single.setAvgMyield(single.getAvgMyield().multiply(new BigDecimal(100)));
            }
            if(single.getAvgYield()!=null){
                single.setAvgYield(single.getAvgYield().multiply(new BigDecimal(100)));
            }
        }
        Collections.sort(resultList);
        return resultList;
    }


    private String getKey(AssetSideDetialRepoDto single){
        String key=single.getAssettypeTwo()+"|"+single.getAssettypeThree()+"|"+single.getAssettypeFour()+"|"+single.getAssettypeFive();
        return key;
    }

    /**
     * 查询其他指标 久期，收益率，期限分类
     *
     * @param resultMap
     * @param pageParam
     */
    private void calOther(Map<String, AssetSideDetialRepoDto> resultMap, Map<String, Object> pageParam) {
        List<AssetSideDetialRepoDto> valueList = modAssetGradeSpecialConfigDtoMapper.queryRegistListValue(pageParam);
        for (AssetSideDetialRepoDto single : valueList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setValue(single.getValue());
            }
        }
        List<AssetSideDetialRepoDto> durationList = modAssetGradeSpecialConfigDtoMapper.queryDurationList(pageParam);
        for (AssetSideDetialRepoDto single : durationList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setAvgDuration(single.getAvgDuration());
            }
        }
        List<AssetSideDetialRepoDto> myieldList = modAssetGradeSpecialConfigDtoMapper.queryMyieldList(pageParam);
        for (AssetSideDetialRepoDto single : myieldList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setAvgMyield(single.getAvgMyield());
            }
        }
        List<AssetSideDetialRepoDto> gyieldList = modAssetGradeSpecialConfigDtoMapper.queryGyieldList(pageParam);
        for (AssetSideDetialRepoDto single : gyieldList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setAvgGyield(single.getAvgGyield());
            }
        }
        List<AssetSideDetialRepoDto> yieldList = modAssetGradeSpecialConfigDtoMapper.queryYieldList(pageParam);
        for (AssetSideDetialRepoDto single : yieldList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setAvgYield(single.getAvgYield());
            }
        }
        pageParam.put("days", "01");
        List<AssetSideDetialRepoDto> noFixedTermList = modAssetGradeSpecialConfigDtoMapper.queryDeadLineTermList(pageParam);
        for (AssetSideDetialRepoDto single : noFixedTermList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setNoFixedTerm(single.getNoFixedTerm());
            }
        }
        pageParam.put("days", "02");
        List<AssetSideDetialRepoDto> underOneMList = modAssetGradeSpecialConfigDtoMapper.queryDeadLineTermList(pageParam);
        for (AssetSideDetialRepoDto single : underOneMList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setUnderOneM(single.getNoFixedTerm());
            }
        }
        pageParam.put("days", "03");
        List<AssetSideDetialRepoDto> underThreeMList = modAssetGradeSpecialConfigDtoMapper.queryDeadLineTermList(pageParam);
        for (AssetSideDetialRepoDto single : underThreeMList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setUnderThreeM(single.getNoFixedTerm());
            }
        }
        pageParam.put("days", "04");
        List<AssetSideDetialRepoDto> underSixMList = modAssetGradeSpecialConfigDtoMapper.queryDeadLineTermList(pageParam);
        for (AssetSideDetialRepoDto single : underSixMList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setUnderSixM(single.getNoFixedTerm());
            }
        }
        pageParam.put("days", "05");
        List<AssetSideDetialRepoDto> underOneYList = modAssetGradeSpecialConfigDtoMapper.queryDeadLineTermList(pageParam);
        for (AssetSideDetialRepoDto single : underOneYList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setUnderOneY(single.getNoFixedTerm());
            }
        }
        pageParam.put("days", "06");
        List<AssetSideDetialRepoDto> underTwoYList = modAssetGradeSpecialConfigDtoMapper.queryDeadLineTermList(pageParam);
        for (AssetSideDetialRepoDto single : underTwoYList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setUnderTwoY(single.getNoFixedTerm());
            }
        }
        pageParam.put("days", "07");
        List<AssetSideDetialRepoDto> underThreeYList = modAssetGradeSpecialConfigDtoMapper.queryDeadLineTermList(pageParam);
        for (AssetSideDetialRepoDto single : underThreeYList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setUnderThreeY(single.getNoFixedTerm());
            }
        }
        pageParam.put("days", "08");
        List<AssetSideDetialRepoDto> afterThreeYList = modAssetGradeSpecialConfigDtoMapper.queryDeadLineTermList(pageParam);
        for (AssetSideDetialRepoDto single : afterThreeYList) {
            String key=getKey(single);
            if (resultMap.containsKey(key)) {
                resultMap.get(key).setAfterThreeY(single.getNoFixedTerm());
            }
        }
    }


    /**
     * 穿透算法
     *
     * @param pageParam
     *            assetcode资管计划代码 reportDate日期 shareamt 投资资管计划份额
     * @return
     */
    private List<AssetSideDetialRepoDto> calPenetrateChl(Map<String, Object> pageParam) {
        List<AssetSideDetialRepoDto> resultList = new ArrayList<AssetSideDetialRepoDto>();
        // 找到对应资管计划对应最近的底层投资日期 及总量
        BigDecimal shareamt = (BigDecimal) pageParam.get("shareamt");
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("assetcode", pageParam.get("assetcode"));
        param.put("reportDate", pageParam.get("reportDate"));
        AssetSideDetialRepoDto dd = modAssetGradeSpecialConfigDtoMapper.selectAllShareAmtAndDate(param);
        if (dd == null) {
            return null;
        }
        BigDecimal bl = shareamt.divide(dd.getShareamt(), 17, RoundingMode.HALF_UP);
        Date cdate = dd.getQuerydate();
        param.put("reportDate", cdate);
        List<AssetSideDetialRepoDto> mianList = modAssetGradeSpecialConfigDtoMapper.queryChlMainBody(param);
        // 导入的底层
        pageParam.put("cdate", cdate);
        List<AssetSideDetialRepoDto> chbBotAstPosList = modAssetGradeSpecialConfigDtoMapper.queryChbBotAstPosList(pageParam);
        for (AssetSideDetialRepoDto chbBot : chbBotAstPosList) {
            if ("01".equals(chbBot.getDeadline())) {
                chbBot.setNoFixedTerm(chbBot.getScalePbc());
            } else if ("02".equals(chbBot.getDeadline())) {
                chbBot.setUnderOneM(chbBot.getScalePbc());
            } else if ("03".equals(chbBot.getDeadline())) {
                chbBot.setUnderThreeM(chbBot.getShareamt());
            } else if ("04".equals(chbBot.getDeadline())) {
                chbBot.setUnderSixM(chbBot.getScalePbc());
            } else if ("05".equals(chbBot.getDeadline())) {
                chbBot.setUnderOneY(chbBot.getScalePbc());
            } else if ("06".equals(chbBot.getDeadline())) {
                chbBot.setUnderTwoY(chbBot.getScalePbc());
            } else if ("07".equals(chbBot.getDeadline())) {
                chbBot.setUnderThreeY(chbBot.getScalePbc());
            } else if ("08".equals(chbBot.getDeadline())) {
                chbBot.setAfterThreeY(chbBot.getScalePbc());
            }
        }
        List<AssetSideDetialRepoDto> pbcScaleList = modAssetGradeSpecialConfigDtoMapper.queryChlPbcScale(param);
        List<AssetSideDetialRepoDto> chlValue = modAssetGradeSpecialConfigDtoMapper.queryChlValue(param);
        List<AssetSideDetialRepoDto> chlMduration = modAssetGradeSpecialConfigDtoMapper.queryChlMduration(param);
        List<AssetSideDetialRepoDto> deadLineList = modAssetGradeSpecialConfigDtoMapper.queryDeadLineList(param);

        Map<String, AssetSideDetialRepoDto> mianMap = new HashMap<String, AssetSideDetialRepoDto>();
        for (AssetSideDetialRepoDto mian : mianList) {
            mianMap.put(mian.getAssetcode(), mian);
            if (StringUtil.isNotEmpty(mian.getAssettypeFive())&& mian.getAssettypeFive().equals("024")) {// 剩余现金无需穿透
                mian.setScaleG06(mian.getShareamt());
                mian.setScalePbc(mian.getShareamt());
                mian.setValue(mian.getShareamt());
                mian.setNoFixedTerm(mian.getShareamt());
            }
        }
        for (AssetSideDetialRepoDto pbc : pbcScaleList) {
            if (mianMap.containsKey(pbc.getAssetcode())) {
                mianMap.get(pbc.getAssetcode()).setScalePbc(pbc.getScalePbc());
            }
        }
        for (AssetSideDetialRepoDto value : chlValue) {
            if (mianMap.containsKey(value.getAssetcode())) {
                mianMap.get(value.getAssetcode()).setValue(value.getValue());
            }
        }
        for (AssetSideDetialRepoDto mduration : chlMduration) {
            if (mianMap.containsKey(mduration.getAssetcode())) {
                mianMap.get(mduration.getAssetcode()).setAvgDuration(mduration.getAvgDuration());
            }
        }
        for (AssetSideDetialRepoDto deadline : deadLineList) {
            if (mianMap.containsKey(deadline.getAssetcode())) {
                if ("01".equals(deadline.getDeadline())) {
                    mianMap.get(deadline.getAssetcode()).setNoFixedTerm(deadline.getShareamt());
                } else if ("02".equals(deadline.getDeadline())) {
                    mianMap.get(deadline.getAssetcode()).setUnderOneM(deadline.getShareamt());
                } else if ("03".equals(deadline.getDeadline())) {
                    mianMap.get(deadline.getAssetcode()).setUnderThreeM(deadline.getShareamt());
                } else if ("04".equals(deadline.getDeadline())) {
                    mianMap.get(deadline.getAssetcode()).setUnderSixM(deadline.getShareamt());
                } else if ("05".equals(deadline.getDeadline())) {
                    mianMap.get(deadline.getAssetcode()).setUnderOneY(deadline.getShareamt());
                } else if ("06".equals(deadline.getDeadline())) {
                    mianMap.get(deadline.getAssetcode()).setUnderTwoY(deadline.getShareamt());
                } else if ("07".equals(deadline.getDeadline())) {
                    mianMap.get(deadline.getAssetcode()).setUnderThreeY(deadline.getShareamt());
                } else if ("08".equals(deadline.getDeadline())) {
                    mianMap.get(deadline.getAssetcode()).setAfterThreeY(deadline.getShareamt());
                }

            }
        }
        Map<String, Object> nparam = new HashMap<String, Object>();
        for (AssetSideDetialRepoDto mian : mianList) {
            if (bl != null) {
                mian.setScaleG06(bl.multiply(mian.getShareamt()));
                mian.setScalePbc(bl.multiply(mian.getScalePbc()));
                mian.setValue(bl.multiply(mian.getValue()));
                mian.setUnderOneM(bl.multiply(mian.getUnderOneM() == null ? BigDecimal.ZERO : mian.getUnderOneM()));
                mian.setUnderThreeM(
                        bl.multiply(mian.getUnderThreeM() == null ? BigDecimal.ZERO : mian.getUnderThreeM()));
                mian.setUnderSixM(bl.multiply(mian.getUnderSixM() == null ? BigDecimal.ZERO : mian.getUnderSixM()));
                mian.setUnderOneY(bl.multiply(mian.getUnderOneY() == null ? BigDecimal.ZERO : mian.getUnderOneY()));
                mian.setUnderTwoY(bl.multiply(mian.getUnderTwoY() == null ? BigDecimal.ZERO : mian.getUnderTwoY()));
                mian.setUnderThreeY(
                        bl.multiply(mian.getUnderThreeY() == null ? BigDecimal.ZERO : mian.getUnderThreeY()));
                mian.setAfterThreeY(
                        bl.multiply(mian.getAfterThreeY() == null ? BigDecimal.ZERO : mian.getAfterThreeY()));
                mian.setNoFixedTerm(
                        bl.multiply(mian.getNoFixedTerm() == null ? BigDecimal.ZERO : mian.getNoFixedTerm()));
            }

            nparam.put("reportDate", cdate);
            nparam.put("assetcode", mian.getAssetcode());
            nparam.put("shareamt", mian.getScalePbc());
            List<AssetSideDetialRepoDto> penetrateList = calPenetrateChl(nparam);
            if (penetrateList == null) {
                resultList.add(mian);
            } else {
                resultList.addAll(penetrateList);
            }
        }
        if (chbBotAstPosList != null && !chbBotAstPosList.isEmpty()) {
            for (AssetSideDetialRepoDto mian : chbBotAstPosList) {
                if (bl != null) {
                    mian.setScaleG06(bl.multiply(mian.getScaleG06()));
                    mian.setScalePbc(bl.multiply(mian.getScalePbc()));
                    mian.setValue(bl.multiply(mian.getValue()));
                    mian.setUnderOneM(bl.multiply(mian.getUnderOneM() == null ? BigDecimal.ZERO : mian.getUnderOneM()));
                    mian.setUnderThreeM(
                            bl.multiply(mian.getUnderThreeM() == null ? BigDecimal.ZERO : mian.getUnderThreeM()));
                    mian.setUnderSixM(bl.multiply(mian.getUnderSixM() == null ? BigDecimal.ZERO : mian.getUnderSixM()));
                    mian.setUnderOneY(bl.multiply(mian.getUnderOneY() == null ? BigDecimal.ZERO : mian.getUnderOneY()));
                    mian.setUnderTwoY(bl.multiply(mian.getUnderTwoY() == null ? BigDecimal.ZERO : mian.getUnderTwoY()));
                    mian.setUnderThreeY(
                            bl.multiply(mian.getUnderThreeY() == null ? BigDecimal.ZERO : mian.getUnderThreeY()));
                    mian.setAfterThreeY(
                            bl.multiply(mian.getAfterThreeY() == null ? BigDecimal.ZERO : mian.getAfterThreeY()));
                    mian.setNoFixedTerm(
                            bl.multiply(mian.getNoFixedTerm() == null ? BigDecimal.ZERO : mian.getNoFixedTerm()));
                }
            }
            resultList.addAll(chbBotAstPosList);
        }
        return resultList;
    }

    /**
     * 计算所有资产类型的小计 以及总合计
     *
     * @param root
     */
    private void calSubtotal(List<AssetSideDetialRepoDto> root) {
        Map<String, List<AssetSideDetialRepoDto>> zclxMap = new HashMap<String, List<AssetSideDetialRepoDto>>();
        // 将各个资产类型装入各自的List中
        for (AssetSideDetialRepoDto single : root) {
            if (zclxMap.containsKey(single.getAssettype())) {
                zclxMap.get(single.getAssettype()).add(single);
            } else {
                List<AssetSideDetialRepoDto> zclxList = new ArrayList<AssetSideDetialRepoDto>();
                zclxList.add(single);
                zclxMap.put(single.getAssettype(), zclxList);
            }
        }
        List<AssetSideDetialRepoDto> xjList=new ArrayList<AssetSideDetialRepoDto>();
        // 循环每个资产类型算出小计
        for (String key : zclxMap.keySet()) {
            List<AssetSideDetialRepoDto> zclxList = zclxMap.get(key);
            AssetSideDetialRepoDto xj=calhj(zclxList);
            if(key !=null){
                Integer t = Integer.parseInt(key) + 1;
                xj.setAssettype(t.toString());
                xjList.add(xj);
            }
        }
        if(!xjList.isEmpty()){
            AssetSideDetialRepoDto hj=calhj(xjList);
            hj.setAssettype("910");
            root.addAll(xjList);
            root.add(hj);
        }

    }

    /**
     * 算合计  对zclxList合计算出 一条合计对象
     * @param zclxList
     * @return
     */
    private AssetSideDetialRepoDto calhj(List<AssetSideDetialRepoDto> zclxList){
        AssetSideDetialRepoDto xj = new AssetSideDetialRepoDto();
        xj.setScaleG06(BigDecimal.ZERO);
        xj.setScalePbc(BigDecimal.ZERO);
        xj.setValue(BigDecimal.ZERO);
        BigDecimal allValueDuration = BigDecimal.ZERO;
        BigDecimal allValueMyield = BigDecimal.ZERO;
        BigDecimal allValueGyield = BigDecimal.ZERO;
        BigDecimal allValueYield = BigDecimal.ZERO;
        BigDecimal underOneM = BigDecimal.ZERO;
        BigDecimal underThreeM = BigDecimal.ZERO;
        BigDecimal underSixM = BigDecimal.ZERO;
        BigDecimal underOneY = BigDecimal.ZERO;
        BigDecimal underTwoY = BigDecimal.ZERO;
        BigDecimal underThreeY = BigDecimal.ZERO;
        BigDecimal afterThreeY = BigDecimal.ZERO;
        BigDecimal noFixedTerm = BigDecimal.ZERO;
        BigDecimal zb = BigDecimal.ZERO;
        BigDecimal jqAmt = BigDecimal.ZERO;
        BigDecimal yiledAmt = BigDecimal.ZERO;
        BigDecimal gyiledAmt = BigDecimal.ZERO;
        BigDecimal myiledAmt = BigDecimal.ZERO;
        for (AssetSideDetialRepoDto single : zclxList) {
            if (single.getScaleG06() != null)
                xj.setScaleG06(xj.getScaleG06().add(single.getScaleG06()));
            if (single.getScalePbc() != null)
                xj.setScalePbc(xj.getScalePbc().add(single.getScalePbc()));
            if (single.getValue() != null)
                xj.setValue(xj.getValue().add(single.getValue()));
            if (single.getUnderOneM() != null) {
                underOneM = underOneM.add(single.getUnderOneM());
            }
            if (single.getUnderThreeM() != null) {
                underThreeM = underThreeM.add(single.getUnderThreeM());
            }
            if (single.getUnderSixM() != null) {
                underSixM = underSixM.add(single.getUnderSixM());
            }
            if (single.getUnderOneY() != null) {
                underOneY = underOneY.add(single.getUnderOneY());
            }
            if (single.getUnderTwoY() != null) {
                underTwoY = underTwoY.add(single.getUnderTwoY());
            }
            if (single.getUnderThreeY() != null) {
                underThreeY = underThreeY.add(single.getUnderThreeY());
            }
            if (single.getAfterThreeY() != null) {
                afterThreeY = afterThreeY.add(single.getAfterThreeY());
            }
            if (single.getNoFixedTerm() != null) {
                noFixedTerm = noFixedTerm.add(single.getNoFixedTerm());
            }
            if(single.getAvgDuration()!=null){
                jqAmt=jqAmt.add(single.getValue());
                allValueDuration = allValueDuration.add(single.getValue().multiply(single.getAvgDuration()));
            }else{
                if(zclxList.size()==1){
                    allValueDuration=null;
                }
            }
            if(single.getAvgMyield()!=null){
                myiledAmt=myiledAmt.add(single.getValue());
                allValueMyield = allValueMyield.add(single.getValue().multiply(single.getAvgMyield()));
            }else{
                if(zclxList.size()==1){
                    allValueMyield=null;
                }
            }
            if(single.getAvgGyield()!=null){
                gyiledAmt=gyiledAmt.add(single.getValue());
                allValueGyield = allValueGyield.add(single.getValue().multiply(single.getAvgGyield()));
            }else{
                if(zclxList.size()==1){
                    allValueGyield=null;
                }
            }
            if(single.getAvgYield()!=null){
                yiledAmt=yiledAmt.add(single.getValue());
                allValueYield = allValueYield.add(single.getValue().multiply(single.getAvgYield()));
            }else{
                if(zclxList.size()==1){
                    allValueYield=null;
                }
            }
            if(single.getRatio()!=null)
                zb=zb.add(single.getRatio());
        }
        xj.setUnderOneM(underOneM);
        xj.setUnderThreeM(underThreeM);
        xj.setUnderSixM(underSixM);
        xj.setUnderOneY(underOneY);
        xj.setUnderTwoY(underTwoY);
        xj.setUnderThreeY(underThreeY);
        xj.setAfterThreeY(afterThreeY);
        xj.setNoFixedTerm(noFixedTerm);
        xj.setRatio(zb);
        if(xj.getValue().compareTo(BigDecimal.ZERO)!=0){
            if(allValueDuration==null){
                xj.setAvgDuration(null);
            }else{
                if(jqAmt.compareTo(BigDecimal.ZERO) !=0){
                    xj.setAvgDuration(allValueDuration.divide(jqAmt, 15, RoundingMode.HALF_UP));
                }else{
                    xj.setAvgDuration(BigDecimal.ZERO);
                }
            }
            if(allValueMyield==null){
                xj.setAvgMyield(null);
            }else{
                if(myiledAmt.compareTo(BigDecimal.ZERO) !=0){
                    xj.setAvgMyield(allValueMyield.divide(myiledAmt, 15, RoundingMode.HALF_UP));
                }else{
                    xj.setAvgMyield(BigDecimal.ZERO);
                }
            }
            if(allValueGyield==null){
                xj.setAvgGyield(null);
            }else{
                if(gyiledAmt.compareTo(BigDecimal.ZERO) !=0){
                    xj.setAvgGyield(allValueGyield.divide(gyiledAmt, 15, RoundingMode.HALF_UP));
                }else{
                    xj.setAvgGyield(BigDecimal.ZERO);
                }
            }
            if(allValueYield==null){
                xj.setAvgYield(null);
            }else{
                if(yiledAmt.compareTo(BigDecimal.ZERO) !=0){
                    xj.setAvgYield(allValueYield.divide(yiledAmt, 15, RoundingMode.HALF_UP));
                }else{
                    xj.setAvgYield(BigDecimal.ZERO);
                }
            }
        }
        return xj;
    }


    public String getSecTypeByFinprodId(Map<String, Object> param){
        return modAssetGradeSpecialConfigDtoMapper.getSecTypeByFinprodId(param);
    }

    public Integer getSpecialCountByFinprodId(ModAssetGradeSpecialConfigDto dto){
        return modAssetGradeSpecialConfigDtoMapper.getSpecialCountByFinprodId(dto);
    }





}