package com.mark.dao;

import com.mark.entity.Label;

import java.util.List;

public interface LabelDao {
    List<Label> findByLabelName(String labelName);

    List<Integer> findBookIdByLabelNameList(List<String> labelNameList);
}
