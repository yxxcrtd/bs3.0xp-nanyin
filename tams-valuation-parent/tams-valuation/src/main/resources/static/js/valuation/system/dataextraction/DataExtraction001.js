    var paramObj =function() {
    	return{
      	   dataSource: $.trim($('#dataSource').val()),
           sqlText:$('#inputSql').val(),
           queryHead:'Y'
    	}
     };
    var search = function() {
        //获取页面查询参数
        Joyin.ajax('checkSearch',paramObj(),function(result) {
        	
            if(result && result.statuCode == '200') {
                Joyin.ajax('search',paramObj(),function(result) {
                    //生成报表面板信息
                    createTable(result);                    
                });
            }
        });
    };
    
    
    /**
     * 生成列表
     */
    var table = null;
    var c_table = $("#dataTable");
    var num = 0;
    var createTable = function(result) {
        var gridObj = {};
        var colModels = [];
        var cols = result.root;
        var j = 0;
        for (var i in cols) {
            for (var key in cols[i]) {
                colModels[j] = {field:key, title:cols[i][key], sortable:false};
                j++;           
            } 
           break;
        }
        gridObj.cols = colModels;
        gridObj.searchOnInit = true;
        gridObj.ajax={
            url: 'search',
            type: 'POST',
            data: function() {
            	var params= paramObj();
            	params.queryHead='N';
            	return params;
            }
        }
        gridObj.checkbox = true;
        gridObj.cellMinWidth = 150;
        if(table){// TODO 表格对象并没有被销毁
        	$("#div1").children().remove();
        	$("#div1").append(c_table);
        	c_table.attr("id", "dataTable" + (++num));
        	table = c_table.joyintable(gridObj);
        }else {
        	table = $("#dataTable").joyintable(gridObj);
        }
    };
    
    /**
     * 导出
     */
    var maxNum = 10000;
    var exportExcel = function() {
    	var datas = '';
    	var exportNum=$.trim($('#length').val());
    	if(exportNum>50000){
    		layer.alert('不允许导出记录数过大'+50000+'条，影响性能！');
    		return;
    	}
        var paramObj = {
        		dataSource: $.trim($('#dataSource').val()),
                sqlText: $.trim($('#inputSql').val())
         };
        if (table != null && table != '') {
    		var selArr = table.getAllCheckedRows();
    		datas=selArr;
            if(selArr.length > 0){
            	paramObj.datas = JSON.stringify(selArr);
            }
        }
        Joyin.ajax('checkSearch',paramObj,function(result) {
            if(result && result.statuCode == '200') {
            	// TODO 框架使用组装 form 传参，需要 hmlt 转义 (现只转义单引号)
            	paramObj.sqlText = paramObj.sqlText.replace(/'/g, '&apos;');
            	if (datas.length==0){
            		if(result.data<=maxNum){
            			if(exportNum<=result.data){
                 			exportAciton(paramObj,exportNum);
            			}else{
                   			exportAciton(paramObj,result.data);
            			}
            		}else{
            			if(exportNum<=maxNum){
                 			exportAciton(paramObj,exportNum);
            			}else{
            				Joyin.confirm("超过建议最大导出数目(" + maxNum + ")，是否继续导出！" , function() {
                    			exportAciton(paramObj,exportNum)
                		    });
            			}
            		}
            	}else {
            		exportAciton(paramObj,datas.length)
            	}
            }
        });
    };
    
    var exportAciton = function (paramObj, length){
    	paramObj.page = 1;
		paramObj.length = length;
    	paramObj.sqlText = paramObj.sqlText.replace(/[\r\n]/g," ");
		Joyin.exportExcel('export', paramObj);
    }
    
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定执行事件
        $('#DataExtraction001_btn_submit').click(function() {
            var sqlText=$('#inputSql').val();
            if(sqlText==null ||sqlText == ''){
                layer.alert('请输入有效的sql语句!');
                return;
            }
            if (sqlText.trim().toUpperCase().indexOf("SELECT") == 0 || sqlText.trim().toUpperCase().indexOf("WITH") == 0) {
                $('#div1').show();
                search();
            }else{
                layer.alert('非法的执行语句!请确认后重新输入');
                return;
            }
        });
        
       // 绑定导出事件
        $('#DataExtraction001_btn_export').click(function() {
            exportExcel();
        });
    });