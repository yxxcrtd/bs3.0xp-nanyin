$(function() {
	
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
	        prodId : $.trim($('#prodId').val()),
	        xmlStatus : $.trim($('#xmlStatus').val()),
		}
	};
	
	/**
	 * 定义数据表格
	 */
	var table = $("#dataTable").joyintable({
		ajax : {
			url : 'findPage',
			type : 'post',
			data : function(){
				return getSearchParams();
			}
		},
		searchOnInit : false,
		cellMinWidth : 100,
		cols : [
				{field : 'prodId', title : '产品代码',sort : true}, 
				{field : 'finprodName', title : '产品简称',sort : true}, 
	            {field:'interType', title: '对账对象',sort: true,visible:false},
	            {field:'interTypeFmt', sortField: "interType", title: '对账对象',sort: true},
                {field:'xmlStatus', title: '发送状态',sort: true,visible:false},
                {field:'xmlStatusFmt', sortField: "xmlStatus", title: '发送状态',sort: true,
                    render: function (data, type, row, meta) {
                        if(data=='未发送'){
                            return '<span style="color:#000">'+'未发送'+'</span>';
                        }else if(data=='已发送'){
                            return '<span style="color:#000">'+'已发送'+'</span>';
                        }else if(data=='发送失败'){
                            return '<span style="color:#f00">'+'发送失败'+'</span>';
                        }
                    }},
                {field : 'serialNo', title : '业务流水号',sort : true,visible:false}, 
                {field : 'returnTxt', title : '接收信息',sort : true,visible:false,width:210},
                {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
                    buttons: [
                        {clas: 'iconfont icon-send_fill',title: '报文下载'},
                        {clas: 'iconfont icon-document',title: '查看日志'}
                        ]
                }
		]
	});
	
	var confirmFunction = function(){
        var msg='';
        var selArr = table.getAllCheckedRows();
        var datas = JSON.stringify(selArr);
        
        if(!selArr || selArr.length === 0){
             Joyin.error(JoyinUtil.getMessage("请勾选查询结果记录！"));
         }else{
             msg = '是否确认发送对账';
             Joyin.confirm(msg,function() {
                 Joyin.ajax('RunAs', 
                         {   
                             datas : datas
                         }, 
                      function(data) {
                         var result = JSON.parse(data);
                         layui.layer.alert("请求已发送",function(index){
                             table.reload();
                             layui.layer.close(index);
                         });
                     });
             }, function(){
                 console.log('取消');
             });
         }
	};
	
    /**
     * 导出
     */
    var exportExcel = function() {
        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            Joyin.error(JoyinUtil.getMessage("请勾选查询结果记录！"));  
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    
    function download(filename, text) {
        var element = document.createElement('a');
        element.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(text));
        element.setAttribute('download', filename);
        element.style.display = 'none';
        document.body.appendChild(element);
        element.click();
        document.body.removeChild(element);
      }
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#ValSubjectInfo001_btn_search').click(function() {
        	Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/valsubjectinfo/ValSubjectInfo001/check', getSearchParams(), function(result) {
        		if(!table.table) {
        			table.load();
                } else {
                	table.reload();
                }
        	});
        });

        // 绑定发送事件
        $('#ValSubjectInfo001_btn_confirm').click(function() {
        	confirmFunction();
        });
        
        // 绑定导出事件
        $('#ValSubjectInfo001_btn_export').click(function() {
            exportExcel();
        });
        
        // 报文下载
        table.bind('click', '.icon-send_fill', function(data, index) {
            var selArr = table.getAllCheckedRows();
            var prodId = selArr[0].prodId;
            var finprodName = selArr[0].finprodName;
            Joyin.ajax('down', 
                    {   
                          prodId : prodId
                    }, 
                 function(data) {
                        download(finprodName+"报文信息.xml",data);
                });
       });
        
        // 查看日志信息
        table.bind('click', '.icon-document', function(data, index) {
            if(['0','1'].indexOf(data.xmlStatus) > -1){
                Joyin.alert('请选择发送失败的数据',function(result){
//                  table.reload();
              });
            }else{
                Joyin.dialog(JoyinUtil.getLabel("查看日志信息"),
                        basePath + "valuation/dailyoperation/dailyaccountmake/valsubjectinfo/ValSubjectInfo002/init",
                        "85%", "85%", {returnTxt: data.returnTxt},function(){
//                            table.reload();
                });
            }
       });
        
    });
	
}());