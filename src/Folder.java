import java.util.ArrayList;
import java.util.Objects;

public class Folder {
	private ArrayList<Note> notes;
	private String name;

	public Folder(String name) {
		this.notes = new ArrayList<Note>();
		this.name = name;
	}

	public void addNote(Note e) {
		notes.add(e);
	}

	public String getName() {
		return this.name;
	}

	public ArrayList<Note> getNotes(){
		return this.notes;
	}

	@Override
	public String toString() {
		int nText = 0;
		int nImage = 0;

		for (Note n: notes) {
			if (n instanceof TextNote)
				nText += 1;
			else if (n instanceof ImageNote)
				nImage += 1;
		}

		return name + ":" + nText + ":" + nImage;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Note))
			return false;
		Folder other = (Folder) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
}
