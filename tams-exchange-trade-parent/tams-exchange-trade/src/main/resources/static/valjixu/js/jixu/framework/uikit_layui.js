/*
 * @Description: 使用layui的库生成样式； 通用函数也放在这里
 * @Author: Ma/Ming
 * @Email: mma@jixunet.com
 * @Date: 2019-01-03 12:06:53
 * @LastEditTime: 2019-11-22 13:44:27
 */
const form_area = "formbody";
const modalarea = "#modal_area";
const DEFAULTPAGECOL = 4;
const FETCHFAILED = "FETCHFAILED_PLESECHECKAGAIN";
var lay;

layui.use(["table", "form", "tableSelect", "dropdown", "checkbox"], function() {
  lay.tableSelect = layui.tableSelect;
  lay.table = layui.table;
  lay.form = layui.form;
  lay.dorpdown = layui.dropdown;
  lay.checkbox = layui.checkbox;
});

function genComp(elem, formID, pageID, callbackFn) {
  var domId = "S" + rndStr();
  if (elem.type === "button") {
    domId = elem.id ? elem.id : domId;
  }
  elem.name = elem.id;
  var nid = "#" + domId;
  var compNid = nid + "_comp";
  var elemDefine = paramCopy(elem);
  var formname = formID;
  elemDefine.formID = formname;
  elemDefine.pageID = pageID;
  //if (elemDefine.id !== undefined) elemDefine.name = elemDefine.id;
  var tablecolname = [];
  var htmlText = "";
  var eventList = {};
  var tabledata = [];
  var optionvalue;
  var options = {};
  var tableIns;
  var chartIns;

  if (elem.type == "table") {
    htmlText = init_table(domId, elemDefine, formID);
    tablecolname = elem.tablecols.map(function(col) {
      return col.colname;
    });
  } else {
    var funstr = "init_" + elem.type + "('" + domId + "'," + "elemDefine,'" + formID + "')";
    htmlText = eval(funstr);
  }

  //checkbox列表赋值
  function checkoption(optval) {
    if (optval === undefined) optval = { "1": "是", "0": "否" };
    var nodeList = [];
    for (key in optval) nodeList.push({ id: key, name: optval[key] });
    var checkOption = {
      elem: nid,
      nodes: nodeList,
      single: elemDefine.single !== "false",
      click: function(node) {
        console.log(checkval());
      }
    };

    layui.use(["checkbox", "layer"], function() {
      var $ = layui.$,
        layer = layui.layer,
        checkbox = layui.checkbox;
      checkbox(checkOption);
    });
  }

  // 下拉框select初始化值
  function optionval(optval) {
    if (elemDefine.type == "checkbox") checkoption(optval);
    if (optval + "" === "-999" || getkeyof(optval, "-999") !== undefined) {
      $(nid).attr("disabled", "");
    } else {
      if (!elemDefine.disabled) $(nid).removeAttr("disabled");
    }
    if (optval == "" || optval == undefined) return;
    optionvalue = toObjList(optval);
    if (elemDefine.type == "select") {
      var newHtml = "<option value=''> </option>";
      newHtml += optionvalue.reduce(function(pv, cv, ci, arr) {
        return pv + "<option value='" + cv.keyword + "'>" + cv.value + "</option>";
      }, "");
      $(nid).html(newHtml);
      if (optionvalue.length > 0) defaultOptionValue = optionvalue[0].keyword;
      else defaultOptionValue = "";

      $(nid).val(defaultOptionValue);
    } else if (elemDefine.type == "selecttab") {
      var tvalue = {
        data: optionvalue,
        // cols: [[{ type: "checkbox" }, { field: "keyword" }, { field:
        // "value" }]]
        cols: [[{ type: "radio" }, { field: "keyword" }, { field: "value" }]]
      };

      var tableSelect;
      layui.use("tableSelect", function() {
        tableSelect = layui.tableSelect;
      });
      tableSelect.render({
        elem: nid, // 定义输入框input对象 必填
        checkedKey: "value", // 表格的唯一建值，非常重要，影响到选中状态 必填
        searchKey: "value", // 搜索输入框的name值 默认keyword
        searchPlaceholder: "搜索", // 搜索输入框的提示文字 默认关键词搜索
        table: tvalue,
        done: function(elem, data) {
          // 选择完后的回调，包含2个返回值 elem:返回之前input对象；data:表格返回的选中的数据 []
          var inputoption = [];
          var inputvalue = [];
          layui.each(data.data, function(index, item) {
            inputoption.push(item.keyword);
            inputvalue.push(item.value);
          });
          elem.val(inputvalue.join(","));
          $(nid + "_hidden").val("" + inputoption.join(",") + "");
          elem[0].click();
        }
      });
    }
  }

  if (elem.type === "select") optionval(elem.opt_val);

  var tabledataSet = function() {
    // 按行检查checkbox的值
    options.cols[0].map(function(colname) {
      $(nid)
        .next()
        .find("[data-field='" + colname.field + "']")
        .map(function(i, a) {
          var di = +$(a)
            .parent()
            .attr("data-index");
          $(a)
            .find(".layui-unselect")
            .map(function(i, a) {
              d = $(a).hasClass("layui-form-checked") ? "1" : "0";
              tabledata[di][colname.field] = d;
            });
        });
    });

    return tabledata;
  };

  function table_addrow() {
    var newrow = {};
    tablecolname.map(col => (newrow[col] = ""));
    // tablecolname.map(col => (newrow[col] = rowdata[col] !== undefined ?
    // rowdata[col] : ""));
    tabledata.push(newrow);
    // tableIns.reload({ data: tabledata });
    tableval(tabledata);
  }

  function table_delrow(rowindex) {
    tabledata.splice(rowindex, 1);
  }

  function table_updaterow(rowindex, rowdata) {
    if (rowdata === undefined) return;

    var row = {};
    tablecolname.map(col => (row[col] = rowdata[col] !== undefined ? rowdata[col] : ""));
    tabledata[rowindex] = row;
    // tableIns.reload({ data: tabledata });
    tableval(tabledata);
  }

  // instant function define 初始化/更新表格数据
  function tableval(tableArray) {
    if (!tableArray) return tabledataSet();
    if (typeof tableArray !== "object") tableArray = [tableArray];

    options = tableOption();
    options.data = parseTableData(tableArray);

    if (elemDefine.type == "selecttab") return options;

    if (tableIns) tableIns.reload(options);
    else {
      tableIns = lay.table.render(options);
    }

    // 更改checkbox样式
    options.cols[0].map(function(colname) {
      $(nid)
        .next()
        .find("[data-field='" + colname.field + "']")
        .map(function(i, a) {
          var d = $(a).attr("data-content");
          if (d == "1") {
            $(a)
              .find("input")
              .map(function(i, a) {
                $(a).attr("checked", "");
              });
          }
        });
    });

    return tabledataSet();
  }

  function tableajax(ajaxUrl, ajaxInputs) {
    options = tableOption();
    ajaxInputs.limits = options.limit;
    var ajaxOption = {
      url: controllerPrefix + ajaxUrl,
      autoSort: false,
      method: "post",
      where: ajaxInputs,
      parseData: function(res) {
        /*
        {
          "code": 0,
          "msg": "",
          "count": 1000,
          "data": [{}, {}]
        } 
        */
        if (isObj(res)) {
          res.data = parseTableData(res);
          res.code = 0;
          res.count = res.data.length;
        } else {
          res.data = parseTableData(res.data);
          res.code = 0;
          res.count = res.totalCount;
        }
        return res;
      }
    };
    $.extend(true, options, ajaxOption);
    if (tableIns) tableIns.reload(options);
    else {
      tableIns = lay.table.render(options);
    }

    var sorteventfilter = "sort(" + domId + ")";
    lay.table.on(sorteventfilter, function(obj) {
      ajaxOption.initSort = obj;
      ajaxOption.where.field = obj.field; //排序字段
      ajaxOption.where.order = obj.type; //排序方式
      tableIns.reload(ajaxOption);
    });
  }

  function parseTableData(tableArray) {
    if (!tableArray) return [];
    var arr = tableArray;
    if (Object.prototype.toString.call(tableArray) == "[object Object]") {
      arr = [];
      for (k in tableArray) {
        arr.push([k, tableArray[k]]);
      }
    }

    tabledata = arr.map(function(rowdata) {
      var arr2obj = {};
      if (Array.isArray(rowdata)) {
        var p = 0;
        for (i in tablecolname) arr2obj[tablecolname[i]] = !tablecolname[i] ? "" : rowdata[p++];
        // return arr2obj;
      } else arr2obj = rowdata;

      return arr2obj;
    });

    return tabledata.map(function(rowdata) {
      var newRowData = paramCopy(rowdata);
      elemDefine.tablecols.map(function(val, idx) {
        if (val.col_func) {
          var cb_msg = getParam(val.col_func);
          if (cb_msg) {
            var input = {};
            input[val.colname] = newRowData[val.colname];
            newRowData[val.colname] = fetch(cb_msg.header, input);
          }
        }
      });
      /* 对整数加.00 for(iRow in newRowData){
        var intReg = /^([^0][0-9]+|0)$/;
        if(intReg.test((newRowData[iRow]+"")))newRowData[iRow]=newRowData[iRow]+".00"; }
       */
      return newRowData;
    });
  }
  function tableOption() {
    var options = {};
    var firstbtn = true;

    var _lasttop =
      $("#formbody")
        .children()
        .last()
        .offset().top || 200;

    options.limit = elemDefine.limit ? elemDefine.limit : "20";
    options.page = true;
    options.elem = nid;
    options.id = domId;
    options.sort = true;
    options.cellMinWidth = 180;
    if (elem.pagedown) options.height = "full-" + (_lasttop + 35);
    // options.skin= 'line';
    options.even = true;
    // options.size= 'sm';
    options.totalRow = false;
    (options.checkbox = true), (options.toolbar = elemDefine.search ? "#toolbar" + domId : "");
    options.defaultToolbar = ["print"];
    options.cols = [
      elem.tablecols.reduce(function(prow, row, i, a) {
        if (row.type == "checkbox") prow.push({ type: "checkbox", fixed: "left" });
        else if (row.type == "number") prow.push({ type: "numbers" });
        else if (row.type == "button") {
          eventList[row.id] = row.id;
          if (firstbtn) {
            firstbtn = false;
            prow.push({
              title: "操作",
              toolbar: "#btn" + domId, // row.id
              fixed: "right",
              width: elemDefine.buttonwidth ? elemDefine.buttonwidth : "120"
            });
          }
        } else if (row.type == "check")
          prow.push({
            templet: "#check" + elemDefine.id + row.colname,
            title: row.label,
            field: row.colname,
            minWidth: row.width
          });
        else if (row.type == "select")
          prow.push({
            templet: "#select" + domId + row.colname,
            title: row.label,
            field: row.colname,
            //width: row.width
            minWidth: row.width
          });
        else if (row.type == "hidden") {
          //
        } else
          prow.push({
            field: row.colname,
            title: row.label,
            width: row.width ? row.width : 180,
            //minWidth: row.width,
            totalRow: row.total ? true : false,
            sort: row.sort ? true : true
          });
        return prow;
      }, [])
    ];

    return options;
  }
  function checkvalold(val) {
    if (elem.type !== "checkbox") return;

    if (val !== undefined) {
      if (val === false)
        $(nid)
          .removeAttr("checked")
          .attr("unchecked", "");
    }

    var isChecked = $(nid)
      .next()
      .hasClass("layui-form-checked");
    return isChecked ? "true" : "false";

    // return $($("input:checkbox[name='" + elem.id +
    // "']:checked")[0]).val();
  }

  function checkval(val) {
    var OPTIONSEP = ";";
    if (elem.type !== "checkbox") return;
    if (val !== undefined) {
      if (typeof val == "boolean") val = val ? "1" : "0";
      $(nid)
        .children()
        .removeClass("on");
      val.split(OPTIONSEP).map(function(value, idx) {
        $(nid)
          .children()
          .each(function(i, a) {
            if ($(a).attr("value") === value) $(a).addClass("on");
          });
      });
    }

    var _v = [];
    $(nid)
      .children(".on")
      .each((i, a) => _v.push($(a).attr("value")));
    console.log(_v.length == 1 ? _v[0] : _v.join(OPTIONSEP));
    return _v.length == 1 ? _v[0] : _v.join(OPTIONSEP);
  }

  function chartval(chartArray) {
    if (!chartArray) return;

    // 创建实例
    if (!chartIns) {
      var chartheight = elemDefine.limit ? elemDefine.limit : 10;
      $(nid).css("height", chartheight * 36 + 100);
      chartIns = echarts.init(document.getElementById(domId));
    }

    var charttype = elemDefine.charttype ? elemDefine.charttype : "bar";
    var chartlegend = [];
    var chartSeries = elem.tablecols.map(function(coldata, arridx) {
      // if (coldata.type === "button" || coldata.type == "check") return
      // [];
      chartlegend.push(coldata.label);
      return {
        type: coldata.charttype ? coldata.charttype : charttype,
        name: coldata.label,
        data: chartArray.map(function(rowdata, idx) {
          if (coldata.type === "number") return (coldata.startnumber ? coldata.startnumber : 0) + (coldata.step ? coldata.step : 1) * idx;
          else if (Array.isArray(rowdata)) {
            return coldata.colindex ? rowdata[coldata.colindex] : rowdata[arridx];
          } else {
            return coldata.colname ? rowdata[coldata.colname] : "";
          }
        })
      };
    });

    var option = {
      title: {
        text: elemDefine.label
      },
      tooltip: {},
      toolbox: {
        feature: {
          dataZoom: {
            yAxisIndex: false
          },
          brush: {
            type: ["lineX", "clear"]
          }
        }
      },
      legend: {
        data: chartlegend
      },
      xAxis: {
        data: chartSeries[0].data
      },
      yAxis: {},
      series: chartSeries
    };

    chartIns.setOption(option);
    chartIns.hideLoading();
  }
  return {
    id: function(id) {
      if (id !== undefined) domId = id;
      return domId;
    },
    nid: function() {
      return nid;
    },
    tableIns: function() {
      return tableIns;
    },
    html: function(html) {
      if (html !== undefined) htmlText = html;
      return htmlText;
    },
    elem: function() {
      return elemDefine;
    },
    disable: function(val) {
      if (val == "true") {
        $(nid).val("");
        $(nid).attr("disabled", "");
        $(nid).addClass("layui-disabled");
      } else {
        $(nid).removeAttr("disabled");
        $(nid).removeClass("layui-disabled");
      }
    },
    hide: function(val) {
      if (val == "true") {
        //$(compNid).css("visibility", "hidden");
        $(compNid).hide();
      } else {
        //$(compNid).css("visibility", "visible");
        $(compNid).show();
      }
    },
    value: function(val) {
      if (val + "" === "-999" || getkeyof(val, "-999") !== undefined) {
        $(nid).attr("disabled", "");
      } else {
        if (!elemDefine.disabled) $(nid).removeAttr("disabled");
      }
      var setvalue = val && val.data ? val.data : val;
      if (elemDefine.type == "table") result = tableval(setvalue);
      else if (elemDefine.type == "chart") chartval(setvalue);
      else if (elemDefine.type == "selecttab") {
        if (!setvalue) {
          result = $(nid + "_hidden").val();
          if (result === "undefined") result = "";
        } else {
          $(nid).attr("ts-selected", setvalue);
          $(nid + "_hidden").val(setvalue);
          $(nid)
            .val(setvalue)
            .change(); // 默认值显示，后期需要修改
        }
        //result = $(nid).attr("ts-selected");
      } else if (elemDefine.type == "checkbox") {
        result = checkval(setvalue);
        /*
         * var isChecked = $(nid) .next()
         * .hasClass("layui-form-checked"); result = isChecked ? "true" :
         * "false"; $(nid).val(setvalue);
         */
      } else {
        if (setvalue !== undefined) $(nid).val(setvalue);
        result = $(nid).val();
      }
      return result;
    },
    checkval: checkval,
    checkoption: checkoption,
    formID: function(fname) {
      if (formname !== undefined) formname = fname;
      return formname;
    },
    pageID: function() {
      return elemDefine.pageID;
    },
    optionval: optionval,
    tableval: tableval,
    chartval: chartval,
    tableaction: function(action, rowindex, rowdata) {
      if (action == "_addrow") table_addrow();
      else if (action == "_delrow") table_delrow(rowindex);
      else if (action == "_updaterow") table_updaterow(rowindex, rowdata);
    },
    tableajax: tableajax
  };
}

