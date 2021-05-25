package com.joyintech;

import java.io.File;

public class Test {
    public static void main(String[] args) {
        updateFileNames("F:\\oracleTomysql\\tams-common\\");
    }

    public static void updateFileNames(String url) {
        //获取文件所在目录
        File file = new File(url);
        // 获取文件夹绝对路径
        String path = file.getAbsolutePath();
        // 判断文件目录是否存在，且是文件目录，非文件
        if (file.exists() && file.isDirectory()) {
            // 遍历文件夹下的所有文件
            File[] childFiles = file.listFiles();
            for (int i = 0; i < childFiles.length; i++) {
                File file2 = childFiles[i];
                if (file2.isFile()) {
                    //下面是对文件重命名的操作，可根据需要修改。利用String里面的方法
                    String oldName = file2.getName();
                    int index = oldName.indexOf(".");
                    String newName = oldName.substring(0, index)+"_mysql.xml";
                    file2.renameTo(new File(path + "\\" + newName));
                    //如果是文件夹，继续递归
                } else if (file2.isDirectory()) {
                    updateFileNames(file2.getAbsolutePath());
                } else {
                    System.out.println("不是一个文件！！！");
                }
            }
        }
    }
}
