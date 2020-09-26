$(function() {

    //数字改变-验证绑定事件 ------------ 开始
    var maxNumComm = 999.99999999;//验证共通最大值
    var minNumComm = 0.00000001;//验证共通最小值
    //剩余期限
    $('#lastPeriod').change(function() {
        checkNum('lastPeriod',maxNumComm ,minNumComm, true);
    });
    //市价全价
    $('#price').change(function() {
        checkNum('price', maxNumComm, minNumComm, false);
    });
    //市价净价    
    $('#netprice').change(function() {
        checkNum('netprice', maxNumComm, minNumComm, false);
    });
    //市价久期    
    $('#mDuration').change(function() {
        checkNum('mDuration', maxNumComm, minNumComm, true);
    });
    //市价凸性
    $('#mConvexity').change(function() {
        checkNum('mConvexity', maxNumComm, minNumComm, true);
    });
    //基点价值
    $('#bpvalue').change(function() {
        checkNum('bpvalue', maxNumComm, minNumComm, true);
    });
    //利差久期
    $('#sduration').change(function() {
        checkNum('sduration', maxNumComm, minNumComm, true);
    });
    //利差凸性
    $('#scnvxty').change(function() {
        checkNum('scnvxty', maxNumComm, minNumComm, true);
    });
    //利率久期
    $('#interestDuration').change(function() {
        checkNum('interestDuration', maxNumComm, minNumComm, true);
    });
    //利率凸性
    $('#interestCnvxty').change(function() {
        checkNum('interestCnvxty', maxNumComm, minNumComm, true);
    });
    //市价收益率
    $('#bpyield').change(function() {
        checkNum('bpyield', maxNumComm, minNumComm, true);
    });
    //数字改变-绑定事件  ------------ 结束
    
    /**
     * 资产代码联动修改债券简称
     */
    $("#secId").change(function(){
    	var arr = $("#secId").find("option:selected").text().match(/\(.+?\)/g);
    	if(arr != null ){
    		$("#finprodAbbr").val(arr[0].replace(/\(|\)/g,''));
    	}
    });
    
    /**
     * 点击提交按钮触发事件
     */
    $("#ValBondValuationInfo003_btn_submit").click(function(){
        submit('submit');
    });
    /**
     * 点击保存按钮触发事件
     */
    $("#ValuationMethodScheme002_btn_save").click(function(){
        submit('save');
    });
    
});

/**
 * 表单提交
 * @returns
 */
function submit(operateType){
    if(pageModel == '1'){//修改
        $("#secId").attr('disabled',false);
    }
    var object = $.serializeToObject('#submitForm');
    
    object.pageModel = pageModel;
    if(pageModel == '1'){//修改
        object.valueSourceUpd = object.valueSource;
        object.valueDateUpd = object.valueDate;
        object.valueDateBfUpd = $('#valueDateBfUpd').val();
        object.valueSourceBfUpd = $('#valueSourceBfUpd').val();
        $("#secId").attr('disabled',true);
    }
    
    //必填项检查
    Joyin.ajaxJson(basePath + 'valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo003/check', object, function(result) {
        //启动流程
        if(operateType === 'save'){
            WorkFlow.doSaveList(
                    basePath + 'valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo003/doCommit', 
                    object,
                    "ValBondValuationInfo003_btn_save");
        }else{
            WorkFlow.doSubmitList(
                    basePath + 'valuation/basedata/marketdata/bondvaluationinfo/ValBondValuationInfo003/doCommit', 
                    object, 
                    'ValBondValuationInfo003_btn_submit',
                    'ValBondValuationInfo003',
                    function (data) {
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }
            );
        }
    });
}

/**
 * 数据校验
 * @param id 数据input的id
 * @param maxNum 符合条件的最大数据
 * @param minNum 符合条件的最小数据
 * @param canBlank 是否可以为空boolean
 * @returns
 */
function checkNum(id,maxNum, minNum, canBlank) {
    // 数值
    var num = $('#' + id).val();
    if (num == '' && canBlank) {
        return true;
    }
    // lable名称，去必填星号
    var labelName = $('#' + id).parent().prev().text().replace('*', '');
    var errmsg;
    if (num == '' && !canBlank) {
        errmsg = labelName + "不能为空。"
    }else if (isNaN(num)) {
        errmsg = labelName + "不能为非数字。"
    } else if (num > maxNum) {
        errmsg = labelName + "不能大于" + maxNum + "。";
    } else if (num < minNum) {
        errmsg = labelName + "不能小于" + toNumberStr(minNum,$("#"+id).attr('data-textrange')) + "。";
    }
    if(errmsg){
        Joyin.error(JoyinUtil.getMessage(errmsg));
        $('#' + id).val("");
        return false;
    }
    return true;
}

/**
 * 小数科学记数转换成字符串
 * @param num 小数科学记数
 * @param digits 小数位数
 * @returns
 */
function toNumberStr(num,digits) {
    // 正则匹配小数科学记数法
    if (/^(\d+(?:\.\d+)?)(e)([\-]?\d+)$/.test(num)) {
        // 正则匹配小数点最末尾的0
        var temp=/^(\d{1,}(?:,\d{3})*\.(?:0*[1-9]+)?)(0*)?$/.exec(num.toFixed(digits)) ;
        if(temp){
            return temp[1];
        }else{
            return num.toFixed(digits)
        }
    }else{
        return ""+num
    }
}