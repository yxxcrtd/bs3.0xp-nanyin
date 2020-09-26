$(function () {
    
    /**
     * 定义数据表格
     */
    var table=$("#ValBondValuationInfo002_table").joyintable({
        ajax: {
            url: 'searchList',
            type: 'POST',
            data: {secId: $("#secId").val()}
        },
        searchOnInit: true,
        checkbox: false,
        cols: [
                {field : 'secId',title : '债券代码',width:120,sort : true},
                {field : 'valueSource',title : '估值来源',width:120, sort : true}, 
                {field : 'valueDate',title : '估值日',width:120, sort : true }, 
                {field : 'lastPeriod', title : '剩余期限',width:120, sort : true },  
                {field : 'price',title : '市价全价',width:120, sort : true}, 
                {field : 'netprice', title : '市价净价',width:120, sort : true },
                {field : 'mDuration', title : '市价久期',width:120, sort : true}, 
                {field : 'mConvexity',title : '市价凸性',width:120, sort : true }, 
                {field : 'bpvalue',title : '基点价值',width:120, sort : true }, 
                {field : 'sduration', title : '利差久期',width:120, sort : true },  
                {field : 'scnvxty',title : '利差凸性',width:120, sort : true}, 
                {field : 'interestDuration', title : '利率久期',width:120, sort : true },
                {field : 'interestCnvxty', title : '利率凸性',width:120, sort : true}, 
                {field : 'bpyield',title : '市价收益率',width:120, sort : true } 
        ],
        loadonce:true
    });
    
});