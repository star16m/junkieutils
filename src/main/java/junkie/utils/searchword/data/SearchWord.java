package junkie.utils.searchword.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import junkie.utils.searchword.data.related.RelatedWord;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class SearchWord implements Serializable {
	private static final long serialVersionUID = -9117288843509814634L;

	@Id
	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "word")
	private String word;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "search_site")
	private String searchSite;

	@NotNull
	@Size(min = 1, max = 255)
	@Column(name = "url_string")
	private String urlString;

	@ManyToMany(cascade = CascadeType.REFRESH)
	@JoinTable(name = "search_word_related", joinColumns = @JoinColumn(name = "search_word"), inverseJoinColumns = @JoinColumn(name = "related_word"))
	@Column(name="related_word_list")
	private List<RelatedWord> relatedWordList = new ArrayList<>();

	public SearchWord(String word, String urlString) {
		this.word = word;
		this.urlString = urlString;
	}
}