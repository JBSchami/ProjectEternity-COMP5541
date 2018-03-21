package Eternity;

import org.junit.Test;

import javax.sound.midi.Soundbank;

import static org.junit.Assert.*;

public class EternityEngineTest {
    private EternityEngine engine = new EternityEngine();

    @Test
    public void isRadsDefaultConstructor() {
        assertTrue(engine.isRads());
    }

    @Test
    public void setRads() {
        engine.setRads(false);
        assertFalse(engine.isRads());
    }

    @Test
    public void getPrecision() {
        assertEquals(0.000000001, engine.getPrecision(), 0);
    }

    @Test
    public void setPrecision() {
        engine.setPrecision(0.0001);
        assertEquals(0.0001, engine.getPrecision(), 0);
    }

    @Test
    public void eFactorial() {
        assertEquals(1, engine.eFactorial(0), 0);
        assertEquals(1, engine.eFactorial(1), 0);
        assertEquals(2, engine.eFactorial(2), 0);
        assertEquals(6, engine.eFactorial(3), 0);
        assertEquals(24, engine.eFactorial(4), 0);
        assertEquals(120, engine.eFactorial(5), 0);
        assertEquals(720, engine.eFactorial(6), 0);
        assertEquals(5040, engine.eFactorial(7), 0);
        assertEquals(40320, engine.eFactorial(8), 0);
        assertEquals(362880, engine.eFactorial(9), 0);
        assertEquals(3628800, engine.eFactorial(10), 0);
        assertEquals(39916800, engine.eFactorial(11), 0);
    }

    @Test
    public void eExpYWholeNumbers() {
        assertEquals(8, engine.eExpY(2,3),0);
        assertEquals(-8, engine.eExpY(-2,3), 0);
        assertEquals(1, engine.eExpY(10,0), 0);
    }

    @Test
    public void eExpYRationalNumbers() {
        assertEquals(3.161099313715693310847190458066637129267e-19, engine.eExpY(106.789,-9.12), engine.getPrecision());
        assertEquals(16.414354763489643725483508528328208471385691195116508, engine.eExpY(2.1351,3.689), engine.getPrecision());
        assertEquals(1158.1331234102452992200476628797011916017513606259170, engine.eExpY(3.12,6.2), engine.getPrecision());
    }

    @Test
    public void eEulerExp() {
        assertEquals(2.718281828459045235360287471352662497757247093699959574966, engine.eEulerExp(1), engine.getPrecision());
        assertEquals(36.598234443677987752594765899183657272888796075573936, engine.eEulerExp(3.6), engine.getPrecision());
        assertEquals(14.837236489292742467742774922904654643808792430024801, engine.eEulerExp(2.69714), engine.getPrecision());
        assertEquals(0.3678794411714423215955237701614608674458111310317678, engine.eEulerExp(-1), engine.getPrecision());
        assertEquals(1.6692347094529326717708069767934718418460828887687e266, engine.eEulerExp(613), engine.getPrecision());
    }

    @Test
    public void eBaseTenExp() {
        assertEquals(100, engine.eBaseTenExp(2), 0);
        assertEquals(1.0e10, engine.eBaseTenExp(10), 0);
        assertEquals(3981.0717055349725077025230508775204348767703729738044, engine.eBaseTenExp(3.6), engine.getPrecision());
    }

