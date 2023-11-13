import com.beust.ah.A;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
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

    @Test
    public void testNavigateToHomePage() {
        getPage().getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Sale")).click();
        Assert.assertEquals(getPage().title(),"Sale");

        getPage().getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();
        Assert.assertEquals(getPage().title(), "Home Page");
    }

    @Test
    public void testNavigateBackAndForward() {
        getPage().getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("\uE622 Men")).click();
        Assert.assertEquals(getPage().title(), "Men");
        Assert.assertEquals(getPage().url(), "https://magento.softwaretestingboard.com/men.html");

        getPage().goBack();
        Assert.assertEquals(getPage().title(), "Home Page");
        Assert.assertEquals(getPage().url(), "https://magento.softwaretestingboard.com/");

        getPage().goForward();
        Assert.assertEquals(getPage().title(), "Men");
        Assert.assertEquals(getPage().url(), "https://magento.softwaretestingboard.com/men.html");
    }

    @Test
    public void testNavigateToHomeByLogo() {
        getPage().getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("What's New")).click();
        Assert.assertEquals(getPage().title(), "What's New");

        getPage().getByLabel("store logo").click();
        Assert.assertEquals(getPage().title(), "Home Page");
    }
}
