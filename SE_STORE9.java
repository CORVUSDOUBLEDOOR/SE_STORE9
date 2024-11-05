import java.io.*;
import java.util.*;

public class SE_STORE9 {
    public static Product[] product;
    public static Member[] member;
    public static Cart[] cart;

    public static void main(String[] args) throws IOException {
        File txtProduct = new File("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\PRODUCT (4).txt");
        ;
        Scanner countNum = new Scanner(System.in);
        String typeNumber;
        boolean loopOrNot = true;
        Scanner reader = new Scanner(txtProduct);
        int countProduct = 0;
        File category = new File("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\CATEGORY.txt");
        ArrayList<String> nameCategory = new ArrayList<String>();
        String typeCategory;
        Scanner inputCategory = new Scanner(category);
        boolean quit = false;
        ArrayList<String> hostIdCategory = new ArrayList<String>();
        boolean loopCategory = true;
        Scanner inputTypeCategory = new Scanner(System.in);
        boolean loopDisplay1 = true;
        boolean loopLogin = true;
        Scanner inputEmail = new Scanner(System.in);
        boolean checkUser = false;
        Scanner inputPassword = new Scanner(System.in);
        int correctIndex = 0;
        String role = "";
        boolean loopAddMember = true;
        double discount;
        int countTypeProduct = 0;

        while (reader.hasNextLine()) {
            String nextLine = reader.nextLine();
            countTypeProduct++;
        }
        reader.close();

        reader = new Scanner(txtProduct);
        product = new Product[countTypeProduct];
        int getCountTheProduct = 0;

        while (reader.hasNextLine()) {
            String[] line = reader.nextLine().split("\\s+");
            String id = line[0];
            String nameProduct = line[1];
            Double cost = Double.parseDouble(line[2].replace("$", ""));
            int quantity = (Integer.parseInt(line[3]));
            String idCategory = (line[4]);
            product[getCountTheProduct] = new Product(id, nameProduct, cost, quantity, idCategory);
            getCountTheProduct++;
        }

        while (inputCategory.hasNextLine()) {
            hostIdCategory.add(inputCategory.next());
            nameCategory.add(inputCategory.nextLine());
        }
        while (loopDisplay1 == true) {
            System.out.print("\t===== SE STORE =====\t\t\t\t\t\n" +
                    "\t1. Login\t\t\t\t\n" +
                    "\t2. Exit\t\t\t\n" +
                    "\t====================\t\t\t\t\t\n" +
                    "\tSelect (1-2) : ");
            typeNumber = countNum.nextLine();
            int countErrorUser = 0;
            loopLogin = true;
            if (typeNumber.equalsIgnoreCase("1")) {
                readMember();
                while (loopLogin == true) {
                    role = "";
                    String Email = "";
                    String password = "";
                    System.out.print("\t===== LOGIN =====\n" +
                            "\tEmail : ");
                    Email = inputEmail.nextLine();
                    System.out.print("\tPassword : ");
                    password = inputPassword.nextLine();
                    boolean checkEmailCorrect = false;
                    int emailIndex = -1;
                    StringBuilder passWord = new StringBuilder();
                    for (int i = 0; i < member.length; i++) {
                        if (Email.equalsIgnoreCase(member[i].getEmailUser())) {
                            passWord.append(member[i].getPasswordUser().charAt(9));
                            passWord.append(member[i].getPasswordUser().charAt(10));
                            passWord.append(member[i].getPasswordUser().charAt(13));
                            passWord.append(member[i].getPasswordUser().charAt(14));
                            passWord.append(member[i].getPasswordUser().charAt(15));
                            passWord.append(member[i].getPasswordUser().charAt(16));
                            checkEmailCorrect = true;
                            emailIndex = i;
                        }
                    }
                    if (checkEmailCorrect) {
                        if (checkEmailCorrect == true && emailIndex != -1) {
                            int loopCheck = 1;
                            if (password.equals(passWord.toString())) {
                                checkUser = true;
                                correctIndex = emailIndex;
                                emailIndex = member.length;
                            } else {
                                checkUser = false;
                                countErrorUser++;
                            }
                        } else {
                            checkUser = false;
                            countErrorUser++;
                        }
                    } else {
                        countErrorUser++;
                        checkEmailCorrect = false;
                    }
                    if (!checkUser || !checkEmailCorrect) {
                        System.out.println("\t====================\n" +
                                "\tError! - Email or Password is Incorrect (" + countErrorUser + ")");
                        checkUser = false;
                        Email = "";
                        password = "";
                        if (countErrorUser == 3) {
                            loopLogin = false;
                            Email = "";
                            password = "";
                        }
                    }

                    //check role
                    role = "";

                    if (checkUser == true) {
                        if (member[correctIndex].getPasswordUser().charAt(6) == '0') {
                            role = "Staff";
                        } else if (member[correctIndex].getPasswordUser().charAt(6) == '1') {
                            role = "Regular";
                        } else if (member[correctIndex].getPasswordUser().charAt(6) == '2') {
                            role = "Silver";
                        } else if (member[correctIndex].getPasswordUser().charAt(6) == '3') {
                            role = "Gold";
                        }
                        if (member[correctIndex].getPasswordUser().charAt(2) == '1') {
                            String emailDisplay = member[correctIndex].getEmailUser().replaceAll("^([\\w]{2})(.*)(@[\\w]{2})(.*)$", "$1***$3***");
                            double point = Double.parseDouble(member[correctIndex].getPointUser());
                            int pointDisplay = (int) point;
                            StringBuilder phoneDisplay = new StringBuilder(member[correctIndex].getPhoneUser());
                            phoneDisplay.insert(3, '-');
                            phoneDisplay.insert(7, '-');
                            loopOrNot = true;
                            if (!role.equalsIgnoreCase("Staff")) {
                                while (loopOrNot == true) {
                                    System.out.println("\t===== SE STORE =====\n" +
                                            "\tHello, " + member[correctIndex].getLastnameUser().charAt(0) + ". " + member[correctIndex].getFirstnameUser() + "\t(" + role + ")\t\t\n" +
                                            "\tEmail: " + emailDisplay + "\t\t\t\n" +
                                            "\tPhone: " + phoneDisplay + "\t\t\t\n" +
                                            "\tYou have " + pointDisplay + " Point\t\t\t\n" +
                                            "\t====================");
                                    System.out.printf(
                                            "\t===== SE STORE =====\t\t\t\n" +

                                                    "\t1. Show Category\t\t\t\n" +
                                                    "\t2. Order Product\n" +
                                                    "\t3. Search Product\n" +
                                                    "\t4. Exit\t\t\t\n" +
                                                    "\t====================\t\t\t\n" +
                                                    "\tSelect (1-4) :\t");
                                    typeNumber = countNum.nextLine();
                                    if (typeNumber.equalsIgnoreCase("1")) {
                                        loopCategory = true;
                                        while (loopCategory) {
                                            System.out.printf("\t=========== SE STORE's Product Categories ===========\t\t\t\n" +
                                                    "\t#\t    Category\n");
                                            for (countProduct = 0; countProduct < nameCategory.size(); countProduct++) {
                                                System.out.printf("\t%-3d %-15s\n",
                                                        countProduct + 1,
                                                        nameCategory.get(countProduct));
                                            }
                                            System.out.printf("\t=========================================\t\t\t\n" +
                                                    "\tSelect Category to Show Product (1-" + (nameCategory.size()) + ") or Q for exit\n" +
                                                    "\tSelect : ");
                                            typeCategory = inputTypeCategory.nextLine();
                                            int choosedTypeCategory = -1;
                                            if (typeCategory.equalsIgnoreCase("Q")) {
                                                loopCategory = false;
                                            } else {
                                                try {
                                                    choosedTypeCategory = Integer.parseInt(typeCategory);
                                                    if (choosedTypeCategory >= 1 && choosedTypeCategory <= nameCategory.size()) {
                                                        System.out.println("\t============     " + nameCategory.get(choosedTypeCategory - 1) + "     ============\t\t\t");
                                                        SE_STORE9 seStore6 = new SE_STORE9();
                                                        seStore6.printProduct(product, role, hostIdCategory, choosedTypeCategory);

                                                        while (!typeCategory.equalsIgnoreCase("q")) {
                                                            System.out.print("\t================================\t\t\t\n" +
                                                                    "\t1. Show Name By DESC\n" +
                                                                    "\t2. Show Quantity By ASC\n" +
                                                                    "\tor Press Q to Exit : ");
                                                            typeCategory = inputTypeCategory.nextLine();
                                                            if (typeCategory.equals("1")) {
                                                                System.out.println("\t============     " + nameCategory.get(choosedTypeCategory - 1) + "     ============\t\t\t");
                                                                Product[] nameSorting = Arrays.stream(product).sorted(Comparator.comparing(Product::getNameProduct,
                                                                                Comparator.reverseOrder()).thenComparing(Product::getQuantity, Comparator.reverseOrder()))
                                                                        .toArray(Product[]::new);
                                                                seStore6.printProduct(nameSorting, role, hostIdCategory, choosedTypeCategory);
                                                            } else if (typeCategory.equals("2")) {
                                                                System.out.println("\t============     " + nameCategory.get(choosedTypeCategory - 1) + "     ============\t\t\t");
                                                                Product[] nameSorting = Arrays.stream(product).sorted(Comparator.comparingInt(Product::getQuantity)
                                                                                .thenComparing(p -> p.getNameProduct().toLowerCase()))
                                                                        .toArray(Product[]::new);
                                                                seStore6.printProduct(nameSorting, role, hostIdCategory, choosedTypeCategory);
                                                            }
                                                        }
                                                    } else {
                                                        System.out.println("\tInvalid number, please enter a value between 1-" + nameCategory.size());
                                                    }
                                                } catch (NumberFormatException e) {
                                                    System.out.println("\tInvalid input, please enter a valid number 1-" + nameCategory.size() + " or Q/q.");
                                                }
                                            }
                                        }
                                        System.out.printf("\t===========================================\t\n");
                                    } else if (typeNumber.equalsIgnoreCase("2")) {
                                        SE_STORE9 seStore9 = new SE_STORE9();
                                        seStore9.printAllProduct(product, role);
                                        boolean loopInputOrder = true;
                                        while (loopInputOrder == true) {
                                            System.out.print("\tEnter the product number followed by the quantity.\t\t\t\t\t\n" +
                                                "\t1. How to Order\n" +
                                                "\t2. List Products\n" +
                                                "\t3. My Cart\n"+
                                                "\tQ. Exit\n");

                                            String inputOrder;
                                            Scanner scannerInputPreOrder = new Scanner(System.in);
                                            System.out.print("\tEnter : ");
                                            inputOrder = scannerInputPreOrder.nextLine();
                                            String[] line = inputOrder.split("\\s+");
                                            if (line.length == 1) {
                                                if (line[0].equalsIgnoreCase("1")) {
                                                    System.out.print("\tHow to Order:\n" +
                                                            "\tTo Add Product: \n" +
                                                            "\t\tEnter the product number followed by the quantity.\t\n" +
                                                            "\t\tExample: 1 50 (Adds 50 chips)\n" +
                                                            "\tTo Adjust Quantity:\n" +
                                                            "\t\t+ to add more items: 1 +50 (Adds 50 more chips)\n" +
                                                            "\t\t- to reduce items: 1 -50 (Removes 50 chips)\n");
                                                } else if (line[0].equalsIgnoreCase("2")) {
                                                    seStore9.printAllProduct(product, role);
                                                } else if (line[0].equalsIgnoreCase("3")) {
                                                    boolean loopMyCart = true;
                                                    while(loopMyCart == true) {
                                                        System.out.print("\t====================\n" +
                                                                "\tMy Cart\n" +
                                                                "\t====================\n");
                                                        loadFileCart();
                                                        ArrayList<Integer> userIdIndexCart = new ArrayList<Integer>();
                                                        for (int i = 0; i < cart.length; i++) {
                                                            if (cart[i].getUserID().equalsIgnoreCase(member[correctIndex].getIdUser())) {
                                                                userIdIndexCart.add(i);
                                                            }
                                                        }
                                                        System.out.print("\t#\tName\t        Quantity\tTotals (฿)\n");
                                                        int countOrder = 1;
                                                        double priceTotal = 0.00;
                                                        for (int i = 0; i < userIdIndexCart.size(); i++) {
                                                            String nameProduct = "";
                                                            for (int j = 0; j < product.length; j++) {
                                                                if (cart[userIdIndexCart.get(i)].getProductID().equalsIgnoreCase(product[j].getId())) {
                                                                    nameProduct = product[j].getNameProduct();
                                                                }
                                                            }

                                                            System.out.printf("\t%-3d %-15s %-13d %-8.2f\n",
                                                                    countOrder,
                                                                    nameProduct,
                                                                    cart[userIdIndexCart.get(i)].getQuantity(),
                                                                    ((product[i].getCost() * 34) * cart[userIdIndexCart.get(i)].getQuantity()));
                                                            countOrder++;
                                                            priceTotal += ((product[i].getCost() * 34) * cart[userIdIndexCart.get(i)].getQuantity());
                                                        }
                                                        System.out.print("\t===========================================\t\n" +
                                                                "\tPrice: " + priceTotal + " Baht\n" +
                                                                "\t===========================================\n");
                                                        System.out.print("\t1. Checkout\n" +
                                                                "\t2. Back\n" +
                                                                "\tEnter : ");
                                                        Scanner inputTypeChooser = new Scanner(System.in);
                                                        String typeChooser = inputTypeChooser.next();

                                                        boolean loopCheckout = true;
                                                        while (loopCheckout == true) {
                                                            if (typeChooser.equalsIgnoreCase("1")) {
                                                                System.out.print("\t====================\n" +
                                                                        "\tCheckout\n" +
                                                                        "\t====================\n");
                                                                printMyCart(userIdIndexCart);
                                                                System.out.print("\t===========================================\t\n" +
                                                                        "\tPrice: " + priceTotal + "\n" +
                                                                        "\tShipping Fee: 50.00\n" +
                                                                        "\tTotal: " + (priceTotal + 50.00) + "\n" +
                                                                        "\t===========================================\t\n" +
                                                                        "\t1. Confirm\n" +
                                                                        "\t2. Cancel\n" +
                                                                        "\tEnter : ");
                                                                typeChooser = inputTypeChooser.next();
                                                                if (typeChooser.equalsIgnoreCase("1")){
                                                                    System.out.print("\tThank you for your purchase\n");
                                                                    loopMyCart = false;
                                                                    loopInputOrder = false;
                                                                    loopCheckout = false;
                                                                    break;
                                                                } else if (typeChooser.equalsIgnoreCase("2")){
                                                                    System.out.print("\t====================\n" +
                                                                            "\tAdd Something to Cart\n" +
                                                                            "\t====================\n");
                                                                    loopCheckout = false;
                                                                    loopMyCart = false;
                                                                    break;
                                                                }
                                                            } else if(typeChooser.equalsIgnoreCase("2")){
                                                                System.out.print("\t====================\n" +
                                                                        "\tAdd Something to Cart\n" +
                                                                        "\t====================\n");
                                                                loopMyCart = false;
                                                                break;
                                                            }
                                                        }
                                                    }
                                                } else if (line[0].equalsIgnoreCase("q")) {
                                                    System.out.println("\tYour cart has been saved!");
                                                    loopInputOrder = false;
                                                    break;
                                                } else {
                                                    System.out.println("\tYour input is invalid!");
                                                }
                                            } else if (line.length == 2) {
                                                boolean invalid = false;
                                                int numberProduct = Integer.parseInt(line[0]);
                                                boolean newOrder = true;
                                                int index_Order = -1;
                                                loadFileCart();
                                                for (int i = 0; i < cart.length; i++) {
                                                    if (member[correctIndex].getIdUser().equalsIgnoreCase(cart[i].getUserID())
                                                            && cart[i].getProductID().equalsIgnoreCase(product[numberProduct - 1].getId())) {
                                                        newOrder = false;
                                                        index_Order = i;
                                                    }
                                                }
                                                if (numberProduct <= product.length) {
                                                    if (line[1].charAt(0) == '+') {
                                                        int numberQuantityProduct = Integer.parseInt(line[1].substring(1));
                                                        if (newOrder) {
                                                            if (product[Integer.parseInt(line[0]) - 1].getQuantity() - numberQuantityProduct < 0) {
                                                                invalid = true;
                                                            } else {
                                                                createNewOrder(member[correctIndex].getIdUser(), product[Integer.parseInt(line[0]) - 1].getId(), numberQuantityProduct);

                                                                product[Integer.parseInt(line[0]) - 1].setQuantity(product[Integer.parseInt(line[0]) - 1].getQuantity() - numberQuantityProduct);
                                                                updateFile_product();
                                                            }
                                                        } else {
                                                            if (product[Integer.parseInt(line[0]) - 1].getQuantity() - numberQuantityProduct < 0) {
                                                                invalid = true;
                                                            } else {
                                                                cart[index_Order].setQuantity(cart[index_Order].getQuantity() + numberQuantityProduct);
                                                                updateFile_Order();

                                                                product[Integer.parseInt(line[0]) - 1].setQuantity(product[Integer.parseInt(line[0]) - 1].getQuantity() - numberQuantityProduct);
                                                                updateFile_product();
                                                            }
                                                        }
                                                    } else if (line[1].charAt(0) == '-') {
                                                        int quantity_input = Integer.parseInt(line[1].substring(1));
                                                        if (!newOrder) {
                                                            if (cart[index_Order].getQuantity() - quantity_input <= 0) {
                                                                int quantity_current = cart[index_Order].getQuantity();

                                                                cart[index_Order].setQuantity(cart[index_Order].getQuantity() - quantity_input);
                                                                updateFile_Order();

                                                                product[Integer.parseInt(line[0]) - 1].setQuantity(product[Integer.parseInt(line[0]) - 1].getQuantity() + quantity_current);
                                                                updateFile_product();
                                                            } else {
                                                                cart[index_Order].setQuantity(cart[index_Order].getQuantity() - quantity_input);
                                                                updateFile_Order();

                                                                product[Integer.parseInt(line[0]) - 1].setQuantity(product[Integer.parseInt(line[0]) - 1].getQuantity() + quantity_input);
                                                                updateFile_product();
                                                            }
                                                        } else {
                                                            invalid = true;
                                                        }
                                                    } else if (Integer.parseInt(line[1]) >= 0) {
                                                        int quantity_input = Integer.parseInt(line[1]);

                                                        if (newOrder) {
                                                            if (product[Integer.parseInt(line[0]) - 1].getQuantity() - quantity_input < 0) {
                                                                invalid = true;
                                                            } else {
                                                                createNewOrder(member[correctIndex].getIdUser(), product[Integer.parseInt(line[0]) - 1].getId(), quantity_input);

                                                                product[Integer.parseInt(line[0]) - 1].setQuantity(product[Integer.parseInt(line[0]) - 1].getQuantity() - quantity_input);
                                                                updateFile_product();
                                                            }
                                                        } else {
                                                            int quantity_current = cart[index_Order].getQuantity() + product[Integer.parseInt(line[0]) - 1].getQuantity();

                                                            if (quantity_current - quantity_input < 0) {
                                                                invalid = true;
                                                            } else {
                                                                cart[index_Order].setQuantity(quantity_input);
                                                                updateFile_Order();

                                                                product[Integer.parseInt(line[0]) - 1].setQuantity(quantity_current - quantity_input);
                                                                updateFile_product();
                                                            }
                                                        }
                                                    }
                                                } else {
                                                    invalid = true;
                                                }
                                                if (invalid == true) {
                                                    System.out.println("\tYour input is invalid!");
                                                }
                                            } else {
                                                System.out.println("\tYour input is invalid!");
                                            }
                                        }
                                    } else if (typeNumber.equalsIgnoreCase("3")) {
                                        SE_STORE9 seStore9 = new SE_STORE9();
                                        Scanner inputseach = new Scanner(System.in);
                                        System.out.print("\t====================\n" +
                                                "\tSearch Product\n" +
                                                "\t====================\n" +
                                                "\tType Product Name: ");
                                        String searhProductName = inputseach.nextLine();
                                        System.out.print("\t====================\n");
                                        boolean searchingTrue = false;
                                        ArrayList<Integer>indexSearch = new ArrayList<Integer>();
                                        for(int i = 0 ; i < product.length; i++){
                                            int countingTrue = 0;
                                            for(int j = 0; j < searhProductName.length(); j++){
                                                try {
                                                    if(searhProductName.toLowerCase().charAt(j) == product[i].getNameProduct().toLowerCase().charAt(j)){
                                                        countingTrue++;
                                                        if(countingTrue == searhProductName.length()){
                                                            searchingTrue = true;
                                                            indexSearch.add(i);
                                                        }
                                                    }
                                                } catch (StringIndexOutOfBoundsException s){
                                                    searchingTrue = false;
                                                }

                                            }
                                        }
                                        if(searchingTrue == true){
                                            seStore9.printSearchProduct(product, role, indexSearch);
                                        }else{
                                            System.out.print("\tSorry Product Not found\n" +
                                                    "\t====================");
                                        }
                                    } else if (typeNumber.equalsIgnoreCase("4")) {
                                        loopLogin = false;
                                        loopOrNot = false;
                                    } else {
                                        System.out.println("\tYour input is invalid, Please try again!!!");
                                    }
                                }
                            } else {
                                while (loopOrNot == true) {
                                    System.out.println("\t===== SE STORE =====\n" +
                                            "\tHello, " + member[correctIndex].getLastnameUser().charAt(0) +
                                            ". " + member[correctIndex].getFirstnameUser() +
                                            "\t(" + role + ")\t\t\n" +
                                            "\tEmail: " + emailDisplay + "\t\t\t\n" +
                                            "\tPhone: " + phoneDisplay + "\t\t\t\n" +
                                            "\tYou have " + pointDisplay + " Point\t\t\t\n" +
                                            "\t====================");
                                    System.out.printf(
                                            "\t===== SE STORE =====\t\t\t\n" +
                                                    "\t1. Show Category\t\t\t\n" +
                                                    "\t2. Add Members\n" +
                                                    "\t3. Edit Member\n" +
                                                    "\t4. Edit Product\n" +
                                                    "\t5. Logout\t\t\t\n" +
                                                    "\t====================\t\t\t\n");
                                    boolean loopInputTypeNumber = true;
                                    readMember();
                                    while (loopInputTypeNumber == true) {
                                        System.out.print("\tSelect (1-5) :\t");
                                        typeNumber = countNum.nextLine();
                                        if (typeNumber.equalsIgnoreCase("1")) {
                                            loopCategory = true;
                                            while (loopCategory) {
                                                System.out.printf("\t=========== SE STORE's Product Categories ===========\t\t\t\n" +
                                                        "\t#\t    Category\n");
                                                for (countProduct = 0; countProduct < nameCategory.size(); countProduct++) {
                                                    System.out.printf("\t%-3d %-15s\n",
                                                            countProduct + 1,
                                                            nameCategory.get(countProduct));
                                                }
                                                System.out.printf("\t=========================================\t\t\t\n" +
                                                        "\tSelect Category to Show Product (1-" + (nameCategory.size()) + ") or Q for exit\n" +
                                                        "\tSelect : ");
                                                typeCategory = inputTypeCategory.nextLine();
                                                int choosedTypeCategory = -1;
                                                if (typeCategory.equalsIgnoreCase("Q")) {
                                                    loopInputTypeNumber = false;
                                                    loopCategory = false;
                                                } else {
                                                    try {
                                                        choosedTypeCategory = Integer.parseInt(typeCategory);
                                                        if (choosedTypeCategory >= 1 && choosedTypeCategory <= nameCategory.size()) {
                                                            System.out.println("\t============     " + nameCategory.get(choosedTypeCategory - 1) + "     ============\t\t\t");
                                                            SE_STORE9 seStore6 = new SE_STORE9();
                                                            seStore6.printProduct(product, role, hostIdCategory, choosedTypeCategory);

                                                            while (!typeCategory.equalsIgnoreCase("q")) {
                                                                System.out.print("\t================================\t\t\t\n" +
                                                                        "\t1. Show Name By DESC\n" +
                                                                        "\t2. Show Quantity By ASC\n" +
                                                                        "\tor Press Q to Exit : ");
                                                                typeCategory = inputTypeCategory.nextLine();
                                                                if (typeCategory.equals("1")) {
                                                                    System.out.println("\t============     " + nameCategory.get(choosedTypeCategory - 1) + "     ============\t\t\t");
                                                                    Product[] nameSorting = Arrays.stream(product).sorted(Comparator.comparing(Product::getNameProduct,
                                                                                    Comparator.reverseOrder()).thenComparing(Product::getQuantity, Comparator.reverseOrder()))
                                                                            .toArray(Product[]::new);
                                                                    seStore6.printProduct(nameSorting, role, hostIdCategory, choosedTypeCategory);
                                                                } else if (typeCategory.equals("2")) {
                                                                    System.out.println("\t============     " + nameCategory.get(choosedTypeCategory - 1) + "     ============\t\t\t");
                                                                    Product[] nameSorting = Arrays.stream(product).sorted(Comparator.comparingInt(Product::getQuantity)
                                                                                    .thenComparing(p -> p.getNameProduct().toLowerCase()))
                                                                            .toArray(Product[]::new);
                                                                    seStore6.printProduct(nameSorting, role, hostIdCategory, choosedTypeCategory);
                                                                }
                                                            }
                                                        } else {
                                                            System.out.println("\tInvalid number, please enter a value between 1-" + nameCategory.size());
                                                        }
                                                    } catch (NumberFormatException e) {
                                                        System.out.println("\tInvalid input, please enter a valid number 1-" + nameCategory.size() + " or Q/q.");
                                                    }
                                                }
                                            }
                                            System.out.printf("\t===========================================\t\n");
                                        } else if (typeNumber.equalsIgnoreCase("2")) {
                                            loopAddMember = true;
                                            Scanner inputAddMember = new Scanner(System.in);
                                            String firstnameAddMember, lastnameAddMember, emailAddMember, phoneAddMember;
                                            while (loopAddMember) {
                                                boolean checkAddmember = true;
                                                System.out.print("\t===== Add Member =====\n" +
                                                        "\tFirstname : ");
                                                firstnameAddMember = inputAddMember.nextLine();
                                                System.out.print("\tLastname : ");
                                                lastnameAddMember = inputAddMember.nextLine();
                                                System.out.print("\tEmail : ");
                                                emailAddMember = inputAddMember.nextLine();
                                                System.out.print("\tPhone : ");
                                                phoneAddMember = inputAddMember.nextLine();

                                                //checkAddMember
                                                if (firstnameAddMember.length() <= 2) {
                                                    checkAddmember = false;
                                                } else if (lastnameAddMember.length() <= 2) {
                                                    checkAddmember = false;
                                                } else if (emailAddMember.length() <= 2 || !emailAddMember.contains("@")) {
                                                    checkAddmember = false;
                                                } else if (phoneAddMember.length() != 10) {
                                                    checkAddmember = false;
                                                }

                                                if (checkAddmember == true) {
                                                    //generate password
                                                    FileWriter writer = new FileWriter("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\MEMBER (5).txt", true);
                                                    Random random = new Random();
                                                    int randomNumber = random.nextInt(999999);
                                                    String randomPassword = String.format("%06d", randomNumber);
                                                    String randomLetter = "";
                                                    for (int i = 0; i < 19; i++) {
                                                        randomLetter += (char) (random.nextInt(26) + 'A');
                                                    }
                                                    StringBuilder newPassword = new StringBuilder(randomLetter);
                                                    newPassword.setCharAt(2, '1');
                                                    newPassword.setCharAt(6, '1');
                                                    int[] numberPosition = {9, 10, 13, 14, 15, 16};
                                                    for (int i = 0; i < randomPassword.length(); i++) {
                                                        newPassword.setCharAt(numberPosition[i], randomPassword.charAt(i));
                                                    }
                                                    System.out.println("\tSuccess - New Member has been created!");
                                                    System.out.println("\t" + firstnameAddMember + " " + lastnameAddMember.charAt(0) + " Password is " + randomPassword);
                                                    loopAddMember = false;
                                                    loopInputTypeNumber = false;

                                                    int indexIdUserLastest = member.length;
                                                    int newIdUser = Integer.parseInt(member[indexIdUserLastest - 1].getIdUser()) + 1;
                                                    String keepMember = newIdUser + "\t" + firstnameAddMember + "\t" + lastnameAddMember + "\t" +
                                                            emailAddMember + "\t" + newPassword + "\t" + phoneAddMember + "\t" + 0.00 + "\n";
                                                    writer.write(keepMember);
                                                    // ปิด FileWriter
                                                    writer.close();

                                                } else if (checkAddmember == false) {
                                                    System.out.println("\tError! - Your Information are Incorrect!");
                                                    loopAddMember = false;
                                                    loopInputTypeNumber = false;
                                                }
                                            }
                                        } else if (typeNumber.equalsIgnoreCase("3")) {
                                            ;
                                            boolean loopEditMember = true;
                                            while (loopEditMember = true) {
                                                System.out.println("\t===== SE STORE's Member =====\t\t\t\t\t\n" +
                                                        "\t#\tName\t\t\t\t\t\t\t\t     Email\t\t\t");
                                                for (int i = 0; i < member.length; i++) {
                                                    System.out.printf("\t%-3d %-40s %-10s\n",
                                                            (i + 1),
                                                            member[i].getFirstnameUser() + " " + member[i].getLastnameUser(),
                                                            member[i].getEmailUser());
                                                }
                                                System.out.print("\t================================\t\t\t\t\t\n" +
                                                        "\tType Member Number, You want to edit or Press Q to Exit\t\n" +
                                                        "\tSelect (1-" + member.length + ") : ");
                                                Scanner inputChooseUserEdit = new Scanner(System.in);
                                                String chooseUserEdit;
                                                chooseUserEdit = inputChooseUserEdit.next();
                                                if (chooseUserEdit.equalsIgnoreCase("q")) {
                                                    loopInputTypeNumber = false;
                                                    loopEditMember = false;
                                                    break;
                                                } else {
                                                    try {
                                                        int chooseUserEditNumber = Integer.parseInt(chooseUserEdit);
                                                        if (chooseUserEditNumber >= 1 && chooseUserEditNumber <= member.length) {
                                                            System.out.print("\t==== Edit info of " + member[chooseUserEditNumber - 1].getFirstnameUser()
                                                                    + " " + member[chooseUserEditNumber - 1].getLastnameUser() + " ====\n" +
                                                                    "\tType new info or Hyphen (-) for none edit.\n" +
                                                                    "\tFirstname : ");
                                                            Scanner inputEdit = new Scanner(System.in);
                                                            String firstnameEdit = inputEdit.nextLine();
                                                            System.out.print("\tLastname : ");
                                                            String lastnameEdit = inputEdit.nextLine();
                                                            System.out.print("\tEmail : ");
                                                            String emailEdit = inputEmail.nextLine();
                                                            System.out.print("\tPhone : ");
                                                            String phoneEdit = inputEdit.nextLine();

                                                            boolean checkEdit = true;
                                                            String saveFirstname = member[chooseUserEditNumber - 1].getFirstnameUser();
                                                            String saveLastname = member[chooseUserEditNumber - 1].getLastnameUser();
                                                            String saveEmail = member[chooseUserEditNumber - 1].getEmailUser();
                                                            String savePhone = member[chooseUserEditNumber - 1].getPhoneUser();

                                                            if (!firstnameEdit.equalsIgnoreCase("-")) {
                                                                if (firstnameEdit.length() >= 2) {
                                                                    member[chooseUserEditNumber - 1].setFirstnameUser(firstnameEdit);
                                                                } else {
                                                                    checkEdit = false;
                                                                }
                                                            }
                                                            if (!lastnameEdit.equalsIgnoreCase("-")) {
                                                                if (lastnameEdit.length() >= 2) {
                                                                    member[chooseUserEditNumber - 1].setLastnameUser(lastnameEdit);
                                                                } else {
                                                                    checkEdit = false;
                                                                }
                                                            }
                                                            if (!emailEdit.equalsIgnoreCase("-")) {
                                                                if (emailEdit.length() >= 2 || !emailEdit.contains("@")) {
                                                                    member[chooseUserEditNumber - 1].setEmailUser(emailEdit);
                                                                } else {
                                                                    checkEdit = false;
                                                                }
                                                            }
                                                            if (!phoneEdit.equalsIgnoreCase("-")) {
                                                                if (phoneEdit.length() >= 2) {
                                                                    member[chooseUserEditNumber - 1].setPhoneUser(phoneEdit);
                                                                } else {
                                                                    checkEdit = false;
                                                                }
                                                            }
                                                            if (checkEdit == true) {
                                                                System.out.println("\tSuccess - Member has been updated!");
                                                                BufferedWriter editMembered = new BufferedWriter(new FileWriter("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\MEMBER (5).txt", false));
                                                                for (int i = 0; i < member.length; i++) {
                                                                    String memberLine = (member[i].getIdUser() + "\t" + member[i].getFirstnameUser() + "\t" + member[i].getLastnameUser() +
                                                                            "\t" + member[i].getEmailUser() + "\t" + member[i].getPasswordUser() + "\t" + member[i].getPhoneUser() + "\t" +
                                                                            member[i].getPointUser() + "\n");
                                                                    editMembered.write(memberLine);
                                                                }
                                                                editMembered.close();
                                                            } else {
                                                                System.out.println("\tError! - Your Information are Incorrect!");
                                                                member[chooseUserEditNumber - 1].setFirstnameUser(saveFirstname);
                                                                member[chooseUserEditNumber - 1].setLastnameUser(saveLastname);
                                                                member[chooseUserEditNumber - 1].setEmailUser(saveEmail);
                                                                member[chooseUserEditNumber - 1].setPhoneUser(savePhone);
                                                            }
                                                            loopInputTypeNumber = false;
                                                            loopEditMember = false;
                                                            break;
                                                        }
                                                    } catch (NumberFormatException e){
                                                        System.out.println("\tInput is wrong, Please try again!!!");
                                                    }
                                                }
                                            }
                                        } else if (typeNumber.equalsIgnoreCase("4")) {
                                            boolean loopEditProduct = true;
                                            while (loopEditProduct == true) {
                                                System.out.println("\t===== SE STORE's Products =====\t\t\t\t\t");
                                                SE_STORE9 seStore6 = new SE_STORE9();
                                                seStore6.printAllProductStaff(product);
                                                System.out.print("\t================================\t\t\t\t\t\n" +
                                                        "\tType Member Number, You want to edit or Press Q to Exit\t\n" +
                                                        "\tSelect (1-" + product.length + ") : ");
                                                Scanner inputChooseProductEdit = new Scanner(System.in);
                                                String chooseProductEdit;
                                                chooseProductEdit = inputChooseProductEdit.next();
                                                if (chooseProductEdit.equalsIgnoreCase("q")) {
                                                    loopInputTypeNumber = false;
                                                    loopEditProduct = false;
                                                    break;
                                                } else {
                                                    try {
                                                        int chooseProductEditNumber = Integer.parseInt(chooseProductEdit);
                                                        if (chooseProductEditNumber >= 1 && chooseProductEditNumber <= product.length) {
                                                            System.out.print("\t==== Edit info of " + product[chooseProductEditNumber - 1].getNameProduct()
                                                                    + "   ====\n" +
                                                                    "\tType new info or Hyphen (-) for none edit.\n" +
                                                                    "\tName : ");
                                                            Scanner inputEdit = new Scanner(System.in);
                                                            String changeNameProduct = inputEdit.nextLine();
                                                            System.out.print("\tQuantity : ");
                                                            String changeQuantityProduct = inputEdit.nextLine();

                                                            boolean checkEdit = true;
                                                            String saveNameProduct = product[chooseProductEditNumber - 1].getNameProduct();
                                                            int saveQuantiyProduct = product[chooseProductEditNumber - 1].getQuantity();

                                                            if (!changeNameProduct.equalsIgnoreCase("-")) {
                                                                product[chooseProductEditNumber - 1].setNameProduct(changeNameProduct);
                                                            }
                                                            if (!changeQuantityProduct.equalsIgnoreCase("-")) {
                                                                if (changeQuantityProduct.charAt(0) == '+' || changeQuantityProduct.charAt(0) == '-') {
                                                                    try {
                                                                        // ตรวจสอบว่ามีจุดทศนิยมหรือไม่
                                                                        if (changeQuantityProduct.contains(".")) {
                                                                            checkEdit = false;
                                                                        } else {
                                                                            int quantityChange = Integer.parseInt(changeQuantityProduct);
                                                                            // ตรวจสอบว่าค่าที่รวมแล้วไม่ทำให้ quantity น้อยกว่า 0
                                                                            if (quantityChange + product[chooseProductEditNumber - 1].getQuantity() < 0) {
                                                                                checkEdit = false;
                                                                            } else {
                                                                                product[chooseProductEditNumber - 1].setQuantity(quantityChange + product[chooseProductEditNumber - 1].getQuantity());
                                                                            }
                                                                        }
                                                                    } catch (NumberFormatException e) {
                                                                        checkEdit = false;  // ถ้าแปลงไม่ได้ ให้ checkEdit = false
                                                                    }
                                                                } else {
                                                                    checkEdit = false;
                                                                }
                                                            }

                                                            if (checkEdit == true) {
                                                                System.out.println("\tSuccess - Product has been updated!");
                                                                BufferedWriter editProducted = new BufferedWriter(new FileWriter("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\PRODUCT (4).txt", false));
                                                                for (int i = 0; i < product.length; i++) {
                                                                    String productLine = product[i].getId() + "\t" + product[i].getNameProduct() + "\t$" + product[i].getCost() +
                                                                            "\t" + product[i].getQuantity() + "\t" + product[i].getIdCategory() + "\n";
                                                                    editProducted.write(productLine);
                                                                }
                                                                editProducted.close();
                                                            } else {
                                                                System.out.println("\tError! - Your Information are Incorrect!");
                                                                product[chooseProductEditNumber - 1].setNameProduct(saveNameProduct);
                                                                product[chooseProductEditNumber - 1].setQuantity(saveQuantiyProduct);
                                                            }
                                                            loopEditProduct = false;
                                                            loopInputTypeNumber = false;
                                                            break;
                                                        }
                                                    } catch (NumberFormatException e){
                                                        System.out.println("\tInput is wrong, Please try again!!!");
                                                    }
                                                }
                                            }
                                        } else if (typeNumber.equalsIgnoreCase("5")) {
                                            loopLogin = false;
                                            loopOrNot = false;
                                            loopInputTypeNumber = false;
                                            Email = "";
                                            password = "";
                                        } else {
                                            System.out.println("\tError please try again!!");
                                            loopInputTypeNumber = true;
                                        }
                                    }
                                }
                            }
                        } else {
                            System.out.println("\t====================\n" +
                                    "\tError! - Your Account are Expired! ");
                        }
                    }
                }
            } else if (typeNumber.equalsIgnoreCase("2")) {
                System.out.printf("\t===== SE STORE =====\t\t\t\n" +
                        "\tThank you for using our service :3\t");
                loopDisplay1 = false;
                System.exit(0);
            } else {
                System.out.println("\tPlease try again!!!");
            }
        }
    }

