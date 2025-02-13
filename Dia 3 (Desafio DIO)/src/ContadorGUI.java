import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ContadorGUI extends JFrame {
    private JTextField campoParametroUm;
    private JTextField campoParametroDois;
    private JTextArea areaResultado;

    public ContadorGUI() {
        setTitle("Contador de Números");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Componentes
        JLabel label1 = new JLabel("Digite o primeiro parâmetro:");
        campoParametroUm = new JTextField(10);

        JLabel label2 = new JLabel("Digite o segundo parâmetro:");
        campoParametroDois = new JTextField(10);

        JButton botaoContar = new JButton("Contar");
        areaResultado = new JTextArea(10, 30);
        areaResultado.setEditable(false);

        // Adicionando componentes ao frame
        add(label1);
        add(campoParametroUm);
        add(label2);
        add(campoParametroDois);
        add(botaoContar);
        add(new JScrollPane(areaResultado));

        // Ação do botão
        botaoContar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarContagem();
            }
        });
    }

    private void realizarContagem() {
        try {
            int parametroUm = Integer.parseInt(campoParametroUm.getText());
            int parametroDois = Integer.parseInt(campoParametroDois.getText());

            // Validar os parâmetros
            if (parametroUm > parametroDois) {
                throw new ParametrosInvalidosException();
            }

            // Limpa a área de resultado
            areaResultado.setText("");

            // Realiza a contagem e exibe os números
            StringBuilder resultado = new StringBuilder();
            for (int i = 1; i <= (parametroDois - parametroUm); i++) {
                resultado.append("Contando: ").append(i).append("\n");
            }
            areaResultado.setText(resultado.toString());

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor, insira números válidos!", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (ParametrosInvalidosException ex) {
            JOptionPane.showMessageDialog(this, "O segundo parâmetro deve ser maior que o primeiro!", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ContadorGUI frame = new ContadorGUI();
            frame.setVisible(true);
        });
    }
}

// Exceção personalizada
class ParametrosInvalidosException extends Exception {
}
