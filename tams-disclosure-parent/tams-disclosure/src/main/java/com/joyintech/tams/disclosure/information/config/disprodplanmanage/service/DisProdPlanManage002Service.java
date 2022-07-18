/*
 * 文件名：DisProdPlanManage002Service.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V2.0
 * 修改人：ruanhongming
 * 修改时间：2018年5月3日
 * 修改内容：新建
 * 系统名称：估值核算系统V2.0
 */

package com.joyintech.tams.disclosure.information.config.disprodplanmanage.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.joyintech.framework.file.SafelyFile;
import com.joyintech.tams.common.base.dao.RepProdInfoMapper;
import com.joyintech.tams.common.base.entity.DisProdPlan;
import com.joyintech.tams.common.base.entity.RepProdInfo;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dao.DisProdPlanManage002Mapper;
import com.joyintech.tams.disclosure.information.config.disprodplanmanage.dto.DisProdPlanManage002Dto;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.progressbar.ProgressBarService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.ExportUtil;
import com.joyintech.framework.util.FileNameUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.RepInfo;
import com.joyintech.tams.disclosure.common.common.util.ReportGlobalConstant;
import com.joyintech.tams.disclosure.common.common.util.ServiceApplication;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 报表模板管理新建service
 *
 * @author longhao
 * @version 2.0 2018年5月3日
 * @see DisProdPlanManage002Service
 * @since 2.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DisProdPlanManage002Service extends CommonExportService<DisProdPlan> {


    @Autowired
    private DisProdPlanManage002Mapper disProdPlanManage002Mapper;

    @Autowired
    private ProgressBarService progressBarService;


    @Autowired
    private RepProdInfoMapper repProdInfoMapper;
    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(DisProdPlanManage002Service.class);

    /**
     * 获取数据源
     *
     * @return
     */
    public String getDataSource() {
//			return DisProdPlanManage002Mapper.getDataSource();
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage002/getDataSource", null,
                String.class);


    }


    public RepProdInfo getPrdInfo(String prodId) {
        RepProdInfo prodInfo = repProdInfoMapper.selectByPrimaryKey2(prodId);
        return prodInfo;
    }

    /**
     * 获取生成方式
     *
     * @return
     */
    public String getCreateWay() {
//		return DisProdPlanManage002Mapper.getCreateWay();
        return restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage002/getCreateWay", null,
                String.class);


    }

    public String getReportWarehouse() {

        return disProdPlanManage002Mapper.getReportWarehouse();
    }

    /**
     * 查询模板参数
     *
     * @param
     * @return
     */
    public List<DisProdPlanManage002Dto> findParamPage(String repId) {


        return restTemplate.getForList(ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage002/findParamPage?repId=" + repId,
                DisProdPlanManage002Dto.class);
//		List<String> paramList= DisProdPlanManage002Mapper.findParamPage(repId);
//		List<DisProdPlanManage002Dto> list=new ArrayList<>();
//			for(String param:paramList){
//				if(StringUtil.isNotEmpty(param)){
//					//去掉参数字符串的{}
//					String params=param.substring(1, param.length()-1);
//					//去掉参数字符串的''
//					String params2=params.replace("'", "");
//					//分割参数字符串
//					String[] params3=params2.split(",");
//					int i=1;
//					for(String para:params3){
//						DisProdPlanManage002Dto dto=new DisProdPlanManage002Dto();
//						//设置参数编号
//						dto.setParamNum(i++);
//						//设置参数名称
//						dto.setParamName(para.split(":")[0]);
//						switch (dto.getParamName()){
//							case "portnum":dto.setName("产品名称");break;
//							case "startDate":dto.setName("开始日期");break;
//							case "endDate":dto.setName("结束日期");break;
//							default: break;
//						}
//						//设置参数类型
//						dto.setParamType(para.split(":")[1]);
//						list.add(dto);
//					}
//				}
//				
//				
//				
//			
//		}
//	
//		return list;
    }

    /**
     * 保存check
     *
     * @param dto
     * @return
     */
    public AjaxResult check(DisProdPlanManage002Dto dto) {
        Set<String> message = new HashSet<String>();
        if (StringUtils.isNotBlank(dto.getRaiseType())
                && StringUtils.equals(dto.getRaiseType(), DictCodeListExt3.新人行募集方式_私募.getValue())) {
            message.add("请填写产品冷静期");
        }
        if (StringUtils.isNotBlank(dto.getIsPeriod())
                && StringUtils.isNotBlank(dto.getIssuingWay())
                && StringUtils.equals(dto.getIsPeriod(), "01")
                && StringUtils.equals(dto.getIssuingWay(), "01")) {
            message.add("自动赎回日");
            message.add("客户实际持有天数");
        }

        AjaxResult result = new AjaxResult();
        if (CollectionUtils.isNotEmpty(message)) {
            List<ValidMessage> errorList = new ArrayList<>();
            message.forEach(action -> {
                errorList.add(new ValidMessage(action));
            });
            result.setErrorList(errorList);
        }
        return result;
    }

    //初始化编辑页面数据
    public DisProdPlanManage002Dto getDisProdPlanManage002Dto(String code) {
//		DisProdPlanManage002Dto dto = DisProdPlanManage002Mapper.getDisProdPlanManage002Dto(code);

        DisProdPlanManage002Dto dto = restTemplate.getForObject(ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage002/getDisProdPlanManage002Dto?code=" + code,
                DisProdPlanManage002Dto.class);


        return dto;
    }

    /**
     * 移除进度条
     *
     * @param request
     * @return
     */
    public void removePercent(HttpServletRequest request) {
        progressBarService.removePercent("DisProdPlanManage002", ProgressBarService.FILE_UPLOAD_ID);
    }

    /**
     * 读取文件
     *
     * @param request
     * @return
     */
    public void readFile(HttpServletRequest request) {
        progressBarService.updatePercent("DisProdPlanManage002", ProgressBarService.FILE_UPLOAD_ID, 1D, false);
    }

    /**
     * 查询是否存在相同模板
     *
     * @param dto
     * @return
     */
    public int selectRepIdAndUri(DisProdPlanManage002Dto dto) {
//		int i=DisProdPlanManage002Mapper.selectRepIdAndUri(dto);

        int i = restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage002/selectRepIdAndUri",
                dto, Integer.class);


        return i;
    }

    /**
     * 插入数据
     *
     * @param dto
     */
    public void insert(DisProdPlanManage002Dto dto) {
//		dto.setKeyId(DisProdPlanManage002Mapper.setId());
//		DisProdPlanManage002Mapper.insert(dto);

        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage002/insert",
                dto, Integer.class);

    }

    /**
     * 更新数据
     *
     * @param dto
     * @return
     */
    public void update(DisProdPlanManage002Dto dto) {

//		DisProdPlanManage002Mapper.update(dto);

        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage002/update",
                dto, Integer.class);
    }

    /**
     * 删除
     *
     * @param param
     */
    public void delete(Map<String, Object> param) {
//		DisProdPlanManage002Mapper.deleteModle(param);

        restTemplate.postForObject(
                ServiceApplication.VALUATION_SERVICE_NAME + "/DisProdPlanManage001/delete",
                param, Integer.class);

    }


    /**
     * 下载报表模板zip文件
     *
     * @param filenpath
     * @return
     * @throws IOException
     */
    public ResponseEntity<byte[]> downLoadReportInfo(String filenpath) throws IOException {
        String uploadFilePath = CacheUtil.getSysParam(ReportGlobalConstant.TEMPLATE_FILE_PATH);
        File filepath = new SafelyFile(uploadFilePath + filenpath);///NB/GLJY
        //检查文件夹是否存在
        String zipFileName = FileNameUtil.createFileName("zip");
        File zipFile = new SafelyFile(uploadFilePath + File.separator + zipFileName);
        ZipOutputStream zipOut = null;
        try {
            if (filepath.exists()) {
                zipOut = new ZipOutputStream(new FileOutputStream(zipFile));
                zipOut.setEncoding("gbk");
                compressFile2(zipOut, filepath, filepath.getName());
            }
        } finally {
            if (zipOut != null) {
                zipOut.close();
            }
        }

        return ExportUtil.export(zipFileName, zipFile, true);

    }


    /**
     * 压缩报表信息
     *
     * @param out        容器
     * @param sourcefile 被压缩文件
     * @param basedir    路径
     * @throws IOException
     */
    private void compressFile2(ZipOutputStream out, File sourcefile, String basedir) throws IOException {
        if (sourcefile.isDirectory()) {
            File[] files = sourcefile.listFiles();

            if (files == null || files.length == 0) {
                out.putNextEntry(new ZipEntry(basedir + File.separator));
            } else {
                for (int i = 0; i < files.length; i++) {
                    compressFile2(out, files[i], files[i].getName());
                }
            }
        } else {
            out.putNextEntry(new ZipEntry(basedir));

            FileInputStream fos = null;
            BufferedInputStream bis = null;
            try {
                fos = new FileInputStream(sourcefile);
                bis = new BufferedInputStream(fos);
                int len;
                byte[] buf = new byte[1024];
                while ((len = bis.read(buf, 0, 1024)) != -1) {
                    out.write(buf, 0, len);
                }
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            } finally {
                if (bis != null) {
                    bis.close();

                }

                if (fos != null) {
                    fos.close();

                }
            }


        }
    }
}
