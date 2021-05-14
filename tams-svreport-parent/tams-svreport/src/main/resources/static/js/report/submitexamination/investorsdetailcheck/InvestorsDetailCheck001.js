(function(){
    var getPageParams = function() {
        return {
            checkDateStart :$('#checkDate').val()==''?null: $('#checkDate').startDate(),
            checkDateEnd :$('#checkDate').val()==''?null: $('#checkDate').endDate(),
            prod : $('#prod').val()==null?null:$('#prod').val(),
            operateMode : $('#operateMode').val()==null?null:$('#operateMode').val().join(","),
            checkResult : $('#checkResult').val()==null?null:$('#checkResult').val().join(","),
            vdateStart :$('#vdate').val()==''?null: $('#vdate').startDate(),
            vdateEnd :$('#vdate').val()==''?null: $('#vdate').endDate(),
            mdateStart : $('#mdate').val()==''?null:$('#mdate').startDate(),
            mdateEnd : $('#mdate').val()==''?null:$('#mdate').endDate()
        }
    };
    
    var table = $('#dataTable').joyintable({
        ajax: {
            url: 'searchTable',
            type: 'POST',
            data: function(d) {
                return getPageParams();
            }
        },
        rowno: false, // 显示行号
        searchOnInit: false, //初始化的时候就请求后台数据
        cols: [
            {title:'检查日期', type:'date', field:'equityConfDate', width:'8%'},
            {title:'检查结果',field:'checkTotalStr', width:'8%'},
            {title:'产品代码',field:'account', sortable:false, width:'8%'},
            {title:'产品登记编码',field:'prodRegistCode', sortable:false, width:'8%'},
            {title:'产品名称',field:'prodName', sortable:false, width:'8%'},
            {title:'产品运作模式',field:'operateModeStr', sortable:false, width:'8%'},
            {title:'是否代销',field:'isConsignStr', sortable:false, width:'8%'},
            {title:'产品成立日',field:'vdate', type:'date', sortable:false, width:'8%'},
            {title:'产品实际到期日',field:'mdate', type:'date', sortable:false, width:'8%'},
            {title:'检查—募集金额',field:'checkMjStr', sortable:false , width:'8%'},
            {title:'检查—申购金额',field:'checkSgamtStr', sortable:false, width:'8%'},
            {title:'检查—申购份额',field:'checkSgqtyStr', sortable:false, width:'8%'},
            {title:'检查—赎回份额',field:'checkShqtyStr', sortable:false, width:'8%'},
            {title:'检查—赎回金额',field:'checkShamtStr', sortable:false, width:'8%'}]
    });

    $(function(){
        //查询按钮绑定事件
        $('#InvestorsDetailCheck001_btn_search').click(function(){
            table.load();
        });

    });
}());