package ru.mcmerphy.rosreestr.controllers.layout.components.alerts;

import javafx.application.HostServices;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.mcmerphy.rosreestr.controllers.layout.components.IconCreditProducer;

import java.io.IOException;

/**
 * Represents about window.
 */
public class AboutAlert extends Alert {

    public AboutAlert(HostServices hostServices) {
        super(Alert.AlertType.INFORMATION);
        setInfoIcon();
        setHeaderText("О программе");
        setTitle("О программе");
        getDialogPane().setContent(getVBox(hostServices));
    }

    /**
     * Set info icon to window.
     */
    private void setInfoIcon() {
        try {
            Stage stage = (Stage) getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(
                    AboutAlert.class.getResource("/images/info.png").openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Node getVBox(HostServices hostServices) {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(
                new Text("Данная программа предназначена для облегчения работы с выписками помещений из росреестра."),
                new Text("Поставляется как есть."),
                new Text("Возможна некорректная работа."),
                new Text("Не стоит ей доверять..."),
                new Text("P.S. Не доверяй никому..."),
                new Text(),
                new IconCreditProducer(hostServices)
                        .setIconDescription("add folder")
                        .setAuthorHyperLink("Linh Pham", "https://www.flaticon.com/authors/linh-pham")
                        .produce(),
                new IconCreditProducer(hostServices)
                        .setIconDescription("add file")
                        .setAuthorHyperLink("Linh Pham", "https://www.flaticon.com/authors/linh-pham")
                        .produce(),
                new IconCreditProducer(hostServices)
                        .setIconDescription("open file")
                        .setAuthorHyperLink("Freepik", "http://www.freepik.com")
                        .produce()
        );

        return vBox;
    }

}
