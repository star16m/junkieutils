package junkie.utils.searchword.data.related;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import junkie.utils.searchword.data.SearchWord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class RelatedSearchWord implements Serializable {
	private static final long serialVersionUID = 7980122077702013815L;
	@Id
	@NotNull
	@Size(min = 1, max = 100)
	private String word;
	@OneToMany
	private List<SearchWord> relatedWordList = new ArrayList<>();
	
	public RelatedSearchWord(String word, List<SearchWord> relatedWordList) {
		this.word = word;
		this.relatedWordList = relatedWordList;
	}
}
