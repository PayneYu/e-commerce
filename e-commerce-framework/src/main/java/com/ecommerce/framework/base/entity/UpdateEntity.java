package com.ecommerce.framework.base.entity;

import java.util.Date;

import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: Supply Center
 * @description:
 * @author: Huizhe Yu
 * @create: 2019-06-04 20:46
 */
@Getter
@Setter
@ToString
public class UpdateEntity extends CreateEntity {

    /** 更新者 */
    @Column(name = "update_by")
    private String updateBy;

    /** 更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "update_time")
    private Date updateTime;
}
