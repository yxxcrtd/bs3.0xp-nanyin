;(function($){
	/**
	 * chosen.jquery.js 项目插件拓展
	 *
	 * 更新 下拉框通用方法
	 *
	 * @param dataResult 原数据，不传时，默认取[data-result]
	 * @param item 筛选，这里取的数据对象中的id，如：['1','2']，不传时，全部展示
	 * @param chosen 选中某一项 string
	 * @param disabled 是否禁用 boolean
	 * @param callback 回调
	 * @return $this
	 *
	 * @author 言伟
	 */
	$.fn.updateChosenArr = function updateChosenArr(obj){
		var $this = $(this),arr = obj.dataResult || JSON.parse($this.attr('data-result') || '[]'),item = obj.item;
		$this.empty();
		$this.append($('<option value>'));
		for(var i in arr){
			if (!arr.hasOwnProperty(i))continue;
			if (obj.item && obj.item.length > 0){
				if ($.inArray(arr[i].id,item) >= 0)
					$('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this);
			} else
				$('<option>').attr('value', arr[i].id).text(arr[i].text).appendTo($this)
		}
		if (obj.chosen)
			$this.val(obj.chosen);
		if (obj.disabled === true || obj.disabled === false)
			$this.attr('disabled', obj.disabled);
		$this.trigger("chosen:updated").trigger('change');
		if (obj.callback)
			obj.callback($this);
		return $this;
	}
})(jQuery)