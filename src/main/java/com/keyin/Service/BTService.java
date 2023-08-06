package com.keyin.Service;

import com.keyin.Entities.BinaryNode.BinaryNode;
import com.keyin.Entities.BinaryNode.RestRepository.BinaryRestRepository;
import com.keyin.Entities.PreviousTreeResponseDTO;
import com.keyin.Entities.UserInput.RestRepo.UserInputRestRepository;
import com.keyin.Entities.UserInput.UserInput;
import com.keyin.Logger.CustomLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BTService {

    @Value("${app.debug}")
    private boolean debugMode;

    private final BinaryRestRepository binaryNodeRepository;
    private final UserInputRestRepository userInputRepository;

    public CustomLogger customLogger = new CustomLogger();

    @Autowired
    public BTService(BinaryRestRepository binaryNodeRepository, UserInputRestRepository userInputRepository) {
        this.binaryNodeRepository = binaryNodeRepository;
        this.userInputRepository = userInputRepository;
    }

    public BinaryNode constructBinarySearchTree(List<Integer> numbers) {

        try{
            BinaryNode root = null;
            for (int num : numbers) {
                root = insertNode(root, num);
            }

            if(debugMode){
                customLogger.LogAction("constructBinarySearchTree()");

            }
            return root;



        } catch (Error e){
            System.err.println(e);
            customLogger.LogErrorMessage("Failed To Construct Binary Search Tree FUNCTION:constructBinarySearchTree()");

        }


        return null;
    }



    private BinaryNode insertNode(BinaryNode root, int value) {

        try{
            if (root == null) {
                return new BinaryNode(value);
            }

            if (value < root.getValue()) {
                root.setLeft(insertNode(root.getLeft(), value));
            } else if (value > root.getValue()) {
                root.setRight(insertNode(root.getRight(), value));
            }

            if(debugMode){
                customLogger.LogAction("insertNode()");


            }


            return root;

        } catch(Error e){
            customLogger.LogErrorMessage("Failure to insert Node FUNCTION: insertNode() ");

        }


        return root;
    }

    public List<BinaryNode> getBinaryTreeNodes(BinaryNode root) {
        List<BinaryNode> nodes = new ArrayList<>();
        if (root != null) {
            nodes.add(root);
            nodes.addAll(getBinaryTreeNodes(root.getLeft()));
            nodes.addAll(getBinaryTreeNodes(root.getRight()));
        }

        if(debugMode){
            customLogger.LogAction("getBinaryTreeNodes()");


        }

        return nodes;
    }

    public void saveUserInputAndBinaryTree(List<Integer> numbers) {

        try {
            BinaryNode root = constructBinarySearchTree(numbers);
            UserInput userInput = new UserInput();
            userInput.setInputs(numbers);
            userInput = userInputRepository.save(userInput);

            List<BinaryNode> binaryTreeNodes = getBinaryTreeNodes(root);
            for (BinaryNode node : binaryTreeNodes) {
                node.setUserInput(userInput);
            }

            binaryNodeRepository.saveAll(binaryTreeNodes);

            if(debugMode){
                customLogger.LogAction("saveUserInputAndBinaryTree()");
            }

        } catch (Error e){
            customLogger.LogErrorMessage("Failure To Save User Input And Binary Tree METHOD: saveUserInputAndBinaryTree()");
        }

    }

    public List<PreviousTreeResponseDTO> getAllPreviousTrees() {

        try{

            if(debugMode){
                customLogger.LogAction("getAllPreviousTrees()");
            }

            List<UserInput> previousTrees = userInputRepository.findAll();

            return previousTrees.stream()

                    .map(userInput -> {
                        List<BinaryNode> binaryTreeNodes = binaryNodeRepository.findByUserInputId(userInput.getId());
                        PreviousTreeResponseDTO dto = new PreviousTreeResponseDTO();
                        dto.setId(userInput.getId());
                        dto.setInputs(userInput.getInputs());
                        dto.setTree(constructBinaryTreeFromNodes(binaryTreeNodes));

                        return dto;

                    })
                    .collect(Collectors.toList());


        } catch (Error e){
            customLogger.LogErrorMessage("Failure to get previous trees METHOD: getAllPreviousTrees()");
            e.printStackTrace();
            customLogger.LogErrorMessage(e.getMessage());

        }

        return null;
    }

    private BinaryNode constructBinaryTreeFromNodes(List<BinaryNode> nodes) {

        try{
            BinaryNode root = null;
            for (BinaryNode node : nodes) {
                root = insertNode(root, node.getValue());
            }

            if(debugMode){
                customLogger.LogAction("constructBinaryTreeFromNodes()");
            }

            return root;

        } catch(Error e){
            customLogger.LogErrorMessage("Failure to Construct Binary Tree From Nodes METHOD: constructBinaryTreeFromNodes()");
            customLogger.LogErrorMessage(e.getMessage());
            e.printStackTrace();


        }


        return null;
    }


}

