package com.transactional.systemactivemq;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Setter
    @Getter
    @Id
    private String id;
    private String name;
    private String email;

}
