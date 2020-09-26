$(function () {
    
    /**
     * 定义数据表格
     */
    var table_zfpl=$("#dataTable_jtzq").joyintable({
        ajax: {
           url: basePath + "valuation/basedata/productinfoquery/ValProductInfoQuery001/queryfloatFeeList1",
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),cashType2:'C04'
            }
        },
        searchOnInit: true,
        checkbox: false,
        height: 150,
        cols: [
           {field:'jtStrDate', title: '计提周期开始日', sort: true},
           {field:'jtEndDate', title: '计提周期结束日', sort: true},
           {field:'jtDate', title: '计提日', sort: true}
        ],
        loadonce:true,
        page:false
    });
    /**
     * 定义数据表格
     */
    var table_zfpl=$("#dataTable_yjbj").joyintable({
        ajax: {
            url: basePath + "valuation/basedata/productinfoquery/ValProductInfoQuery001/queryfloatFeeList2",
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),cashType2:'C04'
            }
        },
        searchOnInit: true,
        checkbox: false,
        height: 150,
        cols: [
           {field:'basicLevel', title: '业绩比较基准层级', sort: true},
           {field:'levelRemark', title: '层级描述', sort: true,width:500},
           {field:'bankRatio',  type:"money",title: '银行收取比例', sort: true},
           {field:'customerRatio', type:"money", title: '客户分配比例', sort: true}
        ],
        loadonce:true,
        page:false
    });
    /**
     * 定义数据表格
     */
    var table_zfpl=$("#dataTable_fdglf").joyintable({
        ajax: {
            url: basePath + "valuation/basedata/productinfoquery/ValProductInfoQuery001/queryfloatFeeList",
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),cashType2:'C04'
            }
        },
        searchOnInit: true,
        checkbox: false,
        height: 150,
        cols: [
           {field:'num', title: '序号', sort: true},
           {field:'vdate', title: '计费开始日', sort: true},
           {field:'mdate', title: '计费结束日', sort: true},
           {field:'paydate', title: '支付日', sort: true}
        ],
        loadonce:true,
        page:false
    });
    /**
     * 定义数据表格
     */
    var table_cpfy=$("#dataTable_cpfy").joyintable({
        ajax: {
            url: basePath + "valuation/basedata/productinfoquery/ValProductInfoQuery001/queryFeeList",
            type: 'POST',
            data: {finprodId: $("#finprodId").val(),noCashType2:'C04'
           }
        },
        searchOnInit: true,
        checkbox: false,
        height: 150,
        cols: [
               {field:'cashId', title: '费用uuid', visible:false, sort: true},
//               {field:'feeName', title: '费用名称', sort: true},
               {field:'cashType2Code', title: '费用类型', visible:false, sort: true},
               {field:'cashType2', title: '费用类型', sort: true},
               {field:'yield', title: '费率(%)', sort: true},
               {field:'baseTypeCode', title: '计算基数', sort: true, visible:false},
               {field:'baseTypeFt', title: '计费基数', sort: true,sortField:'baseType'},
               {field:'baseDateTypeCode', title: '计费日期类型', sort: true, visible:false},
               {field:'baseDateType', title: '计费日期类型', sort: true},
               {field:'basis', title: '计算基础', sort: true},
               {field:'effDate', title: '首次计提日', sort: true},
               {field:'payCycleCode', title: '付费规则', visible:false, sort: true},
               {field:'payCycle', title: '付费规则', sort: true},
               {field:'firstPayDate', title: '首次付费日', sort: true},
               //{field:'historiAdjust', title: '历史费率调整',align:'center', type:'action', buttons: ['edit']}
               {field:'operation', title: '历史调整',fixed : 'right',
                   render: function (data, type, row, meta) {
                       return '<a href="javascript:void(0);" onclick="showAdjust('+ "'" + row.finprodId + "','" + row.cashType2Code + "'" +')" data-cashId="' + row.cashId + '">详细</a>';
                   }
               }
        ],
        loadonce:true,
        page:false
    });
    /**
     * 定义数据表格
     */
    var table_zjzh=$("#dataTable_zjzh").joyintable({
        ajax: {
            url: basePath + "valuation/basedata/productinfoquery/ValProductInfoQuery001/getFinprodAcct",
            type: 'POST',
            data: {finprodId: $("#finprodId").val()
           }
        },
        searchOnInit: true,
        checkbox: false,
        height: 200,
        cols: [
               {field:'acctName', title:'资金账户'},
               {field:'acctTypeCode', title:'资金账户类型', visible:false},
               {field:'acctType', title: '资金账户类型',width:120},
               {field:'feeType', title: '费用品种',
                   render: function (data, type, row, meta) {
                       return '--';
                   }
               },
               {field:'acctNo', title: '资金账户账号',width:120},
               {field:'ccyCode', title: '币种', visible:false},
               {field:'ccy', title: '币种'},
               {field:'customerName', title: '中文名'},
               {field:'bankDep', title: '开户行'},
               {field:'bankDepName', title: '开户行', visible:false},
               {field:'bankDepTypeCode', title: '开户行类型',width:110, visible:false},
               {field:'bankDepType', title: '开户行类型',width:110},
               {field:'corrNo', title: '开户行联行行号',width:120},
               {field:'largePayno', title: '大额支付号',width:110},
               {field:'firstPayDate', title: 'Swift Code',width:110},
               {field:'remark', title: '备注'}
        ],
        loadonce:true,
        page:false
    });
    
    /**
     * 定义数据表格
     */
    var table_zjzh=$("#dataTable_zqzh").joyintable({
        ajax: {
            url: basePath + "valuation/basedata/productinfoquery/ValProductInfoQuery001/getSecAccount",
            type: 'POST',
            data: {finprodId: $("#finprodId").val()
           }
        },
        searchOnInit: true,
        checkbox: false,
        height: 200,
        cols: [
               {field:'finprodId', title:'产品代码',width:200, visible:false},
               {field:'acctId', title:'编号(内部ID)',width:200},
               {field:'acctCode', title:'账户名称',width:200},
               {field:'brokerName', title: '所属券商',width:200},
               {field:'acctDesc', title: '账户描述',width:200},
               {field:'bankDescnumb', title: '关联资金账户个数',width:200},
               {field:'operation', title: '关联资金账户详细',fixed : 'right',
                   render: function (data, type, row, meta) {
                       return '<a href="javascript:void(0);" onclick="showAdjustp('+ "'" + row.finprodId + "','" + row.cashType2Code + "'" +')" data-cashId="' + row.cashId + '">详细</a>';
                   }
               }
        ],
        loadonce:true,
        page:false
    });    
    //页面全部加载完毕后
    $(function() {
        $("#div_cpfy").click(function(){
            $("#div_dataTable_cpfy").toggle();
        });
        $("#div_fdglf").click(function(){
            $("#div_dataTable_fdglf").toggle();
        });
        $("#div_cpfy1").click(function(){
            $("#div_dataTable_cpfy").toggle();
        });
        $("#div_fdglf1").click(function(){
            $("#div_dataTable_fdglf").toggle();
        });
    });
});
var showAdjust=function(finprodId, cashType2Code){
  Joyin.dialog(JoyinUtil.getLabel("查看调整信息"),
          JoyinUtil.getRealPath("valuation/basedata/productinfoquery/ValProductInfoQuery005/init"),
          "80%", "80%", {pageModel:PAGE_MODEL_EDIT, finprodId:finprodId, cashType2: cashType2Code},
          function(){
              //dataTable.reload();
          });
}

var showAdjustp=function(finprodId, cashType2Code){
	  Joyin.dialog(JoyinUtil.getLabel("查看资金账户"),
	          JoyinUtil.getRealPath("valuation/basedata/productinfoquery/ValProductInfoQuery006/init"),
	          "60%", "70%", {pageModel:PAGE_MODEL_EDIT, finprodId:finprodId, cashType2: cashType2Code},
	          function(){
	              //dataTable.reload();
	          });
	}