    public void printProduct(Product[] product, String role, ArrayList<String> hostIdCategory, int choosedTypeCategory) {
        if (role.equals("Regular") || role.equals("Staff")) {
            System.out.println("\t#\tName\t    \tPrice(฿)\t  Quantity");
        } else {
            System.out.println("\t#\tName\t    \tPrice(฿)\t         Quantity");
        }
        int countProduct = 1;
        for (int i = 0; i < product.length; i++) {
            if (product[i].getIdCategory().equals(hostIdCategory.get(choosedTypeCategory - 1))) {
                if (role.equals("Regular") || role.equals("Staff")) {
                    System.out.printf("\t%-3d %-15s %-13.2f %-8d\n",
                            countProduct,
                            product[i].getNameProduct(),
                            product[i].getCost() * 34,
                            product[i].getQuantity());
                    countProduct++;
                } else if (role.equals("Silver")) {
                    double discount = 0.05;
                    System.out.printf("\t%-3d %-15s %-7.2f (%7.2f) %5d\n",
                            countProduct,
                            product[i].getNameProduct(),
                            (product[i].getCost() * 34) - ((product[i].getCost() * 34) * discount),
                            product[i].getCost() * 34,
                            product[i].getQuantity());
                    countProduct++;
                } else {
                    double discount = 0.10;
                    System.out.printf("\t%-3d %-15s %-7.2f (%7.2f) %5d\n",
                            countProduct,
                            product[i].getNameProduct(),
                            (product[i].getCost() * 34) - ((product[i].getCost() * 34) * discount),
                            product[i].getCost() * 34,
                            product[i].getQuantity());
                    countProduct++;
                }
            }
        }
    }

