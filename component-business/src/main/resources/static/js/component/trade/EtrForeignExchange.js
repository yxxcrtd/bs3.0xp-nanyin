/**
 *货币对和外汇即远掉期汇率联动
 */
(function() {
    // 汇率精度为5的货币对
    var ccyPairDecimalPoint5 = [ 'HKD/CNY', 'CNY/MYR', 'CNY/AED', 'CNY/SAR', 'CNY/PLN', 'CNY/TRY',
            'EUR/USD', 'GBP/USD', 'USD/CAD', 'USD/CHF', 'AUD/USD', 'USD/HKD', 'USD/SGD', 'NZD/USD',
            'EUR/GBP' ];
    // 汇率精度为3的货币对
    var ccyPairDecimalPoint3 = ['USD/JPY','EUR/JPY'];
    // 汇率精度为2的货币对
    var ccyPairDecimalPoint2 = ['CNY/KRW','CNY/MNT','CNY/KHR'];
    /**
     * 判断v是否在数组arr中
     */
    var isInArr = function(arr,v){
        for(i = 0, len = arr.length;i < len;i++){
            if(arr[i] == v){
                return true;
            }
        }
        return false;
    }
    /**
     * 根据货币对设置汇率精度
     * curPair 货币对页面元素ID
     * price 汇率页面元素ID
     * precisionAdd 在即期标准精度上增加的位数
     */
    var setDecimalPoint = function(curPair,price,precisionAdd){
        var curPair = $('#'+curPair).val();
        var precisionCal = precisionAdd;
        if(isInArr(ccyPairDecimalPoint5,curPair)){
            precisionCal = 5+precisionAdd;
        }
        else if(isInArr(ccyPairDecimalPoint3,curPair)){
            precisionCal = 3+precisionAdd;
        }
        else if(isInArr(ccyPairDecimalPoint2,curPair)){
            precisionCal = 2+precisionAdd;
        }else{
            precisionCal = 4+precisionAdd;
        }
        $('#'+price).attr('data-textrange',precisionCal).trigger('blur');
        return precisionCal;
    }
    /**
     * 货币对非空时，交易货币选择后，自动显示对应货币
     * curPair 货币对页面元素ID
     * dealCur 交易货币页面元素ID
     * corCur 对应货币页面元素ID
     */
    var showCorCcy = function(curPair,dealCur,corCur){        
        var curPairVal = $('#'+curPair).val();
        var dealCurVal = $('#'+dealCur).val();
        if(curPairVal && dealCurVal){
            var curPairValArr = curPairVal.split('/');
            if(curPairValArr[0] == dealCurVal){
                $('#'+corCur).val(curPairValArr[1]).trigger('change');
            }else if(curPairValArr[1] == dealCurVal){
                $('#'+corCur).val(curPairValArr[0]).trigger('change');
            }
        }
    }
    /**
     * 计算预计对应货币金额
     * curPair 货币对页面元素ID
     * dealCur 交易货币页面元素ID
     * price 即期汇率页面元素ID
     * dealCurAmt 交易货币金额页面元素ID
     * corCurAmt 对应货币金额页面元素ID
     * ccyJpy 日元币种数据字典code值
     */
    var calcExpCorAmt = function(curPair,dealCur,price,dealCurAmt,corCurAmt,JPY_CNY){
        // 货币对
        var curPairVal = $('#'+curPair).val();
        // 交易货币
        var dealCurVal = $('#'+dealCur).val(); 
        // 即期汇率
        var priceVal = $('#'+price).val();
        // 交易货币金额
        var dealCurAmtVal = $('#'+dealCurAmt).val(); 
        if(curPairVal && dealCurVal && priceVal && dealCurAmtVal){
            // 计算对应货币金额
            if(curPairVal.indexOf(dealCurVal) == 0){
                var corCurAmtVal = JoyinMath.doMul(priceVal,dealCurAmtVal);
                if(curPairVal == JPY_CNY){
                    $('#'+corCurAmt).val(JoyinMath.doDiv(corCurAmtVal,100)).trigger('blur');
                }else{
                    $('#'+corCurAmt).val(corCurAmtVal).trigger('blur');
                }
            }else if(curPairVal.indexOf(dealCurVal) > 0){
                var corCurAmtVal = JoyinMath.doDiv(dealCurAmtVal,priceVal);
                if(curPairVal == JPY_CNY){
                    $('#'+corCurAmt).val(JoyinMath.doMul(corCurAmtVal,100) ).trigger('blur');
                }else{
                    $('#'+corCurAmt).val(corCurAmtVal).trigger('blur');
                }
            }
            
        }else{
            $('#'+corCurAmt).val('').trigger('blur');
        }
        
    }
    
    /**
     * curPair 货币对控件id
     * calendarId 交易日历控件id
     */
    var getCalendarIdByCurPair = function(curPair,calendarId){        
        var curPairVal = $('#'+curPair).val();
    	if(curPairVal){
            var data = {curPair:curPairVal};
            Joyin.ajaxJson(basePath + 'component/trade/BussCalendar/selectCalendarIdByCurPair',data,
                    function(result) {
            	if(result!=null){
                	$('#'+calendarId).textSelect('setValue',result.calendarId);
            	}
            });
    	}
    }
    
    
    // 外汇共通组件公开接口
    if(window.EtrForeingExchange == undefined){
        window.EtrForeingExchange = {};
    }
    window.EtrForeingExchange = {
        setDecimalPoint : setDecimalPoint,
        showCorCcy:showCorCcy,
        calcExpCorAmt:calcExpCorAmt,
        getCalendarIdByCurPair:getCalendarIdByCurPair
    };
}());