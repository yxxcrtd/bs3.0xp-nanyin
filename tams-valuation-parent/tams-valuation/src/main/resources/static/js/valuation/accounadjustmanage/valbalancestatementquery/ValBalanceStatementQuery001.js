$(function () {
    
    var flag1 = false;
    var flag2 = false;
    if(hzSchemeSwitch == "01"){
        flag1 = true;
        flag2 = false;
    }else{
        flag1 = false;
        flag2 = true;
    } 
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            booksetId: $('#booksetId').val()
            ,valDate: $('#valDate').val()
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
        tree:true,
        treeId: 'id',
        treeUpId: 'parentId',
        treeShowName: 'name',
        lazyload:true,
        height: 270,
        searchOnInit: false,
        checkbox: false,
        page : false,
        cols: [
            {field:'id', width:160, title: 'ValBalanceStatementQuery001.subjectno'}
            ,{field:'name', width:300, title: 'ValBalanceStatementQuery001.subjectName', render: function (data, type, row, meta) {
	            if(row.level == 1) {
	                return '<strong>' + data + '</strong>';
	            }
	            return data;
	        }}
            ,{field:'oCcyFmt', width:150, title: 'ValBalanceStatementQuery001.oCcyForGZB'}
            ,{field:'exchangeRate',type:"money", digit: 4, width:150,  title: 'ValBalanceStatementQuery001.exchangeRate'}
            ,{field:'numAmt', type:"number", width:150, title: 'ValBalanceStatementQuery001.numAmt'}
            ,{field:'unitCost', type:"money", width:150, title: 'ValBalanceStatementQuery001.unitCost'}
            ,{field:'oCost', type:"money", width:150, title: 'ValBalanceStatementQuery001.oCost'}
            ,{field:'cost', type:"money", width:150, title: 'ValBalanceStatementQuery001.cost'}
            ,{field:'costPercent', type:"money", digit: 4, width:150,  title: 'ValBalanceStatementQuery001.costPercent'}
            ,{field:'closePrice', type:"money", digit: 4, width:150, title: 'ValBalanceStatementQuery001.closePrice'}
            ,{field:'oMarketValue', type:"money", width:150, title: 'ValBalanceStatementQuery001.oMarketValue'}
            ,{field:'marketValue', type:"money", width:150, title: 'ValBalanceStatementQuery001.marketValue'}
            ,{field:'valuePercent',type:"money", digit: 4, width:150,  title: 'ValBalanceStatementQuery001.valuePercent'}
            ,{field:'valueIncrement', type:"money", width:150, title: 'ValBalanceStatementQuery001.valueIncrement'}
//            ,{field:'balFlagFmt',  sortField:"balFlag", width:150, title: 'ValBalanceStatementQuery001.balFlag'}
            //,{field:'marketDate', visible:flag2, width:150, title: 'ValBalanceStatementQuery001.marketDate'}
            ,{field:'shadowPriceValue', visible:flag1, type:"money", width:150, title: 'ValBalanceStatementQuery001.shadowPriceValue'}
            ,{field:'marketDate',visible:true,width:150, title: 'ValBalanceStatementQuery001.marketDate'}
        ]
      });
    var tableSum=$("#dataTableSum").joyintable({
        ajax: {
            url: 'selectSum',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        searchOnInit: false,
        checkbox: false,
        height: 170,
        page : false,
        cols: [
            {field:'subjectNo', width:160, title: 'ValBalanceStatementQuery001.subjectno'}
            ,{field:'subjectName', align : "right", width:300, title: 'ValBalanceStatementQuery001.subjectName'}
            ,{field:'oCcyFmt', width:150, title: 'ValBalanceStatementQuery001.oCcyForGZB'}
            ,{field:'exchangeRate',type:"money", digit: 4, width:150,  title: 'ValBalanceStatementQuery001.exchangeRate'}
            ,{field:'numAmt', type:"number", width:150, title: 'ValBalanceStatementQuery001.numAmt'}
            ,{field:'unitCost', type:"money",width:150, title: 'ValBalanceStatementQuery001.unitCost'}
            ,{field:'oCost', type:"money", width:150, title: 'ValBalanceStatementQuery001.oCost'}
            ,{field:'cost', type:"money", width:150, title: 'ValBalanceStatementQuery001.cost'}
            ,{field:'costPercent', width:150, align : "right", title: 'ValBalanceStatementQuery001.costPercent'}
            ,{field:'closePrice', type:"money", width:150, title: 'ValBalanceStatementQuery001.closePrice'}
            ,{field:'oMarketValue', type:"money", width:150, title: 'ValBalanceStatementQuery001.oMarketValue'}
            ,{field:'marketValue', type:"money", width:150, title: 'ValBalanceStatementQuery001.marketValue'}
            ,{field:'valuePercent', width:150, align : "right", title: 'ValBalanceStatementQuery001.valuePercent'}
            ,{field:'valueIncrement', type:"money", width:150, title: 'ValBalanceStatementQuery001.valueIncrement'}
//            ,{field:'balFlagFmt',  sortField:"balFlag", width:150, title: 'ValBalanceStatementQuery001.balFlag'}
            //,{field:'marketDate', visible:flag2, width:150, title: 'ValBalanceStatementQuery001.marketDate'}
            ,{field:'shadowPriceValue', visible:flag1, type:"money", width:150, title: 'ValBalanceStatementQuery001.shadowPriceValue'}
            ,{field:'marketDate',visible:true,width:150, title: 'ValBalanceStatementQuery001.marketDate'}
        ],
        done : function(data){
            $("#dataTableSum").next().find('.layui-table-header').remove();
        }
      });
    var exportExcel = function() {
        Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery001/check', getPageParams(), function(result) {
            Joyin.exportExcel('export', getPageParams());
        });
        
    };
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
                        $('#valDate').val(msg);
                        bookSetDate=msg;
                    }
                },
                failure : function() {
                }
            });
        }
    }
    /**
     * 画面加载完成
     */
    $(function() {
      if(isInit){
          table.load();
      }
      // 绑定按钮事件
      $('#ValBalanceStatementQuery001_btn_search').click(function() {
    	  // 修复GZHSXT-1362：避免 输入不存在的账套，导致异常。
    	  var dataId = $("#booksetId").attr("data-id");// 从列表中选择的值
    	  var alt = $("#booksetId").attr("alt");// 当前输入的值
    	  if(dataId != alt){ // 输入不存在的账套
    		  $("#booksetId").val("");
    	  }
    	  
          Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valbalancestatementquery/ValBalanceStatementQuery001/check', getPageParams(), function(result) {
//              if(result.data){
//                  var data = result.data;
//                  if(data.finprodAbbr){
//                      $("#prod").html(data.finprodAbbr+" / "+result.data.finprodId);
//                  }
//                  else{
//                      $("#prod").html(data.finprodName+" / "+result.data.finprodId);
//                  }
//                  var date = $("#valDate").val();
//                  var datearr = date.split("-");
//                  $("#valueDate").html(datearr[0]+"年"+datearr[1]+"月"+datearr[2]+"日");
//                  $("#prodInfo").show();
//              }else{
//                  $("#prodInfo").hide();
//              }
              // 若返回的结果存在，则在账套配置表存在模板标志
              if(result.data){
                  if(result.data == "01"){
                      flag1 = true;
                      flag2 = false;
                  }else{
                      flag1 = false;
                      flag2 = true;
                  } 
              }
              // 不存在，则用系统参数中的模板开关控制
              else{
                  if(hzSchemeSwitch == "01"){
                      flag1 = true;
                      flag2 = false;
                  }else{
                      flag1 = false;
                      flag2 = true;
                  } 
              }
//              table.load();
//              tableSum.load();
//              $("#tableSumDiv").show();
              if(!tableSum.table) {
                  tableSum.load();
                  $("#tableSumDiv").show();
              }
              else {
                  tableSum.reload();
                  $("#tableSumDiv").show();
              }
              if(!table.table) {
                  table.load();
              }
              else {
                  table.reload();
              }
              // 根据标志位显示影子定价还是行情日期
              table.showColumns([
                  //{field:'marketDate', visible: flag2},
                  {field:'shadowPriceValue', visible: flag1}]);
          });
      });
      $('#ValBalanceStatementQuery001_btn_export').click(function() {
          exportExcel();
      });
      $('#booksetId').change(function(){
          gradeChange();
      });
    });
});
