/*
 * @Description: 通过JSON文件生成页面文件-> JSON写法见QUIP
 * @Author: Ma/Ming
 * @Email: mma@jixunet.com
 * @Date: 2019-01-03 10:57:39
 * @LastEditTime: 2019-07-11 15:56:28
 */

const PAGECOL = 3;
const VERIFYFAILED = "VERIFYFAILED_PLESECHECKAGAIN";
const CONTENT_PRE_FIX = "";
var lay;
var elemList = {};
layui.use(["form", "table", "element"], function() {
  lay.form = layui.form;
  lay.table = layui.table;
  lay.element = layui.element;
});

var $pageDTO = $("#pageDTO").attr("value");
if ($pageDTO !== undefined) var pageDTO = JSON.parse($pageDTO);

renderPage({ formbodyid: "formbody", parameters: pageDTO });
var pop_form_func;

function renderPage(pageOption, callbackFn) {
  /*pageOption:{
   * formbodyid,-->form显示在哪个页面id
   * formJson, -->form显示的json文件名称
   * parameters-->传入的表单参数
   * layerIndex-->如果是弹框，这会是该弹框的layerIndex(用于关闭)
   * }
   */
  //var elemList = {}; //保存所有组件的列表，用以parent_id联动
  var fds = databind(); //form dataset
  var lsn = selectEventListener(); //监听select事件
  var jsonoptions;
  var formStyle;
  var thislog = debugLog("console");
  var formbodyid = pageOption.formbodyid || "formbody";
  var editable = pageOption.editable;
  var formurl = $("#" + formbodyid).attr("formtmp");

  if (!formurl) formurl = pageOption.formJson;
  $req = $.get(getFormURL(formurl));
  $req
    .done(function(formlist) {
      for (formid in formlist) {
        if (formid === "jsonoptions") {
          jsonoptions = formlist.jsonoptions;
          if (jsonoptions.formgroup) formStyle = "card";
          else formStyle = "collapse";
        } else {
          var newFormDomID = "";
          if (formStyle == "card") {
            newFormDomID = genCardForm(formid, formbodyid, jsonoptions.formgroup[formid]);
          } else {
            if (formlist[formid][0].type == "formoption" && formlist[formid][0].title == "none") {
              newFormDomID = genCustomForm(formlist[formid][0]);
            } else newFormDomID = genForm(formid, formbodyid);
          }

          var formObj = formlist[formid];
          if (formlist[formid][0].formjson !== undefined) {
            var _jsonfile = formlist[formid][0].formjson;
            $.ajaxSettings.async = false;
            $.get(getFormURL(_jsonfile), function(json) {
              formObj = json;
             
            });
            $.ajaxSettings.async = true;

          } 
            renderForm(formObj, {
              formID: newFormDomID,
              frameID: formbodyid,
              params: pageOption.parameters,
              editable: editable
            });
          //elemlink();
        }
      }
      elemlink();

      $("[cb_func][pageID=" + formbodyid + "]").click(function(e) {
        btn_cb_Func(e.currentTarget.id);
      });
      // lay.element.render("collapse");
      lay.element.render();
      if (typeof callbackFn === "function") callbackFn(jsonoptions);
    })
    .fail(function() {
      //thislog("读取JSON文件时发生错误，请检查文件格式或是否可访问：" + formurl);
    });

  return jsonoptions;

  function renderForm(formObj, formOption) {
    //parameters: [{key:value}]传入的参数
    //formObj: [{id:id}]传入的表单模版
    var formID = formOption.formID;
    var $formDom = $("#" + formID);
    var parameters = formOption.params;
    var pageID = formOption.frameID;

    formObj.map(function(elem) {
      if (formOption.editable == false) elem.disabled = "yes";
      if (elem.type !== "formoption") renderElem(elem);
    });

    $formDom.append(genComp({ type: "br" }).html());

    if (formOption.onecol == true) {
      elemlink();
    }

    function renderElem(elem) {
      if (formOption.onecol == true) {
        $formDom.append(genComp({ type: "br" }, formID).html());
        elem.colwidth = 12;
      }
      elem.cb_modal_size == elem.cb_modal_size || "l";

      var c = genComp(elem, formID, pageID);

      //绑定组件 id<->页面id,不绑定button
      fds(elem.id, formID, c);
      $formDom.append(c.html());

      //初始化bu'yan'zheng
      $(c.nid()).on("change", function() {
        elemVerify(c);
      });

      if (elem.opt_func) c.optionval(funcval(elem.opt_func, formID, pageID));
      else if (elem.opt_val) c.optionval(elem.opt_val);

      if (elem.hide_func) c.hide(funcval(elem.hide_func, formID, pageID));

      if (elem.type == "table") c.tableval([]);
      if (elem.type == "chart") c.chartval([]);

      if (parameters && parameters[elem.id] !== undefined) {
        if (elem.noshowonloading !== "yes") c.value(parameters[elem.id]);
      } else if (elem.df_func !== undefined) {
        var func_val = funcval(elem.df_func, formID, pageID, parameters);
        if (func_val !== undefined) {
          if (elem.noshowonloading !== "yes") c.value(func_val);
        }
      } else if (elem.df_val !== undefined) {
        c.value(elem.df_val);
      }

      if (elem.tips) {
        $(c.nid()).on("click", function() {
          pop_tips({ tips: elem.tips, nid: c.nid() });
        });
      }

      lay.form.render();

      if (elemList[elem.id] === undefined) elemList[elem.id] = { child: [] };
      $.extend(elemList[elem.id], { nid: c.nid(), elem: c });
      //过渡使用
      //elemList[c.id()] = { elem: c, child: [],nid:c.nid() };
      elemList[c.id()] = { elem: c, child: [] };

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
          var currPage = +$(c.nid())
            .next()
            .find(".layui-laypage-skip")
            .find("input")
            .attr("value"); //获得当前行数据
          currPage = isNaN(currPage) ? 1 : currPage;
          var pageLimit = +$(c.nid())
            .next()
            .find(".layui-laypage-limits")
            .find("select")
            .val(); //每页数据行数
          pageLimit = isNaN(pageLimit) ? 0 : pageLimit;
          var rowIndex = +obj.tr.attr("data-index"); //事件发生得数据行
          var dataIndex = rowIndex + (currPage - 1) * pageLimit;
          var data = c.tableval()[dataIndex]; //分页后数据会失效，修复
          //c.tableIns().config.page.curr--
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
          cb_done_clickbtn = layEvent[7];
          var r, p;
          if (cb_done_action === "_delrow") {
            //删除行数据
            layer.confirm(
              "是否删除该行数据",
              {
                icon: 3,
                title: false,
                zIndex: layer.zIndex,
                success: function(layero) {
                  layer.setTop(layero);
                }
              },
              function(index) {
                p = funcval(cb_func, formID, pageID, data);
                if (p == "" || p == "FETCHFAILED_PLESECHECKAGAIN" || (p !== undefined && p.status && p.status.success == 0)) {
                  //pop_msg("fail", "删除失败!");
                } else {
                  obj.del();
                  pop_msg("", "删除成功!");
                  c.tableaction("_delrow", dataIndex);
                  if (cb_done_clickbtn) {
                	  if(!Array.isArray(cb_done_clickbtn))cb_done_clickbtn=[cb_done_clickbtn];
                	  for(btns in cb_done_clickbtn){
	                      var _btnElem = elemList[cb_done_clickbtn[btns]];
	                      if (_btnElem) {
	                        $(_btnElem.nid).click();
	                      }
                      }
                    }
                }
                layer.close(index);
              }
            );
          } else {
            p = funcval(cb_func, formID, pageID, data);
            var params = joinObj(data, p);
            if (cb_target_func !== "") {
              var r = funcval(cb_target_func, formID, pageID, params);
              if (r !== "" && r !== null) cb_target_url = r;
            }
            if (cb_target_url !== "")
              if (cb_target === "href") {
                var _linkurl = cb_target_url;
                window.open("?form=" + _linkurl);
              } else if (cb_target === "link") {
                var _linkurl = cb_target_url;
                var h = $(window).height() - 100;
                var w = $(window).width() - 100;
                layer.open({
                  type: 2,
                  title: false,
                  maxmin: true,
                  shade: [0],
                  area: [w + "px", h + "px"],
                  content: [_linkurl] //iframe的url，no代表不显示滚动条
                });
              } else
                pop_modal({ result: params, targeturl: cb_target_url, cb_modal_size: cb_modal_size }, function(modalValue) {
                  if (cb_done_clickbtn) {
                 	  if(!Array.isArray(cb_done_clickbtn))cb_done_clickbtn=[cb_done_clickbtn];
                	  for(btns in cb_done_clickbtn){
	                      var _btnElem = elemList[cb_done_clickbtn[btns]];
	                      if (_btnElem) {
	                        $(_btnElem.nid).click();
	                      }
                      }
                  }
                });
            else if (cb_target_form !== undefined) {
              params.pageID = pageID;
              pop_form(cb_target_form, params, function(formvalue) {
                if (cb_done_action === "_updaterow") {
                  obj.update(formvalue);
                  c.tableaction("_updaterow", dataIndex, formvalue);
                }

                if (cb_done_clickbtn) {
                	cb_done_clickbtn=cb_done_clickbtn.split(',')
               	  if(!Array.isArray(cb_done_clickbtn))cb_done_clickbtn=[cb_done_clickbtn];
            	  for(btns in cb_done_clickbtn){
                      var _btnElem = elemList[cb_done_clickbtn[btns]];
                      if (_btnElem) {
                        $(_btnElem.nid).click();
                      }
                  }
                }
              });
            }
          }

          if (cb_done_action === "_addrow") {
            c.tableaction("_addrow");
          }
        });

        var dblClickEvent = "rowDouble(" + c.id() + ")";
        //双击行事件
        lay.table.on(dblClickEvent, function(obj) {
          //console.log("dbl clicked:" + JSON.stringify(obj.data));
          var prop = c.elem();
          var pageOptions = { pageId: c.pageID(), formId: c.formID() };
          var parameters = obj.data;

          let result = exec_func(prop.cb_func, pageOptions, parameters);

          var r = {};
          if (!isObj(result)) {
            r[prop.cb_func] = result;
          } else {
            r = result.data ? result.data : result;
          }

          //計算cb_target_func的值
          var r_url = prop.cb_target_url;
          if (prop.cb_target_func) {
            r_url = exec_func(prop.cb_target_func, pageOptions, parameters);
            if (r_url == "" || r_url === undefined) r_url = prop.cb_target_url;
          }

          pop_modal({ result: r, targeturl: r_url, cb_modal_size: prop.cb_modal_size, editable: false }, function(modalValue) {});
        });

        var barEventFilter = "toolbar(" + c.id() + ")";
        //工具栏按钮响应
        lay.table.on(barEventFilter, function(obj) {
          if (obj.event == "addrow") {
            c.tableaction("_addrow");
          }

          /*
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
          */
        });
      }

      if (formOption.onecol == true && elem.type == "button") c.hide("true");

      //对于有需要的控件绑定点击事件by cb_func
      /*
        if (elem.cb_func !== undefined && elem.cb_target_form) {
            $(c.nid()).click(function() {
              
                    pop_form(elem.cb_target_form, {pageId:pageID}, function(formValue) {
                     
                    });
                })
              }; 
              
              
              else if (elem.cb_target == "message") {
                layer.confirm(
                  "执行" + elem.label, {
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
                        let msg = result.status && result.status.message ? result.status.message : elem.cb_fail_message ? elem.cb_fail_message :
                          "";
                        msg = result.status && result.status.code ? result.status.code + msg : msg;
                        pop_msg("fail", "操作失败:" + msg);
                      } else {
                        //成功
                        let msg =
                          result && result.status && result.status.message ? result.status.message : elem.cb_done_message ? elem.cb_done_message +
                          ":" + JSON.stringify(result) : JSON.stringify(result);
                        pop_msg("success", "操作成功:" + msg, function() {
                          if (elem.cb_done_action === "_closemodal") {
                            let thisPageLayerIndex = $("#" + formbodyid).attr("thisPageLayerIndex");
                            layer.close(thisPageLayerIndex);
                          }
                        });
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
        */

      if (elem.type == "table") tableonclick();
    }

    function pop_form(formObj, params, fn) {
      var formvalue;
      var newFormDomID = genForm("NONAME", modalarea);
      renderForm(formObj, {
        formID: newFormDomID,
        frameID: params.pageID,
        onecol: true,
        params: params
      });
      layerOption = {
        btn: ["提交", "取消"],
        type: 1,
        shade: false,
        area: "360px",
        title: false,
        content: $("#" + newFormDomID),
        zIndex: layer.zIndex,
        success: function(layero) {
          $("#" + newFormDomID).attr("thisFormLayerIdx", layero);
          layer.setTop(layero);
        },
        yes: function() {
          formvalue = fds("all_para_in_page", newFormDomID);
          for (_elem in formObj) {
            var _prop = formObj[_elem];
            if (_prop.cb_func) {
              var r = exec_func(_prop.cb_func, { formId: newFormDomID, pageId: params.pageID }, { comfirm: "yes" });
            }
          }
          //$("#" + newFormDomID).remove();
          fn(formvalue);
          // layer.close($("#" + newFormDomID).attr("thisFormLayerIdx"));
          layer.close(layer.index);
        },
        end: function() {
          $("#" + newFormDomID).remove();
          if (typeof fn === "function") fn();
        }
      };
      layer.open(layerOption);
      lay.form.render();
    }

    pop_form_func = pop_form;

    function pop_tips(tipsOption) {
      var _tipsMsg = tipsOption.tips;
      var _domId = tipsOption.nid;

      layer.tips(_tipsMsg, _domId, {
        tips: [1, "#1e9"],
        tipsMore: true,
        time: 5000,
        zIndex: layer.zIndex, //重点1
        success: function(layero) {
          layer.setTop(layero); //重点2
        }
      });
    }
  }

  function updatechildval(parentdomid) {
    for (e in elemList)
      if (elemList[e].elem !== undefined && elemList[e].elem.nid() === parentdomid) {
        par = elemList[e];
        par.child.forEach(function(elem) {
          updateelemvalue(elem);
        });
      }
  }

  function updateelemvalue(elem) {
    var prop = elem.elem();
    if (prop.opt_val) elem.optionval(prop.opt_val);
    if (prop.opt_func) {
      var r = funcval(prop.opt_func, elem.formID(), elem.pageID());
      elem.optionval(r);
    }
    if (!prop.ajax) {
      var func_val = funcval(prop.df_func, elem.formID(), elem.pageID());
      if (func_val !== undefined) elem.value(func_val);
      //if (prop.type === "select") lay.form.render("select");
    } else {
      var cb_msg = getParam(prop.df_func);
      if (cb_msg && cb_msg.header) {
        if (!cb_msg.inputs) cb_msg.inputs = {};
        var ajaxInput = fillParam(cb_msg.inputs, { pageId: elem.pageID(), formId: elem.formID() });
        //for (k in cb_msg.inputs) if (typeof cb_msg.inputs[k] == "object") cb_msg.inputs[k] = JSON.stringify(cb_msg.inputs[k]);
        var ajaxUrl = CONTENT_PRE_FIX + cb_msg.header;
        elem.tableajax(ajaxUrl, ajaxInput);
      }
    }

    if (prop.disable_func) {
      elem.disable(funcval(prop.disable_func, elem.formID(), elem.pageID()));
    }

    if (prop.hide_func) {
      elem.hide(funcval(prop.hide_func, elem.formID(), elem.pageID()));
    }

    lay.form.render();
  }

  function elemlink() {
    for (p in elemList) {
      par = elemList[p];
      if (par.child.length > 0 && typeof par.elem !== "undefined") {
        /*  
      $(par.nid).on("change", function(data) {
        updatechildval("#" + $(data.target).attr("id"));
      });
      $(par.nid).on("click", function(data) {
        updatechildval("#" + $(data.target).attr("id"));
      });
    */
        var _domId = par.elem.nid();
        $(_domId).on("change", function(data) {
          // updatechildval("#" + $(data.target).attr("id"));
          updatechildval("#" + $(data.currentTarget).attr("id"));
        });
        $(_domId).on("click", function(data) {
          updatechildval("#" + $(data.currentTarget).attr("id"));
        });

        if (_domId) {
          //更新 下拉框选项  layui 特有
          var vf = "select(" + _domId.substring(1) + ")";
          lay.form.on(vf, function(data) {
            //parid = "#" + $(data.elem).attr("id");
            updatechildval("#" + $(data.elem).attr("id"));
          });
        }
      }
    }
  }
}

