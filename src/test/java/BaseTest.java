import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class BaseTest {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    public Page  getPage() {
        return page;
    }

    @BeforeSuite
    protected  void beforeSuite() {
        playwright = Playwright.create();
    }

    @BeforeTest
    protected  void beforeTest() {
        browser = playwright.chromium()
                .launch(new BrowserType
                                .LaunchOptions()
                                .setHeadless(true));
    }

    @BeforeMethod
    protected void beforeMethod() {
        context = browser.newContext();
        page = context.newPage();

        page.navigate("https://magento.softwaretestingboard.com/");
    }

    @AfterMethod
    protected void afterMethod() {
        context.close();
    }

    @AfterSuite
    protected void afterSuite() {
        playwright.close();
    }
}
