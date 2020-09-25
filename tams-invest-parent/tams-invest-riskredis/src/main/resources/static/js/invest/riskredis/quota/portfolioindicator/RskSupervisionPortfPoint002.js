(function() {
	
	/**
	 * 获取查询参数
	 */
	var getSearchParams = function() {
    	if(entity.opeType == 'delete'){
    		var indId = entity.indId;
    	}

        return {
        	indId:indId || '',
            indName: $('#indName').val(),
            indType:$('#indType').val(),
            supType:$('#supType').val(),
            length:1000,
            portfId:$('#portfolioCode').val()
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#portfRelateIndTable").joyintable({
        ajax: {
            url:  basePath +'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint002/queryRelatedIndList',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols:[
             {field:'indId', visible: false}
            ,{field:'indName', width:200, title: 'RskSupervisionPortfPoint002.indName'}
            ,{field:'indTypeStr', width:200, title: 'RskSupervisionPortfPoint002.indType',sortField:'indType',}
            ,{field:'supTypeStr', width:130, title: 'RskSupervisionPortfPoint002.supType',sortField:'supType',}
            ,{field:'warnCond',  title: 'RskSupervisionPortfPoint002.warnCond',render: transferWarnFoul} 
            ,{field:'foulCond', title: 'RskSupervisionPortfPoint002.foulCond',render: transferWarnFoul} 
            ,{field:'approveCond', title: 'RskSupervisionPortfPoint002.approveCond',render: transferWarnFoul} 
        ],
        page:false,
        height:280,
        done: function() {
            if(formTodoList) {
            	if(entity.opeType == 'delete'){
            		var indId = entity.indId;
            		var allData=table.getAllRows();
            		$.each(allData,function(i,v){
                 		if(v.indId==indId){
                 			 if(pageModel == PAGE_MODEL_REFERENCE){
                 				 var fixedCheckBox = table.$element.next('.layui-table-view').find('.layui-table-fixed').find('tr[data-index="' + i + '"]').find(':checkbox[name="layTableCheckbox"]');
                  	            fixedCheckBox.prop('disabled', false).next().trigger('click');
                  	            fixedCheckBox.prop('disabled', true);
                 			 }
                 			 else
                 				 table.checkRow(i);
                 			return false;
                 		}
                 	});
            	}else{
            		 var indInfoArray = entity.rskSupervisionIndicator002DtoList || [];
                     var allData=table.getAllRows();
                     $.each(indInfoArray, function(j,arr) {
                     	$.each(allData,function(i,v){
                     		if(v.indId==arr.indId){
                     			 if(pageModel == PAGE_MODEL_REFERENCE){
                     				 var fixedCheckBox = table.$element.next('.layui-table-view').find('.layui-table-fixed').find('tr[data-index="' + i + '"]').find(':checkbox[name="layTableCheckbox"]');
                      	            fixedCheckBox.prop('disabled', false).next().trigger('click');
                      	            fixedCheckBox.prop('disabled', true);
                     			 }
                     			 else
                     				 table.checkRow(i);
                     			return false;
                     		}
                     	});
                       
                     });
            	}
            }  
          }
    });
	
    function onSaveOrSubmitAction(operateType) {
        var selArr = table.getAllCheckedRows();
        if (!selArr || selArr.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
        var object = $.serializeToObject('#submitForm', true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.rskSupervisionIndicator002DtoList=selArr; 
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint002/check', object, function(result) {
    		Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList('/invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint002/save', object, "RskSupervisionPortfPoint002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList('/invest/riskredis/quota/portfolioindicator/RskSupervisionPortfPoint002/save', object, "RskSupervisionPortfPoint002_btn_submit");
                }
        });
    });   	    
    };	

    function transferWarnFoul(data, type, row, meta){
    	var lnkNo="";
    	var wg="";var detail = "";
    	if(data==''){
    		return wg;
    	}
    	var detailJson = JSON.parse(data);
		if(detailJson!=null){
			/* 限制型 */
			detail = html2Escape(data);
			if(row.indType.indexOf("LIMIT_")!=-1){
				if(detailJson.threshold_1.value!=null&&detailJson.threshold_1.value!=''){
					wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit);
				}
				
			}else{
				/* 禁止型 */
				wg = detailJson.threshold_1.value_cn;
				wg=wg.substring(0,wg.length-1);
			}
		}
		return wg;
    }
    /**
     * 单位转换
     */
    function valueConvertUnit(value){
    	if(value==''){
    		return value;
    	}
    	var retStr;
    	var supUnitArr = JSON.parse(SUP_UNIT);
    	$.each(supUnitArr,function(i,v){
    		if(v.id==value){
    			if(value==NONE){
    				retStr="";
    			}else{
    				retStr=v.text;
    			}
    			return false;
    		}
    	})
    	return retStr;
    };
    function StringToChinese(id,type){
    	var arr=[];
    	var totalIndTypeEnum=JSON.parse(IND_TYPE_TOTAL_ENUM);
    	//var arrFobEnum=JSON.parse(relateIndType).filter(item => item.id===type);
    	var arrFobEnum=_.filter(JSON.parse(relateIndType),function(item){return item.id===type});
    	if(null!=totalIndTypeEnum&&null!=arrFobEnum){
    		//arr=totalIndTypeEnum.filter(item => item.id===id&&item.col===arrFobEnum[0].text)
    		arr=_.filter(totalIndTypeEnum,function(item){return item.id===id&&item.col===arrFobEnum[0].text})
    	}
    	if(arr==[]||arr.length==0)
    		return id;
    	else
    		return arr[0].text;
	};
    /* 普通字符转换成转意符 */
	var html2Escape = function(sHtml){
		return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
	};
	
    function init(){
    	table.load();
    	if($("#portfolioLevel").val()=='01')
    		$("#portfMixDiv").hide();
    	else
    		$("#portfMixDiv").show();
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	//初始方法调用
    	init();
    	$('#RskSupervisionPortfPoint002_BTN_search').click(function() {
    		 table.load();
    	});
    	
        // 绑定按钮事件
        $('#RskSupervisionPortfPoint002_btn_save').click(function() { 
        	onSaveOrSubmitAction('save');
        });
        // 绑定按钮事件
        $('#RskSupervisionPortfPoint002_btn_submit').click(function() { 
        	onSaveOrSubmitAction('submit');
        });
    });
}());