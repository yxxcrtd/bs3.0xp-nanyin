package com.joyintech.service.portfolio.ptlsubfundsmange.service;

import java.util.*;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.dto.common.TrdCommonParam;
import com.joyintech.tams.invest.common.dto.common.TrdCommonWflParam;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.portfolio.ptlpositionanalysis.dto.PtlPositionAnalysis001Dto;
import com.joyintech.service.portfolio.ptlsingleissuerpositionanalysis.dto.PtlSingleIssuerPositionAnalysisDto;
import com.joyintech.service.portfolio.ptlsubfundsmange.dao.PtlSubFundsMangeMapper;
import com.joyintech.service.portfolio.ptlsubfundsmange.dto.PtlSubFundsMangeDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;

@Service
public class PtlSubFundsMangeService extends BaseService<PtlSingleIssuerPositionAnalysisDto> {

    @Autowired
    private PtlSubFundsMangeMapper ptlSubFundsMangeMapper;

    @Autowired
    private MapperDelegateService mapperDelegateService;
    
    @Autowired
    private PtlSubFundsAllocate001Service ptlSubFundsAllocate001Service;
    /**
     * 平台日期组件接口
     */
    @Autowired(required=false)
    private IPlatformDate platformDate;

    /**
     * 产品简称联想下拉框
     *
     * @param param 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdNameInfo(@RequestBody Map<String, Object> param) {
        // param.put("param", param);
        // param.put("finprodTypeProd",
        // DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return ptlSubFundsMangeMapper.queryProdNameInfo(param);
    }

    /**
     * 产品代码联想下拉框
     *
     * @param param 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public List<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param) {
        // param.put("param", param);
        // param.put("finprodTypeProd",
        // DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return ptlSubFundsMangeMapper.queryProdIdInfo(param);
    }

    /**
     * 查询
     *
     * @param params Map<String, Object>
     * @return Page<ValIncomeStatisticsQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public List<PtlSubFundsMangeDto> findPage(Map<String, Object> params) throws Exception {
        String finprodId = (String) params.get("prodId");
        String[] finprodIdArr = null;
        if (StringUtil.isNotEmpty(finprodId)) {
            finprodIdArr = finprodId.split(",");
        }
        if (finprodIdArr != null) {
            List list = new ArrayList<String>(Arrays.asList(finprodIdArr));
            params.put("list", list);
        }
        //查询组合收益类型
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("profitId", finprodId);
    	String profitType = ptlSubFundsAllocate001Service.getProfitTypeById(map);
    	//业务类型
    	String busiType = "";
    	if(DictCodeList.收益类型_货币型.getValue().equals(profitType)) {
    		busiType = DictCodeList.业务类型_货币型TA交易.getValue();
    	}
    	else if(DictCodeList.收益类型_收益型.getValue().equals(profitType)) {
    		busiType = DictCodeList.业务类型_预期收益型TA交易.getValue();
    	}
    	else if(DictCodeList.收益类型_净值型.getValue().equals(profitType)){
    		busiType = DictCodeList.业务类型_净值型TA交易.getValue();
    	}
        // 业务类型 = 数据字典缓存.业务类型.净值型TA交易
        params.put("busiType", busiType);
        // 投管业务类型 = 数据字典缓存.平安业务类型.产品交易资金分配
        params.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // 投管业务类型 = 数据字典缓存.平安业务类型.产品交易资金分配
        params.put("paBusiType", DictCodeListExt1.平安业务类型_产品交易资金分配.getValue());
        // 是否撤销<>是
        params.put("isCancel", DictCodeList.是否_是.getValue());
        // 拆分标识
        params.put("splitType", DictCodeListExt3.拆分标识_未拆分.getValue());
        // 交易状态
        params.put("tardeStatus", DictCodeListExt1.交易状态_初始.getValue());
        // 持仓类型
        params.put("hodingType", DictCodeList.持仓类型_实际.getValue());
        // 待处理金融产品类型
        params.put("dfinprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        //交易方向
        params.put("ps", DictCodeList.交易方向_产品赎回.getValue());
        //资金账户类型_产品托管户
        params.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        // 获取分配状态
        String distributionstatus = (String) params.get("distributionstatus");
        if (StringUtil.isNotEmpty(distributionstatus)) {
            // 流程中
            if (distributionstatus.equals(DictCodeList.分配状态_流程中.getValue())) {
                return ptlSubFundsMangeMapper.findPage01(params);
            }
            // 有效
            else if (distributionstatus.equals(DictCodeList.分配状态_有效.getValue())) {
                return ptlSubFundsMangeMapper.findPage02(params);
            } else {
                // 待处理查询
                List<PtlSubFundsMangeDto> listDto = getPtlSubFundsMangeDtoList(params);
                // 待处理明细查询
                //List<PtlSubFundsMangeDto> listDtoMx = ptlSubFundsMangeMapper.findPage03mx(params);
                //listDto.addAll(listDtoMx);
                //Page<PtlSubFundsMangeDto> pagelist = new Page<>();
                //pagelist.setRoot(listDto);
                // 待处理明细查询
                List<PtlSubFundsMangeDto> pagelist1 = ptlSubFundsMangeMapper.findPage03mx(params);
                pagelist1.addAll(listDto);
                return pagelist1;
            }
        } else {
            //流程中
            List<PtlSubFundsMangeDto> pagelist = ptlSubFundsMangeMapper.findPage01(params);
            //有效
            List<PtlSubFundsMangeDto> listDtoYx = ptlSubFundsMangeMapper.findPage02(params);
            // 待处理查询
            List<PtlSubFundsMangeDto> listDto = getPtlSubFundsMangeDtoList(params);
            // 待处理明细查询
            List<PtlSubFundsMangeDto> listDtoMx = ptlSubFundsMangeMapper.findPage03mx(params);
            // 有效以及流程中Union all
            pagelist.addAll(listDtoYx);
            pagelist.addAll(listDto);
            pagelist.addAll(listDtoMx);
            return pagelist;
        }

    }

    /**
     * 待处理查询以及处理
     */
    public List<PtlSubFundsMangeDto> getPtlSubFundsMangeDtoList(Map<String, Object> params) {
        /** add by yanya on 20191121 取当前平台日**/
        if(platformDate != null) {
            params.put("platDate", DateUtil.format(platformDate.getSysDate()));
        }
        else {
            params.put("platDate", DateUtil.format(new Date()));
        }
        //待处理汇总查询
        List<PtlSubFundsMangeDto> listDto = ptlSubFundsMangeMapper.findPage03(params);
        for (PtlSubFundsMangeDto dto : listDto) {
            params.put("prodId", dto.getProdId());
            // 查询每一个组合代码挂的流水号
            List<String> listseqNo = ptlSubFundsMangeMapper.findPage03List(params);
            dto.setSeqNoList(listseqNo);
        }
        return listDto;
    }


