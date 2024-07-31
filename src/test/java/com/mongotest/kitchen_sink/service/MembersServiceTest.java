package com.mongotest.kitchen_sink.service;

import com.mongotest.kitchen_sink.model.Member;
import com.mongotest.kitchen_sink.repository.MembersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MembersServiceTest {

    @Mock
    private MembersRepository membersRepository;

    @InjectMocks
    private MembersService membersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllOrderedByName() {
        Member member1 = new Member("1", "Alice", "alice@example.com", "1234567890");
        Member member2 = new Member("2", "Bob", "bob@example.com", "0987654321");
        when(membersRepository.findAll(Sort.by(Sort.Direction.ASC, "name")))
                .thenReturn(Arrays.asList(member1, member2));

        List<Member> members = membersService.findAllOrderedByName();
        assertNotNull(members);
        assertEquals(2, members.size());
        assertEquals("Alice", members.get(0).getName());
        assertEquals("Bob", members.get(1).getName());
    }

    @Test
    void testFindById() {
        Member member = new Member("1", "Alice", "alice@example.com", "1234567890");
        when(membersRepository.findByMemberId("1")).thenReturn(Optional.of(member));

        Optional<Member> foundMember = membersService.findById("1");
        assertTrue(foundMember.isPresent());
        assertEquals("Alice", foundMember.get().getName());
    }

    @Test
    void testFindByMigratedId() {
        Member member = new Member("1", "Alice", "alice@example.com", "1234567890");
        when(membersRepository.findByMemberId("1")).thenReturn(Optional.of(member));

        Optional<Member> foundMember = membersService.findByMigratedId("1");
        assertTrue(foundMember.isPresent());
        assertEquals("Alice", foundMember.get().getName());
    }

    @Test
    void testFindByEmail() {
        Member member = new Member("1", "Alice", "alice@example.com", "1234567890");
        when(membersRepository.findByEmail("alice@example.com")).thenReturn(Optional.of(member));

        Optional<Member> foundMember = membersService.findByEmail("alice@example.com");
        assertTrue(foundMember.isPresent());
        assertEquals("Alice", foundMember.get().getName());
    }

    @Test
    void testCreateMemberWhenNotExists() {
        when(membersRepository.findByMemberId("1")).thenReturn(Optional.empty());
        Member member = new Member("1", "Alice", "alice@example.com", "1234567890");
        when(membersRepository.insert(any(Member.class))).thenReturn(member);

        Member createdMember = membersService.createMember("1", "Alice", "alice@example.com", "1234567890");
        assertNotNull(createdMember);
        assertEquals("Alice", createdMember.getName());
    }

    @Test
    void testCreateMemberWhenExists() {
        Member member = new Member("1", "Alice", "alice@example.com", "1234567890");
        when(membersRepository.findByMemberId("1")).thenReturn(Optional.of(member));

        Member createdMember = membersService.createMember("1", "Alice", "alice@example.com", "1234567890");
        assertNull(createdMember);
    }
}