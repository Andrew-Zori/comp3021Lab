package base;

import java.util.Date;
import java.util.Objects;
import java.io.Serializable;

public class Note implements Comparable<Note>, Serializable{
	private Date date;
	private String title;

	public Note(String title) {
		this.title = title;
		this.date = new Date(System.currentTimeMillis());
	}

	public String getTitle() {
		return this.title;
	}

	public Date getDate() {
		return this.date;
	}

	public String toString(){
		return date.toString() + "\t" + title;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Note))
			return false;
		Note other = (Note) obj;
		return Objects.equals(title, other.title);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title);
	}

	@Override
	public int compareTo(Note o) {
		int dateCompare = this.getDate().compareTo(o.getDate());
		return dateCompare;
	}
}
