/**
 * 增信信息组件JS
 * @auth zhangnian
 */
(function(){
    var getElementById = function(id) {
        return $('#' + entityName_BussFinProductCredit + '_' + id);
    };
    //初始化
    /**
     * 定义数据表格
     */
    var cols = [
        {field:'creditFlag',  title: 'BussFinProductCredit.creditFlag',edit:true,sortable: false,
            editOption:{id:'creditFlag',type:'select',multiple: false, showField:'text',searchable: true,initValue:$('#creditFlag').val(),
                url: JoyinUtil.getRealPath('/component/project/BussFinProductCredit/getMarketInstitutionCustomerName'),postData:function () {
                    return {/*CUSTOMER_TYPE:customerType*/};
                }
            }
        }
        ,{field:'creditType',  title: 'BussFinProductCredit.creditType',editOption:{type:'select', multiple: false,result: CREDIT_TYPE},edit:true}
        ,{field:'remark',  title: 'BussFinProductCredit.remark',edit:true}
        ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
    ]
    var table =$("#bussFinProductCreditTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/component/project/BussFinProductCredit/initInfo'),
            type: 'POST',
            data: getPreviewParams()
        },
        cols:cols,
        searchOnInit: true,
        contextmenu : false,
        loadonce:true,
        checkbox:false,
        height:290,
        addbtn:true
    });

    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    var getAllRows = function(){
        return table.getAllRows();
    }

    /**
     * 获取参数
     * @returns
     */
    function getPreviewParams() {
        var param = {}
        param.finprodId = $("#finprodId").val();
        param.workflowId = $("#workflowId").val();
        param.entityName = entityName_BussFinProductCredit;
        param.pageModel = pageModel;
        return param;
    }

    // 公开接口
    window.BussFinProductCredit = {
        getAllRows:getAllRows
    };

}());