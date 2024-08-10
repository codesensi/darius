package cn.codesensi.darius.api.service;

import cn.codesensi.darius.api.dto.AccountUserDTO;
import cn.codesensi.darius.api.vo.LoginSuccessVO;

/**
 * 登录接口
 */
public interface LoginService {

    /**
     * 账号密码登录
     *
     * @param accountUserDTO 登录用户信息
     * @return 登录成功后信息
     */
    LoginSuccessVO loginAccount(AccountUserDTO accountUserDTO);
}
