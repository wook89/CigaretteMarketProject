package CigarMarket.view;

import java.util.Scanner;

import CigarMarket.model.Cart;
import CigarMarket.model.CigarStorage;
import CigarMarket.model.Customer;

public class ConsoleView {

    public void displayWelcome() {
        String welcome = "*****************************************\n"
                + "*    Welcome to CigaretteMarket         *\n"
                + "*****************************************";
        System.out.println(welcome);
    }

    public void inputCustomerInfo(Customer mCustomer) {
        Scanner sc = new Scanner(System.in);
        System.out.println("고객님 정보를 입력해주세요. 이름과 전화번호를 입력하세요.");
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
        } while (menu < 0 || menu >= menuList.length);
        return menu;
    }
    
    // 숫자를 입력받는 메서드 (숫자가 아니면 try/catch로 처리하고 다시 입력받기)
    public int readNumber(String message) {
        if (message != null || !message.equals(""))
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
        for (int i = 1; i < menuList.length; i++) {
            System.out.println(menuList[i]);
        }
        System.out.println(menuList[0]);
        System.out.println("=========================================");
    }

    public void displayCigarInfo(CigarStorage cigarStorage) {
        for (int i = 0; i < cigarStorage.getNumCigars(); i++) {
            String cigarInfo = cigarStorage.getCigarInfo(i);
            System.out.println("ID: " + cigarInfo);
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String inputString(String message) {
        Scanner sc = new Scanner(System.in);
        System.out.print(message);
        return sc.nextLine();
    }

    public void inputDeliveryInfo(Customer customer) {
        if (customer.getEmail() == null) {
            Scanner sc = new Scanner(System.in);
            System.out.println("배송을 위해 이메일 주소와 집 주소를 입력해주세요.");
            System.out.print(">> 이메일 :");
            customer.setEmail(sc.nextLine());
            System.out.print(">> 주소 :");
            customer.setAddress(sc.nextLine());
        }
    }

    public void displayOrder(Cart cart, Customer customer) {
        System.out.println();
        // 장바구니 내용 출력
        System.out.println("***** 주문한 상품 ******");
        displayCart(cart);
        // 고객 정보 출력 - 이름, 전화번호, 주소, 이메일
        System.out.println("***** 배송 정보 *****");
        System.out.println(">> 이름 : " + customer.getName());
        System.out.println(">> 전화번호 : " + customer.getPhone());
        System.out.println(">> 이메일 : " + customer.getEmail());
        System.out.println(">> 주소 : " + customer.getAddress());
        System.out.println();
    }

    public void displayCart(Cart cart) {
        if (cart.isEmpty()) {
            System.out.println(">> 장바구니가 비어 있습니다.");
            return;
        }
        for (int i = 0; i < cart.getNumItems(); i++) {
            System.out.println("ID: " + cart.getItemInfo(i) + "");
        }
        System.out.println("총 금액 : " + cart.getTotalPrice() + "원");
    }

    public boolean askConfirm(String message, String yes) {
        System.out.print(message);
        Scanner sc = new Scanner(System.in);
        return sc.nextLine().equals(yes);
    }

    public int selectCigarId(CigarStorage mCigarStorage) {
        int cigarId;
        boolean result;
        do {
            cigarId = readNumber("상품 ID 입력 : ");
            result = mCigarStorage.isValidItem(cigarId);
            if (!result) {
                System.out.print("잘못된 상품 ID입니다.");
            }
        } while (!result);
        return cigarId;
    }

    public int inputQuantity(int min, int max) {
        int number;
        do {
            number = readNumber(">> 수량 입력 (" + min + " ~ " + max + "): ");
            if (number < min || number > max) {
                System.out.println("잘못된 수량입니다.");
            }
        } while (number < min || number > max);
        return number;
    }
}
