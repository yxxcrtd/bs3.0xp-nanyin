package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jixu.common.type.ResultInterface;
import com.jixu.joyin2jixu.service.Joyin2JixuInterface;
import com.jixu.msg.dto.JJBkCashAcctDto;
import com.jixu.msg.dto.JJPortfolioDto;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.tams.valuation.common.interf.base.batch.dao.ValFinProductAcctBatchMapper;
import com.joyintech.tams.valuation.common.interf.base.constant.FinalString;
import com.joyintech.tams.valuation.common.interf.base.dto.ValBooksetInfoDto;
import com.joyintech.tams.valuation.common.interf.base.service.common.InterfCommonService;
import com.joyintech.tams.valuation.common.interf.tempTables.entity.ValSubjectInfo;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.standard.entity.StandardInfoProcessorResult;


/**
 * 资金账户信息接口
 * 对资金账户息筛选处理，供给jixu使用
 * @author chufengjie
 * @version 1.0 2018年5月22日
 * @see StandardInfoRecjxService
 * @since 1.0
 */
@Service
public class StandardInfoRecjxService {
	
    /**
     * 接口共通处理Service
     */
    @Autowired
    private InterfCommonService interfCommonService;
    
    /**
     * 几许为joyin调用接口，新建产品等信息处理Service
     */
    @Autowired
    private Joyin2JixuInterface joyin2JixuInterface;
    
    /**
     * 金融产品账户BatchMapper
     */
    @Autowired
    private ValFinProductAcctBatchMapper valFinProductAcctBatchMapper;
	
	
	public void prodInfoJx(StandardInfoProcessorResult  result) {
		List<ValSubjectInfo> listValSubjectInfo = result.getListValSubjectInfo();
	       List<JJBkCashAcctDto> jjBkCashAcctDtoList = new ArrayList<JJBkCashAcctDto>(); 	       
	        // 获取基础信息Dto
	        for (ValSubjectInfo dto : listValSubjectInfo) {
	            //调用共通设计->【详细设计-接口共通方法设计.xlsx】->账套信息(根据产品代码)
                String layeringId = dto.getSubjectId();
                layeringId = layeringId+
                		FinalString.LAYERING_SPLIT+
                		dto.getSubjectType();
                List<ValBooksetInfoDto> valBooksetInfoDtoList = interfCommonService.getBokBooksetByProdId_Layering(layeringId);
            	for(ValBooksetInfoDto booksetInfoDto:valBooksetInfoDtoList) {
    	        	JJBkCashAcctDto jjBkCashAcctDto = getJjPortfolio(booksetInfoDto.getBooksetId(),dto);
    	            if(jjBkCashAcctDto != null){
    	            	jjBkCashAcctDtoList.add(jjBkCashAcctDto);
    	            } 
				}	        	
	        } 	        
            ResultInterface resultInterface= joyin2JixuInterface.updateBkCashAccts(jjBkCashAcctDtoList);
            List<String> list = resultInterface.getErrorMsgs();
	   }
	
     /**
      *  几许数据初始化
      */
    private JJBkCashAcctDto getJjPortfolio(String booksetId,ValSubjectInfo dto){
    	JJBkCashAcctDto jjBkCashAcctDto = new JJBkCashAcctDto();
    	//银行账户代码
    	jjBkCashAcctDto.setAcct_no(dto.getActNo());
    	//查询信息
    	StlCapacctInfo StlCapacctInfo = valFinProductAcctBatchMapper.getStlCapacctInfo(dto.getActNo(),dto.getCcy());
    	//银行账户名
    	jjBkCashAcctDto.setAcct_name(StlCapacctInfo.getAcctName());
    	//账套名称
    	jjBkCashAcctDto.setBookset_id(booksetId); 
    	//银行信息
    	jjBkCashAcctDto.setBank_dep(StlCapacctInfo.getBankDep());
    	//货币
    	jjBkCashAcctDto.setCcy(StlCapacctInfo.getCcy());
        //账户   	
    	jjBkCashAcctDto.setAcc_type(dto.getActType());
    	 
         return jjBkCashAcctDto;
    }	
	

}