function init_input(domId, elem) {
  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? DEFAULTPAGECOL : elem.colwidth);
  htmlText.push("<div  id='" + domId + "_comp'" + " class=' layui-input-inline " + colwidth + "    '>");
  htmlText.push("<label class='layui-form-label form-lable ' >" + elem.label);
  htmlText.push(isRequired(elem) ? "<i class='iconfont must'> * </i>" : "");
  htmlText.push(" </label>");
  htmlText.push("<div class='layui-input-block '>");
  // htmlText.push("<div class='layui-input-inline input-inline '>");
  htmlText.push("<input type='text' class='layui-input " + (elem.disabled ? " layui-disabled" : " ") + "' ");
  // htmlText.push("<input type='text' class='layui-input form-input " +
  // (elem.disabled ? " layui-disabled" : " ") + "' ");
  htmlText.push(" placeholder='请输入内容' ");
  htmlText.push(" id='" + domId + "'");
  htmlText.push(" lay-filter ='" + domId + "'");
  htmlText.push(" formID='" + elem.formID + "'");

  htmlText.push(addELemAttt(elem));

  htmlText.push(elem.disabled ? " disabled" : " ");
  htmlText.push(">");
  htmlText.push("</div>");
  htmlText.push("</div>");

  return htmlText.join(" ");
}

