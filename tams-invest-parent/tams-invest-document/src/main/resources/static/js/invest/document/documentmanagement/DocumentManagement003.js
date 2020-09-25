$(function() {
	var getSearchParams = function() {
        return {
        	businessId:$("#businessId").val()
        }
    };
    /*
     * 绑定表格事件
     */
    var table=$("#dataTable").joyintable({
    	ajax: {
    		url : JoyinUtil.getRealPath('invest/document/documentmanagement/DocumentManagement003/findPage'),
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox:false,
        cols:[
             {field : 'seqno',visible: false}
            ,{field : 'path',visible: false}
            ,{field : 'attachmentFileName', title : 'DocumentManagement003.attachmentFileName',sortable:false}
            ,{field : 'userName', width : 150, title : 'DocumentManagement003.userName',sortable:false}
            ,{field : 'createTime', width : 150, title : 'DocumentManagement001.createTime',sortable:false,type:'date'}
            ,{field:'operation',type: 'action', title: 'SysCommon.operation',buttons:[{text:'下载',clas:'download',title:'下载'},'delt']}
            ]
    });
    
    
    /**
     * 绑定下载事件
     */
    table.bind('click','.download',function(data, index){
//    	JoyinUtil.getRealPath('/framework/download/' + data.seqno);
    	window.location.href=JoyinUtil.getRealPath('/framework/download/' + data.seqno);
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click','.delt',function(data, index){
    	var object = data;
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil
                .getLabel("SysCommon.delete")), function() {
            WorkFlow.doDeleteList(basePath
                    + 'invest/document/documentmanagement/DocumentManagement003/delete',
                    object, "DocumentManagement003_btn_delete", "DocumentManagement003");
        });
        table.load();
    })
    /**
     * 画面加载完成事件
     */
    var init = function(){
    	table.load();
    }
    /**
     * 画面加载完成
     */
    $(function() {
    	init();
    });
    
   
})
    