(function() {
	 window.componentLoadCallback = function (isDone, pagePath, params) {
     	debugger;
         if (isDone) {
        	 var tem=$('#oType').val();
        	 countCnyAmt();
        	 $('#oType').val(tem);
         }
     };
    var editFlag = true;
    var init=function() {
    	debugger;
        if(pageModel!=PAGE_MODEL_NEW){
            $("#finprodName").attr('disabled',true);
            editFlag=false;
        }

		if($("#gradeOrgType").val() === '02'){
			$("#grade_org_id").show();
		}else{
			$("#grade_org_id").hide();
		}
        
    };
    var getSearchParams=function(){
//        return $.serializeToObject($("#submitForm"));
        var dto = {};
        dto.finprodName = $("#finprodName").text();
        dto.finprodId = $("#finprodId").val();
        dto.gradeOrgId = $("#gradeOrgId").val();
        dto.oType = $("#oType").val();
        return dto;
    };
    //折合人民币金额字段取得
	    var countCnyAmt = function() {
	    if($('#finprodName').val()&&$('#finprodName').val()!=""){
	    	$("#finprodId").val($('#finprodName').select2('data')[0].id);
	    }
	    if($("#finprodName").val()){
	    	if ($("#finprodName").val().startsWith("F01")) {
	    		// var mySelect = document.getElementById("oType");
	    		// mySelect.dataSet.result="${MODEL_MAIN_GRADE_TYPE_SEC}";
	    		var itemFwd = [];
	    		var TERM_MODEL_MAIN_GRADE_TYPE = JSON.parse(MODEL_MAIN_GRADE_TYPE);
	    		for ( var i in TERM_MODEL_MAIN_GRADE_TYPE) {
	    			if (!TERM_MODEL_MAIN_GRADE_TYPE.hasOwnProperty(i))
	    				continue;
	    			if($('#finprodName').select2('data')[0].col&&$('#finprodName').select2('data')[0].col!="12"){
	    				if($("#gradeOrgId").val()&&$("#gradeOrgId").val()!="98"){
	    					if (TERM_MODEL_MAIN_GRADE_TYPE[i].id == "01") {
	    	    				itemFwd.push(TERM_MODEL_MAIN_GRADE_TYPE[i].id);
	    	    				break;
	    	    			}
	    				}
	    			}
	    			if($("#gradeOrgId").val()&&$("#gradeOrgId").val()!="98"){
    					if (TERM_MODEL_MAIN_GRADE_TYPE[i].id != "03"
    						&& TERM_MODEL_MAIN_GRADE_TYPE[i].id != "04") {
    						itemFwd.push(TERM_MODEL_MAIN_GRADE_TYPE[i].id)
    					}
    				}else if($("#gradeOrgId").val()&&$("#gradeOrgId").val()=="98"){
    					if (TERM_MODEL_MAIN_GRADE_TYPE[i].id != "02"
    						&& TERM_MODEL_MAIN_GRADE_TYPE[i].id != "03") {
    						itemFwd.push(TERM_MODEL_MAIN_GRADE_TYPE[i].id)
    					}
    				}
	    			
	    		}
	    		$('#oType').updateChosenArr({
	    			item : itemFwd,
	    			disabled : false
	    		});
	    		
	    	} else if ($("#finprodName").val().startsWith("F18")) {
	    		var itemFwd = [];
	    		var TERM_MODEL_MAIN_GRADE_TYPE = JSON.parse(MODEL_MAIN_GRADE_TYPE);
	    		for ( var i in TERM_MODEL_MAIN_GRADE_TYPE) {
	    			if (!TERM_MODEL_MAIN_GRADE_TYPE.hasOwnProperty(i))
	    				continue;
	    			if (TERM_MODEL_MAIN_GRADE_TYPE[i].id != "02") {
	    				itemFwd.push(TERM_MODEL_MAIN_GRADE_TYPE[i].id)
	    			}
	    			
	    		}
	    		$('#oType').updateChosenArr({
	    			item : itemFwd,
	    			disabled : false
	    		});
	    	}
	    }
	};
	
    $(function() {
        init();
        $("#finprodName").on('change',function(){
             countCnyAmt();
        });
        $("#gradeOrgId").on('change',function(){
            countCnyAmt();
       });

		// 评级机构类型onchange事件
		$("#grade_party_type").on('change',function(){
			gradePartyTypeChange();
		});

		// 评级机构联动指定评级机构是否显示
		var gradePartyTypeChange = function() {
			if($("#gradeOrgType").val() === '02'){
				$("#grade_org_id").show();
			}else{
				$("#grade_org_id").hide();
			}
		};

        // zhujiabin add 20181018 end
        $('#Specialassets002_btn_save').click(function() {
        	var object = $.serializeToObject('#submitForm');
            object.pageModel = pageModel;
            object.workflowId = workflowId;
            object.finprodName = $("#finprodName").val();
            object.finprodId = $("#finprodId").val();
            object.gradeOrgId = $("#gradeOrgId").val();
            object.oType = $("#oType").val();
            object.seqno=SEQNO;
        	object.operateType = 'save';
            Joyin.ajaxJson("/model/impairment/assetratingallocation/specialassets/Specialassets002/check",object,function(result){
                	Joyin.confirm(result.data, function () {
                            WorkFlow.doSaveList('/model/impairment/assetratingallocation/specialassets/Specialassets002/save', object, "Specialassets002_btn_save");
                    });
            })
        });
        $('#Specialassets002_btn_submit').click(function() {
        	var object = $.serializeToObject('#submitForm');
            object.pageModel = pageModel;
            object.workflowId = workflowId;
            object.finprodName = $("#finprodName").val();
            object.finprodId = $("#finprodId").val();
            object.gradeOrgId = $("#gradeOrgId").val();
            object.oType = $("#oType").val();
            object.seqno=SEQNO;
        	object.operateType = 'submit';
            Joyin.ajaxJson("/model/impairment/assetratingallocation/specialassets/Specialassets002/check",object,function(result){
                	Joyin.confirm(result.data, function () {
                		WorkFlow.doSubmitList('/model/impairment/assetratingallocation/specialassets/Specialassets002/save', object, "Specialassets002_btn_submit");
                    });
            })
        });
    }());
}());