function init_chart(id, elem) {
  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? DEFAULTPAGECOL : elem.colwidth);
  htmlText.push("<div class=' layui-input-inline " + colwidth + "    '>");
  htmlText.push("<div class='layui-input-block ' ");
  htmlText.push(" id='" + id + "' >");
  htmlText.push("</div>");
  htmlText.push("</div>");
  return htmlText.join(" ");
}

function init_txtarea(id, elem) {
  var htmlText = [];
  htmlText.push("<div class='layui-form-item layui-form-text'>");
  htmlText.push("<label class='layui-form-label ' >");
  htmlText.push(elem.label + " </label>");
  htmlText.push("<div class='layui-input-block '>");
  htmlText.push("<textarea type='text'  class='layui-textarea' ");
  htmlText.push(" placeholder='请输入内容' ");
  htmlText.push(" id='" + id + "' ");

  htmlText.push(addELemAttt(elem));

  htmlText.push("></textarea>");
  htmlText.push("</div>");
  htmlText.push("</div>");

  return htmlText.join(" ");
}

function init_undefined() {
  return "<div></div>";
}

function init_button(domId, elem) {
  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? 1 : elem.colwidth);
  htmlText.push("<div  id='" + domId + "_comp'" + " class='layui-input-inline  " + colwidth + "  '> ");
  if (elem.color == "white")
    htmlText.push("<a class='layui-form-item layui-input-inline layui-btn layui-btn-primary layui-btn-md " + (elem.disabled ? "layui-btn-disabled" : "") + "'  ");
  else {
    htmlText.push("<a class='layui-form-item layui-input-inline layui-btn layui-btn-normal layui-btn-md " + (elem.disabled ? "layui-btn-disabled" : "") + "'  ");
    if (elem.color) htmlText.push("style='background-color:" + elem.color + "' ");
  }
  if (elem.id) htmlText.push(isPermitted(elem.id) ? "" : "  style='visibility:hidden' ");

  htmlText.push(addELemAttt(elem));
  htmlText.push(" lay-filter='" + domId + "'");
  htmlText.push(" id='" + domId + "'>");
  htmlText.push(elem.label);
  htmlText.push("</a>");
  htmlText.push("</div>");
  return htmlText.join("");
}

