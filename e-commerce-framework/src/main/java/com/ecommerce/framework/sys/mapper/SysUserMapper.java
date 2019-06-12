package com.ecommerce.framework.sys.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysUser;

/**
 * 用户表 数据层
 * 
 * @author huizhe yu
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUser user);

    void deleteByIds(Long[] ids);

}
