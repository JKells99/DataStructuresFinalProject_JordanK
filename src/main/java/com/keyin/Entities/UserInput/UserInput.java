package com.keyin.Entities.UserInput;

import com.keyin.Entities.BinaryNode.BinaryNode;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "user_input")
public class UserInput {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "user_input_numbers", joinColumns = @JoinColumn(name = "user_input_id"))
    private List<Integer> inputs;

    // Add any additional fields or methods as needed

    public UserInput() {
        // Default constructor required by JPA
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