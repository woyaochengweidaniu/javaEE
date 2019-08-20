package com.lcm.file.cvs;





import cn.hutool.core.util.URLUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lcm.file.easyexcel.service.IUserService;
import com.lcm.file.util.CollectionUtil;
import com.lcm.file.util.CsvUtils;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 *
 * 项目源码 ：   https://github.com/uniVocity/univocity-parsers
 *
 * maven地址：
 *          <dependency>
 *             <groupId>com.univocity</groupId>
 *             <artifactId>univocity-parsers</artifactId>
 *             <version>2.8.2</version>
 *         </dependency>
 *
 * @author lcm
 */
@RestController
public class CvsController {

    @Autowired
    private IUserService userService;

    /**
     * 导出CVS
     * @param response
     * @throws IOException
     */
    @GetMapping("exportCSV")
    public void exportCVS(HttpServletResponse response) throws IOException {
        ServletOutputStream csvResult = response.getOutputStream();
        response.setContentType("multipart/form-data");
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment;filename="+ URLUtil.encode("test", StringUtil.UTF8) +".csv");
        String[] head = new String[]{"用户姓名","年龄","性别","地址","手机号","业余爱好","出生日期","创建时间"};
        List<Map<String, Object>> list = userService.listMaps(new QueryWrapper<>());
        List<Object[]> objects = CollectionUtil.collectToArray(list);
        CsvUtils.simpleExport(true,"\n",head,objects,"test",csvResult);
    }

    /**
     * 导入CVS
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("importCSV")
    public Object uploadCSV(MultipartFile file) throws IOException {
        //##CODE_START

        CsvParserSettings settings = new CsvParserSettings();
        //the file used in the example uses '\n' as the line separator sequence.
        //the line separator sequence is defined here to ensure systems such as MacOS and Windows
        //are able to process this file correctly (MacOS uses '\r'; and Windows uses '\r\n').
        settings.getFormat().setLineSeparator("\n");
        // creates a CSV parser  如果CSV经过Excel打开后文本格式发生改变，导入进来的是乱码
        CsvParser parser = new CsvParser(settings);

        // parses all rows in one go.
        List<String[]> allRows = parser.parseAll(file.getInputStream(),10000);

        for (String[] strings:allRows) {
            System.out.println(Arrays.toString(strings));
        }
        return "success";


    }





}
