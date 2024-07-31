package com.mongotest.kitchen_sink.service;

import com.mongotest.kitchen_sink.api.v1.MembersController;
import com.mongotest.kitchen_sink.model.Member;
import com.mongotest.kitchen_sink.repository.MembersRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembersService {

    Logger logger = LoggerFactory.getLogger(MembersService.class);

    @Autowired
    private MembersRepository membersRepository;
    public List<Member> findAllOrderedByName() {
        logger.info("Retrieve member record sorted with name");
        return membersRepository.findAll(Sort.by(Sort.Direction.ASC, "name"));
    }

    public Optional<Member> findById(String memberId) {
        logger.info("Retrieve member record with member id");
        return membersRepository.findByMemberId(memberId);
    }

    public Optional<Member> findByMigratedId(String id) {
        logger.info("Retrieve member record with migrated id");
        return membersRepository.findByMemberId(id);
    }

    public Optional<Member> findByEmail(String email) {
        logger.info("Retrieve member record with email");
        return membersRepository.findByEmail(email);
    }

    public Member createMember(String migratedId, String name, String email, String phoneNumber) {
        if (!findById(migratedId).isPresent()) {
            return membersRepository.insert(new Member(migratedId, name, email, phoneNumber));
        }
        return null;
    }
}
