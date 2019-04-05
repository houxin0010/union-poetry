package com.school.union.poetry.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2019-04-05 11:37:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends Model<User> {
    private static final long serialVersionUID = 355324595733583375L;
    /**
     * 主键
     */
    private Long id;
    private Integer optimistic;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private Date createTime;
    private Date updateTime;
}