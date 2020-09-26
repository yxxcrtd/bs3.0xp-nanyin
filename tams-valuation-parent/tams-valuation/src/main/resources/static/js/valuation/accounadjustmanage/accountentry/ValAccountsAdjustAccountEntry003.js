

var ValAccountsAdjustAccountEntry003 = function() {
	
	var DETAIL_PAGE_MODEL_NEW = "0";//页面模式-新建
	var DETAIL_PAGE_MODEL_EDIT = "1";//页面模式-编辑
	var DETAIL_PAGE_MODEL_REFERENCE = "2";//页面模式-查看
	var DETAIL_PAGE_MODEL_COPY = "3";//页面模式-复制
	var DETAIL_PAGE_MODEL_ADD = "4";//页面模式-添加子节点

	var LocalPageModel= DETAIL_PAGE_MODEL_NEW;
	
	var Table_Index = -1;
	
    var hasSub = $("#hasSub").val();
    var ACCOUNTEVENTS = $("#ACCOUNTEVENTS").val();
    
	
	var gridId = "ValAccountsAdjustAccountEntry003_dataTable";
	var gridObj;
	
	
	var nodeValue = $("#nodeValue").val();
    
    var isInit = true;
    
    return {
        /**
         * 页面初始化 
         */
    	init:function() {
    		$("#fsceneNodeid").val($("#fsceneNodeid :eq(0)").attr("value")).trigger("change");
            if (pageModel == DETAIL_PAGE_MODEL_NEW || pageModel == DETAIL_PAGE_MODEL_ADD
                    || pageModel == DETAIL_PAGE_MODEL_COPY) {
                $("#fsceneNodeid").prop("disabled",true).trigger("change");
                if (pageModel != DETAIL_PAGE_MODEL_COPY) {
                    $("#nodeValue").val($("#nodeValue :eq(0)").attr("value")).trigger("change");
                } else {
                    $("#nodeId").prop("disabled",true).trigger("change");
                }
            }

            else if (pageModel == DETAIL_PAGE_MODEL_EDIT) {
                $("#fsceneNodeid").prop("disabled",true).trigger("change");
                $("#nodeId").prop("disabled",true).trigger("change");
            } else if (pageModel == DETAIL_PAGE_MODEL_REFERENCE) {
                $("#fsceneNodeid").prop("disabled",true).trigger("change");
                $("#nodeId").prop("disabled",true).trigger("change");
            }
            
            $("#nodeId").change(function() {
                var nodeIds = ValAccountsAdjustAccountEntry003.getSimpleSelectObj("nodeId");
                var thisValue = $(this).val();
                if (thisValue != ACCOUNTEVENTS) {
                    $(nodeIds).each(function(i, value) {
                        if (value.id == thisValue) {
                            var dictSource = value.col;
                            Joyin.ajax("findDictCodeList",{dictCode:dictSource},function(data) {
                                if (data && data.length > 0) {
                                    $("#nodeValue").html('');
                                    $(data).each(function(j, v) {
                                        $("#nodeValue").append('<option value="' + v.id + '">' + v.text + '</option>');
                                    });
                                    $("#nodeValue").append('<option value="OTHERBRANCH">'+otherbranch+'</option>');
                                    $("#nodeValue").select2({allowClear:false});
                                    var initValue = $("#nodeValue").data('initvalue');
                                    if (initValue) {
                                        $("#nodeValue").val(initValue).trigger('change');
                                        nodeValue=initValue;
                                        isInit = false;
                                    }
                                }
                            })
                            return false;
                        }
                    })
                }
            });
//            if (hasSub) {
//                $("#nodeId").val($("#nodeId :eq(0)").attr("value")).prop("disabled",true);
//            }

            $("#nodeId").trigger("change");
    	   /**
	   	     * 定义会计分录配置数据表格
	   	     */
            gridObj=$("#"+gridId).joyintable({
                ajax: {
                    url: 'findAccountEntryConfigData',
                    type: 'POST',
                    data: {sceneNodeId:$("#sceneNodeId").val(), pageModel:pageModel}
                },
                height:180,
                cols:[
                     {field:'subjectId', visible:false}
                    ,{field:'accountNet', visible:false}
                    ,{field:'formEle', visible:false}
                    ,{field:'cdFlag', visible:false}
                    ,{field:'updateTimeLong', visible:false}
                    ,{field: 'isExtends',width:100, title: '父节点继承'
                    	,render: function (data, type, row, meta) {
                    		if (data == true) {return "是";}else{return "否";}
                    	}
                    }
                    ,{field:'subjectName', width:220, title: '科目'}
                    ,{field:'dictName', width:140, title: '借贷元素'}
                    ,{field:'cdFlagName', width:90, title: '借贷方向'}
                    ,{field:'accountNetName', width:100, title: '记账网点'}
                    ,{field:'operation', width:130, title: 'SysCommon.operation', type:'action', buttons: ['edit','delt']}
                ],
                loadonce:true,
                checkbox:false
            });
            gridObj.load();
    		
    		$("#nodeValue").change(function() {
                if (!isInit) {
                    var thisValue=$(this).val();
                    Joyin.confirm("改变元素值域会清空已配置的分录信息，确认继续吗？",[confirm, cancel],function() {
                        var datas = gridObj.getAllRows();
                        if($.isArray(datas)) {
                        	$.each(datas, function(i, d) {
                        		if (d.isExtends == false) {
                        			gridObj.removeRow(d);
                                }
                            });
                        }
                        nodeValue = thisValue;
                    }, function(){
                        isInit = true;
                        $("#nodeValue").val(nodeValue).trigger('change');
                        isInit = false;
                    });
                }
            });
    		
    		/**
		     * 绑定编辑事件
		     */
    		gridObj.bind('click', '.edit', function(data, index) {
    			Table_Index = index;
    			ValAccountsAdjustAccountEntry003.editAccountEntryConfig(data,DETAIL_PAGE_MODEL_EDIT);
		    });
    		/**
		     * 绑定删除事件
		     */
    		gridObj.bind('click', '.delt', function(data, index) {
    			gridObj.removeRow(data);
		    });
    	},
    	/**
		 * 获取单选下拉框的OBJECT属性
		 */
		getSimpleSelectObj:function (id) {
		    var $option = $('#select2-' + id + '-results').find('li.select2-results__option--highlighted');
		    if($option.length === 0 || _.isUndefined($option.data('data'))){
		        $option = $("#" + id).find("option:selected");
		    }
		    var obj = $option.data('data');
		    if (!obj && typeof obj == 'undefined') {
		        obj = $option.data();
		    }
		    return obj;
		},
    	/**
         * 会计分录配置添加行数据
         */
        eidtAccountEntryRow:function(data){
        	//添加
        	if(LocalPageModel == DETAIL_PAGE_MODEL_NEW){
        		gridObj.addRow(data);
        		
        	}
        	//修改
        	else if(LocalPageModel == DETAIL_PAGE_MODEL_EDIT){
        		if(Table_Index>-1){
                    var datas = gridObj.getAllRows();
                    if(!datas) return;
                    datas.splice(Table_Index,1,data);
                    gridObj.reload({data:datas});
        		}
        	}
        },
    	/**
         * 组装参数
         */
        initRowData:function(row, optype) {
            if ( !row) {
                row = {};
            }
            row.pageModel = optype;
            row.booksetId = $("#booksetId").val();
            row.tableId = $("#tableId").val();
            var level = $("#level").val();
            // 如果会计场景代码为空，是2级节点新建时，必须选择元素值域作为会计场景代码
            if ( !row.tableId) {
                if (level == '1') {
                    if ( !$("#nodeValue").val()) {
                    	Joyin.warn("2级节点新建时，必须选择元素值域才能配置会计分录");
                        return false;
                    } else {
                        row.tableId = $("#nodeValue").val();
                    }
                }
            }
            return row;
        },
    	/**
         *  编辑分录信息
         */
        editAccountEntryConfig:function(row, optype) {
            row = ValAccountsAdjustAccountEntry003.initRowData(row,optype);
            if (row == false) {
                return;
            }
            var label = "新建";
            if (optype == DETAIL_PAGE_MODEL_EDIT) {
                label = "修改";
            }
            LocalPageModel = optype;
	    	Joyin.dialog("会计分录配置"+label,
		      basePath + "valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry002/init",
		      "80%", "90%", row);
        },
        /**
         * 保存会计分录
         */
        saveEntry:function(){
        	var data = {};
            data.fsceneNodeid = $("#fsceneNodeid").val();
            data.booksetId = $("#booksetId").val();
            data.nodeId = $("#nodeId").val();
            data.nodeValue = $("#nodeValue").val();
            data.remark = $("#remark").val();
            data.level = $("#level").val();
            data.sceneNodeId = $("#sceneNodeId").val();
            data.pageModel = pageModel;
            data.updateTimeLong = $("#updateTimeLong").val();
            
            data.rows = gridObj.getAllRows();
            $.each(data.rows, function(k,v){
                if(v.isExtends==null){
                	v.isExtends=false;
                }
            });
            data.gen = $("#isGen").prop("checked");
            data.updateTimeLong = $("#updateTimeLong").val();
            var nodeValues = [];
            $("#nodeValue option:not(:selected)").each(function() {
                nodeValues.push($(this).attr("value"))
            });
            data.nodeValues = nodeValues;
            Joyin.ajaxJson(basePath + "valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry003/checkSave",data,function(result) {
                Joyin.ajaxJson(basePath + "valuation/accounadjustmanage/accountentry/ValAccountsAdjustAccountEntry003/saveEntry",data,function() {
                	parent.layer.close(parent.layer.getFrameIndex(window.name));
                    parent.ValAccountsAdjustAccountEntry001.reloadTree();
                });
            });
        }
    }
    
}();


$(function () {
	ValAccountsAdjustAccountEntry003.init();
	    
	//绑定会计分录配置新建按钮事件
	$('#ValAccountsAdjustAccountEntry003_btn_add').click(function() {
    	ValAccountsAdjustAccountEntry003.editAccountEntryConfig(null,"0");
    });

    //绑定保存按钮事件
    $('#ValAccountsAdjustAccountEntry003_btn_save').click(function() {
	    ValAccountsAdjustAccountEntry003.saveEntry();
    });
});