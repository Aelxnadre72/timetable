package timetable.core;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;



public class TimetableModuleWrite extends SimpleModule {
    private static final String NAME = "TimetableModuleRead";

        // Initializing serializers 

    public TimetableModuleWrite() {
        super(NAME, Version.unknownVersion());

        addSerializer(Event.class, new EventSerializer());
    
        addSerializer(Timetable.class, new TimetableSerializer());
       
        addSerializer(User.class, new UserSerializer());
        
    }
}