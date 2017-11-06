package calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

import static java.awt.Color.WHITE;
import static java.awt.ComponentOrientation.RIGHT_TO_LEFT;

class Calculator extends JFrame {

    private JTextField display;

    private String first = "";
    private String second = "";
    private String sign;
    private boolean dotPlaced = false;

    private JButton multipleButton = null;
    private JButton divideButton = null;
    private JButton addButton = null;
    private JButton subtractButton = null;
    private JButton sqrtButton = null;

    Calculator() {
        super("Calculator");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        Container container = this.getContentPane();
        container.setBackground(WHITE);
        display = new JTextField("");
        JPanel buttonsPanel = new JPanel();
        JPanel pluginButtonsPanel = new JPanel();
        JPanel inputFieldPanel = new JPanel();

        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.setLayout(new GridLayout(5, 3, 0, 0));

        pluginButtonsPanel.setBackground(Color.WHITE);
        pluginButtonsPanel.setLayout(new GridLayout(5, 1, 0, 0));

        display.setPreferredSize(new Dimension(400, 60));
        display.setFont(new Font("Normal", Font.BOLD, 40));
        inputFieldPanel.setComponentOrientation(RIGHT_TO_LEFT);
        inputFieldPanel.add(display);
        display.setEditable(false);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setSize(450, 450 / 10);

        JMenu pluginsMenu = new JMenu("Plugins");

        JCheckBoxMenuItem addition = new JCheckBoxMenuItem("Addition");
        JCheckBoxMenuItem division = new JCheckBoxMenuItem("Division");
        JCheckBoxMenuItem multiplication = new JCheckBoxMenuItem("Multiplication");
        JCheckBoxMenuItem subtraction = new JCheckBoxMenuItem("Subtraction");
        JCheckBoxMenuItem sqrt = new JCheckBoxMenuItem("Sqrt");

        addition.addItemListener(e -> {
            if (e.getStateChange() == e.SELECTED) {
                try {
                    File pluginsLocation = new File("pluginsJar");
                    File[] files = pluginsLocation.listFiles(file -> file.getPath().endsWith("AddPlugin.jar"));
                    URLClassLoader ucl = new URLClassLoader(new URL[]{files[0].toURI().toURL()});
                    for (PluginButton pluginButton : ServiceLoader.load(PluginButton.class, ucl)) {
                        addButton = pluginButton.createPluginButton();
                        pluginButtonsPanel.add(addButton);
                        addButton.addActionListener(operatorListener);
                        this.pack();
                    }
                } catch (Exception ignored) {
                }
            } else {
                pluginButtonsPanel.remove(addButton);
                this.pack();
            }
        });
        subtraction.addItemListener(e -> {
            if (e.getStateChange() == e.SELECTED) {
                try {
                    File pluginsLocation = new File("pluginsJar");
                    File[] files = pluginsLocation.listFiles(file -> file.getPath().endsWith("SubtractionPlugin.jar"));
                    URLClassLoader ucl = new URLClassLoader(new URL[]{files[0].toURI().toURL()});
                    for (PluginButton pluginButton : ServiceLoader.load(PluginButton.class, ucl)) {
                        subtractButton = pluginButton.createPluginButton();
                        pluginButtonsPanel.add(subtractButton);
                        subtractButton.addActionListener(operatorListener);
                        this.pack();
                    }
                } catch (Exception ignored) {
                }
            } else {
                pluginButtonsPanel.remove(subtractButton);
                this.pack();
            }
        });
        multiplication.addItemListener(e -> {
            if (e.getStateChange() == e.SELECTED) {
                try {
                    File pluginsLocation = new File("pluginsJar");
                    File[] files = pluginsLocation.listFiles(file -> file.getPath().endsWith("MultiplicationPlugin.jar"));
                    URLClassLoader ucl = new URLClassLoader(new URL[]{files[0].toURI().toURL()});
                    for (PluginButton pluginButton : ServiceLoader.load(PluginButton.class, ucl)) {
                        multipleButton = pluginButton.createPluginButton();
                        pluginButtonsPanel.add(multipleButton);
                        multipleButton.addActionListener(operatorListener);
                        this.pack();
                    }
                } catch (Exception ignored) {
                }
            } else {
                pluginButtonsPanel.remove(multipleButton);
                this.pack();
            }
        });
        division.addItemListener(e -> {
            if (e.getStateChange() == e.SELECTED) {
                try {
                    File pluginsLocation = new File("pluginsJar");
                    File[] files = pluginsLocation.listFiles(file -> file.getPath().endsWith("DivisionPlugin.jar"));
                    URLClassLoader ucl = new URLClassLoader(new URL[]{files[0].toURI().toURL()});
                    for (PluginButton pluginButton : ServiceLoader.load(PluginButton.class, ucl)) {
                        divideButton = pluginButton.createPluginButton();
                        pluginButtonsPanel.add(divideButton);
                        divideButton.addActionListener(operatorListener);
                        this.pack();
                    }
                } catch (Exception ignored) {
                }
            } else {
                pluginButtonsPanel.remove(divideButton);
                this.pack();
            }
        });
        sqrt.addItemListener(e -> {
            if (e.getStateChange() == e.SELECTED) {
                try {
                    File pluginsLocation = new File("pluginsJar");
                    File[] files = pluginsLocation.listFiles(file -> file.getPath().endsWith("SqrtPlugin.jar"));
                    URLClassLoader ucl = new URLClassLoader(new URL[]{files[0].toURI().toURL()});
                    for (PluginButton pluginButton : ServiceLoader.load(PluginButton.class, ucl)) {
                        sqrtButton = pluginButton.createPluginButton();
                        pluginButtonsPanel.add(sqrtButton);
                        sqrtButton.addActionListener(operatorListener);
                        this.pack();
                    }
                } catch (Exception ignored) {
                }
            } else {
                pluginButtonsPanel.remove(sqrtButton);
                this.pack();
            }
        });

        pluginsMenu.add(addition);
        pluginsMenu.add(division);
        pluginsMenu.add(multiplication);
        pluginsMenu.add(subtraction);
        pluginsMenu.add(sqrt);

        menuBar.add(pluginsMenu);

        this.setJMenuBar(menuBar);

        String[] numberButtonNames = new String[]{"7", "8", "9", "4", "5", "6", "1", "2", "3", "0"};
        String[] otherButtonNames = new String[]{"+/-", "AC", ".", "="};

        Font font2 = new Font("Normal", Font.BOLD, 16);

        for (String buttonName : numberButtonNames) {
            JButton button = new JButton(buttonName);
            button.addActionListener(numberListener);
            button.setFont(font2);
            buttonsPanel.add(button);
        }

        for (String buttonName : otherButtonNames) {
            JButton button = new JButton(buttonName);
            button.addActionListener(otherListener);
            button.setFont(font2);
            buttonsPanel.add(button);
        }
        JButton stubButton = new JButton("");
        stubButton.setEnabled(false);
        buttonsPanel.add(stubButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        mainPanel.add(buttonsPanel);
        mainPanel.add(pluginButtonsPanel);
        container.add(inputFieldPanel, "North");
        container.add(mainPanel, "Center");
        this.setVisible(true);
        this.pack();
        display.setText("0");
    }

    private ActionListener numberListener = e -> {
        String digit = e.getActionCommand();
        if (sign == null) {
            first += digit;
            display.setText(first);
        } else {
            second += digit;
            display.setText(second);
        }
    };

    private ActionListener operatorListener = e -> {
        String command = e.getActionCommand();
        if (!first.isEmpty() && !second.isEmpty() && sign != null) {
            performAction();
        }
        if (command.equals("\u221A")) {
            first = "" + Math.sqrt(Double.parseDouble(first));
            second = "";
            sign = null;
            dotPlaced = first.contains(".");
        } else {
            sign = command;
            second = "";
            dotPlaced = false;
        }
        display.setText(first);
    };

    private ActionListener otherListener = e -> {
        String command = e.getActionCommand();
        switch (command) {
            case "=":
                display.setText(performAction());
                break;
            case "AC":
                first = "";
                second = "";
                sign = null;
                dotPlaced = false;
                display.setText("0");
                break;
            case ".":
                if (!dotPlaced) {
                    if (sign == null) {
                        first += command;
                        display.setText(first);
                    } else {
                        second += command;
                        display.setText(second);
                    }
                    dotPlaced = true;
                }
                break;
            case "+/-":
                String text = display.getText();
                first = first.isEmpty() ? "0" : first;
                if (text.equals(first)) {
                    first = "" + Double.parseDouble(first) * -1;
                    display.setText(first);
                } else {
                    second = "" + Double.parseDouble(second) * -1;
                    display.setText(second);
                }
                break;
        }
    };

    private String performAction() {
        if (sign != null) {
            first = first.isEmpty() ? "0" : first;
            second = second.isEmpty() ? "0" : second;
            switch (sign) {
                case "+":
                    first = "" + (Double.parseDouble(first) + Double.parseDouble(second));
                    break;
                case "-":
                    first = "" + (Double.parseDouble(first) - Double.parseDouble(second));
                    break;
                case "*":
                    first = "" + (Double.parseDouble(first) * Double.parseDouble(second));
                    break;
                case "/":
                    first = "" + (Double.parseDouble(first) / Double.parseDouble(second));
                    break;
            }
            dotPlaced = first.contains(".");
            return first;
        } else {
            return !second.isEmpty() ? second : first;
        }
    }
}
