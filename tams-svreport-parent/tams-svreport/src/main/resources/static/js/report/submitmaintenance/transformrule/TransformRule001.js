$(function() {
	
	
	/**
     * 画面加载完成
     */
    $(function() {
    	init();
    });

	
	
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            transType:$('#transType').val(),
            orgField:$('#orgField').val(),
            dataStatus : $('#status').val()==null?null:$('#status').val().join(",")
        }
    };
	
	var table = $('#dataTable').joyintable({
		ajax : {
			url : 'searchPage',
			type : 'POST',
			data : function() {
				return getSearchParams();
			}
		},
		cols : [ { title: "数据状态", field: 'statusName',width:150,sortField:'dataStatus'},
		         { title: "类型", field: 'transTypeStr',width:150,sortField:'transType'},
				 { title: "原始值", field: 'orgField',width:150},
				 { title: "拼音", field: 'pinyin',width:200},
				 { title: "unicode码", field: 'fieldUnicode',width:150},
				 { title: "转化后值", field: 'targetField',width:150},
				 { title: "是否可维护", field: 'isModifyStr',width:150,sortField:'isModify'},
				 { title: "备注", field: 'remark'},
				 {field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
		         ]
	});
	
    	
    	//新建按钮的事件
        $('#TransformRule001_btn_add').click(function() {
            Joyin.dialog("变形转换规则新建",
                    basePath + "svreport/submitmaintenance/transformrule/SvrTransformRule002/init",
                    "80%", "70%", {pageModel:PAGE_MODEL_NEW},function(){
                        //防止出现查询转圈圈
                 	   if(table.table){
                		   setTimeout(function(){table.reload();},100);
                	   }else{
                		   setTimeout(function(){table.load();},100);
                	   }
            });
        });
        
      //删除按钮点击事件
        $("#TransformRule001_btn_delete").click(function() {
          var rows = table.getAllCheckedRows();
          if (rows.length === 0) {
            return Joyin.error("请至少选择一条记录");
          }
          for (var i = 0; i < rows.length; i++) {
            if (rows[i].dataStatus == "D") {
              return Joyin.error("不能删除已删除的数据，请重新选择");
            }
            if(rows[i].isModify=="02"){
            	return Joyin.error("不能删除不可维护的数据");
            }
          }
          Joyin.confirm("请确认是否要删除选中记录？", function() {
            Joyin.ajaxJson("delete", { deleteList: rows }, function() {
              $("#SvrTransformRule001_btn_search").trigger("click");
            });
          });
        });
        
        $('#SvrTransformRule001_btn_search').click(function() {
        	var param=getSearchParams();
        	table.load();
        });
        
        /**
         * 绑定编辑事件
         */
        table.bind('click','.edit', function(row,index) {
            if(row.dataStatus == "D"){
                return Joyin.error("不能操作已被删除的数据");
            }
            if(row.isModify == "02"){
                return Joyin.error("不能操作不可维护的数据");
            }
            row.operateType = "edit";
            row.pageModel = PAGE_MODEL_EDIT;
               Joyin.dialog("变形转换规则修改",
                      basePath + "svreport/submitmaintenance/transformrule/SvrTransformRule002/init",
                      "80%", "70%", row,function(){
            	   setTimeout(function(){table.reload();},100);
              });
        });
        
        /**
         * 绑定删除事件
         */
        table.bind('click', '.delt', function(data, index) {
            if(data.dataStatus =="D"){
                return Joyin.error("不能删除已删除的数据，请重新选择");
            }
            if(data.isModify == "02"){
                return Joyin.error("不能操作不可维护的数据");
            }
            var param = {deleteList:[data]};
                Joyin.confirm("请确认是否要删除选中记录？", function() {
                    Joyin.ajaxJson('delete', param, function() {
                         $('#SvrTransformRule001_btn_search').trigger("click");
                     });
                 });
        });
        
        // 导出按钮点击事件
        $('#SvrTransformRule001_btn_export').click(function() {
            var selectedItem = table.getAllCheckedRows();
            var paramObj={};
            if(selectedItem.length==0){
                paramObj= getSearchParams();
            }
            else{
                paramObj.keyList = JSON.stringify(selectedItem);
            }
            Joyin.exportExcel('export', paramObj);
        });
        
        /**
         * 页面初始加载 zhengxiaolian
         */
         function init(){
             if(isFromMain=="01"){
                 $('#status').val("A").trigger("change");
                 setTimeout(function() {
                	 table.load();
                 },100);
             }
         }

});