$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            finprodId: $.trim($('#finprodId').val()), 
//          vadte:$('#vdate').val(),
//          mdate:$('#mdate').val()
//          profitType:$.trim($('#profitType').val()),
            /*vdateStr:$('#vdateStr').val(),   
            mdateStr:$('#mdateStr').val()*/
            vdateBegin: $('#vdate').startDate(),
            vdateEnd: $('#vdate').endDate(),
            mdateBegin: $('#mdate').startDate(),
            mdateEnd: $('#mdate').endDate()
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
        checkbox: true,
        cellMinWidth : 100,
        dblclick:function(table, data) {//绑定双击事件
            Joyin.dialog(JoyinUtil.getLabel("查看存款类资产"),
                    JoyinUtil.getRealPath("/valuation/assets/posit/ValDespositAssetQuery002/init"),
                    "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                finprodId:data.finprodId},
                    function(){
                    //table.reload();
                    });
        },
        cols: [
           {field:'finprodId', title: '资产代码', sort: true},
           {field:'finprodName', title: '资产名称', sort: true},
          /* {field:'depositTypeName', title: '存款类型', sort: true,sortField: "depositType"},*/
           {field:'finprodType', title: '存款类型', sort: true,visible:false,},
//           {field:'positAddr', title: '存款机构',sort: true},
           {field:'issueAmt', title: '存放本金(元)',sort: true,type:"money",width:140},
           {field:'vdate1', title: '起息日', sort: true},
           {field:'mdate1', title: '到期日', sort: true},
           {field:'basis', title: '计息基础', sort: true},
           {field:'intType', title: '利率类型', sortField:'intTypeCode',sort: true},
//           {field:'intTypeCode', title: '利率类型', sort: true,visible:false},
           {field:'rate', title: '最新利率(%)',type:"money",pattern: '#.00', sort: true},
           {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ],
    });
    
    /**
     * 导出
     */
//    var exportExcel = function() {
//        var paramObj = getSearchParams();
//        Joyin.exportExcel('export', paramObj);
//    };
    
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
            var selAll = table.getAllRows();
        }
        
        if (!selAll || selAll.length == 0) {
            Joyin.error(JoyinUtil.getMessage("表格无数据！"));
            return;
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
    


        
        
     
          
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValDespositAssetQuery001_btn_search').click(function() {
            table.load();
        });
        $('#ValDespositAssetQuery001_btn_export').click(function() {
           exportExcel();
       });
        // 绑定新建事件
        $('#ValDespositAssetQuery001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("新建（<i class='iconfont must' style='display:inline'>*</i>必填）"),
                        basePath + "valuation/assets/posit/ValDespositAssetQuery002/init",
                        "95%", "90%", {pageModel:PAGE_MODEL_NEW},function(){
                            table.reload();
                        });
        });
        
        
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        var object = {};
        object.finprodId = data.finprodId;
        Joyin.ajaxJson(basePath + 'valuation/assets/posit/ValDespositAssetQuery001/updateCheck', object, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("编辑存款资产（ "+ "<span style='color:red'> * </span>" + "必填）"),
                  basePath + "valuation/assets/posit/ValDespositAssetQuery002/init",
                  "90%", "90%",  {finprodId:data.finprodId,status:"01", pageModel : PAGE_MODEL_EDIT}, function(){
                      table.reload();
                      pageModel : PAGE_MODEL_EDIT
               });
           });
        
        
        
//        Joyin.dialog(JoyinUtil.getLabel("修改"), basePath
//                + "valuation/assets/posit/ValDespositAssetQuery002/init",
//                "90%",
//                "90%",
//                {finprodId:data.finprodId,status:"01", pageModel : PAGE_MODEL_EDIT},    
//                function(){
//                    table.reload();
//                    pageModel : PAGE_MODEL_EDIT
//                }
//          );
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {};
        object.finprodId = data.finprodId;
        var message = "是否删除"+"["+data.finprodId+"]产品";
        Joyin.ajaxJson(basePath + 'valuation/assets/posit/ValDespositAssetQuery001/deleteCheck', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
                
                WorkFlow.doDeleteList(
                        basePath + 'valuation/assets/posit/ValDespositAssetQuery001/delete', 
                        object, 
                        "ValDespositAssetQuery001_btn_delete", 
                        "ValDespositAssetQuery002",
                        function() {
                            table.reload();
                        }        
                );
                
//             Joyin.ajaxJson(basePath + 'valuation/assets/posit/ValDespositAssetQuery001/delete', object, function(result) {
//                 table.reload();
//             });
//           
            
            
            });
        });
  });
    
});