    public void printSearchProduct(Product[] product, String role ,ArrayList<Integer> indexSearch) {
        if (role.equals("Regular") || role.equals("Staff")) {
            System.out.println("\t#\tName\t    \tPrice(฿)\t  Quantity");
        } else {
            System.out.println("\t#\tName\t    \tPrice(฿)\t         Quantity");
        }
        int countProduct = 1;
        for (int i = 0; i < indexSearch.size() ; i++) {
            if (role.equals("Regular") || role.equals("Staff")) {
                System.out.printf("\t%-3d %-15s %-13.2f %-8d\n",
                        countProduct,
                        product[indexSearch.get(i)].getNameProduct(),
                        product[indexSearch.get(i)].getCost() * 34,
                        product[indexSearch.get(i)].getQuantity());
                countProduct++;
            } else if (role.equals("Silver")) {
                double discount = 0.05;
                System.out.printf("\t%-3d %-15s %-7.2f (%7.2f) %5d\n",
                        countProduct,
                        product[indexSearch.get(i)].getNameProduct(),
                        (product[indexSearch.get(i)].getCost() * 34) - ((product[indexSearch.get(i)].getCost() * 34) * discount),
                        product[indexSearch.get(i)].getCost() * 34,
                        product[indexSearch.get(i)].getQuantity());
                countProduct++;
            } else {
                double discount = 0.10;
                System.out.printf("\t%-3d %-15s %-7.2f (%7.2f) %5d\n",
                        countProduct,
                        product[indexSearch.get(i)].getNameProduct(),
                        (product[indexSearch.get(i)].getCost() * 34) - ((product[indexSearch.get(i)].getCost() * 34) * discount),
                        product[indexSearch.get(i)].getCost() * 34,
                        product[indexSearch.get(i)].getQuantity());
                countProduct++;
            }
        }
    }

