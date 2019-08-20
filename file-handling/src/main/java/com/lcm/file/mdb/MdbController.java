package com.lcm.file.mdb;



import com.lcm.file.easyexcel.service.IUserService;
import com.lcm.file.util.AccessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lcm
 */
@RestController
public class MdbController {

    @Autowired
    private IUserService userService;

    @GetMapping("exportMdb")
    public void exportMdb(HttpServletResponse response) throws IOException {
        Map<String,Integer> head = new HashMap<>();
        List<Object[]> data = new ArrayList<>();
        String path = "";
        String tableName = "";


        AccessUtil.exportAccess(head,data,path,tableName);

    }

}
