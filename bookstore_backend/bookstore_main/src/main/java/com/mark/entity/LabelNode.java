package com.mark.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.*;

import java.util.List;

@Data
@Node("Label")
public class LabelNode {
    @Id
    @GeneratedValue
    private Long labelNodeId;
    @Property("labelName")
    private String labelName;

    @Relationship(type="RELATED",direction = Relationship.Direction.OUTGOING)
    private List<LabelNode> relatedLabelNode;

    public LabelNode(){}

    public LabelNode(Long labelNodeId, String labelName) {
        this.labelNodeId = labelNodeId;
        this.labelName = labelName;
    }
}
