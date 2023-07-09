import java.util.Arrays;

public class QuickTests {
    public static void main(String[] args)  {

        MagicSquare magicSquare;
// Testing SSH key
        magicSquare = new MagicSquare(7);
        System.out.println(Arrays.deepToString(magicSquare.getGrid()));
        magicSquare = new MagicSquare(11);
        System.out.println(Arrays.deepToString(magicSquare.getGrid()));
        magicSquare = new MagicSquare(21);
        System.out.println(Arrays.deepToString(magicSquare.getGrid()));
    }
}
