package Eternity;

import org.junit.Test;

import static org.junit.Assert.*;

public class EternityEquationManagerTest {
    public static EternityEquationManager dummy = new EternityEquationManager();

    @Test
    public void test(){
        dummy.test(5.0,6.0);
    }

}