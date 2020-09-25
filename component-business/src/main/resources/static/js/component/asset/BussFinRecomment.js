/**
 * 资产推荐方及费率信息组件JS
 * @auth renping
 */
(function(){
    /**
     * 禁止重复reload table
     */
    var doneFlag =false;
    var initFlag = true;
    var getBussFinRecommentElementById = function(id,type) {
        if(type=='div'){
            return $('#' + entityName_BussFinRecomment + '_' + id+'_'+type);
        }else{
            return $('#' + entityName_BussFinRecomment + '_' + id);
        }
    };
    
    /**
     * 定义数据表格
     */
    var cols = [ {
            field : 'recommender',
            title : 'BussFinRepayment.recommender',
            width : '35%',
            edit : true,
            sortable : false,
            editOption:{type:'select', id:'recommender', multiple: false, showField:'text',searchable: true,
                url: JoyinUtil.getRealPath('/component/asset/BussFinRecomment/qryRecommenderSelect')
            }
        }, {
            field : 'recfeeRate',
            title : 'BussFinRepayment.recfeeRate',
            width : '35%',
            edit : true,
            sortable : false,
            type : 'number',
            digit:4
        }, {
            field : 'operation',
            title : 'SysCommon.operation',
            width : '30%',
            type : 'action',
            buttons : [ 'delt' ]
        } // 编辑和删除事件
        ];
    var table =getBussFinRecommentElementById("bussFinRecommentTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/component/asset/BussFinRecomment/initInfo'),
            type: 'POST',
            data: getPreviewParams
        },
        cols:cols,
        searchOnInit: true,
        loadonce:true,
        checkbox:false,
        addToLast:true,
        height:200,
        page:false,
        addbtn:true,
        contextmenu : false,
        editCell : function(value, data, field, cell, column){
            // 推荐方重复check
            if(field=='recommender'){
            	var allRows = getAllRows();
            	$.each(allRows,function(index,obj){
            		if(index != data.LAY_TABLE_INDEX && obj.recommender == data.recommender){
            			Joyin.error(JoyinUtil.getMessage('FIN_ASSET_E_0009', [JoyinUtil.getLabel('BussFinRepayment.recommender')]),function(){
                        	data.recommender  = '';
                            table.renderRow(data.LAY_TABLE_INDEX, data);
                        });
            		}
            	});
            } else if(field=='recfeeRate'){
              if(typeof FinInterestFactor !== 'undefined') {
              	// 计算买断利率
              	FinInterestFactor.calBuyOutRateByTable(data, table);
              }
            }
        },
        done:function () {
        	if(initFlag){
        		initFlag = false;
            } else {
                if(typeof FinInterestFactor !== 'undefined') {
                  	// 计算买断利率
                  	FinInterestFactor.calBuyOutRateByTable();
                }
            }
        }
    });
        
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        table.removeRow(data);
    });
    
    var getAllRows = function(){
            return table.getAllRows();
    }
    
    /**
     * 初始化列表
     */
    var initGenerationTable = function(){
        table.table ? table.reload() : table.load();
    }
    
    /**
     * 获取参数
     * @returns
     */
    function getPreviewParams() {
        var param = {}
        param.finprodId = $("#finprodId").val();
        param.workflowId = $("#workflowId").val();
        param.isTodoList = isTodoList;
        param.entityName = entityName_BussFinRecomment;
        return param;
    }
    
    // 公开接口
    window.BussFinRecomment = {
            getAllRows:getAllRows,
            initGenerationTable:initGenerationTable
    };
    
    $(function(){
    });
}());