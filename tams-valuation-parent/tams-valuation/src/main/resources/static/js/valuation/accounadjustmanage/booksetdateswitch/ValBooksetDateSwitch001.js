$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	booksetDimension: $.trim($('#batchBooksetType').val()),
        	booksetGroup: $.trim($('#booksetGroup').val()),
            booksetId: $.trim($('#booksetId').val()),
            booksetDateBegin: $.trim($('#booksetDate').startDate()),
            booksetDateEnd: $.trim($('#booksetDate').endDate()),
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
        cellMinWidth : 100,
        cols:[
            {field:'booksetId', title: '账套代码',sort : true}
            ,{field:'booksetName', title: '账套名称',sort : true}
            ,{field:'booksetDate', title: '当前日期 ',sort : true}   
            ,{field:'startDate', title: '账套开始日 ',sort : true} 
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']}
        ]
    });
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        var datas = JSON.stringify(data);
        Joyin.dialog(JoyinUtil.getLabel("日期切换"),
                basePath + "valuation/accounadjustmanage/booksetdateswitch/ValBooksetDateSwitch002/init",
                "90%", "75%", {pageModel:PAGE_MODEL_NEW,datas : datas}
                ,function(){
                    table.reload();
                }
        );
    });
    
    // 维度联动
//    $("#booksetDimension").change(function(){
//    	var dimension = $("#booksetDimension").val();
//    	if(dimension == '0'){// 账套组
//    		$("#c_booksetId").hide();
//    		$("#booksetId").val("").trigger("change");// select清空
//        	$("#c_booksetGroup").show();
//    	}else if(dimension == '1'){// 账套
//    		$("#c_booksetId").show();
//        	$("#c_booksetGroup").hide();
//        	$("#booksetGroup").val("").trigger("change");// select清空
//    	}
//    });
    
    
    function formatoption() {
    	document.getElementById("booksetId").formatoption="false";
       }
    /**
     * 画面加载完成
     */
    $(function() {
    	$("#batchBooksetType").change(function() {
    		$("#booksetId").val('');
    		var batchBooksetType = $("#batchBooksetType").val();
    		$("#booksets").val(batchBooksetType);
    		if ('0' == batchBooksetType) {
    		    document.getElementById('dynamicName').innerHTML ="账套组";
//    			$("#c_booksetId").hide();
//    			$("#booksetId").val("").trigger("change");// select清空
//            	$("#c_booksetGroup").show();
    		} else {
    		    document.getElementById('dynamicName').innerHTML ="账套";
//        		$("#c_booksetId").show();
//            	$("#c_booksetGroup").hide();
//            	$("#booksetGroup").val("").trigger("change");
    		}
    	});
    	
        // 绑定按钮事件
        $('#ValBooksetDateSwitch001_btn_search').click(function() {
            table.load();
        });
        // 绑定按钮事件
        $('#ValBooksetDateSwitch001_btn_batchEdit').click(function() {
        	var rowArr = table.getAllCheckedRows();
        	if (!rowArr || rowArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("请从列表中选择数据"));
                return;
            }
        	
        	var keyStr = "";
        	for(var i=0; i<rowArr.length; i++){ 
				keyStr = keyStr + rowArr[i].booksetId + ",";
			}
        	// get请求，无法传大数据
            Joyin.dialog(JoyinUtil.getLabel("账套日期批量修改"),
                    basePath + "valuation/accounadjustmanage/booksetdateswitch/ValBooksetDateSwitch003/init",
                    "90%", "75%", {pageModel:PAGE_MODEL_EDIT, booksetId : keyStr}
                    ,function(){
                        table.reload();
                    }
            );
        });
    });
}());