package com.keyin.Controller;
import com.keyin.Entities.BinaryNode.BinaryNode;
import com.keyin.Entities.PreviousTreeResponseDTO;
import com.keyin.Entities.TreeResponseDTO;
import com.keyin.Entities.UserInput.UserInput;

import com.keyin.Service.BTService;
import org.apache.catalina.User;
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
    public ResponseEntity<TreeResponseDTO> processNumbers(@RequestBody List<Integer> numbers) {
        BinaryNode root = btService.constructBinarySearchTree(numbers);

        UserInput userInput = new UserInput();
        userInput.setInputs(numbers);

        btService.saveUserInputAndBinaryTree(numbers);

        TreeResponseDTO responseDTO = new TreeResponseDTO();
        responseDTO.setInputs(numbers);
        responseDTO.setTree(root);
        return ResponseEntity.ok(responseDTO);
    }


    @GetMapping("/previous")
    public ResponseEntity<List<PreviousTreeResponseDTO>> getAllPreviousTrees() {
        List<PreviousTreeResponseDTO> previousTrees = btService.getAllPreviousTrees();
        return ResponseEntity.ok(previousTrees);
    }




//    @GetMapping("/previous-trees")
//    public String showPreviousTrees(Model model) {
//        // Retrieve all user inputs from the database
//        List<User> userInputList = userRepository.findAll();
//
//        // Create a list to hold the JSON representation of trees for each user input
//        List<String> treeJsonList = new ArrayList<>();
//
//        // Construct trees and add their JSON representation to the list
//        for (User userInput : userInputList) {
//            BinarySearchTree bst = new BinarySearchTree();
//            String[] numberArray = userInput.getUserInput().split("\\s+");
//            for (String number : numberArray) {
//                try {
//                    int value = Integer.parseInt(number);
//                    bst.insert(value);
//                } catch (NumberFormatException e) {
//                    // Handle invalid input (e.g., non-integer values)
//                }
//            }
//            String treeJson = bst.toJson();
//            treeJsonList.add(treeJson);
//        }
//
//        // Add the list of JSON representations of trees to the model
//        model.addAttribute("treeJsonList", treeJsonList);
//
//        return "previousTrees"; // This will return the "previousTrees.html" page from "templates" folder
//    }
}
