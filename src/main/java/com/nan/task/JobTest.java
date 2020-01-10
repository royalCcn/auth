package com.nan.task;
 
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
 
@Component
public class JobTest{
 
	@Scheduled(fixedRate = 10000)
    public void execute(){
        System.out.println("===========test===========");
    }
	
	//@Scheduled(cron = "4-40 * * * * ?")
    public void execute2(){
        System.out.println("========================test333");
    }
	
}