$(function() {
    //跳转的页码
    var pageCount = 1;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            //报送截止日
            submitDeadLineStart:$('#submitDeadLine').startDate(),
            submitDeadLineEnd:$('#submitDeadLine').endDate(),
            //数据状态
            status : $('#status').val()==null?null:$('#status').val().join(","),
            //数据日期
            updateTimeStart:$('#updateTime').startDate(),
            updateTimeEnd:$('#updateTime').endDate(),
            //报送日期
            submitDateStart:$('#submitDate').startDate(),
            submitDateEnd:$('#submitDate').endDate(),
            //反馈状态
            submitFeedbackStatus: $('#submitFeedbackStatus').val()==null?null:$('#submitFeedbackStatus').val().join(","),
            //登记银行编码
            registBankCode: $('#registBankCode').val(),
            //识别标识
            indentifyMark:$('#indentifyMark').val(),
            //产品登记编码
            prdRegistCode: $('#prdRegistCode').val()==null?null:$('#prdRegistCode').val().join(","),
            //持有日期
            holdDateStart:$('#holdDate').startDate(),
            holdDateEnd:$('#holdDate').endDate(),
            //机构代码
            orgCode : $('#orgCode').val()==null?null:$('#orgCode').val().join(","),
            // 发送状态
            submitSendStatus : $("#submitSendStatus").val() == null ? null : $('#submitSendStatus').val().join(",")
            // 发送状态
            ,sendStatus : $("#sendStatus").val() == null ? null : $('#sendStatus').val().join(",")
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        showDetail: true,//双击详情对比画面
        done : function(){
            if(validKey){
                table.resize($(document).height() - $('.container').height() + table.getHeight());
                //标志是否在当前页选中数据
                var isChecked = false;
                var addTableData = table.getAllRows();
                if(addTableData){
                    $(addTableData).each(function(i,v){
                        if(validKey.prdRegistCode===v.prdRegistCode && validKey.indentifyMark===v.indentifyMark
                            && (Date.ofDate(validKey.holdDate).getTime()) === (Date.ofDate(v.holdDate).getTime())
                            && validKey.ccy===v.ccyCode
                            && validKey.registBankCode===v.registBankCode
                            && v.sendStatusCode==='01'){
                            table.checkRow(v.LAY_TABLE_INDEX);
                            isChecked = true;
                            dateCheckMethod();
                            return false;
                        }
                    })
                }
                if(!isChecked){
                    pageCount++;
                    table.reload({page:{curr:pageCount}});
                }
            }

        },
        cols:[
            { title: "数据状态", field: 'status',width:100},
            {title: '持有日期',field:'holdDate', width:100, visible:false},/*界面查询性能优化*/
            {title: '持有日期',field:'holdDateStr', width:100,sortField:'holdDate'},
            { title: "数据日期", field: 'updateTime', visible:false},/*界面查询性能优化*/
            { title: "数据日期", field: 'updateTimeStr',sortField:'updateTime'},
            { title: "报送状态", field: 'sendStatus',width:100 },
            { title: "报送日期", field: 'submitTime', visible:false},/*界面查询性能优化*/
            { title: "报送日期", field: 'submitTimeStr',sortField:'submitTime'},
            { title: "报送截止日", field: 'chbSubmitDeadline', visible:false},/*界面查询性能优化*/
            { title: "报送截止日", field: 'chbSubmitDeadlineStr',sortField:'chbSubmitDeadline'},
            { title: "反馈状态", field: 'submitFeedbackStatus',width:100,visible: false},
            { title: "反馈状态", field: 'submitFeedbackStatusStr',width:100,sortField:'submitFeedbackStatus'/*界面查询性能优化,
                render: function (data, type, row, meta) {
                    if(!row.submitFeedbackStatus){
                        return "";
                    }
                    if(row.submitFeedbackStatus=="01"){
                        return "<span class='debt-suc'>发送成功</span>";
                    }
                    if(row.submitFeedbackStatus=="02"){
                        return "<span class='debt-fail'>发送失败</span>";
                    }
                    if(row.submitFeedbackStatus=="03"){
                        return "<span class='debt-pass'>中债通过</span>";
                    }
                    if(row.submitFeedbackStatus=="04"){
                        return "<span class='debt-return'>中债退回</span>";
                    }
                    if(row.submitFeedbackStatus=="05"){
                        return "<span class='debt-return'>审核文件下载失败</span>";
                    }
                }*/
            },
            {title: '登记银行代码',field:'registBankCode', width:200},
            {title: '产品登记编码', field:'prdRegistCode', width:100},
            {title: '识别标识',field:'indentifyMark', width:150},
            {title: '币种',field:'ccy', width:150},
            {title: '持有份额',field:'qty', width:150,type:'number', digit:5},
            {title: '持有金额',field:'amount', width:150,type:'number'},
            {title: '折算人民币金额（元）',field:'cnyAmt', width:180,type:'number'},
            {title: '机构代码',field:'orgCode', width:150},
            {field:'ccyCode', visible:false},
            {field:'sendStatusCode', visible:false},
            {field:'sumitSeq', visible:false},
            {field:'statusCode', visible:false}
        ]
    });


    // 导入按钮点击事件
    $('#InvestorsHoldInfoRegist001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax('importFile', {fileName: fileName});
            },function() {
                table.load();
            });
        });
    });

    //导出所有查询结果
    $('#InvestorsHoldInfoRegist001_btn_export').click(function() {
        exportExcel("1");
    });

    //导出中债Excel
    $('#InvestorsHoldInfoRegist001_btn_chbexport').click(function() {
        exportExcel("0");
    });

    //导出删除模板
    $('#InvestorsHoldInfoRegist001_btn_exportDel').click(function(){
        /*if(!table.isSearched()) {
            return;
        }*/
        var expArr=table.getAllCheckedRows();
        var param={};
        if(!expArr || expArr.length ==0){
            param = getSearchParams();
            if (param.status==null||param.status==''){
                param.status='03';
            }
            param.flag = "2";
            param.sendStatus='01';
            Joyin.exportExcel("export",param);
        }
        else{

            for(var i=0;i<expArr.length;i++){
                if(expArr[i].status !="删除"||expArr[i].sendStatus !="未报送"){
                    Joyin.error("只能导出删除状态且未报送的数据，请重新选择");
                    return;
                }
            }
            var keyList = [];
            $.each(expArr,function(i,v){
                v.sendStatus=v.sendStatusCode;
                v.status=v.statusCode;
                v.ccy=v.ccyCode;
            })
            param.keyList = JSON.stringify(expArr);
            param.flag='2';
            Joyin.exportExcel('export', param);
        }
    });

    /**
     * 导出
     */
    var exportExcel = function(flag) {
        var expArr=table.getAllCheckedRows();
        var param={};
        if(!expArr || expArr.length ==0){
            param = getSearchParams();
            param.flag = flag;
            if(flag=="0" &&(param.status==null||param.status=='')){
                param.status="01,02";
            }
            Joyin.exportExcel("export",param);
        }
        else{
            if(flag=="0")
            {
                for(var i=0;i<expArr.length;i++){
                    if(expArr[i].status=="删除"){
                        Joyin.error("只能导出新建或修改状态的数据，请重新选择");
                        return;
                    }
                }
            }
            var keyList = [];
            $.each(expArr,function(i,v){
                v.sendStatus=v.sendStatusCode;
                v.status=v.statusCode;
                v.ccy=v.ccyCode;
            })
            param.keyList = JSON.stringify(expArr);
            param.expKeys = keyList.join(',')
            param.flag = flag;
            Joyin.exportExcel('export', param);
        }

    };

    //数据检查
    function dateCheckMethod(){
        var checkArr=table.getAllCheckedRows();
        var param = {};
        if(checkArr.length === 0) {
            var sendStatus = $('#sendStatus').val();
            var status = $('#status').val();
//            if(sendStatus!='01'){
            if (!(sendStatus && sendStatus.length == 1 && sendStatus[0] == '01')) {
                Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行数据检查！");
                return;
            }
//            else if(status && status!='03'){
//            else if(status && status.includes('03')){
            else if(status && status.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行数据检查！");
                return;
            }
            param = getSearchParams();
            if(!status){
                param.status = '01,02';
            }
            param.isCheck = true;
            Joyin.ajax('sendBeforeCheck',param, function(result){
                window.checkResult = result;
                Joyin.checkData('<h4 class="modal-title" id="myModalLabel">投资者持有信息数据检查 </h4>', 'sendBeforeCheck', getSearchParams());
            })
            return ;
        }
        var errorStatus;
        $.each(checkArr,function(i,v){
            if(v.sendStatusCode !="01"){
                Joyin.error("只能对报送状态为未报送的数据进行检查！");
                errorStatus = true;
                return;
            }
//            else if(v.statusCode && v.statusCode.includes('03')){
            else if(v.statusCode && v.statusCode.indexOf('03') > -1){
                Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行数据检查！");
                errorStatus = true;
                return;
            }
            v.sendStatus=v.sendStatusCode;
            v.status=v.statusCode;
            v.ccy=v.ccyCode;
        });
        if(errorStatus) return;
        param.keyList = JSON.stringify(checkArr);
        param.isCheck = true;
        Joyin.ajax('sendBeforeCheck',param, function(result){
            window.checkResult = result;
            Joyin.checkData('<h4 class="modal-title" id="myModalLabel">投资者持有信息数据检查 </h4>', 'sendBeforeCheck', param);
        })

    }
    $('#InvestorsHoldInfoRegist001_btn_checkData').click(dateCheckMethod);

    /**
     * 报送
     */
    $('#InvestorsHoldInfoRegist001_btn_submit').click(function(){
        var rows = table.getAllCheckedRows();
        if(rows.length === 0) {
            var sendStatus = $('#sendStatus').val();
            var status = $('#status').val();
//            if(sendStatus!='01'){
            if (!(sendStatus && sendStatus.length == 1 && sendStatus[0] == '01')) {
                Joyin.error("只能报送报送状态为未报送的数据");
                return;
            }
//            else if(status!='01'){
//                Joyin.error("只能报送数据状态为新增的数据");
//                return;
//            }
//            else if(status && status.includes('03')){
            else if(status && status.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
                return;
            }
            Joyin.ajax('sendBeforeCheck',getSearchParams(), function(result) {
                if(!result || result.length ==0){
                    Joyin.confirm("按照已选择当前查询条件报送,是否确认！",function(){
                        layui.layer.open({type:3});
                        Joyin.ajaxJson('submitChb', getSearchParams(), function() {
                            table.load();
                            layui.layer.closeAll();
                        });
                    });
                }
                else{
                    window.checkResult = result;
                    Joyin.checkData('<h4 class="modal-title" id="myModalLabel">投资者持有信息数据检查 </h4>', 'sendBeforeCheck', getSearchParams());
                }
            });
            return ;
        }
        for(var i=0;i<rows.length;i++){
            if(rows[i].sendStatusCode !="01"){
                Joyin.error("只能报送报送状态为未报送的数据");
                return;
            }
//            else if(rows[i].statusCode && rows[i].statusCode.includes('03')){
            else if(rows[i].statusCode && rows[i].statusCode.indexOf('03') > -1){
                Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行报送！");
                return;
            }
//            else if(rows[i].statusCode !='01'){
//                Joyin.error("选择的数据中存在非新建数据，请重新选择！");
//                return;             
//            }
        }
        var keyList = [];
        $.each(rows, function(i, v) {
            v.sendStatus=v.sendStatusCode;
            v.status=v.statusCode;
            v.ccy=v.ccyCode;
        });
        Joyin.ajax('sendBeforeCheck', {keyList: JSON.stringify(rows)}, function(result) {
            if(!result || result.length ==0){
                Joyin.confirm("请确认是否要报送选中记录？", function() {
                    layui.layer.open({type:3});
                    Joyin.ajaxJson('submitChb', {keyList: JSON.stringify(rows)}, function() {
                        table.load();
                        layui.layer.closeAll();
                    });
                })
            }
            else{
                window.checkResult = result;
                Joyin.checkData('<h4 class="modal-title" id="myModalLabel">投资者持有信息数据检查 </h4>', 'sendBeforeCheck', {keyList: JSON.stringify(rows)});
            }
        });
    });

    /**
     * 手工处理
     */
    $('#InvestorsHoldInfoRegist001_btn_manual').click(function(){
        var rows = table.getAllCheckedRows();
        if(rows.length === 0) {
//            return Joyin.error("请至少选择一条记录");
            //add by liubin
            var sendStatus = $("#sendStatus").val();
            var submitFeedbackStatus = $("#submitFeedbackStatus").val();
            if(!((sendStatus && sendStatus.length == 1 &&sendStatus[0] == '01')
                || (sendStatus && sendStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
                Joyin.error("只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理");
                return;
            }
            var paramObj = getSearchParams();
            paramObj.flag = '01';
            Joyin.confirm("请确认是否要手工处理符合查询条件的全部记录？", function() {
                Joyin.ajax('checkConfirmMsgForAllManual', null, function() {
                    layui.layer.open({type:3});
                    Joyin.ajax('doManual',
                        {
                            flag:'01',
                            key: JSON.stringify(paramObj),
                            selectedBatchGroup: '3072',
                            selectedProductGroup: '',
                            startDate: new Date(),
                            endDate: new Date(),
                            batchGroupType: '1',
                            prodGroupType: '0',
                            detailName: 'CYSGCL'
                        }, function() {
                            table.load();
                            layui.layer.closeAll();
                        });
                });

            });
        }else{
            for(var i=0;i<rows.length;i++){
//              if(rows[i].sendStatusCode !="01"){
                if (!(rows[i].sendStatusCode == '01'
                    || (rows[i].sendStatusCode == '02' && (rows[i].submitFeedbackStatus == '02' || rows[i].submitFeedbackStatus == '04')))) {
//                  Joyin.error("只能手工处理报送状态为未报送的数据");
                    Joyin.error("只能选择报送状态为未报送的数据，或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理，请重新选择！");
                    return;
                }
            }
            $.each(rows, function(i, v) {
                v.sendStatus=v.sendStatusCode;
                v.status=v.statusCode;
                v.ccy=v.ccyCode;
            });
            rows.flag = '02';
            Joyin.confirm("请确认是否要手工处理选中记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajax('doManual', {keyList: JSON.stringify(rows)}, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }

    });

    /**
     * 取消手工处理
     */
    $('#InvestorsHoldInfoRegist001_btn_cancelManual').click(function() {
        var manArr=table.getAllCheckedRows();
        if(!manArr || manArr.length ==0){
            var sendStatus = $("#sendStatus").val();
            if(!(sendStatus && sendStatus.length==1 && sendStatus[0] == '03')){
                Joyin.error("只能选择手工处理的数据，请重新选择！");
                return;
            }
            var paramObj = getSearchParams();
            paramObj.flag = '01';
            Joyin.confirm("请确认是否要取消手工处理符合查询条件所有记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajaxJson('cancelMannul', paramObj, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }else{
            for(var i=0;i<manArr.length;i++){
                if(manArr[i].sendStatusCode !="03"){
                    Joyin.error("只能选择手工处理的数据，请重新选择！");
                    return;
                }
                manArr[i].sendStatus=manArr[i].sendStatusCode;
                manArr[i].status=manArr[i].statusCode;
                manArr[i].ccy=manArr[i].ccyCode;
            };
            Joyin.confirm("请确认是否要取消手工处理选中记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajaxJson('cancelMannul', {keyList: JSON.stringify(manArr)}, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }

    });
    // add by wangmin 20180827 start
    // 二次报送
    $('#InvestorsHoldInfoRegist001_btn_submitAgain').click(function() {
        var paramObj = getSearchParams();

        var submitArr = table.getAllCheckedRows();
        if(submitArr.length === 0) {
            var submitStatus = $('#sendStatus').val();
            var status = $('#status').val();
            var submitFeedbackStatus = $('#submitFeedbackStatus').val();
            if (!(submitStatus && submitStatus.length == 1 && submitStatus[0] == '02')) {
                Joyin.error("只能对报送状态为接口报送的数据进行重新报送！");
                return;
            }
//               if(status && status.includes('03')){
            if(status && status.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行重新报送！");
                return;
            }
            if(submitFeedbackStatus &&
                (submitFeedbackStatus.indexOf('01') > 0
                    || submitFeedbackStatus.indexOf('03') > 0
                    || submitFeedbackStatus.indexOf('05') > 0)) {
                Joyin.error("只能对反馈状态为发送失败和中债退回的数据进行重新报送！");
                return;
            }

            if(!status){
                paramObj.status = '01,02';
            }
            if (!submitFeedbackStatus) {
                paramObj.submitFeedbackStatus = '02,04';
            }
            Joyin.confirm("请确认是否要按照已选择当前查询条件进行二次报送？",function(){
                layui.layer.open({type:3});
                Joyin.ajaxJson('submitCbAgain', paramObj, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
            return ;
        }

        var keyList = [];
        for (var i = 0; i < submitArr.length; i++) {
            var submitFeedbackStatus = submitArr[i].submitFeedbackStatus;
            var submitStatus = submitArr[i].sendStatusCode;
            if(submitStatus&&submitStatus.indexOf('01') > -1){
                Joyin.error("未报送的数据不能进行二次报送！");
                return;
            }else if(!submitFeedbackStatus){
                Joyin.error("反馈状态为空的数据不能进行二次报送！");
                return;
            }else if(submitFeedbackStatus&&
                (submitFeedbackStatus.indexOf('01') > -1
                    || submitFeedbackStatus.indexOf('03') > -1
                    || submitFeedbackStatus.indexOf('05') > -1)){
                Joyin.error("只能对反馈状态为发送失败和中债退回的数据进行二次报送！");
                return;
            }
        };
        $.each(submitArr, function(i, v) {
            v.sendStatus=v.sendStatusCode;
            v.status=v.statusCode;
            v.ccy=v.ccyCode;
        });

        Joyin.confirm("请确认是否要二次报送？",function(){
            layui.layer.open({type:3});
            Joyin.ajaxJson('submitCbAgain', {keyList: JSON.stringify(submitArr)}, function() {
                table.load();
                layui.layer.closeAll();
            });
        });
    });
    // add by wangmin 20180827 end

    function init(){
        if(isMain=='01'){
            setTimeout(function() {
                if($('#sendStatus').val()=='02'||$('#sendStatus').val()=='03'){
                    $('#submitDate').val(searchDataStart+' - '+searchDataEnd);
                }else{
                    $('#submitDeadLine').val(searchDataStart+' - '+searchDataEnd);
                }
                table.load();
            }, 500);
        }
        //报送前检查调用数据检查
        if(validKey){
            setTimeout(function() {
                table.load();
            },500)
        }
    };
    init();

    $(function(){
        // 绑定按钮事件
        $('#InvestorsHoldInfoRegist001_btn_search').click(function() {
            table.load();
        });
    });
}());