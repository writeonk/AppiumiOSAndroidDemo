/*
@author Kunal Soni
*/

package com.common.iOS;

import com.aventstack.extentreports.Status;
import common.TestBase;
import library.WebDriver;
import org.testng.annotations.Test;

public class TC01Login extends TestBase {

    @Test
    public void tc01VerifyBlankEmailPassword() {

        test = extent.createTest("Login: Verify Blank Email - Password");

        webdriver.waitForElementVisible(Login.btnLogin);
        WebDriver.clickOnButton(Login.btnLogin);
        test.log(Status.PASS, "Click on LogIn Button, Message Popup - Whoops! Please enter your email");
        logger.info("Click on LogIn Button, Message Popup - Whoops! Please enter your email");

        WebDriver.clickOnButton(Common.btnOK);
        test.log(Status.INFO, "Click On OK button");
        logger.info("Click On OK button");
    }

    @Test
    public void tc03VerifyInvalidEmail() {

        test = extent.createTest("Login: Verify Invalid Email");

        webdriver.waitForElementVisible(Common.txtEmail);
        WebDriver.enterText(Common.txtEmail, "automation");
        test.log(Status.INFO, "Enter Invalid Email");
        logger.info("Enter Invalid Email");

        webdriver.waitForElementVisible(Common.txtPassword);
        WebDriver.enterText(Common.txtPassword, "Common@2019");
        test.log(Status.INFO, "Enter Valid Password");
        logger.info("Enter Valid Password");

        webdriver.waitForElementVisible(Login.btnLogin);
        WebDriver.clickOnButton(Login.btnLogin);
        test.log(Status.PASS, "Click on LogIn Button, Message Popup - Whoops! That email is invalid. Please try again.");
        logger.error("Click on LogIn Button, Message Popup - Whoops! That email is invalid. Please try again.");

        webdriver.waitForElementVisible(Common.btnOK);
        WebDriver.clickOnButton(Common.btnOK);
        test.log(Status.INFO, "Click On OK button");
        logger.info("Click On OK button");

    }

    @Test
    public void tc04VerifyInvalidPassword() {

        test = extent.createTest("Login: Verify Invalid Password");

        webdriver.waitForElementVisible(Common.txtEmail);
        WebDriver.clearText(Common.txtEmail);
        test.log(Status.INFO, "Clear Email Field");
        logger.info("Clear Email Field");

        webdriver.waitForElementVisible(Common.txtEmail);
        WebDriver.enterText(Common.txtEmail, "automationtesting@gmail.com");
        test.log(Status.INFO, "Enter Invalid Email");
        logger.info("Enter Invalid Email");

        webdriver.waitForElementVisible(Common.txtPassword);
        WebDriver.clearText(Common.txtPassword);
        test.log(Status.INFO, "Clear Password Field");
        logger.info("Clear Password Field");

        webdriver.waitForElementVisible(Common.txtPassword);
        WebDriver.enterText(Common.txtPassword, "Common");
        test.log(Status.INFO, "Enter InValid Password");
        logger.info("Enter InValid Password");

        webdriver.waitForElementVisible(Login.btnLogin);
        WebDriver.clickOnButton(Login.btnLogin);
        test.log(Status.PASS, "Click On LogIn Button, Message Popup - Whoops! Please enter a valid password");
        logger.error("Click On LogIn Button, Message Popup - Whoops! Please enter a valid password");

        webdriver.waitForElementVisible(Common.btnOK);
        WebDriver.clickOnButton(Common.btnOK);
        test.log(Status.INFO, "Click On OK Button");
        logger.info("Click On OK Button");

    }

    @Test
    public void tc05VerifyValidEmailPassword() {

        test = extent.createTest("Login: Verify Valid Email & Password");

        WebDriver.clearText(Common.txtPassword);
        test.log(Status.INFO, "Clear Password Field");
        logger.info("Clear Password Field");

        WebDriver.enterText(Common.txtPassword, "Common@2019");
        test.log(Status.INFO, "Enter Valid Password");
        logger.info("Enter Valid Password");

        WebDriver.clickOnButton(Login.btnLogin);
        test.log(Status.PASS, "Click on LogIn Button, It takes user to the Dashboard");
        logger.info("Click on LogIn Button, It takes user to the Dashboard");

    }

}
