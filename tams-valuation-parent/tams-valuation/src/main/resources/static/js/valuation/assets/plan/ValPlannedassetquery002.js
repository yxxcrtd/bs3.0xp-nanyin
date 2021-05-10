$(function () {
  
    var profitTypeCode = '02';
    if(profitTypeCode.indexOf(initprofitType)>=0){
        $("#div1").show();
        $("#div2").show();
        $("#div3").show();
        $("#div4").show();
        $("#div5").show();
        /** modify by yanya on 20201222 收益型展示是否其他债券**/
        $("#isQtzqDiv").show();
    }else {
        $("#div1").hide();
        $("#div2").hide();
        $("#div3").hide();
        $("#div4").hide();
        $("#div5").hide();
        /** modify by yanya on 20201222 非收益型不展示是否其他债券**/
        $("#isQtzqDiv").hide();
    };
    var profitTypeCode2 = '03';
    if(profitTypeCode2.indexOf(initprofitType2)>=0){
        $("#div6").show(); 
    }else{
        $("#div6").hide();
    };
    
    /**
     * 定义数据表格-利率报价信息
     */
    var table1=$("#dataTable1").joyintable({
        ajax: {
            url: 'planintrate',
            type: 'POST',
            data: {finprodId: $("#finprodId").val()
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols: [
            {field:'efdate', title: '生效日', width:120},
            {field:'rate2', title: '利率(%)' }         
        ],
        loadonce:true,
        page:false
    });
    /**
     * 定义数据表格-付息计划列表
     */
    var table2=$("#dataTable2").joyintable({
        ajax: {
            url: 'planintpay',
            type: 'POST',
            data: {finprodId: $("#finprodId").val()
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols: [
            {field:'jxdatestr', title: '计息开始日'},
            {field:'jxdateend', title: '计息结束日'},
            {field:'paydate', title: '支付日'},
            {field:'byrate',  type:"money",title: '百元应计利息'},
        ],
      loadonce:true,
      page:false
    });
    /**
     * 定义数据表格-还本计划列表
     */
    var table3=$("#dataTable3").joyintable({
        ajax: {
            url: 'planrepay',
            type: 'POST',
            data: {finprodId: $("#finprodId").val()
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols: [
            {field:'jsdateend', title: '计息结束日'},
            {field:'paydate', title: '支付日'},
            {field:'tqrmon', title: '提前还本金额'},
            {field:'aqrmon', title: '按期还本金额'},
            {field:'israteFt', title: '是否带息'},
            {field:'yjrte', title: '应计利息'}
        ],
      loadonce:true,
      page:false
    });
    /**
     * 定义数据表格-分红计划信息
     */
    var table4=$("#dataTable4").joyintable({
        ajax: {
            url: 'planbonus',
            type: 'POST',
            data: {finprodId: $("#finprodId").val()
            }    
        },
        checkbox: false,
        searchOnInit: true,
        height: 150,
        cols: [
            {field:'jsdatestr', title: '计息开始日'},
            {field:'jsdateend', title: '计息结束日'},
            {field:'paydate', title: '支付日'}
        ],
      loadonce:true,
      page:false
    });
}());