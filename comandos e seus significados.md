PreparedStatment:

Se vc faz uma query assim:

String sql = "SELECT * FROM Aluno WHERE Nome LIKE '%" + nome + "%'";
Alguém poderia digitar o seguinte nome:

';DELETE * FROM Aluno WHERE NOME LIKE ';
Sua String final ficaria:

SELECT * FROM Aluno WHERE Nome LIKE '%';DELETE * FROM Aluno WHERE NOME LIKE '%';
O que é perfeitamente válido. E apaga toda sua tabela de alunos (embora o mais provável, já que isso esbarraria em ForeignKeys seria o cara usar no lugar daquele DELETE um UPDATE, alterando a senha de todos para uma conhecida).

O PreparedStatement não só é mais seguro, mas também trata automaticamente caracteres como as '. Assim o aluno MacDonald’s não daria mais pau no seu SQL.
Ele também lida sozinho com formatos de datas (que não são padronizados no SQL puro, e portanto, dependem do banco) e outros detalhes chatos.

Finalmente, o PreparedStatement mantém a query pré-compilada no banco de dados. Assim, se vc for enviar várias vezes a mesma query, a execução provavelmente será muito mais rápida, já que vc só precisa trocar os valores associados a consulta, reaproveitando o plano de execução já criado.
