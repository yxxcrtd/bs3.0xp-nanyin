/**
 * add by zhangliang 20201019
 * 中债报送共通校验处理
 */
$(function () {

    /**
     * 中债报送共通校验check
     */
    window.chbCommonCheck = function(table, paramObj, checkUrl, dataTitle, submitUrl){
        var checkArr=table.getAllCheckedRows();
        if(checkArr.length === 0) {
            if (paramObj.opertionType == "checkData"){
                if (paramObj.sendStatus && paramObj.sendStatus != '01') {
                    Joyin.error("只能对报送状态为未报送的数据进行数据检查！");
                    return;
                }
                if(paramObj.status && paramObj.status.indexOf('03') > -1){
                    Joyin.error("只能对数据状态为新建和修改的数据进行数据检查！");
                    return;
                }
                paramObj.status = '01,02';
                //  报送状态  有部分画面参数值不统一 目前已知的有产品发行登记和资产要素登记
                paramObj.sendStatus = '01';
                paramObj.submitStatus = '01';
            }else if(paramObj.opertionType == "submitChb"){
                if (paramObj.sendStatus && paramObj.sendStatus != '01') {
                    Joyin.error("只能对报送状态为未报送的数据进行报送！");
                    return;
                }
                if(paramObj.status && paramObj.status != '01'){
                    Joyin.error("只能对数据状态为新建的数据进行报送！");
                    return;
                }
                paramObj.status = '01';
                //  报送状态  有部分画面参数值不统一 目前已知的有产品发行登记和资产要素登记
                paramObj.sendStatus = '01';
                paramObj.submitStatus = '01';
            }else if(paramObj.opertionType == "reSubmitChb"){
                if (paramObj.submitFeedbackStatus && paramObj.submitFeedbackStatus != '02') {
                    Joyin.error("只能对反馈状态为发送失败的数据进行重新报送");
                    return;
                }
                if(paramObj.status && paramObj.status != '01'){
                    Joyin.error("只能对数据状态为新建的数据进行重新报送！");
                    return;
                }
                paramObj.status = '01';
                paramObj.submitFeedbackStatus = '02';
            }

            Joyin.ajax(checkUrl,paramObj, function(result){
                window.checkResult = result.excelValidDtoList;
                window.rows = result.resultList;
                window.submitChb = submitChb;
                window.submitUrl = submitUrl;
                window.dataTable = table;
                Joyin.checkData(dataTitle, checkUrl, paramObj, null, paramObj.opertionType);
            })
            return ;
        }
        var checkKeys = [];
        var keyList = [];
        for(var i=0;i<checkArr.length;i++){
            if (paramObj.opertionType == "checkData"){
                if(checkArr[i].sendStatus !="01"){
                    Joyin.error("数据检查只能检查未报送数据，请重新选择！");
                    return;
                }
                if(checkArr[i].status.indexOf('03') > -1){
                    Joyin.error("选择的检查数据中存在删除状态的数据，请重新选择！");
                    return;
                }
            }else if (paramObj.opertionType == "submitChb"){
                if(checkArr[i].sendStatus !="01"){
                    Joyin.error("只有未报送数据才能报送，请重新选择！");
                    return;
                }
                if(checkArr[i].status != '01'){
                    Joyin.error("只能对数据状态为新建的数据进行报送！");
                    return;reSubmitChb
                }
            }else if (paramObj.opertionType == "reSubmitChb"){
                if (checkArr[i].submitFeedbackStatus != '02' && checkArr[i].submitFeedbackStatus != '发送失败') {
                    Joyin.error("只能对反馈状态为发送失败的数据进行重新报送");
                    return;
                }
                if(checkArr[i].status != '01'){
                    Joyin.error("只能对数据状态为新建的数据进行重新报送！");
                    return;
                }
            }
            checkKeys.push(checkArr[i].registSeqno);
            keyList.push(checkArr[i]);

        };
        Joyin.ajax(checkUrl,{checkKeys:checkKeys.join(','), keyList: JSON.stringify(keyList)}, function(result){
            window.checkResult = result.excelValidDtoList;
            window.rows = result.resultList;
            window.submitChb = submitChb;
            window.submitUrl = submitUrl;
            window.dataTable = table;
            Joyin.checkData(dataTitle, checkUrl, {checkKeys: checkKeys.join(','), keyList: JSON.stringify(keyList)}, null, paramObj.opertionType);
        })
    }

    /**
     * 报送发送接口
     */
    window.submitChb = function(){
        Joyin.confirm("请确认是否要报送选中记录？", function() {
            layui.layer.open({type:3});
            Joyin.ajaxJson(window.submitUrl, {keyList: JSON.stringify(window.rows)}, function() {
                window.dataTable.load();
                layui.layer.closeAll();
            });
        })
    }

})