(function () {
    var getParams = function () {
        return {status: 'E', batchName: $("#batchName").val()};
    }
    /**
     * 任务组类型：0 任务组，1 任务
     * @type {*|jQuery}
     */
    var batchGroupType = $("#batchGroupType").val();

    var selectedTaskIds = parent.window['batch']?parent.window['batch'].selectedTaskIds:[];
    var selectedTaskNames = parent.window['batch']?parent.window['batch'].selectedTaskNames:[];

    /**
     * 定义数据表格
     */
    var table;

    //首次进入页面flag 。判断checkrow时有问题、LAY_CHECKED为null导致selectedProdIds会被删数据
        // 但是取消选中时 是对的。导致
        var firstFlag = false;

    if (batchGroupType === '0') {
        table = $("#jobDataTable").joyintable({
            ajax: {
                url: 'findAllBatch',
                type: 'POST',
                data: function () {
                    return getParams();
                }
            },
            height: 380,
            check:function(ele, row){
                if(!firstFlag){
                	process(this,'seqNo','batchName');
                }
            },
            done:function(data){
            	firstFlag = true;
                $.each(data, function (i, v) {
                    if ($.inArray(v.seqNo, selectedTaskIds) != -1) {
                        table.checkRow(v.LAY_TABLE_INDEX);
                    }
                });
                firstFlag = false;
                setToPage();
            },
            cols: [
                {
                    field: 'batchName', title: '批处理组名', render: function (data, type, row, meta) {
                        return '<a href="javascript:void(0);" class="batchName" data-seqno="' + row.seqNo + '">' + data + '</a>';
                    }
                }
                , {field: 'seqNo', visible: false}
                , {field: 'detail', title: '备注'}
            ]
        });
    } else {
        table = $("#jobDataTable").joyintable({
            ajax: {
                url: 'findAllTask',
                type: 'POST',
                data: function () {
                    return getParams();
                }
            },
            height: 380,
            check:function(ele, row){
                if(!firstFlag){
                	process(this,'taskId','taskName');
                }
            },
            done:function(data){
            	firstFlag = true;
                $.each(data, function (i, v) {
                    if ($.inArray(v.taskId, selectedTaskIds) != -1) {
                        table.checkRow(v.LAY_TABLE_INDEX);
                    }
                });
                firstFlag = false;
                setToPage();
            },
            cols: [
                {field: 'taskName', title: '批处理名'}
                , {field: 'beanName', title: "Bean名"}
                , {field: 'taskId', visible: false}
                , {field: 'taskPri', title: '优先级'}
            ]
        });
    }

    var process = function(object,idKey,nameKey){
    	//获取整个tableDiv
    	var div = $(object);
    	while(div.length>0&&!div.hasClass("layui-table-box")){
    		div = div.parent();
    	}
    	//获取数据table
    	if(div.length>0){
    		var dataTable = div.find("div.layui-table-main").find("tbody");
    		var checkTable = div.find("div.layui-table-fixed").find("div.layui-table-body").find("tbody");
    		//获取table
    		checkTable.find("tr").each(function(){
    			var index = $(this).data("index");
    			var row = dataTable.find("tr[data-index='"+index+"']");
    			var id = row.find("td[data-field='"+idKey+"']").attr("title");
    			var name = row.find("td[data-field='"+nameKey+"']").attr("title");
    			//获取选项框
    			var input = $(this).find("input[type='checkbox']")[0];
    			if(input.checked){
    				if($.inArray(id, selectedTaskIds) === -1){
    					selectedTaskIds.push(id);
    	    			selectedTaskNames.push(name);
    				}
    			}
    			else{
    				if($.inArray(id, selectedTaskIds) != -1){
    					selectedTaskIds.splice($.inArray(id, selectedTaskIds), 1);
    	                selectedTaskNames.splice($.inArray(name, selectedTaskNames), 1);
    				}
    			}
    		});
    	}
    	setToPage();
    }

    var setToPage = function () {
        $("#selectedTaskId").val(selectedTaskIds.join(","))
        var name = selectedTaskNames.join(",");
        $("#selectedBatchTask").text(name.length > 200 ? name.substr(0, 200) + "..." : name);
        var title = selectedTaskNames.join("\r\n");
        $("#selectedBatchTask").attr("title", title);
    }

    var openBatchGroupDetail = function (seqNo, pageModel) {
        var params = {seqNo: seqNo, pageModel: pageModel};
        if (!seqNo) {
            delete params.seqNo;
        }
        Joyin.dialog(JoyinUtil.getLabel("batch.label.setting.batchgroup.title"), basePath + 'system/batch/batchprocess/SysBatchProcess001/openBatchGroupDetail', '99%', '99%',
            params,
            function () {
            });
    }

    $(function () {
        table.load();
        $("body").on("click", ".batchName", function () {
            openBatchGroupDetail($(this).data("seqno"), PAGE_MODEL_REFERENCE);
        })

        $("body").on("click", "#setting_btn_search", function () {
            table.load();
        })

        $("body").on("click", "#setting_btn_select", function () {
//        	selectedTaskIds = [];
//        	selectedTaskNames = [];
            if (batchGroupType == '0') {
//                var selectedBatchGroup = table.getAllCheckedRows();
//                $.each(selectedBatchGroup, function (i, v) {
//	                if ($.inArray(v.seqNo, selectedTaskIds) === -1) {
//	                    selectedTaskIds.push(v.seqNo);
//	                    selectedTaskNames.push(v.batchName);
//	                }
//                });
                var selectedBatchGroup = table.getAllRows();
                $.each(selectedBatchGroup, function (i, v) {
                	var row = table.getRow(i);
                    if (row.LAY_CHECKED) {
                        if ($.inArray(row.seqNo, selectedTaskIds) === -1) {
                        	selectedTaskIds.push(row.seqNo);
                        	selectedTaskNames.push(row.batchName);
                        }
                    } else {
                        if ($.inArray(row.seqNo, selectedTaskIds) != -1) {
                        	selectedTaskIds.splice($.inArray(row.seqNo, selectedTaskIds), 1);
                        	selectedTaskNames.splice($.inArray(row.batchName, selectedTaskNames), 1);
                        }
                    }
                });
                if (selectedTaskIds.length == 0) {
                    Joyin.error("请选择任务组。")
                    return;
                }

                if (selectedTaskIds.length > 20) {
                    Joyin.error("一次最多只能选择20个任务组。")
                    return;
                }
            } else {
//                var selectedTaskGroup = table.getAllCheckedRows();
//                $.each(selectedTaskGroup, function (i, v) {
//	                if ($.inArray(v.taskId, selectedTaskIds) === -1) {
//	                    selectedTaskIds.push(v.taskId);
//	                    selectedTaskNames.push(v.taskName);
//	                }
//                });
                var selectedTaskGroup = table.getAllRows();
                $.each(selectedTaskGroup, function (i, v) {
                    if (v.LAY_CHECKED) {
                        if ($.inArray(v.taskId, selectedTaskIds) === -1) {
                        	selectedTaskIds.push(v.taskId);
                        	selectedTaskNames.push(v.taskName);
                        }
                    } else {
                        if ($.inArray(v.taskId, selectedTaskIds) != -1) {
                        	selectedTaskIds.splice($.inArray(v.taskId, selectedTaskIds), 1);
                        	selectedTaskNames.splice($.inArray(v.taskName, selectedTaskNames), 1);
                        }
                    }
                });
                if (selectedTaskIds.length == 0) {
                    Joyin.error("请选择任务。")
                    return;
                }
            }
            top.returnData = JSON.stringify({
        		selectedTaskIds : selectedTaskIds,
                selectedTaskNames : selectedTaskNames
            });
            Joyin.closeTopWindow();
        })

    })
}());