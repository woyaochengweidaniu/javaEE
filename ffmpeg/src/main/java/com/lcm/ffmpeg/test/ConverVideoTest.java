package com.lcm.ffmpeg.test;

import com.lcm.ffmpeg.utils.ConverVideoUtils;

public class ConverVideoTest {
    public void run() {
        try {
            // 转换并截图
            String filePath = "C:\\temp\\0001.哔哩哔哩-颈椎操[流畅版].flv";
            ConverVideoUtils cv = new ConverVideoUtils(filePath);
            //想要转换的格式
            String targetExtension = ".mp4";
            boolean isDelSourseFile = false;
            boolean beginConver = cv.beginConver(targetExtension,isDelSourseFile);
            System.out.println(beginConver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String args[]) {
        ConverVideoTest c = new ConverVideoTest();
        c.run();
    }


}
