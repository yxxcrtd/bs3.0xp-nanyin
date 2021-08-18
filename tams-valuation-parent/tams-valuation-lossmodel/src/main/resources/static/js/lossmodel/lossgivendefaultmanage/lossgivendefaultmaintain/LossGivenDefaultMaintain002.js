$(function() {
    var editFlag = true;
    var init=function() {
        if(pageModel!=PAGE_MODEL_NEW){
            $("#indentifyMark").attr('disabled',true);
            $("#prdRegistCode").attr('disabled',true);
            $("#ccy").attr('disabled',true);
            $("#holdDate").attr('disabled',true);
            editFlag=false;
        }
        
    };
    var getSearchParams=function(){
//        return $.serializeToObject($("#submitForm"));
        var dto = {};
        dto.assetType = $("#assetType").val();
        dto.assetDetailType = $("#assetDetailType").val();
        dto.isContainPledge = $("#isContainPledge").val();
        dto.lgd = $("#lgd").val();
        return dto;
    };
    //联动
    var assetTypeChange = function() {
    	var traget1=document.getElementById("isContainPledgeDiv");
    	var traget2=document.getElementById("assetDetailTypeDiv");
	if ($("#assetType").val().startsWith("F01")) {
		if(traget1.style.display=="inline"||traget1.style.display==""){
			traget1.style.display="none";
		}
		if(traget2.style.display=="none"){
			traget2.style.display="inline";
		}
	} else if ($("#assetType").val().startsWith("F18")) {
		if(traget2.style.display=="inline"||traget2.style.display==""){
			traget2.style.display="none";
		}
		if(traget1.style.display=="none"){
			traget1.style.display="inline";
		}
	}

	};
	function init(){
        if(isMain=='01'){
            setTimeout(function() {
            	if($('#sendStatus').val()=='02'||$('#sendStatus').val()=='03'){
          		   $('#submitTime').val(searchDataStart+' - '+searchDataEnd);
          	   }else{
          		   $('#pbcSubmitDeadline').val(searchDataStart+' - '+searchDataEnd);
          	   }
                table.load();
            }, 500);
        }
    };
    function doSave(){
    	var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.workflowId = workflowId;
        object.assetType = $("#assetType").val();
        object.assetDetailType = $("#assetDetailType").val();
        object.isContainPledge = $("#isContainPledge").val();
        object.lgd = $("#lgd").val();
        object.seqno=SEQNO;
    	object.operateType = 'save';
        var param = getSearchParams();
        param.operateType = 'save';
        Joyin.ajaxJson("/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002/check",param,function(result){
            if(result){
            	Joyin.confirm(result.data, function () {
                        WorkFlow.doSaveList('/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002/save', object, "LossGivenDefaultMaintain002_btn_save");
                });
            }
        })
    };
    function doSubmit(){
    	var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.workflowId = workflowId;
        object.assetType = $("#assetType").val();
        object.assetDetailType = $("#assetDetailType").val();
        object.isContainPledge = $("#isContainPledge").val();
        object.lgd = $("#lgd").val();
        object.seqno=SEQNO;
    	object.operateType = 'submit';
        var param = getSearchParams();
        param.operateType = 'submit';
        Joyin.ajaxJson("/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002/check",param,function(result){
            if(result){
            	Joyin.confirm(result.data, function () {
            		WorkFlow.doSubmitList('/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002/save', object, "LossGivenDefaultMaintain002_btn_submit");
                });
            }
        })
    };
    $(function() {
    	assetTypeChange();
    	$("#assetType").on('change',function(){
    		assetTypeChange();
    	});
        init();
        
        $('#LossGivenDefaultMaintain002_btn_save').click(function() {
        	var param={};
        	param.seqno=SEQNO;
        	Joyin.ajax('/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002/checkIfUsed',param,function(dataNum){
                if(dataNum=="Y"){
                     Joyin.confirmMulti("本条LGD违约损失率已被使用，本次修改是否影响历史数据？",['是', '否'], function() {
                    	 Joyin.confirm("修改后需手工跑历史批处理！", function() {
                    		 doSave();
                    	 });
                    	
                     });
                }else{
                	doSave();
                }
            });
        	
        });
        $('#LossGivenDefaultMaintain002_btn_submit').click(function() {
        	var param={};
        	param.seqno=SEQNO;
        	Joyin.ajax('/model/impairment/lossgivendefaultmanage/lossgivendefaultmaintain/LossGivenDefaultMaintain002/checkIfUsed',param,function(dataNum){
                if(dataNum=="Y"){
                     Joyin.confirmMulti("本条LGD违约损失率已被使用，本次修改是否影响历史数据？",['是', '否'], function() {
                    	 Joyin.confirm("修改后需手工跑历史批处理！", function() {
                    		 doSubmit();
                    	 });
                    	
                     });
                }else{
                	doSubmit();
                }
            });
        });
    }());
}());