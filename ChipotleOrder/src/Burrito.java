public class Burrito {
    private String meat = "";
    private String[] toppings = new String[5];

    public Burrito(String m, String topping1, String topping2, String topping3, String topping4, String topping5){
        meat = m;
        toppings[0] = topping1;
        toppings[1] = topping2;
        toppings[2] = topping3;
        toppings[3] = topping4;
        toppings[4] = topping5;
    }
    public void setMeat(String a){
        meat = a;
    }
    public void setTopping1(String a){
        toppings[0] = a;
    }
    public void setTopping2(String a){
        toppings[1] = a;
    }
    public void setTopping3(String a){
        toppings[2] = a;
    }
    public void setTopping4(String a){
        toppings[3] = a;
    }
    public void setTopping5(String a){
        toppings[4] = a;
    }

    public String getMeat(){
        return meat;
    }
    public String getTopping1(){
        return toppings[0];
    }
    public String getTopping2(){
        return toppings[1];
    }
    public String getTopping3(){
        return toppings[2];
    }
    public String getTopping4(){
        return toppings[3];
    }
    public String getTopping5(){
        return toppings[4];
    }
}
