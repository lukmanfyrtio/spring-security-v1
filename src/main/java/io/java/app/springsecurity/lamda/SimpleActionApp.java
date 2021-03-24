package io.java.app.springsecurity.lamda;

public class SimpleActionApp {
	
	public static void main(String[] args) {
		SimpleAction simpleAction1 =new SimpleAction() {
			
			@Override
			public String action() {
				// TODO Auto-generated method stub
				return "simpleAction1";
			}
		};
		System.out.println(simpleAction1.action());
		
		SimpleAction simpleAction2 = () -> {
			return "simpleAction2";
		};
		
		System.out.println("lukman from lambda expresion "+simpleAction2.action());
	}
}
