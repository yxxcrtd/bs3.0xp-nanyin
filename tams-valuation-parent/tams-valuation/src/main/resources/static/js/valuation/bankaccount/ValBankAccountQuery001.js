$(function () {
    /**
     * 获取账务明细查询参数
     */
    var getAccountDetailsSearchParams = function() {
        return {
            subjectName: $.trim($('#subjectName').val()),//科目名称
            busiType: $.trim($('#busiType').val()),//业务类别     
            selectDateBegin: $('#selectDate').startDate(),
            selectDateEnd: $('#selectDate').endDate()
        }
    };
    
    /**
     * 定义账务明细数据表格
     */
    var accountDetailsTable=$("#accountDetails-table").joyintable({
        ajax: {
            url: 'findAccountDetailsPage',
            type: 'POST',
            data: function() {
                return getAccountDetailsSearchParams();
            }
    
        },
        cols: [
           {field:'subjectName', title: '科目名称',sortField:'subjectNameCode',width:160},
           {field:'happenDate', title: '发生日期',width:160},
           {field:'ccy', title: '币种',sortField:'ccyCode',width:120},         
           {field:'cdFlag',title: '借贷方向',sortField:'cdFlagCode',width:120},
           {field:'dAmt', type:"money", title: '借方金额',width:160},
           {field:'cAmt', type:"money", title: '贷方金额',width:120},
           {field:'balFlag', title: '余额方向',sortField:'balFlagCode',width:140},
           {field:'oAmt', title: '科目余额',width:160},
           {field:'busiType', title: '业务类别',sortField:'busiTypeCode',width:160}
        ],
    });
    
    /**
     * 事件绑定：账务明细查询
     */
    var accountDetailsBind = function() {
        
        /** 事件绑定: 筛选按钮 */
        $("#ValBankAccountQuery001_btn_filter").click(function(event){
            event.stopPropagation();// 阻止事件冒泡
            $("#c_accountDetailsForm").toggle();
        });
        
        /** 事件绑定: 置空按钮 */
        $("#ValBankAccountQuery001_btn_reset").click(function(event){
            reset( '#accountDetailsForm', accountDetailsTable);
        });
        
        /** 事件绑定: 账务明细-查询 */
        $("#ValBankAccountQuery001_btn_search").click(function(){
            
            var subjectName= $.trim($('#subjectName').val());//科目名称
            var busiType= $.trim($('#busiType').val());//业务类别     
            var selectDateBegin= $('#selectDate').startDate();
            var selectDateEnd= $('#selectDate').endDate();
            var sdate = $('#selectDate').val();
            if(subjectName==''){
                Joyin.error(JoyinUtil.getMessage("科目名称不能为空"));  
                return;
            }
            else if(sdate==''){
                Joyin.error(JoyinUtil.getMessage("查询日期不能为空"));  
                return;
            }
            else if(busiType==''){
                Joyin.error(JoyinUtil.getMessage("业务类别不能为空"));  
                return;
            }else{
                accountDetailsTable.load();
            }
            
            
            
            
            
            
            
            
//            Joyin.ajaxJson(basePath + 'valuation/bankaccount/ValBankAccountQuery001/check', getAccountDetailsSearchParams(), function(result) {
////                if(!accountDetailsTable.table) {
////                    accountDetailsTable.load();
////                }
////                else {
////                    accountDetailsTable.reload();
////                }
//            });
        });
        
    }
    
    /**
     * 重置 Tab
     * conditionArea : 条件区
     * table : 关联的表格
     */
    var reset = function(conditionArea, table){
        $(conditionArea).find("input").val("");
        $(conditionArea).find("select").val("").trigger("change");// select清空
    }
    
    var loaded = {};// 已加载状态记录
    /**
     * 根据选项卡索引，动态加载表格
     */
    var loadTable = function(index){
             if(!loaded[index]){ // 当前没加载
                 switch(index)
                 {
                 case 0:
//                     accountDetailsTable.load();break;//加载表格 - 账务明细查询
                 case 1:
//                     incomeStatisticsTable.load();break;//加载表格 - 收入统计查询
                 }
                 loaded[index] = true;
             }else{// 当前已加载，避免表格错位（架构组要求重载）
                 switch(index)
                 {
                 case 0:
                     accountDetailsTable.reload();break;//加载表格 - 账务明细查询
                 case 1:
                     incomeStatisticsTable.reload();break;//加载表格 - 收入统计查询
                 }
             }
    }
    
 
    /**
     * 页面加载完后执行
     */
    $(function() {
//        // 选项卡点击事件
//        layui.element.on('tab(tabContainer)', function(obj) {
//            loadTable(obj.index);  // 加载当前表格数据
//        });
        
        accountDetailsBind();//事件绑定 - 账务明细查询
        
    });
    
    
 
    
    var getIncomeStatisticsSearchParams = function() {
        return {
            vdate: $('#vdate').val(),//科目名称
            mdate: $('#mdate').val(),//业务类别     
            productId: $.trim($('#productId').val()),
            dateDimension: $('#dateDimension').val(),
            productDimension: $('#productDimension').val()
        }
    };
    
    
    var incomeStatisticsTable = $("#incomeStatistics-table").joyintable({
        
        ajax: {
            url: 'findIncomeStatisticsPage',
            type: 'POST',
            data: function() {
                return getIncomeStatisticsSearchParams();
            }
    
        },
        cols: [
           {field:'product', title: '产品',width:255},
           {field:'cdate', title: '日期',width:255},
           {field:'amtFlagFt',title: '本金标识',width:255,sortField:'amtFlag'},         
           {field:'realizedAmt',title: '已实现收入',type:"money",width:255},
           {field:'extractAmt', title: '已提取收入',type:"money",width:255}
        ],
        
    });
    
    
    $('#ValIncomeStatisticsQuery001_btn_search').click(function() {
        
        var vdate= $('#vdate').val();
        var mdate= $('#mdate').val();
        var productId= $('#productId').val();
        var dateDimension= $('#dateDimension').val();
        var productDimension= $('#productDimension').val()
        if(mdate==''){
            Joyin.error(JoyinUtil.getMessage("截止日不能为空"));  
            return;
        }
        else if(dateDimension==''){
            Joyin.error(JoyinUtil.getMessage("日期维度不能为空")); 
            return;
        }
        else if(productDimension==''){
            Joyin.error(JoyinUtil.getMessage("产品维度不能为空"));  
            return;
        }else{
            if(vdate!=''){
                var oDate1 = new Date(vdate);
                var oDate2 = new Date(mdate);
                if(oDate1.getTime() >= oDate2.getTime()){
                    Joyin.error(JoyinUtil.getMessage("起始日不能大于等于截止日"));
                    return;
                }else{
                    incomeStatisticsTable.load();
                }
                
            }else{
                incomeStatisticsTable.load();
            }
            
        }
        
        
        
        
//        Joyin.ajaxJson(basePath + 'valuation/bankaccount/ValBankAccountQuery001/checkIncomeStatistics', 
//                getIncomeStatisticsSearchParams(),
//                function(result) {
//            if(!incomeStatisticsTable.table) {
//                incomeStatisticsTable.load();
//            }
//            else {
//                incomeStatisticsTable.reload();
//            }
//        });
    });
    
    
    /** 事件绑定: 筛选按钮 */
    $("#ValIncomeStatisticsQuery001_btn_filter").click(function(event){
        event.stopPropagation();// 阻止事件冒泡
        $("#c_incomeStatisticsForm").toggle();
    });
    
    /** 事件绑定: 置空按钮 */
    $("#ValIncomeStatisticsQuery001_btn_reset").click(function(event){
        reset( '#incomeStatisticsForm', incomeStatisticsTable);
    });
    
//    $("#dateDimension").change(function() {
//        var dateDimension = $('#dateDimension').val();
//        console.log(dateDimension);
//        if (dateDimension == '01') {
//            layui.use('laydate', function() {
//                var laydate = layui.laydate;
//
//                // 执行一个laydate实例
//                laydate.render({
//                    elem : '#date', // 指定元素
//                    type : 'date',
//                    range : true
//                });
//            });
//        } else if (dateDimension == '02') {
//            layui.use('laydate', function() {
//                var laydate = layui.laydate;
//
//                // 执行一个laydate实例
//                laydate.render({
//                    elem : '#date', // 指定元素
//                    type : 'year',
//                    range : true
//                });
//            });
//        } else if (dateDimension == '03') {
//            layui.use('laydate', function() {
//                var laydate = layui.laydate;
//
//                laydate.render({
//                    elem : '#date',
//                    type : 'month',
//                    format : 'yyyy年M季度',
//                    range : '~',
//                    min : "1900-1-1",
//                    max : "2099-12-31",
//                    ready : function(value, date, endDate) {
//                        var hd = $("#layui-laydate" + ele.attr("lay-key"));
//                        if (hd.length > 0) {
//                            hd.click(function() {
//                                ren($(this));
//                            });
//                        }
//                        ren(hd);
//                    },
//                    done : function(value, date, endDate) {
//                        if (date.month > 0 && date.month < 5) {
//                            ele.attr("startDate", date.year + "-" + date.month)
//                        }
//                        if (endDate.month > 0 && endDate.month < 5) {
//                            ele.attr("endDate", endDate.year + "-" + endDate.month)
//                        }
//                    }
//                });
//                var ren = function(thiz) {
//                    var mls = thiz.find(".laydate-month-list");
//                    mls.each(function(i, e) {
//                        $(this).find("li").each(function(inx, ele) {
//                            var cx = ele.innerHTML;
//                            if (inx < 4) {
//                                ele.innerHTML = cx.replace(/月/g, "季度");
//                            } else {
//                                ele.style.display = "none";
//                            }
//                        });
//                    });
//                }
//            });
//        } else if (dateDimension == '04') {
//            layui.use('laydate', function() {
//                var laydate = layui.laydate;
//
//                // 执行一个laydate实例
//                laydate.render({
//                    elem : '#date', // 指定元素
//                    type : 'month',
//                    range : true
//                });
//            });
//        } else if (dateDimension == '05') {
//            layui.use('laydate', function() {
//                var laydate = layui.laydate;
//
//                // 执行一个laydate实例
//                laydate.render({
//                    elem : '#date', // 指定元素
//                    type : 'date',
//                    range : true
//                });
//            });
//        }
//
//    });

    
$('#productDimension').change(
        function(){
            if($('#productDimension').val()==03){
                $("#productId").attr("disabled",false);
                $("#productId").val("").trigger("change");// select清空
                
            }else{
                $("#productId").attr("disabled",true);
                $("#productId").val("").trigger("change");// select清空
            }
            
            
        }
        
);
    
    

	
});