function init_btndropdown(id, elem) {
  // elem: {type:btndropdown, buttons:[{id,label,cb_func.....},{}]}

  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? 1 : elem.colwidth);
  //var colwidth = "";
  htmlText.push("<div  id='" + id + "_comp'" + " class='layui-input-inline  " + colwidth + "  ' style='left:-20px;background-color: #fff!important;' > ");
  htmlText.push(" <div class='layui-nav' style='left:-20px;background-color: #fff!important;'>");
  htmlText.push("<li class='layui-nav-item' style='height: 28px;  padding: 0 10px;  line-height: 28px;background-color: #1e9fff;'> ");
  htmlText.push(elem.label);
  htmlText.push("");
  htmlText.push("<dl class='layui-nav-child' style='top: 33px;    left: -20px'>");

  for (_btnIdx in elem.buttons) {
    var _btnElem = elem.buttons[_btnIdx];
    _btnElem.formID = elem.formID;
    _btnElem.pageID = elem.pageID;
    htmlText.push("<dd><a ");
    if (_btnElem.color) htmlText.push("style='background-color:" + _btnElem.color + "' ");
    if (_btnElem.id) htmlText.push(isPermitted(_btnElem.id) ? "" : "  style='visibility:hidden' ");
    htmlText.push(" lay-filter='" + _btnElem.id + "'");
    htmlText.push(addELemAttt(_btnElem));

    htmlText.push(" id='" + (_btnElem.id !== undefined ? _btnElem.id : id + "_" + _btnIdx) + "'>");
    htmlText.push(_btnElem.label);
    htmlText.push("</a></dd>");
  }
  htmlText.push("</div>");
  return htmlText.join("");
}

function init_btngroup(id, elem) {
  // elem: {type:btngroup, buttons:[{id,label,cb_func.....},{}]}
  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? 12 : elem.colwidth);
  htmlText.push("<div  id='" + id + "_comp'" + " class='layui-input-inline  " + colwidth + "  '> ");
  for (_btnIdx in elem.buttons) {
    var _btnElem = elem.buttons[_btnIdx];
    _btnElem.formID = elem.formID;
    _btnElem.pageID = elem.pageID;
    if (_btnElem.color === "white") htmlText.push("<a style='color:#4893e6' class='layui-form-item layui-input-inline layui-btn layui-btn-primary layui-btn-md'  ");
    else {
      htmlText.push("<a class='layui-form-item layui-input-inline layui-btn layui-btn-normal layui-btn-md'  ");
      if (_btnElem.color) htmlText.push("style='background-color:" + _btnElem.color + "' ");
    }
    if (_btnElem.id) htmlText.push(isPermitted(_btnElem.id) ? "" : "  style='visibility:hidden' ");
    htmlText.push(addELemAttt(_btnElem));
    htmlText.push(" lay-filter='" + _btnElem.id + "'");
    htmlText.push(" id='" + (_btnElem.id !== undefined ? _btnElem.id : id + "_" + _btnIdx) + "'>");
    htmlText.push(_btnElem.label);
    htmlText.push("</a>");
  }
  htmlText.push("</div>");
  return htmlText.join("");
}

function init_hr() {
  return "<div><hr></div>";
}

function init_br() {
  return "<div style='visibility:hidden'><hr class='layui-bg-gray' style='height:2px'></div>";
}

function init_blank(id, elem) {
  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? DEFAULTPAGECOL : elem.colwidth);
  htmlText.push("<div style='visibility:hidden' class='" + colwidth + " layui-input-inline'>");
  htmlText.push("<div class='layui-input-block '>");
  htmlText.push("</div>");
  htmlText.push("</div>");
  return htmlText.join(" ");
}

