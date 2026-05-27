package tests.ui;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.ExcelUtils;

public class LoginTests extends BaseTest {

    LoginPage loginPage;

    @DataProvider(name = "TestData")
    public Object[][] loginData() {

    return ExcelUtils
            .getSheetData("TestData");
    }

    @Test(dataProvider = "TestData")
    public void verifyLogin(
            String email,
            String password,
            String type
    ) {

        loginPage = new LoginPage(driver);

        loginPage.login(email, password);

        if (type.equals("valid")) {

            Assert.assertTrue(
                    loginPage.isDashboardDisplayed(),
                    "Dashboard not displayed"
            );

        } else {

            String currentUrl = driver.getCurrentUrl();

            Assert.assertTrue(
                    currentUrl.contains("login"),
                    "User should remain on login page"
            );
        }
    }
}