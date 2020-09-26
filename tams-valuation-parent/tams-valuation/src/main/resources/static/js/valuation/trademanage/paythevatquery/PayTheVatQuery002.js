var table;
var table2;
$(function () {
    /**
     * 定义全局变量，修改金额
     */
    var changeAmt = 0;
    /**
     * 纳税截止日
     */
    var payment1="";
    /**
     * 纳税日期
     */
    var payment2="";
    
    /**
     * 是否变更数据后未查询就提交 标志位，默认true
     */
    var submitFlag = true;
    
    //当前是否包含流程
    var workflowId = $("#workflowId").val();
    
    /**
     * 增值税缴税/退税显示切换
     */
    var showType = function(){
        var taxidenType = $("#taxidenType").val();
        // 若为退税
        if(taxidenType == 'C13'){
            $("#drawback").show();
            $("#drawback2").show();
            $("#paytax").hide();
            $("#paytax2").hide();
        } else {// 隐藏并且置空
            $("#drawback").hide();
            $("#drawback2").hide();
            $("#paytax").show();
            $("#paytax2").show();
        }
    }
    
    /**
     * 更改各输入框值绑定事件
     */
    var linkBind=function() {
        // 修改类型
        $("#taxidenType").change(function(){
            showType();
            // 标志位 置为false
            submitFlag = false;
        });
        // 改变账套代码的值，标志位值为false
        $("#booksetId").change(function(){
            // 标志位 置为false
            submitFlag = false;
        });
        // 改变缴税截止日期的值，标志位值为false
        $("#payTaxMDate").change(function(){
            // 标志位 置为false
            submitFlag = false;
        });
        // 改变退税截止日期的值，标志位值为false
        $("#refundTaxMDate").change(function(){
            // 标志位 置为false
            submitFlag = false;
        });
    };
    /**
     * 获取页面查询参数
     */
    
    var getSearchParams = function() {        
        return {
            booksetId: $.trim($('#booksetId').val()),
            taxidenType: $.trim($('#taxidenType').val()),
            payTaxVDate: $.trim($('#payTaxVDate').val()),
            payTaxMDate: payment1,//缴税截止日
            refundTaxVDate: $.trim($('#refundTaxVDate').val()),
            refundTaxMDate: payment1,//退税截止日   
            payment1:payment1,
            workflowId:workflowId
        }
    };
    
    /**
     * 定义数据表格--缴税
     */
    table=$("#dataTable").joyintable({
        ajax: {
            url: basePath + 'valuation/trademanage/paythevatquery/PayTheVatQuery002/search',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        page:false,
        rowno: true,
        cellMinWidth : 100,
        cols: [
           {field:'booksetId',width:150, title: '账套代码', sort: true},
           {field:'booksetName',width:150, title: '账套名称', sort: true},     
           {field:'taxStartDate',width:150, title: '缴税开始日', sort: true},
           {field:'acctSubjectId',width:150, title: '核算主体代码', sort: true,visible:false},
           {field:'acctSubjectType',width:150, title: '核算主体类别', sort: true,visible:false},
           {field:'standardCcy',width:150, title: '本位币', sort: true,visible:false},
           {field:'taxRefundStartDate',width:150, title: '退税开始日', sort: true,visible:false},
           {field:'interestIncome',width:150, title: '利息收入', type:"money", sort: true},
           {field:'dividendIncome',width:150, title: '红利收入', type:"money", sort: true},
           {field:'difbetIncome',width:150, title: '价差收入', type:"money", sort: true},
           {field:'appreciationTax',width:150, title: '增值税', type:"money", sort: true},
           {field:'constructionTax',width:150, title: '城建税附加', type:"money", sort: true},
           {field:'educationTax',width:150, title: '教育税附加', type:"money", sort: true},
           {field:'placeeducationTax',width:150, title: '地方教育费附加', type:"money", sort: true},
           {field:'additionalTax',width:150, title: '增值税附加', type:"money", sort: true},
           {field:'operation', title: 'SysCommon.operation', width:80, type:'action', fixed: 'right',
               buttons: [
                   {text: '&#xe640;',clas: 'delt',title: '删除'}]
           }
        ],
        loadonce:true
    });
    
    /**
     * 定义数据表格--退税
     */
     table2=$("#dataTable2").joyintable({
        ajax: {
            url: '/valuation/trademanage/paythevatquery/PayTheVatQuery002/search',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        page:false,
        cellMinWidth : 100,
        rowno: true,
        loadonce:true,
        cols: [
           {field:'booksetId',width:150, title: '账套代码', sort: true},
           {field:'booksetName',width:150, title: '账套名称', sort: true},     
           {field:'taxStartDate',width:150, title: '缴税开始日', sort: true, visible:false},
           {field:'acctSubjectId',width:150, title: '核算主体代码', sort: true,visible:false},
           {field:'acctSubjectType',width:150, title: '核算主体类别', sort: true,visible:false},
           {field:'standardCcy',width:150, title: '本位币', sort: true,visible:false},
           {field:'taxRefundStartDate',width:150, title: '退税开始日', sort: true},
           {field:'interestIncome',width:150, title: '利息收入', type:"money", sort: true, edit: true, editOption:{type: 'money', pattern: '#.00'}},
           {field:'dividendIncome',width:150, title: '红利收入', type:"money", sort: true, edit: true, editOption:{type: 'money', pattern: '#.00'}},
           {field:'difbetIncome',width:150, title: '价差收入', type:"money", sort: true, edit: true, editOption:{type: 'money', pattern: '#.00'}},
           {field:'appreciationTax',width:150, title: '增值税', type:"money", sort: true},
           {field:'constructionTax',width:150, title: '城建税附加', type:"money", sort: true, edit: true, editOption:{type: 'money', pattern: '#.00'}},
           {field:'educationTax',width:150, title: '教育税附加', type:"money", sort: true, edit: true, editOption:{type: 'money', pattern: '#.00'}},
           {field:'placeeducationTax',width:150, title: '地方教育费附加', type:"money", sort: true, edit: true, editOption:{type: 'money', pattern: '#.00'}},
           {field:'additionalTax',width:150, title: '增值税附加', type:"money", sort: true},
           {field:'operation', title: 'SysCommon.operation', width:80, type:'action', fixed: 'right',
               buttons: [
                   {text: '&#xe640;',clas: 'delt',title: '删除'}]
           }
        ],
        editCell: function(value, data, field, cell, column){
            var listData = table2.getAllRows();
            // 获取最后一行位置下标
            var totalIndex = listData.length-1;
            // 获取最后一行，即汇总行
            var lastRow = table2.getRow(totalIndex);
            // 获取对应改变的值
            var amount = parseFloat(value)-changeAmt;
            // 若改的列为利息收入、红利收入、价差收入时，增值税对应值也需对应修改
            if(field == "interestIncome" || field == "dividendIncome" ||field == "difbetIncome"){
                // 当前行的增值税变更
                data.appreciationTax = parseFloat(data.appreciationTax) + amount
                // 汇总行对应的增值税变更
                lastRow.appreciationTax = parseFloat(lastRow.appreciationTax) + amount
            }
            // 否则，改的则为其他三项，增值税附加对应的值修改
            else{
                // 当前行的增值税附加变更
                data.additionalTax = parseFloat(data.additionalTax) + amount
                // 汇总行对应的增值税附加变更
                lastRow.additionalTax = parseFloat(lastRow.additionalTax) + amount
            }
            // 对应汇总行当前列重新赋值
            lastRow[field] = parseFloat(lastRow[field])+amount;
            // 改变当前行的值
            table2.renderRow(data.LAY_TABLE_INDEX,data);
            // 改变最后一行汇总行的值
            table2.renderRow(totalIndex,lastRow);
            return true;
        },
        beforeEditCell: function(preVal, field, rowData){
            var listData = table2.getAllRows();
            // 修改前的金额
            changeAmt = parseFloat(rowData[field]);
            if(rowData.LAY_TABLE_INDEX == listData.length-1){
                Joyin.error("汇总行不可直接编辑");
                return false;
            }
            return true;
        }
    });
    
    //列表删除
    table.bind('click', '.delt', function(data, index) {
        var object = {};
        object.booksetId = data.booksetId;       
        var message = "是否删除["+data.booksetId+"]的账套";
        Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
            table.removeRow(table.getRow(index));
        });       
     });
      
    //列表删除
    table2.bind('click', '.delt', function(data, index) {
        var object = {};
        object.booksetId = data.booksetId;       
        var message = "是否删除["+data.booksetId+"]的账套";
        Joyin.confirm(JoyinUtil.getMessage(message,JoyinUtil.getLabel("SysCommon.delete")), function(){
            table2.removeRow(table2.getRow(index));
        });       
    });
    
    
    /**
     * 画面加载完成
     */
    
    $(function() {
        // 判断是否是流程进入的
        if(workflowId){
            table.load();
            //获取纳税日的值，并赋给payment2
            if ($("#taxidenType").val()=='C12'){
                // 缴税显示缴税开始日
                table.showColumns([{field:'taxStartDate', visible: true}, {field:'taxRefundStartDate',visible: false}]);
            }
            else{
                // 退税显示退税开始日
                table.showColumns([{field:'taxRefundStartDate', visible: true}, {field:'taxStartDate',visible: false}]);
            }
        }
        showType();
        // 绑定按钮事件
        $('#PayTheVatQuery002_btn_search').click(function() { 
            //获取纳税截止日的值，并赋给payment
            if ($("#taxidenType").val()=='C12'){
                payment1 = $.trim($('#payTaxMDate').val());
            }else{
                payment1 = $.trim($('#refundTaxMDate').val());
            }
            //为空时给提示
            if(payment1==''||payment1==null){
                  Joyin.error("缴税/退税截止日不可为空",2);
            }else{
                  
                  if ($("#taxidenType").val()=='C12'){
                      // 加载缴税表格
                      $("#table1").show();
                      $("#table2").hide();
                      table.load();
                  }
                  else{
                      // 加载退税表格
                      $("#table2").show();
                      $("#table1").hide();
                      table2.load();
                  }
                  // 查询后标志位 置为true
                  submitFlag = true;
            }
        });
        //绑定联动
        linkBind();
        //绑定删除事件
        
        //绑定提交事件
        $('#PayTheVatQuery002_btn_submit').click(function(){
            // 先判断是否页面元素有变更未查询
            if(submitFlag){
                var object = $.serializeToObject('#submitForm');
                // booksetId 输入框序列化后为数组，需重新赋值为string类型
                if($('#booksetId').val()){
                    object.booksetId = $.trim($('#booksetId').val());
                }
                //获取纳税日的值，并赋给payment2
                if ($("#taxidenType").val()=='C12'){
                    payment2 = $.trim($('#payTaxVDate').val());
                    payment1 = $.trim($('#payTaxMDate').val());
                    // 列表明细
                    object.listpayTheVatQuery002TableDto = table.getAllRows();
                }else{
                    payment2 = $.trim($('#refundTaxVDate').val());
                    payment1 = $.trim($('#refundTaxMDate').val());
                    // 列表明细
                    object.listpayTheVatQuery002TableDto = table2.getAllRows();
                }
                object.payment2 = payment2;          
                //获取纳税截止日的值，并赋给payment
                object.payment1 = payment1;
                object.pageModel = pageModel;
                Joyin.ajaxJson(basePath + 'valuation/trademanage/paythevatquery/PayTheVatQuery002/check', object, function(result) {
                    WorkFlow.doSubmitList(basePath + 'valuation/trademanage/paythevatquery/PayTheVatQuery002/docommit', 
                            object, "PayTheVatQuery002_btn_submit",null,function(data){
                            var index = parent.layer.getFrameIndex(window.name);
                             parent.layer.close(index);
                    });
                });
            }
            else{
                Joyin.error("页面元素已变更,请重新查询。");
            }
        });
    });
    
});



