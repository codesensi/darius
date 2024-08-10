package cn.codesensi.darius.system.service.impl;

import cn.codesensi.darius.system.entity.SysLoginLog;
import cn.codesensi.darius.system.mapper.SysLoginLogMapper;
import cn.codesensi.darius.system.service.ISysLoginLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * 登录日志表 服务实现类
 *
 * @author codesensi
 * @since 2024-08-10 21:26:37
 */
@Service
public class SysLoginLogServiceImpl extends ServiceImpl<SysLoginLogMapper, SysLoginLog> implements ISysLoginLogService {

}
