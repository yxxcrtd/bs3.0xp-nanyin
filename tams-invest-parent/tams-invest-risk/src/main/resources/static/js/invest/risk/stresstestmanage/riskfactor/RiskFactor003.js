$(function () {

    function init() {
        if (entity1 != null) {
            $('#investtype1').val(entity1.type1code).trigger("change");
            var dicode = "";
            if ($('#investtype1').val() != null && $('#investtype1').val() != '' && $('#investtype1').val() != '01') {
                dicode = 'FTR_RSK_TYPE_' + $('#investtype1').val();
            }
            resetInvestType2(dicode, function(){
                $('#investtype2').val(entity1.type2code);
                resetMrkFtrType2(entity1.type1code, entity1.type2code);
                $('#mrkFtrType2').val(entity1.ftrtype2);
                setTimeout(function(){
                    resetTwoClass(entity1.type1code, entity1.type2code, entity1.ftrtype2, entity1.ftrid);
                },50);
            });

            $('#FTRVALUESTRUCT').val(entity1.ftrvaluestruct1).trigger("change");

            $('#FTRSTATUS').val(entity1.effstatus1).trigger("change");
            if (entity1.sdate != "undefined" && entity1.sdate != null) {

                $('#updateTime').val(entity1.sdate + ' - ' + entity1.edate);

            }
        }

    }

    var exportExcel = function () {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    var getSearchParams = function () {

      //  var ctype = $('#ctype').val();

        var investtype1 = $('#investtype1').val();
        var investtype2 = $('#investtype2').val();
        var twoClass = $('#twoClass').val();
        var FTRVALUESTRUCT = $('#FTRVALUESTRUCT').val();
        var FTRSTATUS = $('#FTRSTATUS').val();

        var stDate = $('#updateTime').startDate();
        var edDate = $('#updateTime').endDate();
        return {
          //  ctype: ctype,
            investtype1: investtype1,
            investtype2: investtype2,
            twoClass: twoClass,
            FTRVALUESTRUCT: FTRVALUESTRUCT,
            FTRSTATUS: FTRSTATUS,
            stDate: stDate,
            edDate: edDate
        }

     /*   return  $.serializeToObject('#searchForm');*/


    };

    var oneTable = $("#oneTable").joyintable({
        ajax: {
            url: basePath + '/invest/risk/stresstestmanage/riskfactor/RiskFactor003/findOnePage',
            type: 'POST',
            data: function () {
                // return getSearchParams();


                var obj = getSearchParams();
                if($('.layui-border-box').length == 0){
                    obj.investtype2 =entity1.type2code;
                    obj.twoClass=entity1.ftrid;
                }
                if(obj.twoClass==null||obj.twoClass==''){
                    Joyin.error("由于历史数据量较大，请指定单一风险因子做查询！");
                    return;
                }

                return obj;
            }
        },

        loadonce:false,
        searchOnInit:true,
        cols: [
            {field: 'type1', width: 100, title: '风险分类',  sortField:'type1' }
            , {field: 'type2', width: 100, title: '因子分类', sortField:'type2'}
            , {field: 'ftrType2Name', width: 125, title: '因子二级分类', sortField: 'ftrtype2'}
            , {field: 'ftrid', width: 150, title: '因子代码', sortField:'ftrid'}
            , {field: 'ftrname', width: 160, title: '因子名称', sortField:'ftrname'}
            , {field: 'ftrvaluestruct', width: 125, title: '因子类型',sortField:'ftrvaluestruct'}
            , {field: 'valueexetype', width: 125, title: '变动形式',sortField:'valueexetype'}
            , {field: 'serdate', width: 150, sortField: "effstatus1", title: '日期', sortable: false}
            , {field: 'keyvalue', width: 110, title: '关键点(年)', sortable: false}
            , {field: 'yvalue', width: 110, title: '数值', sortable: false}
            , {field: 'data', width: 110, title: '数据来源', sortField:'data'}
        ]

    });


    /**
     * 画面加载完成
     */
    $(function () {
        init();
        ftrType2List = ftrType2List.map(function(item) {
            return {
                col: "FTR_RSK_TYPE2",
                id: item.ftrType2Code,
                text: item.ftrType2Value,
                type1Id:item.ftrTypeCode
            };
        });
       // $('#investtype1').trigger("change");

        $('#RiskFactor001_btn_filter').click();

        //新建
        $('#RiskFactor001_btn_add').click(function () {
            showDialogOne();
        });

        $('#AssetPool001_btn_access').click(function () {
            showDialogTwo();
        });
        $('#RiskFactor003_btn_export').click(function () {
            exportExcel();
        });
        $('#RiskFactor003_btn_search').click(function () {
            oneTable.load();//加载表格
        });
   /*     if (entity1 != null) {

            oneTable.load();//加载表格
        }*/
        $("#investtype1").change(function () {
            var dicode = "";
            if ($('#investtype1').val() != null && $('#investtype1').val() != '' && $('#investtype1').val() != '01') {
                dicode = 'FTR_RSK_TYPE_' + $('#investtype1').val();
            }
            resetTwoClass($('#investtype1').val(), null, null);
            resetInvestType2(dicode);
            resetMrkFtrType2($('#investtype1').val(), "");

        });
//        $('#investtype1').trigger("change");
        $("#investtype2").change(function () {

            resetTwoClass($('#investtype1').val(), $('#investtype2').val(), null);
            resetMrkFtrType2("", $('#investtype2').val());
            /*$("#twoClass").attr('data-url','/tams/invest/risk/stresstestmanage/riskfactor/RiskFactor001/selectRiskFactorMap/'+value+'/'+value2);
            clearBasicCondition();*/
        });
        $("#mrkFtrType2").change(function () {
            resetTwoClass($('#investtype1').val(), $('#investtype2').val(), $('#mrkFtrType2').val());
        });

    });

        //重设因子分类下拉值
        function resetInvestType2(dicode, callbackFun){
            Joyin.ajax(JoyinUtil.getRealPath('/invest/risk/stresstestmanage/riskfactor/RiskFactor001/selectRiskFactorType1/'), {dicode: dicode},function (result) {
                if (result) {
                    var sceneOptArray = result;
                    var option = "";
                    for (var i = 0; i < sceneOptArray.length; i++) {
                        option += '<option value ="' + sceneOptArray[i].id + '">' + sceneOptArray[i].text + '</option>';
                    }
                    $('#investtype2').empty();
                    $("#investtype2").append('<option value =""></option>' + option);
                    if(typeof callbackFun === "function"){
                        callbackFun();
                    }
                }

            })
        }

        //重设因子名称下拉值
        function resetTwoClass(investType1Val, investType2Val, mrkFtrType2, defaultVal){
            Joyin.ajax(JoyinUtil.getRealPath('/invest/risk/stresstestmanage/riskfactor/RiskFactor001/selectRiskFactorMap2/'), {
                type1: investType1Val,
                type2: investType2Val,
                type3: mrkFtrType2
            },
            function (result) {
                if (result) {
                    var sceneOptArray = result;
                    var option = "";
                    for (var i = 0; i < sceneOptArray.length; i++) {
                        option += '<option value ="' + sceneOptArray[i].id + '">' + sceneOptArray[i].text + '</option>';
                    }
                    $('#twoClass').empty();
                    $("#twoClass").append('<option value =""></option>' + option);
                    if(defaultVal != undefined && defaultVal != null && defaultVal != ""){
                        $('#twoClass').val(defaultVal);
                    }
                }

            })
        }

        //重设因子二级分类下拉值
        function resetMrkFtrType2(type1Val, type2Val){
            var list_tmp = [];
            if(type2Val==""){
                list_tmp = ftrType2List;
            }else{
                list_tmp = ftrType2List.filter(function(item){
                    return item.id.indexOf(type2Val) == 0;
                });
            }
            var option = "";
            for (var i = 0; i < list_tmp.length; i++) {
                option += '<option value ="' + list_tmp[i].id + '">' + list_tmp[i].text + '</option>';
            }
            $('#mrkFtrType2').empty();
            $("#mrkFtrType2").append('<option value =""></option>' + option);

            var strIndex = type2Val.indexOf("Lqu");
            if(type1Val == '02' || strIndex == 0){
                $('#mrkFtrType2').prop('disabled', true).trigger('change');
            }else{
                $('#mrkFtrType2').prop('disabled', false).trigger('change');
            }
        }


}());
