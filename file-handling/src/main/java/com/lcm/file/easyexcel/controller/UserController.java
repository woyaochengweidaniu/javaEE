package com.lcm.file.easyexcel.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;


import com.lcm.file.easyexcel.dto.UserWriteModule;
import com.lcm.file.easyexcel.entity.User;
import com.lcm.file.easyexcel.listener.ExcelListener;
import com.lcm.file.easyexcel.mapper.UserMapper;
import com.lcm.file.easyexcel.service.IUserService;
import com.lcm.file.util.ExcelUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * 使用阿里巴巴开源项目 easy excel
 * 项目源码：https://github.com/alibaba/easyexcel
 *
 *
 * @author lcm
 * @since 2019-07-04
 */
@Api(description = "easyExcel 的用法")
@RestController
@RequestMapping("/work/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ExcelListener excelListener;


    @ApiOperation(value = "下载模板")
    @GetMapping("downLoadTemplate")
    public void downLoadTemplate(HttpServletResponse response) throws IOException {
        //第三个参数new ArrayList()  是我们需要填充的数据
        ExcelUtils.exportModule("导入模板",response, UserWriteModule.class,new ArrayList<>());
    }


    @ApiOperation("Excel导入")
    @PostMapping("uploadData")
    public Object uploadData(MultipartFile file) throws IOException {
       // String path = ClassUtils.getDefaultClassLoader().getResource("").getPath();
        String path = "file-handling/target/classes/static/导入模板20190816.xlsx";

        String fileOriginalFilename = file.getOriginalFilename();
        /*String savePath = path+"static/"+fileOriginalFilename;
        System.out.println(savePath);*/
        File newFile = new File(path);
        file.transferTo(newFile);
        /*InputStream inputStream = file.getInputStream();
        EasyExcelFactory.readBySax(inputStream, new Sheet(1, 1, UserReadModule.class), excelListener);
        inputStream.close();*/
        return "success";
    }

    @ApiOperation("Excel导出使用module")
    @GetMapping("export")
    public void export(HttpServletResponse response) throws IOException {
        List<UserWriteModule> userWriteModules = userMapper.exportList(new QueryWrapper().select("*"));
        ExcelUtils.exportModule("用户信息",response,UserWriteModule.class,userWriteModules);
    }


    /**
     * 不使用module
     * @param response
     * @throws IOException
     */
    @ApiOperation("Excel导出不适用module")
    @GetMapping("exportMap")
    public void exportMap(HttpServletResponse response) throws IOException {
        List<UserWriteModule> userWriteModules = userMapper.exportList(new QueryWrapper().select("*"));
        ExcelUtils.exportMap("用户信息",response,getHeadList(),userWriteModules);
    }



    public List<List<String>> getHeadList(){
        List<List<String>> headList = new ArrayList<>();
        List<String> dataHead = new ArrayList<>();
        dataHead.add("用户姓名");
        dataHead.add("年龄");
        dataHead.add("性别");
        dataHead.add("地址");
        dataHead.add("手机号");
        dataHead.add("兴趣");
       dataHead.forEach(s -> {
           ArrayList<String> objects = new ArrayList<>();
           objects.add(s);
           headList.add(objects);
       });
       return headList;
    }

    @ApiOperation("查询列表")
    @GetMapping("get")
    public Object get(@RequestParam  String name){
        return userService.list(Wrappers.<User>lambdaQuery().eq(User::getUserName,name));
    }

}
