package com.mongotest.kitchen_sink.Exceptions;

/**
 * Member already exists exception
 */
public class MemberExistsWithEmailException extends Exception {
    public MemberExistsWithEmailException() {
        super("Member already exists with provided email");
    }
}