    @Test
    public void eCos() {
        engine.setRads(false);
        assertEquals(1,engine.eCos(0), 0);
        assertEquals(0.984807753012208059366743024589523013670643251719842418790,engine.eCos(10), engine.getPrecision());
        assertEquals(0.939692620785908384054109277324731469936208134264464633090,engine.eCos(20), engine.getPrecision());
        assertEquals(0.866025403784438646763723170752936183471402626905190314027,engine.eCos(30), engine.getPrecision());
        assertEquals(0.766044443118978035202392650555416673935832457080395245854,engine.eCos(40), engine.getPrecision());
        assertEquals(0.642787609686539326322643409907263432907559884205681790324,engine.eCos(50), engine.getPrecision());
        assertEquals(0.5,engine.eCos(60), engine.getPrecision());
        assertEquals(0.342020143325668733044099614682259580763083367514160628465,engine.eCos(70), engine.getPrecision());
        assertEquals(0.173648177666930348851716626769314796000375677184069387236,engine.eCos(80), engine.getPrecision());
        assertEquals(0,engine.eCos(90), 0);

        assertEquals(-0.173648177666930348851716626769314796000375677184069387236,engine.eCos(100), engine.getPrecision());
        assertEquals(-0.342020143325668733044099614682259580763083367514160628465,engine.eCos(110), engine.getPrecision());
        assertEquals(-0.5,engine.eCos(120), engine.getPrecision());
        assertEquals(-0.642787609686539326322643409907263432907559884205681790324,engine.eCos(130), engine.getPrecision());
        assertEquals(-0.766044443118978035202392650555416673935832457080395245854,engine.eCos(140), engine.getPrecision());
        assertEquals(-0.866025403784438646763723170752936183471402626905190314027,engine.eCos(150), engine.getPrecision());
        assertEquals(-0.939692620785908384054109277324731469936208134264464633090,engine.eCos(160), engine.getPrecision());
        assertEquals(-0.984807753012208059366743024589523013670643251719842418790,engine.eCos(170), engine.getPrecision());
        assertEquals(-1,engine.eCos(180), engine.getPrecision());

        assertEquals(-0.984807753012208059366743024589523013670643251719842418790,engine.eCos(190), engine.getPrecision());
        assertEquals(-0.939692620785908384054109277324731469936208134264464633090,engine.eCos(200), engine.getPrecision());
        assertEquals(-0.866025403784438646763723170752936183471402626905190314027,engine.eCos(210), engine.getPrecision());
        assertEquals(-0.766044443118978035202392650555416673935832457080395245854,engine.eCos(220), engine.getPrecision());
        assertEquals(-0.642787609686539326322643409907263432907559884205681790324,engine.eCos(230), engine.getPrecision());
        assertEquals(-0.500000000000000000000000000000000000000000000000000000000,engine.eCos(240), engine.getPrecision());
        assertEquals(-0.342020143325668733044099614682259580763083367514160628465,engine.eCos(250), engine.getPrecision());
        assertEquals(-0.173648177666930348851716626769314796000375677184069387236,engine.eCos(260), engine.getPrecision());
        assertEquals(0,engine.eCos(270), 0);

        assertEquals(0.173648177666930348851716626769314796000375677184069387236,engine.eCos(280), engine.getPrecision());
        assertEquals(0.342020143325668733044099614682259580763083367514160628465,engine.eCos(290), engine.getPrecision());
        assertEquals(0.500000000000000000000000000000000000000000000000000000000,engine.eCos(300), engine.getPrecision());
        assertEquals(0.642787609686539326322643409907263432907559884205681790324,engine.eCos(310), engine.getPrecision());
        assertEquals(0.766044443118978035202392650555416673935832457080395245854,engine.eCos(320), engine.getPrecision());
        assertEquals(0.866025403784438646763723170752936183471402626905190314027,engine.eCos(330), engine.getPrecision());
        assertEquals(0.939692620785908384054109277324731469936208134264464633090,engine.eCos(340), engine.getPrecision());
        assertEquals(0.984807753012208059366743024589523013670643251719842418790,engine.eCos(350), engine.getPrecision());
        assertEquals(1,engine.eCos(360), 0);

        assertEquals(-0.74314482547739423501469704897425697718911387349802638604, engine.eCos(5982), engine.getPrecision());
        assertEquals(0.8455974098348267159044132802620757111536745123493216, engine.eCos(32.264), engine.getPrecision());
    }

    @Test
    public void eLog() {
        assertEquals(1, engine.eLog(10), engine.getPrecision());
        assertEquals(6.836259277277067070219732918774212996511548720462851195456, engine.eLog(931), engine.getPrecision());
    }

    @Test
    public void eLn() {

    }

    @Test
    public void ePI() {
        assertEquals(3.141592653589793238462643383279502884197169399375105820974, engine.ePI(), engine.getPrecision());
    }
}