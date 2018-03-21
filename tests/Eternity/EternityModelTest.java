package Eternity;

import org.junit.Test;

import static org.junit.Assert.*;

public class EternityModelTest {
    EternityModel model = new EternityModel();

    @Test
    public void setRadianSetting() {
        model.setRadianSetting(false);
        assertEquals(false,model.isRadianSetting());
        model.setRadianSetting(true);
        assertEquals(true, model.isRadianSetting());
    }

    @Test
    public void isRadianSetting() {
        assertEquals(true,model.isRadianSetting());
    }

    @Test
    public void setResult() {
        model.setResult(99.98);
        assertEquals(99.98, model.getResult(),0);
    }

    @Test
    public void getResult() {
        assertEquals(0, model.getResult(), 0);
    }

    @Test
    public void pushBackHistory() {
        //Empty at first
        assertEquals("", model.previousHistory());
        assertEquals("", model.nextHistory());
        //Add an entry
        model.pushBackHistory("10+b-ac^123");
        //previous history and next history should point to only entry
        assertEquals("10+b-ac^123", model.previousHistory());
        assertEquals("10+b-ac^123", model.nextHistory());
        //Add an entry
        model.pushBackHistory("65-123*Log(12312)");
        //previous history should point back to last added
        assertEquals("65-123*Log(12312)", model.previousHistory());
        //previous history should point back to one before last added
        assertEquals("10+b-ac^123", model.previousHistory());
        //previous history should not move if it is pointing to first item
        assertEquals("10+b-ac^123", model.previousHistory());
        assertEquals("10+b-ac^123", model.previousHistory());
        //next history should move forward to the next item from the current location
        assertEquals("65-123*Log(12312)", model.nextHistory());
        //next history should not move if the next item from the current location is the last
        assertEquals("65-123*Log(12312)", model.nextHistory());
        assertEquals("65-123*Log(12312)", model.nextHistory());
        assertEquals("65-123*Log(12312)", model.nextHistory());

    }

    @Test
    public void clearHistory() {
        model.pushBackHistory("12341234");
        model.pushBackHistory("09182341");
        model.pushBackHistory("145119078");
        model.clearHistory();

        assertEquals("", model.nextHistory());
        assertEquals("", model.previousHistory());
    }
}