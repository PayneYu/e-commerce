package com.ecommerce.framework.sys.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.common.utils.DateUtils;
import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.framework.base.service.impl.BaseServiceImpl;
import com.ecommerce.framework.sys.entity.SysUserOnline;
import com.ecommerce.framework.sys.mapper.SysUserOnlineMapper;
import com.ecommerce.framework.sys.service.ISysUserOnlineService;

/**
 * 在线用户 服务层处理
 * 
 * @author huizhe yu
 */
@Service
public class SysUserOnlineServiceImpl extends BaseServiceImpl<SysUserOnline, SysUserOnlineMapper>
    implements ISysUserOnlineService {

    @Autowired
    private SysUserOnlineMapper userOnlineMapper;

    /**
     * 通过会话序号查询信息
     *
     * @param sessionId
     *            会话ID
     * @return 在线用户信息
     */
    @Override
    public SysUserOnline selectOnlineById(String sessionId) {
        return userOnlineMapper.selectOnlineById(sessionId);
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessionId
     *            会话ID
     * @return 在线用户信息
     */
    @Override
    public void deleteOnlineById(String sessionId) {
        SysUserOnline userOnline = selectOnlineById(sessionId);
        if (StringUtils.isNotNull(userOnline)) {
            userOnlineMapper.deleteOnlineById(sessionId);
        }
    }

    /**
     * 通过会话序号删除信息
     *
     * @param sessions
     *            会话ID集合
     * @return 在线用户信息
     */
    @Override
    public void batchDeleteOnline(List<String> sessions) {
        for (String sessionId : sessions) {
            SysUserOnline userOnline = selectOnlineById(sessionId);
            if (StringUtils.isNotNull(userOnline)) {
                userOnlineMapper.deleteOnlineById(sessionId);
            }
        }
    }

    /**
     * 保存会话信息
     *
     * @param online
     *            会话信息
     */
    @Override
    public void saveOnline(SysUserOnline online) {
        userOnlineMapper.saveOnline(online);
    }

    /**
     * 查询会话集合
     *
     * @param userOnline
     *            在线用户
     */
    @Override
    public List<SysUserOnline> selectUserOnlineList(SysUserOnline userOnline) {
        return userOnlineMapper.selectUserOnlineList(userOnline);
    }

    /**
     * 强退用户
     *
     * @param sessionId
     *            会话ID
     */
    @Override
    public void forceLogout(String sessionId) {
        userOnlineMapper.deleteOnlineById(sessionId);
    }

    /**
     * 查询会话集合
     *
     * @param expiredDate
     *            失效日期
     */
    @Override
    public List<SysUserOnline> selectOnlineByExpired(Date expiredDate) {
        String lastAccessTime = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, expiredDate);
        return userOnlineMapper.selectOnlineByExpired(lastAccessTime);
    }

}
