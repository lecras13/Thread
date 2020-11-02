package epam.thread.logic;

import com.epam.thead.entity.Lot;
import com.epam.thead.entity.Member;
import com.epam.thead.logic.JsonMember;
import com.epam.thead.logic.JsonMemberCreator;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

@RunWith(DataProviderRunner.class)
public class JsonMemberCreatorTest {
    private static final String MEMBERS_VALID = "src/test/java/resources/membersValid.json";
    private static final String MEMBERS_INVALID = "src/test/java/resources/membersInvalid.json";

    @DataProvider
    public static Object[][] testProvider(){
        Member memberFirst = new Member(1, 5);
        Member memberSecond = new Member(2, 10);
        Member memberThird = new Member(3, 15);
        Member memberFourth = new Member(4, 20);
        Member memberFifth = new Member(5, 25);
        List<Member> members = Arrays.asList(memberFirst, memberSecond, memberThird, memberFourth, memberFifth);
        return new Object[][]{
                {members}
        };
    }

    @Test
    @UseDataProvider("testProvider")
    public void testJsonMemberCreatorIsCorrect(List<Lot> members){
        JsonMember jsonMember = new JsonMemberCreator();
        List<Member> actual = jsonMember.create(MEMBERS_VALID);

        boolean resultCompare = actual.equals(members);

        Assert.assertTrue(resultCompare);
    }

    @Test(expected = NumberFormatException.class)
    public void testJsonMemberCreatorWhenFileIsInCorrect(){
        JsonMember jsonMember = new JsonMemberCreator();
        List<Member> actual = jsonMember.create(MEMBERS_INVALID);
    }
}
