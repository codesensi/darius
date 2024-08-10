package cn.codesensi.darius.api.strategy.captcha;

import cn.codesensi.darius.api.dto.CaptchaDTO;
import cn.codesensi.darius.api.vo.CaptchaVO;

/**
 * 验证码策略接口类
 */
public interface CaptchaStrategy {

    /**
     * 生成验证码
     */
    CaptchaVO captcha(CaptchaDTO captchaDTO);
}
