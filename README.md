Postman Documentation: https://documenter.getpostman.com/view/16129790/TzY6AuVt

AWS Project Setup:

1. Logearse a la consola de AWS
2. Buscar e ingresar al servicio EC2
3. Click en Launch Instance
4. Vamos a seleccionar una imagen tipo: Amazon Linux 2 AMI, damos click en Select
5. Seleccionamos una instancia tipo: t2.micro
6. Seleccionamos Next: Configure Instance Details
7. Seleccionamos Next: Add Storage
8. Seleccionamos Next: Add Tags
9. Seleccionamos Next: Configure Security Group
10. Agregamos una nueva regla seleccionando Add Rule y habilitamos el puerto 8080 simplemente cambiano el Port Range de la nueva regla
11. Seleccionamos Review and Launch
12. Seleccionamos Launch
13. Nos pregunstara por una llave, en la primera opcion seleccionamos Create new key pair
  - En el siguiente cuadro escribimos el nombre de nuestra llave
  - Descarga la llave seleccionando Download Key Pair
14. Nos conectamos a la instancia seleccionandola de nuestro panel principal y damos click en connect
  - En la pestaña EC2 Instance Connect, damos click en Connect


Ingresamos los siguientes comandos
```
$ sudo yum update
```
Instalacion Git:
```
$ sudo yum install git -y
```
Descarga del Repositorio
```
$ git clone https://github.com/ngonza27/PokemonAPI.git
```
Instalacion JAVA:
```
$ sudo rpm --import https://yum.corretto.aws/corretto.key 
$ sudo curl -L -o /etc/yum.repos.d/corretto.repo https://yum.corretto.aws/corretto.repo
$ sudo yum install -y java-16-amazon-corretto-devel
$ cd
$ cd /opt
$ wget https://www-eu.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz
$ sudo tar xzf apache-maven-3.6.3-bin.tar.gz
$ sudo ln -s apache-maven-3.6.3 maven
$ sudo nano /etc/profile.d/maven.sh
```
Agregar las siguiente lineas dentro del archivo maven.sh

```
export M2_HOME=/opt/maven
export PATH=${M2_HOME}/bin:${PATH}
```

Para salirse del editor:
Ctrl+x -> y -> enter
```


$ source /etc/profile.d/maven.sh


$ cd PokemonAPI
$ mvn spring-boot:run

```

La aplicación se ejecutará y luego podrá probar su aplicación utilizando la URL de su instancia ec2 y la configuración del path de la API.

