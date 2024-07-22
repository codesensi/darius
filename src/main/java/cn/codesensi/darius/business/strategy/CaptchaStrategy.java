package cn.codesensi.darius.business.strategy;

import cn.codesensi.darius.business.dto.CaptchaDTO;
import cn.codesensi.darius.business.vo.CaptchaVO;

/**
 * 验证码策略接口类
 */
public interface CaptchaStrategy {

    /**
     * 生成验证码
     */
    CaptchaVO captcha(CaptchaDTO captchaDTO);
}
