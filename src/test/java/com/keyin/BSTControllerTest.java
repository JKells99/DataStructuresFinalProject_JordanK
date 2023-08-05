package com.keyin;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.keyin.Entities.BinaryNode.BinaryNode;
import com.keyin.Entities.BinaryNode.RestRepository.BinaryRestRepository;
import com.keyin.Service.BTService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BSTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BTService binaryTreeService;

    @Mock
    private BinaryRestRepository binaryNodeRepository;

    @Test
    public void testProcessNumbers() throws Exception {
        List<Integer> numbers = Arrays.asList(50, 30, 70, 20, 40, 60, 80);
        ObjectMapper objectMapper = new ObjectMapper();
        String numbersJson = objectMapper.writeValueAsString(numbers);

        // Mock the binaryTreeService.constructBinarySearchTree method to return a valid BinaryNode
        BinaryNode root = new BinaryNode(50); // Replace 50 with the appropriate value
        BinaryNode root2 = new BinaryNode(30); // Replace 50 with the appropriate value
        BinaryNode root3 = new BinaryNode(70); // Replace 50 with the appropriate value


        root.setLeft(root2);
        root.setRight(root3);



        when(binaryTreeService.constructBinarySearchTree(numbers)).thenReturn(root);

        mockMvc.perform(post("/process-numbers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(numbersJson))
                .andExpect(MockMvcResultMatchers.jsonPath(".tree").exists())
                .andExpect(MockMvcResultMatchers.jsonPath(".left.value").value(root.getLeft().getValue()))
                .andExpect(MockMvcResultMatchers.jsonPath(".right.value").value(root.getRight().getValue()))
                .andExpect(status().isOk());
    }

}
