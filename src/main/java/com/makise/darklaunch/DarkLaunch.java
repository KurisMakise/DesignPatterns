package com.makise.darklaunch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 灰度顶层入口
 *
 * @author k.makise
 * @version 1.0
 * @date 2020/7/22 23:52
 */
public class DarkLaunch {

    private static final Logger LOGGER = LoggerFactory.getLogger(DarkLaunch.class);

    /**
     * in seconds
     */
    private static final int DEFAULT_RULE_UPDATE_INTERVAL = 60;

    private DarkRule darkRule;

    public DarkLaunch() {
        this(DEFAULT_RULE_UPDATE_INTERVAL);
    }

    private ScheduledExecutorService executor;

    public DarkLaunch(int ruleUpdateInterval) {
        loadRule();
        this.executor = new ScheduledThreadPoolExecutor(1);

        this.executor.schedule(() -> {
            loadRule();
        }, ruleUpdateInterval, TimeUnit.SECONDS);

    }

    private void loadRule() {
        InputStream in = this.getClass().getResourceAsStream("dark-rule.yaml");

        Yaml yaml = new Yaml();
        DarkRuleConfig darkRuleConfig = yaml.loadAs(in, DarkRuleConfig.class);
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("close file error:{}", e);
        }
        if (darkRuleConfig == null) {
            throw new RuntimeException("Can not load dark rule.");
        }

        // 更新规则并非直接在this.rule上进行，
        // 而是通过创建一个新的DarkRule，然后赋值给this.rule，
        // 来避免更新规则和规则查询的并发冲突问题
        DarkRule newRule = new DarkRule(darkRuleConfig);

        this.darkRule = newRule;
    }

    public DarkFeature getDarkFeature(String darkFeatureKey) {
        return darkRule.getDarkFeature(darkFeatureKey);
    }

}
