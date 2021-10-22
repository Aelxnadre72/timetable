package timetable.core;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;


public class UserDeserializer extends JsonDeserializer<User>{

    TimetableDeserializer timetableDeserializer = new TimetableDeserializer();
    
    
    @Override
    public User deserialize(JsonParser parser, DeserializationContext ctxt)
      throws IOException, JsonProcessingException {
        TreeNode treeNode = parser.getCodec().readTree(parser); 
        return deserialize((JsonNode) treeNode);
    }

    User deserialize(JsonNode treeNode) {
        if (treeNode instanceof ObjectNode objNode) { 
            User user = new User("1");
            JsonNode timetablesNode = objNode.get("timetables");
            if (timetablesNode instanceof ArrayNode arrayNode) { 
                for (JsonNode elemNode : arrayNode) {
                    Timetable timetable =  timetableDeserializer.deserialize(elemNode); 
                    if (timetable != null) {
                        user.addTimetable(timetable);
                    }
                }
                return user;
            }
        }
        return null;
    }
    
}
