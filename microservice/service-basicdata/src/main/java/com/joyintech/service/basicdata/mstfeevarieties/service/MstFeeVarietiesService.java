package com.joyintech.service.basicdata.mstfeevarieties.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.MstFeeinfoMapper;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.basicdata.mstfeevarieties.dao.MstFeeVarietiesMapper;
import com.joyintech.service.basicdata.mstfeevarieties.dto.MstFeeVarietiesDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
/**
 * 
 * 费用品种信息管理
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年8月30日
 * @see MstFeeVarietiesService
 * @since 1.0
 */
@Service
public class MstFeeVarietiesService extends BaseService<MstFeeVarietiesDto>{
    /**
     * 定义mapper文件
     */
    @Autowired
    private MstFeeVarietiesMapper mstFeeVarietiesMapper;
    /**
     * 系统根据数据库生成的mapper文件
     */
    @Autowired
    private MstFeeinfoMapper mstFeeinfoMapper;
    /**
     * mstFeeVarietiesService
     */
    @Autowired
    private MstFeeVarietiesService mstFeeVarietiesService;

    /**
     * 根据页面条件查询数据
     * @param length 长度
     * @param page 页数
     * 
     * @param pageParam 页面参数
     * @return 查询到的所有数据
     * @throws Exception 异常
     */
    public Page<MstFeeVarietiesDto> selectForPage(int page, int length, Map<String, Object> pageParam)
        throws Exception {
        return super.queryForPage(page,length,
            () -> mstFeeVarietiesService.getForPage(pageParam));
    }
    
    public List<MstFeeVarietiesDto> getForPage(Map<String, Object> pageParam){
        List<MstFeeVarietiesDto> pageList = mstFeeVarietiesMapper.selectForPage(pageParam);
        List<MstFeeVarietiesDto> defendDtoList = getDefendDtoList();
        if(pageList!=null && pageList.size()>0 && defendDtoList!=null && defendDtoList.size()>0){
            for(MstFeeVarietiesDto mst:pageList){
                for(MstFeeVarietiesDto entity:defendDtoList){
                    if (mst.getBasicBill() != null && mst.getBasis() != null) {
                        if (StringUtil.equals(mst.getFeeName(), entity.getFeeName())
                            && mst.getFeeType().equals(entity.getFeeType())
                            && mst.getApplyType().equals(entity.getApplyType())
                            && mst.getChargeType().equals(entity.getChargeType())
                            && mst.getBasicBill().equals(entity.getBasicBill())
                            && mst.getBasis().equals(entity.getBasis())
                            && mst.getOrgType2().equals(entity.getOrgType2())) {
                            mst.setFlag(true);
                            break;
                        }
                    }
                    else {
                        if (StringUtil.equals(mst.getFeeName(), entity.getFeeName())
                            && StringUtil.equals(mst.getFeeType(), entity.getFeeType())
                            && StringUtil.equals(mst.getApplyType(), entity.getApplyType())
                            && StringUtil.equals(mst.getChargeType(), entity.getChargeType())
                            && StringUtil.equals(mst.getOrgType2(), entity.getOrgType2())) {
                            mst.setFlag(true);
                            break;
                        }
                    }
                }
            }
        }
        return pageList;
    }
    /**
     * 实现docomplement方法
     */
    public void doComplete(MstFeeVarietiesDto entity) {
        if (StringUtil.equals(entity.getOperateType(), "DELETE")) {//删除操作
            mstFeeinfoMapper.deleteByPrimaryKey(entity.getFeeId());
        }
        else if (StringUtil.equals(entity.getOperateType(), "NEW")) {//新建操作
            mstFeeinfoMapper.insert(entity);
        }
        else {//修改
            mstFeeinfoMapper.updateByPrimaryKeySelective(entity);
        }
    }

//    /**
//     * 导出
//     */
//    @Override
//    protected void buildExportParam(ExportParam exportParam) {
//        exportParam.config("/com/joyintech/basicdata/mstfeevarieties/conf/export_conf.xml").select(
//            () -> mstFeeVarietiesMapper.selectForPage(exportParam.getSelectParam())).dataSource(
//                DataSourceType.MASERT);
//    }

    /**
     * 获得费用名称的下拉框
     * 
     * @param param 查询条件
     * @return 结果集
     */
    public List<SuggestDto> getFeeNameOption(Map<String, Object> param) {
        return mstFeeVarietiesMapper.getFeeNameOption(param);
    }