function init_image(domId, elem) {
  var htmlText = [];
  var _s = "";
  var _w = elem.width;
  var _h = elem.height;
  if (_w !== undefined && _h !== undefined) {
    _s = "style=width:" + _w + "px" + ";height:" + _h + "px";
  }

  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? DEFAULTPAGECOL : elem.colwidth);
  htmlText.push("<div  id='" + domId + "_comp'" + " class=' layui-input-inline " + colwidth + "    '>");
  htmlText.push("<div class='layui-input-block '>");
  htmlText.push("<img " + _s + "  src='" + elem.src + "'>");
  htmlText.push("</div>");
  htmlText.push("</div>");

  return htmlText.join(" ");
}

function init_checkbox_old(id, elem) {
  //老的滑动变更样式 现在已经变更
  var htmlText = [];
  // elem.single =true;
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? DEFAULTPAGECOL : elem.colwidth);
  htmlText.push("<div  class=' layui-input-inline   " + colwidth + "'>");
  // htmlText.push("<label style='width:80px' class='layui-form-label
  // form-lable ' >");
  htmlText.push("<label class='layui-form-label form-lable   ' >");
  htmlText.push(elem.single ? elem.label : "过滤条件" + "</label>");
  htmlText.push("<div class='layui-input-inline'>");
  htmlText.push("<input type='checkbox' class='layui-input' value='true'");
  htmlText.push(elem.single ? " lay-skin='switch' lay-text='是|否'" : " ");
  htmlText.push(addELemAttt(elem));
  htmlText.push(" title='" + (elem.single ? "是" : elem.label) + "'");
  htmlText.push(" id='" + id + "'");
  htmlText.push(" checked=''");
  htmlText.push("></div>");
  htmlText.push("</div>");

  return htmlText.join("");
}

function init_checkbox(id, elem) {
  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? DEFAULTPAGECOL : elem.colwidth);
  htmlText.push("<div style='height:36px' id='" + id + "_comp'" + " class=' layui-input-inline " + colwidth + "    '>");
  htmlText.push("<label class='layui-form-label form-lable ' >" + elem.label);
  htmlText.push(isRequired(elem) ? "<i class='iconfont must'> * </i>" : "");
  htmlText.push(" </label>");
  htmlText.push("<div class='layui-input-block '  ");

  htmlText.push("id='" + id + "'");
  htmlText.push(addELemAttt(elem));

  htmlText.push(">");
  htmlText.push("</div>");
  htmlText.push("</div>");

  return htmlText.join(" ");
}

function init_hidden(id, elem) {
  var htmlText = [];
  htmlText.push("<div>");
  htmlText.push("<input type='hidden' ");
  htmlText.push(" name='" + elem.id + "' ");
  htmlText.push(" id='" + id + "' ");
  htmlText.push(addELemAttt(elem));
  htmlText.push(" value='" + elem.df_val + "' ");
  htmlText.push("></div");

  return htmlText.join("");
}

function init_select(id, elem) {
  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? DEFAULTPAGECOL : elem.colwidth);
  htmlText.push("<div  id='" + id + "_comp'" + " class='  layui-input-inline  " + colwidth + " '>");
  htmlText.push("<label class='layui-form-label' >" + elem.label + (isRequired(elem) ? "<i class='iconfont must'> * </i>" : "") + "</label>");
  htmlText.push("<div class='layui-input-block'>");
  htmlText.push("<select type='text' class='layui-input' placeholder='请选择' ");
  htmlText.push(elem.disabled ? "disabled='disabled'" : "");
  htmlText.push(" id='" + id + "' ");
  htmlText.push(addELemAttt(elem));
  htmlText.push(" lay-filter='" + id + "' ");
  htmlText.push("'><option value=''></option></select></div>");

  return htmlText.join("");
}

function init_selecttab(id, elem) {
  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? DEFAULTPAGECOL : elem.colwidth);
  htmlText.push("<div  id='" + id + "_comp'" + " class=' layui-input-inline   " + colwidth + "'>");
  htmlText.push("<label class='layui-form-label form-lable ' >");
  htmlText.push(elem.label + (isRequired(elem) ? "<i class='iconfont must'> * </i>" : "") + " </label>");
  htmlText.push("<div class='layui-input-block '>");
  htmlText.push("<input type='text'  autocomplete='off'  class='layui-input " + (elem.disabled ? " layui-disabled" : " ") + "' ");
  htmlText.push("placeholder='请输入内容' ");
  htmlText.push(" id='" + id + "' ");
  htmlText.push(addELemAttt(elem));
  htmlText.push(elem.disabled ? " disabled" : " ");
  htmlText.push(">");
  htmlText.push("</div>");
  htmlText.push("</div>");
  var hidehtm = init_hidden(id + "_hidden", elem);
  return htmlText.join(" ") + hidehtm;
}

