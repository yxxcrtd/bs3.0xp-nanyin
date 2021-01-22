$(function() {

    function saveOrSubmit(operateType) {
        var trdAccountmfConfigdetail = table.getAllRows();
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.trdAccountmfConfigdetail = trdAccountmfConfigdetail;
        Joyin.ajaxJson(basePath + 'invest/portfolio/feeprovisionsetting/FeeProvisionSetting002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/portfolio/feeprovisionsetting/FeeProvisionSetting002/save', object, "FeeProvisionSetting002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/portfolio/feeprovisionsetting/FeeProvisionSetting002/save', object, "FeeProvisionSetting002_btn_submit");
                }
            });
        });
    };

    /**
     * 页面加载初始化
     */
    var options = {
        page: true,
        ajax: {
            url: JoyinUtil.getRealPath('invest/portfolio/feeprovisionsetting/FeeProvisionSetting002/findPage'),
            type: 'POST',
            data: function() {
                return {seqNo: $('#seqNo').val(),
                    workflowId: $('#workflowId').val()
                };
            }
        },
        checkbox: false,
        addbtn: true,
        searchOnInit: true,
        cols:[
            {field:'booksetId', width: 150, title: 'FeeProvisionSetting002.booksetId', edit:true,sortable:false, editOption:{type:'select',id:'booksetId',showField:'id',formatoption:true,
                url: JoyinUtil.getRealPath('/invest/portfolio/feeprovisionsetting/FeeProvisionSetting002/getBooksetId'),postData: function(){
                    return {
                        positionStartDate: $("#FeeProvisionSetting002_positionStartDate").val()
                    }
                }}},
            {field:'feeAmtDetail', width: 100, title: 'FeeProvisionSetting002.feeAmtTotal', edit:true, type:'number', sortable:false,textrange:'2'},
            {field:'operation', width: 100, title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ]
    };

    /**
     * 绑定表格事件
     */
    var table = $("#dataTable").joyintable(options);
    /**
     *绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });

    //计提结束日更改与实际到期日联动
    $('#FeeProvisionSetting002_positionEndDate').change(function(){
        $("#FeeProvisionSetting002_actPositionEndDate").val($("#FeeProvisionSetting002_positionEndDate").val());
    });

    /**
     * 下拉框进行选择时进行一些联动操作
     */
    var secAcctIdChange = function() {
        var secAcctId = $("#FeeProvisionSetting002_secAcctId").val();
        if(secAcctId){
            Joyin.ajax(basePath + 'invest/portfolio/feeprovisionsetting/FeeProvisionSetting002/selectOrgInfo', {'secAcctId':secAcctId}, function(data){
                if(data){
                    $("#FeeProvisionSetting002_registOrg").val(data);
                    $('#FeeProvisionSetting002_registOrg').textSelect('setValue',data);
                }
            });
        }
    }

    /**
     * 画面加载完成
     */
    $("#FeeProvisionSetting002_payCycle").attr("disabled",true);
    $("#FeeProvisionSetting002_feeType").attr("disabled",true);
    $("#FeeProvisionSetting002_registOrg").attr("disabled",true);
    if($("#seqNo").val()){
        $("#FeeProvisionSetting002_secAcctId").attr("disabled",true);
        $("#FeeProvisionSetting002_payDate").attr("disabled",true);
        $("#FeeProvisionSetting002_registOrg").attr("disabled",true);
    }
    // 绑定按钮事件
    $('#FeeProvisionSetting002_btn_save').click(function() {
        saveOrSubmit('save');
    });
    $('#FeeProvisionSetting002_btn_submit').click(function() {
        saveOrSubmit('submit');
    });

    //绑定下拉框改变事件
    $('#FeeProvisionSetting002_secAcctId').change(function() {
        secAcctIdChange();
    });
}());