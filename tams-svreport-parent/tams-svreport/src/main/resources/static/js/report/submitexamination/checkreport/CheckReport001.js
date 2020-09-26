(function(){
    var getPageParams = function() {
        return {
            updateTimeStart : $('#checkDate').startDate(),
            updateTimeEnd : $('#checkDate').endDate(),
            orgCode : $('#orgCode').val()==null?null:$('#orgCode').val().join(","),
            repCode : $('#repName').val()==null?null:$('#repName').val().join(","),
            checkResult : $('#checkResult').val()==null?null:$('#checkResult').val().join(",")
        }
    };
    
    var table = $('#checkReportTable').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function(d) {
                return getPageParams();
            }
        },
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        //loadonce:true,
        cols: [{field:'repCode',visible:false},
                  {field:'registSeqno',visible:false},
                  {title:'报表名称',field:'repName'},
                  {title:'检查日期',field:'checkTime'},
                  {title:'唯一标识',field:'validKey'},
                  {title:'检查结果',field:'checkResult',
                   render : function(data, type, row, meta){
                          if(row.checkResult !== '检查通过'){
                        	  return row.checkResult;
//                              return '<a id="jumpTab" href="javascript:;">'+row.checkResult+'</a>';
                          }
                          else{
                              return row.checkResult;
                          }
                      }
                  },
                  {title:'错误信息项',field:'errorInfo', sortable:false},
                  {title:'警告信息项',field:'warnInfo', sortable:false}]
    });
  function init(){
	  if(isFromMain=='01'){
          setTimeout(function() {
              $('#checkDate').val(searchDataStart+' - '+searchDataEnd);
              $('#repName').val(repCode);
              $('#checkResult').val(checkResult);
              table.load();
          }, 500);
      }else{
    	  setTimeout(function() {
              $('#checkDate').val(searchDataStart+' - '+searchDataEnd);
              table.load();
          }, 500);
      }
  };
  init();
    //跳转页面方法
    table.bind('click','#jumpTab',function(row){
        var param = {};
        if(row.repCode === repCode01){
            param = {
                pageId: 'EquityInfoRegist001',
                title : title01,
                url : 'submitmanagement/equityinforegist/EquityInfoRegist001/init?indentifyMark='+row.validKey,
                id: 'MENU_003003001'
            };
        }else if(row.repCode === repCode02){
            param = {
                pageId: 'InvestorsHoldInfoRegist001',
                title : title02,
                url : 'submitmanagement/investorsholdinforegist/InvestorsHoldInfoRegist001/init?validKey='+row.validKey,
                id: 'MENU_003003002'
            };
        }
        param.icon = '';
        top.tab.tabAdd(param);
        $('div[lay-item-id='+param.id+'] iframe', top.window.document.body).attr('src', param.url);
    });
    
    $(function(){
        //数据检查按钮绑定事件
        $('#CheckReport001_btn_search').click(function(){
            table.load();
        });
        //导出
        $('#CheckReport001_btn_export').click(function(){
        	Joyin.exportExcel('export', getPageParams());
        });
    });
}());