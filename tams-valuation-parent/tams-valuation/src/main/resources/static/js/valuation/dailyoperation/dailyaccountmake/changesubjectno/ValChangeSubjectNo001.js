
/**
 * 定义数据表格
 */
var table = $("#importResultTable").joyintable({
    searchOnInit: true,
    checkbox: false,
    height: 400,
    rowno: true,
    loading: false,
    page: false,
    cols: [
        {field:'rowIndexNo', title: 'rowIndexNo', visible:false, sortable: false},
        {field:'rowIndexNoName', title: '行号', width: 150, sortable: false},
        {field:'resultCode', title: 'resultCode', visible:false, sortable: false},
        {field:'validKey', title: '处理状态', width: 150, sortable: false},
        {field:'resultDesc', title: '结果描述', sortable: false,width: 950},
    ],
    loadonce:true
});
//上传文件信息
var FileUpload = function() {
    return {
        /**
         * 文件上传后记录文件信息
         */
        onUploaded : function(res) {
            //alert(123);
            if(res && res.length > 0) {
                $("#fileNameInput_").val(res[0].fileName);
                $('.layui-icon').on('click', function(){
                    $("#fileNameInput_").val("");
                    return true;
                });
                Joyin.ajax(basePath +'valuation/dailyoperation/dailyaccountmake/changesubjectno/ValChangeSubjectNo001/uploadFileTest',
                    {
                        changeSubjectType : $("#changeSubjectType").val(),
                        fileName: $("#fileNameInput_").val(),
                    },
                    function(data) {
                        if (data.data && data.data.importCheckList) {
                            table.reload({data: data.data.importCheckList});
                        }else {
                            Joyin.alert("文件已上传成功，请选择<strong>变更</strong>或<strong>脚本生成</strong>。");
                        }
                    });
            }
        }
    };
}();
$(function () {

    /**
     * 获取查询参数
     */
    var getParams = function() {
        return {
            changeSubjectType: $.trim($('#changeSubjectType').val()),//金融工具类别
            booksetIds: $.trim($('#booksetIds').val()),//金融工具代码
            oriSubjectNo: $.trim($('#oriSubjectNo').val()),//金融工具名称
            changeSubjectNo: $.trim($('#changeSubjectNo').val()),//金融工具名称
            changeSubjectName: $.trim($('#changeSubjectNo').select2('data')[0].text),
            finprodType: $.trim($('#finprodType').val()),// 金融产品类型
            type2: $.trim($('#type2').val()),// 核算分类
            tradeMarket: $.trim($('#tradeMarket').val()),//交易场所
            isQtzq: $.trim($('#isQtzq').val())//是否其他债权类资产
        }
    };
    /**
     * 变更
     */
    var uploadFileUpdate = function(){
        Joyin.ajax(basePath +'valuation/dailyoperation/dailyaccountmake/changesubjectno/ValChangeSubjectNo001/uploadFile',
            {
                changeSubjectType : $("#changeSubjectType").val(),
                fileName: $("#fileNameInput_").val(),
            },
            function(data) {
                if (data.data && data.data.importCheckList) {
                    table.reload({data: data.data.importCheckList});
                }
            });
    };
    /**
     * 脚本生成
     */
    var uploadFileScriptGeneration = function(){
        var object = $.serializeToObject('#submitForm');
        if($("#booksetIds").val()){
            object.booksetIds = $("#booksetIds").val().join(',');
        }
        object.fileName = $("#fileNameInput_").val();
        Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/changesubjectno/ValChangeSubjectNo001/check', object, function(result){
            if (!result.validMessageList) {
                Joyin.exportExcel('scriptGenerationUpload', object);
            }
        });
    };
    /**
     * 画面加载完成
     */
    $(function() {
        /**
         * 绑定“变更”按钮事件
         */
        $('#ValChangeSubjectNo001_btn_submit').click(function() {
            //批量
            if($("#changeSubjectType").val() == CHANGE_SUBJECT_TYPE_02) {
                uploadFileUpdate();
            }
            else {
                var object = getParams();
                if(object.oriSubjectNo == "") {
                    Joyin.error("原科目号不可为空");
                    return;
                }
                if(object.changeSubjectNo == "") {
                    Joyin.error("替换科目号不可为空");
                    return;
                }
                //object.fileName =  $("#fileNameInput_").val();
                Joyin.confirm("确认进行科目号变更？", function () {
                    JoyinProgress.showInDialog('uploadFileProgressBar');
                    Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/changesubjectno/ValChangeSubjectNo001/change', object,
                        function (data) {
                            if (data.data && data.data.importCheckList) {
                                table.reload({data: data.data.importCheckList});
                            }
                        }
                    );
                });
            }
        });
        /**
         * 绑定 【脚本生成】 按钮事件
         */
        $('#ValChangeSubjectNo001_btn_scriptGeneration').click(function() {
            //批量
            if($("#changeSubjectType").val() == CHANGE_SUBJECT_TYPE_02) {
                uploadFileScriptGeneration();
            }
            else {
                var object = getParams();
                if(object.oriSubjectNo == "") {
                    Joyin.error("原科目号不可为空");
                    return;
                }
                if(object.changeSubjectNo == "") {
                    Joyin.error("替换科目号不可为空");
                    return;
                }
                Joyin.confirm("确认进行科目信息变更脚本生成？", function () {
                    var object = $.serializeToObject('#submitForm');
                    if($("#booksetIds").val()){
                        object.booksetIds = $("#booksetIds").val().join(',');
                    }
                    Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/changesubjectno/ValChangeSubjectNo001/check', object, function(result){
                        if (!result.validMessageList) {
                            Joyin.exportExcel('scriptGeneration', object);
                        }
                    });
                });
            }
        });

        /**
         * 绑定“重置”按钮事件
         */
        $('#ValChangeSubjectNo001_btn_reset').click(function() {
            window.location.reload();
        });

        // 处理方式变更
        $("#changeSubjectType").change(function(){
            // 每次切换选项的时候清空之前的值
            $("#booksetIds").empty(); // 账套代码
            $("#oriSubjectNo").val(""); // 原科目号
            $("#changeSubjectNo").empty(); // 替换科目号
            $("#finprodType").empty(); // 金融产品类型
            $("#type2").empty(); // 核算分类
            $("#tradeMarket").empty(); // 交易场所
            $("#isQtzq").empty(); // 是否其他债券投资

            var changeSubjectTypeVal = $("#changeSubjectType").val();
            $("#booksetIdsDiv").hide();
            $("#defultDiv").hide();
            $("#importDiv").hide();
            $("#importResultDiv").hide();
            // $("#defultButtonDiv").hide();
            $("#fileNameInput_").val("");
            // $("#defultButtonDiv").hide();
            $("#downDiv").hide();
            var filDels = $('.layui-upload-drag');
            if(filDels) {
                filDels.each(function(){
                    var _el = $(this), ff = _el.data('file');
                    if(ff) {
                        _el.remove();
                        $('.upload').show();
                    }
                    else if(_el) {
                        //_el.remove();
                    }
                });
            }
            table.reload({data: []});
            if (changeSubjectTypeVal == CHANGE_SUBJECT_TYPE_02) {
                $("#importDiv").show();
                $("#importResultDiv").show();
                $("#downDiv").show();
                $("#condition").hide();
            } else {
                $("#booksetIdsDiv").show();
                $("#defultDiv").show();
                $("#defultButtonDiv").show();
                $("#condition").show();
            }
        });

        // 点击重置按钮
        $("#ValChangeSubjectNo001_btn_TPLDOWLOAD").on('click', function(){
            Joyin.exportExcel('downTemplate', {});
        });
    });

})