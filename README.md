To run this project properly, follow these steps closely:
In the terminal, go into the folder where pom.xml is and do the following commands:

mvn install
mvn exec:java -Dexec.mainClass="c01a3.crawler.Assignment3WebCrawler" -Dexec.args="https://www.kijiji.ca/b-for-sale/city-of-toronto/c30353001l1700273 5"

Where the url is the seed argument and the number being the limit argument.
Once the crawler execution is complete, run:

mvn tomcat:run

Then open your browser and go to http://localhost:8080/cscc01
To see what the result looked like when we tested it and what Kijiji UI we crawled over, go to our screenshots folder.

Thanks!
Saskia Tjioe and Jiaxin Tan
