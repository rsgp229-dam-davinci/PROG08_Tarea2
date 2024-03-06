package views;

public class MenuHelper {
    private static final int EXIT_OPTION = 0;
    private static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    private static void printHeadingBars(int length){
        for (int i = 0; i < length; i++) {
            System.out.print("*");
        }
        System.out.println();
    }
    private static int longestString(String[] strings){
        int longest = 0;
        for (String string : strings) {
            if (string.length() > longest)
                longest = string.length();
        }
        return longest + 3; // Se añaden 3 espacios para el número y el punto
    }
    public static void printMenu(String[] options){
        //clearScreen();
        int longest = longestString(options);
        printHeadingBars(longest);
        for (int i = 0; i < options.length; i++) {
            System.out.printf("%d. %s\n", i+1, options[i]);
        }
        System.out.printf("%d. Salir\n", EXIT_OPTION);
        printHeadingBars(longest);
    }
}
