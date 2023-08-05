package com.keyin.Service;

import com.keyin.Entities.BinaryNode.BinaryNode;
import com.keyin.Entities.BinaryNode.RestRepository.BinaryRestRepository;
import com.keyin.Entities.PreviousTreeResponseDTO;
import com.keyin.Entities.UserInput.RestRepo.UserInputRestRepository;
import com.keyin.Entities.UserInput.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BTService {

    private final BinaryRestRepository binaryNodeRepository;
    private final UserInputRestRepository userInputRepository;

    @Autowired
    public BTService(BinaryRestRepository binaryNodeRepository, UserInputRestRepository userInputRepository) {
        this.binaryNodeRepository = binaryNodeRepository;
        this.userInputRepository = userInputRepository;
    }

    public BinaryNode constructBinarySearchTree(List<Integer> numbers) {
        BinaryNode root = null;
        for (int num : numbers) {
            root = insertNode(root, num);
        }
        return root;
    }



    private BinaryNode insertNode(BinaryNode root, int value) {
        if (root == null) {
            return new BinaryNode(value);
        }

        if (value < root.getValue()) {
            root.setLeft(insertNode(root.getLeft(), value));
        } else if (value > root.getValue()) {
            root.setRight(insertNode(root.getRight(), value));
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
        return nodes;
    }

    public void saveUserInputAndBinaryTree(List<Integer> numbers) {
        BinaryNode root = constructBinarySearchTree(numbers);
        UserInput userInput = new UserInput();
        userInput.setInputs(numbers);
        userInput = userInputRepository.save(userInput);

        List<BinaryNode> binaryTreeNodes = getBinaryTreeNodes(root);
        for (BinaryNode node : binaryTreeNodes) {
            node.setUserInput(userInput);
        }

        binaryNodeRepository.saveAll(binaryTreeNodes);
    }

    public List<PreviousTreeResponseDTO> getAllPreviousTrees() {
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
    }

    private BinaryNode constructBinaryTreeFromNodes(List<BinaryNode> nodes) {
        BinaryNode root = null;
        for (BinaryNode node : nodes) {
            root = insertNode(root, node.getValue());
        }
        return root;
    }


}

