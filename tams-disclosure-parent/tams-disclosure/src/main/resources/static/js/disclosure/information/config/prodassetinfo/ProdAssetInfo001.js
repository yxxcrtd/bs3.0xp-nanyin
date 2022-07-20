$(function(){
	
	/**
     * 获取查询参数
     */
	var getSearchParams = function() {
        return {
            prodId : $('#prodId').val()==null?null:$('#prodId').val().join(","),
            effdateStart : $('#effDate').startDate(),
            effdateEnd : $('#effDate').endDate(),
            effEndDateStart : $('#effEndDate').startDate(),
            effEndDateEnd : $('#effEndDate').endDate(),
            dataStatus: $('#dataStatus').val()
        };
    };

    /**
     * 金额格式化: 框架数据表格 render 后
     */
    var formatMoney = function (s, n)
    {
        n = n > 0 && n <= 20 ? n : 2;
        s = parseFloat((s + '').replace(/[^\d\.-]/g, '')).toFixed(n) + '';
        var l = s.split('.') [0].split('').reverse(),
            r = s.split('.') [1];
        var t = '';
        for (var i = 0; i < l.length; i++)
        {
            t += l[i] + ((i + 1) % 3 == 0 && (i + 1) != l.length ? ',' : '');
        }
        return t.split('').reverse().join('') + '.' + r;

    }
	
    /**
     * 表格数据查询
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        /*
         * 双击查看数据
         */
        dblclick:function(table, data) {
            data.pageModel=PAGE_MODEL_REFERENCE;
            Joyin.dialog(JoyinUtil.getLabel("ProdAssetInfo001"),
                    JoyinUtil.getRealPath("disclosure/information/config/prodassetinfo/ProdAssetInfo002/init"),
                    "98%", "95%", data,
                    function(){table.reload();});
        },
         cols:[
               {field:'prodAssetId', visible: false}
              ,{field:'statusStr', width:'10%', sortField:'dataStatus', title: '数据状态'}
              ,{field:'prodId', width:'10%', sortField:'prodId', title: '产品主代码'}
              ,{field:'prodName', width:'10%', sortField:'prodName', title: '产品名称'}
              ,{field:'fixPreRatio', width:'10%', title: '穿透前固定收益类资产占总资产比例',render: function (data, type, row, meta) {
                     if(data == '') {
                         return '';
                     }
                     return formatMoney(data, 2);
                 }}
              ,{field:'fixAfterRatio', width:'10%', title: '穿透后固定收益类资产占总资产比例',render: function (data, type, row, meta) {
                     if(data == '') {
                         return '';
                     }
                     return formatMoney(data, 2);
                 }}
              ,{field:'equityPreRatio', width:'10%', title: '穿透前权益类资产占总资产比例',render: function (data, type, row, meta) {
                     if(data == '') {
                         return '';
                     }
                     return formatMoney(data, 2);
                 }}
              ,{field:'equityAfterRatio', width:'10%', title: '穿透后权益类资产占总资产比例',render: function (data, type, row, meta) {
                     if(data == '') {
                         return '';
                     }
                     return formatMoney(data, 2);
                 }}
              ,{field:'fdPreRatio', width:'10%', title: '穿透前商品及金融衍生品类资产占总资产比例',render: function (data, type, row, meta) {
                     if(data == '') {
                         return '';
                     }
                     return formatMoney(data, 2);
                 }}
              ,{field:'fdAfterRatio', width:'10%', title: '穿透后商品及金融衍生品类资产占总资产比例',render: function (data, type, row, meta) {
                     if(data == '') {
                         return '';
                     }
                     return formatMoney(data, 2);
                 }}
              ,{field:'mixPreRatio', width:'10%', title: '穿透前混合类资产占总资产比例',render: function (data, type, row, meta) {
                     if(data == '') {
                         return '';
                     }
                     return formatMoney(data, 2);
                 }}
              ,{field:'mixAfterRatio', width:'10%', title: '穿透后混合类资产占总资产比例',render: function (data, type, row, meta) {
                     if(data == '') {
                         return '';
                     }
                     return formatMoney(data, 2);
                 }}
              ,{field:'effDateStr',width:'10%' , sortField:"effDate", title: '生效日',type:'date'}
              ,{field:'effEndDateStr',width:'10%' , sortField:"effEndDate", title: '生效截止日期',type:'date'}
              ,{field: 'updateTime', type: 'datetime',width:'15%', sortable:false,title: "更新时间"}
              ,{field:'updateUser', width:'10%', sortField:'updateUser', title: '更新人'}
              ,{field:'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
          ]
    });
        
    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        Joyin.dialog(JoyinUtil.getLabel("编辑"),
                    basePath + "disclosure/information/config/prodassetinfo/ProdAssetInfo002/init",
                    "95%", "90%", {pageModel:PAGE_MODEL_EDIT,
                prodAssetId:data.prodAssetId},function(){table.reload();})
    });
    
    
  /**
     *绑定删除事件
    */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        // Joyin.ajaxJson(basePath + 'disclosure/information/config/prodpartner/prodassetinfo001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'disclosure/information/config/prodassetinfo/ProdAssetInfo001/delete', object, "ProdAssetInfo001_btn_delete", "ProdAssetInfo002",function(){
                    table.reload();
                });
            });
        // });
    });

    /*文件导出*/
    var exportExcel = function() {
    	if (table != null && table != '') {
            var selArr = table.getAllCheckedRows();
         }
    	var param = getSearchParams();
         if (!selArr || selArr.length == 0) {
         } else {
        	 param.keyList = JSON.stringify(selArr);
         }
        Joyin.exportExcel('export', param);
    };

    $(function() {
        // 绑定按钮事件
        $('#ProdAssetInfo001_btn_search').click(function() {
            table.load();
            $('#ProdAssetInfo001_btn_delete').show();
        });

        /**
         *导出按钮
         */
        $('#ProdAssetInfo001_btn_export').click(function() {
            exportExcel();
        });

        /**
         * 导入按钮
         */
        $('#ProdAssetInfo001_btn_import').click(function() {
            Joyin.confirm(JoyinUtil.getLabel("请确认是否要导入数据？"), function() {
                Joyin.uploadFile(function(fileName) {
                    Joyin.ajax(basePath +"disclosure/information/config/prodassetinfo/ProdAssetInfo001/importFile", {
                        fileName : fileName
                    },function(result){
                        if(!result || !($.isArray(result) && result.length > 0)) {
                            layui.layer.closeAll()
                            table.reload();
                        }
                    });
                });
            });
        });

        $('#ProdAssetInfo001_btn_add').click(
                function() {
                    Joyin.dialog(JoyinUtil.getLabel("产品资产组合新建"), basePath
                            + "/disclosure/information/config/prodassetinfo/ProdAssetInfo002/init",
                            "100%", "90%", {pageModel : PAGE_MODEL_NEW},function(){table.reload();});
                });
        $('#ProdAssetInfo001_btn_delete').click(function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {
                selectedItem : selArr,
                operateType : "delete"
            };
            // Joyin.ajaxJson(basePath + 'disclosure/information/config/prodpartner/ProdPartner001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001", JoyinUtil.getLabel("SysCommon.delete")),
                    function() {
                        WorkFlow.doDeleteList(basePath
                            + 'disclosure/information/config/prodassetinfo/ProdAssetInfo001/delete'
                            ,object,"ProdAssetInfo001_btn_delete","ProdAssetInfo002",function(){table.reload();});
                    });
            // });
        });
    });
})
