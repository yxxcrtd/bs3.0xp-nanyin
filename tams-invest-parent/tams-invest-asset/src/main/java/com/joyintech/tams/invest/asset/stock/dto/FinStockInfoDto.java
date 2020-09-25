/*
 * 文件名：FinPlanAssetCDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2019年1月7日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.asset.stock.dto;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.framework.validation.annotation.Length;
import com.joyintech.framework.validation.annotation.NotEmpty;
import com.joyintech.framework.validation.annotation.ValidLogic;
import com.joyintech.framework.validation.groups.ValidateGroupDelete;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.tams.common.base.entity.SysUploadFileInfo;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.asset.dto.FinInterestFactorDto;
import com.joyintech.tams.component.business.asset.dto.FinPayInterestStdInfoDto;
import com.joyintech.tams.component.business.core.dto.FileCommonDto;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepEquityInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.asset.common.dto.BaseFinProductBaseInfoDto;
import com.joyintech.tams.invest.asset.common.dto.BaseRepAssetDeptInfoDto;
import com.joyintech.tams.invest.asset.common.dto.BaseRepAssetEquityInfoDto;
import com.joyintech.tams.invest.asset.common.dto.BaseRepOtherInvestTargetInfoDto;
import com.joyintech.tams.invest.asset.stock.valid.FinStockInfoValidator;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.annotation.PrimaryKey.Field;
import com.joyintech.workflow.engine.dto.ProcessEntity;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2019年1月7日
 * @see FinStockInfoDto
 * @since 1.0
 */
@PageId("FinStockInfo002")
@ValidLogic(targetClass = FinStockInfoValidator.class,groups = {ValidateGroupEdit.class,
    ValidateGroupDelete.class})//进入validator（check）的地方
public class FinStockInfoDto extends FinProduct implements ProcessEntity{

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = 1L;
    /**
     * 操作类型
     */
    private String operateType;    
    /**
     * 界面类型
     */
    private String pageModel; 
    /**
     * 画面元素
     */
    private String elementId;
    /**
     * 选中的数据列表
     */
    private transient List<FinStockInfoDto> selectedItem;
    private List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList;
    /**
     * 付息信息DTO
     */
    @Valid
    private FinPayInterestStdInfoDto finPayInterestStdInfoDto;
    /**
     * 资产代码
     */

    /**
     * 上市板
     */
    private String board;

    /**
     * 是否沪港通或深港通
     */
    private String shOrZh;
    /**
     * 是否融资融券标的
     */

    private String isMarginFinprod;

    /**
     * 公司名称
     */
    private String customerName;
    /**
     * 当日流通总股本
     */
    private String sShares;
    /**
     * 当日流通股本
     */
    private String cShares;
    /**
     * 资产代码后缀
     */
    private String tradeMarketId;

    /**
     * 附件信息DTO
     */
    private List<FileCommonDto> fileCommonList;

    /**
     * 附件信息DTO
     */
    private List<SysUploadFileInfo> fileArray;

    /**
     * 附件
     */
    private MstFileInputParameter mstFileInputParameter;

    /**
     * 计息要素DTO
     */
    @Valid
    private FinInterestFactorDto finInterestFactorDto;
    /**
     * 业务组件--权益类中债报送
     */
    @Valid
    private BussRepEquityInfoDto bussRepEquityInfoDto;
    /**
     * 权益类资产中债报送
     */
    private BaseRepAssetEquityInfoDto baseRepAssetEquityInfoDto;
    /**
     * 其他资产报送信息
     */
    private BaseRepOtherInvestTargetInfoDto baseRepOtherInvestTargetInfoDto;
    /**
     * 负债信息
     */
    private BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto;
    /**
     * 真实finproductid
     */
    private String realFinProductId;
    // 增加 二级，三级，四级，五级分类by赵东东 2019.10.31 start 
    /**
     * 二级分类
     */
    private String type9;
    /**
     * 三级分类
     */
    private String type10;
    /**
     * 四级分类
     */
    private String type11;
    /**
     * 五级分类
     */
    private String type12;
    /**
     * 获取type9
     * @return
     */
    public String getType9() {
		return type9;
	}
     /**
      * 设置type9
      * @param type9
      */
	public void setType9(String type9) {
		this.type9 = type9;
	}
     /**
      * 获取type10
      * @return
      */
	public String getType10() {
		return type10;
	}
     /**
      * 设置type10
      * @param type10
      */
	public void setType10(String type10) {
		this.type10 = type10;
	}
     /**
      * 获取type11
      * @return
      */
	public String getType11() {
		return type11;
	}
     /**
      * 设置type11
      * @param type11
      */
	public void setType11(String type11) {
		this.type11 = type11;
	}
     /**
      * 获取type12
      * @return
      */
	public String getType12() {
		return type12;
	}
     /**
      * 设置type12
      * @param type12
      */
	public void setType12(String type12) {
		this.type12 = type12;
	}
	// 增加 二级，三级，四级，五级分类by赵东东 2019.10.31 end
	/**
     * 获取 operateType
     *
     * @return operateType.
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置 operateType
     * @param operateType operateType
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }
    
    /**
     * 获取 pageModel
     * @return pageModel.
     */
    public String getPageModel() {
        return pageModel;
    }
    