function pop_modal(modalOption, callbackfn) {
  var result = modalOption.result;
  var targeturl = modalOption.targeturl;
  var formvalue = {};
  var newFormID = genModal(targeturl);
  var editable = modalOption.editable;
  var newPageOption = {
    formbodyid: newFormID,
    formJson: targeturl,
    parameters: result,
    editable: editable
  };

  var title = "详情";
  renderPage(newPageOption, function(jsonOption) {
    if (jsonOption !== undefined && jsonOption.title) title = jsonOption.title;
    var layIdx = $("#" + newFormID).attr("thisPageLayerIndex");
    //$($("#layui-layer"+layIndex).children()[0]).html("<b>新建</b>")
    layer.title(title + "(<i class='must'>*</i>为必填项)", layIdx);
  });
  //title = modalOption.title ? modalOption.title : "详情";
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
      layer.setTop(layero);
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
  $("#" + newFormID).attr("thisPageLayerIndex", layIndex);
  //$($("#layui-layer"+layIndex).children()[0]).html("<b>新建</b>")
  layer.title(title + "(<i class='must'>*</i>为必填项)", layIndex);
  if (s === "f") layer.full(layIndex);
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
      if (typeof fn === "function") fn();
    }
  );
}

function funcval(keyword, formID, pageID, paramObj) {
  var calcstr = keyword;
  if (isObj(pageID) && paramObj === undefined) {
    paramObj = pageID;
    pageID = undefined;
  }
  if (isObj(formID) && pageID === undefined) {
    paramObj = formID;
    pageID = undefined;
    formID = undefined;
  }
  var r = exec_func(calcstr, { formId: formID, pageId: pageID }, paramObj);
  if (r === VERIFYFAILED) return "";
  if (r && r.data) result = r.data;
  else result = r;
  return result;
}

