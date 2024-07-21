package cn.codesensi.darius.auth.service.impl;

import cn.codesensi.darius.auth.dto.AccountUserDTO;
import cn.codesensi.darius.auth.service.AuthService;
import cn.codesensi.darius.auth.vo.LoginSuccessVO;
import cn.codesensi.darius.common.exception.AuthException;
import cn.codesensi.darius.common.properties.DariusConfigProperties;
import cn.codesensi.darius.system.entity.SysUser;
import cn.codesensi.darius.system.service.ISysUserService;
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
    private DariusConfigProperties dariusConfigProperties;
    @Resource
    private ISysUserService sysUserService;

    /**
     * 账号密码登录
     *
     * @param accountUserDTO 登录用户信息
     * @return 登录成功后信息
     */
    @Override
    public LoginSuccessVO account(@Validated @RequestBody AccountUserDTO accountUserDTO) {
        if (dariusConfigProperties.getCaptcha().getEnabled() && StrUtil.isBlank(accountUserDTO.getCaptcha())) {
            throw new AuthException("验证码不能为空");
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
        return null;
    }

}
