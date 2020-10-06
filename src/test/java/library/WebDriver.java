/*
@author Kunal Soni
*/

package library;

import common.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriver extends TestBase {

	public static void enterText(By locator, String text) {
		WebElement element = driver.findElement(locator);
		element.sendKeys(text);
	}

	public static void clearText(By locator) {

		WebElement element = driver.findElement(locator);
		element.clear();
	}

	public static void clickOnButton(By locator) {
		WebElement element = driver.findElement(locator);
		element.click();
	}

	public String getText(By locator) {
		WebElement element = driver.findElement(locator);
		return element.getText();
	}

	public String getPlaceholder(By locator) {
		WebElement element = driver.findElement(locator);
		return element.getAttribute("placeholder");
	}

	public void waitForElementVisible(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, 50);
		wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

}