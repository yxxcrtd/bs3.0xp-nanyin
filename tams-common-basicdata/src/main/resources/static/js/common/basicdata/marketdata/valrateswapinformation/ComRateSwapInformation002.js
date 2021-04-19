$(function() {
    var table;
    /**
     * 保存或提交
     *
     * @param operateType 操作类型（save，submit）
     * @returns
     */
    function saveOrSubmit(operateType){
        /**
         * 获取列表参数
         */
        var object = $.serializeToObject('#submitForm');
        var data = table.getAllRows();
        //操作类型
        object.operateType = operateType;
        object.pageModel = pageModel;
        object.validExist = validExist
        object.disabled = disabled
        object.rateSwapInformationDtoList = data
        if($('#curveCode').select2('data')[0].object != undefined){
	        var curveCode = $('#curveCode').val();
	        var curveType = $('#curveCode').select2('data')[0].object.curveType;
	    	object.curveType = curveType;
	        for(var index in object.rateSwapInformationDtoList){
	        	object.rateSwapInformationDtoList[index].curveCode = curveCode;
	        	object.rateSwapInformationDtoList[index].curveType = curveType;
	        	object.rateSwapInformationDtoList[index].curveDate = object.curveDate;
			}
        }

        Joyin.ajaxJson(basePath + 'common/basicdata/marketdata/valrateswapinformation/ValRateSwapInformation002/check', object,function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'common/basicdata/marketdata/valrateswapinformation/ValRateSwapInformation002/save', object, "ValComRateSwapInformation002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'common/basicdata/marketdata/valrateswapinformation/ValRateSwapInformation002/save', object, "ValComRateSwapInformation002_btn_submit");
                }
            });
        });

    }

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
     * 定义显示列
     * @type {*[]}
     */
    var cols = [
        {field:'termTypeIrs', width: '50%', title: 'ValRateSwapInformation001.termTypeIrs', sortable: false},
        {field:'yield', width: '50%', title: 'ValRateSwapInformation001.yield',type:'number',edit:true, sortable: false, digit:4},
    ]

    table=$("#dataTable").joyintable({
        searchOnInit: true,
        checkbox:false,
        cols: cols,
        height: 360,
        page: false,
        data: dataObj,
        done:function(table, allData){
            //数据加载完成后，加载图表
            if (businessId != ""){
                showEcharts()
            }
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
        if ($("#curveCode").val() == ""){
            return
        }
        $("#main").show();
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('main'));
        // 指定图表的配置项和数据
        var option = buildOption();
        myChart.setOption(option);
    }


    function buildData() {
        var code = $('#curveCode').select2('data')[0].object.baseIntCode;
        var dataList = [];
        var newData = JSON.parse(data);
        for (var i = 0; i < newData.length; i++ ){
            if (newData[i].parentId.indexOf(code) != -1) {
                var obj = {};
                obj.termTypeIrs = newData[i].id;
                dataList.push(obj);
            }
        }
        table.reload({data: dataList});
    }

    /**
     * 初始化画面
     */
    function init(){
        $("#main").hide()
        $("#curveCode").attr("disabled",disabled);
        $("#curveDate").attr("disabled",disabled);
    }
    /**
     * 画面加载完成
     */
    $(function() {
        /**
         * 提交
         */
        $('#ValComRateSwapInformation002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });

        /**
         * 保存
         */
        $('#ValComRateSwapInformation002_btn_save').click(function () {
            saveOrSubmit('save');
        });

        $("#ValComRateSwapInformation002_btn_preview").click(showEcharts);
        $("#curveCode").change(buildData)
        init();
        star();
    });
}());
