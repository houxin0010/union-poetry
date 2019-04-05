package com.school.union.poetry.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (UserRole)表实体类
 *
 * @author makejava
 * @since 2019-04-05 11:37:44
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserRole extends Model<UserRole> {
    private static final long serialVersionUID = -92953360779350734L;
    private Long id;
    private Integer optimistic;
    private Integer userId;
    private Integer roleId;
}