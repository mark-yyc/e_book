package com.mark.dao;

import com.mark.entity.LabelNode;

import java.util.List;

public interface LabelNodeDao {
    void saveNode(String labelName);

    List<LabelNode> findRelated(String labelName);
}
