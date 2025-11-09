FROM jenkins/jenkins:lts-jdk17
USER root

# Установка Docker
RUN apt-get update && \
    apt-get install -y curl && \
    curl -fsSL https://get.docker.com -o get-docker.sh && \
    sh get-docker.sh

# Установка Gradle
RUN apt-get update && \
    apt-get install -y gradle

# Добавление пользователя jenkins в группу docker
RUN usermod -aG docker jenkins

USER jenkins