function exec_func(keyword, pageOptions, paramObj, callbackfn) {
  //作用：求keyword字符串的值；formID:当前求值所在的表单form；paramObj传入的参数
  //paramObj:{key1:val1,key2:val2...}
  //pageOptions:{pageId,elementId,formId}

  var thislog = debugLog("console");
  if (!keyword || keyword == "") return;
  if (typeof keyword === "string") keyword = keyword.split("|");

  if (keyword.length === 1) keyword = keyword[0];

  if (Array.isArray(keyword)) {
    //如果不只一个keyword
    return keyword.reduce(function(pv, cv, ci, arr) {
      var r = exec_func(cv, pageOptions, pv);
      if (typeof r !== "object") {
        var a = {};
        a[cv] = r;
        r = a;
      }

      return joinObj(r && r.data ? r.data : r, pv);
    }, paramObj);
  }
  if (keyword === "all_para_in_page") {
    var elem = allValueInPage(pageOptions.pageId);
    return elem;
  }

  if (keyword.search(/[\>\<\=\&\*\/\+\-\^\(\)]/) !== -1) {
    var calcResult = CalcFunc(keyword, pageOptions, paramObj);
    return calcResult === "" || calcResult == undefined ? "0" : calcResult;
  } //如果包含计算符号， 先进行计算的逻辑

  //如果有。。运算符
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

  var df = exec_func(defvalkey, pageOptions, paramObj);
  // if (typeof(CONSTCOMEFIRST) !== 'undefined' && !CONSTCOMEFIRST && df !== undefined) defvalkey = df;
  if (typeof CONSTCOMEFIRST !== "undefined" && !CONSTCOMEFIRST) {
  } else if (df !== undefined) defvalkey = df;

  var $e = domByName(keyword, pageOptions.formId, pageOptions.pageId);
  if ($e) value[1] = valueById($e.attr("id"));

  var constvar;
  if (typeof constlist !== "undefined") constvar = constlist[keyword];
  if (paramObj && paramObj[keyword] !== undefined)
    //内部参数含有值
    value[3] = paramObj[keyword];
  if (constvar !== undefined)
    //参数为常量
    value[5] = constvar;
  if (value[1] !== undefined) {
    if (tablecol && Array.isArray(value[1])) {
      value[1] = value[1].reduce((pv, cv, ci, arr) => pv + +cv[tablecol], 0);
    }
    if (!elemVerify(elemList[$e.attr("id")].elem)) value[1] = VERIFYFAILED;
  } else {
    var cb_msg = getParam(keyword);
    //查找接口 Format of cb_msg: {header:"someFunctionPath",inputs:{key1,param1....}}
    if (cb_msg && cb_msg.header) {
      if (!cb_msg.inputs) cb_msg.inputs = {};
      thislog("接口函数：" + JSON.stringify(cb_msg));
      cb_msg.inputs = fillParam(cb_msg.inputs, pageOptions, paramObj);
      var msgStr = JSON.stringify(cb_msg).replace(/\\/g, "");
      for (k in cb_msg.inputs) if (typeof cb_msg.inputs[k] == "object") cb_msg.inputs[k] = JSON.stringify(cb_msg.inputs[k]);
      //thislog("发给接口：" + msgStr);
      if (msgStr.indexOf(VERIFYFAILED) == -1) {
        if (paramObj !== undefined && paramObj.export2excel !== undefined) export2excel(CONTENT_PRE_FIX + cb_msg.header, cb_msg.inputs);
        else {
          if (SYS_WORKFLOW_CONTROL && typeof WorkFlow !== "undefined" && pageOptions.workflowElementId !== undefined && pageOptions.workflowPageId !== undefined)
            value[2] = fetch_wfl(CONTENT_PRE_FIX + cb_msg.header, cb_msg.inputs, pageOptions.workflowElementId, pageOptions.workflowPageId, callbackfn);
          else value[2] = fetch(CONTENT_PRE_FIX + cb_msg.header, cb_msg.inputs, callbackfn);
        }
      } else value[2] = VERIFYFAILED;
      //thislog("返回结果：" + JSON.stringify(value[2]));
      if (tablecol && isObj(value[2])) {
        value[2] = value[2][tablecol];
      }
    }
  }
  if (defvalkey !== undefined) value[4] = defvalkey;

  //thislog("exec_func:" + keyword + ",and value is:" + JSON.stringify(value));
  for (i in value) if (value[i] !== undefined) return value[i];

  /*
  function replacer(key, value) {
    if (key == "json" || key == "param") {
      return JSON.stringify(value);
    }
    return value;
  }
  */
}

