package bio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public class Base {

	String creaty = "" + "CREATE TABLE Questions\n" + "(\n" + "id integer NOT NULL,\n" + "Ques text,\n"
			+ "CONSTRAINT PK_Questions PRIMARY KEY (id)\n" + ");" + "\n" + "CREATE TABLE Answer\n" + "(\n"
			+ "id integer NOT NULL,\n" + "Prog text,\n" + "Answ text,\n" + "CONSTRAINT PK_Answer PRIMARY KEY (id)\n"
			+ ");";

	static final String DB_URL = "jdbc:h2:./db/Base";
	static final String DB_Driver = "org.h2.Driver";

	Base() {
		try {
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			Statement stmt = connection.createStatement();/*
			stmt.execute(creaty);
			stmt.execute("INSERT INTO Questions VALUES (1, 'Ваша операционная система');");
			stmt.execute("INSERT INTO Questions VALUES (2, 'Вам нужна онлайн программа?');");
			stmt.execute("INSERT INTO Questions VALUES (3, 'Только бесплатные?');");
			stmt.execute("INSERT INTO Questions VALUES (4, 'Вам нужно формтирование текста?');");
			stmt.execute("INSERT INTO Questions VALUES (5, 'Вам нужны дополнительные материалы<br>(картинки и т.п.) в документе?');");
			stmt.execute("INSERT INTO Questions VALUES (6, 'Вам нужна статистика по тексту?');");
			stmt.execute("INSERT INTO Questions VALUES (7, 'Вам важна скорость работы?');");
			stmt.execute("INSERT INTO Answer VALUES (1, 'Блокнот windows', '3NYNNNY');");
			stmt.execute("INSERT INTO Answer VALUES (2, 'jEdit', '3NYNNNY');");
			stmt.execute("INSERT INTO Answer VALUES (3, 'OpenOffice', '3NYYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (4, 'Sublime Text', '3NYNNYN');");
			stmt.execute("INSERT INTO Answer VALUES (5, 'AbiWord', '3NYYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (6, 'LibreOffice', '3NYYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (7, 'Rnote', '3NYYYNY');");
			stmt.execute("INSERT INTO Answer VALUES (8, 'Microsoft Word', '3NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (9, 'Microsoft Word', '3YYYYNN');");
			stmt.execute("INSERT INTO Answer VALUES (10, 'Microsoft Word', '4NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (11, 'Microsoft Word', '6NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (12, 'Microsoft Word', '7NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (13, 'Google Документы', '3NYYYYY');");
			stmt.execute("INSERT INTO Answer VALUES (14, 'Google Документы', '7NYYYYY');");
			stmt.execute("INSERT INTO Answer VALUES (15, 'Google Документы', '6NYYYYY');");
			stmt.execute("INSERT INTO Answer VALUES (16, 'Google Документы', '4NYYYYY');");
			stmt.execute("INSERT INTO Answer VALUES (17, 'Google Документы', '3YYYYYY');");
			stmt.execute("INSERT INTO Answer VALUES (18, 'iA Writer', '4NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (19, 'iA Writer', '3NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (20, 'iA Writer', '6NYYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (21, 'iA Writer', '7NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (22, 'Notebooks', '3NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (23, 'Notebooks', '7NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (24, 'Notebooks', '4NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (25, 'FocusWriter', '3NYYNYN');");
			stmt.execute("INSERT INTO Answer VALUES (26, 'FocusWriter', '4NYYNYN');");
			stmt.execute("INSERT INTO Answer VALUES (27, 'FocusWriter', '5NYYNYN');");
			stmt.execute("INSERT INTO Answer VALUES (28, 'Final Draft', '3NNYYYY');");
			stmt.execute("INSERT INTO Answer VALUES (29, 'Final Draft', '4NNYYYY');");
			stmt.execute("INSERT INTO Answer VALUES (30, 'Final Draft', '7NNYYYY');");
			stmt.execute("INSERT INTO Answer VALUES (31, 'Scrivener', '3NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (32, 'Scrivener', '4NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (33, 'Scrivener', '7NNYYYN');");
			stmt.execute("INSERT INTO Answer VALUES (34, 'Emacs', '5NYNNYY');");
			stmt.execute("INSERT INTO Answer VALUES (35, 'Emacs', '3NYNNYY');");
			stmt.execute("INSERT INTO Answer VALUES (36, 'Fb2edit', '5NYYYYY');");*/
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

	public ArrayList<String> getQwestions() {
		ArrayList<String> out = new ArrayList<String>();
		try {
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Questions");
			while (rs.next()) {
				out.add(rs.getString("Ques"));
				System.out.println(
						"" + rs.getRow() + ". " + "Ques = " + rs.getString("Ques"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
	
	public String getProg(String text){
		HashMap<String, String>  out = new HashMap<String, String>();
		try {
			Class.forName(DB_Driver);
			Connection connection = DriverManager.getConnection(DB_URL);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Answer");
			while (rs.next()) {
				out.put(rs.getString("answ"), rs.getString("Prog"));
				System.out.println(
						"" + rs.getRow() + ". " + "prog = " + rs.getString("Prog") + ", answ = " + rs.getString("answ"));
			}
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String out1 = "";
		int max = 0;
		
		for(int i = 0; i < out.size(); i++){
			String otvet = (String) out.keySet().toArray()[i];
			int compare = 0;
			for(int i1 = 0; i1 < 7; i1++){
				if(text.charAt(i1) == otvet.charAt(i1))
					compare++;
			}
			
			if(compare > max && text.toCharArray()[1] == 'Y' &&  otvet.toCharArray()[1] == 'Y'){
				out1 = out.get(out.keySet().toArray()[i]);
				max = compare;
			}
			
			if(compare > max && (otvet.toCharArray()[0] == text.toCharArray()[0])){
				if(text.toCharArray()[1] != 'Y' &&  otvet.toCharArray()[1] != 'Y'){
					out1 = out.get(out.keySet().toArray()[i]);
					max = compare;
				}
			}
			
		}
		
		return out1;
	}
}
