package com.joyintech.tams.svreport.pbc.classic.prdassetdebt.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.svreport.pbc.assetposition.dao.PbcMonthAssetReportMapper;
import com.joyintech.tams.svreport.pbc.assetposition.dto.PbcMonthAssetPositionDto;
import com.joyintech.tams.svreport.pbc.assetposition.service.PbcMonthAssetReportService;
import com.joyintech.tams.svreport.pbc.common.constants.PbcReportType;
import com.joyintech.tams.svreport.pbc.common.service.PbcClassicsSubService;
import com.joyintech.tams.svreport.pbc.prod.dao.PbcMonthPrdPostionMapper;
import com.joyintech.tams.svreport.pbc.prod.dto.PbcMonthPrdPostionDto;
import com.joyintech.tams.svreport.pbc.report.dao.PbcMonthReportMapper;
import com.joyintech.tams.svreport.pbc.report.dto.PbcMonthReport;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 功能:人行单产品报表-资产负债信息
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年6月11日
 * @修改记录：
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class PbcPrdAssetDebtSubService extends PbcClassicsSubService<PbcMonthReport>{
	
	/**
	 * 人行月报生成服务
	 */
	@Autowired
	private PbcMonthAssetReportService pbcMonthAssetReportService;
	
	/**
	 * 资产头寸mapper
	 */
    @Autowired
	private PbcMonthAssetReportMapper pbcMonthAssetReportMapper;
    
    /**
     * 产品头寸mapper
     */
    @Autowired
    private PbcMonthPrdPostionMapper pbcMonthPrdPostionMapper;
    
    /**
     * 人行报表mapper
     */
    @Autowired
    private PbcMonthReportMapper pbcMonthReportMapper;
	
    /**
     * 功能:人行单产报表-资产负债信息批处理入口
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年6月12日
	 * @param executeDate 批处理日期
	 * @param prodIdList 产品list
     * @see com.joyintech.tams.svreport.pbc.common.service.PbcClassicsSubService#genReport(java.util.Date, java.util.List)
     */
	@Override
	public void genReport(Date executeDate, List<String> prodIdList) {
		// 每个月的一号生成报表
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTime(executeDate);
//        // 每月1日生成上月的数据
//        // 如果走修改消息池进入则自动进入批处理
//        if ( calendar.get(Calendar.DATE) != 1 ) {  
//            return;
//        }
        // 批处理入口，默认为根据上次月报数据状态生成本次数据状态
        genRegistReport(executeDate,prodIdList,false);
	}

	/**
	 * 功能:人行单产报表-资产负债信息批处理
	 * @since 1.7.0
	 * @version 1.7.0
	 * @author：weiyuqiang 2019年6月12日
	 * @param executeDate 批处理日期
	 * @param prodIdList 产品list
	 * @param isEdit 是否是消息处理编辑触发
	 * void
	 */
	public void genRegistReport(Date executeDate, List<String> prodIdList,boolean isEdit) {
		// 一次性将所有数据全部查出
        List<PbcMonthAssetPositionDto> posAssetList = new ArrayList<>();
        // 权益类从产品头寸取值
        List<PbcMonthPrdPostionDto> posProdList = new ArrayList<>();
        // 分批处理数据
        if (!StringUtil.isEmptyList(prodIdList)){
	        for (int i = 0;i < prodIdList.size();){
	        	int end = i + 100;
	        	if (end > prodIdList.size()){
	        		end = prodIdList.size();
	        	}
	            posAssetList.addAll(pbcMonthAssetReportMapper.selectByDateAndProdidWithAssetPoolCode(prodIdList.subList(i, end),DateUtil.getLastDayOfLayMonth(executeDate)));
	            //多法人模式改造  20200725 leipeng
	            if(CommonUtil.isBAmodel()){
	            	posProdList.addAll(pbcMonthPrdPostionMapper.selectByDateWithAssetPoolCodeEx(prodIdList,DateUtil.getLastDayOfLayMonth(executeDate)));
	        	}else{
	        		posProdList.addAll(pbcMonthPrdPostionMapper.selectByDateWithAssetPoolCode(prodIdList,DateUtil.getLastDayOfLayMonth(executeDate)));
	        	}
	            i = end;
	        }
        } else {
            posAssetList.addAll(pbcMonthAssetReportMapper.selectByDateAndProdidWithAssetPoolCode(prodIdList,DateUtil.getLastDayOfLayMonth(executeDate)));
          //多法人模式改造  20200725 leipeng
        	if(CommonUtil.isBAmodel()){
            	posProdList.addAll(pbcMonthPrdPostionMapper.selectByDateWithAssetPoolCodeEx(prodIdList,DateUtil.getLastDayOfLayMonth(executeDate)));
        	}else{
        		posProdList.addAll(pbcMonthPrdPostionMapper.selectByDateWithAssetPoolCode(prodIdList,DateUtil.getLastDayOfLayMonth(executeDate)));
        	}
        	
        }
        // 查询登记数据
        List<PbcMonthReport> prdAssetRaiseAllList = pbcMonthReportMapper.selectRegList(DateUtil.getLastDayOfLayMonth(executeDate),
                PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT);
        // 已登记数据
        List<PbcMonthReport> prdAssetRaiseRegList = prdAssetRaiseAllList.stream().filter(item->{
            return !DictCodeListExt2.报送状态_未报送.getValue().equals(item.getSendStatus());
        }).collect(Collectors.toList());
        // 未登记数据
        List<PbcMonthReport> prdAssetRaiseNoRegList = prdAssetRaiseAllList.stream().filter(item->{
            return DictCodeListExt2.报送状态_未报送.getValue().equals(item.getSendStatus());
        }).collect(Collectors.toList());
        if (!StringUtil.isEmptyList(prodIdList)){      
			// 单产品出报表
			for (String prodid : prodIdList){
			    // 按产品过滤
			    List<PbcMonthPrdPostionDto> posProdAllList = posProdList.stream().filter(item->{
		            return prodid.equals(item.getProdid());
		        }).collect(Collectors.toList());
			    List<PbcMonthAssetPositionDto> posAssetAllList = posAssetList.stream().filter(item->{
		            return prodid.equals(item.getProdid());
		        }).collect(Collectors.toList());
                List<PbcMonthReport> posAssetRegAllList = prdAssetRaiseAllList.stream().filter(item->{
                    return prodid.equals(item.getProdid());
                }).collect(Collectors.toList());
                List<PbcMonthReport> prdAssetRegNoRegList = prdAssetRaiseNoRegList.stream().filter(item->{
                    return prodid.equals(item.getProdid());
                }).collect(Collectors.toList());
	            // 按产品分头寸
	            Map<String,List<PbcMonthAssetPositionDto>> posAssetAllMap = posAssetAllList.stream().collect(
	                    Collectors.groupingBy(PbcMonthAssetPositionDto::getPbcProdidAndOrgCode,Collectors.toList()));
	            // 按产品分头寸
	            Map<String,List<PbcMonthPrdPostionDto>> posProdAllMap = posProdAllList.stream().collect(
	                    Collectors.groupingBy(PbcMonthPrdPostionDto::getPbcProdidAndOrgCode,Collectors.toList()));
                Map<String,List<PbcMonthReport>> prdAssetRegAllMap = posAssetRegAllList.stream().collect(
                        Collectors.groupingBy(PbcMonthReport::getPbcProdidAndOrgCode,Collectors.toList()));
                Map<String,List<PbcMonthReport>> prdAssetNoRegAllMap = prdAssetRegNoRegList.stream().collect(
                        Collectors.groupingBy(PbcMonthReport::getPbcProdidAndOrgCode,Collectors.toList()));
	            // 记录已经统计过得单产品(带机构)
	            Map<String,String> prodMap = new HashMap<>();
	            // 根据查询出的数据分组进行出单产品报表
	            for (String key : posAssetAllMap.keySet()){
	                if (!prodMap.containsKey(key)){
	                    prodMap.put(key, key);
	                } else {
	                    // 已经出过报表则跳过
	                    continue;
	                }
	                List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
	                if (posAssetAllMap.get(key) != null){
	                    assetPosition = posAssetAllMap.get(key);
	                }
	                List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
	                if (posProdAllMap.get(key) != null){
	                    prodPosition = posProdAllMap.get(key);
	                }
                    List<PbcMonthReport> prodAssetRegList = new ArrayList<>();
                    if (prdAssetRegAllMap.get(key) != null){
                        prodAssetRegList = prdAssetRegAllMap.get(key);
                    }
	                pbcMonthAssetReportService.generateMonthAssetDebtReport(assetPosition,prodPosition,executeDate,isEdit,
	                        PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT,true,prodAssetRegList);
	            }
	            // 根据查询出的数据分组进行出单产品报表
	            for (String key : posProdAllMap.keySet()){
	                if (!prodMap.containsKey(key)){
	                    prodMap.put(key, key);
	                } else {
	                    // 已经出过报表则跳过
	                    continue;
	                }
	                List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
	                if (posAssetAllMap.get(key) != null){
	                    assetPosition = posAssetAllMap.get(key);
	                }
	                List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
	                if (posProdAllMap.get(key) != null){
	                    prodPosition = posProdAllMap.get(key);
	                }
                    List<PbcMonthReport> prodAssetRegList = new ArrayList<>();
                    if (prdAssetRegAllMap.get(key) != null){
                        prodAssetRegList = prdAssetRegAllMap.get(key);
                    }
	                pbcMonthAssetReportService.generateMonthAssetDebtReport(assetPosition,prodPosition,executeDate,isEdit,
	                        PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT,true,prodAssetRegList);
	            }
                // 根据查询出的登记数据分组进行出单产品报表
                for (String key : prdAssetRegAllMap.keySet()){
                    if (!prodMap.containsKey(key)){
                        prodMap.put(key, key);
                    } else {
                        // 已经出过报表则跳过
                        continue;
                    }
                    List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
                    List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
                    List<PbcMonthReport> prodAssetRegList = new ArrayList<>();
                    if (prdAssetRegAllMap.get(key) != null){
                        prodAssetRegList = prdAssetRegAllMap.get(key);
                    } 
                    pbcMonthAssetReportService.generateMonthAssetDebtReport(assetPosition,prodPosition,executeDate,isEdit,
                            PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT,true,prodAssetRegList);
                }
                // 根据查询出的登记数据分组进行出单产品报表
                for (String key : prdAssetNoRegAllMap.keySet()){
                    if (!prodMap.containsKey(key)){
                        prodMap.put(key, key);
                    } else {
                        // 已经出过报表则跳过
                        continue;
                    }
                    List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
                    List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
                    List<PbcMonthReport> prodAssetRegList = new ArrayList<>();
                    if (prdAssetNoRegAllMap.containsKey(key)){
                        prodAssetRegList = prdAssetNoRegAllMap.get(key);
                        prodAssetRegList.forEach(item->{
                            item.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
                        });
                    }
                    pbcMonthAssetReportService.generateMonthAssetDebtReport(assetPosition,prodPosition,executeDate,isEdit,
                            PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT,true,prodAssetRegList);
                }
			}
        } else{
			// 按产品分头寸
			Map<String,List<PbcMonthAssetPositionDto>> posAssetAllMap = posAssetList.stream().collect(
					Collectors.groupingBy(PbcMonthAssetPositionDto::getPbcProdidAndOrgCode,Collectors.toList()));
            // 按产品分头寸
			Map<String,List<PbcMonthPrdPostionDto>> posProdAllMap = posProdList.stream().collect(
			        Collectors.groupingBy(PbcMonthPrdPostionDto::getPbcProdidAndOrgCode,Collectors.toList()));
            Map<String,List<PbcMonthReport>> prdAssetRegAllMap = prdAssetRaiseRegList.stream().collect(
                    Collectors.groupingBy(PbcMonthReport::getPbcProdidAndOrgCode,Collectors.toList()));
            Map<String,List<PbcMonthReport>> prdAssetNoRegAllMap = prdAssetRaiseNoRegList.stream().collect(
                    Collectors.groupingBy(PbcMonthReport::getPbcProdidAndOrgCode,Collectors.toList()));
			// 记录已经统计过得单产品
			Map<String,String> prodMap = new HashMap<>();
			// 根据查询出的数据分组进行出单产品报表
			for (String key : posAssetAllMap.keySet()){
				if (!prodMap.containsKey(key)){
					prodMap.put(key, key);
				} else {
					// 已经出过报表则跳过
					continue;
				}
				List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
				if (posAssetAllMap.get(key) != null){
				    assetPosition = posAssetAllMap.get(key);
				}
                List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
                if (posProdAllMap.get(key) != null){
                    prodPosition = posProdAllMap.get(key);
                }
                List<PbcMonthReport> prodAssetRegList = new ArrayList<>();
                if (prdAssetRegAllMap.get(key) != null){
                    prodAssetRegList = prdAssetRegAllMap.get(key);
                }
				pbcMonthAssetReportService.generateMonthAssetDebtReport(assetPosition,prodPosition,executeDate,isEdit,
						PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT,true,prodAssetRegList);
			}
			// 根据查询出的数据分组进行出单产品报表
			for (String key : posProdAllMap.keySet()){
				if (!prodMap.containsKey(key)){
					prodMap.put(key, key);
				} else {
					// 已经出过报表则跳过
					continue;
				}
                List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
                if (posAssetAllMap.get(key) != null){
                    assetPosition = posAssetAllMap.get(key);
                }
                List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
                if (posProdAllMap.get(key) != null){
                    prodPosition = posProdAllMap.get(key);
                }
                List<PbcMonthReport> prodAssetRegList = new ArrayList<>();
                if (prdAssetRegAllMap.get(key) != null){
                    prodAssetRegList = prdAssetRegAllMap.get(key);
                }
				pbcMonthAssetReportService.generateMonthAssetDebtReport(assetPosition,prodPosition,executeDate,isEdit,
						PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT,true,prodAssetRegList);
			}
            // 根据查询出的登记数据分组进行出单产品报表
            for (String key : prdAssetRegAllMap.keySet()){
                if (!prodMap.containsKey(key)){
                    prodMap.put(key, key);
                } else {
                    // 已经出过报表则跳过
                    continue;
                }
                List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
                List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
                List<PbcMonthReport> prodAssetRegList = new ArrayList<>();
                if (prdAssetRegAllMap.get(key) != null){
                    prodAssetRegList = prdAssetRegAllMap.get(key);
                }
                pbcMonthAssetReportService.generateMonthAssetDebtReport(assetPosition,prodPosition,executeDate,isEdit,
                        PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT,true,prodAssetRegList);
            }
            // 根据查询出的登记数据分组进行出单产品报表
            for (String key : prdAssetNoRegAllMap.keySet()){
                if (!prodMap.containsKey(key)){
                    prodMap.put(key, key);
                } else {
                    // 已经出过报表则跳过
                    continue;
                }
                List<PbcMonthAssetPositionDto> assetPosition = new ArrayList<>();
                List<PbcMonthPrdPostionDto> prodPosition = new ArrayList<>();
                List<PbcMonthReport> prodAssetRegList = new ArrayList<>();
                if (prdAssetNoRegAllMap.containsKey(key)){
                    prodAssetRegList = prdAssetNoRegAllMap.get(key);
                    prodAssetRegList.forEach(item->{
                        item.setStatus(DictCodeListExt2.报送数据状态_删除.getValue());
                    });
                }
                pbcMonthAssetReportService.generateMonthAssetDebtReport(assetPosition,prodPosition,executeDate,isEdit,
                        PbcReportType.PBC_REPORT_ONEPRD_ASSETDEBT,true,prodAssetRegList);
            }
        }
	}

}