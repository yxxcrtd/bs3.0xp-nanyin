var fds = databind(); //form dataset
var elemList = {}; //保存所有组件的列表，用以parent_id联动
const PAGECOL = 3;

var initform = function(formbodyid, formJson, params) {
  formbodyid = formbodyid || "formbody";
  var formurl = $("#" + formbodyid).attr("formtmp");
  if (!formurl) formurl = formJson;
  $req = $.get(getFormURL(formurl));
  $req
    .done(function(formlist) {
      for (formid in formlist) {
        var newFormDomID = genForm(formid, formbodyid);
        renderForm(formlist[formid], newFormDomID, params);
      }
    })
    .fail(function() {
      console.log("error when reading " + formurl);
    });
};

initform();

function renderForm(formObj, formID, parameters) {
  //parameters: [{key:value}]传入的参数
  //formObj: [{id:id}]传入的表单模版
  formObj.map(function(elem) {
    var c = genComp(elem, formID);

    //绑定组件
    fds(elem.id, formID, c);
    var $formDom = $("#" + formID);
    $formDom.append(c.html());

    elemList[elem.id] = c.nid();
    $(c.nid()).on("change", function() {
      fds(elem.id, formID, c);
    });

    if (elem.type == "select") {
      if (elem.opt_val) c.optionval(elem.opt_val);
      if (elem.opt_func) {
        var r = exec_func(elem.opt_func);
        if (r && r.data) c.optionval(r.data);
      }
    }

    if (parameters && parameters[elem.id]) {
      c.value(parameters[elem.id]);
      //fds(elem.id, formID, c);
    } else if (elem.df_val !== undefined) {
      //默认值赋值,容许df_val为值、数组、对象
      c.value(elem.df_val);
      //fds(elem.id, formID, c);
    }
    if (elem.type == "table") c.tableval([]);

    //函数赋值by df_func
    if (elem.df_func !== undefined) {
      var func_val = exec_func(elem.df_func);
      if (func_val) {
        if (isObj(func_val)) c.value(func_val.data);
        else c.value(func_val);
        //c.optionval(func_val.data);
        if (func_val !== undefined && elem.type == "table") {
          if (func_val.data !== undefined) c.tableval(func_val.data);
          //临时代码，非标准返回值处理。正常应该result.data=[];
          //单独处理基础列中{k1:v1,k2:v2....}的表格结构
          else c.tableval(func_val);
          tableonclick();
        }
      }
    }

    //联动 paraid 应该为数组
    if (elem.parent_id && elem.parent_id.length > 0) {
      for (i in elem.parent_id) {
        var pid = elem.parent_id[i];
        $(elemList[pid]).on("change", function() {
          var newvalue = exec_func(elem.df_func);
          c.value(newvalue);
          //fds(elem.id, formID, newvalue);
        });
        $(elemList[pid]).on("click", function() {
          var r = exec_func(elem.df_func);
          c.value(r);
          if (isObj(r) && r.data !== undefined) {
            c.tableval(r.data);
            tableonclick();
          }
        });
      }
    }

    function tableonclick() {
      //------表格内部按钮事件----测试版
      $(".innertablebtn")
        .off("click")
        .on("click", function() {
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

          if (fn.substr(0, 1) !== "_") {
            var r = exec_func(fn, null, rowdata);
            if (!r) {
              r = {};
              r.data = {};
            }
            pop_modal(joinObj(rowdata, r.data), $(self).attr("cb_target_url"));
          }
        });
      //------表格内部按钮事件----测试版结束
    }

    function joinObj(obj1, obj2) {
      //obj1-->obj2
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
      return obj2;
    }

    function isObj(obj) {
      return Object.prototype.toString.call(obj) == "[object Object]";
    }

    function pop_modal(result, targeturl) {
      if (!targeturl) targeturl = elem.cb_target_url;
      var newFormID = genModal(targeturl);
      initform(newFormID, targeturl, result);
      var x = Math.random() * 50;
      var y = Math.random() * 50;
      var h = $(window).height() - x - 100;
      var w = $(window).width() - y - 100;

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
          $("#" + newFormID).hide();
        }
      };
      layer.open(layerOption);
    }

    //对于有需要的控件绑定点击事件by cb_func
    if (elem.cb_func !== undefined) {
      if (elem.type == "_UpdateForm") {
        var fid = elem.cb_target_url;
      } else
        $(c.nid()).click(function() {
          var result = exec_func(elem.cb_func, c.formname());
          //根据"cb_target": "page,div,modal",不同 放置到不同的地方，page： 跳转页面，div： 页面上的div，modal：弹框
          if (elem.cb_target == "self") {
            window.location.href =
              elem.cb_target_id + "?" + JSON.stringify(result);
          } else if (elem.cb_target == "div") {
            var newFormID = genForm(elem.cb_target_url, formID);
            renderForm(newFormID, result);
          } else if (elem.cb_target == "modal") {
            if (!isObj(result)) {
              r = {};
              r[elem.cb_func] = result;
            } else r = result;
            pop_modal(r);
          } else if (elem.cb_target == "message") {
            var layerMsg = "";
            if (result && result.status && result.status.message)
              layerMsg = result.status.code + ":" + result.status.message;
            var layerOption = {
              type: 0,
              content: elem.cb_done_message + ":" + layerMsg,
              zIndex: layer.zIndex,
              success: function(layero) {
                layer.setTop(layero);
              }
            };
            layer.open(layerOption);
          }
        });
    }
  });

  layui.use("form", function() {
    var form = layui.form;
    form.render();
  });
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
      if (!formName) return ds[id];
      else return dsform[key];
    } else ds[id] = dsform[key] = value;
  };
}

