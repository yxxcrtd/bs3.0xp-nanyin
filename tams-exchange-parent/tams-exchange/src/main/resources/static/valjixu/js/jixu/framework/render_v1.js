/*
 * @Description: 通过JSON文件生成页面文件-> JSON写法见QUIP
 * @Author: Ma/Ming
 * @Email: mma@jixunet.com
 * @Date: 2019-01-03 10:57:39
 * @LastEditTime: 2019-04-01 19:11:41
 */

const PAGECOL = 3;
const VERIFYFAILED = "VERIFYFAILED_PLESECHECKAGAIN";
const CONTENT_PRE_FIX = "";
var lay;

layui.use(["form", "table", "element"], function() {
  lay.form = layui.form;
  lay.table = layui.table;
  lay.element = layui.element;
});

renderPage();

function renderPage(formbodyid, formJson, parameters) {
  var elemList = {}; //保存所有组件的列表，用以parent_id联动
  var fds = databind(); //form dataset
  var lsn = selectEventListener(); //监听select事件
  var jsonoptions;
  var formStyle;
  var thislog = debugLog("console");
  formbodyid = formbodyid || "formbody";
  var formurl = $("#" + formbodyid).attr("formtmp");
  if (!formurl) formurl = formJson;
  $req = $.get(getFormURL(formurl));
  $req
    .done(function(formlist) {
      for (formid in formlist) {
        if (formid == "jsonoptions") {
          jsonoptions = formlist.jsonoptions;
          if (jsonoptions.formgroup) formStyle = "card";
          else formStyle = "collapse";
        } else {
          var newFormDomID = formStyle == "card" ? genCardForm(formid, formbodyid, jsonoptions.formgroup[formid]) : genForm(formid, formbodyid);
          renderForm(formlist[formid], {
            formID: newFormDomID,
            frameID: formbodyid,
            params: parameters
          });
          //elemlink();
        }
      }
      elemlink();
      lay.element.render("collapse");
    })
    .fail(function() {
      thislog("读取JSON文件时发生错误，请检查文件格式或是否可访问：" + formurl);
    });

  return jsonoptions;

  function renderForm(formObj, formOption) {
    //parameters: [{key:value}]传入的参数
    //formObj: [{id:id}]传入的表单模版
    var formID = formOption.formID;
    var $formDom = $("#" + formID);
    var parameters = formOption.params;

    formObj.map(function(elem) {
      renderElem(elem);
    });

    $formDom.append(genComp({ type: "br" }).html());

    function renderElem(elem) {
      if (formOption.onecol == true) {
        $formDom.append(genComp({ type: "br" }, formID).html());
        elem.colwidth = 12;
      }
      elem.cb_modal_size == elem.cb_modal_size || "l";

      var c = genComp(elem, formID);

      //绑定组件 id<->页面id,不绑定button
      fds(elem.id, formID, c);
      $formDom.append(c.html());

      $(c.nid()).on("change", function() {
        elemVerify(c);
      });

      if (elem.opt_func) c.optionval(funcval(elem.opt_func, formID));
      else if (elem.opt_val) c.optionval(elem.opt_val);

      if (elem.type == "table") c.tableval([]);
      if (elem.type == "chart") c.chartval([]);

      if (parameters && parameters[elem.id] !== undefined) {
        c.value(parameters[elem.id]);
      } else if (elem.df_func !== undefined) {
        var func_val = funcval(elem.df_func, formID, parameters);
        if (func_val !== undefined) {
          c.value(func_val);
        }
      } else if (elem.df_val !== undefined) {
        c.value(elem.df_val);
      }

      lay.form.render();

      if (elemList[elem.id] === undefined) elemList[elem.id] = { child: [] };
      $.extend(elemList[elem.id], { nid: c.nid(), elem: c });

      //联动 paraid
      if (elem.parent_id !== undefined) {
        if (!Array.isArray(elem.parent_id)) elem.parent_id = [elem.parent_id];
        for (i in elem.parent_id) {
          var pid = elem.parent_id[i];
          if (elemList[pid] === undefined) elemList[pid] = { child: [] };
          elemList[pid].child.push(c);
        }
      }

      function tableonclick() {
        var roweventfilter = "tool(" + c.id() + ")";
        lay.table.on(roweventfilter, function(obj) {
          //行按钮响应
          var tr = obj.tr;
          var data = obj.data; //获得当前行数据
          var layEvent = obj.event.split("||");
          // event = [col.cb_func, col.cb_target, col.cb_target_url, col.cb_target_func,JSON.stringify(col.cb_target_form), col.cb_modal_size, col.cb_done_action].join("||");
          cb_func = layEvent[0].split(",");
          cb_target = layEvent[1];
          cb_target_url = layEvent[2];
          cb_target_func = layEvent[3];

          try {
            cb_target_form = JSON.parse(layEvent[4]);
          } catch (err) {
            cb_target_form = undefined;
          }
          cb_modal_size = layEvent[5];
          cb_done_action = layEvent[6];
          var r, p;
          if (cb_done_action === "_delrow") {
            //删除行数据
            layer.confirm(
              "真的删除该行数据吗",
              {
                icon: 3,
                title: false,
                zIndex: layer.zIndex,
                success: function(layero) {
                  layer.setTop(layero);
                }
              },
              function(index) {
                p = funcval(cb_func, formID, data);
                if (p == "" || (p !== undefined && p.status && p.status.success == 0)) {
                  pop_msg("fail", "删除失败!");
                } else {
                  obj.del();
                  c.tableaction("_delrow", obj.tr.attr("data-index"));
                }
                layer.close(index);
              }
            );
          } else {
            p = funcval(cb_func, formID, data);
            var params = joinObj(data, p);
            if (cb_target_func !== "") {
              var r = funcval(cb_target_func, formID, params);
              if (r !== "" && r !== null) cb_target_url = r;
            }
            if (cb_target_url !== "") popreturn = pop_modal({ result: params, targeturl: cb_target_url, cb_modal_size: cb_modal_size });
            else if (cb_target_form !== undefined) {
              pop_form(cb_target_form, params, function(formvalue) {
                if (cb_done_action === "_updaterow") {
                  obj.update(formvalue);
                  c.tableaction("_updaterow", obj.tr.attr("data-index"), formvalue);
                }
              });
            }
          }

          if (cb_done_action === "_addrow") {
            c.tableaction("_addrow");
          }
        });

        var bareventfilter = "toolbar(" + c.id() + ")";
        //工具栏按钮响应
        lay.table.on(bareventfilter, function(obj) {
          if (obj.event == "addrow") {
            c.tableaction("_addrow");
          }

          var keyword = $("#toobarsearch_" + c.id()).val();
          c.tableIns().reload({
            data: c.tableval().filter(function(data, index) {
              let match = false;
              for (i in data) {
                if (i !== "LAY_TABLE_INDEX" && (data[i] + "").indexOf(keyword) !== -1) match = true;
              }
              return match;
            }),
            page: {
              curr: 1
            }
          });
        });
      }

      //对于有需要的控件绑定点击事件by cb_func
      if (elem.cb_func !== undefined) {
        if (elem.cb_func == "_closeModal") {
          $(c.nid()).click(function() {
            layer.close(layer.index);
          });
        } else
          $(c.nid()).click(function() {
            //根据"cb_target": "message,modal"
            if (elem.cb_target == "tab") {
              //新标签页中打开
              var newFormID = genForm(elem.cb_target_url, formID);
            } else if (elem.cb_target == "modal") {
              let result = exec_func(elem.cb_func, c.formID(), parameters);
              if (!isObj(result)) {
                r = {};
                r[elem.cb_func] = result;
              } else {
                r = result.data ? result.data : result;
              }
              if (elem.cb_target_func) {
                var r_url = exec_func(elem.cb_target_func, formID);
                if (r_url !== "" && r_url !== null) elem.cb_target_url = r_url;
              }
              if (elem.cb_target_url) {
                pop_modal({ result: r, targeturl: elem.cb_target_url, cb_modal_size: elem.cb_modal_size }, function(modalValue) {
                  if (elem.cb_done_upelem) {
                    (Array.isArray(elem.cb_done_upelem) ? elem.cb_done_upelem : [elem.cb_done_upelem]).map(function(upelem) {
                      elemList[upelem].elem.value(modalValue[upelem]);
                    });
                  }
                });
              } else if (elem.cb_target_form) {
                if (elem.cb_target_form_h) pop_form_h(elem.cb_target_form, r);
                else pop_form(elem.cb_target_form, r);
              }
            } else if (elem.cb_target == "message") {
              layer.confirm(
                "执行" + elem.label,
                {
                  icon: 3,
                  title: "确认操作",
                  zIndex: layer.zIndex,
                  success: function(layero) {
                    layer.setTop(layero);
                  }
                },
                function(index) {
                  let result = exec_func(elem.cb_func, c.formID(), parameters);
                  if (result == VERIFYFAILED) {
                    layer.msg("数据验证失败！请检查输入", {
                      zIndex: layer.zIndex,
                      success: function(layero) {
                        layer.setTop(layero); //重点2
                      }
                    });
                  } else {
                    if (result == "" || (result !== undefined && result.status && result.status.success == 0)) {
                      //失败
                      let msg = result.status && result.status.message ? result.status.message : elem.cb_fail_message ? elem.cb_fail_message : "";
                      msg = result.status && result.status.code ? result.status.code + msg : msg;
                      pop_msg("fail", "操作失败:" + msg);
                    } else {
                      //成功
                      let msg =
                        result && result.status && result.status.message
                          ? result.status.message
                          : elem.cb_done_message
                          ? elem.cb_done_message + ":" + JSON.stringify(result)
                          : JSON.stringify(result);
                      pop_msg("success", "操作成功:" + msg);
                      if (elem.cb_done_upelem) {
                        (Array.isArray(elem.cb_done_upelem) ? elem.cb_done_upelem : [elem.cb_done_upelem]).map(function(upelem) {
                          elemList[upelem].elem.value(result[upelem]);
                        });
                      }
                    }
                  }
                  layer.close(index); //关闭确认对话框
                }
              );
            }
          });
      }

      if (elem.type == "table") tableonclick();
    }

    function pop_form(formObj, params, fn) {
      var formvalue;
      var newFormDomID = genForm("NONAME", modalarea);
      renderForm(formObj, {
        formID: newFormDomID,
        onecol: true,
        params: params
      });
      layerOption = {
        btn: ["确定", "取消"],
        type: 1,
        shade: false,
        title: false,
        content: $("#" + newFormDomID),
        zIndex: layer.zIndex,
        success: function(layero) {
          layer.setTop(layero);
        },
        yes: function() {
          formvalue = fds("all_para_in_page", newFormDomID);
          //$("#" + newFormDomID).remove();
          fn(formvalue);
          layer.close(layer.index);
        },
        end: function() {
          $("#" + newFormDomID).remove();
        }
      };
      layer.open(layerOption);
    }

    function pop_form_h(formObj, params, fn) {
      var formvalue;
      var newFormDomID = genForm("NONAME", modalarea);
      renderForm(formObj, {
        formID: newFormDomID,
        params: params
      });
      layerOption = {
        btn: ["确定", "取消"],
        type: 1,
        shade: false,
        title: false,
        content: $("#" + newFormDomID),
        zIndex: layer.zIndex,
        area: ["1200", "600"],
        success: function(layero) {
          layer.setTop(layero);
        },
        yes: function() {
          formvalue = fds("all_para_in_page", newFormDomID);
          //$("#" + newFormDomID).remove();
          fn(formvalue);
          layer.close(layer.index);
        },
        end: function() {
          $("#" + newFormDomID).remove();
        }
      };
      layer.open(layerOption);
    }

    function pop_msg(status, msg, fn) {
      var iconidx = status == "fail" ? 2 : 1;
      layer.alert(
        msg,
        {
          icon: iconidx,
          zIndex: layer.zIndex,
          success: function(layero) {
            layer.setTop(layero);
          }
        },
        function(index) {
          layer.close(index);
        }
      );
    }
    function pop_modal(modalOption, callbackfn) {
      var result = modalOption.result;
      var targeturl = modalOption.targeturl;
      var formvalue = {};
      var newFormID = genModal(targeturl);
      var title = renderPage(newFormID, targeturl, result);
      title = modalOption.targetname ? modalOption.targetname : "详情";
      var s = modalOption.cb_modal_size || "l", //s\m\l\r\f
        x = 0,
        y = 0,
        h = $(window).height(),
        w = $(window).width();
      switch (s) {
        case "s":
          x = h = h / 3;
          break;
        case "m":
          h = h / 2;
          x = h / 2;
          break;
        case "r":
          x = Math.random() * 50;
          y = Math.random() * 50;
          h = $(window).height() - x - 100;
          w = $(window).width() - y - 100;
          break;
        case "l":
          x = 25;
          y = 25;
          h = h - 50;
          w = w - 50;
          break;
      }

      var layerOption = {
        type: 1,
        zIndex: layer.zIndex,
        area: [w + "px", h + "px"],
        title: targeturl,
        offset: [x, y],
        closeBtn: 1,
        maxmin: true,
        content: $("#" + newFormID),
        success: function(layero) {
          //layer.setTop(layero);
        },
        end: function() {
          //formvalue = fds("all_para_in_page", newFormID);
          $("#" + newFormID)
            .find("[name]")
            .map(function(idx, ele) {
              var name = $(ele).attr("name");
              formvalue[name] = $(ele).val();
            });
          if (callbackfn) callbackfn(formvalue);
          $("#" + newFormID).remove();
        }
      };
      var layIndex = layer.open(layerOption);
      layer.title(title + ":" + targeturl, layIndex);
      if (s === "f") layer.full(layIndex);
    }
  }
  function funcval(keyword, formID, paramObj) {
    var calcstr = keyword;
    var r = exec_func(calcstr, formID, paramObj);

    if (r && r.data) result = r.data;
    else result = r;
    return result;
  }

  function exec_func(execfuncOption) {
    //execfuncOption={keyword, formID, paramObj,successFn,failFn,dataVerifyfailFn}

    //作用：求keyword字符串的值；formID:当前求值所在的表单form；paramObj传入的参数
    //paramObj:{key1:val1,key2:val2...}
    var thislog = debugLog("console");
    var keyword = execfuncOption.keyword;
    var paramObj = execfuncOption.inputsdata;
    var formID = execfuncOption.formID;

    if (!keyword || keyword == "") return;

    if (Array.isArray(keyword)) {
      return keyword.reduce(function(pv, cv, ci, arr) {
        var r = exec_func(cv, formID, pv);
        if (typeof r !== "object") {
          var a = {};
          a[cv] = r;
          r = a;
        }

        return joinObj(r && r.data ? r.data : r, pv);
      }, paramObj);
    }
    if (keyword.search(/[\&\*\/\+\-\^\(\)]/) !== -1) return CalcFunc(keyword, formID, paramObj); //如果包含计算符号， 先进行计算的逻辑
    if (keyword.search("..") !== -1) {
      var tablecol = keyword.split("..")[1];
      //var tablerow = keyword.split("..")[2];
      keyword = keyword.split("..")[0];
    }
    var pos = keyword.indexOf(":"),
      defvalkey;
    if (pos !== -1) {
      defvalkey = keyword.substring(pos + 1);
      keyword = keyword.substring(0, pos);
    }

    var value = [];
    var df = exec_func(defvalkey, formID, paramObj);
    if (df !== undefined) defvalkey = df;

    var elem = fds(keyword, formID);

    var constvar;
    if (typeof constlist !== "undefined") constvar = constlist[keyword];
    if (paramObj && paramObj[keyword] !== undefined)
      //内部参数含有值
      value[3] = paramObj[keyword];
    if (constvar !== undefined)
      //参数为常量
      value[5] = constvar;
    if (elem) {
      value[1] = elem.value();
      if (tablecol && Array.isArray(value[1])) {
        value[1] = value[1].reduce((pv, cv, ci, arr) => pv + +cv[tablecol], 0);
      }
      if (!elemVerify(elem)) value[1] = VERIFYFAILED;
    } else {
      var cb_msg = getParam(keyword);
      //查找接口 Format of cb_msg: {header:"someFunctionPath",inputs:{key1,param1....}}
      if (cb_msg && cb_msg.header) {
        if (!cb_msg.inputs) cb_msg.inputs = {};
        thislog("接口函数：" + JSON.stringify(cb_msg));
        cb_msg.inputs = fillParam(cb_msg.inputs, formID, paramObj);
        var msgStr = JSON.stringify(cb_msg).replace(/\\/g, "");
        for (k in cb_msg.inputs) if (typeof cb_msg.inputs[k] == "object") cb_msg.inputs[k] = JSON.stringify(cb_msg.inputs[k]);
        thislog("发给接口：" + msgStr);
        if (msgStr.indexOf(VERIFYFAILED) == -1) value[2] = fetch(CONTENT_PRE_FIX + cb_msg.header, cb_msg.inputs);
        else value[2] = VERIFYFAILED;
        thislog("返回结果：" + JSON.stringify(value[2]));
        if (tablecol && isObj(value[2])) {
          value[2] = value[2][tablecol];
        }
      }
    }
    if (defvalkey !== undefined) value[4] = defvalkey;

    //thislog("exec_func:" + keyword + ",and value is:" + JSON.stringify(value));
    for (i in value) if (value[i] !== undefined) return value[i];

    function fillParam(inputs, formID, paramObj) {
      var result = {};
      if (isObj(inputs)) for (key in inputs) result[key] = fillParam(inputs[key], formID, paramObj);
      else if (Array.isArray(inputs)) result = inputs.map(val => fillParam(val, formID, paramObj));
      else {
        var result = exec_func(inputs, formID, paramObj);
      }
      return result;
    }

    function replacer(key, value) {
      if (key == "json" || key == "param") {
        return JSON.stringify(value);
      }
      return value;
    }
  }

  function CalcFunc(fnStr, formID, paramObj) {
    var thislog = debugLog("console");
    return calc(fnStr);
    function calc(fnStr) {
      const operator = {
        "(": 10,
        ")": 10,
        "^": 7,
        "*": 5,
        "/": 6,
        "+": 3,
        "-": 4,
        ",": 1,
        "&": 1
      };
      var baseline = 0;
      var splitedArr = [];
      fnStr.split("").reduce(function(pv, cv, ci, arr) {
        if (operator[cv]) {
          if (pv !== "") splitedArr.push(pv);
          splitedArr.push(cv);
          pv = "";
        } else {
          pv = pv + cv;
          if (ci == arr.length - 1) splitedArr.push(pv);
        }
        return pv;
      }, "");

      function createNode(subArr) {
        if (subArr.length > 1) {
          do {
            var pv = 0;
            var ta = subArr.map(function(a) {
              if (a == "(") pv += 1;
              else if (a == ")") pv -= 1;
              else return -1;
              return pv;
            });
            var con = ta.indexOf(1) == 0 && ta.indexOf(0) == ta.length - 1;
            if (con) subArr = subArr.slice(1, subArr.length - 1);
          } while (con);
          //console.log(subArr.join(""));
          var idxs = subArr
            .reduce(function(pv, cv, ci, arr) {
              if (operator[cv]) {
                if (cv == ")") baseline -= operator[cv];
                pv.push(baseline + operator[cv]);
                if (cv == "(") baseline += operator[cv];
              } else {
                arr[ci + 1] == "(" ? pv.push(operator["("] - 1 + baseline) : pv.push(999);
              }
              return pv;
            }, [])
            .reduce(function(pv, cv, ci, arr) {
              if (cv == Math.min.apply(null, arr)) pv.push(ci);
              return pv;
            }, []);
          var spi = idxs.length - 1;
          return {
            name: subArr[idxs[spi]],
            children: subArr
              .reduce(
                //idxs:[2,5,6]...分割点的数组；如果不是逗号，则从idxs[0]开始分割， 不包含idx；否则全部分割
                function(pv, cv, ci, arr) {
                  idxs.indexOf(ci) == spi || (arr[idxs[0]] == "," && idxs.indexOf(ci) !== -1) ? pv.push([]) : pv[pv.length - 1].push(cv);
                  return pv;
                },
                [[]]
              )
              .reduce(function(pv, ar, ci, arr) {
                if (ar.length !== 0) pv.push(createNode(ar)); //空数组串不处理
                return pv;
              }, [])
          };
        } else {
          return { name: subArr[0] };
        }
      }

      function postTree(cur, par) {
        if (cur.name == "," && par) {
          par.children = cur.children;
          cur = par;
        }
        if (cur.children) {
          for (i in cur.children) postTree(cur.children[i], cur);
        }
        return cur;
      }

      var tree = createNode(splitedArr);
      tree = postTree(tree);
      thislog("express:" + printTree(tree));

      function printTree(node) {
        logstr = node && node.name ? "" + node.name : "";
        if (node && node.children) {
          for (i in node.children) {
            logstr += printTree(node.children[i]);
          }
        }
        return "[" + logstr + "]";
      }

      function getValue(treenode) {
        var n = treenode.name;
        if (treenode.children) {
          switch (n) {
            case "&":
              fn = f_cat;
              break;
            case "+":
              fn = f_add;
              break;
            case "-":
              fn = f_minus;
              break;
            case "*":
              fn = f_mutiby;
              break;
            case "/":
              fn = f_divid;
              break;
            case "^":
              fn = f_power;
              break;
            default:
              fn = eval("f_" + n);
          }
          r = fn(
            treenode.children.map(function(child) {
              return getValue(child);
            })
          );
        } else {
          if (isNaN(+n)) {
            r = exec_func(n, formID, paramObj);
            thislog("get result from exec_func by n=" + n + " and result is:" + r);
            if (r == "") r = n;
          } else r = n;
        }
        return r;
      }
      return getValue(tree);
    }

    function f_add(args) {
      var a = +args[0];
      var b = +args[1];
      if (isNaN(a)) a = 0;
      if (isNaN(b)) b = 0;
      return a + b;
    }
    function f_minus(args) {
      var a = +args[0];
      var b = +args[1];
      if (isNaN(a)) a = 0;
      if (isNaN(b)) b = 0;
      return a - b;
    }

    function f_mutiby(args) {
      var a = +args[0];
      var b = +args[1];
      if (isNaN(a)) a = 0;
      if (isNaN(b)) b = 0;
      return a * b;
    }

    function f_divid(args) {
      var a = +args[0];
      var b = +args[1];
      if (isNaN(a)) a = 0;
      if (isNaN(b)) b = 1;
      return a / b;
    }

    function f_power(args) {
      var a = +args[0];
      var b = +args[1];
      if (isNaN(a)) a = 0;
      if (isNaN(b)) b = 1;
      return Math.pow(a, b);
    }

    function f_cat(args) {
      var a = args[0];
      var b = args[1];
      return "" + a + b;
    }

    function f_avg(args) {
      var a = 0;
      for (i in args) {
        var b = +args[i];
        if (isNaN(b)) b = 0;
        a += b;
      }
      return a / args.length;
    }

    function f_round(args) {
      var a = +args[0];
      var b = +args[1];
      if (isNaN(a)) a = 0;
      if (isNaN(b)) b = 2;
      return a.toFixed(b);
    }
  }

  function updatechildval(parentdomid) {
    for (e in elemList) if (elemList[e].nid === parentdomid) par = elemList[e];
    par.child.forEach(function(elem) {
      updateelemvalue(elem);
    });
  }

  function updateelemvalue(elem) {
    var prop = elem.elem();
    if (prop.opt_val) elem.optionval(prop.opt_val);
    if (prop.opt_func) {
      var r = funcval(prop.opt_func, elem.formID());
      elem.optionval(r);
    }
    var func_val = funcval(prop.df_func, elem.formID());
    if (func_val) elem.value(func_val);
    if (prop.type === "select") lay.form.render("select");
  }
  function elemlink() {
    for (p in elemList) {
      par = elemList[p];
      $(par.nid).on("change", function(data) {
        updatechildval("#" + $(data.target).attr("id"));
      });
      $(par.nid).on("click", function(data) {
        updatechildval("#" + $(data.target).attr("id"));
      });

      if (par.nid) {
        //更新 下拉框选项  layui 特有
        var vf = "select(" + par.nid.substring(1) + ")";
        lay.form.on(vf, function(data) {
          //parid = "#" + $(data.elem).attr("id");
          updatechildval("#" + $(data.elem).attr("id"));
          /*
          for (e in elemList) if (elemList[e].nid === parid) par = elemList[e];
          par.child.forEach(function(elem) {
            var prop = elem.elem();
            if (prop.opt_val) elem.optionval(prop.opt_val);
            if (prop.opt_func) {
              var r = funcval(prop.opt_func, elem.formID());
              elem.optionval(r);
            }
            var func_val = funcval(prop.df_func, elem.formID());
            if (func_val) elem.value(func_val);
          });

            */
        });
      }
    }
  }
}

