/**
 * 开放计划共通JS
 * @auth fanshuanghu
 */
(function(){
    var table = $("#securitiesAccountInfoTable").joyintable({
        data:secDto==null?[]:secDto,
        searchOnInit:true,
        height:'225px',
        cols:[
              {field:'secAcctId', title: 'SecuritiesAccountInfo.secAcctId',sortable:false, edit:true, editOption:{type:'select',showField:'id',id:'secAcctId',formatoption:true,url: JoyinUtil.getRealPath('/component/portfolio/SecuritiesAccountInfo/getSecAcctId')}}
             ,{field:'secAcctName', title: 'SecuritiesAccountInfo.secAcctName', sortable:false}
             ,{field:'registOrgStr', title: 'SecuritiesAccountInfo.registOrg',sortable:false}
             ,{field:'secAcctSourceStr', title: 'SecuritiesAccountInfo.secAcctSource',sortable:false}
             ,{field:'recAcctId', title: 'SecuritiesAccountInfo.recAcctId',sortable:false}
             ,{field:'trdAcctId', title: 'SecuritiesAccountInfo.trdAcctId',sortable:false}
             ,{field:'acctName', title: 'SecuritiesAccountInfo.acctName',sortable:false}
             ,{field:'operation', title: 'SysCommon.operation',sortable:false, type:'action', buttons: ['delt']}
         ],
         addbtn:true,
         done:function(){
             this.bind('click', '.delt', function(data, index) {
                 var object = {selectedItem: [data], operateType:"delete"};
                 table.deleteRow(index);
             });
             $('body').on('change','#secAcctId',function(event,data){
                 
                 if(data.value[0] != ''){
                     var obj = {'secAcctId':data.value[0]};
                     Joyin.ajax(JoyinUtil.getRealPath( "/component/portfolio/SecuritiesAccountInfo/getSecAcctInfo"),obj,function(result){
//                         if(result.registOrg == '01'){
//                             result.registOrgStr = '中央国债登记结算有限责任公司';
//                         }else if(result.registOrg == '02'){
//                             result.registOrgStr = '上海清算所';
//                         }else if(result.registOrg == '03'){
//                             result.registOrgStr = '中国证券登记结算有限责任公司';
//                         }else if(result.registOrg == '09'){
//                             result.registOrgStr = '其他';
//                         }
                    	 //add by yzg 空数据renderRow不会覆盖 20200306 start 
                    	 var rowData = {};
                    	 if(result.registOrg){
                    		 rowData.registOrg = result.registOrg;
                    	 }else{
                    		 rowData.registOrg = '';
                    	 }
                    	 if(result.recAcctId){
                			 rowData.recAcctId = result.recAcctId;
                		 }else{
                			 rowData.recAcctId = '';
                		 }
                    	 if(result.secAcctName){
                    		 rowData.secAcctName = result.secAcctName;
                    	 }else{
                    		 rowData.secAcctName = '';
                    	 }
                    	 if(result.registOrgStr){
                    		 rowData.registOrgStr = result.registOrgStr;
                    	 }else{
                    		 rowData.registOrgStr = '';
                    	 }
                    	 if(result.trdAcctId){
                    		 rowData.trdAcctId = result.trdAcctId;
                    	 }else{
                    		 rowData.trdAcctId = '';
                    	 }
                    	 if(result.acctName){
                    		 rowData.acctName = result.acctName;
                    	 }else{
                    		 rowData.acctName = '';
                    	 }
                    	 if(result.secAcctSourceStr){
                    		 rowData.secAcctSourceStr = result.secAcctSourceStr;
                    	 }else{
                    		 rowData.secAcctSourceStr = '';
                    	 }
                    	 if(result.secAcctSource){
                    		 rowData.secAcctSource = result.secAcctSource;
                    	 }else{
                    		 rowData.secAcctSource = '';
                    	 }
                    	 table.renderRow(data.rowIndex,rowData);
                             //table.renderRow(data.rowIndex, {registOrg:result.registOrg,secAcctName:result.secAcctName,registOrgStr:result.registOrgStr,recAcctId:result.recAcctId,trdAcctId:result.trdAcctId,acctName:result.acctName,secAcctSourceStr:result.secAcctSourceStr,secAcctSource:result.secAcctSource});
                    	//add by yzg 空数据renderRow不会覆盖  20200306 end 
                     });
                 }else{
                     table.renderRow(data.rowIndex, {secAcctName:'',registOrg:'',recAcctId:'',trdAcctId:'',acctName:'',registOrgStr:'',secAcctSourceStr:'',secAcctSource:''});
                 }
             });
        }
    });
    // 隐藏
    var hide = function() {
        $('#SecuritiesAccountInfo_DIV').hide();
    };
    // 展示
    var show = function() {
        $('#SecuritiesAccountInfo_DIV').show();
    };

    $('#SecuritiesAccountInfo_btn_new').click(function(){
        Joyin.dialog(JoyinUtil.getLabel('MstSecuritiesAccount002.add'),basePath+'invest/basicdata/mstsecuritiesaccount/MstSecuritiesAccount002/init', '100%', '100%', {pageModel:PAGE_MODEL_NEW});
    });
    // 开放计划公开接口
    window.SecuritiesAccountInfo = {
        table:table,
        hide: hide,
        show: show
    };
}());