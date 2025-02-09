import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProjetoUm extends JPanel {
    private double saldo;
    private StringBuilder extrato;

    public ProjetoUm(double saldoInicial) {
        this.saldo = saldoInicial;
        this.extrato = new StringBuilder();
    }

    public void depositar(int valor) {
        saldo += valor;
        extrato.append("Depósito: +").append(valor).append("\n");
    }

    public void sacar(int valor) {
        if (valor > saldo) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente.");
        } else {
            saldo -= valor;
            extrato.append("Saque: -").append(valor).append("\n");
        }
    }

    public void pix(int valor, String destino) {
        if (valor > saldo) {
            JOptionPane.showMessageDialog(null, "Saldo insuficiente para transferência PIX.");
        } else {
            saldo -= valor;
            extrato.append("PIX para ").append(destino).append(": -").append(valor).append("\n");
        }
    }

    public String getExtrato() {
        return extrato.toString();
    }

    public double getSaldo() {
        return saldo;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Banco");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel saldoLabel = new JLabel("Saldo atual:");
        saldoLabel.setBounds(20, 20, 100, 20);
        panel.add(saldoLabel);

        JTextField saldoTextField = new JTextField(20);
        saldoTextField.setBounds(120, 20, 150, 20);
        saldoTextField.setEditable(false);
        panel.add(saldoTextField);

        JButton depositarButton = new JButton("Depositar");
        depositarButton.setBounds(10, 50, 150, 20);
        panel.add(depositarButton);

        JButton sacarButton = new JButton("Sacar");
        sacarButton.setBounds(10, 80, 150, 20);
        panel.add(sacarButton);

        JButton pixButton = new JButton("PIX");
        pixButton.setBounds(10, 110, 150, 20);
        panel.add(pixButton);

        JButton extratoButton = new JButton("Extrato");
        extratoButton.setBounds(10, 140, 150, 20);
        panel.add(extratoButton);

        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(10, 170, 150, 20);
        panel.add(sairButton);

        ProjetoUm conta = new ProjetoUm(0);
        saldoTextField.setText(Double.toString(conta.getSaldo()));

        depositarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int valorDeposito = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do depósito:"));
                conta.depositar(valorDeposito);
                saldoTextField.setText(Double.toString(conta.getSaldo()));
            }
        });

        sacarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int valorSaque = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do saque:"));
                conta.sacar(valorSaque);
                saldoTextField.setText(Double.toString(conta.getSaldo()));
            }
        });

        pixButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                String destino = JOptionPane.showInputDialog(null, "Digite o destino do PIX:");
                int valorPix = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o valor do PIX:"));
                conta.pix(valorPix, destino);
                saldoTextField.setText(Double.toString(conta.getSaldo()));
            }
        });

        extratoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, conta.getExtrato(), "Extrato", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        sairButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(null, "Deseja realmente sair do programa?", "Sair", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        frame.setSize(200, 250);
        frame.setVisible(true);
    }
}
