$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            bookDateBegin: $('#bookDate').startDate(),
            bookDateEnd: $('#bookDate').endDate(),
            booksetId: $.trim($('#booksetId').val()),
            subjectNo: $('#subjectNo').val(),     
            subjectLevel: $('#subjectLevel').val(), 
            ccy: $('#ccy').val(),
            // GZHSXT-1608 delete by QuJianZhong 20181214 start
            // showSubject:$('#showSubject').val(),  
            // GZHSXT-1608 delete by QuJianZhong 20181214 end
            issueType:$('#issueType').val()
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
        dblclick:function(table, data) {
            var issueType=$('#issueType').val();
            if(issueType=='02'){
                Joyin.dialog(JoyinUtil.getLabel("查看账套号："+data.booksetId),
                        JoyinUtil.getRealPath("/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery002/init"),
                        "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE,
                    booksetId:data.booksetId,balanceDateFmt : data.balanceDateFmt,subjectNo:data.subjectNo,oCcy:data.oCcy},
                        function(){});
            }else{
                Joyin.dialog(JoyinUtil.getLabel("查看科目号："+data.subjectNo),
                        JoyinUtil.getRealPath("/valuation/financemanage/accountbook/valsubjectbalancequery/ValSubjectBalanceQuery003/init"),
                        "90%", "90%", {pageModel:PAGE_MODEL_REFERENCE, balanceDateFmt : data.balanceDateFmt,
                    subjectNo1:data.subjectNo,oCcy:data.oCcy},
                        function(){});
            }
        },
		checkbox: true,
		cellMinWidth : 100,
		cols: [
		       {field:'balanceDateFmt', title: '日期', sortField:'balanceDate',width:120, sort: true},
               {field:'booksetId', title: '账套代码',width:150, sort: true},
               {field:'bookSetName', title: '账套名称',width:200, sort: true},
    		   {field:'subjectNo', title: '科目号',width:120, sort: true},
               {field:'subjectName', title: '科目名称',width:200, sort: true},
               {field:'oCcy', title: '原币币种',width:90, sort: true,visible:false},
               {field:'oCcyFmt', title: '原币币种', sortField:'oCcy',width:130, sort: true},
               {field:'bCcy', title: '记账币种',width:90, sort: true,visible:false},
               {field:'bCcyFmt', title: '记账币种', sortField:'bCcy',width:130, sort: true},
               {field:'oAmt', type:"money", title: '原币余额',width:150, sort: true},
//               {field:'', type:"money", title: '汇率',width:150, sort: true},
               {field:'bAmt', type:"money", title: '本币余额',width:150, sort: true},
               {field:'balFlag', title: '借/贷',width:80, sort: true,visible:false},
               {field:'balFlagFmt', title: '借/贷', sortField:'balFlag',width:80, sort: true},
               {field:'numAmt', type:"number", title: '数量',width:120, sort: true}
	
		],
	});
	
	var exportExcel = function() {
	    var selArr;
        var datas;
	    if (table != null && table != '') {
            selArr = table.getAllCheckedRows();
            datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };
    
    var booksetIdChange = function () {
        var booksetType = $('#booksetType').val();
        var object = {booksetTypes:booksetType};
        Joyin.ajaxJson(basePath + '/valuation/accounadjustmanage/voucherquery/ValVoucherQuery001/findBooksetIds', object, function(result) {
        	$("#booksetId").empty();
        	$.each(result, function(index, item) {                   
        		$('#booksetId').append(new Option(item.text,item.id));
            });
		}); 
    };
	/**
     * 画面加载完成
     */
    $(function() {
//    	$("#bookDate").val(initDateRange);
        
        
//        $('#bookDate').val(sysDate+' - '+sysDate);
//    	$("#ccy").val(initCCY).trigger('change');
        // 绑定按钮事件
        $('#ValSubjectBalanceQuery001_btn_search').click(function() {
//        	if ($('#bookDate').startDate() == '' || $('#bookDate').endDate() == '') {
//        		Joyin.error('请输入日期！');
//        		return;
//        	}
//        	if ($('#ccy').val() == '') {
//        		Joyin.error('请输入币种！');
//        		return;
//        	}
            table.load();
            // GZHSXT-1608 delete by QuJianZhong 20181214 start
//        	var showSubject = $('#showSubject').val()        
//			  if (showSubject == "N") {
//				table.showColumns([ {
//					field : 'subjectNo',
//					visible : false
//				}, {
//					field : 'subjectName',
//					visible : false
//				}, ]);
//			} else {
//				table.showColumns([ {
//					field : 'subjectNo',
//					visible : true
//				}, {
//					field : 'subjectName',
//					visible : true
//				}, ]);
//			}
           // GZHSXT-1608 delete by QuJianZhong 20181214 end
        });
        $('#ValSubjectBalanceQuery001_btn_export').click(function() {
            exportExcel();
        });
        
        $("#booksetId").on('change',function(){
            // 不知道调用何种方法，框架没有说明，将js从框架中拿出
            // 若有值，默认为 逐账套展示
            if($("#booksetId").val()){
                $("#issueType").val("02");
                $("#issueType").next().addClass('disabled').find('li').off('click');
                $("#issueType").next().find('li').removeClass('laytable-checkbox-selected');
                $("#issueType").next().find('li').each(function(index,n){
                    if($(this).data('value') == '02'){
                        $(this).addClass('laytable-checkbox-selected');
                    }
                });
            }else{
                $("#issueType").next().removeClass('disabled').find('li').off('click').bind('click',function(){
                    if(!$(this).hasClass('laytable-checkbox-selected')){
                        $("#issueType").next().find('li').removeClass('laytable-checkbox-selected');
                        $(this).addClass('laytable-checkbox-selected');
                        $("#issueType").val($(this).data('value')).trigger('change');
                    }
                });
            }
        })
        $('#booksetType').change(function(){
        	booksetIdChange();
        });
    });

	
});