$(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            secTypeModel : $('#secTypeModel').val(),
            secDetailTypeModel : $('#secDetailTypeModel').val(),
            finprodId : $('#finprodId').val(),
        }
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'ValCashFlowDiscount001search',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
        searchOnInit: false,
		cellMinWidth:100 ,
	    checkbox:true,
        cols : [
                {field : 'seqNo',title : 'seqNo',visible : false},
                { title: "资产类型", field: 'secTypeModelStr',sortField:'secTypeModel',sort : true},
                { title: "资产细分类", field: 'secDetailTypeModelStr',sortField:'secDetailTypeModel',sort : true},
                { title: "资产代码", field: 'finprodId',sort : true},
                { title: "资产名称", field: 'finprodName',sort : true},
                { title: "曲线族", field: 'curveGroupCodeStr',sortField:'curveGroupCode',sort : true},
                { title: "曲线类型", field: 'curveTypeStr',sortField:'curveType',sort : true},
                { title: "是否平滑曲线", field: 'isSmoothStr',sortField:'isSmooth',sort : true},
                { title: "平滑区间", field: 'smoothSectionStr',sortField:'smoothSection',sort : true},
                { field: 'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']}
               ],
        dblclick: function(table, data){
        	Joyin.dialog(JoyinUtil.getLabel("现金流折现模型曲线配置明细"), "/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002/init",
                    "90%", "90%", {seqNo:data.seqNo, pageModel:PAGE_MODEL_REFERENCE,status:"02"}, function(){
                        //table.reload();
                    }
              );
        }
    })
    /**
     * 绑定编辑事件
     */
     table.bind('click','.edit', function(row,index) {
                 row.pageModel = PAGE_MODEL_EDIT;
                    Joyin.dialog("现金流折现模型曲线配置修改",
                           "/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002/init",
                           "90%", "100%", row,function(){
                       table.reload();
                   });
             });
    /**
     * 新建资产大类评级配置
     */
    $('#ValCashFlowDiscount001_btn_add').click(
        function() {
            Joyin.dialog(JoyinUtil.getLabel("新建现金流折现模型曲线配置"), basePath
                    + "valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount002/init", "70%",
                    "75%", null,
                    function(){
                        table.reload();
                        pageModel : PAGE_MODEL_NEW
                    },
                    null
            );
    });

   /**
   *删除
   */
    $('#ValCashFlowDiscount001_btn_delete').click(function() {
       	delRows();
       });
       var delRows = function () {
       	 if(table!=null && table!=''){
                var selArr = table.getAllCheckedRows();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};

            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                                    WorkFlow.doDeleteList('/valmodel/valgeneralfixedincomeassets/cashflowdiscountmanagement/CashFlowDiscount001/delete', object, "ValCashFlowDiscount001_btn_delete", "CashFlowDiscount002", function(){
                                        table.reload();//删除事件请求及回调函数
                                    });
                                });
       };

    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams())
    };

    /**
     * 重置
     * @returns
     */
    function resetPageData() {
        document.location.reload();//当前页面
    }

        /**
         * 查询
         */
        $('#CashFlowDiscount001_btn_search').click(function() {
            table.load();
        });
        /**
         * 导出
         */
        $('#ValCashFlowDiscount001_btn_export').click(function() {
            exportExcel();
        });

    // 绑定重置按钮事件
    $('#ValCashFlowDiscount001_btn_reset').click(function() {
        resetPageData();
    });

     // 资产类别事件
         var assetTypeChange = function() {
         		if ($("#secTypeModel").val().startsWith("F01")) {
         			var itemFwd = [];
         			var TEMP_MODEL_SEC_DETAIL_TYPE = JSON.parse(MODEL_SEC_DETAIL_TYPE);
         			for ( var i in TEMP_MODEL_SEC_DETAIL_TYPE) {
         				if (!TEMP_MODEL_SEC_DETAIL_TYPE.hasOwnProperty(i))
         					continue;
         				if (TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "08"
         						&& TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "09"
         						&& TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "011"
         						&& TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "013"
         						&& TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "023"
         						&& TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "024") {
         					itemFwd.push(TEMP_MODEL_SEC_DETAIL_TYPE[i].id);
         				}
         			}
         			$('#secDetailTypeModel').updateChosenArr({
         				item : itemFwd,
         				disabled : false
         			});

         		} else if ($("#secTypeModel").val().startsWith("F18")) {
         			var itemFwd = [];
         			var TEMP_MODEL_SEC_DETAIL_TYPE = JSON.parse(MODEL_SEC_DETAIL_TYPE);
                     for ( var i in TEMP_MODEL_SEC_DETAIL_TYPE) {
                         if (!TEMP_MODEL_SEC_DETAIL_TYPE.hasOwnProperty(i))
                             continue;
                         if (TEMP_MODEL_SEC_DETAIL_TYPE[i].id == "08"
                                 || TEMP_MODEL_SEC_DETAIL_TYPE[i].id == "09") {
                              itemFwd.push(TEMP_MODEL_SEC_DETAIL_TYPE[i].id);
                         }
                     }
         			$('#secDetailTypeModel').updateChosenArr({
         				item : itemFwd,
         				disabled : false
         			});
         		}
         	};

     $(function() {
            // 资产类别onchange事件
            $("#secTypeModel").on('change',function(){
                         assetTypeChange();
                    });
        });
});