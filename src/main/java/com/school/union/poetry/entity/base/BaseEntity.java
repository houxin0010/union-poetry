package com.school.union.poetry.entity.base;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.io.Serializable;

/**
 * BaseEntity
 *
 * @author houxin
 * @date 2019/4/5
 */
@Data
public class BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    @Version
    private Integer optimistic = 0;
}
