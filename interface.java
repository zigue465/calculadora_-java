import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface extends JFrame implements ActionListener {
    // Componentes da calculadora
    private JTextField display;
    private JButton[] botoes;
    private JPanel painel;
    private JPanel painelBotoes;

    // Variáveis de estado
    private double num1;
    private double num2;
    private char operator;
    private boolean waitingOperator;

    public Interface() {
        // Configura a janela
        setTitle("Programação Orientada ao Objeto - 2º GTI");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Inicializa as variáveis
        num1 = 0.0;
        num2 = 0.0;
        operator = ' ';
        waitingOperator = true;

        // Cria o display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 64));
        add(display, BorderLayout.NORTH);

        // Cria o painel de botões
        painel = new JPanel(new BorderLayout());
        // Adiciona espaço nas laterais do painel de botões
        painel.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));

        // Cria os botões em um painel interno
        botoes = new JButton[17];
        painelBotoes = new JPanel(new GridLayout(5, 4, 10, 10)); // Espaçamento entre os botões
        for (int i = 0; i < 17; i++) {
            botoes[i] = new JButton();
            painelBotoes.add(botoes[i]);
            botoes[i].addActionListener(this);
        }

        botoes[0].setText("7");
        botoes[1].setText("8");
        botoes[2].setText("9");
        botoes[3].setText("/");
        botoes[4].setText("4");
        botoes[5].setText("5");
        botoes[6].setText("6");
        botoes[7].setText("*");
        botoes[8].setText("1");
        botoes[9].setText("2");
        botoes[10].setText("3");
        botoes[11].setText("-");
        botoes[12].setText("0");
        botoes[13].setText(".");
        botoes[14].setText("=");
        botoes[15].setText("+");
        botoes[16].setText("C");

        painel.add(painelBotoes, BorderLayout.CENTER);
        add(painel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        if (Character.isDigit(comando.charAt(0))) {
            if (waitingOperator) {
                display.setText(comando);
                waitingOperator = false;
            } else {
                display.setText(display.getText() + comando);
            }
        } else if (comando.charAt(0) == '.') {
            if (waitingOperator) {
                display.setText("0.");
                waitingOperator = false;
            } else if (display.getText().indexOf('.') == -1) {
                display.setText(display.getText() + ".");
            }
        } else if (comando.charAt(0) == 'C') {
            display.setText(""); // Limpar o visor
            num1 = 0.0;
            num2 = 0.0;
            operator = ' ';
            waitingOperator = true;
        } else if (comando.charAt(0) == '=') {
            if (!waitingOperator) {
                num2 = Double.parseDouble(display.getText());
                double resultado = calcular();
                display.setText(Double.toString(resultado));
                waitingOperator = true;
            }
        } else {
            if (!waitingOperator) {
                num2 = Double.parseDouble(display.getText());
                double resultado = calcular();
                display.setText(Double.toString(resultado));
                num1 = resultado; // Defina num1 para o resultado anterior
                operator = comando.charAt(0);
                waitingOperator = true;
            } else {
                operator = comando.charAt(0);
            }
        }
    }

    private double calcular() {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0)
                    return num1 / num2;
                else
                    return Double.NaN; // Divisão por zero
            default:
                return num2;
        }
    }
}
