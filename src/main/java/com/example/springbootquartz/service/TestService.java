package com.example.springbootquartz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;
import java.util.StringJoiner;

@Slf4j
@Service
public class TestService {

    public void run(String id) throws Exception {
        StringJoiner joiner = new StringJoiner(" ")
                .add("id: ")
                .add(id)
                .add("time: ")
                .add(LocalDateTime.now().toString());
        log.info(joiner.toString());
    }

}
