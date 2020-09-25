/* ===========================================================
 * jquery-plugins.js v1.0.0
 * ===========================================================
 * Copyright 2017 He Yangyang
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ========================================================== */

/* *************************************************
 * ******************* Joyin 工具类定义 ****************
 * *************************************************/
!function(global, $) {
    var Joyin = function() {
        return {
            required:{required:true},

            numberRequired:{required:true, number:true},

            rateRequired:{required:true, number:true, minValue:0, maxValue:999},

            /**
             * 获取带参数的message内容
             * @param message 消息内容
             * @param args 消息参数(只有一个参数时传string，多个参数时传数组)
             */
            getMessage : function(message, args) {
                if (!message) {
                    return message;
                }

                if (args) {
                    if (typeof args == "object" && args.length) {
                        for (var i = 0; i < args.length; i++) {
                            var pattern = new RegExp("\\{" + i + "\\}", "g");
                            message = message.replace(pattern, args[i]);
                        }
                    } else {
                        message = message.replace(/\{0\}/g, args);
                    }
                }

                return message;
            },
            /**
             * 数据检查共通方法
             */
            checkData:function(title, checkUrl, param, callback) {
                window._checkUrl = checkUrl;
                window._param = param;
                this.dialog(title || '数据检查','/disDataCheckResult/init','900px','520px',null,function(params) {
                    if (params && _.isArray(params)) {
                        callback.call(callback, params[0], params[1], params[2], params[3]);
                    }
                }); 
            }
        };
    };
    var result ={};
    if(global.Joyin){
        result = global.Joyin;
    }
    global.Joyin = $.extend(Joyin(),result);
}(window, window.jQuery);