package com.sumerge.kafkaapp.sequence.repository;

import com.sumerge.kafkaapp.sequence.entity.DbSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@Component
public class SequenceRepository {

    private MongoOperations mongoOperations;

    @Autowired
    public SequenceRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }


    public int getSequenceNumber(String sequenceName) {
        //get seq no.
        Query query = new Query(Criteria.where("id").is(sequenceName));

        //update seq no.
        Update update = new Update().inc("seq", 1);

        //modify in document
        DbSequence counter = mongoOperations.findAndModify(query,
                new Update().inc("seq", 1),
                options()
                        .returnNew(true)
                        .upsert(true),
                DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
