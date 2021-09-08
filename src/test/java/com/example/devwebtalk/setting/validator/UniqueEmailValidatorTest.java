package com.example.devwebtalk.setting.validator;

import com.example.devwebtalk.dto.UserModifyDto;
import com.example.devwebtalk.entity.User;
import com.example.devwebtalk.repository.UserRepository;
import org.hibernate.validator.internal.engine.ValidatorFactoryImpl;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.*;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class UniqueEmailValidatorTest {
    @Autowired
    UserRepository userRepository;
    Validator validator;


    @BeforeEach
    public void before() {
        setUpValidator();
        initDB();
    }

    public void setUpValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private void initDB() {
        User user = new User("a@devweb.com","111111");
        userRepository.save(user);
    }

    @Test
    @DisplayName("이메일 중복 테스트")
    void emailDuplication_Test() {
        UserModifyDto dto = new UserModifyDto();
        dto.setEmail("a@devweb.com");
        Set<ConstraintViolation<UserModifyDto>> validate = validator.validate(dto);
        for (ConstraintViolation<UserModifyDto> userModifyDtoConstraintViolation : validate) {
            System.out.println("userModifyDtoConstraintViolation = " + userModifyDtoConstraintViolation);
            
        }
    }

}