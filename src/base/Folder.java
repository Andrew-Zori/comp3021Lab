package base;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Collections;
import java.util.List;

public class Folder implements Comparable<Folder>{
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

	@Override
	public int compareTo(Folder o) {
		int nameCompare = this.getName().compareTo(o.getName());
		return nameCompare;
	}

	public void sortNotes(){
		Collections.sort(this.notes);
	}
	public List<Note> searchNotes(String keywords){
		String[] keywordsList = keywords.split(" ");
		List<Note> hitNotes = new ArrayList<Note>();
		for (Note n: notes) {
			boolean conditionsMet = true;
			for (int i = 0; i < keywordsList.length; i++) {
				if ((keywordsList.length > i + 2)) {
					if ((keywordsList[i + 1].toLowerCase().equals("or"))) {
						if (n.getTitle().contains(keywordsList[i]) || n.getTitle().contains(keywordsList[i + 2])) {
							i += 2;
							continue;
						}

						if (n instanceof TextNote) {
							if ((((TextNote) n).getContent().contains(keywordsList[i])) || (((TextNote) n).getContent().contains(keywordsList[i+2]))) {
								i += 2;
								continue;
							}
						}
					}
				}
				else{
					if (n.getTitle().contains(keywordsList[i])){
						continue;
					}

					if (n instanceof TextNote){
						if (((TextNote) n).getContent().contains(keywordsList[i])){
							continue;
						}
					}
				}
				conditionsMet = false;
				break;
			}
			if (conditionsMet){
				hitNotes.add(n);
			}
		}
		for (Note n: hitNotes){
			System.out.println(n.getTitle());
		}
		return hitNotes;
	}
}
