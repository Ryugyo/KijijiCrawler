package c01a3.crawler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import c01a3.servlet.DBpath;

public class Assignment3WebCrawler {

  private static String base  = "https://www.kijiji.ca";
  private static Document d = null;

  /**
   * Gather information on Toronto rental listings from the provided
   * Kijiji url. Keep gathering information until the limit is reached
   * or until the listings run out.
   * @param seed - the url to the Kijiji rental listings.
   * @param limit - the number of entries the user wants.
   * @throws IOException - if connection to seed fails.
   */
  public static void crawler(String seed, int limit) throws IOException {
    d = Jsoup.connect(seed).get();
    // if connection fails, throw exception
    if (d == null) {
      throw new IOException("Connection failed. Check URL");
    }

    int counter = limit;
    int buildId = 0;
    // otherwise, get all the kijiji rental listings
    Elements rentals = d.select("div.search-item");

    // connect to sqlite database
    try{
      // Connect to a3kijiji database
      Class.forName("org.sqlite.JDBC");
      Connection conn = DriverManager.getConnection("jdbc:sqlite:" + DBpath.DBpath);
      Statement stmt = conn.createStatement();
      // empty Buildings table
      stmt.executeUpdate("DELETE FROM Buildings");

      // insert element to Buildings table
      while (counter > 0) {
        for (Element rental : rentals) {
          // get some of the rental info, such as price and listing id
          String listPrice = rental.select("div.price").text().replaceAll("\\$*,*", "");
          int price = 0;
          if (!listPrice.equals("Please Contact")) {
            double priceDecimal = Double.parseDouble(rental.select("div.price").text().replaceAll("\\$*,*", ""));
            price = (int) Math.floor(priceDecimal);
          }

          // to get remaining info, must connect into rental view page
          Element innerUrl = rental.select("a").first();
          Document innerDoc = Jsoup.connect(base + innerUrl.attr("href")).get();
          String address = innerDoc.select("span.address-3617944557").text();
          
          String numBeds = innerDoc.select("h4.realEstateLabel-3766429502:contains(Bedrooms) ~ div").text();
          float numBaths = Float.parseFloat(innerDoc.select("h4.realEstateLabel-3766429502:contains(Bathrooms) ~ div").text());

          String unitType = "no result";
          String size = null;
          // add in optional parameters as necessary
          String details = innerDoc.select("h4.realEstateLabel-3766429502").text();
          if (details.contains("Unit Type")) {
            unitType = innerDoc.select("h4.realEstateLabel-3766429502:contains(Unit Type) ~ div").text();
            System.out.println("Unit Type: " + unitType);
          }
          if (details.contains("Size (sqft)")) {
            size = innerDoc.select("h4.realEstateLabel-3766429502:contains(Size) ~ div").text().replaceAll("\\D", "");
            System.out.println("Size: " + size + "sqft");
          }

          // display information to stdout
          System.out.println("ID: " + buildId + " Address: " + address + " Price: $" + price);
          System.out.println("Bedrooms: " + numBeds + " Bathrooms: " + numBaths);

          // insert into database
          PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Buildings(buildId, address, price, type, bedrooms, bathrooms, size) VALUES(?, ?, ?, ?, ?, ?, ?)");
          pstmt.setInt(1, buildId);
          pstmt.setString(2, address);
          pstmt.setInt(3, price);
          pstmt.setString(4, unitType);
          pstmt.setString(5, numBeds);
          pstmt.setFloat(6, numBaths);
          if (size != null) {
            pstmt.setInt(7, Integer.parseInt(size));
          } else {
            pstmt.setInt(7, 0);
          }
          pstmt.executeUpdate();
          System.out.println("...inserted to database\n");
          // deduct the counter
          counter--;
          buildId++;
          // if limit reached before end of page, end the loop
          if (counter == 0) {
            break;
          }
        }
        // if limit still not reached, need to go to next page
        if (counter > 0) {
          d = Jsoup.connect(base + rentals.select("a.Next").text()).get();
          System.out.println("Going to next page for more entries...");
        }
      }
    
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    // take user arguments and use them to run the crawler
    try {
      System.out.println("starting crawler...started");
      int limit = Integer.valueOf(args[1]);
      Assignment3WebCrawler.crawler(args[0], limit);
      System.out.println("done");
    } catch (IOException e) {
      // if something bad happens, print stack trace
      e.printStackTrace();
    }
  }
}
