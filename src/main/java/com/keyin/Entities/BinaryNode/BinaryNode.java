package com.keyin.Entities.BinaryNode;

import javax.persistence.*;


@Entity
public class BinaryNode {


    @Id
    @SequenceGenerator(name = "binarynode_sequence", sequenceName = "binarynode_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "binarynode_sequence")
    private Long id;
    private int value;

    @ManyToOne
    private BinaryNode left;

    @ManyToOne
    private BinaryNode right;
    private int height;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
