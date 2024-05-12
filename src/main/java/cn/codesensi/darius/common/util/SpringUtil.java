package cn.codesensi.darius.common.util;

import cn.hutool.core.util.ObjUtil;
import jakarta.annotation.Nonnull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring工具类 方便在非spring管理环境中获取bean
 *
 * @author ruoyi
 */
@Component
public final class SpringUtil implements BeanFactoryPostProcessor, ApplicationContextAware {
    /**
     * Spring应用上下文环境
     */
    private static ConfigurableListableBeanFactory beanFactory;

    private static ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(@Nonnull ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtil.beanFactory = beanFactory;
    }

    @Override
    public void setApplicationContext(@Nonnull ApplicationContext applicationContext) throws BeansException {
        SpringUtil.applicationContext = applicationContext;
    }

    /**
     * 获取对象
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return clazz.cast(beanFactory.getBean(name));
    }

    /**
     * 获取类型为requiredType的对象
     */
    public static <T> T getBean(Class<T> clz) {
        return beanFactory.getBean(clz);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的bean定义，则返回true
     */
    public static boolean containsBean(String name) {
        return beanFactory.containsBean(name);
    }

    /**
     * 判断以给定名字注册的bean定义是一个singleton还是一个prototype。 如果与给定名字相应的bean定义没有被找到，将会抛出一个异常（NoSuchBeanDefinitionException）
     */
    public static boolean isSingleton(String name) {
        return beanFactory.isSingleton(name);
    }


    /**
     * 获取当前的环境配置，无配置返回null
     */
    public static String[] getActiveProfiles() {
        return applicationContext.getEnvironment().getActiveProfiles();
    }

    /**
     * 获取当前的环境配置，当有多个环境配置时，只获取第一个
     */
    public static String getActiveProfile() {
        final String[] activeProfiles = getActiveProfiles();
        return ObjUtil.isNotEmpty(activeProfiles) ? activeProfiles[0] : null;
    }

    /**
     * 获取配置文件中的值
     */
    public static String getRequiredProperty(String key) {
        return applicationContext.getEnvironment().getRequiredProperty(key);
    }
}
