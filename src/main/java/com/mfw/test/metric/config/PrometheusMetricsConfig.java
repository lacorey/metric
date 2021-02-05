package com.mfw.test.metric.config;

import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrometheusMetricsConfig {

    @Autowired
    private PrometheusMeterRegistry registry;

    @Bean
    Summary getSummary() {
        return Summary.build().labelNames("uri", "accessType", "code")
                .name("api_cost_summary").help("请求耗时summary")
                .quantile(0.5, 0.05)
                .quantile(0.8, 0.01)
                .quantile(0.9, 0.01)
                .register(registry.getPrometheusRegistry());
    }

    @Bean
    Histogram getHistogram(){
        return Histogram.build().labelNames("uri", "accessType", "code")
                .name("api_cost_histogram").help("请求耗时histogram")
                .buckets(1,3,5,7,9,10).register(registry.getPrometheusRegistry());
    }

}
