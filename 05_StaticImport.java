import static pac01.Tekito01.MES;
import static pac01.Tekito01.hello; // <= hello()メソッド。メソッドのstatic importであっても、"hello()" とか書いたりはしない
//import static pac1.Tekito.*; // <= "static import" でもアスタリスクでの全読み込みは可能の模様
import static pac01.Tekito02.hello; // <= こっちはフィールド変数のhello

class UseStaticImport {

	private final static String MES = "static importしたものと名前被りが生じた場合は、static importはなかったことになります。\n（コンパイルエラーや実行時例外も発生せず、単にコンパイル時にstatic importが無視されるとのこと）\n※１．ただし、同名フィールドやメソッドを複数static importすると、コンパイルエラーが生じる模様\n※２．ただしフィールド or メンバに食い違いがあれば、同名メンバをstatic importすることはできる";

	public static void main(String[] args) {
		System.out.println(MES);
		System.out.println(hello());
		System.out.println(hello);
	}
}
