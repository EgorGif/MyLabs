package pack;
import java.io.Serializable;
import java.util.ResourceBundle;

public class Doctor extends ObjectWithResourceBundle implements Serializable{
    private String name;

    public Doctor() {
        name =null;
    }

    public Doctor(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
