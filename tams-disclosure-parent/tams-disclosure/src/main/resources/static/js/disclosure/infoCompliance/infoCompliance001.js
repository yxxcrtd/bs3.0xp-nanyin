(function() {
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
        return {
            infoType:$("#infoType").val(),
            infoName:$("#infoName").val(),
            infoStatus:$("#infoStatus").val()
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
        dblclick:function(table, data) {
            Joyin.dialog("合规留痕管理查看",
                    basePath + "disclosure/infoCompliance/infoCompliance002/init",
                    "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                    seqno:data.seqno},function(){table.reload();});
        },
        cols:[
             {field:'seqno',visible:false}
            ,{field:'infoType', visible:false}
            ,{field:'infoStatus', visible:false}
            ,{field:'infoDevStatus', visible:false}
        	,{field:'indexNo', width:120, title: '序号'}
        	,{field:'infoTypeText', width:120, title: '类型'}
        	,{field:'infoName', width:600, title: '模板/报表名称'}
        	,{field:'approveUser', width:200, title: '当前处理人', sortable:false}
            ,{field:'infoStatusText', width:200, title: '当前状态', sortable:false}
        	,{field:'infoDevStatusText', width:200, title: '开发状态', sortable:false}
        	,{field:'updateTimeText', width:200, title: '更新时间',sortable:false}
        	,{field:'operation', width:200, title: '操作',type:'action',buttons:['delt']}
        ],
        checkbox:true,
        progressBar: true
    });
    
    var exportExcel = function(){       
        Joyin.exportExcel('export', getSearchParams());
    };

    /**
     *  点击新建菜单,进入新建界面
     **/
    $("#infoCompliance001_btn_add").click(function(){
        Joyin.dialog("信披单据合规新建",basePath+"disclosure/infoCompliance/infoCompliance002/init","80%","80%",{pageModel:PAGE_MODEL_NEW},
            function(){
                table.reload();
            }
        )
    });
    //点击修改开发状态
    $("#infoCompliance001_btn_update").click(function(){
        var selRows = table.getAllCheckedRows();
        if(selRows != null && selRows.length == 1){
            var infoStatus = selRows[0].infoStatus;
            if(infoStatus != '02'){
                Joyin.error("数据状态不是审核通过,不能修改!");
            } else {
                Joyin.dialog("开发状态管理",basePath+"disclosure/infoCompliance/infoCompliance003/init","70%","70%",
                    {seqno:selRows[0].seqno},function () {
                        table.reload();
                    })
            }
        } else {
            Joyin.error("只能选中一条数据进行修改!");
        }
    });

    table.bind("click",".delt",function(data,index){
        var object={seqno:data.seqno}
        Joyin.ajaxJson(basePath+"disclosure/infoCompliance/infoCompliance001/check",object,function(result){
            Joyin.confirm(JoyinUtil.getMessage("确认是否要删除？"),function(){
                Joyin.ajaxJson(basePath+"disclosure/infoCompliance/infoCompliance001/delete",object,function(result){
                    table.reload();
                });
            });
        })
    });
   
    /**
     * 画面加载完成
     */
    $(function() {
    	// 绑定按钮事件
    	$('#InfoCompliance001_btn_search').click(function() {
    		table.load();
    	});

        $('#SysActionLog001_btn_export').click(function() {
            exportExcel();
        });
        table.load();

    });
}());