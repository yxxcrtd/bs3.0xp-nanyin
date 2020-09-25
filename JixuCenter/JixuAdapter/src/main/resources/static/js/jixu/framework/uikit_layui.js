const form_area = "formbody";
const modalarea = "#modal_area";

function genComp(elem, formID) {
  var pageComp = {};
  var domId = "S" + rndStr();
  var nid = "#" + domId;
  var elemDefine = paramCopy(elem);
  var value = "";
  var optionvalue = {};
  var formname = formID;
  var tablecolname = [];
  var clickedrowid = "";
  var htmlText = "";
  var buttonset = false;
  var eventList = {};
  var rowdata = {};
  var table;
  var tabledata = [];
  var hidevalue;
  var options = {};

  function isObj(obj) {
    return Object.prototype.toString.call(obj) == "[object Object]";
  }

  //初始化特殊元素

  if (elem.type == "table") {
    htmlText = init_table(domId, elem.label, elem);
    tablecolname = elem.tablecols.map(function(col) {
      return col.colname;
    });
  } else {
    var funstr =
      "init_" + elem.type + "('" + domId + "','" + elem.label + "',elemDefine)";
    htmlText = eval(funstr);
  }

  //下拉框select初始化值
  var optionval = function(optval) {
    if (elemDefine.type !== "select") return;
    var optionvalue = [];
    if (isObj(optval)) {
      for (value in optval) {
        optionvalue.push({ value: value, option: optval[value] });
      }
    } else if (Array.isArray(optval)) optionvalue = optval;
    var newHtml = "";
    var newHtml = optionvalue.reduce(function(pv, cv, ci, arr) {
      return pv + "<option value='" + cv.value + "'>" + cv.option + "</option>";
    }, "");

    $(nid).html(newHtml);
    //$(nid).val(first);
  };

  if (elem.type === "select") optionval(elem.opt_val);

  var tabledataSet = function() {
    //按行检查checkbox的值
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

  //instant function define 初始化/更新表格数据
  var tableval = function(tableArray) {
    if (!tableArray) return tabledataSet();
    var arr = tableArray;

    //--------Dirty CODE STRAT--- 为了临时接口文件。数据为{key1:value1,key2:value2,.......}
    if (Object.prototype.toString.call(tableArray) == "[object Object]") {
      arr = [];
      for (k in tableArray) {
        arr.push([k, tableArray[k]]);
      }
    }
    //--------Dirty CODE END-------

    if (!Array.isArray(arr)) return;
    tabledata = arr.map(function(rowdata) {
      var arr2obj = {};
      if (Array.isArray(rowdata)) {
        var p = 0;
        for (i in tablecolname)
          arr2obj[tablecolname[i]] = !tablecolname[i] ? "" : rowdata[p++];
        return arr2obj;
      } else return rowdata;
    });

    var firstbtn = true;
    options.data = tabledata;
    options.page = elemDefine.page ? elemDefine.page : "false";
    options.limit = "99";
    options.elem = nid;
    options.cols = [
      elem.tablecols.reduce(function(prow, row, i, a) {
        if (row.type == "checkbox") prow.push({ type: "checkbox" });
        else if (row.type == "number") prow.push({ type: "numbers" });
        else if (row.type == "button") {
          eventList[row.id] = row.id;
          if (firstbtn) {
            firstbtn = false;
            prow.push({
              title: "操作",
              toolbar: "#btn" + domId //row.id
            });
          }
        } else if (row.type == "check")
          prow.push({
            templet: "#check" + elemDefine.id + row.colname,
            title: row.label,
            field: row.colname
          });
        else prow.push({ field: row.colname, title: row.label });
        return prow;
      }, [])
    ];
    layui.use(["table", "form"], function() {
      table = layui.table;
    });
    table.render(options);
    //layui.form.render("select");

    //更改checkbox样式
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
                console.log($(a).attr("checked"));
              });
            //$(a).find(".layui-unselect").map(function(i, a) {$(a).addClass("layui-form-checked");console.log($(a).attr("class"));});
          }
        });
    });

    //绑定按钮事件
    $(".innertablebtn").on("click", function() {
      var self = this;
      var fn = $(self).attr("cb_func");
      var rowid = +$(self)
        .parentsUntil("tbody")
        .last()
        .attr("data-index");

      var rowdata = {};
      $(self)
        .parentsUntil("tr")
        .parent()
        .find("td")
        .map(function(i, a) {
          var v = $(a)
            .find("div")
            .text();
          var k = $(a).attr("data-field");
          if (k) rowdata[k] = v;
        });

      if (fn.substr(0, 1) == "_") tablefunc(fn, rowid);
    });
  };

  //表格初始化

  var tablefunc = function(operation, rowid, rowdata) {
    function tabledataswap(row1, row2) {
      var rd = tabledata[row1];
      tabledata[row1] = tabledata[row2];
      tabledata[row2] = rd;
    }

    if (operation === "_moverowup") {
      if (rowid > 0) tabledataswap(rowid, rowid - 1);
    } else if (operation === "_moverowdown") {
      if (rowid < tabledata.length) tabledataswap(rowid, rowid + 1);
    } else if (operation === "_delrow") {
      if (rowid) {
        if (tabledata.length == 1) tabledata = [];
        else {
          for (i = rowid; i < tabledata.length - 1; i++) {
            tabledata[i] = tabledata[i + 1];
          }
          tabledata.length--;
        }
      }
    } else if (operation === "_addrow") {
      if (!rowid || rowid == 0) {
        tabledata[tabledata.length++] = rowdata;
      } else {
        tabledata.length++;
        for (i = tabledata.length - 1; i > rowid; i--) {
          tabledata[i] = tabledata[i - 1];
        }
        tabledata[rowid] = rowdata;
      }
    }
    tableval(tabledata);
    //options.data = tabledata;
    //tableIns.reload();
  };

  return {
    id: function(id) {
      if (id !== undefined) domId = id;
      return domId;
    },
    nid: function() {
      return nid;
    },
    html: function(html) {
      if (html !== undefined) htmlText = html;
      return htmlText;
    },
    elem: function() {
      return elemDefine;
    },
    value: function(val) {
      if (val !== undefined) {
        if (
          Array.isArray(val) ||
          Object.prototype.toString.call(val) == "[object Object]"
        ) {
          if (elemDefine.type == "select") {
            //this.optionval(val);
            //for (k in val) value = val[k];
          } else if (elemDefine.type == "list") {
            val.reduce(function(pv, cv, ci, arr) {}, []); //合并所有的key、value
            $(nid).append(function() {
              val
                .map(function(row) {
                  return "<td>" + a + "</td>";
                })
                .join("");
            });
          }
        } else value = val;
        $(nid).val(value);
      }
      return $(nid).val();
    },
    checkval: function(val) {
      if (val) val = true;
      else val = false;
      if (elem.type !== "checkbox") return;
      //$($("input:checkbox[name='navto1']:checked")[0]).val()
      return $($("input:checkbox[name='" + elem.id + "']:checked")[0]).val();
    },
    formname: function(fname) {
      if (formname !== undefined) formname = fname;
      return formname;
    },
    optionval: optionval,
    tableval: tableval,
    table: function() {
      return table;
    },
    tablefunc: tablefunc
  };
}

