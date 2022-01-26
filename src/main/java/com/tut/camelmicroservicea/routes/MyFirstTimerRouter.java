package com.tut.camelmicroservicea.routes;

import com.tut.camelmicroservicea.beans.MyLoggerBeanProcessor;
import com.tut.camelmicroservicea.beans.MyTimeBean;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyFirstTimerRouter  extends RouteBuilder {

    @Autowired
    private MyTimeBean myTimeBean;

    @Autowired
    private MyLoggerBean myLoggerBean;

    @Override
    public void configure() throws Exception {
        // Structure:
        //
        // 1. queue: listen to a queue for an event
        // 2. transform or process:  the event's content when an event arrives in the queue
        // 3. database: persist in the db


        // for now le't use timer instead of queue and log instead of db
        // defined the endpoints. From which endpoint event comes and to which endpoint it goes
        // timer and log are keys. they are unique
        from("timer:first-timer")
                .bean(myTimeBean)
                .log("${body}")
                .bean(myLoggerBean)
                .log("${body}")
                .process(new MyLoggerBeanProcessor())
                .to("log:first-timer");
    }
}

// this is a processing example where we can process the data from it but we don't change it.
@Component
class MyLoggerBean {
    private Logger logger = LoggerFactory.getLogger(MyLoggerBean.class);

    public void  process(String message) {
        logger.info("MyLoggerBean {}", message);
    }
}