function exec_func(funcStr, formID, paramObj) {
  if (!funcStr || funcStr == "") return;
  //计算funcStr的值

  var value = "";
  var keyword = funcStr; //暂时为funstr，后期为解析完成的因子

  if (paramObj !== undefined && paramObj[keyword] !== undefined)
    value = paramObj[keyword];
  else if (fds(keyword, formID) !== undefined) {
    var _c = fds(keyword, formID);
    value = _c.value();
    if (_c.elem().type === "checkbox") value = _c.checkval();
    if (_c.elem().type === "table") value = JSON.stringify(_c.tableval());
  } else if (fds(keyword) !== undefined) {
    value = fds(keyword).value();
  } else {
    var cb_msg = getParam(keyword);
    if (cb_msg !== undefined) {
      for (k in cb_msg.inputs) {
        if (k == "all" && cb_msg.inputs[k] == "true") {
          cb_msg.inputs = fds("all_para_in_page");
          break;
          //传递全页面参数给后台 [{id:value}]
        }
        var dp = exec_func(cb_msg.inputs[k], formID, paramObj);
        if (dp !== undefined && k !== "params") cb_msg.inputs[k] = dp;
        if (k == "json")
          cb_msg.inputs[k] = JSON.stringify(fds("all_para_in_page", formID));
        if (k == "params") {
          var result = [];
          for (i in cb_msg.inputs[k]) {
            var vid = cb_msg.inputs[k][i];
            var _v = exec_func(vid);
            result.push(_v);
          }
          cb_msg.inputs[k] = JSON.stringify(result);
        }
      }
      console.log("发送字符串：" + JSON.stringify(cb_msg).replace(/\\/g, ""));
      var value = fetch(getURL(cb_msg.header), cb_msg.inputs, "AlertALL");
      console.log("返回结果：" + JSON.stringify(value));
    }
  }
  return value;
}

