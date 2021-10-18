package timetable.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.time.LocalDateTime;



public class EventDeserializer extends JsonDeserializer<Event> {
    
    

    @Override
    public Event deserialize(JsonParser parser, DeserializationContext context) 
    throws IOException, JsonProcessingException{
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);

    }
    Event deserialize(JsonNode jNode) {
        if (jNode instanceof ObjectNode objNode) {
            String title = "";
            String desc = "";
            String timeStart = "";
            String timeEnd = "";
            String date = "";
            if (objNode.get("title") instanceof TextNode) {
                title = titleNode.asText();
            }
            if (objNode.get("description") instanceof TextNode) {
                desc = descNode.asText();
            }
            if (objNode.get("time-start") instanceof TextNode) {
                timeStart = timeStartNode.asText();
            }
            if (objNode.get("time-end") instanceof TextNode) {
                timeEnd = timeEndNode.asText();
            }
            if (objNode.get("date") instanceof TextNode) {
                date = dateNode.asText();
            }
            Event event = new Event(title, desc, timeStart, timeEnd, date);
            return event;
        }
        return null;
    }


}
