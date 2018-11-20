import org.junit.Assert;
import org.junit.Test;



public class MainClassTest extends MainClass {

    @Test
    public void testGetLocalNumber() {

     int a = this.getLocalNumber();
     int expected = 14;


        Assert.assertTrue( "This is wrong number", a == expected);


    }

    @Test
    public void testGetClassNumber() {

        int b = this.getClassNumber();
        int expected = 45;

        Assert.assertTrue("This is wrong number, should be great then 45", b > expected);


    }

    @Test
    public void testGetClassString() {


            String str = this.getClass_string();
            String expected1 = "Hello, world";
            String expected2 = "hello, world";


        Assert.assertTrue("Please, check spelling", str == expected1 | str == expected2 );



    }


}
