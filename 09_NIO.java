import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

class UseNIO {
	public static void main(String... args) {
		String target = "file/test05.txt";
		w("[ Paths.get(\"" + target + "\") ]");
		Path p = Paths.get("file/test05.txt");
		w("-- p.getRoot()  --");
		w(p.getRoot());
		w("-- p.getName(0) --");
		w(p.getName(0));
		w("-- p.getFileName() --");
		w(p.getFileName());
		w("-- p.getNameCount() --");
		w(p.getNameCount());
		w("-- p.isAbsolute() --");
		w(p.isAbsolute());
		w("-- p.subpath(0, 1) --");
		w(p.subpath(0, 1));
		w("-- p.resolve(\"\") --");
		w(p.resolve(""));
		w("-- p.resolve(\"hoge\") --");
		w(p.resolve("hoge"));
		w("-- p.resolve(\"/etc/passwd\") --");
		w(p.resolve("/etc/passwd"));
		w("-- p.resolveSibling(\"\") --");
		w(p.resolveSibling(""));
		w("-- p.resolveSibling\"hoge\") --");
		w(p.resolveSibling("hoge"));
		w("-- p.resolveSibling(\"/etc/passwd\") --");
		w(p.resolveSibling("/etc/passwd"));
		w("-- Files.list(p) --");
		try {
			w(Files.list(p));
		} catch (IOException e) {
			w(e);
		}
		w("-- Files.exists(p) --");
		final Boolean b;
		w(b = Files.exists(p));
		w("-- Files.createFile(p) --");
		try {
			w(Files.createFile(p));
		} catch (IOException e) {
			w(e);
		}
		w("-- Paths.get(\"/etc/../etc/passwd\").normalize() --");
		w(Paths.get("/etc/../etc/passwd").normalize());
		w("-- Paths.get(\"etc\").relativize("
				+ "Paths.get(\"/etc/passwd\")) --");
		w(Paths.get("/etc").relativize(
				Paths.get("/etc/passwd")));

		try {
			Files.walkFileTree(
					Paths.get("."), new SimpleFileVisitor<Path>() {
						@Override
						public FileVisitResult postVisitDirectory(
								Path dir, IOException e) {
							w("[postVisitDirectory] " + dir);
							return FileVisitResult.CONTINUE;
						}

						@Override
						public FileVisitResult preVisitDirectory(
								Path dir, BasicFileAttributes a) {
							if (dir.getNameCount() > 1) {
								w("---- getNameCount() > 1  ----");
								w(dir.getName(1));
							}

							if (dir.getNameCount() > 1
									&& ".git".equals(
											dir.getName(1).toString())) {
								w("---- "
										+ dir.getName(1)
										+ "is skipped. ----");
								return FileVisitResult.SKIP_SUBTREE;
							}
							w("[preVisitDirectory] " + dir);
							return FileVisitResult.CONTINUE;
						}

						@Override
						public FileVisitResult visitFile(
								Path p, BasicFileAttributes a) {
							w("[visitFile] " + p);
							return FileVisitResult.CONTINUE;
						}

						@Override
						public FileVisitResult visitFileFailed(
								Path p, IOException e) {
							w("[visitFileFailed] " + p);
							return FileVisitResult.CONTINUE;
						}
					});
		} catch (IOException e) {
			w(e);
		}

		try (
				Stream<String> s = Files.lines(Paths.get("file/test04.txt"))) {
			s.forEach(str -> w("[Files.lines()] " + str));
		} catch (IOException e) {
			w(e);
		}

		try (
				Stream<Path> s = Files.walk(Paths.get("file"))) {
			s.forEach(path -> w("[Files.walk()] " + path));
		} catch (IOException e) {
			w(e);
		}
	}

	public static void w(Object s) {
		if (s == null) {
			w("null");
			return;
		}
		if (s instanceof String) {
			System.out.println(s);
		} else {
			System.out.println("[" + s.getClass().getName() + "] " + s);
		}
	}
}
