$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	booksetId: $.trim($('#booksetId').val()),
        	bokDetailId: $.trim($('#bokDetailId').val()),
        	busiId: $.trim($('#busiId').val()),
        	subjectNo: $.trim($('#subjectNo').val()),
        	bookDateStart:$.trim($('#bookDate').startDate()),
        	bookDateEnd:$.trim($('#bookDate').endDate())
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
        
        checkbox: true,
        cellMinWidth : 100,
        cols: [
               {field:'booksetId', width:150, title: '账套', sort: true },
               {field:'tableId', width:120, title: '场景代码',align:'right', sort: true},
               {field:'tradeId', width:160, title: '交易编号' , sort: true},
               {field:'busiId', width:150,  title: '业务明细代码' , sort: true},
               {field:'taskId', width:120, title: '任务代码',align:'right', sort: true},
               {field:'bookDate', width:120, title: '入账日期', sort: true},
               {field:'onwayDate', width:120, title: '在途日期', sort: true},
               {field:'baseSubjectNature', width:150, title: '基础科目性质',align:'right', sort: true},
               {field:'bokDetailId', width:240, title: '账务明细代码', sort: true},
               {field:'branch', width:120, title: '分支序号',align:'right', sort: true},
               {field:'subjectNo', width:120, title: '科目号',align:'right', sort: true},
               {field:'detailSubjectNo', width:240, title: '四级科目号', sort: true},
               {field:'cdFlag', width:120, title: '借贷方向',align:'right', sort: true},
//               {field:'oCcy', width:160,  title: '原币' , sort: true},
    		   {field:'oCcy', title: '原币',width:120,sort: true,visible:false},
               {field:'oCcyFmt', sortField: "oCcy", title: '原币',width:120,sort: true},
               {field:'oHappenAmt',type:"money", width:150, title: '原币发生额', sort: true},
//               {field:'bCcy', width:160, title: '本位币', sort: true},
    		   {field:'bCcy', title: '本位币',width:120,sort: true,visible:false},
               {field:'bCcyFmt', sortField: "bCcy", title: '本位币',width:120,sort: true},
               {field:'bHappenAmt',type:"money", width:150, title: '本位币发生额', sort: true},
               {field:'happenNumber',type:"number",digit: 4,  width:150, title: '发生数量',align:'right', sort: true},
               {field:'mdate', width:120, title: '结息日', sort: true},
               {field:'bookId', width:240, title: '凭证编号' , sort: true},
               {field:'bookRemapk', width:220, title: '凭证说明', sort: true },
               {field:'offsetFlag', width:120, title: '冲正标识',align:'right', sort: true},
               {field:'offsetDate', width:120, type:'date', title: '冲正日期', sort: true },
               {field:'rSeqno', width:150, type:'money', title: '反向流水号' , sort: true},
               {field:'bookType', width:120, title: '凭证类型',align:'right', sort: true},
               {field:'bookOrder', width:120, title: '处理序列',align:'right', sort: true},
               {field:'balFlagHappenDate', width:160, title: '余额方向（发生日）',align:'right', sort: true},
               {field:'oAmtHappenDate',type:"money", width:180, title: '原币余额（发生日）',align:'right', sort: true},
               {field:'bAmtHappenDate',type:"money", width:190, title: '本位币余额（发生日）',align:'right', sort: true},
               {field:'numAmtHappenDate',type:"number",digit: 4, width:160, title: '数量余额（发生日）',align:'right', sort: true},
               {field:'balFlagBookDate', width:160, title: '余额方向（入账日）',align:'right', sort: true},
               {field:'oAmtBookDate',type:"money", width:180, title: '原币余额（入账日）',align:'right', sort: true},
               {field:'bAmtBookDate',type:"money", width:190,title: '本位币余额（入账日）',align:'right', sort: true},
               {field:'numAmtBookDate',type:"number",digit: 4, width:160,  title: '数量余额（入账日）',align:'right', sort: true},
               {field:'exDate', width:120, title: '换汇日期', sort: true},
               {field:'exRate',type:"number",digit:14, width:150, title: '折算汇率',align:'right', sort: true}
           ],
    });
    
    var dateChange = function(){
    	var dateStart = $('#bookDate').startDate();
    	var dateEnd = $('#bookDate').endDate();
        if(new Date(dateEnd)-new Date(dateStart)>3*24*60*60*1000){
            layer.alert('入账日期区间不能大于三天！');
            $('#bookDate').val('');
            return ;
        }
    }
    
    /**
     * 导出
     */
    var exportExcel = function() {
        if (table) {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length === 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    };

    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#SelectBokdetailCurrent001_btn_search').click(function() {
            table.load();
        });
        
        $('#bookDate').change(function(){
            dateChange();
        });
        
        // 绑定导出事件
        $('#SelectBokdetailCurrent001_btn_export').click(function() {
            exportExcel();
        });
    });
});
