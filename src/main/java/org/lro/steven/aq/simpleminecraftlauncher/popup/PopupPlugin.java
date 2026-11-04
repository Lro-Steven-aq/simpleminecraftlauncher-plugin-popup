package org.lro.steven.aq.simpleminecraftlauncher.popup;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import org.lro.steven.aq.simpleminecraftlauncher.plugin.PluginManager;
import org.lro.steven.aq.simpleminecraftlauncher.plugin.api.Plugin;
import org.lro.steven.aq.simpleminecraftlauncher.plugin.api.event.impl.LogEvent;
import org.lro.steven.aq.simpleminecraftlauncher.plugin.api.event.impl.PopupEvent;
import org.lro.steven.aq.simpleminecraftlauncher.plugin.api.signalbus.EventConsumer;
import org.lro.steven.aq.simpleminecraftlauncher.plugin.api.signalbus.SignalBus;

import java.util.Optional;
public class PopupPlugin implements Plugin {
    @Override
    public void start(SignalBus signalBus) {

        signalBus.connect("ui.popup.error", (EventConsumer) event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(popupEvent.getTitle());
                alert.setHeaderText(popupEvent.getHead());
                alert.setContentText(popupEvent.getMessage());
                alert.showAndWait();
            });
        });

        signalBus.connect("ui.popup.info", (EventConsumer) event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(popupEvent.getTitle());
                alert.setHeaderText(popupEvent.getHead());
                alert.setContentText(popupEvent.getMessage());
                alert.showAndWait();
            });
        });

        signalBus.connect("ui.popup.warn", (EventConsumer) event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(popupEvent.getTitle());
                alert.setHeaderText(popupEvent.getHead());
                alert.setContentText(popupEvent.getMessage());
                alert.showAndWait();
            });
        });

        signalBus.connect("ui.popup.fatal", (EventConsumer) event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(popupEvent.getTitle());
                alert.setHeaderText(popupEvent.getHead());
                alert.setContentText(popupEvent.getMessage());
                alert.showAndWait();
            });
        });

        signalBus.connect("ui.popup.debug", (EventConsumer) event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle(popupEvent.getTitle());
                alert.setHeaderText(popupEvent.getHead());
                alert.setContentText(popupEvent.getMessage());
                alert.showAndWait();
            });
        });

        signalBus.connect("ui.popup.trace", (EventConsumer) event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.NONE);
                alert.setTitle(popupEvent.getTitle());
                alert.setHeaderText(popupEvent.getHead());
                alert.setContentText(popupEvent.getMessage());
                alert.showAndWait();
            });
        });

        signalBus.connect("ui.popup.confirmation", (EventConsumer) event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle(popupEvent.getTitle());
                alert.setHeaderText(popupEvent.getHead());
                alert.setContentText(popupEvent.getMessage());

                Optional<ButtonType> userChoice = alert.showAndWait();
                if (userChoice == null) {
                    PluginManager.get().getSignalBus().emit("log.warn", LogEvent.of("NULL return: can't get user's choice"));
                }
                if (userChoice.isPresent()) {
                    ButtonType buttonType = userChoice.get();
                    if (buttonType.equals(ButtonType.FINISH) ||
                            buttonType.equals(ButtonType.APPLY) ||
                            buttonType.equals(ButtonType.OK)) {
                        popupEvent.getCallableOnYes().run();
                    } else if (buttonType.equals(ButtonType.NO)) {
                        popupEvent.getCallableOnNo().run();
                    } else {
                        popupEvent.getCallableOnClose().run();
                    }
                }
            });
        });
    }
}
