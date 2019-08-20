package com.lcm.file.easyexcel.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import com.lcm.file.easyexcel.dto.UserReadModule;
import com.lcm.file.easyexcel.entity.User;
import com.lcm.file.easyexcel.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * 监听器   监听Excel导入时会去读取Excel的内容
 * @author lcm
 */
@Component
public class ExcelListener extends AnalysisEventListener {

    @Autowired
    private IUserService userService;

    private List<UserReadModule> userReadModuleList = new ArrayList<>();

    public ExcelListener(){};
    public ExcelListener(IUserService service){
        this.userService = service;
    }


    /**
     * 该方法是逐条读取Excel 的内容
     * @param object
     * @param context
     */
    @Override
    public void invoke(Object object, AnalysisContext context) {
        Integer currentRowNum = context.getCurrentRowNum();
        if (currentRowNum!=0){
            System.out.println(object);
            UserReadModule userReadModule = (UserReadModule) object;
            userReadModuleList.add(userReadModule);
        }

    }


    /**
     * 将所有的内容读取之后的方法
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

        System.out.println("所有数据读取完毕");
        List<User> list = new ArrayList<>();
        userReadModuleList.forEach(userReadModule -> {
            User user = new User();
            BeanUtils.copyProperties(userReadModule,user);
            list.add(user);
        });

        userService.saveBatch(list);



    }
}
