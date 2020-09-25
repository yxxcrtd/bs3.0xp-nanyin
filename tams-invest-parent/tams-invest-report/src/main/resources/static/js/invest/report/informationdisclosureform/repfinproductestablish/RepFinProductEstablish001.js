(function(){
	
	var dataTable1;
    var dataTable;
    var index;
	
    /**
     * 获取查询参数
     */
    //var searchform = $.serializeToObject('#searchForm');
    //$('#vDate').startDate() $('#mDate') 
    var getSearchParams = function (target){
    	if(index==0 || index==undefined){
    		return {
        		finprodIdList : $('.finprodIdList').val().join(','),
        		startVDate:$('.vDate').startDate(),
        		endVDate:$('.vDate').endDate(),
        		startMDate:$('.mDate').startDate(),
        		endMDate:$('.mDate').endDate(),
        		saleChannel:$('.saleChannel').val(),
        		fileType:$('.fileType').val(),
        		department:$('.department').val()
        		
        	};
    	}else{
    		return {
    			finprodIdList : $('.finprodIdList1').val().join(','),
        		startVDate:$('.vDate1').startDate(),
        		endVDate:$('.vDate1').endDate(),
        		startMDate:$('.mDate1').startDate(),
        		endMDate:$('.mDate1').endDate(),
        		saleChannel:$('.saleChannel1').val(),
        		fileType:$('.fileType1').val(),
        		department:$('.department1').val()
        	};
    	}
    };
    
    /**
     * 创建表格
     */
    var createTable = function(tableId, dataUrl) {
    	var table = $(tableId);
    	return table.joyintable({
            ajax: {
                url: JoyinUtil.getRealPath(dataUrl),
                type: 'POST',
                data: function() {
                    return getSearchParams(table.data('target'));
                }
            },
            cols:[
                  {field:'finprodMarketId', title: 'RepFinProductEstablish001.productId',sortField:'FINPROD_MARKET_ID'}
                  ,{field:'finprodName', title: 'RepFinProductEstablish001.productName',sortField:'FINPROD_NAME'}
                  ,{field:'vdate', title: 'RepFinProductEstablish001.valueDate',  type:'date'}
                  ,{field:'mdate', title: 'RepFinProductEstablish001.expiryDate',  type:'date'}
                  ,{field:'portfolioId', title: 'RepFinProductEstablish001.productSeries',sortField:'PORTFOLIO_ID'}
                  ,{field:'finprodId', visible: false}
                  ,{field:'branch', visible: false}
                  ,{field:'pFinprodId', visible: false}
                  ,{field:'fileType', visible: false}
                  ,{field:'revealFileId', visible: false}
                  ,{field:'department', visible: false}
              ],
        });
    };
    
    
  
    var exportExcel = function(){       
        Joyin.exportExcel('export', getSearchParams());
    };
    var exportExcel1 = function(){       
        Joyin.exportExcel(JoyinUtil.getRealPath("invest/report/informationdisclosureform/repfinproductexpiration/RepFinProductExpiration001/export"), getSearchParams());
    };
    $(function () {
    	//导出
    	$('.btn-export').click(function(){
    		if(index==0 || index==undefined){
    			exportExcel();
    		}
    		else{
    			exportExcel1();
    		}
    	})
    	// 初始化表格
    	dataTable1 = createTable('#dataTable1',  'invest/report/informationdisclosureform/repfinproductexpiration/RepFinProductExpiration001/findPage');
    	dataTable = createTable('#dataTable', 'invest/report/informationdisclosureform/repfinproductestablish/RepFinProductEstablish001/findPage');
    	
    	//tab切换监听
    	layui.use('element', function(){
    		  var element = layui.element;
    		  //一些事件监听
    		  element.on('tab(docDemoTabBrief)', function(data){
    			index = data.index;
    			//$('.btn-export').css('display','none');
    		  });
    		});
    	
    	//查询按钮
    	$('.btn-search').click(function(){
    		if(index==0 || index==undefined){
    			var strError = '';
    			if($('.finprodIdList').val() == '' || $('.finprodIdList').val() == null){
    				strError = "1.请输入"+JoyinUtil.getLabel("RepFinProductExpiration001.productSeries")+"<br>";
    			}
    			if($('.fileType').val() == '' || $('.fileType').val() == null){
    				if(strError != '' && strError != null){
    					strError = strError + "2.请输入"+JoyinUtil.getLabel("RepFinProductEstablish001.documentType");
    				}
    				else{
    					strError = strError + "1.请输入"+JoyinUtil.getLabel("RepFinProductEstablish001.documentType");
    				}
        			//Joyin.error("请输入"+JoyinUtil.getLabel("RepFinProductEstablish001.documentType"));
        		}
    			if(strError != '' && strError != null){
    				Joyin.error(strError);
    				return;
    			}
    			dataTable.load();
    		}else{
    			var strError = '';
    			if($('.finprodIdList1').val() == '' || $('.finprodIdList1').val() == null){
    				strError = "1.请输入"+JoyinUtil.getLabel("RepFinProductExpiration001.productSeries")+"<br>";
    			}
    			if($('.fileType1').val() == '' || $('.fileType1').val() == null){
    				if(strError != '' && strError != null){
    					strError = strError + "2.请输入"+JoyinUtil.getLabel("RepFinProductEstablish001.documentType");
    				}
    				else{
    					strError = strError + "1.请输入"+JoyinUtil.getLabel("RepFinProductEstablish001.documentType");
    				}
        			//Joyin.error("请输入"+JoyinUtil.getLabel("RepFinProductEstablish001.documentType"));
        		}
    			if(strError != '' && strError != null){
    				Joyin.error(strError);
    				return;
    			}
    			dataTable1.load();
    		}
    	});
    	//产品成立生成公告
        $('#RepFinProductEstablish001_btn_createanno').click(function(){
        	if(dataTable!=null && dataTable!=''){
                var selArr = dataTable.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr};
          //披露文档代码校验
            var errorMessage='';
            for(var i=0; i<selArr.length; i++){
            	if(selArr[i].revealFileId==''|| selArr[i].revealFileId==null){
            		errorMessage = errorMessage+JoyinUtil.getMessage("REP_E_0001",selArr[i].finprodMarketId)+"<br>";
            	}
            }
            if(errorMessage != '' && errorMessage != null){
            	Joyin.error(errorMessage);
            	return;
            }
            var obj ={selectedItem:selArr};
            //生成公告
            Joyin.exportExcel(basePath+"invest/report/informationdisclosureform/repfinproductestablish/RepFinProductEstablish001/generateBulletin", {dtoStr:JSON.stringify(obj)});
        	//Joyin.ajaxJson(basePath + 'invest/report/informationdisclosureform/repfinproductestablish/RepFinProductEstablish001/generateBulletin', object, function(result) {});
        })
        
        // RepFinProductExpiration001绑定按钮事件
        /*$('#RepFinProductExpiration001_btn_export').click(function() {
                exportExcel();
        });*/
        //产品到期生成公告
        $('#RepFinProductExpiration001_btn_createanno').click(function() {
            if(dataTable1!=null && dataTable1!=''){
                var selArr = dataTable1.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr};
            //披露文档代码校验
            var errorMessage='';
            for(var i=0; i<selArr.length; i++){
            	if(selArr[i].revealFileId==''|| selArr[i].revealFileId==null){
            		errorMessage = errorMessage+JoyinUtil.getMessage("REP_E_0001",selArr[i].finprodMarketId)+"<br>";
            	}
            	if(errorMessage != '' && errorMessage != null){
                	Joyin.error(errorMessage);
                	return;
                }
            }
            //生成公告
            var obj ={selectedItem:selArr};
            //生成公告
            Joyin.exportExcel(basePath+"invest/report/informationdisclosureform/repfinproductestablish/RepFinProductEstablish001/generateBulletin", {dtoStr:JSON.stringify(obj)});
        	//Joyin.ajaxJson(basePath + 'invest/report/informationdisclosureform/repfinproductexpiration/RepFinProductExpiration001/generateBulletin', object, function(result) {});
        })
    });
}());