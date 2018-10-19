package pack;
import java.util.Locale;
import java.util.ResourceBundle;

public class Test {
    public static void main (String args[]) throws Exception{

        Locale.setDefault(new Locale(args[0],args[1]));

        Doctor MasDoc[] = new Doctor[6];
        MasDoc[0] = new Doctor("Arnold");
        MasDoc[1] = new Doctor ("Slavik");
        MasDoc[2] = new Doctor ("Antoha");
        MasDoc[3] = new Doctor ("Arturyan");
        MasDoc[4] = new Doctor ("Akagi");
        MasDoc[5] = new Doctor ("Lemon");

        Nurse MasNur[] = new Nurse[4];
        MasNur[0] = new Nurse("Svetochka");
        MasNur[1] = new Nurse ("Liza");
        MasNur[2] = new Nurse ("Katya");
        MasNur[3] = new Nurse ("Stefanya");

        Patient MasPat[] = new Patient[5];
        MasPat[0] = new Patient("Ivan", "PROCEDURES","Other",MasDoc[0],MasDoc[1], Locale.getDefault());
        MasPat[1] = new Patient ("Kolya","MEDICINE","Violation",MasDoc[2],MasNur[0],Locale.getDefault());
        MasPat[2] = new Patient ("Dominik","OPERATION","EndOfTreatment",MasDoc[4],MasNur[1],Locale.getDefault());
        MasPat[3] = new Patient ("Kristina","MEDICINE","Other",MasDoc[3],MasNur[2],Locale.getDefault());
        MasPat[4] = new Patient ("Karina","PROCEDURES","EndOfTreatment",MasDoc[5],MasNur[3],Locale.getDefault());

        // for (Patient n : MasPat)
        // System.out.println(n);

        try {
           Connector con = new Connector("src\\pack\\Pat.dat");
           con.write( MasPat);
           Patient[] Pat = con.read("src\\pack\\Pat.dat");
           System.out.println( "Patients: ");
           for ( Patient n : Pat ) {
               System.out.println(n);
           }
        }
        catch ( Exception e ) {
            System.err.println(e);
        }

    }

    }
