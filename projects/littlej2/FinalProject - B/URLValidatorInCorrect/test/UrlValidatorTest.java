

import junit.framework.TestCase;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;
import java.util.regex.Matcher;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!


public class UrlValidatorTest extends TestCase {


    public UrlValidatorTest(String testName) {
        super(testName);
    }

    public void testManualTest() {
        //You can use this function to implement your manual testing
        //test with all default.
        UrlValidator UrlValidator = new UrlValidator();
        System.out.println("ftp://foo.bar.com/: " + UrlValidator.isValid("ftp://foo.bar.com/"));
        //assertEquals(true, urlValidator.isValid("ftp://foo.bar.com/"));
        System.out.println("https://www.google.com: " + UrlValidator.isValid("https://www.google.com"));
        System.out.println("http://www.google.com: " + UrlValidator.isValid("http://www.google.com"));
        System.out.println("www.hotmail.com: " + UrlValidator.isValid("hotmail.com"));


        //Bug: Schemes option is not working
        String[] schemes = {"http", "https"};
        UrlValidator urlValidator = new UrlValidator(schemes);
        if (urlValidator.isValid("http://www.google.com/")) {
            System.out.println("url is valid");
        } else {
            System.out.println("url is invalid");
        }


        UrlValidator urlValidator2 = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        if (urlValidator2.isValid("http://www.google.com/")) {
            System.out.println("url2a is valid");
        } else {
            System.out.println("url2a is invalid");
        }

        if (urlValidator2.isValid("http://www.google.com?action=query/")) {
            System.out.println("url2b is valid");
        } else {
            System.out.println("url2b is invalid");
        }

        if (urlValidator2.isValid("http://www.google.com?action=/")) {
            System.out.println("url2c is valid");
        } else {
            System.out.println("url2c is invalid");
        }

        if (urlValidator2.isValid(".com/")) {
            System.out.println("url2d is valid");
        } else {
            System.out.println("url2d is invalid");
        }

        //Bug.  Should be invalid
        if (urlValidator2.isValid("http:///www.google.com/")) {
            System.out.println("url2e is valid");
        } else {
            System.out.println("url2e is invalid");
        }

        if (urlValidator2.isValid("http://google.com/")) {
            System.out.println("url2f is valid");
        } else {
            System.out.println("url2f is invalid");
        }

        //Should this be invalid?
        if (urlValidator2.isValid("http://google/")) {
            System.out.println("url2g is valid");
        } else {
            System.out.println("url2g is invalid");
        }

        if (urlValidator2.isValid("http:www.google.com")) {
            System.out.println("url2h is valid");
        } else {
            System.out.println("url2h is invalid");
        }

        if (urlValidator2.isValid("http//www.google.com")) {
            System.out.println("url2i is valid");
        } else {
            System.out.println("url2i is invalid");
        }

        if (urlValidator2.isValid(":///www.google.com")) {
            System.out.println("url2j is valid");
        } else {
            System.out.println("url2j is invalid");
        }

        if (urlValidator2.isValid(":///www.com")) {
            System.out.println("url2k is valid");
        } else {
            System.out.println("url2k is invalid");
        }

        if (urlValidator2.isValid(":///www.google.com?")) {
            System.out.println("url2l is valid");
        } else {
            System.out.println("url2l is invalid");
        }

        //Bug. Should be valid
        if (urlValidator2.isValid("http://www.google.com:80?action=view")) {
            System.out.println("url2m is valid");
        } else {
            System.out.println("url2m is invalid");
        }


    }


    public void testYourFirstPartition() {
        //Test good scheme and good authority
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] goodSchemes = {"http://"};
        String[] goodAuthority = {"www.google.com", "google.com"};
        int randomNum1;
        int randomNum2;

