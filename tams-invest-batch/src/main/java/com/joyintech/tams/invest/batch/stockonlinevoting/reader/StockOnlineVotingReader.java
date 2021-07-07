package com.joyintech.tams.invest.batch.stockonlinevoting.reader;

import com.joyintech.batch.batchbase.baseclass.BaseReader;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.invest.batch.common.util.BatchConstant;
import com.joyintech.tams.invest.batch.stockonlinevoting.dto.StockOnlineVotingDto;
import com.joyintech.tams.invest.common.constant.BussinessConstant;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 股票网络投票基础数据导入 Reader
 */
@Service
@Scope("prototype")
public class StockOnlineVotingReader extends BaseReader {
    @Override
    public List createList() {
        List<StockOnlineVotingDto> list = new ArrayList<>();
        StockOnlineVotingDto dto = new StockOnlineVotingDto();
        //FTP方式
        String ftpType = CacheUtil.getSysParam(BussinessConstant.SYS_PARA_FTP_TYPE);
        //文件路径
        String filePath = CacheUtil.getSysParam(BussinessConstant.STOCK_ONLINE_VOTING_FILE_PATH)
                + "/" + DateUtil.format((Date) this.getParams().get(BatchConstant.PLATE_DATE),DateUtil.DEFAULT_OTHER_FORMAT);
        //ftp url
        String host = CacheUtil.getSysParam(BussinessConstant.STOCK_ONLINE_VOTING_FTP_IP);
        // PORT
        String port = CacheUtil.getSysParam(BussinessConstant.STOCK_ONLINE_VOTING_FTP_PROT);
        // ftp连接用户名
        String username = CacheUtil.getSysParam(BussinessConstant.STOCK_ONLINE_VOTING_FTP_USENAME);
        // ftp密码
        String password = CacheUtil.getSysParam(BussinessConstant.STOCK_ONLINE_VOTING_FTP_PASSWORD);
        //临时文件存放目录
        String localPath = CacheUtil.getSysParam(BussinessConstant.STOCK_ONLINE_VOTING_FILE_EXPORTPATH);

        dto.setFtpType(ftpType);
        dto.setFilePath(filePath);
        dto.setHost(host);
        dto.setPort(port);
        dto.setUsername(username);
        dto.setPassword(password);
        dto.setLocalPath(localPath);

        list.add(dto);
        return list;
    }
}
