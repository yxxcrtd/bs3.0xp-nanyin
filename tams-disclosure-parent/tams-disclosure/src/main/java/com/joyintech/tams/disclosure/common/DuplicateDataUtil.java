package com.joyintech.tams.disclosure.common;

import com.joyintech.tams.systemtools.dto.ExcelValidDto;
import org.apache.commons.lang3.StringUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author wubingbing
 * @date 2022-05-22 19:55
 */
public class DuplicateDataUtil {
    public static int countDuplicatedData(List<Map<String,String>> list,String... duplicateKey){
        if(list==null || list.size()<1){
            return 0;
        }
        Set<String> set = new HashSet<>();

        if(duplicateKey!=null && duplicateKey.length>1){
            list.forEach(e->{
                StringBuilder sb = new StringBuilder();
                Stream.of(duplicateKey).forEach(ele->{
                    sb.append(e.get(ele));
                });
                set.add(sb.toString());
            });
            return list.size()-set.size();
        }else{
            //duplicateKey 为空则查询所有列
            list.forEach(e->{
                StringBuilder sb = new StringBuilder();
                e.keySet().forEach(ele->{
                    sb.append(e.get(ele));
                });
                set.add(sb.toString());
            });
            return list.size()-set.size();
        }
    }
    public static void checkData(List<ExcelValidDto> excelValidDtoList,List<Map<String,String>> list,String... duplicateKey){
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                Map var1 = list.get(i);
                Map var2  =list.get(j);
                boolean flag = true;
                for (String s : duplicateKey) {
                    if(!var1.get(s).equals(var2.get(s))){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    var1.put("ERRMSG","重复数据");
                    ExcelValidDto excelValidDto = new ExcelValidDto("0未导入",String.valueOf(i + 2),"表中数据重复");
                    excelValidDtoList.add(excelValidDto);
                    break;
                }
            }
        }

    }

    public static boolean checkFileName(String fileName,List<String> list){
        if(StringUtils.isEmpty(fileName)){
            return false;
        }else if(list == null || list.size() <1){
            return false;
        }
        boolean flag = false;
        for (String var1 : list) {
            if(fileName.indexOf(var1)>=0){
                flag = true;
                break;
            }
        }
        return flag;
    }
}
