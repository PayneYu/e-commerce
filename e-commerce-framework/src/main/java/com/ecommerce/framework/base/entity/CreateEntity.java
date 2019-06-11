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
public class CreateEntity extends BaseEntity {

    /** 创建者 */
    @Column(name = "create_by")
    private String createBy;

    /** 创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_time")
    private Date createTime;
}
