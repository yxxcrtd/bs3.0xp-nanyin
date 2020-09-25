/**
 * OPICS业务组件
 */
(function() {
    var initBussTrdOpicsPage = function() {
        /**
         * 定义数据表格
         *
         */
        var BussTrdOpics_option = {
            searchOnInit: true,
            checkbox: false,
            cols: [
                  {field:'sendflag', title: 'BussTrdOpics.sendflag', width:150, edit:true, sortable:false,
                      editOption:{type:'select', multiple: false, searchable: false, result: YES_NO_DATA}}
                  ,{field:'dealtype', title: 'BussTrdOpics.dealtype', width:100, edit:true, sortable:false,
                      editOption:{type:'select', multiple: false, searchable: false, url: JoyinUtil.getRealPath('/component/trade/BussTrdOpics/getSelectOptions?type=DEALTYPE')}}//交易类型
                  ,{field:'trad', title: 'BussTrdOpics.trad',width:100, sortable:false} //交易员
                  ,{field:'port', title: 'BussTrdOpics.port',width:100, sortable:false} //投资组合
                  ,{field:'cost', title: 'BussTrdOpics.cost', width:200, edit:true, sortable:false,
                      editOption:{type:'select', multiple: false, searchable: false, url: JoyinUtil.getRealPath('/component/trade/BussTrdOpics/getSelectOptions?type=COST')}}//成本中心
                  ,{field:'branch', title: 'BussTrdOpics.branch', width:100, edit:true, sortable:false,
                      editOption:{type:'select', multiple: false, searchable: false, url: JoyinUtil.getRealPath('/component/trade/BussTrdOpics/getSelectOptions?type=BRANCH')}}//分支机构
                  ,{field:'brok',  title: 'BussTrdOpics.brok', width:100, edit:true, sortable:false,
                      editOption:{type:'select', multiple: false, searchable: false, url: JoyinUtil.getRealPath('/component/trade/BussTrdOpics/getSelectOptions?type=BROK')}}//是否修复
                  ,{field:'verind', title: 'BussTrdOpics.verind', width:140, edit:true, sortable:false,
                      editOption:{type:'select', multiple: false, searchable: false, url: JoyinUtil.getRealPath('/component/trade/BussTrdOpics/getSelectOptions?type=VERIND')}}//是否自动复核 
                  ,{field:'vdate', title: 'BussTrdOpics.vdate',type:'date', width:120, sortable:false} //起息日 
                  ,{field:'mdate', title: 'BussTrdOpics.mdate',type:'date', width:120, sortable:false} //到期日 
                  ,{field:'ccy', title: 'BussTrdOpics.ccy', width:100, edit:true, sortable:false,
                      editOption:{type:'select', multiple: false, searchable: true, result: CCY_DATA}} //币种
                  ,{field:'amt', title: 'BussTrdOpics.amt', width:100, sortable:false} //金额
                  ,{field:'basis', title: 'BussTrdOpics.basis', edit:true, width:100, sortable:false,
                      editOption:{type:'select', multiple: false, searchable: false, url: JoyinUtil.getRealPath('/component/trade/BussTrdOpics/getSelectOptions?type=BASIS')}}//计息规则
                  ,{field:'spread', title: 'BussTrdOpics.spread', width:100, sortable:false} //点差
                  ,{field:'dealdate', title: 'BussTrdOpics.dealdate', type:'date', width:120, sortable:false} //交易日
                  ,{field:'dealtext', title: 'BussTrdOpics.dealtext', edit:true, width:100, sortable:false}//交易备注 
                  ,{field:'remark', title: 'BussTrdOpics.remark', width:100, sortable:false}//备注 
            ],
            page:false,
            data:[]
        };
        if (BussTrdOpics_ENTITY) {
            BussTrdOpics_option.data.push(BussTrdOpics_ENTITY);
        }
        var BussTrdOpics_table = $("#BussTrdOpics_table").joyintable(BussTrdOpics_option);
        $("#BussTrdOpics_btn_generate").on("click", function () {
            if(stlCommandInput != null){
                var data = stlCommandInput();
                Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdOpics/check'), data, function () {
                    Joyin.ajaxJson(JoyinUtil.getRealPath('/component/trade/BussTrdOpics/generateOrder'), data, function (ret) {
                        var order = ret.data;
                        table.clear(function () {
                            table.addRow(order);
                        });
                    });
                });
            }
        });
    };

    var stlCommandInput;
    var setStlCommandInput = function (func) {
        stlCommandInput = func;
    };

    // 划款指令共通组件公开接口
    if(window.BussTrdOpics == undefined){
        window.BussTrdOpics = {};
    }
    window.BussTrdOpics = {
        /**
         * 输入函数范例，需提供是否汇总交易，拆借类型，组合代码，金融产品交易Entity：
         *   function {
         *       return {'isTotalTrade':'02','bankLendingType':null,'portfolioId':'123456','deal':getDealEntity()}
         *   }
         */
        setStlCommandInput : setStlCommandInput,
        initBussTrdOpicsPage: initBussTrdOpicsPage
    };
    initBussTrdOpicsPage();
}());