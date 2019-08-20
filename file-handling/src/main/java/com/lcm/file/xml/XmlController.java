package com.lcm.file.xml;




import com.lcm.file.easyexcel.service.IUserService;
import com.lcm.file.util.XmlUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * dom4j   导入导出  格式xml
 *
 * 参考文档1： https://www.cnblogs.com/hongwz/p/5514786.html
 */
@RestController
public class XmlController {
    @Autowired
    private IUserService userService;


    /**
     * xml 导出
     * @param response
     */
    @GetMapping("exportXml")
    public void exportXml(HttpServletResponse response) {
        List<Map<String, Object>> list = userService.listMaps();
        XmlUtil.exportXml(list, "user", response);

    }


    /**
     * xml导入
     * @param file
     * @throws Exception
     */
    @PostMapping("importXml")
    public void importXml(MultipartFile file) throws Exception {

        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(file.getInputStream());
        Element rootElement = document.getRootElement();
        System.out.println("根节点名字：" + rootElement.getName());
        List<Element> elements = rootElement.elements();
        elements.forEach(element -> {
            System.out.println(element.elementText("user_name"));

            System.out.println(element.elementText("age"));
            System.out.println(element.elementText("sex"));
            System.out.println(element.elementText("phone"));
            System.out.println(element.elementText("address"));
            System.out.println(element.elementText("birthday"));
            System.out.println(element.elementText("create_time"));
            System.out.println("#####################################");

        });
    }
}
