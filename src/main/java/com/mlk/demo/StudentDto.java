package com.mlk.demo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private String firstname;
    private String lastname;
    private String email;
}