function init_input(id, TXTLABEL, elem) {
  var htmlText = [];
  htmlText.push("<div class='layui-col-md4 layui-input-inline'>");
  htmlText.push("<label class='layui-form-label form-lable ' >");
  htmlText.push(TXTLABEL + " </label>");
  htmlText.push("<div class='layui-input-block '>");
  htmlText.push(
    "<input type='text' name='title' class='layui-input " +
      (elem.disabled ? " layui-disabled" : " ") +
      "' "
  );
  htmlText.push("placeholder='请输入内容' ");
  htmlText.push("id='" + id + "'");
  htmlText.push("name='" + elem.id + "'");
  htmlText.push(elem.disabled ? " disabled" : " ");
  htmlText.push(">");
  htmlText.push("</div>");
  htmlText.push("</div>");

  return htmlText.join(" ");
}

function init_txtarea(id, TXTLABEL, elem) {
  var htmlText = [];
  htmlText.push("<div class='layui-form-item layui-form-text'>");
  htmlText.push("<label class='layui-form-label ' >");
  htmlText.push(TXTLABEL + " </label>");
  htmlText.push("<div class='layui-input-block '>");
  htmlText.push("<textarea type='text' name='title' class='layui-textarea' ");
  htmlText.push("placeholder='请输入内容' ");
  htmlText.push("id='" + id + "'");
  htmlText.push("name='" + elem.id + "'");
  htmlText.push("></textarea>");
  htmlText.push("</div>");
  htmlText.push("</div>");

  return htmlText.join(" ");
}

