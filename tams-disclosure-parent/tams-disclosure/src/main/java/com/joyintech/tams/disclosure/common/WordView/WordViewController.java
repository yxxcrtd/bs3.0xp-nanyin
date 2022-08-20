package com.joyintech.tams.disclosure.common.WordView;

import com.joyintech.framework.annotation.PageId;
import com.joyintech.framework.base.BaseController;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.listener.FileAttachmentProcess;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.disclosure.busicommon.poireport.service.PoiReportService;
import com.joyintech.tams.utils.poi.PoiUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Map;

@Controller
@PageId("WordView")
@RequestMapping("/disclosure/common/WordView")
public class WordViewController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(WordViewController.class);

    @Autowired(required = false)
    private FileAttachmentProcess fileAttachmentProcess;

    @Autowired
    private PoiReportService poiReportService;


    @Override
    public String init(Model model) {
        String fileNo = this.getParam("fileNo");
        String filePath = "";
        //通过主键获取文件路径
        if(null != fileAttachmentProcess){
            UploadFile file = fileAttachmentProcess.fetch(fileNo);
            filePath  = file.getTargetFile().getPath();
        }
        model.addAttribute("filePath",filePath);
        return super.init(model);
    }

    @RequestMapping(value="/previewReport", method = RequestMethod.POST)
    public void previewReport(HttpServletRequest request, HttpServletResponse response) {
        String imagePath = "";
        Map<String,Object> params = RequestUtil.getPageParam();
        String filePath = (String)params.get("filePath");
        ByteArrayInputStream bis = null;
        try {
            if ( filePath !=null ) {
                //将生成的文件作为输入，转换成html后，发送到response
                byte[] bytes = poiReportService.getFileByteArray(filePath);

                if(bytes != null) {
                    bis = new ByteArrayInputStream(bytes);
                    PoiUtils.docxToHtml(bis,response.getOutputStream(),imagePath);
                }
            }

        } catch (Exception e ) {
            logger.error("查询数据错误。",e);
        } finally {
            if(bis != null){
                try{
                    bis.close();
                }catch(IOException e){
                    logger.error("关闭输入字节流异常",e);
                }
            }
        }
        //return value;
    }
}
