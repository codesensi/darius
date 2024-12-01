package cn.codesensi.darius.sys.service.impl;

import cn.codesensi.darius.sys.entity.SysLoginLog;
import cn.codesensi.darius.sys.mapper.SysLoginLogMapper;
import cn.codesensi.darius.sys.service.ISysLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 登录日志表 服务实现类
 *
 * @author codesensi
 * @since 2024-11-30 23:33:21
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements ISysLoginLogService {

}
