$(function () {

    /**
     * 定义数据表格
     */
	var table=$("#ValManualVoucher002_table").joyintable({
	    ajax: {
            url: basePath + 'valuation/accounadjustmanage/manualvoucher/ValManualVoucher002/searchList',
            type: 'POST',
            data: {seqno : $("#seqno").val()}
        },
        searchOnInit: true,
        checkbox: false,
		cols: [
		   {field:'subjectNo', title: '科目号',width:150},
           {field:'subjectName', title: '科目名称',width:200},
           {field:'detailSubjectNo', title: '科目尾号',width:150},
           {field:'detailSubjectName', title: '科目尾号名称',width:200},
           {field:'cdFlag', title: '借贷',width:80},
           {field:'ccy', title: '原币币种',width:100},
           {field:'happenAmt', type:"money", title: '原币金额',width:120},
           {field:'happenNumber', type:"number", title: '数量',width:80},
           {field:'bCcy', title: '记账币种',width:100},
           {field:'bHappenAmt', type:"money", title: '本币金额',width:120}
		],
		loadonce:true
	});

	//页面全部加载完毕后
        $(document).ready(function(){
        	// 审核页面加载时
        	// 业务类型为债券时，显示投资目的
            if ($("#finprodType").val() == 'F01'){
            	$("#invAimDiv").show();
            }
            else if ($("#finprodType").val() == 'F17' && $("#invAim").val()){
            	$("#invAimDiv").show();
            }
            else{
            	$("#invAimDiv").hide();
            }

            if($("#workflowId").val()=='' || !$("#workflowId").val()){
                $("#seqnoDiv").hide();
            }
//            }else{
//                $("#seqnoDiv").show();
//            }
        });
	
});