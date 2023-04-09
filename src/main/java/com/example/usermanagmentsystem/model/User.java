package com.example.usermanagmentsystem.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level=AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    Long id;
    String name;
    String email;
    String country;
}
