package com.ecommerce.web.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.common.annotation.Log;
import com.ecommerce.common.base.AjaxResult;
import com.ecommerce.common.config.Global;
import com.ecommerce.common.enums.BusinessType;
import com.ecommerce.common.utils.StringUtils;
import com.ecommerce.common.utils.file.FileUploadUtils;
import com.ecommerce.framework.base.controller.BaseController;
import com.ecommerce.framework.shiro.service.SysPasswordService;
import com.ecommerce.framework.sys.entity.SysUser;
import com.ecommerce.framework.sys.service.ISysDictDataService;
import com.ecommerce.framework.sys.service.ISysUserService;
import com.ecommerce.framework.util.ShiroUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 个人信息 业务处理
 * 
 * @author huizhe yu
 */
@Slf4j
@Controller
@RequestMapping("/system/user/profile")
public class SysProfileController extends BaseController {

    private String prefix = "system/user/profile";

    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysDictDataService dictDataService;

    /**
     * 个人信息
     */
    @GetMapping
    public String profile(ModelMap mmap) {
        SysUser user = getSysUser();
        user.setSex(dictDataService.selectDictLabel("sys_user_sex", user.getSex()));
        mmap.put("user", userService.selectById(user.getId()));
        mmap.put("roleGroup", userService.selectUserRoleGroup(user.getId()));
        return prefix + "/profile";
    }

    @GetMapping("/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        SysUser user = getSysUser();
        if (passwordService.matches(user, password)) {
            return true;
        }
        return false;
    }

    @GetMapping("/resetPwd")
    public String resetPwd(ModelMap mmap) {
        SysUser user = getSysUser();
        mmap.put("user", userService.selectById(user.getId()));
        return prefix + "/resetPwd";
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwd(String oldPassword, String newPassword) {
        SysUser user = getSysUser();
        if (StringUtils.isNotEmpty(newPassword) && passwordService.matches(user, oldPassword)) {
            user.setSalt(ShiroUtils.randomSalt());
            user.setPassword(passwordService.encryptPassword(user.getLoginName(), newPassword, user.getSalt()));
            try {
                userService.update(user);
                setSysUser(userService.selectById(user.getId()));
                return success();
            } catch (Exception e) {
                return error("修改密码失败");
            }
        } else {
            return error("修改密码失败，旧密码错误");
        }
    }

    /**
     * 修改用户
     */
    @GetMapping("/edit")
    public String edit(ModelMap mmap) {
        SysUser user = getSysUser();
        mmap.put("user", userService.selectById(user.getId()));
        return prefix + "/edit";
    }

    /**
     * 修改头像
     */
    @GetMapping("/avatar")
    public String avatar(ModelMap mmap) {
        SysUser user = getSysUser();
        mmap.put("user", userService.selectById(user.getId()));
        return prefix + "/avatar";
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/update")
    @ResponseBody
    public AjaxResult update(SysUser user) {
        SysUser currentUser = getSysUser();
        currentUser.setUserName(user.getUserName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setSex(user.getSex());
        try {
            userService.update(currentUser);
            setSysUser(userService.selectById(user.getId()));
            return success();
        } catch (Exception e) {
            return error("修改密码失败");
        }
    }

    /**
     * 保存头像
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PostMapping("/updateAvatar")
    @ResponseBody
    public AjaxResult updateAvatar(@RequestParam("avatarfile") MultipartFile file) {
        SysUser currentUser = getSysUser();
        try {
            if (!file.isEmpty()) {
                String avatar = FileUploadUtils.upload(Global.getAvatarPath(), file);
                currentUser.setAvatar(avatar);
                userService.update(currentUser);
                setSysUser(userService.selectById(currentUser.getId()));
            }
            return success();
        } catch (Exception e) {
            log.error("修改头像失败！", e);
            return error(e.getMessage());
        }
    }
}
