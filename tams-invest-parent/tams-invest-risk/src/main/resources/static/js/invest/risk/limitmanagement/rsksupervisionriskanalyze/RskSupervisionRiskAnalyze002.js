/**
 * RskSupervisionRiskAnalyze002 指标监督明细查询
 */
var rsk = function() {
	var globalFoud=null;
    /**
     * 获取查询参数
     */
    var getSearchParams = function() {
        return {
            indId : indId,
            portfId : portfId,
            foulCond : foulCond,
            supDate : supDate,
            exportFlag : "03"  //监督明细导出
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
            ,{field:'indName', title: 'RskSupervisionRiskAnalyze001.indName'}
            ,{field:'assetCode', title: 'RskSupervisionRiskAnalyze001.assetCode'}
            ,{field:'assetName', title: 'RskSupervisionRiskAnalyze001.assetName'}
            ,{field:'foulCond', title: 'RskSupervisionRiskAnalyze001.threshold',render: transferWarnFoul}
            ,{field:'supVal', title: 'RskSupervisionRiskAnalyze001.supVal',render: transferSupVal}
            ,{field:'supStatAbbr',sortField:"supStat",title: 'RskSupervisionRiskAnalyze001.supStat',render:function(d, type, row, meta){
                if(d == normal)
                	 return  "<span style='color: green'>"+d+"</span>";
                else if(d == warning)
                    return  "<span style='color: orange'>"+d+"</span>";
                else if(d == violation)
	                return  '<a  href="javascript:;" onclick="showSupDetail(\''+ row.indId+',\''+row.portfolioId+'\''+',\''+row.supDate
					+ '\')" style="cursor: pointer;"><span style="color: red">'+d+'</span></a>';
                }}
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
    		wg = String(wg).replace(/^(.*\..{6}).*$/,"$1");
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
    	if(null!=globalFoud){
    		return globalFoud;
    	}
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
		globalFoud=wg;
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
    	//var arrFobEnum=JSON.parse(relateIndType).filter(item => item.id===type);
    	var arrFobEnum=_.filter(JSON.parse(relateIndType),function(item){return item.id===type});
    	if(null!=totalIndTypeEnum&&null!=arrFobEnum){
    		//arr=totalIndTypeEnum.filter(item => item.id===id&&item.col===arrFobEnum[0].text)
    		arr=_.filter(totalIndTypeEnum,function(item){return item.id===id&&item.col===arrFobEnum[0].text});
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
    	var paramObj=getSearchParams();
        Joyin.exportExcel('exportMx', paramObj);
    };


    /**
     * 画面加载完成
     */
    $(function() {
        table.load();
        /*$('#RskSupervisionRiskAnalyze001_btn_export').click(function() {
            exportExcel();
        });*/
        
        $('div.analy-dtl-form>a#RskSupervisionRiskAnalyze002_btn_export').click(function() {
            exportExcel();
        });
    });
}();
