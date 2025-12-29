# twitter-clone – Ahmet Berkant Tülü

Twitter (X) benzeri bir sosyal medya uygulamasının **backend** tarafını içeren Spring Boot projesidir.

## 🚀 Kullanılan Teknolojiler
- Java 17
- Spring Boot
- Spring Web (REST API)
- Spring Data JPA
- Spring Security (Basic / JWT)
- PostgreSQL
- Lombok
- Maven

## 🏗️ Mimari
Proje katmanlı mimari prensiplerine göre geliştirilmiştir:

- Controller
- Service
- Repository
- DTO
- Entity
- Exception Handling
- Security Configuration

## 📌 Özellikler
- Kullanıcı kayıt & giriş işlemleri
- Tweet oluşturma, silme, listeleme
- Yorum (Comment) işlemleri
- Like & Retweet işlemleri
- Global Exception Handling
- CORS ve Security yapılandırmaları

## ⚙️ Kurulum
1. Projeyi klonla:
```bash
git clone https://github.com/Ahmetberkant/twitter-clone.git


spring.datasource.url=jdbc:postgresql://localhost:5432/twitterdb
spring.datasource.username=postgres
spring.datasource.password=*****


mvn spring-boot:run
