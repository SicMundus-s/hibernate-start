package org.example.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.convertor.BirthdayConvertor;
import org.example.type.JsonType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeRegistration;

import java.time.LocalDate;

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
    @Convert(converter = BirthdayConvertor.class)
    private Birthday birthDay;

 //   @Type(JsonType.class)
 //   private String info;
    @Enumerated(EnumType.STRING)
    private Role role;
}
