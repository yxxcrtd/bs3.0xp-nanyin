/**
 * 评级信息组件JS
 * @auth zhangnian
 */
(function(){
    var getElementById = function(id) {
        return $('#' + entityName_BusiFinBondRatingInfo + '_' + id);
    };
    //初始化
        /**
         * 定义数据表格
         */
    var cols = [
            {field:'oType', sortable:false, title: 'BusiFinBondRatingInfo.gradeType',editOption:{type:'select', multiple: false,result: MAIN_GRADE_TYPE},edit:true}
            ,{field:'shortLongTerm', sortable:false, title: 'BusiFinBondRatingInfo.shortLongTerm',editOption:{type:'select', multiple: false,result: LONG_SHORT_TERM},edit:true}
            ,{field:'gradeResult', sortable:false, title: 'BusiFinBondRatingInfo.gradeResult',editOption:{type:'select', multiple: false,result: MAIN_GRADE_RESULT},edit:true}
            ,{field:'gradeDate', sortable:false, title: 'BusiFinBondRatingInfo.gradeDate',edit:true,type:'date'}
            ,{field:'gradeOrgId', sortable:false, title: 'BusiFinBondRatingInfo.gradeOrgId',editOption:{type:'select', multiple: false,result: GRADE_PARTY},edit:true}
            ,{field:'operation', sortable:false, title: 'SysCommon.operation', type:'action', buttons: ['delt']}//编辑和删除事件
        ]
    var table =$("#busiFinBondRatingInfoTable").joyintable({
        ajax: {
            url: JoyinUtil.getRealPath('/component/bond/BusiFinBondRatingInfo/initInfo'),
            type: 'POST',
            data: getPreviewParams()
        },
        cols:cols,
        searchOnInit: true,
        contextmenu : false,
        loadonce:true,
        checkbox:false,
        height:290,
        addbtn:true,
        
        //start MODIFY BY WJ 20200416 修改bug 债券评级修改失败 评级机构显示代码等bug
        //新增修改  主体评级不允许删除 编辑 新增。
        addclick: function(row) {
        	row.oType = '02';
        	//增加属性 标识为新增列。
            row.updateFlg = '01';
        },
        beforeEditCell:function(preVal, field, rowData){
            if(rowData.updateFlg !='01' && rowData.oType ==='01' ){
            	//非新增 并且 是主体评级的不让修改并且编辑数据。
                return false;
            }
            
            // 新增数据 非新增 都  列名是评级类型的 不让编辑此列。
            //rowData.updateFlg ==='01' &&
            if(rowData.updateFlg !='01' && field == 'oType'){
            	return false;
            }
           return true;
        }
        // end MODIFY BY WJ 20200416 修改bug 债券评级修改失败 评级机构显示代码等bug
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
    	
    	//start MODIFY BY WJ 20200416 修改bug 债券评级修改失败 评级机构显示代码等bug
        //新增修改  主体评级不允许删除 编辑 新增。
    	if(data.oType === '01'){
    		Joyin.error('主体评级不允许删除!');
    		return;
    	}
    	
        table.removeRow(data);
    });
    var getAllRows = function(){
            return table.getAllRows();
    }
    
    /**
     * 获取参数
     * @returns
     */
    function getPreviewParams() {
        var param = {}
        param.finprodId = $("#finprodId").val();
        param.workflowId = $("#workflowId").val();
        param.entityName = entityName_BusiFinBondRatingInfo;
        param.pageModel = pageModel;
        // 保存待办评级信息不显示bug对应 add by renping 20191012 start
        param.isTodoList = isTodoList;
        // 保存待办评级信息不显示bug对应 add by renping 20191012 end
        //发行人主体
        param.issuer = $("#issuer").val();
        //融资人主体
        param.financier = $("#financier").val();
        return param;
    }
    
    // 公开接口
    window.BusiFinBondRatingInfo = {
            getAllRows:getAllRows
    };
    
}());