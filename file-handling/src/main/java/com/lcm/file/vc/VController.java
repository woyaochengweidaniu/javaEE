package com.lcm.file.vc;

import cn.hutool.core.util.URLUtil;
import com.lcm.file.util.ZXingCode;
import org.apache.poi.util.StringUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 二维码下载
 */
@RestController
public class VController {


    @GetMapping("downloadVC")
    public void downloadVC(HttpServletResponse response) throws IOException {
        //在线图片地址
        String str= "https://benyuan-fangao.oss-cn-hangzhou.aliyuncs.com/BENYUAN/ADMINIMAGE/20190522/a3a4c769203c488489d2b4d99806a273.jpg";
        //将图片和文字进行合成，然后输出
        byte[]  code = ZXingCode.getLogoQRCode("刘超民", "辣条", str);
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+ URLUtil.encode("test", StringUtil.UTF8) +".jpg");
        outputStream.write(code,0,code.length);

    }
}
