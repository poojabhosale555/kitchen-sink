package com.mongotest.kitchen_sink.api.v1;

import com.mongotest.kitchen_sink.Exceptions.MemberExistsWithEmailException;
import com.mongotest.kitchen_sink.Exceptions.MemberNotFoundException;
import com.mongotest.kitchen_sink.model.Member;
import com.mongotest.kitchen_sink.service.MembersService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/v1/members")
public class MembersController {
    Logger logger = LoggerFactory.getLogger(MembersController.class);

    @Autowired
    private MembersService membersService;

    @GetMapping("/list")
    public ResponseEntity<List<Member>> getMembers() {
        logger.info("Retrieving all members list");
        return new ResponseEntity<>(membersService.findAllOrderedByName(), HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<?> getMemberById(@PathVariable String memberId) {
        logger.info("Retrieving member by member id");

        Optional<Member> member = membersService.findById(memberId);

        if (!member.isPresent()) {
            logger.info("Member not found for given member id " + memberId);
            throw new MemberNotFoundException(memberId);
        }

       return new ResponseEntity<>(member, HttpStatus.OK);
    }

    /*@PostMapping("/create")
    public ResponseEntity<?> createMember(@RequestBody Map<String, String> payload) throws MemberExistsWithEmailException {
        logger.info("Creating member record");

        Optional<Member> member = membersService.findByEmail(payload.get("email"));

        if (member.isPresent()) {
            logger.info("Member already exists with provided email");
            throw new MemberExistsWithEmailException();
        }

        return new ResponseEntity<Member>(membersService.createMember(payload.get("migratedId"), payload.get("name"), payload.get("email"), payload.get("phoneNumber")), HttpStatus.CREATED);
    }*/

    @PostMapping("/create")
    public ResponseEntity<?> createMember(@Valid @RequestBody com.mongotest.kitchen_sink.bean.Member memberForm) throws MemberExistsWithEmailException {
        logger.info("Creating member record");

        Optional<Member> member = membersService.findByEmail(memberForm.getEmail());

        if (member.isPresent()) {
            logger.info("Member already exists with provided email");
            throw new MemberExistsWithEmailException();
        }

        return new ResponseEntity<Member>(membersService.createMember(
                memberForm.getMigratedId(),
                memberForm.getName(),
                memberForm.getEmail(),
                memberForm.getPhoneNumber()
        ), HttpStatus.CREATED);
    }

}
