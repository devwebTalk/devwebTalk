package com.example.devwebtalk.entity;

import com.example.devwebtalk.dto.UserModifyDto;
import com.example.devwebtalk.entity.type.SocialType;
import com.example.devwebtalk.setting.converter.CryptoConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.TABLE;

/**
 * Created by Kim Young Long.
 * My Git Blog : https://kha0213.github.io/
 * Date: 2021-07-10
 * Time: 오후 3:29
 */
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter @Setter
@Table(name = "USERS")
@TableGenerator(
        name = "USER_SEQ_GENERATOR",
        table = "TB_SEQUENCES",
        pkColumnValue = "USER_SEQ",
        allocationSize = 1) //TODO 운영엔 50으로 하자
@Entity
public class User extends BaseEntity {
    @Id @GeneratedValue(strategy = TABLE, generator = "USER_SEQ_GENERATOR")
    @Column(name = "USER_ID")
    private Long id;

    private String phone;
    @Column(unique = true)
    @Convert(converter = CryptoConverter.class)
    private String email;

    private String name;

    private LocalDate birthday;
    @Convert(converter = CryptoConverter.class)
    private String pw;

    @OneToMany(mappedBy = "user")
    @Enumerated(value = EnumType.STRING)
    @MapKeyEnumerated(value = STRING)
    @ToString.Exclude
    private Map<SocialType, SocialLogin> socials = new HashMap<>();

    @ToString.Exclude
    @ManyToMany(mappedBy = "users")
    private List<ChattingRoom> rooms = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private List<FriendsGroup> friendsGroups = new ArrayList<>();

    /**
     * 생성자 함수
     */
    public User(String name) {
        this.name = name;
    }

    public User(String name, LocalDate birthday) {
        this(name, null, birthday);
    }

    public User(String name, String email, LocalDate birthday) {
        this(name, null, email, birthday);
    }

    public User(String email, String pw) {
        this.email = email;
        this.pw = pw;
    }

    public User(String name, String phone, String email, LocalDate birthday) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.birthday = birthday;
    }

    public User(String name, String phone, String email, LocalDate birthday, String pw) {
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.birthday = birthday;
        this.pw = pw;
    }

    /** 비즈니스 메서드 */
    public void updateField(UserModifyDto user) {
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.phone = user.getPhone();
    }
}
