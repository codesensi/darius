package cn.codesensi.darius.common.strategy.impl;

import cn.codesensi.darius.system.dto.CaptchaDTO;
import cn.codesensi.darius.system.service.CaffeineService;
import cn.codesensi.darius.system.vo.CaptchaVO;
import cn.codesensi.darius.common.cache.caffeine.CaffeineConstant;
import cn.codesensi.darius.common.exception.SystemException;
import cn.codesensi.darius.common.properties.DariusProperties;
import cn.codesensi.darius.common.strategy.CaptchaStrategy;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.base.Captcha;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;

/**
 * 图形验证码策略实现类
 */
@Slf4j
@Service("imageCaptchaStrategy")
public class ImageCaptcha implements CaptchaStrategy {

    @Resource
    private DariusProperties dariusProperties;
    @Resource
    private CaffeineService caffeineService;

    /**
     * 生成图形验证码
     */
    @Override
    public CaptchaVO captcha(CaptchaDTO captchaDTO) {
        DariusProperties.ImageType imageType = dariusProperties.getCaptcha().getImageType();
        String name = imageType.name();
        // 构建类名
        name = name.toLowerCase();
        name = StrUtil.upperFirst(name);
        String className = name + "Captcha";
        CaptchaVO captchaVO = new CaptchaVO();
        try {
            Class<?> clazz = Class.forName("com.wf.captcha." + className);
            Captcha captcha = (Captcha) clazz.getDeclaredConstructor().newInstance();
            // 算术验证码
            if (captcha instanceof ArithmeticCaptcha) {
                String arithmeticString = ((ArithmeticCaptcha) captcha).getArithmeticString();
                log.info("算术验证码运算公式：{}", arithmeticString);
            }
            // 验证码结果
            String text = captcha.text();
            String key = "image:" + UUID.fastUUID().toString(true);
            log.info("图形验证码唯一标识：{}，验证码内容：{}", key, text);
            // 放入缓存
            caffeineService.put(CaffeineConstant.CACHE_CAPTCHA, key, text, CaffeineConstant.EXPIRE_5_MIN);
            // 返回结果
            captchaVO.setKey(key);
            captchaVO.setResult(captcha.toBase64());
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("图形验证码类型错误：{}", name);
            throw new SystemException("图形验证码生成失败");
        }
        return captchaVO;
    }

}
