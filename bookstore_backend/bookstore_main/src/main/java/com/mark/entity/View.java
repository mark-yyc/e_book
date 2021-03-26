package com.mark.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document("view")
public class View {
    private Integer viewAmount;

    @CreatedDate
    private Instant createTime;

    @LastModifiedDate
    private Instant updateTime;

}
