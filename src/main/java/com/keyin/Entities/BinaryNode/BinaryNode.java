package com.keyin.Entities.BinaryNode;

import com.keyin.Entities.UserInput.UserInput;

import javax.persistence.*;



import javax.persistence.*;

@Entity
@Table(name = "binary_node")
public class BinaryNode {
    @Id
    @SequenceGenerator(name = "node_sequence", sequenceName = "node_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "node_sequence")
    private Long id;

    private int value;
    @ManyToOne
    private UserInput userInput;

    @ManyToOne
    @JoinColumn(name = "left_child_id")
    private BinaryNode left;

    @ManyToOne
    @JoinColumn(name = "right_child_id")
    private BinaryNode right;


    public BinaryNode() {
    }

    public BinaryNode(int value) {
        this.value = value;
    }


    public UserInput getUserInput() {
        return userInput;
    }

    public void setUserInput(UserInput userInput) {
        this.userInput = userInput;
    }

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

