package com.epifanova.testtasksber;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class TestTaskSberApplicationTests {

  @Test
  void contextLoads() {
  }

}
