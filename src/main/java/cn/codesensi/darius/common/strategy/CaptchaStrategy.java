package cn.codesensi.darius.common.strategy;

import cn.codesensi.darius.system.dto.CaptchaDTO;
import cn.codesensi.darius.system.vo.CaptchaVO;

/**
 * 验证码策略接口类
 */
public interface CaptchaStrategy {

    /**
     * 生成验证码
     */
    CaptchaVO captcha(CaptchaDTO captchaDTO);
}
