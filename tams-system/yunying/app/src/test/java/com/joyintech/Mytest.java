package com.joyintech;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: tams-base
 * @description: test
 * @author: 殷亚
 * @create: 2021-12-31 13:16
 **/
public class Mytest {
    public static void main(String[] args) {
        List<FlightTicketInfo> infoList = new ArrayList<FlightTicketInfo>();
        List<FlightTicketInfo> infoList2= new ArrayList<FlightTicketInfo>();


        infoList.add(new FlightTicketInfo("11111", "xiaoming"));
        infoList.add(new FlightTicketInfo("11111", "xiaoming"));
        infoList.add(new FlightTicketInfo("33333", "xiaoming"));
        infoList.add(new FlightTicketInfo("44444", "xiaoming"));

        infoList2.addAll(infoList);


    }

    static class FlightTicketInfo {


        /**
         * 账套代码
         */
        private String booksetId;

        /**
         * 四级科目号
         */
        private String detailSubjectNo;

        public FlightTicketInfo(String booksetId, String detailSubjectNo) {
            this.booksetId = booksetId;
            this.detailSubjectNo = detailSubjectNo;
        }

        /**
         * 取得 账套代码
         * @return 账套代码
         */
        public String getBooksetId() {
            return booksetId;
        }

        /**
         * 设置 账套代码
         * @param booksetId 账套代码
         */
        public void setBooksetId(String booksetId) {
            this.booksetId = booksetId == null ? null : booksetId.trim();
        }

        /**
         * 取得 四级科目号
         * @return 四级科目号
         */
        public String getDetailSubjectNo() {
            return detailSubjectNo;
        }

        /**
         * 设置 四级科目号
         * @param detailSubjectNo 四级科目号
         */
        public void setDetailSubjectNo(String detailSubjectNo) {
            this.detailSubjectNo = detailSubjectNo == null ? null : detailSubjectNo.trim();
        }



    }

}
