package com.keyin.Entities;

import com.keyin.Entities.BinaryNode.BinaryNode;

import java.util.List;

public class PreviousTreeResponseDTO {

    private Long id;
    private List<Integer> inputs;
    private BinaryNode tree;

    public BinaryNode getTree() {
        return tree;
    }

    public void setTree(BinaryNode tree) {
        this.tree = tree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getInputs() {
        return inputs;
    }

    public void setInputs(List<Integer> inputs) {
        this.inputs = inputs;
    }


}
