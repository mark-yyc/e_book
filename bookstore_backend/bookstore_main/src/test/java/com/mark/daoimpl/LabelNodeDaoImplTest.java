package com.mark.daoimpl;

import com.mark.dao.LabelNodeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LabelNodeDaoImplTest {
    @Autowired
    private LabelNodeDao labelNodeDao;

    @Test
    void saveNode() {
        labelNodeDao.saveNode("小说");
        assertTrue(true);
    }
}