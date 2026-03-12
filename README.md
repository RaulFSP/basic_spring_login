# basic_spring_login
Projeto de exemplo simples de **login session utilizando o spring security** com um banco em memória h2.
Essa aplicação demonstra como configurar um serviço de auntenticação básico e funcional que protege rotas usando Spring Security, JPA e um banco de dados leve para desenvolvimento.

## Sobre o login
É possível criar um usuário pela rota /usuarios por meio de um POST.

```
{
  "username" : "novo usuario",
  "password" : "senha",
  "email" : "algum@email.com"
}
```
Também já há um usuário cadastrado no sistema. Use **root** como username, e **123** senha no formulário de login.

## Tecnologias Usadas
| Tecnologia | Versão / Descrição |
|------------|---------------------|
| Java | Java 21 |
| Spring Boot | Base do projeto |
| Spring Security | Autenticação |
| Spring Data JPA | Persistência |
| H2 | Banco de dados em memória |
| Maven | Gerenciamento de dependências |
