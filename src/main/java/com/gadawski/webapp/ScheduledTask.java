package com.gadawski.webapp;

import com.gadawski.drools.DroolsApp;
import com.gadawski.drools.PersonPresence;
import com.gadawski.drools.RuleDate;
import com.google.common.collect.Lists;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@ComponentScan(value = "com.gadawski")
@Component
public class ScheduledTask {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private DroolsApp droolsApp;

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Scheduled(fixedRate = 900000)
    public void reportCurrentTime() {
        List<Object> data = Lists.newArrayList();

        LocalDateTime now = LocalDateTime.now();
        RuleDate ruleDate = new RuleDate(now.getDayOfWeek(), now.getHour());

        data.add(ruleDate);

        if (userService.isLgPresent()) {
            data.add(new PersonPresence());
        } else {
            log.info("Still nobody lg not present.");
            return;
        }

        droolsApp.bootstrapDrools(data);

        log.info("The time is now {}", dateFormat.format(new Date()));
    }
}