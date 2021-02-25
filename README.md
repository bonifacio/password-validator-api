# password-validator-api

Para executar o projeto local, é necessário executar o seguinte comando na pasta raiz do projeto:
```
./gradlew bootRun
```
Para executar os testes:
```
./gradlew test
```

Com isso a aplicação ficará disponível em http://localhost:8080.

Utilizando a api:
```
curl --location --request GET 'http://localhost:8080/password-validator/AbTp9!fok'
```

Pelo fato do projeto ser relativamente pequeno, optei por uma estrutura de pacotes mais simples:

    com.boni.passwordvalidatorapi
    ├── rest            # Contém o controller, tratamento de esceções e o contrato 
    └── validator       # Contém todas as classes de validação
        └── exception   # Contém as exceções lançadas nas validações

Os testes estão organizados numa estrutura parecida, sendo que no pacote `validator` estão os testes de unidade e no pacote `rest`estão os testes e2e.

###Solução:

Pelo fato de existir uma série de validações, que é um cenário onde pode surgir a necessidade de plugar ou remover validações, optei por implementar um validador chamado `PasswordValidator` que recebe uma lista de validadores do tipo `Validator`.

Cada validação feita na senha implementa a interface `Validator` e via injeção de dependência, são injetadas na classe `PasswordValidator`. Dessa forma, caso exista a necessidade incluir uma validação, basta implementar a disponibilizar com um `Bean`.

Apesar de estar claro que o retorno deve ser um boolean (e é esse o retorno final da api), optei por implementar de uma forma que todas as validações são feitas e os erros são colocados dentro de uma exception chamada `ListValidatorExeption`. Dessa forma, consigo logar os erros e caso surja a necessidade de em algum momento retornar os erros na api, a implementação já está contemplando.

Sobre o design da API, optei pela utilização do método `GET` pois é um cenário onde a app client vai enviar um dado para obter uma informação sobre o mesmo. Não existe a itenção de criar ou alterar algum recurso. Estando de acordo com a expecificação, o retorno é 200 (OK), senão o retorno é 400 (BAD REQUEST). Nos dois caso um body é retornado com o boolean conforme o enunciado. Porém, ao meu ver, poderíamos assumir o status code para a validação.
<b>Obs.: como é uma app para fazer a validação da senha, não me preocupei em passar a informação direta na url, visto que não existe a possibilidade de associar a senha a um usuário. Mas é um ponto de atenção.</b> 