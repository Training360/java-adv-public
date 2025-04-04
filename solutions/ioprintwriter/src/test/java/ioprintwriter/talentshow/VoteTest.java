package ioprintwriter.talentshow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class VoteTest {

    Vote v = new Vote(1, 10);

    @Test
    void createVote() {
        assertEquals(1, v.getId());
        assertEquals(10, v.getNumber());
    }

    @Test
    void incNumber() {
        assertEquals(10, v.getNumber());
        v.incNum();
        assertEquals(11, v.getNumber());
    }

}
