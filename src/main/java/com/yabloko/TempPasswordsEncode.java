package com.yabloko;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class TempPasswordsEncode {

    // НЕЛЬЗЯ ПРОСТО ВЗЯТЬ И В ЛЮБОМ МЕТОДЕ ПОЛУЧИТЬ ПРИВЗКУ СПРИНГА
    // НУЖНО ИСПОЛЬЗОВАТЬ ЗАПУСК ПРИЛОЖЕНИЯ
    @Autowired
    PasswordEncoder passwordEncoder;

    void encode() {
        String a = passwordEncoder.encode("a");
        System.out.println(a);
    }

    // как бы тест метод
    static void main(String[] args) {
        new TempPasswordsEncode().encode();
    }
}
