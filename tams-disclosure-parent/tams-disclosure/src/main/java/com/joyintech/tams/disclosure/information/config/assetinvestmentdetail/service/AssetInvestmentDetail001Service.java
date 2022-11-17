package com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.dao.AssetInvestmentDetailMapper;
import com.joyintech.tams.disclosure.information.config.assetinvestmentdetail.dto.AssetInvestmentDetail001Dto;
import com.joyintech.tams.systemtools.dfs.JoyinDFSTemplate;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class AssetInvestmentDetail001Service extends CommonExportService<AssetInvestmentDetail001Dto> implements BaseProcessService<AssetInvestmentDetail001Dto> {

    @Autowired
    private AssetInvestmentDetailMapper assetInvestmentDetailMapper;

    @Autowired
    private JoyinDFSTemplate joyinDFSTemplate;

    public Page<AssetInvestmentDetail001Dto> selectForPage(Map<String, Object> param) {
        String prodId = (String)param.get("prodId");
        if(StringUtil.isNotEmpty(prodId)){
            param.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA,prodId));
        }
        Page<AssetInvestmentDetail001Dto> result = queryForPage(Integer.parseInt(param.get("page").toString()), Integer.parseInt(param.get("length").toString()), ()
                -> assetInvestmentDetailMapper.select(param));
        return result;
    }



    @Override
    public void doComplete(AssetInvestmentDetail001Dto dto, OperationType operationType) {
        dto.setUpdateTime(DateUtil.now());
        dto.setOperateType(operationType.toString());
        String type = dto.getOperateType();
        if (StringUtil.equals(type, "DELETE")) {
            HashMap map =new HashMap();
            map.put("prodId",dto.getProdId());
            map.put("pubDate",dto.getPubDateStr());
            map.put("assetCode",dto.getAssetCode());
            AssetInvestmentDetail001Dto dto1 = assetInvestmentDetailMapper.selectByPrimaryKey(map);
            dto1.setUpdateUser(LoginUserUtil.getUserName());
            dto1.setStatus("D");
            assetInvestmentDetailMapper.updateByKey(dto1);
        }
        else if (StringUtil.equals(type, "NEW")) {
            HashMap map =new HashMap();
            map.put("prodId",dto.getProdId());
            map.put("pubDate",dto.getPubDateStr());
            map.put("assetCode",dto.getAssetCode());
            AssetInvestmentDetail001Dto dto1 = assetInvestmentDetailMapper.selectByPrimaryKey(map);
            if (dto1!=null && !StringUtil.equals(dto1.getStatus(),"D")){
                dto.setStatus("E");
                dto.setErrormsg("");
                dto.setUpdateUser(CommonUtil.getUserName());
                assetInvestmentDetailMapper.updateByKey(dto);
            }
            else {
                AssetInvestmentDetail001Dto assetInvestmentDetail001Dto = new AssetInvestmentDetail001Dto();
                BeanUtil.copyPropertiesIgnoreNull(assetInvestmentDetail001Dto, dto);
                assetInvestmentDetail001Dto.setStatus("E");
                assetInvestmentDetail001Dto.setUpdateUser(CommonUtil.getUserName());
                assetInvestmentDetailMapper.insert(assetInvestmentDetail001Dto);
            }
        }
        else {
            dto.setStatus("E");
            dto.setErrormsg("");
            dto.setUpdateUser(CommonUtil.getUserName());
            assetInvestmentDetailMapper.updateByKey(dto);
        }

    }




    @Override
    public Page<AssetInvestmentDetail001Dto> selectPageData(Map<String, Object> selectParam) {
        return this.selectForPage(selectParam);
    }



    public List<SelectOptionDto> findProdId(Map<String, Object> map) {
        return assetInvestmentDetailMapper.findProdId(map);
    }



    public Page<SelectOptionDto> getProdIdlist(Map map) {
        Map<String, Object> pageParam = RequestUtil.getPageParam();
        return this.queryForPageForSelectReturn(Integer.parseInt((String) pageParam.get("page")), Integer.parseInt((String) pageParam.get("length")),()->assetInvestmentDetailMapper.getProdIdlist(map));
    }


    /**
     * 分批查询
     * @param
     * @return
     */
    /*private List<AccountAmount001Dto> selectBalanceByKey(List<AccountAmount001Dto> dataList) {
        int maxDataCountOneTime = 1000;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
            return accountAmountMapper.selectBalanceList(dataList);
        }
        // 导入大于1000时，返回值
        List<AccountAmount001Dto> equtiyList = new ArrayList<>();

        List<AccountAmount001Dto> newDataList = new ArrayList<>(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        List<AccountAmount001Dto> listTemp;
        for (int i = 0; i < part; i++ ) {
            listTemp = accountAmountMapper.selectBalanceList(newDataList.subList(0, maxDataCountOneTime));
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            listTemp = accountAmountMapper.selectBalanceList(newDataList);
            // 不为空，放入返回list中
            if (CollectionUtils.isNotEmpty(listTemp)) {
                equtiyList.addAll(listTemp);
            }
        }
        return equtiyList;
    }*/


    public AssetInvestmentDetail001Dto selectByPrimaryKey(Map map) {
        return assetInvestmentDetailMapper.selectByPrimaryKey(map);
    }
}