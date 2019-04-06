package com.school.union.poetry.entity;

import com.school.union.poetry.entity.base.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * (User)表实体类
 *
 * @author makejava
 * @since 2019-04-05 11:37:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    private static final long serialVersionUID = 355324595733583375L;
    private String username;
    private String password;
    private String telephone;
    private String email;
    private Date createTime;
    private Date updateTime;
}