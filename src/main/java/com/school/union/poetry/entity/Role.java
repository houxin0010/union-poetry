package com.school.union.poetry.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * (Role)表实体类
 *
 * @author makejava
 * @since 2019-04-05 11:37:43
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Role extends Model<Role> {
    private static final long serialVersionUID = -96147354050874006L;
    private Long id;
    private Integer optimistic;
    private String roleCode;
    private String roleName;
}