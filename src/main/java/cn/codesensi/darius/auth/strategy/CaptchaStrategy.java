package cn.codesensi.darius.auth.strategy;

import cn.codesensi.darius.auth.dto.CaptchaDTO;
import cn.codesensi.darius.auth.vo.CaptchaVO;

/**
 * 验证码策略接口类
 */
public interface CaptchaStrategy {

    /**
     * 生成验证码
     */
    CaptchaVO captcha(CaptchaDTO captchaDTO);
}