    public void printAllProductStaff(Product[] product) {
        System.out.println("\t#\tName\t    \tPrice(฿)\t  Quantity");
        int countProduct = 1;
        for (int i = 0; i < product.length; i++) {
            System.out.printf("\t%-3d %-15s %-13.2f %-8d\n",
                    countProduct,
                    product[i].getNameProduct(),
                    product[i].getCost() * 34,
                    product[i].getQuantity());
            countProduct++;
        }
    }

    public void printAllProduct(Product[] product, String role) {
        System.out.println("\t=========== SE STORE's Products ===========");
        if (role.equals("Regular") || role.equals("Staff")) {
            System.out.println("\t#\tName\t    \tPrice(฿)\t  Quantity");
        } else {
            System.out.println("\t#\tName\t    \tPrice(฿)\t         Quantity");
        }
        int countProduct = 1;
        for (int i = 0; i < product.length; i++) {
            if (role.equals("Regular") || role.equals("Staff")) {
                System.out.printf("\t%-3d %-15s %-13.2f %-8d\n",
                        countProduct,
                        product[i].getNameProduct(),
                        product[i].getCost() * 34,
                        product[i].getQuantity());
                countProduct++;
            } else if (role.equals("Silver")) {
                double discount = 0.05;
                System.out.printf("\t%-3d %-15s %-7.2f (%7.2f) %5d\n",
                        countProduct,
                        product[i].getNameProduct(),
                        (product[i].getCost() * 34) - ((product[i].getCost() * 34) * discount),
                        product[i].getCost() * 34,
                        product[i].getQuantity());
                countProduct++;
            } else {
                double discount = 0.10;
                System.out.printf("\t%-3d %-15s %-7.2f (%7.2f) %5d\n",
                        countProduct,
                        product[i].getNameProduct(),
                        (product[i].getCost() * 34) - ((product[i].getCost() * 34) * discount),
                        product[i].getCost() * 34,
                        product[i].getQuantity());
                countProduct++;
            }
        }
        System.out.println("\t===========================================\t");
    }

