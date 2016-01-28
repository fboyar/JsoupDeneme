import java.util.ArrayList;
import java.util.List;

import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class HelloWorld {
	
	private static String domainName = "http://www.milliyet.com.tr/";
	private static boolean isContainsHTTP = true;
	
	public static void main(String[] args) throws Exception{
		isContainsHTTP = false;
		Document document = Jsoup.connect(domainName).get();
//		Element articles = GetFromDocumentByElementID(document, "articles");
		Element articles = document.getElementsByClass("oneCikanlar").first();
		
		List<String> hrefList = GetHrefListFromElement(articles);
		
		if (isContainsHTTP){
			ClearList(hrefList);		
			hrefList.forEach(href -> {
										System.out.println(domainName + href);
									});
		}
		else {
			hrefList.forEach(href -> {
				System.out.println(href);
			});			
		}
		
//		for (String href : hrefList.stream().filter(p -> !((String)p).startsWith("http:")).collect(Collectors.toList())){
//			System.out.println(href);
//		}
	}
	
	public static Element GetFromDocumentByElementID(Document document, String elementID){
		return document.getElementById(elementID);
	}
	
	public static List<String> GetHrefListFromElement(Element element){
		List<String> hrefList = new ArrayList<String>();
		element.select("a[href]").forEach(anchor -> {
														hrefList.add(anchor.attr("href"));
														});
		
		return hrefList;
	}
	
	public static void ClearList(List<String> list){
		list.removeIf(item -> item.contains("http:"));
	}

}
