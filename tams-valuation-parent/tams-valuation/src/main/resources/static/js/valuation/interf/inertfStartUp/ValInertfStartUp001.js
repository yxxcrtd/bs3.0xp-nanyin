

$(function() {
    
    var table = $("#dataTable").joyintable({
        ajax : {
            url : 'openInertfGroupSelect',
            type : 'POST',
            data : function() {
                return getSearchParams();
            }
        },
//        searchOnInit : true,
        cellMinWidth : 100,
        loadonce : true,
        cols : [  {field : 'orderno', fixed : true,title : '批次号'},
                    {field : 'beanName', title : '接口任务英文名称'}, 
                    { field : 'taskName', title : '接口任务'}]
    });
    var getSearchParams = function(){ 
        return {
        name :$('#inertfName').val(),
    }}


    /**
     * 执行
     */
    $('#ValInertfStart001_btn_save').click(function() {
        runAsData();
    });
    
    /**
     *执行
     */
        var runAsData = function() {
            if (table != null && table != '') {
                var selArr = table.getAllCheckedRows();
                var startDate = $('#executeDate').startDate();
                var endDate = $('#executeDate').endDate();
                var datas = JSON.stringify(selArr);
                var department = $('#department').val();
            }
            if (!selArr || selArr.length == 0) {
                Joyin.error(JoyinUtil.getMessage("没有选中数据"));
                return;
            }
            if ($('#executeDate').startDate() == null || $('#executeDate').startDate() == '') {
                Joyin.error(JoyinUtil.getMessage("没有选定时间"));
                return;
            }
            Joyin.ajax('runAs',{datas : datas,
                    startDate:startDate,
                    endDate:endDate,
                    department:department},
                function() {  
                    layui.layer.alert("启动成功");
            });

        };
        /**
         * 查询
         */
        $('#ValInertfStartUp001_btn_search').click(function() {
            table.load();
        });
    });