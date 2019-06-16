package com.ecommerce.framework.sys.mapper;

import com.ecommerce.framework.base.mapper.BaseMapper;
import com.ecommerce.framework.sys.entity.SysUser;

import java.util.List;

/**
 * 用户表 数据层
 * 
 * @author huizhe yu
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser selectById(Long id);
    /**
     * 根据条件分页查询用户对象
     *
     * @param sysUser 用户信息
     * @return 用户信息集合信息
     */
    List<SysUser> selectUserList(SysUser sysUser);

    /**
     * 修改用户信息
     *
     * @param user 用户信息
     * @return 结果
     */
    int updateUser(SysUser user);

    void deleteByIds(Long[] ids);

}
