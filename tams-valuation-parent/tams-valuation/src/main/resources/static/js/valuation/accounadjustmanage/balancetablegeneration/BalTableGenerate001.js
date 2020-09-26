
    var showError = function() {
        layui.layer.alert('生成失败的余额表才可以点击错误明细按钮！');
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
		cols: [
		   {field:'valDate', title : '估值日期',width:120,sort : true},
		   {field:'finprodId', title: '产品代码',width:120,sort : true},
		   {field:'finprodName', title: '产品简称',sort : true},
		   {field:'finprodType', title: '产品类型',width:120,sort : true,
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
                   }else if(row.buildStatus=='2'){
                       return '<span class="layui-badge-rim layui-bg-red">'+'生成失败'+'</span>';
                   }else if(row.buildStatus=='3'){
                       return '<span class="layui-badge-rim layui-bg-blue">'+'已生成（无错误凭证）'+'</span>';
                   }else if(row.buildStatus=='4'){
                       return '<span class="layui-badge-rim layui-bg-red">'+'已生成（有错误凭证）'+'</span>';
                   }
               }
		   },
           {field:'errorType', title: '错误类型', width:100, sort : true},
		   {field:'errorInfo', title : '错误明细', width:100,sort : false, 
//               render:function(){
//		       return '<a href="www.baidu.com">查看</a>';
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
        var flag=true;
        var msg='';
        var arr=new Array();
        for(var i = 0, row; row = rows[i]; i++){
            if(row.buildStatus=='0'){
                flag=false;
                msg='第'+(i+1)+'条产品:'+row.finprodId+'在'+row.valDate+'日未生成余额表，是否继续导出余额表？';
                break;
            }else if(row.buildStatus=='3'){
                var datas = JSON.stringify(rows);
                Joyin.exportExcel('export', {datas:datas});
            }else {
                flag=false;
                msg='第'+(i+1)+'条产品:'+row.finprodId+'在'+row.valDate+'日未生成余额表失败，是否继续导出余额表？';
                break;
            }
        }
        if(!flag){
            layer.alert(msg);
            return;
        }
    };
    
    function handleByHand(){
        var rows=table.getAllCheckedRows();
        if(rows.length<=0){
            layer.alert("请选择一条记录!");
            return;
        }
        var flag=true;
        var msg='';
        index = 0;
        var arr=new Array();
        for(var i = 0, row; row = rows[i]; i++){
            var parms={};
            parms.prodId=row.finprodId;
            parms.valDateBegin=row.valDate;
            parms.valDateEnd=row.valDate;
            parms.buildStatus=row.buildStatus;
            arr.push(parms);
            var parm={
                prodId:row.finprodId,
                valDateBegin:row.valDate,
                valDateEnd:row.valDate,
                buildStatus:row.buildStatus,
            };
            if(row.buildStatus=='0'){
                msg='第'+(i+1)+'条产品:'+row.finprodId+'在'+row.valDate+'日未登账，请先进行登账批处理！';
                
            }else if(row.buildStatus=='3'){
                msg="选择数据中含有已生成（无错误凭证）请重新选择！！";
            }
        }
        if(msg!=null || msg!=''){
            layer.alert(msg);
            return;
        }else{
            //进行确认操作
            Joyin.confirm("确认对勾选记录进行余额表生成?", function(){
                Joyin.ajax("handleByHandCheck",{data:parm},function(data) {
                    if(data > 0){
                        layer.alert("所选日期范围存在已经做过余额表对账的产品，不能重新生成余额表，请重新选择！");
                    }else{
                        Joyin.ajax("handle", /*IF(选中记录.生成状态 = 已生成（无错误凭证）) */
                            {data:JSON.stringify(arr)},
                             function(data) {
                                if(data>=1){
                                    //弹出执行结果
                                    layui.layer.alert("生成成功！",function(index){
                                        if(!table.table){
                                            table.load();
                                        }else{
                                            table.reload();
                                        }
                                    });
                                }else{
                                    //弹出执行结果
                                    layui.layer.alert('生成失败！',function(index){
                                        if(!table.table){
                                            table.load();
                                        }else{
                                            table.reload();
                                        }
                                    });
                                }
                          });
                    }
                });

            }, function() {
                //取消情况...
            });
            
        }
        
//          $.ajax({
//          url : "handleByHandCheck",
//          type : "POST",
//          dataType : "json",
//          data : {data:getSearchParams()},
//          success: function(data) {
//                if(data > 0){
//                    layer.alert("所选日期范围存在已经做过余额表对账的产品，不能重新生成余额表，请重新选择！");
//                }else{
//                    $.ajax({
//                        url : "handleByHandCheck2", /*IF(选中记录.生成状态 = 已生成（无错误凭证）) */
//                        type : "POST",
//                        dataType : "json",
//                        data : {data:getSearchParams()},
//                        success: function(data) {
//                              if(data > 0){
//                            	  Joyin.confirm("选择数据中含有已生成（无错误凭证）是否重新选择？",[confirm, cancel],function() {
//                            		  $.ajax({
//                                          url : "handle", /*IF(选中记录.生成状态 = 已生成（无错误凭证）) */
//                                          type : "POST",
//                                          dataType : "json",
//                                          data : {data:getSearchParams()},
//                                          success: function(data) {
//                                                if(data > 0){
//                                                    layer.alert("所选日期范围存在已经做过余额表对账的产品，不能重新生成余额表，请重新选择！");
//                                                }else{
//                                                    
//                                                }
//                                            }
//                                        });
//                                  }, function(){
//                                      
//                                  });
//                              }else{
//                            	  $.ajax({
//                                      url : "handle", /*IF(选中记录.生成状态 = 已生成（无错误凭证）) */
//                                      type : "POST",
//                                      dataType : "json",
//                                      data : {data:getSearchParams()},
//                                      success: function(data) {
//                                            if(data > 0){
//                                                layer.alert("所选日期范围存在已经做过余额表对账的产品，不能重新生成余额表，请重新选择！");
//                                            }else{
//                                                
//                                            }
//                                        }
//                                    });
//                              }
//                          }
//                      });
//                }
//            }
//        });
        
    }
    
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#BalTableGenerate001_btn_search').click(function() {
            table.load();
        });
        $('#BalTableGenerate001_btn_create').click(function() {
            handleByHand();
        });
        $('#BalTableGenerate001_btn_export').click(function() {
            exportExcel();
        });
    });
	
});