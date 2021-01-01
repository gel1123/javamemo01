import java.io.File;


class UseFieAPI {
	public static void main(String... args) {
		File f = new File("file/test01.txt");
		f.deleteOnExit();
		w("-- f.delete() --");
		w(f.delete());
		//w("-- f.createNewFile() --");
		//w(f.createNewFile());
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
		w("--  --");
	}
	public static void w(Object s) {
		System.out.println("["+s.getClass().getName()+"] "+s);
	}
}
