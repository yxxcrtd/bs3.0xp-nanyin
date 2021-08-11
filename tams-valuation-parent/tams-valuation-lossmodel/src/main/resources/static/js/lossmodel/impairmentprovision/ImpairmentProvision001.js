$(function () {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            prodId: $.trim($('#prodId').val()),
            type9: $.trim($('#type9').val()),
            decDate : $('#decDate').val()
        }
    };

    /**
     * 减值情况计提表
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        checkbox: false,
        isMulti:true,
        loadonce:true,
        page : true,
        cols: [[
            {field:'decDateFmt', sortable: false, width:120, title: '日期',rowspan: 2}
            ,{field:'decDate', sortable: false, width:120, title: '日期', visible:false}
            ,{field:'prodId', sortable: false, width:120, title: '产品代码',rowspan: 2}
            ,{field:'prodName', sortable: false, width:120, title: '产品名称',rowspan: 2}
            ,{field:'type9Fmt', sortable: false, width:120, title: '资产二级分类',rowspan: 2}
            ,{field:'type9', sortable: false, width:120, title: '资产二级分类', visible:false}
            ,{title: '前瞻后减值金额',sortable: false, colspan: 4}
            ,{title: '前瞻后减值计算比例',sortable: false, colspan: 8}
            ,{title: '前瞻后资产余额',sortable: false, colspan: 4}
            ,{title: '前瞻前减值金额',sortable: false, colspan: 4}
            ,{title: '前瞻前减值计算比例',sortable: false, colspan: 8}
            ,{title: '债项数',sortable: false, colspan: 4}
        ],[
            {field:'eclP1', type:"number", digit:4,sortable: false, title: '阶段一',width:100}
            ,{field:'eclP2', type:"number", digit:4,sortable: false, title: '阶段二',width:100}
            ,{field:'eclP3', type:"number", digit:4,sortable: false, title: '阶段三',width:100}
            ,{field:'eclSum', type:"number", digit:4,sortable: false, title: '合计',width:100}
            ,{field:'decRatioP1Fmt', type:"text", sortable: false, title: '阶段一',width:100}
            ,{field:'decRatioP1', type:"number", digit:4,sortable: false, title: '阶段一',width:100, visible:false}
            ,{field:'decRatioP2Fmt', type:"text", sortable: false, title: '阶段二',width:100}
            ,{field:'decRatioP2', type:"number", digit:4,sortable: false, title: '阶段二',width:100, visible:false}
            ,{field:'decRatioP3Fmt', type:"text", sortable: false, title: '阶段三',width:100}
            ,{field:'decRatioP3', type:"number", digit:4,sortable: false, title: '阶段三',width:100, visible:false}
            ,{field:'decRatioSumFmt', type:"text", sortable: false, title: '合计',width:100}
            ,{field:'decRatioSum', type:"number", digit:4,sortable: false, title: '合计',width:100, visible:false}
            ,{field:'decBalanceP1', type:"number", digit:4,sortable: false, title: '阶段一',width:100}
            ,{field:'decBalanceP2', type:"number", digit:4,sortable: false, title: '阶段二',width:100}
            ,{field:'decBalanceP3', type:"number", digit:4,sortable: false, title: '阶段三',width:100}
            ,{field:'decBalanceSum', type:"number", digit:4,sortable: false, title: '合计',width:100}
            ,{field:'perEclP1', type:"number", digit:4,sortable: false, title: '阶段一',width:100}
            ,{field:'perEclP2', type:"number", digit:4,sortable: false, title: '阶段二',width:100}
            ,{field:'perEclP3', type:"number", digit:4,sortable: false, title: '阶段三',width:100}
            ,{field:'perEclSum', type:"number", digit:4,sortable: false, title: '合计',width:100}
            ,{field:'perDecRatioP1Fmt', type:"text", sortable: false, title: '阶段一',width:100}
            ,{field:'perDecRatioP1', type:"number", digit:4,sortable: false, title: '阶段一',width:100, visible:false}
            ,{field:'perDecRatioP2Fmt', type:"text", sortable: false, title: '阶段二',width:100}
            ,{field:'perDecRatioP2', type:"number", digit:4,sortable: false, title: '阶段二',width:100, visible:false}
            ,{field:'perDecRatioP3Fmt', type:"text", sortable: false, title: '阶段三',width:100}
            ,{field:'perDecRatioP3', type:"number", digit:4,sortable: false, title: '阶段三',width:100, visible:false}
            ,{field:'perDecRatioSumFmt', type:"text", sortable: false, title: '合计',width:100}
            ,{field:'perDecRatioSum', type:"number", digit:4,sortable: false, title: '合计',width:100, visible:false}
            ,{field:'bondCountP1', type:"number", digit:4,sortable: false, title: '阶段一',width:100}
            ,{field:'bondCountP2', type:"number", digit:4,sortable: false, title: '阶段二',width:100}
            ,{field:'bondCountP3', type:"number", digit:4,sortable: false, title: '阶段三',width:100}
            ,{field:'bondCountSum', type:"number", digit:4,sortable: false, title: '合计',width:100}
        ]]
    });

    /**
     * 事件绑定：减值情况计提表
     */
    var init = function() {
        /** 事件绑定: 减值情况计提表-查询 */
        $("#ImpairmentProvision001_btn_search").click(function(event){
            var strError = '';
            if(!$('#decDate').val()){
                strError = strError + "请输入查询日期";
            }
            if(strError != '' && strError != null){
                Joyin.error(strError);
                return;
            }
            table.load();//重载表格 - 参数设置配置
        });

        $('#ImpairmentProvision001_btn_export').click(function() {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);

        });
    };
	
	/**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        init();
    });
	
});



