package cn.codesensi.darius.system.service.impl;

import cn.codesensi.darius.common.config.caffeine.CaffeineConstant;
import cn.codesensi.darius.common.constant.Constant;
import cn.codesensi.darius.common.exception.LoginException;
import cn.codesensi.darius.common.properties.DariusProperties;
import cn.codesensi.darius.common.util.Ip2regionUtil;
import cn.codesensi.darius.common.util.IpUtil;
import cn.codesensi.darius.common.util.ServletUtil;
import cn.codesensi.darius.system.dto.AccountUserDTO;
import cn.codesensi.darius.system.entity.SysAuthLog;
import cn.codesensi.darius.system.entity.SysUser;
import cn.codesensi.darius.common.enums.AuthType;
import cn.codesensi.darius.system.service.CaffeineService;
import cn.codesensi.darius.system.service.ISysUserService;
import cn.codesensi.darius.system.service.LoginService;
import cn.codesensi.darius.system.task.TaskFactory;
import cn.codesensi.darius.system.task.TaskManager;
import cn.codesensi.darius.system.vo.LoginSuccessVO;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.annotation.Resource;
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
    private CaffeineService caffeineService;

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
            if (StrUtil.isBlank(accountUserDTO.getCaptcha())) {
                throw new LoginException("验证码为空");
            }
            // 与缓存中的值对比
            String captchaText = (String) caffeineService.get(CaffeineConstant.CACHE_CAPTCHA, accountUserDTO.getCaptchaKey());
            if (!accountUserDTO.getCaptcha().equals(captchaText)) {
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
        SysAuthLog sysAuthLog = new SysAuthLog();
        sysAuthLog.setAuthType(AuthType.LOGIN.getCode());
        sysAuthLog.setAuthTime(LocalDateTime.now());
        String ipAddr = IpUtil.getIpAddr();
        sysAuthLog.setAuthIp(ipAddr);
        sysAuthLog.setAuthArea(Ip2regionUtil.search(ipAddr));
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtil.getUserAgent());
        sysAuthLog.setAuthOs(userAgent.getOperatingSystem().getName());
        sysAuthLog.setAuthDevice(userAgent.getOperatingSystem().getDeviceType().getName());
        sysAuthLog.setAuthBrowser(userAgent.getBrowser().getName());
        sysAuthLog.setAuthState(Constant.ONE_INT);
        sysAuthLog.setCreator(StpUtil.getLoginIdAsLong());
        TaskManager.me().execute(TaskFactory.recordAuthLog(sysAuthLog));
        return loginSuccessVO;
    }
}
