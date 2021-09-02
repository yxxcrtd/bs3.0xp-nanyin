$(function() {

    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            assetType : $('#assetType').val(),
            assetDetailType : $('#assetDetailType').val()
        }
    };
/**
 * 表格加载
 */
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'ValAssetCategories001search',
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
                { title: "资产类别", field: 'assetTypeStr',sortField:'assetType',sort : true},
                { title: "资产分类", field: 'assetDetailTypeStr',sortField:'assetDetailType',sort : true},
                { title: "评级类型", field: 'oTypeStr',sortField:'oType',sort : true},
                { title: "评级机构类型", field: 'gradeOrgTypeStr',sortField:'gradeOrgType',sort : true},
                { title: "评级机构", field: 'gradeOrgIdStr',sortField:'gradeOrgId',sort : true},
                { field: 'operation', title: 'SysCommon.operation', type:'action', buttons: ['edit']}
               ],
        dblclick: function(table, data){
        	Joyin.dialog(JoyinUtil.getLabel("资产大类评级配置明细"), "/model/impairment/assetratingallocation/assetcategories/AssetCategories002/init",
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
                    Joyin.dialog("资产大类评级配置修改",
                           "/model/impairment/assetratingallocation/assetcategories/AssetCategories002/init",
                           "90%", "100%", row,function(){
                       table.reload();
                   });
             });
    /**
     * 新建资产大类评级配置
     */
    $('#ValAssetCategories001_btn_add').click(
        function() {
            Joyin.dialog(JoyinUtil.getLabel("新建资产大类评级配置"), basePath
                    + "model/impairment/assetratingallocation/assetcategories/AssetCategories002/init", "90%",
                    "90%", null,
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
    $('#ValAssetCategories001_btn_delete').click(function() {
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
                    WorkFlow.doDeleteList('/model/impairment/assetratingallocation/assetcategories/AssetCategories001/delete', object, "ValAssetCategories001_btn_delete", "AssetCategories002", function(){
                        table.reload();//删除事件请求及回调函数
                    });
                });
       };

    var exportExcel = function(){
        Joyin.exportExcel('export',getSearchParams())
    };

    // 资产类别事件
        var assetTypeChange = function() {
        		if ($("#assetType").val().startsWith("F01")) {
        			var itemFwd = [];
        			var TEMP_MODEL_SEC_DETAIL_TYPE = JSON.parse(MODEL_SEC_DETAIL_TYPE);
        			for ( var i in TEMP_MODEL_SEC_DETAIL_TYPE) {
        				if (!TEMP_MODEL_SEC_DETAIL_TYPE.hasOwnProperty(i))
        					continue;
        				if (TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "08"
        						&& TEMP_MODEL_SEC_DETAIL_TYPE[i].id != "09") {
        					itemFwd.push(TEMP_MODEL_SEC_DETAIL_TYPE[i].id);
        				}
        			}
        			$('#assetDetailType').updateChosenArr({
        				item : itemFwd,
        				disabled : false
        			});

        		} else if ($("#assetType").val().startsWith("F18")) {
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
        			$('#assetDetailType').updateChosenArr({
        				item : itemFwd,
        				disabled : false
        			});
        		}
        	};

        /**
         * 查询
         */
        $('#AssetCategories001_btn_search').click(function() {
            table.load();
        });
        /**
         * 导出
         */
        $('#ValAssetCategories001_btn_export').click(function() {
            exportExcel();
        });

         // 资产类别onchange事件
        $("#assetType").on('change',function(){
                     assetTypeChange();
                });
});