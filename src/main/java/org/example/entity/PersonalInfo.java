package org.example.entity;

import jakarta.persistence.Convert;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.convertor.BirthdayConverter;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class PersonalInfo {
    private String firstname;
    private String lastname;
    @Convert(converter = BirthdayConverter.class)
    private Birthday birthDay;
}
