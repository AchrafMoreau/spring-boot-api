package com.example.runnerz.run;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;

@Repository
public class jdbcClientRunRepository {

    private List<Run> runs = new ArrayList<Run>();
    private static final Logger log = LoggerFactory.getLogger(jdbcClientRunRepository.class);

    private final JdbcClient myjdbcClient;

    public jdbcClientRunRepository(JdbcClient jdbcClient){
        this.myjdbcClient = jdbcClient;
    }

    public List<Run> findAll(){
        return myjdbcClient.sql("select * from RUN")
                .query(Run.class)
                .list();
    }

    public Optional<Run> findbyId(Integer id)
    {
        return myjdbcClient.sql("SELECT id, title, start_on, complete_on, miles, location FROM RUN WHERE id = :id")
                .param("id", id)
                .query(Run.class)
                .optional();
    }

    public void create(Run run)
    {
        var update = myjdbcClient.sql("INSERT INTO RUN(id, title, start_on, complete_on, miles, location) VALUES(?,?,?,?,?,?)")
                .params(List.of(run.id(), run.title(), run.startOn(), run.completeOn(), run.miles(), run.location().toString()))
                .update();
        Assert.state(update == 1 , "Failed To Create run " + run.title());
    }

    public void update(Run run, Integer id)
    {
        var update = myjdbcClient.sql("UPDATE RUN SET title = ?, start_on = ?, complete_on = ?, miles = ?, location = ? WHERE id = ?")
                .params(run.title(), run.startOn(), run.completeOn(), run.miles(), run.location().toString(), id)
                .update();
        Assert.state(update == 1, "Failed To Update " + run.title());
    }

    public void delete(Integer id)
    {
        int update = myjdbcClient.sql("DELETE FROM RUN WHERE id = :id")
                .param("id", id)
                .update();
        Assert.state(update == 1, "Field To Delete");
    }

    public void saveAll(List<Run> runs)
    {
        runs.stream().forEach(this::create);
    }

    public int count()
    {
        return myjdbcClient.sql("SELECT * FROM RUN")
                .query()
                .listOfRows()
                .size();
    }
}
