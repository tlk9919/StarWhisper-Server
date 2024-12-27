package com.example.starwhisperserver.dao;

import com.example.starwhisperserver.model.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author admin_tlk
 * @since 2024-12-12
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
