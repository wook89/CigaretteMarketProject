package CigarMarket.view;

import java.util.Scanner;

import CigarMarket.model.Customer;

public class ConsoleView {

	public void displayWelcome() {
		String welcome = "*****************************************\n"
				+ "*    Welcome to CigaretteMarket    *\n"
				+ "*****************************************";
		System.out.println(welcome);
	}

	public void inputCustomerInfo(Customer mCustomer) {
		Scanner sc = new Scanner(System.in);
		System.out.println("마켓을 사용하시려면 이름과 전화번호를 입력하세요.");
		System.out.println(">> 이름 : ");
		mCustomer.setName(sc.nextLine());
		System.out.println(">> 전화번호 :");
		mCustomer.setPhone(sc.nextLine());
		
	}

	public int selectMenu(String[] menuList) {
		displayMenu(menuList);
		int menu;
		do {
			menu = readNumber(">> 메뉴 선택 :");
			
		}while(menu < 0 || menu >= menuList.length);
		return menu;
	}

	private int readNumber(String message) {
		if(message != null || !message.equals(""))
			System.out.println(message);
		Scanner sc = new Scanner(System.in);
		try {
			int number = sc.nextInt();
			return number;
		} catch (Exception e) {
			System.out.println("숫자를 입력하세요 :");
			return readNumber(message);
		}
	}

	private void displayMenu(String[] menuList) {
		System.out.println("=========================================");
		for(int i=1;i<menuList.length;i++) {
			System.out.println(menuList[i]);
		}
		System.out.println(menuList[0]);
		System.out.println("=========================================");

	}

}
