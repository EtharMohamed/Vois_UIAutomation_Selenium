package TestCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import static java.lang.System.*;

public class BaseTest {

    private WebDriver driver;

    /**
     * POM of Login Module
     * Another method of POM that we can make it in separated files
     */

    By logInPanelHeading = By.id("logInPanelHeading");
    By UserNameField = By.id("txtUsername");
    By PasswordField = By.id("txtPassword");
    By LoginButton = By.id("btnLogin");
    By DashBoard = By.cssSelector("#content > div > div.head > h1");

    //POM of Navigation Module
    By AdminButton = By.xpath("//*[@id=\"menu_admin_viewAdminModule\"]/b");
    By UserManagementButton = By.id("menu_admin_UserManagement");
    By UsersButton = By.id("menu_admin_viewSystemUsers");
    By SysUserPage = By.cssSelector("#systemUser-information > a");

    @BeforeClass
    public void OpenWebSite()
    {
        out.println("Start OrangeHR Project");
        System.setProperty("webdriver.chrome.driver","C:\\Users\\EtharMohamed\\Desktop\\drivers\\chromedriver.exe");
        this.driver = new ChromeDriver();
        final ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        this.driver.manage().window().maximize();
        String OrangeHRURL = new String ("https://opensource-demo.orangehrmlive.com/");
        this.driver.navigate().to(OrangeHRURL);
    }

    @AfterClass
    public void CloseWebSite()
    {
        System.out.println("CleanUp");
        this.driver.close();
    }

    /**
     * TC_1.1
     * Successful Login with valid username and password
     */
    @Test(priority = 0)
            public void Login()
    {
        //Make sure that login page is opened
        WebDriverWait wait1 = new WebDriverWait(driver,30);
        wait1.until(ExpectedConditions.visibilityOfElementLocated(logInPanelHeading));
        this.driver.findElement(logInPanelHeading);

        //Login
        this.driver.findElement(UserNameField).sendKeys("Admin");
        this.driver.findElement(PasswordField).sendKeys("admin123");
        this.driver.findElement(LoginButton).click();

        //Assert Login Successfully
        String DashboardPage;
        WebDriverWait wait2 = new WebDriverWait(driver,30);
        wait2.until(ExpectedConditions.visibilityOfElementLocated(DashBoard));
        DashboardPage = driver.findElement(DashBoard).getText();
        Assert.assertEquals(DashboardPage,"Dashboard");
    }

    /**
     * TC_2.1
     * Navigate to Users Page
     */
    @Test(priority = 1)
    public void UsersNavigation()
    {
        String SysUserText;

        //Navigate to Admin
        this.driver.findElement(AdminButton).click();
        this.driver.findElement(UserManagementButton).click();
        this.driver.findElement(UsersButton);

        //Assert On System User Page
        SysUserText = driver.findElement(SysUserPage).getText();
        Assert.assertEquals(SysUserText,"System Users");
    }

}