    /**
     * 导出查询
     *
     * @param params Map<String, Object>
     * @return Page<ValIncomeStatisticsQuery001Dto>
     * @throws Exception Exception
     * @see
     * @since 1.0
     */
    public List<PtlSubFundsMangeDto> findPageList(Map<String, Object> params) throws Exception {
        String finprodId = (String) params.get("prodId");
        String[] finprodIdArr = null;
        if (StringUtil.isNotEmpty(finprodId)) {
            finprodIdArr = finprodId.split(",");
        }
        if (finprodIdArr != null) {
            List list = new ArrayList<String>(Arrays.asList(finprodIdArr));
            params.put("list", list);
        }
        // 业务类型 = 数据字典缓存.业务类型.净值型TA交易
        params.put("busiType", DictCodeList.业务类型_净值型TA交易.getValue());
        // 投管业务类型 = 数据字典缓存.平安业务类型.产品交易资金分配
        params.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        // 投管业务类型 = 数据字典缓存.平安业务类型.产品交易资金分配
        params.put("paBusiType", DictCodeListExt1.平安业务类型_产品交易资金分配.getValue());
        // 是否撤销<>是
        params.put("isCancel", DictCodeList.是否_是.getValue());
        // 拆分标识
        params.put("splitType", DictCodeListExt3.拆分标识_未拆分.getValue());
        // 交易状态
        params.put("tardeStatus", DictCodeListExt1.交易状态_初始.getValue());
        // 持仓类型
        params.put("hodingType", DictCodeList.持仓类型_实际.getValue());
        // 待处理金融产品类型
        params.put("dfinprodType2", DictCodeListExt1.金融产品类型_投管用_理财产品分层.getValue());
        //交易方向
        params.put("ps", DictCodeList.交易方向_产品赎回.getValue());
        //资金账户类型_产品托管户
        params.put("acctType", DictCodeListExt1.资金账户类型_产品托管户.getValue());
        // 获取分配状态
        String distributionstatus = (String) params.get("distributionstatus");
        if (StringUtil.isNotEmpty(distributionstatus)) {
            // 流程中
            if (distributionstatus.equals(DictCodeList.分配状态_流程中.getValue())) {
                return ptlSubFundsMangeMapper.findPage01(params);
            }
            // 有效
            else if (distributionstatus.equals(DictCodeList.分配状态_有效.getValue())) {
                return ptlSubFundsMangeMapper.findPage02(params);
            } else {
                // 待处理查询
                List<PtlSubFundsMangeDto> listDto = getPtlSubFundsMangeDtoList(params);
                // 待处理明细查询
                List<PtlSubFundsMangeDto> listDtoMx = ptlSubFundsMangeMapper.findPage03mx(params);
                listDto.addAll(listDtoMx);
                // 待处理
                return listDto;
            }
        } else {
            // 有效以及流程中Union all
            List<PtlSubFundsMangeDto> listSumDto = ptlSubFundsMangeMapper.findPage04(params);
            // 待处理查询
            List<PtlSubFundsMangeDto> listDto = getPtlSubFundsMangeDtoList(params);
            // 待处理明细查询
            List<PtlSubFundsMangeDto> listDtoMx = ptlSubFundsMangeMapper.findPage03mx(params);
            listDto.addAll(listDtoMx);
            // 合并
            listSumDto.addAll(listDto);
            // 待处理
            return listSumDto;
        }

    }


    public String doComplete(TrdCommonParam trdCommonParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonParam.getTrdFinProductDealTotal(),
                trdCommonParam.getPaTrdFinProductDealTotal(),
                trdCommonParam.getTrdProductDeals(),
                trdCommonParam.getDealAdds(),
                trdCommonParam.getPaDeals(),
                trdCommonParam.getPtlTrades()
        );
        return "Success";
    }

    public String doInsert(TrdCommonWflParam trdCommonWflParam) {
        mapperDelegateService.insertAutoClassifyBatch(trdCommonWflParam.getWflTrdFinProductDealTotal(),
                trdCommonWflParam.getWflPaTrdFinDealTotal(),
                trdCommonWflParam.getWflTrdProductDeals(),
                trdCommonWflParam.getWflDealAdds(),
                trdCommonWflParam.getWflPaDeals()
        );
        return "Success";
    }
}
