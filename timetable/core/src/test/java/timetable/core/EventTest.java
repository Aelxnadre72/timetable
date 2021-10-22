package timetable.core;


import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions;

public class EventTest {

/*     @Test
    public void testSetTimeStart(){
        //Event har ikke lenger Day, men date, Monday er derfor ikke gyldig argumenti konstruktør
        Event event = new Event("Work","At the office","08:00","09:00","Monday");
        event.setTimeStart("07:30");
        Assertions.assertEquals("07:30", event.getTimeStart());
    }

    @Test
    public void testTimeStartExceptionTest(){
        Event event = new Event("Work","At the office","08:00","09:00","Monday");
        Assertions.assertThrows(IllegalArgumentException.class, () ->  event.setTimeStart("99:99"));
        Assertions.assertThrows(IllegalArgumentException.class, () ->  event.setTimeStart("100:30"));
        Assertions.assertThrows(IllegalArgumentException.class, () ->  event.setTimeStart("fem"));

    }

    @Test
    public void SetTimeEndExceptionTest(){
        Event event = new Event("Work","At the office","08:00","09:00","Monday");
        Assertions.assertThrows(IllegalArgumentException.class, () ->  event.setTimeEnd("99:99"));
        Assertions.assertThrows(IllegalArgumentException.class, () ->  event.setTimeEnd("100:30"));
        Assertions.assertThrows(IllegalArgumentException.class, () ->  event.setTimeEnd("fem"));

    }

    @Test
    public void testSetTimeEnd(){
        Event event = new Event("Work","At the office","08:00","09:00","Monday");
        event.setTimeEnd("10:30");
        Assertions.assertEquals("10:30", event.getTimeEnd());
    }

    @Test
    public void testConstructor(){
        Event event = new Event("Work","At the office","08:00","09:00","Monday");
        Assertions.assertEquals("08:00", event.getTimeStart());
        Assertions.assertEquals("09:00", event.getTimeEnd());
        Assertions.assertEquals("Work", event.getTitle());
        Assertions.assertEquals("At the office", event.getDescription());
<<<<<<< HEAD
<<<<<<< HEAD
        Assertions.assertEquals(1, event.getDayOfWeek());
=======
        Assertions.assertEquals("Monday", event.getDayOfWeek());
>>>>>>> controller
=======
        Assertions.assertEquals("Monday", event.getDayOfWeek());
=======
        Assertions.assertEquals(1, event.getDayOfWeek());
>>>>>>> 4a13a446b6d59da16a964d88ccac2935bc05e1c5
>>>>>>> 32b8522b01460f19f94a49853fe80f620cc97dd8
    }

    @Test
    public void constructorExceptionTest(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Event("Work","At the office","08:0","09:00","Monday"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Event("Work","At the office","08:00","20004:00","Monday"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Event("Work","At the office","81:81","09:00","Monday"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Event("Work","At the office","08:00","1:00","Monday"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Event("Work","At the office","08:00","five","Monday"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Event("Work","At the office","four","20:00","Monday"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Event("Work","At the office","08:00","99:00","Monday"));
<<<<<<< HEAD
    } */
}
