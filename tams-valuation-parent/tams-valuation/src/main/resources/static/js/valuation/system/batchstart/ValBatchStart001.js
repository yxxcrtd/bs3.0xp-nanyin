(function () {


    var executeBatch = function (selectedBatchGroup,
                                 selectedProductGroup,
                                 startDate,
                                 endDate) {
        var date = new Date();
        var batchGroupType = $("input[name='batchGroupType']:checked").val();
        var prodGroupType = $("input[name='prodGroupType']:checked").val();
        Joyin.ajax('executeBatch', {
            selectedBatchGroup: selectedBatchGroup,
            selectedProductGroup: selectedProductGroup,
            startDate: startDate,
            endDate: endDate,
            batchGroupType: batchGroupType,
            prodGroupType: prodGroupType,
        }, function () {
            $("#setting_btn_save").attr("title", '20秒后才能再次执行').prop("disabled", true);
            setTimeout(function () {
                $("#setting_btn_save").attr("title", '').prop("disabled", false);
            }, 20000)
        }, '')//realName+"创建于"+date.format("yyyy-MM-dd HH:mm:ss")

    }

    var setTaskToPage = function () {
        $("#selectedBatch").val(selectedTaskIds.join(","));
        var name = selectedTaskNames.join(",");
        $("#selectedBatchName").val(name.length > 50 ? name.substr(0, 50) + "..." : name);
        $("#selectedBatchName").attr("title", selectedTaskNames.join("\r\n"));
    }

    var setProdToPage = function () {
        $("#selectedProd").val(selectedProdIds.join(","));
        var name = selectedProdNames.join(",");
        $("#selectedProdName").val(name.length > 50 ? name.substr(0, 50) + "..." : name);
        $("#selectedProdName").attr("title", selectedProdNames.join("\r\n"));
    }

    var selectedTaskIds = [];
    var selectedTaskNames = [];
    var selectedProdIds = [];
    var selectedProdNames = [];

    var batchGroupTypeOld = 0;
    var prodGroupTypeOld = 0;

    $(function () {
        layui.form.on('radio(batchGroupType)', function (data) {
            if (data.value != batchGroupTypeOld) {
                if (selectedTaskIds.length != 0) {
                    Joyin.confirm("切换模式会清空原数据，请确认。", function () {
                        batchGroupTypeOld = data.value;
                        selectedTaskIds = [];
                        selectedTaskNames = [];
                        setTaskToPage();
                    }, function () {
                        $("input[name='batchGroupType'][value='" + batchGroupTypeOld + "']").next().trigger("click");
                    })
                } else {
                    batchGroupTypeOld = data.value;
                }
            }
        });
        layui.form.on('radio(prodGroupType)', function (data) {
            if (data.value != prodGroupTypeOld) {
                if (selectedProdIds.length != 0) {
                    Joyin.confirm("切换模式会清空原数据，请确认。", function () {
                        prodGroupTypeOld = data.value;
                        selectedProdIds = [];
                        selectedProdNames = [];
                        setProdToPage();
                    }, function () {
                        $("input[name='prodGroupType'][value='" + prodGroupTypeOld + "']").next().trigger("click");
                    })
                } else {
                    prodGroupTypeOld = data.value;
                }
            }
        });

        $("body").on("click", "#ValBatchStart001_btn_batchGroupSelect", function () {
            var batchGroupType = $("input[name='batchGroupType']:checked").val();
            var title = "";
            if (batchGroupType === '0') {
                title = "组";
            }
            var params = {batchGroupType: batchGroupType};
            window['batch'] = {selectedTaskIds: selectedTaskIds, selectedTaskNames: selectedTaskNames};
            var dialogIndex = Joyin.dialog("选择任务" + title, 'openBatchGroupSelect', '99%', '99%',
                params,
                function (selectedDatas) {
                    if (selectedDatas) {
                    	selectedDatas = JSON.parse(selectedDatas);
                        selectedTaskIds = selectedDatas.selectedTaskIds;
                        selectedTaskNames = selectedDatas.selectedTaskNames;
                        setTaskToPage();
                    }
                });
        })

        $("body").on("click", "#ValBatchStart001_btn_prodGroupSelect", function () {
            var prodGroupType = $("input[name='prodGroupType']:checked").val();
            var title = "";
            if (prodGroupType === '0') {
                title = "组";
            }
            window['prod'] = {selectedProdIds: selectedProdIds, selectedProdNames: selectedProdNames};
            var params = {prodGroupType: prodGroupType};
            Joyin.dialog("选择产品" + title, 'openProdGroupSelect', '99%', '99%',
                params,
                function (selectedDatas) {
                    if (selectedDatas) {
                    	selectedDatas = JSON.parse(selectedDatas);
                        selectedProdIds = selectedDatas.selectedProdIds;
                        selectedProdNames = selectedDatas.selectedProdNames;
                        setProdToPage();
                    }
                });
        })

        $("body").on("click", "#SysBatchStart001_btn_save", function () {
        	$('#SysBatchStart001_btn_save').prop('disabled', true);
            var startDate = $('#executeDate').startDate();
            var endDate = $('#executeDate').endDate();
            console.log(startDate);
            if(startDate==''||startDate==null||typeof(startDate) == undefined
                   ||endDate==''||endDate==null||typeof(endDate) == undefined   
            ){
                Joyin.error('日期不能为空!');
                $('#SysBatchStart001_btn_save').prop('disabled', false);
                return; 
            }
            if (selectedTaskIds && selectedTaskIds.length == 0) {
                Joyin.error('请选择一条任务。');
                $('#SysBatchStart001_btn_save').prop('disabled', false);
                return;
            }
            if (selectedProdIds && selectedProdIds.length == 0) {
                $.ajax({
                    url : "checkExecute",
                    type : "POST",
                    data : {booksetId:$.trim(selectedProdIds)},
                    success: function(msg){
                        if(msg>0){
                            layer.alert('选择的账套存在正在进行登帐，请稍后再试!!');
                            $('#SysBatchStart001_btn_save').prop('disabled', false);
                            return;
                        }else{
                            Joyin.warn('产品组未选，表示选择所有产品。', function () {
                                executeBatch(selectedTaskIds.join(","), null, startDate, endDate);
                            });
                        }
                        $('#SysBatchStart001_btn_save').prop('disabled', false);
                    },
                    failure : function() {
                    }
                });

            } else {
                $.ajax({
                    url : "checkExecute",
                    type : "POST",
                    data : {booksetId:$.trim(selectedProdIds)},
                    success: function(msg){
                        if(msg>0){
                            layer.alert('选择的账套存在正在进行登帐，请稍后再试!!');
                            $('#SysBatchStart001_btn_save').prop('disabled', false);
                            return;
                        }else{
                            executeBatch(selectedTaskIds.join(","),
                                selectedProdIds.join(","),
                                startDate,
                                endDate);
                        }
                        $('#SysBatchStart001_btn_save').prop('disabled', false);
                    },
                    failure : function() {
                    }
                });
            }
        });
        
        $('#ValBatchStart001_reset').click(function() {
        	location.reload();
        });
    })
}());