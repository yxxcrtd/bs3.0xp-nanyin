package com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dao.ValWithholdingAmortizeSet002Mapper;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet001Dto;
import com.joyintech.service.valuation.valuationparametermanage.withholdingamortize.dto.ValWithholdingAmortizeSet002Dto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinTradeProduct;
import com.joyintech.tams.common.base.entity.FinTradeProductAdd;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.dao.BokDetailIdMapper;
import com.joyintech.tams.common.base.dao.FinProductTypeMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductAddMapper;
import com.joyintech.tams.common.base.dao.FinTradeProductMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author tanxuan
 * @version 1.0 2019年11月29日
 * @see ValWithholdingAmortizeSet002Service
 * @since 1.0
 */
@Service("serviceValWithholdingAmortizeSet002Service")
public class ValWithholdingAmortizeSet002Service extends CommonExportService<ValWithholdingAmortizeSet002Dto> implements BaseProcessService<ValWithholdingAmortizeSet002Dto> {

    @Autowired
    private ValWithholdingAmortizeSet002Mapper valWithholdingAmortizeSet002Mapper;

    @Autowired
    private FinTradeProductMapper finTradeProductMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private FinTradeProductAddMapper finTradeProductAddMapper;

    @Autowired
    private FinProductTypeMapper finProductTypeMapper;

    @Autowired
    private BokDetailIdMapper bokDetailIdMapper;

    @Autowired
    private InterfCommonService interfCommonService;

    /**
     * 账套代码 - 业务编号获取
     * @param exception
     * @param params
     * @return 返回的数据
     */

    public List<SelectOptionDto> getFinprodId(Map<String, Object> params)
        throws Exception {
        List<SelectOptionDto> resultList = valWithholdingAmortizeSet002Mapper.getFinprodId(params);

        List<SelectOptionDto> selectOptionDtoList = new ArrayList<>();
        for (SelectOptionDto single : resultList) { // 组装select节点对象
            SelectOptionDto selectOptionDto = new SelectOptionDto();
            selectOptionDto.setId(single.getId());
            selectOptionDto.setText(single.getId());
            selectOptionDtoList.add(selectOptionDto);
        }
        return selectOptionDtoList;
    }

    /**
     * 通过业务编号，获取信息
     * 
     * @param pageParam
     * @return valWithholdingAmortizeSet001Dto
     */
    public ValWithholdingAmortizeSet001Dto getFinprodInfo(Map<String, Object> pageParam) {
        return valWithholdingAmortizeSet002Mapper.getFinprodInfo(pageParam);
    }
    
    /**
     * 获取交易日期
     * 
     * @param pageParam
     * @return valWithholdingAmortizeSet001Dto
     */
    public ValWithholdingAmortizeSet001Dto getTradeDate(Map<String, Object> pageParam) {
        return valWithholdingAmortizeSet002Mapper.getTradeDate(pageParam);
    }

    /**
     * 查询
     * 
     * @param pageParam
     * @return valWithholdingAmortizeSet002Dto
     */
    public ValWithholdingAmortizeSet002Dto findByPrimaryKey(Map<String, Object> pageParam) {
        return valWithholdingAmortizeSet002Mapper.selectByPrimaryKey(pageParam);
    }

