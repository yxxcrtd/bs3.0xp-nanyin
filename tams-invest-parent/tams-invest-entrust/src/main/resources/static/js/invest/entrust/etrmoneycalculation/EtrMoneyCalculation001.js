/**
 * 可投金额试算
 */
(function(){
    /**
     * 获取查询参数
     */
    var searchform =function(){
        var data = $.serializeToObject('#dataform');
        if(table.getAllRows().length>0)
        {
            var  tablelist=table.getAllRows();
            data.detailJsonList=JSON.stringify(tablelist);
        }

       return data;

    };

    /**
     * 定义数据表格
     */

    var table=$("#etrMoneyCalculationDetailDtoList").joyintable({

        ajax: {
            url: 'calculation',
            type: 'POST',
            data: function(dataValue) {
                return searchform();
            }
        },
        checkbox: false,
        sortfont: false,
        rowno:true,
        cols:[{field:'targetName', title: 'EtrMoneyCalculation001.targetName', width:300}
            ,{field:'portfoliopackage', title: 'EtrMoneyCalculation001.portfoliopackage', width:200}
            ,{field:'sumMoney', title: 'EtrMoneyCalculation001.sumMoney', type:"money",width:250}
            ,{field:'useMoney', title: 'EtrMoneyCalculation001.useMoney',type:"money", width:250}
            ,{field:'surplusMoney', title: 'EtrMoneyCalculation001.surplusMoney',type:"money", width:250}
            ]
    });
    /**
     * 导出
     */
    var exportExcel = function(){       
        Joyin.exportExcel(basePath+"invest/entrust/etrmoneycalculation/EtrMoneyCalculation001/export", searchform());
    };


    /**
     * 编辑按钮事件
     */
    $(function () {
        $('#fund').hide();
        $('#stock').hide();
        $('#finprodtwo').hide();
        $('#finprodthree').hide();
        /**
         * 导出按钮
         */
        $('#EtrMoneyCalculation001_btn_export').click(function() {
            var datelist=table.getAllRows();
            if(datelist.length==0)
            {
                Joyin.error("请先查询出数据后再导出!");
                return false;
            }
            exportExcel();
        });
        /**
         * 计算
         */
        $('#EtrMoneyCalculation001_btn_calculation').click(function() {

            var finprodId=$('#finprodId').val();
            if(finprodId==''|| $('#portfolioId').val()==''|| $('#portfolioId').val()==null)
            {
                Joyin.error("请先选择组合代码和资产代码!");
                return false;
            }
            if($('#finprodType').val()==''|| $('#finprodType').val()==null)
            {
                Joyin.error("请选择资产类型!");
                return false;
            }
            table.load();
            //可投金额为各指标限制的（剩余可投金额）最小值；
            setTimeout( function(){
                var datelist=table.getAllRows();
                var haveMoney=99999999999999;//可投金额
                for(var i=0;i<datelist.length;i++) {
                    if(datelist[i].surplusMoney=="Max")
                    {
                        datelist[i].surplusMoney="99999999999999";
                    }
                    if(parseFloat(datelist[i].surplusMoney) <parseFloat(haveMoney))
                    {
                        haveMoney=datelist[i].surplusMoney;
                    }

                }
                if(datelist.length==0)
                {
                    $('#haveMoney').val('0');
                }
                else
                {
                    $('#haveMoney').val(haveMoney=="99999999999999"?"Max":haveMoney);
                }


            }, 2000 );//延迟2000毫秒等 table数据加载完毕否则取不到值

        });
        /**
         * 资产代码输入时根据资产类型获取对应的资产代码，选择资产代码后联动相应项进行赋值和显示隐藏内容
         */
       $('#finprodId').on('change', function () {
           getMsg($("#finprodId").val());
           table.clear(true);
        });
        $('#finprodIdtwo').on('change', function () {
            getMsg($("#finprodIdtwo").val());
            table.clear(true);
        });
        $('#finprodIdthree').on('change', function () {
            getMsg($("#finprodIdthree").val());
            table.clear(true);
        });
       function  getMsg(finprodId) {

           Joyin.ajax(basePath + 'invest/entrust/etrmoneycalculation/EtrMoneyCalculation001/getFinprodMsg', {
               'finprodId': finprodId,
               'finprodType':$("#finprodType").val()
           }, function (data) {

               if(data!='') {
                   $('#bondOneType').val(data.bondOneType);//债券一级分类（资产类型选择债券时显示）
                   $('#bondTwoType').val(data.bondTwoType);//债券二级分类（资产类型选择债券时显示）
                   $('#publisher').val(data.publisher);//发行人（资产类型选择债券时显示）
                   $('#bondRating').val(data.bondRating);//债项评级（资产类型选择债券时显示）
                   $('#bondSubjectRating').val(data.bondSubjectRating);//主体评级（资产类型选择债券时显示）
                   $('#fundType').val(data.fundType);//基金类型（资产类型选择基金时显示）
                   $('#fundTwoType').val(data.fundTwoType);//基金二级分类（资产类型选择基金时显示）
                   $('#manager').val(data.manager);//管理人（资产类型选择基金时显示）
                   $('#tradingPlace').val(data.tradingPlace); //交易场所（资产类型选择股票时显示）
                   $('#board').val(data.board); // 上市版（资产类型选择股票时显示）
                   $('#repIndustry').val(data.repIndustry); //行业（资产类型选择股票时显示）
                   $('#haveMoney').val(data.haveMoney); //可投金额
               }
           });
       }

        function  getFinprodIdByType(finprodType) {
            Joyin.ajax(basePath + 'invest/entrust/etrmoneycalculation/EtrMoneyCalculation001/getFinprodByType', {
                'finprodType': finprodType
            }, function (data) {
                $('#finprodId').empty();//清空之前选项
                $('#finprodId').append(new Option("", ""));//默认值为空
                $.each(data, function (index, item) {
                    $('#finprodId').append(new Option(item.text, item.id));// 下拉菜单里添加元素
                });

            });
        }
       function  inputClear() {

           $('#finprodId').val("");//资产代码
           $('#bondOneType').val("");//债券一级分类（资产类型选择债券时显示）
           $('#bondTwoType').val("");//债券二级分类（资产类型选择债券时显示）
           $('#publisher').val("");//发行人（资产类型选择债券时显示）
           $('#bondRating').val("");//债项评级（资产类型选择债券时显示）
           $('#bondSubjectRating').val("");//主体评级（资产类型选择债券时显示）
           $('#fundType').val("");//基金类型（资产类型选择基金时显示）
           $('#fundTwoType').val("");//基金二级分类（资产类型选择基金时显示）
           $('#manager').val("");//管理人（资产类型选择基金时显示）
           $('#tradingPlace').val(""); //交易场所（资产类型选择股票时显示）
           $('#board').val(""); // 上市版（资产类型选择股票时显示）
           $('#repIndustry').val(""); //行业（资产类型选择股票时显示）
           $('#haveMoney').val(""); //可投金额
       }
        $('#finprodType').on('change', function () {
             getFinprodIdByType($("#finprodType").val());
             if($('#finprodType').val()=='F01')
             {
                 $('#bond').show();
                 $('#bondPJ').show();
              }
             else {
                 $('#bond').hide();
                 $('#bondPJ').hide();
             }
            if($('#finprodType').val()=='F06')
            {
                $('#fund').show();
            }
            else {
                $('#fund').hide();
            }
            if($('#finprodType').val()=='F10')
            {
                $('#stock').show();
            }
            else {
                $('#stock').hide();
            }
            inputClear();

            table.clear(true);
        });
    });
}());