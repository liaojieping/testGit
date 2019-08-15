package com.cache.springbootcache;

import com.cache.springbootcache.entity.Dep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootCacheApplicationTests {
    @Autowired
    StringRedisTemplate stringRedisTemplate;  //key-value是字符串的
    @Autowired
    RedisTemplate redisTemplate;            //key-value都是对象的
    @Autowired
    RedisTemplate<Object, Dep> depRedisTemplate;  //配置后的redistemplate，将序列化数据转化为json
    @Autowired
    RabbitTemplate rabbitTemplate;    //操作消息队列
    @Autowired
    AmqpAdmin amqpAdmin;//此对象用于创建虚拟主机中的交换器，消息队列，以及绑定规则
    @Autowired
    JavaMailSenderImpl javaMailSender;  //Java邮件发送器
    @Test
    public void contextLoads() {
        stringRedisTemplate.opsForValue().append("myFirstRedis","haha");  //给redis保存数据
        String aa=stringRedisTemplate.opsForValue().get("myFirstRedis"); //在redist取出数据
        System.out.print(aa);
    }
    @Test
    public void test(){
     /*   Dep dep=new Dep(1,"IT事业部");
        redisTemplate.opsForValue().set("Dep-01",dep);  //向redis存入数据,注：存入对象需是可序列化的,此时在redis中，是已经序列化的，此时需要转换成json格式*/
          Dep dep=new Dep(1,"IT事业部");
        Dep dep1=new Dep(2,"IT财务部");
       /* depRedisTemplate.opsForValue().set("dep_01",dep);*/   //针对于字符串操作

        //针对于Set集合操作
        SetOperations<Object, Dep> set= depRedisTemplate.opsForSet();
        set.add("dep_02",dep);
        set.add("dep_02",dep1);
         Set<Dep> aa=depRedisTemplate.opsForSet().members("dep_02");
        for (Dep dep11: aa
             ) {
            System.out.print(dep11.getId()+"-----"+dep11.getDepartmentName()+"\n");
        }


    }
    @Test
    public  void test1(){
        //默认序列化发送过去(发送消息到消息队列)，若想转换成josn格式，需手配置，如此例子中的rabbitmaConfg配置的i
        rabbitTemplate.convertAndSend("liao.fanout","",new Dep(1,"这是消息队列测试部门12345"));
        //交换器列席为fanout的为全部定制，及绑定再转换器的消息队列都会收到消息，无锡routingkey
        //若是direct或者tipic类型则许需要加routingkey的值
    }
    @Test
    public  void test2(){
        //接受消息队列中的消息
        Dep o=(Dep)rabbitTemplate.receiveAndConvert("nilingDep");
        System.out.print("---------"+o.getDepartmentName());
    }
    @Test
    public void test3(){
         /*amqpAdmin.declareExchange(new DirectExchange("代码创建的交换器"));
         System.out.print("新建完成");//默认创建的一对一的，即direct类型的，消息队列中取值后就为空*/
         amqpAdmin.declareQueue(new Queue("代码创建的消息队列",true));//是持久化的
        System.out.print("创建消息队列完成");
        amqpAdmin.declareBinding(new Binding("代码创建的消息队列",Binding.DestinationType.QUEUE,"代码创建的交换器","amq",null));
        //参数分别为目的地，目的地类型，交换器，路由健，以及有无参数
    }

    //测试简单邮件发送
    @Test
    public void JavaMailSend(){
        SimpleMailMessage message= new SimpleMailMessage(); //创建简单邮件发送对象，通过邮件送器发送这个对象
        message.setSubject("关于今天回家信息");
        message.setText("今天17：00记得准时做班车回家");
        message.setTo("1191529148@qq.com");
        message.setFrom("563577802@qq.com");
       javaMailSender.send(message);
    }
    //测试复杂邮件发送，如附件
    @Test
    public void JavaMailSend1() {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper hepl = new MimeMessageHelper(mimeMessage, true);
            hepl.setSubject("关于今天回家信息");
            hepl.setText("<b style='color:red'>今天17：00记得准时做班车回家</b>",true);
            hepl.setTo("1191529148@qq.com");
            hepl.setFrom("563577802@qq.com");
            //上传文件
            hepl.addAttachment("测试邮件上传附件.jpg",new File("C:\\Users\\Dell\\Desktop\\住宿.jpg"));
            javaMailSender.send(mimeMessage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
