package cn.codesensi.darius.common.base;

import cn.codesensi.darius.common.enums.RequestMethod;
import cn.codesensi.darius.common.exception.DemoModeException;
import cn.codesensi.darius.common.properties.DariusConfigProperties;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * web层通用数据处理
 */
public class BaseController {

    @Resource
    private DariusConfigProperties dariusConfigProperties;

    @ModelAttribute
    public void init(HttpServletRequest request) {
        if (dariusConfigProperties.getDemoMode()) {
            // 增删改 请求
            if (RequestMethod.PUT.getCode().equals(request.getMethod()) || RequestMethod.POST.getCode().equals(request.getMethod()) || RequestMethod.DELETE.getCode().equals(request.getMethod())) {
                throw new DemoModeException("演示模式，不允许操作");
            }
        }
    }

}