function init_table(id, elem, formId) {
  var htmlText = [];
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? 12 : elem.colwidth);
  htmlText.push("<div  id='" + id + "_comp'" + "  class=' layui-input-inline   " + colwidth + "'>");
  htmlText.push("<table class='layui-table'  ");
  htmlText.push(" id='" + id + "' ");
  htmlText.push(addELemAttt(elem));
  htmlText.push(" lay-filter='" + id + "' ");
  htmlText.push(" style='width:100%'></table>");
  htmlText.push("</div>");
  htmlText.push("<div id='" + id + "_page'></div>");

  // 头部工具栏

  htmlText.push("<script type='text/html' id='toolbar" + id + "'>");
  // htmlText.push("<input type='text'
  // style='display:inline-block;width:190px;height:26px;vertical-align:middle;margin-right:-1px;border:
  // 1px solid #C9C9C9;' id='toobarsearch_" + id + "'>" );
  htmlText.push("<input type='text' style='display:none;width:190px;height:26px;vertical-align:middle;margin-right:-1px;border: 1px solid #C9C9C9;' id='toobarsearch_" + id + "'>");
  // htmlText.push("<button class='layui-btn layui-btn-primary layui-btn-sm'
  // lay-event='search'><i class='layui-icon
  // layui-icon-search'></i></button>");
  htmlText.push("<button style='display:none' class='layui-btn layui-btn-primary layui-btn-sm' lay-event='search'><i class='layui-icon layui-icon-search'></i></button>");
  htmlText.push("<button class='layui-btn layui-btn-primary layui-btn-sm' lay-event='addrow'><i class='layui-icon layui-icon-add-1'></i></button>");
  htmlText.push("</script>");

  // 下拉框
  for (cols in elem.tablecols) {
    var col = elem.tablecols[cols];
    if (col && col.type == "select") {
      htmlText.push("<script type='text/html' id='select" + id + col.colname + "'>");
      htmlText.push("<select name='" + elem.id + "' lay-verify='' lay-search='' >"); // disabled>");
      htmlText.push("<option value=210>male</option>");
      htmlText.push("<option value=110>female</option>");
      htmlText.push("</script>");
    }
  }

  // 处理复选框
  for (cols in elem.tablecols) {
    var col = elem.tablecols[cols];
    if (col && col.type == "check") {
      htmlText.push("<script type='text/html' ");
      htmlText.push("id='check" + elem.id + col.colname + "'>");
      htmlText.push("<input type='checkbox' class='layui-bg-blue' lay-event='" + col.colname + "' title=" + col.label + ">");
      htmlText.push("</script>");
    }
  }

  // 处理表格内部按钮-------表格内按钮放在一起
  htmlText.push("<script type='text/html' ");
  htmlText.push("id='btn" + id + "'>");
  for (cols in elem.tablecols) {
    var col = elem.tablecols[cols];
    if (col && col.type == "button") {
      var eventStr = [
        col.cb_func,
        col.cb_target,
        col.cb_target_url,
        col.cb_target_func,
        JSON.stringify(col.cb_target_form),
        col.cb_modal_size,
        col.cb_done_action,
        col.cb_done_clickbtn
      ].join("||");
      htmlText.push("<a  ");
      htmlText.push(addELemAttt(col));
      htmlText.push("class='innertablebtn layui-form-item layui-input-inline layui-btn layui-btn-primary layui-btn-sm' ");
      htmlText.push("lay-event='" + eventStr + "'");
      htmlText.push(">" + col.label + "</a>");
    }
  }
  htmlText.push("</script>");

  return htmlText.join("");
}

function init_date(id, elem) {
  var colwidth = "layui-col-md" + parseInt(isNaN(+elem.colwidth) ? DEFAULTPAGECOL : elem.colwidth);
  return (
    "<div  id='" +
    id +
    "_comp'" +
    " class='layui-input-inline  " +
    colwidth +
    " '>" +
    "<label class='layui-form-label  ' >" +
    elem.label +
    (isRequired(elem) ? "<i class='iconfont must'> * </i>" : "") +
    " </label>" +
    "<div class='layui-input-block  '>" +
    "<input type='text'  class='layui-input " +
    (elem.disabled ? " layui-disabled" : " ") +
    "' autocomplete='off'   " +
    addELemAttt(elem) +
    "' id='" +
    id +
    "'></div>" +
    "</div>" +
    "<script>var laydate = layui.laydate;layui.use('laydate',function(){laydate.render({ elem: '#" +
    id +
    "' ,value: ''})});</script>"
  );
}

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
  "disabled",
  "single",
  "parent_id",
  "name",
  "formID",
  "pageID",
  "cb_workflow_pageid",
  "cb_workflow_elementid"
];

function addELemAttt(elem) {
  var htmlText = [];

  /*
  if (elem.cb_func) htmlText.push(" cb_func='" + elem.cb_func + "'");
  if (elem.cb_target) htmlText.push(" cb_target='" + elem.cb_target + "'");
  if (elem.cb_target_url) htmlText.push(" cb_target_url='" + elem.cb_target_url + "'");
  if (elem.cb_target_func) htmlText.push(" cb_target_func='" + elem.cb_target_func + "'");
  if (elem.cb_target_form) htmlText.push(" cb_target_form='" + elem.cb_target_form + "'");
  if (elem.cb_export_source) htmlText.push(" cb_export_source='" + elem.cb_export_source + "'");
  if (elem.cb_modal_size) htmlText.push(" cb_modal_size='" + elem.cb_modal_size + "'");
  if (elem.cb_done_clickbtn) htmlText.push(" cb_done_clickbtn='" + elem.cb_done_clickbtn + "'");
  if (elem.cb_done_action) htmlText.push(" cb_done_action='" + elem.cb_done_action + "'");
  if (elem.cb_done_message) htmlText.push(" cb_done_message='" + elem.cb_done_message + "'");

  if (elem.df_func) htmlText.push(" df_func='" + elem.df_func + "'");
  if (elem.opt_func) htmlText.push(" opt_func='" + elem.opt_func + "'");
  if (elem.disable_func) htmlText.push(" disable_func='" + elem.disable_func + "'");
  if (elem.hide_func) htmlText.push(" hide_func='" + elem.hide_func + "'");
  if (elem.tips) htmlText.push(" tips='" + elem.tips + "'");
  if (elem.verify) htmlText.push(" verify='" + elem.verify + "'");
  if (elem.single) htmlText.push(" single='" + elem.single + "'");

  if (elem.id) htmlText.push(" name='" + elem.id + "'");
  if (elem.parent_id) htmlText.push(" parent_id='" + elem.parent_id + "'");
  */

  for (idx in attrList) {
    var attr = attrList[idx];
    if (elem[attr] !== undefined) {
      if (attr === "cb_target_form") htmlText.push(" " + attr + "='" + JSON.stringify(elem[attr]) + "'");
      else if (Array.isArray(elem[attr])) htmlText.push(" " + attr + "='" + elem[attr].join(",") + "'");
      else htmlText.push(" " + attr + "='" + elem[attr] + "'");
    }
  }

  return htmlText.join("");
}

function genForm(targetFormName, targetFormDomId) {
  var id = "form_" + rndStr();
  var htmlText = [];
  if (targetFormName !== "NONAME") htmlText.push("<div class='layui-colla-item'>");
  if (targetFormName !== "NONAME") htmlText.push("<h2 class='layui-colla-title'>" + targetFormName + "</h2>");
  htmlText.push("<div ");
  htmlText.push(" id='" + id + "'");
  htmlText.push(" formtmp='" + targetFormName + "'");
  htmlText.push(" formId='" + targetFormName + "'");
  htmlText.push(" class='formtmp  layui-colla-content layui-show'");
  htmlText.push("></div>");
  if (targetFormName !== "NONAME") htmlText.push("</div>");

  if (!targetFormDomId) $("#" + form_area).append(htmlText.join(""));
  else $(toDomSelector(targetFormDomId)).append(htmlText.join(""));
  return id;
}

