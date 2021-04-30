package com.mark.repository;

import com.mark.entity.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label,Integer> {
    List<Label> findByLabelName(String labelName);

    @Query(value = "select distinct l.bookId from Label l where l.labelName in :labelNameList")
    List<Integer> findBookIdByLabelNameList(@Param("labelNameList")List<String> labelNameList);
}
