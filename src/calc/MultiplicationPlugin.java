package calc;

import javax.swing.*;

public class MultiplicationPlugin extends PluginButton{

    private final String buttonName = "*";

    public String getButtonName() {
        return buttonName;
    }

    @Override
    JButton createPluginButton() {

        JButton pluginButton = new JButton("*");
        pluginButton.setFont(font);
        return  pluginButton;
    }
}
