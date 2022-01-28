package com.sunshine.music.brian.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author sven
 * Created on 2022/1/28 2:27 下午
 */
@Data
@TableName("user")
public class UserEntity {
    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private Integer age;

    private Byte sex;
}
