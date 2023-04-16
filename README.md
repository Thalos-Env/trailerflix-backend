<h2>Trailerflix</h2>
<p>
Um serviço de streaming voltado para trailers de diversos filmes. 
</p>

<hr>

<h3>Como rodar o projeto</h3>
<p>
1. Update com o Maven para fazer o build das dependências. <br>
<i> Botão direito no nome do projeto > Maven > update project... > Ok </i> <br><br>
2. Necessário configurar a conexão com o banco MySQL. <br>
No aquivo <i>.properties </i> há as variáveis de ambiente que precisam ser configuradas: <br>
<code>spring.datasource.url=${db_url}</code> <br>
<code>spring.datasource.username=${db_username}</code> <br>
<code>spring.datasource.password=${db_password}</code> <br>

Documento auxiliar para configuração de variável de ambiente nas IDEs STS, VS Code e Intellij: <br>
[Variaveis_de_ambiente.pdf](https://github.com/Thalos-Env/trailerflix-backend/files/11240989/Variaveis_de_ambiente.pdf) 
<br><br>
3. Solicitar chave pública e privada a alguém do time; 
<br>
4. Talvez seja necessário configurar o Lombok (Como no Eclipse). <br>
Basta seguir os passos: <i>https://dicasdejava.com.br/como-configurar-o-lombok-no-eclipse/ </i>
</p>

<hr>

<h3>Tecnologias e dependências</h3>
<p>
Java 17 <br>
Springboot 2.7.9 <br>
Flyway <br>
MySQL Connector <br>
Swagger 2.9.2 <br>
Lombok <br>
</p>
