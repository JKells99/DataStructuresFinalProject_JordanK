package com.keyin.Service;

import com.keyin.Entities.BinaryNode.BinaryNode;
import com.keyin.Entities.BinaryNode.RestRepository.BinaryRestRepository;
import com.keyin.Entities.UserInput.RestRepo.UserInputRestRepository;
import com.keyin.Entities.UserInput.UserInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
        userInputRepository.save(userInput);
        binaryNodeRepository.saveAll(getBinaryTreeNodes(root));
    }
}
