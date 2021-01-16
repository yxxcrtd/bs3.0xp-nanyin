/**
 * 产品登记编码新建
 */
(function() {
    /**
     * 获取表单参数
     */
        var informationform = function() {
            return $.serializeToObject('#informationForm');
        } 
        
        var prodId =function(){
        	$('#prodName').val('');
         	$('#operateMode').val('');
         	$('#vdate').val('');
         	$('#mdate').val('');        	
        	var prodId = $('#prodId').val();
        	if(prodId){        		
        				Joyin.ajax(basePath + "svreport/system/proregistcodemanage/ProRegistCodeManage003/selectInfo",{prodId:prodId},
                				function(result){
                			if(result){        				
                				$('#prodName').val(result.prodName);
                				if(result.operateMode == '01'){
                					$('#operateMode').val("封闭式净值型");       					
                				}else if(result.operateMode == '02'){
                					$('#operateMode').val("封闭式非净值型");
                				}else if(result.operateMode == '03'){
                					$('#operateMode').val("开放式净值型");
                				}else if(result.operateMode == '04'){
                					$('#operateMode').val("开放式非净值型");
                				}
                 		    	$('#vdate').val(result.vdateStr).trigger('change');
                 		    	$('#mdate').val(result.mdateStr).trgger('change');       		    	    		    	
                			}
                	});      	        	
        	}
        }
        
        /*var regCode = function(){
        	var regCode = $('#regCode').val();
        	if(regCode){
    			Joyin.ajax(basePath + "svreport/system/proregistcodemanage/ProRegistCodeManage003/selectCode",{prodId:prodId,regCode:regCode},
            			function(result){
            		if(result.data){
            			Joyin.error(JoyinUtil.getMessage("不同的产品代码不能对应同一产品登记编码。"));
            			$('#regCode').val('');
            			return;
            		}
    			});
    		}
        }*/

        /**
         * 保存或提交
         * @param operateType 操作类型（save，submit）
         * @returns
         */
        function saveOrSubmit(operateType) {
        	var vDate = $('#vdate').val();
        	var mDate = $('#mdate').val();
        	var prodId = $('#prodId').val();
        	var regCode = $('#regCode').val();			
            var object = $.serializeToObject('#informationForm');
            object.operateType = operateType;
            object.pageModel = pageModel;
            if(regCode && regCode.length != 14 && regCode.length != 15){
        		Joyin.error(JoyinUtil.getMessage("产品登记编码只能是14位或15位字符，请重新输入。"));
        		$('#regCode').val('');
        		return;
        	}
            if(regCode){
            	Joyin.ajax(basePath + "svreport/system/proregistcodemanage/ProRegistCodeManage003/selectCode",{regCode:regCode},
            			function(result){
            		if(result.data){
            			Joyin.error(JoyinUtil.getMessage("不同的产品代码不能对应同一产品登记编码。"));            			
            			return;
            		}else{
            			Joyin.ajax(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/checkInsertData1', {regCode:regCode}, function(result) {
            			Joyin.ajax(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/checkInsertData', {prodId:prodId,regCode:regCode}, function(result) {
                            Joyin.ajaxJson(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/check', object, function(result) {
                                Joyin.confirm(result.data, function() {                	
                                    if(operateType === 'save') {                    	
                                        WorkFlow.doSaveList(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/save', object, "ProRegistCodeManage003_btn_save");               
                                    }
                                    else {                   	
                                        WorkFlow.doSubmitList(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/save', object, "ProRegistCodeManage003_btn_submit");
                                    	}
                                });
                            });
                          });
            		  });
            		}
    			});           	
            	/* Joyin.ajax(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/checkInsertData', {prodId:prodId,regCode:regCode}, function(result) {
                     Joyin.ajaxJson(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/check', object, function(result) {
                         Joyin.confirm(result.data, function() {                	
                             if(operateType === 'save') {                    	
                                 WorkFlow.doSaveList(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/save', object, "ProRegistCodeManage003_btn_save");               
                             }
                             else {                   	
                                 WorkFlow.doSubmitList(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/save', object, "ProRegistCodeManage003_btn_submit");
                             	}
                         });
                     });
                   });*/
            }else{          
            Joyin.ajaxJson(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/check', object, function(result) {
                Joyin.confirm(result.data, function() {                	
                    if(operateType === 'save') {                    	
                        WorkFlow.doSaveList(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/save', object, "ProRegistCodeManage003_btn_save");               
                    }
                    else {                   	
                        WorkFlow.doSubmitList(basePath + 'svreport/system/proregistcodemanage/ProRegistCodeManage003/save', object, "ProRegistCodeManage003_btn_submit");
                    	}
                });
            });
        }
   }
    
        $(function() {
            // 按钮事件
            $('#ProRegistCodeManage003_btn_save').click(function() {
                saveOrSubmit('save');
            });
            $('#ProRegistCodeManage003_btn_submit').click(function() {
                saveOrSubmit('submit');
            });
            $('#prodId').change(function(){
            	prodId();
    		});
            /*$('#regCode').change(function(){
            	regCode();
    		});*/
            
        });    
}());