package com.keyin.Entities.BinaryNode.RestRepository;

import com.keyin.Entities.BinaryNode.BinaryNode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BinaryRestRepository extends JpaRepository<BinaryNode,Long> {
    List<BinaryNode> findByUserInputId(Long userInputId);


}
