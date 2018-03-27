package junkie.utils.searchword.search;

import java.util.function.BiConsumer;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class SearchResult {
	private Document document;
	public SearchResult(Document document) {
		this.document = document;
	}
	public int find(String cssQuery, BiConsumer<Element, Integer> initializer) {
		Elements elements = document.select(cssQuery);
		Integer i = 0;
		for (Element element : elements) {
			initializer.accept(element , i++);
		}
		return elements.size();
	}
}
