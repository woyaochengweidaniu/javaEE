package com.lcm.file.easyexcel.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lcm.file.easyexcel.dto.UserWriteModule;
import com.lcm.file.easyexcel.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lcm
 * @since 2019-07-04
 */
@Repository
public interface UserMapper extends BaseMapper<User> {


    @Select("select ${ew.SqlSelect} from user ${ew.customSqlSegment}")
    List<UserWriteModule> exportList(@Param("ew") Wrapper wrapper);

}
