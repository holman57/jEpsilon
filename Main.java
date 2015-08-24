
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;

public class Main {

    static String[] avoidMe = {
            "blogger", "DefenceTalk", "AsiaOne", "http://twitter.com", "http://www.adbrite.com", "http://www.sinodefence.com", "http://www.jihadwatch.org/", "http://www.businessinsider.com/defense", "http://xbradtc.wordpress.com/", "http://www.navytimes.com", "http://www.blogsofwar.com/", "http://www.chinasecurity.us/index.asp", "http://www.foggofwar.com/", "http://chinesemil.blogspot.com/", "http://www.darkgovernment.com/news/", "http://waronterrornews.typepad.com/home/", "http://terrorwonk.blogspot.com/", "http://warincontext.org/", "http://www.shadowspear.com/", "http://www.globalincidentmap.com/map.php", "http://redbannernorthernfleet.blogspot.com/", "http://ramansterrorismanalysis.blogspot.com/", "http://www.strategypage.com/", "http://www.defencetalk.com/", "http://missiledefense.wordpress.com/", "http://www.yourdefencenews.com", "http://military-matters.blogspot.com/", "http://karakapend.wordpress.com/", "http://strikefighterconsultinginc.com/blog/", "http://terrorism-online.blogspot.com/", "http://www.warsintheworld.com/", "http://www.uasvision.com/", "http://www.globalenvision.org/", "http://www.spacewar.com/", "http://www.murdoconline.net/", "http://www.military.com/", "http://www.iwpr.net/", "http://www.alert5.com/", "http://newwars.wordpress.com/", "http://warnewsupdates.blogspot.com/2007", "http://warnewsupdates.blogspot.com/2008", "http://warnewsupdates.blogspot.com/2009", "http://warnewsupdates.blogspot.com/2010", "http://warnewsupdates.blogspot.com/2011", "http://warnewsupdates.blogspot.com/2012", "http://warnewsupdates.blogspot.com/2013", "http://warnewsupdates.blogspot.com/2014", "http://warnewsupdates.blogspot.com/2015", "http://warnewsupdates.blogspot.com/search?updated"
    };
    static String[] keywords = {
            "missile", "global", "attack", "conflict", "dollar", "deploy", "protest", "base", "syria", "diplomat", "desk", "moscow", "global", "terror", "arab", "saudi", "bomb", "france", "britain", "greece", "audit", "yellen", "government", "bank", "kiev", "offensive", "islamic", "kremlin", "immigration", "state", "corruption", "mi6", "cia", "military", "lebanon", "ukraine", "ww2", "wwii", "refugee", "isis", "launch", "explosion", "beirut", "mobilization", "china", "migrant", "turkey", "germany", "war", "japan", "ceasefire", "putin", "martial", "washington", "berlin", "crisis", "congress", "gun", "illegal", "israel", "iran", "korea", "aircraft", "europe", "force", "submarine", "navy", "army"
    };
    static String[] bounceImages = {
            "logo", "banner", "advertisement", "button", "picture-5", "moatads", "KNlE0c1yAP3V", "bookyards", "pv.gif", "bwbx", "/black.jpg", "zom.jpg", "square-divider", "i100-radx2.jpg", "signin", "util_cs.gif", "sign.gif", "-fb.jpg", "facebook-loading", "s400", "icon", "twitter", "brand/news.png", "more-down-2.png", "share", "/t.gif", "ico.", "google_play", "washingtonpost_black", "60x60", "afp-gif", "60x85", "hover", "-ZI635-24", "cloudfront", "139x90", "/news.png", "source-txt", "feed.png", "Associated-Press.png", "search", "co.uk/frameworks", "comments", "getclicky", "pictures/picture-", "14_images/seoul.gif", "noscript", "googleadservices", "com&nojs=1", "transparent", "picture-68272", "menu", "addthis", "iphone", "sandbox", "s4.", "thumb", "thetimesofisrael-529x60", "statse", "fanxiang13", "nojavascript", "/ivy", "imrworldwide", "39814569.cms", "?[AQB]", "//a.abc", "50x50", "fanxiang12.gif", "cmstrendslog", "//i.", "metrics", "sidekick", "138x92", "masthead", "email", "pixel", "icon", "Icon", "ICON", "white-2x", "grey.gif", "hits", "thalys", "icon", "s2.", "base64", "scorecardresearch", "doubleclick"
    };

    //Master list of URLs
    static List<String> alphaURL = new ArrayList<>();

