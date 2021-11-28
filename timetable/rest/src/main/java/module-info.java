module timetableModule.rest {
  exports timetable.restapi;

  requires jakarta.ws.rs;
  requires org.slf4j;
  requires transitive timetableModule.core;
  requires org.glassfish.hk2.api;
  requires jersey.common;
  requires jersey.server;
  requires jersey.media.json.jackson;

  opens timetable.restapi to
      jersey.server;
}
