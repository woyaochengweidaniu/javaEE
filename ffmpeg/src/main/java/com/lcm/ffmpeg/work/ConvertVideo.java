package com.lcm.ffmpeg.work;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ConvertVideo {

    private final static String PATH = "C:\\temp\\11.mp4";

    public static void main(String[] args) {
        if (!checkfile(PATH)) {
            System.out.println(PATH + " is not file");
            return;
        }
        if (process()) {
            System.out.println("ok");
        }
    }

    private static boolean process() {
        int type = checkContentType();
        boolean status = false;
        if (type == 0) {
            System.out.println("直接将文件转为avi文件");
            status = processFLV(PATH);// 直接将文件转为flv文件
        } else if (type == 1) {
            String avifilepath = processAVI(type);
            if (avifilepath == null){
                return false;// avi文件没有得到
            }
            //status = processFLV(avifilepath);// 将avi转为flv
        }
        return status;
    }

    private static int checkContentType() {
        String type = PATH.substring(PATH.lastIndexOf(".") + 1, PATH.length())
                .toLowerCase();
        // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
        if (type.equals("avi")) {
            return 0;
        } else if (type.equals("mpg")) {
            return 0;
        } else if (type.equals("wmv")) {
            return 0;
        } else if (type.equals("3gp")) {
            return 0;
        } else if (type.equals("mov")) {
            return 0;
        } else if (type.equals("mp4")) {
            return 1;
        } else if (type.equals("asf")) {
            return 0;
        } else if (type.equals("asx")) {
            return 0;
        } else if (type.equals("flv")) {
            return 0;
        }
        // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等),
        // 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
        else if (type.equals("wmv9")) {
            return 1;
        } else if (type.equals("rm")) {
            return 1;
        } else if (type.equals("rmvb")) {
            return 1;
        }
        return 1;
    }

    private static boolean checkfile(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            return false;
        }
        return true;
    }

    // 对ffmpeg无法解析的文件格式(wmv9，rm，rmvb等), 可以先用别的工具（mencoder）转换为avi(ffmpeg能解析的)格式.
    private static String processAVI(int type) {
        List<String> commend = new ArrayList<String>();
        commend.add("C:\\environment\\mencoder\\codes\\mencoder.exe");
        commend.add(PATH);
        commend.add("-oac");
        commend.add("lavc");
        commend.add("-lavcopts");
        commend.add("acodec=mp3:abitrate=64");
        commend.add("-ovc");
        commend.add("xvid");
        commend.add("-xvidencopts");
        commend.add("bitrate=600");
        commend.add("-of");
        commend.add("avi");
        commend.add("-o");
        commend.add("C:\\temp\\bbb.avi");
        try {
            ProcessBuilder builder = new ProcessBuilder();
            builder.command(commend);
            builder.start();
            return "C:\\temp\\bbb.avi";
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
    private static boolean processFLV(String oldfilepath) {

        if (!checkfile(PATH)) {
            System.out.println(oldfilepath + " is not file");
            return false;
        }

        // 文件命名
        Calendar c = Calendar.getInstance();
        String savename = String.valueOf(c.getTimeInMillis())+ Math.round(Math.random() * 100000);
        List<String> commend = new ArrayList<String>();
        commend.add("C:\\environment\\ffmpeg\\bin\\ffmpeg.exe");
        commend.add("-i");
        commend.add(oldfilepath);
        commend.add("-ab");
        commend.add("56");
        commend.add("-ar");
        commend.add("22050");
        commend.add("-qscale");
        commend.add("8");
        commend.add("-r");
        commend.add("15");
        commend.add("-s");
        commend.add("600x500");
        commend.add("C:\\temp\\out\\bb.mp4");

        try {
            Runtime runtime = Runtime.getRuntime();
            Process proce = null;
            String cmd = "";
            String cut = "     C:\\environment\\ffmpeg\\bin\\ffmpeg.exe   -i   "
                    + oldfilepath
                    + "   -y   -f   image2   -ss   8   -t   0.001   -s   600x500   C:\\temp\\截图\\"
                    + Math.random() +".jpg";
            String cutCmd = cmd + cut;
            proce = runtime.exec(cutCmd);
            ProcessBuilder builder = new ProcessBuilder(commend);
            builder.command(commend);
            builder.start();

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}