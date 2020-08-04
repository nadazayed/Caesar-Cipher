import java.util.Scanner;

public class cipher
{
    //public static String alphaWord = "abcdefghijklmnopqrstuvwxyz";
    public static char[] alpha = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    //public static char[] alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();


    public static void main(String[] args)
    {
        String plainTxt, cipherTxt;
        int method = 0, key = 0;

        Scanner scan = new Scanner (System.in);

        while(true)
        {
            System.out.println("1-Encryption  2-Decryption  3-BruteForce");
            method = Integer.parseInt(scan.nextLine());
            //method = scan.nextInt();

            if (method == 1)
            {
                System.out.println("Enter ur plain txt");
                plainTxt = scan.nextLine();

                if (check(plainTxt) == 0)
                    System.out.println("Enter valid chars");

                else
                {
                    System.out.println("Enter ur key");
                    key = Integer.parseInt(scan.nextLine());

                    enc(plainTxt,key);
                }

            }

            else if (method == 2)
            {
                System.out.println("Enter ur cipher txt");
                cipherTxt = scan.nextLine();

                if (check(cipherTxt) == 0)
                    System.out.println("Enter valid chars");

                else
                {
                    System.out.println("Enter ur key");
                    key = Integer.parseInt(scan.nextLine());

                    dec(cipherTxt,key);
                }

            }

            else if (method == 3)
            {
                System.out.println("Enter ur cipher txt to break");
                cipherTxt = scan.nextLine();

                if (check(cipherTxt) == 0)
                    System.out.println("Enter valid chars");

                else
                    bf (cipherTxt);
            }

            else
                System.out.println("Invalid choice. Retry");
        }


    }

    //Encryption method hna
    public static void enc (String plainTxt, int key)
    {
        char [] plain = plainTxt.toCharArray();
        char [] cipher = new char[plain.length];

        int cipherIndex,  plainIndex,  check = 1,  eq;

        for (int i=0; i<plain.length && check == 1; i++) //kol char f my string
        {
            plainIndex = 0; //reset

            plainIndex=index(plain[i]);

            if (plainIndex == -1) //space
            {
                cipher[i] = ' ';
            }

            else //char 3dy
            {
                eq = (plainIndex + key)%alpha.length;
                if (eq < 0) //case lw -ve
                    eq+=alpha.length;

                cipher[i] = alpha[eq];
            }

        }

        String output = new String(cipher);
        System.out.println(".key: "+key+"\t .cipher: "+output+"\n \n");

    }

    //Decryption method hna
    public static void dec (String cipherTxt, int key)
    {
        char [] cipher = cipherTxt.toCharArray();
        char [] plain = new char[cipher.length];

        int cipherIndex,  plainIndex,    eq;

        for (int i=0; i<cipher.length; i++) //kol char f my string
        {
            cipherIndex = 0; //reset

            cipherIndex=index(cipher[i]);

            if (cipherIndex == -1) //space
            {
                plain[i] = ' ';
            }

            else //char 3dy
            {
                eq = (cipherIndex - key)%alpha.length;
                if (eq < 0) //case lw -ve
                    eq+=alpha.length;

                plain[i] = alpha[eq];
            }

        }

        String output = new String(plain);
        System.out.println(".key: "+key+"\t .plain: "+output+"\n \n");
    }

    public static int index (char x)
    {
        int y=-2;

        if (x == ' ')
        {
            y = -1;
        }
        else
        {
            for (int i=0; i<alpha.length; i++)
            {
                if (alpha[i] == x)
                    y=i;
            }
        }

        return y;
    }

    public static int check (String x)
    {
        int y =1;
        char [] letters = x.toCharArray();

        for (int i=0 ; i<letters.length; i++)
        {
            if (( Character.isLetter(letters[i]) && Character.isLowerCase(letters[i]) ) || (letters[i] == ' '))
                continue;
            else
            {
                y = 0;
                break;
            }
        }
        return y;
    }

    public static void bf (String cipher)
    {
        for (int i=0; i<alpha.length; i++) // for each key
        {
            dec(cipher,i);
        }
    }
}
