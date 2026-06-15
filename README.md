# StockFlow 📦

Sistema de gerenciamento de estoque genérico, desenvolvido com o objetivo de fornecer uma solução flexível para o controle de produtos, movimentações e solicitações relacionadas ao estoque, podendo ser adaptado para diferentes segmentos de mercado.

---

## 📋 Sobre o Projeto

O **StockFlow** é uma plataforma de gestão de estoque que busca centralizar e organizar os processos de controle de produtos, entradas e saídas de mercadorias e acompanhamento de ocorrências operacionais.

Diferentemente de sistemas voltados para um nicho específico, o StockFlow foi projetado para ser uma solução **genérica e extensível**, permitindo futuras adaptações para diferentes tipos de empresas e cenários de negócio.

---

## 🎯 Objetivos

* Centralizar o gerenciamento de estoque;
* Garantir rastreabilidade de movimentações;
* Controlar solicitações de entrada e retirada de produtos;
* Facilitar a identificação de divergências e problemas operacionais;
* Oferecer uma solução flexível e de fácil manutenção.

---

## 🚀 Funcionalidades

### Gestão de Produtos

* Cadastro de produtos;
* Organização por categorias;
* Controle de quantidade em estoque.

### Gestão de Estoque

* Consulta de estoque atual;
* Histórico de movimentações;
* Controle de entradas e saídas.

### Solicitações

* Solicitações de compra;
* Solicitações de retirada;
* Fluxo de aprovação de movimentações.

### Chamados de Estoque

* Registro de:

  * Falta de produtos;
  * Divergências de estoque;
  * Estoque baixo;
  * Outras ocorrências operacionais.

### Gestão de Usuários

* Controle de acesso baseado em cargos;
* Gerenciamento de permissões;
* Administração de usuários.

---

## 🧩 Modelo de Domínio

### Usuários

```text
Usuario
    │
    └── Cargo
        ├── Administrador
        ├── Gerente
        ├── Supervisor
        └── Encarregado
```

O sistema possui uma única entidade de usuário, sendo as responsabilidades definidas por meio de cargos.

### Responsabilidades por Cargo

| Cargo         | Responsabilidades                                                                                           |
| ------------- | ----------------------------------------------------------------------------------------------------------- |
| Administrador | Gerencia o sistema, usuários e categorias.                                                                  |
| Gerente       | Gerencia as solicitações de compra, realizando sua análise e aprovação.                                     |
| Supervisor    | Gerencia as operações de entrada e saída de itens do estoque, aprovando e supervisionando as movimentações. |
| Encarregado   | Verifica a chegada de itens ao estoque e realiza solicitações de retirada de produtos.                      |

---

### Produtos e Estoque

```text
Categoria
     │
     └── Produto
             │
             └── Estoque (1:1)
```

* **Produto:** entidade principal do sistema.
* **Categoria:** utilizada para organização dos produtos.
* **Estoque:** representa o estado atual do produto, armazenando a quantidade disponível.

---

### Movimentações

```text
MovimentacaoLote
        │
        └── ItemMovimentacao
```

Toda entrada ou saída de produtos é registrada por meio de uma movimentação, garantindo rastreabilidade e auditoria das operações.

---

### Solicitações

#### Solicitação de Compra

```text
SolicitacaoCompra
        │
        └── ItemSolicitacaoCompra
```

Após aprovação, gera uma:

```text
MovimentacaoLote (ENTRADA)
```

#### Solicitação de Retirada

```text
SolicitacaoRetirada
        │
        └── ItemSolicitacaoRetirada
```

Após aprovação, gera uma:

```text
MovimentacaoLote (SAÍDA)
```

---

### Chamados de Estoque

```text
ChamadoEstoque
        │
        └── ItemChamado
```

Utilizado para registrar ocorrências operacionais relacionadas ao estoque, como divergências, falta de produtos e níveis críticos de estoque.

---

## 🔄 Fluxo Simplificado

```text
Solicitação
      ↓
Aprovação
      ↓
Movimentação
      ↓
Atualização do Estoque
```

---

## 🛠️ Tecnologias Utilizadas

* Java
* Spring Boot
* Spring Data JPA
* Spring Security
* PostgreSQL
* Maven
* Hibernate
* JWT Authentication
* Docker (opcional)

---

## 📁 Estrutura do Projeto

```text
src
└── main
    └── java
        └── com.stockflow.StockFlowApi
            ├── usuario
            ├── categoria
            ├── produto
            ├── estoque
            ├── movimentacao
            ├── solicitacaoCompra
            ├── solicitacaoRetirada
            ├── chamado
            └── config
```

---

## 🎓 Objetivo Acadêmico

O projeto foi desenvolvido com fins acadêmicos e de aprendizado, aplicando conceitos de:

* Engenharia de Software;
* Banco de Dados;
* APIs REST;
* Padrões de Projeto;
* Boas práticas de desenvolvimento.

---
