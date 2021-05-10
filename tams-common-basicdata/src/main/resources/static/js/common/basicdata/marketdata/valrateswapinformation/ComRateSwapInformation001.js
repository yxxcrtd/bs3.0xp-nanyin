$(function() {

    var table;
    var curveCodeVal;
    var curveDateVal;

    /**
     * 获取查询参数
     */
    var searchform = function () {
        var data = $.serializeToObject('#searchForm');
        return data;
    };

    /**
     * 获取图表Title
     */
    function getTitle(){
        var title = $("#curveCode").find("option:selected").text();
        return title;
    }
    /**
     * 获取标准期限
     */
    function getTermTypeIrs(){
        var data = table.getAllRows();
        var termTypeIrs = [];
        for(var i = 0; i < data.length; i++){
            termTypeIrs.push(data[i].termTypeIrs);
        }
        return termTypeIrs;
    }
    /**
     * 获取收益率
     */

    function getYield(){
        var data = table.getAllRows();
        var yieldPirce = [];
        for(var i = 0; i < data.length; i++){
        	yieldPirce.push(data[i].yield);
        }
        return yieldPirce;
    }


    /**
     * 定义显示列
     * @type {*[]}
     */
    var cols = [
        {field:'curveCode', visible: false},
        {field:'curveDate', visible: false},
        {field:'termTypeIrs', width: '50%', title: 'ComRateSwapInformation001.termTypeIrs', sortable: false},
        {field:'yield', width: '50%', title: 'ComRateSwapInformation001.yield',type:'number',edit:true, sortable: false, digit:4},
    ]

    table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function () {
                return searchform();
            }
        },
        searchOnInit: false,
        checkbox:false,
        height: 360,
        cols: cols,
        page: false,
        done: function(table, allData){
            //数据加载完成后，加载图表
            $("#main").hide();
            if (table.length > 0){
                showEcharts();
            }
            curveCodeVal = $("#curveCode").val();
            curveDateVal = $("#curveDate").val();
        },
    })


    /**
     * 图标展示
     */
    function buildOption() {
        var option = {
            title: {
                text: getTitle(),
                left:'center'
            },
            tooltip : {
                trigger: 'axis'
            },
            legend: {
                data:['收益率'],
                top:'bottom'
            },
            calculable : true,
            xAxis : [
                {
                    type : 'category',
                    boundaryGap : false,
                    data : getTermTypeIrs()
                }
            ],
            yAxis : [
                {
                    type : 'value'
                }
            ],
            series : [
                {
                    name: '收益率',
                    type: 'line',
                    data: getYield()
                }
            ]
        };

        return option;
    }

    function showEcharts(){
        if ($("#curveCode").val() == "" || $("#curveDate").val() == ""){
            return
        }
        $("#main").show();
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = buildOption();
        myChart.setOption(option);
    }

    /**
     * 画星星
     */
    function star() {
        // 加红星星
        // 查找label
        var _findPrevLabel = function (element, level) {
            var $el = element.prev();
            if (!$el.is('label') && level > 0) {
                $el = $el.find('label');
                if ($el.length === 0) {
                    return _findPrevLabel(element.parent(), level - 1);
                }
            }
            return $el;
        };

        // 非查看模式
        if (typeof pageModel !== 'undefined'
            && (pageModel === PAGE_MODEL_EDIT || pageModel === PAGE_MODEL_NEW)) {
            $('.must').each(function () {
                $(this).remove();
            });
            $('*[required]').each(function () {
                var $el = $(this);
                var $target = _findPrevLabel($el, 3);
                if ($target.find('> .must').length === 0) {
                    $target.append($('<i class="must">*</i>'));
                }
            });
        }
    }
    /**
     * 导出
     */
    var exportExcel = function () {
        Joyin.exportExcel(basePath + "common/basicdata/marketdata/valrateswapinformation/ComRateSwapInformation001/export", searchform());
    };
    /**
     * 初始化画面
     */
    function init(){
        $("#main").hide();
    }

    /**
     * 画面加载完成
     */
    $(function() {
        $("#ComRateSwapInformation001_btn_add").click(function () {
            Joyin.dialog(JoyinUtil.getLabel("利率互换行情信息新建"),
                basePath + "common/basicdata/marketdata/valrateswapinformation/ComRateSwapInformation002/init",
                "98%", "98%");
        });

        $("#ComRateSwapInformation001_btn_del").click(function () {
            if(table!=null && table!=''){
                var selArr = table.getAllRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("MST_RateSwapInfo_0008"));
                return;
            }
            var object = {};
            object.rateSwapInformationDtoList = selArr
            object.operateType = "delete"
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/valrateswapinformation/ComRateSwapInformation001/check', object, function (result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")), function () {
                    WorkFlow.doDeleteList(basePath + 'common/basicdata/marketdata/valrateswapinformation/ComRateSwapInformation001/delete', object,
                        "ComRateSwapInformation001_btn_del", "ComRateSwapInformation002", function () {
                            table.reload();
                        });
                });
            });
        })
        $("#ComRateSwapInformation001_btn_edit").click(function () {
            if(table!=null && table!=''){
                var selArr = table.getAllRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("MST_RateSwapInfo_0003"));
                return;
            }

            var selArr = table.getAllRows();
            var object = {};
            object.curveCode = curveCodeVal;
            object.curveDate = curveDateVal;
            object.rateSwapInformationDtoList = selArr;
            object.operateType = "edit";
            object.pageModel = PAGE_MODEL_EDIT;
            Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/valrateswapinformation/ComRateSwapInformation001/check', object, function (result) {
                Joyin.dialog(JoyinUtil.getLabel("ComRateSwapInformation002.edit"),
                    JoyinUtil.getRealPath("common/basicdata/marketdata/valrateswapinformation/ComRateSwapInformation002/init"),"98%", "95%", object,
                    function () {
                        table.reload();
                    }
                );
            });
        })


        /**
         * 查询按钮
         */
        $('#ComRateSwapInformation001_btn_search').click(function () {
            if ($("#curveCode").val() == ""){
                Joyin.error(JoyinUtil.getMessage("MST_RateSwapInfo_0004"));
                return;
            }
            if ($("#curveDate").val() == ""){
                Joyin.error(JoyinUtil.getMessage("MST_RateSwapInfo_0005"));
                return;
            }
            table.load();
        });
        /**
         * 导出按钮
         */
        $('#ComRateSwapInformation001_btn_export').click(function () {
            if ($("#curveCode").val() == ""){
                Joyin.error(JoyinUtil.getMessage("MST_RateSwapInfo_0004"));
                return;
            }
            if ($("#curveDate").val() == ""){
                Joyin.error(JoyinUtil.getMessage("MST_RateSwapInfo_0005"));
                return;
            }
            exportExcel();
        });
        /**
         * 导入按钮
         */
        $('#ComRateSwapInformation001_btn_import').click(function() {
            Joyin.uploadFile({pageId:'ComRateSwapInformation001MTM'},function(fileName) {
                Joyin.ajax(basePath + 'common/basicdata/marketdata/valrateswapinformation/ComRateSwapInformation001/checkImportData', {fileName: fileName}, function(checkResult) {
                    if (checkResult) {
                        setTimeout( function () {
                            if(checkResult.data.errorMsg){
                                Joyin.error(checkResult.data.errorMsg);
                                return;
                            }
                            if (checkResult.data.confirmMsg) {
                                Joyin.confirm(checkResult.data.confirmMsg, function () {
                                    var data = {
                                        tempList: checkResult.data.correctData
                                    }
                                    Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/valrateswapinformation/ComRateSwapInformation001/importFile', data,
                                        function (resultObj) {
                                            if (resultObj.statuCode.toString() === '200') {
                                                /** modify by yanya on 20210329 修正导入逻辑**/
                                                //Joyin.alert("文件导入成功！");
                                                table.reload();
                                                return;
                                            }
                                        });
                                }, {}, function () {
                                    layui.layer.closeAll()
                                    table.reload();
                                });
                            }else {
                                var data = {
                                    tempList: checkResult.data.correctData
                                }
                                Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/valrateswapinformation/ComRateSwapInformation001/importFile', data,
                                    function (resultObj) {
                                        if (resultObj.statuCode.toString() === '200') {
                                            /** modify by yanya on 20210329 修正导入逻辑**/
                                            //Joyin.alert("文件导入成功！");
                                            table.reload();
                                            return;
                                        }
                                    });
                            }
                        },1000);
                    }
                });
            },function() {
                table.reload();
            });
        });
        init();
        star();
    });
}());
