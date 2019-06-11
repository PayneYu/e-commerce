package com.ecommerce.framework.base.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ecommerce.common.annotation.Excel;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entity基类
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 用户ID */
    @Excel(name = "唯一标识")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Override
    public boolean equals(Object obj) {
        if (null == obj) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!getClass().equals(obj.getClass())) {
            return false;
        }
        BaseEntity that = (BaseEntity)obj;
        return null == this.getId() ? false : this.getId().equals(that.getId());
    }
}
