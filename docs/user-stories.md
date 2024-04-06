# Esse eu já li!

O sistema que deve ser criado se chama "Esse eu já li!" e é um portal onde as pessoas podem marcar livros que já leram e e com isso ganharem pontos dentro de um sistema de gamificação. Ao fazerem o login no sistema, os usuários vêem uma lista de livros. Eles podem clicar para visualizar um livro e marcar que já leram aquele livro. Cada livro que leram eles ganham 1 ponto, sendo que a cada 100 páginas que o livro tiver ele vale um ponto adicional (exemplos: 72 páginas vale 1 ponto, 124 páginas vale 2 pontos, 350 páginas vale 4 pontos). A cada 5 livros que o usuário ler de um mesmo estilo, ele deve receber um troféu "Leitor de #estilo#"; por exemplo, se ele leu 5 livros do estilo "Ficção Científica", ganhará o troféu "Leitor de Ficção Científica". O sistema ainda deve ter um ranking que exibe os 10 usuários com maior pontuação e cada usuário pode entrar em uma página pessoal que irá mostrar os seus pontos e os troféus que conquistou.


## User Stories

### US 1. Como Leitor, quero me autenticar no sistema, para que para que eu possa salvar minhas informações e acompanhar minha leitura de livros

**Business value**: 8

#### Testes de aceitação

1. Dado que sou um leitor não autenticado
    1. Quando acesso a página de login
    1. Vejo um formulário de autenticação com campos para inserir meu nome de usuário e senha

1. Dado que sou um leitor autenticado
    1. Após me autenticar
    1. Acedo à página de lista de livros


### US 2. Como Leitor, quero visualizar uma lista de livros, para que eu possa escolher qual livro ler

**Business value**: 5

#### Testes de aceitação

1. Dado que sou um leitor autenticado
    1. Quando acesso a página de lista de livros
    1. Vejo uma lista de livros disponíveis para leitura

1. Dado que sou um leitor autenticado
    1. Quando acesso a página de lista de livros
    1. Cada livro na lista exibe o título, autor e uma breve descrição

1. Dado que sou um leitor autenticado
    1. Quando acesso a página de lista de livros
    1. Posso clicar em um livro para ver mais detalhes sobre ele


### US 3. Como Leitor, quero visualizar os detalhes de um livro, para obter mais informações sobre ele

**Business value**: 3

#### Testes de aceitação

1. Dado que sou um leitor autenticado
    1. Quando clico em um livro na página de lista de livros
    1. Sou redirecionado para a página de detalhes do livro

1. Dado que sou um leitor autenticado
    1. Quando estou na página de detalhes do livro
    1. Vejo o título, autor, descrição, número de páginas e se já li o livro

1. Dado que sou um leitor autenticado
    1. Quando estou na página de detalhes do livro
    1. Posso marcar o livro como lido


## US 4. Como Leitor, quero marcar a leitura de um livro, para acompanhar meu progresso de leitura

**Business value**: 5

#### Testes de aceitação

1. Dado que sou um leitor autenticado
    1. Quando estou na página de detalhes do livro
    1. Posso marcar o livro como lido

1. Dado que sou um leitor autenticado
    1. Quando marco o livro como lido na página de detalhes do livro
    1. Ganho pontos de acordo com a quantidade de páginas do livro

1. Dado que sou um leitor autenticado
    1. Quando leio pelo menos 5 livros do mesmo estilo
    1. Ganho o troféu "Leitor de <Estilo>"


## US 5. Como Leitor, quero visualizar o ranking de usuários, para ver os 10 usuários com maior pontuação

**Business value**: 3

#### Testes de aceitação

1. Dado que sou um leitor autenticado
    1. Quando acesso a página de ranking de usuários
    1. Vejo uma lista dos 10 usuários com maior pontuação


## US 6. Como Leitor, quero visualizar meus pontos e troféus, para acompanhar minha progressão no sistema de gamificação

**Business value**: 2

#### Testes de aceitação

1. Dado que sou um leitor autenticado
    1. Quando acesso a minha página pessoal
    1. Vejo a quantidade de pontos que acumulei

1. Dado que sou um leitor autenticado
    1. Quando acesso a minha página pessoal
    1. Vejo os troféus que conquistei


### US 7. Como Leitor, quero entrar em minha página de perfil, para visualizar meus pontos e troféus ganhos

**Business value**: 2

#### Testes de aceitação

1. Dado que sou um leitor autenticado
    1. Quando acesso a minha página de perfil
    1. Vejo a quantidade de pontos que acumulei

1. Dado que sou um leitor autenticado
    1. Quando acesso a minha página de perfil
    1. Vejo os troféus que conquistei
