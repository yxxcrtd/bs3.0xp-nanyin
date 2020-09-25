$(function () {
    
    /** 框架版本 2.1.7后，iframe 禁用滚动条 start **/
    var autoSize = function(){
        var height = $(window).height() - 15;
        var width = $(window).width() - 15 ;
        $(".layui-fluid").css({
            "overflow" : "auto",
            "height": height + "px",
            "width": width + "px",
        });
    };
    autoSize();
    $(window).resize(function() {
        autoSize();
    });
    /** 框架版本 2.1.7后，iframe 禁用滚动条 end **/
    
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            queryDate: $('#queryDate').val()
        }
    };
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'selectReserve',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        tree:true,
        treeId: 'id',
        treeUpId: 'parentId',
        treeShowName: 'rowName',
        height: 250,
        searchOnInit: false,
        checkbox: false,
        page : false,
        cols: [
            {field:'rowName', title: '产品账户'}
            ,{field:'rrsBalance', type:"money", digit: 4, width:160, title: 'FlowForecastReport001.rrs'}
            ,{field:'yysBalance',type:"money", digit: 4, width:160,  title: 'FlowForecastReport001.yys'}
            ,{field:'xxxrBalance', type:"number", digit: 4, width:160, title: 'FlowForecastReport001.xxxr'}
            ,{field:'xxxrBBalance', type:"money", digit: 4, width:160, title: 'FlowForecastReport001.xxxrB'}
            ,{field:'lcbBalance', type:"money", width:160, digit: 4, title: 'FlowForecastReport001.lcb'}
            ,{field:'dkNetSum', type:"money", width:160, digit: 4, title: 'FlowForecastReport001.dkNetSum'}
            ,{field:'totalBalance', type:"money",width:160, digit: 4,  title: 'FlowForecastReport001.total'}
        ]
      });
    
    var tableSum = $("#dataTableForecast").joyintable({
        ajax: {
            url: 'selectForcast',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        tree:true,
        treeId: 'id',
        treeUpId: 'parentId',
        treeShowName: 'rowName',
        searchOnInit: false,
        checkbox: false,
        height: 250,
        page : false,
        cols: [
            {field:'rowName', width:160, title: ''}
            ,{field:'targetTimeone', type:"money", digit: 4, width:150, title: ''}
            ,{field:'targetTimetwo', type:"money", digit: 4, width:150, title: ''}
            ,{field:'targetTimethree',type:"money", digit: 4, width:150,  title: ''}
            ,{field:'targetTimefour', type:"money", digit: 4, width:150, title: ''}
            ,{field:'targetTimefive', type:"money", digit: 4, width:150, title: ''}
            ,{field:'targetTimesix', type:"money", digit: 4, width:150, title: ''}
            ,{field:'totalBalance1', type:"money", digit: 4, width:150, title: ''}
            ,{field:'totalBalance2', type:"money", digit: 4, width:150, title: ''}
            ,{field:'totalBalance3', type:"money", digit: 4, width:150, title: ''}
            ,{field:'totalBalance4', type:"money", digit: 4, width:150, title: ''}
        ],
        done : function(data){
            Joyin.ajax(basePath + 'invest/product/flowforecastreport/FlowForecastReport001/getDateAreas', {queryDate: $('#queryDate').val()}, function(result) {
                $(".laytable-cell-10004-targetTimeone:first").html(result[0].dateArea);
                $(".laytable-cell-10004-targetTimetwo:first").html(result[1].dateArea);
                $(".laytable-cell-10004-targetTimethree:first").html(result[2].dateArea);
                $(".laytable-cell-10004-targetTimefour:first").html(result[3].dateArea);
                $(".laytable-cell-10004-targetTimefive:first").html(result[4].dateArea);
                $(".laytable-cell-10004-targetTimesix:first").html(result[5].dateArea);
                $(".laytable-cell-10004-totalBalance1:first").html(result[6].dateArea);
                $(".laytable-cell-10004-totalBalance2:first").html(result[7].dateArea);
                $(".laytable-cell-10004-totalBalance3:first").html(result[8].dateArea);
                $(".laytable-cell-10004-totalBalance4:first").html(result[9].dateArea);
            });
        }
      });
    
    /**
     * 画面加载完成
     */
    $(function() {
      // 绑定按钮事件
      $('#FlowForecastReport001_btn_search').click(function() {
          if(!$("#queryDate").val()){
              layer.alert('请先选择查询日期!');
              return;
          }
          $("#reverseDiv").show();
          $("#forecastDiv").show();
    	  if(!table.table) {
              table.load();
          }
          else {
              table.reload();
          }
    	  if(!tableSum.table) {
              tableSum.load();
          }
          else {
              tableSum.reload();
          }
          
      });
    });
}());
