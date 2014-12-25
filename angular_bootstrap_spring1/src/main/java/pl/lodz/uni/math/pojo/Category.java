package pl.lodz.uni.math.pojo;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

import pl.lodz.uni.math.engine.CategoryEngine;
import pl.lodz.uni.math.engine.NoteEngine;

@MappedSuperclass
public class Category implements Serializable, Cloneable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6911420680446731876L;
	@Id
	@GeneratedValue
	@Column(name = "CategoryId")
	private int categoryId;
	@Column(name = "CategoryName")
	private String categoryName;
	@OneToMany(mappedBy = "category", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<NoteEngine> notes;

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<NoteEngine> getNotes() {
		return notes;
	}

	public void setNotes(Set<NoteEngine> notes) {
		this.notes = notes;
	}

	public Category(String categoryName) {
		super();
		this.categoryName = categoryName;
	}

	public Category() {
		super();
	}

	public Category(int categoryId, String categoryName, Set<NoteEngine> notes) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.notes = notes;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + categoryId;
		result = prime * result
				+ ((categoryName == null) ? 0 : categoryName.hashCode());
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
		Category other = (Category) obj;
		if (categoryId != other.categoryId)
			return false;
		if (categoryName == null) {
			if (other.categoryName != null)
				return false;
		} else if (!categoryName.equals(other.categoryName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName="
				+ categoryName + "]";
	}

	@Override
	public CategoryEngine clone() {
		return new CategoryEngine(categoryId, categoryName, notes);
	}

}
