package base;

import java.io.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Serializable {
	private ArrayList<Folder> folders;

	public NoteBook() {
		folders = new ArrayList<Folder>();
	}

	/**
	 *
	 * Constructor of an object Notebook from an object serialization on disk
	 */
	public NoteBook(String file){
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try{
			fis = new FileInputStream(file);
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook) in.readObject();
			this.folders = n.folders;
			in.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;

	public boolean createImageNote(String folderName, String title) {
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}

	public boolean createTextNote(String folderName, String title) {
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}

	public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}

	public ArrayList<Folder> getFolders() {
		return this.folders;
	}

	public boolean insertNote(String folderName, Note note) {
		Folder f = null;
		for (Folder f1: folders) {
			if (f1.getName().equals(folderName)) f = f1;
		}

		if (f == null) {
			f = new Folder(folderName);
			this.folders.add(f);
			}

		for (Note n: f.getNotes()){
			if (n.getTitle().equals(note.getTitle())) {
				System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
				return false;
			}
		}

		f.addNote(note);
		return true;
	}

	public void sortFolders(){
		Collections.sort(folders);
	}

	public List<Note> searchNotes(String keywords){
		List<Note> conditionsMet = new ArrayList<Note>();
		for (Folder f: folders){
			List<Note> notesPerFolder = new ArrayList<Note>(f.searchNotes(keywords));
			for (Note n: notesPerFolder){
				System.out.println(n.getTitle());
			}
			conditionsMet.addAll(notesPerFolder);
		}
		return conditionsMet;
	}

	/**
	 * method to save the NoteBook instance to file
	 *
	 * @param file, the path of the file where to save the object serialization
	 * @return true if save on file is successful, else false
	 */
	public boolean save(String file){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream(file);
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
