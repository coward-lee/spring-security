package org.example.domain;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private String id;
    private String username;
    private String password;
    private String time = LocalDateTime.now().toString();

}
