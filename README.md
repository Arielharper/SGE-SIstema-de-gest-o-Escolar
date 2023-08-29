# Sistema de Gerenciamento Escolar (SGE)

O Sistema de Gerenciamento Escolar (SGE) é um projeto que visa criar um sistema para gerenciar informações de alunos, professores e disciplinas em uma instituição educacional. O projeto utiliza a linguagem Java e o banco de dados MySQL para armazenar e recuperar os dados.

## Funcionalidades

O projeto SGE possui as seguintes funcionalidades:

1. **Criação de Tabelas:** O sistema inicia criando tabelas no banco de dados para armazenar informações sobre alunos, professores, disciplinas e relações entre eles.

2. **Adicionar Registros:** É possível adicionar registros de alunos, professores e disciplinas por meio de interfaces gráficas.

3. **Consultas por Código:** O sistema permite consultar informações específicas de alunos, professores e disciplinas com base em seus códigos de identificação.

4. **Consultas por Nome:** Também é possível realizar consultas por nome, retornando resultados que correspondam ao nome pesquisado.

5. **Listagem de Registros:** O sistema fornece a capacidade de listar todos os alunos, professores e disciplinas cadastrados.

## Estrutura do Projeto

O projeto SGE é dividido em classes Java que representam as funcionalidades principais do sistema:

- `Dataprocess.java`: Essa classe lida com a conexão ao banco de dados, criação de tabelas e execução de consultas e inserções de registros.

- `PesquisaDialog.java`: Uma classe que cria uma interface gráfica para realizar pesquisas de registros por código.

- `App.java`: A classe principal que cria a interface principal do sistema com botões para adicionar registros e realizar consultas.

## Requisitos

- Java JDK instalado
- MySQL Server instalado
- Biblioteca de driver JDBC para MySQL

## Como Executar o Projeto

1. Certifique-se de ter o MySQL Server instalado e em execução.

2. Importe a biblioteca de driver JDBC para MySQL no projeto.

3. Execute a classe `App.java` para iniciar a interface do sistema.

## Conclusão

O Sistema de Gerenciamento Escolar (SGE) é um projeto Java que demonstra como criar um sistema simples de gerenciamento de informações escolares usando um banco de dados MySQL. Ele oferece funcionalidades básicas para adicionar e consultar registros de alunos, professores e disciplinas.

