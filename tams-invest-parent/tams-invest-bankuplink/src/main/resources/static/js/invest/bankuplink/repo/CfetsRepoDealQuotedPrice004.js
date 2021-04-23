(function(){

    var repoInfoTable1 = $("#CfetsRepoDealQuotedPrice004_table_1").joyintable({
        ajax: {
            url: 'queryPledgeBondList',
            type: 'POST',
            data: function() {
                var pageParam = $.serializeToObject('#cfetsRepoDealQuotedPrice004_form_1');
                pageParam.secAcctId = secAcctId;
                pageParam.portfolioId = portfolioId;
                var pledgeAllRows = repoInfoTable2.getAllRows();
                var bondCodes = [];
                for(var i=0;i<pledgeAllRows.length;i++){
                    bondCodes.push(pledgeAllRows[i].bondCode);
                }
                pageParam.bondCodes = JSON.stringify(bondCodes);
                return pageParam;
            }
        },
        height:180,
        searchOnInit:true,
        cols:[
            {field:'bondCode', width:150, title: 'CfetsRepoDealQuotedPrice004.table1.bondCode'}
            ,{field:'bondAbbrName', width:150, title: 'CfetsRepoDealQuotedPrice004.table1.bondAbbrName'}
            ,{field:'isSusStr', width:100, title: 'CfetsRepoDealQuotedPrice004.table1.isSus', sortField:"isSus"}
            ,{field:'isSecBondStr', width:100, title: 'CfetsRepoDealQuotedPrice004.table1.isSecBond', sortField:"isSecBond"}
            ,{field:'netPrice', width:150, title: 'CfetsRepoDealQuotedPrice004.table1.netPrice'}
            ,{field:'valueDateStr', width:150, title: 'CfetsRepoDealQuotedPrice004.table1.valueDate', sortField:"valueDate"}
            ,{field:'mdateStr', width:120, title: 'CfetsRepoDealQuotedPrice004.table1.mdate', sortField:"mdate"}
            ,{field:'exerciseDateStr', width:150, title: 'CfetsRepoDealQuotedPrice004.table1.exerciseDate', sortField:"exerciseDate"}
            ,{field:'repRegistOrgStr', width:150, title: 'CfetsRepoDealQuotedPrice004.table1.repRegistOrg', sortField:"repRegistOrg"}
        ],
        //loadonce:true
    });
    

    /*if(entity && entity.pledgeBondList){
        var divd = new BigNumber(10000);
        for(var i in entity.pledgeBondList){
            var row = entity.pledgeBondList[i];
            var pledgeNumber = new BigNumber(row.pledgeNumber);
            row.pledgeNumber = pledgeNumber.multipliedBy(divd);
            var avaPledgeNumber = new BigNumber(row.avaPledgeNumber);
            row.avaPledgeNumber = avaPledgeNumber.multipliedBy(divd);
            var pledgePrin = new BigNumber(row.pledgePrin?row.pledgePrin:0);
            row.pledgePrin = pledgePrin.multipliedBy(divd);
        }
    }*/
    var repoInfoTable2 = $("#CfetsRepoDealQuotedPrice004_table_2").joyintable({
        data : (entity && entity.pledgeBondList) ? entity.pledgeBondList : [],
        height : 180,
        searchOnInit : true,
        cols : [ {
            field : 'bondCode',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice004.table2.bondCode'
        }, {
            field : 'bondAbbrName',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice004.table2.bondAbbrName'
        }, {
            field : 'isSusStr',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice004.table2.isSus'
        }, {
            field : 'isSecBondStr',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice004.table2.isSecBond'
        }, {
            field : 'pledgePrin',
            type : 'number',
            width : 150,
            edit : true,
            title : '本次质押券面（万）'
        }, {
            field : 'pledgeRate',
            type : 'number',
            width : 150,
            edit : true,
            title : 'CfetsRepoDealQuotedPrice004.table2.pledgeRate'
        }, {
            field : 'netPrice',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice004.table2.netPrice'
        }, {
            field : 'valueDateStr',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice004.table2.valueDate'
        }, {
            field : 'mdateStr',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice004.table2.mdate'
        }, {
            field : 'exerciseDateStr',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice004.table2.exerciseDate'
        }, {
            field : 'repRegistOrgStr',
            width : 150,
            title : 'CfetsRepoDealQuotedPrice004.table2.repRegistOrg'
        } ],
        done : function(table, allData) {
            repoInfoTable1.table ? repoInfoTable1.reload() : repoInfoTable1.load();
        },
        editCell : function(value, data, field, cell, column) {
        }
    });

    /**
     * 添加按钮按下
     * @param array
     * @returns {boolean}
     */
    var joyinProcess = function () {
        var avaSelectedRows = repoInfoTable1.getAllCheckedRows();
        var pledgeAllRows = repoInfoTable1.getAllRows();
        if (avaSelectedRows != null && avaSelectedRows.length > 0) {
            // 验证是否已冻结
            for (var d = avaSelectedRows.length - 1; d >= 0; d--) {
                if(d.freezeFlag == "Y"){
                    Joyin.error("选取的债券代码"+d.bondCode+"已冻结！");
                    return ;
                }
            }
            // 倒序删除，正序会存在问题
            for (var d = avaSelectedRows.length - 1; d >= 0; d--) {
                repoInfoTable1.removeRow(avaSelectedRows[d]);
            }
            // 已选质押券添加
            for (var d = avaSelectedRows.length - 1; d >= 0; d--) {
                repoInfoTable2.addRow(avaSelectedRows[d]);
            }
        }else{
            Joyin.error(JoyinUtil.getMessage("CFETS_COMMON_E_034"));
        }
    };
    /**
     * 移除按钮按下
     * @param array
     * @returns {boolean}
     */
    var removeProcess = function () {
        var pledgeAllRows = repoInfoTable2.getAllCheckedRows();
        if (pledgeAllRows != null && pledgeAllRows.length > 0) {
            // 倒序删除，正序会存在问题
            for (var i = pledgeAllRows.length - 1; i >= 0; i--) {
                repoInfoTable2.removeRow(pledgeAllRows[i]);
            }
        }
    };
    

    /**
     * 保存按钮按下
     *
     * @param array
     * @returns {boolean}
     */
    var addPledgeBond = function () {
        var pledgeAllRows = repoInfoTable2.getAllRows();
        window.parent.CfetsRepoDealQuotedPrice002.addPledgeBond(pledgeAllRows,"nrepoInfo");
        var index = window.parent.layer.getFrameIndex(window.name);
        window.parent.layer.close(index);
    };
    $(function(){
        /**
         * 可质押放大镜点击事件
         */
        $('#CfetsRepoDealQuotedPrice004_entrustOrder_search').click(function(){
            repoInfoTable1.table ? repoInfoTable1.reload() : repoInfoTable1.load();
        });

        // 添加按钮事件
        $('#cfetsRepoDealQuotedPrice004_form_2_btn1').click(joyinProcess);
        // 移除按钮事件
        $('#cfetsRepoDealQuotedPrice004_form_2_btn2').click(removeProcess);

        // 保存按钮事件
        $('#CfetsRepoDealQuotedPrice004_save').click(addPledgeBond);
    })
}());