$(function () {
    var myDefineData = [
  ];
    var data1 = [
        ["",],
    ];
    /*$(".form_date").datetimepicker({
     format: 'yyyy-mm-dd',
     "minView": 2,
     autoclose: true
     });
     */
    var getPageParams = function() {
        return {
            // 机构代码
            orgCode : $('#WholeVerification001_orgCode').val(),
            // 查询日期
            searchDate : $('#WholeVerification001_searchDate').val(),
        };
    };

    var joyTable = function (option) {
        var defaultOption = {
            data: [],
            language: {
                "sProcessing": "处理中...",
                "sLengthMenu": " _MENU_ 条/页",
                "sZeroRecords": "",
                "sInfo": "共 _TOTAL_ 条数据，",//
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "dom": "<'row'<'col-sm-6'f>>" +
                "<'row'<'col-sm-12'tr>>" +
                "<'row'<'col-sm-5 mytestr'il <'clear'>><'col-sm-7'p>>",
                "oPaginate": {
                    "sFirst": "|<",
                    "sPrevious": "<",
                    "sNext": ">",
                    "sLast": ">|"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "dom": "<'row'<'col-sm-12'tr>>" +
            "<'row'<'col-sm-5 mytestr' il <'clear'>><'col-sm-7'p>>",

            "pagingType": "full_numbers",
            scrollX: true
        }
        var tableId = option.tableId;
        option.tableId = null;
        option = $.extend(true, defaultOption, option);
        $("#" + tableId).dataTable(option);
    };
    var tableColumns = {
        "table2": [
            {title: "资产代码"},
            {title: "资产名称"},
            {title: "资产代码"},
            {title: "资产名称"}
        ],
        "table3": [
            {title: "机构代码"},
            {title: "机构名称"},
            {title: "产品等级编码"},
            {title: "产品名称"},
            {title: "客户类型"},
            {title: "募集结束日"},
            {title: "募集登记日"},
            {title: "已募集登记"}
        ],
        "table4":[
            {title: "机构代码"},
            {title: "资产负债代码"},
            {title: "底层登记"},
            {title: "当前发行总数量"},
            {title: "当前发行总金额(万元)"},
            {title: "未投资头寸"},
            {title: "底层总资产市值"},
            {title: "底层总负债市值"},
            {title: "是否偏差过大"}
        ],
        "table5":[
            {title: "机构代码"},
            {title: "机构名称"},
            {title: "行内标识码"},
            {title: "产品登记编码"},
            {title: "产品名称"},
            {title: "客户类型"},
            {title: "募集起始日"},
            {title: "募集结束日"},
            {title: "产品起始日"},
            {title: "现金持仓金额"}
        ],
        "table6":[
            {title: "机构名称"},
            {title: "理财产品代码"},
            {title: "资产负债编码"},
            {title: "最大单价"},
            {title: "最小单价"}
        ],
        "table7": [
            {title: "机构代码"},
            {title: "机构名称"},
            {title: "资产负债编码"},
            {title: "是否登记了底层"},
            {title: "当前发行总数量"},
            {title: "当前发行总金额"},
            {title: "未投资头寸"},
            {title: "底层总资产市值"},
            {title: "底层总负债市值"},
            {title: "是否偏差过大"}
        ]
    }
    var tableInitStatus = {};
    var tableInitData = {
        "table1":[['C12588','成都农商行','268','655.2583','268','655.2583','0','268','655.2583','268','655.2583','0','1',]],
        "table2":[],
        "table3":[['C12588','成都农商行','PC15047400','PC15047400','个人客户专享','20171008','20171012','否'],
            ['C12588','成都农商行','PA15039700','PA15039700','个人客户专享','20171008','20171012','否'],
            ['C12588','成都农商行','PA15040500','PA15040500','个人客户专享','20171008','20171012','否'],
            ['C12588','成都农商行','PC15046300','PC15046300','个人客户专享','20171012','20171016','否'],
            ['C12588','成都农商行','PC15048000','PC15048000','个人客户专享','20171012','20171016','否'],
            ['C12588','成都农商行','PC15045600','PC15045600','个人客户专享','20171012','20171016','否'],
            ['C12588','成都农商行','PC15047900','PC15047900','个人客户专享','20171012','20171016','否'],
            ['C12588','成都农商行','PC15045700','PC15045700','个人客户专享','20171012','20171016','否']
        ],
        "table4":[],
        "table5":[['成都农商行','CC15059100','CC15059100','“周周鑫”开放式理财计划150591','机构客户专属','已募集登记','20151229','20151229','20151230','-1025406550.79'],
            ['成都农商行','CA15044000','CA15044000','“双周赢”开放式理财计划150440','机构客户专属','已募集登记','20151013','20151014','20151015','-971439566.77'],
            ['成都农商行','CC15058500','CC15058500','“周周鑫”开放式理财计划150585','机构客户专属','已募集登记','20151225','20151228','20151229','-3491871780.82']
        ],
        "table6":[['成都农商行','PA15015000','1489145','0.315231147457627','0.1034']
        ],
        "table7":[['C12588','成都农商行','ZIDY20151117000283','有','7024970000','7024970000','0','7100453093.19','0','否'],
            ['C12588','成都农商行','ZIDY20151028000252','有','7024970000','7024970000','0','7100453093.19','0','否'],
            ['C12588','成都农商行','ZIDY20151229000328','有','9755690000','9755690000','0','9887960555.97','0','否'],
            ['C12588','成都农商行','ZIDY20151203000304','有','9755690000','9755690000','0','9887960555.97','0','否'],
            ['C12588','成都农商行','ZIDY20150729000193','有','7024970000','7024970000','0','7100453093.19','0','否'],
            ['C12588','成都农商行','ZIDY20150729000194','有','7024970000','7024970000','0','7100453093.19','0','否']
        ]
    }
    $(".tab-pane").hide();
    $(".tab-pane.active").show()
    $('#form a').click(function (e) {
        e.preventDefault()
        $(this).tab('show')
        $(".tab-pane.active").show();
        var tableId = $(".tab-pane.active table").attr("id");
        if(tableId=="table1"){
            return;
        }
        if(tableInitStatus[tableId]){
            return;
        }
        tableInitStatus[tableId] = true;
        console.log(tableInitData[tableId])
        console.log(tableId)
        joyTable({
            tableId:tableId,
            columns:tableColumns[tableId],
            data:tableInitData[tableId]
        });

    });
    joyTable({
        tableId: "table1",
        data:tableInitData['table1'],
        col: [
            {title: "机构代码"},
            {title: "机构名称"},
            {title: "理财产品存续只数"},
            {title: "理财产品存续金额(万元)"},
            {title: "月度统计表存续只数"},
            {title: "月度统计表存续金额(万元)"},
            {title: "偏离度%"},
            {title: "理财产品存续只数(人民币产品)"},
            {title: "理财产品存续金额(万元/人民币产品)"},
            {title: "月度统计表存续只数"},
            {title: "月度统计表存续金额(万元/人民币产品)"},
            {title: "偏离度"},
            {title: "合格率"}
        ]
    });
    // $('#from a:first').click();


//    $("#form").on('click',"div", function (params){
//        params;
//    });

    var option = {
        tooltip: {
            trigger: 'item',
            formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        title: {
            text: '100%',
            x: 'center',
            y: 'center'
        },
        series: [
            {
                name: '',
                type: 'pie',
                radius: ['50%', '70%'],
                avoidLabelOverlap: false,
                label: {
                    normal: {
                        show: false,
                        position: 'center'
                    },
                    emphasis: {
                        show: true,
                        textStyle: {
                            fontSize: '30',
                            fontWeight: 'bold',
                        }
                    }
                },
                labelLine: {
                    normal: {
                        show: false
                    }
                },
                data: [
                    {value: 0, name: '不合格'},
                    {value: 10, name: '合格'}
                ],
                color:['#cacad9','#a8de64','#666666']
            }
        ]
    };
    var pieDivs = $(".joy-pie .joy-pie-div");
    var pie1 = echarts.init(pieDivs[0]);
    pie1.setOption(option, true);
//    pieDivs.on('click', function (params) {
//        params;
//    });
    var pie2 = echarts.init(pieDivs[1]);
    pie2.setOption($.extend(true, {}, option, {
        title: {text: "100%"}, series: [{
            data: [
                {value: 0, name: '不合格'},
                {value: 10, name: '合格'}
            ],
            color:['#cacad9','#a8de64','#666666']
        }]
    }), true);
    var pie3 = echarts.init(pieDivs[2]);
    pie3.setOption($.extend(true, {}, option, {
        title: {text: "96%"}, series: [{
            data: [
                {value: 4, name: '不合格'},
                {value: 96, name: '合格'}
            ],
            color:['#cacad9','#a8de64','#666666']
        }]
    }), true);
    var pie4 = echarts.init(pieDivs[3]);
    pie4.setOption($.extend(true, {}, option, {
        title: {text: "75%"}, series: [{
            data: [
                {value: 1, name: '不合格'},
                {value: 3, name: '合格'}
            ],
            color:['#cacad9','#ffaabb','#666666']
        }]
    }), true);
    var pie5 = echarts.init(pieDivs[4]);
    pie5.setOption($.extend(true, {}, option, {
        title: {text: "94%"}, series: [{
            data: [
                {value: 6, name: '不合格'},
                {value: 94, name: '合格'}
            ],
            color:['#cacad9','#ffaabb','#666666']
        }]
    }), true);
    var pie6 = echarts.init(pieDivs[5]);
    pie6.setOption($.extend(true, {}, option, {
        title: {text: "97%"}, series: [{
            data: [
                {value: 3, name: '不合格'},
                {value: 97, name: '合格'}
            ],
            color:['#cacad9','#a8de64','#666666']
        }]
    }), true);
    var pie7 = echarts.init(pieDivs[6]);
    pie7.setOption($.extend(true, {}, option, {
        title: {text: "96%"}, series: [{
            data: [
                {value: 4, name: '不合格'},
                {value: 96, name: '合格'}
            ],
            color:['#cacad9','#a8de64','#666666']
        }]
    }), true);

    $(function() {
        /*delayVerification.init();
        // 查询按钮点击事件
        $('#WholeVerification001_btn_search').bind("click",delayVerification.searchData);*/
    });
})