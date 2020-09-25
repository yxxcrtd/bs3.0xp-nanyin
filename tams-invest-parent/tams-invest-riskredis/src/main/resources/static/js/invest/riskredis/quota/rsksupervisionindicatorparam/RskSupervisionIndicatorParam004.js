/**
 * 风险授权管理新建
 */
$(function() {
	 
    /**
     * 保存或提交
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType) {
    	var object = table.getAllCheckedRows();
    	if (!object || object.length == 0) {
            Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
            return;
        }
    	var redisField = "";
    	var redisFieldTemp=new Array();
    	for(var i=0;i<object.length;i++){
    		redisFieldTemp[i]=object[i].id;
    	}
    	if(redisFieldTemp)
    		redisField = redisFieldTemp.join(",");
    	top.returnData=redisField;
    	var index = parent.layer.getFrameIndex(window.name);
        parent.layer.close(index);
    	
    }
    /**
     * 定义数据表格
     */
	var options = {
		page: false,
        ajax : {
            url : JoyinUtil.getRealPath('invest/riskredis/quota/rsksupervisionindicatorparam/RskSupervisionIndicatorParam004/findPage'),
            type : 'POST',
            data : function() {
                return {
                	
                };
            }
        },
        searchOnInit:true,
        checkbox:true,
        height:'200',
        loadonce:false,
    	cols:[
             {field:'id' , title: 'RskSupervisionIndicatorParam004.id'
             }
             ,{field:'text' ,title: 'RskSupervisionIndicatorParam004.text'
             }
         ],
         done: function() {
        	 if(redisField){
        		 var redisId = redisField.split(",");
        		 var row = table.getAllRows();
        		 if(redisId&&redisId.length>0&&row&&row.length>0){
        			 for(var i=0;i<row.length;i++){
        				 for(var j=0;j<redisId.length;j++){
        					 if(row[i].id==redisId[j]){
        						 table.checkRow(row[i].LAY_TABLE_INDEX);
            				 }
        				 }
        				 
        			 }
        		 }
        	 }
         }
         
    };
	
	/*
     * 绑定表格事件
     */
	var table = $("#dataTable").joyintable(options);
    
    var init = function(){
	
   }

    $(function() {
    	init();
        // 按钮事件
        $('#RskSupervisionIndicatorParam004_btn_save').click(function() {
            saveOrSubmit('save');
        });
        
    });
}());