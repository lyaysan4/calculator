package calc;

import javax.swing.*;

public class SqrtPlugin extends PluginButton {

    private final String buttonName = "sqrt";

    public String getButtonName() {
        return buttonName;
    }

    @Override
    JButton createPluginButton() {

        JButton pluginButton = new JButton("\u221A");
        pluginButton.setFont(font);
        return pluginButton;
    }
}
