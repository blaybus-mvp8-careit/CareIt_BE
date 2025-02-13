package com.example.careit.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class AppConfig {
    static {
        Dotenv dotenv = Dotenv.load(); // .env 파일 로드
        System.setProperty("REDIS_HOST", Objects.requireNonNull(dotenv.get("REDIS_HOST")));
        System.setProperty("REDIS_PORT", Objects.requireNonNull(dotenv.get("REDIS_PORT")));
    }
}
