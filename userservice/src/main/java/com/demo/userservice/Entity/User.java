package com.demo.userservice.Entity;

import com.demo.userservice.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")  // MongoDB collection name
public class User {

    @Id
    private String id; // MongoDB ObjectId as String

    private String name;

    private String email;  // unique constraint is optional in MongoDB; can create index if needed

    private String password;

    private Role role; // Enum works fine as String; MongoDB stores it as a string by default

    private LocalDateTime createdAt;
}