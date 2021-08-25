$(function() {
    var editFlag = true;
    var init=function() {
        if(pageModel!=PAGE_MODEL_NEW){
            $("#finprodId").attr('disabled',true);
            $("#indentifyMark").attr('disabled',true);
            $("#stockBordsttus").attr('disabled',true);
            $("#curveCode").attr('disabled',true);
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
    	document.getElementById("stockId").disabled = true;
    	if ($("#stockBordsttus").val().startsWith("02")) {
    		document.getElementById("compOrg").disabled = false;
    		document.getElementById("compStockid").disabled = false;
    		document.getElementById("marketMulti").disabled = false;
    		document.getElementById("finprodNetasset").disabled = false;
    		document.getElementById("finprodProit").disabled = false;
    	}else{
    		document.getElementById("compOrg").disabled = true;
    		document.getElementById("compStockid").disabled = true;
    		document.getElementById("marketMulti").disabled = true;
    		document.getElementById("finprodNetasset").disabled = true;
    		document.getElementById("finprodProit").disabled = true;
    	}

	};
	var marketMultiChange = function() {
    	if ($("#marketMulti").val().startsWith("01")) {
    		document.getElementById("finprodProit").disabled = true;
    		document.getElementById("finprodNetasset").disabled = false;
    	}else if ($("#marketMulti").val().startsWith("02")){
    		document.getElementById("finprodNetasset").disabled = true;
    		document.getElementById("finprodProit").disabled = false;
    	}

	};
	var stockidChange = function() {
		 if($('#compStockid').val()&&$('#compStockid').val()!=""){
		    	$("#compStockCode").val($('#compStockid').select2('data')[0].id);
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
        Joyin.ajaxJson("/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002/check",object,function(result){
            if(result){
            	Joyin.confirm(result.data, function () {
                        WorkFlow.doSaveList('/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002/save', object, "ModBintreeConfig002_btn_save");
                });
            }
        })
    };
    function doSubmit(){
    	var object = $.serializeToObject('#submitForm');
        object.pageModel = pageModel;
        object.workflowId = workflowId;
        object.finprodId = $("#finprodId").val();
        object.finprodName = $("#finprodName").val();
        object.stockBordsttus = $("#stockBordsttus").val();
        object.stockId = $("#stockId").val();
        object.compOrg = $("#compOrg").val();
        object.compOrgname = $('#compOrg').select2('data')[0].text;
        object.marketMulti = $("#marketMulti").val();
        object.compStockid = $("#compStockid").val();
        object.finprodProit = $("#finprodProit").val();
        object.finprodNetasset = $("#finprodNetasset").val();
        object.smoothsection = $("#smoothsection").val();
        object.bintreeStep = $("#bintreeStep").val();
        object.curveCode = $("#curveCode").val();
        object.seqno=SEQNO;
    	object.operateType = 'submit';
        Joyin.ajaxJson("/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002/check",object,function(result){
            if(result){
            	Joyin.confirm(result.data, function () {
            		WorkFlow.doSubmitList('/valmodel/convertibleorexchangeableassets/bintreeconfig/ModBintreeConfig002/save', object, "ModBintreeConfig002_btn_submit");
                });
            }
        })
    };
    $(function() {
        init();
        assetTypeChange();
        marketMultiChange();
        $("#stockBordsttus").on('change',function(){
        	assetTypeChange();
       });
        stockidChange();
        $("#compStockid").on('change',function(){
        	stockidChange();
       });
        $("#marketMulti").on('change',function(){
        	marketMultiChange();
       });
	   $('#ModBintreeConfig002_btn_save').click(function() {
			doSave();
		});
		$('#ModBintreeConfig002_btn_submit').click(function() {
			doSubmit();
		});
        $('#finprodId').change(function(){
            if ($('#finprodId').val() != '') {//香港联合交易所
                $('#finprodName').val($('#finprodId').select2('data')[0].text);
                $('#stockId').val($('#finprodId').select2('data')[0].object);
            }else{
                $('#finprodName').val("");
                $('#stockId').val("");
            }
        });
    }());
}());