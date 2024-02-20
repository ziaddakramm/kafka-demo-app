package com.sumerge.kafkaapp.service;

import com.sumerge.kafkaapp.entity.DbSequence;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@Service
@Data
public class SequenceGeneratorService {

    private MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
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
