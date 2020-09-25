/**
 * 付息计划详情
 */
$(function() {
    var getElementById = function(id,type) {
        if(type=="div"){
            return $('#' + entityName_FinPayInterestStdInfo + '_' + id+'_'+'div');
        }else{
            return $('#' + entityName_FinPayInterestStdInfo + '_' + id);
        }
    };
    //获取还本信息元素id
    var getBussFinRepayElmentById = function(id){
        return $('#' + entityName_BussFinRepayment + '_' + id);
    }
    //获取计息要素画面元素id
    var getFinInterestFactorElementById = function(id){
        return $('#' + entityName_FinInterestFactor + '_' + id);
    }
    var cols = [];
    cols = [ {
        field : 'vdate',
        title : 'FinPayInterestStdInfo.vdate',
        sortable : true,
        width : 200,
        edit : false,
        type : 'date'
    }, {
        field : 'mdate',
        title : 'FinPayInterestStdInfo.mdate',
        sortable : true,
        width : 200,
        edit : false,
        type : 'date'
    }, {
        field : 'payDate',
        title : 'FinPayInterestStdInfo.payDate',
        sortable : true,
        width : 200,
        edit : false,
        type : 'date'
    }, {
        field : 'rangeYield',
        title : 'FinPayInterestStdInfo.intervalRate2',
        sortable : false,
        width : 200,
        edit : false,
        type : 'number',
        digit: 4
    }, {
        field : 'intPrinAmt',
        title : 'FinPayInterestStdInfo.prinAmt',
        sortable : false,
        width : 200,
        edit : false,
        type : 'number',
        digit: 4
    }, {
        field : 'intAmt',
        title : 'FinPayInterestStdInfo.intAmt',
        sortable : false,
        width : 200,
        edit : false,
        type : 'number',
        digit: 16
    }, {
        field : 'vdateY',
        type : 'date',
        visible : false
    }, {
        field : 'vdateTerm',
        type : 'date',
        visible : false
    }, {
        field : 'mdateTerm',
        type : 'date',
        visible : false
    }, {
        field : 'termdays',
        type : 'number',
        visible : false
    }, {
        field : 'frequency',
        type : 'number',
        visible : false
    }
    ];
    var options = {
            ajax: {
                url: JoyinUtil.getRealPath('/component/asset/FinPayInterestStdInfo/generationSubPlan'),
                type: 'POST',
                dataType: 'json',
                data: function () {
                    return getPreviewParams();
                }
            },
            cols: cols,
            searchOnInit: true,
            loadonce: true,
            checkbox: false,
            page: false,
            contextmenu : false
        };
    subPlanTable = $("#subPlanTable").joyintable(options);
    /**
     * 获取参数
     * @returns
     */
    function getPreviewParams() {
        return JSON.parse($('#finPayInterestStdInfo_JsonStr').val());
    }
});