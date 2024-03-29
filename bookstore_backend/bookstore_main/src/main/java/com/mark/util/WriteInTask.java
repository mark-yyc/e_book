package com.mark.util;

import com.mark.BookstoreApplication;
import com.mark.entity.View;
import com.mark.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WriteInTask {
    @Autowired
    private ViewRepository viewRepository;

    @Scheduled(fixedDelay = 10*1000)
    void writeIn(){
        List<View> views=viewRepository.findAll();
        View view=null;
        if(views.size() != 0){
            view=views.get(0);
        }else{
            view=new View();
        }
        view.setViewAmount(BookstoreApplication.atomicAmount.getValue());
        viewRepository.save(view);
    }
}
