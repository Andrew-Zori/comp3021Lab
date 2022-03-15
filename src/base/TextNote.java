package base;
import java.io.*;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;


public class TextNote extends Note{
	private String content;

	public TextNote(String title){
		super(title);
	}

	public TextNote(String title, String content) {
		super(title);
		this.content = content;
	}

	/**
	 *load a TextNote from File f
	 *
	 * the title of the TextNote is the name of the file
	 * the content of the TextNote is the content of the file
	 *
	 * @param f
	 */
	public TextNote(File f){
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	private String getTextFromFile(String absolutePath){
		String result = "";

		FileInputStream fis = null;
		ObjectInputStream in = null;
		try{
			fis = new FileInputStream(absolutePath);
			in = new ObjectInputStream(fis);
			result = ((TextNote) in.readObject()).getContent();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * export text note to file
	 *
	 * @param pathFolder path of the folder where to export the note
	 *                   the file has to be named as the title of the note with extension ".txt"
	 *
	 *                   if the title contains white spaces " " they has to be replaced with underscores "_"
	 */
	public void exportTextToFile(String pathFolder){
		if (pathFolder.equals("")){
			pathFolder = ".";
		}
		String fileName = this.getTitle().replaceAll(" ", "_");
		File file = new File(pathFolder + File.separator + fileName + ".txt");

		try{
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(String.valueOf(this));
			writer.flush();
			writer.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public String getContent(){
		return this.content;
	}
}
