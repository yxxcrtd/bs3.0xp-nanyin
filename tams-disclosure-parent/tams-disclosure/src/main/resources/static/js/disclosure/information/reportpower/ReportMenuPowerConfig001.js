$(function(){
	var getPageParams = function() {
	    return{
	        reportName: $('#reportName').val(), 
        }
	};
	// 表格显示
	var table=$("#dataTable").joyintable({
        ajax: {
            url: 'searchPage',
            type: 'POST',
            data: function() {
                return getPageParams();
            }
        },
        searchOnInit: false,
        cellMinWidth:100 ,
        cols: [
            { title:"报表名称",field: 'reportName'},
            { title:"分配角色",field: 'roleName'},
            { title:"报表ID",field: 'repId', visible:false},          
            {field: 'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
	});
	/**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        var repId=data.repId
        var reportName=data.reportName
        data.operateType = "edit";
            Joyin.dialog(JoyinUtil.getLabel("角色分配"),
                    basePath + "disclosure/information/reportpower/ReportMenuPowerConfig002/init",
                    "90%", "90%",{repId:repId,reportName:reportName},function(){table.reload();});   
    });
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
            var repId=data.repId
            data.operateType = "delt";
                Joyin.ajax('getDelete',
                        {repId:repId},function(){
                        	layer.alert('删除成功！'),
                            table.reload();
                        });
        }, function() {
            console.log('取消');
        }); 
    });
    var powerAssign = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var arr=new Array();
            for(var i in selArr){    
               arr.push({'repId': selArr[i].repId});
            }
            var datas = JSON.stringify(arr);
            //设置是也来自批量分配角色
            var flag = 'Y';
        }
        if (!selArr || selArr.length == 0) {
            Joyin.alert("至少选择一个报表！",2);
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.confirm(JoyinUtil.getMessage("确认批量权限分配？这将会导致所选的报表重新分配！",JoyinUtil.getLabel("SysCommon.delete")), function(){
                Joyin.dialog(JoyinUtil.getLabel("角色分配"),
                        basePath + "disclosure/information/reportpower/ReportMenuPowerConfig002/init",
                        "90%", "90%",{datas:datas,flag:flag},function(){table.reload();});
            }, function() {
                console.log('取消');
            });      
        }
    };
    var exportExcel = function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        /*if (!selArr || selArr.length == 0) {
            Joyin.alert("至少选择一个报表！",2);
        } else {*/
            Joyin.exportExcel('export', {
                datas : datas
            });
       // }
    };
	 $(function() {
		 $('#ReportMenuPowerConfig001_btn_search').click(function() {
	            table.load(); 
	        });		 
		 $('#ReportMenuPowerConfig001_btn_powerAssign').click(function() {
		         powerAssign();
	        });
		 $('#ReportMenuPowerConfig001_btn_export').click(function() {
	            exportExcel();
	        });
	 });
});