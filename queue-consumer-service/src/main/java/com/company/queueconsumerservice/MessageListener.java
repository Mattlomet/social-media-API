package com.company.queueconsumerservice;


import com.company.queueconsumerservice.util.feign.AccountFeign;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageListener {

    @Autowired
    AccountFeign accountFeign;

    @RabbitListener(queues = QueueConsumerServiceApplication.QUEUE_NAME)
    public void deleteAccount(int id){
        accountFeign.deleteAccountById(id);
        System.out.println("deleting account by id - " + id);
    }

}
