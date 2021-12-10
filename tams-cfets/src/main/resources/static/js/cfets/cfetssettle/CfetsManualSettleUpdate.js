(function(){
     //portSettleinfoDtoList

    repoInfoTableOption = {
        rowno: true,
        searchOnInit: true,
        checkbox: false,
        page: false,
        cols: [
            //组合
            {field: 'portfolioId', width: "200", title: '组合代码', sortable:false}
            //组合名称
            ,{field: 'portfolioName', width: "200", title: '组合名称', sortable:false}
            //付息周期
            , {field: 'settleNum', width: "90", title: '交收数量',type: 'money', sortable:false}
            //持有面额
            , {field: 'settleAmt', width: "200", title: '交收金额', type: 'money', sortable:false}
        ],
        beforeEditCell:function(){
        	return false;
        },
        data: []
    }

    if (portSettleinfoDtoList && portSettleinfoDtoList.length > 0) {
        repoInfoTableOption.data = repoInfoTableOption.data.concat(portSettleinfoDtoList);
    }

     /**
      * 定义数据表格
      */
     var repoInfoTable = $("#portSettleinfoDtoList").joyintable(repoInfoTableOption);

}());