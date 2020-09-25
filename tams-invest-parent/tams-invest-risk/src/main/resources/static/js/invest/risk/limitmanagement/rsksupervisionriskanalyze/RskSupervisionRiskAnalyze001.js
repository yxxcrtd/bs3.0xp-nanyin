/**
 * RskSupervisionRiskAnalyze001 指标参数管理
 */
var rsk = function() {
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        var groupName = $('#groupName').val();
        var indName = $('#indName').val();
        var supType = $('#supType').val();
        var supStat = $('#supStat').val();
        var starSupDate = $('#supDate').startDate();
        var endSupDate = $('#supDate').endDate();
        return {
            groupName : groupName,
            indName : indName,
            supType : supType,
            supStat : supStat,
            starSupDate : starSupDate,
            endSupDate : endSupDate
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
        cols:[
            {field:'portfolioId',visible :false}
            ,{field:'indId',visible :false}
            ,{field:'groupName', title: 'RskSupervisionRiskAnalyze001.groupName',width:250}
            ,{field:'supTypeAbbr',sortField:"supType", title: 'RskSupervisionRiskAnalyze001.supType'}
            ,{field:'supDate', title: 'RskSupervisionRiskAnalyze001.supDate',type:'date'}
            ,{field:'indName', title: 'RskSupervisionRiskAnalyze001.indName',width:200}
            ,{field:'foulCond', title: 'RskSupervisionRiskAnalyze001.threshold',width:200,render: transferWarnFoul}
            ,{field:'supVal', title: 'RskSupervisionRiskAnalyze001.supVal',width:100,render: transferSupVal}
            ,{field:'supStatAbbr',sortField:"supStat",title: 'RskSupervisionRiskAnalyze001.supStat',render:function(d,type, row, meta){
            	 if(d == normal)
                	 return  '<a  href="javascript:;" onclick="javascript:showSupDetail(\''+ row.indId+'\',\''+row.portfolioId+'\',\''+new Date(row.supDate).format('yyyy-MM-dd')
  					+ '\')" style="cursor: pointer;"><span style="color: green">'+d+'</span></a>';
                else if(d == warning)
                	 return  '<a  href="javascript:;" onclick="javascript:showSupDetail(\''+ row.indId+'\',\''+row.portfolioId+'\',\''+new Date(row.supDate).format('yyyy-MM-dd')
 					+ '\')" style="cursor: pointer;"><span style="color: orange">'+d+'</span></a>';
                else if(d == violation)
	                return  '<a  href="javascript:;" onclick="javascript:showSupDetail(\''+ row.indId+'\',\''+row.portfolioId+'\',\''+new Date(row.supDate).format('yyyy-MM-dd')
					+ '\')" style="cursor: pointer;"><span style="color: red">'+d+'</span></a>';
                }}
            ,{field:'memo',width:200,title: 'RskSupervisionRiskAnalyze001.memo'}
        ]
    });
    
    function transferSupVal(data, type, row, meta){
    	var wg=data;
    	if(wg==''){
    		return wg;
    	}
    	if(row.indType.indexOf("FORBID_")!=-1){
    		/* 禁止型 */
			//------update by jjpei 20190812----解析禁投项------
			var secCell=false;
			if(wg.indexOf("||")!=-1){
				secCell=true;
			}
			var wgArr=wg.split(",");
			var wgStr='';
			for(var m=0;m<wgArr.length;m++){
				if(wgArr[m]!=null&&wgArr[m]!='') {
					if(secCell) {
						var wgArrSub = wgArr[m].split("||");
						if(wgArrSub.length>1)
							wgStr += wgArrSub[1]+",";
						else
							wgStr += wgArrSub[0]+",";
					}else{
						wgStr += wgArr[m]+",";
					}
				}
			}
			wg=wgStr.substring(0,wgStr.length-1);
			//------update by jjpei 20190812----解析禁投项------
    	}
    	else{
    		wg = String(new Number(wg)).replace(/^(.*\..{6}).*$/,"$1");
    		wg = Number(wg); 
    		var detailJson = JSON.parse(row.foulCond);
    		var curunit=valueConvertUnit(detailJson.threshold_1.unit);
    		if(detailJson.threshold_1.unit=='PERCENT'){
    			wg=toDecimal(wg*100)+curunit;
    		}
    		else if(detailJson.threshold_1.unit=='WRMB'){
    			wg=String(new Number(wg/10000)).replace(/^(.*\..{6}).*$/,"$1")+curunit;
    		}
    		else{
    			wg=String(new Number(wg)).replace(/^(.*\..{6}).*$/,"$1")+curunit;
    		}
    	}
    	return wg;
    }
    function toDecimal(x) {   
  	  var f = parseFloat(x);    
  	if (isNaN(f)) {   
  	  return;    
  	}          
  	f = Math.round(x*100)/100;  
  	return f;        
  	}
    function transferWarnFoul(data, type, row, meta){
    	var lnkNo="";
    	var wg="";var detail = "";
    	if(data==''){
    		return wg;
    	}
    	var detailJson = JSON.parse(data);
		if(detailJson!=null){
			/* 限制型 */
			if(row.indType.indexOf("LIMIT_")!=-1){
				if(detailJson.threshold_2.lnk==lnkNo){
					if(detailJson.threshold_1.value!=null&&detailJson.threshold_1.value!=''){
						wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit);
					}
				}else{
					wg = detailJson.threshold_1.op+detailJson.threshold_1.value+valueConvertUnit(detailJson.threshold_1.unit)+detailJson.threshold_2.lnk+detailJson.threshold_2.op+detailJson.threshold_2.value+valueConvertUnit(detailJson.threshold_2.unit);
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
    			retStr=v.text;
    			return false;
    		}
    	})
    	return retStr;
    };
    function StringToChinese(id,type){
    	var arr=[];
    	var totalIndTypeEnum=JSON.parse(IND_TYPE_TOTAL_ENUM);
    	var arrFobEnum=_.filter(JSON.parse(relateIndType),function(item){return item.id===type});
    	if(null!=totalIndTypeEnum&&null!=arrFobEnum){
    		arr=_.filter(totalIndTypeEnum,function(item){return item.id===id&&item.col===arrFobEnum[0].text});
    	}
    	if(arr==[]||arr.length==0)
    		return id;
    	else
    		return arr[0].text;
	};
	
    /**
     * 绑定导出事件
     */
    var exportExcel = function() {
    	var getParams=getSearchParams();
        Joyin.exportExcel('export', getParams);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        // 绑定按钮事件
        $('#RskSupervisionRiskAnalyze001_btn_search').click(function() {
            table.load();
        });
        $('#RskSupervisionRiskAnalyze001_btn_export').click(function() {
            exportExcel();
        });
    });
}();

function showSupDetail(indId,portfId,supdate){
	var data={};
	data.indId=indId;
	data.portfId=portfId;
	data.supDate=supdate;
   Joyin.dialog(JoyinUtil.getLabel("RskSupervisionRiskAnalyze001.detail"),
           basePath + "invest/risk/limitmanagement/rsksupervisionriskanalyze/RskSupervisionRiskAnalyze002/init",
           "70%", "90%",data);
};
