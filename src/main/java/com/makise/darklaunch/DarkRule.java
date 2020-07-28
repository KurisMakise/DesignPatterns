package com.makise.darklaunch;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 灰度规则
 *
 * @author k.makise
 * @version 1.0
 * @date 2020/7/22 23:53
 */
public class DarkRule {
    private Map<String, DarkFeature> darkFeatureMap = new HashMap<>();

    public DarkRule(DarkRuleConfig darkRuleConfig) {
        List<DarkRuleConfig.DarkFeatureConfig> darkRuleConfigFeatures = darkRuleConfig.getDarkFeatureConfigs();
        for (DarkRuleConfig.DarkFeatureConfig darkFeatureConfig : darkRuleConfigFeatures) {
            darkFeatureMap.put(darkFeatureConfig.getKey(), new DarkFeature(darkFeatureConfig));
        }
    }

    public DarkFeature getDarkFeature(String darkKey) {
        return darkFeatureMap.get(darkKey);
    }
}
