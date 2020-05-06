package com.supermistmc.autovoter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;

import java.util.HashSet;
import java.util.Set;

public class Main {

    final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";

    final static java.util.Random rand = new java.util.Random();

    final static Set<String> identifiers = new HashSet<String>();

    public static String randomIdentifier() {
        StringBuilder builder = new StringBuilder();
        while(builder.toString().length() == 0) {
            int length = rand.nextInt(5)+5;
            for(int i = 0; i < length; i++) {
                builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
            }
            if(identifiers.contains(builder.toString())) {
                builder = new StringBuilder();
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) throws Exception {

        System.setProperty("webdriver.chrome.driver", "/Users/mohan.raj/Downloads/chromedriver");
        System.setProperty("webdriver.chrome.silentOutput", "false");
        String[] userNames = {
                "username" // add usernames here
        };
        int len = 10;
        long ipTime = 60*1000*2;
        while (len-- > 0) {
            long start = System.currentTimeMillis();
            String userName = randomIdentifier();
            System.out.println(userName + " executing..");
            WebDriver driver = new SafariDriver();
            driver.get("https://www.planetminecraft.com/server/supermistmc/vote/");
            driver.manage().window().fullscreen();
            Thread.sleep(3000);  // Let the user actually see something!
            WebElement inputElement = driver.findElement(By.name("mcname"));
            inputElement.click();
            inputElement.sendKeys(userName);
            Thread.sleep(5000);
            WebElement webElement = driver.findElement(By.className("r3submit"));
            webElement.click();
            System.out.println(userName + " exiting..");
            Thread.sleep(10000);
            driver.quit();
            long end = System.currentTimeMillis() - start;
            Thread.sleep(ipTime - end);
            System.out.println(userName + " new user..");
            Thread.sleep(3000);
        }
    }

}
