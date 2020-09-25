(function() {
    var getPageParams = function(){
        return {
            repId: $('#repId').val(),           
        }
    }
    /**
     * 定义数据表格
     */
    var roleTable=$("#roleTable").joyintable({
        ajax: {
            url: 'findAllRole',
            type: 'POST',
            data: {repId:$("#repId").val()}
        },
        cols:[
             {field:'workflowId', visible:false,fixed:true}
            ,{field:'roleId', visible:false,fixed:true}
            ,{field:'roleName', width:220, title: '可选角色',fixed:true}
            ,{field:'operation', width:170, title: 'SysCommon.operation', type:'action', buttons: ['add']}
        ],
        searchOnInit: true,
        loadonce:true,
        checkbox:false,
        height:290,
        page:false
        
    });
    
    var userRoleTable = $("#userRoleTable").joyintable({
        ajax: {
            url: 'findUserRole',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        cols:[
             {field:'workflowId', visible:false}
            ,{field:'roleId', visible:false}
            ,{field:'roleName', width:220, title: '已选角色',fixed:true}
            ,{field:'operation', width:170, title: 'SysCommon.operation', type:'action', buttons: ['delt']}
        ],
        searchOnInit:true,
        loadonce:true,
        checkbox:false,
        height:290,
        page:false
    });
    
    /**
     * 绑定编辑事件
     */
    roleTable.bind('click', '.add', function(data, index) {
        var index=0;
        var obj=userRoleTable.getAllRows();
       for(var i in obj){
           if(obj[i].roleName==data.roleName){
             index++;
        }  
       }
       if(index>0){
           roleTable.removeRow(data);
       }else{
           roleTable.removeRow(data);
           userRoleTable.addRow(data); 
       }
    });
    
    userRoleTable.bind('click', '.delt', function (data, index) {
        var index=0;
        var obj=roleTable.getAllRows();
       for(var i in obj){
           if(obj[i].roleName==data.roleName){
             index++;
        }  
       }
       if(index>0){
           userRoleTable.removeRow(data);
       }else{
           userRoleTable.removeRow(data);
           roleTable.addRow(data); 
       }
    });
    
    function getFormData(){
        return{
            userId:$("#userId").val(),
            userName:$("#userName").val(),
            workflowId:$("#workflowId").val(),
            createDept:$("#createDept").val(),
            selectedItem:userRoleTable.getAllRows(),
            unSelectedItem:roleTable.getAllRows(),
            flag: $('#flag').val(),
            datas: $('#datas').val()
        };
    }
    
    function saveOrSubmit() {
        var object = getFormData();
        var flag=object.flag;
        var datas=object.datas;
        var arr=JSON.stringify(object.selectedItem);
        Joyin.confirm("是否确认修改？", function(){
        	Joyin.ajax('save',
        			{repId:$("#repId").val(),arr:arr,flag:flag,datas:datas},function(result){
        				//点击保存后关闭窗口
        				var index = parent.layer.getFrameIndex(window.name);
        				parent.layer.close(index);
        			});
        });
    };
   
    var befFormData = undefined;
	function init() {
		userRoleTable.load();
		roleTable.load();
		var objUser = userRoleTable.getAllRows();
		var objAll = roleTable.getAllRows();
		for ( var i in objAll) {
			var index = false;
			for(var j in objUser){
				if (objAll[i].roleId == objUser[j].roleId) {
					index=true;
					break;
				}
			}
			if(index){
				roleTable.removeRow(objAll[i]);
			}
			
		}
	}
	;
    /**
	 * 画面加载完成
	 */
    $(function() {
    	//初始化
    	init();
        $("#SysActorAssign002_btn_save").click(function(){
            saveOrSubmit();
        });
    });
}());