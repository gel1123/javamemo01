import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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
			BufferedReader r =
				new BufferedReader(
					new FileReader("file/test02.txt"));
			BufferedWriter w =
				new BufferedWriter(
					new FileWriter("file/test03.txt"));
		) {
			String s = "";
			while ((s = r.readLine()) != null) {
				w("[r.readLine()] "+s);
				w.write(s);
				w.newLine();
			}
		} catch (IOException e) {
			w(e);
		}
		try (
			BufferedReader r_test03 =
				new BufferedReader(
					new FileReader("file/test03.txt"))
		) {
			String s = "";
			while ((s = r_test03.readLine()) != null) {
				w("[r_test03.readLine()] "+s);
			}
			File file = new File("file/test03.txt");
			w("-- file.canRead() --");
			w(file.canRead());
			w("-- file.canWrite() --");
			w(file.canWrite());
			w("-- file.canExecute() --");
			w(file.canExecute());
		} catch (IOException e) {
			w(e);
		}
		try (
			FileReader in = new FileReader("file/test02.txt");
			FileWriter out = new FileWriter("file/test03.txt")
		) {
			int c = 0;
			while ((c=in.read()) != -1) {
				w("[read-test02.txt] " + ((char)c));
				out.write((char)c);
				out.flush();
			}
		} catch (IOException e) {
			w(e);
		}
		try (
			FileReader in = new FileReader("file/test03.txt");
		) {
			int c = 0;
			while ((c=in.read()) != -1) {
				w("[read-test03.txt] " + ((char)c));
			}
		} catch (IOException e) {
			w(e);
		}
		try (
			BufferedReader r = new BufferedReader(
				new FileReader(
					"file/test04.txt"));
		) {
			String s = "";
			int count = 0;
			while ((s = r.readLine()) != null) {
				w("[readLine-test04.txt] " + s);
				if (count++ == 1) {
					w("-- r.mark(100) --");
					r.mark(100);
				}
			}
			w("-- r.reset() --");
			r.reset();
			w("[readLine-test04.txt] " + r.readLine());
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
