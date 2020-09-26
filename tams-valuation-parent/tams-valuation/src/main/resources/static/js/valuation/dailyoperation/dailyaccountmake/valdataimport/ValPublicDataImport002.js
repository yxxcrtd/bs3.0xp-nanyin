$(function () {	
    /**
     * 获取查询参数
     */
    var getPageParams = function() {       
        return {
        	seqno : $('#seqno').val()        
        }
    };
    
    /**
     * 表格加载
     */
    
    /**
     * 定义数据表格
     */
	var table=$("#dataTable").joyintable({
	    ajax: {
            url: 'searchList',
            type: 'POST',
            data:  function() {
                return getPageParams();
            }
        },
        dblclick:function(table, data) {
			//跳转详情界面
            Joyin.dialog(JoyinUtil.getLabel("查看导入数据："+data.taskCname),
                    JoyinUtil.getRealPath("/valuation/dailyoperation/dailyaccountmake/valdataimport/ValPublicDataImport003/init"),
                    "95%", "95%", 
                    {pageModel:PAGE_MODEL_REFERENCE,parentSeqno:data.seqno,tmpName:data.eName,logStatus:data.status},
                    function(){
                        //table.reload();
                        });
        },
        searchOnInit: true,
        checkbox: false,
		cols: [{field:'seqno', title: 'seqno', visible:false},
		       {field:'eName', title: 'eName', visible:false},
	    	   {field:'busiDate', title: '日期', width:120},
	   		   {field:'taskCname', title: '接口文件名称',width:160},
	           {field:'status', title: '执行状态',width:95,
                     render: function (data, type, row, meta) {
                         if(data=='02'){
                             return '<span class="layui-badge-rim layui-bg-green">'+'成功'+'</span>';
                         }else if(data=='03'){
                             return '<span class="layui-badge-rim layui-bg-red">'+'失败'+'</span>';
                         }else if(data=='01'){
                             return '<span class="layui-badge-rim layui-bg-blue">'+'执行中'+'</span>';
                         }else if(data=='00'){
                             return '<span class="layui-badge-rim layui-bg-gray">'+'未执行'+'</span>';
                         }else if(data=='09'){
                             return '<span class="layui-badge-rim layui-bg-orange">'+'警告'+'</span>';
                         }
                     }},
	           {field:'detailAction', title: '执行结果',width:300},
	           {field:'startDateFmt',sorfiled:"startTime", title: '操作日期',width:120},
		],
		loadonce:true
	});
    
    /**
     * 画面加载完成
     */
    $(function() {
    	//table.load();
    });
});