    /**
     * 设置 pageModel
     * @param pageModel pageModel
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }
    
    /**
     * 获取 elementId
     * @return elementId.
     */
    public String getElementId() {
        return elementId;
    }
    
    /**
     * 设置 elementId
     * @param elementId elementId
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }
    
    /**
     * 获取 selectedItem
     * @return selectedItem.
     */
    public List<FinStockInfoDto> getSelectedItem() {
        return selectedItem;
    }
    
    /**
     * 设置 selectedItem
     * @param selectedItem selectedItem
     */
    public void setSelectedItem(List<FinStockInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }



    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public List<BaseFinProductBaseInfoDto> getBaseFinProductBaseInfoList() {
        return baseFinProductBaseInfoList;
    }

    public void setBaseFinProductBaseInfoList(List<BaseFinProductBaseInfoDto> baseFinProductBaseInfoList) {
        this.baseFinProductBaseInfoList = baseFinProductBaseInfoList;
    }

    public FinPayInterestStdInfoDto getFinPayInterestStdInfoDto() {
        return finPayInterestStdInfoDto;
    }

    public void setFinPayInterestStdInfoDto(FinPayInterestStdInfoDto finPayInterestStdInfoDto) {
        this.finPayInterestStdInfoDto = finPayInterestStdInfoDto;
    }

    public FinInterestFactorDto getFinInterestFactorDto() {
        return finInterestFactorDto;
    }

    public void setFinInterestFactorDto(FinInterestFactorDto finInterestFactorDto) {
        this.finInterestFactorDto = finInterestFactorDto;
    }

    /**
     * 取得 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
     * @return 市场代码，可以按规则生成，或者手输，不校验唯一。对于理财产品分层，同母金融产品代码对应的市场代码。
     */
    @Override
   // @Pattern(fieldName = "FinStockInfo002.finprodMarketId",regx = "^(?=.*[a-zA-Z]+)(?=.*[0-9]+)[a-zA-Z0-9]+$",message = "FW_E_0002",groups={ValidateGroupEdit.class})
    @NotEmpty(fieldName = "FinStockInfo002.finprodMarketId",groups={ValidateGroupEdit.class})//非空校验
    public String getFinprodMarketId() {
        return super.getFinprodMarketId();
    }
    /**
     * 取得 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     * @return 金融产品类型（投管分类），债券、理财产品、基金、理财产品模板、理财产品分层
     */

//    @PrimaryKey(Field.PK_2)
//    @PrimaryKey(Field.PK_2)
    public String getFinprodType2Str() {
        return DictCodeListExt1.金融产品类型_投管用_股票.getValue();
    }


    
    @PrimaryKey(Field.PK_1)
    public String getFinprodIdStr() {
        return DictCodeListExt1.金融产品类型_投管用_股票.getValue()+"_"+getFinprodMarketId();
    }

    /**
     * 取得 起息日
     * @return 起息日
     */
    @NotEmpty(fieldName = "FinStockInfo002.vDate",groups={ValidateGroupEdit.class})//非空校验
    public Date getVdate() {
        return super.getVdate();
    }

    /**
     * 取得 交易日历
     * @return 交易日历
     */
    public Date getMdate() {
        return super.getMdate();
    }


