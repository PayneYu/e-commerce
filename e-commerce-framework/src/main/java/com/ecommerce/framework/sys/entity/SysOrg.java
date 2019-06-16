package com.ecommerce.framework.sys.entity;

import com.ecommerce.common.annotation.Excel;
import com.ecommerce.framework.base.entity.BaseEntity;
                      
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.Table;

/**
 * sys_org 机构
 *
 * @author huizhe yu
 * @date 2019-06-15
 */
@Getter
@Setter
@ToString
@Table(name="sys_org")
public class SysOrg extends BaseEntity {

  /**
   * parentId 上级机构
   */
  @Excel(name = "上级机构")
  private Long parentId;
  /**
   * orgType 机构类型
   */
  @Excel(name = "机构类型")
  private Integer orgType;
  /**
   * orgCode 机构编码
   */
  @Excel(name = "机构编码")
  private String orgCode;
  /**
   * orgName 机构名称
   */
  @Excel(name = "机构名称")
  private String orgName;
  /**
   * orgLevel 机构级别
   */
  @Excel(name = "机构级别")
  private Integer orgLevel;
  /**
   * orgAdress 机构地址
   */
  @Excel(name = "机构地址")
  private String orgAddress;
  /**
   * orgPathCode 机构全路径ID
   */
  @Excel(name = "机构全路径ID")
  private String orgPathCode;
  /**
   * orgPathName 机构全路径名称
   */
  @Excel(name = "机构全路径名称")
  private String orgPathName;
  /**
   * lng 经度
   */
  @Excel(name = "经度")
  private Double lng;
  /**
   * lat 纬度
   */
  @Excel(name = "纬度")
  private Double lat;

}


