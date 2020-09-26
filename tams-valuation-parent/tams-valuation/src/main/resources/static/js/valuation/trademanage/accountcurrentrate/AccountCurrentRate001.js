$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            accountType: $.trim($('#accountType').val()),//账户类型
            account: $.trim($('#account').val()),//账户
            intType: $.trim($('#intType').val())
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
        dblclick:function(table, data) {//绑定双击事件
            Joyin.dialog(JoyinUtil.getLabel("查看账户活期利率"),
                    JoyinUtil.getRealPath("/valuation/trademanage/accountcurrentrate/AccountCurrentRate002/init"),
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                cashId:data.cashId,effDate:data.effDate,booksetId:data.booksetId,ccy:data.ccy},
                    function(){
                    //table.reload();
                    });
        },
        
        
        
        
        cols: [
           {field:'booksetId', title: '账套代码', sort: true},
           {field:'acctNo', title: '账号', sort: true},
           {field:'acctName', title: '账户名', sort: true},
           {field:'acctType', title: '账户类型', sort: true,visible:false},
           {field:'acctTypeCode', title: '账户类型', sort: true,sortField:'acctType'},
           {field:'basis', title: '计息基础', sort: true},
           {field:'yield', title: '利率(%)/基准利率',sort: true,align:'right'},
           {field:'effDate', title: '生效日',sort: true},
           {field:'calcTypeFt', title: '利率类型',sortField:'calcType',event:'calcTypetool',render: function (data, type, row, meta) {
              if(row.calcType=='02'){
//                  var html ="<a href="+basePath+"valuation/basedata/marketdata/intratequote/ValIntRateQuote001/init?intRateId="+row.yield+">"+row.calcTypeFt+"</a>";
//                  var button = "<button value="+row.yield+">"+row.calcTypeFt+"</button>";
//                  var html ="<a onclick=&quot;javascript:return false&quot; href="+basePath+"valuation/basedata/marketdata/intratequote/ValIntRateQuote001/init?intRateId="+row.yield+">"+row.calcTypeFt+"</a>";
                    var html = "<a href=javascript:void(0); onclick=js_method(&quot;"+row.yield+"&quot;,&quot;"+basePath+"&quot;)>"+row.calcTypeFt+"</a>";
//                  data.status="A";
                 
//                  var newTab = {
//                      pageId: "ValIntRateQuote001",
//                      title: '利率信息',
//                      url:'/valuation/basedata/marketdata/intratequote/ValIntRateQuote001/init',
//                      id: 'VALINFOSERVICE_001_004_005', 
//                      icon: ''
//                  };
//                  
//                  var newTop =top.tab.tabAdd(newTab);
                  
                  
                  return html;
              }
              else{
                  return row.calcTypeFt;
              }
                  
               
               
               
           }},
           {field:'calcType', title: '利率类型', sort: true,visible:false},
           {field:'cashId', title: '现金流代码', visible:false},
           {field:'ccy', title: '币种', visible:false},
           {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ],
    }); 
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };  
    
    
    
    var deleteData = function(){
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            console.log(selArr);
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("没有选中数据"));
            return;
        }

        var object = {};
        object.listAccountCurrentRate001Dto = selArr;
        object.data = datas;
        object.operateType = "delete";
//        var message = "是否确认删除？"+selArr.length+"条记录";
        var message = "是否确认删除？";
        Joyin.ajaxJson(basePath + 'valuation/trademanage/accountcurrentrate/AccountCurrentRate001/deleteCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
//      
                WorkFlow.doDeleteList(
                        basePath + 'valuation/trademanage/accountcurrentrate/AccountCurrentRate001/deleteList', 
                        object, 
                        "AccountCurrentRate001_btn_delete", 
                        "AccountCurrentRate002",
                        function() {
                            table.reload();
                        }        
                );
                
                
            });
        });
        
        
        
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#AccountCurrentRate001_btn_search').click(function() {
            table.load();
        });       
        //  绑定导出事件
        $('#AccountCurrentRate001_btn_export').click(function() {
            exportExcel();
        });
        
        // 绑定新建事件
        $('#AccountCurrentRate001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("新建账户活期利率（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                        basePath + "valuation/trademanage/accountcurrentrate/AccountCurrentRate002/init",
                        "80%", "80%", {pageModel:PAGE_MODEL_NEW},function(){
                            table.reload(); 
                        });
        });
        
        
        $('#AccountCurrentRate001_btn_delete').click(function() {
            deleteData();
        });


    });
    
//    table.bind('click',){
//        
//        
//    };
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
// 
            Joyin.dialog(JoyinUtil.getLabel("修改账户活期利率   （ "+ "<span style='color:red'> * </span>" + "必填）"),
                  basePath + "valuation/trademanage/accountcurrentrate/AccountCurrentRate002/init",
                  "80%", "80%",  {cashId:data.cashId,effDate:data.effDate,booksetId:data.booksetId,ccy:data.ccy,status:"01", pageModel : PAGE_MODEL_EDIT}, function(){
                      table.reload();
                      pageModel : PAGE_MODEL_EDIT
               });
       
        
        
        

    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {};
        object.cashId = data.cashId;
        object.effDate = data.effDate;
        object.acctName = data.acctName;
        object.acctNo = data.acctNo;
        object.operateType = "DELETE";
//        var message = "是否删除"+"["+data.acctNo+"]帐户"+data.effDate+"的记录";
        var message = "是否确认删除？";
        Joyin.ajaxJson(basePath + 'valuation/trademanage/accountcurrentrate/AccountCurrentRate001/deleteCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
//             Joyin.ajaxJson(basePath + 'valuation/trademanage/accountcurrentrate/AccountCurrentRate001/delete', object, function(result) {
//                 table.reload();
//             });
                WorkFlow.doDeleteList(
                        basePath + 'valuation/trademanage/accountcurrentrate/AccountCurrentRate001/delete', 
                        object, 
                        "AccountCurrentRate001_btn_delete", 
                        "AccountCurrentRate002",
                        function() {
                            table.reload();
                        }        
                );
                
                
                
            });
        });
  });
    
    
    
    
    
    
    $("#accountType").change(function(){
        $("#account").val("");
    });
    
    
});



