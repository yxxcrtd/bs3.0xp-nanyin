$(function () {
    
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            booksetId : $.trim($('#booksetId').val()),
            booksetName : $("#booksetName").val(),
            acctMode: $("#acctMode").val(),
            schemeType: $("#schemeType").val(),
            schId: $("#schId").val(),
            booksetIdTmpl: $("#booksetIdTmpl").val(),
        }
    };
    
    /**
     * 定义数据表格——结构映射
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url:  "findPage",
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        cols: [
           {field:'booksetId', title: '账套代码'},
           {field:'booksetName', title: '账套简称'},
           {field:'booksetNameTmpl', title: '模板账套'},
           {field:'booksetIdTmpl', title: '模板账套Id', visible: false},
           {field:'acctModeFmt', sortfield: 'acctMode', title: '收益类型'},
           {field:'acctMode', sortfield: 'acctMode', title: '收益类型', visible: false},
           {field:'schemeTypeFmt', sortfield: 'schemeType', title: '方案类型'},
           {field:'schemeType', sortfield: 'schemeType', title: '方案类型', visible: false},
           {field:'schName', sortfield: 'schName', title: '方案名称'},
           {field:'booksetSchemeId', sortfield: 'booksetSchemeId', title: '账套方案代码', visible: false},
        ],
    });
    
    // 查询触发
    $('#ValProdSchemeConfig001_btn_search').click(function() {
        table.load();
    });
    
    /**
     * 导出按钮
     */
    $('#ValProdSchemeConfig001_btn_export').click(function() {
        if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
            var datas = JSON.stringify(selArr);
        }
        if (!selArr || selArr.length == 0) {
            var paramObj = getSearchParams();
            Joyin.exportExcel('export', paramObj);
        } else {
            Joyin.exportExcel('export', {
                datas : datas
            });
        }
    });
    
    // 点击配置按钮，对选中行进行配置
    $('#ValProdSchemeConfig001_btn_config').click(function() {
        var selArr = table.getAllCheckedRows();
        if(!selArr || selArr.length == 0){
            Joyin.error("请至少选择一条记录进行操作。");
            return;
        }
        var schemeType = selArr[0].schemeType;
        var booksetIds = "";
        var booksetIdTmpl = selArr[0].booksetIdTmpl;
        for(var i=0; i<selArr.length; i++){
            // 相同类型校验
            if(schemeType != selArr[i].schemeType){
                Joyin.error("请选择方案类型相同的产品。");
                return;
            }
            // 相同模板校验
            if(booksetIdTmpl != selArr[i].booksetIdTmpl){
                Joyin.error("请选择同一模板下的产品。");
                return;
            }
            // 拼接boosketId,以','隔开
            if(i==0){
                booksetIds = selArr[i].booksetId
            }
            else{
                booksetIds += "," + selArr[i].booksetId;
            }
        }
        Joyin.dialog(JoyinUtil.getLabel("对账方案配置"),
                basePath + "valuation/valuationparametermanage/prodschemeconfig/ValProdSchemeConfig002/init",
                "95%", "90%", {pageModel:PAGE_MODEL_NEW,schemeType : schemeType,booksetIds : booksetIds, booksetIdTmpl:booksetIdTmpl},function(){
                    table.reload();
                });
    });
});
