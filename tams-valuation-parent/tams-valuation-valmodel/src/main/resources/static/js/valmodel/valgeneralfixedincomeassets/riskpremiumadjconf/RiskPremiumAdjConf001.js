$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            assetType: $.trim($('#assetType').val()),
            assetId: $.trim($('#assetId').val()),
        }
    };

    /**
     * 风险溢价调整信息
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit: false,
        cellMinWidth:100 ,
        checkbox:true,
        cols: [
            {field:'seqno',width:120, title: '流水号', visible:false},
            {field:'finprodId', sortField: "finprodId",width:200, title: '资产代码'},
            {field:'finprodName', sortField: "finprodName",width:230, title: '资产名称'},
            {field:'secTypeModelFmt', sortField: "secTypeModel",width:150, title: '资产类型'},
            {field:'secTypeModel',width:120, title: '资产类型', visible:false},
            {field:'gradeSourceFmt', sortField: "gradeSource",width:150, title: '评级来源'},
            {field:'gradeSource',width:120, title: '评级来源', visible:false},
            {field:'gradeResultFmt', sortField: "gradeResult",width:150, title: '评级'},
            {field:'gradeResult',  width:200, title: '评级', visible:false},
            /** modify by yanya on 20210823 调整基准日为定价日并增加曲线名称、曲线日期 start**/
            {field:'basedateFmt', sortField: "basedate",width:150, title: '定价日'},
            {field:'basedate',width:120, title: '定价日', visible:false},
            {field:'curveName', sortField: "curveName",width:180, title: '曲线名称'},
            {field:'curveCode',width:120, title: 'curveCode', visible:false},
            {field:'curveDateFmt', sortField: "curveDate",width:150, title: '曲线日期'},
            {field:'curveDate',width:120, title: 'curveDate', visible:false},
            /** modify by yanya on 20210823 调整基准日为定价日并增加曲线名称、曲线日期 end**/
            {field:'spreadFmt', width:150, title: '风险溢价%'},
            {field:'spread', type:"number", digit:6, width:140, title: '风险溢价%', visible:false},
            {field:'spreadAdjFmt', width:150, title: '风险溢价调整%'},
            {field:'spreadAdj', type:"number", digit:6, width:140, title: '风险溢价调整%', visible:false},
            {field:'operation', title: 'SysCommon.operation', width:120, type:'action', fixed: 'right',
                buttons: ['edit']
            }
        ],
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("风险溢价调整信息"),
                basePath + "valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_REFERENCE,  seqno: data.seqno, operateType: "view"},function(){
                });
        }
    });

    /**
     * 事件绑定：风险溢价调整信息
     */
    var init = function() {
        /** 事件绑定: 风险溢价调整信息-查询 */
        $("#RiskPremiumAdjConf001_btn_search").click(function(event){
            table.load();//重载表格 - 参数设置配置
        });

        /**
         * 绑定事件：减值管理信息 - 编辑
         */
        table.bind('click', '.edit', function(data, index) {
            data.operateType = 'edit';
            Joyin.dialog(JoyinUtil.getLabel("修改风险溢价调整信息"),
                basePath + "valmodel/valgeneralfixedincomeassets/riskpremiumadjconf/RiskPremiumAdjConf002/init",
                "85%", "85%", {pageModel:PAGE_MODEL_EDIT, seqno: data.seqno, operateType: "edit"},function(){
                table.reload();
            });
        });
    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        $("#RiskPremiumAdjConf001_btn_export").hidden;
        // 绑定按钮事件
        init();
    });
	
});