function selectEventListener() {
  var emList = [];

  return function(em) {
    emList.push(em);
    emList.forEach(function(em) {
      if (em.elem().opt_val) em.optionval(em.elem().opt_val);
      if (em.elem.opt_func) {
        var r = funcval(em.elem.opt_func, formID);
        em.optionval(r);
      }
      var func_val = funcval(em.elem.df_func, formID);
      if (func_val) em.value(func_val);
      lay.form.render("select");
    });
  };
}

function databind() {
  var ds = {};
  var dsform = {};
  //var autocleartimer = window.setInterval(clearemptydata, 5000);

  function clearemptydata() {
    for (key in dsform) if ($(dsform[key].nid()).length == 0) delete dsform[key];
    return;
  }

  return function(id, formName, value) {
    if (!id) return;
    if (!formName) formName = "";
    var key = id + formName;
    if (id == "all_para_in_page") {
      var allpara = {};
      for (i in ds) allpara[i] = ds[i].value() ? ds[i].value() : "";
      return allpara;
    }
    if (!value) {
      if (!formName || dsform[key] == undefined) return ds[id];
      else return dsform[key];
    } else {
      ds[id] = dsform[key] = value;
    }
  };
}

function elemVerify(elem) {
  if (elem.elem().verify) {
    var r = dataVerify(elem.value(), elem.elem().verify);
    if (!r.pass) {
      layer.tips(r.tips, elem.nid(), {
        tips: 1,
        tipsMore: true,
        time: 5000,
        zIndex: layer.zIndex, //重点1
        success: function(layero) {
          layer.setTop(layero); //重点2
        }
      });
      return false;
    }
  }
  return true;
}