    /**
     * 流程审核完成时执行 {@inheritDoc}
     */
    @Override
    public void doComplete(ValWithholdingAmortizeSet002Dto entity, OperationType type) {

        // TrdProductDeal trdProductDeal = new TrdProductDeal();
        // String tradeId = entity.getFinprodId();
        // trdProductDeal = trdProductDealMapper.selectByPrimaryKey(tradeId);

        if (OperationType.NEW.equals(type)) {
            TrdProductDeal trdProductDeal = new TrdProductDeal();
            String tradeId = entity.getFinprodId();
            trdProductDeal = trdProductDealMapper.selectByPrimaryKey(tradeId);

            FinTradeProduct finTradeProduct = new FinTradeProduct();
            String finprodTypeNew = "C19";
            finTradeProduct.setFinprodId(entity.getFinprodId());
            finTradeProduct.setFinprodType(finprodTypeNew);
            if(trdProductDeal.getBranch() == null){
                long branch = 0;
                finTradeProduct.setBranch(branch);
            }else{
                finTradeProduct.setBranch(trdProductDeal.getBranch());
            }
//            finTradeProduct.setBranch(trdProductDeal.getBranch());
            finTradeProduct.setPrin(entity.getPrin());
            finTradeProduct.setCcy(trdProductDeal.getCcy());
            finTradeProduct.setVdate(DateUtil.parse(entity.getVdate()));
            finTradeProduct.setMdate(DateUtil.parse(entity.getMdate()));
            finTradeProductMapper.insert(finTradeProduct);

            FinTradeProductAdd finTradeProductAdd = new FinTradeProductAdd();
            finTradeProductAdd.setFinprodId(finTradeProduct.getFinprodId());
            finTradeProductAdd.setFinprodType(finTradeProduct.getFinprodType());
            finTradeProductAdd.setBranch(finTradeProduct.getBranch());
            finTradeProductAdd.setRegistOrg(trdProductDeal.getRegistOrg());
            finTradeProductAddMapper.insert(finTradeProductAdd);

            FinProductType finProductType = new FinProductType();
            finProductType.setFinprodId(finTradeProduct.getFinprodId());
            finProductType.setBranch(finTradeProduct.getBranch());
            finProductType.setType1(entity.getFeeType());
            finProductTypeMapper.insert(finProductType);

            BokDetailId bokDetailId = new BokDetailId();
            bokDetailId.setBooksetId(entity.getBooksetId());
            bokDetailId.setBusiId(finTradeProduct.getFinprodId());
            bokDetailId.setBusiDetailType(finTradeProduct.getFinprodType());
            bokDetailId.setTradeId(entity.getFinprodId());
            bokDetailId.setDetailSubjectNoTail(finTradeProduct.getFinprodId());
            bokDetailId.setDetailSubnameTail(finTradeProduct.getFinprodId());
            bokDetailId.setBranch(finTradeProduct.getBranch());
            bokDetailId.setCcy(finTradeProduct.getCcy());
            bokDetailId.setBokDetailId(interfCommonService.genMd5BokDetailId(bokDetailId));
            bokDetailIdMapper.insert(bokDetailId);

            // valWithholdingAmortizeSet002Mapper.insert1(entity);
            // valWithholdingAmortizeSet002Mapper.insert2(entity);
            // valWithholdingAmortizeSet002Mapper.insert3(entity);
            // valWithholdingAmortizeSet002Mapper.insert4(entity);
        }
        else if (OperationType.MODIFY.equals(type)) {
            // FinTradeProduct finTradeProduct = new FinTradeProduct();
            // finTradeProduct.setFinprodId(entity.getFinprodId());
            // finTradeProduct.setBranch(trdProductDeal.getBranch());
            // finTradeProduct.setPrin(entity.getPrin());
            // finTradeProduct.setVdate(DateUtil.parse(entity.getVdate()));
            // finTradeProduct.setMdate(DateUtil.parse(entity.getMdate()));
            // finTradeProductMapper.updateByPrimaryKey(finTradeProduct);

            valWithholdingAmortizeSet002Mapper.update(entity);
        }
        else if (OperationType.DELETE.equals(type)) {

            // FinProductTypeKey finProductTypeKey = new FinProductTypeKey();
            // finProductTypeKey.setFinprodId(entity.getFinprodId());
            // finProductTypeMapper.deleteByPrimaryKey(finProductTypeKey);
            //
            // FinTradeProductAddKey finTradeProductAddKey = new FinTradeProductAddKey();
            // finTradeProductAddKey.setFinprodId(entity.getFinprodId());
            // finTradeProductAddMapper.deleteByPrimaryKey(finTradeProductAddKey);
            //
            // FinTradeProductKey finTradeProductKey = new FinTradeProductKey();
            // finTradeProductKey.setFinprodId(entity.getFinprodId());
            // finTradeProductMapper.deleteByPrimaryKey(finTradeProductKey);

            valWithholdingAmortizeSet002Mapper.deleteBokDetailId(entity);
            valWithholdingAmortizeSet002Mapper.deleteFinProductType(entity);
            valWithholdingAmortizeSet002Mapper.deleteFinTradeProductAdd(entity);
            valWithholdingAmortizeSet002Mapper.deleteFinTradeProduct(entity);
        }
    }

}
