package cn.codesensi.darius.common.base;

import cn.codesensi.darius.common.enums.RequestMethod;
import cn.codesensi.darius.common.exception.ModeException;
import cn.codesensi.darius.common.properties.DariusProperties;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * web层通用数据处理
 */
public class BaseController {

    @Resource
    private DariusProperties dariusProperties;

    @ModelAttribute
    public void init(HttpServletRequest request) {
        if (dariusProperties.getDemoMode()) {
            // 增删改 请求
            if (RequestMethod.PUT.getCode().equals(request.getMethod()) || RequestMethod.POST.getCode().equals(request.getMethod()) || RequestMethod.DELETE.getCode().equals(request.getMethod())) {
                throw new ModeException("演示模式，不允许操作");
            }
        }
    }

}
