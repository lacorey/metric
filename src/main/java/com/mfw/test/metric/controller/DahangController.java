package com.mfw.test.metric.controller;

import io.prometheus.client.Histogram;
import io.prometheus.client.Summary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class DahangController {
    @Autowired
    private Summary summary;

    @Autowired
    private Histogram histogram;

    @GetMapping(value = "/summary")
    public String test(){
        Random r = new Random();
        summary.labels("/summary","url","dahang").observe(r.nextDouble());
        return "ok";
    }

    @GetMapping(value = "/histogram")
    public String ok(){
        Random r = new Random();
        histogram.labels("/histogram","url","dahang").observe(r.nextDouble()*10);
        return "haha";
    }
}
