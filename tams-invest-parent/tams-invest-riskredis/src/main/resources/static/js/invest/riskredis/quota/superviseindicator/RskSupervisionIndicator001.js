(function() {
	/**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
        	indName: $('#indName').val(),
        	indType: $('#indType').val(),
        	supType: $('#supType').val(),
        }
    };
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
        ajax: {
            url: 'findPage',
            type: 'POST',
            data: function() {
                return getSearchParams();
            }
        },
        searchOnInit:true,
        /**
         * 双击查看
         */
        dblclick:function(table, data) {
            Joyin.dialog(JoyinUtil.getLabel("SysCommon.refrence"),
                    basePath + "invest/riskredis/quota/superviseindicator/RskSupervisionIndicator002/init",
                    "80%", "95%", {pageModel:PAGE_MODEL_REFERENCE,indId:data.indId});
        },
        
        cols:[
             {field:'indId',  title: 'RskSupervisionIndicator001.indId',visible: false}
            ,{field:'indName', width:500 , title: 'RskSupervisionIndicator001.indName'}
            ,{field:'indTypeStr', width:180 , sortField:"indType", title: 'RskSupervisionIndicator001.indType'}
            ,{field:'supTypeStr', width:160 , sortField:"supType",title: 'RskSupervisionIndicator001.supType'}
            ,{field:'warnCond', width:240 ,title: 'RskSupervisionIndicator001.warnCond',render: transferWarnFoul}
            ,{field:'foulCond', width:240 ,title: 'RskSupervisionIndicator001.foulCond',render: transferWarnFoul}
            ,{field:'approveCond', title: 'RskSupervisionIndicator001.approveCond',render: transferWarnFoul,width:150}
            ,{field:'isEnabledStr', width:100 , sortField:"isEnabled",title: 'RskSupervisionIndicator001.isEnabled'}
            ,{field:'updateFlg', visible:false}
            ,{field:'operation', width:160, title: 'SysCommon.operation', type:'action', buttons: ['edit', 'delt']}
        ]
    });    
    /**
     * 绑定编辑事件
     */
    table.bind('click', '.edit', function(data, index) {
        data.pageModel = PAGE_MODEL_EDIT;
        data.operateType = 'edit';
        data.startWfType="modify";
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001/check', data, function(result) {
            Joyin.dialog(JoyinUtil.getLabel("RskSupervisionIndicator002.edit"),
                    basePath + "invest/riskredis/quota/superviseindicator/RskSupervisionIndicator002/init",
                    "80%", "95%",data,function() {
                                        table.reload();
                                    });
        });
    });
    
    /**
     * 绑定删除事件
     */
    table.bind('click', '.delt', function(data, index) {
        var object = {selectedItem: [data], operateType:"delete"};
        Joyin.ajaxJson(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001/check', object, function(result) {
            Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                WorkFlow.doDeleteList(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001/delete', object,
                        "RskSupervisionIndicator001_btn_delete", "RskSupervisionIndicator002",function() {
                    table.reload();
                });
            });
        });
    });
    function transferWarnFoul(data, type, row, meta){
    	var lnkNo="";
    	var wg="";var detail = "";
    	if(data==''){
    		return wg;
    	}
    	var detailJson = JSON.parse(data);
		if(detailJson!=null){
			/* 限制型 */
			detail = html2Escape(data);
			if(row.indType.indexOf("LIMIT_")!=-1){
				if(detailJson.threshold_1.value!=null&&detailJson.threshold_1.value!=''){
					wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit);
				}
				
			}else{
				/* 禁止型 */
                //------update by jjpei 20190812----解析禁投项------
				wg = detailJson.threshold_1.value_cn;
				/*var wgArr=wg.split(",");
				var wgStr='';
				for(var m=0;m<wgArr.length;m++){
					if(wgArr[m]!=null&&wgArr[m]!='')
						wgStr+=StringToChinese(wgArr[m],row.indType)+",";
				}*/
				wg=wg.substring(0,wg.length-1);
                //------update by jjpei 20190812----解析禁投项------
			}
		}
		return wg;
    }
    /**
     * 单位转换
     */
    function valueConvertUnit(value){
    	if(value==''){
    		return value;
    	}
    	var retStr;
    	var supUnitArr = JSON.parse(SUP_UNIT);
    	$.each(supUnitArr,function(i,v){
    		if(v.id==value){
    			if(value==NONE){
    				retStr="";
    			}
    			else{
    				retStr=v.text;}
    			return false;
    		}
    	})
    	return retStr;
    };
    function StringToChinese(id,type){
    	var arr=[];
    	var totalIndTypeEnum=JSON.parse(IND_TYPE_TOTAL_ENUM);
    	//var arrFobEnum=JSON.parse(relateIndType).filter(item => item.id===type);
    	var arrFobEnum=_.filter(JSON.parse(relateIndType),function(item){return item.id===type});
    	if(null!=totalIndTypeEnum&&null!=arrFobEnum){
    		//arr=totalIndTypeEnum.filter(item => item.id===id&&item.col===arrFobEnum[0].text)
    		arr=_.filter(totalIndTypeEnum,function(item){return item.id===id&&item.col===arrFobEnum[0].text})
    	}
    	if(arr==[]||arr.length==0)
    		return id;
    	else
    		return arr[0].text;
	};
    /* 普通字符转换成转意符 */
	var html2Escape = function(sHtml){
		return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
	};
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
        var paramObj = getSearchParams();
        Joyin.exportExcel('export', paramObj);
    };
    /**
     * 弹出新建画面
     * @returns
     */
    function openNew() {
        Joyin.dialog(JoyinUtil.getLabel("RskSupervisionIndicator002"),
                JoyinUtil.getRealPath("invest/riskredis/quota/superviseindicator/RskSupervisionIndicator002/init"),
                "80%", "95%",{pageModel : PAGE_MODEL_NEW,startWfType:"new"},function(){table.reload();}
        );
    }

    var initButton=function(){
	   	 $('.layui-btn').each(function () {
	   	 var self = $(this);
	        if(self.hasClass('btn-play')){
	       	 self.find('i').addClass('icon-startup');
	        }
	        else if(self.hasClass('btn-pause')){
	       	 self.find('i').addClass('icon-stop');
	        }
	   	 });
   }
    
    /**
     * 画面加载完成
     */
    $(function() {
    	initButton();
    	// 绑定按钮事件
        $('#RskSupervisionIndicator001_btn_search').click(function() {
            table.load();
            $('#RskSupervisionIndicator001_btn_delete').show();
        });
        $('#RskSupervisionIndicator001_btn_export').click(function() {
            exportExcel();
        });
        $('#RskSupervisionIndicator001_btn_add').click(function() {
            openNew();
        });
        $('#RskSupervisionIndicator001_btn_play').click(function() {
        	 if(table!=null && table!=''){
                 var selArr = table.getAllCheckedRows();
             }
             if (!selArr || selArr.length == 0) {
                 Joyin.warn(JoyinUtil.getMessage("COMMON_E_0002"));
                 return;
             }
             var flag=true;
             var selectedItem = [];
             $.each(selArr,function(i,v){
             	if(v.isEnabled==USE_STATUS_01){
             		flag=false;
             		return false;
             	}
             })
             if(!flag){
            	 Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0013',JoyinUtil.getLabel("RskSupervisionIndicator001.stop")));
            	 return;
             }
             var object = {selectedItem: selArr, operateType:"play"};
             Joyin.ajaxJson(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001/check', object, function(result) {
                 Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("RskSupervisionIndicator001.play")), function(){
		             Joyin.ajaxJson(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001/playOrstop', object, function(result) {
		            	 table.reload();
		              });
                 });
             });
        });
        $('#RskSupervisionIndicator001_btn_stop').click(function() {
        	 if(table!=null && table!=''){
                 var selArr = table.getAllCheckedRows();
             }
             if (!selArr || selArr.length == 0) {
                 Joyin.warn(JoyinUtil.getMessage("COMMON_E_0002"));
                 return;
             }
             var flag=true;
             var selectedItem = [];
             $.each(selArr,function(i,v){
             	if(v.isEnabled!=USE_STATUS_01){
             		flag=false;
             		return false;
             	}
             })
             if(!flag){
            	 Joyin.error(JoyinUtil.getMessage('RSKRDS_W_0013',JoyinUtil.getLabel("RskSupervisionIndicator001.play")));
            	 return;
             }
             var object = {selectedItem: selArr, operateType:"stop"};
             Joyin.ajaxJson(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001/check', object, function(result) {
                 Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("RskSupervisionIndicator001.stop")), function(){
		             Joyin.ajaxJson(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001/playOrstop', object, function(result) {
		            	 table.reload();
		              });
                 });
             });
        });
       /* $('#RskSupervisionIndicator001_btn_delete').click(function() {
            var selArr = table.getAllCheckedRows();
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("COMMON_E_0002"));
                return;
            }
            var object = {selectedItem: selArr, operateType:"delete"};
            Joyin.ajaxJson(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001/check', object, function(result) {
                Joyin.confirm(JoyinUtil.getMessage("COMMON_C_0001",JoyinUtil.getLabel("SysCommon.delete")), function(){
                    WorkFlow.doDeleteList(basePath + 'invest/riskredis/quota/superviseindicator/RskSupervisionIndicator001/delete', object,
                            "RskSupervisionIndicator001_btn_delete","RskSupervisionIndicator002");
                });
            });
        });*/
    });
}());