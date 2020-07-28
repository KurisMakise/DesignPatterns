package com.makise.darklaunch;

import lombok.Data;

import java.util.List;

/**
 * 灰度配置
 *
 * @author k.makise
 * @version 1.0
 * @date 2020/7/22 23:54
 */

public class DarkRuleConfig {
    /**
     * features:
     * - key: call_newapi_getUserById
     * enabled: true
     * rule: {893,342,1020-1120,%30}
     * - key: call_newapi_registerUser
     * enabled: true
     * rule: {1391198723, %10}
     * - key: newalgo_loan
     * enabled: true
     * rule: {0-1000}
     */
    private List<DarkFeatureConfig> darkFeatureConfigs;

    public List<DarkFeatureConfig> getDarkFeatureConfigs() {
        return darkFeatureConfigs;
    }

    public void setDarkFeatureConfigs(List<DarkFeatureConfig> darkFeatureConfigs) {
        this.darkFeatureConfigs = darkFeatureConfigs;
    }

    @Data
    public static class DarkFeatureConfig {
        private String key;
        private boolean enabled;
        private String rule;
    }
}
