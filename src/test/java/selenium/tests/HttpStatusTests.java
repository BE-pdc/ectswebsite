package selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class HttpStatusTests extends ParentTest {

    protected static List<String> brokenLinks = new ArrayList<>();

    //protected static List<String> workingLinks = new ArrayList<>();
    public static boolean working = true;
    public String baseUrl = "http://localhost/opleidingen/";
    public String baseYear = "2018-19/";

    @Test
    public void HomePageUrls() {
        working = true;
        String homePage = baseUrl + baseYear;
        linkTester(homePage, false);
        ShowBrokenLinks();
    }

    @Test
    public void SeveralProgramUrls() {
        working = true;
        List<String> programs = Arrays.asList("IPEPS", "PGR-COA", "PBA-BM", "PBA-TI");
        for (String program : programs) {
            String homePage = baseUrl + baseYear + program;
            System.out.println(homePage);
            linkTester(homePage, true);
        }
        ShowBrokenLinks();
    }

    @Test
    public void CachingAllPrograms(){
        working = true;
            String homePage = baseUrl + baseYear + "/PGR-COA";
            CheckThisLink(homePage);
        ShowBrokenLinks();
    }

    @Test
    public void CachingEachYear() {
        working = true;
        List<String> years = createYears(6, 20);
        for (int i = 0; i < years.size(); i++) {
            String homePage = baseUrl + years.get(i) + "/PBA-TI";
            System.out.println(homePage);
            CheckThisLink(homePage);
        }
        ShowBrokenLinks();
    }

    @Test
    public void ProgramYearUrls() {
        working = true;
        String homePage = baseUrl + baseYear + "PGR-COA/3011";
        linkTester(homePage, true);
        ShowBrokenLinks();
    }

    public void CheckThisLink(String url) {
        if (url == null || url.isEmpty()) {
            System.out.println(url + " URL is either not configured for anchor tag or it is empty");
        }
        try {

            //Old method
//            HttpClient client = HttpClientBuilder.create().build();
//            HttpResponse response = client.execute(new HttpGet(url));
//            int statusCode = response.getStatusLine().getStatusCode();

            HttpURLConnection.setFollowRedirects(false);
            HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
            con.setRequestMethod("HEAD");
            int statusCode = con.getResponseCode();

            if (statusCode >= 400) {
                brokenLinks.add(url);
                System.out.println(url + " is a broken link");
                working = false;
            } else {
                //workingLinks.add(url);
                System.out.println(url + " is a valid link");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void linkTester(String url, boolean EveryLink) {
        driver.get(url);
        CheckThisLink(url);
        List<WebElement> links = driver.findElements(By.tagName("a"));


        Iterator<WebElement> it = links.iterator();
        int i = 0;
        while (it.hasNext()) {
            url = it.next().getAttribute("href");
            if(EveryLink) {
                i = 3;
            }
            else{
                i++;
            }
            if(i % 3 == 0) {
                CheckThisLink(url);
            }
        }
    }

    public void ShowBrokenLinks() {
        if (brokenLinks.size() > 0) {
            System.out.println("The broken links are: ");
            for (int i = 0; i < brokenLinks.size(); i++) {
                System.out.println(brokenLinks.get(i));
            }
        } else {
            System.out.println("All the links are working");
        }
        brokenLinks.clear();
        assertEquals(true, working);
    }

    public List<String> createYears(int start, int end) {
        List<String> years = new ArrayList<>();
        while (start < end) {
            String yearUrl;
            yearUrl = "20" + String.format("%02d", start) + "-" + String.format("%02d", start + 1);
            start++;
            years.add(yearUrl);
        }
        return years;
    }

}

