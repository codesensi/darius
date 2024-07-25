package cn.codesensi.darius.business.service.impl;

import cn.codesensi.darius.business.dto.AccountUserDTO;
import cn.codesensi.darius.business.service.AuthService;
import cn.codesensi.darius.business.service.CaffeineService;
import cn.codesensi.darius.business.vo.LoginSuccessVO;
import cn.codesensi.darius.common.cache.caffeine.CaffeineConstant;
import cn.codesensi.darius.common.exception.AuthException;
import cn.codesensi.darius.common.properties.DariusProperties;
import cn.codesensi.darius.system.entity.SysUser;
import cn.codesensi.darius.system.service.ISysUserService;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.BCrypt;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 账号鉴权接口实现
 *
 * @author codesensi
 * @since 2024-07-21 11:22:32
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

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
    public LoginSuccessVO account(@Validated @RequestBody AccountUserDTO accountUserDTO) {
        // 校验验证码
        if (dariusProperties.getCaptcha().getEnabled()) {
            if (StrUtil.isBlank(accountUserDTO.getCaptchaKey())) {
                throw new AuthException("验证码唯一标识为空");
            }
            if (StrUtil.isBlank(accountUserDTO.getCaptcha())) {
                throw new AuthException("验证码为空");
            }
            // 与缓存中的值对比
            String captchaText = (String) caffeineService.get(CaffeineConstant.CACHE_CAPTCHA, accountUserDTO.getCaptchaKey());
            if (!accountUserDTO.getCaptcha().equals(captchaText)) {
                throw new AuthException("验证码错误");
            }
        }
        SysUser sysUser = sysUserService.lambdaQuery()
                .eq(SysUser::getUsername, accountUserDTO.getUsername())
                .one();
        if (sysUser == null) {
            throw new AuthException("账号不存在");
        }
        if (!BCrypt.checkpw(accountUserDTO.getPassword(), sysUser.getPassword())) {
            throw new AuthException("账号密码错误");
        }
        // 登录
        StpUtil.login(sysUser.getId());
        LoginSuccessVO loginSuccessVO = new LoginSuccessVO();
        loginSuccessVO.setAccessToken(StpUtil.getTokenValue());
        return loginSuccessVO;
    }

}
