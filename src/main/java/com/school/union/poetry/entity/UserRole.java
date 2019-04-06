package com.school.union.poetry.entity;

import com.school.union.poetry.entity.base.BaseEntity;
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
public class UserRole extends BaseEntity {
    private static final long serialVersionUID = -92953360779350734L;
    private Integer userId;
    private Integer roleId;
}