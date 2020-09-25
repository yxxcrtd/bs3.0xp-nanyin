(function () {

    /**
     * 定义数据表格
     */
    /**
     * 定义数据表格
     */
    var counterList = {};
    var findcounterList = $("#counterList").val();
    if(findcounterList != null && findcounterList != '' && findcounterList != undefined){
        counterList = JSON.parse(findcounterList);
    }
    var counterTbl = $("#counterTable").joyintable({
        data:counterList,
        searchOnInit: true,
        loadonce: true,
        rowno: true,
        page: false,
        checkbox:true,
        height : 200,
        cols: [
            {field: 'jycyname', width:130, sortField:'jycyname', title: 'FxSecRequestQuoteCounter.jycyname'}
            , {field: 'jyyname', width:150, sortField:'jyyname', title: 'FxSecRequestQuoteCounter.jyyname'}
            , {field: 'tradestatus', width:140, sortField:'tradestatus', title: 'FxSecRequestQuoteCounter.tradestatus'}
            , {field: 'prinamt', width:100, sortField:'prinamt', title: 'FxSecRequestQuoteCounter.prinamt',type: 'money'}
            , {field: 'cleanprice', width:110, sortField:'cleanprice', title: 'FxSecRequestQuoteCounter.cleanprice',type: 'money'}
            , {field: 'dirtyprice', width:110, sortField:'dirtyprice', title: 'FxSecRequestQuoteCounter.dirtyprice',type: 'money'}
            , {field: 'matureyield', width:130, sortField:'matureyield', title: 'FxSecRequestQuoteCounter.matureyield'}
            , {field: 'exerciseyield', width:130, sortField:'exerciseyield', title: 'FxSecRequestQuoteCounter.exerciseyield'}
            , {field : 'seqno', visible : false}
            , {field : 'jycyid', visible : false}
            , {field : 'jgdm', visible : false}
        ]
    });
    //保存提交
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm', true);
        var counterList;
        if (counterTbl != null && counterTbl != '') {
            counterList = counterTbl.getAllRows();
        }
        if(counterList == null && counterList.length <= 0){
            JoyinUtil.showMessage("请选择交易对手！","error");
            return;
        }
        object.counterList = counterList;
        object.pageModel = pageModel;
        object.operateType = operateType;
        Joyin.ajaxJson(basePath + 'interf/cfetsup/secrequestquote/FxSecRequestQuote002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if (operateType === 'add') {
                    //保存
                    object.elementId='FxSecRequestQuote002_btn_save'
                    WorkFlow.doSaveList(basePath + 'interf/cfetsup/secrequestquote/FxSecRequestQuote002/save', object, "FxSecRequestQuote002_btn_save", "FxSecRequestQuote002");
                } else if (operateType === 'submit') {
                    //提交
                    object.elementId='FxSecRequestQuote002_btn_submit'
                    WorkFlow.doSubmitList(basePath + 'interf/cfetsup/secrequestquote/FxSecRequestQuote002/save', object, "FxSecRequestQuote002_btn_submit", "FxSecRequestQuote002");
                }else if(operateType === 'confirm'){
                    //保存
                    object.elementId='FxSecRequestQuote002_btn_confirm'
                    WorkFlow.doSaveList(basePath + 'interf/cfetsup/secrequestquote/FxSecRequestQuote002/confirm', object, "FxSecRequestQuote002_btn_confirm", "FxSecRequestQuote002");
                }else if(operateType === 'revoke'){//撤回报价不走流程
                    //直接撤回报价 调用接口发送
                }
            });
        });
    }
    /**
     * 画面加载完成
     */
    $(function () {
        var operateType=$('#operateType').val();
        console.log(operateType);
        debugger;
       if(operateType=='add'||operateType=='copy'){
            $('#FxSecRequestQuote002_btn_confirm').hide();
            $('#FxSecRequestQuote002_btn_revoke').hide();
       }else if(operateType=='reply'){
            $('#FxSecRequestQuote002_btn_save').hide();
            $('#FxSecRequestQuote002_btn_submit').hide();
            $('#FxSecRequestQuote002_btn_confirm').show();
            $('#FxSecRequestQuote002_btn_revoke').show();
            $('.reply').attr("disabled",true);
       }else if(operateType=='submit'){
           $('#FxSecRequestQuote002_btn_confirm').hide();
           $('#FxSecRequestQuote002_btn_revoke').hide();
           $('#FxSecRequestQuote002_btn_risk').hide();
       }
        // 绑定新建按钮事件
        $('#FxSecRequestQuote002_btn_add').click(function () {
            var counterList = JSON.stringify(counterTbl.getAllRows());
            var dialogIndex = Joyin.dialog(JoyinUtil.getLabel("FxSecRequestQuote003.select"),
                "/interf/cfetsup/secrequestquote/FxSecRequestQuote003/init",
                "49%", "80%", {pageModel:PAGE_MODEL_NEW,counterList:counterList}, function(data) {
                    if(!data) return;
                    debugger;
                    var result = data.data;
                    var selArr = JSON.parse(result.data);
                    console.log(selArr);
                    $.each(selArr, function(i,object){
                        console.log(object);
                        var row = {
                            jycyname: object.jycyname,
                            jycyid:object.jycyid,
                            jgdm:object.jgdm
                        };

                        counterTbl.removeRow(row);
                        //添加一行
                        counterTbl.addRow(row);
                    });
                });
        });
        $('#FxSecRequestQuote002_btn_save').click(function (){
            saveOrSubmit('add');
        });
        $('#FxSecRequestQuote002_btn_submit').click(function (){
            saveOrSubmit('submit');
        });

        $('#FxSecRequestQuote002_btn_confirm').click(function (){
            saveOrSubmit('confirm');
        });

        $('#FxSecRequestQuote002_btn_revoke').click(function (){
            saveOrSubmit('revoke');
        });

        //产品名称联动
        $('#accountname').change(function() {
            var accountId = $("#accountname").val();
            //var accountName=$('#accountname').text();
            if(accountId==null|| accountId==''){
                return ;
            }
            $("#accountid").attr('value', accountId);
            var obj={'accountid':accountId};
            $.ajax({
                type: "POST",
                url: basePath+"interf/cfetsup/secrequestquote/FxSecRequestQuote002/findHomeCstp",
                data:JSON.stringify(obj),
                dataType:"json",
                contentType:"application/json;charset=utf-8",
                async: false,
                error: function (request) {//失败
                    console.log(request);
                },
                success: function (data) {//成功
                    if(data.result=="true"){
                        $("#homecstpid").attr('value', data.jgdm);
                        $("#homecstpname").attr('value', data.jgmc);
                    }else {
                        JoyinUtil.showMessage(data.msg,"error");
                        return;
                    }
                }
            });
        });
        //债券名称联动
        $('#secname').change(function() {
            var seccode = $("#secname").val();
           // var secname=$('#secname').text();
            if(seccode==null|| seccode==''){
                return ;
            }
            $("#seccode").attr('value', seccode);
        });
    });

}());