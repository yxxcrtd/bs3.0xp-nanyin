/**
 * 上农商个性化_指令环节风险试算改造需求 add by libaisong 2020-11-27
 */
(function(){
   
    /**
     * 定义数据表格
     */
    var table=$("#dataTable").joyintable({
    	 
    	page: false,
         data: (RiskTrialRetDto1) ? RiskTrialRetDto1 : [],
         height: 200,
        cols:[
              {field:'portfolioCode', visible: false}
              ,{field:'groupId', visible:false}
              ,{field:'kriCode', visible: false}
  			  ,{field:'mathProcess', visible :false}
              ,{field:'kriName',  title: 'BaseRiskMathComm.indName', width:250,sortable:false// add by zhangrui1 指标计算明细超链接 start
                  ,render: function(data, type, row, meta) {
                      if (!row.mathProcess) {
                          return data;
                      }
                      return "<a  class='pro-link showExprDetail' style='cursor: pointer;'>"+data+"</ a>";
                  }
                  // add by zhangrui1 指标计算明细超链接 end}
              }
              ,{field:'kriTypeCn',  title: 'BaseRiskMathComm.indType', visible: false}
              ,{field:'portfolioName',  title: 'BaseRiskMathComm.portfolioName', width:200,sortable:false}
              ,{field:'assetName',  title: 'BaseRiskMathComm.assetName', width:200,sortable:false}
              ,{field:'warningLine',  title: 'BaseRiskMathComm.wthreshold', width:160,sortable:false}
              ,{field:'approveLine',  title: 'BaseRiskMathComm.approveold', width:160,sortable:false}
              ,{field:'forbiddenLn',  title: 'BaseRiskMathComm.vthreshold', width:160,sortable:false}
              ,{field:'kriValue',  title: 'BaseRiskMathComm.currValue',width:150,sortable:false,render: function(data, type, row, meta){
                      if(data&&row.kriType.indexOf("FORBID_")!=-1){
                          return forbidVal(data,row.kriType);
                      }
                      return row.kriExpr==null?data:"<span title='"+row.kriExpr+"'>"+data+"</span>";;
                  }}
              ,{field:'oldValue',  title: 'BaseRiskMathComm.preValue',width:150,sortable:false,render: function(data, type, row, meta){
                  return row.kriOldExpr==null?data:"<span title='"+row.kriOldExpr+"'>"+data+"</span>";;
              }}
              ,{field:'kriTrend',  title: 'BaseRiskMathComm.kriTrend',visible: false}
              ,{field:'kriStatus', visible: false}
              ,{field:'kriStatusCn',  title: 'BaseRiskMathComm.currStatus',sortable:false,
                  render: function (data, type, row, meta) {
                      if(row.kriStatus == 0){
                          return "<span>正常</span>";
                      }
                      if(row.kriStatus == 1){
                          return "<span style='color:orange'>预警</span>";
                      }
                      if(row.kriStatus == 2){
                          return "<span style='color:#F7580D'>审批</span>";
                      }
                      if(row.kriStatus == 3){
                          return "<span style='color:#F71A0D'>违规</span>";
                      }
                      if(row.kriStatus == 4){
                          return "<span style='color:#951F1F'>异常</span>";
                      }
                      return row.kriStatus;
                  }
              },
              {field:'totalConclu', visible: false}
          ],
         /* done:function(data1,data2,data3){
          	var dataTableDone = window.BussRepoSec[entityNameBussRepoSec].pledgeBondTotalTblDone;
          	if(typeof pledgeBondTotalTblDone != "undefined" &&$.isFunction(pledgeBondTotalTblDone)){
          		pledgeBondTotalTblDone(data1,data2,data3);
          	}
          	debugger;
          }*/
    });
    
    table.bind('click','.showExprDetail', function(row) {
        debugger
    	var data={};
        data.indId=row.kriCode;
        data.indName =row.kriName;
        data.mathProcess = mathToString(row.mathProcess).replace(/#/g,'"');
        Joyin.dialog(JoyinUtil.getLabel("RskIndExprDtl"),
               basePath + "invest/riskredis/quota/rsksupervisionriskanalyze/RskIndExprDtl/init",
               "70%", "90%",data);
    });
    
    /**
     * 画面加载完成
     */
    $(function () {
    	table.load();
    	table.reload({data:RiskTrialRetDto1,url:null}) ;
    });
    
    
    //解析禁投项
    function forbidVal(data,indType) {
        var wg=data;
        if(!wg||wg==''){
            return wg;
        }
        if(indType.indexOf("FORBID_")!=-1){
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
        return wg;
    }
    
    
    // add by libaisong 2020-11-28 指标计算明细 start
    var mathToString = function(mathProcess) {
        var object = JSON.parse(mathProcess);
        var str = JSON.stringify(object.PARAM);
        return str.replace(/\"/g,'#');
    }
    // add by libaisong 2020-11-28  指标计算明细 end
}());
