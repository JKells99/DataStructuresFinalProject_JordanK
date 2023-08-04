package com.keyin.Controller;
import com.keyin.Entities.BinaryNode.BinaryNode;
import com.keyin.Entities.UserInput.UserInput;

import com.keyin.Service.BTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BTController {

    private final BTService btService;

    @Autowired
    public BTController(BTService btService) {
        this.btService = btService;
    }

    @PostMapping("/process-numbers")
    public ResponseEntity<Map<String, Object>> processNumbers(@RequestBody List<Integer> numbers) {
        BinaryNode root = btService.constructBinarySearchTree(numbers);

        UserInput userInput = new UserInput();
        userInput.setInputs(numbers);

        // Save user input and binary tree nodes to the database
        btService.saveUserInputAndBinaryTree(numbers);

        Map<String, Object> response = new HashMap<>();
        response.put("tree", root);
        return ResponseEntity.ok(response);
    }

    // Add other routes and methods as needed
}
