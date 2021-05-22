package com.mark.repository;

import com.google.gson.JsonObject;
import com.mark.entity.LabelNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LabelNodeRepository extends Neo4jRepository<LabelNode,Long> {
    @Query(value = "MATCH (label1:Label)<-[:RELATED]-(label2:Label{labelName:$labelName}),(label3:Label)<-[:RELATED]-(label:Label)<-[:RELATED]-(label2:Label{labelName:$labelName})RETURN [label1,label2,label3]")
    List<LabelNode> findRelated(@Param("labelName") String labelName);

    List<LabelNode> findByLabelName(String labelName);
}
