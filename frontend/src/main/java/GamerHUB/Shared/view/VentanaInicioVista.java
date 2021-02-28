package GamerHUB.Shared.view;

import GamerHUB.GestionUsuarios.repository.ListaUsuario;
import GamerHUB.Shared.controllers.VistaInicioControlador;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class VentanaInicioVista {

    private Stage stageppal;
    private BorderPane rootLayout;
    private ListaUsuario listaUsuario;

    public VentanaInicioVista(Stage stageppal, BorderPane rootLayout, ListaUsuario listaUsuario) {
        this.stageppal = stageppal;
        this.rootLayout = rootLayout;
        this.listaUsuario = listaUsuario;
    }

    /**
     * Vista de acceso o "logeo" a la aplicación. Se solicitan los datos nombre de usuario
     * y contraseña al usuario para poder acceder al resto de la aplicación, si esta registrado,
     * o en su defecto registrarse antes.
     *
     * @throws IOException
     */
    public void LaunchInicio() throws IOException {

        URL url = new File("frontend/src/main/java/GamerHUB/Shared/view/VistaInicio.fxml").toURI().toURL();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(url);
        AnchorPane inicio = (AnchorPane) loader.load();

        rootLayout.setCenter(inicio);

        //inicio.setBackground();
        VistaInicioControlador controladorInicio = loader.getController();
        controladorInicio.setVista(this, stageppal, listaUsuario);

    }

    public Stage getStageppal() {
        return stageppal;
    }
}