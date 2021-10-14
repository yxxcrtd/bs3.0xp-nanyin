package com.joyintech.tams.business.common.service;

import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.tams.business.common.dao.CfgDataTableFlagMapper;
import com.joyintech.tams.business.common.dao.CfgDataTableMapper;
import com.joyintech.tams.business.common.dto.SetDataTabelDto;
import com.joyintech.tams.business.common.entity.CfgDataTableShow;
import com.joyintech.tams.business.common.entity.CfgDataTableShowFlag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * 数据表格设置service
 */
@Service
public class CfgDataTableService {

    /**
     * 用户自定义设置
     */
    private static final int EFFERANGE_IS = 1;

    /**
     * 默认设置
     */
    private static final int EFFERANGE_NO = 0;

    /**
     * 用户设置操作方向
     */
    private static final int SET_FLAG = 1;

    @Autowired
    private CfgDataTableMapper cfgDataTableMapper;

    @Autowired
    private CfgDataTableFlagMapper cfgDataTableFlagMapper;

    /**
     * 查询数据表格式设置
     *
     * @param cfgDataTableShow
     * @return
     */
    public List<CfgDataTableShow> getCfgDataTableList(CfgDataTableShow cfgDataTableShow) {
        return cfgDataTableMapper.getCfgDataTableList(cfgDataTableShow);
    }

    /**
     * 查询整个页面数据表格式设置
     *
     * @param pageCode
     * @return
     */
    public Map<String, List<CfgDataTableShow>> getByPageCode(String pageCode) {
    	List<CfgDataTableShow> list = cfgDataTableMapper.listByPageCode(pageCode);
    	Map<String,List<CfgDataTableShow>> ret = new HashMap<String, List<CfgDataTableShow>>();
    	for (CfgDataTableShow info : list) {
			String dtcode = info.getDtCode();
			List<CfgDataTableShow> temp = ret.get(dtcode);
			if (temp==null) {
				temp = new ArrayList<CfgDataTableShow>();
				ret.put(dtcode, temp);
			}
			temp.add(info);
		}
        return ret;
    }

    /**
     * 查询整个页面数据表格式设置
     *
     * @param pageCode
     * @return
     */
    public Map<String, List<CfgDataTableShow>> getByPageCode1(String pageCode) {
        Map<String, List<CfgDataTableShow>> map = new HashMap<>();
        //获取登录用户的ID
        String userId = SecurityUtil.getLoginUser().getUid();
        //获取页面的表格编号
        List<String> dtCodes = cfgDataTableMapper.getDtCodeByPageCode(pageCode);
        if (null != dtCodes && dtCodes.size() > 0) {
            dtCodes.stream().forEach(dtCode -> {
                //先查用户自定义设置，没有再查询默认设置
                List<CfgDataTableShow> uDataList = cfgDataTableMapper.getByPageCode1(pageCode, dtCode, EFFERANGE_IS, userId);
                if (uDataList != null && uDataList.size() < 1) {
                    List<CfgDataTableShow> dataList = cfgDataTableMapper.getByPageCode1(pageCode, dtCode, EFFERANGE_NO, null);
                    map.put(dtCode, dataList);
                } else {
                    map.put(dtCode, uDataList);
                }
            });
        }
        return map;
    }

    /**
     * 数据表格设置
     *
     * @param setDataTabelDto
     */
    @Transactional(rollbackFor = Exception.class)
    public void setDataTable(SetDataTabelDto setDataTabelDto) {
        //获取登录用户的ID
        String userId = SecurityUtil.getLoginUser().getUid();
        //先判断用户是否自定义过设置
        String pageCode = setDataTabelDto.getPageCode();
        String dtCode = setDataTabelDto.getDtCode();
        CfgDataTableShowFlag flag = cfgDataTableFlagMapper.getByPageCodeAndDtCode(pageCode, dtCode, userId);
        if (null != flag) {

        } else {
            List<CfgDataTableShow> dataList = cfgDataTableMapper.getByPageCode1(pageCode, dtCode, EFFERANGE_NO, null);
            List<CfgDataTableShow> newList = new ArrayList<>();
            if (null != dataList && dataList.size() > 0) {
                dataList.stream().forEach(data -> {
                    CfgDataTableShow show = new CfgDataTableShow();
                    BeanUtils.copyProperties(data, show);
                    show.setEffeRange(1l);
                    show.setRangeId(userId);
                    newList.add(show);
                });
            }
            setSeqNo(setDataTabelDto, newList);
            cfgDataTableMapper.insertCfgDataTableBatch(newList);
        }
    }

    /**
     * 设置字段顺序
     *
     * @param setDataTabelDto
     * @param newList
     */
    private void setSeqNo(SetDataTabelDto setDataTabelDto, List<CfgDataTableShow> newList) {
        //目标列
        AtomicReference<Long> seqNo = new AtomicReference<>(1L);
        newList.stream().forEach(dataTable -> {
            if (setDataTabelDto.getTargetCode().equals(dataTable.getColCode())) {
                seqNo.set(dataTable.getSeqNo());
            }
        });
        //原列
        newList.stream().forEach(dataTable -> {
            if (setDataTabelDto.getSrcCode().equals(dataTable.getColCode())) {
                dataTable.setSeqNo(seqNo.get());
            }
        });
        if (setDataTabelDto.getFlag().intValue() == SET_FLAG) {
            newList.stream().forEach(dataTable -> {
                if (dataTable.getSeqNo().intValue() >= seqNo.get() && !setDataTabelDto.getSrcCode().equals(dataTable.getColCode())) {
                    dataTable.setSeqNo(dataTable.getSeqNo() + 1);
                }
            });
        } else {
            newList.stream().forEach(dataTable -> {
                if (dataTable.getSeqNo().intValue() <= seqNo.get() && !setDataTabelDto.getSrcCode().equals(dataTable.getColCode())) {
                    dataTable.setSeqNo(dataTable.getSeqNo() - 1);
                }
            });
        }
    }
}
