package com.school.union.poetry.entity;

import com.school.union.poetry.entity.base.BaseEntity;
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
public class Role extends BaseEntity {
    private static final long serialVersionUID = -96147354050874006L;
    private String roleCode;
    private String roleName;
}