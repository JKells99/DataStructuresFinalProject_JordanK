package com.keyin.Entities.BinaryNode;

import javax.persistence.*;



import javax.persistence.*;

@Entity
@Table(name = "binary_node")
public class BinaryNode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int value;

    @ManyToOne
    @JoinColumn(name = "left_child_id")
    private BinaryNode left;

    @ManyToOne
    @JoinColumn(name = "right_child_id")
    private BinaryNode right;

    // Add any additional fields or methods as needed

    public BinaryNode() {
        // Default constructor required by JPA
    }

    public BinaryNode(int value) {
        this.value = value;
    }

    // Getters and setters for id, value, left, and right nodes
    // Add any additional methods as needed

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
}

