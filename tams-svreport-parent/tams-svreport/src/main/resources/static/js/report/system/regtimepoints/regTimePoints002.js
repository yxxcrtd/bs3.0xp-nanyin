$(function() {
    var getAllData=function(){
        var param={};
        var str='';
        $.each($('#submitForm').find(":input"),function(i,v){
            var id = $(v).attr("id");
            var idv=$("#"+id).val();
            str=str+'"'+id+'":"'+idv +'",'
        });
        str="{"+str.substring(0,str.length-1)+"}";
        param=JSON.parse(str);
        return param;
    };
    var init = function(){
        if(pageModel == PAGE_MODEL_NEW){
            $("#editOrNew").hide();
        }
        //
        else if(isSubmit && isSubmit == no){
            $('#indentifyMark').attr("disabled","true");
        }
        else{
            //非新建模式需要处理在识别标识变
            $('#indentifyMark').on('change',function(){
                if(!$('#orgIndentifyMark').val()||$('#orgIndentifyMark').val()==orgIndentifyMarkDefault){
                    $('#orgIndentifyMark').val(indentifyMarkDefault);
                }
                else if($('#indentifyMark').val()==indentifyMarkDefault){
                    $('#orgIndentifyMark').val(orgIndentifyMarkDefault);
                }
            });
        }
    };
    $(function(){
        init();
        $('#EquityInfo002_btn_save').on('click',function(){
            // debugger
            var param = getAllData();
            //如果画面修改过识别标识且不为新建画面则说明为识别标识变更
            if(pageModel!=PAGE_MODEL_NEW && $('#indentifyMark').val()!=indentifyMarkDefault){
                param.isChangeId="01";
            }
            //通过修改按钮打开此画面
            else if(pageModel!=PAGE_MODEL_NEW){
                param.isChangeId="03";
            }

            Joyin.ajaxJson("checkSave",param,function(result){
                if(result){
                    layui.layer.open({type:3});
                    Joyin.ajaxJson("doSave",param,function(result){
                        layui.layer.closeAll();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    });
                }
            })
        })
        $('#holderType').on('change',function(){
            // 当投资者类别选择01、02、03时，机构证件类别置灰，个人证件类别和性别可用
            // 否则，机构证件类别可用，个人证件类别和性别置灰
            var arr = ["01","02","03"];
            holderTypeChange(arr.indexOf($('#holderType').val())>=0);
        });
    })
}())

function holderTypeChange(isperson){
    // 机构证件类别
    $("#institutionIdentifyType").val('').trigger('change');
    $("#institutionIdentifyType").attr('disabled',isperson);
    // 个人证件类别
    $("#personalIdentifyType").val('').trigger("change");
    $("#personalIdentifyType").attr('disabled',!isperson);
    // 性别
    $("#holderSex").val('').trigger("change");
    $("#holderSex").attr('disabled',!isperson);

//	$("#holderSex").attr("required",'required');
//	$("#holderSex").removeAttr("disabled");
}