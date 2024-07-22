package cn.codesensi.darius.business.service;

import cn.codesensi.darius.business.dto.AccountUserDTO;
import cn.codesensi.darius.business.vo.LoginSuccessVO;

/**
 * 账号鉴权接口
 *
 * @author codesensi
 * @since 2024-07-21 11:22:32
 */
public interface AuthService {

    /**
     * 账号密码登录
     *
     * @param accountUserDTO 登录用户信息
     * @return 登录成功后信息
     */
    LoginSuccessVO account(AccountUserDTO accountUserDTO);

}
