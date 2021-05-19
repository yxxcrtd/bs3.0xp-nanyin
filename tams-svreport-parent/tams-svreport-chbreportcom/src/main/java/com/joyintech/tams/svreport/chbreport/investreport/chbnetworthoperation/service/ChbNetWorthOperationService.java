package com.joyintech.tams.svreport.chbreport.investreport.chbnetworthoperation.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.interf.common.util.FileUtil;
import com.joyintech.tams.report.interf.common.util.TisUtil;
import com.joyintech.tams.svreport.chbreport.investreport.chbnetworthoperation.dao.ChbNetWorthOperationMapper;
import com.joyintech.tams.svreport.chbreport.investreport.chbnetworthoperation.dto.ChbNetWorthInfoDto;
import com.joyintech.tams.svreport.chbreport.investreport.chbnetworthoperation.dto.ChbNetWorthInfoExportDto;
import com.joyintech.tams.svreport.chbreport.investreport.utils.ChbInvestReportUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;
/**
 * @ClassName ChbNetWorthOperationService
 * @Description: 净值披露报告service
 * @Author wangyong
 * @Date 2021-05-09
 * @Version V1.0
 **/
@Service
public class ChbNetWorthOperationService extends CommonExportService<ChbNetWorthInfoDto>  {
    @Autowired
    private ChbNetWorthOperationMapper chbNetWorthOperationMapper;
    /**
     * 创建产品净值披露报告
     * @param map
     * @param reportName
     * @return
     * @throws Exception
     */
    public File createExcel(Map<String, Object> map, String reportName) {
        List<ChbNetWorthInfoExportDto> listFileDto = new ArrayList<ChbNetWorthInfoExportDto>();
        listFileDto.add(this.createData(map));
        File file = new File(listFileDto.get(0).getFilePath(),listFileDto.get(0).getFileName());
        return file;
    }

    /**
     * 导出公告
     * @param map
     * @param reportName
     * @return
     */
    public ResponseEntity<byte[]> exportExcel(Map<String, Object> map, String reportName) {
        List<ChbNetWorthInfoExportDto> listFileDto = new ArrayList<ChbNetWorthInfoExportDto>();
        listFileDto.add(this.createData(map));
        File file = new File(listFileDto.get(0).getFilePath(),listFileDto.get(0).getFileName());
        return ExportUtil.export(listFileDto.get(0).getFileName(), file, true);
    }

    /**
     * 组装数据
     * @param param
     */
    public ChbNetWorthInfoExportDto createData( Map<String, Object> param) {
        ChbNetWorthInfoExportDto exportFileDto = new ChbNetWorthInfoExportDto();
        if(StringUtil.isNotEmpty(param.get("endDate")) && StringUtil.isNotEmpty(param.get("prodId"))) {
            ChbNetWorthInfoDto chbNetWorthInfoDto = this.qryChbInvestPrdInfo(param);
            List<ChbNetWorthInfoDto> dtoList = chbNetWorthOperationMapper.getNetWorthData(param);
            // 设置文件信息
            String path = FileNameUtil.createUploadFilePath("");//创建路径
            File filepath = new File(path);
            if(!filepath.exists()){
                filepath.mkdirs();
            }
            // 存放地址
            String depositfilePath = SvrDataUtil.getSysParam(GlobalConstant.COM_PROP_ACCESS_EXPORTPATH);
            String fileName = ChbInvestReportUtil.generateFileNameForReport(DictCodeListExt3.中债披露报告_产品净值披露.getValue(),
                    chbNetWorthInfoDto.getProdName(), chbNetWorthInfoDto.getProdId(), chbNetWorthInfoDto.getCustomerType(),
                    param.get("endDate").toString());
            String exceltemplate = "chbreport/chbnetworthoperation/ChbNetWorthOperationMapper";

            //设置文件名称和路径
            exportFileDto.setFileName(fileName);
            exportFileDto.setFilePath(path);
            //赋值
            Map<String, Object> mapinfo = new HashMap<>();
            mapinfo.put("title", chbNetWorthInfoDto.getProdName());
            mapinfo.put("dtoList", dtoList);
            TisUtil.createFile(mapinfo, exceltemplate, fileName);
            FileUtil.removeFileToDirectory(depositfilePath+"/"+fileName,path);
        }
        return exportFileDto;
    }

    /**
     * 查询投资运行报告中产品相关信息
     * @param params
     * @return
     * @throws Exception
     */
    public ChbNetWorthInfoDto qryChbInvestPrdInfo(Map<String, Object> params){
        List<ChbNetWorthInfoDto> list = chbNetWorthOperationMapper.getNetWorthData(params);
        ChbNetWorthInfoDto chbNetWorthInfoDto = new ChbNetWorthInfoDto();
        if(!StringUtil.isEmptyList(list)) {
            chbNetWorthInfoDto =  list.get(0);
        }
        return chbNetWorthInfoDto;
    }
}
