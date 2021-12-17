package com.mark.repository;

import com.google.gson.JsonObject;
import com.mark.entity.LabelNode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LabelNodeRepositoryTest {
    @Autowired
    LabelNodeRepository labelNodeRepository;

    @Autowired
    LabelRepository labelRepository;

    @Test
    void findRelated() {
//        List<LabelNode> results=labelNodeRepository.findRelated("科幻");
//        for (LabelNode jsonObject:results){
//            System.out.println(jsonObject.toString());
//        }
        List<String> labelNameList= Arrays.asList("参考","编程");
        List<Integer> results=labelRepository.findBookIdByLabelNameList(labelNameList);
        for(Integer result:results){
            System.out.println(result);
        }
    }
}