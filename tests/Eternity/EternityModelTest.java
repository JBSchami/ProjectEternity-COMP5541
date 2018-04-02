package Eternity;

import Eternity.Logic.EternityModel;
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


    }

    @Test
    public void clearHistory() {

    }
}