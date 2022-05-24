package com.example.preddiplom.service.scan;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScanServiceRunner implements ApplicationRunner {
    private final ScanService scanService;

    @Override
    public void run(ApplicationArguments applicationArguments) throws InterruptedException {
        while(true) {
            scanService.run();
            Thread.sleep(30000); // сканим
        }
    }

}
