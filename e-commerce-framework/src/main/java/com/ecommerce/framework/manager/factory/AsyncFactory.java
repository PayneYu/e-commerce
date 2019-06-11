package com.ecommerce.framework.manager.factory;

import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecommerce.common.utils.AddressUtils;
import com.ecommerce.common.utils.spring.SpringContextHolder;
import com.ecommerce.framework.shiro.session.OnlineSession;
import com.ecommerce.framework.sys.entity.SysOperLog;
import com.ecommerce.framework.sys.entity.SysUserOnline;
import com.ecommerce.framework.sys.service.ISysUserOnlineService;

/**
 * 异步工厂（产生任务用）
 * 
 * @author liuhulu
 *
 */
public class AsyncFactory {
    private static final Logger sys_user_logger = LoggerFactory.getLogger("sys-user");

    /**
     * 同步session到数据库
     * 
     * @param session
     *            在线用户会话
     * @return 任务task
     */
    public static TimerTask syncSessionToDb(final OnlineSession session) {
        return new TimerTask() {
            @Override
            public void run() {
                SysUserOnline online = new SysUserOnline();
                online.setSessionId(String.valueOf(session.getId()));
                online.setDeptName(session.getDeptName());
                online.setLoginName(session.getLoginName());
                online.setStartTimestamp(session.getStartTimestamp());
                online.setLastAccessTime(session.getLastAccessTime());
                online.setExpireTime(session.getTimeout());
                online.setIpaddr(session.getHost());
                // online.setLoginLocation(AddressUtils.getRealAddressByIP(session.getHost()));
                online.setBrowser(session.getBrowser());
                online.setOs(session.getOs());
                online.setStatus(session.getStatus());
                SpringContextHolder.getBean(ISysUserOnlineService.class).insert(online);

            }
        };
    }

    /**
     * 操作日志记录
     * 
     * @param operLog
     *            操作日志信息
     * @return 任务task
     */
    public static TimerTask recordOper(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                // 远程查询操作地点
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                // SpringContextHolder.getBean(ISysOperLogService.class).insertOperlog(operLog);
            }
        };
    }

}
