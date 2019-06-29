public class MainClass {

    public static void main(String[] args) {
        char[][] chars = new char[3][3];
        for(int i = 0; i < (int) Math.random()*5; i++ ) {
            System.out.println("Just test");
        }
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                chars[i][j] = 'K';
            }
        }
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j < chars.length; j++) {
                System.out.println(chars[i][j]);
            }
        }
    }

}
