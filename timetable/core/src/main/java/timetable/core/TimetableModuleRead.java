package timetable.core;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;




public class TimetableModuleRead extends SimpleModule {
    private static final String NAME = "TimetableModuleRead";

    // Initializing deserializers 
    public TimetableModuleRead() {
        super(NAME, Version.unknownVersion());
        
        addDeserializer(Event.class, new EventDeserializer());

        
        addDeserializer(Timetable.class, new TimetableDeserializer());

        
        addDeserializer(User.class, new UserDeserializer());
        
    }
}
