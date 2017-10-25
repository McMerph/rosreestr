package ru.mcmerphy.rosreestr.controllers.layout.components;

import javafx.application.HostServices;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import org.apache.commons.lang3.StringUtils;

/**
 * Icon credit producer. Used to produce layout for icon credit.
 */
public class IconCreditProducer {

    private final HostServices hostServices;

    private final HBox hBox;

    private String iconDescription;
    private Hyperlink authorHyperLink;

    public IconCreditProducer(HostServices hostServices) {
        this.hostServices = hostServices;
        hBox = new HBox();
        hBox.setAlignment(Pos.BASELINE_LEFT);
    }

    public IconCreditProducer setIconDescription(String iconDescription) {
        this.iconDescription = iconDescription;
        return this;
    }

    public IconCreditProducer setAuthorHyperLink(String text, String uri) {
        authorHyperLink = getHyperLink(text, uri);
        return this;
    }

    /**
     * @return {@link Node} instance to represent icon credit.
     */
    public Node produce() {
        hBox.getChildren().addAll(
                new Label(String.join(" ", formatIconDescription(iconDescription), "icon made by")),
                authorHyperLink,
                new Label("from"),
                getHyperLink("www.flaticon.com", "https://www.flaticon.com/"),
                new Label("is licensed by"),
                getHyperLink("Creative Commons BY 3.0", "http://creativecommons.org/licenses/by/3.0/")
        );
        return hBox;
    }

    private Hyperlink getHyperLink(String text, String uri) {
        Hyperlink hyperlink = new Hyperlink(text);
        hyperlink.setOnAction(event -> hostServices.showDocument(uri));
        return hyperlink;
    }


    private String formatIconDescription(String iconDescription) {
        return "\"" + StringUtils.capitalize(iconDescription) + "\"";
    }

}
