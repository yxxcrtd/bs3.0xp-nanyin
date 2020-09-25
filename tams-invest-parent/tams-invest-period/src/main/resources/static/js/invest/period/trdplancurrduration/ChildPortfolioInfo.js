(function() {
    //表格默认公共配置
    var defaultOption = {
        // rowno : true,
        searchOnInit : true,
        checkbox : false,
        page : false,
        cols : [
            {
                field : 'subPortfolioId',
                sortable : false,
                title : 'TrdDurationCommon.subPortfolioId',
                width : 150,
            },
            {
                field : 'subPortfolioName',
                title : 'TrdDurationCommon.subPortfolioName',
                width : 150,
                sortable : false,
            },
        ],
        editCell:function(value, data, field, cell, column){
        	if(secOver){
        		debugger;
        		var int =0;
        		var flag = false;//是否联动计算通道费信息
            	if(CHANNEL){//通道存在
            		if(finprodType2==FINPROD_TYPE_TG_F01){//债券
                		if(COLLECT_TYPE==COLLECT_TYPE_01){//回款前扣除
                			flag=true;
                		}
                	}else if(finprodType2==FINPROD_TYPE_TG_F07){//存款
                		if(COLLECT_TYPE==COLLECT_TYPE_01&&IS_CAL_LOADRATE&&IS_CAL_LOADRATE=='01'){//回款前扣除
                			flag=true;
                		}
                	}
            		//项目与计划类 标的挂通道  不影响交易金额
            	}
            	
            	if(field=='penaltyIntamt'){
            		//此处取出值为string 可能存在逗号，手动去除加减
            		if(flag){
            			int=JoyinMath.doMinus(JoyinMath.doAdd(Number(value.replace(",","")),data.intAmt),data.channelFee)
            		}else{
            			int=JoyinMath.doAdd(Number(value.replace(",","")),data.intAmt)
            		}
            		
            	}else if(field=='intAmt'){
            		//此处取出值为string 可能存在逗号，手动去除加减
            		if(flag){
            			int =JoyinMath.doMinus(JoyinMath.doAdd(Number(value.replace(",","")),data.penaltyIntamt),data.channelFee)
            		}else{
            			int =JoyinMath.doAdd(Number(value.replace(",","")),data.penaltyIntamt)
            		}
            	}else if(field=='channelFee'){
            		//此处取出值为string 可能存在逗号，手动去除加减
            		if(flag){
            			int =JoyinMath.doMinus(JoyinMath.doAdd(data.intAmt,data.penaltyIntamt),Number(value.replace(",","")))
            		}else{
            			int =JoyinMath.doAdd(data.intAmt,data.penaltyIntamt)
            		}
            	}
            	
        		data.tradeAmt=JoyinMath.doAdd(data.prinAmt,int)
        		childPortfolioInfoTable.renderRow(data.LAY_TABLE_INDEX,{"tradeAmt": data.tradeAmt})
        		top.childPortfolioInfo = childPortfolioInfoTable.getAllRows();
        	}
        },
        done : function(){
        	if(secOver){
        		top.childPortfolioInfo = childPortfolioInfoTable.getAllRows();
            }
        },
    };
    if(childPortfolioInfo != null){
        if(subAconShareAmt&&subAconShareAmt!=0){//对基金折算的特殊处理
            var childPortfolioInfoArr =  JSON.parse(childPortfolioInfo);
            if(childPortfolioInfoArr&&childPortfolioInfoArr.length>0){
                var allbconShareAmt = 0;//折算前总金额
                $.each(childPortfolioInfoArr,function (index,value) {
                   if(value.bconShareAmt){//折算前
                       allbconShareAmt =  JoyinMath.doAdd(allbconShareAmt,value.bconShareAmt,2);
                   }
                });
                $.each(childPortfolioInfoArr,function (index,value) {
                    if(value.bconShareAmt){
                        //按照比例分配折算后金额
                       value.aconShareAmt = JoyinMath.doDiv(JoyinMath.doMul(value.bconShareAmt,subAconShareAmt,2),allbconShareAmt,2);
                    }
                });
            }
            childPortfolioInfo = JSON.stringify(childPortfolioInfoArr);
        }
        defaultOption.data = JSON.parse(childPortfolioInfo);
    }
    //表格配置合成
    defaultOption.cols = defaultOption.cols.concat(JSON.parse(cols));
    /**
     * 定义数据表格
     */
    var childPortfolioInfoTable = $("#childPortfolioInfoTable").joyintable(defaultOption);
    /**
     * 画面加载完成
     */
    $(function() {
    })

}());