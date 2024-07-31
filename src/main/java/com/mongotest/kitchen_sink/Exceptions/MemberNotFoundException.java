package com.mongotest.kitchen_sink.Exceptions;

/**
 * Member not found exception
 */
public class MemberNotFoundException extends RuntimeException {
    public MemberNotFoundException(String memberId) {
        super("Member not found with member id " + memberId);
    }
}
