package com.mark.daoimpl;

import com.mark.dao.LabelNodeDao;
import com.mark.entity.Label;
import com.mark.entity.LabelNode;
import com.mark.repository.LabelNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LabelNodeDaoImpl implements LabelNodeDao {
    @Autowired
    LabelNodeRepository labelNodeRepository;

    @Override
    public void saveNode(String labelName) {
        LabelNode labelNode=new LabelNode();
        labelNode.setLabelName(labelName);
        labelNodeRepository.save(labelNode);
    }

    @Override
    public List<LabelNode> findRelated(String labelName) {
        if(labelNodeRepository.findByLabelName(labelName)!=null&&labelNodeRepository.findByLabelName(labelName).size()!=0){
            return labelNodeRepository.findRelated(labelName);
        }else{
            return null;
        }

    }

}
