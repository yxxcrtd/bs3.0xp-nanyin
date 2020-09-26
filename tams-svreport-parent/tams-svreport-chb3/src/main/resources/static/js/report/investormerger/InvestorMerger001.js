$(function() {
    var subgo = function(){
 	   Joyin.error("请先勾选待合并的数据！");
    }
	
	
  //跳转的页码
    var pageCount = 1;
//    var chooseholderName = '';
//    var chooseidentifyNumber = '';
//    var choosepersonalIdentifyType = '';
//    var chooseinstitutionIdentifyType = '';
//    var chooseotherIdentifyName = '';
//    var chooseindentifyMark = '';
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            //合并后识别标识
        	mergerIndentifyMark:$('#mergerIndentifyMark').val(),
        	//合并状态
        	operateStatus:$('#operateStatus').val()==null?null:$('#operateStatus').val().join(","),
        	//数据日期
        	dataTimeStart:$('#dataTime').startDate(),
        	dataTimeEnd:$('#dataTime').endDate(),
        	//投资者名称
        	holderName:$('#holderName').val(),
        	//个人证件类别
        	personalIdentifyType:$('#personalIdentifyType').val()==null?null:$('#personalIdentifyType').val().join(","),
        	//机构证件类别
        	institutionIdentifyType:$('#institutionIdentifyType').val()==null?null:$('#institutionIdentifyType').val().join(","),
        	//证件号码
        	identifyNumber:$('#identifyNumber').val(),
//        	chooseholderName,
//        	chooseidentifyNumber,
//        	choosepersonalIdentifyType,
//        	chooseinstitutionIdentifyType,
//        	chooseotherIdentifyName,
//        	chooseindentifyMark
        }
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        done: function (data) {
        	layui.form.render();
        },
        checkOnClickRow:false,
        cols:[
            { title: "indentifyMark", field: 'indentifyMark',width:0,sortable:false,visible:false},
            { title: "isOrNoSubmit", field: 'isOrNoSubmit',width:0,sortable:false,visible:false},
            { title: "合并状态", field: 'operatestatusStr',sortable:false,width:110},
            { title: "数据日期", field: 'dataDate', type: 'datetime',sortable:false,width:170},
            {field:'chooseMerger', title:'合并后识别标识选择',width: 172,unresize: true,sortable:false,render: function (data, type, row, meta) {
            	if(row.isOrNoSubmit!=null){
            		if(row.indentifyMark===row.mergerindentifymark){
                		var progress = '<div> <input type="checkbox" value='+row.indentifyMark+' checked="" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" disabled=""> </div>';
                	}else{
                		var progress = '<div> <input type="checkbox" value='+row.indentifyMark+' name="close" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" disabled=""> </div>';
                	}
            	}else if(row.indentifyMark===row.mergerindentifymark){
            		var progress = '<div> <input type="checkbox" value='+row.indentifyMark+' checked="" name="open" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF" disabled=""> </div>';
            	}else{
            		var progress = '<div> <input type="checkbox" value='+row.indentifyMark+' name="close" lay-skin="switch" lay-filter="switchTest" lay-text="ON|OFF"> </div>';
            	}
                return progress;}},
            { title: "合并人", field: 'updateUser',sortable:false,width:120},
            { title: "合并时间", field: 'updateTime', type: 'datetime',sortable:false,width:170},
            { title: "识别标识", field: 'indentifyMark',sortable:false,width:150},
            { title: "合并后识别标识", field: 'mergerindentifymark',sortable:false,width:150},
            { title: "该投资者是否属于本行", field: 'isBelongBankStr',sortable:false,width:180},
            { title: "投资者名称", field: 'holderName',sortable:false,width: 109},
            { title: "个人证件类别", field: 'personalIdentifyTypeStr',sortable:false,width: 200},
            { title: "机构证件类别", field: 'institutionIdentifyTypeStr',sortable:false,width: 200},
            { title: "证件号码", field: 'identifyNumber',sortable:false,width: 172},
            { title: "原识别标识", field: 'orgIndentifyMark',sortable:false,width:120},
            { title: "投资者所属银行名称", field: 'theBankName',sortable:false,width:168},
            { title: "投资者所属银行代码", field: 'theBankCode',sortable:false,width:168},
            { title: "投资者境内外标识", field: 'holderMarkStr',sortField:'holderMark',sortable:false,width:152},
            { title: "投资者所属国家或地区", field: 'holderRegionStr',sortField:'holderRegion',sortable:false,width:180},
            { title: "投资者类别", field: 'holderTypeStr',sortField:'holderType',sortable:false,width:114},
            { title: "其他证件名称", field: 'otherIdentifyName',sortable:false,width:126},
            { title: "SPV资金托管账户及开户行", field: 'spvCapAccBank',sortField:'spvCapAccBank',sortable:false,width: 210},
            { title: "其他资金托管账户及开户行", field: 'otherCapAccBank',sortable:false,width: 210},
            { title: "性别", field: 'holderSexStr',sortable:false,width:75},
            { title: "风险偏好", field: 'holderRiskPreferStr',sortable:false,width: 100},
            {title:"手机号码", field: 'holderCellphone',sortable:false,width: 120},
            {title:"固定电话", field: 'holderTelephone',sortable:false,width: 120},
            {title:"电子邮箱", field: 'holderEmail',sortable:false,width: 150},
            {title:"备注", field: 'remark', sortable:false,width: 160}
        ]
    });
    
