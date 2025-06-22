package com.deepblue.redisson.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.springframework.batch.item.ItemStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class RedissonConfig {

    @Value("${spring.data.redis.cluster.nodes}")
    private String nodeAddresses;

    @Value("${spring.data.redis.password}")
    private String password;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redissonClient() {
        Config config = new Config();
        ClusterServersConfig clusterConfig = config.useClusterServers()
                .addNodeAddress(parseNodeAddresses())
                .setIdleConnectionTimeout(10000)
                .setConnectTimeout(10000)
                .setTimeout(3000)
                .setRetryAttempts(3)
                .setRetryInterval(1500)
                .setSubscriptionsPerConnection(5)
                .setSubscriptionConnectionMinimumIdleSize(1)
                .setSubscriptionConnectionPoolSize(50)
                .setMasterConnectionMinimumIdleSize(32)
                .setMasterConnectionPoolSize(500);

        if (StringUtils.isNotBlank(password)) {
            clusterConfig.setPassword(password);
        }

        return Redisson.create(config);
    }

    private String[] parseNodeAddresses() {
        return Arrays.stream(nodeAddresses.split(",")).map(item -> "redis://" + item).toArray(String[]::new);
    }
}


