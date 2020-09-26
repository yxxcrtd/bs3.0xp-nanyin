$(function () {
    /**
     * 获取查询参数
     */
    var getPageParams = function() {
        return {
            yearDate: $('#yearDate').val()
            ,declarDate: $('#declarDate').val()
            ,sumDimen: $('#sumDimen').val()
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
                {field:'finprodId',sortable: false, width:150, title: 'ValueAddedTax001.finprodId', rowspan: 2,fixed:'left'}
                ,{field:'finprodName',sortable: false, width:300, title: 'ValueAddedTax001.finprodName', rowspan: 2,fixed:'left'}
                ,{field:'signFlag', width:150, title: '收益标识',sortable: false,visible:false, rowspan: 2}
                ,{field:'signFlagFmt', sortField: "signFlag", width:150, title: '收益标识',sortable: false,rowspan: 2}
                ,{field:'a1', type:"money", width:250, title: 'ValueAddedTax001.A1',sortable: false, rowspan: 2}
                ,{field:'b1', type:"money", width:210, title: 'ValueAddedTax001.B1',sortable: false, rowspan: 2}
                ,{field:'a2', type:"money", width:200, title: 'ValueAddedTax001.A2',sortable: false, rowspan: 2}
                ,{field:'b2', type:"money", width:200, title: 'ValueAddedTax001.B2',sortable: false, rowspan: 2}
                ,{field:'b3', type:"money",width:230, title: 'ValueAddedTax001.B3',sortable: false, rowspan: 2}
                ,{align: "center", title: 'ValueAddedTax001.freeTax',sortable: false,align:"center", colspan: 4}
                ,{field:'c1', type:"money",width:330, title: 'ValueAddedTax001.C1',sortable: false, rowspan: 2}
                ,{field:'c2', type:"money",width:350, title: 'ValueAddedTax001.C2',sortable: false, rowspan: 2}
                ,{field:'c3', type:"money",width:370, title: 'ValueAddedTax001.C3',sortable: false, rowspan: 2}
                ,{field:'c4', type:"money",width:310, title: 'ValueAddedTax001.C4',sortable: false, rowspan: 2}
                ,{field:'c5', type:"money",width:330, title: 'ValueAddedTax001.C5',sortable: false, rowspan: 2}
                ,{field:'e1', type:"money",width:210, title: 'ValueAddedTax001.E1',sortable: false, rowspan: 2}
                ,{field:'e2', type:"money",width:210, title: 'ValueAddedTax001.E2',sortable: false, rowspan: 2}
                ,{field:'e3', type:"money",width:210, title: 'ValueAddedTax001.E3',sortable: false, rowspan: 2}
            ],[
                {field:'free36file', type:"money", width:150,sortable: false, title: 'ValueAddedTax001.FREE_36FILE'}
                ,{field:'free46file', type:"money", width:150,sortable: false, title: 'ValueAddedTax001.FREE_46FILE'}
                ,{field:'free70file', type:"money", width:150,sortable: false, title: 'ValueAddedTax001.FREE_70FILE'}
                ,{field:'freeSec', type:"money", width:150,sortable: false, title: 'ValueAddedTax001.FREE_SEC'}
            ]]
      });
    var build=function(){
        Joyin.ajax("check",{data:getPageParams()},function(data) {
            if(data.data == '01'){
                Joyin.confirm("所选征期尚有账套未完成价税分离,是否继续处理？",function() {
                    Joyin.ajax("build",{data:getPageParams()},function(data) {
                        
                    });
                    if(!table.table) {
                        table.load();
                    }
                    else {
                        table.reload();
                    }
                }, function(){
                    console.log('取消');
                });
            }else {
                Joyin.ajax("build", 
                    {data:getPageParams()},
                    function(data) {
                        if(!table.table) {
                            table.load();
                        }
                        else {
                            table.reload();
                        }
                  });
            }
        });
    }
    
    var exportExcel = function() {
        var selArr;
        var datas;
        if (table != null && table != '') {
            selArr= table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getPageParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    
    function showYear() {
    	var laydate = layui.laydate;
     	laydate.render({
 			elem: '#yearDate',
 			type: 'year',
 			format: 'yyyy'
 		});
     }

    /**
     * 画面加载完成
     */
    $(function() {
    	$("#yearDate").click(function(event){
			event.stopPropagation();// 阻止事件冒泡
		});
    	showYear();
      // 绑定按钮事件
      $('#ValueAddedTaxQuery001_btn_search').click(function() {
          Joyin.ajax("checkEmpty",{data:getPageParams()},function(result) {
//          Joyin.ajaxJson(basePath + 'valuation/accounadjustmanage/valueaddedtax/ValueAddedTax001/checkEmpty', {data:getPageParams()}, function(result) {
              console.log(result);
              if(!table.table) {
                  table.load();
              }
              else {
                  table.reload();
              }
          });
         
      });
      $('#ValueAddedTaxQuery001_btn_export').click(function() {
          exportExcel();
      });
      $('#ValueAddedTaxQuery001_btn_create').click(function() {
          build();
      });
    });
});
