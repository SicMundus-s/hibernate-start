package org.example.entity.EmbededId;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.PersonalInfo;

import java.io.Serial;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/**
 * Просто как пример EmbeddedId
 */
public class UserId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String username;

    private PersonalInfo personalInfo;
}
