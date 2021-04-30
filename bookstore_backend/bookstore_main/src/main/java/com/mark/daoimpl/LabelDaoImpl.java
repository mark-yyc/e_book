package com.mark.daoimpl;

import com.mark.dao.LabelDao;
import com.mark.entity.Label;
import com.mark.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LabelDaoImpl implements LabelDao {
    @Autowired
    private LabelRepository labelRepository;

    @Override
    public List<Label> findByLabelName(String labelName) {
        return labelRepository.findByLabelName(labelName);
    }

    @Override
    public List<Integer> findBookIdByLabelNameList(List<String> labelNameList) {
        return labelRepository.findBookIdByLabelNameList(labelNameList);
    }
}
