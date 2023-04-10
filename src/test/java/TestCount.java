import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestCount {


    @Test
    public void testCount() {
        MainHandler mainHandler = new MainHandler();
        mainHandler.writeFile("{\"title\": \"булка\", \"date\": \"2022.02.08\", \"sum\": 200}");
        mainHandler.getCountMaxSum().parseTsvFile();
        int a = mainHandler.getCountMaxSum().getMaxSum().get("еда");
        mainHandler.addProduct();
        int b = mainHandler.getCountMaxSum().getMaxSum().get("еда");
        Assertions.assertEquals(a + 200, b);
    }
}
