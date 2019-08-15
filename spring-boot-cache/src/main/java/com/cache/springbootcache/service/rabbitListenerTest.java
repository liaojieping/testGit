package com.cache.springbootcache.service;

import com.cache.springbootcache.entity.Dep;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class rabbitListenerTest {
    /*@RabbitListener(queues = "nilingDep")  //此注解代表监听消息对列消息，当消息队列有消息时，运行此方法
    public void test1(Dep dep){
       System.out.print("收到消息"+dep.getDepartmentName());
    }*/
    //对象参数也可以时Message message，通过messgae.getBody来得到消息体的内容
}
