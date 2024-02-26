package com.sumerge.kafkaapp.sequence.repository;

import com.sumerge.kafkaapp.sequence.entity.DbSequence;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;

@RunWith(MockitoJUnitRunner.class)
public class SequenceRepositoryTest {

    @InjectMocks
    private SequenceRepository sequenceRepository;

    @Mock
    private MongoOperations mongoOperations;

    @Before
    public void setUp() {

    }

    @Test
    public void testGetSequenceNumber() {

        String sequenceName = "testSequence";
        Query query = new Query(Criteria.where("id").is(sequenceName));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = options().returnNew(true).upsert(true);

        DbSequence expectedCounter = new DbSequence();
        expectedCounter.setId(sequenceName);
        expectedCounter.setSeq(1);

        when(mongoOperations.findAndModify(any(Query.class), any(Update.class), any(FindAndModifyOptions.class), any(Class.class)))
                .thenReturn(expectedCounter);


        int result = sequenceRepository.getSequenceNumber(sequenceName);


        assertEquals(1, result);
    }
}
