/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tableview;

import controladores.GUITableViewController;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.Persona;

/**
 *
 * @author jsanchez
 */
public class TableView extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("/vista/GUITableView.fxml"));
        // Obtener el controlador de la ventana principal para pasarle los datos
        FXMLLoader miCargador  = new FXMLLoader(getClass().getResource("/vista/GUITableView.fxml"));
        Parent root = miCargador.load();

        GUITableViewController controladorPrincipal = miCargador.<GUITableViewController>getController();
        controladorPrincipal.initDatos(getPersonas());

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    // Inicialización de la lista de personas, podría hacerse mejor en el controlador principal.
    private ObservableList<Persona> getPersonas()
    {
        //ObservableList<Persona> datos = null;
        List<Persona> listaPersonas = new ArrayList<Persona>();
        listaPersonas.add(new Persona("Pedro", "Garcia"));
        listaPersonas.add(new Persona("José", "Pérez"));
        return FXCollections.observableList(listaPersonas);
        
    }
    
    
}
