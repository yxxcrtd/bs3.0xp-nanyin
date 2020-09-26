$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            booksetId: $('#booksetId').val()
            ,balDate: $('#balDate').val()
        }
    };
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        checkbox: false,
        searchOnInit: false,
        cellMinWidth:100 ,
        isMulti:true,
        cols: [[
                {field:'subjectNo', width:100, title: 'ValBalanceStatementQuery001.subjectNo',sortable: false, rowspan: 2,fixed:'left'}
                ,{field:'subjectName', width:100, title: 'ValBalanceStatementQuery001.subjectName',sortable: false, rowspan: 2,fixed:'left'}
                ,{align: "center", title: 'ValBalanceStatementQuery001.oBalance',sortable: false,align:"center", colspan: 7}
                ,{align: 'center', title: 'ValBalanceStatementQuery001.bBalance',sortable: false,align:'center', colspan: 6}
            ],[
                {field:'oCcyFmt', width:130,sortable: false, title: 'ValBalanceStatementQuery001.oCcy'}
                ,{field:'oOpenBalFlagFmt', width:128,sortable: false, title: 'ValBalanceStatementQuery001.oOpenBalFlag'}
                ,{field:'oOpenBalance', type:"money", width:128,sortable: false, title: 'ValBalanceStatementQuery001.oOpenBalance'}
                ,{field:'oHappenAmtD', type:"money", width:160,sortable: false, title: 'ValBalanceStatementQuery001.oHappenAmtD'}
                ,{field:'oHappenAmtC', type:"money", width:160,sortable: false, title: 'ValBalanceStatementQuery001.oHappenAmtC'}
                ,{field:'oEndBalFlagFmt', width:128,sortable: false, title: 'ValBalanceStatementQuery001.oEndBalFlag'}
                ,{field:'oEndBalance', type:"money", width:128,sortable: false, title: 'ValBalanceStatementQuery001.oEndBalance'}
                ,{field:'bOpenBalFlagFmt', width:128,sortable: false, title: 'ValBalanceStatementQuery001.bOpenBalFlag'}
                ,{field:'bOpenBalance', type:"money", width:128,sortable: false, title: 'ValBalanceStatementQuery001.bOpenBalance'}
                ,{field:'bHappenAmtD', type:"money", width:160,sortable: false, title: 'ValBalanceStatementQuery001.bHappenAmtD'}
                ,{field:'bHappenAmtC', type:"money", width:160,sortable: false, title: 'ValBalanceStatementQuery001.bHappenAmtC'}
                ,{field:'bEndBalFlagFmt', width:128,sortable: false, title: 'ValBalanceStatementQuery001.bEndBalFlag'}
                ,{field:'bEndBalance', type:"money", width:128,sortable: false, title: 'ValBalanceStatementQuery001.bEndBalance'}
            ]]
      });
    var gradeChange=function(){
        var booksetId=$.trim($('#booksetId').val());
        if(booksetId==''||booksetId==null){
            return;
        }else{
            $.ajax({
                url : 'checkBokkSetDate',
                type : "POST",
                data : {booksetId:booksetId},
                success: function(msg){
                    if(msg=='-1'){
                        $('#booksetId').val('').trigger('change');
                        layer.alert('所选账套的会计日期不一致，请分开执行!!');
                    }else{
                        $('#balDate').val(msg);
                        bookSetDate=msg;
                    }
                },
                failure : function() {
                }
            });
        }
    }
    
    var exportExcel = function() {
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/bokbalancestatementquery/BokBalanceStatementQuery001/check', getPageParams(), function(result) {
      if(result.data){
        var paramObj = getPageParams();
        Joyin.exportExcel('export', paramObj);   
            }
        });
    };

    /**
     * 画面加载完成
     */
    $(function() {
        if(isInit){
            table.load();
        }
      // 绑定按钮事件
      $('#BokBalanceStatementQuery001_btn_search').click(function() {
    	  // 修复GZHSXT-1362：避免 输入不存在的账套，导致异常。
    	  var dataId = $("#booksetId").attr("data-id");// 从列表中选择的值
    	  var alt = $("#booksetId").attr("alt");// 当前输入的值
    	  if(dataId != alt){ // 输入不存在的账套
    		  $("#booksetId").val("");
    	  }
    	  
          Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/bokbalancestatementquery/BokBalanceStatementQuery001/check', getPageParams(), function(result) {
              if(result.data){
                  var data = result.data;
                  if(data.booksetName){
                      $("#prod").html(data.booksetName+" / "+result.data.booksetId);
                  }
                  var date = $("#balDate").val();
                  var datearr = date.split("-");
                  $("#valueDate").html(datearr[0]+"年"+datearr[1]+"月"+datearr[2]+"日");
                  $("#prodInfo").show();
              }else{
                  $("#prodInfo").hide();
              }
              if(!table.table) {
                  table.load();
              }
              else {
                  table.reload();
              }
          });
      });
      $('#BokBalanceStatementQuery001_btn_export').click(function() {
          exportExcel();
      });
      $('#booksetId').change(function(){
          gradeChange();
      });
    });
});