function init_undefined() {
  return "<div></div>";
}
function init_button(id, TXTLABEL, elem) {
  var htmlText = [];
  htmlText.push(
    "<a class='layui-form-item layui-input-inline layui-btn layui-btn-normal layui-btn-md'  "
  );
  htmlText.push(" name='" + elem.id + "'");
  htmlText.push(" id='" + id + "'>");
  htmlText.push(TXTLABEL);
  htmlText.push("</a>");
  return htmlText.join("");
}

function init_redbutton(id, TXTLABEL, elem) {
  var htmlText = [];
  htmlText.push(
    "<a class='layui-form-item layui-input-inline layui-btn layui-btn-danger layui-btn-md'  "
  );
  htmlText.push(" name='" + elem.id + "'");
  htmlText.push(" id='" + id + "'>");
  htmlText.push(TXTLABEL);
  htmlText.push("</a>");
  return htmlText.join("");
}

function init_label(id, TXTLABEL, elem) {
  var htmlText = [];
  htmlText.push(
    "<a class='layui-form-item layui-input-inline layui-btn layui-btn-disabled layui-btn-md'  "
  );
  htmlText.push(" name='" + elem.id + "'");
  htmlText.push(" id='" + id + "'>");
  htmlText.push(TXTLABEL);
  htmlText.push("</a>");
  return htmlText.join("");
}

function init_hr() {
  return "<div><hr></div>";
}

function init_br() {
  return "<div style='visibility:hidden'><hr class='layui-bg-gray'></div>";
}

function init_checkbox(id, TXTLABEL, elem) {
  var htmlText = [];
  htmlText.push("<div class='layui-form-item lay-col-md3 layui-input-inline'>");
  htmlText.push("<div class='layui-input-inline'>");
  htmlText.push("<input type='checkbox'");
  htmlText.push(" name='" + elem.id + "'");
  htmlText.push(" title='" + TXTLABEL + "'");
  htmlText.push(" id='" + id + "'");
  htmlText.push(" checked=''");
  htmlText.push("></div>");
  htmlText.push("</div>");

  return htmlText.join("");
}

function init_hidden(id, TXTLABEL, elem) {
  var htmlText = [];
  htmlText.push("<div>");
  htmlText.push("<input type='hidden' ");
  htmlText.push(" name='" + elem.id + "' ");
  htmlText.push(" id='" + id + "' ");
  htmlText.push(" value='" + elem.df_val + "' ");
  htmlText.push("></div");

  return htmlText.join("");
}

function init_select(id, TXTLABEL) {
  return (
    "<div class=' layui-col-md4 layui-input-inline '>" +
    "<label class='layui-form-label' >" +
    TXTLABEL +
    "</label>" +
    "<div class='layui-input-block'>" +
    "<select type='text' name='interest' class='layui-input '  placeholder='请选择' id='" +
    id +
    "'><option value=''></option></select></div>"
  );
}

function init_selectm(id, TXTLABEL, elem) {
  return (
    "<div class='layui-col-md4 '>" +
    "<label class='layui-form-label' >" +
    TXTLABEL +
    "</label>" +
    "<div class=''>" +
    "<select type='text' name='interest' placeholder='请选择' id='" +
    id +
    "' " +
    "xm-select='" +
    id +
    "'><option value=''></option></select></div>" +
    "<script type='text/javascript'>var formSelects = layui.formSelects;</script>"
  );
}

