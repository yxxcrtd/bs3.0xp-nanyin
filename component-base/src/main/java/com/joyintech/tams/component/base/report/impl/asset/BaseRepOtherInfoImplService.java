package com.joyintech.tams.component.base.report.impl.asset;

import com.joyintech.tams.common.base.dao.RepAssetOtherMapper;
import com.joyintech.tams.common.base.entity.RepAssetOther;
import com.joyintech.tams.component.base.report.asset.BaseRepOtherInfoService;
import com.joyintech.tams.component.base.report.dto.BaseRepOtherDto;
import com.joyintech.tools.util.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BaseRepOtherInfoImplService implements BaseRepOtherInfoService {

    /**
     * 生成
     */
    @Autowired
    private RepAssetOtherMapper repAssetOtherMapper;

    @Override
    public boolean insert(List<RepAssetOther> list) {
        if (list != null && list.size() > 0) {
            for(RepAssetOther repAssetOther : list){
                repAssetOtherMapper.insert(repAssetOther);
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(BaseRepOtherDto dto) {
        List<String> finlist = dto.getRepFinprodIdList();
        if (finlist != null && finlist.size() > 0) {
            List<List<String>> list= ListUtils.splitList(finlist);
            for(List<String> idlist:list){
                for(String repFinprodId: idlist){
                    repAssetOtherMapper.deleteByPrimaryKey(repFinprodId);
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean update(BaseRepOtherDto dto) {
        List<RepAssetOther> list = dto.getRepAssetOtherList();
        if(list!=null && list.size()>0){
            if(this.delete(dto)){
                return this.insert(list);
            }
        }
        return false;
    }

    @Override
    public List<RepAssetOther> select(BaseRepOtherDto dto) {
        List<RepAssetOther> result = new ArrayList<RepAssetOther>();
        dto.getRepFinprodIdList().forEach(item ->{
            result.add(repAssetOtherMapper.selectByPrimaryKey(item));
        });
        return result;
    }
}
