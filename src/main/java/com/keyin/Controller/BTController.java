package com.keyin.Controller;
import com.keyin.Entities.BinaryNode.BinaryNode;
import com.keyin.Entities.PreviousTreeResponseDTO;
import com.keyin.Entities.TreeResponseDTO;
import com.keyin.Entities.UserInput.UserInput;

import com.keyin.Logger.CustomLogger;
import com.keyin.Service.BTService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BTController {
    @Value("${app.debug}")
    private boolean debugMode;

    private final BTService btService;
    public CustomLogger customLogger = new CustomLogger();


    @Autowired
    public BTController(BTService btService) {
        this.btService = btService;
    }

    @PostMapping("/process-numbers")
    public ResponseEntity<TreeResponseDTO> processNumbers(@RequestBody List<Integer> numbers) {

        try{
            BinaryNode root = btService.constructBinarySearchTree(numbers);

//        UserInput userInput = new UserInput();
//        userInput.setInputs(numbers);

            btService.saveUserInputAndBinaryTree(numbers);

            TreeResponseDTO responseDTO = new TreeResponseDTO();
            responseDTO.setInputs(numbers);
            responseDTO.setTree(root);
            if(debugMode){
                customLogger.LogAction("ROUTE: /process-numbers");

            }
            return ResponseEntity.ok(responseDTO);


        } catch(Error e){


            customLogger.LogErrorMessage("ROUTE: /process-numbers");
            e.printStackTrace();


        }


        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/previous")
    public ResponseEntity<List<PreviousTreeResponseDTO>> getAllPreviousTrees() {

        try{
            List<PreviousTreeResponseDTO> previousTrees = btService.getAllPreviousTrees();

            if(debugMode){
                customLogger.LogAction("ROUTE: /previous");


            }


            return ResponseEntity.ok(previousTrees);

        } catch(Error e){
            e.printStackTrace();
            customLogger.LogErrorMessage("ROUTE: /previous");

        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }




}
