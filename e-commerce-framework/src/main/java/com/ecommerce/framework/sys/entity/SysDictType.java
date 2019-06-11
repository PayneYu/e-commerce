package com.ecommerce.framework.sys.entity;

import javax.persistence.Table;

import com.ecommerce.common.annotation.Excel;
import com.ecommerce.framework.base.entity.UpdateEntity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 字典类型表 sys_dict_type
 * 
 * @author huizhe yu
 */
@Getter
@Setter
@ToString
@Table(name = "sys_dict_type")
public class SysDictType extends UpdateEntity {

    /** 字典主键 */
    @Excel(name = "字典主键")
    private Long dictId;

    /** 字典名称 */
    @Excel(name = "字典名称")
    private String dictName;

    /** 字典类型 */
    @Excel(name = "字典类型 ")
    private String dictType;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

}
