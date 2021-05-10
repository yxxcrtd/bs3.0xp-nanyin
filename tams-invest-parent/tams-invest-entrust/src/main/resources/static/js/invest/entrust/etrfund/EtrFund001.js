/**
 * 基金委托
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){
    	var assetType1;
    	if($("#assetType1").val() == null || $("#assetType1").val() == ''){
    		assetType1 = $("#assetType1").val();
    	}
    	else{
    		assetType1 = $("#assetType1").val().join(',');
    	}
    	
    	var portfolioId;
        if($("#portfolioId").val() == null || $("#portfolioId").val() == ''){
            portfolioId = $("#portfolioId").val();
        }
        else{
            portfolioId = $("#portfolioId").val().join(',');
        }
    	/**新增资产二、三、四级分类 王贵奇 2019/7/29 开始**/
    	var assetType10;
        if($("#assetType10").val() == null || $("#assetType10").val() == ''){
        	assetType10 = $("#assetType10").val();
        }
        else{
        	assetType10 = $("#assetType10").val().join(',');
        }
    	
    	var assetType11;
        if($("#assetType11").val() == null || $("#assetType11").val() == ''){
        	assetType11 = $("#assetType11").val();
        }
        else{
        	assetType11 = $("#assetType11").val().join(',');
        }
    	/**新增资产二、三、四级分类 王贵奇 2019/7/29 结束**/
        
    	var ps = $("#ps").val();