    public static void main(String[] args) throws org.xml.sax.SAXException {
        try {
            //build list of URLs from rss xml parse
            buildMe();
        } catch (org.xml.sax.SAXException e) {
            //e.printStackTrace();
        }
        //randomize list
        Collections.shuffle(alphaURL);

        //iterate over list
        for (int i = 0; i < alphaURL.size(); i++) {
            //parse URLs one by one
            diveUrl(alphaURL.get(i));
        }
    }
    public static void buildMe() throws org.xml.sax.SAXException {
        //      getURL();
        //       parseNodes();
        String get_URL;
        int x = 0;
        while (x <= 4) {
            switch (x){
                case 0: {
                    get_URL = "http://www.cnbc.com/id/100727362/device/rss/rss.html";
                    //    System.out.println(get_URL);
                    parseNodes(get_URL);
                    break;
                }
                case 1: {
                    get_URL = "http://feeds.feedburner.com/zerohedge/feed?format=xml";
                    //    System.out.println(get_URL);
                    parseNodes(get_URL);
                    break;
                }
                case 2:{
                    get_URL = "http://www.reddit.com/r/news/.rss";
                    //   System.out.println(get_URL);
                    redditMap(get_URL);
                    break;
                }
                case 3:
                {
                    get_URL = "http://feeds.reuters.com/Reuters/worldNews?format=xml";
                    //   System.out.println(get_URL);
                    parseNodes(get_URL);
                    break;
                }
                case 4:{
                    get_URL = "http://warnewsupdates.blogspot.com/";
                    //    System.out.println(get_URL);
                    warMap(get_URL);
                    break;
                }
            }
            x++;
        }
    }
    public static void parseNodes(String get_URL) {

        try {

            //Connect to the website and get the html
            Document doc = Jsoup.connect(get_URL).get();

            //Get all elements with link tag ,
            Elements link = doc.getElementsByTag("link");

            for (Element el : link) {

                //for each element get the srs url
                String src = el.text();

                if (!(src == null | src.length() == 0)) {
                    alphaURL.add(src);
                    // System.out.println(src);
                }
            }

        } catch (IOException ex) {
            //   System.err.println("There was an error");
            //   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void diveUrl(String markURL) {

        List<internalComparison> internalMark = new ArrayList<>();

        try {
            //Connect to the website and get Elements search "title"
            Document doc = Jsoup.connect(markURL).get();
            Elements link = doc.getElementsByTag("title");

            //     forEach loop (search for "title" with keywords)
            for (Element el : link) {

                //for each element get the srs url
                String src = el.text();

                //      breakMe     breaks image loop (and trys again)
                //      die         breaks method and returns
                boolean breakMe =   false;
                boolean die =       false;

                //      check keyword gate
                boolean Continue = false;
                for (int i = 0; i < keywords.length; i++) {
                    if (src.toLowerCase().contains(keywords[i]))
                        Continue = true;
                }
                if (Continue)
                    if (!(src == null | src.length() == 0)) {
                        Elements images = doc.select("img");
                        for (Element image : images) {

                            if (!(image.attr("width") == "") && (image.attr("width") == null))
                            {
                                int x = Integer.parseInt(image.attr("width"));
                                if (x < 100)
                                    die = true;
                            }
                            if (!(image.attr("height") == "") && (image.attr("height") == null))
                            {
                                int x = Integer.parseInt(image.attr("height"));
                                if (x < 100)
                                    die = true;
                            }

                            if ((image.attr("src").contains("gif"))||(image.attr("src").contains("jpg"))||(image.attr("src").contains("png")))
                            if (image.attr("src").contains("//"))
                            if (!(image == null | image.attr("src").length() < 5))
                            if (!(breakMe)) {
                                for (int i = 0; i < bounceImages.length; i++) {
                                    if (image.attr("src").contains(bounceImages[i])){
                                        die = true;
                                    }
                                }
                                if (!(die)){
                                    internalComparison mark = new internalComparison();
                                    mark.setTitle(src);
                                    mark.setUrl(markURL);
                                    mark.setImg(image.attr("src"));
                                    mark.printMark();
                                    breakMe = true;
                                }
                                die = false;
                            }
                        }
                    }
            }
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    public static void redditMap(String get_URL) {

        List<String> redditURL = new ArrayList<>();

        try {
            //Connect to the website and get the html
            Document doc = Jsoup.connect(get_URL).get();

            //Get all elements with img tag ,
            Elements link = doc.select("link");

            for (Element el : link) {
                //for each element get the srs url
                String src = el.text();

                if (!(src == null | src.length() == 0)) {
                    redditURL.add(src);
                    // System.out.println(src);
                }
            }

            for (int i = 0; i < redditURL.size(); i++) {

                //Connect to the website and get the html
                Document redditDoc = Jsoup.connect(redditURL.get(i)).get();

                //Get all elements with img tag ,
                Elements redditLink = redditDoc.select("a.title");

                for (Element el : redditLink) {

                    //for each element get the srs url
                    String src = el.attr("href");

                    if (!(src == null | src.length() == 0)) {
                        alphaURL.add(src);
                        // System.out.println(src);
                    }
                }
            }
        } catch (IOException ex) {
            //   System.err.println("There was an error");
            //   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void warMap(String get_URL) {

        try {
            //Connect to the website and get the html
            Document doc = Jsoup.connect(get_URL).get();

            //Get all elements with img tag ,
            Elements img = doc.getElementsByTag("a");

            for (Element el : img) {

                //for each element get the srs url
                String src = el.attr("abs:href");

                boolean Continue = true;
                for (int i = 0; i < avoidMe.length; i++) {
                    if (src.contains(avoidMe[i]))
                        Continue = false;
                }
                if (Continue)
                    if (!(src == null | src.length() == 0)) {
                        alphaURL.add(src);
                        // System.out.println(src);
                    }
            }
        } catch (IOException ex) {
            //   System.err.println("There was an error");
            //   Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
