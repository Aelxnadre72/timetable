package timetable.json.serialization;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;


import timetable.core.Event;
import timetable.core.Timetable;



class TimetableDeserializer extends JsonDeserializer<Timetable>{
    private EventDeserializer eventDeserializer = new EventDeserializer();
    

    @Override
    public Timetable deserialize(JsonParser parser, DeserializationContext ctxt)
        throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
  }

    
    
    Timetable deserialize(JsonNode treeNode) {
        if (treeNode instanceof ObjectNode objNode) {

            int week = objNode.get("week").intValue();
            int year = objNode.get("year").intValue(); 
            Timetable timetable = new Timetable(week, year);
            JsonNode eventsNode = objNode.get("events");
            for (JsonNode elemNode : ((ArrayNode) eventsNode)) {
                Event event = eventDeserializer.deserialize(elemNode);
                if (event != null) {
                    timetable.addEvent(event);
                    }
                }
            return timetable;
            }
        return null;
    }
}
