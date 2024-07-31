package com.mongotest.kitchen_sink.api.v1;

import com.mongotest.kitchen_sink.model.Member;
import com.mongotest.kitchen_sink.service.MembersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MembersControllerTest {

    @Mock
    private MembersService membersService;

    @InjectMocks
    private MembersController membersController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(membersController).build();
    }

    @Test
    void getMembers() throws Exception {
        List<Member> members = Arrays.asList(new Member("1", "John Doe", "john.doe@example.com", "1234567890"));
        when(membersService.findAllOrderedByName()).thenReturn(members);

        mockMvc.perform(get("/api/v1/members/list"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].memberId").isNotEmpty())
                .andExpect(jsonPath("$[0].migratedId").value("1"))
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[0].email").value("john.doe@example.com"))
                .andExpect(jsonPath("$[0].phoneNumber").value("1234567890"));

        verify(membersService, times(1)).findAllOrderedByName();
    }

    @Test
    void getMemberById() throws Exception {
        Optional<Member> member = Optional.of(new Member("1", "John Doe", "john.doe@example.com", "1234567890"));

        String generatedMemberId = String.valueOf(member.get().getMemberId());

        when(membersService.findById(generatedMemberId)).thenReturn(member);

        String uri = "/api/v1/members/" + generatedMemberId;

        mockMvc.perform(get(uri))
                 .andExpect(status().isOk())
                .andExpect(jsonPath("$.memberId").isNotEmpty())
                .andExpect(jsonPath("$.memberId").value(generatedMemberId))
                .andExpect(jsonPath("$.migratedId").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"));


        verify(membersService, times(2)).findById("1");
    }

    @Test
    void createMember() throws Exception {
        Member member = new Member("1", "John Doe", "john.doe@example.com", "1234567890");
        when(membersService.createMember(anyString(), anyString(), anyString(), anyString())).thenReturn(member);

        mockMvc.perform(post("/api/v1/members/create")
                        .contentType("application/json")
                        .content("{\"migratedId\":\"1\", \"name\":\"John Doe\", \"email\":\"john.doe@example.com\", \"phoneNumber\":\"1234567890\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.migratedId").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john.doe@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("1234567890"));

        verify(membersService, times(1)).createMember("1", "John Doe", "john.doe@example.com", "1234567890");
    }
}