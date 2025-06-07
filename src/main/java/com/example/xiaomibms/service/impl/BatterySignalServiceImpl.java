package com.example.xiaomibms.service.impl;

import com.example.xiaomibms.domain.dto.BatterySignalDTO;
import com.example.xiaomibms.domain.entity.BatterySignal;
import com.example.xiaomibms.mapper.BatterySignalMapper;
import com.example.xiaomibms.service.BatterySignalService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Service
public class BatterySignalServiceImpl implements BatterySignalService {
    @Autowired
    private BatterySignalMapper batterySignalMapper;
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    @Qualifier("signalProcessExecutor")
    private Executor signalProcessExecutor;

    // 缓存前缀
    private static final String SIGNAL_CACHE_KEY_PREFIX = "signal:";

    // 缓存过期时间(秒)
    private static final long CACHE_EXPIRE_TIME = 1800;

    private final String CACHE_PREFIX = "batterySignal:";

    /**
     * 创建电池信号
     * @param batterySignalDTO
     */
    @Override
    public void addSignal(BatterySignalDTO batterySignalDTO) {
        BatterySignal batterySignal = new BatterySignal();
        BeanUtils.copyProperties(batterySignalDTO, batterySignal);
        
        // 设置创建时间为当前时间
        batterySignal.setCreateTime(LocalDateTime.now());
        
        // 使用传入的状态，如果为null则默认为0
        if (batterySignal.getStatus() == null) {
            batterySignal.setStatus(0);
        }
        
        batterySignalMapper.insert(batterySignal);
        
        // 异步更新Redis缓存
        CompletableFuture.runAsync(() -> {
            String cacheKey = SIGNAL_CACHE_KEY_PREFIX + batterySignal.getId();
            redisTemplate.opsForValue().set(cacheKey, batterySignal, CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
        }, signalProcessExecutor);
    }

    /**
     * 根据id删除电池信号
     * @param id
     */
    @Override
    public void deleteSignalById(Long id) {
        BatterySignal signal = batterySignalMapper.findById(id);
        if (signal != null) {
            batterySignalMapper.deleteById(id);
            //删除Redis缓存
            redisTemplate.delete(SIGNAL_CACHE_KEY_PREFIX + id);
        }
    }

    /**
     * 根据id修改电池信号
     * @param batterySignalDTO
     */
    @Override
    public void updateSignal(BatterySignalDTO batterySignalDTO) {
        // 获取原电池信号数据
        BatterySignal oldSignal = batterySignalMapper.findById(batterySignalDTO.getId());
        if (oldSignal == null) {
            throw new RuntimeException("电池信号不存在");
        }

        // 创建新的电池信号对象并复制属性
        BatterySignal batterySignal = new BatterySignal();
        BeanUtils.copyProperties(batterySignalDTO, batterySignal);

        // 保留创建时间，不允许更新分片键
        batterySignal.setCreateTime(oldSignal.getCreateTime());

        // 检查状态是否变更
        if (batterySignalDTO.getStatus() != null &&
                !batterySignalDTO.getStatus().equals(oldSignal.getStatus())) {
            // 状态发生变化，需要采用"删除后新增"的方式
            batterySignalMapper.deleteById(oldSignal.getId());

            // 保留原ID
            batterySignal.setId(oldSignal.getId());
            batterySignalMapper.insert(batterySignal);
        } else {
            // 状态没有变化，确保不更新分片键
            batterySignal.setStatus(oldSignal.getStatus()); // 强制保持原状态
            batterySignalMapper.updateWithoutShardingKey(batterySignal);
        }

        // 删除Redis缓存
        redisTemplate.delete(SIGNAL_CACHE_KEY_PREFIX + batterySignal.getId());
    }

    /**
     * 根据ID获取电池信号
     * @param id
     * @return
     */
    public BatterySignal getSignalById(Long id){
        String  Key = SIGNAL_CACHE_KEY_PREFIX + id;
        //先从Redis缓存中获取
        BatterySignal batterySignal = (BatterySignal) redisTemplate.opsForValue().get(Key);
        if(batterySignal != null) {
            return batterySignal;
        }
        //缓存不存在，从数据库中查找
        batterySignal = batterySignalMapper.findById(id);
        if(batterySignal != null){
            //更新Redis缓存
            redisTemplate.opsForValue().set(Key, batterySignal, CACHE_EXPIRE_TIME, TimeUnit.SECONDS);
        }
        return batterySignal;
    }

    /**
     * 根据carId查询电池信号
     * @param carId
     * @return
     */
    public List<BatterySignal> findByCarId(Long carId){
        List<BatterySignal> batterySignals= batterySignalMapper.findByCarId(carId);
        return batterySignals;
    }

    /**
     * 根据电池类型查询电池信号
     * @param batteryType
     * @return
     */
    public List<BatterySignal> findByBatteryType(String batteryType){
        List<BatterySignal> batterySignals = batterySignalMapper.findByBatteryType(batteryType);
        return batterySignals;
    }

    /**
     * 根据信号状态查询电池信号
     * @param status
     * @return
     */
    public List<BatterySignal> findByStatus(int status){
        List<BatterySignal> batterySignals = batterySignalMapper.findByStatus(status);
        return batterySignals;
    }

    /**
     * 根据时间和状态范围查询电池信号
     * @param status
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<BatterySignal> findByStatusAndTimeRange(int status, LocalDateTime startTime, LocalDateTime endTime) {
        return batterySignalMapper.findByStatusAndTimeRange(status, startTime, endTime);
    }
}