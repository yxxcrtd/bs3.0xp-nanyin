package com.joyintech.tams.report.errormessage.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.TreeBidiMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.errormessage.dao.ErrorMessage001Mapper;
import com.joyintech.tams.report.errormessage.dto.ErrorMessage001;
import com.joyintech.tams.constant.DictCode;

/**
* 错误表信息查询.
* @ClassName: ErrorMessage001Service 
* @author liuyu
* @date 2019年5月13日 上午10:47:24 
*
 */
@Service
@Transactional
public class ErrorMessage001Service extends CommonExportService<ErrorMessage001> {
  /**
   * 日志.
   */
  private Logger logger = LoggerFactory.getLogger(this.getClass());
  
  /**
   * mapper.
   */
  @Autowired
  private ErrorMessage001Mapper errorMessage001Mapper;
  
  private static BidiMap<String,String> interfTypeMap;
  
  List<SelectOptionDto> options;

  /**
  * 一句话描述功能.
  * @author liuyu
  * @time 2019年5月13日 上午11:00:54
  * @param selectParam 查询参数
  * @return infoList
   */
  public Page<ErrorMessage001> queryPage(Map<String,Object> selectParam) { 
    dealParam(selectParam);
    int pagenum = Integer.parseInt(selectParam.get(GlobalConstant.PARAM_PAGE).toString());
    int legth = Integer.parseInt(selectParam.get(GlobalConstant.PARAM_LENGTH).toString());
    Page<ErrorMessage001> page =
            super.queryForPage(
            		pagenum,legth,()->errorMessage001Mapper.queryPage(selectParam));
    dealResult(page);
    return page;
  }
  
  /**
  * 结果处理.
  * @author liuyu
  * @time 2019年5月13日 下午5:22:57
  * @param page 页面
   */
  private void dealResult(Page<ErrorMessage001> page) {
    List<ErrorMessage001> infoList = page.getRoot();
    for (ErrorMessage001 info : infoList) {
      info.setStatus(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, info.getStatus()));
      // modify by dubaojun 2020-11-03 错误数据页面接口类型取值修改为数据字典 start
//    info.setInterfType(getInteRemark(info.getInterfType()));
    info.setInterfType(SysDataUtil.getNameFromDataDict(DictCode.校验接口类型, info.getInterfType()));
    // modify by dubaojun 2020-11-03 错误数据页面接口类型取值修改为数据字典 end
      info.setCheckResult(SysDataUtil.getNameFromDataDict(DictCode.检查结果, "02"));
    }
    
  }
  /**
   * 结果处理.
   * @author liuyu
   * @time 2019年5月13日 下午5:22:57
   * @param page 页面
    */
   private void dealResult(List<ErrorMessage001> infoList) {
     for (ErrorMessage001 info : infoList) {
       info.setStatus(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, info.getStatus()));
       // modify by dubaojun 2020-11-03 错误数据页面接口类型取值修改为数据字典 start
//       info.setInterfType(getInteRemark(info.getInterfType()));
       info.setInterfType(SysDataUtil.getNameFromDataDict(DictCode.校验接口类型, info.getInterfType()));
       // modify by dubaojun 2020-11-03 错误数据页面接口类型取值修改为数据字典 end
       info.setCheckResult(SysDataUtil.getNameFromDataDict(DictCode.检查结果, "02"));
     }
     
   }

  /**
  * 获取接口代码汉字名称.
  * @author liuyu
  * @time 2019年5月14日 上午9:15:35
  * @param interfType 接口代码
  * @return 接口名称
   */
  private String getInteRemark(String interfType) {
    List<SelectOptionDto> selects = errorMessage001Mapper.selectInterfTypeSelects(null);;
    for (SelectOptionDto dto:selects) {
      if (interfType.equals(dto.getId()) || interfType.equals(dto.getCol())) {
        return dto.getText();
      }
    }
    return null;
  }

  /**
  * 处理参数.
  * @author liuyu
  * @time 2019年5月13日 下午4:00:39
  * @param selectParam 查询参数
  */
  private void dealParam(Map<String, Object> selectParam) {
    selectParam.put("statusList",
            StringUtil.splitToList(",",(String) selectParam.get("status")));
    selectParam.put("orgCodeList", 
            StringUtil.splitToList(",",(String) selectParam.get("orgCode")));
    selectParam.put("interfTypeList", 
            StringUtil.splitToList(",",(String)selectParam.get("interfType")));
    
    
  }

  /**
  * 获取接口下拉列表.
  * @author liuyu
  * @time 2019年5月14日 上午11:26:48
  * @return options 选择集合
   */
  public List<SelectOptionDto> getInterTypeSelects(String param) {
//    if (options != null) {
//      return options;
//    }    
    options = errorMessage001Mapper.selectInterfTypeSelects(param);
    return options;
  }
  /**
	 * 导出数据来源
	 * 
	 * @param map
	 *            参数
	 * @return 结果
	 * @see
	 * @since 1.0
	 */
	public List<ErrorMessage001> setDataList(Map<String, Object> map) {
		List<ErrorMessage001> resultList = new ArrayList<>();
		String keyList = (String) map.get("keyList");
		if (StringUtil.isNotEmpty(keyList)) {
			try {
				resultList = JsonUtils.json2List(keyList, ErrorMessage001.class);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
				return null;
			}
		} else {
			dealParam(map);
			resultList = errorMessage001Mapper.queryPage(map);
			this.dealResult(resultList);
		}
		return resultList;
	}
}
