module timetableModule.rest {
    //requires timetableModule.core;
    requires jakarta.ws.rs;
    requires org.slf4j;
    requires timetableModule.core;
    //requires org.glassfish.hk2.api; 
    //opens timetable.restapi to jersey.server;
}
