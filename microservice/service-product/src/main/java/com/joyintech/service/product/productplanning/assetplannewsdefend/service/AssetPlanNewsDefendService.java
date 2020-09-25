package com.joyintech.service.product.productplanning.assetplannewsdefend.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.productplanning.assetplannewsdefend.dao.AssetPlanNewsDefendMapper;
import com.joyintech.service.product.productplanning.assetplannewsdefend.dto.AssetPlanNewsDefend;
import com.joyintech.service.product.productplanning.assetplannewsdefend.dto.AssetPlanNewsForm;
import com.joyintech.tams.common.base.dao.AssetPlanDetailMapper;
import com.joyintech.tams.common.base.dao.AssetPlanInfoMapper;
import com.joyintech.tams.common.base.entity.AssetPlanDetail;
import com.joyintech.tams.common.base.entity.AssetPlanInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
/**
 * 资产规划信息维护
 * @author guyunfei
 * 20191226
 */
@Service
public class AssetPlanNewsDefendService extends BaseService {

    @Autowired
    private AssetPlanNewsDefendMapper assetPlanNewsDefendMapper;

    @Autowired
    private AssetPlanInfoMapper assetPlanInfoMapper;

    @Autowired
    private AssetPlanDetailMapper assetPlanDetailMapper;

    /**
     * 资产规划信息维护 一览查询
     * @param page
     * @param length
     * @param params
     * @return
     */
    public Page<AssetPlanNewsDefend> selectForPage(int page, int length, Map<String,Object> params){
        return super.queryForPage(page,length,()->assetPlanNewsDefendMapper.selectForPage(params));
    }

    /**
     * 查询时间冲突的数量
     * @param params
     * @return
     */
    public int getConflictCount(Map<String,Object> params){
        return assetPlanNewsDefendMapper.getConflictCount(params);
    }

    /**
     * 查询时间交叉的数量
     * @param params
     * @return
     */
    public int getOverlapCount(Map<String,Object> params){
        return assetPlanNewsDefendMapper.getOverlapCount(params);
    }

    /**
     * 产品系列规划目标
     * @param params
     * @return
     */
    public List<AssetPlanNewsDefend> queryProductTarget(Map<String,Object> params){
        return assetPlanNewsDefendMapper.queryProductTarget(params);
    }

    /**
     * 删除资产规划信息
     * @param params
     * @return
     */
    public Integer deleteByPrimary(Map<String,Object> params){
        //删除资产规划信息
        int plan = assetPlanNewsDefendMapper.deleteAssetPlan(params);
        //删除资产规划明细
        assetPlanNewsDefendMapper.deleteAssetPlanDetail(params);
        return plan;
    }

    /**
     * 根据资产规划编号查看详情
     * @param assetPlanCode
     * @return
     */
    public AssetPlanNewsForm queryData(String assetPlanCode){
        //资产规划查询
        AssetPlanInfo assetPlanInfo = assetPlanInfoMapper.selectByPrimaryKey(assetPlanCode);
        //资产规划明细查询
        List<AssetPlanDetail> detailData = assetPlanNewsDefendMapper.getDetailData(assetPlanCode);
        detailData.forEach(e->{
            //存入DB的数据单位为元，页面展示单位为万元，进行转换
            e.setPlanChangeAmt(ArithmeticUtil.divide(e.getPlanChangeAmt(),10000,4));
            e.setPlanInitAmt(ArithmeticUtil.divide(e.getPlanInitAmt(),10000,4));
            e.setPlanTargetAmt(ArithmeticUtil.divide(e.getPlanTargetAmt(),10000,4));
        });
        //数据造成
        AssetPlanNewsForm form = new AssetPlanNewsForm();
        form.setAssetPlanInfo(assetPlanInfo);
        form.setAssetPlanDetailList(detailData);
        return form;
    }

    /**
     * 资产规划信息新增、修改
     * @param form
     * @return
     */
    public Integer updateOrAdd(AssetPlanNewsForm form){

        AssetPlanInfo plan = new AssetPlanInfo();
        List<AssetPlanDetail> detail = new ArrayList<>();
        if(form != null && form.getAssetPlanInfo() != null){
            plan  = form.getAssetPlanInfo();
        }
        if(form != null && form.getAssetPlanDetailList() != null){
            detail =form.getAssetPlanDetailList();
        }

        if( form != null
                && form.getOperateType() != null
                && StringUtil.equals(form.getOperateType(), "MODIFY")){
            //修改
            int i = assetPlanInfoMapper.updateByPrimaryKey(plan);

            String assetPlanCode = plan.getAssetPlanCode();

            //更新该资产配置编号的所有明细数据先删除再插入
            Map<String,Object> param = new HashMap<>();
            param.put("assetPlanCode",assetPlanCode);
            assetPlanNewsDefendMapper.deleteAssetPlanDetail(param);
            detail.forEach(e->{
                //存入DB的数据单位为元，页面展示单位为万元，进行转换
                e.setPlanInitAmt(ArithmeticUtil.multiply(e.getPlanInitAmt(),BigDecimal.valueOf(10000)));
                e.setPlanTargetAmt(ArithmeticUtil.multiply(e.getPlanTargetAmt(),BigDecimal.valueOf(10000)));
                e.setPlanChangeAmt(ArithmeticUtil.multiply(e.getPlanChangeAmt(),BigDecimal.valueOf(10000)));
                e.setAssetPlanCode(assetPlanCode);
                assetPlanDetailMapper.insert(e);
            });
            return i;
        }else{
            //新增
            int i = assetPlanInfoMapper.insert(plan);
            detail.forEach(e->{
                //存入DB的数据单位为元，页面展示单位为万元，进行转换
                e.setPlanInitAmt(ArithmeticUtil.multiply(e.getPlanInitAmt(),BigDecimal.valueOf(10000)));
                e.setPlanTargetAmt(ArithmeticUtil.multiply(e.getPlanTargetAmt(),BigDecimal.valueOf(10000)));
                e.setPlanChangeAmt(ArithmeticUtil.multiply(e.getPlanChangeAmt(),BigDecimal.valueOf(10000)));
                assetPlanDetailMapper.insert(e);
            });
            return i;
        }
    }
}
