package c01a3.testcrawler;

import java.io.IOException;

import c01a3.crawler.Assignment3WebCrawler;

public class TestAssignment3WebCrawler {

  public static void testGetRental() {
    // check if rental gets all the information we need
    try {
      Assignment3WebCrawler.crawler("https://www.kijiji.ca/b-apartments-condos/city-of-toronto/page-2/c37l1700273", 10);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}
