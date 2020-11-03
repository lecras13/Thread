package epam.thread.logic;

import com.epam.thead.entity.Lot;
import com.epam.thead.logic.JsonLot;
import com.epam.thead.logic.JsonLotCreator;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class JsonLotCreatorTest {
    private static final String LOTS_VALID = "src/test/java/resources/lotsValid.json";
    private static final String LOTS_INVALID = "src/test/java/resources/lotsInvalid.json";

    @DataProvider
    public static Object[][] testProvider(){
        Lot lotFirst = new Lot(1, 100);
        Lot lotSecond = new Lot(2, 200);
        Lot lotThird = new Lot(3, 500);
        List<Lot> lots = Arrays.asList(lotFirst, lotSecond, lotThird);
        return new Object[][]{
                {lots}
        };
    }

    @Test
    @UseDataProvider("testProvider")
    public void testJsonLotCreatorIsCorrect(List<Lot> lots){
        JsonLot jsonLot = new JsonLotCreator();
        List<Lot> actual = jsonLot.create(LOTS_VALID);

        boolean resultCompare = actual.equals(lots);

        Assert.assertTrue(resultCompare);
    }

    @Test(expected = NumberFormatException.class)
    public void testJsonLotCreatorWhenFileIsInCorrect(){
        JsonLot jsonLot = new JsonLotCreator();
        List<Lot> actual = jsonLot.create(LOTS_INVALID);
    }
}
