package junkie.utils.searchword.data;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class SearchWord implements Serializable {
	private static final long serialVersionUID = -9117288843509814634L;

	@Id
	@NotNull
	@Size(min = 1, max = 255)
	private String word;

	@NotNull
	@Size(min = 1, max = 255)
	private String urlString;

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "word")
//	@JoinColumn
//	private List<RelatedSearchWord> relatedWordList = new ArrayList<>();

	public SearchWord(String word, String urlString) {
		this.word = word;
		this.urlString = urlString;
	}
}
