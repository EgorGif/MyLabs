package pack;
import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.Date;
import java.util.Locale;


public class Patient  extends ObjectWithResourceBundle  implements Serializable {
    private String name;
    private Doctor DoctorForPatient;
    private Doctor doctor;
    private Nurse nurse;
    private Date date;
    private enum Type {PROCEDURES,MEDICINE,OPERATION};
    private enum Status {EndOfTreatment,Violation,Other}//выписан по причинам окончания лечения,нарушения, другое
    private Type type;
    private Status status;
    transient public ResourceBundle resourceBundle = ResourceBundle.getBundle("Base");

    public Patient() {
        name = "";
        DoctorForPatient = new Doctor();
        doctor = new Doctor();
        nurse = new Nurse();
        type = Type.PROCEDURES;
        status = Status.Other;
        date = new Date();
        Locale.getDefault();
    }


    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public Status getStatus () {
        return status;
    }

    public String toString () {
        if (doctor.getName() == null)
            return new String(resourceBundle.getString("Patient")+":"+name+'/'+resourceBundle.getString("DoctorPrescribedTreatment")
                +':'+DoctorForPatient.getName()+'/'+resourceBundle.getString("Type")+':'+resourceBundle.getString(type.toString())+'/'+resourceBundle.getString("Nurse")
                    +':'+nurse.getName()+'/'+ resourceBundle.getString("Status")+':'+resourceBundle.getString(status.toString())+' '+
                    DateLocalFormat.getTimeStyle(date,Locale.getDefault()));
        else
            return new String(resourceBundle.getString("Patient")+":"+name+'/'+resourceBundle.getString("DoctorPrescribedTreatment")
                    +':'+DoctorForPatient.getName()+'/'+resourceBundle.getString("Type")+':'+resourceBundle.getString(type.toString())+'/'+resourceBundle.getString("Doctor")
                    +':'+doctor.getName()+'/'+ resourceBundle.getString("Status")+':'+resourceBundle.getString(status.toString())+' '+
                    DateLocalFormat.getTimeStyle(date,Locale.getDefault()));
    }

    public Patient (String name, String type, String status,Doctor DoctorName,Doctor doctor,Locale locale) {
        this.name = name;
        this.type = Type.valueOf(type);
        this.status = Status.valueOf(status);
        this.DoctorForPatient = DoctorName;
        this.DoctorForPatient = DoctorName;
        this.doctor = doctor;
        this.nurse = new Nurse ();
        date = new Date();
    }

    public Patient (String name, String type, String status, Doctor DoctorName, Nurse nurse,Locale locale) {
        this.name = name;
        this.type = Type.valueOf(type);
        this.status = Status.valueOf(status);
        this.DoctorForPatient = DoctorName;
        this.nurse = nurse;
        this.doctor = new Doctor();
        date = new Date();
    }
}
