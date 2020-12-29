import static pac1.Tekito.MES;
import static pac1.Tekito.hello; // <= メソッドのstatic importであっても、"hello()" とか書いたりはしない
//import static pac1.Tekito.*; // <= "static import" でもアスタリスクでの全読み込みは可能の模様

class UseStaticImport {

	private final static String MES = "static importしたものと名前被りが生じた場合は、static importはなかったことになります。\n（コンパイルエラーや実行時例外も発生せず、単にコンパイル時にstatic importが無視されるとのこと）";

	private final static String hello = "static importしたものと同名メンバだけで、メソッド or フィールドで食い違いがある場合は？";

	public static void main(String[] args) {
		System.out.println(MES);
		System.out.println(hello());
	}
}
