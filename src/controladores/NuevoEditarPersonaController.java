/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import modelo.Persona;

/**
 * FXML Controller class
 *
 * @author jsanchez
 */
public class NuevoEditarPersonaController implements Initializable {

    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellidos;
    @FXML
    private Button botonCancelar;
    @FXML
    private Button bSalvar;
    
    private boolean cancelar;

    public void initPersona(Persona p)
    {
        
        textNombre.setText(p.getFirstName());
        textApellidos.setText(p.getLastName());
    }
    
    public Persona getPersona()
    {
        return new Persona(textNombre.getText(), textApellidos.getText());
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cancelar = false;
    }    

    // pulsado cancelar o bien salvar
    @FXML
    private void pulsadoBoton(ActionEvent event) {
        Button b = (Button) event.getSource();
        if (b== botonCancelar)
        {
            cancelar = true;
            // cerrar la ventana.
            b.getScene().getWindow().hide();
        }
        else {
            // complete el código cuando se pulsa salvar......
             if (textNombre.getText().isEmpty() || textApellidos.getText().isEmpty()) {
            return;
        }
        Stage stage;
        stage = (Stage) bSalvar.getScene().getWindow();
        stage.close();
        }
    }
    
    public boolean getCancelar()
    { return cancelar;
    
    }
    
    
}
