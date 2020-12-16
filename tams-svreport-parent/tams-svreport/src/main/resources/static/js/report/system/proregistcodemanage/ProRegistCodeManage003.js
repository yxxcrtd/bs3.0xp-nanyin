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
        	var prodId = $('#prodId').val();
        	if(prodId){
        		Joyin.ajax(basePath + "svreport/system/proregistcodemanage/ProRegistCodeManage003/selectId",{prodId:prodId},
        				function(result){
        			if(result.data){
        				Joyin.error(JoyinUtil.getMessage("产品代码已存在，请重新输入。"));
        				$('#prodId').val('');
        				return;
        			}
        	});
        	}
        }
        
        var regCode = function(){
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
        }

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
        	if (vDate && mDate && mDate <= vDate){		
				Joyin.error(JoyinUtil.getMessage("到期日必须大于起息日。"));
				$('#mdate').val('');
			}
        	else{				
            var object = $.serializeToObject('#informationForm');
            object.operateType = operateType;
            object.pageModel = pageModel;
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
            $('#regCode').change(function(){
            	regCode();
    		});
            
        });    
}());