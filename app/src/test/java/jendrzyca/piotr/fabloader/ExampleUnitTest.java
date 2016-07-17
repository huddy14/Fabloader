package jendrzyca.piotr.fabloader;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    private String idsBuilder(String[] ids)
    {
        StringBuilder builder = new StringBuilder();

        for (String s : ids) {
            builder.append(s).append(",");
        }

        String result = builder.toString();
        return result.substring(0, result.length() - 1);
    }

    @Test
    public void idsBuilder_works()
    {
        String[] id = {"asd", "asd", "asd", "asd"};
        assertEquals("asd,asd,asd,asd", idsBuilder(id));
    }
}