class Hello {
	public static void main(String[] args) {
		if (args == null || args.length < 1) {
			System.out.println("Hello!");
		} else {
			System.out.println("Hello " + args[0] + "!");
		}
	}
}
