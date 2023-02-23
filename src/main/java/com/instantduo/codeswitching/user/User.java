package com.instantduo.codeswitching.user;


import com.instantduo.codeswitching.common.type.Gender;
import com.instantduo.codeswitching.common.type.Grade;
import com.instantduo.codeswitching.common.type.Language;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String loginId;


    private String password;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Integer age;

    @Enumerated(value = EnumType.STRING)
    private Language language;

    @Enumerated(value = EnumType.STRING)
    private Grade grade;

    public User(String loginId, String password, Gender gender, Integer age, Language language, Grade grade) {
        this.loginId = loginId;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.language = language;
        this.grade = grade;
    }
}
