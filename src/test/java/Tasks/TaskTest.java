package Tasks;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class TaskTest{

    @Test
    void testgetStatusIcon(){
        assertEquals("[\u2718]", new Deadline("do assignments", "3/2/2019 1300").getStatusIcon());
    }

    @Test
    void testToString() {
        assertEquals("[T][\u2718] do assignments", new ToDos("do assignments").toString());
        assertEquals("[D][\u2718] do assignments (by: 3/2/2019 1300)",
                new Deadline("do assignments", "3/2/2019 1300").toString());
        assertEquals("[E][\u2718] do assignments (at: 3/2/2019 1300)",
                new Events("do assignments", "3/2/2019 1300").toString());
    }
}
