package cn.codesensi.darius.auth.strategy.impl;

import cn.codesensi.darius.auth.dto.CaptchaDTO;
import cn.codesensi.darius.auth.strategy.CaptchaStrategy;
import cn.codesensi.darius.auth.vo.CaptchaVO;
import cn.codesensi.darius.common.exception.SystemException;
import cn.codesensi.darius.common.properties.DariusConfigProperties;
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
    private DariusConfigProperties dariusConfigProperties;

    /**
     * 生成图形验证码
     */
    @Override
    public CaptchaVO captcha(CaptchaDTO captchaDTO) {
        DariusConfigProperties.ImageType imageType = dariusConfigProperties.getCaptcha().getImageType();
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
                log.info("运算公式：{}", arithmeticString);
            }
            // 验证码结果
            String text = captcha.text();
            String key = UUID.fastUUID().toString(true);
            log.info("唯一标识：{}，生成的图形验证码结果：{}", key, text);

            // TODO 放入缓存

            captchaVO.setKey(key);
            // 返回图片base64
            captchaVO.setResult(captcha.toBase64());
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException |
                 InvocationTargetException e) {
            log.error("图形验证码类型错误：{}", name);
            throw new SystemException("图形验证码生成失败");
        }
        return captchaVO;
    }
}
