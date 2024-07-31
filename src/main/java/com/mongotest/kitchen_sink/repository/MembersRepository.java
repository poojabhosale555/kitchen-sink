package com.mongotest.kitchen_sink.repository;


import com.mongotest.kitchen_sink.model.Member;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembersRepository extends MongoRepository<Member, ObjectId> {

    Optional<Member> findByMemberId(String memberId);

    Optional<Member> findByMigratedId(String migratedId);

    Optional<Member> findByEmail(String email);


}
