package pl.lodz.uni.math.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import pl.lodz.uni.math.engine.CategoryEngine;
import pl.lodz.uni.math.engine.NoteEngine;
import pl.lodz.uni.math.engine.RateEngine;
import pl.lodz.uni.math.engine.UserEngine;

@MappedSuperclass
public class Note implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3697685742277708187L;
	@Id
	@GeneratedValue
	@Column(name = "NoteId")
	private int noteId;
	@Column(name = "linkId")
	private long linkId;
	@Column(name = "NoteTitle")
	private String noteTitle;
	@Column(name = "NoteText")
	private String noteText;
	@Column(name = "CreateDate")
	private Date createDate;
	@ManyToOne
	@JoinColumn(name = "UserId")
	private UserEngine author;
	@OneToOne
	@JoinColumn(name = "notes")
	private CategoryEngine category;
	@OneToMany(mappedBy = "note", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<RateEngine> rates;

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteText() {
		return noteText;
	}

	public void setNoteText(String noteText) {
		this.noteText = noteText;
	}

	public UserEngine getAuthor() {
		return author;
	}

	public void setAuthor(UserEngine author) {
		this.author = author;
	}

	public CategoryEngine getCategory() {
		return category;
	}

	public void setCategory(CategoryEngine category) {
		this.category = category;
	}

	public Set<RateEngine> getRates() {
		return rates;
	}

	public void setRates(Set<RateEngine> rates) {
		this.rates = rates;
	}

	public long getLinkId() {
		return linkId;
	}

	public void setLinkId(long linkId) {
		this.linkId = linkId;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Note() {
	}

	public Note(int noteId, long linkId, Date createDate, String noteTitle,
			String noteText, UserEngine author, CategoryEngine category,
			Set<RateEngine> rates) {
		super();
		this.noteId = noteId;
		this.linkId = linkId;
		this.createDate = createDate;
		this.noteTitle = noteTitle;
		this.noteText = noteText;
		this.author = author;
		this.category = category;
		this.rates = rates;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noteId;
		result = prime * result
				+ ((noteText == null) ? 0 : noteText.hashCode());
		result = prime * result
				+ ((noteTitle == null) ? 0 : noteTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (noteId != other.noteId)
			return false;
		if (noteText == null) {
			if (other.noteText != null)
				return false;
		} else if (!noteText.equals(other.noteText))
			return false;
		if (noteTitle == null) {
			if (other.noteTitle != null)
				return false;
		} else if (!noteTitle.equals(other.noteTitle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", linkId=" + linkId + ", noteTitle="
				+ noteTitle + ", noteText=" + noteText + "]";
	}

	@Override
	public NoteEngine clone() {
		return new NoteEngine(noteId, linkId, createDate, noteTitle, noteText,
				author, category, rates);
	}

}