    /**
     * 根据费用代码查询数据
     * 
     * @param map 费用代码
     * @return 查询到的实体类说
     */
    public MstFeeVarietiesDto selectByFeeId(Map<String, Object> map) {
        return mstFeeVarietiesMapper.selectByFeeId(map);
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @param feeId
     * @return
     * @see
     * @since 1.0
     */
    public int isUseByFeeId(String feeId) {
        return mstFeeVarietiesMapper.isUseByFeeId(feeId);
    }

    /**
     * 通过费用名称查询数据，验证费用名称的唯一性
     * <功能详细描述>
     * @param feeName
     * @return
     * @see
     * @since 1.0
     */
    public List<MstFeeVarietiesDto> getDtoByFeeName(String feeName) {
        return mstFeeVarietiesMapper.getDtoByFeeName(feeName);
    }
    /**
     * 获取初始化维护数据
     * <功能详细描述>
     * @return MstFeeVarietiesDto 集合
     * @see
     * @since 1.0
     */
    public List<MstFeeVarietiesDto> getDefendDtoList() {
        List<MstFeeVarietiesDto> defendDtoList = new ArrayList<MstFeeVarietiesDto>();
        MstFeeVarietiesDto dto1 = new MstFeeVarietiesDto();
        dto1.setFeeName("浮动差额中收");
        dto1.setFeeType(DictCodeList.费用品种类型_浮动差额中收.getValue());
        dto1.setApplyType(DictCodeList.关联范围_产品.getValue());
        dto1.setChargeType(DictCodeList.收费类型_计提式费用.getValue());
        dto1.setOrgType2(DictCodeListExt2.费用二级类型_本机构管理费.getValue());
        MstFeeVarietiesDto dto2 = new MstFeeVarietiesDto();
        dto2.setFeeName("浮动管理费-产品份额");
        dto2.setFeeType(DictCodeList.费用品种类型_浮动管理费.getValue());
        dto2.setApplyType(DictCodeList.关联范围_产品.getValue());
        dto2.setChargeType(DictCodeList.收费类型_计提式费用.getValue());
        dto2.setBasicBill(DictCodeList.计费基数_费用品种_存续本金.getValue());
        dto2.setBasis(DictCodeList.计息基础_A_365.getValue());
        dto2.setOrgType2(DictCodeListExt2.费用二级类型_本机构管理费.getValue());
        MstFeeVarietiesDto dto3 = new MstFeeVarietiesDto();
        dto3.setFeeName("浮动管理费-资产净值");
        dto3.setFeeType(DictCodeList.费用品种类型_浮动管理费.getValue());
        dto3.setApplyType(DictCodeList.关联范围_产品.getValue());
        dto3.setChargeType(DictCodeList.收费类型_计提式费用.getValue());
        dto3.setBasicBill(DictCodeList.计费基数_费用品种_资产规模.getValue());
        dto3.setBasis(DictCodeList.计息基础_A_365.getValue());
        dto3.setOrgType2(DictCodeListExt2.费用二级类型_本机构管理费.getValue());
        MstFeeVarietiesDto dto4 = new MstFeeVarietiesDto();
        dto4.setFeeName("结算服务费");
        dto4.setFeeType(DictCodeList.费用品种类型_结算服务费.getValue());
        dto4.setApplyType(DictCodeList.关联范围_其他.getValue());
        dto4.setChargeType(DictCodeList.收费类型_不规则费用.getValue());
        dto4.setOrgType2(DictCodeListExt2.费用二级类型_其他机构其他产品费用.getValue());
        MstFeeVarietiesDto dto5 = new MstFeeVarietiesDto();
        dto5.setFeeName("账户维护费");
        dto5.setFeeType(DictCodeList.费用品种类型_账户维护费.getValue());
        dto5.setApplyType(DictCodeList.关联范围_其他.getValue());
        dto5.setChargeType(DictCodeList.收费类型_不规则费用.getValue());
        dto5.setOrgType2(DictCodeListExt2.费用二级类型_其他机构其他产品费用.getValue());
        MstFeeVarietiesDto dto6 = new MstFeeVarietiesDto();
        dto6.setFeeName("增值附加税费");
        dto6.setFeeType(DictCodeList.费用品种类型_增值附加税费.getValue());
        dto6.setApplyType(DictCodeList.关联范围_其他.getValue());
        dto6.setChargeType(DictCodeList.收费类型_不规则费用.getValue());
        dto6.setOrgType2(DictCodeListExt2.费用二级类型_其他机构其他产品费用.getValue());
        MstFeeVarietiesDto dto7 = new MstFeeVarietiesDto();
        dto7.setFeeName("托管费-收益型");
        dto7.setFeeType(DictCodeList.费用品种类型_托管费.getValue());
        dto7.setApplyType(DictCodeList.关联范围_产品.getValue());
        dto7.setChargeType(DictCodeList.收费类型_计提式费用.getValue());
        dto7.setBasicBill(DictCodeList.计费基数_费用品种_存续本金.getValue());
        dto7.setBasis(DictCodeList.计息基础_A_365.getValue());
        dto7.setOrgType2(DictCodeListExt2.费用二级类型_本机构托管费.getValue());
        MstFeeVarietiesDto dto8 = new MstFeeVarietiesDto();
        dto8.setFeeName("托管费-净值型");
        dto8.setFeeType(DictCodeList.费用品种类型_托管费.getValue());
        dto8.setApplyType(DictCodeList.关联范围_产品.getValue());
        dto8.setChargeType(DictCodeList.收费类型_计提式费用.getValue());
        dto8.setBasicBill(DictCodeList.计费基数_费用品种_资产规模.getValue());
        dto8.setBasis(DictCodeList.计息基础_A_365.getValue());
        dto8.setOrgType2(DictCodeListExt2.费用二级类型_本机构托管费.getValue());
        MstFeeVarietiesDto dto9 = new MstFeeVarietiesDto();
        dto9.setFeeName("销售费-收益型");
        dto9.setFeeType(DictCodeListExt1.一次性费用类型_投管_交易手续费.getValue());
        dto9.setApplyType(DictCodeList.关联范围_产品.getValue());
        dto9.setChargeType(DictCodeList.收费类型_计提式费用.getValue());
        dto9.setBasicBill(DictCodeList.计费基数_费用品种_存续本金.getValue());
        dto9.setBasis(DictCodeList.计息基础_A_365.getValue());
        dto9.setOrgType2(DictCodeListExt2.费用二级类型_本机构销售手续费.getValue());
        MstFeeVarietiesDto dto10 = new MstFeeVarietiesDto();
        dto10.setFeeName("销售费-净值型");
        dto10.setFeeType(DictCodeListExt1.一次性费用类型_投管_交易手续费.getValue());
        dto10.setApplyType(DictCodeList.关联范围_产品.getValue());
        dto10.setChargeType(DictCodeList.收费类型_计提式费用.getValue());
        dto10.setBasicBill(DictCodeList.计费基数_费用品种_资产规模.getValue());
        dto10.setBasis(DictCodeList.计息基础_A_365.getValue());
        dto10.setOrgType2(DictCodeListExt2.费用二级类型_本机构销售手续费.getValue());
        MstFeeVarietiesDto dto11 = new MstFeeVarietiesDto();
        dto11.setFeeName("固定管理费-收益型");
        dto11.setFeeType(DictCodeList.费用品种类型_固定管理费.getValue());
        dto11.setApplyType(DictCodeList.关联范围_产品.getValue());
        dto11.setChargeType(DictCodeList.收费类型_计提式费用.getValue());
        dto11.setBasicBill(DictCodeList.计费基数_费用品种_存续本金.getValue());
        dto11.setBasis(DictCodeList.计息基础_A_365.getValue());
        dto11.setOrgType2(DictCodeListExt2.费用二级类型_本机构管理费.getValue());
        MstFeeVarietiesDto dto12 = new MstFeeVarietiesDto();
        dto12.setFeeName("固定管理费-净值型");
        dto12.setFeeType(DictCodeList.费用品种类型_固定管理费.getValue());
        dto12.setApplyType(DictCodeList.关联范围_产品.getValue());
        dto12.setChargeType(DictCodeList.收费类型_计提式费用.getValue());
        dto12.setBasicBill(DictCodeList.计费基数_费用品种_资产规模.getValue());
        dto12.setBasis(DictCodeList.计息基础_A_365.getValue());
        dto12.setOrgType2(DictCodeListExt2.费用二级类型_本机构管理费.getValue());
        defendDtoList.add(dto12);
        defendDtoList.add(dto11);
        defendDtoList.add(dto10);
        defendDtoList.add(dto9);
        defendDtoList.add(dto8);
        defendDtoList.add(dto7);
        defendDtoList.add(dto6);
        defendDtoList.add(dto5);
        defendDtoList.add(dto4);
        defendDtoList.add(dto3);
        defendDtoList.add(dto2);
        defendDtoList.add(dto1);
        return defendDtoList;
    }

    
}
