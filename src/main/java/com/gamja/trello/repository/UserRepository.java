package com.gamja.trello.repository;

import com.gamja.trello.common.exception.CustomException;
import com.gamja.trello.common.exception.ErrorCode;
import com.gamja.trello.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    default User findUserByEmail(String email) {
        return findByEmail(email).orElseThrow(
                () -> new CustomException(ErrorCode.USERNAME_NOT_FOUND)
        );
    }

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
}
