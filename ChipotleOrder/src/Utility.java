
import java.util.Scanner;
public class Utility {
    public static Scanner in = new Scanner(System.in);
    public static String input = "";
    public static int posOfMisunderstand = 0;
    public static int numberOfBurritos = 0;
    public static int numberOfTacos = 0;
    public static int numberOfBowls = 0;
    public static Bowl[] bagBowl = new Bowl[3];
    public static Taco[] bagTaco = new Taco[3];
    public static Burrito[] bagBurrito = new Burrito[3];
    
    public static int editBowl(){
        if (numberOfBowls ==0){
            System.out.println("You haven't selected any bowls. You are being rerouted to choose a different action.");
            return 5;
        }
        int done = 0;
        while (done !=100){
            System.out.println("You currently have " + numberOfBowls + " bowls. which would you like to edit? 1, 2, or 3.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("1") != -1 || input.indexOf("first") != -1){
                editCertainBowl(1);
                return 5;
            }
            else if (input.indexOf("2") != -1|| input.indexOf("second") != -1){
                if (numberOfBowls>1){
                    editCertainBowl(2);
                    return 5;
                }
                else System.out.println("You have not selected 2 bowls, you will be asked to choose a different bowl");
            }
            else if (input.indexOf("3") != -1|| input.indexOf("third") != -1){
                if (numberOfBowls>2){
                    editCertainBowl(3);
                    return 5;
                }
                else System.out.println("You have not selected 2 bowls, you will be asked to choose a different bowl");
            }
        }
        return 100;
    }
    public static void editCertainBowl(int num){
        int done = 0;
        while (done != 100){
            System.out.println("What would you like to edit? Meat, topping 1, 2, 3, 4, or 5?");
            input = in.nextLine().toLowerCase();
            if (input.contains("meat")){
                System.out.println("what would you like to change your meat to? We have chicken, beef, or pork");
                input = in.nextLine().toLowerCase();
                if (input.contains("chicken")){
                    bagBowl[num].setMeat("chicken");
                }
                else if (input.contains("beef")){
                    bagBowl[num].setMeat("beef");
                }
                else if (input.contains("pork")){
                    bagBowl[num].setMeat("pork");
                }
                else System.out.println("you did not pick a valid meat");
                System.out.println("Would you like to continue editing this Burrito?");
                input = in.nextLine().toLowerCase();
                if (input.contains("no")){
                    done = 100;
                }
                else if (input.contains("yes")){
                }
                else {
                    System.out.println("you did not input a valid response so you are being redirected");
                    done = 100;
                }
            }
            else if (input.contains("1") || input.contains("first")){
                System.out.println("What would you like to change your topping to? We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping.");
                input = in.nextLine().toLowerCase();
                if (input.contains("bean")){
                    bagBowl[num].setTopping1("beans");
                }
            }
        }
    }
    public static int editTaco(){
        return 100;
    }
    public static int editBurrito(){
        return 100;
    }
    public static int editWhatType(){
        int done = 0;
        while (done != 100){
            System.out.println("would you like to edit one of your burritos, tacos, or bowls?");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end")!= -1){
                return 100;
            }
            else if (input.indexOf("bowl")!= -1){
                return 9;
            }
            else if (input.indexOf("taco")!= -1){
                return 10;
            }
            else if (input.indexOf("burrito")!= -1){
                return 11;
            }
            misunderstand();
        }
        return 100;
    }
    public static int whichTypeOrderNext(){
        int done = 0;
        while (done != 100){
            System.out.println("Which type would you like to order next? burrito, taco, or bowl?");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end")!= -1){
                return 100;
            }
            else if (input.indexOf("burrito")!= -1){
                return 2;
            }
            else if (input.indexOf("taco")!= -1){
                return 3;
            }
            else if (input.indexOf("burrito")!= -1){
                return 4;
            }
            misunderstand();
        }
        return 100;
    }
    public static int whatNext(){
        System.out.println("You currently have selected " + numberOfBowls + " Bowls, " + numberOfTacos + " Tacos, and " + numberOfBurritos+ " Burritos.");
        int done = 0;
        while (done != 100){
            System.out.println("What would you like to do next? Order a new burrito, taco, or bowl? Edit one you already have selected? Or place your order? Type \"new\" for a new item, \"edit\" to edit an item, or \"place\" to place your order");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") != -1){
                return 100;
            }
            else if (input.indexOf("new")!= -1){
                return 6;
            }
            else if (input.indexOf("edit") != -1){
                return 7;
            }
            else if (input.indexOf("place")!= -1){
                return 8;
            }
            misunderstand();
        }
        return 100;
    }
    public static int createBowl(){
        int done = 0;
        String firstTopping = "";
        String secondTopping = "";
        String thirdTopping = "";
        String fourthTopping = "";
        String fifthTopping = "";
        String meat = "";
        if (numberOfBowls >2){
            System.out.println("You have already selected 3 bowls. You are fat enough. You are being redirected to do another action.");
            return 5;
        }
        while (done!=50){
            System.out.println("What meat would you like on your Bowl? We have chicken, steak, and pork. You are only allowed one meat.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("chicken") != -1){
                meat = "chicken";
                done = 50;
            }
            else if (input.indexOf("beef") != -1 || input.indexOf("steak") != -1){
                meat = "steak";
                done = 50;
            }
            else if (input.indexOf("pork") != -1){
                meat = "pork";
                done = 50;
            }
            else misunderstand();
        }
        while (done!=0){
            System.out.println("You have chose " + meat + ". You get five toppings on your bowl. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings. What would you like for your first topping?");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                firstTopping = "beans";
                done = 0;
            }
            else if (input.indexOf("salsa") != -1){
                firstTopping = "salsa";
                done = 0;
            }
            else if (input.indexOf("guac") != -1){
                firstTopping = "guacamole";
                done = 0;
            }
            else if (input.indexOf("corn") != -1){
                firstTopping = "corn";
                done = 0;
            }
            else if (input.indexOf("sour cream") != -1){
                firstTopping = "sour cream";
                done = 0;
            }
            else if (input.indexOf("lettuce") != -1){
                firstTopping = "lettuce";
                done = 0;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                firstTopping = "fajita veggies";
                done = 0;
            }
            else if (input.indexOf("queso") != -1){
                firstTopping = "queso";
                done = 0;
            }
            else if (input.indexOf("no") != -1){
                firstTopping = "no topping";
                done = 0;
            }
            else misunderstand();
        }
        while (done!=10){
            System.out.println("You have chose " + firstTopping + ". Now for your second topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                secondTopping = "beans";
                done = 10;
            }
            else if (input.indexOf("salsa") != -1){
                secondTopping = "salsa";
                done = 10;
            }
            else if (input.indexOf("guac") != -1){
                secondTopping = "guacamole";
                done = 10;
            }
            else if (input.indexOf("corn") != -1){
                secondTopping = "corn";
                done = 10;
            }
            else if (input.indexOf("sour cream") != -1){
                secondTopping = "sour cream";
                done = 10;
            }
            else if (input.indexOf("lettuce") != -1){
                secondTopping = "lettuce";
                done = 10;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                secondTopping = "fajita veggies";
                done = 10;
            }
            else if (input.indexOf("queso") != -1){
                secondTopping = "queso";
                done = 10;
            }
            else if (input.indexOf("no") != -1){
                secondTopping = "no topping";
                done = 10;
            }
            else misunderstand();
        }
        while (done!=20){
            System.out.println("You have chose " + secondTopping + ". Now for your third topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                thirdTopping = "beans";
                done = 20;
            }
            else if (input.indexOf("salsa") != -1){
                thirdTopping = "salsa";
                done = 20;
            }
            else if (input.indexOf("guac") != -1){
                thirdTopping = "guacamole";
                done = 20;
            }
            else if (input.indexOf("corn") != -1){
                thirdTopping = "corn";
                done = 20;
            }
            else if (input.indexOf("sour cream") != -1){
                thirdTopping = "sour cream";
                done = 20;
            }
            else if (input.indexOf("lettuce") != -1){
                thirdTopping = "lettuce";
                done = 20;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                thirdTopping = "fajita veggies";
                done = 20;
            }
            else if (input.indexOf("queso") != -1){
                thirdTopping = "queso";
                done = 20;
            }
            else if (input.indexOf("no") != -1){
                thirdTopping = "no topping";
                done = 20;
            }
            else misunderstand();
        }
        while (done!=0){
            System.out.println("You have chose " + thirdTopping + ". Now for your fourth topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                fourthTopping = "beans";
                done = 0;
            }
            else if (input.indexOf("salsa") != -1){
                fourthTopping = "salsa";
                done = 0;
            }
            else if (input.indexOf("guac") != -1){
                fourthTopping = "guacamole";
                done = 0;
            }
            else if (input.indexOf("corn") != -1){
                fourthTopping = "corn";
                done = 0;
            }
            else if (input.indexOf("sour cream") != -1){
                fourthTopping = "sour cream";
                done = 0;
            }
            else if (input.indexOf("lettuce") != -1){
                fourthTopping = "lettuce";
                done = 0;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                fourthTopping = "fajita veggies";
                done = 0;
            }
            else if (input.indexOf("queso") != -1){
                fourthTopping = "queso";
                done = 0;
            }
            else if (input.indexOf("no") != -1){
                fourthTopping = "no topping";
                done = 0;
            }
            else misunderstand();
        }
        while (done!=10){
            System.out.println("You have chose " + fourthTopping + ". Now for your fifth and final topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                fifthTopping = "beans";
                done = 10;
            }
            else if (input.indexOf("salsa") != -1){
                fifthTopping = "salsa";
                done = 10;
            }
            else if (input.indexOf("guac") != -1){
                fifthTopping = "guacamole";
                done = 10;
            }
            else if (input.indexOf("corn") != -1){
                fifthTopping = "corn";
                done = 10;
            }
            else if (input.indexOf("sour cream") != -1){
                fifthTopping = "sour cream";
                done = 10;
            }
            else if (input.indexOf("lettuce") != -1){
                fifthTopping = "lettuce";
                done = 10;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                fifthTopping = "fajita veggies";
                done = 10;
            }
            else if (input.indexOf("queso") != -1){
                fifthTopping = "queso";
                done = 10;
            }
            else if (input.indexOf("no") != -1){
                fifthTopping = "no topping";
                done = 10;
            }
            else misunderstand();
        }
        System.out.println("You have chosen " + fifthTopping + " as your final topping.");
        bagBowl[numberOfBowls] = new Bowl(meat, firstTopping, secondTopping, thirdTopping, fourthTopping, fifthTopping);
        numberOfBowls++;
        System.out.println("You have selected a bowl with " + meat + ", " + firstTopping + ", " + secondTopping + ", " + thirdTopping+ ", " + fourthTopping + ", and " + fifthTopping);
        return 5;
    }
    public static int createTaco(){
        int done = 0;
        String firstTopping = "";
        String secondTopping = "";
        String thirdTopping = "";
        String fourthTopping = "";
        String fifthTopping = "";
        String meat = "";
        if (numberOfTacos >2){
            System.out.println("You have already selected 3 tacos. You are fat enough. You are being redirected to do another action.");
            return 5;
        }
        while (done!=50){
            System.out.println("What meat would you like on your Taco? We have chicken, steak, and pork. You are only allowed one meat.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("chicken") != -1){
                meat = "chicken";
                done = 50;
            }
            else if (input.indexOf("beef") != -1 || input.indexOf("steak") != -1){
                meat = "steak";
                done = 50;
            }
            else if (input.indexOf("pork") != -1){
                meat = "pork";
                done = 50;
            }
            else misunderstand();
        }
        while (done!=0){
            System.out.println("You have chose " + meat + ". You get five toppings on your Taco. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings. What would you like for your first topping?");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                firstTopping = "beans";
                done = 0;
            }
            else if (input.indexOf("salsa") != -1){
                firstTopping = "salsa";
                done = 0;
            }
            else if (input.indexOf("guac") != -1){
                firstTopping = "guacamole";
                done = 0;
            }
            else if (input.indexOf("corn") != -1){
                firstTopping = "corn";
                done = 0;
            }
            else if (input.indexOf("sour cream") != -1){
                firstTopping = "sour cream";
                done = 0;
            }
            else if (input.indexOf("lettuce") != -1){
                firstTopping = "lettuce";
                done = 0;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                firstTopping = "fajita veggies";
                done = 0;
            }
            else if (input.indexOf("queso") != -1){
                firstTopping = "queso";
                done = 0;
            }
            else if (input.indexOf("no") != -1){
                firstTopping = "no topping";
                done = 0;
            }
            else misunderstand();
        }
        while (done!=10){
            System.out.println("You have chose " + firstTopping + ". Now for your second topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                secondTopping = "beans";
                done = 10;
            }
            else if (input.indexOf("salsa") != -1){
                secondTopping = "salsa";
                done = 10;
            }
            else if (input.indexOf("guac") != -1){
                secondTopping = "guacamole";
                done = 10;
            }
            else if (input.indexOf("corn") != -1){
                secondTopping = "corn";
                done = 10;
            }
            else if (input.indexOf("sour cream") != -1){
                secondTopping = "sour cream";
                done = 10;
            }
            else if (input.indexOf("lettuce") != -1){
                secondTopping = "lettuce";
                done = 10;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                secondTopping = "fajita veggies";
                done = 10;
            }
            else if (input.indexOf("queso") != -1){
                secondTopping = "queso";
                done = 10;
            }
            else if (input.indexOf("no") != -1){
                secondTopping = "no topping";
                done = 10;
            }
            else misunderstand();
        }
        while (done!=20){
            System.out.println("You have chose " + secondTopping + ". Now for your third topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                thirdTopping = "beans";
                done = 20;
            }
            else if (input.indexOf("salsa") != -1){
                thirdTopping = "salsa";
                done = 20;
            }
            else if (input.indexOf("guac") != -1){
                thirdTopping = "guacamole";
                done = 20;
            }
            else if (input.indexOf("corn") != -1){
                thirdTopping = "corn";
                done = 20;
            }
            else if (input.indexOf("sour cream") != -1){
                thirdTopping = "sour cream";
                done = 20;
            }
            else if (input.indexOf("lettuce") != -1){
                thirdTopping = "lettuce";
                done = 20;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                thirdTopping = "fajita veggies";
                done = 20;
            }
            else if (input.indexOf("queso") != -1){
                thirdTopping = "queso";
                done = 20;
            }
            else if (input.indexOf("no") != -1){
                thirdTopping = "no topping";
                done = 20;
            }
            else misunderstand();
        }
        while (done!=0){
            System.out.println("You have chose " + thirdTopping + ". Now for your fourth topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                fourthTopping = "beans";
                done = 0;
            }
            else if (input.indexOf("salsa") != -1){
                fourthTopping = "salsa";
                done = 0;
            }
            else if (input.indexOf("guac") != -1){
                fourthTopping = "guacamole";
                done = 0;
            }
            else if (input.indexOf("corn") != -1){
                fourthTopping = "corn";
                done = 0;
            }
            else if (input.indexOf("sour cream") != -1){
                fourthTopping = "sour cream";
                done = 0;
            }
            else if (input.indexOf("lettuce") != -1){
                fourthTopping = "lettuce";
                done = 0;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                fourthTopping = "fajita veggies";
                done = 0;
            }
            else if (input.indexOf("queso") != -1){
                fourthTopping = "queso";
                done = 0;
            }
            else if (input.indexOf("no") != -1){
                fourthTopping = "no topping";
                done = 0;
            }
            else misunderstand();
        }
        while (done!=10){
            System.out.println("You have chose " + fourthTopping + ". Now for your fifth and final topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                fifthTopping = "beans";
                done = 10;
            }
            else if (input.indexOf("salsa") != -1){
                fifthTopping = "salsa";
                done = 10;
            }
            else if (input.indexOf("guac") != -1){
                fifthTopping = "guacamole";
                done = 10;
            }
            else if (input.indexOf("corn") != -1){
                fifthTopping = "corn";
                done = 10;
            }
            else if (input.indexOf("sour cream") != -1){
                fifthTopping = "sour cream";
                done = 10;
            }
            else if (input.indexOf("lettuce") != -1){
                fifthTopping = "lettuce";
                done = 10;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                fifthTopping = "fajita veggies";
                done = 10;
            }
            else if (input.indexOf("queso") != -1){
                fifthTopping = "queso";
                done = 10;
            }
            else if (input.indexOf("no") != -1){
                fifthTopping = "no topping";
                done = 10;
            }
            else misunderstand();
        }
        System.out.println("You have chosen " + fifthTopping + " as your final topping.");
        bagTaco[numberOfTacos] = new Taco(meat, firstTopping, secondTopping, thirdTopping, fourthTopping, fifthTopping);
        numberOfTacos++;
        System.out.println("You have selected a Taco with " + meat + ", " + firstTopping + ", " + secondTopping + ", " + thirdTopping+ ", " + fourthTopping + ", and " + fifthTopping);
        return 5;
    }
    public static int createBurrito(){
        int done = 0;
        String firstTopping = "";
        String secondTopping = "";
        String thirdTopping = "";
        String fourthTopping = "";
        String fifthTopping = "";
        String meat = "";
        if (numberOfBurritos >2){
            System.out.println("You have already selected 3 burritos. You are fat enough. You are being redirected to do another action.");
            return 5;
        }
        while (done!=50){
            System.out.println("What meat would you like on your Burrito? We have chicken, steak, and pork. You are only allowed one meat.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("chicken") != -1){
                meat = "chicken";
                done = 50;
            }
            else if (input.indexOf("beef") != -1 || input.indexOf("steak") != -1){
                meat = "steak";
                done = 50;
            }
            else if (input.indexOf("pork") != -1){
                meat = "pork";
                done = 50;
            }
            else misunderstand();
        }
        while (done!=0){
            System.out.println("You have chose " + meat + ". You get five toppings on your burrito. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings. What would you like for your first topping?");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                firstTopping = "beans";
                done = 0;
            }
            else if (input.indexOf("salsa") != -1){
                firstTopping = "salsa";
                done = 0;
            }
            else if (input.indexOf("guac") != -1){
                firstTopping = "guacamole";
                done = 0;
            }
            else if (input.indexOf("corn") != -1){
                firstTopping = "corn";
                done = 0;
            }
            else if (input.indexOf("sour cream") != -1){
                firstTopping = "sour cream";
                done = 0;
            }
            else if (input.indexOf("lettuce") != -1){
                firstTopping = "lettuce";
                done = 0;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                firstTopping = "fajita veggies";
                done = 0;
            }
            else if (input.indexOf("queso") != -1){
                firstTopping = "queso";
                done = 0;
            }
            else if (input.indexOf("no") != -1){
                firstTopping = "no topping";
                done = 0;
            }
            else misunderstand();
        }
        while (done!=10){
            System.out.println("You have chose " + firstTopping + ". Now for your second topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                secondTopping = "beans";
                done = 10;
            }
            else if (input.indexOf("salsa") != -1){
                secondTopping = "salsa";
                done = 10;
            }
            else if (input.indexOf("guac") != -1){
                secondTopping = "guacamole";
                done = 10;
            }
            else if (input.indexOf("corn") != -1){
                secondTopping = "corn";
                done = 10;
            }
            else if (input.indexOf("sour cream") != -1){
                secondTopping = "sour cream";
                done = 10;
            }
            else if (input.indexOf("lettuce") != -1){
                secondTopping = "lettuce";
                done = 10;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                secondTopping = "fajita veggies";
                done = 10;
            }
            else if (input.indexOf("queso") != -1){
                secondTopping = "queso";
                done = 10;
            }
            else if (input.indexOf("no") != -1){
                secondTopping = "no topping";
                done = 10;
            }
            else misunderstand();
        }
        while (done!=20){
            System.out.println("You have chose " + secondTopping + ". Now for your third topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                thirdTopping = "beans";
                done = 20;
            }
            else if (input.indexOf("salsa") != -1){
                thirdTopping = "salsa";
                done = 20;
            }
            else if (input.indexOf("guac") != -1){
                thirdTopping = "guacamole";
                done = 20;
            }
            else if (input.indexOf("corn") != -1){
                thirdTopping = "corn";
                done = 20;
            }
            else if (input.indexOf("sour cream") != -1){
                thirdTopping = "sour cream";
                done = 20;
            }
            else if (input.indexOf("lettuce") != -1){
                thirdTopping = "lettuce";
                done = 20;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                thirdTopping = "fajita veggies";
                done = 20;
            }
            else if (input.indexOf("queso") != -1){
                thirdTopping = "queso";
                done = 20;
            }
            else if (input.indexOf("no") != -1){
                thirdTopping = "no topping";
                done = 20;
            }
            else misunderstand();
        }
        while (done!=0){
            System.out.println("You have chose " + thirdTopping + ". Now for your fourth topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                fourthTopping = "beans";
                done = 0;
            }
            else if (input.indexOf("salsa") != -1){
                fourthTopping = "salsa";
                done = 0;
            }
            else if (input.indexOf("guac") != -1){
                fourthTopping = "guacamole";
                done = 0;
            }
            else if (input.indexOf("corn") != -1){
                fourthTopping = "corn";
                done = 0;
            }
            else if (input.indexOf("sour cream") != -1){
                fourthTopping = "sour cream";
                done = 0;
            }
            else if (input.indexOf("lettuce") != -1){
                fourthTopping = "lettuce";
                done = 0;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                fourthTopping = "fajita veggies";
                done = 0;
            }
            else if (input.indexOf("queso") != -1){
                fourthTopping = "queso";
                done = 0;
            }
            else if (input.indexOf("no") != -1){
                fourthTopping = "no topping";
                done = 0;
            }
            else misunderstand();
        }
        while (done!=10){
            System.out.println("You have chose " + fourthTopping + ". Now for your fifth and final topping. We have beans, salsa, guacamole, corn, sour cream, cheese, lettuce, fajita veggies, queso sauce, or the option of no topping. You may repeat toppings.");
            input = in.nextLine().toLowerCase();
            if (input.indexOf("end") > -1){
                return 100;
            }
            if (input.indexOf("bean") != -1){
                fifthTopping = "beans";
                done = 10;
            }
            else if (input.indexOf("salsa") != -1){
                fifthTopping = "salsa";
                done = 10;
            }
            else if (input.indexOf("guac") != -1){
                fifthTopping = "guacamole";
                done = 10;
            }
            else if (input.indexOf("corn") != -1){
                fifthTopping = "corn";
                done = 10;
            }
            else if (input.indexOf("sour cream") != -1){
                fifthTopping = "sour cream";
                done = 10;
            }
            else if (input.indexOf("lettuce") != -1){
                fifthTopping = "lettuce";
                done = 10;
            }
            else if (input.indexOf("veggies") != -1 || input.indexOf("fajita") != -1){
                fifthTopping = "fajita veggies";
                done = 10;
            }
            else if (input.indexOf("queso") != -1){
                fifthTopping = "queso";
                done = 10;
            }
            else if (input.indexOf("no") != -1){
                fifthTopping = "no topping";
                done = 10;
            }
            else misunderstand();
        }
        System.out.println("You have chosen " + fifthTopping + " as your final topping.");
        bagBurrito[numberOfBurritos] = new Burrito(meat, firstTopping, secondTopping, thirdTopping, fourthTopping, fifthTopping);
        numberOfBurritos++;
        System.out.println("You have selected a burrito with " + meat + ", " + firstTopping + ", " + secondTopping + ", " + thirdTopping+ ", " + fourthTopping + ", and " + fifthTopping);
        return 5;
    }
    public static int startOrder(){
        System.out.println("We have created a new order for you. We offer three menu items: Burritos, Tacos, and Bowls. Which would you like to order first?");
        input = in.nextLine().toLowerCase();
        if (input.indexOf("end")!= -1){
            return 100;
        }
        else if (input.indexOf("burrito") != -1 || input.indexOf("burritos") != -1){
            return 2;
        }
        else if (input.indexOf("taco") != -1 || input.indexOf("tacos") != -1){
            return 3;
        }
        else if (input.indexOf("bowl") != -1 || input.indexOf("bowls") != -1){
            return 4;
        }
        misunderstand();
        return 1;
    }
    public static int wantToOrder(){
        System.out.println("Welcome to Chipotle Online Order Software! Would you like to place an order with us? At any time type \"end\" to stop the conversation");
        input = in.nextLine().toLowerCase();
        if ((input.indexOf("end") == -1) && (input.indexOf("yes")!=-1 || input.indexOf("sure") != -1 || input.indexOf("yea") != -1)){
            return 1;
        }
        else if(input.indexOf("end") != -1){
            return 100;
        }
        misunderstand();
        return 0;

    }
    public static void misunderstand(){
        if(posOfMisunderstand%3 == 0){
            System.out.println("I'm sorry, I don't understand. Please clarify");
        }
        if(posOfMisunderstand%3 == 1){
            System.out.println("I didn't get that, please specify");
        }
        if(posOfMisunderstand%3 == 2){
            System.out.println("I'm not sure what you are talking about, please change your wording");
        }
        posOfMisunderstand++;
    }

}