function fillParam(inputs, pageOptions, paramObj) {
  var result = {};
  if (isObj(inputs)) for (key in inputs) result[key] = fillParam(inputs[key], pageOptions, paramObj);
  else if (Array.isArray(inputs)) result = inputs.map(val => fillParam(val, pageOptions, paramObj));
  else {
    var result = exec_func(inputs, pageOptions, paramObj);
  }
  return result;
}

function CalcFunc(fnStr, pageOptions, paramObj) {
  var thislog = debugLog("file");
  return calc(fnStr);

  function calc(fnStr) {
    const operator = {
      "(": 10,
      ")": 10,
      ">": 8,
      "<": 8,
      "=": "8",
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
      if (treenode.children && treenode.children.length > 0) {
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
          case ">":
            fn = f_biggerthan;
            break;
          case "<":
            fn = f_lessthan;
            break;
          case "=":
            fn = f_equal;
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
          r = exec_func(n, pageOptions, paramObj);
          if (r == "" || r == undefined) r = n;
          thislog("get result from exec_func by n=" + n + " and result is:" + r);
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

  function f_biggerthan(args) {
    var a = +args[0];
    var b = +args[1];

    return a > b ? true : false;
  }

  function f_lessthan(args) {
    var a = +args[0];
    var b = +args[1];

    return a < b ? true : false;
  }

  function f_equal(args) {
    var a = args[0];
    var b = args[1];

    return a == b ? true : false;
  }

  function f_iif(args) {
    var a = args[0];
    var b = args[1];
    var c = args[2];
    return a ? b : c;
  }

  function f_1000(args) {
    var a = "" + args[0]; //原始数字
    var b = a.split("."); //小数点分割
    return (
      b[0].replace(/\d{1,3}(?=(\d{3})+$)/g, function(s) {
        return s + ",";
      }) + (b[1] ? "." + b[1] : "")
    );
  }
}

function btn_cb_Func(domId, parameters, callbackFn) {
  //parameters--> data in table
  var attrList = [
    "cb_func",
    "cb_target",
    "cb_target_url",
    "cb_target_func",
    "cb_target_form",
    "cb_export_source",
    "cb_modal_size",
    "cb_done_clickbtn",
    "cb_done_upelem",
    "cb_done_action",
    "cb_done_message",
    "df_func",
    "opt_func",
    "disable_func",
    "hide_func",
    "tips",
    "verify",
    "single",
    "parent_id",
    "name",
    "formID",
    "pageID",
    "cb_workflow_pageid",
    "cb_workflow_elementid"
  ];

  var $d = $("#" + domId);
  var btnAttr = {};
  for (idx in attrList) {
    var attr = attrList[idx];
    if ($d.attr(attr) !== undefined) btnAttr[attr] = $d.attr(attr);
  }

  var pageOptions = {
    formId: btnAttr.formID,
    pageId: btnAttr.pageID,
    elementId: btnAttr.name,
    workflowPageId: btnAttr.cb_workflow_pageid,
    workflowElementId: btnAttr.cb_workflow_elementid
  };

  if (btnAttr.cb_func === "_closemodal") {
    let thisPageLayerIndex = $("#" + btnAttr.pageID).attr("thisPageLayerIndex");
    layer.close(thisPageLayerIndex);
  } else {
    //根据"cb_target": "export,message,modal,link"
    if (btnAttr.cb_target === "export") {
      var cb_func = btnAttr.cb_func;
      var cb_formID = btnAttr.formID;
      var cb_pageID = btnAttr.pageID;
      var exportData = [];
      //export to excel
      if (btnAttr.cb_export_source !== undefined && btnAttr.cb_export_source !== "") {
        //export with table data
        var $t = domByName(btnAttr.cb_export_source, btnAttr.formID, btnAttr.pageID);

        if ($t.length > 0) {
          //find the table

          var checkedData = lay.table.checkStatus($t.attr("id"));
          checkedData.data.map(function(rowData, idx) {
            if (idx === 0) {
              var titleArray = [];
              for (datafeild in rowData) titleArray.push($("th[data-field=" + datafeild + "]").text());
              exportData.push(titleArray);
            }
            var rowArray = [];
            for (datafeild in rowData) rowArray.push(rowData[datafeild]);
            exportData.push(rowArray);
          });
        }
      }

      var daInterface = getParam(cb_func);
      var exportfilename = daInterface !== undefined ? daInterface.header : cb_func;
      if (exportData.length > 0) export2excel(exportfilename, exportData);
      else exec_func(cb_func, pageOptions, { export2excel: true });
    }

    if (btnAttr.cb_target == "modal") {
      //計算cb_func的值
      var ad = btnAttr.cb_func.split(",");
      let result = exec_func(ad, pageOptions, parameters);
      var r = {};
      if (!isObj(result)) {
        r[btnAttr.cb_func] = result;
      } else {
        r = result.data ? result.data : result;
      }

      //計算cb_target_func的值
      var r_url = btnAttr.cb_target_url;
      if (btnAttr.cb_target_func) {
        r_url = exec_func(btnAttr.cb_target_func, pageOptions);
        if (r_url == "" || r_url === undefined) r_url = btnAttr.cb_target_url;
      }

      //有目標json的時候
      if (r_url) {
        pop_modal({ result: r, targeturl: r_url, cb_modal_size: btnAttr.cb_modal_size }, function(modalValue) {
          //關閉彈框后，更新當前元素、點擊按鈕
          if (btnAttr.cb_done_upelem) {
            btnAttr.cb_done_upelem.split(",").map(function(upelem) {
              var $e = domByName(upelem, btnAttr.formID, btnAttr.pageID);
              valueById($e.attr("id"), modalValue[upelem]);
            });
          }

          if (btnAttr.cb_done_clickbtn) {
            btnAttr.cb_done_clickbtn.split(",").map(function(clkelem) {
              var $e = domByName(clkelem, btnAttr.formID, btnAttr.pageID);
              $e.click();
            });
          }

          if (typeof callbackFn === "function") callbackfn(modalValue);
        });
      } else if (btnAttr.cb_target_form) {
        var f = JSON.parse(btnAttr.cb_target_form);
        var formOption = {
          formID: btnAttr.formID,
          pageID: btnAttr.pageID
        };
        pop_form_func(f, formOption, function(formValue) {
          if (typeof callbackFn === "function") callbackfn(formValue);
        });
      }
    }

    if (btnAttr.cb_target == "message") {
      layer.confirm(
        "执行" + $d.text(),
        {
          icon: 3,
          title: "确认操作",
          zIndex: layer.zIndex,
          success: function(layero) {
            layer.setTop(layero);
          }
        },
        function(index) {
          let result = exec_func(btnAttr.cb_func, pageOptions, parameters);
          if (result == VERIFYFAILED) {
            layer.msg("数据验证失败！请检查输入", {
              zIndex: layer.zIndex,
              success: function(layero) {
                layer.setTop(layero);
              }
            });
          } else {
            if (result !== FETCHFAILED) {
              if (result == "" || (typeof result.status !== "undefined" && result.status.success == "1")) {
                var _msg = result;
                if (typeof result.status == "object" && result.status.success == "1") _msg = result.status.message;
                pop_msg("success", "操作成功:" + _msg, function() {
                  if (btnAttr.cb_done_upelem) {
                    btnAttr.cb_done_upelem.split(",").map(function(upelem) {
                      var $e = domByName(upelem, btnAttr.formID, btnAttr.pageID);
                      valueById($e.attr("id"), result[upelem]);
                    });
                  }

                  if (btnAttr.cb_done_action === "_closemodal") {
                    let thisPageLayerIndex = $("#" + btnAttr.pageID).attr("thisPageLayerIndex");
                    layer.close(thisPageLayerIndex);
                  }
                });
              } else {
                pop_msg("faild", "操作失败:" + result.status.message, function() {});
              }
            }
          }
          layer.close(index); //关闭确认对话框
        }
      );
    }

    if (btnAttr.cb_target === "link") {
      var _linkurl = btnAttr.cb_target_url;
      var h = $(window).height() - 100;
      var w = $(window).width() - 100;
      layer.open({
        type: 2,
        title: false,
        maxmin: true,
        shade: [0],
        area: [w + "px", h + "px"],
        content: [_linkurl] //iframe的url，no代表不显示滚动条
      });
    }

    if (btnAttr.cb_target === "href") {
      var _linkurl = btnAttr.cb_target_url;
      window.open("?form=" + _linkurl);
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
        var r = funcval(em.elem.opt_func, formID, pageID);
        em.optionval(r);
      }
      var func_val = funcval(em.elem.df_func, formID, pageID);
      if (func_val) em.value(func_val);
      lay.form.render("select");
    });
  };
}

function domByName(name, formId, pageId) {
  if (name === "" || name === "[]" || name === undefined) return;
  if (name.search(/[\,\>\<\=\&\*\/\+\-\^\(\)]/) !== -1) return;
  var $e;
  if (pageId === undefined) {
    if (formId === undefined) $e = $("[name=" + name + "]");
    else $e = $("[name=" + name + "][formid=" + formId + "]");
  } else $e = $("[name=" + name + "][formid=" + formId + "][pageid=" + pageId + "]");
  if ($e.length == 0) $e = $("[name=" + name + "][pageid=" + pageId + "]");
  if ($e.length > 1) $e = $e.first();
  return $e;
}

function allValueInPage(pageId) {
  if (pageId == undefined) return;
  var allValue = {};
  $e = $("[pageid=" + pageId + "]");
  $e.each(function(i, d) {
    var n = $(d).attr("name");
    var v = elemList[n].elem;
    if (v) {
      allValue[n] = v.value();
    }
  });
  return allValue;
}

function valueById(domId, value) {
  if (domId === undefined) return;
  var e = elemList[domId];
  //var n = $("#" + domId).attr("name");
  if (e !== undefined && e.elem !== undefined) {
    if (value !== undefined) e.elem.value(value);
    return e.elem.value();
  }
}

function databind() {
  var ds = {};
  var dsform = {};

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

function elemVerify(elem, callbackfn) {
  var verifyResult = true;
  if (elem.elem().verify) {
    var r = dataVerify(elem.value(), elem.elem().verify);
    if (!r.pass) {
      layer.tips(r.tips, elem.nid(), {
        tips: [1, "#c00"],
        tipsMore: false,
        time: 5000,
        zIndex: layer.zIndex, //重点1
        success: function(layero) {
          layer.setTop(layero); //重点2
        }
      });
      verifyResult = false;
    }
  }
  if (typeof callbackfn === "function" && verifyResult) callbackfn();
  return verifyResult;
}

function dataVerify(data, verifier) {
  var verifyrules = {
    required: [/[\S]+/, "必填项不能为空"],
    phone: [/^1\d{10}$/, "请输入正确的手机号"],
    email: [/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/, "邮箱格式不正确"],
    url: [/(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/, "链接格式不正确"],
    number: [/^(-?\d+)(\.\d+)?$/, "数字限定"],
    date: [/^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/, "日期格式不正确"],
    identity: [/(^\d{15}$)|(^\d{17}(x|X|\d)$)/, "请输入正确的身份证号"],
    percent: [/^((\d|[1-9]\d)(\.\d+)?|100)$/, "请输入0-100的数"]
  };

  if (verifier.indexOf("required") == -1 && data === "") return { pass: true, tips: "" };
  else
    return verifier.split("|").reduce(
      function(pv, cv, idx, arr) {
        var rule = verifyrules[cv];
        if (rule) {
          return rule[0].test(data) ? { pass: pv.pass && true, tips: pv.tips } : { pass: pv.pass && false, tips: rule[1] + ";" + pv.tips };
        } else return { pass: pv.pass && true, tips: pv.tips };
      },
      { pass: true, tips: "" }
    );
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
