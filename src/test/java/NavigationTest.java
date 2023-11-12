import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {
    @Test
    public void testHomePageUrlAndTitle() {
        String expectedHomeUrl = "https://magento.softwaretestingboard.com/";
        String expectedHomeTitle = "Home Page";

        Assert.assertEquals(getPage().url(), expectedHomeUrl);
        Assert.assertEquals(getPage().title(), expectedHomeTitle);
    }

    @Test(dependsOnMethods = "testHomePageUrlAndTitle")
    public void testNavigateMenuWomenToWomenPage() {
        String expectedUrl = "https://magento.softwaretestingboard.com/women.html";
        String expectedTitle = "Women";
        String homeUrl = getPage().url();
        String homeTitle = getPage().title();

        getPage().getByText("Women").click();

        String womenUrl = getPage().url();
        String womenTitle = getPage().title();

        Assert.assertNotEquals(homeUrl, womenUrl);
        Assert.assertNotEquals(homeTitle, womenTitle);

        Assert.assertEquals(womenUrl,expectedUrl);
        Assert.assertEquals(womenTitle, expectedTitle);
    }
}
