package cn.gaozheng.sales.crontab;

import cn.gaozheng.sales.config.BeanConfig;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * WaitingVehicleQueueTask
 *
 * @author Cheng Bo
 * @version 1.0
 */
@Component
@EnableScheduling
@Slf4j
@AllArgsConstructor
@Async
public class WaitingVehicleQueueTask {
    private static final String CORN_ONE = "0/15 * * * * ?";
    private static final String CORN_TWO = "0/15 * * * * ?";
    private static final String CORN_FOUR = "0/15 * * * * ?";
    private static final String CORN_FIVE = "0/15 * * * * ?";
    private static final String CORN_SIX = "0/120 * * * * ?";
    private static final String COORN_SEVEN = "0/300 * * * * ?";;
    private static final String CORN_SEND_SMS = "0/10 * * * * ?";

    private BeanConfig beanConfig;

    /**
     * To queue 1
     * 移动等待提单到队列中
     */
    @Scheduled(cron = CORN_ONE)
    @Transactional(rollbackFor = Exception.class)
    public void taskOne() {

    }
}
