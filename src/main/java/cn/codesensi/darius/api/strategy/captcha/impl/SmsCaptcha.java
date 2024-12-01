package cn.codesensi.darius.api.strategy.captcha.impl;

import cn.codesensi.darius.api.dto.CaptchaDTO;
import cn.codesensi.darius.api.strategy.captcha.CaptchaStrategy;
import cn.codesensi.darius.api.vo.CaptchaVO;
import cn.codesensi.darius.common.constant.CacheConstant;
import cn.codesensi.darius.common.exception.SystemException;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * 短信验证码策略实现类
 */
@Slf4j
@Service("smsCaptchaStrategy")
public class SmsCaptcha implements CaptchaStrategy {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 生成短信验证码
     */
    @Override
    public CaptchaVO captcha(CaptchaDTO captchaDTO) {
        String phone = captchaDTO.getPhone();
        if (StrUtil.isBlank(phone)) {
            throw new SystemException("手机号不能为空");
        }
        // 生成验证码
        String key = "sms:" + phone;
        String result = RandomUtil.randomNumbers(6);
        log.info("短信验证码手机号：{}，验证码内容：{}", phone, result);
        // 放入缓存
        stringRedisTemplate.opsForValue().set(key, result, CacheConstant.EXPIRE_5_MINUTES, TimeUnit.MINUTES);
        // 返回结果
        CaptchaVO captchaVO = new CaptchaVO();
        captchaVO.setKey(key);
        captchaVO.setResult(result);
        return captchaVO;
    }
}
