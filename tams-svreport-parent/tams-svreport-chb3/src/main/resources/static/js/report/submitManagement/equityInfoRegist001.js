
/**
 * Created by Administrator on 2017/8/15 0015.
 */
// By zhengyan
$(function(){
    //跳转的页码
    var pageCount = 1;
    var getPageParams = function() {
        var submitStatus=$('#submitStatus').val();
        if(submitStatus){
            submitStatus=submitStatus.join();
        }
        var holderType = $('#holderType').val();
        if (holderType) {
            holderType = holderType.join();
        }
        var status=$('#status').val();
        if(status){
            status=status.join();
        }
        var submitFeedbackStatus=$('#submitFeedbackStatus').val();
        if(submitFeedbackStatus){
            submitFeedbackStatus=submitFeedbackStatus.join();
        }
        var personalIdentifyType=$('#personalIdentifyType').val();
        if(personalIdentifyType){
            personalIdentifyType=personalIdentifyType.join();
        }
        var institutionIdentifyType=$('#institutionIdentifyType').val();
        if(institutionIdentifyType){
            institutionIdentifyType=institutionIdentifyType.join();
        }
        var orgCode=$('#orgCode').val();
        if(orgCode){
            orgCode=orgCode.join();
        }
        return {
            // 登记银行代码
            bankCode : $('#bankCode').val(),
            // 识别标识
            indentifyMark : $('#indentifyMark').val(),
            // 原识别标识
            orgIndentifyMark : $('#orgIndentifyMark').val(),
            // 投资者名称
            holderName : $('#holderName').val(),
            //投资者类别
            holderType :holderType,
            // 个人证件类别
            personalIdentifyType : personalIdentifyType,
            // 机构证件类别
            institutionIdentifyType : institutionIdentifyType,
            // 证件号码
            identifyNumber : $('#identifyNumber').val(),
            // 报送状态
            submitFeedbackStatus : submitFeedbackStatus,
            // 反馈状态
            submitStatus : submitStatus,
            // 数据状态
            status : status,
            //报送日开始
            submitDate : $('#submitDate').startDate(),
            //报送日结束
            submitDateEnd : $('#submitDate').endDate(),
            //数据日期开始日
            updateTime : $('#updateTime').startDate(),
            //数据日期截止日
            updateTimeEnd : $('#updateTime').endDate(),
            //报送截止日开始
            submitDeadLineStart : $('#submitDeadLine').startDate(),
            //报送截止日结束
            submitDeadLineEnd : $('#submitDeadLine').endDate(),
            //机构代码
            orgCode: orgCode
        };
    };
    var table = $('#equityInfoRegist').joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        showDetail: true,//双击详情对比画面
        done :function(){
            if(indentifyMark){
                table.resize($(document).height() - $('.container').height() + table.getHeight());
                var addTableData = table.getAllRows();
                //标志是否在当前页选中数据
                var isChecked = false;
                if(addTableData){
                    $(addTableData).each(function(i,v){
                        if(indentifyMark===v.indentifyMark && v.submitStatusName==='未报送'){
                            table.checkRow(v.LAY_TABLE_INDEX);
                            dateCheckMethod();
                            isChecked = true;
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
        cols: [
            { title: "数据状态", field: 'statusName',width:100,sortField:'status'},
            { field: 'status',visible:false},
            { title: "数据日期", field: 'updateTime', visible: false},/*界面查询性能优化*/
            { title: "数据日期", field: 'updateTimeStr',sortField:'updateTime'},
            { title: "报送状态", field: 'submitStatusName',width:100 ,sortField:'submitStatus'},
            { title: "报送日期", field: 'submitTime',  visible: false},/*界面查询性能优化*/
            { title: "报送日期", field: 'submitTimeStr',sortField:'submitTime'},
            { title: "报送截止日", field: 'chbSubmitDeadline', visible: false},/*界面查询性能优化*/
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
            { title: "数据类型", field: 'type' ,sortField:'type',width:100},
            { field: 'submitStatus',visible:false},
            { title: "报送流水号", field: 'sumitSeq',visible:false},
            { title: "登记银行代码", field: 'bankCode',width:160 },
            { title: "机构代码",field:'orgCode',width: 160},
            { title: "识别标识", field: 'indentifyMark',width:100 },
            { title: "原识别标识", field: 'orgIndentifyMark',width:120 },
            { title: "该投资者是否属于本行", field: 'isBelongBank',sortField:'isBelongBank',width:180},
            { title: "投资者所属银行名称", field: 'theBankName',width:168 },
            { title: "投资者所属银行代码", field: 'theBankCode',width:168},
            { title: "投资者境内外标识", field: 'holderMark',sortField:'holderMark',width:152},
            { title: "投资者所属国家或地区", field: 'holderRegion',sortField:'holderRegion',width:180 },
            { title: "投资者类别", field: 'holderType',sortField:'holderType',width:114},
            { title: "证件类别", field: 'identifyType',sortField:'identifyType',width: 200},
            { title: "其他证件名称", field: 'otherIdentifyName',width:126},
            { title: "证件号码", field: 'identifyNumber',width: 172},
            { title: "SPV资金托管账户及开户行", field: 'spvCapAccBank',sortField:'spvCapAccBank',width: 210},
            { title: "其他资金托管账户及开户行", field: 'otherCapAccBank',width: 210},
            /*{ title: "SPV登记编码", field: 'spvRegistCode',width: 128 },*/
            { title: "投资者名称", field: 'holderName', width: 109},
            { title: "性别", field: 'holderSex',width:75},
            { title: "风险偏好", field: 'holderRiskPrefer',width:100/*界面查询性能优化,
                render: function (data, type, row, meta) {
                    if(!row.holderRiskPrefer){
                        return "";
                    }
                    if(row.holderRiskPrefer=="01"){
                        return "<span class='text-Conervative'>保守型</span>";
                    }
                    if(row.holderRiskPrefer=="02"){
                        return "<span class='text-steady'>稳健型</span>";
                    }
                    if(row.holderRiskPrefer=="03"){
                        return "<span class='text-balance'>平衡型</span>";
                    }
                    if(row.holderRiskPrefer=="04"){
                        return "<span class='text-grow'>成长型</span>";
                    }
                    if(row.holderRiskPrefer=="05"){
                        return "<span class='text-progress'>进取型</span>";
                    }
                }*/
            },
            {title:"手机号码", field: 'holderCellphone', width: 120},
            {title:"固定电话", field: 'holderTelephone', width: 120},
            {title:"电子邮箱", field: 'holderEmail', width: 150 },
            {title:"备注", field: 'remark', sortable:false, width:160}
        ]
    });

    // 查询按钮点击事件
    $('#EquityInfoRegist001_btn_search').click(function() {
        /*if($('#searchForm').joyinvalid()) {*/
        table.load();
//        }
    });

    // 导出中债Excel按钮点击事件
    $('#EquityInfoRegist001_btn_chbexport').click(function() {
        exportMethod("0");
    });

    // 导出查询结果按钮点击事件
    $('#EquityInfoRegist001_btn_export').click(function() {
        exportMethod("1");
    });

    //导出方法
    var exportMethod = function (flag){
        var expArr=table.getAllCheckedRows();
        var param={};
        if(!expArr || expArr.length ==0){
            param = getPageParams();
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
                    if(expArr[i].status=="03"){
                        Joyin.error("只能导出新建或修改状态的数据，请重新选择");
                        return;
                    }
                }
            }
            var keyList = [];
            $.each(expArr, function(i, row) {
                keyList.push(row.bankCode + '|' + row.indentifyMark + '|' + row.submitStatus);
            });
            param.expKeys = keyList.join(',')
            param.flag = flag;
            Joyin.exportExcel("exportSelect",param);
        }

    };


    // 导入按钮点击事件
    $('#EquityInfoRegist001_btn_import').click(function() {
        Joyin.confirm("请确认是否要导入数据？", function() {
            Joyin.uploadFile(function(fileName) {
                Joyin.ajax('importFile', {fileName: fileName});
            },function() {
                table.load();
            });
        });
    });
    //导出删除模板
    $('#EquityInfoRegist001_btn_exportDel').click(function(){
        if(!table.isSearched()) {
            return;
        }
        var expArr=table.getAllCheckedRows();
        var param={};
        if(!expArr || expArr.length ==0){
            param = getPageParams();
            if (param.status==null||param.status==''){
                param.status='03';
            }
            param.flag = "2";
            param.submitStatus='01';
            Joyin.exportExcel("export",param);
        }
        else{

            for(var i=0;i<expArr.length;i++){
                if(expArr[i].status !="03"||expArr[i].submitStatus !="01"){
                    Joyin.error("只能导出删除状态且未报送的数据，请重新选择");
                    return;
                }
            }

            var keyList = [];
            $.each(expArr, function(i, row) {
                keyList.push(row.bankCode + '|' + row.indentifyMark + '|' + row.submitStatus);
            });
            param.expKeys = keyList.join(',')
            param.flag = "2";
            Joyin.exportExcel("exportDel",param);
        }
    });
    //数据检查
    $('#EquityInfoRegist001_btn_checkData').click(dateCheckMethod);
    function dateCheckMethod(){
        var checkArr=table.getAllCheckedRows();
        if(checkArr.length === 0) {
            var submitStatus = $('#submitStatus').val();
            var status = $('#status').val();
//            if(submitStatus!='01'){
            if (!(submitStatus && submitStatus.length == 1 && submitStatus[0] == '01')) {
                Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行数据检查！");
                return;
            }
//            else if(status && status.includes('03')){
            else if(status && status.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
                return;
            }
            var paramObj = getPageParams()
            if(!status){
                paramObj.status = '01,02';
            }
            Joyin.ajax('reportCheck',paramObj, function(result){
                window.checkResult = result;
                Joyin.checkData(dataTitle, checkUrl, getPageParams());
            })
            return ;
        }
        var keyList = [];
        for(var i=0;i<checkArr.length;i++){
            //modify by wangmin 修改取数顺序 start 2018-07-12
//          keyList.push(checkArr[i].submitStatus + '|' + checkArr[i].bankCode + '|' + checkArr[i].indentifyMark );
            keyList.push(checkArr[i].bankCode + '|' + checkArr[i].indentifyMark + '|' + checkArr[i].submitStatus );
            //modify by wangmin 修改取数顺序 end 2018-07-12
            if(checkArr[i].submitStatusName !="未报送"){
                Joyin.error("数据检查只能检查未报送数据，请重新选择！");
                return;
            }
//            else if(checkArr[i].status && checkArr[i].status.includes('03')){
            else if(checkArr[i].status && checkArr[i].status.indexOf('03') > -1){
                Joyin.error("选择的报送数据中存在删除状态的数据，请重新选择！");
                return;
            }
        };
        Joyin.ajax('reportCheck',{checkKeys:keyList.join(',')}, function(result){
            window.checkResult = result;
            Joyin.checkData(dataTitle, checkUrl, {checkKeys: keyList.join(',')});
        })

    }
    // 手工处理 
    $('#EquityInfoRegist001_btn_manual').click(function() {
        // debugger
        var manArr=table.getAllCheckedRows();
        if(!manArr || manArr.length ==0){
            //modify by liubin 当不选择时默认选择符合查询条件的全部数据
            var submitStatus = $("#submitStatus").val();
            var submitFeedbackStatus = $("#submitFeedbackStatus").val();
            if(!((submitStatus && submitStatus.length == 1 &&submitStatus[0] == '01')
                || (submitStatus && submitStatus.indexOf('02')>-1 && submitFeedbackStatus && (submitFeedbackStatus.indexOf('02')>-1 ||  submitFeedbackStatus.indexOf('04')> -1)))){
                Joyin.error("只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理");
                return;
            }
            var paramObj = getPageParams();
            paramObj.flag = "01";//判断是否选中记录
            Joyin.confirm("请确认是否要手工处理全部未报送记录？", function() {
                //add by liubin 页面添加滚动条

                Joyin.ajax('checkConfirmMsgForAllManual', null, function() {
                    layui.layer.open({type:3});
                    Joyin.ajax('update', {
                        flag:'01',
                        key: JSON.stringify(paramObj),
                        selectedBatchGroup: '3073',
                        selectedProductGroup: '',
                        startDate: new Date(),
                        endDate: new Date(),
                        batchGroupType: '1',
                        prodGroupType: '0',
                        detailName: 'SFSGCL'
                    }, function() {
                        table.load();
                        layui.layer.closeAll();
                    });
                });

//        		layui.layer.open({type:3});
//	            Joyin.ajaxJson('update', paramObj, function() {
//	                table.load();
//	                layui.layer.closeAll();
//	            });
            });
//            Joyin.error(JoyinUtil.getMessage('REPORT_COMMON_E_0017'));
//            return;
        }else{
            var keyList = [];
            for(var i=0;i<manArr.length;i++){
                keyList.push(manArr[i].bankCode + '|' + manArr[i].indentifyMark + '|' + manArr[i].submitStatus+ '|' + manArr[i].sumitSeq);
                //            if(manArr[i].submitStatusName !="未报送"){
                if (!(manArr[i].submitStatus == '01'
                    || (manArr[i].submitStatus == '02' && (manArr[i].submitFeedbackStatus == '02' || manArr[i].submitFeedbackStatus == '04')))) {
                    //                Joyin.error("只能选择未报送的数据，请重新选择！");
                    Joyin.error("只能选择报送状态为未报送的数据，或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理，请重新选择！");
                    return;
                }
            };
            Joyin.confirm("请确认是否要手工处理选中记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajax('update', {updateKeys:keyList.join(',')}, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }
    });
    //liaochunyu add 2017-12-02 start
    // 取消手工处理
    $('#EquityInfoRegist001_btn_cancelManual').click(function() {
        var manArr=table.getAllCheckedRows();
        if(!manArr || manArr.length ==0){
            //add by liubin
            var submitStatus = $("#submitStatus").val();
            if(!(submitStatus && submitStatus.length==1 && submitStatus[0] == '03')){
                Joyin.error("只能选择手工处理的数据，请重新选择！");
                return;
            }
            var paramObj = getPageParams();
            paramObj.flag = "01";//判断是否选中记录
            Joyin.confirm("请确认是否取消手工处理全部未报送记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajaxJson('cancelMannul', paramObj, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }else{
            var keyList = [];
            for(var i=0;i<manArr.length;i++){
                keyList.push((manArr[i].sumitSeq =='' ? null : manArr[i].sumitSeq) +'|'+ manArr[i].bankCode + '|' + manArr[i].indentifyMark);
                if(manArr[i].submitStatusName !="手工处理"){
                    Joyin.error("只能选择手工处理的数据，请重新选择！");
                    return;
                }
            };
            Joyin.confirm("请确认是否要取消手工处理选中记录？", function() {
                layui.layer.open({type:3});
                Joyin.ajaxJson('cancelMannul', {updateKeys:keyList.join(',')}, function() {
                    table.load();
                    layui.layer.closeAll();
                });
            });
        }

    });
    //liaochunyu add 2017-12-02 end
    //报送
    $('#EquityInfoRegist001_btn_submit').click(function() {
        var submitArr=table.getAllCheckedRows();
        if(submitArr.length === 0) {
            var paramObj = getPageParams();
            var submitStatus = $('#submitStatus').val();
            var status = $('#status').val();
//            if(submitStatus!='01'){
            if (!(submitStatus && submitStatus.length == 1 && submitStatus[0] == '01')) {
                Joyin.error("只能对报送状态为未报送，数据状态为新建和修改的数据进行报送！");
                return;
            }
//            else if(status && status.includes('03')){
            else if(status && status.indexOf('03') > -1){
                Joyin.error("只能对数据状态为新建和修改的数据进行报送！");
                return;
            }
            if(!status){
                paramObj.status = '01,02';
            }
            Joyin.ajax('sendBeforeCheck',paramObj, function(result){
                if(!result || result.length ==0){
                    Joyin.confirm("按照已选择当前查询条件报送,是否确认！",function(){
                        layui.layer.open({type:3});
                        Joyin.ajaxJson('submitChb', getPageParams(), function() {
                            table.load();
                            layui.layer.closeAll();
                        });
                    });
                }
                else{
                    window.checkResult = result;
                    Joyin.checkData(dataTitle, checkUrl, getPageParams());
                }
            })
            return ;
        }
        var keyList = [];
        for(var i=0;i<submitArr.length;i++){
            keyList.push(submitArr[i].bankCode + '|' + submitArr[i].indentifyMark + '|' + submitArr[i].submitStatus+ '|' + submitArr[i].sumitSeq);
            if(submitArr[i].submitStatus !="01"){
                Joyin.error("选择的报送数据中存在已报送数据，请重新选择！");
                return;
            }
            else if(submitArr[i].status =="03" && submitArr[i].submitStatus =="01"){
                Joyin.error("选择的报送数据中存在删除状态的数据，请重新选择！");
                return;
            }
        };

        Joyin.ajax('sendBeforeCheck',{checkKeys:keyList.join(',')}, function(result){
            if(!result || result.length ==0){
                Joyin.confirm("请确认是否要报送选中记录？", function() {
                    layui.layer.open({type:3});
                    Joyin.ajaxJson('submitChb', {submitKeys:keyList.join(',')}, function() {
                        table.load();
                        layui.layer.closeAll();
                    });
                });
            }
            else{
                window.checkResult = result;
                Joyin.checkData(dataTitle, checkUrl, {checkKeys: keyList.join(',')});
            }
        })
//        Joyin.confirm("请确认是否要报送选中记录？", function() {
//            Joyin.ajaxJson('submitChb', {submitKeys:keyList.join(',')}, function() {
//                table.load();
//            });
//        });
    });

    // add by etliu 20180824 start
    // 二次报送
    $('#EquityInfoRegist001_btn_submitAgain').click(function() {
        var paramObj = getPageParams();

        var submitArr = table.getAllCheckedRows();
        if(submitArr.length === 0) {
            var submitStatus = $('#submitStatus').val();
            var status = $('#status').val();
            var submitFeedbackStatus = $('#submitFeedbackStatus').val();
            if (!(submitStatus && submitStatus.length == 1 && submitStatus[0] == '02')) {
                Joyin.error("只能对报送状态为接口报送且反馈状态为发送失败或中债退回的数据进行重新报送！");
                return;
            }
//            if(status && status.includes('03')){
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
            var submitStatus = submitArr[i].submitStatus;
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
                Joyin.error("只能对报送状态为接口报送且反馈状态为发送失败或中债退回的数据进行重新报送!");
                return;
            }
            keyList.push(submitArr[i].sumitSeq);
        };

        paramObj.keyList = keyList.join(',');

        Joyin.confirm("请确认是否要二次报送？",function(){
            layui.layer.open({type:3});
            Joyin.ajaxJson('submitCbAgain', paramObj, function() {
                table.load();
                layui.layer.closeAll();
            });
        });
    });
    // add by etliu 20180824 end

    var init= function(){
        $('#updateTime').val('');
        $('#submitDate').val('');

        if(isMain=='01'){
            setTimeout(function() {
                if($('#submitStatus').val()=='02'||$('#submitStatus').val()=='03'){
                    $('#submitDate').val(searchDataStart+' - '+searchDataEnd);
                }else{
                    $('#submitDeadLine').val(searchDataStart+' - '+searchDataEnd);
                }
                table.load();
            }, 500);
        }

        //报送前检查调用数据检查
        if(indentifyMark){
            setTimeout(function() {
                table.load();
            },500)
        }
    };
    init();
});