function genCustomForm(formOption) {
  var colwidth = "layui-col-md" + parseInt(isNaN(formOption.formwidth) ? 12 : formwidth);
  var id = "form_" + rndStr();
  var _backColor = formOption.backcolor || "#f5f5f5";
  var htmlText = [];
  htmlText.push("<div class='" + colwidth + "'  style='background: " + _backColor + ";'>");
  htmlText.push("<div ");
  htmlText.push(" id='" + id + "'");
  htmlText.push(" formtmp='" + formOption.targetFormName + "'");
  htmlText.push(" class='formtmp   '");
  htmlText.push("></div>");
  htmlText.push("</div>");

  if (!formOption.targetFormDomId) $("#" + form_area).append(htmlText.join(""));
  else $(toDomSelector(targetFormDomId)).append(htmlText.join(""));
  return id;
}

function genCardForm(targetFormName, targetFormDomId, formwidth) {
  var colwidth = "layui-col-md" + parseInt(isNaN(+formwidth) ? 12 : formwidth);
  var id = "form_" + rndStr();
  var htmlText = [];
  htmlText.push("<div class='" + colwidth + "' >");
  htmlText.push("<div class='layui-card' >");
  htmlText.push("<div class=‘layui-card-header’>" + targetFormName + "</div>");
  htmlText.push("<div ");
  htmlText.push(" id='" + id + "'");
  htmlText.push(" formtmp='" + targetFormName + "'");
  htmlText.push(" class='formtmp  layui-card-body'");
  htmlText.push("></div>");
  htmlText.push("</div>");
  htmlText.push("</div>");

  if (!targetFormDomId) $("#" + form_area).append(htmlText.join(""));
  else $(toDomSelector(targetFormDomId)).append(htmlText.join(""));
  return id;
}

// genModal->一个大元素，需要指定一个jsonfile。可以平铺在页面， 也可以作为弹窗
function genModal(targetFormJsonFile, targetDIVid) {
  //var id = "modal_form_" + rndStr();
  var id = targetFormJsonFile;
  var modalformhtml = "<div id='" + id + "' formtmp='" + targetFormJsonFile + "' class='formtmp layui-collapse'/>";
  if (!targetDIVid) $(modalarea).append(modalformhtml);
  else {
    $(toDomSelector(targetDIVid)).append(modalformhtml);
  }
  return id;
}

function getkeyof(obj, key) {
  var result;
  if (isObj(obj)) {
    if (obj[key] !== undefined) result = obj[key];
  }
  return result;
}

function toDomSelector(str) {
  var $ds = "";
  if (!str) $ds = "body";
  else {
    if (str.charAt(0) === "." || str.charAt(0) === "#") $ds = str;
    else $ds = "#" + str;
  }
  return $ds;
}

function toNumber(str) {
  var num = +str;
  if (isNaN(num)) num = 0;
  return num;
}

function getParam(cb_func) {
  var str = paramCopy(funclist[cb_func]);
  return str;
}

function getFormURL(urlString) {
  var thislog = debugLog("console");
  // ie 下工作不正常
  var formUrl = "";
  i = 0;
  do {
    url = formPrefix + jsonformdir[i] + urlString + ".json";
    isNotExist = false;
    try {
      $.ajax({
        url: url,
        async: false,
        type: "HEAD",
        error: function() {
          isNotExist = true;
        }
      });
    } catch (err) {}
    i++;
  } while (i < jsonformdir.length && isNotExist);

  thislog("reques form file name:" + urlString + ";and defined url is:" + url);

  return url;
}

var existID = {}; // 避免生成重复id
function rndStr(len) {
  len = len || 4; // 最短字符长度
  var $chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefhijkmnprstwxyz0123456789";
  var maxPos = $chars.length;
  var str = "";
  var i = 0;
  do {
    str += $chars.charAt(Math.floor(Math.random() * maxPos));
    i++;
  } while (existID[str] || i < len);
  existID[str] = str;
  return str;
}

function paramCopy(obj) {
  if (typeof obj != "object") {
    return obj;
  }
  var newobj = Array.isArray(obj) ? [] : {};
  for (var attr in obj) {
    newobj[attr] = paramCopy(obj[attr]);
  }
  return newobj;
}

function debugLog(SWITCHON) {
  // swicthon: console: 控制台；file:logfile：alert：弹框;none:关闭
  const DEBUGON = true;
  // SWITCHON="file";
  var fn;
  if (!DEBUGON) fn = function() {};
  else if (SWITCHON === "console") {
    fn = function(logmsg) {
      console.log(logmsg);
    };
  } else if (SWITCHON === "file") {
    fn = function(logmsg) {};
  } else fn = function() {};
  return fn;
}

