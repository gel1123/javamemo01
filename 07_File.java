import java.io.*;


class UseFieAPI {
	public static void main(String... args) {
		File f = new File("file/test01.txt");
		f.deleteOnExit();
		w("-- f.delete() --");
		w(f.delete());
		w("-- f.createNewFile() --");
		try {
			w(f.createNewFile());
		} catch(IOException e){
			w(e);
		}
		w("-- f.exists() --");
		w(f.exists());
		w("-- f.getAbsolutePath() --");
		w(f.getAbsolutePath());
		w("-- f.getName() --");
		w(f.getName());
		w("-- f.getParent() --");
		w(f.getParent());
		w("-- f.isDirectory() --");
		w(f.isDirectory());
		w("-- f.isFile() --");
		w(f.isFile());
		w("-- f.lastModified() --");
		w(f.lastModified());
		w("-- f.length() --");
		w(f.isFile());
		w("-- f.list() --");
		w(f.list());
		w("-- f.listFiles() --");
		w(f.listFiles());
		try (
			BufferedReader r = new BufferedReader(new FileReader("file/test02.txt"));
			BufferedWriter w = new BufferedWriter(new FileWriter("file/test03.txt"))
		) {
			String s = "";
			while ((s = r.readLine()) != null) {
				w.write(s);
				w.newLine();
			}
		} catch (IOException e) {
			w(e);
		} 
	}
	public static void w(Object s) {
  if (s == null) {
			w("null");
			return;
		}
		System.out.println("["+s.getClass().getName()+"] "+s);
	}
}
