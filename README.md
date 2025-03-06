# Price_Check

**Price_Check** é uma API desenvolvida para verificar, acompanhar e organizar o histórico de preços de peças de hardware em lojas online. Criei ela para funcionar de forma bem simples, pois pretendo montar um computador e quero monitor os preços ao decorrer das semanas para verificar se há algum padrão de preço. 
---

## Funcionalidades

- **Monitoramento de Preços**: Captura os preços de produtos em lojas online com base nas URLs e tags HTML configuradas.
- **Organização de Histórico**: Registra e organiza os preços em um banco de dados, permitindo a exibição de históricos agrupados por nome do produto.
- **Endpoints Documentados**: API documentada com **Swagger** para facilitar a integração e visualização dos recursos disponíveis.

---

## Tecnologias Utilizadas

- **Spring Boot**: Para a estrutura principal da API.
- **Spring Data JPA**: Para gerenciamento do banco de dados.
- **Jsoup**: Para web scraping e extração de preços das páginas HTML.
- **PostgreSQL**: Banco de dados relacional utilizado no projeto.
- **Swagger/OpenAPI**: Para documentação clara dos endpoints.
- **Lombok**: Para reduzir a verbosidade do código.


