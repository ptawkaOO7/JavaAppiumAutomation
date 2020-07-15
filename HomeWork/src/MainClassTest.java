import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends MainClass
{
    @Test

    public void testGetLocalNumber()
    {
        int a = this.getLocalNumber();
        Assert.assertTrue("a != 14", a == 14);
    }

    @Test

    public void testGetClassNumber()
    {
        int a = this.getClassNumber();
        Assert.assertTrue("a <= 45", a > 45);
    }

    @Test

    public void testGetClassString()
    {
        String a = this.getClassString();
        Assert.assertTrue("string doesn't contain 'hello'", a.contains("hello") || a.contains("Hello"));
    }
}
