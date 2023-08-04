package com.keyin.Entities;

import com.keyin.Entities.BinaryNode.BinaryNode;

import java.util.List;

public class TreeResponseDTO {
    private List<Integer> inputs;
    private BinaryNode tree;

    public List<Integer> getInputs() {
        return inputs;
    }

    public void setInputs(List<Integer> inputs) {
        this.inputs = inputs;
    }

    public BinaryNode getTree() {
        return tree;
    }

    public void setTree(BinaryNode tree) {
        this.tree = tree;
    }
}
