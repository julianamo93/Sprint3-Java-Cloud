<<<<<<< HEAD
# 🦷 Smartooth AI 🦷

## ✨ Sobre

O Smartooth AI é um sistema desenvolvido em Java com o framework Spring Boot, focado na otimização de serviços odontológicos utilizando inteligência artificial e machine learning. O sistema visa oferecer uma experiência personalizada aos usuários, facilitando a escolha de procedimentos odontológicos e destacando planos de saúde disponíveis. Além disso, conta com um programa de pontos que incentiva a fidelização dos pacientes.

Neste repositório encontra-se a API responsável pelo gerenciamento de profissionais, pacientes, planos, e demais funcionalidades voltadas para a área odontológica.


## 🪐 Developers 

- [Sabrina Couto](https://github.com/sabrinacouto) 
- [Juliana Mo.](https://github.com/julianamo93) 
- [Kevin Nobre](https://github.com/KevinNobre) 

## Modelo DER

![diagramJavaSprint](https://media.discordapp.net/attachments/1273452588343230546/1292880955614167121/Relational_1_1.png?ex=670558b8&is=67040738&hm=b032a4f4354a2ddcde398b3c55f869d3814a1c46a8eaf709091387e722bae0e1&=&format=webp&quality=lossless&width=1101&height=662)



## Diagrama de Classes

![diagramJavaSprint](https://media.discordapp.net/attachments/1273452588343230546/1292880903823036548/Diagrama_de_Classe.jpg?ex=670558ac&is=6704072c&hm=0033ffce8f3e9ce27f89ce91280b08b58eb2710eb085e659c3b2ca564497fe1f&=&format=webp&width=1112&height=662)

## 💬 Rodando localmente

### Clone o projeto

```bash
  git clone https://github.com/sabrinacouto/SmartoothAI-API.git
```

### Configurar o Projeto no IntelliJ IDEA
<ul>
  <li>Selecione a opção "Open" e navegue até o diretório do projeto Smartooth AI clonado.</li>
  <li>Em Project Structure garanta que o SDK esteja para o Java 17.</li>
  <li>Verifique no IntelliJ se a aba do Gradle está presente na barra lateral.</li>
  <li>Caso o projeto não seja automaticamente reconhecido como Gradle, abra o arquivo build.gradle e aceite a importação do Gradle quando o IntelliJ perguntar.</li>
</ul>

### Configurar o JDBC para o Oracle Database
Configurar as credenciais do banco de dados Oracle no application.properties:
  ```bash
  spring.datasource.url=jdbc:oracle:thin:@//localhost:1521/XEPDB1
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver
```


### Rodando a Aplicação

Vá até a classe principal que possui a anotação @SpringBootApplication e clique no botão Run ou Debug no IntelliJ.


Utilize Postman ou Insomnia para realizar
as requisições das rotas com a URL:

```endpoint
   http://localhost:8080/
```
## 📌 Endpoints para RecomendacaoTrat

### GET - Retorna todas as recomendações
GET http://localhost:8080/recomendacoes

### GET - Retorna uma recomendação por ID
GET http://localhost:8080/recomendacoes/{id}

### POST - Cria uma nova recomendação
POST http://localhost:8080/recomendacoes 

### PUT - Edita uma recomendação existente
PUT http://localhost:8080/recomendacoes/{id} 

### DELETE - Deleta uma recomendação existente
DELETE http://localhost:8080/recomendacoes/{id}

## 📌 Endpoints para a entidade Plano

### GET - Retorna todos os planos
GET http://localhost:8080/planos

### GET - Retorna um plano por ID
GET http://localhost:8080/planos/{id}

### POST - Cria um novo plano
POST http://localhost:8080/planos 

### PUT - Atualiza um plano existente
PUT http://localhost:8080/planos/{id} 

### DELETE - Deleta um plano existente
DELETE http://localhost:8080/planos/{id}


## 📌 Endpoints para Profissional

### GET - Retorna um profissional por ID
GET http://localhost:8080/profissional/{id}

### POST - Cria um novo profissional
POST http://localhost:8080/profissional

### PUT - Atualiza um profissional existente
PUT http://localhost:8080/profissional/{id} 

### DELETE - Deleta um profissional existente
DELETE http://localhost:8080/profissional/{id}


## 📌 Endpoints para UsuarioPaciente

### GET - Retorna um paciente por ID
GET http://localhost:8080/usuarios/{id}

### POST - Cria um novo Pacinete
POST http://localhost:8080/usuarios

### PUT - Atualiza um Paciente existente
PUT http://localhost:8080/usuarios/{id} 

### DELETE - Deleta um Paciente existente
DELETE http://localhost:8080/usuarios/{id}


## 📍 Dependências
```gradle
dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-validation'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	runtimeOnly 'com.oracle.database.jdbc:ojdbc11'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation 'org.springframework.boot:spring-boot-starter-test'
	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
}
```

## 🔮 Tecnologias utilizadas

<br> <div align="center"> 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="70" height="70">
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/spring/spring-original-wordmark.svg" width="70" height="70"/> 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/oracle/oracle-original.svg" width="70" height="70" /> 
<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/gradle/gradle-plain-wordmark.svg" width="70" height="70" />
<br> 
</div> 
<br>

  
=======
# Sprint3-Java-Cloud
>>>>>>> 0a3ee5e3cf2ecba3ccc0a0ba0d316bf5bfb9549b
