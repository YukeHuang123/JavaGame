# set the the version of java jdk
FROM openjdk:17
LABEL authors="thu16_a3_d"

# set the working directory inside the container
WORKDIR /app

# copy files to the docker
COPY . /app

# make the Gradle Wrapper executable
RUN chmod +x gradlew

# download dependencies
RUN ./gradlew dependencies

# copy the rest project files
RUN ./gradlew build

#run the application
CMD["./gradlew","run"]