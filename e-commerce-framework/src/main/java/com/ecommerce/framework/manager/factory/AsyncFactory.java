package com.ecommerce.framework.manager.factory;

import com.ecommerce.common.constant.Constants;
import com.ecommerce.common.utils.AddressUtils;
import com.ecommerce.common.utils.ServletUtils;
import com.ecommerce.common.utils.spring.SpringContextHolder;
import com.ecommerce.framework.shiro.session.OnlineSession;
import com.ecommerce.framework.sys.entity.SysLoginInfo;
import com.ecommerce.framework.sys.entity.SysOperLog;
import com.ecommerce.framework.sys.entity.SysUserOnline;
import com.ecommerce.framework.sys.service.ISysOperLogService;
import com.ecommerce.framework.sys.service.ISysUserOnlineService;
import com.ecommerce.framework.sys.service.impl.SysLoginInfoServiceImpl;
import com.ecommerce.framework.util.LogUtils;
import com.ecommerce.framework.util.ShiroUtils;
import eu.bitwalker.useragentutils.UserAgent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.TimerTask;

/**
 * 异步工厂（产生任务用）
 * 
 * @author
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
     * 记录登陆信息
     *
     * @param username
     *            用户名
     * @param status
     *            状态
     * @param message
     *            消息
     * @param args
     *            列表
     * @return 任务task
     */
    public static TimerTask recordLoginInfo(final String username, final String status, final String message,
        final Object... args) {
        final UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getRequest().getHeader("User-Agent"));
        final String ip = ShiroUtils.getIp();
        return new TimerTask() {
            @Override
            public void run() {
                StringBuilder s = new StringBuilder();
                s.append(LogUtils.getBlock(ip));
                s.append(AddressUtils.getRealAddressByIP(ip));
                s.append(LogUtils.getBlock(username));
                s.append(LogUtils.getBlock(status));
                s.append(LogUtils.getBlock(message));
                // 打印信息到日志
                sys_user_logger.info(s.toString(), args);
                // 获取客户端操作系统
                String os = userAgent.getOperatingSystem().getName();
                // 获取客户端浏览器
                String browser = userAgent.getBrowser().getName();
                // 封装对象
                SysLoginInfo loginInfo = new SysLoginInfo();
                loginInfo.setLoginName(username);
                loginInfo.setIpAddress(ip);
                loginInfo.setLoginLocation(AddressUtils.getRealAddressByIP(ip));
                loginInfo.setBrowser(browser);
                loginInfo.setOs(os);
                loginInfo.setMsg(message);
                // 日志状态
                if (Constants.LOGIN_SUCCESS.equals(status) || Constants.LOGOUT.equals(status)) {
                    loginInfo.setStatus(Constants.SUCCESS);
                } else if (Constants.LOGIN_FAIL.equals(status)) {
                    loginInfo.setStatus(Constants.FAIL);
                }
                loginInfo.setLoginTime(new Date());
                // 插入数据
                SpringContextHolder.getBean(SysLoginInfoServiceImpl.class).insert(loginInfo);
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
                operLog.setOperTime(new Date());
                operLog.setOperLocation(AddressUtils.getRealAddressByIP(operLog.getOperIp()));
                SpringContextHolder.getBean(ISysOperLogService.class).insert(operLog);
            }
        };
    }

}
