(function () {
    /**
     * 定义可质押债券列表
     */
    var avaPledgeBondTBL = $("#avaPledgeBondList").joyintable({
        // ajax请求数据
        ajax: {
            // 请求路径
            url: basePath+'invest/entrust/etrstandardbond/EtrStandardBond002/selectAvaPledgeBondList', //请求路径
            // 请求类型
            type: 'POST',
            data: function () {
                var param = {};
                param.portfolioId = $("#portfolioId").val();
                param.tradePlace = $("#tradePlace").val();
                param.secAcctId = $("#secAcctId").val();
                // 获取请求参数
                return param;
            }
        },
        page: false,
        loadonce: true,
        searchOnInit:true,
        height:200,
        /**
         * 定义表头
         */
        cols: [
            {field: 'portfolioId', width: 150, title: 'EtrStandardBond002.avaColModel.portfolioId'}
            // 债券代码
            ,{field: 'finprodMarketId', width: 150, title: 'EtrStandardBond002.avaColModel.finProdId'}
            // 证券账户/通道
            , {field: 'secAcctId', width: 150, title: function(){return JoyinUtil.getLabel("EtrStandardBond002.avaColModel.secAcctId")+JoyinUtil.getLabel("invest.channelId");}()}
            // 交易场所
            , {field: 'tradePlaceText', width: 150, title: 'EtrStandardBond002.avaColModel.tradePlace'}
            // 券面总额
            , {
                field: 'posStrdPrinAmt',
                width: 150,
                title: 'EtrStandardBond002.avaColModel.posStrdPrinAmt',
                sortable: false,
                type: 'money',
                digit: 2
            }
            // 可质押券面总额
            , {
                field: 'pledStrdPrinAmt',
                width: 150,
                title: 'EtrStandardBond002.avaColModel.pledStrdPrinAmt',
                sortable: false,
                type: 'money',
                digit: 2
            }
            , {field: 'portfolioId', visible: false}
            , {field: 'tradePlace', visible: false}
            , {field: 'secAcctId', visible: false}

        ]
    });

    var tempPledgePrinAmt;
    var tempInStdrdPrinAmt;

    /**
     * 定义入库质押券列表
     */
    var pledgeBondTbl = $("#pledgeBondList").joyintable({
    	page: false,
        loadonce: true,
        height:200,
        cols: [
            {field: 'portfolioId', width: 150, title: 'EtrStandardBond002.pledgeColModel.portfolioId'}
            // 债券代码
            ,{field: 'finprodMarketId', width: 150, title: 'EtrStandardBond002.pledgeColModel.finProdId', sortable: false}
            // 证券账户/通道
            , {field: 'secAcctId', width: 150, title: function(){return JoyinUtil.getLabel("EtrStandardBond002.pledgeColModel.secAcctId")+JoyinUtil.getLabel("invest.channelId");}(),sortable: false}
            // 交易场所
            , {field: 'tradePlaceText',width: 150,title: 'EtrStandardBond002.pledgeColModel.tradePlace',sortable: false}
            // 可质押券面总额
            , {
                field: 'pledStrdPrinAmt',
                width: 150,
                title: 'EtrStandardBond002.pledgeColModel.pledStrdPrinAmt',
                sortable: false,
                type: 'money',
                digit: 2
            }
            // 折算比例%
            , {
                field: 'scale',
                width: 150,
                title: 'EtrStandardBond002.pledgeColModel.scale',
                sortable: false,
                type: 'number',
                digit: 2
            }
            , {field: 'portfolioId', visible: false}
            , {field: 'tradePlace', visible: false}
            , {field: 'secAcctId', visible: false}
            , {field: 'branch', visible: false}
            , {field: 'finProdId', visible: false}
        ],
        beforeEditCell: function (preVal, field, rowData) {
            if (field === "pledgePrinAmt") {
                tempPledgePrinAmt = preVal.replace(/\,/g, '');
            }
//            if (field === "inStdrdPrinAmt") {
//                tempInStdrdPrinAmt = preVal.replace(/\,/g, '');
//            }
            return true;
        },
        
    });

    /**
     * 校验行数据中证券账户是否不一致
     * @param array
     * @returns {boolean}
     */
    var checkIsDifferent = function (array) {
        var isDifferent = false;
        if (array != null && array.length > 1) {
            for (var index in array) {
                if (index < array.length - 1
                    && array[index].secAcctId != array[index * 1 + 1].secAcctId) {
                    isDifferent = true;
                    break;
                }
            }
        }
        return isDifferent;
    };

    /**
     * 校验折算率不能为空
     */
    var checkDiscountRate = function (array) {
        var isnull = false;
        $.each(array, function (i, v) {
                if (!v.discountRate) {
                    isnull = true;
                    return isnull;
                }
        });
        return isnull;
    }

    /**
     * 加入按钮按下
     * @param array
     * @returns {boolean}
     */
    var joyinProcess = function () {
        var avaSelectedRows = avaPledgeBondTBL.getAllCheckedRows();
        var pledgeAllRows = pledgeBondTbl.getAllRows();
        var isStoreHouse ;
        var params;
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstandardbond/EtrStandardBond001/selectPageData', params, function (result) {
            if(result&&!result.data){
                  isStoreHouse = result;
            }
        },false);
        var istrue = false;
        if(avaSelectedRows != null && avaSelectedRows.length > 0&&isStoreHouse != null && isStoreHouse.root.length > 0){
                    var isStoreHouseList = isStoreHouse.root;
                    for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                        for (var j in isStoreHouseList) {
                            if (isStoreHouseList[j].portfolioId == avaSelectedRows[i].portfolioId
                                && isStoreHouseList[j].tradePlace == avaSelectedRows[i].tradePlace
                                && isStoreHouseList[j].secAcctId == avaSelectedRows[i].secAcctId
                                && isStoreHouseList[j].finprodMarketId == avaSelectedRows[i].finprodMarketId
                              ) {
                                    istrue = true;
                            }
                        }
                    }

         }
         if(istrue){
               Joyin.confirm("所选的质押券包含有已入库的质押券，是否继续添加？", function () {
                           if (avaSelectedRows != null && avaSelectedRows.length > 0) {
                                          // 若添加的质押券不只一个，且登记托管机构不全相同，则保存质押券时提示质押券登记托管机构不同，不允许保存；
                                          // 如果 选择的待质押劵 和质押劵列表中债券托管机构不一致 则 提示债券托管机构不一致，不能添加！
                                          var isDuplicated = false;
                                          var mergeArray = new Array();
                                          mergeArray = _.concat(mergeArray, avaSelectedRows);
                                          mergeArray = _.concat(mergeArray, pledgeAllRows);
                                          if (checkIsDifferent(mergeArray)) {
                                              Joyin.error(JoyinUtil.getMessage("TRD_E_0010"));
                                              return false;
                                          }
                                          // 如果 选择的待质押劵在质押劵列表中存在  则 提示 是否覆盖  质押劵列表中债券
                                          var isConfirm = false;
                                          var confirmMessageArray = new Array();

            // 倒序删除，正序会存在问题
            for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                for (var j in pledgeAllRows) {
                    if (pledgeAllRows[j].portfolioId == avaSelectedRows[i].portfolioId
                        && pledgeAllRows[j].tradePlace == avaSelectedRows[i].tradePlace
                        && pledgeAllRows[j].secAcctId == avaSelectedRows[i].secAcctId
                        && pledgeAllRows[j].finprodMarketId == avaSelectedRows[i].finprodMarketId) {
                        isConfirm = true;
                        confirmMessageArray.push(avaSelectedRows[i].finprodMarketId);
                        break;
                    }
                }
            }
            if (isConfirm) {
                Joyin.confirm(JoyinUtil.getMessage("TRD_C_0003", confirmMessageArray.join("，")), function () {
                    // 倒序删除，正序会存在问题
                    for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                        for (var j in pledgeAllRows) {
                            // 覆盖质押券列表中重复记录
                            if (pledgeAllRows[j].portfolioId == avaSelectedRows[i].portfolioId
                                && pledgeAllRows[j].tradePlace == avaSelectedRows[i].tradePlace
                                && pledgeAllRows[j].secAcctId == avaSelectedRows[i].secAcctId
                                && pledgeAllRows[j].finprodMarketId == avaSelectedRows[i].finprodMarketId) {
                                pledgeBondTbl.removeRow(pledgeAllRows[j]);
                            }
                        }
                        avaPledgeBondTBL.removeRow(avaSelectedRows[i]);
                        pledgeBondTbl.addRow(avaSelectedRows[i]);
                    }
                });
            } else {
                // 倒序删除，正序会存在问题
                for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                	avaPledgeBondTBL.removeRow(avaSelectedRows[i]);
                    pledgeBondTbl.addRow(avaSelectedRows[i]);
                                              }
                                          }
                                      }


                    });

            }else{
                if (avaSelectedRows != null && avaSelectedRows.length > 0) {
                      // 若添加的质押券不只一个，且登记托管机构不全相同，则保存质押券时提示质押券登记托管机构不同，不允许保存；
                      // 如果 选择的待质押劵 和质押劵列表中债券托管机构不一致 则 提示债券托管机构不一致，不能添加！
                      var isDuplicated = false;
                      var mergeArray = new Array();
                      mergeArray = _.concat(mergeArray, avaSelectedRows);
                      mergeArray = _.concat(mergeArray, pledgeAllRows);
                      if (checkIsDifferent(mergeArray)) {
                          Joyin.error(JoyinUtil.getMessage("TRD_E_0010"));
                          return false;
                      }
                      // 如果 选择的待质押劵在质押劵列表中存在  则 提示 是否覆盖  质押劵列表中债券
                      var isConfirm = false;
                      var confirmMessageArray = new Array();

                      // 倒序删除，正序会存在问题
                      for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                          for (var j in pledgeAllRows) {
                              if (pledgeAllRows[j].portfolioId == avaSelectedRows[i].portfolioId
                                  && pledgeAllRows[j].tradePlace == avaSelectedRows[i].tradePlace
                                  && pledgeAllRows[j].secAcctId == avaSelectedRows[i].secAcctId
                                  && pledgeAllRows[j].finprodMarketId == avaSelectedRows[i].finprodMarketId) {
                                  isConfirm = true;
                                  confirmMessageArray.push(avaSelectedRows[i].finprodMarketId);
                                  break;
                              }
                          }
                      }
                      if (isConfirm) {
                          Joyin.confirm(JoyinUtil.getMessage("TRD_C_0003", confirmMessageArray.join("，")), function () {
                              // 倒序删除，正序会存在问题
                              for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                                  for (var j in pledgeAllRows) {
                                      // 覆盖质押券列表中重复记录
                                      if (pledgeAllRows[j].portfolioId == avaSelectedRows[i].portfolioId
                                          && pledgeAllRows[j].tradePlace == avaSelectedRows[i].tradePlace
                                          && pledgeAllRows[j].secAcctId == avaSelectedRows[i].secAcctId
                                          && pledgeAllRows[j].finprodMarketId == avaSelectedRows[i].finprodMarketId) {
                                          pledgeBondTbl.removeRow(pledgeAllRows[j]);
                                      }
                                  }
                                  avaPledgeBondTBL.removeRow(avaSelectedRows[i]);
                                  pledgeBondTbl.addRow(avaSelectedRows[i]);
                              }
                          });
                      } else {
                          // 倒序删除，正序会存在问题
                          for (var i = avaSelectedRows.length - 1; i >= 0; i--) {
                            avaPledgeBondTBL.removeRow(avaSelectedRows[i]);
                              pledgeBondTbl.addRow(avaSelectedRows[i]);
                          }
                      }
                  }
          }
    };

    /**
     * 移除按钮按下
     * @param array
     * @returns {boolean}
     */
    var removeProcess = function () {
        var avaRows = avaPledgeBondTBL.getAllRows();
        var pledgeAllRows = pledgeBondTbl.getAllCheckedRows();
        if (pledgeAllRows != null && pledgeAllRows.length > 0) {
            // 倒序删除，正序会存在问题
            for (var i = pledgeAllRows.length - 1; i >= 0; i--) {
                var isAvaHave = false;
                // 删除可用质押券列表中相同记录
                for (var j in avaRows) {
                    if (avaRows[j].portfolioId == pledgeAllRows[i].portfolioId
                        && avaRows[j].tradePlace == pledgeAllRows[i].tradePlace
                        && avaRows[j].secAcctId == pledgeAllRows[i].secAcctId
                        && avaRows[j].finprodMarketId == pledgeAllRows[i].finprodMarketId) {
                        isAvaHave = true;
                        break;
                    }
                }
                pledgeBondTbl.removeRow(pledgeAllRows[i]);
                if (!isAvaHave) {
                    avaPledgeBondTBL.addRow(pledgeAllRows[i]);
                }
            }
        }
    };

    /**
     * 保存按钮按下
     *
     * @param array
     * @returns {boolean}
     */
    var saveOrSubmit = function (operateType) {
        var pledgeAllRows = pledgeBondTbl.getAllRows();
        if(pledgeAllRows.length==0){
            Joyin.error(JoyinUtil.getMessage("TRD_BOND_STANDAR_001"));
            return false;
        }
        if (checkIsDifferent(pledgeAllRows)) {
            Joyin.error(JoyinUtil.getMessage("TRD_E_0010"));
            return false;
        }
        var object = $.serializeToObject('#submitForm',true);
        object.pageModel = pageModel;
        object.operateType = operateType;
        object.pledgeBondList = pledgeBondTbl.getAllRows();
        object.avaPledgeBondList = avaPledgeBondTBL.getAllRows();
        Joyin.ajaxJson(basePath + 'invest/entrust/etrstandardbond/EtrStandardBond002/check', object, function (result) {
            Joyin.confirm(result.data, function () {
                if (operateType === 'save') {
                    WorkFlow.doSaveList(basePath + 'invest/entrust/etrstandardbond/EtrStandardBond002/save', object, "TrdStandardBond002_btn_save");
                } else {
                    WorkFlow.doSubmitList(basePath + 'invest/entrust/etrstandardbond/EtrStandardBond002/save', object, "TrdStandardBond002_btn_submit");
                }
            });
        });
    };

    /**
     * 画面加载完成
     */
    $(function () {
    	pledgeBondTbl.load();

        // 工作流查看时，直接加载质押券列表
        if (pledgeBondList) {
            pledgeBondTbl.reload({data: JSON.parse(pledgeBondList), url: null});
        }
        if (avaPledgeBondList) {
            avaPledgeBondTBL.load();
            avaPledgeBondTBL.reload({data: JSON.parse(avaPledgeBondList), url: null});
        }else{
            avaPledgeBondTBL.reload();
        }
        // 检索按钮事件
        $("#EtrStandardBond002_btn_search").click(function () {
            avaPledgeBondTBL.load();
        });
        // 加入按钮事件
        $("#EtrStandardBond002_btn_joyin").click(joyinProcess);
        // 移除按钮事件
        $("#EtrStandardBond002_btn_remove").click(removeProcess);
        // 保存按钮事件,避免进入个人代办，使用submit方法
        $("#EtrStandardBond002_btn_save").click(function () {
            saveOrSubmit('submit');
        });
        // 提交按钮事件
        $("#EtrStandardBond002_btn_submit").click(function () {
            saveOrSubmit('submit');
        });
        // 重新初始化证券账户
        if (!$('#secAcctId').val() && secAcctId) {
            $('#secAcctId').textSelect('setValue', secAcctId);
        }
    });
}());