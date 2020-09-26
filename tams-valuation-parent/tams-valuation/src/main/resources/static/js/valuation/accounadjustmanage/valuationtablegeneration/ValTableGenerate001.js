
var showError = function() {
    layui.layer.alert('生成失败的估值表才可以点击错误明细按钮！');
};

$(function () {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            valDateBegin: $.trim($('#valDate').startDate()),
            valDateEnd: $.trim($('#valDate').endDate()),
            prodTypes: $.trim($('#prodType').val()),
            prodIds: $.trim($('#prodId').val()),
            buildStatuses: $.trim($('#buildStatus').val()),
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
//      dblclick:function(table, data) {
//            Joyin.dialog(JoyinUtil.getLabel("查看凭证编号："+data.busVouchnum),
//                    JoyinUtil.getRealPath("/valuation/accounadjustmanage/voucherquery/ValVoucherQuery002/init"),
//                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
//                busVouchnum:data.busVouchnum,tableId : data.tableId},
//                    function(){});
//        },
        cols: [
            {field:'valDate', title : '估值日期',width:120,sort : true},
            {field:'finprodId', title: '账套代码',width:120,sort : true},
            {field:'finprodName', title: '账套简称',sort : true},
            {field:'finprodType', title: '收益类型',width:120,sort : true,
                render: function (data, type, row, meta) {
                    if(row.finprodType=='01'){
                        return '净值';
                    }else if(row.finprodType=='02'){
                        return '预期收益';
                    }else if(row.finprodType=='03'){
                        return '货币';
                    }else {
                        return '结构化产品';
                    }
                }
            },
            {field:'buildStatus', title: '生成状态',width:120,sort : true,
                render: function (data, type, row, meta) {
                    if(row.buildStatus=='0'){
                        return '<span class="layui-badge-rim">'+'未生成'+'</span>';
                    }else if(row.buildStatus=='1'){
                        return '<span class="layui-badge-rim layui-bg-blue">'+'生成成功'+'</span>';
                    }else{
                        return '<span class="layui-badge-rim layui-bg-red">'+'生成失败'+'</span>';
                    }
                }
            },
            {field:'errorType', title: '错误类型', width:100, sort : true},
            {field:'errorInfo', title : '错误明细', width:110,sort : false,
//               render:function(){
//             return '<a href="www.baidu.com">查看</a>';
//               }
                render: function (data, type, row, meta) {
                    if(row.buildStatus=='0'){
                        return  '<a href="javascript:showError();" >'+'查看'+'</a>';
                    }else if(row.buildStatus=='1'){
                        return  '<a href="javascript:showError();" >'+'查看'+'</a>'
                    }else{
                        return  '<a href="javascript:void(0);">'+'查看'+'</a>';
                    }
                }
            }
        ],
    });

    var exportExcel = function() {
        var rows=table.getAllCheckedRows();
        if(rows.length<=0){
            layer.alert("请选择一条记录!");
            return;
        }
//        if(rows.length>=2){
//            layer.alert("请选择一条记录!");
//            return;
//        }
        var flag=true;
        var msg='';
        var arr=new Array();
        for(var i = 0, row; row = rows[i]; i++){
            if(row.buildStatus=='0'){
                flag=false;
                msg='产品'+row.finprodId+'在'+row.valDate+'日未生成估值表，不允许导出！';
                if(!flag){
                    layer.alert(msg);
                    return;
                }
            }else if(row.buildStatus=='1'){
                var datas = JSON.stringify(rows);
                Joyin.exportExcel('export', {datas:datas});
            }else {
                flag=false;
                msg='第'+(i+1)+'条产品:'+row.finprodId+'在'+row.valDate+'日未生成估值表失败，不允许导出！';
                if(!flag){
                    layer.alert(msg);
                    return;
                }
            }
        }
    };

    /**
     * 手动生成
     * @returns
     */
    function handleByHand(){
        //获取选择的数局
        var rows=table.getAllCheckedRows();

        //若未勾选数据，给予提示
        if(rows.length<=0){
            layer.alert("请选择一条记录!");
            return;
        }

        //获取传递参数
        var paArr = new Array();
        for(var i = 0, row; row = rows[i]; i++){
            var pa={
                prodId:row.finprodId,
                valDate:row.valDate,
            };
            paArr.push(pa);
        }

        //进行登账检查，检查是否有产品在当期日期未进行登账的。
        //定义返回状态和信息
        var returnNum = 1;//返回状态
        var returnMessage = "无误";//返回结果信息
        /*        Joyin.ajaxSync("checkAccountsForHandle",
                        {data:JSON.stringify(paArr)},
                    function(data) {
                        if(data!=null){
                            //返回结果
                            returnNum = data['returnNum'];//返回状态
                            returnMessage = data['returnMessage'];//返回结果信息
                        }
                    }
                  );*/
        $.ajax({
            url : "checkAccountsForHandle",
            type : "POST",
            data : {data:JSON.stringify(paArr)},
            async: false,//同步状态，一步一步往下执行
            success: function(data) {
                if(data!=null){
                    //返回结果
                    returnNum = data['returnNum'];//返回状态
                    returnMessage = data['returnMessage'];//返回结果信息
                }
            }
        });
        //提示错误信息
        if(returnNum == 0){
            //弹出检查结果
            layui.layer.alert(returnMessage,function(index){
                if(!table.table){
                    table.load();
                }else{
                    table.reload();
                }
                layui.layer.close(index);
            });

            //停止后续执行
            return;
        }


        //检查勾选数据，是否存在【非未生成（成功和失败的）】状态的数据
        var isHaveOkDate = 0;//默认不存在不是未生成状态的数据
        for(var i = 0, row; row = rows[i]; i++){
            //用状态进行判断，【0：未生成； 1：已生成； 其他：失败的】
            if(row.buildStatus!='0'){
                isHaveOkDate = 1;//存在不是未生成状态的数据
            }
        }

        //进行生成逻辑处理
        var alertMessage = '是否确认生成？';//根据选择的数据，给予提示
        if(isHaveOkDate == 1){
            alertMessage = "所选包含已生成的数据，是否继续生成？";
        }

        //进行确认操作
        Joyin.confirm(alertMessage, function(){
            //调用后台执行手动生成
            Joyin.ajaxSync("handle",
                {data:JSON.stringify(paArr)},
                function(data) {
                    if(data!=null){
                        //弹出执行结果
                        Joyin.alert(data['returnMessage'],null,function(index){// debugger;
                            if(!table.table){
                                table.load();
                            }else{
                                table.reload();
                            }
                            layui.layer.close(index);
                        });
                    }
                });

        }, function() {
            //取消情况...
        });

    }


    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValTableGenerate001_btn_search').click(function() {
            table.load();
        });
        $('#ValTableGenerate001_btn_create').click(function() {
            handleByHand();
        });
        $('#ValTableGenerate001_btn_export').click(function() {
            exportExcel();
        });
    });

});