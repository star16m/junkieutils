package junkie.utils.searchword.search.connector;

import java.util.List;

import junkie.utils.searchword.data.related.RelatedWord;
import junkie.utils.searchword.search.SearchException;

public interface ISearchConnector {
	public List<RelatedWord> searchRelationWord() throws SearchException;
}