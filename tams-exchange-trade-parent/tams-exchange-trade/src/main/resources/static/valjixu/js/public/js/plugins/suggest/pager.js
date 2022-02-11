/**
 *
 * Pager, paginacao client-side com javascript
 * @author Edy Segura, edy@segura.pro.br
 * 
 */

function Pager(tableName, itemsPerPage) {
	this.tableName = tableName;
	this.itemsPerPage = itemsPerPage;
	this.currentPage = 1;
	this.pages = 0;
	this.inited = false;
	
	
	this.init = function() {
		var table = document.getElementById(tableName);
		
		if(table) {
			var rows = table.tBodies[0].rows;
			var records = rows.length;
			this.pages = Math.ceil(records / itemsPerPage);
			this.inited = true;
		}
	}
	
	
	this.showRecords = function(from, to) {    
		var table = document.getElementById(tableName);
		var rows  = table.tBodies[0].rows;
		
		for (var i=0; i<rows.length; i++) {
			var row = rows[i];
			
			if (i < from || i > to) {
				row.style.display = 'none';
			}
			else {
				row.style.display = '';
			}
		}
	}
	
	
	this.showPage = function(pageNumber) {
		if (!this.inited) {
			alert("Não inicializado!");
			return;
		}
		
		this.currentPage = pageNumber;
		
		var inputPager = document.getElementById('input-' + tableName);
		if (inputPager) {
			inputPager.value = this.currentPage;
		}
		
		var from = (pageNumber - 1) * itemsPerPage;
		var to   = from + itemsPerPage - 1;
		this.showRecords(from, to);
	}
	
	
	this.prev = function() {
		if (this.currentPage > 1) {
			this.showPage(this.currentPage - 1);
		}
	}
	
	
	this.next = function() {
		if (this.currentPage < this.pages) {
			this.showPage(this.currentPage + 1);
		}
	}
	

	this.showPageNav = function(pagerName, positionId) {
		if (! this.inited) {
			alert("not inited");
			return;
		}
		var element = $('#' + positionId)
		
		var pagerHtml = '<i class="layui-icon" onclick="' + pagerName + '.showPage(1);" >&#xe65a;</i> ';
		pagerHtml += '<i class="layui-icon" onclick="' + pagerName + '.prev();" >&#xe603;</i> '
		pagerHtml += '<input class="pagedisplay" id="input-' + this.tableName + '" type="number" max="'+this.pages+'" min="1" value="1" size="1" /> / ' + this.pages + ' ';
        pagerHtml += '<i class="layui-icon" style="cursor: pointer;" onclick="' + pagerName + '.jumpToPage(\''+ pagerName+ '\',\'' + this.tableName + '\');">&#xe605;</i>';
		pagerHtml += '<i class="layui-icon" style="cursor: pointer;" onclick="'+pagerName+'.next();" >&#xe602;</i> ';
		pagerHtml += '<i class="layui-icon" style="cursor: pointer;" onclick="'+pagerName+'.showPage(' + this.pages +');" >&#xe65b;</i> ';
			
		element.html(pagerHtml);
		element.mouseenter(function() {
		    element.data('isMouseenter',1);
        }).mouseleave(function() {
            element.data('isMouseenter',0);
        });
	}
	this.jumpToPage = function(pagerName, tablename){
	    eval(pagerName+'.showPage('+$("#input-"+tablename).val()+')');
	}
}

