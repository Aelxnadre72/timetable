package timetable.core;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;






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
                JsonNode titleNode = objNode.get("title");
                title = titleNode.asText();
            }
            if (objNode.get("description") instanceof TextNode) {
                JsonNode descNode = objNode.get("description");
                desc = descNode.asText();
            }
            if (objNode.get("time-start") instanceof TextNode) {
                JsonNode timeStartNode = objNode.get("time-start");
                timeStart = timeStartNode.asText();
            }
            if (objNode.get("time-end") instanceof TextNode) {
                JsonNode timeEndNode = objNode.get("time-end");
                timeEnd = timeEndNode.asText();
                
            }
            if (objNode.get("date") instanceof TextNode) {
                JsonNode dateNode = objNode.get("date");
                date = dateNode.asText();
            }
            Event event = new Event(title, desc, timeStart, timeEnd, date);
                
            return event;
        }
        return null;
    }


}
