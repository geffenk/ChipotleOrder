public class App {
    public static int pos = 0;
    public static void main(String[] args) throws Exception {
        while (pos!=100){
            if (pos == 0){
                pos = Utility.wantToOrder();
            }
            if (pos ==1){
                pos = Utility.startOrder();
            }
            if (pos == 2){
                pos = Utility.createBurrito();
            }
            if (pos == 3){
                pos = Utility.createTaco();
            }
            if (pos == 4){
                pos = Utility.createBowl();
            }
            if (pos == 5){
                pos = Utility.whatNext();
            }
            if (pos == 6){
                pos = Utility.whichTypeNext();
            }
            if (pos == 7){
                pos = Utility.editWhatType();
            }
        }
        
    }
    
}
//test test test