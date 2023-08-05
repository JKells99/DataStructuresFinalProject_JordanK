package com.keyin.Entities.UserInput;

import com.keyin.Entities.BinaryNode.BinaryNode;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user_input")
public class UserInput {
    @Id
    @SequenceGenerator(name = "input_sequence", sequenceName = "input_sequence", allocationSize = 1, initialValue=1)
    @GeneratedValue(generator = "input_sequence")
    private Long id;

    @ElementCollection
    @CollectionTable(name = "user_input_numbers", joinColumns = @JoinColumn(name = "user_input_id"))
    private List<Integer> inputs;

    @OneToOne(cascade = CascadeType.ALL)
    private BinaryNode rootNode;

    public UserInput() {
    }

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
}