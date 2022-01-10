/**
 * PdFactorConfig002 计划类（净值型）资产估值行情信息新建（含修改）
 */
$(function () {
	
    //判断是提交或是保存
    function saveOrSubmit(operateType) {
        // var object = $.serializeToObject('#submitForm', true);
		var object = $.serializeToObject('#submitForm');
		object.useStatus=$('#useStatus').val();
        object.pageModel = pageModel;
        object.operateType = operateType;
        //获取资产代码联想文本框的hidcode金融产品代码
        if (pageModel == PAGE_MODEL_NEW || pageModel == PAGE_MODEL_EDIT) {
            object.seqno = $('#seqno').val();
        }
		var elem = $('#elements').val();
		if(elem =='01' || elem =='02' || elem =='05'){//文本输入
			object.elementsValue = $('#inputValue').val();
		}else if(elem =='03'){//评级下拉选
			object.elementsValue = $('#selectRateValue').val();
		}else{//风险五类 可为空
			if($('#selectRskValue').val() != null){
				object.elementsValue = $('#selectRskValue').val().join(",");
			}else{
				object.elementsValue = "  ";//设置为空字符串
			}
			object.selectRskValue = object.elementsValue;
			
		}
        Joyin.ajaxJson(basePath + 'model/impairment/phaseparameter/ModStagePara002/check', object, function (result) {
            Joyin.confirmMulti("本次修改是否影响历史数据？", ['是', '否'],function () {
				Joyin.confirm("修改后需手工跑历史批处理！", function () {
	            	WorkFlow.doSubmitList(basePath + 'model/impairment/phaseparameter/ModStagePara002/save', object, "ModStagePara002_btn_submit");
				});	
            });
        });
    };
    //画面初始化
    var init = function () {
        //编辑模式资产所属阶段、要素绝对值、要素不可编辑
        if (pageModel == PAGE_MODEL_EDIT) {
			$('#stageOfAeest').attr("disabled", true);
            $('#absoluteOfElements').attr("disabled", true);
 			$('#elements').attr("disabled", true);
        }
		changeElements();

    };
	/* elements关联联动*/
	var changeElements = function (){
		var elem = $('#elements').val();//要素选择
		if(elem =='01' || elem =='02' || elem =='05'){//文本输入
			$('#inputType').show();
			/*$('#selectRate').hide();
			$('#selectRsk').hide();*/
		}else if(elem =='03'){//评级下拉选
			$('#selectRate').show();
			/*$('#inputType').hide();
			$('#selectRsk').hide();*/
		}else{//风险五类
			$('#selectRsk').show();
			$/*('#selectRate').hdie();
			$('#inputType').hide();*/
		}
	}
    
    $(function () {
        //初始化画面
        init();
        $('#ModStagePara002_btn_submit').click(function () {
            saveOrSubmit('submit');
        });
		//要素联动事件
        $('#elements').change(function () {
            changeElements();
        });
        
    });
}());