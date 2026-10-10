package org.lro.steven.aq.simpleminecraftlauncher.popup;

import org.lro.steven.aq.simpleminecraftlauncher.plugin.api.Plugin;
import org.lro.steven.aq.simpleminecraftlauncher.plugin.api.event.impl.PopupEvent;
import org.lro.steven.aq.simpleminecraftlauncher.plugin.api.signalbus.SignalBus;

import javax.swing.*;

public class PopupPlugin implements Plugin {
    @Override
    public void start(SignalBus signalBus) {

        signalBus.connect("ui.popup.error", event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            JOptionPane.showMessageDialog(null,
                    popupEvent.getMessage(),
                    popupEvent.getTitle(),
                    JOptionPane.ERROR_MESSAGE);
        });

        signalBus.connect("ui.popup.info", event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            JOptionPane.showMessageDialog(null,
                    popupEvent.getMessage(),
                    popupEvent.getTitle(),
                    JOptionPane.INFORMATION_MESSAGE);
        });

        signalBus.connect("ui.popup.warn", event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            JOptionPane.showMessageDialog(null,
                    popupEvent.getMessage(),
                    popupEvent.getTitle(),
                    JOptionPane.WARNING_MESSAGE);
        });

        signalBus.connect("ui.popup.fatal", event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            JOptionPane.showMessageDialog(null,
                    popupEvent.getMessage(),
                    popupEvent.getTitle(),
                    JOptionPane.ERROR_MESSAGE);
        });

        signalBus.connect("ui.popup.debug", event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            JOptionPane.showMessageDialog(null,
                    popupEvent.getMessage(),
                    popupEvent.getTitle(),
                    JOptionPane.INFORMATION_MESSAGE);
        });

        signalBus.connect("ui.popup.trace", event -> {
            PopupEvent popupEvent = (PopupEvent) event;
            JOptionPane.showMessageDialog(null,
                    popupEvent.getMessage(),
                    popupEvent.getTitle(),
                    JOptionPane.INFORMATION_MESSAGE);
        });
    }
}
