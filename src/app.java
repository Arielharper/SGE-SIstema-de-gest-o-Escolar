import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class App {
    private Dataprocess dt;

    public App() {
        dt = new Dataprocess();
        dt.iniciar(); // Criar banco de dados e tabelas
    }

    private void adicionarAluno() {
        String nome = JOptionPane.showInputDialog("Digite o nome do aluno:");
        String idadeStr = JOptionPane.showInputDialog("Digite a idade do aluno:");
        int idade = Integer.parseInt(idadeStr);
        dt.setNewAluno(nome, idade);
    }

    private void adicionarProfessor() {
        String nome = JOptionPane.showInputDialog("Digite o nome do professor:");
        String cpf = JOptionPane.showInputDialog("Digite o CPF do professor:");
        String departamento = JOptionPane.showInputDialog("Digite o departamento do professor:");
        dt.setNewProfessor(nome, cpf, departamento);
    }

    private void adicionarDisciplina() {
        String nome = JOptionPane.showInputDialog("Digite o nome da disciplina:");
        int professorId = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do professor responsável:"));
        dt.setNewDisciplina(nome, professorId);
    }

    private void abrirPesquisaDialog() {
        PesquisaDialog pesquisaDialog = new PesquisaDialog(null, dt);
        pesquisaDialog.setVisible(true);
    }

    public void run() {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("SGE Sistema Genrenciador Escolar");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout());

            JButton botaoAdicionarAluno = new JButton("Adicionar Aluno");
            JButton botaoAdicionarProfessor = new JButton("Adicionar Professor");
            JButton botaoAdicionarDisciplina = new JButton("Adicionar Disciplina");
            JButton botaoPesquisa = new JButton("Pesquisar");

            JTextArea areaDeTexto = new JTextArea(10, 50);
            JScrollPane scrollPane = new JScrollPane(areaDeTexto);

            botaoAdicionarAluno.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adicionarAluno();
                }
            });

            botaoAdicionarProfessor.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adicionarProfessor();
                }
            });

            botaoAdicionarDisciplina.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adicionarDisciplina();
                }
            });

            botaoPesquisa.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    abrirPesquisaDialog();
                }
            });

            JPanel buttonPanel = new JPanel(new FlowLayout());
            buttonPanel.add(botaoAdicionarAluno);
            buttonPanel.add(botaoAdicionarProfessor);
            buttonPanel.add(botaoAdicionarDisciplina);
            buttonPanel.add(botaoPesquisa);

            frame.add(buttonPanel, BorderLayout.NORTH);
            frame.add(scrollPane, BorderLayout.CENTER);

            frame.pack();
            frame.setVisible(true);
        });
    }

    public static void main(String[] args) {
        App app = new App();
        app.run();
    }
}
