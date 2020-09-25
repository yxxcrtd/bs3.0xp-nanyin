/**
 * 画面加载
 */
$(function() {
    $('#TrdIRSDuration004_btn_save').click(function () {
        saveOrSubmit('save');
    });
    $('#TrdIRSDuration004_btn_submit').click(function () {
        saveOrSubmit('submit');
    });
    //页面初始化加载固息端付息计划列表
    getFixedPaymentPlanList();
    //页面初始化加载浮息端付息计划列表
    getFloatPaymentPlanList();
    window.fixedCheckState=true; <!--固息端列表校验标识状态-->
    window.floatCheckState=true; <!--浮息端列表校验标识状态-->
});
/**
 * 固息端付息计划列表
 */
function getFixedPaymentPlanList () {
    // alert(JoyinUtil.toJSON($("#fixedPaymentPlanTableMsg").val()));
    var cols = [];
    cols = [ {
        field : 'fixVdate',
        title : '计息开始日',
        sortable : false,
        width : 200,
        edit : true,
        required:true,
        type : 'date'
    }, {
        field : 'fixMdate',
        title : '计息结束日',
        sortable : false,
        width : 200,
        edit : true,
        required:true,
        type : 'date'
    }, {
        field : 'fixPayDate',
        title : '理论支付日',
        sortable : false,
        required:true,
        width : 200,
        edit : true,
        type : 'date'
    }, {
        field : 'statusValid',
        title : '状态',
        sortable : false,
        width : 100,
        edit : false,
        editOption: {type: 'select', multiple: false, searchable: false, showField: 'text', result: '[{"id":"已付息","text":"已付息"},{"id":"未付息","text":"未付息"}]'},
        required:true,
        digit: 4
    }, {
        field : 'remark',
        title : '备注',
        sortable : false,
        width : 200,
        edit : true,
        digit: 4
    },
        {
            field : 'operation',
            title : 'SysCommon.operation',
            type : 'action',
            width : 200,
            buttons : [ 'delt']
        },
        {
            field: 'flag',
            title: 'flag',
            width: 10,
            visible:false
        }
    ];

    var fixedData=$("#fixedPaymentPlanTableMsg").val();
    var fixedDatajsonObj= JSON.parse(fixedData);
    var  fixedPaymentPlanTable = $("#fixedPaymentPlanTable").joyintable({

        /*   ajax: {
                url: JoyinUtil.getRealPath('/invest/period/trdirsduration/TrdIRSDuration004/getFixedPaymentPlanList'),
                type: 'POST',
                dataType: 'json'
            },*/
        // data:[{"fixVdate":"2019-08-13","fixMdate":"2019-08-13","fixPayDate":"2019-08-13","statusValid":"ces","remark":"备注"}],
        data:fixedDatajsonObj,
        cols: cols,
        searchOnInit: true,
        loadonce: true,
        checkbox: false,
        page: true,
        addbtn:true,
        height:200,
        limits:[5,10,15,20,25,30,35,40,45],
        limit:5,
        rowno:true,
        addclick: function(row) {
            //新增一行
            /* var _row = fixedPaymentPlanTable.getRow(0);
             row.fixVdate = _row ? _row.fixVdate : '';
             row.fixMdate = _row ? _row.fixMdate : '';
             row.fixPayDate = _row ? _row.fixPayDate : '';
             row.statusValid='未付息';
             row.flag=1;*/
            var _row = fixedPaymentPlanTable.getRow(fixedPaymentPlanTable.getAllRows().length-2);
            /* if(Date.ofDate(_row.floatMdate).format('yyyy-MM-dd')==$("#mdate").val())
             {
                 return false;
             }*/
            row.fixVdate = _row ? _row.fixMdate : '';//前一行的结束日期
            row.fixMdate = _row ?  Date.ofDate(_row.fixMdate).DateAdd('d', 1).format('yyyy-MM-dd'): '';
            row.fixPayDate = _row ?  row.fixMdate : '';
            row.flag=1;
            row.statusValid='未付息';
        },
        // 编辑行
        editRow: function(preVal, field, rowData) {

        },
        //  编辑单元格之前
        beforeEditCell: function(preVal, field, rowData){
            //	已付息状态的不可编辑

            if(rowData.statusValid=='已付息'&& rowData.flag!=1){
                return false;
            }
            return true;
        },
        // 编辑单元格
        editCell : function(value, data, field, cell, column) {
            var datelist=fixedPaymentPlanTable.getAllRows();
            if(field=='fixMdate'){
                //  alert($("#mdate").val());
                if($("#mdate").val()<data.fixMdate){
                    var rowData = {};
                    Joyin.error("计息结束日不可大于到期日",function(){
                        if(data.LAY_TABLE_INDEX==datelist.length-1)
                        {
                            //最后一条数据不合法设为到期日
                            rowData.fixMdate=$("#mdate").val();
                        }
                        else {
                            rowData.fixMdate = Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].fixVdate).format('yyyy-MM-dd') == '' ? $("#mdate").val() : Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].fixVdate).format('yyyy-MM-dd');//當前日期賦值為下一条数据开始日，如果下一条开始日为空则设为到期日
                        }
                        fixedPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
                    window.fixedCheckState=false;
                    return;
                }
                else {
                    window.fixedCheckState=true;
                }
                if(data.fixMdate<data.fixVdate){
                    var rowData = {};
                    Joyin.error("计息结束日必须大于计息开始日",function(){
                        if(data.LAY_TABLE_INDEX==datelist.length-1)
                        {
                            //最后一条数据不合法设为到期日
                            rowData.fixMdate=$("#mdate").val();
                        }
                        else {
                            rowData.fixMdate = Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].fixVdate).format('yyyy-MM-dd') == '' ? $("#mdate").val() : Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].fixVdate).format('yyyy-MM-dd');//當前日期賦值為下一条数据开始日，如果下一条开始日为空则设为到期日
                        }
                        fixedPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
                    window.fixedCheckState=false;
                    return;
                }
                else {
                    window.fixedCheckState=true;
                }
                //当前结束日期不等于下一条数据开始日期则设为下一条开始日期，如果下一条开始日期为空设为到期日
                if(data.fixMdate!=Date.ofDate(datelist[data.LAY_TABLE_INDEX+1].fixVdate).format('yyyy-MM-dd')){
                    var rowData = {};
                    Joyin.error("计划之间的计划开始日和计划结束日需首尾相连",function(){
                        if(data.LAY_TABLE_INDEX==datelist.length-1)
                        {
                            //最后一条数据不合法设为到期日
                            rowData.fixMdate=$("#mdate").val();
                        }
                        else {
                            rowData.fixMdate = Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].fixVdate).format('yyyy-MM-dd') == '' ? $("#mdate").val() : Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].fixVdate).format('yyyy-MM-dd');//當前日期賦值為下一条数据开始日，如果下一条开始日为空则设为到期日
                        }
                        fixedPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
                    window.fixedCheckState=false;
                    return;
                }
                else {
                    window.fixedCheckState=true;
                }
                //循环遍历之前的列表数据 ,如果当前输入的日期在之前的时间范围内则提示

                for (var i=0;i<datelist.length-1;i++)
                {
                    if( data.fixMdate>Date.ofDate(datelist[i].fixVdate).format('yyyy-MM-dd') && data.fixMdate<Date.ofDate(datelist[i].fixMdate).format('yyyy-MM-dd')){
                        var rowData = {};
                        Joyin.error("计划之间的计划开始日和计划结束日需首尾相连",function(){
                            if(data.LAY_TABLE_INDEX==datelist.length-1)
                            {
                                //最后一条数据不合法设为到期日
                                rowData.fixMdate=$("#mdate").val();
                            }
                            else {
                                rowData.fixMdate = Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].fixVdate).format('yyyy-MM-dd') == '' ? $("#mdate").val() : Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].fixVdate).format('yyyy-MM-dd');//當前日期賦值為下一条数据开始日，如果下一条开始日为空则设为到期日
                            }
                            fixedPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                        });

                        window.fixedCheckState=false;
                        break;
                    }
                    else {
                        window.fixedCheckState=true;
                    }
                }
            }
            if(field=='fixVdate'){
                if(data.fixVdate>data.fixMdate){
                    var rowData = {};
                    Joyin.error("计息结束日必须大于计息开始日",function(){
                        rowData.fixVdate=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].fixMdate).format('yyyy-MM-dd');//上一条数据结束日
                        fixedPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
                    window.fixedCheckState=false;
                    return;
                }
                else {
                    window.fixedCheckState=true;
                }

                if($("#mdate").val()<data.fixVdate){
                    var rowData = {};
                    Joyin.error("计息开始日不可大于到期日",function(){
                        rowData.fixVdate=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].fixMdate).format('yyyy-MM-dd');//上一条数据结束日
                        fixedPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
                    window.fixedCheckState=false;
                    return;
                }
                else {
                    window.fixedCheckState=true;
                }

                if(data.fixVdate!=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].fixMdate).format('yyyy-MM-dd')){
                    var rowData = {};
                    Joyin.error("计划之间的计划开始日和计划结束日需首尾相连",function(){
                        rowData.fixVdate=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].fixMdate).format('yyyy-MM-dd');//上一条数据结束日
                        fixedPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                    });
                    window.fixedCheckState=false;
                    return;
                }
                else {
                    window.fixedCheckState=true;
                }
                //循环遍历之前的列表数据 ,如果当前输入的日期在之前的时间范围内则提示

                for (var i=0;i<datelist.length-1;i++)
                {
                    if( data.fixVdate>Date.ofDate(datelist[i].fixVdate).format('yyyy-MM-dd') && data.fixVdate<Date.ofDate(datelist[i].fixMdate).format('yyyy-MM-dd')){
                        var rowData = {};
                        Joyin.error("计划之间的计划开始日和计划结束日需首尾相连",function(){
                            rowData.fixVdate=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].fixMdate).format('yyyy-MM-dd');//上一条数据结束日
                            fixedPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                        });
                        window.fixedCheckState=false;
                        break;
                    }
                    else {
                        window.fixedCheckState=true;
                    }
                }
            }

        }
    });


    fixedPaymentPlanTable.bind('click', '.delt', function(data, index) {

        //R如果是已付息狀態不允許刪除
        if(data.statusValid=='已付息'&& data.flag!=1)
        {
            Joyin.error("已付息不允许刪除");
            return;
        }
        Joyin.confirm("确定要删除?",function() {
            window.fixedCheckState=true;
            fixedPaymentPlanTable.removeRow(data);
        });
    });

}