    public static void AddCartToFile(String newLine) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\CART.txt", true))) {
            writer.write(newLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createNewOrder(String userID, String productID, int quantity) throws FileNotFoundException {
        String newOrder = userID + "\t" + productID + "\t" + quantity + "\n";
        AddCartToFile(newOrder);
        loadFileCart();
        updateFile_Order();
    }

    public static void updateFile_Order() {
        Cart[] backUpOrder = cart;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\CART.txt", false))) {
            for (int i = 0; i < backUpOrder.length; i++) {
                if (backUpOrder[i].getQuantity() > 0) {
                    writer.write(backUpOrder[i].getUserID() + "\t" + backUpOrder[i].getProductID() + "\t" + backUpOrder[i].getQuantity() + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadFileCart() throws FileNotFoundException{
        File cartReader = new File("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\CART.txt");
        Scanner inputCart = new Scanner(cartReader);
        int countCart = 0;
        while (inputCart.hasNextLine()) {
            String Line = inputCart.nextLine();
            countCart++;
        }
        inputCart.close();
        inputCart = new Scanner(cartReader);
        cart = new Cart[countCart];
        int getCountCart = 0;
        while (inputCart.hasNextLine()) {
            String[] line = inputCart.nextLine().split("\\s+");
            cart[getCountCart] = new Cart(line[0], line[1], Integer.parseInt(line[2]));
            getCountCart++;
        }
    }

    public static void updateFile_product(){
        Product [] backUpProducts = product;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\PRODUCT (4).txt",false))){
            for(int i = 0; i < backUpProducts.length; i++){
                writer.write(backUpProducts[i].getId() + "\t" + backUpProducts[i].getNameProduct() + "\t" + "$" +
                        backUpProducts[i].getCost() + "\t" + backUpProducts[i].getQuantity() + "\t" + backUpProducts[i].getIdCategory() + "\n");
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readMember() throws FileNotFoundException {
        File memberReader = new File("C:\\Users\\informatics\\IdeaProjects\\untitled2\\src\\MEMBER (5).txt");
        Scanner inputMember = new Scanner(memberReader);
        int countMember = 0;
        while (inputMember.hasNextLine()) {
            String memberLine = inputMember.nextLine();
            countMember++;
        }
        inputMember.close();

        inputMember = new Scanner(memberReader);
        member = new Member[countMember];
        int getCountMember = 0;
        while (inputMember.hasNextLine()) {
            String[] line = inputMember.nextLine().split("\\s+");
            String idUser = line[0];
            String firstnameUser = line[1];
            String lastnameUser = line[2];
            String emailUser = line[3];
            String passwordUser = line[4];
            String phoneUser = line[5];
            String pointUser = line[6];
            member[getCountMember] = new Member(idUser, firstnameUser, lastnameUser, emailUser, passwordUser, phoneUser, pointUser);
            getCountMember++;
        }
    }

    public static void printMyCart ( ArrayList<Integer> userIdIndexCart){
        System.out.print("\t#\tName\t        Quantity\tTotals (฿)\n");
        int countOrder = 1;
        double priceTotal = 0.00;
        for(int i = 0 ; i < userIdIndexCart.size(); i++){
            String nameProduct = "";
            for(int j = 0; j < product.length; j++){
                if(cart[userIdIndexCart.get(i)].getProductID().equalsIgnoreCase(product[j].getId())){
                    nameProduct = product[j].getNameProduct();
                }
            }

            System.out.printf("\t%-3d %-15s %-13d %-8.2f\n",
                    countOrder,
                    nameProduct,
                    cart[userIdIndexCart.get(i)].getQuantity(),
                    ((product[i].getCost() * 34) * cart[userIdIndexCart.get(i)].getQuantity()));
            countOrder++;
            priceTotal += ((product[i].getCost() * 34) * cart[userIdIndexCart.get(i)].getQuantity());
        }
    }
}
//	        Price	   Quantity
//	%-3d %-15s $%-9.2f %-10d