    /**
     * 取得 公司名称
     * @return 公司名称
     */
    @NotEmpty(fieldName = "FinStockInfo002.finCompanyName",groups={ValidateGroupEdit.class})//非空校验
    public String getIssuer() {
        return super.getIssuer();
    }

    /**
     * 资产代码非空校验
     * @return
     */
//    @Override
 //   @NotEmpty(fieldName = "FinStockInfo002.finProdMarketId",groups={ValidateGroupEdit.class})//非空校验
//    public String getFinprodMarketId() {
//        return super.getFinprodMarketId();
//    }
    /**
     * 资产名称非空校验
     * @return
     */
    @Override
   @NotEmpty(fieldName = "FinStockInfo002.finprodAbbr",groups={ValidateGroupEdit.class})//非空校验
    public String getFinprodName() {
        return super.getFinprodName();
    }

    @Override
    @NotEmpty(fieldName = "FinStockInfo002.finprodAbbr", groups = {ValidateGroupEdit.class})
    @Length(fieldName = "FinStockInfo002.finprodAbbr", max = 100, groups = {
        ValidateGroupEdit.class})
    public String getFinprodAbbr() {
        return super.getFinprodAbbr();
    }

    /**
     * 公司名称非空校验
     * @return
     */
//    @Override
//    @NotEmpty(fieldName = "FinStockInfo002.customerName",groups={ValidateGroupEdit.class})//非空校验
//    public String getCustomerName() {
//        return super.getCustomerName();
//    }
    /**
     * 交易日历非空校验
     * @return
     */
    @NotEmpty(fieldName = "FinStockInfo002.calendarId", groups = {ValidateGroupEdit.class})
    public String getCalendarId() {
        return super.getCalendarId();
    }
    /**
     * 上市板
     * @return
     */
    @NotEmpty(fieldName = "FinStockInfo002.board",groups={ValidateGroupEdit.class})//上市板非空校验
    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }
    /**
     * 将币种对应的数据字典代码转换为数据字典值
     * @return 数据字典值
     */
    public String getBoardName() {
        String name=SysDataUtil.getNameFromDataDict(DictCode.上市板, this.getBoard());
        return name;

    }
    /**
     * 交易场所
     * @return
     */
    @NotEmpty(fieldName = "FinStockInfo002.tradeMarket",groups={ValidateGroupEdit.class})//交易场所非空校验
    public String getTradeMarket() {
        return super.getTradeMarket();
    }
    public String getTradeMarketAbbr(){
        return SysDataUtil.getNameFromDataDict(DictCode.交易场所, this.getTradeMarket());
    }
    /**
     * 币种
     * @return
     */
    @NotEmpty(fieldName = "FinStockInfo002.currency",groups={ValidateGroupEdit.class})//币种非空校验
    public String getCcy() {
        return super.getCcy();
    }
    public String getCcyAbbr(){
        return SysDataUtil.getNameFromDataDict(DictCode.币种, this.getCcy());
    }
    /**
     * 是否沪港通或深港通
     * @return
     */
    @NotEmpty(fieldName = "FinStockInfo002.shOrZh",groups={ValidateGroupEdit.class})//是否沪港通或深港通非空校验

    public String getShOrZh() {
        return shOrZh;
    }

    public void setShOrZh(String shOrZh) {
        this.shOrZh = shOrZh;
    }

    /**
     * 是否沪港通或深港通的数据字典转换
     * @return
     */
    public String getShOrZhName() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否沪港通或深港通, this.getShOrZh());
    }

    /**
     *是否融资融券标的
     * @return
     */
    @NotEmpty(fieldName = "FinStockInfo002.isMarginFinprod",groups={ValidateGroupEdit.class})//是否融资融券标的非空校验
    public String getIsMarginFinprod() {
        return isMarginFinprod;
    }


    public void setIsMarginFinprod(String isMarginFinprod) {
        this.isMarginFinprod = isMarginFinprod;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     *是否融资融券标的的数据字典转换
     * @return
     */
    public String getIsMarginFinprodName() {
        return SysDataUtil.getNameFromDataDict(DictCode.是否, this.getIsMarginFinprod());
    }

    public String getsShares() {
        return sShares;
    }

    public void setsShares(String sShares) {
        this.sShares = sShares;
    }

    public String getcShares() {
        return cShares;
    }

    public void setcShares(String cShares) {
        this.cShares = cShares;
    }
    /**
     * 获取 bussRepEquityInfoDto
     * @return bussRepEquityInfoDto.
     */
    public BussRepEquityInfoDto getBussRepEquityInfoDto() {
        return bussRepEquityInfoDto;
    }


    /**
     * 设置 bussRepEquityInfoDto
     * @param bussRepEquityInfoDto bussRepEquityInfoDto
     */
    public void setBussRepEquityInfoDto(BussRepEquityInfoDto bussRepEquityInfoDto) {
        this.bussRepEquityInfoDto = bussRepEquityInfoDto;
    }


    /**
     * 获取 baseRepAssetEquityInfoDto
     * @return baseRepAssetEquityInfoDto.
     */
    public BaseRepAssetEquityInfoDto getBaseRepAssetEquityInfoDto() {
        return baseRepAssetEquityInfoDto;
    }


    /**
     * 设置 baseRepAssetEquityInfoDto
     * @param baseRepAssetEquityInfoDto baseRepAssetEquityInfoDto
     */
    public void setBaseRepAssetEquityInfoDto(BaseRepAssetEquityInfoDto baseRepAssetEquityInfoDto) {
        this.baseRepAssetEquityInfoDto = baseRepAssetEquityInfoDto;
    }


    /**
     * 获取 baseRepOtherInvestTargetInfoDto
     * @return baseRepOtherInvestTargetInfoDto.
     */
    public BaseRepOtherInvestTargetInfoDto getBaseRepOtherInvestTargetInfoDto() {
        return baseRepOtherInvestTargetInfoDto;
    }


    /**
     * 设置 baseRepOtherInvestTargetInfoDto
     * @param baseRepOtherInvestTargetInfoDto baseRepOtherInvestTargetInfoDto
     */
    public void setBaseRepOtherInvestTargetInfoDto(BaseRepOtherInvestTargetInfoDto baseRepOtherInvestTargetInfoDto) {
        this.baseRepOtherInvestTargetInfoDto = baseRepOtherInvestTargetInfoDto;
    }


    /**
     * 获取 baseRepAssetDeptInfoDto
     * @return baseRepAssetDeptInfoDto.
     */
    public BaseRepAssetDeptInfoDto getBaseRepAssetDeptInfoDto() {
        return baseRepAssetDeptInfoDto;
    }


    /**
     * 设置 baseRepAssetDeptInfoDto
     * @param baseRepAssetDeptInfoDto baseRepAssetDeptInfoDto
     */
    public void setBaseRepAssetDeptInfoDto(BaseRepAssetDeptInfoDto baseRepAssetDeptInfoDto) {
        this.baseRepAssetDeptInfoDto = baseRepAssetDeptInfoDto;
    }

    public String getTradeMarketId() {
        return tradeMarketId;
    }

    public void setTradeMarketId(String tradeMarketId) {
        this.tradeMarketId = tradeMarketId;
    }

    /**
     * 获取 fileCommonList
     * @return fileCommonList.
     */
    public List<FileCommonDto> getFileCommonList() {
        return fileCommonList;
    }



    /**
     * 设置 fileCommonList
     * @param fileCommonList fileCommonList
     */
    public void setFileCommonList(List<FileCommonDto> fileCommonList) {
        this.fileCommonList = fileCommonList;
    }

    /**
     * 获取 mstFileInputParameter
     * @return mstFileInputParameter.
     */
    public MstFileInputParameter getMstFileInputParameter() {
        return mstFileInputParameter;
    }



    /**
     * 设置 mstFileInputParameter
     * @param mstFileInputParameter mstFileInputParameter
     */
    public void setMstFileInputParameter(MstFileInputParameter mstFileInputParameter) {
        this.mstFileInputParameter = mstFileInputParameter;
    }


    /**
     * 获取 fileArray
     * @return fileArray.
     */
    public List<SysUploadFileInfo> getFileArray() {
        return fileArray;
    }



    /**
     * 设置 fileArray
     * @param fileArray fileArray
     */
    public void setFileArray(List<SysUploadFileInfo> fileArray) {
        this.fileArray = fileArray;
    }

	public String getRealFinProductId() {
		return realFinProductId;
	}

	public void setRealFinProductId(String realFinProductId) {
		this.realFinProductId = realFinProductId;
	}


}