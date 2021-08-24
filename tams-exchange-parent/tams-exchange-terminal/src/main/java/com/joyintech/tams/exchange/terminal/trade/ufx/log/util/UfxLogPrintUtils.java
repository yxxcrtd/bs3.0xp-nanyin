package com.joyintech.tams.exchange.terminal.trade.ufx.log.util;

import com.hundsun.t2sdk.common.share.dataset.CommonDataset;
import com.hundsun.t2sdk.interfaces.share.dataset.IDataset;

/**
 * @Author: think
 * @Description:
 * @Date: Created in 17:04 2021/8/4
 */
public class UfxLogPrintUtils {

    public static String logDatasetInfo(IDataset ds) {
        int currentLineIndex = ((CommonDataset) ds).getCurrentLineIndex();
        StringBuilder sb = new StringBuilder();
        int columnCount = ds.getColumnCount();
        int j;
        for (j = 1; j <= columnCount; ++j) {
            sb.append("# " + j + "\t");
            sb.append(ds.getColumnName(j));
            sb.append("\t");
            sb.append(ds.getColumnType(j) + "\n");
        }
        ds.beforeFirst();
        while (ds.hasNext()) {
            ds.next();
            for (j = 1; j <= columnCount; ++j) {
                sb.append("|" + ds.getString(ds.getColumnName(j)) + "\t");
            }
            sb.append("\n");
        }
        if (currentLineIndex != 0 && ds.getRowCount() != 0) {
            ds.locateLine(currentLineIndex);
        } else {
            ds.beforeFirst();
        }
        return sb.toString();
    }
}
