package cn.codesensi.darius.api.service.impl;

import cn.codesensi.darius.api.dto.AccountUserDTO;
import cn.codesensi.darius.api.service.LoginService;
import cn.codesensi.darius.api.task.TaskFactory;
import cn.codesensi.darius.api.task.TaskManager;
import cn.codesensi.darius.api.vo.LoginSuccessVO;
import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.common.enums.LoginMode;
import cn.codesensi.darius.common.enums.LoginType;
import cn.codesensi.darius.common.exception.LoginException;
import cn.codesensi.darius.common.properties.DariusProperties;
import cn.codesensi.darius.common.util.Ip2regionUtil;
import cn.codesensi.darius.common.util.IpUtil;
import cn.codesensi.darius.common.util.ServletUtil;
import cn.codesensi.darius.sys.entity.SysLoginLog;
import cn.codesensi.darius.sys.entity.SysUser;
import cn.codesensi.darius.sys.service.ISysUserService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;

/**
 * 登录接口实现
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private DariusProperties dariusProperties;
    @Resource
    private ISysUserService sysUserService;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 账号密码登录
     *
     * @param accountUserDTO 登录用户信息
     * @return 登录成功后信息
     */
    @Override
    public LoginSuccessVO loginAccount(@Validated @RequestBody AccountUserDTO accountUserDTO) {
        // 校验验证码
        if (dariusProperties.getCaptcha().getEnabled()) {
            if (StrUtil.isBlank(accountUserDTO.getCaptchaKey())) {
                throw new LoginException("验证码唯一标识为空");
            }
            String captcha = accountUserDTO.getCaptcha();
            if (StrUtil.isBlank(captcha)) {
                throw new LoginException("验证码为空");
            }
            // 与缓存中的值对比
            String captchaCache = stringRedisTemplate.opsForValue().get(accountUserDTO.getCaptchaKey());
            if (StrUtil.isBlank(captchaCache)) {
                throw new LoginException("验证码不存在");
            }
            if (!captcha.equals(captchaCache)) {
                throw new LoginException("验证码错误");
            }
        }
        SysUser sysUser = sysUserService.lambdaQuery()
                .eq(SysUser::getUsername, accountUserDTO.getUsername())
                .one();
        if (sysUser == null) {
            throw new LoginException("账号不存在");
        }
        if (!BCrypt.checkpw(accountUserDTO.getPassword(), sysUser.getPassword())) {
            throw new LoginException("账号密码错误");
        }
        // 登录
        StpUtil.login(sysUser.getId());
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        loginSuccessVO.setAccessToken(StpUtil.getTokenValue());

        // 异步记录登录成功日志
        SysLoginLog sysLoginLog = new SysLoginLog();
        sysLoginLog.setType(LoginType.LOGIN.getCode());
        sysLoginLog.setMode(LoginMode.ACCOUNT.getCode());
        sysLoginLog.setLoginTime(LocalDateTime.now());
        String ipAddr = IpUtil.getIpAddr();
        sysLoginLog.setIp(ipAddr);
        sysLoginLog.setArea(Ip2regionUtil.search(ipAddr));
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getUserAgent());
        sysLoginLog.setOs(userAgent.getOperatingSystem().getName());
        sysLoginLog.setDevice(userAgent.getOperatingSystem().getDeviceType().getName());
        sysLoginLog.setBrowser(userAgent.getBrowser().getName());
        sysLoginLog.setState(Constant.ONE_INT);
        sysLoginLog.setCreator(StpUtil.getLoginIdAsLong());
        TaskManager.me().execute(TaskFactory.recordAuthLog(sysLoginLog));
        return loginSuccessVO;
    }
}