//    layui.use('form', function () {
//        var form = layui.form;
//	    form.on('checkbox(layTableAllChoose)', function(data){
////	    	    layer.tips('温馨提示：全选被限制使用', data.othis)
//	    	var child = $(':checkbox[name!="layTableCheckbox"]');
//
//	        child.each(function(index, item){
//	            item.checked = data.elem.checked;
//	            $(body).find('tr').toggleClass(self.options.rowSelectedClass, obj.checked);
//	        });
//	        form.render('checkbox');
//	    	});
//    });
       function init(){
           if(isMain=='01'){
               setTimeout(function() {
            	   $('#dataTime').val(searchDataStart+' - '+searchDataEnd);
                   table.load();
               }, 500);
           }
         //报送前检查调用数据检查
           if(validKey){
               setTimeout(function() {
                   table.load();
               },500)
           }
       };
       init();
       
       $(function(){
           // 绑定按钮事件
           $('#SvrInvestorMerger001_btn_search').click(function() {
               table.load();
	           checkboxall();
           });
       });
       
     //导出所有查询结果
       $('#SvrInvestorMerger001_btn_export').click(function() {
           exportExcel("1");
       });
       
     //手工处理
//       $('#SvrInvestorMerger001_btn_manual').click(function() {
//    	   var manArr=table.getAllCheckedRows();
//           if(!manArr || manArr.length !=1){
//        	   Joyin.error("请先勾选待合并的数据！");
//           }else{
//        	   Joyin.confirm("请确认是否要手工处理选中记录？", function() {
////        		var checkkeyList = [];   
////        		checkkeyList = JSON.stringify(manArr);   
////	        	layui.layer.open({type:3});
////	            Joyin.ajaxJson('manual', {checkkeyList : JSON.stringify(manArr)}, function() {
////	                table.load();
////	                layui.layer.closeAll();
////	            });
//        		   
//       		   	var paramObj = getSearchParams();
//   	        	layui.layer.open({type:3});
//   	            Joyin.ajaxJson('manual', paramObj, function() {
//   	                table.load();
//   	                checkboxall();
//   	                layui.layer.closeAll();
//   	            });
//   	        });
//           }
//       });
       
       var checkboxall = function(){
    	   var isProccess = false;
         	//监听表格复选框选择
         	table.layTable.on('checkbox(dataTable)', function (obj) {
         		// 防止处理逻辑多次循环调用
         		// 设置单资源占用
         		if (isProccess){
         			return;
         		}
         		isProccess = true;
   	        // 标志是否在当前页选中数据
   	        var isChecked = obj.checked;
   	        var checkdata = obj.data;
   	        var addTableData = table.getAllRows();
   	        // 处理已选择的数据
   	        if(addTableData){
   	        	// 取消选择
//		        	table.uncheck();
//		        	chooseindentifyMark = checkdata.indentifyMark;
//		        	chooseholderName = checkdata.holderName;
//		            chooseidentifyNumber = checkdata.identifyNumber;
//		            choosepersonalIdentifyType = checkdata.personalIdentifyType;
//		            chooseinstitutionIdentifyType = checkdata.institutionIdentifyType;
//		            chooseotherIdentifyName = checkdata.otherIdentifyName;
   	            $(addTableData).each(function(i,v){
   	                if(checkdata.holderName===v.holderName
   	                		&& checkdata.identifyNumber===v.identifyNumber
   	                		&& checkdata.personalIdentifyType===v.personalIdentifyType
   	                		&& checkdata.institutionIdentifyType===v.institutionIdentifyType
   	                		&& checkdata.otherIdentifyName===v.otherIdentifyName){
   	                	if(checkdata.indentifyMark===v.indentifyMark){
   	                	} else {
   	                		table.checkRow(v.LAY_TABLE_INDEX);
   	                	}
   	                }
   	            })
   	        }
//   	        if(!isChecked){
//   	            pageCount++;
//   	            table.reload({page:{curr:pageCount}});
//   	        }
   	        // 关闭占用
         		isProccess = false;
         	});
       }

       
       layui.form.on('switch(switchTest)', function(data){
    	   var indentifyMark = this.value;
    	   Joyin.confirm("请确认是否要手工处理选中记录？", function() {
    		   var paramObj = getSearchParams();
    		   paramObj.indentifyMark = indentifyMark;
  	        	layui.layer.open({type:3});
  	            Joyin.ajaxJson('manual', paramObj, function() {
  	            	table.load();
  	                checkboxall();
  	                layui.layer.closeAll();
  	            });
  	        },data,function(){
  	        	var obj = $(data.elem);
  	        	obj.prop('checked',!(data.elem.checked));
  	        	layui.form.render();
  	        });
       });
       
       
       /**
        * 导出
        */
	    var exportExcel = function(flag) {
		var expArr = table.getAllCheckedRows();
		var param = {};
		if (!expArr || expArr.length == 0) {
			param = getSearchParams();
			Joyin.exportExcel("export", param);
		} else {
			var keyList = [];
			param.keyList = JSON.stringify(expArr);
			param.expKeys = keyList.join(',')
			param.flag = flag;
			Joyin.exportExcel('export', param);
		}
	};
       
}());