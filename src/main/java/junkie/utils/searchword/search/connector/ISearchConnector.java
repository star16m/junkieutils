package junkie.utils.searchword.search.connector;

import junkie.utils.searchword.data.related.RelatedSearchWord;
import junkie.utils.searchword.search.SearchException;

public interface ISearchConnector {
	public RelatedSearchWord searchRelationWord() throws SearchException;
}