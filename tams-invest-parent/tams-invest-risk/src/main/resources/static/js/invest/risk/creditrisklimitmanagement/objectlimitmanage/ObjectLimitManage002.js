/**
 * MstCalendarType002 日历类型管理新建（含修改）
 */
$(function() {
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.operateType = operateType; 
/*    	if($('#creditTagId').val() != null && $('#creditTagId').val() != ''){
    		object.creditTagId = $('#creditTagId').val();
    	}*/
        if(termChangeAction()){
        Joyin.ajaxJson(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage002/check', object, function(result) {
            Joyin.confirm(result.data, function() {
                if(operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage002/save', object, "ObjectLimitManage002_btn_save");
                }
                else {
                    WorkFlow.doSubmitList(basePath + 'invest/risk/creditrisklimitmanagement/objectlimitmanage/ObjectLimitManage002/save', object, "ObjectLimitManage002_btn_submit");
                }
            });
        });
        }
    };

    function termChangeAction() {
    	/*var term= $('#term').val();
    	var termUnit= $('#termUnit').val();
		 if(termUnit!='Y'){
			var str = term.substring(term.indexOf(".")+1,term.indexOf(".") + 3);
			if(str!="00"){
			//if (!(/(^[1-9]\d*$)/.test(term))){
				JoyinUtil.showMessage("最长可投期限单位不为年时,期限只能为整数", 'error', function() {
					//$('#term').val('');
					return false;
				});
			}else{
				return true;
			}
		 }else if(termUnit=='Y'){
			   var str = term.substring(term.indexOf(".")+1,term.indexOf(".") + 3);
			   if(str=="25" || str=="50" || str=="75" || str=="00"){
					return true;		 
	       		}else{
	     		   JoyinUtil.showMessage("最长可投期限单位为年时,小数点为0.25/0.5/0.75", 'error', function() {
	   					return false;
	   				});
			   }
		 }
		 */
	      var endTermValue= $('#endTermValue').val();
	      var endTermUnit= $('#endTermUnit').val();
	      if(endTermUnit!='Y'){
	    	  if(endTermValue.indexOf(".")>0){
				var str = endTermValue.substring(endTermValue.indexOf(".")+1,endTermValue.indexOf(".") + 3);
				if(str!="00"){
				//if (!(/(^[1-9]\d*$)/.test(term))){
					JoyinUtil.showMessage("终止期限单位不为年时,期限只能为整数", 'error', function() {
						//$('#term').val('');
						return false;
					});
				}else{
					return true;
				}
	    	  }else{return true;}
			 }else if(endTermUnit=='Y'){
				   if(endTermValue.indexOf(".")>0){
					
				   var str = endTermValue.substring(endTermValue.indexOf(".")+1,endTermValue.indexOf(".") + 3);
				   if(str=="25" || str=="50" || str=="75" || str=="00" || str==""){
						return true;		 
		       		}else{
		     		   JoyinUtil.showMessage("终止期限单位为年时,小数点为0.25/0.5/0.75", 'error', function() {
		     				//$('#term').val('');
		   					return false;
		   				});
				   }
			   }else{return true;}     
			}else{return true;}
    };
    
    $(function() {
 
        // 绑定按钮事件
        $('#ObjectLimitManage002_btn_save').click(function() {
            saveOrSubmit('save');
        });
        $('#ObjectLimitManage002_btn_submit').click(function() {
            saveOrSubmit('submit');
        });
        
        
        if(isNbCredit=="01"){
    		$("#isPerpetualSecDiv").hide();
    		$("#issueTypeDiv").hide();        	
        }
        //$('#upTime').attr('disabled',true)
        $('#upTime').attr("readonly","readonly")//将input元素设置为readonly

       // 非查看模式
		if (typeof pageModel !== 'undefined' && (pageModel === PAGE_MODEL_NEW )) {
		    $('#upTime').val(new Date());
		}else{
	        $('#busiType').attr("readonly","readonly")//将input元素设置为readonly
	        $('#busiType').attr('disabled',false)
	        $('#approId').attr("readonly","readonly")//将input元素设置为readonly
		}
		
    });
}());