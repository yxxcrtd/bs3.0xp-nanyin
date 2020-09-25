/**
 * 共通类js
 */
(function() {
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
                wg = detailJson.threshold_1.value_cn;
                wg=wg.substring(0,wg.length-1);
            }
        }
        return wg;
    }
    /* 普通字符转换成转意符 */
    var html2Escape = function(sHtml){
        return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
    };

    function valueConvertUnit(value){
        if(value==''){
            return value;
        }
        if(value=='NONE'){
            return "";
        }
        var retStr;
        var supUnitArr = JSON.parse(SUP_UNIT);
        $.each(supUnitArr,function(i,v){
        	if(v.id==value){
    			if(value=='NONE'){
    				retStr="";
    			}
    			else{
    				retStr=v.text;
    			}
    			return false;
    		}
        })
        return retStr;
    };

 function transferSupVal(data, type, row, meta){
    	var wg=data;
    	if(wg==''){
    		return wg;
    	}
    	if(row.indType.indexOf("FORBID_")!=-1){
    	    /* 禁止型 */
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
    	}
    	else{
    		if(row.supStat == "ERROR"){
                  return '<span style="color: red">'+JoyinUtil.getLabel("RskSupervisionRiskAnalyze001.exceptionMessage")+'</span>';
             }
    		wg = JoyinMath.doMul(parseFloat(new Number(wg)), 1, 6);
    		wg = Number(wg);
    		var detailJson = JSON.parse(row.foulCond);
    		var curunit=valueConvertUnit(detailJson.threshold_1.unit);
    		if(detailJson.threshold_1.unit=='PERCENT'){
    			wg=toDecimal(wg*100,4)+curunit;
    		}
    		else if(detailJson.threshold_1.unit=='WRMB'){
//    			wg=String(new Number(wg/10000)).replace(/^(.*\..{6}).*$/,"$1")+curunit;NumberObject.toFixed(num)
    			wg=JoyinMath.doMul(parseFloat(new Number(wg/10000)), 1, 4)+curunit;
    		}else if(detailJson.threshold_1.unit=='BP'){
                wg=toDecimal(wg*10000,4)+curunit;
            }
    		else{
    			wg=JoyinMath.doMul(parseFloat(new Number(wg)), 1, 6)+curunit;
    		}
    	}
    	return wg;
    }


function toDecimal(x,num) {
  	var f = parseFloat(x);
  	if (isNaN(f)) {
  	  return;
  	}
  	//f = Math.round(x*100)/100;
	f=JoyinMath.doMul(f, 1, num)
  	return f;
 }
	function formatDepreciation(value, data, cell, unit){
		var patrn = /^[+-]?(0|([1-9]\d*))(\.\d+)?$/g;
		if(!patrn.test(value)){
			value = "";
		}else{
			if(unit=='DAY'){
				if(value.indexOf(".")!=-1){
					value = value.substring(0,value.indexOf("."));
				}
				value = value;
			}else{
				if(value.indexOf(".")==-1){
					value = value+".0000";
				}
				else{
					value = value+"0000";
					value = value.substring(0,value.indexOf("."))+value.substring(value.indexOf("."),value.indexOf(".")+5);
				}
			}
		}
		return value;
	}
    /**
     * chosen.jquery.js 项目插件拓展
     *
     * 更新 下拉框通用方法
     *
     * @param dataResult 原数据，不传时，默认取[data-result]
     * @param item 筛选，这里取的数据对象中的id，如：['1','2']，不传时，全部展示
     * @param chosen 选中某一项 string
     * @param disabled 是否禁用 boolean
     * @param callback 回调
     * @return $this
     */
    $.fn.updateChosenArr = function updateChosenArr(obj){
        var $this = $(this),arr = obj.dataResult || JSON.parse($this.attr('data-result') || '[]'),item = obj.item;
        $this.empty();
        $this.append($('<option value>'));
        for(var i in arr){
            if (!arr.hasOwnProperty(i))continue;
            if (obj.item && obj.item.length > 0){
                if ($.inArray(arr[i].id,item) >= 0)
                    $('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this);
            } else
                $('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this)
        }
        if (obj.chosen)
            $this.val(obj.chosen);
        if (obj.disabled === true || obj.disabled === false)
            $this.attr('disabled', obj.disabled);
        $this.trigger("chosen:updated").trigger('change');
        if (obj.callback)
            obj.callback($this);
        return $this;
    }

    window.RskQuotaCommon={
        transferWarnFoul:transferWarnFoul,
        valueConvertUnit:valueConvertUnit,
        transferSupVal:transferSupVal,
        toDecimal:toDecimal,
        formatDepreciation:formatDepreciation,
    }
}());