/**
 * 浮息端付息计划列表
 */
function getFloatPaymentPlanList () {
    var cols = [];
    cols = [ {
        field : 'floatVdate',
        title : '计息开始日',
        sortable : true,
        width : 200,
        required:true,
        edit : true,
        type : 'date'
    }, {
        field : 'floatMdate',
        title : '计息结束日',
        sortable : true,
        width : 200,
        edit : true,
        type : 'date'
    }, {
        field : 'floatPayDate',
        title : '理论支付日',
        sortable : true,
        width : 200,
        edit : true,
        type : 'date'
    }, {
        field : 'statusValid',
        title : '状态',
        sortable : false,
        width : 200,
        edit : false,
        type:'select',
        editOption: {type: 'select', multiple: false, searchable: false, showField: 'text', result: '[{"id":"已付息","text":"已付息"},{"id":"未付息","text":"未付息"}]'},
        digit: 4,
        required:true
    }, {
        field : 'remark',
        title : '备注',
        sortable : false,
        width : 200,
        edit : true,
        digit: 4
    },
        {
            field : 'operation',
            title : 'SysCommon.operation',
            type : 'action',
            buttons : [ 'delt']
        }
        ,
        {
            field: 'flag',
            title: 'flag',
            width: 10,
            visible:false
        }
    ];
    var floatData=$("#floatPaymentPlanTableMsg").val();
    var floatDatajsonObj= JSON.parse(floatData);
    var floatPaymentPlanTable = $("#floatPaymentPlanTable").joyintable(
        {
            /*   ajax: {
                    url: JoyinUtil.getRealPath('/invest/period/trdirsduration/TrdIRSDuration004/getFloatPaymentPlanList'),
                    type: 'POST',
                    dataType: 'json'
                },*/
            data:floatDatajsonObj,
            cols: cols,
            searchOnInit: true,
            loadonce: true,
            checkbox: false,
            addbtn:true,
            page: true,
            limit:5,
            limits:[5,10,15,20,25,30,35,40,45],
            height: 200,
            rowno:true,
            addclick: function(row) {
                //新增一行
                var _row = floatPaymentPlanTable.getRow(floatPaymentPlanTable.getAllRows().length-2);
                /* if(Date.ofDate(_row.floatMdate).format('yyyy-MM-dd')==$("#mdate").val())
                 {
                     return false;
                 }*/
                row.floatVdate = _row ? _row.floatMdate : '';//前一行的结束日期
                row.floatMdate = _row ?  Date.ofDate(_row.floatMdate).DateAdd('d', 1).format('yyyy-MM-dd'): '';
                row.floatPayDate = _row ?  row.floatMdate : '';
                row.flag=1;
                row.statusValid='未付息';
            },
            // 编辑行
            editRow: function(preVal, field, rowData) {

            },
            //  编辑单元格之前
            beforeEditCell: function(preVal, field, rowData){
                //	已付息状态的不可编辑
                if(rowData.statusValid=='已付息'&& rowData.flag!=1){
                    return false;
                }
                return true;
            },
            // 编辑单元格
            editCell : function(value, data, field, cell, column) {
                var datelist=floatPaymentPlanTable.getAllRows();
                if(field=='floatMdate'){
                    //  alert($("#mdate").val());
                    if($("#mdate").val()<data.floatMdate){
                        var rowData = {};
                        Joyin.error("计息结束日不可大于到期日",function(){
                            if(data.LAY_TABLE_INDEX==datelist.length-1)
                            {
                                //最后一条数据不合法设为到期日
                                rowData.floatMdate=$("#mdate").val();
                            }
                            else {
                                rowData.floatMdate = Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].floatVdate).format('yyyy-MM-dd') == '' ? $("#mdate").val() : Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].floatVdate).format('yyyy-MM-dd');//當前日期賦值為下一条数据开始日，如果下一条开始日为空则设为到期日
                            }

                            floatPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                        });
                        window.floatCheckState=false;
                        return;
                    }
                    else {
                        window.floatCheckState=true;
                    }
                    if(data.floatMdate<data.floatVdate){
                        var rowData = {};
                        Joyin.error("计息结束日必须大于计息开始日",function(){
                            if(data.LAY_TABLE_INDEX==datelist.length-1)
                            {
                                //最后一条数据不合法设为到期日
                                rowData.floatMdate=$("#mdate").val();
                            }
                            else {
                                rowData.floatMdate = Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].floatVdate).format('yyyy-MM-dd') == '' ? $("#mdate").val() : Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].floatVdate).format('yyyy-MM-dd');//當前日期賦值為下一条数据开始日，如果下一条开始日为空则设为到期日
                            }

                            floatPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                        });
                        window.floatCheckState=false;
                        return;
                    }
                    else {
                        window.floatCheckState=true;
                    }

                    //当前结束日期不等于下一条数据开始日期则设为下一条开始日期，如果下一条开始日期为空设为到期日
                    if(data.floatMdate!=Date.ofDate(datelist[data.LAY_TABLE_INDEX+1].floatVdate).format('yyyy-MM-dd')){
                        var rowData = {};
                        Joyin.error("计划之间的计划开始日和计划结束日需首尾相连",function(){
                            if(data.LAY_TABLE_INDEX==datelist.length-1)
                            {
                                //最后一条数据不合法设为到期日
                                rowData.floatMdate=$("#mdate").val();
                            }
                            else {
                                rowData.floatMdate = Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].floatVdate).format('yyyy-MM-dd') == '' ? $("#mdate").val() : Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].floatVdate).format('yyyy-MM-dd');//當前日期賦值為下一条数据开始日，如果下一条开始日为空则设为到期日
                            }
                            fixedPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                        });
                        window.floatCheckState=false;
                        return;
                    }
                    else {
                        window.floatCheckState=true;
                    }
                    //循环遍历之前的列表数据 ,如果当前输入的日期在之前的时间范围内则提示

                    for (var i=0;i<datelist.length-1;i++)
                    {
                        if( data.floatMdate>Date.ofDate(datelist[i].floatVdate).format('yyyy-MM-dd') && data.floatMdate<Date.ofDate(datelist[i].floatMdate).format('yyyy-MM-dd')){
                            var rowData = {};
                            Joyin.error("计划之间的计划开始日和计划结束日需首尾相连",function(){
                                if(data.LAY_TABLE_INDEX==datelist.length-1)
                                {
                                    //最后一条数据不合法设为到期日
                                    rowData.floatMdate=$("#mdate").val();
                                }
                                else {
                                    rowData.floatMdate = Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].floatVdate).format('yyyy-MM-dd') == '' ? $("#mdate").val() : Date.ofDate(datelist[data.LAY_TABLE_INDEX + 1].floatVdate).format('yyyy-MM-dd');//當前日期賦值為下一条数据开始日，如果下一条开始日为空则设为到期日
                                }

                                floatPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                            });
                            window.floatCheckState=false;
                            break;

                        }
                        else {
                            window.floatCheckState=true;
                        }
                    }
                }
                if(field=='floatVdate'){
                    if(data.floatVdate>data.floatMdate){
                        var rowData = {};
                        Joyin.error("计息结束日必须大于计息开始日",function(){
                            rowData.floatVdate=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].floatMdate).format('yyyy-MM-dd');//上一条数据结束日
                            floatPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                        });
                        window.floatCheckState=false;
                        return;
                    }
                    else {
                        window.floatCheckState=true;
                    }

                    if($("#mdate").val()<data.floatVdate){
                        var rowData = {};
                        Joyin.error("计息开始日不可大于到期日",function(){
                            rowData.floatVdate=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].floatMdate).format('yyyy-MM-dd');//上一条数据结束日
                            floatPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                        });
                        window.floatCheckState=false;
                        return;
                    }
                    else {
                        window.floatCheckState=true;
                    }

                    if(data.floatVdate!=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].floatMdate).format('yyyy-MM-dd')){
                        var rowData = {};
                        Joyin.error("计划之间的计划开始日和计划结束日需首尾相连",function(){
                            rowData.floatVdate=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].floatMdate).format('yyyy-MM-dd');//上一条数据结束日
                            floatPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                        });
                        window.floatCheckState=false;
                        return;
                    }
                    else {
                        window.floatCheckState=true;
                    }
                    //循环遍历之前的列表数据 ,如果当前输入的日期在之前的时间范围内则提示

                    for (var i=0;i<datelist.length-1;i++)
                    {
                        if( data.floatVdate>Date.ofDate(datelist[i].floatVdate).format('yyyy-MM-dd') && data.floatVdate<Date.ofDate(datelist[i].floatMdate).format('yyyy-MM-dd')){
                            var rowData = {};
                            Joyin.error("计划之间的计划开始日和计划结束日需首尾相连",function(){
                                rowData.floatVdate=Date.ofDate(datelist[data.LAY_TABLE_INDEX-1].floatMdate).format('yyyy-MM-dd');//上一条数据结束日
                                floatPaymentPlanTable.renderRow(data.LAY_TABLE_INDEX, rowData);
                            });
                            window.floatCheckState=false;
                            break;
                        }
                        else {
                            window.floatCheckState=true;
                        }
                    }
                }
            }

        });

    floatPaymentPlanTable.bind('click', '.delt', function(data, index) {

        //R如果是已付息狀態不允許刪除
        if(data.statusValid=='已付息'&& data.flag!=1)
        {
            Joyin.error("已付息不允许删除");
            return;
        }
        Joyin.confirm("确定要删除?",function() {
            window.floatCheckState=true;
            floatPaymentPlanTable.removeRow(data);
        });

    });
}
/**
 * 提交和保存
 */
function saveOrSubmit(operateType) {
    /*  if(!window.fixedCheckState ||!window.floatCheckState)
      {
          Joyin.error("数据不合法，请重新检查");
          return;
      }*/

    var object = $.serializeToObject('#submitForm');
    object.operateType = operateType;
    Joyin.ajaxJson(basePath + 'invest/period/trdirsduration/TrdIRSDuration004/check', object, function (result) {
        Joyin.confirm(result.data, function () {
            if (operateType === 'save') {
                WorkFlow.doSaveList(basePath + 'invest/period/trdirsduration/TrdIRSDuration004/save', object, "TrdIRSDuration004_btn_save", "TrdIRSDuration004", function () {

                });
            }
            else {
                WorkFlow.doSubmitList(basePath + 'invest/period/trdirsduration/TrdIRSDuration004/save', object, "TrdIRSDuration004_btn_submit", "TrdIRSDuration004", function () {

                });
            }
        });
    });
}

