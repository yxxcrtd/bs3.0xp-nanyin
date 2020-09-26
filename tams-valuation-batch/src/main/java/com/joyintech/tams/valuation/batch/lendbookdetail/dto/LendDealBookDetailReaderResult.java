/*
 * 文件名：TradeDetail.java 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved. 描述：中信银行资管系统
 * 修改人：qiangchengxi 修改时间：2016年12月24日 修改内容：新建 系统名称：中信银行资管系统
 */

package com.joyintech.tams.valuation.batch.lendbookdetail.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.framework.json.CustomDateDeserialize;

/**
 * 拆借交易批處理reader返回結果DTO
 * 
 * @author 吴德宝
 * @version 1.0 2018年04月26日
 * @see LendDealBookDetailReaderResult
 * @since 1.0
 */
public class LendDealBookDetailReaderResult  extends NeedOperateEntity implements Serializable  {
    
    private static final long serialVersionUID = 5711895055346932283L;

    /**
     * 账套代码
     */
    private String booksetId;
    
    /**
     * 发生日期
     */
    private Date happenDate;

    /**
     * 入账日期
     */
    private Date bookDate;
    
    //交易登账用交易信息-交易登账
    private List<LendDealDetailDTO> listLendDealDTO = new ArrayList<LendDealDetailDTO>();
    
    //交易计提用信息
    private List<LendDealDetailDTO> listLendMatureDTO = new ArrayList<LendDealDetailDTO>();
    
    /**
     * 取得 账套代码
     * @return 账套代码
     */
    public String getBooksetId() {
        return booksetId;
    }

    /**
     * 设置 账套代码
     * @param booksetId 账套代码
     */
    public void setBooksetId(String booksetId) {
        this.booksetId = booksetId == null ? null : booksetId.trim();
    }
    
    /**
     * 取得 发生日期
     * @return 发生日期
     */
    public Date getHappenDate() {
        return happenDate;
    }

    /**
     * 设置 发生日期
     * @param happenDate 发生日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setHappenDate(Date happenDate) {
        this.happenDate = happenDate;
    }

    /**
     * 取得 入账日期
     * @return 入账日期
     */
    public Date getBookDate() {
        return bookDate;
    }

    /**
     * 设置 入账日期
     * @param bookDate 入账日期
     */
    @JsonDeserialize(using = CustomDateDeserialize.class)
    public void setBookDate(Date bookDate) {
        this.bookDate = bookDate;
    }

    //交易登账用交易信息 --到期登账
    public List<LendDealDetailDTO> getListLendDealDTO() {
       return listLendDealDTO;
    }

    //交易登账用交易信息 设值
    public void setListLendDealDTO(List<LendDealDetailDTO> listLendDealDTO) {
       this.listLendDealDTO = listLendDealDTO;
    }

    //交易计提用信息 取值
    public List<LendDealDetailDTO> getListLendMatureDTO() {
       return listLendMatureDTO;
    }

    //交易计提用信息 设值
    public void setListLendMatureDTO(List<LendDealDetailDTO> listLendMatureDTO) {
       this.listLendMatureDTO = listLendMatureDTO;
    }
    
}
