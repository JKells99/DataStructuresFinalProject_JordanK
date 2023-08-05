package com.keyin;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.keyin.Controller.BTController;
import com.keyin.Entities.BinaryNode.BinaryNode;
import com.keyin.Entities.BinaryNode.RestRepository.BinaryRestRepository;
import com.keyin.Entities.PreviousTreeResponseDTO;
import com.keyin.Entities.UserInput.RestRepo.UserInputRestRepository;
import com.keyin.Entities.UserInput.UserInput;
import com.keyin.Service.BTService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;


import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class BinaryTreeServiceTest {
    @Mock
    private BinaryRestRepository binaryNodeRepository;

    @Mock
    private UserInputRestRepository userInputRepository;

    @InjectMocks
    private BTService binaryTreeService;

    @Test
    public void testConstructBinarySearchTree() {
        List<Integer> numbers = Arrays.asList(50, 30, 70, 20, 40, 60, 80);
        BinaryNode root = binaryTreeService.constructBinarySearchTree(numbers);
        Assertions.assertEquals(50, root.getValue());

        Assertions.assertEquals(30, root.getLeft().getValue());
        Assertions.assertEquals(70, root.getRight().getValue());


        Assertions.assertEquals(40, root.getLeft().getRight().getValue());
        Assertions.assertEquals(20, root.getLeft().getLeft().getValue());


        Assertions.assertEquals(80, root.getRight().getRight().getValue());
        Assertions.assertEquals(60, root.getRight().getLeft().getValue());
    }

    @Test
    public void testGetPrevious(){
        UserInput userInput1 = new UserInput();
        userInput1.setId(1L);
        userInput1.setInputs(Arrays.asList(50, 30, 70, 20, 40, 60, 80));

        UserInput userInput2 = new UserInput();
        userInput2.setId(2L);
        userInput2.setInputs(Arrays.asList(98, 44, 66, 34, 5));

        List<BinaryNode> binaryTree1 = createBinaryTreeFromInputs(userInput1.getInputs());
        List<BinaryNode> binaryTree2 = createBinaryTreeFromInputs(userInput2.getInputs());



        when(userInputRepository.findAll()).thenReturn(Arrays.asList(userInput1, userInput2));

        when(binaryNodeRepository.findByUserInputId(1L)).thenReturn(binaryTree1);
        when(binaryNodeRepository.findByUserInputId(2L)).thenReturn(binaryTree2);

        List<PreviousTreeResponseDTO> responseDTOList = binaryTreeService.getAllPreviousTrees();

        System.out.println(responseDTOList.toString());






    }





    private List<BinaryNode> createBinaryTreeFromInputs(List<Integer> inputs) {
        // Use the BTService class's constructBinarySearchTree() method to create the binary tree
        BTService binaryTreeService = new BTService(binaryNodeRepository, userInputRepository);
        BinaryNode root = binaryTreeService.constructBinarySearchTree(inputs);

        // Use the BTService class's getBinaryTreeNodes() method to get a list of BinaryNode objects
        return binaryTreeService.getBinaryTreeNodes(root);
    }










}