function dataVerify(data, verifier) {
  var verifyrules = {
    required: [/[\S]+/, "必填项不能为空"],
    phone: [/^1\d{10}$/, "请输入正确的手机号"],
    email: [/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/, "邮箱格式不正确"],
    url: [/(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/, "链接格式不正确"],
    number: [/^(-?\d+)(\.\d+)?$/, "数字限定"],
    date: [/^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/, "日期格式不正确"],
    identity: [/(^\d{15}$)|(^\d{17}(x|X|\d)$)/, "请输入正确的身份证号"]
  };

  var rule = verifyrules[verifier];
  if (rule) {
    return rule[0].test(data) ? { pass: true } : { pass: false, tips: rule[1] };
  } else return { pass: true };
}

function joinObj(obj1, obj2) {
  $.extend(true, obj1, obj2);
  /*/obj1-->obj2--原生代码，使用jquery时不需要
  if (obj2 == undefined) obj2 = {};
  for (key in obj1) {
    {
      if (isObj(obj1[key])) {
        if (obj2[key] == undefined) {
          obj2[key] = {};
        }
        joinObj(obj1[key], obj2[key]);
      } else if (obj2[key] == undefined) obj2[key] = obj1[key];
    }
  }
  */
  return obj1;
}
function isObj(obj) {
  return Object.prototype.toString.call(obj) == "[object Object]";
}
function objCopy(obj) {
  if (typeof obj != "object") {
    return obj;
  }
  var newobj = Array.isArray(obj) ? [] : {};
  for (var attr in obj) {
    newobj[attr] = objCopy(obj[attr]);
  }
  return newobj;
}

function toObjListold(value, keyword) {
  //1. 字符串，val 单值，需要转为{data:[{funcStr:val}]}
  //2. 单层数组,[val1,val2...],转为{data:[{funcStr:val1},{funcStr:val2}]}
  //3. 单层对象,{key1:val1,key2:val2...},转为{data:[{keyword:key1,value:val1},{keyword:key2,value:val2}]},
  //4. 双层数组,[[val11,val12],[val21,val22]...],转为{data:[{0:val11,1:val12},{0:val21,1:val22}]}，用索引做key，保持顺序
  var result = [];
  if (typeof value !== "object") {
    //单值
    var a;
    if (keyword == undefined) keyword = "value";
    a[keyword] = value;
    result.push(a);
  } else if (Array.isArray(value)) {
    if (Array.isArray(value[0])) {
      //双层数组
      for (key in value) {
        result.push(
          value[key].reduce(function(pv, cv, ci, arr) {
            pv[ci] = cv;
            return pv;
          }, {})
        );
      }
    } else if (typeof value[0] !== "object") {
      //单层数组
      for (key in value) result.push({ keyword: key, value: value[key] });
    }
  } else {
    //单层对象&
    for (key in value) result.push({ keyword: key, value: value[key] });
  }
  return result;
}

function key_list(objOfArr) {
  //返回[{key1:val1,key2:val2...}]中的关键字列表[key1,key2....]
  var objOfArr = this.toObjList(objOfArr);
  return a.reduce(function(pv, cv, i, arr) {
    for (k in val) if (pv.indexOf(k) == -1) pv.push(k);
    return pv;
  }, []);
}

function val_list(objOfArr, keyword) {
  //objOfArr的keyword的值列表[val1,val2...]
  var objOfArr = this.toObjList(objOfArr);
  objOfArr.map(function(val) {
    var r;
    for (k in val) if (k == keyword) r = val[k];
    return r;
  });
  return objOfArr;
}
