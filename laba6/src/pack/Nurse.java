package pack;
import java.io.Serializable;
import java.util.ResourceBundle;

public class Nurse extends ObjectWithResourceBundle  implements Serializable{
    String name;

    public Nurse () {
        name = null;
    }

    public Nurse(String name) {
        this.name = name;
    }

    public String  getName() {
        return name;
    }

}
