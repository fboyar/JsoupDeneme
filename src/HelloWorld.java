import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HelloWorld {
	
	private static String domainName = "https://onedio.com";
	
	public static void main(String[] args) throws Exception{
		Document document = Jsoup.connect(domainName).get();
		Element articles = GetFromDocumentByElementID(document, "articles");
		List<String> hrefList = GetHrefListFromElement(articles);
		ClearList(hrefList); 
		
		hrefList.forEach(href -> {
									System.out.println(domainName + href);
								});
		
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
