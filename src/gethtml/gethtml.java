package gethtml;
import java.io.IOException;

import javax.print.Doc;

import org.jsoup.Jsoup;
public class gethtml {
	public static String gethtml(String url) throws IOException {
		org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
		return doc.toString();
	}
	public static void main(String[] urgs) throws IOException {
		String string = gethtml("http://www.jb51.net/article/43485.htm");
		System.out.println(string);
	}
	public static Doc getdoc(String url) throws IOException {
		org.jsoup.nodes.Document doc = Jsoup.connect(url).get();
		return (Doc) doc;
	}
}
