package io.debezium.transforms.outbox;

import static org.fest.assertions.Assertions.assertThat;

import org.apache.kafka.connect.data.Schema;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SchemaBuilderUtilTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void canGenerateSchemaForArraysWithMoreThanOneItem() throws JsonProcessingException {
        String jsonString = "{\"id\":\"1\", \"arrayField\":[{\"duplicationError\":1},{\"duplicationError\":2}]}";
        JsonNode document = new ObjectMapper().readTree(jsonString);
        Schema schema = SchemaBuilderUtil.jsonNodeToSchema(document);
        assertThat(schema.field("arrayField").schema().valueSchema().field("duplicationError")).isNotNull();

    }

}
