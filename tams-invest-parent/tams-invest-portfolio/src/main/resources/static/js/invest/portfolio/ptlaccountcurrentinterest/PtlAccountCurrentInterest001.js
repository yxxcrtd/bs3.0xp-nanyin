$(function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            busiAcctNo: $('#busiAcctNo').val(),//业务编号
            effDateStart:$('#effDate').startDate(),//到期日
            effDateEnd:$('#effDate').endDate()
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({ 
        ajax: {
            url: 'findPage',//请求路径
            type: 'POST',//请求类型
            data: function() {
                return getSearchParams();//获取请求参数
            }//ajax请求数据
        },
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest002/init",
                    "98%", "80%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                                    cashId:data.cashId,
                                    effDate:data.effDate,
                                    rateType:data.rateType});//主键组合代码
        },
        /**
         * 定义表格名称
         */
        cols:[
             {field:'statusStr', title: 'PtlAccountCurrentInterest001.status',sortable:false}//状态
            ,{field:'busiAcctNo', title: 'PtlAccountCurrentInterest001.busiAcctNo'}//业务编号
            ,{field:'acctName', title: 'PtlAccountCurrentInterest001.acctName'}//账号名称
            ,{field:'rateTypeStr', title: 'PtlAccountCurrentInterest001.rateType',sortField:'rateType'}//组合代码
            ,{field:'rate', title: 'PtlAccountCurrentInterest001.rate',type:'number', visible : false}
            ,{field:'rateStr', title: 'PtlAccountCurrentInterest001.rate',width : 150,type:'number'}
            ,{field:'effDate', title: 'PtlAccountCurrentInterest001.effDate',type:'date'}//开始日
            ,{field:'ccyStr', title: 'PtlAccountCurrentInterest001.ccy',sordFiled:'ccy',sortField:'ccy'}//币种
            ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}//编辑和删除事件
            
        ],
        done:function(data){
        	//modify by wm 浮动利息展示“浮动利率名称*系数+利差” start 2019-08-03
        	/*$('td[data-field="rate"]').attr("style","color:blue;");;
        	$('td[data-field="rate"]').click(function() {
        		var row = table.getRow($(this).parent().data('index'));
        		Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                        basePath + "invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest003/init",
                        "70%", "70%",{
                    pageModel:PAGE_MODEL_REFERENCE,
                    cashId: row.cashId,
                    effDate:row.effDate,
                    ccy:row.ccy,
                    rateType:row.rateType});
            });*/
       // 	$('td[data-field="rateStr"]').attr("style","color:blue;");;
        	//modify by wm 修改为固定利率不展示为蓝色并且点击不展示弹出框
        	var obj = $('td[data-field="rateStr"]');
             $.each(obj,function(v){
            	 var row = table.getRow(v);
            	 if(row.rateType=='02'){
            		 $(this).attr("style","color:blue;");
            	 }
             });
        	$('td[data-field="rateStr"]').click(function() {
        		var row = table.getRow($(this).parent().data('index'));
        		if(row.rateType=='02'){
        			Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                            basePath + "invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest003/init",
                            "70%", "70%",{
                        pageModel:PAGE_MODEL_REFERENCE,
                        cashId: row.cashId,
                        effDate:row.effDate,
                        ccy:row.ccy,
                        rateType:row.rateType});
        		}
            });
        	//modify by wm 浮动利息展示“浮动利率名称*系数+利差” end 2019-08-03
        }
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.rate = '1';
        data.currentRate ='1';
        data.coefficient = '1';
        data.spreadRate = '1';
        data.indexId = '1';
        data.operateType = 'edit';
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest002/check', data, function(result) {//数据验证
            Joyin.dialog(JoyinUtil.getLabel("PtlAccountCurrentInterest002.edit"),
                    basePath +"invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest002/init",
                    "98%", "98%", {pageModel:PAGE_MODEL_EDIT,//page_model:编辑
                                    cashId:data.cashId,
                                    effDate:data.effDate,
                                    rateType:data.rateType});//主键组合代码
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest001/check', object, function(result) {//数据验证
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest001/delete', object, "PtlAccountCurrentInterest001_btn_delete", "PtlAccountCurrentInterest002");//删除
            });
        });
    });
    
    /**
     * 导出
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();//获取参数
        Joyin.exportExcel('export', paramObj);//导出
    };
    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定查询按钮
        $('#PtlAccountCurrentInterest001_btn_search').click(function() {
            table.load();//加载表格
            $('#PtlAccountCurrentInterest001_btn_delete').show();//删除按钮显示
        });
        //绑定到处按钮
        $('#PtlAccountCurrentInterest001_btn_export').click(function() {
            exportExcel();//导出
        });
        //绑定新建按钮
        $('#PtlAccountCurrentInterest001_btn_add').click(function() {
            Joyin.dialog(JoyinUtil.getLabel("PtlAccountCurrentInterest002"),
                    basePath+ "invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest002/init",
                        "75%", "75%", {pageModel:PAGE_MODEL_NEW});
        });
        //绑定删除按钮
        $('#PtlAccountCurrentInterest001_btn_delete').click(function() {
            var deleteStr = '';
            var deleteList = [];
            if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();//判断表格不为空，然后获取选择的数据
            }
            if (!selArr || selArr.length == 0 || selArr.length > 1) {//判断是否选择，如果没有则报错COMMON_E_0026
                Joyin.error("请选择一条数据操作。");
                return;
            }else if(!selArr || selArr.length >= 1){
                for(var i = 0; i<selArr.length; i++){
                    var s = deleteStr.concat(selArr[i].busiAcctNo,'-',selArr[i].effDateStr);
                    deleteList.push(s);
                }
//                Joyin.error(JoyinUtil.getLabel("PtlAccountCurrentInterest001.onlyDeleteOne"));
//                return;
            }
            var o = '';
            var deleteMess = o.concat(JoyinUtil.getLabel("SysCommon.delete"),JoyinUtil.getLabel("PtlAccountCurrentInterest001.busiAcctNo"),'-',
                    JoyinUtil.getLabel("PtlAccountCurrentInterest001.effDate"),'[',deleteList.join(','),']')
            var object = {selectedItem: selArr, operateType:"delete"};//请求参数
            Joyin.ajaxJson(basePath + 'invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest001/check', object, function(result) {//数据验证
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete").concat('[',deleteList,']')), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/portfolio/ptlaccountcurrentinterest/PtlAccountCurrentInterest001/delete', object, "PtlAccountCurrentInterest001_btn_delete", "PtlAccountCurrentInterest002");//删除
                });
            });
        });
    });
}());