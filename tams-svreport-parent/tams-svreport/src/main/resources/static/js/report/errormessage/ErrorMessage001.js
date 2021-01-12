$(function(){
    var getPageParams = function() {
        return {
        	createTimeStart:$('#createTime').startDate(),
        	createTimeEnd:$('#createTime').endDate(),
        	orgCode:$('#orgCode').val()==null?null:$('#orgCode').val().join(','),
        	interfType:$('#interfType').val()==null?null:$('#interfType').val().join(','),
        	// status:$('#status').val()==null?null:$('#status').val().join(','),
        	// fileName:$('#fileName').val()
        }
    };
    
    var table = $('#errorTable').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        //loadonce:true,
        cols: [   {title:'工作流代码',field:'workflowId',visible:false},
                  {title:'检查时间',field:'createTime',type:'date',sortable:true},
                  {title:'接口类型',field:'interfType'},
                  {title:'文件名称',field:'fireName',width:250	,sortable:false},
                  {title:'业务主键',field:'dataid'},
                 // {title:'数据状态',field:'status'},
                //{title:'原始数据',field:'orgData',width:150,sortable:false},
                //{title:'机构代码',field:'orgCode',width:150},
                  {title:'检查结果',field:'checkResult',sortable:false},
                  {title:'错误信息',field:'errMsg',sortable:false},
                //{title:'部门代码',field:'deptCode', width:150,sortable:false},
               ]
    });
    
    function init(){
        if(isFromMain=='01'){
            setTimeout(function() {
                $('#createTime').val(searchDataStart+' - '+searchDataEnd);
                table.load();
            }, 500);
        }
    };
    init();
    
    /**
     * 查询按钮
     */
    $('#ErrorMessage001_btn_search').click(function(){
    	table.load();
    });
    // 导出按钮点击事件
    $('#ErrorMessage001_btn_export').click(function() {
        var selectedItem = table.getAllCheckedRows();
        var paramObj={};
        if(selectedItem.length==0){
            paramObj= getPageParams();
        }
        else{
        	//add by wm 针对特殊字符处理  jason 识别“'”字符时转换会有问题，将企转换为“&apos;” start
        	for(var i=0;i<selectedItem.length;i++){
        		if(selectedItem[i].orgData&&selectedItem[i].orgData.indexOf("'") != -1){
        			selectedItem[i].orgData = selectedItem[i].orgData.replace(new RegExp("'", 'gm'),"&apos;");
    			}
        		if(selectedItem[i].errMsg&&selectedItem[i].errMsg.indexOf("'") != -1){
        			selectedItem[i].errMsg = selectedItem[i].errMsg.replace(new RegExp("'", 'gm'),"&apos;");
    			}
        	}
			//add by wm 针对特殊字符处理  jason 识别“'”字符时转换会有问题，将企转换为“&apos;” end
            paramObj.keyList = JSON.stringify(selectedItem);
        }
        Joyin.exportExcel('export', paramObj);
    });
    
})