function init_table(id, TXTLABEL, elem) {
  var htmlText = [];
  htmlText.push("<table class='layui-table'  ");
  htmlText.push("id='" + id + "' ");
  htmlText.push("lay-filter='" + id + "' ");
  htmlText.push("></table>");

  //处理复选框
  for (cols in elem.tablecols) {
    var col = elem.tablecols[cols];
    if (col && col.type == "check") {
      htmlText.push("<script type='text/html' ");
      htmlText.push("id='check" + elem.id + col.colname + "'>");
      htmlText.push(
        "<input type='checkbox' class='layui-bg-blue' lay-event='" +
          col.colname +
          "' title=" +
          col.label +
          ">"
      );
      htmlText.push("</script>");
    }
  }

  //处理表格内部按钮-------表格内按钮放在一起
  htmlText.push("<script type='text/html' ");
  htmlText.push("id='btn" + id + "'>");
  for (cols in elem.tablecols) {
    var col = elem.tablecols[cols];
    if (col && col.type == "button") {
      htmlText.push("<a cb_func='" + col.cb_func + "'");
      htmlText.push("<a cb_target='" + col.cb_target + "'");
      htmlText.push("<a cb_target_url='" + col.cb_target_url + "'");
      htmlText.push(
        "class='innertablebtn layui-form-item layui-input-inline layui-btn layui-btn-normal layui-btn-sm' "
      );
      htmlText.push("lay-event='" + col.id + "'");
      htmlText.push(">" + col.label + "</a>");
    }
  }
  htmlText.push("</script>");

  return htmlText.join("");
}

function init_date(id, TXTLABEL, style) {
  return (
    "<div class='layui-input-inline layui-col-md4'>" +
    "<label class='layui-form-label  ' >" +
    TXTLABEL +
    " </label>" +
    "<div class='layui-input-block  '>" +
    "<input type='text' name='title' class='layui-input' autocomplete='off'   " +
    "id='" +
    id +
    "'></div>" +
    "</div>" +
    "<script>var laydate = layui.laydate;layui.use('laydate',function(){laydate.render({ elem: '#" +
    id +
    "' ,value: '2018-08-18'})});</script>"
  );
}

function init_list(id, TXTLABEL, elem) {
  var htmlText = [];
  htmlText.push("<div class='layui-form'>");
  htmlText.push("<table class='layui-table' ");
  htmlText.push(" id='" + id + "'>");
  htmlText.push("<tbody>");
  htmlText.push("<tr>");
  for (i = 0; i < toNumber(elem.listcol); i++) {
    htmlText.push("<td></td>");
  }
  htmlText.push("</tr>");
  htmlText.push("></table>");
  htmlText.push("</div>");

  return htmlText.join("");
}

function genForm(targetFormName, targetFormDomId) {
  var id = "form_" + rndStr();
  var htmlText = [];
  htmlText.push("<fieldset class='layui-elem-field'>");
  htmlText.push("<legend>" + targetFormName + "</legend>");
  htmlText.push("<div ");
  htmlText.push(" id='" + id + "'");
  htmlText.push(" formtmp='" + targetFormName + "'");
  htmlText.push(" class='formtmp lay-form'");
  htmlText.push("></div>");
  htmlText.push("</fieldset>");

  if (!targetFormDomId) $("#" + form_area).append(htmlText.join(""));
  else $(toDomSelector(targetFormDomId)).append(htmlText.join(""));
  return id;
}

//genModal->一个大元素，需要指定一个jsonfile。可以平铺在页面， 也可以作为弹窗
function genModal(targetFormJsonFile, targetDIVid) {
  var id = "modal_form_" + rndStr();
  var modalformhtml =
    "<div id='" +
    id +
    "' formtmp='" +
    targetFormJsonFile +
    "' class='formtmp layui-form'/>";
  if (!targetDIVid) $(modalarea).append(modalformhtml);
  else {
    $(toDomSelector(targetDIVid)).append(modalformhtml);
  }
  return id;
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
