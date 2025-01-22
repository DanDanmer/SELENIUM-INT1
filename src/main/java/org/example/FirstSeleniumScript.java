package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstSeleniumScript {
    public static void main(String[] args) {
        // Constants for URL and expected titles
        final String GOOGLE_URL = "https://www.google.com/";
        final String GOOGLE_TITLE = "Google";
        final String AMAZON_URL = "https://www.amazon.com/";
        final String AMAZON_TITLE = "Amazon.com. Spend less. Smile more.";

        // Set up the WebDriver for Chrome
        WebDriverManager.chromedriver().clearDriverCache().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();

            // Navigate to Google
            driver.get(GOOGLE_URL);
            validatePage(driver, GOOGLE_URL, GOOGLE_TITLE, "Google");

            // Navigate to Amazon
            driver.navigate().to(AMAZON_URL);
            validatePage(driver, AMAZON_URL, AMAZON_TITLE, "Amazon");

            // Perform navigation actions
            driver.navigate().back();
            driver.navigate().forward();
            driver.navigate().refresh();

            // Validate final Amazon page again
            validatePage(driver, AMAZON_URL, AMAZON_TITLE, "Amazon");

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            driver.quit();  // Ensure browser closes properly
        }
    }

    /**
     * Validates if the current page matches the expected URL and title.
     *
     * @param driver       The WebDriver instance
     * @param expectedUrl  The expected URL
     * @param expectedTitle The expected title
     * @param siteName     The name of the site being validated
     */
    private static void validatePage(WebDriver driver, String expectedUrl, String expectedTitle, String siteName) {
        String currentUrl = driver.getCurrentUrl();
        String title = driver.getTitle();

        System.out.println("Current URL: " + currentUrl);
        System.out.println("Page Title: " + title);

        if (currentUrl.equalsIgnoreCase(expectedUrl) && title.equalsIgnoreCase(expectedTitle)) {
            System.out.println("✅ Successfully navigated to " + siteName + "!");
        } else {
            System.out.println("❌ Navigation to " + siteName + " failed. Check URL or title.");
        }
    }
}
