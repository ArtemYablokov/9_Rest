package com.yabloko.repositories;

import com.yabloko.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token, Long> {
    Token findByValue(String value);
    boolean existsByValue(String value);
}