function export2excel(address, inputs, callbackfn) {
  //address = "../../../../info/voucherList.do";
  //inputs = { "voucher": "0", "currency": "0", "secid": "", "portfolio": "PD2019032800000218", "start": "", "end": "", "acctNum": "", "num": "1", "page": "1", "limits": "100000", "export2excel": "1", "exportdata": "[{}]" };
  var controllerAddress = controllerPrefix + address;
  var fileName = address.split("/").pop() + ".xls";
  console.log(inputs);
  if (Array.isArray(inputs)) {
    //静态导出

    var htmlText = [];
    htmlText.push("<table>");
    inputs.map(function(tr) {
      htmlText.push("<tr>");
      tr.map(function(td) {
        htmlText.push("<td>" + td + "</td>");
      });
      htmlText.push("</tr>");
    });
    htmlText.push("</table>");

    let blob = new Blob([htmlText.join("")], { type: "application/vnd.ms-excel" });
    if (isIE()) {
      window.navigator.msSaveOrOpenBlob(blob, fileName);
    } else {
      // 创建blob链接
      $("body").append("<a id=exceldownloadlink></a>");
      let downloadLink = document.getElementById("exceldownloadlink");
      downloadLink.src = URL.createObjectURL(blob);
      downloadLink.download = fileName;
      downloadLink.href = URL.createObjectURL(blob);
      downloadLink.click();
      $("#exceldownloadlink").remove();
    }
  } else {
    //后台导出
    inputs.export2excel = "1";
    var formData = new FormData();
    for (i in inputs) formData.append(i, inputs[i]);
    const xhr = new XMLHttpRequest();
    xhr.open("post", controllerAddress, true);
    // 请求类型 bufffer
    xhr.responseType = "blob";
    xhr.onload = function() {
      if (xhr.status === 200 || xhr.status === 304) {
        // 将后台 buffer 转换为 blob
        let blob = new Blob([xhr.response], { type: "application/vnd.ms-excel" });

        if (isIE()) {
          window.navigator.msSaveOrOpenBlob(blob, fileName);
        } else {
          // 创建blob链接
          $("body").append("<a id=exceldownloadlink></a>");
          let downloadLink = document.getElementById("exceldownloadlink");
          downloadLink.src = URL.createObjectURL(blob);
          downloadLink.download = fileName;
          downloadLink.href = URL.createObjectURL(blob);
          downloadLink.click();
          $("#exceldownloadlink").remove();
        }
      }
    };
    xhr.send(formData);
  }
}

function fetch(address, inputs, callbackfn) {
  // if (SYS_WORKFLOW_CONTROL) fetch_wfl(address, inputs, callbackfn);

  var fetchlog = debugLog("console");
  var controllerAddress = controllerPrefix + address;
  fetchlog("fetching url:" + controllerAddress);
  var data;
  var _pages = "1";
  var _limits = "100000";
  $.extend(true, inputs, { page: _pages, limits: _limits });

  fetchlog("地址:" + controllerAddress + "<---参数:" + JSON.stringify(inputs).replace(/\\/g, ""));

  $.ajax({
    url: controllerAddress,
    async: false,
    type: "POST",
    data: inputs,
    success: function(json) {
      fetchlog("返回结果：" + JSON.stringify(json).replace(/\\/g, ""));
      if (typeof json == "undefined") json = ""; //无返回值

      try {
        json = $.parseJSON(json);
      } catch (e) {}

      if (isObj(json.status) && +json.status.success === 0) {
        //后台返回错误
        var errorStatusCode = json.status.code;
        var errorStatusMessage = json.status.message;
        var errorStatusLevel = json.status.level;
        pop_message("fail", json.status.message);
        data = FETCHFAILED;
      } else {
        //成功返回
        data = typeof json.data !== "undefined" ? json.data : json;
        if (typeof callbackfn === "function") callbackfn(data, json.pages, json.totalCount);
      }
    },
    error: function(xhr, ajaxOptions, thrownError) {
      var msg = "调用方法'" + controllerAddress + "' 时发生错误;";
      fetchlog(msg);
    }
  });
  return data;
}

function fetch_wfl(address, inputs, elementId, pageId, callbackfn) {
  var fetchlog = debugLog("console");
  var controllerAddress = controllerPrefix + address;
  fetchlog("fetching url:" + controllerAddress);
  var _pages = "1";
  var _limits = "100000";
  $.extend(true, inputs, { page: _pages, limits: _limits });

  fetchlog("地址:" + controllerAddress + "<---参数:" + JSON.stringify(inputs).replace(/\\/g, ""));

  var verifyinput = $.extend(null, inputs, { commitCheck: "1" });
  //调用workflow
  WorkFlow.doSubmit(controllerAddress, inputs, elementId, pageId, function() {});
  /*
  fetch(address, verifyinput, function(data) {
    WorkFlow.doSubmit(controllerAddress, inputs, elementId, pageId, function() {
    });
  });
  */
}

function pop_message(status, msg, fn) {
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

function isRequired(elem) {
  var result = false;
  if (elem.verify && elem.verify.indexOf("required") != -1) {
    result = true;
  }
  return result;
}

function isObj(obj) {
  if (!obj) return false;
  return Object.prototype.toString.call(obj) == "[object Object]";
}

function isPermitted(elementid) {
  var permitUrl = controllerPrefix + "info/getSessionBtn.do";
  if (!SYS_PERMIT_CONTROL) return true;
  else {
    var result = false;
    $.ajax({
      url: permitUrl,
      async: false,
      type: "POST",
      data: { buttonId: elementid },
      success: function(data) {
        result = data || "true";
      }
    });
    console.log("id:" + elementid + ";permitt:" + result);
    return result === "true";
  }
}

function toObjList(value, keyword) {
  // 1. 字符串，val 单值，需要转为{data:[{funcStr:val}]}
  // 2. 单层数组,[val1,val2...],转为{data:[{funcStr:val1},{funcStr:val2}]}
  // 3.
  // 单层对象,{key1:val1,key2:val2...},转为{data:[{keyword:key1,value:val1},{keyword:key2,value:val2}]},
  // 4.
  // 双层数组,[[val11,val12],[val21,val22]...],转为{data:[{0:val11,1:val12},{0:val21,1:val22}]}，用索引做key，保持顺序
  var result = [];
  if (typeof value !== "object") {
    // 单值
    var a = {};
    if (keyword == undefined) keyword = "value";
    a[keyword] = value;
    result.push(a);
  } else if (Array.isArray(value)) {
    if (Array.isArray(value[0])) {
      // 双层数组
      for (key in value) {
        result.push(
          value[key].reduce(function(pv, cv, ci, arr) {
            pv[ci] = cv;
            return pv;
          }, {})
        );
      }
    } else if (typeof value[0] !== "object") {
      // 单层数组
      for (key in value) result.push({ keyword: key, value: value[key] });
    } else {
      result = value;
    }
  } else {
    // 单层对象&
    for (key in value) result.push({ keyword: key, value: value[key] });
  }
  return result;
}

function isIE() {
  if (!!window.ActiveXObject || "ActiveXObject" in window) {
    return true;
  } else {
    return false;
  }
}
