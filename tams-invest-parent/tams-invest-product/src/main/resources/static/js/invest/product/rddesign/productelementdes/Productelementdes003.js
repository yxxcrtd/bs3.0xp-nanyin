/**
 * 产品要素设计查询
 */
(function() {
    /**
     * 查看产品需求详细信息
     */

    var dataTable03 = $("#dataTable03").joyintable({

        ajax: {
            url: 'findSinglePage',
            type: 'POST',
            data: {requirementId:requirementId
            }
        },
        height: 'full-' + 220,
        cols:[

            {field:'dataStatusStr', width:120, title: 'Productelementdes001.statusValid'}
            ,{field:'requirementId', width:130, title: 'Productelementdes001.requirementId'}
            ,{field:'requirementBasis', width:200, title: 'Productelementdes001.requirementBasis'}
            ,{field:'tarCustomer', width:120, title: 'Productelementdes001.tarCustomer'}
            ,{field:'prdScale', width:130, title: 'Productelementdes001.prdScale'}
            ,{field:'prdPeriod', width:130, title: 'Productelementdes001.prdPeriod'}
            ,{field:'prdRate', width:130, title: 'Productelementdes001.prdRate'}
            ,{field:'prdRequirementDetial', width:130, title: 'Productelementdes001.prdRequirementDetial'}
            ,{field:'requirementSourceStr', width:130, title: 'Productelementdes001.requirementSource'}
            ,{field:'feasibilityStatusStr', width:130, title: 'Productelementdes001.feasibilityStatus'}
            ,{field:'feasibilitTypeStr', width:130, title: 'Productelementdes001.feasibilitType'}
            // ,{field: 'operation', title: 'Productelementdes001.table.operation', type: 'action', buttons: ['dealRevoke'],
            //     render: function (data, type, row, meta) {
            //         if(row.effectStatus=="01")
            //             return "<a class='pro-link dealRevoke' style='cursor: pointer;'>" + "撤销" + "</a>";
            //     }
            // }
        ],
        checkbox: true
    });

    $(function () {
        //dataTable03.load();

        });

}());