ArrayList<Line> priceData;
ArrayList<Stock> stockData;
String[] stockArray;
SimpleDateFormat dateFormat;

PFont font1;
PFont font2;
Scroll_Bar scrollBar;

// make homePAge accept param for the arralyust and toggle between nasdaq and nyse
// add comments for every function 


int screenNumber = 1;
String tickerName = "PEZ";

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.text.SimpleDateFormat;

void setup() {
  size(1024,580);
  font1 = createFont("GillSans-36.vlw",36);
  font2 = createFont("GillSans-18.vlw",18);
  
  scrollBar = new Scroll_Bar();
  priceData = new ArrayList<Line>();
  stockData = new ArrayList<Stock>();
  
  stockArray = readStockInfo("stocks.csv");
  readPriceData("daily_prices1k.csv");
  
  createStocks();
  latestDataPoints = queryLatestDataPoints();
  nasdaq = queryNASDAQ();
  nyse = queryNYSE();
  masterListStockButtons = new ArrayList<Widget>();
}

void draw() {
  switch(screenNumber) {
    case 1:
      drawHomePage();
      break;
    case 2:
      if (exchangeName.equals("NYSE")) drawMasterList(nyse);
      else drawMasterList(nasdaq);
      break;
    case 3:
      drawGainers();
      break;
    case 4:
      drawBiggestLosers();
      break;
    case 5:
      drawPOT(tickerName);//(priceData);
      break;
    default:
      drawHomePage();
  }
}
void mousePressed() {
  scrollBar.mousePressedBar();
  activateAllButtons(mouseX, mouseY, screenNumber);
}
 
void mouseReleased() {
  scrollBar.mouseReleasedBar();
}

String[] readStockInfo(String fileName) {
  String[] lines = loadStrings(fileName);
  println("there are " + lines.length + " stocks");
  return lines;
}

void initStock(String ticker) {
  for (int i = 0; i < stockArray.length; i++) {
    String[] data = split(stockArray[i], ",");
    if (data[0].equals(ticker)) {
      stockData.add(new Stock(stockArray[i]));
      break;
    }
  }
}

void readPriceData(String fileName) {
  String[] lines = loadStrings(fileName);
  for (int i = 0 ; i < lines.length; i++) {
    priceData.add(new Line(lines[i]));
    //println(lines[i]);
    //text(lines[i], 10, i*15);
  }
  println("there are " + lines.length + " lines");
}


// Alice, added method createStocks() to automate the initialisation of stocks, 30/03/20, 7pm
//
// createStocks() automates the initialisation of the Stock objects (stored in ArrayList<Stock> stockData)
// It then intialises each Stock object's relevant ArrayList<Line> pricePoints, i.e each Stock
// has an ArrayList<Line> of all the data points associated with that company
// createStocks() ensures that ONLY the companies from the stocks.csv that are ALSO present in the relevant daily_prices.csv file are 
// initialised 
//
void createStocks() {
  for (int i = 1; i < stockArray.length; i++) {
    boolean quit = false;
    String[] temp = split(stockArray[i], ",");
    String ticker = temp[0];

    for (int j = 0; j < priceData.size() && quit == false; j++) {
      if (ticker.equals(priceData.get(j).getTicker())) {
        initStock(ticker);
        quit = true;
      }
    }
  }
  for (Stock stock : stockData) {
    stock.getStockData();
  }
}