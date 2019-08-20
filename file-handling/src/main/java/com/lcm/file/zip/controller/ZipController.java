package com.lcm.file.zip.controller;

import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ZipUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;


/**
 * @author lcm
 */
@RestController
public class ZipController {


    @PostMapping("uploadZip")
    public Object uploadZip(MultipartFile file) throws IOException {


        String path = file.getOriginalFilename();
        URL resource = ClassLoaderUtil.getContextClassLoader().getResource("");
        Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println("resourse："+resource);
        System.out.println("resource.getPath:" + resource.getPath());
        System.out.println("path:" + path);
        System.out.println(resource+path);
        System.out.println(resource.getPath() + path);
        file.transferTo(new File(resource.getPath()+path));
        File unzip = ZipUtil.unzip(resource.getPath() + path);
        File[] files = unzip.listFiles();
        for (File file1 : files) {
            System.out.println(file1.getPath());
        }

        return "上传成功";
    }

    public static void main(String[] args) {
        //对文件夹进行压缩
       /* File zip = ZipUtil.zip("C:\\test\\innerfile", Charset.defaultCharset());
        System.out.println(zip.getPath());*/

        File unzip = ZipUtil.unzip("C:\\test\\innerfile.zip");
        File[] files = unzip.listFiles();
        for (File file:files) {
            System.out.println(file.getPath());
        }
    }
}
