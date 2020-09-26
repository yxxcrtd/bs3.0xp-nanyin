$(function() {
	//初始化估值日期等于平台日
//	$('#valDate').val(sysDate);
	
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
		return {
			valDate : $.trim($('#valDate').val()),
	        prodId : $.trim($('#prodId').val()),
	        interType : $.trim($('#interType').val()),
	        xmlStatus : $.trim($('#xmlStatus').val()),
	        checkStatus : $.trim($('#checkStatus').val()),
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
	            {field : 'valDate', title : '估值日期',sort : true,width:180},
				{field : 'prodId', title : '产品代码',sort : true,width:210}, 
				{field : 'finprodName', title : '产品简称',sort : true,width:210}, 
                {field:'interType', title: '对账对象',sort: true,visible:false,width:180},
                {field:'interTypeFmt', sortField: "interType", title: '对账对象',sort: true,width:180},
                {field:'xmlStatus', title: '报文状态',sort: true,visible:false,width:180},
                {field:'xmlStatusFmt', sortField: "xmlStatus", title: '报文状态',sort: true,width:180,
                    render: function (data, type, row, meta) {
                        if(data=='未发送'){
                            return '<span style="color:#000">'+'未发送'+'</span>';
                        }else if(data=='已发送'){
                            return '<span style="color:#000">'+'已发送'+'</span>';
                        }else if(data=='发送失败'){
                            return '<span style="color:#f00">'+'发送失败'+'</span>';
                        }
                    }},
                {field:'checkStatus', title: '对账状态',sort: true,visible:false,width:180},
                {field:'checkStatusFmt', sortField: "checkStatus", title: '对账状态',sort: true,width:180,
                    render: function (data, type, row, meta) {
                        if(data=='未对账'){
                            return '<span style="color:#000">'+'未对账'+'</span>';
                        }else if(data=='对账中'){
                            return '<span style="color:#000">'+'对账中'+'</span>';
                        }else if(data=='对账一致'){
                            return '<span style="color:#000">'+'对账一致'+'</span>';
                        }else if(data=='对账不一致'){
                            return '<span style="color:#f00">'+'对账不一致'+'</span>';
                        }
                    }},
                {field:'remark', title: '备注',sort: true, width:210},
                {field : 'serialNo', title : '业务流水号',sort : true,visible:false,width:210}, 
                {field : 'returnTxt', title : '接收信息',sort : true,visible:false,width:210},
	            {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
	                buttons: [
	                    {clas: 'iconfont icon-copy',title: '查看明细'},
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
        var valDate = $('#valDate').val();
        
        if(!selArr || selArr.length === 0){
             Joyin.error(JoyinUtil.getMessage("请勾选查询结果记录！"));
         }else{
             Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/valelectreconciliation/ValElectReconciliation001/check', getSearchParams(), function(result) {
                 if(result.data){
                     msg = '是否确认发送对账';
                     
                     Joyin.confirm(msg,function() {
                         Joyin.ajax('RunAs', 
                                 {   
                                     datas : datas,
                                     valDate : valDate
                                 }, 
                              function(data) {
                                 var result = JSON.parse(data);
                                 layui.layer.alert("请求已发送",function(index){
                                     if(!table.table){
                                         table.load();
                                     }else{
                                         table.reload();
                                     }
                                     layui.layer.close(index);
                                 });
                             });
                     }, function(){
                         console.log('取消');
                     });
                 }
             });
         }
	};
	
    /**
     * 调整
     */
    var adjustValue = function(operateType) {
        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            Joyin.error(JoyinUtil.getMessage("请勾选查询结果记录！"));  
        } else {
            Joyin.prompt("请添加备注信息", 2, function (result) {
                Joyin.ajax(
                        basePath + 'valuation/dailyoperation/dailyaccountmake/valelectreconciliation/ValElectReconciliation001/adjustValue', 
                        {datas : datas,remark : result,operateType : operateType},function (data) {
                            table.reload();
                  });
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
        $('#ValElectReconciliation001_btn_search').click(function() {
        	Joyin.ajaxJson(basePath + 'valuation/dailyoperation/dailyaccountmake/valelectreconciliation/ValElectReconciliation001/check', getSearchParams(), function(result) {
        		if(!table.table) {
        			table.load();
                } else {
                	table.reload();
                }
        	});
        });

        // 绑定发送事件
        $('#ValElectReconciliation001_btn_confirm').click(function() {
        	confirmFunction();
        });
        
        // 绑定调整事件
        $('#ValElectReconciliation001_btn_adjust').click(function() {
            adjustValue('adjust');
        });
        
        // 绑定取消调整事件
        $('#ValElectReconciliation001_btn_canadjust').click(function() {
            adjustValue('canadjust');
        });
        
        // 绑定导出事件
        $('#ValElectReconciliation001_btn_export').click(function() {
            exportExcel();
        });
        
        // 查看对账明细
        table.bind('click', '.icon-copy', function(data, index) {
            if(['0','1'].indexOf(data.checkStatus) > -1){
                Joyin.alert('请选择对账一致或对账不一致的数据',function(result){
//                  table.reload();
              });
            }else if(['2','3'].indexOf(data.checkStatus) > -1){
                if(data.interType === '1'){
                    Joyin.dialog(JoyinUtil.getLabel("查看对账明细——估值表"),
                            basePath + "valuation/dailyoperation/dailyaccountmake/valelectreconciliation/ValElectReconciliation002/init",
                            "85%", "85%", {serialNo: data.serialNo,interType: data.interType},function(){
//                                table.reload();
                    });
                }else if(data.interType === '0'){
                    Joyin.dialog(JoyinUtil.getLabel("查看对账明细——余额表"),
                            basePath + "valuation/dailyoperation/dailyaccountmake/valelectreconciliation/ValElectReconciliation003/init",
                            "85%", "85%", {serialNo: data.serialNo,interType: data.interType},function(){
//                                table.reload();
                    });
                }
            }
       });
        
        // 报文下载
        table.bind('click', '.icon-send_fill', function(data, index) {
            var selArr = table.getAllCheckedRows();
            var prodId = selArr[0].prodId;
            var finprodName = selArr[0].finprodName;
            var interType = selArr[0].interType;
            var valDate = selArr[0].valDate;
            Joyin.ajax('down', 
                    {   
                          prodId : prodId,
                          interType : interType,
                          valDate : valDate
                    }, 
                 function(data) {
                        download(finprodName+"报文信息.xml",data);
                });
       });
        
        // 查看日志信息
        table.bind('click', '.icon-document', function(data, index) {
            if(['0','1'].indexOf(data.xmlStatus) > -1){
                Joyin.alert('请选择发送失败的数据',function(result){
//                    table.reload();
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