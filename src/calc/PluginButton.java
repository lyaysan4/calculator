package calc;

import javax.swing.*;
import java.awt.*;

public abstract class PluginButton extends JButton {
    Font font = new Font("Normal", Font.BOLD, 16);
    abstract JButton createPluginButton();
}
