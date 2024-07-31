package com.mongotest.kitchen_sink.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.intellij.lang.annotations.Pattern;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "members")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    private ObjectId id;

    @NotNull
    private UUID memberId;

    private String migratedId;

    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String phoneNumber;

    public Member(String migratedId, String name, String email, String phoneNumber) {
        this.memberId = UUID.randomUUID();
        this.migratedId = migratedId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
}
