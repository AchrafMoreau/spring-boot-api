package com.example.runnerz.run;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;

import java.time.LocalDateTime;

public record Run(
        @Id
        Integer id,
        String title,
        LocalDateTime startOn,
        LocalDateTime completeOn,
        Integer miles,
        Location location,
        @Version
        Integer version
) {
}
