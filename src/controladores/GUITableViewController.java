/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import modelo.Persona;
import javafx.scene.image.Image;
 import javafx.scene.image.ImageView;

/**
 *
 * @author jsanchez
 */
public class GUITableViewController implements Initializable {
    
    
    @FXML
    private TableView<Persona> vistaTablafxID;
    @FXML
    private TableColumn<Persona, String> TableColumna1fxID;
    @FXML
    private TableColumn<Persona, String> TableColumna2fxID;
    @FXML
    private TableColumn<Persona,String> imageColumnfxID;
    @FXML
    private Button botonAdd;
    @FXML
    private Button BModificar;
    @FXML
    private Button BBorrar;
    
    
    private ObservableList<Persona> datosPersonas = FXCollections.observableArrayList(); // datos para visualizar
    
    
    public void initDatos(ObservableList<Persona> datos)
    {
        datosPersonas = datos;
        vistaTablafxID.setItems(datosPersonas);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        imageColumnfxID.setText("Imagen");
        TableColumna1fxID.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        TableColumna2fxID.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        imageColumnfxID.setCellValueFactory(celda3 -> celda3.getValue().pathImagenProperty());
        
        imageColumnfxID.setCellFactory(columna -> {
        return new TableCell<Persona,String>(){
            private ImageView view = new ImageView();
            @Override
            protected void updateItem(String item, boolean empty){
                super.updateItem(item, empty);
                if(item == null || empty) setGraphic(null);
                else{
                    Image image = new Image(GUITableViewController.class.getResourceAsStream(item),
                                            40, 40, true, true);
                    view.setImage(image);
                    setGraphic(view);
                }
            }
        };
    });
       

    }    

    @FXML
    private void pulsadoBoton(ActionEvent event) throws IOException {
        
        Button b = ((Button) event.getSource());
        if (b== botonAdd) addPersona();
        else if (b == BModificar){
            // modificar persona, complete el código....
            modPersona();
        }else{
        delPersona();
        }
                
    }
    // abre la ventana secundaria para en este caso añadir una persona.
    private void addPersona() 
    {
        try {
            FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("/vista/NuevoEditarPersona.fxml"));
            Parent root = miCargador.load();
            
            // acceso al controlador de datos persona
            NuevoEditarPersonaController controladorPersona = miCargador.<NuevoEditarPersonaController>getController();
            Persona persona = new Persona("","");
            controladorPersona.initPersona(persona);
            
            Scene scene = new Scene(root,400,400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Añadir nueva persona");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
            if (!controladorPersona.getCancelar())
            { // no ha dado a cancelar. debe obtener el valor de p y añadirlo a la tabla complete el código.
               Persona p;
                p = controladorPersona.getPersona();
                datosPersonas.add(p);
            }
                    
                    
        } catch (IOException ex) {
            Logger.getLogger(GUITableViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }

    private void modPersona() throws IOException {
        FXMLLoader miCargador;
        miCargador = new FXMLLoader(getClass().getResource("/vista/NuevoEditarPersona.fxml"));
        AnchorPane root = (AnchorPane) miCargador.load();
        int i;
        i = vistaTablafxID.getSelectionModel().getSelectedIndex();
        Persona persona = datosPersonas.get(i);
        NuevoEditarPersonaController controladorPersona = miCargador.<NuevoEditarPersonaController>getController();
        controladorPersona.initPersona(persona);

        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Editar Persona");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
        
        if (!controladorPersona.getCancelar()) {// no ha dado a cancelar. debe obtener el valor de p y añadirlo a la tabla complete el código.
            persona = controladorPersona.getPersona();
            datosPersonas.add(i, persona);
            datosPersonas.remove(i+1);
        } //To change body of generated methods, choose Tools | Templates.
    }
    
    private void delPersona() throws IOException {
        int i;
        i = vistaTablafxID.getSelectionModel().getSelectedIndex();
        if (i >= 0) {
            datosPersonas.remove(i);
        }
    }
    
    /*imageColumnfxID.setCellFactory(columna ->{
    return new TableCell<Persona,String>(){
    private ImageView view = new ImageView();
    @Override
    protected void updateItem(String item, boolean empty){
    super.updateItem(item, empty);
    if(item == null || empty) setGraphic(null);
    else{
    File imageFile = new File(item);
    String fileLocation = imageFile.toURI().toString();
    Image image = new Image(fileLocation, 40, 40, true, true);
    view.setImage(image);
    setGraphic(view);
    }
    }
    };
    });*/

    
    
    
}
 
   
