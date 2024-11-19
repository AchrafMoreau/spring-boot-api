package com.example.runnerz.run;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.asm.TypeReference;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;

@Component
public class RunJsonDataLoader implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(RunJsonDataLoader.class);
    private final jdbcClientRunRepository runerRepository;
    private final ObjectMapper objectMapper;

    public RunJsonDataLoader(jdbcClientRunRepository myrunreository, ObjectMapper myobjectMapper)
    {
        this.runerRepository = myrunreository;
        this.objectMapper = myobjectMapper;
    }


    @Override
    public void run(String... args) throws Exception {

        if(runerRepository.count() == 0){
            try(InputStream inputstream = TypeReference.class.getResourceAsStream("/data/runs.json")){
                if (inputstream == null) {
                    throw new RuntimeException("JSON file '/data/runs.json' not found");
                }
                Runs allRuns = objectMapper.readValue(inputstream, Runs.class);
                log.info("Reading {} runs from json file and saving to a database collection .", allRuns.runs().size());
                runerRepository.saveAll(allRuns.runs());
            }catch (IOException e){
                throw new RuntimeException("Failed to read json file", e);
            }
        }else{
            log.info("The Table is already filled with data");
        }
    }
}
