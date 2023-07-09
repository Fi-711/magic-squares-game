import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Contains static methods and constants used to implement ASCII art and ANSI colours
 */
public class VFX {
    /*
    ANSI Color Codes from: https://en.wikipedia.org/wiki/ANSI_escape_code and
    https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println
     */

    // ANSI Text Colours
    public static String ANSI_RESET = "\u001B[0m";
    public static String ANSI_RED = "\u001B[31m";
    public static String ANSI_YELLOW = "\u001B[33m";

    /*
        ASCII Art Reworked from code from: https://mkyong.com/java/ascii-art-java-example/
     */

    // Turn Off colours - colours cause issue if used in terminal with no access to ANSI colours
    public static void offColours() {
        ANSI_RESET = "";
        ANSI_RED = "";
        ANSI_YELLOW = "";
    }

    // Original code was for 'Java' word hard coded, needed to modify to accept different texts and now also accepts
    // ANSI colors and xOffSet
    public static void asciiText(String text, String color, int xOffSet) {
        // Modified width and height to adapt to up to 20 characters
        int width = text.length() * 10 + 10, height = 30;

        // Standard Buffered Image and Graphics Module
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setFont(new Font("SansSerif", Font.BOLD, 14));

        Graphics2D graphics = (Graphics2D) g;
        graphics.drawString(text, xOffSet, 20);

        // ANSI Color
        System.out.println(color);
        for (int y = 0; y < height; y++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = 0; x < width; x++) {
                stringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "*");
            }

            if (stringBuilder.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(stringBuilder);
        }
        // Reset color
        System.out.print(ANSI_RESET);
    }

    /**
     * Creates an ascii wizard image
     */
    public static void asciiWizard() {
        // Sets color
        System.out.println(ANSI_RED);

        // Prints ASCII Art - 5 tabs (20 spaces) to centre it
        System.out.println("""
                \t\t\t\t\t                                       .         *                              
                \t\t\t\t\t                                       /,,(,.*%#,.                              
                \t\t\t\t\t                                 .                                              
                \t\t\t\t\t                      ,     ...      &&,       ,&&/          ..                 
                \t\t\t\t\t                       ,,*..    /&%%   /@@@@@(   &%%#         .. ...           
                \t\t\t\t\t                        ///    .&&%@@@@@@     .@@@%%%%*    ...  ..              
                \t\t\t\t\t                   /(/     *&&%%&@@@@    /&%%%%%&&        ....              
                \t\t\t\t\t                      ,,,,     (@&&&%%% %%%%%#%%%%&&@&,      ......             
                \t\t\t\t\t          ///*****//(((((//**/ #@@&&(&@%%%((%%%&&@@@#@%  ,,,,,,***/*,,,......   
                \t\t\t\t\t                      ((*,,,  (@&%@@@%@@@,%@&&&@&*(@@@&.  ..,,.,*/**/((/        
                \t\t\t\t\t                       ,,.../(%.@&@%/&@@%&//**&&&&&&@%(,,,/,..,,/               
                \t\t\t\t\t                        ,%/&%%%%&@@@@@,*%&&@@@@@%&.,#**,(((,....                
                \t\t\t\t\t                       (%%(%&&&&%&@&&@/*%&&&&&%&@&%&&%/%*(,/(//.,               
                \t\t\t\t\t                     ((       %@@@&&&@(,&%%%#@@&&&%&&%&(         (*.            
                \t\t\t\t\t                           &@@@@@@@&%%& #&%&@@@@&%&%%%%&&%.                     
                \t\t\t\t\t                     #%%%&&@&@@@@@@&%&/#%%@@&&&&@@&&&%%%%%%%%%###*              
                \t\t\t\t\t                #%%##%%&&%@@@&%%&@@&&&&&%&@&&##%&@@@%&&%%%%%%%%##(/**###.       
                \t\t\t\t\t            #(//(%&&&@@@@@%@&%%%&@&%&&&%%&&&%###%&@@@&&&&%%.. ,####%%%&&&&&%,   
                \t\t\t\t\t        %&%####%%&@@@&&@@&&&*&%&&&%%%%%%&&&%%%%%%%##&@@%##&%######%%%%&&@@@&&&/ 
                \t\t\t\t\t     (&@@&&&&&@@@@@@@&@&@@&%&&&&%%%%%%%&&&#.   (##%%@@@@&&&%%##&@@@@@@@@@@@@@@@(
                \t\t\t\t\t    @@@@&&(&&,/@@@@@@@#&#@&@@@&&&%#%%%%&@&&%%%%%%%&&@@%&%&&&@@@@@%((#(/#(((&&@@ 
                \t\t\t\t\t   *,(&&*#%,,%,,@@%%&  ,*/(#%@%@&%%%%%&@@@@@@@%&%#(#&/*(#(@@@@#(/.&&....,*//%   
                \t\t\t\t\t   *,%%%%%&&&&@@@@@%/    @@/,*#%&%%%&&&%@*.%, .   .,&&&@* /#(*... *@&&&%%./&&%  
                \t\t\t\t\t ..,/%&&@@@&*@@@@@@,   @@@@ @@@*@&&&&&&       .@@@  (@@@@  ,,.@@@@@@@@@@#.%,,*  
                \t\t\t\t\t   *@/@@@ */         #@@@*  &@% @@@&&&@(       .@@   @@@@@ *@@@&@@@@@@@@(/&.*&&,
                \t\t\t\t\t   %,*   ,.        .@@@@     @  @@@@@@@@        ./    @@@@&     /(..    ,@//&@*&.
                \t\t\t\t\t    ./,          &@@@&         (@@@@@@@@(            &@@@&.       /,.    ,  ,* 
                \t\t\t\t\t                 &&&@(           (@@@@@@@@@            &&&&&                ,   
                \t\t\t\t\t                (&&&(             @@@@@@@@@@%          &&&&&                    
                \t\t\t\t\t                                  @%(@@&*@@@@          .%                       
                \t\t\t\t\t                                 #***,,*@@*@,                                   
                \t\t\t\t\t                                  ..   ...                                                     
                 """);
        // Turn off colour
        System.out.print(ANSI_RESET);
    }

    /**
     * Creates an ascii thumb's up image
     */
    public static void asciiThumbsUp() {
        // Sets color
        System.out.println(ANSI_YELLOW);
        // One tab (4 spaces) to indent it
        System.out.println("""
                \t________$$$$_______________
                \t_______$$__$_______________
                \t_______$___$$______________
                \t_______$___$$______________
                \t_______$$___$$_____________
                \t________$____$$____________
                \t________$$____$$$__________
                \t_________$$_____$$_________
                \t_________$$______$$________
                \t__________$_______$$_______
                \t____$$$$$$$________$$______
                \t__$$$_______________$$$$$$
                \t_$$____$$$$____________$$$
                \t_$___$$$__$$$____________$$
                \t_$$________$$$____________$
                \t__$$____$$$$$$____________$
                \t__$$$$$$$____$$___________$
                \t__$$_______$$$$___________$
                \t___$$$$$$$$$__$$_________$$
                \t____$________$$$$_____$$$$
                \t____$$____$$$$$$____$$$$$$
                \t_____$$$$$$____$$__$$
                \t_______$_____$$$_$$$
                \t________$$$$$$$$$$
                """);
        // Turn off colour
        System.out.print(ANSI_RESET);
    }
}

/*
 University ID Number: c1948118
 Name: Suhail Ahmed
 */
