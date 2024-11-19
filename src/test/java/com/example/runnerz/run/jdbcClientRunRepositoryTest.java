package com.example.runnerz.run;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;


class InMemoryRepositoryTest {

    InMemoryRepository memory;

    @BeforeEach
    void setUp()
    {
        memory = new InMemoryRepository();
        memory.create(new Run(1,
                "Monday Morning Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(30, ChronoUnit.MINUTES),
                3,
                Location.INDOOR, null));

        memory.create(new Run(2,
                "Wednesday Evening Run",
                LocalDateTime.now(),
                LocalDateTime.now().plus(60, ChronoUnit.MINUTES),
                6,
                Location.INDOOR, null));

    }

    @Test
    void shouldFindsAll()
    {
        List<Run> runs = memory.findAll();
        assertEquals(2, runs.size(), "SHould Return 2 Runs");
    }
}
