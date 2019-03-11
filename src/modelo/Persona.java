/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author jsanchez
 */
public class Persona {

private final StringProperty firstName = new SimpleStringProperty();
private final StringProperty lastName = new SimpleStringProperty();
private final StringProperty pathImagen = new SimpleStringProperty();

Object v = "/recursos/Sonriente.png";
StringProperty var = new SimpleStringProperty((String) v);

    public StringProperty setPathImagen() {
        return var;
    }

public Persona(String firstName, String lastName) {
   this.firstName.setValue(firstName);
   this.lastName.setValue(lastName);
    }

    public final void setFirstName(String value) {
        firstName.set(value);
    }

    public final String getFirstName() {
        return firstName.get();
    }

    public final StringProperty firstNameProperty() {
        return firstName;
    }

    public final void setLastName(String value) {
        lastName.set(value);
    }

    public final String getLastName() {
        return lastName.get();
    }

    public final StringProperty lastNameProperty() {
        return lastName;
    }
   
    public StringProperty pathImagenProperty() {
        return var;
    }

}

