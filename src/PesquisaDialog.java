import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PesquisaDialog extends JDialog {
    private Dataprocess dt;
    private JTextField pesquisaField;
    private JComboBox<String> tipoPesquisaCombo;
    private JTextArea resultadoArea;
    private JLabel mensagemLabel;

    public PesquisaDialog(Frame parent, Dataprocess dt) {
        super(parent, "Pesquisa", true);
        this.dt = dt;

        JLabel tipoPesquisaLabel = new JLabel("Tipo de Pesquisa:");
        tipoPesquisaCombo = new JComboBox<>(new String[]{"ID", "Nome"});

        JLabel pesquisaLabel = new JLabel("Valor:");
        pesquisaField = new JTextField(20);

        JButton botaoPesquisar = new JButton("Pesquisar");
        resultadoArea = new JTextArea(10, 40);
        resultadoArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultadoArea);

        mensagemLabel = new JLabel();

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));
        panel.add(tipoPesquisaLabel);
        panel.add(tipoPesquisaCombo);
        panel.add(pesquisaLabel);
        panel.add(pesquisaField);
        panel.add(new JLabel());
        panel.add(botaoPesquisar);
        panel.add(new JLabel());
        panel.add(mensagemLabel);

        botaoPesquisar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoPesquisa = (String) tipoPesquisaCombo.getSelectedItem();
                String valorPesquisa = pesquisaField.getText();
                if (tipoPesquisa.equals("ID")) {
                    try {
                        int codigo = Integer.parseInt(valorPesquisa);
                        String resultado = dt.consultarPorCodigo(codigo);
                        if (resultado.isEmpty()) {
                            mensagemLabel.setText("Nenhum resultado encontrado.");
                        } else {
                            mensagemLabel.setText("");
                            resultadoArea.setText(resultado);
                        }
                    } catch (NumberFormatException ex) {
                        mensagemLabel.setText("Digite um número válido.");
                        resultadoArea.setText("");
                    }
                } else if (tipoPesquisa.equals("Nome")) {
                    String resultado = dt.consultarPorNome(valorPesquisa);
                    if (resultado.isEmpty()) {
                        mensagemLabel.setText("Nenhum resultado encontrado.");
                    } else {
                        mensagemLabel.setText("");
                        resultadoArea.setText(resultado);
                    }
                }
            }
        });

        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        pack();
    }
}