//    	if($("#ps").val() == null || $("#ps").val() == ''){
//    		ps = $("#ps").val();
//    	}
//    	else{
//    		ps = $("#ps").val().join(',');
//    	}
        return {
        	portfolioId:portfolioId,
        	assetType1:assetType1,
        	// 新增资产二、三、四级分类 王贵奇 2019/7/29 开始
        	assetType10:assetType10,
        	assetType11:assetType11,
        	// 新增资产二、三、四级分类 王贵奇 2019/7/29 结束
        	entrustStatus:$("#entrustStatus").val(),
        	ccy:$("#ccy").val(),
        	finprodId:$("#finprodId").val(),
        	ps:ps,
        	startDate:$("#entrustDate").startDate(),
        	endDate:$("#entrustDate").endDate(),
        	trmFundType:$("#trmFundType").val(),
        	tradeMarket:$("#tradeMarket").val()
        };
    };

    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return searchform();
            }
        },
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("EtrFund002.detail"),
                    JoyinUtil.getRealPath("invest/entrust/etrfund/EtrFund002/init?parentPageId=" + pageId),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
        cols:[ 
            {field:'entrustStatusStr', title: '委托状态', width:'10%',sortField:"entrustStatus"}
//            ,{field:'entrustId', title: 'EtrFund001.entrustId', width:180}
            ,{field:'portfolioId', 
            	title: 'EtrFund001.portfolioId',
            	render : function(data, type, row, meta) {
                	return "<a  class='pro-link portfolioIdLink' style='cursor: pointer;'>"+row.portfolioId+"</ a>";
        		},
            	width:'10%',sortField:"portfolioId"
            }
            ,{field:'portfolioName', title: 'EtrFund001.portfolioName', width:'10%',sortField:"portfolioName"}
            ,{field:'assetType1Str', title: 'EtrFund001.finprodType2', width:'10%',sortField:"assetType1",visible: false}//原基金类型
            ,{field:'assetType1Name', title: 'EtrInvestBondInfo001.type9', width:'10%',sortField:"assetType1"}//资产二级分类 王贵奇2019/7/29
            ,{field:'assetType10Name', title: 'EtrInvestBondInfo001.type10', width:'10%',sortField:"assetType10"}//资产三级分类 王贵奇2019/7/29
            ,{field:'assetType11Name', title: 'EtrInvestBondInfo001.type11', width:'10%',sortField:"assetType11",visible:IS_SHOW_TYPE == '01'?true:false}//资产四级分类 王贵奇2019/7/29
            ,{field:'finprodMarketId', 
            	title: 'EtrFund001.finprodId', 
            	render : function(data, type, row, meta) {
            		if(row.finprodId != undefined){
            			return "<a  class='pro-link finprodIdLink' style='cursor: pointer;'>"+row.finprodMarketId+"</ a>";
            		}
            		else{
                	    return '';
                		}
            		},
            	width:'10%',sortField:"finprodMarketId"
            }
            ,{field:'finprodId', visible:false}
            ,{field:'finprodAbbr', title: 'EtrFund001.finprodAbbr', width:'10%',sortField:"finprodAbbr"}
            ,{field:'trmFundTypeStr', title: 'EtrFund002.trmFundType', width:'10%',sortField:"trmFundType"}
            ,{field:'tradeMarketStr', title: 'EtrFund002.tradePlat', width:'10%',sortField:"tradeMarket"}
            ,{field:'psStr', title: 'EtrFund001.ps', width:'10%',sortField:"ps"}
            /*,{field:'price', title: 'EtrFund001.price', width:'10%'}
            ,{field:'payFreq', title: 'EtrFund001.payFreq', width:'10%'}*/
            ,{field:'ccyStr', title: 'EtrFund001.ccy', width:'10%',sortField:"ccy"}
            ,{field:'vdateStr', title: 'EtrFund001.entrustVDate', type:'date', width:'10%',sortField:"vdate"}
            ,{field:'mdateStr', title: 'EtrFund001.entrustMDate', type:'date', width:'10%',sortField:"mdate"}
            ,{field:'entrustAmtOrNumStr',type:'number', title: 'EtrFund001.entrustAmtOrNumStr', width:150, type:'money',sortable:false}
            //,{field:'entrustNumStr',type:'number', title: 'EtrFund001.entrustNum', width:'10%', type:'money',sortField:"entrustNum"}
            ,{field:'finishAmt',type:'number', title: 'EtrFund001.finishAmt', width:'10%', type:'money',sortField:"finishAmt"}
            ,{field:'transactioncom', 
            	title: 'EtrFund001.transactioncom',
            	render : function(data, type, row, meta){
        			return "<div class='layui-progress layui-progress-big' style='margin-top:5px;'><div class='layui-progress-bar layui-bg-green' style='width:"+row.transactioncom+"%'><span class='layui-progress-text'>"+row.transactioncom+"%</span></div></div>";
        		},
            	width:'10%',sortable:false
            }
            ,{field:'action', title: '操作' , type:'action',buttons: ['edit'] , width:'10%'}
        ],
    });
    
    /**
     * 组合代码链接
     */
    table.bind('click','.portfolioIdLink', function(row) {
    	Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/portfolio/ptlmanagementinfo/PtlManagementInfo002/init",
                "98%", "90%", {pageModel:PAGE_MODEL_REFERENCE,//page_model:查看
                portfolioId:row.portfolioId});//主键组合代码
    });
    
    /**
     * 资产代码链接
     */
    table.bind('click','.finprodIdLink', function(row) {
    	//在新建界面进行显示单行的详细信息
    	Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                basePath + "invest/asset/fund/FinFundTarget002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_REFERENCE,finprodId:row.finprodId});
    	
    });
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        //data.valueDate = new Date(data.valueDate).format();
    	if(data.entrustStatusStr != "已退回" && data.entrustStatusStr != "已撤回"){
    		Joyin.error("请选择委托状态为：已退回、已撤回的进行修改");
    		return;
    	}
        data.pageModel=PAGE_MODEL_EDIT;
        data.operateType='edit'
        //Joyin.ajaxJson(basePath + 'invest/entrust/etrinvestbondinfo/EtrLendSpecInfo002/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("EtrFund002.edit"),
                    basePath + "invest/entrust/etrfund/EtrFund002/init?parentPageId=" + pageId,
                    "98%", "95%",data,
                    function(){table.reload();});
        //});
        
    });
    /**
     * 绑定删除事件
     */
    /*table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/entrust/etrfund/EtrFund001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0024",JoyinUtil.getLabel("SysCommon.delete")), function(){
            	Joyin.ajaxJson(basePath + 'invest/entrust/etrfund/EtrFund001/delete', object, function(result){
            		table.reload();
            		
            	});
            });
        });	//画面提示message【BUSS_COM_C_0024：请确认是否要删除?】																	
    });*/
    
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrfund/EtrFund001/export", searchform());
    };
    
    /** 
     * 新建
     */
    function showDialog() {
        Joyin.dialog(JoyinUtil.getLabel('EtrFund002'), basePath +'invest/entrust/etrfund/EtrFund002/init?parentPageId=' + pageId, '98%', '95%');
    }

    /**
     * 编辑按钮事件
     */
    $(function () {
        /**
         * 查询按钮
         */
        $('#'+pageId+'_btn_search').click(function() {
            table.load();
        });
        
        /** 
         * 新建
         */
        $('#'+pageId+'_btn_add').click(function(){
        	showDialog();
        	table.reload()
        });
        
        /**
         * 导出按钮
         */
        $('#'+pageId+'_btn_export').click(function() {
            exportExcel();
        });
        
        /** 
         * 关闭委托
         */
        $('#'+pageId+'_btn_close').click(function(){
        	if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
        	for(var i=0; i<selArr.length; i++){
        		if(selArr[i].entrustStatusStr == '待接收'){
            		Joyin.error('不能对待接收的数据进行关闭操作');
            		return;
            	}
            	if(selArr[i].entrustStatusStr == '已关闭'){
            		Joyin.error('已关闭状态的数据不允许再次关闭');
            		return;
            	}
        	}
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"),"关闭");
                return;
            }
            //	画面提示message【ETR_C_0002：请确认是否要关闭委托？】																
            var object = {selectedItem: selArr, operateType:"close"};
            Joyin.ajaxJson(basePath + 'invest/entrust/etrfund/EtrFund001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("ETR_C_0002"), function(){
                	Joyin.ajaxJson(basePath + 'invest/entrust/etrfund/EtrFund001/close', object, function(){
                		
                		table.reload();
                	});
                });
            });
        })
        
        /** 
         * 撤销委托
         */
        $('#'+pageId+'_btn_revoke').click(function(){
        	if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
        	
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }//BUSS_COM_C_0032
            var object = {selectedItem: selArr, operateType:"recall"};
            object.assetType11 = selArr[0].assetType11;
            Joyin.ajaxJson(basePath + 'invest/entrust/etrfund/EtrFund001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("BUSS_COM_C_0032","撤销"), function(){
                	Joyin.ajaxJson(basePath + 'invest/entrust/etrfund/EtrFund001/recall', object, function(result){
                    	
                		table.reload();
                    });
                });
            });
        })
        // 多对多导入
        $('#'+pageId+'_btn_import').click(function() {
                Joyin.uploadFile({pageId:'EtrFund003MTM'},function(fileName) {
                            Joyin.ajax(basePath + 'invest/entrust/etrfund/EtrFund003/checkImportData', {fileName: fileName}, function(checkResult) {
                                // 若是模板数据大于100条，提示重新导入，若是小于100条，进入多对多指令界面
                                if(!checkResult.data.errorMsg){
                                setTimeout( function () {
                                    Joyin.confirm(checkResult.data.confirmMsg,function() {
                                        var result = {};
                                        result.tempList = checkResult.data.correctData;
                                        result.operateType = "import";
                                        result.pageModel='';
                                        Joyin.dialog(JoyinUtil.getLabel("EtrFund003.edit"),
                                            JoyinUtil.getRealPath("invest/entrust/etrfund/EtrFund003/init"),
                                            "98%", "95%", result,
                                            function(){table.reload();});
                                    });
                                },1000);
                                }else{
                                    Joyin.alert(checkResult.data.errorMsg);
                                }
                            });
                },function() {
                    table.reload();
                });
           
        });
        //是否展示资产四五级分类
        if(IS_SHOW_TYPE == '01'){
        	$('#assetType11Div').show();
        }else{
        	$('#assetType11Div').hide();
        }

        // add by etliu 20210501 银行间场内场外拆分 start
        //是否展示交易所相关内容
        if(pageId == 'EtrFundEx001' || pageId == 'EtrFund001'){
            $('#exContentDiv').show();
        } else {
            $('#exContentDiv').hide();
        }
        // add by etliu 20210501 银行间场内场外拆分 end
    });
}());