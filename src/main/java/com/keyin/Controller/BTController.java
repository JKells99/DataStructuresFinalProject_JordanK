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

@CrossOrigin(origins = "*", allowedHeaders = "*")
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

//        UserInput userInput = new UserInput();
//        userInput.setInputs(numbers);

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




}
