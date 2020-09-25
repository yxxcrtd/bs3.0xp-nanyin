package com.joyintech.tams.invest.common.trade;

import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import com.joyintech.tams.invest.common.trade.dao.CreditLinesDealsMapper;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.CreditLinesDealCommon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *  平安银行占用、释放授信额度处理模块
 * Add by xkni 2019-09-12
 * @return
 */
public class PaCelinesUpdate extends  CelinesUpdateFactory {
    @Autowired
    private CreditLinesDealsMapper creditLinesMapper;
    /**
     * 占用、释放授信额度共通处理
     * @param inputmodel 交易model
     * @param isNotBottomflag 是否是底层资产交易
     * @param isSave 是否入库
     * @param isdeleteflag 是否是作废表示
     * 代码结构调整新增 by xkni 2019-08-23
     * @return
     */
    @Override
    @Transactional(rollbackFor=Exception.class, propagation = Propagation.REQUIRES_NEW)
    public List<CreditLinesDealCommon> isNeedOccupyCre(AllTradeCommonInputDto inputmodel, boolean isNotBottomflag, boolean isSave, Boolean isdeleteflag) {
        super.inputmodel=inputmodel;
        super.isNotBottomflag=isNotBottomflag;
        super.isSave=isSave;
        super.isdeleteflag=isdeleteflag;
        super.creditLinesMapper=creditLinesMapper;
        HashMap<String, Object> map=new HashMap<>();
        List<CreditLines> list=new ArrayList<>();// 品种授信列表
        map.put("creLineslist",list);// TOdo 待实现内容List<CreditLines> list 自定义品种授信
       //自定义品种授信
        super.getVarietiesCeLineList(map);
        AllTradeCommonInputDto allTradeCommonInputDto=new AllTradeCommonInputDto();
        BeanUtil.copyPropertiesIgnoreNull(allTradeCommonInputDto,inputmodel);
        return super.isNeedOccupyCre(allTradeCommonInputDto,isNotBottomflag,isSave,isdeleteflag);
    }
    //重写 父类的checkData 方法,业务相同的话也可以不重写，直接用父类的实现逻辑
    @Override
    public List<CreditLinesDealCommon> checkData() {
        List<CreditLinesDealCommon> list = new ArrayList<CreditLinesDealCommon>();
        //执行父类方法，也可不执行
        super.checkData();
        //实现自己的差异性业务逻辑
        return  list;
    }


}