function CalcFunc(funcStr) {
  const operator = { "(": 5, ")": 5, "*": 3, "/": 3, "+": 1, "-": 1, ",": -1 };

  function calc(fnStr, arg1, arg2) {
    fnStr = "33*4+price-avg(43*price)*4*((a+b)*c)";
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

    function findSpIdx(Arr) {
      //确定分割点。返回分割点数组
      var result = [];
      var baseline = 0;
      for (i = 0; i < Arr.length; i++) {
        if (operator[Arr[i]]) {
          if (Arr[i] == ")") baseline -= operator[Arr[i]];
          result.push(baseline + operator[Arr[i]]);
          if (Arr[i] == "(") baseline += operator[Arr[i]];
        } else result.push(99);
      }
      min = Math.min.apply(null, result);
      return result.reduce(function(pv, cv, ci, arr) {
        if (cv == min) pv.push(ci);
        return pv;
      }, []);
    }
    function arrSplite(Arr, idxs, SPALL) {
      //idxs:[2,5,6]...分割点的数组
      //如果SPALL=false，则从idxs[0]开始分割， 不包含idx；否则全部分割
      //返回分割好的数组

      var r = [];
      Arr.reduce(function(pv, cv, ci, arr) {}, []);
      for (i = 0; i < idx; i++) r[0].push(Arr[i]);
      for (i = idx + 1; i < Arr.length; i++) r[1].push(Arr[i]);
      return r;
    }
    function createNode(subArr) {
      console.log(subArr);
      //if (subArr[0] == "(" && subArr[-1] == ")") subArr = subArr.slice(1, -1);
      if (subArr.length > 1) {
        var idxs = findSpIdx(subArr);
        return {
          name: subArr[idxs[0]],
          children: arrSplite(subArr, idxs, subArr[idxs[0]] == ",").map(
            function(ar) {
              return createNode(ar);
            }
          )
        };
      } else {
        return { name: subArr[0] };
        //return { children: [], name: subArr[0] };
      }
    }

    var tree = createNode(splitedArr);
    console.log(tree);
  }

  function add(arg1, arg2) {
    return getvalue(arg1) + getvalue(arg2);
  }

  function minus(arg1, arg2) {
    return getvalue(arg1) - getvalue(arg2);
  }

  function mutiby(arg1, arg2) {
    return getvalue(arg1) * getvalue(arg2);
  }

  function divid(arg1, arg2) {
    return getvalue(arg1) / getvalue(arg2);
  }

  function round(arg1, arg2) {
    return getvalue(arg1).fix(arg2);
  }

  function getvalue(str) {
    if (!isNaN(+str)) return +str;
  }
  //根据关键字获取数据
  //结算funcstr的值
  //0. 遍历当前页面所有元素值
  //1. 含有本form元素id的，采用id的当前值；
  //2. 当前form无该id，则查找页面是否包含id；
  //3. 当然页面查找无该id，则像接口查找；
  //4. 支持各个数据结果的基本四则运算及（）；括号支持可以后续完成
  //5.  支持字串为数值计算
  var tree = [];

  //查找父亲id
  function findparentid(p, cid) {
    var pid = "";

    !(function fp(p) {
      if (!p.leftChild || !p.rightChild) return;
      if (p.leftChild.id == cid || p.rightChild.id == cid) pid = p.id;
      else {
        fp(p.leftChild);
        fp(p.rightChild);
      }
    })();
    return pid;
  }

  function findnode(p, nid) {
    var node = {};
    !(function fn(p) {
      if (!p) return;
      if (p.id == nid) node = p;
      else {
        fn(p.leftChild);
        fn(p.rightChild);
      }
    })();
    return node;
  }

  function gennode(fstr) {
    var ps = 0;
    var a = "";
    var pt = {}; //树指针当前节点 pTree
    var pl = {}; //pLast 最后的计算节点
    var pstack = []; //指针堆栈
    while (ps < fstr.length) {
      var str = "";
      a = funcStr.charAt(ps);
      switch (a) {
        case "-":
        case "+":
          var p = {};
          var pnd = {}; //new node
          pnd.name = str;
          pt.id = fstr + ps;
          if (!pt.leftChild) {
            pnd.parenId = pt.id;
            pt.leftChild = pnd;
            pt.operation = a;
          } else {
            pnd.parenId = pl.id;
            pl.rightChild = pnd;
          }
          break;
        case "*":
        case "/":
          break;
        case "(":
          break;
        case ")":
          break;
        default:
          str += a;
      }
    }
  }
}
