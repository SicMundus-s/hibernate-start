package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.convertor.BirthdayConverter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    private String username;
    private String firstname;
    private String lastname;
    @Column(name = "birth_date")
    @Convert(converter = BirthdayConverter.class)
    private Birthday birthDay;

 //   @Type(JsonType.class)
 //   private String info;
    @Enumerated(EnumType.STRING)
    private Role role;
}
