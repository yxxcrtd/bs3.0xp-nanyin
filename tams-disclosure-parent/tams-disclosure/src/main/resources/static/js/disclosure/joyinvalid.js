/**
 * Created by 何阳阳 on 2017/8/11 0011.
 * 日期格式化
 *
 */
// ---------------------------------------------------
// 日期格式化
// 格式 YYYY/yyyy/YY/yy 表示年份
// MM/M 月份
// W/w 星期
// dd/DD/d/D 日期
// hh/HH/h/H 时间
// mm/m 分钟
// ss/SS/s/S 秒
// ---------------------------------------------------

/* ****************************************************
 * jquery validator扩展
 * ****************************************************/
jQuery.fn.joyinvalid = function() {
    var $this = $(this);
    var options = {}, rules = {};
    $this.find(':input').each(function() {
        var element = $(this);
        var dataRules = $(this).data('rules');
        if(dataRules) {
            var detail = {};
            dataRules = dataRules.split(';');
            $.each(dataRules, function(i, rule) {
                if(rule) {
                    var rs = rule.split(':');
                    if(rs) {
                        detail[rs[0]] = $.parseJSON(rs[1] || true);
                    }
                }
            });
            if(!$.isEmptyObject(detail)) {
                rules[element.attr('name')] = detail;
            }
        }
    });
    if(!$.isEmptyObject(rules)) {
        options.rules = rules;
    }
    $this.validate(options);
    return $this.valid();
};
/*
// 汉化
$.extend($.validator.messages, {
    required: '该项目不允许为空',
    remote: '请修正该字段',
    email: '请输入正确格式的电子邮件',
    url: '请输入合法的网址',
    date: '请输入合法的日期',
    dateISO: '请输入合法的日期',
    number: '请输入合法的数字',
    digits: '只能输入整数',
    creditcard: '请输入合法的信用卡号',
    equalTo: '请再次输入相同的值',
    accept: '请输入拥有合法后缀名的字符串',
    maxlength: '请输入一个长度最多是 {0} 的字符串',
    minlength: '请输入一个长度最少是 {0} 的字符串',
    rangelength: '请输入一个长度介于 {0} 和 {1} 之间的字符串',
    range: '请输入一个介于 {0} 和 {1} 之间的值',
    max: '请输入一个最大为 {0} 的值',
    min: '请输入一个最小为 {0} 的值'
});
// 默认设置
$.validator.setDefaults({
    errorClass: "errors",
    success: 'valid',
    unhighlight: function (element, errorClass, validClass) { //验证通过
        $(element).tooltip('destroy').removeClass(errorClass);
    },
    errorPlacement: function (error, element) {
        if ($(element).next("div").hasClass("tooltip")) {
            $(element).attr("data-original-title", $(error).text()).data('placement', 'bottom').tooltip("show");
        } else {
            $(element).attr("title",$(error).text()).data('placement', 'bottom').tooltip('show');
        }
    }
});
// 整数校验规则
$.validator.addMethod("integer", function(value, element, param) {
    return this.optional( element ) || /^-?\d+$/.test( value );
}, '该项目只能输入整数');
// 代码输入验证规则
$.validator.addMethod("code", function( value, element, param ) {
    return this.optional( element ) || /^[a-zA-Z0-9]+$/.test( value );
}, "该项目只能输入英数字" );
// 正则表达式校验规则
$.validator.addMethod("pattern", function( value, element, param ) {
    if ( this.optional( element ) ) {
        return true;
    }
    if ( typeof param === "string" ) {
        param = new RegExp( "^(?:" + param + ")$" );
    }
    return param.test( value );
}, "格式不正确" );
// 输入长度验证规则（不区分汉字）
$.validator.addMethod("length", function( value, element, param ) {
    var length = value.length;
    return this.optional( element ) || length == param;
}, "该项目的长度只能是{0}位" );
// 输入字节验证规则
$.validator.addMethod("byteRangeLength", function(value, element, param) {
    var length = value.length;
    for(var i = 0; i < value.length; i++){
        if(value.charCodeAt(i) > 127){
            length = length + 3;
        }
    }
    return this.optional(element) || ( length >= param[0] && length <= param[1] );
}, $.validator.format("请确保输入的值在{0}-{1}个字节之间(一个中文字算3个字节)"));
*/


