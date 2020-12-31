(function () {
    var getParams = function () {
        return {status: 'E', groupName:$("#prdGroupName").val()};
    }

    var prodGroupType = $("#prodGroupType").val();

    /**
     * 定义数据表格
     */
    var table;

    //首次进入页面flag 。判断checkrow时有问题、LAY_CHECKED为null导致selectedProdIds会被删数据
    // 但是取消选中时 是对的。导致
    var firstFlag = false;
    if (prodGroupType === '0') {
        table = $("#prdGroupDataTable").joyintable({
            ajax: {
                url: 'findAllPrdGroup',
                type: 'POST',
                data: function () {
                    return getParams();
                }
            },
            height: 380,
            check:function(ele, row){
                if(!firstFlag){
                	process(this,'seqNo','groupName');
                }
            },
            done:function(data){
            	firstFlag = true;
                $.each(data, function (i, v) {
                    if ($.inArray(v.seqNo, selectedProdIds) != -1) {
                        table.checkRow(v.LAY_TABLE_INDEX)
                    }
                })
                firstFlag = false;
                setToPage();
            },
            cols: [
                {
                    field: 'groupName', title: '产品组名', render: function (data, type, row, meta) {
                        return '<a href="javascript:void(0);" class="prdGroupName" data-seqno="' + row.seqNo + '">' + data + '</a>';
                    }
                }
                , {field: 'seqNo', visible: false}
                , {field: 'detail', title: '备注'}
            ]
        });
    } else {
        table = $("#prdGroupDataTable").joyintable({
            ajax: {
                url: 'findAllProd',
                type: 'POST',
                data: function () {
                    return getParams();
                }
            },
            height: 380,
            check:function(ele, row){
            	if(!firstFlag){
            		process(this,'prodId','prodName');
            	}
            },
            done:function(data){
            	firstFlag = true;
                $.each(data, function (i, v) {
                    if ($.inArray(v.prodId, selectedProdIds) != -1) {
                        table.checkRow(v.LAY_TABLE_INDEX)
                    }
                })
                firstFlag = false;
                setToPage();
            },
            cols: [
                {field: 'prodId', width: '40%', title: '产品代码'}
                , {field: 'prodName', width: '44%', title: "产品简称"}
            ]
        });
    }

    var selectedProdIds = parent.window['prod']?parent.window['prod'].selectedProdIds:[];
    var selectedProdNames = parent.window['prod']?parent.window['prod'].selectedProdNames:[];


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
    				if($.inArray(id, selectedProdIds) === -1){
    	    			selectedProdIds.push(id);
    	    			selectedProdNames.push(name);
    				}
    			}
    			else{
    				if($.inArray(id, selectedProdIds) != -1){
    	                selectedProdIds.splice($.inArray(id, selectedProdIds), 1);
    	                selectedProdNames.splice($.inArray(name, selectedProdNames), 1);
    				}
    			}
    		});
    	}
    	setToPage();
    }

    var setToPage = function () {
        $("#selectedProdId").val(selectedProdIds.join(","))
        var name = selectedProdNames.join(",");
        $("#selectedProdName").html(name.length > 200 ? name.substr(0, 200) + "..." : name);
        var title = selectedProdNames.join("\r\n");
        $("#selectedProdName").attr("title", title);
    }


    var selectRow = function (ele, row) {
        if (row.LAY_CHECKED) {
            if ($.inArray(row.prodId, selectedProdIds) === -1) {
                selectedProdIds.push(row.prodId);
                selectedProdNames.push(row.prodName);
            }
        } else {
            if(!firstFlag){
                if ($.inArray(row.prodId, selectedProdIds) != -1) {
                    selectedProdIds.splice($.inArray(row.prodId, selectedProdIds), 1);
                    selectedProdNames.splice($.inArray(row.prodName, selectedProdNames), 1);
                }
            }
        }
        setToPage();
    }

    var openPrdGroupDetail = function (seqNo, pageModel) {
        Joyin.dialog(JoyinUtil.getLabel("batch.label.setting.prdgroup.title"), basePath + 'system/batch/batchprocess/SysBatchProcess003/openPrdGroupDetail', '100%', '100%',
                {seqNo: seqNo, pageModel: pageModel}, function () {
                });
    }

    $(function () {
        table.load();
        $("body").on("click", ".prdGroupName", function () {
            openPrdGroupDetail($(this).data("seqno"), PAGE_MODEL_REFERENCE);
        })

        $("body").on("click", "#setting_btn_search", function () {
            table.load();
        })

        $("body").on("click", "#setting_btn_select", function () {
//        	selectedProdIds = [];
//        	selectedProdNames = [];
            if (prodGroupType == '0') {
                var selectedProdGroup = table.getAllRows();
                $.each(selectedProdGroup, function (i, v) {
                	var row = table.getRow(i);
                    if (row.LAY_CHECKED) {
                        if ($.inArray(row.seqNo, selectedProdIds) === -1) {
                            selectedProdIds.push(row.seqNo);
                            selectedProdNames.push(row.groupName);
                        }
                    } else {
                        if ($.inArray(row.seqNo, selectedProdIds) != -1) {
                            selectedProdIds.splice($.inArray(row.seqNo, selectedProdIds), 1);
                            selectedProdNames.splice($.inArray(row.groupName, selectedProdNames), 1);
                        }
                    }
                });
                if (selectedProdIds.length == 0) {
                    Joyin.error("请选择产品组。")
                    return;
                }

                if (selectedProdIds && selectedProdIds.length > 100) {
                    Joyin.error('一次只能选择100条产品组。');
                    return;
                }

            } else {
                var selectedProds = table.getAllRows();
                $.each(selectedProds, function (i, v) {
//                	var row = table.getRow(i);
                    if (v.LAY_CHECKED) {
                        if ($.inArray(v.prodId, selectedProdIds) === -1) {
                            selectedProdIds.push(v.prodId);
                            selectedProdNames.push(v.prodName);
                        }
                    } else {
                        if ($.inArray(v.prodId, selectedProdIds) != -1) {
                            selectedProdIds.splice($.inArray(v.prodId, selectedProdIds), 1);
                            selectedProdNames.splice($.inArray(v.prodName, selectedProdNames), 1);
                        }
                    }
                });
//                if (selectedProdIds.length == 0) {
//                    Joyin.error("请选择产品。")
//                    return;
//                }
            }
            top.returnData = JSON.stringify({
            	selectedProdIds : selectedProdIds,
                selectedProdNames : selectedProdNames
            });
            Joyin.closeTopWindow();
        })
    })
}());