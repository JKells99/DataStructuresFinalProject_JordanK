package com.keyin.Entities.UserInput;

import com.keyin.Entities.BinaryNode.BinaryNode;

import javax.persistence.*;
import java.util.List;


@Entity
public class UserInput {


    @Id
    @SequenceGenerator(name = "inputs_sequence", sequenceName = "inputs_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "inputs_sequence")
    private Long id;


    @ElementCollection
    private List<Integer> inputs;

    @OneToOne(cascade = CascadeType.ALL)
    private BinaryNode rootNode;
    
    public BinaryNode getRootNode() {
        return rootNode;
    }

    public void setRootNode(BinaryNode rootNode) {
        this.rootNode = rootNode;
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

    @Override
    public String toString() {
        return "UserInput{" +
                "id=" + id +
                ", inputs=" + inputs +
                '}';
    }
}
