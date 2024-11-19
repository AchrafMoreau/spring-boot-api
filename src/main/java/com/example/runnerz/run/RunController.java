package com.example.runnerz.run;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/runs")
public class RunController {

    private final RunRepository runrepository;

    public RunController(RunRepository myrunrepository){
        this.runrepository = myrunrepository;
    }


    @GetMapping("/")
    List<Run> findAll()
    {
        return runrepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Run> findbyId(@PathVariable Integer id)
    {
        return runrepository.findById(id);
    }

    @PostMapping("/")
    void store(@RequestBody Run run)
    {
        runrepository.create(run);
    }

    @PutMapping("/{id}")
    void update(@RequestBody Run run, @PathVariable Integer id)
    {
        runrepository.update(run, id);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id)
    {
        runrepository.delete(id);
    }

    @GetMapping("/location/{location}")
    List<Run> findByLocation(@PathVariable String location)
    {
        return runrepository.findByLocation(location);
    }
//    @GetMapping("/{id}")
//    Optional<Run> findById(@PathVariable Integer id)
//    {
//        Optional<Run> myrun = runrepository.findById(id);
//        if(myrun.isEmpty()){
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
////            throw new ErrorResponseException(HttpStatusCode.valueOf(404));
//        }
//        return myrun;
//    }
//
//    @PostMapping("/")
//    String StoreRun(@RequestBody Run run)
//    {
//        return runrepository.store(run);
//    }
//
//    @PutMapping("/{id}")
//    String updateRun(@RequestBody Run run, @PathVariable Integer id)
//    {
//        return runrepository.update(run, id);
//    }
//
//    @DeleteMapping("/{id}")
//    String deleteRun(@PathVariable Integer id)
//    {
//        return runrepository.delete(id);
//    }
}
