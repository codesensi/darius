package cn.codesensi.darius.api.strategy.captcha;

import cn.codesensi.darius.api.dto.CaptchaDTO;
import cn.codesensi.darius.common.enums.CaptchaStrategyType;
import cn.codesensi.darius.api.vo.CaptchaVO;
import cn.codesensi.darius.common.exception.SystemException;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

/**
 * 验证码策略上下文
 */
@Slf4j
@Component
public class CaptchaStrategyContext {

    @Resource
    private CaptchaStrategyFactory captchaStrategyFactory;

    /**
     * 生成验证码
     */
    public CaptchaVO captcha(CaptchaDTO captchaDTO) {
        // 将属性的值转换成具体的枚举
        String type = captchaDTO.getType();
        Optional<CaptchaStrategyType> optional = Arrays.stream(CaptchaStrategyType.class.getEnumConstants())
                .filter((e) -> type.equals(e.getCode()))
                .findAny();
        if (optional.isEmpty()) {
            log.error("未匹配到验证码策略：{}", type);
            throw new SystemException("验证码生成失败");
        }
        // 获取策略实现
        CaptchaStrategyType captchaStrategyType = optional.get();
        CaptchaStrategy captchaStrategy = captchaStrategyFactory.getCaptchaStrategy(captchaStrategyType);
        return captchaStrategy.captcha(captchaDTO);
    }
}
