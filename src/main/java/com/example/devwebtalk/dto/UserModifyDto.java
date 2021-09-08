package com.example.devwebtalk.dto;

import com.example.devwebtalk.entity.SocialLogin;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.entity.type.SocialType;
import com.example.devwebtalk.setting.annotation.UniqueEmail;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class UserModifyDto {
    @NotBlank
    @UniqueEmail
    @Email(message = "이메일 형식을 지켜주세요.")
    private String email;
    @Pattern(regexp = "/^\\d{3}-\\d{3,4}-\\d{4}$/", message = "전화번호 형식을 정확히 입력해 주세요")
    private String phone;

    private String name;
    @Past
    private LocalDate birthday;

    private Map<SocialType, SocialLogin> socials = new HashMap<>();

    public UserModifyDto(User user, Map<SocialType, SocialLogin> socials) {
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.name = user.getName();
        this.birthday = user.getBirthday();
        this.socials = socials;
    }
}