        //GG
        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length);
        randomNum2 = ThreadLocalRandom.current().nextInt(0, goodAuthority.length );
        String testUrl = goodSchemes[randomNum1] + goodAuthority[randomNum2];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) != true)
        {
            System.out.println("Bug");
        }
    }

    public void testYourSecondPartition() {
        //Good schemes + good authority + good Path
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

        String[] goodSchemes = {"http://"};
        String[] goodAuthority = {"www.google.com", "google.com"};
        String[] goodPath = {":80", ":800", ":8000", "/home"};
        int randomNum1;
        int randomNum2;
        int randomNum3;

        //GGG
        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, goodAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, goodPath.length );
        String testUrl = goodSchemes[randomNum1] + goodAuthority[randomNum2] + goodPath[randomNum3];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) != true)
        {
            System.out.println("Bug");
        }
    }

    public void testYourThirdPartition() {
        //good schemes + good authority + good Path + good query
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] goodSchemes = {"http://"};
        String[] goodAuthority = {"www.google.com", "google.com"};
        String[] goodPath = {":80", ":800", ":8000", "/home"};
        String[] goodQuery = { "?action=view",  "?action=view&name=me"};
        int randomNum1;
        int randomNum2;
        int randomNum3;
        int randomNum4;

        //GGGG
        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, goodAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, goodPath.length );
        randomNum4 = ThreadLocalRandom.current().nextInt(0, goodQuery.length);

        String testUrl = goodSchemes[randomNum1] + goodAuthority[randomNum2] + goodPath[randomNum3] + goodQuery[randomNum4];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) != true)
        {
            System.out.println("Bug");
        }
    }

    public void testYourFourthPartition() {
        //Good Scheme + Bad Authority
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

        String[] goodSchemes = {"http://"};
        String[] badAuthority = {"www.gogle.com", "www.google", "wwww.google.com"};
        int randomNum1;
        int randomNum2;

        //GB
        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, badAuthority.length );
        String testUrl = goodSchemes[randomNum1] + badAuthority[randomNum2];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }
    }

    public void testYourSixthPartition() {
        //Good Scheme + Bad Authority + Bad Path
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

        String[] goodSchemes = {"http://"};
        String[] badAuthority = {"www.gogle.com", "www.google", "wwww.google.com"};
        String[] badPath = {":", ":ABC", "80"};
        int randomNum1;
        int randomNum2;
        int randomNum3;

        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, badAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, badPath.length );
        //GBB
        String testUrl = goodSchemes[randomNum1] + badAuthority[randomNum2] + badPath[randomNum3] ;
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }

    }

    public void testYourSeventhPartition() {
        //goodSchemes + Bad Authority + Bad Path + Bad Query
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] goodSchemes = {"http://"};
        String[] badAuthority = {"www.gogle.com", "www.google", "wwww.google.com"};
        String[] badPath = {":", ":ABC", "80"};
        String[] badQuery = { "?action", "?", "?ABC"};
        int randomNum1;
        int randomNum2;
        int randomNum3;
        int randomNum4;

        //GBBB
        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, badAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, badPath.length );
        randomNum4 = ThreadLocalRandom.current().nextInt(0, badQuery.length );
        String testUrl = goodSchemes[randomNum1] + badAuthority[randomNum2] + badPath[randomNum3] + badQuery[randomNum4];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }

    }

    public void testYourEighthPartition() {
        //Bad Schemes + Bad Authority + Bad Path + Bad Query
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] badSchemes = {"http:///", "htp:/"};
        String[] badAuthority = {"www.gogle.com", "www.google", "wwww.google.com"};
        String[] badPath = {":", ":ABC", "80"};
        String[] badQuery = { "?action", "?", "?ABC"};

        int randomNum1;
        int randomNum2;
        int randomNum3;
        int randomNum4;

        //BBBB
        randomNum1 = ThreadLocalRandom.current().nextInt(0, badSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, badAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, badPath.length );
        randomNum4 = ThreadLocalRandom.current().nextInt(0, badQuery.length);
        String testUrl = badSchemes[randomNum1] + badAuthority[randomNum2] + badPath[randomNum3] + badQuery[randomNum4];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }
    }

    public void testYourNinethPartition() {
        //good schemes + bad Authority + good path + bad Query
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] goodSchemes = {"http://"};
        String[] badAuthority = {"www.gogle.com", "www.google", "wwww.google.com"};
        String[] goodPath = {":80", ":800", ":8000", "/home"};
        String[] badQuery = { "?action", "?", "?ABC"};
        int randomNum1;
        int randomNum2;
        int randomNum3;
        int randomNum4;

        //GBGB
        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, badAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, goodPath.length );
        randomNum4 = ThreadLocalRandom.current().nextInt(0, badQuery.length );
        String testUrl = goodSchemes[randomNum1] + badAuthority[randomNum2] + goodPath[randomNum3] + badQuery[randomNum4];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }
    }

    public void testYourTenthPartition() {
        //Good Schemes + Good Authority + bad path + bad query
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] goodSchemes = {"http://"};
        String[] goodAuthority = {"www.google.com", "google.com"};
        String[] badPath = {":", ":ABC", "80"};
        String[] badQuery = { "?action", "?", "?ABC"};
        int randomNum1;
        int randomNum2;
        int randomNum3;
        int randomNum4;

        //GGBB
        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, goodAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, badPath.length );
        randomNum4 = ThreadLocalRandom.current().nextInt(0, badQuery.length );
        String testUrl = goodSchemes[randomNum1] + goodAuthority[randomNum2] + badPath[randomNum3] + badQuery[randomNum4];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }

    }

    public void testYourEleventhPartition() {
        //Good Scheme + Good Authority + bad path
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] goodSchemes = {"http://"};
        String[] goodAuthority = {"www.google.com", "google.com"};
        String[] badPath = {":", ":ABC", "80"};
        int randomNum1;
        int randomNum2;
        int randomNum3;

        //GGB
        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, goodAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, badPath.length );
        String testUrl = goodSchemes[randomNum1] + goodAuthority[randomNum2] + badPath[randomNum3];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }

    }

    public void testYourTwelfthPartition() {
        //Good Schemes + Good Authority + Good Path + Bad Query
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] goodSchemes = {"http://"};
        String[] goodAuthority = {"www.google.com", "google.com"};
        String[] goodPath = {":80", ":800", ":8000", "/home"};
        String[] badQuery = { "?action", "?", "?ABC"};
        int randomNum1;
        int randomNum2;
        int randomNum3;
        int randomNum4;

        //GGGB
        randomNum1 = ThreadLocalRandom.current().nextInt(0, goodSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, goodAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, goodPath.length );
        randomNum4 = ThreadLocalRandom.current().nextInt(0, badQuery.length );
        String testUrl = goodSchemes[randomNum1] + goodAuthority[randomNum2] + goodPath[randomNum3] + badQuery[randomNum4];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }

    }

    public void testYourThirteenthPartition() {
        //Bad Schemes + Good Authority
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] badSchemes = {"http:///", "htp:/"};
        String[] goodAuthority = {"www.google.com", "google.com"};
        int randomNum1;
        int randomNum2;

        //BG
        randomNum1 = ThreadLocalRandom.current().nextInt(0, badSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, goodAuthority.length );
        String testUrl = badSchemes[randomNum1] + goodAuthority[randomNum2] ;
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }

    }

    public void testYourFourteenthPartition() {
        //Bad Schemes + Good Authority + Good Path
        UrlValidator tester = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
        String[] badSchemes = {"http:///", "htp:/"};
        String[] goodAuthority = {"www.google.com", "google.com"};
        String[] goodPath = {":80", ":800", ":8000", "/home"};
        int randomNum1;
        int randomNum2;
        int randomNum3;

        //BGG
        randomNum1 = ThreadLocalRandom.current().nextInt(0, badSchemes.length );
        randomNum2 = ThreadLocalRandom.current().nextInt(0, goodAuthority.length );
        randomNum3 = ThreadLocalRandom.current().nextInt(0, goodPath.length );
        String testUrl = badSchemes[randomNum1] + goodAuthority[randomNum2] + goodPath[randomNum3];
        System.out.println(testUrl);
        if(tester.isValid(testUrl) == true)
        {
            System.out.println("Bug");
        }

    }
    //You need to create more test cases for your Partitions if you need to

    public void testIsValid() {
        //You can use this function for programming based testing
        try {
            UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);

            String[] scheme = {"http://",
                    "ftp://",
                    "h3t://",
                    "3ht://",
                    "http:/",
                    "http:",
                    "http/",
                    "://",
                    ""};

            String[] auth = {"wwww.google.com",
                    "go.com",
                    "go.au",
                    "0.0.0.0",
                    "255.255.255.255",
                    "256.256.256.256",
                    "256.com",
                    "1.2.3.4.5",
                    "1.2.3.4",
                    "1.2.3",
                    ".1.2.3.4",
                    "go.a",
                    "go.a1a",
                    "go.1aa",
                    "aaa.",
                    ".aaa",
                    "aaa",
                    ""};

            String[] port = {":80", ":65535", ":0", "", ":-1", ":65636", ":65a"};

            String[] path = {"/test1",
                    "t123",
                    "/$23",
                    "/..",
                    "/../",
                    "/test1/",
                    "/#",
                    "",
                    "/test1/file",
                    "/t123/file",
                    "/$23/file",
                    "/../file",
                    "/..//file",
                    "/test1//file",
                    "/#/file"};

            String[] query = {"?action=view", "?action=edit&mode=up", ""};

            for (int i = 0; i < 100; i++) {

                // Build the url string from the above url partitions
                StringBuilder url = new StringBuilder();

                int rand = new Random().nextInt(scheme.length);
                url.append(scheme[rand]);

                rand = new Random().nextInt(auth.length);
                url.append(auth[rand]);

                rand = new Random().nextInt(port.length);
                url.append(port[rand]);

                rand = new Random().nextInt(path.length);
                url.append(path[rand]);

                rand = new Random().nextInt(query.length);
                url.append(query[rand]);

                // Create string
                String urlString = url.toString();

                System.out.println(urlString);

                // Check if valid string
                urlVal.isValid(urlString);
            }
        } catch (Throwable e) {
            e.printStackTrace();
            System.err.println("\n\n");
            System.err.println(e);
            System.err.println("Cause = " + e.getCause());
        }


    }


}
