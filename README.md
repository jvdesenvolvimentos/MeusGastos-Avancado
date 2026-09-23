# Aplicativo de Controle de Gastos Pessoais

## Informações do Aluno
* **Nome:** João Vitor da Silva Soares
* **Data de Entrega:** 23/09/2026

## Justificativa da Escolha do Tema
O tema "Controle de Gastos Pessoais" foi escolhido por sua alta utilidade prática no cotidiano, permitindo gerenciar despesas de forma simples, direta e organizada. Além disso, estruturalmente ele atende perfeitamente a todos os requisitos técnicos exigidos pela atividade, contemplando listagem de itens e formulário de cadastro.

## Descrição do Funcionamento do Aplicativo
O aplicativo foi desenvolvido utilizando Kotlin e Jetpack Compose, estruturado sob o padrão arquitetural MVVM (Model-View-ViewModel). Suas principais características incluem:
1. **Tela de Listagem (Tela 1):** Exibe todos os gastos cadastrados em formato de cartões (mostrando título, categoria, data e valor), calcula automaticamente o valor total acumulado e disponibiliza um botão flutuante para acessar a tela de cadastro.
2. **Tela de Cadastro (Tela 2):** Contém campos de texto formatados para inserção do título, valor, categoria e data do gasto, salvando as informações de forma persistente no dispositivo.
3. **Persistência Local (Room Database):** Todos os dados inseridos são salvos e consultados localmente utilizando o banco de dados Room, garantindo que as informações não sejam perdidas ao fechar o aplicativo.