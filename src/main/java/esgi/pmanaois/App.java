package esgi.pmanaois;

import esgi.pmanaois.domain.Contractor;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        var contractor = Contractor.of("Paolo", "Manaois", "username@domain.com");
        System.out.println( contractor.toString() );
    }
}
