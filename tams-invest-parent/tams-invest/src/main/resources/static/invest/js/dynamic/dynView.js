
/*
 * 展示checkbox界面并根据选中项进行回调展示数据
 * dynId表示数据库表DYN_CONFIG_INFO中DYN_ID配置的值
 * 设置显示列按钮id以pageId开头    例：<a id="EtrIbBondinfoHq_checkbox_start">
 * 多选框展示页面id以pageId开头   例：<div id="EtrIbBondinfoHq_checkbox_background">
 * 行情展示页面id以pageId开头    例：<div id="EtrIbBondinfoHq_view_contain">
 * suffix后缀表示年、%等, value表示字段英文名称，label表示字段中文名称
 */
function DynamicView (pageId, dynId, callback){
    const o={};
    o.pageid = pageId;
    o.dynId = dynId;
    o.callback=callback; // 回调方法，存在则不执行默认展示方法，可以继续选择性调用
    o.all=[];  // 选择list
    o.checkedList=[]; // checklist
    o.result={};    // 行情结果对象
    
    o.init=function (){
        let _background = $("#"+ o.pageid +"_checkbox_background");
        _background.append('<div style="margin-top:10px;"><form id="'+ o.pageid +'_checkbox_form" style="margin-top:10px;"><div id="'+ o.pageid +'_checkbox_contain"></div><div style="text-align:center"><button id="'+ o.pageid +'_checkbox_btn_realseach" style="margin-top:5px;margin-bottom:5px;">确认</button><button id="'+ o.pageid +'_checkbox_btn_seachClose" style="margin-top:5px;margin-bottom:5px;margin-left: 50px;">取消</button></div></form></div>');

        let isclicked=false; // 多选项有变化才会修改后台数据

        if(true){
            let url = basePath + 'invest/entrust/EtrCommonController/selectByDynId';
            Joyin.ajaxJson(url, {"dynId":o.dynId}, function (datajson) {
                o.all=datajson.all;
                // 构造多选框
                const _pageContain = $("#"+o.pageid+"_checkbox_contain");
                _pageContain.empty(); // 显示多选框之前清空前一次的
                let m; // 存储奇数序号
                $.each(datajson.all,function(index, value){
                    // alert(index + "-" + value.value + "-" + value.label);
                    if(value && value != ''){
                        if((index+1) % 2 == 0 && index > 0){
                            o.itemfun(_pageContain, m, value);
                            m = null;
                        }else{
                            m = value;
                        }
                    }
                });
                if(m != null){
                    o.itemfun(_pageContain, m);
                }

                // 对构造的多选项赋值
                let chs = datajson.checked;
                if(chs){
                    let arr = chs.split(",");
                    o.checkedList = [];
                    $.each(arr,function(index, value){
                        if(value != ''){
                            const checked_input = $("#"+o.pageid+"_checkbox_contain input:checkbox[value='"+ value +"']");
                            if(checked_input){
                                // 初始化页面展示数据
                                var temp = {}
                                temp.name=value;
                                temp.label=checked_input.attr("title");
                                temp.suffix=checked_input.attr("suffix");
                                checked_input.attr("checked",'checked');
                                o.checkedList[o.checkedList.length]=temp;
                            }
                        }
                    });
                }

                // 绑定构造的多选项click事件
                $("#"+o.pageid+"_checkbox_contain input:checkbox").click(function (){
                    isclicked = true;
                    let thisCheckbox = $(this);
                    if(thisCheckbox.attr("checked") == "checked"){
                        thisCheckbox.removeAttr("checked");
                    }else{
                        thisCheckbox.attr("checked", "checked");
                    }
                });

                if(o.callback){
                    o.callback(o.checkedList, o.result);
                }else{
                    o.defaultView();   // 初始化展示空行情
                    o.resultToView(); // 初始化行情信息
                }
            });
        }


        // 设置显示列
        $("#"+o.pageid+"_checkbox_start").click(function() {
            if($("#"+o.pageid+"_checkbox_background").css("display")=="none"){
                $("#"+o.pageid+"_checkbox_background").show();
            }
            else{
                $("#"+o.pageid+"_checkbox_background").hide();
            }
        });

        // 取消
        $("#"+o.pageid+"_checkbox_btn_seachClose").click(function() {
            $("#"+o.pageid+"_checkbox_background").hide();
            return false;
        });

        // 确认
        $("#"+o.pageid+"_checkbox_btn_realseach").click(function() {
            if(isclicked){
                let checkedlist=[];
                let index = 0 ;
                let arr = $("#"+o.pageid+"_checkbox_contain input:checkbox");
                o.checkedList = [];
                $.each(arr,function(index, value){
                    let isChecked = $(this).attr("checked");
                    if (isChecked && isChecked == 'checked'){
                        let value = $(this).val();
                        // alert(value + "-" + $(this).attr("checked"));
                        if(value && value != ''){
                            checkedlist[checkedlist.length]=value;
                            let temp = {};
                            temp.name=value;
                            temp.label=$(this).attr("title");
                            temp.suffix=$(this).attr("suffix");
                            o.checkedList[o.checkedList.length]=temp;
                        }
                    }
                });
                if (checkedlist.length > 0){
                    let url = basePath + 'invest/entrust/EtrCommonController/updateByParam';
                    let params = {"dynId":dynId, "checked":checkedlist.join(",")};
                    Joyin.ajaxJson(url, params, function (datajson) {
                        $("#"+o.pageid+"_checkbox_background").hide();
                        if(o.callback){
                            o.callback(o.checkedList, o.result);
                        }else{
                            o.defaultView(); // 展示空行情
                            o.resultToView();// 展示行情
                        }
                    });
                }
                isclicked=false;
            }
            return false;
        });
    };

    o.itemfun = function(_this, m, n){ // 构造两列表单
        if(!n){
            let div = $('<div style="margin-left: 40px; margin-top:6px;"><span style="display:inline-block; width: 138px; text-align: left; vertical-align: top;"><label><input type="checkbox" name="dynInfo" value="'+ m.value +'" title="'+ m.label +'" suffix="'+m.suffix+'">'+ m.label +'</label></span></div>');
            _this.append(div);
        }else{
            let div = $('<div style="margin-left: 40px; margin-top:6px;"><span style="display:inline-block; width: 138px; text-align: left; vertical-align: top;"><label><input type="checkbox" name="dynInfo" value="'+ m.value +'" title="'+ m.label +'" suffix="'+m.suffix+'">'+ m.label +'</label></span><span style="display:inline-block; width: 120px; text-align: left; vertical-align: top;"><label><input type="checkbox" name="dynInfo" value="'+ n.value +'" title="'+ n.label +'" suffix="'+n.suffix+'">'+ n.label +'</label></span></div>');
            _this.append(div);
        }
    };
    
    /**
     * 空值展示行情
     * @param id 需要展示的#标识
     * @param checkedList 选中项
     */
    o.defaultView = function () {
        var _this = $("#" + o.pageid + "_view_contain");
        let checkedList = o.checkedList;
        _this.empty();
        $.each(checkedList, function(index, value){
            // alert(index + "-" + value.name + "-" + value.label + "=" + value.suffix);
            if(value.label){
                var item = $('<div style="float: left;width: 50%;height: 22px;line-height: 22px;"><label>'+ value.label +':</label><label id="EtrIbBondinfoHq_'+ value.name +'"></label></div>');
                item.appendTo(_this);
            }
        });
    };
        
    /**
     * 行情展示
     */
    o.resultToView = function(result){
        if(result){
            o.result=result; // 更新行情信息
        }else{
            result=o.result; // 使用缓存行情信息
        }
        let chs = o.checkedList;  // 使用缓存选择项

        if(result && chs){
            $.each(chs,function(index, value){
                let name = value.name;
                let _labelName = $("#"+ o.pageid +"_" + name);
                if(_labelName){
                    _labelName.html('');
                    let resultValue = result[name];
                    // alert(index + "-" + value.name + "-" + value.label + "===" + str);
                    if(resultValue && resultValue != ''){
                        if(value.suffix && value.suffix != 'undefined' && value.suffix != ''){
                            if(value.suffix == '%'){
                                _labelName.html(JoyinUtil.formatNumber(resultValue, 4, null, 2) + value.suffix);
                            }else{
                                _labelName.html(resultValue + value.suffix);
                            }
                        }else{
                            _labelName.html(resultValue);
                        }
                    }
                }
            });
        }
    };
    
    return o;
}
